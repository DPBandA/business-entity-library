/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "jobtask")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobtask.findAll", query = "SELECT j FROM Jobtask j"),
    @NamedQuery(name = "Jobtask.findById", query = "SELECT j FROM Jobtask j WHERE j.id = :id"),
    @NamedQuery(name = "Jobtask.findByStartdate", query = "SELECT j FROM Jobtask j WHERE j.startdate = :startdate"),
    @NamedQuery(name = "Jobtask.findByName", query = "SELECT j FROM Jobtask j WHERE j.name = :name"),
    @NamedQuery(name = "Jobtask.findByEnddate", query = "SELECT j FROM Jobtask j WHERE j.enddate = :enddate"),
    @NamedQuery(name = "Jobtask.findByNotes", query = "SELECT j FROM Jobtask j WHERE j.notes = :notes"),
    @NamedQuery(name = "Jobtask.findByCompleted", query = "SELECT j FROM Jobtask j WHERE j.completed = :completed")})
public class Jobtask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Size(max = 255)
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "COMPLETED")
    private Boolean completed;
    @JoinColumn(name = "DEPARTMENTRESPONSIBLE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department departmentresponsibleId;
    @JoinColumn(name = "EMPLOYEERESPONSIBLE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee employeeresponsibleId;

    public Jobtask() {
    }

    public Jobtask(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Department getDepartmentresponsibleId() {
        return departmentresponsibleId;
    }

    public void setDepartmentresponsibleId(Department departmentresponsibleId) {
        this.departmentresponsibleId = departmentresponsibleId;
    }

    public Employee getEmployeeresponsibleId() {
        return employeeresponsibleId;
    }

    public void setEmployeeresponsibleId(Employee employeeresponsibleId) {
        this.employeeresponsibleId = employeeresponsibleId;
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
        if (!(object instanceof Jobtask)) {
            return false;
        }
        Jobtask other = (Jobtask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Jobtask[ id=" + id + " ]";
    }
    
}
