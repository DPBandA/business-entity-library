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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
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
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name = "findAllEmployees", query = "SELECT e FROM Employee e ORDER BY e.lastName")
})
@XmlRootElement
public class Employee implements Person, Serializable, Comparable, BusinessEntity {

    private static final long serialVersionUId = 1L;
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
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department department;
//    @OneToOne(cascade = CascadeType.REFRESH)
//    private BusinessOffice businessOffice;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateHired;
    private Double billingRate;
    private String notes;
    private Boolean active;
    private String employmentType;
//    @OneToOne(cascade = CascadeType.REFRESH)
//    private Business business;
    @OneToOne(cascade = CascadeType.ALL)
    private Signature signature;
    private String post;
    @Transient
    private Boolean isDirty;

    public Employee() {
        firstName = "";
        lastName = "";
        //businessOffice = new BusinessOffice();
        internet = new Internet();
        addresses = new ArrayList<>();
        phoneNumbers = new ArrayList<>();
        active = true;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        //businessOffice = new BusinessOffice();
        internet = new Internet();
        addresses = new ArrayList<>();
        phoneNumbers = new ArrayList<>();
        active = true;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

//    public Business getBusiness() {
//        return business;
//    }
//
//    public void setBusiness(Business business) {
//        this.business = business;
//    }
    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Boolean getActive() {
        return active;
    }

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

//    public BusinessOffice getBusinessOffice() {
//        if (businessOffice == null) {
//            businessOffice = new BusinessOffice();
//        }
//        return businessOffice;
//    }
//
//    public void setBusinessOffice(BusinessOffice businessOffice) {
//        this.businessOffice = businessOffice;
//    }
    @JsonBackReference
    @XmlTransient
    public Department getDepartment() {
        if (department == null) {
            department = new Department();
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //@XmlTransient
    @XmlTransient
    @JsonIgnore
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    //@XmlTransient
    @XmlTransient
    @JsonIgnore
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

    public Double getBillingRate() {
        return billingRate;
    }

    public void setBillingRate(Double billingRate) {
        this.billingRate = billingRate;
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
        // TODO: Warning - this method won't work in the case the Id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

    public static List<Employee> findEmployeesByName(EntityManager em, String name) {

        try {
            String newName = name.toUpperCase().trim().replaceAll("'", "''");

            List<Employee> employees
                    = em.createQuery("SELECT e FROM Employee e where UPPER(e.firstName) like '%"
                            + newName + "%'" + " OR UPPER(e.lastName) like '%"
                            + newName + "%'"
                            + " ORDER BY e.lastName", Employee.class).getResultList();
            return employees;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Employee> findEmployees(EntityManager em, String query) {

        try {
            String newQuery = query.toUpperCase().trim().replaceAll("'", "''");

            List<Employee> employees
                    = em.createQuery("SELECT e FROM Employee e where UPPER(e.firstName) like '%"
                            + newQuery + "%'" + " OR UPPER(e.lastName) like '%"
                            + newQuery + "%'" + " OR UPPER(e.department.name) like '%"
                            + newQuery + "%'"
                            + " ORDER BY e.lastName", Employee.class).getResultList();
            return employees;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Employee> findActiveEmployeesByName(EntityManager em, String name) {

        try {
            String newName = name.toUpperCase().trim().replaceAll("'", "''");

            List<Employee> employees
                    = em.createQuery("SELECT e FROM Employee e WHERE ( UPPER(e.firstName) like '"
                            + newName + "%'" + " OR UPPER(e.lastName) like '"
                            + newName + "%'"
                            + ") AND e.active = 1"
                            + " ORDER BY e.lastName", Employee.class).getResultList();
            return employees;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Employee> findActiveEmployees(EntityManager em, String query) {

        try {
            String newQuery = query.toUpperCase().trim().replaceAll("'", "''");

            List<Employee> employees
                    = em.createQuery("SELECT e FROM Employee e WHERE ( UPPER(e.firstName) like '"
                            + newQuery + "%'" + " OR UPPER(e.lastName) like '"
                            + newQuery + "%'" + " OR UPPER(e.department.name) like '"
                            + newQuery + "%'"
                            + ") AND e.active = 1"
                            + " ORDER BY e.lastName", Employee.class).getResultList();
            return employees;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Employee findEmployeeByUsername(EntityManager em, String username, javax.naming.ldap.LdapContext ctx) {

        try {

            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = {"displayName"};

            constraints.setReturningAttributes(attrIDs);

            NamingEnumeration answer = ctx.search("DC=bos,DC=local", "SAMAccountName=" // tk make DC=? option
                    + username, constraints);
            if (answer.hasMore()) { // assuming only one match
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                // return the employee for the given first and last names
                String name[] = attrs.get("displayName").get().toString().split(" ");
                if (name.length == 2) { // simple first and last names
                    return findEmployeeByName(em, name[0].trim(), name[1].trim());
                } else if (name.length == 3) { // double barrel first name without hyphen and last name
                    return findEmployeeByName(em, name[0].trim() + " " + name[1].trim(), name[2].trim());
                } else if (name.length == 3) { // double barrel first name without hyphen and last name
                    return findEmployeeByName(em, name[0].trim() + "-" + name[1].trim(), name[2].trim());
                }

            } else {
                return null;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    /**
     * Gets first employee with the given firstname and lasname
     *
     * @param em
     * @param firstName
     * @param lastName
     * @return
     */
    public static Employee findEmployeeByName(EntityManager em, String firstName, String lastName) {

        if (firstName != null && lastName != null) {
            String newFirstName = firstName.replaceAll("'", "''").trim().toUpperCase();
            String newLastName = lastName.replaceAll("'", "''").trim().toUpperCase();
            try {
                List<Employee> employees = em.createQuery("SELECT e FROM Employee e "
                        + "WHERE UPPER(e.firstName) "
                        + "= '" + newFirstName + "' AND UPPER(e.lastName) = '" + newLastName + "'",
                        Employee.class).getResultList();
                if (employees.size() > 0) {
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
    public static Employee findActiveEmployeeByName(EntityManager em, String firstName, String lastName) {

        if (firstName != null && lastName != null) {
            String newFirstName = firstName.replaceAll("'", "''").trim().toUpperCase();
            String newLastName = lastName.replaceAll("'", "''").trim().toUpperCase();
            try {
                List<Employee> employees = em.createQuery("SELECT e FROM Employee e "
                        + "WHERE e.active = 1 AND UPPER(e.firstName) "
                        + "= '" + newFirstName + "' AND UPPER(e.lastName) = '" + newLastName + "'",
                        Employee.class).getResultList();
                if (employees.size() > 0) {
                    Employee employee = employees.get(0);
                    return employee;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public static List<Employee> findAllEmployees(EntityManager em) {

        try {
            return em.createNamedQuery("findAllEmployees", Employee.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Employee> findAllActiveEmployees(EntityManager em) {

        try {
            return em.createQuery("SELECT e FROM Employee e WHERE e.active = 1 ORDER BY e.lastName", Employee.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<String> findAllEmployeeNames(EntityManager em) {

        ArrayList<String> names = new ArrayList<String>();

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

    public static Employee findEmployeeById(EntityManager em, Long Id) {
        return em.find(Employee.class, Id);
    }

    public static Employee findDefaultEmployee(
            EntityManager em,
            String firstName,
            String lastName,
            Boolean userTransaction) {
        Employee employee = Employee.findEmployeeByName(em, firstName, lastName);

        // create employee if it does not exist
        if (employee == null) {
            employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            //employee.setBusinessOffice(BusinessOffice.findDefaultBusinessOffice(em, "--"));

            employee.setDepartment(Department.findDefaultDepartment(em, "--"));
            // save
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

    public static Employee findEmployeeByName(EntityManager em, String name) {
        String names[] = name.split(",");
        if (names.length == 2) {
            if (!names[1].trim().equals("") && !names[0].trim().equals("")) {
                return Employee.findEmployeeByName(em,
                        names[1].trim(),
                        names[0].trim());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    public static Employee findActiveEmployeeByName(EntityManager em, String name) {
        String names[] = name.split(",");
        if (names.length == 2) {
            if (!names[1].trim().equals("") && !names[0].trim().equals("")) {
                return Employee.findActiveEmployeeByName(em,
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

        return new ReturnMessage(false, "Employee not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String findEmployeeDefaultEmailAdress(Employee employee, EntityManager em) {
        String address = "";

        // Get email1 which is treated as the employee's company email address
        if (!employee.getInternet().getEmail1().trim().equals("")) {
            address = employee.getInternet().getEmail1();
        } else {
            // Get and set default email using company domain   
            String listAsString = (String) SystemOption.getOptionValueObject(em, "domainNames");
            String domainNames[] = listAsString.split(";");

            JobManagerUser user = JobManagerUser.findJobManagerUserByEmployeeId(em, employee.getId());

            // Build email address
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

        emails.add(Employee.findEmployeeDefaultEmailAdress(department.getHead(), em));
        // Get the email of the acting head of he/she is currently acting
        if (department.getActingHeadActive()) {
            emails.add(Employee.findEmployeeDefaultEmailAdress(department.getActingHead(), em));
        }

        return emails;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getName(), ((Employee) o).getName());
    }

}
