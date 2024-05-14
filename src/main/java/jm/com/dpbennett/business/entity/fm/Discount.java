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
@Table(name = "discount")
@NamedQueries({
    @NamedQuery(name = "findAllDiscounts", query = "SELECT d FROM Discount d ORDER BY d.name")
})
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

    @Override
    public Boolean getActive() {
        return active;
    }

    @Override
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
            
            value = value.replaceAll("'", "`");

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

            value = value.replaceAll("'", "`");

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
          
            List<Discount> discounts = em.createQuery("SELECT d FROM Discount d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + value.toUpperCase() + "'", Discount.class).getResultList();
            if (!discounts.isEmpty()) {
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
            if (!discounts.isEmpty()) {
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
            
            valueType = valueType.replaceAll("'", "`");

            List<Discount> discounts = em.createQuery("SELECT d FROM Discount d"
                    + " WHERE d.discountValue = " + value
                    + " AND d.discountValueType LIKE '" + valueType + "'", Discount.class).getResultList();
            if (!discounts.isEmpty()) {
                return discounts.get(0);
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
}
