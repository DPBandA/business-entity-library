/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
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
import jm.com.dpba.business.entity.utils.BusinessEntityUtils;


/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "distributor")
@NamedQueries({
    @NamedQuery(name = "findAllDistributors", query = "SELECT e FROM Distributor e ORDER BY e.name")
})
@XmlRootElement
public class Distributor implements Serializable, BusinessEntity {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String street;
    private String pO;
    private String city;
    private String country;
    private String phone;
    private String fax;
    private String email;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Distributor() {
    }

    public Distributor(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getpO() {
        return pO;
    }

    public void setpO(String pO) {
        this.pO = pO;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the Id fields are not set
        if (!(object instanceof Distributor)) {
            return false;
        }
        Distributor other = (Distributor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Distributor> findDistributorsBySearchPattern(EntityManager em, String searchPattern) {

        try {
            List<Distributor> distributors = em.createQuery("SELECT d FROM Distributor d "
                    + "WHERE UPPER(d.name) "
                    + "LIKE '" + searchPattern.toUpperCase() + "%' "
                    + "ORDER BY d.name", Distributor.class).getResultList();
            return distributors;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Get the first distributor that matches the given name
    public static Distributor findDistributorByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<Distributor> distributors = em.createQuery("SELECT d FROM Distributor d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + newName.toUpperCase() + "'", Distributor.class).getResultList();
            if (distributors.size() > 0) {
                return distributors.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Distributor findDistributorById(EntityManager em, Long Id) {

        try {
            Distributor distributor = em.find(Distributor.class, Id);
            return distributor;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Distributor findDefaultDistributor(EntityManager em,
            String name,
            Boolean useTransaction) {
        Distributor distributor = Distributor.findDistributorByName(em, name);

        if (distributor == null) {
            distributor = new Distributor();
            distributor.setName(name);

            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, distributor);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, distributor);
            }
        }

        return distributor;
    }
}
