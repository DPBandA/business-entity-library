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

import jm.com.dpbennett.business.entity.Company;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.cm.Customer;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "business")
public class Business implements Customer, Company, BusinessEntity, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;
    private String type;
    private String notes;
    private String taxRegistrationNumber;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Department> departments;
    private String departmentLabel;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Address> addresses;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Contact> contacts;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastAccessed;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFirstReceived;
    private Boolean active;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee head;
    private String domainName;
    @Transient
    private Boolean isDirty;

    public Business() {
        this.name = "";
        this.number = "";
        this.type = "";
        this.notes = "";
        this.taxRegistrationNumber = "";
        this.addresses = new ArrayList<>();
        this.contacts = new ArrayList<>();
        this.departmentLabel = "Department";
        this.departments = new ArrayList<>();
        this.domainName = "";
    }

    public Business(String name) {
        this.name = name;
        this.number = "";
        this.type = "";
        this.notes = "";
        this.taxRegistrationNumber = "";
        this.addresses = new ArrayList<>();
        this.contacts = new ArrayList<>();
        this.departmentLabel = "Department";
        this.departments = new ArrayList<>();
        this.domainName = "";
    }

    public String getDepartmentLabel() {
        if (departmentLabel == null) {
            departmentLabel = "Department";
        }
        return departmentLabel;
    }

    public void setDepartmentLabel(String departmentLabel) {
        this.departmentLabel = departmentLabel;
    }

    public String getDomainName() {
        if (domainName == null) {
            domainName = "";
        }
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
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
    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    @Override
    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
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
        if (!(object instanceof Business)) {
            return false;
        }
        Business other = (Business) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.Business[id=" + id + "]";
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
    public List<Address> getAddresses() {
        return addresses;
    }

    @Override
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Date getDateLastAccessed() {
        return dateLastAccessed;
    }

    @Override
    public void setDateLastAccessed(Date dateLastAccessed) {
        this.dateLastAccessed = dateLastAccessed;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
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
    public Date getDateFirstReceived() {
        return dateFirstReceived;
    }

    @Override
    public void setDateFirstReceived(Date dateFirstReceived) {
        this.dateFirstReceived = dateFirstReceived;
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
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    public static Business findBusinessById(EntityManager em, Long id) {

        try {
            Business business = em.find(Business.class, id);
            return business;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Get the first business that matches the given name
    public static Business findBusinessByName(EntityManager em, String value) {

        try {
           
            List<Business> businesses = em.createQuery("SELECT b FROM Business b "
                    + "WHERE UPPER(b.name) "
                    + "= '" + value.toUpperCase() + "'", Business.class).getResultList();
            if (!businesses.isEmpty()) {
                return businesses.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Business findDefaultBusiness(EntityManager em,
            String name,
            Boolean useTransaction) {
        Business business = Business.findBusinessByName(em, name);

        if (business == null) {
            business = new Business();
            business.setName(name);

            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, business);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, business);
            }
        }

        return business;
    }

    public static List<Business> findAllBusinesses(EntityManager em) {

        try {
            return em.createQuery("SELECT b FROM Business b ORDER BY b.name", Business.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Business> findBusinessesByName(EntityManager em, String value) {

        try {
           
            List<Business> businesses
                    = em.createQuery("SELECT b FROM Business b where UPPER(b.name) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY b.name", Business.class).getResultList();
            return businesses;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<Business> findActiveBusinessesByName(EntityManager em, String value) {

        try {
           
            List<Business> businesses
                    = em.createQuery("SELECT b FROM Business b where UPPER(b.name) like '%"
                            + value.toUpperCase().trim() + "%' AND b.active = 1 ORDER BY b.name", Business.class).getResultList();
            return businesses;
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

        return new ReturnMessage(false, "Business not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Address getDefaultAddress() {
        return new Address();
    }

    @Override
    public Contact getDefaultContact() {
        return new Contact();
    }

    @Override
    public List<BusinessOffice> getBusinessOffices() {
        return new ArrayList<>();
    }

    @Override
    public void setBusinessOffices(List<BusinessOffice> businessOffices) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
