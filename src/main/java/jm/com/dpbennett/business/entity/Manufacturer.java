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
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "manufacturer")
@NamedQueries({
    @NamedQuery(name = "findAllManufacturers", query = "SELECT e FROM Manufacturer e ORDER BY e.name")
})
@XmlRootElement
public class Manufacturer implements Serializable, BusinessEntity, Converter {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    @Column(name = "Name")
    private String name = "";
    // tk replace all of these fields with Address, Contact, Internet classes
    // see Client class for ideas.
    @Column(name = "Street")
    private String street = "";
    @Column(name = "PO")
    private String pO = "";
    @Column(name = "City")
    private String city = "";
    @Column(name = "Country")
    private String country = "";
    @Column(name = "Phone")
    private String phone = "";
    @Column(name = "Fax")
    private String fax = "";
    @Column(name = "Email")
    private String email = "";
    @Transient
    private Boolean isDirty;

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getpO() {
        return pO;
    }

    public void setpO(String pO) {
        this.pO = pO;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Manufacturer> findManufacturersBySearchPattern(EntityManager em, String searchPattern) {

        try {
            List<Manufacturer> manufacturers = em.createQuery("SELECT m FROM Manufacturer m "
                    + "WHERE UPPER(m.name) "
                    + "LIKE '" + searchPattern.toUpperCase() + "%' "
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
    public static Manufacturer findManufacturerByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<Manufacturer> manufacturers = em.createQuery("SELECT m FROM Manufacturer m "
                    + "WHERE UPPER(m.name) "
                    + "= '" + newName.toUpperCase() + "'", Manufacturer.class).getResultList();
            if (manufacturers.size() > 0) {
                return manufacturers.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName(value.trim());

            return manufacturer;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null || value.equals("")) {
            return "";
        } else {
            if (((Manufacturer) value).getName() != null) {
                return ((Manufacturer) value).getName().replaceAll("&#38;", "&");
            } else {
                return "";
            }
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

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
