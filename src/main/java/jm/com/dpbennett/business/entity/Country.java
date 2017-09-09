/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

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
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "country")
@NamedQueries({   
    @NamedQuery(name = "findAllCountries", query = "SELECT c FROM Country c ORDER BY c.name")   
})
@XmlRootElement
public class Country implements Serializable, BusinessEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String twoDigitCode;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        return "jm.com.dpbennett.entity.Country[ id=" + id + " ]";
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

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
