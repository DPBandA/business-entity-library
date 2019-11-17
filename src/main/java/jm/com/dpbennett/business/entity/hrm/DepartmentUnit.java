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

package jm.com.dpbennett.business.entity.hrm;

import java.io.Serializable;
import java.text.Collator;
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
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;


/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "departmentunit")
@NamedQueries({
    @NamedQuery(name = "findAllDepartmentUnits", query = "SELECT e FROM DepartmentUnit e ORDER BY e.name"),
    @NamedQuery(name = "findAllActiveDepartmentUnits", query = "SELECT e FROM DepartmentUnit e WHERE e.active = :active ORDER BY e.name")
})
public class DepartmentUnit implements Serializable, BusinessEntity, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String number;
    private Boolean active;
    @Transient
    private Boolean isDirty;

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
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        if (!(object instanceof DepartmentUnit)) {
            return false;
        }
        DepartmentUnit other = (DepartmentUnit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.name, ((DepartmentUnit) o).name);
    }
    
    public static List<DepartmentUnit> findDepartmentUnitsByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<DepartmentUnit> departmentUnits =
                    em.createQuery("SELECT d FROM DepartmentUnit d where UPPER(d.name) like '"
                    + newName.toUpperCase().trim() + "%' ORDER BY d.name", DepartmentUnit.class).getResultList();
            return departmentUnits;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<DepartmentUnit>();
        }
    }

    public static List<DepartmentUnit> findAllActiveDepartmentUnits(EntityManager em) {

        try {
            return em.createQuery("select d from DepartmentUnit d where d.active = 1 order by d.name", DepartmentUnit.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static DepartmentUnit findDepartmentUnitByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<DepartmentUnit> departmentUnits = em.createQuery("SELECT d FROM DepartmentUnit d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + newName.toUpperCase() + "'", DepartmentUnit.class).getResultList();
            if (departmentUnits.size() > 0) {
                return departmentUnits.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static DepartmentUnit findDepartmentUnitById(EntityManager em, Long Id) {
        return em.find(DepartmentUnit.class, Id);
    }

    public static List<String> findAllDepartmentUnitNames(EntityManager em) {

        ArrayList<String> names = new ArrayList<String>();

        try { // tk try String.class instead of Department.class for better performance
            List<DepartmentUnit> departmentUnits = em.createNamedQuery("findAllDepartmentUnits", DepartmentUnit.class).getResultList();
            for (DepartmentUnit departmentUnit : departmentUnits) {
                names.add(departmentUnit.getName());
            }
            return names;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<DepartmentUnit> findAllDepartmentUnits(EntityManager em) {

        try {
            return em.createNamedQuery("findAllDepartmentUnits", DepartmentUnit.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static DepartmentUnit getDefaultDepartmentUnit(EntityManager em,
            String name) {
        DepartmentUnit departmentUnit = DepartmentUnit.findDepartmentUnitByName(em, name);

        if (departmentUnit == null) {
            departmentUnit = new DepartmentUnit();

            em.getTransaction().begin();
            departmentUnit.setName(name);
            BusinessEntityUtils.saveBusinessEntity(em, departmentUnit);
            em.getTransaction().commit();
        }

        return departmentUnit;
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
