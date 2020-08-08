/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2020  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sc;

import jm.com.dpbennett.business.entity.sm.Category;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "marketproduct")
public class MarketProduct implements BusinessEntity, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String brand;
    private String model;
    private String type;
    private String code;
    private Boolean active;
    @Column(length = 1024)
    private String description;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Category> categories;
    @Transient
    private Boolean isDirty;

    public MarketProduct() {
        this.active = true;
        this.categories = new ArrayList<>();
    }

    public MarketProduct(String name) {
        this.active = true;
        this.name = name;
        this.categories = new ArrayList<>();
    }
    
     public static List<MarketProduct> findAllActiveMarketProducts(EntityManager em) {

        try {
            List<MarketProduct> marketProducts = 
                    em.createQuery("SELECT m FROM MarketProduct m WHERE m.active = 1 ORDER BY m.name", 
                            MarketProduct.class).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }
     
     public static MarketProduct findActiveMarketProductByName(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<MarketProduct> marketProducts = em.createQuery("SELECT m FROM MarketProduct m "
                    + "WHERE m.active = 1 AND UPPER(m.name) "
                    + "= '" + value.toUpperCase() + "'", MarketProduct.class).getResultList();
            
            if (marketProducts.size() > 0) {
                return marketProducts.get(0);
            }
            
            return null;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<MarketProduct> findActiveMarketProductsByAnyPartOfNameOrDescription(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<MarketProduct> marketProducts
                    = em.createQuery("SELECT m FROM MarketProduct m WHERE (m.name like '%"
                            + value + "%'"
                            + " OR m.description like '%"
                            + value + "%')"
                            + " AND m.active = 1"
                            + " ORDER BY m.name", MarketProduct.class).setMaxResults(500).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<MarketProduct> findMarketProductsByAnyPartOfNameOrDescription(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<MarketProduct> marketProducts
                    = em.createQuery("SELECT m FROM MarketProduct m WHERE m.name like '%"
                            + value + "%'"
                            + " OR m.description like '%"
                            + value + "%'"
                            + " ORDER BY m.name", MarketProduct.class).setMaxResults(500).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
        if (!(object instanceof MarketProduct)) {
            return false;
        }
        MarketProduct other = (MarketProduct) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Object o) {
        if ((((MarketProduct) o).id != null) && (this.id != null)) {
            return Collator.getInstance().compare(((MarketProduct) o).id.toString(),
                    this.id.toString());
        } else {
            return 0;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        return new ReturnMessage(false, "Market product not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    public String getIsActive() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setIsActive(String active) {
        if (active.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
    }
}
