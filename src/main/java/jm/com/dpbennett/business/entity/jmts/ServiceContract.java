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
    // Requested services   
    // tk test commenting these out cause they may no longer be used
    // may have to look back at how the service contract is generated
    // because they may be used.
//    private Boolean serviceRequestedTesting;
//    private Boolean serviceRequestedCalibration;
//    private Boolean serviceRequestedLabelEvaluation;
//    private Boolean serviceRequestedInspection;
//    private Boolean serviceRequestedConsultancy;
//    private Boolean serviceRequestedTraining;
//    private Boolean serviceRequestedFoodInspectorate;
//    private Boolean serviceRequestedLegalMetrology;
//    private Boolean serviceRequestedSaleOfPublication;
//    private Boolean serviceRequestedStationeryOrPhotocopy;
//    private Boolean serviceRequestedCertification;
//    private Boolean serviceRequestedCertificationStandards;
//    private Boolean serviceRequestedDetentionRehabInspection;
//    private Boolean serviceRequestedFacilitiesManagement;
//    private Boolean serviceRequestedCementTesting;
//    private Boolean serviceRequestedPetrolSampling;
//    private Boolean serviceRequestedOther;
//    private String serviceRequestedOtherText;
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
    private Long billingAddressId;
    @Column(length = 1024)
    private String specialInstructions;
    private String submittedBy;
    private String receivedBy;
    private Integer estimatedTurnAroundTime;
    private Boolean autoAddSampleInformation;
    @Transient
    private Job job;

    public ServiceContract() {
        selectedService = new Service();
        // Requested services
//        serviceRequestedTesting = false;
//        serviceRequestedCalibration = false;
//        serviceRequestedLabelEvaluation = false;
//        serviceRequestedInspection = false;
//        serviceRequestedConsultancy = false;
//        serviceRequestedTraining = false;
//        serviceRequestedFoodInspectorate = false;
//        serviceRequestedLegalMetrology = false;
//        serviceRequestedSaleOfPublication = false;
//        serviceRequestedStationeryOrPhotocopy = false;
//        serviceRequestedCertification = false;
//        serviceRequestedCertificationStandards = false;
//        serviceRequestedDetentionRehabInspection = false;
//        serviceRequestedFacilitiesManagement = false;
//        serviceRequestedCementTesting = false;
//        serviceRequestedPetrolSampling = false;
//        serviceRequestedOther = false;
//        serviceRequestedOtherText = "";
//        serviceRequestedDetails = "";
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
        // Requested services
//        serviceRequestedTesting = src.serviceRequestedTesting;
//        serviceRequestedCalibration = src.serviceRequestedCalibration;
//        serviceRequestedLabelEvaluation = src.serviceRequestedLabelEvaluation;
//        serviceRequestedInspection = src.serviceRequestedInspection;
//        serviceRequestedConsultancy = src.serviceRequestedConsultancy;
//        serviceRequestedTraining = src.serviceRequestedTraining;
//        serviceRequestedFoodInspectorate = src.serviceRequestedFoodInspectorate;
//        serviceRequestedLegalMetrology = src.serviceRequestedLegalMetrology;
//        serviceRequestedSaleOfPublication = src.serviceRequestedSaleOfPublication;
//        serviceRequestedStationeryOrPhotocopy = src.serviceRequestedStationeryOrPhotocopy;
//        serviceRequestedCertification = src.serviceRequestedCertification;
//        serviceRequestedCertificationStandards = src.serviceRequestedCertificationStandards;
//        serviceRequestedDetentionRehabInspection = src.serviceRequestedDetentionRehabInspection;
//        serviceRequestedFacilitiesManagement = src.serviceRequestedFacilitiesManagement;
//        serviceRequestedCementTesting = src.serviceRequestedCementTesting;
//        serviceRequestedPetrolSampling = src.serviceRequestedPetrolSampling;
//        serviceRequestedOther = src.serviceRequestedOther;
//        serviceRequestedOtherText = src.serviceRequestedOtherText;
//        serviceRequestedDetails = src.serviceRequestedDetails;
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

//    public Boolean getServiceRequestedPetrolSampling() {
//        if (serviceRequestedPetrolSampling == null) {
//            serviceRequestedPetrolSampling = false;
//        }
//        return serviceRequestedPetrolSampling;
//    }

//    public void setServiceRequestedPetrolSampling(Boolean serviceRequestedPetrolSampling) {
//        this.serviceRequestedPetrolSampling = serviceRequestedPetrolSampling;
//    }
//
//    public Boolean getServiceRequestedCementTesting() {
//        if (serviceRequestedCementTesting == null) {
//            serviceRequestedCementTesting = false;
//        }
//        return serviceRequestedCementTesting;
//    }

//    public void setServiceRequestedCementTesting(Boolean serviceRequestedCementTesting) {
//        this.serviceRequestedCementTesting = serviceRequestedCementTesting;
//    }
//
//    public Boolean getServiceRequestedFacilitiesManagement() {
//        if (serviceRequestedFacilitiesManagement == null) {
//            serviceRequestedFacilitiesManagement = false;
//        }
//        return serviceRequestedFacilitiesManagement;
//    }

//    public void setServiceRequestedFacilitiesManagement(Boolean serviceRequestedFacilitiesManagement) {
//        this.serviceRequestedFacilitiesManagement = serviceRequestedFacilitiesManagement;
//    }
//
//    public Boolean getServiceRequestedDetentionRehabInspection() {
//        if (serviceRequestedDetentionRehabInspection == null) {
//            serviceRequestedDetentionRehabInspection = false;
//        }
//        return serviceRequestedDetentionRehabInspection;
//    }

//    public void setServiceRequestedDetentionRehabInspection(Boolean serviceRequestedDetentionRehabInspection) {
//        this.serviceRequestedDetentionRehabInspection = serviceRequestedDetentionRehabInspection;
//    }
//
//    public Boolean getServiceRequestedCertification() {
//        if (serviceRequestedCertification == null) {
//            serviceRequestedCertification = false;
//        }
//        return serviceRequestedCertification;
//    }

//    public void setServiceRequestedCertification(Boolean serviceRequestedCertification) {
//        this.serviceRequestedCertification = serviceRequestedCertification;
//    }
//
//    public Boolean getServiceRequestedCertificationStandards() {
//        if (serviceRequestedCertificationStandards == null) {
//            serviceRequestedCertificationStandards = false;
//        }
//        return serviceRequestedCertificationStandards;
//    }

//    public void setServiceRequestedCertificationStandards(Boolean serviceRequestedCertificationStandards) {
//        this.serviceRequestedCertificationStandards = serviceRequestedCertificationStandards;
//    }
//
//    public Boolean getServiceRequestedStationeryOrPhotocopy() {
//        if (serviceRequestedStationeryOrPhotocopy == null) {
//            serviceRequestedStationeryOrPhotocopy = false;
//        }
//        return serviceRequestedStationeryOrPhotocopy;
//    }

//    public void setServiceRequestedStationeryOrPhotocopy(Boolean serviceRequestedStationeryOrPhotocopy) {
//        this.serviceRequestedStationeryOrPhotocopy = serviceRequestedStationeryOrPhotocopy;
//    }
//
//    public Boolean getServiceRequestedSaleOfPublication() {
//        if (serviceRequestedSaleOfPublication == null) {
//            serviceRequestedSaleOfPublication = false;
//        }
//        return serviceRequestedSaleOfPublication;
//    }

//    public void setServiceRequestedSaleOfPublication(Boolean serviceRequestedSaleOfPublication) {
//        this.serviceRequestedSaleOfPublication = serviceRequestedSaleOfPublication;
//    }
//
//    public Boolean getServiceRequestedLegalMetrology() {
//        if (serviceRequestedLegalMetrology == null) {
//            serviceRequestedLegalMetrology = false;
//        }
//        return serviceRequestedLegalMetrology;
//    }

//    public void setServiceRequestedLegalMetrology(Boolean serviceRequestedLegalMetrology) {
//        this.serviceRequestedLegalMetrology = serviceRequestedLegalMetrology;
//    }
//
//    public Boolean getServiceRequestedFoodInspectorate() {
//        if (serviceRequestedFoodInspectorate == null) {
//            serviceRequestedFoodInspectorate = false;
//        }
//        return serviceRequestedFoodInspectorate;
//    }

//    public void setServiceRequestedFoodInspectorate(Boolean serviceRequestedFoodInspectorate) {
//        this.serviceRequestedFoodInspectorate = serviceRequestedFoodInspectorate;
//    }

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

//    public String getServiceRequestedOtherText() {
//        return serviceRequestedOtherText;
//    }
//
//    public void setServiceRequestedOtherText(String serviceRequestedOtherText) {
//        this.serviceRequestedOtherText = serviceRequestedOtherText;
//    }

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

//    public Boolean getServiceRequestedCalibration() {
//        if (serviceRequestedCalibration == null) {
//            serviceRequestedCalibration = false;
//        }
//        return serviceRequestedCalibration;
//    }
//
//    public void setServiceRequestedCalibration(Boolean serviceRequestedCalibration) {
//        this.serviceRequestedCalibration = serviceRequestedCalibration;
//    }

//    public Boolean getServiceRequestedConsultancy() {
//        if (serviceRequestedConsultancy == null) {
//            serviceRequestedConsultancy = false;
//        }
//        return serviceRequestedConsultancy;
//    }
//
//    public void setServiceRequestedConsultancy(Boolean serviceRequestedConsultancy) {
//        this.serviceRequestedConsultancy = serviceRequestedConsultancy;
//    }

    public String getServiceRequestedDetails() {
        return serviceRequestedDetails;
    }

    public void setServiceRequestedDetails(String serviceRequestedDetails) {
        this.serviceRequestedDetails = serviceRequestedDetails;
    }

//    public Boolean getServiceRequestedInspection() {
//        if (serviceRequestedInspection == null) {
//            serviceRequestedInspection = false;
//        }
//        return serviceRequestedInspection;
//    }
//
//    public void setServiceRequestedInspection(Boolean serviceRequestedInspection) {
//        this.serviceRequestedInspection = serviceRequestedInspection;
//    }

//    public Boolean getServiceRequestedLabelEvaluation() {
//        if (serviceRequestedLabelEvaluation == null) {
//            serviceRequestedLabelEvaluation = false;
//        }
//        return serviceRequestedLabelEvaluation;
//    }
//
//    public void setServiceRequestedLabelEvaluation(Boolean serviceRequestedLabelEvaluation) {
//        this.serviceRequestedLabelEvaluation = serviceRequestedLabelEvaluation;
//    }

//    public Boolean getServiceRequestedOther() {
//        if (serviceRequestedOther == null) {
//            serviceRequestedOther = false;
//        }
//        return serviceRequestedOther;
//    }
//
//    public void setServiceRequestedOther(Boolean serviceRequestedOther) {
//        this.serviceRequestedOther = serviceRequestedOther;
//    }

//    public Boolean getServiceRequestedTesting() {
//        if (serviceRequestedTesting == null) {
//            serviceRequestedTesting = false;
//        }
//        return serviceRequestedTesting;
//    }
//
//    public void setServiceRequestedTesting(Boolean serviceRequestedTesting) {
//        this.serviceRequestedTesting = serviceRequestedTesting;
//    }

//    public Boolean getServiceRequestedTraining() {
//        if (serviceRequestedTraining == null) {
//            serviceRequestedTraining = false;
//        }
//        return serviceRequestedTraining;
//    }
//
//    public void setServiceRequestedTraining(Boolean serviceRequestedTraining) {
//        this.serviceRequestedTraining = serviceRequestedTraining;
//    }

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

            if (getSelectedService().getId() != null) {
                getSelectedService().save(em);
            }
            
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
}
