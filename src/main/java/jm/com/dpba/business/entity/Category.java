/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Long id = null;
    private String name = "";
    private String type = "";

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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static List<Category> findCategoriesByType(EntityManager em, String type) {

        try {
            String newType = type.trim().replaceAll("'", "''");

            return em.createQuery("SELECT c FROM Category c "
                    + "WHERE UPPER(c.type) "
                    + "= '" + newType.toUpperCase() + "' ORDER BY c.name", Category.class).getResultList();

            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.Category[id=" + id + "]";
    }
}
