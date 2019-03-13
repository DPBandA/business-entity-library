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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "servicecontract")
@NamedQueries({
    @NamedQuery(name = "findAllServiceContracts", query = "SELECT s FROM ServiceContract s ORDER BY s.id")
    ,
    @NamedQuery(name = "findByJobId", query = "SELECT s FROM ServiceContract s WHERE s.jobId = :jobId")
})
@XmlRootElement
public class ServiceContract implements Serializable, BusinessEntity {

    private static final Long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long jobId; // tk needed?
    // Requested services    
    private Boolean serviceRequestedTesting;
    private Boolean serviceRequestedCalibration;
    private Boolean serviceRequestedLabelEvaluation;
    private Boolean serviceRequestedInspection;
    private Boolean serviceRequestedConsultancy;
    private Boolean serviceRequestedTraining;
    private Boolean serviceRequestedFoodInspectorate;
    private Boolean serviceRequestedLegalMetrology;
    private Boolean serviceRequestedSaleOfPublication;
    private Boolean serviceRequestedStationeryOrPhotocopy;
    private Boolean serviceRequestedCertification;
    private Boolean serviceRequestedCertificationStandards;
    private Boolean serviceRequestedDetentionRehabInspection;
    private Boolean serviceRequestedFacilitiesManagement;
    private Boolean serviceRequestedCementTesting;
    private Boolean serviceRequestedPetrolSampling;
    private Boolean serviceRequestedOther;
    private String serviceRequestedOtherText;
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
    @Transient
    private Boolean isDirty;
    private Long billingAddressId; // tk needed?
    @Column(length = 1024)
    private String specialInstructions;
    private String submittedBy;
    private String receivedBy;
    private Integer estimatedTurnAroundTime;
    private Boolean autoAddSampleInformation; // tk needed? How is it used?

    public ServiceContract() {
        // Requested services
        serviceRequestedTesting = false;
        serviceRequestedCalibration = false;
        serviceRequestedLabelEvaluation = false;
        serviceRequestedInspection = false;
        serviceRequestedConsultancy = false;
        serviceRequestedTraining = false;
        serviceRequestedFoodInspectorate = false;
        serviceRequestedLegalMetrology = false;
        serviceRequestedSaleOfPublication = false;
        serviceRequestedStationeryOrPhotocopy = false;
        serviceRequestedCertification = false;
        serviceRequestedCertificationStandards = false;
        serviceRequestedDetentionRehabInspection = false;
        serviceRequestedFacilitiesManagement = false;
        serviceRequestedCementTesting = false;
        serviceRequestedPetrolSampling = false;
        serviceRequestedOther = false;
        serviceRequestedOtherText = "";
        serviceRequestedDetails = "";
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

    public Boolean getServiceRequestedPetrolSampling() {
        return serviceRequestedPetrolSampling;
    }

    public void setServiceRequestedPetrolSampling(Boolean serviceRequestedPetrolSampling) {
        this.serviceRequestedPetrolSampling = serviceRequestedPetrolSampling;
    }

    public Boolean getServiceRequestedCementTesting() {
        return serviceRequestedCementTesting;
    }

    public void setServiceRequestedCementTesting(Boolean serviceRequestedCementTesting) {
        this.serviceRequestedCementTesting = serviceRequestedCementTesting;
    }

    public Boolean getServiceRequestedFacilitiesManagement() {
        return serviceRequestedFacilitiesManagement;
    }

    public void setServiceRequestedFacilitiesManagement(Boolean serviceRequestedFacilitiesManagement) {
        this.serviceRequestedFacilitiesManagement = serviceRequestedFacilitiesManagement;
    }

    public Boolean getServiceRequestedDetentionRehabInspection() {
        return serviceRequestedDetentionRehabInspection;
    }

    public void setServiceRequestedDetentionRehabInspection(Boolean serviceRequestedDetentionRehabInspection) {
        this.serviceRequestedDetentionRehabInspection = serviceRequestedDetentionRehabInspection;
    }

    public Boolean getServiceRequestedCertification() {
        return serviceRequestedCertification;
    }

    public void setServiceRequestedCertification(Boolean serviceRequestedCertification) {
        this.serviceRequestedCertification = serviceRequestedCertification;
    }

    public Boolean getServiceRequestedCertificationStandards() {
        return serviceRequestedCertificationStandards;
    }

    public void setServiceRequestedCertificationStandards(Boolean serviceRequestedCertificationStandards) {
        this.serviceRequestedCertificationStandards = serviceRequestedCertificationStandards;
    }

    public Boolean getServiceRequestedStationeryOrPhotocopy() {
        return serviceRequestedStationeryOrPhotocopy;
    }

    public void setServiceRequestedStationeryOrPhotocopy(Boolean serviceRequestedStationeryOrPhotocopy) {
        this.serviceRequestedStationeryOrPhotocopy = serviceRequestedStationeryOrPhotocopy;
    }

    public Boolean getServiceRequestedSaleOfPublication() {
        return serviceRequestedSaleOfPublication;
    }

    public void setServiceRequestedSaleOfPublication(Boolean serviceRequestedSaleOfPublication) {
        this.serviceRequestedSaleOfPublication = serviceRequestedSaleOfPublication;
    }

    public Boolean getServiceRequestedLegalMetrology() {
        return serviceRequestedLegalMetrology;
    }

    public void setServiceRequestedLegalMetrology(Boolean serviceRequestedLegalMetrology) {
        this.serviceRequestedLegalMetrology = serviceRequestedLegalMetrology;
    }

    public Boolean getServiceRequestedFoodInspectorate() {
        return serviceRequestedFoodInspectorate;
    }

    public void setServiceRequestedFoodInspectorate(Boolean serviceRequestedFoodInspectorate) {
        this.serviceRequestedFoodInspectorate = serviceRequestedFoodInspectorate;
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

    public static ServiceContract create() {
        ServiceContract serviceContract = new ServiceContract();

        return serviceContract;
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
    public ReturnMessage save(EntityManager em) {
         try {
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
}
