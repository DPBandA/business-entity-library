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

package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "service")
public class Service implements Serializable, BusinessEntity, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Department> departmentsOfferingService;

    public Service() {
        departmentsOfferingService = new ArrayList<>();
    }
    
    public Service(String name) {
        this.name = name;
        departmentsOfferingService = new ArrayList<>();
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
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
