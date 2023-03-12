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
package jm.com.dpbennett.business.entity.fm;

import jm.com.dpbennett.business.entity.hrm.Department;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;
//import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "sector")
@NamedQueries({
    @NamedQuery(name = "findAllSectors", query = "SELECT s FROM Sector s ORDER BY s.name")
    ,
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
    @Transient
    private Boolean isDirty;

    public Sector() {
        this.name = "";
        this.departments = new ArrayList<>();
        this.active = true;
        this.description = "";
    }

    public Sector(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
        this.active = true;
        this.description = "";
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
            List<Sector> sectors
                    = em.createQuery(
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

    public static List<Sector> findSectorsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Sector> sectors
                    = em.createQuery("SELECT s FROM Sector s WHERE UPPER(s.name) LIKE '%"
                            + newName.toUpperCase().trim() + "%' ORDER BY s.name", Sector.class).getResultList();
            return sectors;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Sector> findActiveSectorsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Sector> sectors
                    = em.createQuery("SELECT s FROM Sector s WHERE UPPER(s.name) LIKE '%"
                            + newName.toUpperCase().trim() + "%' AND s.active = 1 ORDER BY s.name", Sector.class).getResultList();
            return sectors;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
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
    public ReturnMessage save(EntityManager em) {
        try {
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Sector not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
