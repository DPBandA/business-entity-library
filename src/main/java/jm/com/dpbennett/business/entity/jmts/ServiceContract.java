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

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.fm.Service;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "servicecontract")
@NamedQueries({
    @NamedQuery(name = "findAllServiceContracts", query = "SELECT s FROM ServiceContract s ORDER BY s.id"),
    @NamedQuery(name = "findByJobId", query = "SELECT s FROM ServiceContract s WHERE s.jobId = :jobId")
})
public class ServiceContract implements BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long jobId;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Service selectedService;
    private String serviceRequestedDetails;
    // Additional services    
    private Boolean additionalServiceUrgent;
    private Boolean additionalServiceFaxResults;
    private Boolean additionalServiceTelephonePresumptiveResults;
    private Boolean additionalServiceSendMoreContractForms;
    private Boolean additionalServiceOther;
    private String additionalServiceOtherText;
    // Intended market
    private Boolean intendedMarketLocal;
    private Boolean intendedMarketCaricom;
    private Boolean intendedMarketUK;
    private Boolean intendedMarketUSA;
    private Boolean intendedMarketCanada;
    private Boolean intendedMarketOther;
    private String intendedMarketOtherText;
    private Long billingAddressId;
    @Column(length = 1024)
    private String specialInstructions;
    private String submittedBy;
    private String receivedBy;
    private Integer estimatedTurnAroundTime;
    private Boolean autoAddSampleInformation;
    @Transient
    private Job job;
    @Transient
    private Boolean isDirty;

    public ServiceContract() {
        selectedService = new Service();
        // Additional services
        additionalServiceUrgent = false;
        additionalServiceFaxResults = false;
        additionalServiceTelephonePresumptiveResults = false;
        additionalServiceSendMoreContractForms = false;
        additionalServiceOther = false;
        additionalServiceOtherText = "";
        // Intended market
        intendedMarketLocal = true;
        intendedMarketCaricom = false;
        intendedMarketUK = false;
        intendedMarketUSA = false;
        intendedMarketCanada = false;
        intendedMarketOther = false;
        intendedMarketOtherText = "";
    }

    public ServiceContract(ServiceContract src) {
        selectedService = new Service();
        // Additional services
        additionalServiceUrgent = src.additionalServiceUrgent;
        additionalServiceFaxResults = src.additionalServiceFaxResults;
        additionalServiceTelephonePresumptiveResults = src.additionalServiceTelephonePresumptiveResults;
        additionalServiceSendMoreContractForms = src.additionalServiceSendMoreContractForms;
        additionalServiceOther = src.additionalServiceOther;
        additionalServiceOtherText = src.additionalServiceOtherText;
        // Intended market
        intendedMarketLocal = src.intendedMarketLocal;
        intendedMarketCaricom = src.intendedMarketCaricom;
        intendedMarketUK = src.intendedMarketUK;
        intendedMarketUSA = src.intendedMarketUSA;
        intendedMarketCanada = src.intendedMarketCanada;
        intendedMarketOther = src.intendedMarketOther;
        intendedMarketOtherText = src.intendedMarketOtherText;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Service getSelectedService() {

        if (selectedService == null) {
            selectedService = new Service();
        }

        return selectedService;
    }

    public void setSelectedService(Service selectedService) {
        this.selectedService = selectedService;
    }

    public Service getSelectedServiceForContract() {

        if (selectedService == null) {

            if (job != null) {
                if (!getJob().getServices().isEmpty()) {
                    return getJob().getServices().get(0);
                } else {
                    return new Service("None");
                }
            } else {
                return new Service("None");
            }

        } else {
            return selectedService;
        }

    }

    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }
        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAutoAddSampleInformation() {
        if (autoAddSampleInformation == null) {
            autoAddSampleInformation = false;
        }
        return autoAddSampleInformation;
    }

    public void setAutoAddSampleInformation(Boolean autoAddSampleInformation) {
        this.autoAddSampleInformation = autoAddSampleInformation;
    }

    public String getAdditionalServiceOtherText() {
        return additionalServiceOtherText;
    }

    public void setAdditionalServiceOtherText(String additionalServiceOtherText) {
        this.additionalServiceOtherText = additionalServiceOtherText;
    }

    public String getIntendedMarketOtherText() {
        return intendedMarketOtherText;
    }

    public void setIntendedMarketOtherText(String intendedMarketOtherText) {
        this.intendedMarketOtherText = intendedMarketOtherText;
    }

    public String getServiceRequestedDetails() {
        return serviceRequestedDetails;
    }

    public void setServiceRequestedDetails(String serviceRequestedDetails) {
        this.serviceRequestedDetails = serviceRequestedDetails;
    }

    public Boolean getAdditionalServiceFaxResults() {
        if (additionalServiceFaxResults == null) {
            additionalServiceFaxResults = false;
        }
        return additionalServiceFaxResults;
    }

    public void setAdditionalServiceFaxResults(Boolean additionalServiceFaxResults) {
        this.additionalServiceFaxResults = additionalServiceFaxResults;
    }

    public Boolean getAdditionalServiceOther() {
        if (additionalServiceOther == null) {
            additionalServiceOther = false;
        }
        return additionalServiceOther;
    }

    public void setAdditionalServiceOther(Boolean additionalServiceOther) {
        this.additionalServiceOther = additionalServiceOther;
    }

    public Boolean getAdditionalServiceSendMoreContractForms() {
        if (additionalServiceSendMoreContractForms == null) {
            additionalServiceSendMoreContractForms = false;
        }
        return additionalServiceSendMoreContractForms;
    }

    public void setAdditionalServiceSendMoreContractForms(Boolean additionalServiceSendMoreContractForms) {
        this.additionalServiceSendMoreContractForms = additionalServiceSendMoreContractForms;
    }

    public Boolean getAdditionalServiceTelephonePresumptiveResults() {
        if (additionalServiceTelephonePresumptiveResults == null) {
            additionalServiceTelephonePresumptiveResults = false;
        }
        return additionalServiceTelephonePresumptiveResults;
    }

    public void setAdditionalServiceTelephonePresumptiveResults(Boolean additionalServiceTelephonePresumptiveResults) {
        this.additionalServiceTelephonePresumptiveResults = additionalServiceTelephonePresumptiveResults;
    }

    public Boolean getAdditionalServiceUrgent() {
        if (additionalServiceUrgent == null) {
            additionalServiceUrgent = false;
        }
        return additionalServiceUrgent;
    }

    public void setAdditionalServiceUrgent(Boolean additionalServiceUrgent) {
        this.additionalServiceUrgent = additionalServiceUrgent;
    }

    public Integer getEstimatedTurnAroundTime() {
        return estimatedTurnAroundTime;
    }

    public void setEstimatedTurnAroundTime(Integer estimatedTurnAroundTime) {
        this.estimatedTurnAroundTime = estimatedTurnAroundTime;
    }

    public Boolean getIntendedMarketCanada() {
        if (intendedMarketCanada == null) {
            intendedMarketCanada = false;
        }
        return intendedMarketCanada;
    }

    public void setIntendedMarketCanada(Boolean intendedMarketCanada) {
        this.intendedMarketCanada = intendedMarketCanada;
    }

    public Boolean getIntendedMarketCaricom() {
        if (intendedMarketCaricom == null) {
            intendedMarketCaricom = false;
        }
        return intendedMarketCaricom;
    }

    public void setIntendedMarketCaricom(Boolean intendedMarketCaricom) {
        this.intendedMarketCaricom = intendedMarketCaricom;
    }

    public Boolean getIntendedMarketLocal() {
        if (intendedMarketLocal == null) {
            intendedMarketLocal = false;
        }
        return intendedMarketLocal;
    }

    public void setIntendedMarketLocal(Boolean intendedMarketLocal) {
        this.intendedMarketLocal = intendedMarketLocal;
    }

    public Boolean getIntendedMarketOther() {
        if (intendedMarketOther == null) {
            intendedMarketOther = false;
        }
        return intendedMarketOther;
    }

    public void setIntendedMarketOther(Boolean intendedMarketOther) {
        this.intendedMarketOther = intendedMarketOther;
    }

    public Boolean getIntendedMarketUK() {
        if (intendedMarketUK == null) {
            intendedMarketUK = false;
        }
        return intendedMarketUK;
    }

    public void setIntendedMarketUK(Boolean intendedMarketUK) {
        this.intendedMarketUK = intendedMarketUK;
    }

    public Boolean getIntendedMarketUSA() {
        if (intendedMarketUSA == null) {
            intendedMarketUSA = false;
        }
        return intendedMarketUSA;
    }

    public void setIntendedMarketUSA(Boolean intendedMarketUSA) {
        this.intendedMarketUSA = intendedMarketUSA;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressI(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getSpecialInstructions() {
        if (specialInstructions == null) {
            specialInstructions = "";
        }
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ServiceContract)) {
            return false;
        }
        ServiceContract other = (ServiceContract) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Service contract Id: " + id;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {
    }

    public static ServiceContract findServiceContractByJobId(EntityManager em, Long jobId) {

        try {
            Query q = em.createNamedQuery("findByJobId");
            q.setParameter("jobId", jobId);

            return (ServiceContract) q.getSingleResult();
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            getSelectedService().save(em);

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Service Contract not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
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
    public ReturnMessage delete(EntityManager em) {
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
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
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
}
