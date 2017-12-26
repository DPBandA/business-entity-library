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
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
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
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "findAllAddresses", query = "SELECT e FROM Address e ORDER BY e.type")
})
@XmlRootElement
public class Address implements Serializable, BusinessEntity, Comparable, Converter {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
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
        this.type = "";
    }

    public Address(Address src) {
        this.name = "";
        this.country = "";
        this.postalCode = "";
        this.stateOrProvince = "";
        this.city = "";
        this.addressLine2 = "";
        this.addressLine1 = "";
        this.type = "";
        this.Id = null;
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
        this.type = "";
        this.Id = null;
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
        this.Id = null;
    }

    public Boolean getIsDirty() {
        return isDirty;
    }

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
        return Id;
    }

    @Override
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getAddressLine1() {
        if (addressLine1 == null || addressLine1.isEmpty()) {
            addressLine1 = " ";
        }
        return addressLine1;
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

    public String getType() {
        if (type == null || type.isEmpty()) {
            type = " ";
        }
        return type;
    }

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
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;

        return !((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id)));
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
        if ((((Address) o).Id != null) && (this.Id != null)) {
            return Collator.getInstance().compare(((Address) o).Id.toString(), this.Id.toString());
        } else {
            return 0;
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Address address = new Address();

        if (value != null) {
            address.setName(value);
        }

        return address;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Address) value).getName();
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

    public static Address findAddressByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<Address> addresses = em.createQuery("SELECT a FROM  Address a "
                    + "WHERE UPPER(a.name) "
                    + "= '" + newName.toUpperCase() + "'", Address.class).getResultList();
            if (addresses.size() > 0) {
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
     * @param query
     * @return
     */
    public static Address findClientAddress(EntityManager em, String query) {

        try {
            String newQuery = query.replaceAll("'", "''");
            String address[] = newQuery.split("; ");
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

            if (addresses.size() > 0) {
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
            String newQuery = query.replaceAll("'", "''");
            String address[] = newQuery.split("; ");
            String addressLine1 = address[0];
            String addressLine2 = address[1];
            String city = address[2];
            String stateOrProvince = address[3];

            Client client = Client.findClientById(em, clientId);

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

    public static List<Address> findClientAddresses(EntityManager em, String query) {

        try {
            String newQuery = query.trim().replaceAll("'", "''");

            List<Address> addresses;
            Query SQLQuery = em.createQuery("SELECT a FROM Client c JOIN c.addresses a"
                    + " WHERE a.addressLine1 LIKE '%" + newQuery + "%'"
                    + " OR a.addressLine2 LIKE '%" + newQuery + "%'"
                    + " OR a.city LIKE '%" + newQuery + "%'"
                    + " OR a.stateOrProvince LIKE '%" + newQuery + "%'",
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
            System.out.println(e);
        }

        return new ReturnMessage(false, "Address not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
