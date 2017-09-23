/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.MethodResult;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Desmond
 */
@Entity
@Table(name = "client")
@NamedQueries({
    @NamedQuery(name = "findAllClients", query = "SELECT c FROM Client c ORDER BY c.name")
})
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class Client implements Customer, Serializable, BusinessEntity {

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
    private String notes;
    private Boolean internal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFirstReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastAccessed;
    private Boolean tag;
    private String taxRegistrationNumber;
    private Boolean active;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntered;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    private Boolean international;

    public Client() {
        this.taxRegistrationNumber = "";
        this.tag = false;
        this.internal = false;
        this.notes = "";
        this.type = "";
        this.number = "";
        this.name = "";
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        internet = new Internet();
        active = true;
        international = false;
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
        this.type = "";
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
        this.type = "";
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
        if (enteredBy == null) {
            return new Employee();
        }
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
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public void setMainContact(Contact mainContact) {
//        if (!getContacts().isEmpty()) {
//            getContacts().set(0, contact);
//        }
        int index = 0;

        if (!getContacts().isEmpty()) {
            for (Contact contact : getContacts()) {
                if (contact.getType().equals("Main")) {
                    getContacts().set(index, mainContact);
                    return;
                }
                ++index;
            }
            mainContact.setType("Main");
            getContacts().set(0, mainContact);
        }
    }

    @Override
    public Address getBillingAddress() {
        if (!getAddresses().isEmpty()) {
            for (Address address : getAddresses()) {
                if (address.getType().equals("Billing")) {
                    return address;
                }
            }
            // use the first found address as the billing address
            Address address = getAddresses().get(0);
            address.setType("Billing");
            return address;

        } else {
            Address address = new Address();
            address.setType("Billing");
            getAddresses().add(address);
            return getAddresses().get(0);
        }
    }

    @Override
    public void setBillingAddress(Address billingAddress) {
        int index = 0;

        if (!getAddresses().isEmpty()) {
            for (Address address : getAddresses()) {
                if (address.getType().equals("Billing")) {
                    getAddresses().set(index, billingAddress);
                    return;
                }
                ++index;
            }
            billingAddress.setType("Billing");
            getAddresses().set(0, billingAddress);
        }
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

    public static List<Client> findClientsByTRN(EntityManager em, String trn) {

        try {
            String newTrn = trn.replaceAll("'", "''");

            List<Client> clients =
                    em.createQuery("SELECT c FROM Client c where UPPER(c.taxRegistrationNumber) like '"
                    + newTrn.toUpperCase().trim() + "%' ORDER BY c.taxRegistrationNumber", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<String> findActiveClientNames(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<String> names =
                    em.createQuery("SELECT c FROM Client c WHERE UPPER(c.name) like '"
                    + newName.toUpperCase().trim() + "%'"
                    + " AND c.active = 1"
                    + " ORDER BY c.name", String.class).getResultList();
            return names;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<Client> findActiveClientsByFirstPartOfName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Client> clients =
                    em.createQuery("SELECT c FROM Client c WHERE UPPER(c.name) like '"
                    + newName.toUpperCase().trim() + "%'"
                    + " AND c.active = 1"
                    + " ORDER BY c.name", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Client> findActiveClientsByAnyPartOfName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Client> clients =
                    em.createQuery("SELECT c FROM Client c WHERE UPPER(c.name) like '%"
                    + newName.toUpperCase().trim() + "%'"
                    + " AND c.active = 1"
                    + " ORDER BY c.name", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    // tk in client manager
    public static List<String> findClientNames(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<String> names =
                    em.createQuery("SELECT c FROM Client c where UPPER(c.name) like '"
                    + newName.toUpperCase().trim()
                    + "%' ORDER BY c.name", String.class).getResultList();
            return names;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Client> findClientsByFirstPartOfName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Client> clients =
                    em.createQuery("SELECT c FROM Client c where UPPER(c.name) like '"
                    + newName.toUpperCase().trim()
                    + "%' ORDER BY c.name", Client.class).getResultList();
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Client> findClientsByAnyPartOfName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Client> clients =
                    em.createQuery("SELECT c FROM Client c where UPPER(c.name) like '%"
                    + newName.toUpperCase().trim()
                    + "%'"
                    + " ORDER BY c.name", Client.class).getResultList();
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

    public static Client findClientByName(EntityManager em, String clientName, Boolean ignoreCase) {

        List<Client> clients;

        try {
            String newClientName = clientName.trim().replaceAll("'", "''");

            if (ignoreCase) {
                clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE UPPER(c.name) "
                        + "= '" + newClientName.toUpperCase() + "'", Client.class).getResultList();
            } else {
                clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE c.name "
                        + "= '" + newClientName + "'", Client.class).getResultList();
            }

            if (!clients.isEmpty()) {
                return clients.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error getting client: possibly null name");
            return null;
        }
    }

    public static Client findActiveClientByName(EntityManager em, String clientName, Boolean ignoreCase) {

        List<Client> clients;

        try {
            String newClientName = clientName.trim().replaceAll("'", "''");

            if (ignoreCase) {
                clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE UPPER(c.name) "
                        + "= '" + newClientName.toUpperCase() + "'"
                        + " AND c.active = 1", Client.class).getResultList();
            } else {
                clients = em.createQuery("SELECT c FROM Client c "
                        + "WHERE c.name "
                        + "= '" + newClientName + "'"
                        + " AND c.active = 1", Client.class).getResultList();
            }

            if (!clients.isEmpty()) {
                return clients.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error getting client: possibly null name");
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

    public static Client findDefaultClient(
            EntityManager em,
            String name,
            Boolean useTransaction) {
        Client client = findClientByName(em, name, true);

        if (client == null) {
            client = new Client(name);
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
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
