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
package jm.com.dpbennett.business.entity.hrm;

import jm.com.dpbennett.business.entity.Person;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
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
import jm.com.dpbennett.business.entity.auth.Signature;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.sm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name = "findAllEmployees", query = "SELECT e FROM Employee e ORDER BY e.lastName")
})
public class Employee implements Person, Serializable, Comparable, BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sex;
    private String nameSuffix;
    private String number;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String title;
    private String name;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<EmployeePosition> positions;
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department department;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateHired;
    private String notes;
    private Boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private Signature signature;
    @Transient
    private Boolean isDirty;

    public Employee() {
        firstName = "";
        lastName = "";
        internet = new Internet();
        addresses = new ArrayList<>();
        phoneNumbers = new ArrayList<>();
        positions = new ArrayList<>();
        active = true;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        internet = new Internet();
        addresses = new ArrayList<>();
        phoneNumbers = new ArrayList<>();
        positions = new ArrayList<>();
        active = true;
    }

    public String getEmploymentPositions() {
        String eps = "";
        int index = 0;
        for (EmployeePosition ep : positions) {
            if (index == 0) {
                eps = ep.getName();
            } else {
                eps = eps + ", " + ep.getName();
            }

            index++;
        }

        return eps;
    }

    public Boolean isProcurementOfficer() {
        for (EmployeePosition position : positions) {
            if (position.getName().equalsIgnoreCase("Procurement Officer")) {
                return true;
            }
        }

        return false;
    }
    
    public Boolean hasEmploymentPosition(String employmentPosition) {
        
        for (EmployeePosition position : positions) {
            if (position.getName().equalsIgnoreCase(employmentPosition)) {
                return true;
            }
        }

        return false;
    }

    public List<EmployeePosition> getPositions() {
        if (positions == null) {
            positions = new ArrayList<>();
        }
        return positions;
    }

    public String getAllPositions() {
        String allPositions = "";

        for (int i = 0; i < getPositions().size(); i++) {
            if (i == 0) {
                allPositions = getPositions().get(i).getTitle();
            } else {
                allPositions = allPositions + ", " + getPositions().get(i).getTitle();
            }
        }

        return allPositions;
    }

    public void setPositions(List<EmployeePosition> positions) {
        this.positions = positions;
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

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    @Override
    public Boolean getActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
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

    @JsonbTransient
    public Department getDepartment() {
        if (department == null) {
            department = new Department();
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getFirstName() {
        if (firstName == null) {
            return "";
        }
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        if (lastName == null) {
            return "";
        }
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getTitle() {
        if (title == null) {
            title = "";
        }
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
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

        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return BusinessEntityUtils.getPersonFullName(this, Boolean.TRUE);
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getNameSuffix() {
        return nameSuffix;
    }

    @Override
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    @Override
    public String getName() {
        name = toString();

        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static List<Employee> findByAnyPartOfName(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Employee> employees
                    = em.createQuery("SELECT e FROM Employee e where UPPER(e.firstName) like '%"
                            + value + "%'" + " OR UPPER(e.lastName) like '%"
                            + value + "%'"
                            + " ORDER BY e.lastName", Employee.class).getResultList();
            return employees;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Employee> find(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
           
            List<Employee> employees
                    = em.createQuery("SELECT e FROM Employee e where UPPER(e.firstName) like '%"
                            + value + "%'" + " OR UPPER(e.lastName) like '%"
                            + value + "%'" + " OR UPPER(e.department.name) like '%"
                            + value + "%'"
                            + " ORDER BY e.lastName", Employee.class).getResultList();
            return employees;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Employee> findAllActiveByName(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
           
            List<Employee> employees
                    = em.createQuery("SELECT e FROM Employee e WHERE ( UPPER(e.firstName) like '%"
                            + value + "%'" + " OR UPPER(e.lastName) like '%"
                            + value + "%'"
                            + ") AND e.active = 1"
                            + " ORDER BY e.lastName", Employee.class).getResultList();
            
            return employees;
            
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Employee> findActiveByPosition(EntityManager em,
            String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Employee> employees
                    = em.createQuery("SELECT e FROM Employee e"
                            + " JOIN e.positions positions"
                            + " WHERE positions.title = '" + value + "'"
                            + " AND e.active = 1",
                            Employee.class).getResultList();

            return employees;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Employee> findActive(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
           
            List<Employee> employees 
                    = em.createQuery("SELECT DISTINCT e FROM Employee e LEFT JOIN e.positions positions WHERE ( UPPER(e.firstName) like '%"
                            + value + "%'" + " OR UPPER(e.lastName) like '%"
                            + value + "%'" + " OR UPPER(positions.title) like '%"
                            + value + "%'" + " OR UPPER(e.department.name) like '%"
                            + value + "%')"
                            + " AND e.active = 1 OR e.active IS NULL"
                            + " ORDER BY e.lastName", Employee.class).getResultList();
            return employees;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    /**
     * Gets first employee with the given firstname and lasname
     *
     * @param em
     * @param firstName
     * @param lastName
     * @return
     */
    public static Employee findByFirstAndLastName(EntityManager em, 
            String firstName, String lastName) {

        if (firstName != null && lastName != null) {
           
            try {
                
                firstName = firstName.replaceAll("&amp;", "&").replaceAll("'", "`");
                lastName = lastName.replaceAll("&amp;", "&").replaceAll("'", "`");
                
                List<Employee> employees = em.createQuery("SELECT e FROM Employee e "
                        + "WHERE UPPER(e.firstName) "
                        + "= '" + firstName + "' AND UPPER(e.lastName) = '" 
                        + lastName + "'",
                        Employee.class).getResultList();
                if (!employees.isEmpty()) {
                    Employee employee = employees.get(0);
                    return employee;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Gets first active employee with the given firstname and lasname
     *
     * @param em
     * @param firstName
     * @param lastName
     * @return
     */
    public static Employee findActiveByName(EntityManager em, 
            String firstName, 
            String lastName) {

        if (firstName != null && lastName != null) {
           
            try {
                
                firstName = firstName.replaceAll("&amp;", "&").replaceAll("'", "`");
                lastName = lastName.replaceAll("&amp;", "&").replaceAll("'", "`");
                
                List<Employee> employees = em.createQuery("SELECT e FROM Employee e "
                        + "WHERE e.active = 1 AND UPPER(e.firstName) "
                        + "= '" + firstName + "' AND UPPER(e.lastName) = '" 
                        + lastName + "'",
                        Employee.class).getResultList();
                if (!employees.isEmpty()) {
                    Employee employee = employees.get(0);
                    return employee;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public static List<Employee> findAll(EntityManager em) {

        try {
            return em.createNamedQuery("findAllEmployees", Employee.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Employee> findAllActive(EntityManager em) {

        try {
            return em.createQuery("SELECT e FROM Employee e WHERE (e.active = 1 OR e.active IS NULL) ORDER BY e.lastName", Employee.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<String> findAllNames(EntityManager em) {

        ArrayList<String> names = new ArrayList<>();

        try {
            List<Employee> employees = em.createNamedQuery("findAllEmployees", Employee.class).getResultList();
            for (Employee employee : employees) {
                names.add(employee.getLastName() + ", " + employee.getFirstName());
            }
            return names;
        } catch (Exception e) {
            return null;
        }
    }

    public static Employee findById(EntityManager em, Long Id) {
        return em.find(Employee.class, Id);
    }

    public static Employee findDefault(
            EntityManager em,
            String firstName,
            String lastName,
            Boolean userTransaction) {
        
        Employee employee = Employee.findByFirstAndLastName(em, firstName, lastName);

        if (employee == null) {
            employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);

            employee.setDepartment(Department.findDefault(em, "--"));

            if (userTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, employee);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, employee);
            }
        }

        return employee;
    }

    public static Employee findByName(EntityManager em, String name) {
        
        name = name.replaceAll("&amp;", "&").replaceAll("'", "`");
        
        // NB: This assumes that the name is given as "lastname, firstname"
        String names[] = name.split(",");
        
        if (names.length == 2) {
            if (!names[1].trim().equals("") && !names[0].trim().equals("")) {
                
                return Employee.findByFirstAndLastName(em,
                        names[1].trim(),
                        names[0].trim());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Employee findActiveByName(EntityManager em, String name) {
        
        name = name.replaceAll("&amp;", "&").replaceAll("'", "`");
        String names[] = name.split(",");
        
        if (names.length == 2) {
            if (!names[1].trim().equals("") && !names[0].trim().equals("")) {
                return Employee.findActiveByName(em,
                        names[1].trim(),
                        names[0].trim());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Boolean isMemberOf(Department department) {
        return Objects.equals(getDepartment().getId(), department.getId());
    }

    public Boolean isStaffMemberOf(Department department) {
        for (Employee employee : department.getStaff()) {
            if (Objects.equals(getId(), employee.getId())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println("Employee save exception: " + e);
        }

        return new ReturnMessage(false, "Employee not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    public static String findDefaultEmailAdress(Employee employee, EntityManager em) {
        String address = "";

        if (!employee.getInternet().getEmail1().trim().equals("")) {
            address = employee.getInternet().getEmail1();
        } else {

            String listAsString = (String) SystemOption.getOptionValueObject(em, "domainNames");
            String domainNames[] = listAsString.split(";");

            User user = User.findActiveJobManagerUserByEmployeeId(em, employee.getId());

            if (user != null) {
                address = user.getUsername();
                if (domainNames.length > 0) {
                    address = address + "@" + domainNames[0];
                }
            }

        }

        return address;
    }

    public static List<String> getDepartmentSupervisorsEmailAddresses(Department department, EntityManager em) {
        List<String> emails = new ArrayList<>();

        emails.add(Employee.findDefaultEmailAdress(department.getHead(), em));

        if (department.getActingHeadActive()) {
            emails.add(Employee.findDefaultEmailAdress(department.getActingHead(), em));
        }

        return emails;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getName(), ((Employee) o).getName());
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public void setType(String type) {

    }

    @Override
    public Date getDateEntered() {
        return null;
    }

    @Override
    public void setDateEntered(Date dateEntered) {

    }

    @Override
    public Date getDateEdited() {
        return null;
    }

    @Override
    public void setDateEdited(Date dateEdited) {

    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        return null;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public Person getEditedBy() {
        return null;
    }

    @Override
    public void setEditedBy(Person person) {
    }

    @Override
    public Person getEnteredBy() {
        return null;
    }

    @Override
    public void setEnteredBy(Person person) {
    }

    @Override
    public String getCategory() {
        return "";
    }

    @Override
    public void setCategory(String category) {
    }

    @Override
    public String getComments() {
        return "";
    }

    @Override
    public void setComments(String comments) {
    }

}
