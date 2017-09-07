/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.text.Collator;
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

    public JobReportItem() {
        departments = new ArrayList<Department>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public List<Department> getDepartments() {
        if (departments == null) {
            departments = new ArrayList<Department>();
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

}