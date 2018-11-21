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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "systemoptions")
@NamedQueries({
    @NamedQuery(name = "findAllSystemOptions", query = "SELECT s FROM SystemOption s ORDER BY s.name")
    ,
    @NamedQuery(name = "findAllFinancialSystemOptions", 
            query = "SELECT s FROM SystemOption s WHERE s.category LIKE '%FINANCE%' OR s.category LIKE '%Finance%' ORDER BY s.name")
})
public class SystemOption implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(length = 1024)
    private String optionValue;
    private String optionValueType;
    private String category;
    @Column(length = 1024)
    private String comments;
    @Transient
    private Boolean isDirty;

    public SystemOption() {
        name = "";
        optionValue = "";
        optionValueType = "";
        category = "";
        comments = "";
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOptionValue() {
        if (optionValue == null) {
            optionValue = "";
        }
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public static Object getOptionValueObject(EntityManager em, String name) {
        SystemOption option = SystemOption.findSystemOptionByName(em, name);

        if (option != null) {
            switch (option.getOptionValueType()) {
                case "String":
                    return option.getOptionValue();
                case "Long":
                    return Long.parseLong(option.getOptionValue());    
                case "Integer":
                    return Integer.parseInt(option.getOptionValue());
                case "Boolean":
                    return Boolean.parseBoolean(option.getOptionValue());
                case "List<String>":
                    return getOptionValueListObject(em, option.getOptionValue());
                default:
                    return option.getOptionValue();
            }

        } else {
            return "";
        }
    }

    public static List<String> getOptionValueListObject(EntityManager em,
            String optionValue) {
        ArrayList list = new ArrayList();
        String itemSep = (String) SystemOption.getOptionValueObject(em,
                "defaultListItemSeparationCharacter");

        String items[] = optionValue.split(itemSep);

        list.addAll(Arrays.asList(items));

        return list;
    }

    public String getOptionValueType() {
        if (optionValueType == null) {
            optionValueType = "";
        }
        return optionValueType;
    }

    public void setOptionValueType(String optionValueType) {
        this.optionValueType = optionValueType;
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
        if (!(object instanceof SystemOption)) {
            return false;
        }
        SystemOption other = (SystemOption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getOptionValue();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param em
     * @param name
     * @return
     */
    private static SystemOption findSystemOptionByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''").trim();

            List<SystemOption> options = em.createQuery("SELECT o FROM SystemOption o "
                    + "WHERE UPPER(o.name) "
                    + "LIKE '" + newName.toUpperCase() + "%'", SystemOption.class).getResultList();

            if (options.size() > 0) {
                // Make sure this is the current option stored in the database
                SystemOption option = options.get(0);
                em.refresh(option);

                return option;
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<SystemOption> findAllSystemOptions(EntityManager em) {

        try {
            List<SystemOption> systemOption = em.createNamedQuery("findAllSystemOptions", SystemOption.class).getResultList();

            return systemOption;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<SystemOption> findAllFinancialSystemOptions(EntityManager em) {

        try {
            List<SystemOption> systemOption = em.createNamedQuery("findAllFinancialSystemOptions", SystemOption.class).getResultList();

            return systemOption;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<SystemOption> findSystemOptions(EntityManager em, String queryString) {

        try {
            String newQueryString = queryString.toUpperCase().trim().replaceAll("'", "''");

            List<SystemOption> systemOptions
                    = em.createQuery("SELECT o FROM SystemOption o WHERE "
                            + "UPPER(o.name) LIKE '%" + newQueryString + "%'"
                            + " OR UPPER(o.optionValue) like '%" + newQueryString + "%'"
                            + " OR UPPER(o.comments) like '%" + newQueryString + "%'"
                            + " OR UPPER(o.category) LIKE '%" + newQueryString + "%'"
                            + " ORDER BY o.comments", SystemOption.class).getResultList();
            return systemOptions;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<SystemOption> findFinancialSystemOptions(EntityManager em, String queryString) {

        try {
            String newQueryString = queryString.toUpperCase().trim().replaceAll("'", "''");

            List<SystemOption> systemOptions
                    = em.createQuery("SELECT o FROM SystemOption o WHERE o.category = 'FINANCE' AND ("
                            + " UPPER(o.name) LIKE '%" + newQueryString + "%'"
                            + " OR UPPER(o.optionValue) like '%" + newQueryString + "%'"
                            + " OR UPPER(o.comments) like '%" + newQueryString + "%'"
                            + " ) ORDER BY o.comments", SystemOption.class).getResultList();
            return systemOptions;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Double getGCTPercentage(EntityManager em) {
        Double percentGCT;

        // Get percentage GCT for use by job costing and payment
        try {
            percentGCT = Double.parseDouble(SystemOption.findSystemOptionByName(em, "GCT").getOptionValue());
        } catch (NumberFormatException e) {
            percentGCT = 0.0;
            System.out.println("Exception occurred while getting GCT percentage: " + e);
        }

        return percentGCT;
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

        return new ReturnMessage(false, "System option not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
