/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
import java.util.Date;
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
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "tax")
@NamedQueries({
    @NamedQuery(name = "findAllTaxes", query = "SELECT t FROM Tax t ORDER BY t.name"),
    @NamedQuery(name = "findAllActiveTaxes", query = "SELECT t FROM Tax t WHERE t.active = 1 ORDER BY t.name")
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

    @Override
    public Boolean getActive() {
        return active;
    }

    @Override
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

    @Override
    public String getCategory() {
        return category;
    }

    @Override
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

    public static List<Tax> findAllActiveTaxes(EntityManager em) {

        try {
            List<Tax> taxes = em.createNamedQuery("findAllActiveTaxes", Tax.class).getResultList();

            return taxes;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Tax> findTaxesByNameAndDescription(EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

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

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Tax)) {
            return false;
        }
        Tax other = (Tax) object;

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
            System.out.println("Tax save exception: " + e);
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
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Tax> taxes = em.createQuery("SELECT t FROM Tax t "
                    + "WHERE UPPER(t.name) "
                    + "= '" + value.toUpperCase() + "'", Tax.class).getResultList();
            if (!taxes.isEmpty()) {
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
            if (!taxes.isEmpty()) {
                return taxes.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
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
