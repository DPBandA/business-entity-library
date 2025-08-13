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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "businessoffice")
@NamedQueries({
    @NamedQuery(name = "findAllBusinessOffices", query = "SELECT b FROM BusinessOffice b ORDER BY b.name"),
    @NamedQuery(name = "findAllActiveBusinessOffices", query = "SELECT b FROM BusinessOffice b WHERE b.active = 1 ORDER BY b.name")
})
public class BusinessOffice implements Serializable, BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String code;
    private Boolean active;
    @Transient
    private Boolean isDirty;

    public BusinessOffice() {
        this.active = false;
        this.code = "";
        this.internet = null;
        this.name = "";
    }

    public BusinessOffice(String name) {
        this.active = false;
        this.code = "";
        this.internet = null;
        this.name = "";
        this.name = name;
    }

    public BusinessOffice(BusinessOffice src, Long id) {
        this.active = false;
        this.code = "";
        this.internet = null;
        this.name = "";
        doCopy(src, id);
    }

    public BusinessOffice(BusinessOffice src) {
        this.active = false;
        this.code = "";
        this.internet = null;
        this.name = "";
        doCopy(src);
    }

    public final void doCopy(BusinessOffice src, Long id) {
        this.id = id;
        this.name = src.name;
        this.code = src.code;
        this.active = src.active;
        if (src.internet != null) {
            internet = new Internet(src.internet);
        }
        if (src.address != null) {
            address = new Address(src.address);
        }
    }

    public final void doCopy(BusinessOffice src) {
        this.name = src.name;
        this.code = src.code;
        this.active = src.active;
        if (src.internet != null) {
            internet = new Internet(src.internet);
        }
        if (src.address != null) {
            address = new Address(src.address);
        }
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
    public Boolean getActive() {
        if (active == null) {
            active = false;
        }
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Internet getInternet() {
        return internet;
    }

    public void setInternet(Internet internet) {
        this.internet = internet;
    }

    public Address getAddress() {
        if (address == null) {
            address = new Address();
        }
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCode() {
        if (code == null) {
            code = "";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusinessOffice)) {
            return false;
        }
        BusinessOffice other = (BusinessOffice) object;

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

    public static List<BusinessOffice> findBusinessOfficesByName(
            EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<BusinessOffice> businessOffices
                    = em.createQuery("SELECT b FROM BusinessOffice b where UPPER(b.name) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY b.name", BusinessOffice.class).getResultList();
            return businessOffices;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<BusinessOffice> findActiveBusinessOfficesByName(
            EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<BusinessOffice> businessOffices
                    = em.createQuery("SELECT b FROM BusinessOffice b where UPPER(b.name) like '%"
                            + value.toUpperCase().trim() + "%' AND b.active = 1 ORDER BY b.name", BusinessOffice.class).getResultList();
            return businessOffices;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<BusinessOffice> findAllBusinessOffices(EntityManager em) {

        try {
            List<BusinessOffice> offices = em.createNamedQuery("findAllBusinessOffices", BusinessOffice.class).getResultList();

            return offices;
        } catch (Exception e) {

            return null;
        }

    }

    public static List<BusinessOffice> findAllActiveBusinessOffices(EntityManager em) {

        try {
            List<BusinessOffice> offices = em.createNamedQuery("findAllActiveBusinessOffices",
                    BusinessOffice.class).getResultList();

            return offices;
        } catch (Exception e) {

            return null;
        }

    }

    public static List<String> findAllBusinessOfficeNames(EntityManager em) {

        ArrayList<String> names = new ArrayList<String>();

        try {
            List<BusinessOffice> offices = em.createNamedQuery("findAllBusinessOffices", BusinessOffice.class).getResultList();
            for (BusinessOffice office : offices) {
                names.add(office.getName());
            }

            return names;
        } catch (Exception e) {

            return null;
        }

    }

    public static BusinessOffice findBusinessOfficeByName(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<BusinessOffice> offices = em.createQuery("SELECT b FROM BusinessOffice b "
                    + "WHERE UPPER(b.name) "
                    + "= '" + value.toUpperCase() + "'", BusinessOffice.class).getResultList();

            if (!offices.isEmpty()) {
                return offices.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return null;
    }

    public static BusinessOffice findBusinessOfficeById(EntityManager em, Long id) {

        try {
            BusinessOffice office = em.find(BusinessOffice.class, id);

            return office;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static BusinessOffice findDefaultBusinessOffice(EntityManager em, String name) {
        BusinessOffice office = BusinessOffice.findBusinessOfficeByName(em, name);

        if (office == null) {
            em.getTransaction().begin();
            office = new BusinessOffice();
            office.setName(name);
            BusinessEntityUtils.saveBusinessEntity(em, office);
            em.getTransaction().commit();
        }

        return office;
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

        return new ReturnMessage(false, "Business not saved");

    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
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
