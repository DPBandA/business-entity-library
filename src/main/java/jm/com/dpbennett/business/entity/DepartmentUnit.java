/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.MethodResult;


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
public class DepartmentUnit implements Serializable, BusinessEntity, Comparable, Converter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String number;
    private Boolean active;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         DepartmentUnit departmentUnit = new DepartmentUnit();

        if (value != null) {
            departmentUnit.setName(value);
        }

        return departmentUnit;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       return ((DepartmentUnit) value).getName();
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
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
