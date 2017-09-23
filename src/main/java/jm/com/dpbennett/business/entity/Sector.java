/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.MethodResult;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "sector")
@NamedQueries({
    @NamedQuery(name = "findAllSectors", query = "SELECT s FROM Sector s ORDER BY s.name"),
    @NamedQuery(name = "findAllActiveSectors", query = "SELECT s FROM Sector s WHERE s.active = 1 ORDER BY s.name")
})
@XmlRootElement
public class Sector implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Department> departments;
    private Boolean active;
    @Column(length = 1024)
    private String description;

    public Sector() {
        departments = new ArrayList<>();
    }
    
    public Sector(String name) {
        this.name = name;
        departments = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        if (active == null) {
            active = true;
        }
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    @JsonIgnore
    public List<Department> getDepartments() {
        if (departments == null) {
            departments = new ArrayList<>();
        }
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Sector> findAllSectors(EntityManager em) {

        try {
            List<Sector> sectors = em.createNamedQuery("findAllSectors", Sector.class).getResultList();

            return sectors;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<Sector> findAllActiveSectors(EntityManager em) {

        try {
            List<Sector> sectors = em.createNamedQuery("findAllActiveSectors", Sector.class).getResultList();

            return sectors;
        } catch (Exception e) {

            return null;
        }
    }

    public static Sector findSectorById(EntityManager em, Long id) {

        try {
            Sector sector = em.find(Sector.class, id);

            return sector;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<Sector> findAllSectorsByDeparment(EntityManager em, Department department) {
        try {
            List<Sector> sectors =
                    em.createQuery(
                    "SELECT s FROM Sector s JOIN s.departments department"
                    + " WHERE department.name = '" + department.getName().trim() + "'"
                    + " ORDER BY s.name", Sector.class).getResultList();
            return sectors;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Sector findSectorByName(EntityManager em, String sectorName) {

        try {
            String newSectorName = sectorName.trim().replaceAll("'", "''");

            List<Sector> sectors = em.createQuery("SELECT s FROM Sector s "
                    + "WHERE UPPER(s.name) "
                    + "= '" + newSectorName.toUpperCase() + "'", Sector.class).getResultList();
            if (sectors.size() > 0) {
                return sectors.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setUsable(String usable) {
        if (usable.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
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
