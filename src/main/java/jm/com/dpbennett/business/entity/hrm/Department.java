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

import jm.com.dpbennett.business.entity.jmts.Job;
import java.io.Serializable;
import java.text.Collator;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.fm.JobCategory;
import jm.com.dpbennett.business.entity.auth.Privilege;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "department")
@NamedQueries({
    @NamedQuery(name = "findAllDepartments", query = "SELECT e FROM Department e ORDER BY e.name"),
    @NamedQuery(name = "findAllActiveDepartments", query = "SELECT e FROM Department e WHERE e.active = 1 ORDER BY e.name"),
    @NamedQuery(name = "findBySubGroupCode", query = "SELECT e FROM Department e WHERE e.code = :code")
})
public class Department implements Serializable, BusinessEntity, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // tk to be rename to code in here and in the database
    @Column(name = "subGroupCode")
    private String code;
    private String jobCostingType;
    private String name;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee head;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee actingHead;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<JobCategory> jobCategories;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> staff;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Laboratory> laboratories;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<DepartmentUnit> departmentUnits;
    private Boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet;
    private Boolean actingHeadActive;
    @OneToOne(cascade = CascadeType.ALL)
    private Privilege privilege;
    @Transient
    private Boolean isDirty;

    public Department() {
        name = "";
        code = "";
        jobCostingType = "";
        staff = new ArrayList<>();
        laboratories = new ArrayList<>();
        departmentUnits = new ArrayList<>();
        active = true;
        actingHeadActive = false;
    }

    public Department(String name) {
        this.name = name;
        code = "";
        jobCostingType = "";
        staff = new ArrayList<>();
        laboratories = new ArrayList<>();
        departmentUnits = new ArrayList<>();
        active = true;
        actingHeadActive = false;
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

    public Privilege getPrivilege() {
        if (privilege == null) {
            privilege = new Privilege(name);
        }

        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public String getJobCostingType() {
        if (jobCostingType == null) {
            jobCostingType = "";
        }

        return jobCostingType;
    }

    public void setJobCostingType(String jobCostingType) {
        this.jobCostingType = jobCostingType;
    }

    public Boolean getActingHeadActive() {
        if (actingHeadActive == null) {
            actingHeadActive = false;
        }
        return actingHeadActive;
    }

    public void setActingHeadActive(Boolean actingHeadActive) {
        this.actingHeadActive = actingHeadActive;
    }

    public Internet getInternet() {
        if (internet == null) {
            internet = new Internet();
        }
        return internet;
    }

    public void setInternet(Internet internet) {
        this.internet = internet;
    }

    public List<Laboratory> getLaboratories() {
        if (laboratories == null) {
            laboratories = new ArrayList<>();
        }
        return laboratories;
    }

    public void setLaboratories(List<Laboratory> laboratories) {
        this.laboratories = laboratories;
    }

    public List<DepartmentUnit> getDepartmentUnits() {
        if (departmentUnits == null) {
            departmentUnits = new ArrayList<>();
        }
        return departmentUnits;
    }

    public void setDepartmentUnits(List<DepartmentUnit> departmentUnits) {
        this.departmentUnits = departmentUnits;
    }

    @Override
    public Boolean getActive() {
        if (active == null) {
            active = false;
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

    public Employee getActingHead() {

        return actingHead;
    }

    public void setActingHead(Employee actingHead) {
        this.actingHead = actingHead;
    }

    public List<JobCategory> getJobCategories() {
        return jobCategories;
    }

    public void setJobCategories(List<JobCategory> jobCategories) {
        this.jobCategories = jobCategories;
    }

    public List<Employee> getStaff() {
        return staff;
    }

    public void setStaff(List<Employee> staff) {
        this.staff = staff;
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
        if (code == null) {
            code = "";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        if (code != null) {
            return name; // tk + " (" + code + ")";
        } else {
            if (name != null) {
                return name;
            } else {
                return "";
            }
        }

    }

    public static List<Department> findDepartmentsByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("'", "`");

            List<Department> departments
                    = em.createQuery("SELECT d FROM Department d where UPPER(d.name) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY d.name", Department.class).getResultList();
            return departments;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Department> findActiveDepartmentsByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("'", "`");

            List<Department> departments
                    = em.createQuery("SELECT d FROM Department d WHERE UPPER(d.name) LIKE '%"
                            + value.toUpperCase().trim() + "%' AND d.active = 1 ORDER BY d.name", Department.class).getResultList();
            return departments;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Department> findAllActiveDepartments(EntityManager em) {

        try {
            return em.createQuery("SELECT d FROM Department d WHERE d.active = 1 ORDER BY d.name", Department.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Department findDepartmentByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("'", "`");

            List<Department> departments = em.createQuery("SELECT d FROM Department d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + value.toUpperCase() + "'", Department.class).getResultList();
            if (!departments.isEmpty()) {
                return departments.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Department findActiveDepartmentByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("'", "`");

            List<Department> departments = em.createQuery("SELECT d FROM Department d "
                    + "WHERE d.active = 1 AND UPPER(d.name) "
                    + "= '" + value.toUpperCase() + "'", Department.class).getResultList();
            if (!departments.isEmpty()) {
                return departments.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Department findDepartmentById(EntityManager em, Long Id) {
        if (Id != null) {
            return em.find(Department.class, Id);
        } else {
            return null;
        }
    }

    public static List<String> findAllDepartmentNames(EntityManager em) {

        ArrayList<String> names = new ArrayList<>();

        try {

            List<Department> departments = em.createNamedQuery("findAllDepartments", Department.class).getResultList();

            for (Department department : departments) {
                names.add(department.getName());
            }
            return names;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Department> findAllDepartments(EntityManager em) {

        try {
            return em.createNamedQuery("findAllDepartments", Department.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public static Department findDepartmentBySubGroupCode(EntityManager em, String subGroupCode) {

        try {

            subGroupCode = subGroupCode.replaceAll("'", "`");

            Query query = em.createNamedQuery("findBySubGroupCode");
            query.setParameter("subGroupCode", subGroupCode);
            return (Department) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getName(), ((Department) o).getName());
    }

    public static Department findDefaultDepartment(EntityManager em,
            String name) {
        Department department = Department.findDepartmentByName(em, name);

        if (department == null) {
            department = new Department(name);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, department);
            em.getTransaction().commit();
        }

        return department;
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

        return new ReturnMessage(false, "Department not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method guards against returning very long names. This is used in an
     * autocomplete JSF component for instance to prevent the list of entity
     * names from extending beyond the screen. In the future, the maximum length
     * of say 50 will be a value stored in the resource bundle of the BEL.
     *
     * @return
     */
    public String getTruncatedName() {
        if (getName().length() >= 50) {
            return getName().substring(0, 50);
        } else {
            return getName();
        }
    }

    public void setTruncatedName(String name) {
        setName(name);
    }

    public static Department findDepartmentBySystemOptionDeptId(String option, EntityManager em) {

        Long id = (Long) SystemOption.getOptionValueObject(em, option);

        Department department = Department.findDepartmentById(em, id);
        em.refresh(department);

        if (department != null) {
            return department;
        } else {
            return new Department("");
        }
    }

    public static Department findDepartmentAssignedToJob(Job job, EntityManager em) {

        Department dept;

        if (job.getSubContractedDepartment().getName().equals("--")
                || job.getSubContractedDepartment().getName().equals("")) {
            // This is not a subcontracted job see return to parent department            
            dept = Department.findDepartmentByName(em, job.getDepartment().getName());
            if (dept != null) {
                em.refresh(dept);
            }

            return dept;
        } else {
            dept = Department.findDepartmentByName(em, job.getSubContractedDepartment().getName());
            em.refresh(dept);

            return dept;
        }
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
