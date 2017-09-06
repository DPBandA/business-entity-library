/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "jobcosting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobcosting.findAll", query = "SELECT j FROM Jobcosting j")
    , @NamedQuery(name = "Jobcosting.findById", query = "SELECT j FROM Jobcosting j WHERE j.id = :id")
    , @NamedQuery(name = "Jobcosting.findByName", query = "SELECT j FROM Jobcosting j WHERE j.name = :name")})
public class Jobcosting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @JoinTable(name = "jobcostingandpayment_jobcosting", joinColumns = {
        @JoinColumn(name = "jobCostings_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "JobCostingAndPayment_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Jobcostingandpayment> jobcostingandpaymentList;
    @JoinTable(name = "jobcosting_costcomponent", joinColumns = {
        @JoinColumn(name = "JobCosting_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "costComponents_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Costcomponent> costcomponentList;

    public Jobcosting() {
    }

    public Jobcosting(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Jobcostingandpayment> getJobcostingandpaymentList() {
        return jobcostingandpaymentList;
    }

    public void setJobcostingandpaymentList(List<Jobcostingandpayment> jobcostingandpaymentList) {
        this.jobcostingandpaymentList = jobcostingandpaymentList;
    }

    @XmlTransient
    public List<Costcomponent> getCostcomponentList() {
        return costcomponentList;
    }

    public void setCostcomponentList(List<Costcomponent> costcomponentList) {
        this.costcomponentList = costcomponentList;
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
        if (!(object instanceof Jobcosting)) {
            return false;
        }
        Jobcosting other = (Jobcosting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Jobcosting[ id=" + id + " ]";
    }
    
}
