/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.MethodResult;


/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "business")
@XmlRootElement
public class Business implements Customer, BusinessEntity, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    private String name = "";
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contact> contacts = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastAccessed = null;
    private String number = "";
    private String type = "";
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFirstReceived = null;
    private String notes = "";
    private String taxRegistrationNumber = "";

    public Business() {
        addresses = new ArrayList<Address>();
        contacts = new ArrayList<Contact>();
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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
    @XmlTransient
    public List<Address> getAddresses() {
        return addresses;
    }

    @Override
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    @XmlTransient
    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Address getBillingAddress() {
        if (addresses != null) {
            if (!addresses.isEmpty()) {
                return addresses.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setBillingAddress(Address billingAddress) {
        if (addresses != null) {
            if (!addresses.isEmpty()) {
                addresses.set(0, billingAddress);
            }
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
    public static Business findBusinessByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<Business> businesses = em.createQuery("SELECT b FROM Business b "
                    + "WHERE UPPER(b.name) "
                    + "= '" + newName.toUpperCase() + "'", Business.class).getResultList();
            if (businesses.size() > 0) {
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

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
