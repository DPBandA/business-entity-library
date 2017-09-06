/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"),
    @NamedQuery(name = "Registration.findById", query = "SELECT r FROM Registration r WHERE r.id = :id"),
    @NamedQuery(name = "Registration.findByDateregistered", query = "SELECT r FROM Registration r WHERE r.dateregistered = :dateregistered"),
    @NamedQuery(name = "Registration.findByRegulatorydocuments", query = "SELECT r FROM Registration r WHERE r.regulatorydocuments = :regulatorydocuments"),
    @NamedQuery(name = "Registration.findByStatus", query = "SELECT r FROM Registration r WHERE r.status = :status"),
    @NamedQuery(name = "Registration.findByDateofapplication", query = "SELECT r FROM Registration r WHERE r.dateofapplication = :dateofapplication"),
    @NamedQuery(name = "Registration.findByActive", query = "SELECT r FROM Registration r WHERE r.active = :active"),
    @NamedQuery(name = "Registration.findByNumber", query = "SELECT r FROM Registration r WHERE r.number = :number"),
    @NamedQuery(name = "Registration.findByType", query = "SELECT r FROM Registration r WHERE r.type = :type"),
    @NamedQuery(name = "Registration.findByComment", query = "SELECT r FROM Registration r WHERE r.comment = :comment"),
    @NamedQuery(name = "Registration.findByDateexpired", query = "SELECT r FROM Registration r WHERE r.dateexpired = :dateexpired")})
public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATEREGISTERED")
    @Temporal(TemporalType.DATE)
    private Date dateregistered;
    @Size(max = 255)
    @Column(name = "REGULATORYDOCUMENTS")
    private String regulatorydocuments;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DATEOFAPPLICATION")
    @Temporal(TemporalType.DATE)
    private Date dateofapplication;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "DATEEXPIRED")
    @Temporal(TemporalType.DATE)
    private Date dateexpired;
    @ManyToMany(mappedBy = "registrationList")
    private List<Foodfactory> foodfactoryList;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "ID")
    @ManyToOne
    private Job jobId;

    public Registration() {
    }

    public Registration(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateregistered() {
        return dateregistered;
    }

    public void setDateregistered(Date dateregistered) {
        this.dateregistered = dateregistered;
    }

    public String getRegulatorydocuments() {
        return regulatorydocuments;
    }

    public void setRegulatorydocuments(String regulatorydocuments) {
        this.regulatorydocuments = regulatorydocuments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateofapplication() {
        return dateofapplication;
    }

    public void setDateofapplication(Date dateofapplication) {
        this.dateofapplication = dateofapplication;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateexpired() {
        return dateexpired;
    }

    public void setDateexpired(Date dateexpired) {
        this.dateexpired = dateexpired;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
    }

    public Job getJobId() {
        return jobId;
    }

    public void setJobId(Job jobId) {
        this.jobId = jobId;
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
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Registration[ id=" + id + " ]";
    }
    
}
