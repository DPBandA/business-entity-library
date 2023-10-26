/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2023  D P Bennett & Associates Limited

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

import jm.com.dpbennett.business.entity.sm.Category;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.hrm.Manufacturer;
import jm.com.dpbennett.business.entity.sm.Product;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "marketproduct")
public class MarketProduct implements BusinessEntity, Comparable, Serializable, Product {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String commonName;
    private String brand;
    private String model;
    private String type;
    private String code;
    private Boolean active;
    @Column(length = 1024)
    private String description;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
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

    public String getCommonName() {
        if (commonName == null) {

            commonName = getName();
        }
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Override
    public Manufacturer getManufacturer() {
        if (manufacturer == null) {
            manufacturer = new Manufacturer();
        }
        return manufacturer;
    }

    @Override
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public static List<MarketProduct> findAllActiveMarketProducts(EntityManager em) {

        try {
            List<MarketProduct> marketProducts;
            marketProducts = em.createQuery("SELECT m FROM MarketProduct m WHERE m.active = 1 ORDER BY m.name",
                    MarketProduct.class).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static List<MarketProduct> findAllActiveMarketProductsByType(EntityManager em,
            String type) {

        try {
            List<MarketProduct> marketProducts;
            marketProducts = em.createQuery("SELECT m FROM MarketProduct m WHERE "
                    + "m.active = 1 AND "
                    + "m.type = '" + type + "' "
                    + "ORDER BY m.name",
                    MarketProduct.class).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);

            return new ArrayList<>();
        }
    }

    public static MarketProduct findActiveMarketProduct(
            EntityManager em, String name) {

        String newName = name.replaceAll("'", "''").trim().toUpperCase();

        try {
            List<MarketProduct> products = em.createQuery("SELECT m FROM MarketProduct m "
                    + "WHERE m.active = 1 AND UPPER(m.name)" + " = '" + newName + "'",
                    MarketProduct.class).getResultList();
            if (!products.isEmpty()) {
                MarketProduct product = products.get(0);

                return product;
            }
        } catch (Exception e) {
            System.out.println("Error finding market product: " + e);
            return null;
        }

        return null;
    }

    public static MarketProduct findActiveMarketProductByType(
            EntityManager em, String name, String type) {

        String newName = name.replaceAll("'", "''").trim().toUpperCase();

        try {
            List<MarketProduct> products = em.createQuery("SELECT m FROM MarketProduct m "
                    + "WHERE m.active = 1 AND UPPER(m.name)" + " = '" + newName
                    + "' AND m.type = '" + type + "'",
                    MarketProduct.class).getResultList();
            if (!products.isEmpty()) {
                MarketProduct product = products.get(0);

                return product;
            }
        } catch (Exception e) {
            System.out.println("Error finding market product: " + e);
            return null;
        }

        return null;
    }

    public static List<MarketProduct> findActiveMarketProductsByName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<MarketProduct> marketProducts
                    = em.createQuery("SELECT m FROM MarketProduct m WHERE UPPER(m.name) like '%"
                            + value.toUpperCase() + "%'"
                            + " AND m.active = 1"
                            + " ORDER BY m.name", MarketProduct.class).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<MarketProduct> findActiveMarketProductsByNameAndType(EntityManager em,
            String name, String type) {

        try {
            String newName = name.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<MarketProduct> marketProducts
                    = em.createQuery("SELECT m FROM MarketProduct m WHERE UPPER(m.name) like '%"
                            + newName.toUpperCase() + "%'"
                            + " AND m.active = 1 AND m.type = '" + type + "'"
                            + " ORDER BY m.name", MarketProduct.class).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<MarketProduct> findMarketProductsByName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<MarketProduct> marketProducts
                    = em.createQuery("SELECT m FROM MarketProduct m WHERE UPPER(m.name) like '%"
                            + value.toUpperCase() + "%'"
                            + " ORDER BY m.name", MarketProduct.class).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<MarketProduct> findMarketProductsByNameAndType(EntityManager em,
            String name, String type) {

        try {
            name = name.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<MarketProduct> marketProducts
                    = em.createQuery("SELECT m FROM MarketProduct m WHERE UPPER(m.name) like '%"
                            + name.toUpperCase() + "%' AND m.type = '" + type + "'"
                            + " ORDER BY m.name", MarketProduct.class).getResultList();

            return marketProducts;

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public String getModel() {
        if (model == null) {
            model = "";
        }

        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getDescription() {
        if (description == null) {
            description = "";
        }

        return description;
    }

    @Override
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
        if (brand == null) {
            brand = "";
        }

        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
    public Boolean getActive() {
        if (active == null) {
            active = true;
        }

        return active;
    }

    @Override
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
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarketProduct)) {
            return false;
        }
        MarketProduct other = (MarketProduct) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        String nameString = getName();

        if (!getBrand().isEmpty()) {
            nameString = nameString + ", " + getBrand();
        }

        if (!getModel().isEmpty()) {
            nameString = nameString + ", " + getModel();
        }

        return nameString;
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
        if (name == null) {
            name = "";
        }

        return name;
    }

    public String getStoredName() {
        if (name == null) {
            name = "";
        }
        
        return name;
    }

    public void setStoredName(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        if (type == null) {
            type = "";
        }

        return type;
    }

    @Override
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

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
