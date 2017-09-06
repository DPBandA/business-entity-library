/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "documentreport")
@NamedQueries({
    @NamedQuery(name = "findAllDocumentReports", query = "SELECT d FROM DocumentReport d ORDER BY d.name")
})
public class DocumentReport implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    @Column(length = 1024)
    private String sqlText;
    // Show/hide fields
    private Boolean showType = false;
    private Boolean showNumber = false;
    private Boolean showDateReceived = false;
    private Boolean showRequestingDepartment = false;
    private Boolean showResponsibleOfficer = false;
    private Boolean showSubmittedBy = false;
    private Boolean showDescription = false;
    private Boolean showNotes = false;
    private Boolean showExpectedDateOfCompletion = false;
    private Boolean showDateOfCompletion = false;
    private Boolean showUrl = false;
    private Boolean showClassification = false;
    private Boolean showComments = false;
    private Boolean showWorkPerformedOnDocument = false;
    private Boolean showDocumentForm = false;
    private Boolean showSequenceNumber = false;
    private Boolean showMonthReceived = false;
    private Boolean showTurnAroundTime = false;
    private Boolean showCurrentDocumentTurnaroundTime = false;
    private Boolean showNumberOfDocuments = false;
    @Column(length = 1024)
    private String columnsToExclude = "";

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getColumnsToExclude() {
        return columnsToExclude;
    }

    public void setColumnsToExclude(String columnsToExclude) {
        this.columnsToExclude = columnsToExclude;
    }

    public Boolean getShowNumberOfDocuments() {
        return showNumberOfDocuments;
    }

    public void setShowNumberOfDocuments(Boolean showNumberOfDocuments) {
        this.showNumberOfDocuments = showNumberOfDocuments;
    }

    public Boolean getShowCurrentDocumentTurnaroundTime() {
        return showCurrentDocumentTurnaroundTime;
    }

    public void setShowCurrentDocumentTurnaroundTime(Boolean showCurrentDocumentTurnaroundTime) {
        this.showCurrentDocumentTurnaroundTime = showCurrentDocumentTurnaroundTime;
    }

    public Boolean getShowClassification() {
        return showClassification;
    }

    public void setShowClassification(Boolean showClassification) {
        this.showClassification = showClassification;
    }

    public Boolean getShowComments() {
        return showComments;
    }

    public void setShowComments(Boolean showComments) {
        this.showComments = showComments;
    }

    public Boolean getShowDateOfCompletion() {
        return showDateOfCompletion;
    }

    public void setShowDateOfCompletion(Boolean showDateOfCompletion) {
        this.showDateOfCompletion = showDateOfCompletion;
    }

    public Boolean getShowDateReceived() {
        return showDateReceived;
    }

    public void setShowDateReceived(Boolean showDateReceived) {
        this.showDateReceived = showDateReceived;
    }

    public Boolean getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(Boolean showDescription) {
        this.showDescription = showDescription;
    }

    public Boolean getShowDocumentForm() {
        return showDocumentForm;
    }

    public void setShowDocumentForm(Boolean showDocumentForm) {
        this.showDocumentForm = showDocumentForm;
    }

    public Boolean getShowExpectedDateOfCompletion() {
        return showExpectedDateOfCompletion;
    }

    public void setShowExpectedDateOfCompletion(Boolean showExpectedDateOfCompletion) {
        this.showExpectedDateOfCompletion = showExpectedDateOfCompletion;
    }

    public Boolean getShowMonthReceived() {
        return showMonthReceived;
    }

    public void setShowMonthReceived(Boolean showMonthReceived) {
        this.showMonthReceived = showMonthReceived;
    }

    public Boolean getShowNotes() {
        return showNotes;
    }

    public void setShowNotes(Boolean showNotes) {
        this.showNotes = showNotes;
    }

    public Boolean getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(Boolean showNumber) {
        this.showNumber = showNumber;
    }

    public Boolean getShowRequestingDepartment() {
        return showRequestingDepartment;
    }

    public void setShowRequestingDepartment(Boolean showRequestingDepartment) {
        this.showRequestingDepartment = showRequestingDepartment;
    }

    public Boolean getShowResponsibleOfficer() {
        return showResponsibleOfficer;
    }

    public void setShowResponsibleOfficer(Boolean showResponsibleOfficer) {
        this.showResponsibleOfficer = showResponsibleOfficer;
    }

    public Boolean getShowSequenceNumber() {
        return showSequenceNumber;
    }

    public void setShowSequenceNumber(Boolean showSequenceNumber) {
        this.showSequenceNumber = showSequenceNumber;
    }

    public Boolean getShowSubmittedBy() {
        return showSubmittedBy;
    }

    public void setShowSubmittedBy(Boolean showSubmittedBy) {
        this.showSubmittedBy = showSubmittedBy;
    }

    public Boolean getShowTurnAroundTime() {
        return showTurnAroundTime;
    }

    public void setShowTurnAroundTime(Boolean showTurnAroundTime) {
        this.showTurnAroundTime = showTurnAroundTime;
    }

    public Boolean getShowType() {
        return showType;
    }

    public void setShowType(Boolean showType) {
        this.showType = showType;
    }

    public Boolean getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(Boolean showUrl) {
        this.showUrl = showUrl;
    }

    public Boolean getShowWorkPerformedOnDocument() {
        return showWorkPerformedOnDocument;
    }

    public void setShowWorkPerformedOnDocument(Boolean showWorkPerformedOnDocument) {
        this.showWorkPerformedOnDocument = showWorkPerformedOnDocument;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
        if (!(object instanceof DocumentReport)) {
            return false;
        }
        DocumentReport other = (DocumentReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.DocumentReport[id=" + id + "]";
    }

    public static List<DocumentReport> findAllDocumentReports(EntityManager em) {

        try {
            List<DocumentReport> reports = em.createNamedQuery("findAllDocumentReports", DocumentReport.class).getResultList();
            return reports;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static DocumentReport findDocumentReportById(EntityManager em, Long Id) {

        try {
            DocumentReport report = em.find(DocumentReport.class, Id);
            return report;
        } catch (Exception e) {

            return null;
        }
    }
    
    
    public static DocumentReport findDocumentReportByName(EntityManager em, String documentReportName) {

        try {
            String newDocumentReportName = documentReportName.trim().replaceAll("'", "''");

            List<DocumentReport> reports = em.createQuery("SELECT d FROM DocumentReport d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + newDocumentReportName.toUpperCase() + "'", DocumentReport.class).getResultList();
            if (reports.size() > 0) {
                return reports.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
