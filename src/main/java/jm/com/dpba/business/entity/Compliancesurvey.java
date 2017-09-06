/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "compliancesurvey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compliancesurvey.findAll", query = "SELECT c FROM Compliancesurvey c")
    , @NamedQuery(name = "Compliancesurvey.findById", query = "SELECT c FROM Compliancesurvey c WHERE c.id = :id")
    , @NamedQuery(name = "Compliancesurvey.findByApprovedbysigdateforreleaserequestpoe", query = "SELECT c FROM Compliancesurvey c WHERE c.approvedbysigdateforreleaserequestpoe = :approvedbysigdateforreleaserequestpoe")
    , @NamedQuery(name = "Compliancesurvey.findByAuthsigdatefordetentionrequestpoe", query = "SELECT c FROM Compliancesurvey c WHERE c.authsigdatefordetentionrequestpoe = :authsigdatefordetentionrequestpoe")
    , @NamedQuery(name = "Compliancesurvey.findByAuthsigdatefornoticeofdentiondm", query = "SELECT c FROM Compliancesurvey c WHERE c.authsigdatefornoticeofdentiondm = :authsigdatefornoticeofdentiondm")
    , @NamedQuery(name = "Compliancesurvey.findByAuthsigdatefornoticeofreleasefromdentiondm", query = "SELECT c FROM Compliancesurvey c WHERE c.authsigdatefornoticeofreleasefromdentiondm = :authsigdatefornoticeofreleasefromdentiondm")
    , @NamedQuery(name = "Compliancesurvey.findByComments", query = "SELECT c FROM Compliancesurvey c WHERE c.comments = :comments")
    , @NamedQuery(name = "Compliancesurvey.findByCompanytypes", query = "SELECT c FROM Compliancesurvey c WHERE c.companytypes = :companytypes")
    , @NamedQuery(name = "Compliancesurvey.findByDateedited", query = "SELECT c FROM Compliancesurvey c WHERE c.dateedited = :dateedited")
    , @NamedQuery(name = "Compliancesurvey.findByDateofdetention", query = "SELECT c FROM Compliancesurvey c WHERE c.dateofdetention = :dateofdetention")
    , @NamedQuery(name = "Compliancesurvey.findByDateofsurvey", query = "SELECT c FROM Compliancesurvey c WHERE c.dateofsurvey = :dateofsurvey")
    , @NamedQuery(name = "Compliancesurvey.findByDatesigned", query = "SELECT c FROM Compliancesurvey c WHERE c.datesigned = :datesigned")
    , @NamedQuery(name = "Compliancesurvey.findByDistributor", query = "SELECT c FROM Compliancesurvey c WHERE c.distributor = :distributor")
    , @NamedQuery(name = "Compliancesurvey.findByDomesticmarketdetentionnumber", query = "SELECT c FROM Compliancesurvey c WHERE c.domesticmarketdetentionnumber = :domesticmarketdetentionnumber")
    , @NamedQuery(name = "Compliancesurvey.findByFullrelease", query = "SELECT c FROM Compliancesurvey c WHERE c.fullrelease = :fullrelease")
    , @NamedQuery(name = "Compliancesurvey.findByInspectionpoint", query = "SELECT c FROM Compliancesurvey c WHERE c.inspectionpoint = :inspectionpoint")
    , @NamedQuery(name = "Compliancesurvey.findByInspectorsigdateforsamplerequestpoe", query = "SELECT c FROM Compliancesurvey c WHERE c.inspectorsigdateforsamplerequestpoe = :inspectorsigdateforsamplerequestpoe")
    , @NamedQuery(name = "Compliancesurvey.findByJobnumber", query = "SELECT c FROM Compliancesurvey c WHERE c.jobnumber = :jobnumber")
    , @NamedQuery(name = "Compliancesurvey.findByName", query = "SELECT c FROM Compliancesurvey c WHERE c.name = :name")
    , @NamedQuery(name = "Compliancesurvey.findByNoticeofdetentionissuedfordomesticmarket", query = "SELECT c FROM Compliancesurvey c WHERE c.noticeofdetentionissuedfordomesticmarket = :noticeofdetentionissuedfordomesticmarket")
    , @NamedQuery(name = "Compliancesurvey.findByNoticeofreleasefromdetentionissuedfordomesticmarket", query = "SELECT c FROM Compliancesurvey c WHERE c.noticeofreleasefromdetentionissuedfordomesticmarket = :noticeofreleasefromdetentionissuedfordomesticmarket")
    , @NamedQuery(name = "Compliancesurvey.findByOthercompanytypes", query = "SELECT c FROM Compliancesurvey c WHERE c.othercompanytypes = :othercompanytypes")
    , @NamedQuery(name = "Compliancesurvey.findByPortofentry", query = "SELECT c FROM Compliancesurvey c WHERE c.portofentry = :portofentry")
    , @NamedQuery(name = "Compliancesurvey.findByPortofentrydetentionnumber", query = "SELECT c FROM Compliancesurvey c WHERE c.portofentrydetentionnumber = :portofentrydetentionnumber")
    , @NamedQuery(name = "Compliancesurvey.findByPreparedbysigdateforreleaserequestpoe", query = "SELECT c FROM Compliancesurvey c WHERE c.preparedbysigdateforreleaserequestpoe = :preparedbysigdateforreleaserequestpoe")
    , @NamedQuery(name = "Compliancesurvey.findByReasonfordetention", query = "SELECT c FROM Compliancesurvey c WHERE c.reasonfordetention = :reasonfordetention")
    , @NamedQuery(name = "Compliancesurvey.findByReferencenumber", query = "SELECT c FROM Compliancesurvey c WHERE c.referencenumber = :referencenumber")
    , @NamedQuery(name = "Compliancesurvey.findByReleasedatedomesticmarket", query = "SELECT c FROM Compliancesurvey c WHERE c.releasedatedomesticmarket = :releasedatedomesticmarket")
    , @NamedQuery(name = "Compliancesurvey.findByReleasefromdetentionreportdate", query = "SELECT c FROM Compliancesurvey c WHERE c.releasefromdetentionreportdate = :releasefromdetentionreportdate")
    , @NamedQuery(name = "Compliancesurvey.findByReleaserequestreportdate", query = "SELECT c FROM Compliancesurvey c WHERE c.releaserequestreportdate = :releaserequestreportdate")
    , @NamedQuery(name = "Compliancesurvey.findByRequestfordetentionissuedforportofentry", query = "SELECT c FROM Compliancesurvey c WHERE c.requestfordetentionissuedforportofentry = :requestfordetentionissuedforportofentry")
    , @NamedQuery(name = "Compliancesurvey.findByRequestforreleaseissuedforportofentry", query = "SELECT c FROM Compliancesurvey c WHERE c.requestforreleaseissuedforportofentry = :requestforreleaseissuedforportofentry")
    , @NamedQuery(name = "Compliancesurvey.findByRequestforsampleissuedforportofentry", query = "SELECT c FROM Compliancesurvey c WHERE c.requestforsampleissuedforportofentry = :requestforsampleissuedforportofentry")
    , @NamedQuery(name = "Compliancesurvey.findByRetailer", query = "SELECT c FROM Compliancesurvey c WHERE c.retailer = :retailer")
    , @NamedQuery(name = "Compliancesurvey.findBySamplestobecollected", query = "SELECT c FROM Compliancesurvey c WHERE c.samplestobecollected = :samplestobecollected")
    , @NamedQuery(name = "Compliancesurvey.findBySamplestobedisposed", query = "SELECT c FROM Compliancesurvey c WHERE c.samplestobedisposed = :samplestobedisposed")
    , @NamedQuery(name = "Compliancesurvey.findBySurveyendtime", query = "SELECT c FROM Compliancesurvey c WHERE c.surveyendtime = :surveyendtime")
    , @NamedQuery(name = "Compliancesurvey.findBySurveylocationtype", query = "SELECT c FROM Compliancesurvey c WHERE c.surveylocationtype = :surveylocationtype")
    , @NamedQuery(name = "Compliancesurvey.findBySurveystarttime", query = "SELECT c FROM Compliancesurvey c WHERE c.surveystarttime = :surveystarttime")
    , @NamedQuery(name = "Compliancesurvey.findBySurveytype", query = "SELECT c FROM Compliancesurvey c WHERE c.surveytype = :surveytype")
    , @NamedQuery(name = "Compliancesurvey.findByTypeofestablishment", query = "SELECT c FROM Compliancesurvey c WHERE c.typeofestablishment = :typeofestablishment")
    , @NamedQuery(name = "Compliancesurvey.findByTypeofportofentry", query = "SELECT c FROM Compliancesurvey c WHERE c.typeofportofentry = :typeofportofentry")
    , @NamedQuery(name = "Compliancesurvey.findByOtherinspectionlocation", query = "SELECT c FROM Compliancesurvey c WHERE c.otherinspectionlocation = :otherinspectionlocation")})
public class Compliancesurvey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "APPROVEDBYSIGDATEFORRELEASEREQUESTPOE")
    @Temporal(TemporalType.DATE)
    private Date approvedbysigdateforreleaserequestpoe;
    @Column(name = "AUTHSIGDATEFORDETENTIONREQUESTPOE")
    @Temporal(TemporalType.DATE)
    private Date authsigdatefordetentionrequestpoe;
    @Column(name = "AUTHSIGDATEFORNOTICEOFDENTIONDM")
    @Temporal(TemporalType.DATE)
    private Date authsigdatefornoticeofdentiondm;
    @Column(name = "AUTHSIGDATEFORNOTICEOFRELEASEFROMDENTIONDM")
    @Temporal(TemporalType.DATE)
    private Date authsigdatefornoticeofreleasefromdentiondm;
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "COMPANYTYPES")
    private String companytypes;
    @Column(name = "DATEEDITED")
    @Temporal(TemporalType.DATE)
    private Date dateedited;
    @Column(name = "DATEOFDETENTION")
    @Temporal(TemporalType.DATE)
    private Date dateofdetention;
    @Column(name = "DATEOFSURVEY")
    @Temporal(TemporalType.DATE)
    private Date dateofsurvey;
    @Column(name = "DATESIGNED")
    @Temporal(TemporalType.DATE)
    private Date datesigned;
    @Column(name = "DISTRIBUTOR")
    private Boolean distributor;
    @Column(name = "DOMESTICMARKETDETENTIONNUMBER")
    private String domesticmarketdetentionnumber;
    @Column(name = "FULLRELEASE")
    private Boolean fullrelease;
    @Column(name = "INSPECTIONPOINT")
    private String inspectionpoint;
    @Column(name = "INSPECTORSIGDATEFORSAMPLEREQUESTPOE")
    @Temporal(TemporalType.DATE)
    private Date inspectorsigdateforsamplerequestpoe;
    @Column(name = "JOBNUMBER")
    private String jobnumber;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTICEOFDETENTIONISSUEDFORDOMESTICMARKET")
    private Boolean noticeofdetentionissuedfordomesticmarket;
    @Column(name = "NOTICEOFRELEASEFROMDETENTIONISSUEDFORDOMESTICMARKET")
    private Boolean noticeofreleasefromdetentionissuedfordomesticmarket;
    @Column(name = "OTHERCOMPANYTYPES")
    private Boolean othercompanytypes;
    @Column(name = "PORTOFENTRY")
    private String portofentry;
    @Column(name = "PORTOFENTRYDETENTIONNUMBER")
    private String portofentrydetentionnumber;
    @Column(name = "PREPAREDBYSIGDATEFORRELEASEREQUESTPOE")
    @Temporal(TemporalType.DATE)
    private Date preparedbysigdateforreleaserequestpoe;
    @Column(name = "REASONFORDETENTION")
    private String reasonfordetention;
    @Column(name = "REFERENCENUMBER")
    private String referencenumber;
    @Column(name = "RELEASEDATEDOMESTICMARKET")
    @Temporal(TemporalType.DATE)
    private Date releasedatedomesticmarket;
    @Column(name = "RELEASEFROMDETENTIONREPORTDATE")
    @Temporal(TemporalType.DATE)
    private Date releasefromdetentionreportdate;
    @Column(name = "RELEASEREQUESTREPORTDATE")
    @Temporal(TemporalType.DATE)
    private Date releaserequestreportdate;
    @Column(name = "REQUESTFORDETENTIONISSUEDFORPORTOFENTRY")
    private Boolean requestfordetentionissuedforportofentry;
    @Column(name = "REQUESTFORRELEASEISSUEDFORPORTOFENTRY")
    private Boolean requestforreleaseissuedforportofentry;
    @Column(name = "REQUESTFORSAMPLEISSUEDFORPORTOFENTRY")
    private Boolean requestforsampleissuedforportofentry;
    @Column(name = "RETAILER")
    private Boolean retailer;
    @Column(name = "SAMPLESTOBECOLLECTED")
    private Boolean samplestobecollected;
    @Column(name = "SAMPLESTOBEDISPOSED")
    private Boolean samplestobedisposed;
    @Column(name = "SURVEYENDTIME")
    @Temporal(TemporalType.TIME)
    private Date surveyendtime;
    @Column(name = "SURVEYLOCATIONTYPE")
    private String surveylocationtype;
    @Column(name = "SURVEYSTARTTIME")
    @Temporal(TemporalType.TIME)
    private Date surveystarttime;
    @Column(name = "SURVEYTYPE")
    private String surveytype;
    @Column(name = "TYPEOFESTABLISHMENT")
    private String typeofestablishment;
    @Column(name = "TYPEOFPORTOFENTRY")
    private String typeofportofentry;
    @Column(name = "OTHERINSPECTIONLOCATION")
    private String otherinspectionlocation;
    @ManyToMany(mappedBy = "compliancesurveyList")
    private List<Documentstandard> documentstandardList;
    @JoinColumn(name = "BROKER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client brokerId;
    @JoinColumn(name = "AUTHSIGFORNOTICEOFDENTIONDM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Signature authsigfornoticeofdentiondmId;
    @JoinColumn(name = "BROKERREPRESENTATIVE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact brokerrepresentativeId;
    @JoinColumn(name = "AUTHSIGFORDETENTIONREQUESTPOE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Signature authsigfordetentionrequestpoeId;
    @JoinColumn(name = "CONSIGNEEREPRESENTATIVE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact consigneerepresentativeId;
    @JoinColumn(name = "INSPECTORSIGFORSAMPLEREQUESTPOE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Signature inspectorsigforsamplerequestpoeId;
    @JoinColumn(name = "CONSIGNEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client consigneeId;
    @JoinColumn(name = "EDITEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee editedbyId;
    @JoinColumn(name = "ENTRYDOCUMENTINSPECTION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Entrydocumentinspection entrydocumentinspectionId;
    @JoinColumn(name = "AUTHEMPLOYEEFORDETENTIONREQUESTPOE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee authemployeefordetentionrequestpoeId;
    @JoinColumn(name = "PREPAREDBYEMPLOYEEFORRELEASEREQUESTPOE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee preparedbyemployeeforreleaserequestpoeId;
    @JoinColumn(name = "INSPECTIONADDRESS_ID", referencedColumnName = "ID")
    @ManyToOne
    private Address inspectionaddressId;
    @JoinColumn(name = "SPECIFIEDRELEASELOCATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Address specifiedreleaselocationId;
    @JoinColumn(name = "APPROVEDBYEMPLOYEEFORRELEASEREQUESTPOE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee approvedbyemployeeforreleaserequestpoeId;
    @JoinColumn(name = "SPECIFIEDRELEASELOCATIONDOMESTICMARKET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Address specifiedreleaselocationdomesticmarketId;
    @JoinColumn(name = "INSPECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee inspectorId;
    @JoinColumn(name = "AUTHEMPFORNOTICEOFRELEASEFROMDENTIONDM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee authempfornoticeofreleasefromdentiondmId;
    @JoinColumn(name = "PREPAREDBYSIGFORRELEASEREQUESTPOE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Signature preparedbysigforreleaserequestpoeId;
    @JoinColumn(name = "AUTHEMPLOYEEFORNOTICEOFDENTIONDM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee authemployeefornoticeofdentiondmId;
    @JoinColumn(name = "RETAILOUTLET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client retailoutletId;
    @JoinColumn(name = "RETAILREPRESENTATIVE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact retailrepresentativeId;
    @JoinColumn(name = "LOCATIONOFDETAINEDPRODUCTDOMESTICMARKET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Address locationofdetainedproductdomesticmarketId;
    @JoinColumn(name = "APPROVEDBYSIGFORRELEASEREQUESTPOE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Signature approvedbysigforreleaserequestpoeId;
    @JoinColumn(name = "AUTHSIGFORNOTICEOFRELEASEFROMDENTIONDM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Signature authsigfornoticeofreleasefromdentiondmId;

    public Compliancesurvey() {
    }

    public Compliancesurvey(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getApprovedbysigdateforreleaserequestpoe() {
        return approvedbysigdateforreleaserequestpoe;
    }

    public void setApprovedbysigdateforreleaserequestpoe(Date approvedbysigdateforreleaserequestpoe) {
        this.approvedbysigdateforreleaserequestpoe = approvedbysigdateforreleaserequestpoe;
    }

    public Date getAuthsigdatefordetentionrequestpoe() {
        return authsigdatefordetentionrequestpoe;
    }

    public void setAuthsigdatefordetentionrequestpoe(Date authsigdatefordetentionrequestpoe) {
        this.authsigdatefordetentionrequestpoe = authsigdatefordetentionrequestpoe;
    }

    public Date getAuthsigdatefornoticeofdentiondm() {
        return authsigdatefornoticeofdentiondm;
    }

    public void setAuthsigdatefornoticeofdentiondm(Date authsigdatefornoticeofdentiondm) {
        this.authsigdatefornoticeofdentiondm = authsigdatefornoticeofdentiondm;
    }

    public Date getAuthsigdatefornoticeofreleasefromdentiondm() {
        return authsigdatefornoticeofreleasefromdentiondm;
    }

    public void setAuthsigdatefornoticeofreleasefromdentiondm(Date authsigdatefornoticeofreleasefromdentiondm) {
        this.authsigdatefornoticeofreleasefromdentiondm = authsigdatefornoticeofreleasefromdentiondm;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCompanytypes() {
        return companytypes;
    }

    public void setCompanytypes(String companytypes) {
        this.companytypes = companytypes;
    }

    public Date getDateedited() {
        return dateedited;
    }

    public void setDateedited(Date dateedited) {
        this.dateedited = dateedited;
    }

    public Date getDateofdetention() {
        return dateofdetention;
    }

    public void setDateofdetention(Date dateofdetention) {
        this.dateofdetention = dateofdetention;
    }

    public Date getDateofsurvey() {
        return dateofsurvey;
    }

    public void setDateofsurvey(Date dateofsurvey) {
        this.dateofsurvey = dateofsurvey;
    }

    public Date getDatesigned() {
        return datesigned;
    }

    public void setDatesigned(Date datesigned) {
        this.datesigned = datesigned;
    }

    public Boolean getDistributor() {
        return distributor;
    }

    public void setDistributor(Boolean distributor) {
        this.distributor = distributor;
    }

    public String getDomesticmarketdetentionnumber() {
        return domesticmarketdetentionnumber;
    }

    public void setDomesticmarketdetentionnumber(String domesticmarketdetentionnumber) {
        this.domesticmarketdetentionnumber = domesticmarketdetentionnumber;
    }

    public Boolean getFullrelease() {
        return fullrelease;
    }

    public void setFullrelease(Boolean fullrelease) {
        this.fullrelease = fullrelease;
    }

    public String getInspectionpoint() {
        return inspectionpoint;
    }

    public void setInspectionpoint(String inspectionpoint) {
        this.inspectionpoint = inspectionpoint;
    }

    public Date getInspectorsigdateforsamplerequestpoe() {
        return inspectorsigdateforsamplerequestpoe;
    }

    public void setInspectorsigdateforsamplerequestpoe(Date inspectorsigdateforsamplerequestpoe) {
        this.inspectorsigdateforsamplerequestpoe = inspectorsigdateforsamplerequestpoe;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNoticeofdetentionissuedfordomesticmarket() {
        return noticeofdetentionissuedfordomesticmarket;
    }

    public void setNoticeofdetentionissuedfordomesticmarket(Boolean noticeofdetentionissuedfordomesticmarket) {
        this.noticeofdetentionissuedfordomesticmarket = noticeofdetentionissuedfordomesticmarket;
    }

    public Boolean getNoticeofreleasefromdetentionissuedfordomesticmarket() {
        return noticeofreleasefromdetentionissuedfordomesticmarket;
    }

    public void setNoticeofreleasefromdetentionissuedfordomesticmarket(Boolean noticeofreleasefromdetentionissuedfordomesticmarket) {
        this.noticeofreleasefromdetentionissuedfordomesticmarket = noticeofreleasefromdetentionissuedfordomesticmarket;
    }

    public Boolean getOthercompanytypes() {
        return othercompanytypes;
    }

    public void setOthercompanytypes(Boolean othercompanytypes) {
        this.othercompanytypes = othercompanytypes;
    }

    public String getPortofentry() {
        return portofentry;
    }

    public void setPortofentry(String portofentry) {
        this.portofentry = portofentry;
    }

    public String getPortofentrydetentionnumber() {
        return portofentrydetentionnumber;
    }

    public void setPortofentrydetentionnumber(String portofentrydetentionnumber) {
        this.portofentrydetentionnumber = portofentrydetentionnumber;
    }

    public Date getPreparedbysigdateforreleaserequestpoe() {
        return preparedbysigdateforreleaserequestpoe;
    }

    public void setPreparedbysigdateforreleaserequestpoe(Date preparedbysigdateforreleaserequestpoe) {
        this.preparedbysigdateforreleaserequestpoe = preparedbysigdateforreleaserequestpoe;
    }

    public String getReasonfordetention() {
        return reasonfordetention;
    }

    public void setReasonfordetention(String reasonfordetention) {
        this.reasonfordetention = reasonfordetention;
    }

    public String getReferencenumber() {
        return referencenumber;
    }

    public void setReferencenumber(String referencenumber) {
        this.referencenumber = referencenumber;
    }

    public Date getReleasedatedomesticmarket() {
        return releasedatedomesticmarket;
    }

    public void setReleasedatedomesticmarket(Date releasedatedomesticmarket) {
        this.releasedatedomesticmarket = releasedatedomesticmarket;
    }

    public Date getReleasefromdetentionreportdate() {
        return releasefromdetentionreportdate;
    }

    public void setReleasefromdetentionreportdate(Date releasefromdetentionreportdate) {
        this.releasefromdetentionreportdate = releasefromdetentionreportdate;
    }

    public Date getReleaserequestreportdate() {
        return releaserequestreportdate;
    }

    public void setReleaserequestreportdate(Date releaserequestreportdate) {
        this.releaserequestreportdate = releaserequestreportdate;
    }

    public Boolean getRequestfordetentionissuedforportofentry() {
        return requestfordetentionissuedforportofentry;
    }

    public void setRequestfordetentionissuedforportofentry(Boolean requestfordetentionissuedforportofentry) {
        this.requestfordetentionissuedforportofentry = requestfordetentionissuedforportofentry;
    }

    public Boolean getRequestforreleaseissuedforportofentry() {
        return requestforreleaseissuedforportofentry;
    }

    public void setRequestforreleaseissuedforportofentry(Boolean requestforreleaseissuedforportofentry) {
        this.requestforreleaseissuedforportofentry = requestforreleaseissuedforportofentry;
    }

    public Boolean getRequestforsampleissuedforportofentry() {
        return requestforsampleissuedforportofentry;
    }

    public void setRequestforsampleissuedforportofentry(Boolean requestforsampleissuedforportofentry) {
        this.requestforsampleissuedforportofentry = requestforsampleissuedforportofentry;
    }

    public Boolean getRetailer() {
        return retailer;
    }

    public void setRetailer(Boolean retailer) {
        this.retailer = retailer;
    }

    public Boolean getSamplestobecollected() {
        return samplestobecollected;
    }

    public void setSamplestobecollected(Boolean samplestobecollected) {
        this.samplestobecollected = samplestobecollected;
    }

    public Boolean getSamplestobedisposed() {
        return samplestobedisposed;
    }

    public void setSamplestobedisposed(Boolean samplestobedisposed) {
        this.samplestobedisposed = samplestobedisposed;
    }

    public Date getSurveyendtime() {
        return surveyendtime;
    }

    public void setSurveyendtime(Date surveyendtime) {
        this.surveyendtime = surveyendtime;
    }

    public String getSurveylocationtype() {
        return surveylocationtype;
    }

    public void setSurveylocationtype(String surveylocationtype) {
        this.surveylocationtype = surveylocationtype;
    }

    public Date getSurveystarttime() {
        return surveystarttime;
    }

    public void setSurveystarttime(Date surveystarttime) {
        this.surveystarttime = surveystarttime;
    }

    public String getSurveytype() {
        return surveytype;
    }

    public void setSurveytype(String surveytype) {
        this.surveytype = surveytype;
    }

    public String getTypeofestablishment() {
        return typeofestablishment;
    }

    public void setTypeofestablishment(String typeofestablishment) {
        this.typeofestablishment = typeofestablishment;
    }

    public String getTypeofportofentry() {
        return typeofportofentry;
    }

    public void setTypeofportofentry(String typeofportofentry) {
        this.typeofportofentry = typeofportofentry;
    }

    public String getOtherinspectionlocation() {
        return otherinspectionlocation;
    }

    public void setOtherinspectionlocation(String otherinspectionlocation) {
        this.otherinspectionlocation = otherinspectionlocation;
    }

    @XmlTransient
    public List<Documentstandard> getDocumentstandardList() {
        return documentstandardList;
    }

    public void setDocumentstandardList(List<Documentstandard> documentstandardList) {
        this.documentstandardList = documentstandardList;
    }

    public Client getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Client brokerId) {
        this.brokerId = brokerId;
    }

    public Signature getAuthsigfornoticeofdentiondmId() {
        return authsigfornoticeofdentiondmId;
    }

    public void setAuthsigfornoticeofdentiondmId(Signature authsigfornoticeofdentiondmId) {
        this.authsigfornoticeofdentiondmId = authsigfornoticeofdentiondmId;
    }

    public Contact getBrokerrepresentativeId() {
        return brokerrepresentativeId;
    }

    public void setBrokerrepresentativeId(Contact brokerrepresentativeId) {
        this.brokerrepresentativeId = brokerrepresentativeId;
    }

    public Signature getAuthsigfordetentionrequestpoeId() {
        return authsigfordetentionrequestpoeId;
    }

    public void setAuthsigfordetentionrequestpoeId(Signature authsigfordetentionrequestpoeId) {
        this.authsigfordetentionrequestpoeId = authsigfordetentionrequestpoeId;
    }

    public Contact getConsigneerepresentativeId() {
        return consigneerepresentativeId;
    }

    public void setConsigneerepresentativeId(Contact consigneerepresentativeId) {
        this.consigneerepresentativeId = consigneerepresentativeId;
    }

    public Signature getInspectorsigforsamplerequestpoeId() {
        return inspectorsigforsamplerequestpoeId;
    }

    public void setInspectorsigforsamplerequestpoeId(Signature inspectorsigforsamplerequestpoeId) {
        this.inspectorsigforsamplerequestpoeId = inspectorsigforsamplerequestpoeId;
    }

    public Client getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Client consigneeId) {
        this.consigneeId = consigneeId;
    }

    public Employee getEditedbyId() {
        return editedbyId;
    }

    public void setEditedbyId(Employee editedbyId) {
        this.editedbyId = editedbyId;
    }

    public Entrydocumentinspection getEntrydocumentinspectionId() {
        return entrydocumentinspectionId;
    }

    public void setEntrydocumentinspectionId(Entrydocumentinspection entrydocumentinspectionId) {
        this.entrydocumentinspectionId = entrydocumentinspectionId;
    }

    public Employee getAuthemployeefordetentionrequestpoeId() {
        return authemployeefordetentionrequestpoeId;
    }

    public void setAuthemployeefordetentionrequestpoeId(Employee authemployeefordetentionrequestpoeId) {
        this.authemployeefordetentionrequestpoeId = authemployeefordetentionrequestpoeId;
    }

    public Employee getPreparedbyemployeeforreleaserequestpoeId() {
        return preparedbyemployeeforreleaserequestpoeId;
    }

    public void setPreparedbyemployeeforreleaserequestpoeId(Employee preparedbyemployeeforreleaserequestpoeId) {
        this.preparedbyemployeeforreleaserequestpoeId = preparedbyemployeeforreleaserequestpoeId;
    }

    public Address getInspectionaddressId() {
        return inspectionaddressId;
    }

    public void setInspectionaddressId(Address inspectionaddressId) {
        this.inspectionaddressId = inspectionaddressId;
    }

    public Address getSpecifiedreleaselocationId() {
        return specifiedreleaselocationId;
    }

    public void setSpecifiedreleaselocationId(Address specifiedreleaselocationId) {
        this.specifiedreleaselocationId = specifiedreleaselocationId;
    }

    public Employee getApprovedbyemployeeforreleaserequestpoeId() {
        return approvedbyemployeeforreleaserequestpoeId;
    }

    public void setApprovedbyemployeeforreleaserequestpoeId(Employee approvedbyemployeeforreleaserequestpoeId) {
        this.approvedbyemployeeforreleaserequestpoeId = approvedbyemployeeforreleaserequestpoeId;
    }

    public Address getSpecifiedreleaselocationdomesticmarketId() {
        return specifiedreleaselocationdomesticmarketId;
    }

    public void setSpecifiedreleaselocationdomesticmarketId(Address specifiedreleaselocationdomesticmarketId) {
        this.specifiedreleaselocationdomesticmarketId = specifiedreleaselocationdomesticmarketId;
    }

    public Employee getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Employee inspectorId) {
        this.inspectorId = inspectorId;
    }

    public Employee getAuthempfornoticeofreleasefromdentiondmId() {
        return authempfornoticeofreleasefromdentiondmId;
    }

    public void setAuthempfornoticeofreleasefromdentiondmId(Employee authempfornoticeofreleasefromdentiondmId) {
        this.authempfornoticeofreleasefromdentiondmId = authempfornoticeofreleasefromdentiondmId;
    }

    public Signature getPreparedbysigforreleaserequestpoeId() {
        return preparedbysigforreleaserequestpoeId;
    }

    public void setPreparedbysigforreleaserequestpoeId(Signature preparedbysigforreleaserequestpoeId) {
        this.preparedbysigforreleaserequestpoeId = preparedbysigforreleaserequestpoeId;
    }

    public Employee getAuthemployeefornoticeofdentiondmId() {
        return authemployeefornoticeofdentiondmId;
    }

    public void setAuthemployeefornoticeofdentiondmId(Employee authemployeefornoticeofdentiondmId) {
        this.authemployeefornoticeofdentiondmId = authemployeefornoticeofdentiondmId;
    }

    public Client getRetailoutletId() {
        return retailoutletId;
    }

    public void setRetailoutletId(Client retailoutletId) {
        this.retailoutletId = retailoutletId;
    }

    public Contact getRetailrepresentativeId() {
        return retailrepresentativeId;
    }

    public void setRetailrepresentativeId(Contact retailrepresentativeId) {
        this.retailrepresentativeId = retailrepresentativeId;
    }

    public Address getLocationofdetainedproductdomesticmarketId() {
        return locationofdetainedproductdomesticmarketId;
    }

    public void setLocationofdetainedproductdomesticmarketId(Address locationofdetainedproductdomesticmarketId) {
        this.locationofdetainedproductdomesticmarketId = locationofdetainedproductdomesticmarketId;
    }

    public Signature getApprovedbysigforreleaserequestpoeId() {
        return approvedbysigforreleaserequestpoeId;
    }

    public void setApprovedbysigforreleaserequestpoeId(Signature approvedbysigforreleaserequestpoeId) {
        this.approvedbysigforreleaserequestpoeId = approvedbysigforreleaserequestpoeId;
    }

    public Signature getAuthsigfornoticeofreleasefromdentiondmId() {
        return authsigfornoticeofreleasefromdentiondmId;
    }

    public void setAuthsigfornoticeofreleasefromdentiondmId(Signature authsigfornoticeofreleasefromdentiondmId) {
        this.authsigfornoticeofreleasefromdentiondmId = authsigfornoticeofreleasefromdentiondmId;
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
        if (!(object instanceof Compliancesurvey)) {
            return false;
        }
        Compliancesurvey other = (Compliancesurvey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Compliancesurvey[ id=" + id + " ]";
    }
    
}
