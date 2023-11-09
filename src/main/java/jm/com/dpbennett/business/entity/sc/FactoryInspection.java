/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2023  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sc;

import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.hrm.BusinessOffice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.hrm.Address;
import jm.com.dpbennett.business.entity.hrm.Manufacturer;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "factoryinspection")
public class FactoryInspection implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inspectionDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inspectionStartTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inspectionEndTime;
    @Column(length = 1024)
    private String workInProgress;
    private String workProgress;
    private String inspectionType;
    @Column(length = 1024)
    private String generalComments;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignedInspector;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Address address;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Contact factoryRepresentative;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<FactoryInspectionComponent> inspectionComponents;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<ProductInspection> productInspections;
    private String name;
    private Integer maxDaysForCompliance;
    private String actionsTaken;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice businessOffice;
    private String jobNumber;
    @Transient
    private Boolean isDirty;
    @Transient
    private String editStatus;

    public FactoryInspection() {
        inspectionComponents = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsJobNumberValid() {
        return !getJobNumber().isEmpty();
    }

    public static List<Object[]> getReportRecords(
            EntityManager em,
            String startDate,
            String endDate,
            Long departmentId) {

        String reportSQL = "SELECT DISTINCT"
                + "     factoryinspection.`JOBNUMBER`," // 0 - Job number 
                + "     assignedInspector.`NAME`," // 1 - Assigned inspector
                + "     factoryinspection.`GENERALCOMMENTS`," // 2 - General comments                  
                + "     businessoffice.`NAME`," // 3 - Business office   
                + "     manufacturer.`NAME`," // 4 - Manufacturer  
                + "     factoryinspection.`INSPECTIONDATE`," // 5 - Inspection date
                + "     factoryinspection.`WORKPROGRESS`," // 6 - Work progress 
                + "     factoryinspection.`WORKINPROGRESS`," // 7 - Work in progress
                + "     SUM(DISTINCT productinspection.`QUANTITY`)," // 8 - Product quantity
                + "     factoryinspection.`ID`" // 9 - ID
                + " FROM"
                + "     factoryinspection"
                + "     LEFT JOIN `employee` assignedInspector ON factoryinspection.`ASSIGNEDINSPECTOR_ID` = assignedInspector.`ID`"
                + "     LEFT JOIN `manufacturer` manufacturer ON factoryinspection.`MANUFACTURER_ID` = manufacturer.`ID`"
                + "     LEFT JOIN `businessoffice` businessoffice ON factoryinspection.`BUSINESSOFFICE_ID` = businessoffice.`ID`"
                + "     LEFT JOIN `factoryinspection_productinspection` factoryinspection_productinspection ON factoryinspection.`ID` = factoryinspection_productinspection.`FactoryInspection_ID`"
                + "     LEFT JOIN `productinspection` productinspection ON factoryinspection_productinspection.`productInspections_ID` = productinspection.`ID`"
                + " WHERE"
                + "     (factoryinspection.`INSPECTIONDATE` >= " + startDate
                + " AND factoryinspection.`INSPECTIONDATE` <= " + endDate + ")"
                + " GROUP BY"
                + "     factoryinspection.`ID`"
                + " ORDER BY"
                + "     factoryinspection.`ID` DESC";

        try {
            return em.createNativeQuery(reportSQL).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

    }

    public String getJobNumber() {
        if (jobNumber == null) {
            jobNumber = "";
        }
        
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public List<ProductInspection> getProductInspections() {
        if (productInspections != null) {
            Collections.sort(productInspections);
        } else {
            productInspections = new ArrayList<>();
        }

        return productInspections;
    }

    public void setProductInspections(List<ProductInspection> productInspections) {
        this.productInspections = productInspections;
    }

    public static List<FactoryInspection> findFactoryInspectionsByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate,
            int maxResults) {

        List<FactoryInspection> foundFactoryInspections;
        String searchQuery = null;
        String searchTextAndClause = "";
        String joinClause;
        String searchText;

        if (originalSearchText != null) {
            searchText = originalSearchText.replaceAll("'", "''").replaceAll("&amp;", "&");
        } else {
            searchText = "";
        }

        joinClause
                = " LEFT JOIN factoryinspection.assignedInspector assignedInspector"
                + " LEFT JOIN factoryinspection.manufacturer manufacturer";

        switch (searchType) {
            case "General":
                if (!searchText.equals("")) {
                    searchTextAndClause
                            = " AND ("
                            + " UPPER(factoryinspection.workInProgress) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(factoryinspection.workProgress) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(factoryinspection.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(factoryinspection.inspectionType) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(factoryinspection.generalComments) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(factoryinspection.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(factoryinspection.actionsTaken) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(assignedInspector.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(assignedInspector.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(manufacturer.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " )";
                }
                if ((startDate == null) || (endDate == null)) {
                    searchQuery
                            = "SELECT DISTINCT factoryInspection FROM FactoryInspection factoryInspection"
                            + joinClause
                            + " WHERE (0 = 0)" // used as place holder
                            + searchTextAndClause
                            + " ORDER BY factoryInspection.id DESC";
                } else {
                    searchQuery
                            = "SELECT DISTINCT factoryInspection FROM FactoryInspection factoryInspection"
                            + joinClause
                            + " WHERE (factoryInspection." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                            + " AND factoryInspection." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                            + searchTextAndClause
                            + " ORDER BY factoryInspection.id DESC";
                }
                break;
            case "?":
                break;
        }

        try {
            foundFactoryInspections = em.createQuery(searchQuery, FactoryInspection.class).
                    setMaxResults(maxResults).getResultList();
            if (foundFactoryInspections == null) {
                foundFactoryInspections = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundFactoryInspections;
    }

    public static List<FactoryInspection> findFactoryInspectionsByName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<FactoryInspection> factoryInspections
                    = em.createQuery("SELECT f FROM FactoryInspection f WHERE UPPER(f.name) LIKE '%"
                            + value.toUpperCase().trim() + "%' ORDER BY f.name", FactoryInspection.class).getResultList();

            return factoryInspections;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static FactoryInspection findFactoryInspectionByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<FactoryInspection> factoryInspections = em.createQuery("SELECT f FROM FactoryInspection f "
                    + "WHERE UPPER(f.name) "
                    + "= '" + value.toUpperCase() + "'", FactoryInspection.class).getResultList();

            if (!factoryInspections.isEmpty()) {
                return factoryInspections.get(0);
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String getWorkProgress() {
        return workProgress;
    }

    public void setWorkProgress(String workProgress) {
        this.workProgress = workProgress;
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

        if (isDirty) {
            setEditStatus("(edited)");
        } else {
            setEditStatus("        ");
        }
    }

    public Address getAddress() {
        if (address == null) {
            return new Address();
        }
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Manufacturer getManufacturer() {
        if (manufacturer == null) {
            return new Manufacturer();
        }
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BusinessOffice getBusinessOffice() {
        return businessOffice;
    }

    public void setBusinessOffice(BusinessOffice businessOffice) {
        this.businessOffice = businessOffice;
    }

    /**
     * Get all the entities contained in lists in this class
     *
     * @return
     */
    public List<BusinessEntity> getAllBusinessEntitiesLists() {
        ArrayList<BusinessEntity> entities = new ArrayList<>();

        entities.addAll(inspectionComponents);

        return entities;
    }

    public String getActionsTaken() {
        return actionsTaken;
    }

    public void setActionsTaken(String actionsTaken) {
        this.actionsTaken = actionsTaken;
    }

    public Integer getMaxDaysForCompliance() {
        return maxDaysForCompliance;
    }

    public void setMaxDaysForCompliance(Integer maxDaysForCompliance) {
        this.maxDaysForCompliance = maxDaysForCompliance;
    }

    public Employee getAssignedInspector() {
        return assignedInspector;
    }

    public void setAssignedInspector(Employee assignedInspector) {
        this.assignedInspector = assignedInspector;
    }

    public Contact getFactoryRepresentative() {
        if (factoryRepresentative == null) {
            return new Contact();
        }
        return factoryRepresentative;
    }

    public void setFactoryRepresentative(Contact factoryRepresentative) {
        this.factoryRepresentative = factoryRepresentative;
    }

    public String getGeneralComments() {
        return generalComments;
    }

    public void setGeneralComments(String generalComments) {
        this.generalComments = generalComments;
    }

    public List<FactoryInspectionComponent> getInspectionComponents() {
        if (inspectionComponents == null) {
            inspectionComponents = new ArrayList<>();
        }

        return inspectionComponents;
    }

    public List<FactoryInspectionComponent> getAllSortedFactoryInspectionComponents() {

        Collections.sort(getInspectionComponents());

        return inspectionComponents;
    }

    public void setInspectionComponents(List<FactoryInspectionComponent> inspectionComponents) {
        this.inspectionComponents = inspectionComponents;
    }

    public List<FactoryInspectionComponent> getInspectionComponentsByCategory(String category) {
        ArrayList<FactoryInspectionComponent> components = new ArrayList<>();

        for (FactoryInspectionComponent factoryInspectionComponent : inspectionComponents) {
            if (factoryInspectionComponent.getCategory() != null) {
                if (factoryInspectionComponent.getCategory().equals(category)) {
                    components.add(factoryInspectionComponent);
                }
            }
        }
        return components;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Date getInspectionEndTime() {
        return inspectionEndTime;
    }

    public void setInspectionEndTime(Date inspectionEndTime) {
        this.inspectionEndTime = inspectionEndTime;
    }

    public Date getInspectionStartTime() {
        return inspectionStartTime;
    }

    public void setInspectionStartTime(Date inspectionStartTime) {
        this.inspectionStartTime = inspectionStartTime;
    }

    public String getInspectionType() {
//        if (inspectionType == null) {
//            inspectionType = "Routine";
//        }
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getWorkInProgress() {
        return workInProgress;
    }

    public void setWorkInProgress(String workInProgress) {
        this.workInProgress = workInProgress;
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
        if (!(object instanceof FactoryInspection)) {
            return false;
        }
        FactoryInspection other = (FactoryInspection) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            // Save inspection components
            if (!getInspectionComponents().isEmpty()) {
                for (FactoryInspectionComponent inspectionComponent : getInspectionComponents()) {
                    if ((inspectionComponent.getIsDirty() || inspectionComponent.getId() == null)
                            && !inspectionComponent.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Inspection component save error occurred",
                                "An error occurred while saving an Inspection component",
                                Message.SEVERITY_ERROR_NAME);
                    }
                }
            }

            // Save product inspections
            if (!getProductInspections().isEmpty()) {
                for (ProductInspection productInspection : getProductInspections()) {
                    if ((productInspection.getIsDirty() || productInspection.getId() == null)
                            && !productInspection.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Product save error occurred",
                                "An error occurred while saving a product",
                                Message.SEVERITY_ERROR_NAME);
                    }
                }
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Factory inspection not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
