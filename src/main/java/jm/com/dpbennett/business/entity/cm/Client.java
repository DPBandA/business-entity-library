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
package jm.com.dpbennett.business.entity.cm;

import jm.com.dpbennett.business.entity.fm.Discount;
import jm.com.dpbennett.business.entity.hrm.Address;
import jm.com.dpbennett.business.entity.fm.AccPacCustomer;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Internet;
import jm.com.dpbennett.business.entity.fm.Tax;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Desmond Bennett
 */
@Entity
@Table(name = "client")
@NamedQueries({
    @NamedQuery(name = "findAllClients", query = "SELECT c FROM Client c ORDER BY c.name")
})
@XmlRootElement
public class Client implements Customer, Serializable, BusinessEntity, Comparable {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;
    private String type;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contact> contacts;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet;
    @Column(length = 1024)
    private String notes;
    private Boolean internal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFirstReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastAccessed;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    private Boolean tag;
    private String taxRegistrationNumber;
    private Boolean active;
    private Boolean international;
    @Transient
    private Boolean isDirty;
    // Billing  
    @Transient
    private AccPacCustomer financialAccount;
    private String accountingId;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Address billingAddress;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Contact billingContact;
    private Double creditLimit;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Discount discount;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Tax defaultTax;
    private Boolean taxExempt;
    private String typeOfBusiness;
    private String identification;
    private String identificationType;

    public Client() {
        this.taxRegistrationNumber = "";
        this.tag = false;
        this.internal = false;
        this.notes = "";
        this.type = "Client";
        this.number = "";
        this.name = "";
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        internet = new Internet();
        active = true;
        international = false;
        accountingId = "";
        typeOfBusiness = "";
        identification = "";
        identificationType = ""; 
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public Tax getDefaultTax() {
        return (defaultTax == null ? new Tax() : defaultTax);
    }

    public void setDefaultTax(Tax defaultTax) {
        this.defaultTax = defaultTax;
    }

    public String getAccountingId() {
        if (accountingId == null) {
            accountingId = "";
        }
        return accountingId;
    }

    public void setAccountingId(String accountingId) {
        this.accountingId = accountingId;
    }

    public AccPacCustomer getFinancialAccount() {
        if (financialAccount == null) {
            financialAccount = new AccPacCustomer(getAccountingId(), getName());
        }
        return financialAccount;
    }

    public void setFinancialAccount(AccPacCustomer financialAccount) {
        this.financialAccount = financialAccount;
        if (financialAccount != null) {
            accountingId = financialAccount.getIdCust();
        }
    }

    public Address getBillingAddress() {
        if (billingAddress == null) {
            if (!getBillingAddresses().isEmpty()) {
                billingAddress = getBillingAddresses().get(0);
            }
            else {
                return new Address();
            }
        }
        
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Contact getBillingContact() {
        if (billingContact == null) {
            if (!getContacts().isEmpty()) {
                billingContact = getContacts().get(0);
            }
        }
        return billingContact;
    }

    public void setBillingContact(Contact billingContact) {
        this.billingContact = billingContact;
    }

    public Discount getDiscount() {
        return (discount == null ? new Discount() : discount);
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Boolean getTaxExempt() {
        if (taxExempt == null) {
            taxExempt = false;
        }
        return taxExempt;
    }

    public void setTaxExempt(Boolean taxExempt) {
        this.taxExempt = taxExempt;
    }

    public Double getCreditLimit() {
        if (creditLimit == null) {
            creditLimit = 0.0;
        }
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
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

    public Date getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    public Employee getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Employee editedBy) {
        this.editedBy = editedBy;
    }

    public Client(Client src, Boolean active) {
        doCopy(src);
        this.active = active;
    }

    public Client(String name) {
        this.taxRegistrationNumber = "";
        this.tag = false;
        this.internal = false;
        this.notes = "";
        this.type = "Client";
        this.number = "";
        this.name = name;
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        internet = new Internet();
        active = true;
    }

    public Client(String name, Boolean active) {
        this.taxRegistrationNumber = "";
        this.tag = false;
        this.internal = false;
        this.notes = "";
        this.type = "Client";
        this.number = "";
        this.name = name;
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        internet = new Internet();
        this.active = active;
    }

    public Boolean getInternational() {
        if (international == null) {
            international = false;
        }
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public Employee getEnteredBy() {
        
        return enteredBy;
    }

    public void setEnteredBy(Employee enteredBy) {
        this.enteredBy = enteredBy;
    }

    /**
     * Copy the client without copying the id field
     *
     * @param src
     */
    public final void doCopy(Client src) {
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        name = src.name;
        number = src.number;
        type = src.type;
        if (src.contacts != null) {
            for (Contact contact : src.contacts) {
                contacts.add(new Contact(contact));
            }
        }
        if (src.addresses != null) {
            for (Address address : src.addresses) {
                addresses.add(new Address(address));
            }
        }
        if (src.internet != null) {
            internet = new Internet(src.internet);
        }
        notes = src.notes;
        internal = src.internal;
        dateFirstReceived = src.dateFirstReceived;
        dateLastAccessed = src.dateLastAccessed;
        tag = src.tag;
        active = src.active;
        taxRegistrationNumber = src.taxRegistrationNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public String getIsActive() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setIsActive(String active) {
        if (active.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
    }

    @Override
    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    @Override
    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
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

    /**
     * This method guards against returning very long names. This is used in an
     * autocomplete JSF component for instance to prevent the list of clients
     * from extending beyond the screen. In the future, the maximum length of
     * say 50 will be a value stored in the resource bundle of the BEL.
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

    @Override
    @XmlTransient
    public List<Address> getAddresses() {
        if (addresses != null) {
            Collections.sort(addresses);
        } else {
            addresses = new ArrayList<>();
        }

        return addresses;
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

    @Override
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    @XmlTransient
    public List<Contact> getContacts() {
        if (contacts != null) {
            Collections.sort(contacts);
        } else {
            contacts = new ArrayList<>();
        }

        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getStringListOfContactPhoneNumbers() {
        String list = "";

        for (Contact contact : getContacts()) {
            //for (PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
            if (list.equals("")) // first? 
            {
                list = contact.getMainPhoneNumber().getLocalNumber();
            } else {
                list = list + ", " + contact.getMainPhoneNumber().getLocalNumber();
            }
            //}
        }

        return list;
    }

    /**
     * Get the first main contact which is treated as the main contact in the
     * list of contacts.
     *
     * @return
     */
    @Override
    public Contact getDefaultContact() {
        if (!getContacts().isEmpty()) {
            // Use the last found contact as the main contact if none was found.            
            return getContacts().get(getContacts().size() - 1);
        } else {
            return new Contact("", "", "Main");
        }
    }

    /**
     * Get the main contact which is treated as the main contact in the list of
     * contacts.
     *
     * @return
     */
    public Contact getMainContact() {
        if (!getContacts().isEmpty()) {
            //return getContacts().get(0);
            for (Contact contact : getContacts()) {
                if (contact.getType().equals("Main")) {
                    return contact;
                }
            }
            // use the first found address as the billing address
            Contact contact = getContacts().get(0);
            contact.setType("Main");
            return contact;
        } else {
            Contact contact = new Contact();
            contact.setType("Main");
            getContacts().add(contact);
            return getContacts().get(0);
        }
    }

    public Contact addContact(Contact contact) {
        getContacts().add(contact);
        return getContacts().get(0);
    }

    /**
     * Returns the first found address with billing type "Billing" as the main
     * billing address.
     *
     * @return
     */
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

    public Address addAddress(Address address) {
        getAddresses().add(address);
        return getAddresses().get(0);
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public Date getDateFirstReceived() {
        return dateFirstReceived;
    }

    @Override
    public void setDateFirstReceived(Date dateFirstReceived) {
        this.dateFirstReceived = dateFirstReceived;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        if (name != null) {
            return name.replaceAll("&#38;", "&");
        } else {
            return "";
        }
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

    public static List<Client> findClientsByTRN(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Client> clients;
            clients = em.createQuery("SELECT c FROM Client c where UPPER(c.taxRegistrationNumber) like '"
                    + value.toUpperCase() + "%' ORDER BY c.taxRegistrationNumber", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<String> findActiveClientNames(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<String> names
                    = em.createQuery("SELECT c FROM Client c WHERE UPPER(c.name) like '"
                            + value.toUpperCase() + "%'"
                            + " AND c.active = 1"
                            + " ORDER BY c.name", String.class).getResultList();
            return names;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Client> findActiveClientsByFirstPartOfName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Client> clients
                    = em.createQuery("SELECT c FROM Client c WHERE c.name like '"
                            + value + "%'"
                            + " AND c.active = 1"
                            + " ORDER BY c.id", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Client> findActiveClientsByAnyPartOfName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Client> clients
                    = em.createQuery("SELECT c FROM Client c WHERE c.name like '%"
                            + value + "%'"
                            + " AND c.active = 1"
                            + " ORDER BY c.id", Client.class).setMaxResults(10).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Client> findClientsByAnyPartOfName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Client> clients
                    = em.createQuery("SELECT c FROM Client c WHERE c.name like '%"
                            + value + "%'"
                            + " ORDER BY c.id", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    // tk in client manager
    public static List<String> findClientNames(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<String> names
                    = em.createQuery("SELECT c FROM Client c where UPPER(c.name) like '"
                            + value.toUpperCase()
                            + "%' ORDER BY c.name", String.class).getResultList();
            return names;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Client> findClientsByFirstPartOfName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Client> clients
                    = em.createQuery("SELECT c FROM Client c where UPPER(c.name) like '"
                            + value.toUpperCase()
                            + "%' ORDER BY c.name", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    // is in client manager. remove later
    public static List<Client> getAllClients(EntityManager em) {

        try {
            List<Client> clients = em.createNamedQuery("findAllClients", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Client findClientByName(EntityManager em, String value, Boolean ignoreCase) {

        List<Client> clients;

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            if (ignoreCase) {
                clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE UPPER(c.name) "
                        + "= '" + value.toUpperCase() + "'", Client.class).getResultList();
            } else {
                clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE c.name "
                        + "= '" + value + "'", Client.class).getResultList();
            }

            if (!clients.isEmpty()) {
                return clients.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Client findClientById(EntityManager em, Long id) {

        try {
            Client client = em.find(Client.class, id);

            return client;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Client findActiveClientByName(EntityManager em, String value, Boolean ignoreCase) {

        List<Client> clients;

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            if (ignoreCase) {
                clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE UPPER(c.name) "
                        + "= '" + value.toUpperCase() + "'"
                        + " AND c.active = 1", Client.class).getResultList();
            } else {
                clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE c.name "
                        + "= '" + value + "'"
                        + " AND c.active = 1", Client.class).getResultList();
            }

            if (!clients.isEmpty()) {
                return clients.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // tk change name to findClientById
    public static Client getClientById(EntityManager em, Long Id) {

        try {
            Client client = em.find(Client.class, Id);
            return client;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Client findActiveDefaultClient(
            EntityManager em,
            String name,
            Boolean useTransaction) {
        Client client = findActiveClientByName(em, name, false);

        if (client == null) {
            client = new Client(name);
            client.setActive(true);
            client.setInternet(Internet.findDefaultInternet(em, "--", useTransaction));
            client.setEnteredBy(Employee.findDefaultEmployee(em, "--", "--", useTransaction));

            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, client);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, client);
            }
        }

        return client;
    }

    public static List<Client> findClientsBySearchPattern(EntityManager em, String searchPattern) {

        try {
            List<Client> clients = em.createQuery("SELECT c FROM Client c "
                    + "WHERE UPPER(c.name) "
                    + "LIKE '" + searchPattern.toUpperCase() + "%' "
                    + "ORDER BY c.name", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            // Save contacts and addresses
            for (Contact contact : getContacts()) {
                if (contact.getId() == null) {
                    contact.save(em);
                }
            }
            for (Address address : getAddresses()) {
                if (address.getId() == null) {
                    address.save(em);
                }
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Client not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getName(), ((Client) o).getName());
    }
}
