/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "laboratory")
@NamedQueries({
    @NamedQuery(name = "findAllLaboratories", query = "SELECT l FROM Laboratory l ORDER BY l.name")
})
@XmlRootElement
public class Laboratory implements BusinessEntity, Company, Serializable, Converter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String number;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BusinessOffice> businessOffices;
    private Boolean active;

    public Laboratory() {
        businessOffices = new ArrayList<BusinessOffice>();
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
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        if (!(object instanceof Laboratory)) {
            return false;
        }
        Laboratory other = (Laboratory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.Laboratory[id=" + id + "]";
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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public List<BusinessOffice> getBusinessOffices() {
        return businessOffices;
    }

    @Override
    public void setBusinessOffices(List<BusinessOffice> businessOffices) {
        this.businessOffices = businessOffices;
    }

    public static List<Laboratory> findLaboratoriesByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Laboratory> laboratories =
                    em.createQuery("SELECT l FROM Laboratory l where UPPER(l.name) like '"
                    + newName.toUpperCase().trim() + "%' ORDER BY l.name", Laboratory.class).getResultList();
            return laboratories;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Laboratory>();
        }
    }

    public static List<Laboratory> findAllActiveLaboratories(EntityManager em) {

        try {
            return em.createQuery("SELECT l from Laboratory l where l.active = 1 order by l.name", Laboratory.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Laboratory findLaboratoryByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<Laboratory> laboratories = em.createQuery("SELECT l FROM Laboratory l "
                    + "WHERE UPPER(l.name) "
                    + "= '" + newName.toUpperCase() + "'", Laboratory.class).getResultList();
            if (laboratories.size() > 0) {
                return laboratories.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Laboratory findLaboratoryById(EntityManager em, Long Id) {
        return em.find(Laboratory.class, Id);
    }

    public static List<String> findAllLaboratoryNames(EntityManager em) {

        ArrayList<String> names = new ArrayList<String>();

        try { // tk try String.class instead of Laboratory.class for better performance
            List<Laboratory> laboratories = em.createNamedQuery("findAllLaboratories", Laboratory.class).getResultList();
            for (Laboratory laboratory : laboratories) {
                names.add(laboratory.getName());
            }
            return names;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Laboratory laboratory = new Laboratory();

        if (value != null) {
            laboratory.setName(value);
        }

        return laboratory;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Laboratory) value).getName();
    }

    public static Laboratory getDefaultLaboratory(EntityManager em,
            String name) {
        Laboratory laboratory = Laboratory.findLaboratoryByName(em, name);

        if (laboratory == null) {
            laboratory = new Laboratory();

            em.getTransaction().begin();
            laboratory.setName(name);
            BusinessEntityUtils.saveBusinessEntity(em, laboratory);
            em.getTransaction().commit();
        }

        return laboratory;
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
