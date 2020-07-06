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
package jm.com.dpbennett.business.entity.sm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "category")
public class Category implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @Transient
    private Boolean isDirty;

    public Category() {
        this.name = "";
        this.type = "";
        this.isDirty = false;
    }

    public Category(String name) {
        this.name = name;
        this.type = "";
        this.isDirty = false;
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

    public String getType() {
        if (type == null) {
            type = "";
        }
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

        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));

    }

    public static List<Category> findAllCategories(EntityManager em) {

        try {
            List<Category> categories = em.createQuery("SELECT c FROM Category c ORDER BY c.name", Category.class).getResultList();

            return categories;

        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }
    
    public static List<Category> findCategoriesByName(EntityManager em, String name) {

        try {
            List<Category> categories
                    = em.createQuery("SELECT c FROM Category c where UPPER(c.name) like '%"
                            + name.toUpperCase().trim() + "%' ORDER BY c.name", Category.class).getResultList();
            
            return categories;
            
        } catch (Exception e) {
            
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Category> findCategoriesByType(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            return em.createQuery("SELECT c FROM Category c "
                    + "WHERE UPPER(c.type) "
                    + "= '" + value.toUpperCase() + "' ORDER BY c.name", Category.class).getResultList();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String toString() {
        return name;
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

        return new ReturnMessage(false, "Category not saved");
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
