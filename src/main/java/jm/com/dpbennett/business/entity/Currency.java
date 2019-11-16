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

package jm.com.dpbennett.business.entity;

import jm.com.dpbennett.business.entity.fm.Asset;
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
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;
import java.text.Collator;

/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "currency")
public class Currency implements Asset, BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;  
    private String type;
    private String symbol;
    private String description;
    @Transient
    private Boolean isDirty;

    public Currency() {
        this.name = "";
        this.code = "";
        this.symbol = "";
        this.description = "";
        this.isDirty = false;
    }      
    
    

    public Currency(String name) {
        this.name = name;
        this.code = "";
        this.symbol = "";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Currency[id=" + id + "]";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public static Currency findByName(EntityManager em, String value) {

        try {
            value = value.trim().replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Currency> currencies = em.createQuery("SELECT c FROM Currency c "
                    + "WHERE UPPER(c.name) "
                    + "= '" + value.toUpperCase() + "'", Currency.class).getResultList();
            if (currencies.size() > 0) {
                return currencies.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
    
    public static Currency findByCode(EntityManager em, String code) {

        String newCode = code.replaceAll("'", "''");

        try {
            List<Currency> currencies = em.createQuery("SELECT c FROM Currency c "
                    + "WHERE c.code "
                    + "= '" + newCode + "'", Currency.class).getResultList();
            if (currencies.size() > 0) {
                return currencies.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
    
    public static List<Currency> findAll(EntityManager em) {

        try {
            List<Currency> codes = em.createQuery("SELECT c FROM Currency c ORDER BY c.code", Currency.class).getResultList();

            return codes;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<Currency> findAllByName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Currency> currencies
                    = em.createQuery("SELECT c FROM Currency c WHERE UPPER(c.name) LIKE '%"                            
                            + value.toUpperCase().trim() + "%' ORDER BY c.name",
                            Currency.class).getResultList();
            return currencies;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Currency findById(EntityManager em, Long id) {

        try {
            Currency code = em.find(Currency.class, id);

            return code;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
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

        return new ReturnMessage(false, "Currency not saved");
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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.getName(), ((Client) o).getName());
    }

}
