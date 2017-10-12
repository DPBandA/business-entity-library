/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "contact")
@XmlRootElement
public class Contact implements Person, BusinessEntity, Serializable, Comparable, Converter {

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

    public Contact() {
        phoneNumbers = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Contact(String name) {
        this.name = name;
        phoneNumbers = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Contact(Contact src) {
        doCopy(src);
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if ((((Contact) o).id != null) && (this.id != null)) {
            return Collator.getInstance().compare(
                    new Long(((Contact) o).id).toString(),
                    new Long(this.id).toString());
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
     * @return
     */
    public static Contact findClientContactById(EntityManager em, String query, Long clientId) {

        try {
            String newQuery = query.replaceAll("'", "''");
            String contact[] = newQuery.split(", ");
            String lastname = contact[0];
            String firstname = contact[1];

            Client client = Client.findClientById(em, clientId);

            if (client != null) {
                for (Contact cont : client.getContacts()) {
                    if (cont.getFirstName().equals(firstname)
                            && cont.getLastName().equals(lastname)) {
                        return cont;
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
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Contact contact = new Contact();

        if (value != null) {
            contact.setName(value);
        }

        return contact;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Contact) value).getName();
    }

    @Override
    public MethodResult save(EntityManager em) {
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

            return new MethodResult();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new MethodResult(false, "Contact not saved");
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
