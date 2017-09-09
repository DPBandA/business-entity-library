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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "petrolcompany")
@Inheritance(strategy = InheritanceType.JOINED)
public class PetrolCompany implements Customer, Company, BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
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
    private String notes;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFirstReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastAccessed;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PetrolStation> petrolStations;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BusinessOffice> businessOffices;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Internet internet;
    private String taxRegistrationNumber;

    public PetrolCompany() {
        petrolStations = new ArrayList<>();
        businessOffices = new ArrayList<>();
        addresses = new ArrayList<>();
        contacts = new ArrayList<>();
    }

     public PetrolCompany(String name) {
         this.name = name;
        petrolStations = new ArrayList<PetrolStation>();
        businessOffices = new ArrayList<BusinessOffice>();
        addresses = new ArrayList<Address>();
        contacts = new ArrayList<Contact>();
        //internet = new Internet();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public List<PetrolStation> getPetrolStations() {
        if (petrolStations != null) {
            Collections.sort(petrolStations);
        }
        return petrolStations;
    }

    public void setPetrolStations(List<PetrolStation> petrolStations) {
        this.petrolStations = petrolStations;
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
        if (!(object instanceof PetrolCompany)) {
            return false;
        }
        PetrolCompany other = (PetrolCompany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.PetrolCompany[id=" + id + "]";
    }

    @Override
    public String getName() {
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
    public Address getBillingAddress() {
        if (!addresses.isEmpty()) {
            return addresses.get(0);
        } else {
            addresses.add(new Address());
            return addresses.get(0);
        }
    }

    @Override
    public void setBillingAddress(Address billingAddress) {
        if (!addresses.isEmpty()) {
            addresses.set(0, billingAddress);
        }
        else {
            addresses.add(billingAddress);
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
    public List<BusinessOffice> getBusinessOffices() {
        return businessOffices;
    }

    @Override
    public void setBusinessOffices(List<BusinessOffice> businessOffices) {
        this.businessOffices = businessOffices;
    }

    @Override
    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    @Override
    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }
    
    
    

    public static List<PetrolCompany> findPetrolCompaniesByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<PetrolCompany> companies =
                    em.createQuery("SELECT p FROM PetrolCompany p where UPPER(p.name) like '"
                    + newName.toUpperCase().trim() + "%' ORDER BY p.name", PetrolCompany.class).getResultList();
            return companies;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<PetrolCompany>();
        }
    }
    
     public static PetrolCompany findPetrolCompanyByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<PetrolCompany> petrolCompanies = em.createQuery("SELECT p FROM PetrolCompany p "
                    + "WHERE UPPER(p.name) "
                    + "= '" + newName.toUpperCase() + "'", PetrolCompany.class).getResultList();
            if (petrolCompanies.size() > 0) {
                return petrolCompanies.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
     
     
    public static PetrolCompany findPetrolCompanyById(EntityManager em, Long id) {

        try {
            if (id != null) {
                PetrolCompany company = em.find(PetrolCompany.class, id);
                return company;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

   
    public static List<PetrolCompany> findAllPetrolCompanies(EntityManager em) {

        try {
            List<PetrolCompany> companies = em.createQuery("SELECT p FROM PetrolCompany p ORDER BY p.name", PetrolCompany.class).getResultList();

            return companies;
        } catch (Exception e) {
            System.out.println(e);
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
