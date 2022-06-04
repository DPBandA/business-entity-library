/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2022  D P Bennett & Associates Limited

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

package jm.com.dpbennett.business.entity.sm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "preference")
@NamedQueries({
    @NamedQuery(name = "findAllPreferencesByName", query = "SELECT p FROM Preference p WHERE p.name = :name ORDER BY p.name"),
    @NamedQuery(name = "findAllPreferences", query = "SELECT p FROM Preference p ORDER BY p.name")
})
public class Preference implements Serializable, PreferenceInterface {

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
    @Transient
    private Boolean isDirty;

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
    public String getPreferenceValue() {
        if (preferenceValue == null) {
            preferenceValue = "";
        }
        return preferenceValue;
    }

    @Override
    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getRoles() {
        return roles;
    }

    @Override
    public void setRoles(String roles) {
        this.roles = roles;
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
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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

        try {
            String newValue = value.replaceAll("'", "''");

            List<Preference> preferences =
                    em.createQuery("SELECT p FROM Preference p where UPPER(p.preferenceValue) like '%"
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
                    + newValue.toUpperCase().trim() + "%' ORDER BY p.preferenceValue DESC", Preference.class).getResultList();
            
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
            if (!preferences.isEmpty()) {
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

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
