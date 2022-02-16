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

package jm.com.dpbennett.business.entity.sm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "country")
@NamedQueries({   
    @NamedQuery(name = "findAllCountries", query = "SELECT c FROM Country c ORDER BY c.name")   
})
public class Country implements Serializable, BusinessEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String twoDigitCode;
    @Transient
    private Boolean isDirty;

    public Country() {
        name = "";
    }

    public Country(String name) {
        this.name = name;
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
    public String getTwoDigitCode() {
        return twoDigitCode;
    }

    public void setTwoDigitCode(String twoDigitCode) {
        this.twoDigitCode = twoDigitCode;
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
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public static List<Country> findAllCountries(EntityManager em) {

        try {
            List<Country> countries = em.createNamedQuery("findAllCountries", Country.class).getResultList();
            return countries;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<Country> findCountriesByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Country> countries =
                    em.createQuery("SELECT c FROM Country c where UPPER(c.name) like '%"
                    + newName.toUpperCase().trim() + "%' ORDER BY c.name", Country.class).getResultList();
            return countries;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static Country findCountryByName(EntityManager em, String countryName) {

        try {
            String newCountryName = countryName.trim().replaceAll("'", "''");

            List<Country> countries = em.createQuery("SELECT c FROM Sector c "
                    + "WHERE UPPER(c.name) "
                    + "= '" + newCountryName.toUpperCase() + "'", Country.class).getResultList();
            if (countries.size() > 0) {
                return countries.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
