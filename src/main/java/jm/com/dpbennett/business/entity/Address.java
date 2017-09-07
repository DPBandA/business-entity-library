/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.utils.BusinessEntityUtils;

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
    private String type = "";
    private String addressLine1 = "";
    private String addressLine2 = "";
    private String city = "";
    private String stateOrProvince = "";
    private String postalCode = "";
    private String country = "";
    private String name = "";

    public Address() {
    }

    public Address(Address src) {
        this.Id = null;
        doCopy(src);
    }

    public Address(String name) {
        this.Id = null;
        this.name = name;
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
        if (addressLine1 == null) {
            addressLine1 = "";
        }
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        if (addressLine2 == null) {
            addressLine2 = "";
        }
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
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

    public String getCity() {
        if (city == null) {
            city = "";
        }
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        if (country == null) {
            country = "";
        }
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        if (postalCode == null) {
            postalCode = "";
        }
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateOrProvince() {
        if (stateOrProvince == null) {
            stateOrProvince = "";
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (getAddressLine1().trim().isEmpty() ? "" : getAddressLine1())
                + (getAddressLine2().trim().isEmpty() ? "" : ", " + getAddressLine2())
                + (getCity().trim().isEmpty() ? "" : ", " + getCity())
                + (getStateOrProvince().trim().isEmpty() ? "" : ", " + getStateOrProvince())
                + "\n";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    @Override
    public int compareTo(Object o) {
        if ((((Address) o).Id != null) && (this.Id != null)) {
            return Collator.getInstance().compare(
                    new Long(((Address) o).Id).toString(),
                    new Long(this.Id).toString());
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
}
