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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "findAllAddresses", query = "SELECT e FROM Address e ORDER BY e.type")
})
public class Address implements Serializable, BusinessEntity, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String stateOrProvince;
    private String postalCode;
    private String country;
    private String name;
    @Transient
    private Boolean isDirty;

    public Address() {
        this.name = "";
        this.country = "";
        this.postalCode = "";
        this.stateOrProvince = "";
        this.city = "";
        this.addressLine2 = "";
        this.addressLine1 = "";
        this.type = "Billing";
    }

    public Address(Address src) {
        this.name = "";
        this.country = "";
        this.postalCode = "";
        this.stateOrProvince = "";
        this.city = "";
        this.addressLine2 = "";
        this.addressLine1 = "";
        this.type = "Billing";
        this.id = null;
        doCopy(src);
    }

    public Address(String name) {
        this.name = name;
        this.country = "";
        this.postalCode = "";
        this.stateOrProvince = "";
        this.city = "";
        this.addressLine2 = "";
        this.addressLine1 = "";
        this.type = "Billing";
        this.id = null;
    }

    public Address(String name, String type) {
        this.name = name;
        this.country = "";
        this.postalCode = "";
        this.stateOrProvince = "";
        this.city = "";
        this.addressLine2 = "";
        this.addressLine1 = "";
        this.type = type;
        this.id = null;
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

    public Address prepare() {

        addressLine1 = getAddressLine1().trim();
        addressLine2 = getAddressLine2().trim();
        city = getCity().trim();
        stateOrProvince = getStateOrProvince().trim();
        country = getCountry().trim();

        return this;
    }

    public final void doCopy(Address src) {
        type = src.type;
        addressLine1 = src.addressLine1;
        addressLine2 = src.addressLine2;
        city = src.city;
        stateOrProvince = src.stateOrProvince;
        postalCode = src.postalCode;
        country = src.country;
        name = src.name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        if (addressLine1 == null || addressLine1.isEmpty()) {
            addressLine1 = " ";
        }
        return addressLine1;
    }

    public String getAddressLine1FriendlyDisplay() {
        if (!getAddressLine1().trim().isEmpty()) {
            return getAddressLine1().trim();
        } else {
            return "-- none --";
        }
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        if (addressLine2 == null || addressLine2.isEmpty()) {
            addressLine2 = " ";
        }
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Override
    public String getType() {
        if (type == null || type.isEmpty()) {
            type = " ";
        }
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        if (city == null || city.isEmpty()) {
            city = " ";
        }
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        if (country == null || country.isEmpty()) {
            country = " ";
        }
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        if (postalCode == null || postalCode.isEmpty()) {
            postalCode = " ";
        }
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateOrProvince() {
        if (stateOrProvince == null || stateOrProvince.isEmpty()) {
            stateOrProvince = " ";
        }
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        if (getAddressLine1().trim().isEmpty()
                && getAddressLine2().trim().isEmpty()
                && getCity().trim().isEmpty()
                && getStateOrProvince().trim().isEmpty()) {
            return "";
        } else {
            return (getAddressLine1().trim().isEmpty() ? "" : getAddressLine1())
                    + (getAddressLine2().trim().isEmpty() ? "" : ", " + getAddressLine2())
                    + (getCity().trim().isEmpty() ? "" : ", " + getCity())
                    + (getStateOrProvince().trim().isEmpty() ? "" : ", " + getStateOrProvince());
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {

        if (getAddressLine1().trim().isEmpty()
                && getAddressLine2().trim().isEmpty()
                && getCity().trim().isEmpty()
                && getStateOrProvince().trim().isEmpty()) {
            return "";
        } else {

//            Address.prepare(this);
            return getAddressLine1()
                    + "; " + getAddressLine2()
                    + "; " + getCity()
                    + "; " + getStateOrProvince();
        }
    }

    public String getDisplay() {

        if (getAddressLine1().trim().isEmpty()
                && getAddressLine2().trim().isEmpty()
                && getCity().trim().isEmpty()
                && getStateOrProvince().trim().isEmpty()) {
            return "";
        } else {
            return (getAddressLine1().trim().isEmpty() ? "" : getAddressLine1())
                    + (getAddressLine2().trim().isEmpty() ? "" : ",\n" + getAddressLine2())
                    + (getCity().trim().isEmpty() ? "" : ",\n" + getCity())
                    + (getStateOrProvince().trim().isEmpty() ? "" : ",\n" + getStateOrProvince());
        }
    }

    @Override
    public int compareTo(Object o) {
        if ((((Address) o).id != null) && (this.id != null)) {
            if (((Address) o).id < this.id) {
                return 1;
            } else if (Objects.equals(((Address) o).id, this.id)) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public static Address findDefaultAddress(
            EntityManager em,
            String name,
            Boolean useTransaction) {

        Address address = findAddressByName(em, name);

        if (address == null) {
            address = new Address(name);

            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, address);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, address);
            }
        }

        return address;
    }

    public static Address findAddressByName(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
           
            List<Address> addresses = em.createQuery("SELECT a FROM Address a "
                    + "WHERE UPPER(a.name) "
                    + "= '" + value.toUpperCase() + "'", Address.class).getResultList();
            if (!addresses.isEmpty()) {
                return addresses.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Find an address associated with a client.
     *
     * @param em
     * @param value
     * @return
     */
    public static Address findClientAddress(EntityManager em, String value) {

        try {
            
            String address[] = value.split("; ");
            String addressLine1 = address[0];
            String addressLine2 = address[1];
            String city = address[2];
            String stateOrProvince = address[3];

            List<Address> addresses;
            Query SQLQuery = em.createQuery("SELECT a FROM Client c JOIN c.addresses a"
                    + " WHERE a.addressLine1 = '" + addressLine1 + "'"
                    + " AND (a.addressLine2 = '" + addressLine2 + "' OR a.addressLine2 IS NULL)"
                    + " AND (a.city = '" + city + "' OR a.city IS NULL)"
                    + " AND (a.stateOrProvince = '" + stateOrProvince + "' OR a.stateOrProvince IS NULL)",
                    Address.class);
            addresses = SQLQuery.getResultList();

            if (!addresses.isEmpty()) {
                return addresses.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Find an address associated with a client given the client's id. This
     * method uses a cude method by finding the client first then looping over
     * the addresses to find the required address. I full SQL solution is to be
     * developed.
     *
     * @param em
     * @param query
     * @param clientId
     * @return
     */
    public static Address findClientAddressById(EntityManager em, String query, Long clientId) {

        try {

            String address[] = query.split("; ");
            String addressLine1 = address[0];
            String addressLine2 = address[1];
            String city = address[2];
            String stateOrProvince = address[3];

            Client client = Client.findById(em, clientId);

            if (client != null) {
                for (Address addr : client.getAddresses()) {
                    if (addr.getAddressLine1().equals(addressLine1)
                            && addr.getAddressLine2().equals(addressLine2)
                            && addr.getCity().equals(city)
                            && addr.getStateOrProvince().equals(stateOrProvince)) {
                        return addr;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Address findClientAddress(String query, Client client) {

        try {

            String address[] = query.split("; ");
            String addressLine1 = address[0];
            String addressLine2 = address[1];
            String city = address[2];
            String stateOrProvince = address[3];

            if (client != null) {
                for (Address addr : client.getAddresses()) {
                    if (addr.getAddressLine1().equals(addressLine1)
                            && addr.getAddressLine2().equals(addressLine2)
                            && addr.getCity().equals(city)
                            && addr.getStateOrProvince().equals(stateOrProvince)) {
                        return addr;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Address findAddress(String query, List<Address> addresses) {

        try {

            String address[] = query.split("; ");
            String addressLine1 = address[0];
            String addressLine2 = address[1];
            String city = address[2];
            String stateOrProvince = address[3];

            for (Address addr : addresses) {
                if (addr.getAddressLine1().equals(addressLine1)
                        && addr.getAddressLine2().equals(addressLine2)
                        && addr.getCity().equals(city)
                        && addr.getStateOrProvince().equals(stateOrProvince)) {
                    return addr;
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Address> findClientAddresses(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("'", "`");
           
            List<Address> addresses;
            Query SQLQuery = em.createQuery("SELECT a FROM Client c JOIN c.addresses a"
                    + " WHERE a.addressLine1 LIKE '%" + value + "%'"
                    + " OR a.addressLine2 LIKE '%" + value + "%'"
                    + " OR a.city LIKE '%" + value + "%'"
                    + " OR a.stateOrProvince LIKE '%" + value + "%'",
                    Address.class);
            addresses = SQLQuery.getResultList();

            return addresses;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Address findAddressById(EntityManager em, Long id) {

        try {
            Address address = em.find(Address.class, id);

            return address;
        } catch (Exception e) {

            return null;
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
            System.out.println("Address save exception: " + e);
        }

        return new ReturnMessage(false, "Address not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static Boolean validate(Address address) {

        if (address != null) {
            if (!BusinessEntityUtils.validateText(address.getAddressLine1().trim())) {
                return false;
            }
        } else {
            return false;
        }

        return true;
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
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
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
