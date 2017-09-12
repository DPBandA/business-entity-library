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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "classification")
@NamedQueries({
    @NamedQuery(name = "findAllClassifications", query = "SELECT c FROM Classification c ORDER BY c.name"),
    @NamedQuery(name = "findAllActiveClassifications", query = "SELECT c FROM Classification c WHERE c.active = 1 ORDER BY c.name")
})
@XmlRootElement
public class Classification implements BusinessEntity, Serializable, Converter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private String name = "";
    private Boolean active;
    @Column(length = 1024)
    private String description;
    private Boolean isEarning;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsEarning() {
        if (isEarning == null) {
            isEarning = true;
        }
        return isEarning;
    }

    public void setIsEarning(Boolean isEarning) {
        this.isEarning = isEarning;
    }

    public String getDescription() {
        if (description == null) {
            description = "";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setUsable(String usable) {
        if (usable.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
    }
    
    public String getEarning() {
        if (getIsEarning()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setEarning(String earning) {
        if (earning.equals("Yes")) {
            setIsEarning(true);
        } else {
            setIsEarning(false);
        }
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
        if (!(object instanceof Classification)) {
            return false;
        }
        Classification other = (Classification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Classification> findAllClassifications(EntityManager em) {

        try {
            List<Classification> classfications = em.createNamedQuery("findAllClassifications", Classification.class).getResultList();

            return classfications;
        } catch (Exception e) {

            return null;
        }

    }

    public static List<Classification> findAllActiveClassifications(EntityManager em) {

        try {
            List<Classification> classfications = em.createNamedQuery("findAllActiveClassifications", Classification.class).getResultList();

            return classfications;
        } catch (Exception e) {
            return null;
        }

    }

    public static Classification findClassificationById(EntityManager em, Long id) {

        try {
            Classification classification = em.find(Classification.class, id);

            return classification;
        } catch (Exception e) {

            return null;
        }
    }

    public static Classification findClassificationByName(EntityManager em, String classificationName) {

        try {
            String newClassificationName = classificationName.trim().replaceAll("'", "''");

            List<Classification> classifications = em.createQuery("SELECT c FROM Classification c "
                    + "WHERE UPPER(c.name) "
                    + "= '" + newClassificationName.toUpperCase() + "'", Classification.class).getResultList();
            if (classifications.size() > 0) {
                return classifications.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static List<Classification> findClassificationsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Classification> classifications =
                    em.createQuery("SELECT c FROM Classification c where UPPER(c.name) like '"
                    + newName.toUpperCase().trim() + "%' ORDER BY c.name", Classification.class).getResultList();
            return classifications;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Classification> findActiveClassificationsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Classification> classifications =
                    em.createQuery("SELECT c FROM Classification c where UPPER(c.name) like '"
                    + newName.toUpperCase().trim() + "%' AND c.active = 1 ORDER BY c.name", Classification.class).getResultList();
            return classifications;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            Classification classification = new Classification();
            classification.setName(submittedValue.trim());

            return classification;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null || value.equals("")) {
            return "";
        } else {
            if (((Classification) value).getName() != null) {
                return ((Classification) value).getName().replaceAll("&#38;", "&");
            } else {
                return "";
            }
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