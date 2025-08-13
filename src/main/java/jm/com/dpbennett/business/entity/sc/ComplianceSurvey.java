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
package jm.com.dpbennett.business.entity.sc;

import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.hrm.Address;
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
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.dm.DocumentStandard;
import jm.com.dpbennett.business.entity.auth.Signature;
import jm.com.dpbennett.business.entity.hrm.BusinessOffice;
import jm.com.dpbennett.business.entity.sm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "compliancesurvey")
public class ComplianceSurvey implements BusinessEntity {
    
    // tk signatures are to be replaced by fullnames.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surveyType;
    private String surveyLocationType;
    private String typeOfEstablishment;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice businessOffice;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfSurvey;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date surveyStartTime;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date surveyEndTime;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSigned;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseRequestReportDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseDateDomesticMarket;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseFromDetentionReportDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfDetention;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfNoticeOfDetention;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee inspector;
    @Column(length = 1024)
    private String comments;
    private String typeOfPortOfEntry;
    private String portOfEntry;
    private String inspectionPoint;
    @OneToOne(cascade = CascadeType.ALL)
    private Address inspectionAddress;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client consignee;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Contact consigneeRepresentative;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Address consigneeAddress;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client broker;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Contact brokerRepresentative;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Address brokerAddress;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client retailOutlet;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Contact retailRepresentative;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Address retailOutletAddress;
    private String referenceNumber;
    @Column(length = 1024)
    private String reasonForDetention;
    @OneToOne(cascade = CascadeType.ALL)
    private Address specifiedReleaseLocation;
    // Notice of refease from detention
    @OneToOne(cascade = CascadeType.ALL)
    private Address specifiedReleaseLocationDomesticMarket;
    @OneToOne(cascade = CascadeType.ALL)
    private Address locationOfDetainedProductDomesticMarket;
    private Boolean retailer;
    private Boolean distributor;
    private Boolean otherCompanyTypes;
    private String companyTypes;
    private Boolean fullRelease;
    private Boolean samplesToBeDisposed;
    private Boolean samplesToBeCollected;
    private Boolean requestForDetentionIssuedForPortOfEntry;
    private Boolean requestForSampleIssuedForPortOfEntry;
    private Boolean requestForReleaseIssuedForPortOfEntry;
    private Boolean noticeOfDetentionIssuedForDomesticMarket;
    private Boolean noticeOfReleaseFromDetentionIssuedForDomesticMarket;
    private Boolean compliant;
    private String portOfEntryDetentionNumber;
    private String domesticMarketDetentionNumber;
    // Port of Entry/POE Detention Request
    @OneToOne(cascade = CascadeType.REFRESH)
    private Signature authSigForDetentionRequestPOE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date authSigDateForDetentionRequestPOE;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee authEmployeeForDetentionRequestPOE;
    private String consignmentSizeDetained;
    // Sample Request - Port of Entry
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee inspectorForSampleRequestPOE;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Signature inspectorSigForSampleRequestPOE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inspectorSigDateForSampleRequestPOE;
    // Release Request - Port of Entry
    @OneToOne(cascade = CascadeType.REFRESH)
    private Signature preparedBySigForReleaseRequestPOE;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee preparedByEmployeeForReleaseRequestPOE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date preparedBySigDateForReleaseRequestPOE;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Signature approvedBySigForReleaseRequestPOE;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee approvedByEmployeeForReleaseRequestPOE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvedBySigDateForReleaseRequestPOE;
    // Notice of Detention - Domestic Market
    @OneToOne(cascade = CascadeType.REFRESH)
    private Signature authSigForNoticeOfDentionDM;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date authSigDateForNoticeOfDentionDM;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee authEmployeeForNoticeOfDentionDM; // tk replace with *Detention*
    // Notice of Release from Detention - Domestic Market
    @OneToOne(cascade = CascadeType.REFRESH)
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
    private List<DocumentStandard> standardsBreached;
    private String workProgress;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> inspectors;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<ProductInspection> productInspections;
    private Boolean applicationForRehabilitation;
    @Transient
    private Boolean isDirty;
    @Transient
    private String editStatus;

    public ComplianceSurvey() {
        this.surveyType = "";
    }

    public String getConsignmentSizeDetained() {

        if (consignmentSizeDetained == null) {
            consignmentSizeDetained = "";
        }

        return consignmentSizeDetained;
    }

    public void setConsignmentSizeDetained(String consignmentSizeDetained) {
        this.consignmentSizeDetained = consignmentSizeDetained;
    }

    public Employee getInspectorForSampleRequestPOE() {

        if (inspectorForSampleRequestPOE == null) {
            return new Employee();
        }

        return inspectorForSampleRequestPOE;
    }

    public void setInspectorForSampleRequestPOE(Employee inspectorForSampleRequestPOE) {
        this.inspectorForSampleRequestPOE = inspectorForSampleRequestPOE;
    }

    public Address getRetailOutletAddress() {
        if (retailOutletAddress == null) {
            return new Address();
        }

        return retailOutletAddress;
    }

    public void setRetailOutletAddress(Address retailOutletAddress) {
        this.retailOutletAddress = retailOutletAddress;
    }

    public Address getConsigneeAddress() {
        if (consigneeAddress == null) {
            return new Address();
        }

        return consigneeAddress;
    }

    public void setConsigneeAddress(Address consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsJobNumberValid() {
        return !getJobNumber().isEmpty();
    }

    public BusinessOffice getBusinessOffice() {
        return businessOffice;
    }

    public void setBusinessOffice(BusinessOffice businessOffice) {
        this.businessOffice = businessOffice;
    }

    public static List<Object[]> getReportRecords(
            EntityManager em,
            String startDate,
            String endDate,
            Long departmentId) {

        String reportSQL = "SELECT DISTINCT"
                //                + "     compliancesurvey.`ID`" // 0 - ID
                + "     compliancesurvey.`JOBNUMBER`," // 0 - Job number 
                + "     consignee.`NAME`," // 1 - Consignee
                + "     compliancesurvey.`COMMENTS`," // 2 - Comments                  
                + "     businessoffice.`NAME`," // 3 - Business office                 
                + "     entrydocumentinspection.`ENTRYDOCUMENTNUMBER`," // 4 - Entry document #s
                + "     entrydocumentinspection.`CONTAINERNUMBERS`," // 5 - Containers
                + "     compliancesurvey.`SURVEYTYPE`," // 6 - Survey type
                + "     compliancesurvey.`SURVEYLOCATIONTYPE`," // 7 - Survey location type 
                + "     compliancesurvey.`TYPEOFESTABLISHMENT`," // 8 - Type of establishment
                + "     retailoutlet.`NAME`," // 9 - Retail outlet  
                + "     compliancesurvey.`DATEOFSURVEY`," // 10 - Date of survey
                + "     compliancesurvey.`TYPEOFPORTOFENTRY`," // 11 - Type of port of entry
                + "     compliancesurvey.`PORTOFENTRY`," // 12 - Port of entry
                + "     compliancesurvey.`INSPECTIONPOINT`," // 13 - Inspection point
                + "     broker.`NAME`," // 14 - Broker
                + "     compliancesurvey.`REASONFORDETENTION`," // 15 - Reason for detention
                + "     GROUP_CONCAT(DISTINCT documentstandard.`NAME` SEPARATOR ', ') AS standardsBreached," // 16 - Standards breached
                + "     compliancesurvey.`WORKPROGRESS`," // 17 - Work progress 
                + "     GROUP_CONCAT(DISTINCT employee.`NAME` SEPARATOR '; ') AS inspectors," // 18 - Inspectors
                + "     SUM(DISTINCT productinspection.`QUANTITY`)," // 19 - Product quantity
                + "     entrydocumentinspection.`PROFILEFLAGGED`," // 20 - Profile flagged
                + "     GROUP_CONCAT(DISTINCT productinspection.`TARIFFCODE` SEPARATOR ', ')," // 21 - Commodity codes
                + "     (LENGTH(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '))"
                + "     - LENGTH(REPLACE(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '), 'Detention', '')))"
                + "     / LENGTH('Detention')," // 22 Detentions            
                + "     (LENGTH(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '))"
                + "     - LENGTH(REPLACE(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '), 'Destruction', '')))"
                + "     / LENGTH('Destruction')," // 23 Destructions
                + "     (LENGTH(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '))"
                + "     - LENGTH(REPLACE(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '), 'Seizure', '')))"
                + "     / LENGTH('Seizure')," // 24 Seizures
                + "     (LENGTH(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '))"
                + "     - LENGTH(REPLACE(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '), 'Condemnation', '')))"
                + "     / LENGTH('Condemnation')," // 25 Condemnations
                + "     (LENGTH(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '))"
                + "     - LENGTH(REPLACE(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '), 'Verification', '')))"
                + "     / LENGTH('Verification')," // 26 Verifications
                + "     (LENGTH(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '))"
                + "     - LENGTH(REPLACE(GROUP_CONCAT(DISTINCT productinspection.`ENFORCEMENTACTION` SEPARATOR ', '), 'Withdrawal', '')))"
                + "     / LENGTH('Withdrawal')," // 27 Withdrawals
                + "     GROUP_CONCAT(DISTINCT marketproduct.`NAME` SEPARATOR ', ')," // 28 - Products
                + "     GROUP_CONCAT(DISTINCT productcategory.`NAME` SEPARATOR ', ')," // 29 - Product categories
                + "     compliancesurvey.`ID`" // 30
                + " FROM"
                + "     compliancesurvey"
                + "     LEFT JOIN `client` consignee ON compliancesurvey.`CONSIGNEE_ID` = consignee.`ID`"
                + "     LEFT JOIN `client` broker ON compliancesurvey.`BROKER_ID` = broker.`ID`"
                + "     LEFT JOIN `businessoffice` businessoffice ON compliancesurvey.`BUSINESSOFFICE_ID` = businessoffice.`ID`"
                + "     LEFT JOIN `entrydocumentinspection` entrydocumentInspection ON compliancesurvey.`ENTRYDOCUMENTINSPECTION_ID` = entrydocumentInspection.`ID`"
                + "     LEFT JOIN `client` retailoutlet ON compliancesurvey.`RETAILOUTLET_ID` = retailoutlet.`ID`"
                + "     LEFT JOIN `compliancesurvey_documentstandard` compliancesurvey_documentstandard ON compliancesurvey.`ID` = compliancesurvey_documentstandard.`ComplianceSurvey_ID`"
                + "     LEFT JOIN `documentstandard` documentstandard ON compliancesurvey_documentstandard.`standardsBreached_ID` = documentstandard.`ID`"
                + "     LEFT JOIN `compliancesurvey_employee` compliancesurvey_employee ON compliancesurvey.`ID` = compliancesurvey_employee.`ComplianceSurvey_ID`"
                + "     LEFT JOIN `employee` employee ON compliancesurvey_employee.`inspectors_ID` = employee.`ID`"
                + "     LEFT JOIN `compliancesurvey_productinspection` compliancesurvey_productinspection ON compliancesurvey.`ID` = compliancesurvey_productinspection.`ComplianceSurvey_ID`"
                + "     LEFT JOIN `productinspection` productinspection ON compliancesurvey_productinspection.`productInspections_ID` = productinspection.`ID`"
                + "     LEFT JOIN `marketproduct` marketproduct ON productinspection.`MARKETPRODUCT_ID` = marketproduct.`ID`"
                + "     LEFT JOIN `category` productcategory ON productinspection.`PRODUCTCATEGORY_ID` = productcategory.`ID`"
                + " WHERE"
                + "     (compliancesurvey.`DATEOFSURVEY` >= " + startDate
                + " AND compliancesurvey.`DATEOFSURVEY` <= " + endDate + ")"
                + " GROUP BY"
                + "     compliancesurvey.`ID`"
                + " ORDER BY"
                + "     compliancesurvey.`ID` DESC";

        try {
            return em.createNativeQuery(reportSQL).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

    }

    public Date getDateOfNoticeOfDetention() {
        return dateOfNoticeOfDetention;
    }

    public void setDateOfNoticeOfDetention(Date dateOfNoticeOfDetention) {
        this.dateOfNoticeOfDetention = dateOfNoticeOfDetention;
    }

    public Boolean getCompliant() {
        if (compliant == null) {
            compliant = false;
        }
        return compliant;
    }

    public void setCompliant(Boolean compliant) {
        this.compliant = compliant;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public Boolean getDisableVerificationReport() {

        return false;
    }

    public Boolean getApplicationForRehabilitation() {
        if (applicationForRehabilitation == null) {
            applicationForRehabilitation = false;
        }
        return applicationForRehabilitation;
    }

    public void setApplicationForRehabilitation(Boolean applicationForRehabilitation) {
        this.applicationForRehabilitation = applicationForRehabilitation;
    }

    public String getInspectorList() {
        String list = "";

        for (Employee employee : getInspectors()) {
            list = list + " " + employee.toString();
        }

        return list;
    }

    public List<Employee> getInspectors() {
        if (inspectors == null) {
            inspectors = new ArrayList<>();
        }
        return inspectors;
    }

    public void setInspectors(List<Employee> inspectors) {
        this.inspectors = inspectors;
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

        if (isDirty) {
            setEditStatus("(edited)");
        } else {
            setEditStatus("        ");
        }
    }

    public String getWorkProgress() {
        if (workProgress == null) {
            workProgress = "Ongoing";
        }
        return workProgress;
    }

    public void setWorkProgress(String workProgress) {
        this.workProgress = workProgress;
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

    public String getSurveyType() {
        if (surveyType == null) {
            surveyType = "";
        }
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    @Override
    public Date getDateEdited() {
        return dateEdited;
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    @Override
    public Employee getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }
        return editedBy;
    }

    @Override
    public void setEditedBy(Person person) {
        this.editedBy = (Employee) person;
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
        if (noticeOfReleaseFromDetentionIssuedForDomesticMarket == null) {
            noticeOfReleaseFromDetentionIssuedForDomesticMarket = false;
        }
        return noticeOfReleaseFromDetentionIssuedForDomesticMarket;
    }

    public void setNoticeOfReleaseFromDetentionIssuedForDomesticMarket(Boolean noticeOfReleaseFromDetentionIssuedForDomesticMarket) {
        this.noticeOfReleaseFromDetentionIssuedForDomesticMarket = noticeOfReleaseFromDetentionIssuedForDomesticMarket;
    }

    public Boolean getRequestForDetentionIssuedForPortOfEntry() {
        if (requestForDetentionIssuedForPortOfEntry == null) {
            requestForDetentionIssuedForPortOfEntry = false;
        }
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
            return new Client("", false);
        }
        return broker;
    }

    public void setBroker(Client broker) {
        this.broker = broker;
    }

    public Contact getBrokerRepresentative() {
        if (brokerRepresentative == null) {
            return new Contact();
        }
        return brokerRepresentative;
    }

    public void setBrokerRepresentative(Contact brokerRepresentative) {
        this.brokerRepresentative = brokerRepresentative;
    }

    public Address getBrokerAddress() {
        if (brokerAddress == null) {
            return new Address();
        }
        return brokerAddress;
    }

    public void setBrokerAddress(Address brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    public Client getConsignee() {
        if (consignee == null) {
            return new Client("", false);
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

            return new Contact();

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

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateOfSurvey() {
        return dateOfSurvey;
    }

    public void setDateOfSurvey(Date dateOfSurvey) {
        this.dateOfSurvey = dateOfSurvey;
    }

    public Date getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(Date dateSigned) {
        this.dateSigned = dateSigned;
    }

    public Employee getInspector() {
        if (inspector == null) {
            return new Employee();
        }
        return inspector;
    }

    public void setInspector(Employee inspector) {
        this.inspector = inspector;
    }

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
            return new Client("", false);
        }
        return retailOutlet;
    }

    public void setRetailOutlet(Client retailOutlet) {
        this.retailOutlet = retailOutlet;
    }

    public Contact getRetailRepresentative() {
        if (retailRepresentative == null) {

            return new Contact();
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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
        return !getSurveyType().trim().equals("");
    }

    public Boolean getComplianceSurveyTypeIsMarketSurvey() {
        return getSurveyType().trim().equals("Market Survey");
    }

    public Boolean getComplianceSurveyTypeIsProductSurvey() {
        return getSurveyType().trim().equals("Product Survey");
    }

    public Boolean getComplianceSurveyLocationTypeIsCommercialMarketplace() {
        return getSurveyLocationType().trim().equals("Commercial Marketplace");
    }

    public Boolean getComplianceSurveyLocationTypeIsPortOfEntry() {
        return getSurveyLocationType().trim().equals("Port of Entry");
    }

    public Boolean getComplianceSurveyLocationTypeIsSite() {
        return getSurveyLocationType().trim().equals("Site");
    }

    public Boolean getComplianceSurveyLocationTypeIsPortOfEntryOrSite() {
        return getSurveyLocationType().trim().equals("Port of Entry")
                || getSurveyLocationType().trim().equals("Site");
    }

    public Boolean getComplianceSurveyTypeOfPortOfEntryIsAirport() {
        return getTypeOfPortOfEntry().trim().equals("Airport");
    }

    public Boolean getComplianceSurveyTypeOfPortOfEntryIsSeaport() {
        return getTypeOfPortOfEntry().trim().equals("Seaport");
    }

    public static List<ComplianceSurveySearchResult> findComplianceSurveyResultsByDateSearchField(
            EntityManager em,
            User user,
            String dateSearchField,
            String searchType,
            String searchText,
            Date startDate,
            Date endDate,
            Boolean includeProductInspectionSearch,
            int maxResult) {

        List<ComplianceSurveySearchResult> foundComplianceSurveys;
        searchText = searchText.replaceAll("&amp;", "&").replaceAll("'", "`");
        String searchQuery = null;
        String searchTextAndClause = "";
        String joinClause;

        joinClause
                = " JOIN complianceSurvey.retailOutlet retailOutlet"
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
                    searchTextAndClause
                            = " AND ("
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
                    searchQuery
                            = "SELECT DISTINCT complianceSurvey FROM ComplianceSurvey complianceSurvey"
                            + joinClause
                            + " WHERE (0 = 0)" // used as place holder
                            + searchTextAndClause
                            + " ORDER BY complianceSurvey.id DESC";
                } else {
                    searchQuery
                            = "SELECT DISTINCT complianceSurvey FROM ComplianceSurvey complianceSurvey"
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

        try {
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
            User user,
            String dateSearchField,
            String searchType,
            String searchText,
            Date startDate,
            Date endDate,
            Boolean includeProductInspectionSearch,
            int maxResults) {

        List<ComplianceSurvey> foundComplianceSurveys;
        searchText = searchText.replaceAll("&amp;", "&").replaceAll("'", "`");
        String searchQuery = null;
        String searchTextAndClause = "";
        String joinClause;

        joinClause
                = " LEFT JOIN complianceSurvey.retailOutlet retailOutlet"
                + " LEFT JOIN complianceSurvey.retailRepresentative retailRepresentative"
                + " LEFT JOIN complianceSurvey.consignee consignee"
                + " LEFT JOIN complianceSurvey.consigneeRepresentative consigneeRepresentative"
                + " LEFT JOIN complianceSurvey.broker broker"
                + " LEFT JOIN complianceSurvey.editedBy editedBy"
                + " LEFT JOIN complianceSurvey.brokerRepresentative brokerRepresentative"
                + " LEFT JOIN complianceSurvey.entryDocumentInspection entryDocumentInspection"
                + (includeProductInspectionSearch ? " JOIN complianceSurvey.productInspections productInspections" : "")
                //+ " JOIN complianceSurvey.productInspections productInspections"
                + " LEFT JOIN complianceSurvey.inspector inspector";
        switch (searchType) {
            case "General":
                if (!searchText.equals("")) {
                    searchTextAndClause
                            = " AND ("
                            + " UPPER(complianceSurvey.portOfEntry) LIKE '%" + searchText.toUpperCase() + "%'"
                            + (includeProductInspectionSearch ? " OR UPPER(productInspections.name) LIKE '%" + searchText.toUpperCase() + "%'" : "")
                            + " OR UPPER(complianceSurvey.inspectionPoint) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(complianceSurvey.jobNumber) LIKE '%" + searchText.toUpperCase() + "%'"
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
                    searchQuery
                            = "SELECT DISTINCT complianceSurvey FROM ComplianceSurvey complianceSurvey"
                            + joinClause
                            + " WHERE (0 = 0)" // used as place holder
                            + searchTextAndClause
                            + " ORDER BY complianceSurvey.id DESC";
                } else {
                    searchQuery
                            = "SELECT DISTINCT complianceSurvey FROM ComplianceSurvey complianceSurvey"
                            + joinClause // tk date search field to be used when doing dashboard search
                            + " WHERE (complianceSurvey." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                            + " AND complianceSurvey." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                            + searchTextAndClause
                            + " ORDER BY complianceSurvey.id DESC";
                }
                break;
            case "?":
                break;
        }

        try {
            if (maxResults == 0) {
                foundComplianceSurveys = em.createQuery(searchQuery, ComplianceSurvey.class).getResultList();
            } else {
                foundComplianceSurveys = em.createQuery(searchQuery, ComplianceSurvey.class).setMaxResults(maxResults).getResultList();
            }
            if (foundComplianceSurveys == null) {
                foundComplianceSurveys = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundComplianceSurveys;
    }

    public static ComplianceSurvey findComplianceSurveyById(
            EntityManager em, Long Id) {

        try {

            ComplianceSurvey complianceSurvey = em.find(ComplianceSurvey.class, Id);

            return complianceSurvey;
        } catch (Exception e) {
            return null;
        }

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

    public static ComplianceSurvey findComplianceSurveyByName(
            EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<ComplianceSurvey> complianceSurveys = em.createQuery("SELECT c FROM  ComplianceSurvey c "
                    + "WHERE UPPER(c.name) "
                    + "= '" + value.toUpperCase() + "'", ComplianceSurvey.class).getResultList();
            if (!complianceSurveys.isEmpty()) {
                return complianceSurveys.get(0);
            }

            return null;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            getBusinessOffice().save(em);
            getInspector().save(em);
            getInspectionAddress().save(em);
            getConsignee().save(em);
            getConsigneeRepresentative().save(em);
            getConsigneeAddress().save(em);
            getBroker().save(em);
            getBrokerRepresentative().save(em);
            getBrokerAddress().save(em);
            getRetailOutlet().save(em);
            getRetailRepresentative().save(em);
            getRetailOutletAddress().save(em);
            getSpecifiedReleaseLocation().save(em);
            getSpecifiedReleaseLocationDomesticMarket().save(em);
            getLocationOfDetainedProductDomesticMarket().save(em);

            if (getAuthSigForDetentionRequestPOE().getId() != null) {
                getAuthSigForDetentionRequestPOE().save(em);
            }

            if (getAuthEmployeeForDetentionRequestPOE().getId() != null) {
                getAuthEmployeeForDetentionRequestPOE().save(em);
            }

            if (getInspectorForSampleRequestPOE().getId() != null) {
                getInspectorForSampleRequestPOE().save(em);
            }

            if (getInspectorSigForSampleRequestPOE().getId() != null) {
                getInspectorSigForSampleRequestPOE().save(em);
            }

            if (getPreparedBySigForReleaseRequestPOE().getId() != null) {
                getPreparedBySigForReleaseRequestPOE().save(em);
            }

            if (getPreparedByEmployeeForReleaseRequestPOE().getId() != null) {
                getPreparedByEmployeeForReleaseRequestPOE().save(em);
            }

            if (getPreparedBySigForReleaseRequestPOE().getId() != null) {
                getPreparedBySigForReleaseRequestPOE().save(em);
            }

            if (getApprovedBySigForReleaseRequestPOE().getId() != null) {
                getApprovedBySigForReleaseRequestPOE().save(em);
            }

            if (getApprovedByEmployeeForReleaseRequestPOE().getId() != null) {
                getApprovedByEmployeeForReleaseRequestPOE().save(em);
            }

            if (getAuthSigForNoticeOfDentionDM().getId() != null) {
                getAuthSigForNoticeOfDentionDM().save(em);
            }

            if (getAuthEmployeeForNoticeOfDentionDM().getId() != null) {
                getAuthEmployeeForNoticeOfDentionDM().save(em);
            }

            if (getAuthSigForNoticeOfReleaseFromDentionDM().getId() != null) {
                getAuthSigForNoticeOfReleaseFromDentionDM().save(em);
            }

            if (getAuthEmpForNoticeOfReleaseFromDentionDM().getId() != null) {
                getAuthEmpForNoticeOfReleaseFromDentionDM().save(em);
            }

            for (Employee inspector1 : inspectors) {
                inspector1.save(em);
            }

            getEditedBy().save(em);
            getEntryDocumentInspection().save(em);

            for (DocumentStandard documentStandard : standardsBreached) {
                documentStandard.save(em);
            }

            for (Employee inspector1 : inspectors) {
                inspector1.save(em);
            }

            if (!getProductInspections().isEmpty()) {
                for (ProductInspection productInspection : getProductInspections()) {
                    if ((productInspection.getIsDirty() || productInspection.getId() == null)
                            && !productInspection.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Product save error occurred",
                                "An error occurred while saving a product",
                                Message.SEVERITY_ERROR_NAME);
                    }
                }
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Compliance Survey not saved");
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
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
