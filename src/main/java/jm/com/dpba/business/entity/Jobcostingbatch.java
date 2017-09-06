/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "jobcostingbatch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobcostingbatch.findAll", query = "SELECT j FROM Jobcostingbatch j"),
    @NamedQuery(name = "Jobcostingbatch.findById", query = "SELECT j FROM Jobcostingbatch j WHERE j.id = :id"),
    @NamedQuery(name = "Jobcostingbatch.findByBatchnumber", query = "SELECT j FROM Jobcostingbatch j WHERE j.batchnumber = :batchnumber"),
    @NamedQuery(name = "Jobcostingbatch.findByJobnumber", query = "SELECT j FROM Jobcostingbatch j WHERE j.jobnumber = :jobnumber")})
public class Jobcostingbatch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "BATCHNUMBER")
    private String batchnumber;
    @Size(max = 255)
    @Column(name = "JOBNUMBER")
    private String jobnumber;
    @JoinTable(name = "jobcostingbatch_job", joinColumns = {
        @JoinColumn(name = "JobCostingBatch_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "jobs_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Job> jobList;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;

    public Jobcostingbatch() {
    }

    public Jobcostingbatch(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchnumber() {
        return batchnumber;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    @XmlTransient
    @JsonIgnore
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        if (!(object instanceof Jobcostingbatch)) {
            return false;
        }
        Jobcostingbatch other = (Jobcostingbatch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Jobcostingbatch[ id=" + id + " ]";
    }
    
}
