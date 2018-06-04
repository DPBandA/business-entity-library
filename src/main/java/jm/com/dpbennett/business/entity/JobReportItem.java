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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "jobreportitem")
@NamedQueries({
    @NamedQuery(name = "findAllJobReportItems", query = "SELECT j FROM JobReportItem j ORDER BY j.id ASC")
})
public class JobReportItem implements Serializable, BusinessEntity, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double itemValue;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Department> departments;
    @Transient
    private Boolean isDirty;

    public JobReportItem() {
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

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobReportItem)) {
            return false;
        }
        JobReportItem other = (JobReportItem) object;
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

    @Override
    public int compareTo(Object o) {

        if ((((JobReportItem) o).id != null) && (this.id != null)) {
            if (((JobReportItem) o).id > this.id) {
                return -1;
            } else if (((JobReportItem) o).id < this.id) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
    
    
    public static List<JobReportItem> findAllJobReportItems(EntityManager em) {

        try {
            List<JobReportItem> items = em.createNamedQuery("findAllJobReportItems", JobReportItem.class).getResultList();

            return items;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }    
       

    public static List<JobReportItem> findAllJobReportItemsByDeparment(EntityManager em, Department department) {
        try {
            List<JobReportItem> jobJobReportItems =
                    em.createQuery(
                    "SELECT j FROM JobReportItem j JOIN j.departments department"
                    + " WHERE department.name = '" + department.getName().trim() + "'"
                    + " ORDER BY j.id", JobReportItem.class).getResultList();
            return jobJobReportItems;
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
