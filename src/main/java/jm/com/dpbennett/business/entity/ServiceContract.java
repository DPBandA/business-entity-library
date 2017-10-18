/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

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

package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "servicecontract")
@NamedQueries({
    @NamedQuery(name = "findAllServiceContracts", query = "SELECT s FROM ServiceContract s ORDER BY s.id"),
    @NamedQuery(name = "findByJobId", query = "SELECT s FROM ServiceContract s WHERE s.jobId = :jobId")
})
@XmlRootElement
public class ServiceContract implements Serializable, BusinessEntity {

    private static final Long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long jobId;
    private Long billingAddressId;
    private Boolean additionalServiceUrgent = false;
    private Boolean additionalServiceFaxResults = false;
    private Boolean additionalServiceTelephonePresumptiveResults = false;
    private Boolean additionalServiceSendMoreContractForms = false;
    private Boolean additionalServiceOther = false;
    private String additionalServiceOtherText;
    private Integer estimatedTurnAroundTime;
    @Column(length = 1024)
    private String specialInstructions;
    private String submittedBy;
    private String receivedBy;
    private Boolean intendedMarketLocal = false;
    private Boolean intendedMarketCaricom = false;
    private Boolean intendedMarketUK = false;
    private Boolean intendedMarketUSA = false;
    private Boolean intendedMarketCanada = false;
    private Boolean intendedMarketOther = false;
    // requested services
    private String intendedMarketOtherText;
    private Boolean serviceRequestedTesting = false;
    private Boolean serviceRequestedCalibration = false;
    private Boolean serviceRequestedLabelEvaluation = false;
    private Boolean serviceRequestedInspection = false;
    private Boolean serviceRequestedConsultancy = false;
    private Boolean serviceRequestedTraining = false;
    private Boolean serviceRequestedOther = false;
    private String serviceRequestedOtherText;
    private String serviceRequestedDetails;
    private Boolean autoAddSampleInformation;

    public ServiceContract() {
    }
    
     public static ServiceContract create() {
        ServiceContract serviceContract = new ServiceContract();
        // init service contract
        serviceContract.setIntendedMarketLocal(true);
        serviceContract.setAutoAddSampleInformation(true);
        serviceContract.setAdditionalServiceUrgent(false);
        serviceContract.setAdditionalServiceFaxResults(false);
        serviceContract.setAdditionalServiceTelephonePresumptiveResults(false);
        serviceContract.setAdditionalServiceSendMoreContractForms(false);
        serviceContract.setAdditionalServiceOther(false);
        serviceContract.setAdditionalServiceOtherText("");
        serviceContract.setIntendedMarketLocal(false);
        serviceContract.setIntendedMarketCaricom(false);
        serviceContract.setIntendedMarketUK(false);
        serviceContract.setIntendedMarketUSA(false);
        serviceContract.setIntendedMarketCanada(false);
        serviceContract.setIntendedMarketOther(false);
        serviceContract.setIntendedMarketOtherText("");
        serviceContract.setServiceRequestedTesting(false);
        serviceContract.setServiceRequestedCalibration(false);
        serviceContract.setServiceRequestedLabelEvaluation(false);
        serviceContract.setServiceRequestedInspection(false);
        serviceContract.setServiceRequestedConsultancy(false);
        serviceContract.setServiceRequestedTraining(false);
        serviceContract.setServiceRequestedOther(false);
        serviceContract.setServiceRequestedOtherText("");
        serviceContract.setSpecialInstructions("");

        return serviceContract;
    }

    public String getServicesRequested() {
        String services = "";

        if (serviceRequestedCalibration) {
            services = services + "Calibration";
        }
        if (serviceRequestedTesting) {
            services = services + ", Testing";
        }
        if (serviceRequestedLabelEvaluation) {
            services = services + ", Label Evaluation";
        }
        if (serviceRequestedInspection) {
            services = services + ", Inspection";
        }
        if (serviceRequestedConsultancy) {
            services = services + ", Consultancy";
        }
        if (serviceRequestedTraining) {
            services = services + ", Training";
        }
        if (serviceRequestedOther) {
            services = services + ", " + serviceRequestedOtherText;
        }

        return services;
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
        return autoAddSampleInformation;
    }

    public void setAutoAddSampleInformation(Boolean autoAddSampleInformation) {
        this.autoAddSampleInformation = autoAddSampleInformation;
    }

    public String getServiceRequestedOtherText() {
        return serviceRequestedOtherText;
    }

    public void setServiceRequestedOtherText(String serviceRequestedOtherText) {
        this.serviceRequestedOtherText = serviceRequestedOtherText;
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

    public Boolean getServiceRequestedCalibration() {
        return serviceRequestedCalibration;
    }

    public void setServiceRequestedCalibration(Boolean serviceRequestedCalibration) {
        this.serviceRequestedCalibration = serviceRequestedCalibration;
    }

    public Boolean getServiceRequestedConsultancy() {
        return serviceRequestedConsultancy;
    }

    public void setServiceRequestedConsultancy(Boolean serviceRequestedConsultancy) {
        this.serviceRequestedConsultancy = serviceRequestedConsultancy;
    }

    public String getServiceRequestedDetails() {
        return serviceRequestedDetails;
    }

    public void setServiceRequestedDetails(String serviceRequestedDetails) {
        this.serviceRequestedDetails = serviceRequestedDetails;
    }

    public Boolean getServiceRequestedInspection() {
        return serviceRequestedInspection;
    }

    public void setServiceRequestedInspection(Boolean serviceRequestedInspection) {
        this.serviceRequestedInspection = serviceRequestedInspection;
    }

    public Boolean getServiceRequestedLabelEvaluation() {
        return serviceRequestedLabelEvaluation;
    }

    public void setServiceRequestedLabelEvaluation(Boolean serviceRequestedLabelEvaluation) {
        this.serviceRequestedLabelEvaluation = serviceRequestedLabelEvaluation;
    }

    public Boolean getServiceRequestedOther() {
        return serviceRequestedOther;
    }

    public void setServiceRequestedOther(Boolean serviceRequestedOther) {
        this.serviceRequestedOther = serviceRequestedOther;
    }

    public Boolean getServiceRequestedTesting() {
        return serviceRequestedTesting;
    }

    public void setServiceRequestedTesting(Boolean serviceRequestedTesting) {
        this.serviceRequestedTesting = serviceRequestedTesting;
    }

    public Boolean getServiceRequestedTraining() {
        return serviceRequestedTraining;
    }

    public void setServiceRequestedTraining(Boolean serviceRequestedTraining) {
        this.serviceRequestedTraining = serviceRequestedTraining;
    }

    public Boolean getAdditionalServiceFaxResults() {
        return additionalServiceFaxResults;
    }

    public void setAdditionalServiceFaxResults(Boolean additionalServiceFaxResults) {
        this.additionalServiceFaxResults = additionalServiceFaxResults;
    }

    public Boolean getAdditionalServiceOther() {
        return additionalServiceOther;
    }

    public void setAdditionalServiceOther(Boolean additionalServiceOther) {
        this.additionalServiceOther = additionalServiceOther;
    }

    public Boolean getAdditionalServiceSendMoreContractForms() {
        return additionalServiceSendMoreContractForms;
    }

    public void setAdditionalServiceSendMoreContractForms(Boolean additionalServiceSendMoreContractForms) {
        this.additionalServiceSendMoreContractForms = additionalServiceSendMoreContractForms;
    }

    public Boolean getAdditionalServiceTelephonePresumptiveResults() {
        return additionalServiceTelephonePresumptiveResults;
    }

    public void setAdditionalServiceTelephonePresumptiveResults(Boolean additionalServiceTelephonePresumptiveResults) {
        this.additionalServiceTelephonePresumptiveResults = additionalServiceTelephonePresumptiveResults;
    }

    public Boolean getAdditionalServiceUrgent() {
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
        return intendedMarketCanada;
    }

    public void setIntendedMarketCanada(Boolean intendedMarketCanada) {
        this.intendedMarketCanada = intendedMarketCanada;
    }

    public Boolean getIntendedMarketCaricom() {
        return intendedMarketCaricom;
    }

    public void setIntendedMarketCaricom(Boolean intendedMarketCaricom) {
        this.intendedMarketCaricom = intendedMarketCaricom;
    }

    public Boolean getIntendedMarketLocal() {
        return intendedMarketLocal;
    }

    public void setIntendedMarketLocal(Boolean intendedMarketLocal) {
        this.intendedMarketLocal = intendedMarketLocal;
    }

    public Boolean getIntendedMarketOther() {
        return intendedMarketOther;
    }

    public void setIntendedMarketOther(Boolean intendedMarketOther) {
        this.intendedMarketOther = intendedMarketOther;
    }

    public Boolean getIntendedMarketUK() {
        return intendedMarketUK;
    }

    public void setIntendedMarketUK(Boolean intendedMarketUK) {
        this.intendedMarketUK = intendedMarketUK;
    }

    public Boolean getIntendedMarketUSA() {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceContract)) {
            return false;
        }
        ServiceContract other = (ServiceContract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
