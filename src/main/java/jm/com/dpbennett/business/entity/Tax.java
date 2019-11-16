/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

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
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "tax")
@NamedQueries({
    @NamedQuery(name = "findAllTaxes", query = "SELECT t FROM Tax t ORDER BY t.name")
})
public class Tax implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean active;
    private Boolean exempt;
    private String name;
    private Double taxValue;
    private String taxValueType;    
    @OneToOne(cascade = CascadeType.REFRESH)
    private AccountingCode accountingCode;
    private String type;
    @Column(length = 1024)
    private String description;
    private String category;
    @Transient
    private Boolean isDirty;

    public Tax() {
        active = true;
        exempt = false;
        name = "";
        taxValue = 0.0;
        taxValueType = "Percentage";
        type = "";
        description = "";
        category = "";
    }

    public Tax(String name) {
        active = true;
        exempt = false;
        this.name = name;
        taxValue = 0.0;
        taxValueType = "Percentage";
        type = "";
        description = "";
        category = "";
    }

    public Boolean getExempt() {
        return exempt;
    }

    public void setExempt(Boolean exempt) {
        this.exempt = exempt;
    }

    public Double getValue() {
        if (taxValueType.equals("Percentage")) {
            return taxValue / 100.0;
        }

        return taxValue;
    }

    public static Tax findDefault(EntityManager em, String name) {
        Tax tax = Tax.findByName(em, name);

        if (tax == null) {
            tax = new Tax(name);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, tax);
            em.getTransaction().commit();
        }

        return tax;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    public String getTaxValueType() {
        return taxValueType;
    }

    public void setTaxValueType(String taxValueType) {
        this.taxValueType = taxValueType;
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

    public static List<Tax> findAllTaxes(EntityManager em) {

        try {
            List<Tax> taxes = em.createNamedQuery("findAllTaxes", Tax.class).getResultList();

            return taxes;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Tax> findTaxesByNameAndDescription(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Tax> taxes
                    = em.createQuery("SELECT t FROM Tax t WHERE UPPER(t.name) LIKE '%"
                            + value.toUpperCase().trim() + "%' OR UPPER(t.description) LIKE '%"
                            + value.toUpperCase().trim() + "%' ORDER BY t.name",
                            Tax.class).getResultList();
            return taxes;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Tax> findActiveTaxesByNameAndDescription(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Tax> taxes
                    = em.createQuery("SELECT t FROM Tax t WHERE (UPPER(t.name) LIKE '%"
                            + value.toUpperCase().trim() + "%' OR UPPER(t.description) LIKE '%"
                            + value.toUpperCase().trim() + "%') AND t.active = 1 ORDER BY t.name",
                            Tax.class).getResultList();
            return taxes;
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
        if (!(object instanceof Tax)) {
            return false;
        }
        Tax other = (Tax) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

        return new ReturnMessage(false, "Tax not saved");
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

    public static Tax findByName(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Tax> taxes = em.createQuery("SELECT t FROM Tax t "
                    + "WHERE UPPER(t.name) "
                    + "= '" + value.toUpperCase() + "'", Tax.class).getResultList();
            if (taxes.size() > 0) {
                return taxes.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
     public static Tax findByValue(EntityManager em, Double value) {

        try {
            
            List<Tax> taxes = em.createQuery("SELECT t FROM Tax t "
                    + "WHERE t.taxValue = " + value + " AND t.exempt = 0", Tax.class).getResultList();
            if (taxes.size() > 0) {
                return taxes.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
