/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "registration")
public class Registration implements BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String number;   
    private String status;   
    private String comment;
    private Boolean active;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRegistered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateExpired;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfApplication;
    private String regulatoryDocuments;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Job job;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        if (job == null) {
            job = new Job("");
        }

        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Date getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(Date dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegulatoryDocuments() {
        if (regulatoryDocuments == null) {
            regulatoryDocuments = "--";
        }
        return regulatoryDocuments;
    }

    public void setRegulatoryDocuments(String regulatoryDocuments) {
        this.regulatoryDocuments = regulatoryDocuments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public int compareTo(Object o) {
//         this sorts in descending based on registration date.
        if ((((Registration) o).dateRegistered != null) && (this.dateRegistered != null)) {
            return Collator.getInstance().compare(
                    new Long(((Registration) o).dateRegistered.getTime()).toString(),
                    new Long(this.dateRegistered.getTime()).toString());
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.Registration[id=" + id + "]";
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
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
