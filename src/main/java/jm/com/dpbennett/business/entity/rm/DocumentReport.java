/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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

package jm.com.dpbennett.business.entity.rm;

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
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "documentreport")
@NamedQueries({
    @NamedQuery(name = "findAllDocumentReports", query = "SELECT d FROM DocumentReport d ORDER BY d.name")
})
public class DocumentReport implements BusinessEntity {

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
    @Transient
    private Boolean isDirty;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
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
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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
    
    
    public static DocumentReport findDocumentReportByName(
            EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
           
            List<DocumentReport> reports = em.createQuery("SELECT d FROM DocumentReport d "
                    + "WHERE UPPER(d.name) "
                    + "= '" + value.toUpperCase() + "'", DocumentReport.class).getResultList();
            if (!reports.isEmpty()) {
                return reports.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
