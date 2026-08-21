/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jm.com.dpbennett.business.entity.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "financialaccount")
@NamedQueries({
    @NamedQuery(name = "findAllFields",
            query = "SELECT f FROM Field f ORDER BY f.name")
})
public class FinancialAccount1 implements
        Serializable,
        Account,
        Comparable<FinancialAccount1> {

    private static final long serialVersionUID = 1L;
    private static final System.Logger LOG = System.getLogger(FinancialAccount1.class.getName());
    public static List<FinancialAccount1> findAllFields(EntityManager em) {
        
        try {
            
            List<FinancialAccount1> codes = em.createNamedQuery("findAllFields", FinancialAccount1.class).getResultList();
            
            return codes;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static List<FinancialAccount1> findFields(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<FinancialAccount1> fields
                    = em.createQuery("SELECT a FROM Field a WHERE UPPER(a.name) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.description) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.code) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.account) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.type) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.abbreviation) LIKE '%" + value.toUpperCase().trim()
                            + "%' ORDER BY a.name",
                            FinancialAccount1.class).getResultList();
            return fields;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public static List<FinancialAccount1> findActiveFields(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<FinancialAccount1> fields
                    = em.createQuery("SELECT a FROM Field a WHERE (UPPER(a.name) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.description) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.code) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.account) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.type) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.abbreviation) LIKE '%" + value.toUpperCase().trim()
                            + "%') AND (a.active = 1 OR a.active IS NULL) ORDER BY a.name",
                            FinancialAccount1.class).getResultList();
            return fields;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public static FinancialAccount1 findByName(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<FinancialAccount1> fields = em.createQuery("SELECT a FROM Field a "
                    + "WHERE UPPER(a.name) "
                    + "= '" + value.toUpperCase() + "'", FinancialAccount1.class).getResultList();
            if (!fields.isEmpty()) {
                return fields.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static FinancialAccount1 findByCode(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<FinancialAccount1> fields = em.createQuery("SELECT a FROM FieldField a "
                    + "WHERE UPPER(a.code) "
                    + "= '" + value.toUpperCase() + "'", FinancialAccount1.class).getResultList();
            if (!fields.isEmpty()) {
                return fields.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static FinancialAccount1 findActiveByCode(EntityManager em, String value) {
        
        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<FinancialAccount1> fields = em.createQuery("SELECT a FROM Field a "
                    + "WHERE UPPER(a.code) "
                    + "= '" + value.toUpperCase() + "' AND (a.active = 1 OR a.active IS NULL)", FinancialAccount1.class).getResultList();
            if (!fields.isEmpty()) {
                return fields.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean active;
    private String name;
    private String type;
    @OneToOne(cascade = CascadeType.REFRESH)
    private FinancialAccount1 parent;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FinancialAccount1> children;
    @OneToOne(cascade = CascadeType.REFRESH)
    private AccountingCode code;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Currency currency;
    @Column(length = 1024)
    private String description;
    private String category;
    private Boolean hidden;
    private Boolean placeholder;
    private Double total;
    @Transient
    private Boolean isDirty;

    public FinancialAccount1() {
        active = true;
        name = "";
        description = "";
        category = "";
        code = new AccountingCode();
        total = 0.0;
    }

    public FinancialAccount1(String name) {
        active = true;
        this.name = name;
        type = "";
        description = "";
        category = "";
        code = new AccountingCode();
        total = 0.0;
    }

    public FinancialAccount1(String name, String description, Double total) {
        active = true;
        this.name = name;
        type = "";
        this.description = description;
        category = "";
        code = new AccountingCode();
        this.total = total;
    }

    public List<FinancialAccount1> getChildren() {

        if (children == null) {
            children = new ArrayList<>();
        }

        return children;
    }

    public void setChildren(List<FinancialAccount1> children) {
        this.children = children;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getHidden() {
        if (hidden == null) {
            hidden = false;
        }
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getPlaceholder() {
        if (placeholder == null) {
            placeholder = false;
        }
        return placeholder;
    }

    public void setPlaceholder(Boolean placeholder) {
        this.placeholder = placeholder;
    }

    public Currency getCurrency() {

        if (currency == null) {
            return new Currency();
        }

        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public FinancialAccount1 getParent() {

        if (parent == null) {
            return new FinancialAccount1();
        }

        return parent;
    }

    public void setParent(FinancialAccount1 parent) {
        this.parent = parent;
    }

    public AccountingCode getCode() {
        if (code == null) {
            return new AccountingCode();
        }

        return code;
    }

    public void setCode(AccountingCode code) {
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

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
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
        if (!(object instanceof FinancialAccount1)) {
            return false;
        }
        FinancialAccount1 other = (FinancialAccount1) object;

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
            
            for (FinancialAccount1 financialAccount : getChildren()) {
                financialAccount.save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Field not saved");
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


    @Override
    public int compareTo(FinancialAccount1 fa) {
        return this.getName().compareTo(fa.getName());
    }

    @Override
    public LocalDateTime getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(LocalDateTime dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(LocalDateTime dateEdited) {
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

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SystemOption> getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SystemOption getSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
