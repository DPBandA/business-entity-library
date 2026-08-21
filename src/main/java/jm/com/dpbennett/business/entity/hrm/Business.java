/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.Transient;
import jm.com.dpbennett.business.entity.Company;
import java.io.Serializable;
import java.text.Collator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.cm.Customer;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "business")
public class Business implements Customer, Company, BusinessEntity, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    private static final System.Logger LOG = System.getLogger(Business.class.getName());
    public static Business findById(EntityManager em, Long id) {
        
        try {
            Business business = em.find(Business.class, id);
            return business;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static Business findByName(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
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
    public static Business findDefault(EntityManager em,
            String name,
            Boolean useTransaction) {
        Business business = Business.findByName(em, name);
        
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
    public static List<Business> findAll(EntityManager em) {
        
        try {
            return em.createQuery("SELECT b FROM Business b ORDER BY b.name", Business.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public static List<Business> findAllActive(EntityManager em) {
        
        try {
            return em.createQuery("SELECT b FROM Business b WHERE b.active = 1 ORDER BY b.name",
                    Business.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public static List<Business> findAllByName(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Business> businesses
                    = em.createQuery("SELECT b FROM Business b where UPPER(b.name) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY b.name", Business.class).getResultList();
            return businesses;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public static List<Business> findAllActiveByName(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<Business> businesses
                    = em.createQuery("SELECT b FROM Business b where UPPER(b.name) like '%"
                            + value.toUpperCase().trim() + "%' AND b.active = 1 ORDER BY b.name", Business.class).getResultList();
            return businesses;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;
    private String type;
    private String description;
    private String notes;
    private String taxRegistrationNumber;
    private String departmentLabel;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee head;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Department> departments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contact> contacts;
    private LocalDateTime dateLastAccessed;
    private LocalDateTime dateFirstReceived;
    private Boolean active;
    private String domainName;
    @Transient
    private Boolean isDirty;
    @Transient
    private Address billingAddress;
    @Transient
    private Contact contact;

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

    public Contact getMainContact() {
        if (!getContacts().isEmpty()) {
            for (Contact mainContact : getContacts()) {
                if (mainContact.getType().equals("Main")) {
                    return mainContact;
                }
            }
            Contact mainContact = getContacts().get(0);
            mainContact.setType("Main");
            return mainContact;
        } else {
            Contact mainContact = new Contact();
            mainContact.setType("Main");
            getContacts().add(mainContact);
            return getContacts().get(0);
        }
    }

    public Contact getContact() {
        if (contact == null) {
            setContact(getMainContact());
        }

        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Address> getBillingAddresses() {
        ArrayList<Address> billingAddresses = new ArrayList<>();

        for (Address address : getAddresses()) {
            if (address.getType().equals("Billing")) {
                billingAddresses.add(address);
            }
        }

        return billingAddresses;
    }

    public Address getBillingAddress() {
        if (billingAddress == null) {
            //if (client != null) {
            setBillingAddress(getDefaultAddress());
            //} else {
            //    return new Address();
            //}
        }

        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
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

        if (head == null) {
            return new Employee();
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
    public String getTaxRegistrationNumber() {
        if (taxRegistrationNumber == null) {
            taxRegistrationNumber = "";
        }

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
        if (!(object instanceof Business)) {
            return false;
        }
        Business other = (Business) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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

    @Override
    public List<Address> getAddresses() {

        if (addresses == null) {
            addresses = new ArrayList<>();
        }

        return addresses;
    }

    @Override
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public List<Contact> getContacts() {

        if (contacts == null) {
            contacts = new ArrayList<>();
        }

        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public LocalDateTime getDateLastAccessed() {
        return dateLastAccessed;
    }

    @Override
    public void setDateLastAccessed(LocalDateTime dateLastAccessed) {
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
    public LocalDateTime getDateFirstReceived() {
        return dateFirstReceived;
    }

    @Override
    public void setDateFirstReceived(LocalDateTime dateFirstReceived) {
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


    @Override
    public ReturnMessage save(EntityManager em) {

        try {

            for (Contact contact : getContacts()) {
                contact.save(em);
            }

            for (Address address : getAddresses()) {
                address.save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println("Business save exception: " + e);
        }

        return new ReturnMessage(false, "Business not saved");

    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Address getDefaultAddress() {
        if (!getBillingAddresses().isEmpty()) {

            return getBillingAddresses().get(getBillingAddresses().size() - 1);

        } else if (!getAddresses().isEmpty()) {

            return getAddresses().get(getAddresses().size() - 1);

        } else {
            return new Address("", "Billing");
        }
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
    public LocalDateTime getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(LocalDateTime dateEntered) {
    }

    @Override
    public LocalDateTime getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(LocalDateTime dateEdited) {
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Employee getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public ReturnMessage saveUnique(EntityManager em) {
        try {

            if (this.id == null) {

                Business existing = Business.findByName(em, this.name);
                if (existing != null) {

                    return new ReturnMessage(false, "Organization exists");
                } else {

                    return save(em);
                }
            } else {

                return save(em);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Organization not saved");
    }

    @Override
    public List<SystemOption> getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SystemOption getSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
