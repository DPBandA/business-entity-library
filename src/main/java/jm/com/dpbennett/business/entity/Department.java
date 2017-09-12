/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
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
import javax.persistence.Query;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.utils.MethodResult;


/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "department")
@NamedQueries({
    @NamedQuery(name = "findAllDepartments", query = "SELECT e FROM Department e ORDER BY e.name"),
    @NamedQuery(name = "findAllActiveDepartments", query = "SELECT e FROM Department e WHERE e.active = :active ORDER BY e.name"),
    @NamedQuery(name = "findBySubGroupCode", query = "SELECT e FROM Department e WHERE e.subGroupCode = :subGroupCode")
})
@XmlRootElement
public class Department implements Serializable, BusinessEntity, Converter, Comparable {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subGroupCode;
    private String jobCostingType;
    private String name;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee head;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee actingHead;
    @OneToMany(cascade = CascadeType.ALL)
    private List<JobCategory> jobCategories;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> staff;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Laboratory> laboratories;
    @OneToMany(cascade = CascadeType.ALL)
    private List<DepartmentUnit> departmentUnits;
    private Boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet;
    private Boolean actingHeadActive;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Privilege privilege;
   
    public Department() {
        name = "";
        subGroupCode = "";
        jobCostingType = "";
        staff = new ArrayList<>();
        laboratories = new ArrayList<>();
        departmentUnits = new ArrayList<>();
    }

    public Department(String name) {
        this.name = name;
        subGroupCode = "";
        jobCostingType = "";
        staff = new ArrayList<>();
        laboratories = new ArrayList<>();
        departmentUnits = new ArrayList<>();
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

    @XmlTransient
    public List<Laboratory> getLaboratories() {
        if (laboratories == null) {
            laboratories = new ArrayList<>();
        }
        return laboratories;
    }

    public void setLaboratories(List<Laboratory> laboratories) {
        this.laboratories = laboratories;
    }

    @XmlTransient
    public List<DepartmentUnit> getDepartmentUnits() {
        if (departmentUnits == null) {
            departmentUnits = new ArrayList<>();
        }
        return departmentUnits;
    }

    public void setDepartmentUnits(List<DepartmentUnit> departmentUnits) {
        this.departmentUnits = departmentUnits;
    }

    public Boolean getActive() {
        if (active == null) {
            active = false;
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

    public Employee getActingHead() {
        if (actingHead == null) {
            actingHead = new Employee("--", "--");
        }
        return actingHead;
    }

    public void setActingHead(Employee actingHead) {
        this.actingHead = actingHead;
    }

    @XmlTransient
    public List<JobCategory> getJobCategories() {
        return jobCategories;
    }

    public void setJobCategories(List<JobCategory> jobCategories) {
        this.jobCategories = jobCategories;
    }

    @XmlTransient
    public List<Employee> getStaff() {
        return staff;
    }

    public void setStaff(Vector<Employee> staff) {
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

    public String getSubGroupCode() {
        if (subGroupCode == null) {
            subGroupCode = "";
        }
        return subGroupCode;
    }

    public void setSubGroupCode(String subGroupCode) {
        this.subGroupCode = subGroupCode;
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (subGroupCode != null) {
            return name + " (" + subGroupCode + ")";
        } else {
            if (name != null) {
                return name;
            } else {
                return "";
            }
        }
    }

    public static List<Department> findDepartmentsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Department> departments =
                    em.createQuery("SELECT d FROM Department d where UPPER(d.name) like '%"
                    + newName.toUpperCase().trim() + "%' ORDER BY d.name", Department.class).getResultList();
            return departments;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Department> findActiveDepartmentsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Department> departments =
                    em.createQuery("SELECT d FROM Department d WHERE UPPER(d.name) LIKE '%"
                    + newName.toUpperCase().trim() + "%' AND d.active = 1 ORDER BY d.name", Department.class).getResultList();
            return departments;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Department> findAllActiveDepartments(EntityManager em) {

        try {
            return em.createQuery("select d from DEPARTMENT d where d.active = 1 order by d.name", Department.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Department findDepartmentByName(EntityManager em, String departmentName) {

        try {
            String newDepartmentName = departmentName.trim().replaceAll("'", "''");

            List<Department> departments = em.createQuery("SELECT d FROM Department d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + newDepartmentName.toUpperCase() + "'", Department.class).getResultList();
            if (departments.size() > 0) {
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

        try { // tk try String.class instead of Department.class for better performance
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

//    public static Department findDepartmentBySubGroupCode(EntityManager em, Integer subGroupCode) {
//
//        try {
//            Query query = em.createNamedQuery("findBySubGroupCode");
//            query.setParameter("subGroupCode", subGroupCode);
//            return (Department) query.getSingleResult();
//        } catch (Exception e) {
//            return null;
//        }
//    }
    public static Department findDepartmentBySubGroupCode(EntityManager em, String subGroupCode) {

        try {
            Query query = em.createNamedQuery("findBySubGroupCode");
            query.setParameter("subGroupCode", subGroupCode);
            return (Department) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Department department = new Department();

        if (value != null) {
            department.setName(value);
        }

        return department;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Department) value).getName();
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.name, ((Department) o).name);
    }

    public static Department findDefaultDepartment(EntityManager em,
            String name) {
        Department department = Department.findDepartmentByName(em, name);

        if (department == null) {
            department = new Department();

            em.getTransaction().begin();
            department.setName(name);
            BusinessEntityUtils.saveBusinessEntity(em, department);
            em.getTransaction().commit();
        }

        return department;
    }

    public static boolean save(EntityManager em, Department department) {
        if (!BusinessEntityUtils.validateName(department.getName())) {
            return false;
        }

        // Head
        Employee head = Employee.findEmployeeByName(em, department.getHead().getName());
        if (head != null) {
            department.setHead(head);
        } else {
            department.setHead(Employee.findDefaultEmployee(em, "--", "--", false));
        }

        // Acting head
        Employee actinghead = Employee.findEmployeeByName(em, department.getActingHead().getName());
        if (head != null) {
            department.setActingHead(actinghead);
        } else {
            department.setActingHead(Employee.findDefaultEmployee(em, "--", "--", false));
        }

        em.getTransaction().begin();
        BusinessEntityUtils.saveBusinessEntity(em, department.getPrivilege());
        BusinessEntityUtils.saveBusinessEntity(em, department);
        em.getTransaction().commit();


        return true;
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