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

package jm.com.dpbennett.business.entity.fm;

import jm.com.dpbennett.business.entity.hrm.DepartmentUnit;
import jm.com.dpbennett.business.entity.hrm.Department;
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
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.hrm.Laboratory;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
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

    @Override
    public String getDescription() {
        if (description == null) {
            description = "";
        }
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        if (cost == null) {
            cost = 0.0;
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
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
