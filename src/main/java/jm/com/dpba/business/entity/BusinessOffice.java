/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
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
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpba.business.entity.utils.BusinessEntityUtils;


/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "businessoffice")
@NamedQueries({
    @NamedQuery(name = "findAllBusinessOffices", query = "SELECT e FROM BusinessOffice e ORDER BY e.name")
})
@XmlRootElement
public class BusinessOffice implements Serializable, BusinessEntity, Converter {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private String name = "";
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet = null;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String code = "";
    private Boolean active = false;

    public BusinessOffice() {
    }

    public BusinessOffice(String name) {
        this.name = name;
    }

    public BusinessOffice(BusinessOffice src, Long id) {
        doCopy(src, id);
    }

    public BusinessOffice(BusinessOffice src) {
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

    public Boolean getActive() {
        if (active == null) {
            active = false;
        }
        return active;
    }

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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (name != null) {
            return name;
        } else {
            return "";
        }
    }

    public static List<BusinessOffice> findBusinessOfficesByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<BusinessOffice> businessOffices =
                    em.createQuery("SELECT b FROM BusinessOffice b where UPPER(b.name) like '"
                    + newName.toUpperCase().trim() + "%' ORDER BY b.name", BusinessOffice.class).getResultList();
            return businessOffices;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<BusinessOffice>();
        }
    }
    
    public static List<BusinessOffice> findActiveBusinessOfficesByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<BusinessOffice> businessOffices =
                    em.createQuery("SELECT b FROM BusinessOffice b where UPPER(b.name) like '"
                    + newName.toUpperCase().trim() + "%' AND b.active = 1 ORDER BY b.name", BusinessOffice.class).getResultList();
            return businessOffices;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<BusinessOffice>();
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

    public static BusinessOffice findBusinessOfficeByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<BusinessOffice> offices = em.createQuery("SELECT b FROM BusinessOffice b "
                    + "WHERE UPPER(b.name) "
                    + "= '" + newName.toUpperCase() + "'", BusinessOffice.class).getResultList();

            if (offices.size() > 0) {
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

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        BusinessOffice office = new BusinessOffice();

        if (value != null) {
            office.setName(value);
        }

        return office;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((BusinessOffice) value).getName();
    }

    public static BusinessOffice getDefaultBusinessOffice(EntityManager em, String name) {
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
}
