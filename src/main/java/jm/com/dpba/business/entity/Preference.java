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
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "preference")
@NamedQueries({
    @NamedQuery(name = "findAllPreferencesByName", query = "SELECT p FROM Preference p WHERE p.name = :name ORDER BY p.name"),
    @NamedQuery(name = "findAllPreferences", query = "SELECT p FROM Preference p ORDER BY p.name")
})
public class Preference implements Serializable, BusinessEntity, Converter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String preferenceValue;
    private String type;
    private String category;
    private String roles;
    private String description;

    public Preference() {
    }

    public Preference(String name, String preferenceValue) {
        this.name = name;
        this.preferenceValue = preferenceValue;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPreferenceValue() {
        if (preferenceValue == null) {
            preferenceValue = "";
        }
        return preferenceValue;
    }

    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Preference)) {
            return false;
        }
        Preference other = (Preference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name + ": " + id;
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

    public static List<Preference> findAllPreferencesByValue(EntityManager em, String value) {
//        try {
//            Query query = em.createNamedQuery("findAllPreferencesByName");
//            query.setParameter("name", name);
//            return query.getResultList();
//        } catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }
        try {
            String newValue = value.replaceAll("'", "''");

            List<Preference> preferences =
                    em.createQuery("SELECT p FROM Preference p where UPPER(p.preferenceValue) like '"
                    + newValue.toUpperCase().trim() + "%' ORDER BY p.preferenceValue", Preference.class).getResultList();
            return preferences;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<String> findAllPreferenceValues(EntityManager em, String value) {

        List<String> values = new ArrayList<>();

        try {
            String newValue = value.replaceAll("'", "''");

            List<Preference> preferences =
                    em.createQuery("SELECT p FROM Preference p where UPPER(p.preferenceValue) like '"
                    + newValue.toUpperCase().trim() + "%' ORDER BY p.preferenceValue", Preference.class).getResultList();
            
            if (preferences != null) {
                for (Preference preference : preferences) {
                    values.add(preference.preferenceValue);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            return values;
        }

        return values;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Preference preference = new Preference();

        if (value != null) {
            preference.setPreferenceValue(value);
        }

        return preference;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Preference) value).getPreferenceValue();
    }

    public static List<Preference> findAllPreferencesByName(EntityManager em, String name) {

        try {
            Query query = em.createNamedQuery("findAllPreferencesByName");
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Preference findPreferenceByValue(EntityManager em, String value) {

        try {
            String newValue = value.trim().replaceAll("'", "''");

            List<Preference> preferences = em.createQuery("SELECT p FROM Preference p "
                    + "WHERE UPPER(p.preferenceValue) "
                    + "= '" + newValue.toUpperCase() + "'", Preference.class).getResultList();
            if (preferences.size() > 0) {
                return preferences.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Preference findPreferenceById(EntityManager em, Long Id) {

        try {
            return em.find(Preference.class, Id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
