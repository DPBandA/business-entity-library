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
package jm.com.dpbennett.business.entity.fm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "classification")
@NamedQueries({
    @NamedQuery(name = "findAllClassifications", query = "SELECT c FROM Classification c ORDER BY c.name")
    ,
    @NamedQuery(name = "findAllActiveClassifications", query = "SELECT c FROM Classification c WHERE c.active = 1 ORDER BY c.name")
})
@XmlRootElement
public class Classification implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Boolean active;
    @Column(length = 1024)
    private String description;
    private Boolean isEarning;
    private Boolean isTaxable;
    private String category;
    @Transient
    private Boolean isDirty;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Tax defaultTax;

    public Classification() {
        this.name = "";
        this.active = true;
        this.description = "";
        this.isEarning = true;
        this.category = "";
    }

    public Tax getDefaultTax() {
        return (defaultTax == null ? new Tax() : defaultTax);
    }

    public void setDefaultTax(Tax defaultTax) {
        this.defaultTax = defaultTax;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsTaxable() {
        if (isTaxable == null) {
            isTaxable = getIsEarning();
        }
        return isTaxable;
    }

    public void setIsTaxable(Boolean isTaxable) {
        this.isTaxable = isTaxable;
    }

    public String getCategory() {
        if (category == null) {
            category = "";
        }
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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

    public static Classification findClassificationByName(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Classification> classifications = em.createQuery("SELECT c FROM Classification c "
                    + "WHERE UPPER(c.name) "
                    + "= '" + value.toUpperCase() + "'", Classification.class).getResultList();
            if (classifications.size() > 0) {
                return classifications.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static List<Classification> findClassificationsByName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Classification> classifications
                    = em.createQuery("SELECT c FROM Classification c where UPPER(c.name) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY c.name", Classification.class).getResultList();
            return classifications;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Classification> findClassificationsByNameAndCategory(EntityManager em, String value, String category) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Classification> classifications
                    = em.createQuery("SELECT c FROM Classification c where UPPER(c.name) like '%"
                            + value.toUpperCase().trim() + "%' AND c.category = " + category + " ORDER BY c.name", Classification.class).getResultList();
            return classifications;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Classification> findActiveClassificationsByName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Classification> classifications
                    = em.createQuery("SELECT c FROM Classification c where UPPER(c.name) like '%"
                            + value.toUpperCase().trim() + "%' AND c.active = 1 ORDER BY c.name", Classification.class).getResultList();
            return classifications;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Classification> findActiveClassificationsByNameAndCategory(EntityManager em, String value, String category) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Classification> classifications
                    = em.createQuery("SELECT c FROM Classification c where UPPER(c.name) like '"
                            + value.toUpperCase().trim() + "%' AND c.active = 1 AND c.category = '" + category + "' ORDER BY c.name", Classification.class).getResultList();
            return classifications;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
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

        return new ReturnMessage(false, "Classification not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
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
}
