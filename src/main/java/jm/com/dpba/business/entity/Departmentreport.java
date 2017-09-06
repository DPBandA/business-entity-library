/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "departmentreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departmentreport.findAll", query = "SELECT d FROM Departmentreport d"),
    @NamedQuery(name = "Departmentreport.findById", query = "SELECT d FROM Departmentreport d WHERE d.id = :id"),
    @NamedQuery(name = "Departmentreport.findByExecutivesummary", query = "SELECT d FROM Departmentreport d WHERE d.executivesummary = :executivesummary"),
    @NamedQuery(name = "Departmentreport.findByReportyear", query = "SELECT d FROM Departmentreport d WHERE d.reportyear = :reportyear"),
    @NamedQuery(name = "Departmentreport.findByReportperiod", query = "SELECT d FROM Departmentreport d WHERE d.reportperiod = :reportperiod")})
public class Departmentreport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "EXECUTIVESUMMARY")
    private String executivesummary;
    @Column(name = "REPORTYEAR")
    private Integer reportyear;
    @Column(name = "REPORTPERIOD")
    private Integer reportperiod;
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department departmentId;

    public Departmentreport() {
    }

    public Departmentreport(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExecutivesummary() {
        return executivesummary;
    }

    public void setExecutivesummary(String executivesummary) {
        this.executivesummary = executivesummary;
    }

    public Integer getReportyear() {
        return reportyear;
    }

    public void setReportyear(Integer reportyear) {
        this.reportyear = reportyear;
    }

    public Integer getReportperiod() {
        return reportperiod;
    }

    public void setReportperiod(Integer reportperiod) {
        this.reportperiod = reportperiod;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
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
        if (!(object instanceof Departmentreport)) {
            return false;
        }
        Departmentreport other = (Departmentreport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Departmentreport[ id=" + id + " ]";
    }
    
}
