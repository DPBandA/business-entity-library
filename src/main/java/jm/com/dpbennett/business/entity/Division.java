/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "division")
@NamedQueries({
    @NamedQuery(name = "findAllDivisions", query = "SELECT e FROM Division e ORDER BY e.name")
})
@XmlRootElement
public class Division implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany
    private List<Department> departments;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Division)) {
            return false;
        }
        Division other = (Division) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.Division[id=" + id + "]";
    }

    public static Division findDivisionById(EntityManager em, Long Id) {

        try {
            Division division = em.find(Division.class, Id);

            return division;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<Division> findAllDivisions(EntityManager em) {

        try {
            return em.createNamedQuery("findAllDivisions", Division.class).getResultList();
        } catch (Exception e) {
            return null;
        }
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

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
