/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.fm;

import jm.com.dpbennett.business.entity.fm.AccountingCode;
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
 * @author Desmond Bennett
 */
@Entity
@Table(name = "discount")
@NamedQueries({
    @NamedQuery(name = "findAllDiscounts", query = "SELECT d FROM Discount d ORDER BY d.name")
})
@XmlRootElement
public class Discount implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean active;
    private String name;
    private Double discountValue;
    private String discountValueType;
    @OneToOne(cascade = CascadeType.REFRESH)
    private AccountingCode accountingCode;
    private String type;
    @Column(length = 1024)
    private String description;
    private String category;
    @Transient
    private Boolean isDirty;

    public Discount() {
        active = true;
        name = "";
        discountValue = 0.0;
        discountValueType = "Percentage";
        type = "";
        description = "";
        category = "";
    }

    public Discount(String name) {
        active = true;
        this.name = name;
        discountValue = 0.0;
        discountValueType = "Percentage";
        type = "";
        description = "";
        category = "";
    }
    
    public Discount(String name, Double discountValue, String discountValueType) {
        active = true;
        this.name = name;
        this.discountValue = discountValue;
        this.discountValueType = discountValueType;
        type = "";
        description = "";
        category = "";
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public static Discount findDefault(EntityManager em, String name) {
        Discount discount = Discount.findByName(em, name);

        if (discount == null) {
            discount = new Discount(name);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, discount);
            em.getTransaction().commit();
        }

        return discount;
    }
    
    public static Discount findDefault(EntityManager em, 
            String name, Double value, String type) {
        Discount discount = Discount.findByName(em, name);

        if (discount == null) {
            discount = new Discount(name, value, type);
            discount.setActive(false);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, discount);
            em.getTransaction().commit();
        }

        return discount;
    }

    public Double getValue() {
        if (discountValueType.equals("Percentage")) {
            return discountValue / 100.0;
        }

        return discountValue;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountValueType() {
        return discountValueType;
    }

    public void setDiscountValueType(String discountValueType) {
        this.discountValueType = discountValueType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AccountingCode getAccountingCode() {
        return accountingCode;
    }

    public void setAccountingCode(AccountingCode accountingCode) {
        this.accountingCode = accountingCode;
    }

    public static List<Discount> findAllDiscounts(EntityManager em) {

        try {
            List<Discount> discounts = em.createNamedQuery("findAllDiscounts", Discount.class).getResultList();

            return discounts;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Discount> findDiscountsByNameAndDescription(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Discount> discounts
                    = em.createQuery("SELECT d FROM Discount d WHERE UPPER(d.name) LIKE '%"
                            + value.toUpperCase().trim() + "%' OR UPPER(d.description) LIKE '%"
                            + value.toUpperCase().trim() + "%' ORDER BY d.name",
                            Discount.class).getResultList();
            return discounts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Discount> findActiveDiscountsByNameAndDescription(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Discount> discounts
                    = em.createQuery("SELECT d FROM Discount d WHERE (UPPER(d.name) LIKE '%"
                            + value.toUpperCase().trim() + "%' OR UPPER(d.description) LIKE '%"
                            + value.toUpperCase().trim() + "%') AND d.active = 1 ORDER BY d.name",
                            Discount.class).getResultList();
            return discounts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Discount)) {
            return false;
        }
        Discount other = (Discount) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

        return new ReturnMessage(false, "Discount not saved");
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

    public static Discount findByName(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Discount> discounts = em.createQuery("SELECT d FROM Discount d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + value.toUpperCase() + "'", Discount.class).getResultList();
            if (discounts.size() > 0) {
                return discounts.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Discount findByValue(EntityManager em, Double value) {

        try {
           
            List<Discount> discounts = em.createQuery("SELECT d FROM Discount d"
                    + " WHERE d.discountValue = " + value, Discount.class).getResultList();
            if (discounts.size() > 0) {
                return discounts.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Discount findByValueAndType(EntityManager em, 
            Double value, 
            String valueType) {

        try {
           
            List<Discount> discounts = em.createQuery("SELECT d FROM Discount d"
                    + " WHERE d.discountValue = " + value + 
                    " AND d.discountValueType LIKE '" + valueType + "'", Discount.class).getResultList();
            if (discounts.size() > 0) {
                return discounts.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
