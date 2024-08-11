/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.pm;

import jm.com.dpbennett.business.entity.hrm.Employee;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.hrm.EmployeePosition;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "procurementmethod")
@NamedQueries({
    @NamedQuery(name = "findAllProcurementMethods",
            query = "SELECT p FROM ProcurementMethod p ORDER BY p.procurementMethod")
})
public class ProcurementMethod implements BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1024)
    private String description;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    @Transient
    private Boolean isDirty;
    @Transient
    private String editStatus;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<EmployeePosition> requiredSignatoryPositions;
    private String procurementMethod;
    private Double lowerSigningLimit;
    private Double upperSigningLimit;
    private Integer quotationsRequired;
    private Boolean active;

    /**
     * Default constructor.
     */
    public ProcurementMethod() {
        requiredSignatoryPositions = new ArrayList<>();
        description = "";
    }
    
    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setUsable(String usable) {
        if (usable.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
    }

    public Integer getQuotationsRequired() {
        if (quotationsRequired == null) {
            quotationsRequired = 1;
        }
        return quotationsRequired;
    }

    public void setQuotationsRequired(Integer quotationsRequired) {
        this.quotationsRequired = quotationsRequired;
    }

    @Override
    public Boolean getActive() {
        if (active == null) {
            active = true;
        }
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getLowerSigningLimit() {
        return lowerSigningLimit;
    }

    public void setLowerSigningLimit(Double lowerSigningLimit) {
        this.lowerSigningLimit = lowerSigningLimit;
    }

    public Double getUpperSigningLimit() {
        return upperSigningLimit;
    }

    public void setUpperSigningLimit(Double upperSigningLimit) {
        this.upperSigningLimit = upperSigningLimit;
    }

    public String getProcurementMethod() {
        return procurementMethod;
    }

    public void setProcurementMethod(String procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    /**
     * Splits the description into three(3) parts.
     *
     * @param part1Length
     * @param part2Length
     * @param part3Length
     * @return
     */
    public String[] splitDescription(int part1Length, int part2Length, int part3Length) {
        int descriptionLength = getDescription().length();
        String[] descriptionParts = {"", "", ""};

        // Get part 1
        if (part1Length <= descriptionLength) {
            descriptionParts[0] = getDescription().substring(0, part1Length);
        }
        // Get part 2
        if ((part1Length + part2Length) <= descriptionLength) {
            descriptionParts[1] = getDescription().substring(part1Length,
                    (part1Length + part2Length));
        }
        // Get part 3
        if ((part1Length + part2Length + part3Length) <= descriptionLength) {
            descriptionParts[2] = getDescription().substring((part1Length + part2Length),
                    (part1Length + part2Length + part3Length));
        }

        return descriptionParts;
    }

    public void clean() {
        setIsDirty(false);
    }

    public List<EmployeePosition> getRequiredSignatoryPositions() {
        return requiredSignatoryPositions;
    }

    public void setRequiredSignatoryPositions(List<EmployeePosition> requiredSignatoryPositions) {
        this.requiredSignatoryPositions = requiredSignatoryPositions;
    }

    public List<EmployeePosition> getAllSortedRequiredSignatoryPositions() {

        Collections.sort(getRequiredSignatoryPositions());

        return requiredSignatoryPositions;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    @Override
    public Date getDateEdited() {
        return dateEdited;
    }

    @Override
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

    @Override
    public Employee getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }
        return editedBy;
    }

    public void setEditedBy(Employee employee) {
        editedBy = employee;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof ProcurementMethod)) {
            return false;
        }
        ProcurementMethod other = (ProcurementMethod) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Purchase Requisition (" + getProcurementMethod() + ")";
    }

    @Override
    public String getName() {
        return procurementMethod;
    }

    @Override
    public void setName(String name) {
        procurementMethod = name;
    }

    public static ProcurementMethod findById(EntityManager em, Long Id) {

        return em.find(ProcurementMethod.class, Id);
    }

    public static List<ProcurementMethod> findAll(EntityManager em) {

        try {
            return em.createNamedQuery("findAllProcurementMethods", ProcurementMethod.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<ProcurementMethod> findAllByName(
            EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
           
            List<ProcurementMethod> procurementMethods
                    = em.createQuery("SELECT p FROM ProcurementMethod p WHERE UPPER(p.procurementMethod) LIKE '%"
                            + value.toUpperCase().trim() + "%' ORDER BY p.procurementMethod", ProcurementMethod.class).getResultList();
            
            return procurementMethods;
            
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<ProcurementMethod> findAllActiveByName(
            EntityManager em, String value) {

        try {            
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
           
            List<ProcurementMethod> procurementMethods
                    = em.createQuery("SELECT p FROM ProcurementMethod p WHERE (p.active = 1) AND UPPER(p.procurementMethod) LIKE '%"
                            + value.toUpperCase().trim() + "%' ORDER BY p.procurementMethod", ProcurementMethod.class).getResultList();
            
            return procurementMethods;
            
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {

        try {
            
            getEditedBy().save(em);
            
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();

        } catch (Exception e) {
            return new ReturnMessage(false,
                    "Procurement Method Save Error Occurred!",
                    "An error occurred while saving procurement method"
                    + "\n" + e,
                    Message.SEVERITY_ERROR_NAME);
        }

    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
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
