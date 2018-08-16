/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "subgroup")
@XmlRootElement
public class Subgroup implements BusinessEntity, Comparable, Serializable {

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
    private Boolean active;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee head;
    @Transient
    private Boolean isDirty;

    public Subgroup() {
        this.name = "";
        this.code = "";
        this.type = "";
        this.notes = "";
        this.departments = new ArrayList<>();
    }

    public Subgroup(String name) {
        this.name = name;
        this.code = "";
        this.type = "";
        this.notes = "";
        this.departments = new ArrayList<>();
    }

    public Boolean getActive() {
        if (active == null) {
            active = true;
        }

        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Employee getHead() {
        if (head == null) {
            head = new Employee("--", "--");
        }
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    public List<Department> getDepartments() {
        if (departments == null) {
            departments = new ArrayList<>();
        }
        return departments;
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

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subgroup)) {
            return false;
        }
        Subgroup other = (Subgroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
   
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    public static Subgroup findById(EntityManager em, Long id) {

        try {
            Subgroup subgroup = em.find(Subgroup.class, id);
            return subgroup;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Get the first subgroup that matches the given name
     * @param em
     * @param name
     * @return 
     */
    public static Subgroup findByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<Subgroup> subgroups = em.createQuery("SELECT s FROM Subgroup s "
                    + "WHERE UPPER(s.name) "
                    + "= '" + newName.toUpperCase() + "'", Subgroup.class).getResultList();
            if (subgroups.size() > 0) {
                return subgroups.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Subgroup findDefault(EntityManager em,
            String name,
            Boolean useTransaction) {
        Subgroup subgroup = Subgroup.findByName(em, name);

        if (subgroup == null) {
            subgroup = new Subgroup();
            subgroup.setName(name);

            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, subgroup);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, subgroup);
            }
        }

        return subgroup;
    }

    public static List<Subgroup> findAll(EntityManager em) {

        try {
            return em.createQuery("SELECT s FROM Subgroup s ORDER BY s.name", Subgroup.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<Subgroup> findAllActive(EntityManager em) {

        try {
            return em.createQuery("SELECT s FROM Subgroup s WHERE s.active = 1 ORDER BY s.name", Subgroup.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Subgroup> findAllByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Subgroup> subgroups
                    = em.createQuery("SELECT s FROM Subgroup s where UPPER(s.name) like '%"
                            + newName.toUpperCase().trim() + "%' ORDER BY s.name", Subgroup.class).getResultList();
            return subgroups;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
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

        return new ReturnMessage(false, "Subgroup not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
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

}
