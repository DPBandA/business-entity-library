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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ComplianceSurveySearchResult;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "complianceSurvey")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ComplianceSurvey
        extends ComplianceSurveySearchResult
        implements
        Serializable,
        BusinessEntity,
        Form,
        GeneralComplianceSurvey {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private String name = "";
    private String surveyType = "";
    private String surveyLocationType = "";
    private String typeOfEstablishment = "";
    @OneToOne(cascade = CascadeType.ALL)
    private Client retailOutlet = null;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact retailRepresentative = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfSurvey = null;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date surveyStartTime = null;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date surveyEndTime = null;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductInspection> productInspections = null;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee inspector = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSigned = null;
    @Column(length = 1024)
    private String comments = "";
    private String typeOfPortOfEntry = "";
    private String portOfEntry = "";
    private String inspectionPoint = "";
    @OneToOne(cascade = CascadeType.ALL)
    private Address inspectionAddress = null;
    @OneToOne(cascade = CascadeType.ALL)
    private Client broker = null;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact brokerRepresentative = null;
    @OneToOne(cascade = CascadeType.ALL)
    private Client consignee = null;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact consigneeRepresentative = null;
    private String referenceNumber = "";
    @Column(length = 1024)
    private String reasonForDetention = "";
    @OneToOne(cascade = CascadeType.ALL)
    private Address specifiedReleaseLocation = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseRequestReportDate = null;
    // notice of refease from detention
    @OneToOne(cascade = CascadeType.ALL)
    private Address specifiedReleaseLocationDomesticMarket = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseDateDomesticMarket = null;
    @OneToOne(cascade = CascadeType.ALL)
    private Address locationOfDetainedProductDomesticMarket = null;
    private Boolean retailer = null;
    private Boolean distributor = null;
    private Boolean otherCompanyTypes = null;
    private String companyTypes = "";
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseFromDetentionReportDate = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfDetention = null;
    private Boolean fullRelease = null;
    private Boolean samplesToBeDisposed = null;
    private Boolean samplesToBeCollected = null;
    private Boolean requestForDetentionIssuedForPortOfEntry = null;
    private Boolean requestForSampleIssuedForPortOfEntry;
    private Boolean requestForReleaseIssuedForPortOfEntry;
    private Boolean noticeOfDetentionIssuedForDomesticMarket;
    private Boolean noticeOfReleaseFromDetentionIssuedForDomesticMarket;
    private String portOfEntryDetentionNumber;
    private String domesticMarketDetentionNumber;
    // Signatures, dates and names
    // Detention Request - Port of Entry
    @OneToOne(cascade = CascadeType.ALL)
    private Signature authSigForDetentionRequestPOE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date authSigDateForDetentionRequestPOE;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee authEmployeeForDetentionRequestPOE;
    // Sample Request - Port of Entry
    @OneToOne(cascade = CascadeType.ALL)
    private Signature inspectorSigForSampleRequestPOE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inspectorSigDateForSampleRequestPOE;
    // Release Request - Port of Entry
    @OneToOne(cascade = CascadeType.ALL)
    private Signature preparedBySigForReleaseRequestPOE;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee preparedByEmployeeForReleaseRequestPOE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date preparedBySigDateForReleaseRequestPOE;
    @OneToOne(cascade = CascadeType.ALL)
    private Signature approvedBySigForReleaseRequestPOE;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee approvedByEmployeeForReleaseRequestPOE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvedBySigDateForReleaseRequestPOE;
    // Notice of Detention - Domestic Market
    @OneToOne(cascade = CascadeType.ALL)
    private Signature authSigForNoticeOfDentionDM;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date authSigDateForNoticeOfDentionDM;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee authEmployeeForNoticeOfDentionDM;
    // Notice of Release from Detention - Domestic Market
    @OneToOne(cascade = CascadeType.ALL)
    private Signature authSigForNoticeOfReleaseFromDentionDM;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date authSigDateForNoticeOfReleaseFromDentionDM;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee authEmpForNoticeOfReleaseFromDentionDM;
    // End signatures    
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    @OneToOne(cascade = CascadeType.ALL)
    private EntryDocumentInspection entryDocumentInspection;
    private String jobNumber;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<DocumentStandard> standardsBreached = null;
    private String otherInspectionLocation;

    public ComplianceSurvey() {
    }

    public ComplianceSurvey(GeneralComplianceSurvey generalComplianceSurvey, Long id) {
        this.id = id;
        this.surveyType = generalComplianceSurvey.getSurveyType();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getOtherInspectionLocation() {
        if (otherInspectionLocation == null) {
            otherInspectionLocation = "";
        }
        return otherInspectionLocation;
    }

    public void setOtherInspectionLocation(String otherInspectionLocation) {
        this.otherInspectionLocation = otherInspectionLocation;
    }

    public String getJobNumber() {
        if (jobNumber == null) {
            jobNumber = "";
        }
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public EntryDocumentInspection getEntryDocumentInspection() {
        if (entryDocumentInspection == null) {
            entryDocumentInspection = new EntryDocumentInspection();
        }
        return entryDocumentInspection;
    }

    public void setEntryDocumentInspection(EntryDocumentInspection entryDocumentInspection) {
        this.entryDocumentInspection = entryDocumentInspection;
    }

    public String getTypeOfPortOfEntry() {
        if (typeOfPortOfEntry == null) {
            typeOfPortOfEntry = "Seaport";
        }
        return typeOfPortOfEntry;
    }

    public void setTypeOfPortOfEntry(String typeOfPortOfEntry) {
        this.typeOfPortOfEntry = typeOfPortOfEntry;
    }

    public String getTypeOfEstablishment() {
        return typeOfEstablishment;
    }

    public void setTypeOfEstablishment(String typeOfEstablishment) {
        this.typeOfEstablishment = typeOfEstablishment;
    }

    @Override
    public String getSurveyType() {
        if (surveyType == null) {
            surveyType = "";
        }
        return surveyType;
    }

    @Override
    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public Date getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
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

    public Signature getApprovedBySigForReleaseRequestPOE() {
        if (approvedBySigForReleaseRequestPOE == null) {
            return new Signature();
        }
        return approvedBySigForReleaseRequestPOE;
    }

    public void setApprovedBySigForReleaseRequestPOE(Signature approvedBySigForReleaseRequestPOE) {
        this.approvedBySigForReleaseRequestPOE = approvedBySigForReleaseRequestPOE;
    }

    public Employee getApprovedByEmployeeForReleaseRequestPOE() {
        if (approvedByEmployeeForReleaseRequestPOE == null) {
            return new Employee();
        }
        return approvedByEmployeeForReleaseRequestPOE;
    }

    public void setApprovedByEmployeeForReleaseRequestPOE(Employee approvedByEmployeeForReleaseRequestPOE) {
        this.approvedByEmployeeForReleaseRequestPOE = approvedByEmployeeForReleaseRequestPOE;
    }

    public Date getApprovedBySigDateForReleaseRequestPOE() {
        return approvedBySigDateForReleaseRequestPOE;
    }

    public void setApprovedBySigDateForReleaseRequestPOE(Date approvedBySigDateForReleaseRequestPOE) {
        this.approvedBySigDateForReleaseRequestPOE = approvedBySigDateForReleaseRequestPOE;
    }

    public Signature getAuthSigForDetentionRequestPOE() {
        if (authSigForDetentionRequestPOE == null) {
            return new Signature();
        }
        return authSigForDetentionRequestPOE;
    }

    public void setAuthSigForDetentionRequestPOE(Signature authSigForDetentionRequestPOE) {
        this.authSigForDetentionRequestPOE = authSigForDetentionRequestPOE;
    }

    public Date getAuthSigDateForDetentionRequestPOE() {
        return authSigDateForDetentionRequestPOE;
    }

    public void setAuthSigDateForDetentionRequestPOE(Date authSigDateForDetentionRequestPOE) {
        this.authSigDateForDetentionRequestPOE = authSigDateForDetentionRequestPOE;
    }

    public Employee getAuthEmployeeForDetentionRequestPOE() {
        if (authEmployeeForDetentionRequestPOE == null) {
            return new Employee();
        }
        return authEmployeeForDetentionRequestPOE;
    }

    public void setAuthEmployeeForDetentionRequestPOE(Employee authEmployeeForDetentionRequestPOE) {
        this.authEmployeeForDetentionRequestPOE = authEmployeeForDetentionRequestPOE;
    }

    public Signature getInspectorSigForSampleRequestPOE() {
        if (inspectorSigForSampleRequestPOE == null) {
            return new Signature();
        }
        return inspectorSigForSampleRequestPOE;
    }

    public void setInspectorSigForSampleRequestPOE(Signature inspectorSigForSampleRequestPOE) {
        this.inspectorSigForSampleRequestPOE = inspectorSigForSampleRequestPOE;
    }

    public Date getInspectorSigDateForSampleRequestPOE() {
        return inspectorSigDateForSampleRequestPOE;
    }

    public void setInspectorSigDateForSampleRequestPOE(Date inspectorSigDateForSampleRequestPOE) {
        this.inspectorSigDateForSampleRequestPOE = inspectorSigDateForSampleRequestPOE;
    }

    public Signature getPreparedBySigForReleaseRequestPOE() {
        if (preparedBySigForReleaseRequestPOE == null) {
            return new Signature();
        }
        return preparedBySigForReleaseRequestPOE;
    }

    public void setPreparedBySigForReleaseRequestPOE(Signature preparedBySigForReleaseRequestPOE) {
        this.preparedBySigForReleaseRequestPOE = preparedBySigForReleaseRequestPOE;
    }

    public Employee getPreparedByEmployeeForReleaseRequestPOE() {
        if (preparedByEmployeeForReleaseRequestPOE == null) {
            return new Employee();
        }
        return preparedByEmployeeForReleaseRequestPOE;
    }

    public void setPreparedByEmployeeForReleaseRequestPOE(Employee preparedByEmployeeForReleaseRequestPOE) {
        this.preparedByEmployeeForReleaseRequestPOE = preparedByEmployeeForReleaseRequestPOE;
    }

    public Date getPreparedBySigDateForReleaseRequestPOE() {
        return preparedBySigDateForReleaseRequestPOE;
    }

    public void setPreparedBySigDateForReleaseRequestPOE(Date preparedBySigDateForReleaseRequestPOE) {
        this.preparedBySigDateForReleaseRequestPOE = preparedBySigDateForReleaseRequestPOE;
    }

    public Signature getAuthSigForNoticeOfReleaseFromDentionDM() {
        if (authSigForNoticeOfReleaseFromDentionDM == null) {
            return new Signature();
        }
        return authSigForNoticeOfReleaseFromDentionDM;
    }

    public void setAuthSigForNoticeOfReleaseFromDentionDM(Signature authSigForNoticeOfReleaseFromDentionDM) {
        this.authSigForNoticeOfReleaseFromDentionDM = authSigForNoticeOfReleaseFromDentionDM;
    }

    public Date getAuthSigDateForNoticeOfReleaseFromDentionDM() {
        return authSigDateForNoticeOfReleaseFromDentionDM;
    }

    public void setAuthSigDateForNoticeOfReleaseFromDentionDM(Date authSigDateForNoticeOfReleaseFromDentionDM) {
        this.authSigDateForNoticeOfReleaseFromDentionDM = authSigDateForNoticeOfReleaseFromDentionDM;
    }

    public Employee getAuthEmpForNoticeOfReleaseFromDentionDM() {
        if (authEmpForNoticeOfReleaseFromDentionDM == null) {
            return new Employee();
        }
        return authEmpForNoticeOfReleaseFromDentionDM;
    }

    public void setAuthEmpForNoticeOfReleaseFromDentionDM(Employee authEmpForNoticeOfReleaseFromDentionDM) {
        this.authEmpForNoticeOfReleaseFromDentionDM = authEmpForNoticeOfReleaseFromDentionDM;
    }

    public Signature getAuthSigForNoticeOfDentionDM() {
        if (authSigForNoticeOfDentionDM == null) {
            return new Signature();
        }
        return authSigForNoticeOfDentionDM;
    }

    public void setAuthSigForNoticeOfDentionDM(Signature authSigForNoticeOfDentionDM) {
        this.authSigForNoticeOfDentionDM = authSigForNoticeOfDentionDM;
    }

    public Date getAuthSigDateForNoticeOfDentionDM() {
        return authSigDateForNoticeOfDentionDM;
    }

    public void setAuthSigDateForNoticeOfDentionDM(Date authSigDateForNoticeOfDentionDM) {
        this.authSigDateForNoticeOfDentionDM = authSigDateForNoticeOfDentionDM;
    }

    public Employee getAuthEmployeeForNoticeOfDentionDM() {
        if (authEmployeeForNoticeOfDentionDM == null) {
            return new Employee();
        }
        return authEmployeeForNoticeOfDentionDM;
    }

    public void setAuthEmployeeForNoticeOfDentionDM(Employee authEmployeeForNoticeOfDentionDM) {
        this.authEmployeeForNoticeOfDentionDM = authEmployeeForNoticeOfDentionDM;
    }

    public String getPortOfEntryDetentionNumber() {
        return portOfEntryDetentionNumber;
    }

    public void setPortOfEntryDetentionNumber(String portOfEntryDetentionNumber) {
        this.portOfEntryDetentionNumber = portOfEntryDetentionNumber;
    }

    public String getDomesticMarketDetentionNumber() {
        return domesticMarketDetentionNumber;
    }

    public void setDomesticMarketDetentionNumber(String domesticMarketDetentionNumber) {
        this.domesticMarketDetentionNumber = domesticMarketDetentionNumber;
    }

    public Boolean getRequestForSampleIssuedForPortOfEntry() {
        return requestForSampleIssuedForPortOfEntry;
    }

    public void setRequestForSampleIssuedForPortOfEntry(Boolean requestForSampleIssuedForPortOfEntry) {
        this.requestForSampleIssuedForPortOfEntry = requestForSampleIssuedForPortOfEntry;
    }

    public Boolean getRequestForReleaseIssuedForPortOfEntry() {
        return requestForReleaseIssuedForPortOfEntry;
    }

    public void setRequestForReleaseIssuedForPortOfEntry(Boolean requestForReleaseIssuedForPortOfEntry) {
        this.requestForReleaseIssuedForPortOfEntry = requestForReleaseIssuedForPortOfEntry;
    }

    public Boolean getNoticeOfReleaseFromDetentionIssuedForDomesticMarket() {
        return noticeOfReleaseFromDetentionIssuedForDomesticMarket;
    }

    public void setNoticeOfReleaseFromDetentionIssuedForDomesticMarket(Boolean noticeOfReleaseFromDetentionIssuedForDomesticMarket) {
        this.noticeOfReleaseFromDetentionIssuedForDomesticMarket = noticeOfReleaseFromDetentionIssuedForDomesticMarket;
    }

    public Boolean getRequestForDetentionIssuedForPortOfEntry() {
        return requestForDetentionIssuedForPortOfEntry;
    }

    public void setRequestForDetentionIssuedForPortOfEntry(Boolean requestForDetentionIssuedForPortOfEntry) {
        this.requestForDetentionIssuedForPortOfEntry = requestForDetentionIssuedForPortOfEntry;
    }

    public Boolean getNoticeOfDetentionIssuedForDomesticMarket() {
        return noticeOfDetentionIssuedForDomesticMarket;
    }

    public void setNoticeOfDetentionIssuedForDomesticMarket(Boolean noticeOfDetentionIssuedForDomesticMarket) {
        this.noticeOfDetentionIssuedForDomesticMarket = noticeOfDetentionIssuedForDomesticMarket;
    }

    public Address getInspectionAddress() {
        if (inspectionAddress == null) {
            inspectionAddress = new Address("");
        }
        return inspectionAddress;
    }

    public void setInspectionAddress(Address inspectionAddress) {
        this.inspectionAddress = inspectionAddress;
    }

    public Boolean getSamplesToBeDisposed() {
        return samplesToBeDisposed;
    }

    public void setSamplesToBeDisposed(Boolean samplesToBeDisposed) {
        this.samplesToBeDisposed = samplesToBeDisposed;
    }

    public Boolean getSamplesToBeCollected() {
        return samplesToBeCollected;
    }

    public void setSamplesToBeCollected(Boolean samplesToBeCollected) {
        this.samplesToBeCollected = samplesToBeCollected;
    }

    public Boolean getFullRelease() {
        if (fullRelease == null) {
            fullRelease = false;
        }
        return fullRelease;
    }

    public void setFullRelease(Boolean fullRelease) {
        this.fullRelease = fullRelease;
    }

    public Date getDateOfDetention() {
        return dateOfDetention;
    }

    public void setDateOfDetention(Date dateOfDetention) {
        this.dateOfDetention = dateOfDetention;
    }

    public Date getReleaseFromDetentionReportDate() {
        return releaseFromDetentionReportDate;
    }

    public void setReleaseFromDetentionReportDate(Date releaseFromDetentionReportDate) {
        this.releaseFromDetentionReportDate = releaseFromDetentionReportDate;
    }

    public Boolean getOtherCompanyTypes() {
        if (otherCompanyTypes == null) {
            otherCompanyTypes = false;
        }
        return otherCompanyTypes;
    }

    public void setOtherCompanyTypes(Boolean otherCompanyTypes) {
        this.otherCompanyTypes = otherCompanyTypes;
    }

    public String getCompanyTypes() {
        return companyTypes;
    }

    public void setCompanyTypes(String companyTypes) {
        this.companyTypes = companyTypes;
    }

    public Boolean getRetailer() {
        if (retailer == null) {
            retailer = false;
        }
        return retailer;
    }

    public void setRetailer(Boolean retailer) {
        this.retailer = retailer;
    }

    public Boolean getDistributor() {
        if (distributor == null) {
            distributor = false;
        }
        return distributor;
    }

    public void setDistributor(Boolean distributor) {
        this.distributor = distributor;
    }

    public Address getLocationOfDetainedProductDomesticMarket() {
        if (locationOfDetainedProductDomesticMarket == null) {
            locationOfDetainedProductDomesticMarket = new Address("");
        }
        return locationOfDetainedProductDomesticMarket;
    }

    public void setLocationOfDetainedProductDomesticMarket(Address locationOfDetainedProductDomesticMarket) {
        this.locationOfDetainedProductDomesticMarket = locationOfDetainedProductDomesticMarket;
    }

    public Address getSpecifiedReleaseLocationDomesticMarket() {
        if (specifiedReleaseLocationDomesticMarket == null) {
            specifiedReleaseLocationDomesticMarket = new Address("");
        }
        return specifiedReleaseLocationDomesticMarket;
    }

    public void setSpecifiedReleaseLocationDomesticMarket(Address specifiedReleaseLocationDomesticMarket) {
        this.specifiedReleaseLocationDomesticMarket = specifiedReleaseLocationDomesticMarket;
    }

    public Date getReleaseDateDomesticMarket() {
        return releaseDateDomesticMarket;
    }

    public void setReleaseDateDomesticMarket(Date releaseDateDomesticMarket) {
        this.releaseDateDomesticMarket = releaseDateDomesticMarket;
    }

    public Date getReleaseRequestReportDate() {
        return releaseRequestReportDate;
    }

    public void setReleaseRequestReportDate(Date releaseRequestReportDate) {
        this.releaseRequestReportDate = releaseRequestReportDate;
    }

    public String getInspectionPoint() {
        if (inspectionPoint == null) {
            inspectionPoint = "";
        }
        return inspectionPoint;
    }

    public void setInspectionPoint(String inspectionPoint) {
        this.inspectionPoint = inspectionPoint;
    }

    public Client getBroker() {
        if (broker == null) {
            broker = new Client("", false);
        }
        return broker;
    }

    public void setBroker(Client broker) {
        this.broker = broker;
    }

    public Contact getBrokerRepresentative() {
        if (brokerRepresentative == null) {
            brokerRepresentative = new Contact();
        }
        return brokerRepresentative;
    }

    public void setBrokerRepresentative(Contact brokerRepresentative) {
        this.brokerRepresentative = brokerRepresentative;
    }

    public Client getConsignee() {
        if (consignee == null) {
            consignee = new Client("", false);
        }
        return consignee;
    }

    public void setConsignee(Client consignee) {
        this.consignee = consignee;
    }

    public Address getSpecifiedReleaseLocation() {
        if (specifiedReleaseLocation == null) {
            specifiedReleaseLocation = new Address("");
        }
        return specifiedReleaseLocation;
    }

    public void setSpecifiedReleaseLocation(Address specifiedReleaseLocation) {
        this.specifiedReleaseLocation = specifiedReleaseLocation;
    }

    public Contact getConsigneeRepresentative() {
        if (consigneeRepresentative == null) {
            // try to first contact from the consignee
            // or use default
//            if (!getConsignee().getContacts().isEmpty()) {
//                consigneeRepresentative = getConsignee().getContacts().get(0);
//            } else {
            consigneeRepresentative = new Contact();
//            }

        }
        return consigneeRepresentative;
    }

    public void setConsigneeRepresentative(Contact consigneeRepresentative) {
        this.consigneeRepresentative = consigneeRepresentative;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getReasonForDetention() {
        if (reasonForDetention == null) {
            reasonForDetention = "";
        }
        return reasonForDetention;
    }

    public void setReasonForDetention(String reasonForDetention) {
        this.reasonForDetention = reasonForDetention;
    }

    public String getSurveyLocationType() {
        if (surveyLocationType == null) {
            surveyLocationType = "Port of Entry";
        }
        return surveyLocationType;
    }

    public void setSurveyLocationType(String surveyLocationType) {
        this.surveyLocationType = surveyLocationType;
    }

    public String getPortOfEntry() {
        if (portOfEntry == null) {
            portOfEntry = "";
        }
        return portOfEntry;
    }

    public void setPortOfEntry(String portOfEntry) {
        this.portOfEntry = portOfEntry;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public Date getDateOfSurvey() {
        return dateOfSurvey;
    }

    @Override
    public void setDateOfSurvey(Date dateOfSurvey) {
        this.dateOfSurvey = dateOfSurvey;
    }

    public Date getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(Date dateSigned) {
        this.dateSigned = dateSigned;
    }

    @Override
    public Employee getInspector() {
        if (inspector == null) {
            return new Employee();
        }
        return inspector;
    }

    @Override
    public void setInspector(Employee inspector) {
        this.inspector = inspector;
    }

    @XmlTransient
    public List<ProductInspection> getProductInspections() {
        if (productInspections != null) {
            Collections.sort(productInspections); 
        } else {
            productInspections = new ArrayList<>();
        }

        return productInspections;
    }

    public void setProductInspections(List<ProductInspection> productInspections) {
        this.productInspections = productInspections;
    }

    @XmlTransient
    public List<DocumentStandard> getStandardsBreached() {
        if (standardsBreached != null) {
            Collections.sort(standardsBreached);
        } else {
            standardsBreached = new ArrayList<>();
        }

        return standardsBreached;
    }

    public void setStandardsBreached(List<DocumentStandard> standardsBreached) {
        this.standardsBreached = standardsBreached;
    }

    public Client getRetailOutlet() {
        if (retailOutlet == null) {
            retailOutlet = new Client("", false);
        }
        return retailOutlet;
    }

    public void setRetailOutlet(Client retailOutlet) {
        this.retailOutlet = retailOutlet;
    }

    public Contact getRetailRepresentative() {
        if (retailRepresentative == null) {
            // try to first contact from the retail outlet
            // or use default
            if (!getRetailOutlet().getContacts().isEmpty()) {
                retailRepresentative = getRetailOutlet().getContacts().get(0);
            } else {
                retailRepresentative = new Contact();
            }

        }
        return retailRepresentative;
    }

    public void setRetailRepresentative(Contact retailRepresentative) {
        this.retailRepresentative = retailRepresentative;
    }

    public Date getSurveyEndTime() {
        return surveyEndTime;
    }

    public void setSurveyEndTime(Date surveyEndTime) {
        this.surveyEndTime = surveyEndTime;
    }

    public Date getSurveyStartTime() {
        return surveyStartTime;
    }

    public void setSurveyStartTime(Date surveyStartTime) {
        this.surveyStartTime = surveyStartTime;
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
        if (!(object instanceof ComplianceSurvey)) {
            return false;
        }
        ComplianceSurvey other = (ComplianceSurvey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Boolean getComplianceSurveyTypeIsValid() {
        if (!getSurveyType().trim().equals("")) {
            return true;
        }

        return false;
    }

    public Boolean getComplianceSurveyTypeIsMarketSurvey() {
        if (getSurveyType().trim().equals("Market Survey")) {
            return true;
        }

        return false;
    }

    public Boolean getComplianceSurveyTypeIsProductSurvey() {
        if (getSurveyType().trim().equals("Product Survey")) {
            return true;
        }

        return false;
    }

    public Boolean getComplianceSurveyLocationTypeIsCommercialMarketplace() {
        if (getSurveyLocationType().trim().equals("Commercial Marketplace")) {
            return true;
        }

        return false;
    }

    public Boolean getComplianceSurveyLocationTypeIsPortOfEntry() {
        if (getSurveyLocationType().trim().equals("Port of Entry")) {
            return true;
        }

        return false;
    }

    public Boolean getComplianceSurveyLocationTypeIsSite() {
        if (getSurveyLocationType().trim().equals("Site")) {
            return true;
        }

        return false;
    }

    public Boolean getComplianceSurveyLocationTypeIsPortOfEntryOrSite() {
        if (getSurveyLocationType().trim().equals("Port of Entry")
                || getSurveyLocationType().trim().equals("Site")) {
            return true;
        }

        return false;
    }

    public Boolean getComplianceSurveyTypeOfPortOfEntryIsAirport() {
        if (getTypeOfPortOfEntry().trim().equals("Airport")) {
            return true;
        }

        return false;
    }

    public Boolean getDisableOtherInspectionLocation() {
        if (getInspectionPoint().toUpperCase().equals("OTHER")) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean getComplianceSurveyTypeOfPortOfEntryIsSeaport() {
        if (getTypeOfPortOfEntry().trim().equals("Seaport")) {
            return true;
        }

        return false;
    }

//    public static List<ComplianceSurvey> findComplianceSurveysByDateSearchField(
    public static List<ComplianceSurveySearchResult> findComplianceSurveyResultsByDateSearchField(
            EntityManager em,
            JobManagerUser user,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate,
            Boolean includeProductInspectionSearch,
            int maxResult) {

//        List<ComplianceSurvey> foundComplianceSurveys;
        List<ComplianceSurveySearchResult> foundComplianceSurveys;
        String searchQuery = null;
        String searchTextAndClause = "";
        String joinClause;
        String searchText;

        if (originalSearchText != null) {
            searchText = originalSearchText.replaceAll("'", "''");
        } else {
            searchText = "";
        }

        joinClause =
                " JOIN complianceSurvey.retailOutlet retailOutlet"
                + " JOIN complianceSurvey.retailRepresentative retailRepresentative"
                + " JOIN complianceSurvey.consignee consignee"
                + " JOIN complianceSurvey.consigneeRepresentative consigneeRepresentative"
                + " JOIN complianceSurvey.broker broker"
                + " JOIN complianceSurvey.editedBy editedBy"
                + " JOIN complianceSurvey.brokerRepresentative brokerRepresentative"
                + " JOIN complianceSurvey.entryDocumentInspection entryDocumentInspection"
                + (includeProductInspectionSearch ? " JOIN complianceSurvey.productInspections productInspections" : "")
                //+ " JOIN complianceSurvey.productInspections productInspections"
                + " JOIN complianceSurvey.inspector inspector";
        switch (searchType) {
            case "General":
                if (!searchText.equals("")) {
                    searchTextAndClause =
                            " AND ("
                            + " UPPER(complianceSurvey.portOfEntry) LIKE '%" + searchText.toUpperCase() + "%'"
                            + (includeProductInspectionSearch ? " OR UPPER(productInspections.name) LIKE '%" + searchText.toUpperCase() + "%'" : "")
                            + " OR UPPER(complianceSurvey.inspectionPoint) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(retailOutlet.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(broker.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(consignee.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(inspector.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(inspector.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(editedBy.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(editedBy.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.surveyType) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(entryDocumentInspection.vessel) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(entryDocumentInspection.countryOfConsignment) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(entryDocumentInspection.containerNumbers) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(entryDocumentInspection.entryDocumentNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.referenceNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.reasonForDetention) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.companyTypes) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " )";
                }
                if ((startDate == null) || (endDate == null)) {
                    searchQuery =
                            "SELECT DISTINCT complianceSurvey FROM ComplianceSurvey complianceSurvey"
                            + joinClause
                            + " WHERE (0 = 0)" // used as place holder
                            + searchTextAndClause
                            + " ORDER BY complianceSurvey.id DESC";
                } else {
                    searchQuery =
                            "SELECT DISTINCT complianceSurvey FROM ComplianceSurvey complianceSurvey"
                            + joinClause
                            + " WHERE (complianceSurvey." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                            + " AND complianceSurvey." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                            + searchTextAndClause
                            + " ORDER BY complianceSurvey.id DESC";
                }
                break;
            case "?":
                break;
        }

        try { // tk make max result to return an option
//            foundComplianceSurveys = em.createQuery(searchQuery, ComplianceSurvey.class).getResultList(); //.setMaxResults(500).getResultList();
            foundComplianceSurveys = em.createQuery(searchQuery, ComplianceSurveySearchResult.class).setMaxResults(maxResult).getResultList();
            if (foundComplianceSurveys == null) {
                foundComplianceSurveys = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundComplianceSurveys;
    }

    public static List<ComplianceSurvey> findComplianceSurveysByDateSearchField(
            EntityManager em,
            JobManagerUser user,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate,
            Boolean includeProductInspectionSearch) {

//        List<ComplianceSurvey> foundComplianceSurveys;
        List<ComplianceSurvey> foundComplianceSurveys;
        String searchQuery = null;
        String searchTextAndClause = "";
        String joinClause;
        String searchText;

        if (originalSearchText != null) {
            searchText = originalSearchText.replaceAll("'", "''");
        } else {
            searchText = "";
        }

        joinClause =
                " JOIN complianceSurvey.retailOutlet retailOutlet"
                + " JOIN complianceSurvey.retailRepresentative retailRepresentative"
                + " JOIN complianceSurvey.consignee consignee"
                + " JOIN complianceSurvey.consigneeRepresentative consigneeRepresentative"
                + " JOIN complianceSurvey.broker broker"
                + " JOIN complianceSurvey.editedBy editedBy"
                + " JOIN complianceSurvey.brokerRepresentative brokerRepresentative"
                + " JOIN complianceSurvey.entryDocumentInspection entryDocumentInspection"
                + (includeProductInspectionSearch ? " JOIN complianceSurvey.productInspections productInspections" : "")
                //+ " JOIN complianceSurvey.productInspections productInspections"
                + " JOIN complianceSurvey.inspector inspector";
        switch (searchType) {
            case "General":
                if (!searchText.equals("")) {
                    searchTextAndClause =
                            " AND ("
                            + " UPPER(complianceSurvey.portOfEntry) LIKE '%" + searchText.toUpperCase() + "%'"
                            + (includeProductInspectionSearch ? " OR UPPER(productInspections.name) LIKE '%" + searchText.toUpperCase() + "%'" : "")
                            + " OR UPPER(complianceSurvey.inspectionPoint) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(retailOutlet.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(broker.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(consignee.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(inspector.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(inspector.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(editedBy.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(editedBy.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.surveyType) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(entryDocumentInspection.vessel) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(entryDocumentInspection.countryOfConsignment) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(entryDocumentInspection.containerNumbers) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(entryDocumentInspection.entryDocumentNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.referenceNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.reasonForDetention) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.companyTypes) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " )";
                }
                if ((startDate == null) || (endDate == null)) {
                    searchQuery =
                            "SELECT DISTINCT complianceSurvey FROM ComplianceSurvey complianceSurvey"
                            + joinClause
                            + " WHERE (0 = 0)" // used as place holder
                            + searchTextAndClause
                            + " ORDER BY complianceSurvey.id DESC";
                } else {
                    searchQuery =
                            "SELECT DISTINCT complianceSurvey FROM ComplianceSurvey complianceSurvey"
                            + joinClause
                            + " WHERE (complianceSurvey." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                            + " AND complianceSurvey." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                            + searchTextAndClause
                            + " ORDER BY complianceSurvey.id DESC";
                }
                break;
            case "?":
                break;
        }

        try { // tk make max result to return an option
            foundComplianceSurveys = em.createQuery(searchQuery, ComplianceSurvey.class).setMaxResults(500).getResultList();
            if (foundComplianceSurveys == null) {
                foundComplianceSurveys = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundComplianceSurveys;
    }

    public static ComplianceSurvey findComplianceSurveyById(EntityManager em, Long Id) {

        try {

            ComplianceSurvey complianceSurvey = em.find(ComplianceSurvey.class, Id);

            return complianceSurvey;
        } catch (Exception e) {
            return null;
        }

    }

    public static Boolean save(EntityManager em, ComplianceSurvey survey) {

        try {
            Employee employee = Employee.findEmployeeByName(em, survey.getEditedBy().getName());
            survey.setDateEdited(new Date());
            survey.setEditedBy(employee);

            em.getTransaction().begin();

            // now save survey            
            Long id = BusinessEntityUtils.saveBusinessEntity(em, survey);
            em.getTransaction().commit();

            if (id == null) {
                return false;
            } else if (id == 0L) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return true;
    }
    
     public static ComplianceSurvey findDefaultComplianceSurvey(
            EntityManager em,
            String name,
            Boolean useTransaction) {
         
        ComplianceSurvey complianceSurvey = findComplianceSurveyByName(em, name);

        if (complianceSurvey == null) {
            complianceSurvey = new ComplianceSurvey();
            complianceSurvey.setName(name);
            
            if (useTransaction) {
                em.getTransaction().begin();
                BusinessEntityUtils.saveBusinessEntity(em, complianceSurvey);
                em.getTransaction().commit();
            } else {
                BusinessEntityUtils.saveBusinessEntity(em, complianceSurvey);
            }
        }

        return complianceSurvey;
    }

    public static ComplianceSurvey findComplianceSurveyByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<ComplianceSurvey> complianceSurveys = em.createQuery("SELECT c FROM  ComplianceSurvey c "
                    + "WHERE UPPER(c.name) "
                    + "= '" + newName.toUpperCase() + "'", ComplianceSurvey.class).getResultList();
            if (complianceSurveys.size() > 0) {
                return complianceSurveys.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) {
        ComplianceSurvey c = new ComplianceSurvey();
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
