/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "jobtask")
public class JobTask implements BusinessEntity, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    private String notes;
    private Boolean completed;
    @OneToOne(cascade=CascadeType.REFRESH)
    private Department departmentResponsible;
    @OneToOne(cascade=CascadeType.REFRESH)
    private Employee employeeResponsible;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Department getDepartmentResponsible() {
        return departmentResponsible;
    }

    public void setDepartmentResponsible(Department departmentResponsible) {
        this.departmentResponsible = departmentResponsible;
    }

    public Employee getEmployeeResponsible() {
        return employeeResponsible;
    }

    public void setEmployeeResponsible(Employee employeeResponsible) {
        this.employeeResponsible = employeeResponsible;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
        if (!(object instanceof JobTask)) {
            return false;
        }
        JobTask other = (JobTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.JobTask[id=" + id + "]";
    }

}