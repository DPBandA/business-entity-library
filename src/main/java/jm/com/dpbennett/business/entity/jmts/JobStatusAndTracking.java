/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.jmts;

import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
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
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "jobstatusandtracking")
public class JobStatusAndTracking implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSubmitted;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAndTimeEntered;
    private String jobTransferedTo; // to be removed
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee transferredTo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTransfered; // tk change name dateTransferred   
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee completedBy;
    private String productOrSampleReceivedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateProductOrSampleReceived;
    @Column(length = 1024)
    private String statusNote;
    private Boolean samplesCollected;
    private String samplesCollectedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSamplesCollected;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedDateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateStatusEdited;
    private String workProgress;
    private Boolean documentCollected;
    private String documentCollectedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDocumentCollected;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateJobEmailWasSent;
    private Integer jobEmailFrequency;
    private Boolean completed;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date alertDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfLastPayment;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date depositDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date costingDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCostingCompleted;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCostingApproved;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCostingInvoiced;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Transient
    private String editStatus;
    @Transient
    private User openedBy;
    @Transient
    private Date dateOpened;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public User getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(User openedBy) {
        this.openedBy = openedBy;
    }

    public Employee getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(Employee completedBy) {
        this.completedBy = completedBy;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(Date expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public Date getDateCostingApproved() {
        return dateCostingApproved;
    }

    public void setDateCostingApproved(Date dateCostingApproved) {
        this.dateCostingApproved = dateCostingApproved;
    }

    public Date getDateCostingInvoiced() {
        return dateCostingInvoiced;
    }

    public void setDateCostingInvoiced(Date dateCostingInvoiced) {
        this.dateCostingInvoiced = dateCostingInvoiced;
    }

    public Date getDateCostingCompleted() {
        return dateCostingCompleted;
    }

    public void setDateCostingCompleted(Date dateCostingCompleted) {
        this.dateCostingCompleted = dateCostingCompleted;
    }

    public Date getCostingDate() {
        return costingDate;
    }

    public void setCostingDate(Date costingDate) {
        this.costingDate = costingDate;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date DepositDate) {
        this.depositDate = DepositDate;
    }

    public Date getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    public void setDateOfLastPayment(Date dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    @Override
    public Employee getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }
        return editedBy;
    }

    public void setEditedBy(Employee editedBy) {
        this.editedBy = editedBy;
    }

    @Override
    public Employee getEnteredBy() {
        if (enteredBy == null) {
            return new Employee();
        }

        return enteredBy;
    }

    public void setEnteredBy(Employee enteredBy) {

        this.enteredBy = enteredBy;
    }

    public Employee getTransferredTo() {
        return transferredTo;
    }

    public void setTransferredTo(Employee transferredTo) {
        this.transferredTo = transferredTo;
    }

    public Boolean getCompleted() {
        if (completed == null) {
            completed = false;
        }
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getDateAndTimeEntered() {
        return dateAndTimeEntered;
    }

    public void setDateAndTimeEntered(Date dateAndTimeEntered) {
        this.dateAndTimeEntered = dateAndTimeEntered;
    }

    public Date getDateDocumentCollected() {
        return dateDocumentCollected;
    }

    public void setDateDocumentCollected(Date dateDocumentCollected) {
        this.dateDocumentCollected = dateDocumentCollected;
    }

    public Date getDateJobEmailWasSent() {
        return dateJobEmailWasSent;
    }

    public void setDateJobEmailWasSent(Date dateJobEmailWasSent) {
        this.dateJobEmailWasSent = dateJobEmailWasSent;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Date getDateProductOrSampleReceived() {
        return dateProductOrSampleReceived;
    }

    public void setDateProductOrSampleReceived(Date dateProductOrSampleReceived) {
        this.dateProductOrSampleReceived = dateProductOrSampleReceived;
    }

    public Date getDateSamplesCollected() {
        return dateSamplesCollected;
    }

    public void setDateSamplesCollected(Date dateSamplesCollected) {
        this.dateSamplesCollected = dateSamplesCollected;
    }

    public Date getDateStatusEdited() {
        return dateStatusEdited;
    }

    public void setDateStatusEdited(Date dateStatusEdited) {
        this.dateStatusEdited = dateStatusEdited;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Date getDateTransfered() {
        return dateTransfered;
    }

    public void setDateTransfered(Date dateTransfered) {
        this.dateTransfered = dateTransfered;
    }

    public Boolean getDocumentCollected() {
        if (documentCollected == null) {
            documentCollected = false;
        }
        return documentCollected;
    }

    public void setDocumentCollected(Boolean documentCollected) {
        this.documentCollected = documentCollected;
    }

    public String getDocumentCollectedBy() {
        return documentCollectedBy;
    }

    public void setDocumentCollectedBy(String documentCollectedBy) {
        this.documentCollectedBy = documentCollectedBy;
    }

    public Date getExpectedDateOfCompletion() {
        return expectedDateOfCompletion;
    }

    public void setExpectedDateOfCompletion(Date expectedDateOfCompletion) {
        this.expectedDateOfCompletion = expectedDateOfCompletion;
    }

    public Integer getJobEmailFrequency() {
        return jobEmailFrequency;
    }

    public void setJobEmailFrequency(Integer jobEmailFrequency) {
        this.jobEmailFrequency = jobEmailFrequency;
    }

//    public String getJobEnteredBy() {
//        return jobEnteredBy;
//    }
//
//    public void setJobEnteredBy(String jobEnteredBy) {
//        this.jobEnteredBy = jobEnteredBy;
//    }
    public String getJobTransferedTo() {
        return jobTransferedTo;
    }

    public void setJobTransferedTo(String jobTransferedTo) {
        this.jobTransferedTo = jobTransferedTo;
    }

    public String getProductOrSampleReceivedBy() {
        return productOrSampleReceivedBy;
    }

    public void setProductOrSampleReceivedBy(String productOrSampleReceivedBy) {
        this.productOrSampleReceivedBy = productOrSampleReceivedBy;
    }

    public Boolean getSamplesCollected() {
        if (samplesCollected == null) {
            samplesCollected = false;
        }
        return samplesCollected;
    }

    public void setSamplesCollected(Boolean samplesCollected) {
        this.samplesCollected = samplesCollected;
    }

    public String getSamplesCollectedBy() {
        return samplesCollectedBy;
    }

    public void setSamplesCollectedBy(String samplesCollectedBy) {
        this.samplesCollectedBy = samplesCollectedBy;
    }

    public String getStatusNote() {
        if (statusNote == null) {
            statusNote = "";
        }
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }

    public String getWorkProgress() {
        if (workProgress == null) {
            workProgress = "Not started";
        }
        return workProgress;
    }

    public void setWorkProgress(String workProgress) {
        this.workProgress = workProgress;
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
        if (!(object instanceof JobStatusAndTracking)) {
            return false;
        }
        JobStatusAndTracking other = (JobStatusAndTracking) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.JobStatusAndTracking[id=" + id + "]";
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {
        
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            if (getTransferredTo() != null) {
                getTransferredTo().save(em);
            }
            
            if (getEnteredBy() != null) {
                getEnteredBy().save(em);
            }
            
            if (getEditedBy() != null) {
                getEditedBy().save(em);
            }
            
            if (getCompletedBy() != null) {
                getCompletedBy().save(em);
            }
            
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Job Status and Tracking not saved");
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean getIsDirty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
