/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2022  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */

public interface SystemOptionInterface extends BusinessEntity {

    @Override
    public Long getId();

    @Override
    public void setId(Long id);

    @Override
    public Boolean getIsDirty();

    @Override
    public void setIsDirty(Boolean isDirty);

    public String getComments();

    public void setComments(String comments);

    public String getCategory();

    public void setCategory(String category);

    public String getOptionValue();

    public void setOptionValue(String optionValue);

    public static Object getOptionValueObject(EntityManager em, String name) {
        SystemOptionInterface option = SystemOptionInterface.findSystemOptionByName(em, name);

        if (option != null) {
            switch (option.getOptionValueType()) {
                case "String":
                    return option.getOptionValue();
                case "Long":
                    return Long.parseLong(option.getOptionValue());
                case "Integer":
                    return Integer.parseInt(option.getOptionValue());
                case "Double":
                    return Double.parseDouble(option.getOptionValue());
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
        String itemSep = (String) SystemOptionInterface.getOptionValueObject(em,
                "defaultListItemSeparationCharacter");

        String items[] = optionValue.split(itemSep);

        list.addAll(Arrays.asList(items));

        return list;
    }

    public String getOptionValueType();

    public void setOptionValueType(String optionValueType);

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object object);

    @Override
    public String toString();

    @Override
    public String getName();

    @Override
    public void setName(String name);

    /**
     *
     * @param em
     * @param name
     * @return
     */
    public static SystemOptionInterface findSystemOptionByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''").trim();

            List<SystemOptionInterface> options = em.createQuery("SELECT o FROM SystemOption o "
                    + "WHERE UPPER(o.name) "
                    + "LIKE '" + newName.toUpperCase() + "%'", SystemOptionInterface.class).getResultList();

            if (!options.isEmpty()) {
                // Make sure this is the current option stored in the database
                SystemOptionInterface option = options.get(0);
                em.refresh(option);

                return option;
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<SystemOptionInterface> findAllSystemOptions(EntityManager em) {

        try {
            List<SystemOptionInterface> systemOption = em.createNamedQuery("findAllSystemOptions", SystemOptionInterface.class).getResultList();

            return systemOption;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<SystemOptionInterface> findAllFinancialSystemOptions(EntityManager em) {

        try {
            List<SystemOptionInterface> systemOption = em.createNamedQuery("findAllFinancialSystemOptions", SystemOptionInterface.class).getResultList();

            return systemOption;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<SystemOptionInterface> findSystemOptions(EntityManager em, String queryString) {

        try {
            String newQueryString = queryString.toUpperCase().trim().replaceAll("'", "''");

            List<SystemOptionInterface> systemOptions
                    = em.createQuery("SELECT o FROM SystemOption o WHERE "
                            + "UPPER(o.name) LIKE '%" + newQueryString + "%'"
                            + " OR UPPER(o.optionValue) like '%" + newQueryString + "%'"
                            + " OR UPPER(o.comments) like '%" + newQueryString + "%'"
                            + " OR UPPER(o.category) LIKE '%" + newQueryString + "%'"
                            + " ORDER BY o.comments", SystemOptionInterface.class).getResultList();
            return systemOptions;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<SystemOptionInterface> findFinancialSystemOptions(EntityManager em, String queryString) {

        try {
            String newQueryString = queryString.toUpperCase().trim().replaceAll("'", "''");

            List<SystemOptionInterface> systemOptions
                    = em.createQuery("SELECT o FROM SystemOption o WHERE UPPER(o.category) = 'FINANCE' AND ("
                            + " UPPER(o.name) LIKE '%" + newQueryString + "%'"
                            + " OR UPPER(o.optionValue) like '%" + newQueryString + "%'"
                            + " OR UPPER(o.comments) like '%" + newQueryString + "%'"
                            + " ) ORDER BY o.comments", SystemOptionInterface.class).getResultList();
            return systemOptions;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public ReturnMessage save(EntityManager em);

    @Override
    public ReturnMessage validate(EntityManager em);

}
