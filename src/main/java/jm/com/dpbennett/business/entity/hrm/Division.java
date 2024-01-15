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
package jm.com.dpbennett.business.entity.hrm;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "division")
@NamedQueries({
    @NamedQuery(name = "findAllDivisions", query = "SELECT e FROM Division e ORDER BY e.name")
})
public class Division implements BusinessEntity, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String type;
    private String notes;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Department> departments;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Subgroup> subgroups;
    private Boolean active;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee head;
    @Transient
    private Boolean isDirty;

    public Division() {
        this.name = "";
        this.code = "";
        this.type = "";
        this.notes = "";
        this.departments = new ArrayList<>();
        this.subgroups = new ArrayList<>();
    }

    public Division(String name) {
        this.name = name;
        this.code = "";
        this.type = "";
        this.notes = "";
        this.departments = new ArrayList<>();
        this.subgroups = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public static Employee findHeadOfActiveDivistionByDepartment (EntityManager em,
            Department department) {
        
        List<Division> activeDivistions = Division.findAllActive(em);
        
        for (Division activeDivistion : activeDivistions) {
            List<Department> departments = activeDivistion.getDepartments();
            for (Department department1 : departments) {
                if (Objects.equals(department1.getId(), department.getId())) {
                    return activeDivistion.getHead();
                }
            }
        }
       
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Subgroup> getSubgroups() {
         if (subgroups == null) {
            subgroups = new ArrayList<>();
        }
        
        return subgroups;
    }

    public void setSubgroups(List<Subgroup> subgroups) {
        this.subgroups = subgroups;
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

    public Employee getHead() {
       
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
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

    public List<Department> getDepartments() {
        if (departments == null) {
            departments = new ArrayList<>();
        }

        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getDepartmentList() {
        String listStr = "";
        int index = 0;

        for (Department department : departments) {
            if (index == 0) {
                listStr = department.getName();
            } else {
                listStr = listStr + ", " + department.getName();
            }
            ++index;
        }

        return listStr;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Division)) {
            return false;
        }
        Division other = (Division) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    public static Division findById(EntityManager em, Long Id) {

        try {
            Division division = em.find(Division.class, Id);

            return division;
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * Get the first division that matches the given name
     *
     * @param em
     * @param name
     * @return
     */
    public static Division findByName(EntityManager em, String name) {

        try {
           
            List<Division> divisions = em.createQuery("SELECT d FROM Division d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + name.toUpperCase() + "'", Division.class).getResultList();

            if (!divisions.isEmpty()) {
                return divisions.get(0);
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Division findDefault(EntityManager em,
            String name,
            Boolean useTransaction) {

        Division division = Division.findByName(em, name);

        if (division == null) {
            division = new Division(name);

            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, division);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, division);
            }
        }

        return division;
    }

    public static List<Division> findAll(EntityManager em) {

        try {
            return em.createNamedQuery("findAllDivisions", Division.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Division> findAllByName(EntityManager em, String name) {

        try {
            
            List<Division> divisions
                    = em.createQuery("SELECT d FROM Division d where UPPER(d.name) like '%"
                            + name.toUpperCase().trim() + "%' ORDER BY d.name", Division.class).getResultList();
            return divisions;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<Division> findAllActiveByName(EntityManager em, String name) {

        try {
          
            List<Division> divisions
                    = em.createQuery("SELECT d FROM Division d where UPPER(d.name) like '%"
                            + name.toUpperCase().trim() + "%' AND d.active = 1 ORDER BY d.name", Division.class).getResultList();
            return divisions;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Division> findAllActive(EntityManager em) {

        try {
            return em.createQuery("SELECT d FROM Division d WHERE d.active = 1 ORDER BY d.name", Division.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
     /**
     * Finds the first division that contains the specified subgroup.
     * 
     * @param em
     * @param subgroup
     * @return 
     */
    public static Division findBySubgroup(EntityManager em, Subgroup subgroup) {

        try {

            List<Division> divisions
                    = em.createQuery(
                            "SELECT d FROM Division d"
                            + " JOIN d.subgroups subgroups"
                            + " WHERE subgroups.id = " + subgroup.getId(), 
                            Division.class).getResultList();

            if (!divisions.isEmpty()) {
                
                return divisions.get(0);
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
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

        return new ReturnMessage(false, "Division not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
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
