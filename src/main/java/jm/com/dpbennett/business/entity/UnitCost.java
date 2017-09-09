/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "unitcost")
public class UnitCost implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department department;
    private String service;
    @Column(length = 1024)
    private String description;
    private Double cost;
    private String unit;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date effectiveDate;
    private Boolean effective;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Laboratory laboratory;
    @OneToOne(cascade = CascadeType.REFRESH)
    private DepartmentUnit departmentUnit;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Laboratory getLaboratory() {
        if (laboratory == null) {
            laboratory = new Laboratory();
        }
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public DepartmentUnit getDepartmentUnit() {
        if (departmentUnit == null) {
            departmentUnit = new DepartmentUnit();
        }
        return departmentUnit;
    }

    public String getLaboratoryAndDepartmentUnitName() {
        String combinedName = "";

        if (!getLaboratory().getName().equals("") && !getLaboratory().getName().equals("--")) {
            combinedName = combinedName + getLaboratory().getName();
            if (!getDepartmentUnit().getName().equals("") && !getDepartmentUnit().getName().equals("--")) {
                combinedName = combinedName + "/" + getDepartmentUnit().getName();
            }
        } else if (!getDepartmentUnit().getName().equals("") && !getDepartmentUnit().getName().equals("--")) {
            combinedName = combinedName + getDepartmentUnit().getName();
        }

        return combinedName;
    }

    public void setDepartmentUnit(DepartmentUnit departmentUnit) {
        this.departmentUnit = departmentUnit;
    }

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getService() {
        if (service == null) {
            service = "";
        }
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        if (description == null) {
            description = "";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        if (cost == null) {
            cost = new Double(0.0);
        }
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Department getDepartment() {
        if (department == null) {
            return new Department("");
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        if (!(object instanceof UnitCost)) {
            return false;
        }
        UnitCost other = (UnitCost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpbennett.entity.UnitCost[ id=" + id + " ]";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static List<UnitCost> findUnitCosts(
            EntityManager em,
            String originalDepartmentName,
            String originalSearchText) {

        List<UnitCost> foundUnitCosts;
        String searchQuery;
        String searchTextAndClause = "";
        String joinClause;
        String searchText;
        String departmentName;

        // NB: replace ' with '' to avoid SQL query error
        if (originalSearchText != null) {
            searchText = originalSearchText.replaceAll("'", "''");
        } else {
            searchText = "";
        }

        // NB: replace ' with '' to avoid SQL query error
        if (originalDepartmentName != null) {
            departmentName = originalDepartmentName.replaceAll("'", "''");
        } else {
            departmentName = "";
        }

        joinClause =
                " JOIN unitCost.department department"
                + " JOIN unitCost.laboratory laboratory"
                + " JOIN unitCost.departmentUnit departmentUnit";

        if (!searchText.equals("") && !departmentName.equals("")) {
            searchTextAndClause =
                    " AND ("
                    + " UPPER(unitCost.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(unitCost.service) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(unitCost.description) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(unitCost.unit) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(department.name) LIKE '%" + departmentName.toUpperCase() + "%'"
                    + " OR UPPER(laboratory.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(departmentUnit.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " )";
        } else if (searchText.equals("") && !departmentName.equals("")) {
            searchTextAndClause =
                    " AND ("
                    + " UPPER(department.name) LIKE '%" + departmentName.toUpperCase() + "%'"
                    + " )";
        } else if (!searchText.equals("") && departmentName.equals("")) {
            searchTextAndClause =
                    " AND ("
                    + " UPPER(unitCost.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(unitCost.service) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(unitCost.description) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(unitCost.unit) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(laboratory.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " OR UPPER(departmentUnit.name) LIKE '%" + searchText.toUpperCase() + "%'"
                    + " )";
        }

        searchQuery =
                "SELECT unitCost FROM UnitCost unitCost"
                + joinClause
                + " WHERE (0 = 0)" // used as place holder
                + searchTextAndClause
                + " ORDER BY unitCost.service DESC";
        try {
            foundUnitCosts = em.createQuery(searchQuery, UnitCost.class).getResultList();
            if (foundUnitCosts == null) {
                foundUnitCosts = new ArrayList<UnitCost>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<UnitCost>();
        }

        return foundUnitCosts;
    }

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
