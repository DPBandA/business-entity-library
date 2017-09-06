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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "classification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classification.findAll", query = "SELECT c FROM Classification c")
    , @NamedQuery(name = "Classification.findById", query = "SELECT c FROM Classification c WHERE c.id = :id")
    , @NamedQuery(name = "Classification.findByName", query = "SELECT c FROM Classification c WHERE c.name = :name")
    , @NamedQuery(name = "Classification.findByActive", query = "SELECT c FROM Classification c WHERE c.active = :active")
    , @NamedQuery(name = "Classification.findByDescription", query = "SELECT c FROM Classification c WHERE c.description = :description")
    , @NamedQuery(name = "Classification.findByIsearning", query = "SELECT c FROM Classification c WHERE c.isearning = :isearning")})
public class Classification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ISEARNING")
    private Boolean isearning;
    @OneToMany(mappedBy = "classificationId")
    private List<Documentstandard> documentstandardList;
    @OneToMany(mappedBy = "classificationId")
    private List<Servicerequest> servicerequestList;
    @OneToMany(mappedBy = "classificationId")
    private List<Job> jobList;
    @OneToMany(mappedBy = "classificationId")
    private List<Documenttracking> documenttrackingList;

    public Classification() {
    }

    public Classification(Long id) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsearning() {
        return isearning;
    }

    public void setIsearning(Boolean isearning) {
        this.isearning = isearning;
    }

    @XmlTransient
    public List<Documentstandard> getDocumentstandardList() {
        return documentstandardList;
    }

    public void setDocumentstandardList(List<Documentstandard> documentstandardList) {
        this.documentstandardList = documentstandardList;
    }

    @XmlTransient
    public List<Servicerequest> getServicerequestList() {
        return servicerequestList;
    }

    public void setServicerequestList(List<Servicerequest> servicerequestList) {
        this.servicerequestList = servicerequestList;
    }

    @XmlTransient
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    @XmlTransient
    public List<Documenttracking> getDocumenttrackingList() {
        return documenttrackingList;
    }

    public void setDocumenttrackingList(List<Documenttracking> documenttrackingList) {
        this.documenttrackingList = documenttrackingList;
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
        if (!(object instanceof Classification)) {
            return false;
        }
        Classification other = (Classification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Classification[ id=" + id + " ]";
    }
    
}
