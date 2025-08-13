/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.mt;

import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.cm.Customer;
import jm.com.dpbennett.business.entity.cert.Certification;
import jm.com.dpbennett.business.entity.hrm.Address;
import java.text.Collator;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "petrolstation")
public class PetrolStation implements Customer, BusinessEntity, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;
    private String type;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client client;
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
    private List<PetrolPump> petrolPumps;
    private String taxRegistrationNumber;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee lastAssignee;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Certification certification;
    @Transient
    private Boolean isDirty;

    public PetrolStation() {
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        petrolPumps = new ArrayList<>();
    }

    public PetrolStation(String name) {
        this.name = name;
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        petrolPumps = new ArrayList<>();
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

    public Certification getCertification() {
        if (certification == null) {
            certification = new Certification();
        }
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    public Integer getNumberOfPetrolPumpNozzles() {
        Integer numOfNozzles = 0;

        for (PetrolPump pump : petrolPumps) {
            numOfNozzles = numOfNozzles + pump.getNozzles().size();
        }

        return numOfNozzles;
    }

    public Integer getNumberOfPetrolPumpNozzlesNotWorking() {
        Integer numOfNozzles = 0;

        for (PetrolPump pump : getPetrolPumps()) {
            for (PetrolPumpNozzle nozzle : pump.getNozzles()) {
                if (nozzle.getStatus() != null) {
                    if (nozzle.getStatus().toUpperCase().trim().equals("NOT WORKING")
                            || nozzle.getStatus().toUpperCase().trim().equals("REJECTED")) {
                        ++numOfNozzles;
                    }
                }
            }
        }

        return numOfNozzles;
    }

    public Employee getLastAssignee() {
        if (lastAssignee == null) {
            return new Employee();
        }
        return lastAssignee;
    }

    public void setLastAssignee(Employee lastAssignee) {
        this.lastAssignee = lastAssignee;
    }

    public Client getClient() {
        if (client == null) {
            return new Client("");
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public Address getDefaultAddress() {
//        return billingAddress;
//    }
//
//    @Override
//    public void setMainBillingAddress(Address billingAddress) {
//        this.billingAddress = billingAddress;
//    }
    @Override
    public Address getDefaultAddress() {
        if (!addresses.isEmpty()) {
            return addresses.get(0);
        } else {
            addresses.add(new Address());
            return addresses.get(0);
        }
    }

    public List<PetrolPump> getPetrolPumps() {
        Collections.sort(petrolPumps);
        return petrolPumps;
    }

    public void setPetrolPumps(List<PetrolPump> petrolPumps) {
        this.petrolPumps = petrolPumps;
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
        if (!(object instanceof PetrolStation)) {
            return false;
        }
        PetrolStation other = (PetrolStation) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        if (name != null) {
            return name;
        } else {
            return "";
        }
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
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
    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    @Override
    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public static List<PetrolStation> findPetrolStationsByName(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<PetrolStation> stations
                    = em.createQuery("SELECT p FROM PetrolStation p where UPPER(p.name) like '"
                            + value.toUpperCase().trim() + "%' ORDER BY p.name", PetrolStation.class).getResultList();
            return stations;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<PetrolStation>();
        }
    }

    public static List<PetrolStation> findPetrolStationsByDateSearchField(
            EntityManager em,
            User user,
            String dateSearchField,
            String searchType,
            String searchText,
            Date startDate,
            Date endDate,
            Boolean includeSampleSearch) {

        List<PetrolStation> stations;
        searchText = searchText.replaceAll("&amp;", "&").replaceAll("'", "`");
        String searchQuery = null;
        String searchTextAndClause = "";

        if (searchType.equals("General")) {

            if (!searchText.equals("")) {
                searchTextAndClause
                        = " AND ("
                        + " UPPER(client.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(petrolStation.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(lastAssignee.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(lastAssignee.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
            }

            searchQuery
                    = "SELECT petrolStation FROM PetrolStation petrolStation"
                    + " JOIN petrolStation.client client"
                    + " JOIN petrolStation.certification certification"
                    + " JOIN petrolStation.lastAssignee lastAssignee"
                    //                    + " WHERE (2 >= 1)" // used as placeholder for now
                    + " WHERE (certification." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                    + " AND certification." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                    + searchTextAndClause
                    + " ORDER BY certification.expiryDate ASC";

        }

        try {
            stations = em.createQuery(searchQuery, PetrolStation.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return stations;
    }

    public static PetrolStation findPetrolStationByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<PetrolStation> companies = em.createQuery("SELECT p FROM PetrolStation p "
                    + "WHERE UPPER(p.name) "
                    + "= '" + value.toUpperCase() + "'", PetrolStation.class).getResultList();
            if (!companies.isEmpty()) {
                return companies.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static PetrolStation findPetrolStationById(EntityManager em, Long id) {

        try {
            PetrolStation station = em.find(PetrolStation.class, id);

            return station;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            getClient().save(em);

            for (Contact contact : contacts) {
                contact.save(em);
            }

            for (Address address : addresses) {
                address.save(em);
            }
            
            for (PetrolPump petrolPump : petrolPumps) {
                petrolPump.save(em);
            }
            
            getLastAssignee().save(em);
            getCertification().save(em);            

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Petrol Station not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact getDefaultContact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
