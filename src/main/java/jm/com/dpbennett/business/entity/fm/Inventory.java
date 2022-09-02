/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2022  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.fm;

import jm.com.dpbennett.business.entity.sc.*;
import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
import java.text.Collator;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.hrm.Manufacturer;
import jm.com.dpbennett.business.entity.hrm.User;
import jm.com.dpbennett.business.entity.jmts.Job;
import jm.com.dpbennett.business.entity.pm.Supplier;
import jm.com.dpbennett.business.entity.rm.DatePeriod;
import jm.com.dpbennett.business.entity.sm.Category;
import jm.com.dpbennett.business.entity.sm.Product;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable, Comparable, BusinessEntity, Product {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Category category;
    private Long quantity;
    private Double unitCost;
    private Double cost;
    private String stockKeepingUnit;
    private String measurementUnit;
    private String valuationMethod;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private MarketProduct product;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateChecked;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date timeChecked;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Supplier supplier;
    private String batchCode;
    private String dateMark;
    private String status;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    @Transient
    private Boolean isDirty;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
     public static List<Inventory> find(
            EntityManager em,
            String searchText,
            Integer maxResults) {

        List<Inventory> foundInventory;
        String searchQuery;
        String searchTextAndClause;
        String selectClause = "SELECT inventory FROM Inventory inventory";
        String mainJoinClause = " JOIN inventory.category category"
                + " JOIN inventory.manufacturer manufacturer"
                + " JOIN job.department department"
                + " JOIN job.subContractedDepartment subContractedDepartment"
                + " LEFT JOIN job.department.staff staff"
                + " LEFT JOIN job.subContractedDepartment.staff staff2"
                + " JOIN job.classification classification"
                + " JOIN job.sector sector"
                + " JOIN job.client client"
                + " JOIN job.jobCategory jobCategory"
                + " JOIN job.jobSubCategory jobSubCategory"
                + " JOIN job.assignedTo assignedTo"
                + " JOIN job.jobCostingAndPayment jobCostingAndPayment"
                + " LEFT JOIN job.jobSamples jobSamples"
                + " LEFT JOIN job.representatives representatives";

        if (estimate) {
            costEstimateSubclause = " AND (jobCostingAndPayment.estimate = 1)";
        } else {
            costEstimateSubclause = " AND (jobCostingAndPayment.estimate IS NULL OR jobCostingAndPayment.estimate = 0)";
        }

        if (searchText != null) {
            searchText = searchText.trim().replaceAll("'", "''");
        } else {
            searchText = "";
        }
        String mainSearchWhereClause = " UPPER(businessOffice.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(business.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(subContractedDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(job.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(job.reportNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(job.comment) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobStatusAndTracking.statusNote) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(job.instructions) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(sector.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(client.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobCategory.category) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSubCategory.subCategory) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(assignedTo.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(assignedTo.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(assignedTo.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobCostingAndPayment.invoiceNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobCostingAndPayment.purchaseOrderNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSamples.reference) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSamples.description) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSamples.productBrand) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSamples.name) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSamples.productModel) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSamples.productSerialNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                + " OR UPPER(jobSamples.productCode) LIKE '%" + searchText.toUpperCase() + "%'";

        String datePeriodSubClause = "jobStatusAndTracking." + dateSearchPeriod.getDateField() + " >= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getStartDate(), "'", "YMD", "-")
                + " AND jobStatusAndTracking." + dateSearchPeriod.getDateField() + " <= " + BusinessEntityUtils.getDateString(dateSearchPeriod.getEndDate(), "'", "YMD", "-");

        // Build query based on search type
        switch (searchType) {
            case "Appr'd & uninv'd jobs":
                searchTextAndClause
                        = " AND subContractedDepartment.name = '--' AND ("
                        + mainSearchWhereClause
                        + " )";
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " AND jobStatusAndTracking.workProgress NOT LIKE 'Cancelled'"
                        + " AND (jobCostingAndPayment.costingApproved = 1)"
                        + " AND (classification.isEarning = 1)"
                        + " AND (jobCostingAndPayment.invoiced IS NULL OR jobCostingAndPayment.invoiced = 0)" + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Unapproved job costings":
                searchTextAndClause
                        = " AND ("
                        + mainSearchWhereClause
                        + " )";
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " AND jobStatusAndTracking.workProgress NOT LIKE 'Cancelled'"
                        + " AND (jobCostingAndPayment.costingApproved IS NULL OR jobCostingAndPayment.costingApproved = 0)" + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Incomplete jobs":
                searchTextAndClause
                        = " AND ("
                        + mainSearchWhereClause
                        + " )";
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " AND jobStatusAndTracking.workProgress NOT LIKE 'Completed' AND jobStatusAndTracking.workProgress NOT LIKE 'Cancelled'" + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Invoiced jobs":
                searchTextAndClause
                        = " AND subContractedDepartment.name = '--' AND ("
                        + mainSearchWhereClause
                        + " )";
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " AND jobStatusAndTracking.workProgress NOT LIKE 'Cancelled'"
                        + " AND (jobCostingAndPayment.costingApproved = 1)"
                        + " AND (classification.isEarning = 1)"
                        + " AND (jobCostingAndPayment.invoiced = 1)" + ")"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Parent jobs only":
                searchTextAndClause
                        = " AND subContractedDepartment.name = '--' AND ("
                        + mainSearchWhereClause
                        + " )";
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " )"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "General":
                searchTextAndClause
                        = " AND ("
                        + mainSearchWhereClause
                        + " )";
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " )"
                        + searchTextAndClause
                        + " ORDER BY job.id DESC";
                break;
            case "Jobs in period":
                searchQuery
                        = selectClause
                        + " JOIN job.jobStatusAndTracking jobStatusAndTracking"
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " ORDER BY job.id DESC";
                break;
            case "Monthly report":
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " AND ( UPPER(department.name) = '" + searchText.toUpperCase() + "'"
                        + " OR UPPER(subContractedDepartment.name) = '" + searchText.toUpperCase() + "'"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            case "My department's jobs":
                searchTextAndClause
                        = " AND ("
                        + mainSearchWhereClause
                        + " )";
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " )"
                        + searchTextAndClause
                        + " AND ( UPPER(department.name) LIKE '%" + user.getEmployee().getDepartment().getName().toUpperCase() + "%'"
                        + " OR UPPER(subContractedDepartment.name) LIKE '%" + user.getEmployee().getDepartment().getName().toUpperCase() + "%'"
                        + " OR (UPPER(staff.lastName) LIKE '%" + user.getEmployee().getLastName().toUpperCase() + "%'"
                        + " AND UPPER(staff.firstName) LIKE '%" + user.getEmployee().getFirstName().toUpperCase() + "%')"
                        + " OR (UPPER(staff2.lastName) LIKE '%" + user.getEmployee().getLastName().toUpperCase() + "%'"
                        + " AND UPPER(staff2.firstName) LIKE '%" + user.getEmployee().getFirstName().toUpperCase() + "%')"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            case "My jobs":
                searchTextAndClause
                        = " AND ("
                        + mainSearchWhereClause
                        + " )";
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " )"
                        + searchTextAndClause
                        + " AND ( (UPPER(assignedTo.lastName) LIKE '%" + user.getEmployee().getLastName().toUpperCase() + "%'"
                        + " AND UPPER(assignedTo.firstName) LIKE '%" + user.getEmployee().getFirstName().toUpperCase() + "%')"
                        + " OR (UPPER(representatives.lastName) LIKE '%" + user.getEmployee().getLastName().toUpperCase() + "%'"
                        + " AND UPPER(representatives.firstName) LIKE '%" + user.getEmployee().getFirstName().toUpperCase() + "%')"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            case "Jobs for my department":
                searchText = user.getEmployee().getDepartment().getName().replaceAll("'", "''");
                searchQuery
                        = selectClause
                        + mainJoinClause
                        + " WHERE (" + datePeriodSubClause
                        + costEstimateSubclause
                        + " AND ( UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(subContractedDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )"
                        + " ORDER BY job.id DESC";
                break;
            default:
                System.out.println("Default search to be implemented");

                break;
        }

        try {
            if (maxResults == 0) {
                foundJobs = em.createQuery(searchQuery, Job.class).getResultList();
            } else {
                foundJobs = em.createQuery(searchQuery, Job.class).setMaxResults(maxResults).getResultList();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundJobs;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getValuationMethod() {
        return valuationMethod;
    }

    public void setValuationMethod(String valuationMethod) {
        this.valuationMethod = valuationMethod;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public Supplier getSupplier() {
        if (supplier == null) {
            supplier = new Supplier();
        }
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }
        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    public MarketProduct getProduct() {
        if (product == null) {
            product = new MarketProduct();
        }
        return product;
    }

    public void setProduct(MarketProduct product) {
        this.product = product;
    }

    public Employee getEnteredBy() {
        if (enteredBy == null) {
            return new Employee();
        }
        return enteredBy;
    }

    public void setEnteredBy(Employee enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Category getCategory() {
        if (category == null) {
            return new Category();
        }
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        if (type == null) {
            type = "";
        }
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Manufacturer getManufacturer() {
        if (manufacturer == null) {
            return new Manufacturer();
        }

        return manufacturer;
    }

    @Override
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Date getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(Date dateChecked) {
        this.dateChecked = dateChecked;
    }

    public String getDateMark() {
        return dateMark;
    }

    public void setDateMark(String dateMark) {
        this.dateMark = dateMark;
    }

    public Date getTimeChecked() {
        return timeChecked;
    }

    public void setTimeChecked(Date timeChecked) {
        this.timeChecked = timeChecked;
    }

    @Override
    public int compareTo(Object o) {
        String thisIdStr = (this.getId() == null ? "" + Integer.MAX_VALUE : this.getId().toString());
        String oIdStr = (((Inventory) o).getId() == null ? "" + Integer.MAX_VALUE : ((Inventory) o).getId().toString());

        return Collator.getInstance().compare(thisIdStr, oIdStr);
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Inventory not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
