/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "service")
public class Service implements Serializable, BusinessEntity, Comparable, Converter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Department> departmentsOfferingService;

    public Service() {
        departmentsOfferingService = new ArrayList<Department>();
    }
    
    public Service(String name) {
        this.name = name;
        departmentsOfferingService = new ArrayList<Department>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Department> getDepartmentsOfferingService() {
        if (departmentsOfferingService != null) {
            Collections.sort(departmentsOfferingService);
        } else {
            departmentsOfferingService = new ArrayList<Department>();
        }
        
        return departmentsOfferingService;
    }

    public void setDepartmentsOfferingService(List<Department> departmentsOfferingService) {
        this.departmentsOfferingService = departmentsOfferingService;
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
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
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
        return Collator.getInstance().compare(this.name, ((Service) o).name);
    }
    
    public static Service findServiceByName(EntityManager em, String serviceName) {

        try {
            String newServiceName = serviceName.trim().replaceAll("'", "''");

            List<Service> services = em.createQuery("SELECT s FROM Service s "
                    + "WHERE UPPER(s.name) "
                    + "= '" + newServiceName.toUpperCase() + "'", Service.class).getResultList();
            if (services.size() > 0) {
                return services.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
     public static List<Service> findServicesByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Service> services =
                    em.createQuery("SELECT s FROM Service s where UPPER(s.name) like '"
                    + newName.toUpperCase().trim() + "%' ORDER BY s.name", Service.class).getResultList();
            return services;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Service>();
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         Service service = new Service();

        if (value != null) {
            service.setName(value);
        }

        return service;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Service)value).getName();
    }
}
