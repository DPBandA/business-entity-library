/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "jobstatusandtracking")
@XmlRootElement
public class JobStatusAndTracking implements Serializable {

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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date costingDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCostingCompleted;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCostingApproved ;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Employee getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }
        return editedBy;
    }

    public void setEditedBy(Employee editedBy) {
        this.editedBy = editedBy;
    }

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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.JobStatusAndTracking[id=" + id + "]";
    }
}
