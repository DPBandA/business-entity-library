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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "accountingcode")
@NamedQueries({
    @NamedQuery(name = "findAllAccountingCodes",
            query = "SELECT a FROM AccountingCode a ORDER BY a.name")
})
public class AccountingCode implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean active;
    private String name;
    private String code;
    private String account;
    private String type;
    @Column(length = 1024)
    private String description;
    private String abbreviation;
    @Transient
    private Boolean isDirty;

    public AccountingCode() {
        active = true;
        name = "";
        code = "";
        account = "";
        type = "";
        description = "";
        abbreviation = "";
    }

    public AccountingCode(String name) {
        active = true;
        this.name = name;
        code = "";
        account = "";
        type = "";
        description = "";
        abbreviation = "";
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static List<AccountingCode> findAllAccountingCodes(EntityManager em) {

        try {
            List<AccountingCode> codes = em.createNamedQuery("findAllAccountingCodes", AccountingCode.class).getResultList();

            return codes;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<AccountingCode> findAccountingCodes(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<AccountingCode> accountingCodes
                    = em.createQuery(
                            "SELECT a FROM AccountingCode a WHERE UPPER(a.name) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.description) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.code) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.account) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.type) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.abbreviation) LIKE '%" + value.toUpperCase().trim()
                            + "%' ORDER BY a.name",
                            AccountingCode.class).getResultList();
            return accountingCodes;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<AccountingCode> findActiveAccountingCodes(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<AccountingCode> accountingCodes
                    = em.createQuery("SELECT a FROM AccountingCode a WHERE (UPPER(a.name) LIKE '%" + value.toUpperCase().trim() 
                            + "%' OR UPPER(a.description) LIKE '%" + value.toUpperCase().trim() 
                            + "%' OR UPPER(a.code) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.account) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.type) LIKE '%" + value.toUpperCase().trim()
                            + "%' OR UPPER(a.abbreviation) LIKE '%" + value.toUpperCase().trim()
                            + "%') AND (a.active = 1 OR a.active IS NULL) ORDER BY a.name",
                            AccountingCode.class).getResultList();
            return accountingCodes;
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
        if (!(object instanceof AccountingCode)) {
            return false;
        }
        AccountingCode other = (AccountingCode) object;
        
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

        return new ReturnMessage(false, "AccountingCode not saved");
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

    public static AccountingCode findByName(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<AccountingCode> accountingCodes = em.createQuery("SELECT a FROM AccountingCode a "
                    + "WHERE UPPER(a.name) "
                    + "= '" + value.toUpperCase() + "'", AccountingCode.class).getResultList();
          
            if (!accountingCodes.isEmpty()) {
                return accountingCodes.get(0);
            }
            
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static AccountingCode findByCode(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<AccountingCode> accountingCodes = em.createQuery("SELECT a FROM AccountingCode a "
                    + "WHERE UPPER(a.code) "
                    + "= '" + value.toUpperCase() + "'", AccountingCode.class).getResultList();
            if (!accountingCodes.isEmpty()) {
                return accountingCodes.get(0);
            }
            
            return null;
        } catch (Exception e) {
            System.out.println(e);
            
            return null;
        }
    }

    public static AccountingCode findActiveByCode(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<AccountingCode> accountingCodes = em.createQuery("SELECT a FROM AccountingCode a "
                    + "WHERE UPPER(a.code) "
                    + "= '" + value.toUpperCase() + "' AND (a.active = 1 OR a.active IS NULL)", AccountingCode.class).getResultList();
            if (!accountingCodes.isEmpty()) {
                return accountingCodes.get(0);
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
}
