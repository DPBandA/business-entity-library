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
package jm.com.dpbennett.business.entity.hrm;

import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.cm.Client;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "contact")
@XmlRootElement
public class Contact implements Person, BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String sex;
    private String salutation;
    private String position;
    private String title;
    private String nameSuffix;
    private String firstName;
    private String lastName;
    private String middleName;
    private String notes;
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    @Transient
    private Boolean isDirty;

    public Contact() {
        phoneNumbers = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        phoneNumbers = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Contact(String name) {
        this.name = name;
        phoneNumbers = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Contact(String firstName, String lastName, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        phoneNumbers = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Contact(Contact src) {
        doCopy(src);
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

    public static Contact findContactById(EntityManager em, Long id) {

        try {
            Contact contact = em.find(Contact.class, id);

            return contact;
        } catch (Exception e) {

            return null;
        }
    }

    public List getContactTypes() {
        throw new UnsupportedOperationException("Not supported yet: getContactTypes() to be put in Contact class");
        //return Application.getStringListAsSortableSelectItems(getEntityManager(), "personalContactTypes");
    }

    public Contact prepare() {

        firstName = getFirstName().trim();
        lastName = getLastName().trim();

        return this;
    }

    public final void doCopy(Contact src) {
        phoneNumbers = new ArrayList<>();
        addresses = new ArrayList<>();
        this.name = src.name;
        this.type = src.type;
        this.sex = src.sex;
        this.salutation = src.salutation;
        this.position = src.position;
        this.title = src.title;
        this.nameSuffix = src.nameSuffix;
        this.firstName = src.firstName;
        this.lastName = src.lastName;
        this.middleName = src.middleName;
        this.notes = src.notes;
        for (PhoneNumber phoneNumber : src.phoneNumbers) {
            phoneNumbers.add(new PhoneNumber(phoneNumber));
        }
        for (Address address : src.addresses) {
            addresses.add(new Address(address));
        }
        if (src.internet != null) {
            internet = new Internet(src.internet);
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    @XmlTransient
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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
    public List<PhoneNumber> getPhoneNumbers() {
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<>();
        }

        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public PhoneNumber getMainPhoneNumber() {
        if (!phoneNumbers.isEmpty()) {
            return phoneNumbers.get(0);
        } else {
            phoneNumbers.add(new PhoneNumber());
            return phoneNumbers.get(0);
        }
    }

    public PhoneNumber getMainFaxNumber() {
        if (!phoneNumbers.isEmpty()) {
            if (phoneNumbers.size() > 1) {
                return phoneNumbers.get(1);
            } else {
                phoneNumbers.add(1, new PhoneNumber());
                return phoneNumbers.get(1);
            }
        } else {
            phoneNumbers.add(0, new PhoneNumber());
            phoneNumbers.add(1, new PhoneNumber());
            return phoneNumbers.get(1);
        }
    }

    public String getType() {
        if (type == null) {
            type = "";
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
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
    public String getFirstName() {
        if (firstName == null || firstName.isEmpty()) {
            firstName = "";
        }
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        if (lastName == null || lastName.isEmpty()) {
            lastName = "";
        }
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getMiddleName() {
        if (middleName == null || middleName.isEmpty()) {
            middleName = "";
        }
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
    public String getNameSuffix() {
        return nameSuffix;
    }

    @Override
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    @Override
    public String getName() {
        if (getFirstName().trim().isEmpty()
                && getLastName().trim().isEmpty()) {
            return "";
        } else {
            return getLastName() + ", " + getFirstName();
        }
    }

    public String getFriendlyName() {
        if (!getName().isEmpty()) {
            return getName();
        } else {
            return "-- none --";
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if ((((Contact) o).id != null) && (this.id != null)) {
            if (((Contact) o).id < this.id) {
                return 1;
            } else if (Objects.equals(((Contact) o).id, this.id)) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    /**
     * Gets first contact found with the given firstname and lastname
     *
     * @param em
     * @param firstName
     * @param lastName
     * @return
     */
    public static Contact findContactByName(EntityManager em, String firstName, String lastName) {

        if (firstName != null && lastName != null) {
            String newFirstName = firstName.replaceAll("'", "''").trim().toUpperCase();
            String newLastName = lastName.replaceAll("'", "''").trim().toUpperCase();
            try {
                List<Contact> contacts = em.createQuery("SELECT c FROM Contact c "
                        + "WHERE UPPER(c.firstName) "
                        + "= '" + newFirstName + "' AND UPPER(c.lastName) = '" + newLastName + "'",
                        Contact.class).getResultList();
                if (contacts.size() > 0) {
                    return contacts.get(0);
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Find a contact associated with a client. This method uses a crude method
     * by finding the client first then looping over the contacts to find the
     * required contact. I full SQL solution is to be developed.
     *
     * @param em
     * @param query
     * @param clientId
     * @return
     */
    public static Contact findClientContactById(EntityManager em, String query, Long clientId) {

        try {
            String newQuery = query.replaceAll("'", "''");
            String contacts[] = newQuery.split(", ");
            String lastname = contacts[0];
            String firstname = contacts[1];

            Client client = Client.findClientById(em, clientId);

            if (client != null) {
                for (Contact contact : client.getContacts()) {
                    if (contact.getFirstName().equals(firstname)
                            && contact.getLastName().equals(lastname)) {
                        return contact;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Contact findClientContact(EntityManager em, String query, Client client) {

        try {
            String newQuery = query.replaceAll("'", "''");
            String contacts[] = newQuery.split(", ");
            String lastname = contacts[0];
            String firstname = contacts[1];

            if (client != null) {
                for (Contact contact : client.getContacts()) {
                    if (contact.getFirstName().equals(firstname)
                            && contact.getLastName().equals(lastname)) {
                        return contact;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Contact findClientContactByEmployee(EntityManager em,
            Employee employee, Long clientId) {

        try {

            Client client = Client.findClientById(em, clientId);

            if (client != null) {
                for (Contact contact : client.getContacts()) {
                    if (contact.getFirstName().equals(employee.getFirstName())
                            && contact.getLastName().equals(employee.getLastName())) {
                        return contact;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Contact findDefaultContact(
            EntityManager em,
            String firstName,
            String lastName,
            Boolean useTransaction) {
        Contact contact = Contact.findContactByName(em, firstName, lastName);

        // create employee if it does not exist
        if (contact == null) {
            contact = new Contact();
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setInternet(Internet.findDefaultInternet(em, "--", useTransaction));

            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, contact);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, contact);
            }
        }

        return contact;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            em.getTransaction().begin();

            // Save phone numbers and addresses
            for (PhoneNumber phoneNumber : getPhoneNumbers()) {
                if (phoneNumber.getId() == null) {
                    BusinessEntityUtils.saveBusinessEntity(em, phoneNumber);
                }
            }
            for (Address address : getAddresses()) {
                if (address.getId() == null) {
                    BusinessEntityUtils.saveBusinessEntity(em, address);
                }
            }

            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Contact not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
