/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "preference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preference.findAll", query = "SELECT p FROM Preference p"),
    @NamedQuery(name = "Preference.findById", query = "SELECT p FROM Preference p WHERE p.id = :id"),
    @NamedQuery(name = "Preference.findByCategory", query = "SELECT p FROM Preference p WHERE p.category = :category"),
    @NamedQuery(name = "Preference.findByDescription", query = "SELECT p FROM Preference p WHERE p.description = :description"),
    @NamedQuery(name = "Preference.findByName", query = "SELECT p FROM Preference p WHERE p.name = :name"),
    @NamedQuery(name = "Preference.findByPreferencevalue", query = "SELECT p FROM Preference p WHERE p.preferencevalue = :preferencevalue"),
    @NamedQuery(name = "Preference.findByRoles", query = "SELECT p FROM Preference p WHERE p.roles = :roles"),
    @NamedQuery(name = "Preference.findByType", query = "SELECT p FROM Preference p WHERE p.type = :type")})
public class Preference implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "PREFERENCEVALUE")
    private String preferencevalue;
    @Size(max = 255)
    @Column(name = "ROLES")
    private String roles;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;

    public Preference() {
    }

    public Preference(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferencevalue() {
        return preferencevalue;
    }

    public void setPreferencevalue(String preferencevalue) {
        this.preferencevalue = preferencevalue;
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
        return "jm.com.dpba.business.entity.utils.Preference[ id=" + id + " ]";
    }
    
}
