/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jm.com.dpbennett.business.entity.hrm.Employee;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.SystemOption;
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
    private static final System.Logger LOG = System.getLogger(JobStatusAndTracking.class.getName());
    public static JobStatusAndTracking copy(JobStatusAndTracking src) {
        
        JobStatusAndTracking copy = new JobStatusAndTracking();
        
        copy.dateSubmitted = src.dateSubmitted;
        copy.dateAndTimeEntered = src.dateAndTimeEntered;
        copy.jobTransferedTo = src.jobTransferedTo;
        copy.transferredTo = src.transferredTo;
        copy.enteredBy = src.enteredBy;
        copy.editedBy = src.editedBy;
        copy.completedBy = src.completedBy;
        copy.dateTransfered = src.dateTransfered;
        copy.productOrSampleReceivedBy = src.productOrSampleReceivedBy;
        copy.dateProductOrSampleReceived = src.dateProductOrSampleReceived;
        copy.statusNote = src.statusNote;
        copy.samplesCollected = src.samplesCollected;
        copy.samplesCollectedBy = src.samplesCollectedBy;
        copy.dateSamplesCollected = src.dateSamplesCollected;
        copy.expectedDateOfCompletion = src.expectedDateOfCompletion;
        copy.dateOfCompletion = src.dateOfCompletion;
        copy.dateStatusEdited = src.dateStatusEdited;
        copy.workProgress = src.workProgress;
        copy.documentCollected = src.documentCollected;
        copy.documentCollectedBy = src.documentCollectedBy;
        copy.dateDocumentCollected = src.dateDocumentCollected;
        copy.dateJobEmailWasSent = src.dateJobEmailWasSent;
        copy.jobEmailFrequency = src.jobEmailFrequency;
        copy.completed = src.completed;
        copy.alertDate = src.alertDate;
        copy.dateOfLastPayment = src.dateOfLastPayment;
        copy.depositDate = src.depositDate;
        copy.costingDate = src.costingDate;
        copy.dateCostingCompleted = src.dateCostingCompleted;
        copy.dateCostingApproved = src.dateCostingApproved;
        copy.dateCostingInvoiced = src.dateCostingInvoiced;
        copy.expectedStartDate = src.expectedStartDate;
        copy.startDate = src.startDate;
        
        return copy;
        
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateSubmitted;
    private LocalDateTime dateAndTimeEntered;
    private String jobTransferedTo;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee transferredTo;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee completedBy;
    private LocalDateTime dateTransfered;
    private String productOrSampleReceivedBy;
    private LocalDateTime dateProductOrSampleReceived;
    @Column(length = 1024)
    private String statusNote;
    private Boolean samplesCollected;
    private String samplesCollectedBy;
    private LocalDateTime dateSamplesCollected;
    private LocalDateTime expectedDateOfCompletion;
    private LocalDateTime dateOfCompletion;
    private LocalDateTime dateStatusEdited;
    private String workProgress;
    private Boolean documentCollected;
    private String documentCollectedBy;
    private LocalDateTime dateDocumentCollected;
    private LocalDateTime dateJobEmailWasSent;
    private Integer jobEmailFrequency;
    private Boolean completed;
    private LocalDateTime alertDate;
    private LocalDateTime dateOfLastPayment;
    private LocalDateTime depositDate;
    private LocalDateTime costingDate;
    private LocalDateTime dateCostingCompleted;
    private LocalDateTime dateCostingApproved;
    private LocalDateTime dateCostingInvoiced;
    private LocalDateTime expectedStartDate;
    private LocalDateTime startDate;
    @Transient
    private String editStatus;
    @Transient
    private User openedBy;
    @Transient
    private LocalDateTime dateOpened;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDateTime dateOpened) {
        this.dateOpened = dateOpened;
    }

    public User getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(User openedBy) {
        this.openedBy = openedBy;
    }

    public Employee getCompletedBy() {

        if (completedBy == null) {
            return new Employee();
        }

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(LocalDateTime expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public LocalDateTime getDateCostingApproved() {
        return dateCostingApproved;
    }

    public void setDateCostingApproved(LocalDateTime dateCostingApproved) {
        this.dateCostingApproved = dateCostingApproved;
    }

    public LocalDateTime getDateCostingInvoiced() {
        return dateCostingInvoiced;
    }

    public void setDateCostingInvoiced(LocalDateTime dateCostingInvoiced) {
        this.dateCostingInvoiced = dateCostingInvoiced;
    }

    public LocalDateTime getDateCostingCompleted() {
        return dateCostingCompleted;
    }

    public void setDateCostingCompleted(LocalDateTime dateCostingCompleted) {
        this.dateCostingCompleted = dateCostingCompleted;
    }

    public LocalDateTime getCostingDate() {
        return costingDate;
    }

    public void setCostingDate(LocalDateTime costingDate) {
        this.costingDate = costingDate;
    }

    public LocalDateTime getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDateTime DepositDate) {
        this.depositDate = DepositDate;
    }

    public LocalDateTime getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    public void setDateOfLastPayment(LocalDateTime dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDateTime alertDate) {
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

        if (transferredTo == null) {
            return new Employee();
        }

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

    public LocalDateTime getDateAndTimeEntered() {
        return dateAndTimeEntered;
    }

    public void setDateAndTimeEntered(LocalDateTime dateAndTimeEntered) {
        this.dateAndTimeEntered = dateAndTimeEntered;
    }

    public LocalDateTime getDateDocumentCollected() {
        return dateDocumentCollected;
    }

    public void setDateDocumentCollected(LocalDateTime dateDocumentCollected) {
        this.dateDocumentCollected = dateDocumentCollected;
    }

    public LocalDateTime getDateJobEmailWasSent() {
        return dateJobEmailWasSent;
    }

    public void setDateJobEmailWasSent(LocalDateTime dateJobEmailWasSent) {
        this.dateJobEmailWasSent = dateJobEmailWasSent;
    }

    public LocalDateTime getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDateTime dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public LocalDateTime getDateProductOrSampleReceived() {
        return dateProductOrSampleReceived;
    }

    public void setDateProductOrSampleReceived(LocalDateTime dateProductOrSampleReceived) {
        this.dateProductOrSampleReceived = dateProductOrSampleReceived;
    }

    public LocalDateTime getDateSamplesCollected() {
        return dateSamplesCollected;
    }

    public void setDateSamplesCollected(LocalDateTime dateSamplesCollected) {
        this.dateSamplesCollected = dateSamplesCollected;
    }

    public LocalDateTime getDateStatusEdited() {
        return dateStatusEdited;
    }

    public void setDateStatusEdited(LocalDateTime dateStatusEdited) {
        this.dateStatusEdited = dateStatusEdited;
    }

    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDateTime dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public LocalDateTime getDateTransfered() {
        return dateTransfered;
    }

    public void setDateTransfered(LocalDateTime dateTransfered) {
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

    public LocalDateTime getExpectedDateOfCompletion() {
        return expectedDateOfCompletion;
    }

    public void setExpectedDateOfCompletion(LocalDateTime expectedDateOfCompletion) {
        this.expectedDateOfCompletion = expectedDateOfCompletion;
    }

    public Integer getJobEmailFrequency() {
        return jobEmailFrequency;
    }

    public void setJobEmailFrequency(Integer jobEmailFrequency) {
        this.jobEmailFrequency = jobEmailFrequency;
    }

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
        if (!(object instanceof JobStatusAndTracking)) {
            return false;
        }
        JobStatusAndTracking other = (JobStatusAndTracking) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "jm.com.dpbennett.entity.JobStatusAndTracking[id=" + id + "]";
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
    public LocalDateTime getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(LocalDateTime dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(LocalDateTime dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            if (transferredTo != null) {
                transferredTo.save(em);
            }

            if (enteredBy != null) {
                enteredBy.save(em);
            }

            if (editedBy != null) {
                editedBy.save(em);
            }

            if (completedBy != null) {
                completedBy.save(em);
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

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SystemOption> getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SystemOption getSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
