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
package jm.com.dpbennett.business.entity.hrm;

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
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.fm.MarketProduct;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "manufacturer")
@NamedQueries({
    @NamedQuery(name = "findAllManufacturers", query = "SELECT m FROM Manufacturer m ORDER BY m.name")
})
public class Manufacturer implements BusinessEntity, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String code;
    private String status;
    private String registrationStatus;
    private String businessRegistration;
    private String taxRegistration;
    private Integer yearOfLastProductTest;
    private String paymentStatus;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date paymentDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRegistrationDue;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastVisited;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<MarketProduct> marketProducts;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Contact> contacts;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Address> addresses;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee productLastSampledBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Internet internet;
    @Column(length = 1024)
    private String notes;
    private Boolean tag;
    private Boolean active;
    private Boolean international;
    @Transient
    private Boolean isDirty;

    public Manufacturer() {
        this.name = "";
        this.tag = false;
        this.notes = "";
        this.type = "Manufacturer";
        marketProducts = new ArrayList<>();
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        internet = new Internet();
        active = true;
        international = false;
    }

    public Manufacturer(String name) {
        this.name = name;
        this.tag = false;
        this.notes = "";
        this.type = "Manufacturer";
        marketProducts = new ArrayList<>();
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        internet = new Internet();
        active = true;
        international = false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<MarketProduct> getMarketProducts() {
        return marketProducts;
    }

    public void setMarketProducts(List<MarketProduct> marketProducts) {
        this.marketProducts = marketProducts;
    }

    public Integer getYearOfLastProductTest() {
        return yearOfLastProductTest;
    }

    public void setYearOfLastProductTest(Integer yearOfLastProductTest) {
        this.yearOfLastProductTest = yearOfLastProductTest;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getDateRegistrationDue() {
        return dateRegistrationDue;
    }

    public void setDateRegistrationDue(Date dateRegistrationDue) {
        this.dateRegistrationDue = dateRegistrationDue;
    }

    public Date getDateLastVisited() {
        return dateLastVisited;
    }

    public void setDateLastVisited(Date dateLastVisited) {
        this.dateLastVisited = dateLastVisited;
    }

    public Employee getProductLastSampledBy() {
        return productLastSampledBy;
    }

    public void setProductLastSampledBy(Employee productLastSampledBy) {
        this.productLastSampledBy = productLastSampledBy;
    }

    public String getBusinessRegistration() {
        return businessRegistration;
    }

    public void setBusinessRegistration(String businessRegistration) {
        this.businessRegistration = businessRegistration;
    }

    public String getTaxRegistration() {
        return taxRegistration;
    }

    public void setTaxRegistration(String taxRegistration) {
        this.taxRegistration = taxRegistration;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Boolean getInternational() {

        if (internet == null) {
            internet = new Internet();
        }
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public List<Contact> getContacts() {
        if (contacts != null) {
            Collections.sort(contacts);
        } else {
            contacts = new ArrayList<>();
        }

        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Address> getAddresses() {
        if (addresses != null) {
            Collections.sort(addresses);
        } else {
            addresses = new ArrayList<>();
        }

        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manufacturer)) {
            return false;
        }
        Manufacturer other = (Manufacturer) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Manufacturer> findAllActiveManufacturers(EntityManager em) {

        try {

            return em.createQuery("SELECT m FROM Manufacturer m WHERE m.active = 1 OR m.active IS NULL ORDER BY m.name", Manufacturer.class).getResultList();

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Manufacturer> findManufacturersBySearchPattern(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Manufacturer> manufacturers = em.createQuery("SELECT m FROM Manufacturer m "
                    + "WHERE UPPER(m.name) "
                    + "LIKE '" + value.toUpperCase() + "%' "
                    + "ORDER BY m.name", Manufacturer.class).getResultList();
            return manufacturers;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Manufacturer findManufacturerById(EntityManager em, Long Id) {

        try {
            Manufacturer manufacturer = em.find(Manufacturer.class, Id);
            return manufacturer;
        } catch (Exception e) {
            return null;
        }
    }

    // Get the first manufacturer that matches the given name
    public static Manufacturer findManufacturerByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Manufacturer> manufacturers = em.createQuery("SELECT m FROM Manufacturer m "
                    + "WHERE UPPER(m.name) "
                    + "= '" + value.toUpperCase() + "'", Manufacturer.class).getResultList();
            if (!manufacturers.isEmpty()) {
                return manufacturers.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Manufacturer findDefaultManufacturer(EntityManager em,
            String name,
            Boolean useTransaction) {

        Manufacturer manufacturer = Manufacturer.findManufacturerByName(em, name);

        if (manufacturer == null) {
            manufacturer = new Manufacturer();
            manufacturer.setName(name);

            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, manufacturer);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, manufacturer);
            }
        }

        return manufacturer;
    }

    public static Manufacturer findActiveManufacturerByName(EntityManager em,
            String value,
            Boolean ignoreCase) {

        List<Manufacturer> manufacturers;

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            if (ignoreCase) {
                manufacturers = em.createQuery("SELECT m FROM Manufacturer m "
                        + "WHERE UPPER(m.name) "
                        + "= '" + value.toUpperCase() + "'"
                        + " AND m.active = 1", Manufacturer.class).getResultList();
            } else {
                manufacturers = em.createQuery("SELECT m FROM Manufacturer m "
                        + "WHERE m.name "
                        + "= '" + value + "'"
                        + " AND m.active = 1", Manufacturer.class).getResultList();
            }

            if (!manufacturers.isEmpty()) {
                return manufacturers.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Manufacturer> findActiveManufacturersByAnyPartOfName(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Manufacturer> manufacturers
                    = em.createQuery("SELECT m FROM Manufacturer m WHERE m.name like '%"
                            + value + "%'"
                            + " AND m.active = 1"
                            + " ORDER BY m.name", Manufacturer.class).getResultList();
            return manufacturers;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Manufacturer> findManufacturersByAnyPartOfName(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Manufacturer> manufacturers
                    = em.createQuery("SELECT m FROM Manufacturer m WHERE m.name like '%"
                            + value + "%'"
                            + " ORDER BY m.id", Manufacturer.class).getResultList();

            return manufacturers;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            for (MarketProduct marketProduct : getMarketProducts()) {
                marketProduct.save(em);
            }

            for (Contact contact : getContacts()) {
                contact.save(em);
            }

            for (Address address : getAddresses()) {
                address.save(em);
            }

            if (getProductLastSampledBy() != null) {
                getProductLastSampledBy().save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Manufacturer not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getName(), ((Manufacturer) o).getName());
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

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
