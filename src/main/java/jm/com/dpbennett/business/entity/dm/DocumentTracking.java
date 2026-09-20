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
package jm.com.dpbennett.business.entity.dm;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.fm.Classification;
import java.io.Serializable;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "documenttracking")
@NamedQueries({
    @NamedQuery(name = "findAllDocumentTrackings", query = "SELECT d FROM DocumentTracking d ORDER BY d.number")
})
public class DocumentTracking implements Document, Serializable, Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    private static final System.Logger LOG = System.getLogger(DocumentTracking.class.getName());
    public static List<DocumentTracking> findGroupedDocumentTrackingsByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            Date startDate,
            Date endDate) {
        
        List<DocumentTracking> foundDocuments;
        String searchQuery = null;
        
        switch (searchType) {
            case "General":
                searchQuery
                        = "SELECT new jm.com.dpbennett.entity.DocumentTracking(doc.type, COUNT(doc.type)) FROM DocumentTracking doc"
                        + " WHERE (doc." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND doc." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + " GROUP BY doc.type";
                break;
            case "My documents":
                break;
            case "My department's documents":
                break;
            default:
                break;
        }
        
        try {
            foundDocuments = em.createQuery(searchQuery, DocumentTracking.class).getResultList();
            if (foundDocuments == null) {
                foundDocuments = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        return foundDocuments;
    }
    public static List<DocumentTracking> findDocumentTrackingsByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate) {
        
        List<DocumentTracking> foundDocuments;
        String searchQuery = null;
        String searchTextAndClause = "";
        String searchText = originalSearchText;
        
        switch (searchType) {
            case "General":
                if (!searchText.equals("")) {
                    searchTextAndClause
                            = " AND ("
                            + " UPPER(doc.number) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(responsibleDepartment.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(responsibleOfficer.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(responsibleOfficer.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(submittedBy.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(submittedBy.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.description) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.notes) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.status) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.priorityLevel) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.url) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.workPerformedOnDocument) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.documentForm) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " )";
                }
                searchQuery
                        = "SELECT doc FROM DocumentTracking doc"
                        + " JOIN doc.responsibleDepartment responsibleDepartment"
                        + " JOIN doc.responsibleOfficer responsibleOfficer"
                        + " JOIN doc.submittedBy submittedBy"
                        + " JOIN doc.classification classification"
                        + " WHERE (doc." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND doc." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY doc.dateReceived DESC";
                break;
            case "By type":
                searchTextAndClause
                        = " AND ("
                        + " UPPER(t.name) = '" + searchText.toUpperCase() + "'"
                        + " )";
                searchQuery
                        = "SELECT doc FROM DocumentTracking doc"
                        + " JOIN doc.type t"
                        + " WHERE (doc." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND doc." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY doc.dateReceived DESC";
                break;
            case "My department's documents":
                break;
            default:
                break;
        }
        
        try {
            foundDocuments = em.createQuery(searchQuery, DocumentTracking.class).getResultList();
            if (foundDocuments == null) {
                foundDocuments = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        return foundDocuments;
    }
    public static DocumentTracking findDocumentTrackingById(EntityManager em, Long Id) {
        
        return em.find(DocumentTracking.class, Id);
    }
    public static List<DocumentTracking> findAllDocumentTrackings(EntityManager em) {
        
        try {
            return em.createNamedQuery("findAllDocumentTrackings", DocumentTracking.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static String getDocumentTrackingNumber(DocumentTracking documentTracking, String prefix) {
        String number = prefix;
        
        if (documentTracking.getResponsibleDepartment().getCode() != null) {
            number = number + documentTracking.getResponsibleDepartment().getCode();
        } else {
            number = number + "?";
        }
        if (documentTracking.getDocumentType() != null) {
            number = number + "_" + documentTracking.getDocumentType().getCode();
        }
        if (documentTracking.getDocumentForm() != null) {
            number = number + "/" + documentTracking.getDocumentForm();
        }
        if (documentTracking.getSequenceNumber() != null) {
            NumberFormat formatter = DecimalFormat.getIntegerInstance();
            formatter.setMinimumIntegerDigits(2);
            number = number + "_" + formatter.format(documentTracking.getSequenceNumber());
        } else {
            number = number + "_?";
        }
        if (documentTracking.getDateReceived() != null) {
            number = number + "/" + BusinessEntityUtils.getMonthShortFormat(documentTracking.getDateReceived())
                    + BusinessEntityUtils.getYearShortFormat(documentTracking.getDateReceived(), 2);
        }
        
        return number;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private DocumentType documentType;
    private String number;
    private Boolean autoGenerateNumber;
    private LocalDateTime dateReceived;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department requestingDepartment;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department responsibleDepartment;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee responsibleOfficer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee submittedBy;
    @Column(length = 1024)
    private String description;
    @Column(length = 1024)
    private String notes;
    private LocalDateTime expectedDateOfCompletion;
    private LocalDateTime dateOfCompletion;
    private String url;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Classification classification;
    @Column(length = 1024)
    private String comments;
    private String workPerformedOnDocument;
    private String documentForm;
    private Long sequenceNumber;
    private Integer monthReceived;
    private Integer yearReceived;
    private Integer turnAroundTime;
    private Long numberOfDocuments;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client externalClient;
    @Column(length = 1024)
    private String goal;
    @Column(length = 1024)
    private String status;
    private String priorityLevel;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @Transient
    private Boolean isDirty;

    public DocumentTracking() {
    }
    public DocumentTracking(DocumentType documentType, Long numberOfDocuments) {
        this.documentType = documentType;
        this.numberOfDocuments = numberOfDocuments;
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
    public Employee getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }

        return editedBy;
    }

    @Override
    public void setEditedBy(Person employee) {
        editedBy = (Employee) employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Integer getYearReceived() {
        if (dateReceived != null) {
            yearReceived = dateReceived.getYear();
        }
        return yearReceived;
    }

    public void setYearReceived(Integer yearReceived) {
        this.yearReceived = yearReceived;
    }

    public Client getExternalClient() {
        if (externalClient == null) {
            return new Client();
        }

        return externalClient;
    }

    public void setExternalClient(Client externalClient) {
        this.externalClient = externalClient;
    }


    public Long getNumberOfDocuments() {
        return numberOfDocuments;
    }

    public void setNumberOfDocuments(Long numberOfDocuments) {
        this.numberOfDocuments = numberOfDocuments;
    }

    public Integer getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(Integer turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public Boolean getAutoGenerateNumber() {
        if (autoGenerateNumber == null) {
            autoGenerateNumber = false;
        }
        return autoGenerateNumber;
    }

    public void setAutoGenerateNumber(Boolean autoGenerateNumber) {
        this.autoGenerateNumber = autoGenerateNumber;
    }

    public Department getRequestingDepartment() {
        if (requestingDepartment == null) {
            return new Department();
        }

        return requestingDepartment;
    }

    public void setRequestingDepartment(Department requestingDepartment) {
        this.requestingDepartment = requestingDepartment;
    }

    public Integer getMonthReceived() {
        if (dateReceived != null) {
            monthReceived = dateReceived.getMonthValue() - 1;
        }
        return monthReceived;
    }

    public void setMonthReceived(Integer monthReceived) {
        this.monthReceived = monthReceived;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getDocumentForm() {
        return documentForm;
    }

    public void setDocumentForm(String documentForm) {
        this.documentForm = documentForm;
    }

    public Department getResponsibleDepartment() {
        if (responsibleDepartment == null) {
            return new Department();
        }

        return responsibleDepartment;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getWorkPerformedOnDocument() {
        return workPerformedOnDocument;
    }

    public void setWorkPerformedOnDocument(String workPerformedOnDocument) {
        this.workPerformedOnDocument = workPerformedOnDocument;
    }

    public void setResponsibleDepartment(Department responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDateTime dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public LocalDateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public LocalDateTime getExpectedDateOfCompletion() {
        return expectedDateOfCompletion;
    }

    public void setExpectedDateOfCompletion(LocalDateTime expectedDateOfCompletion) {
        this.expectedDateOfCompletion = expectedDateOfCompletion;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getResponsibleOfficer() {

        if (responsibleOfficer == null) {
            return new Employee();
        }

        return responsibleOfficer;
    }

    public void setResponsibleOfficer(Employee responsibleOfficer) {
        this.responsibleOfficer = responsibleOfficer;
    }

    public Employee getSubmittedBy() {

        if (submittedBy == null) {
            return new Employee();
        }

        return submittedBy;
    }

    public void setSubmittedBy(Employee submittedBy) {
        this.submittedBy = submittedBy;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DocumentTracking)) {
            return false;
        }
        DocumentTracking other = (DocumentTracking) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "jm.com.dpbennett.entity.Document[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.toString(), o.toString());
    }

    @Override
    public Classification getClassification() {
        if (classification == null) {
            return new Classification();
        }

        return classification;
    }

    @Override
    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Integer getCurrentDocumentTurnaroundTime() {

        if (getDateReceived() == null) {
            return null;
        }
        if (getDateOfCompletion() == null) {
            return null;
        }

        return BusinessEntityUtils.calculatePeriodInWorkingDays(dateReceived, dateOfCompletion);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {

    }


    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            if (documentType != null) {
                documentType.save(em);
            }

            if (requestingDepartment != null) {
                requestingDepartment.save(em);
            }

            if (responsibleDepartment != null) {
                responsibleDepartment.save(em);
            }

            if (responsibleOfficer != null) {
                responsibleOfficer.save(em);
            }

            if (submittedBy != null) {
                submittedBy.save(em);
            }

            if (classification != null) {
                classification.save(em);
            }

            if (externalClient.getId() != null) {
                externalClient.save(em);
            }

            if (editedBy != null) {
                editedBy.save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Document Standard not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public DocumentType getDocumentType() {

        if (documentType == null) {
            return new DocumentType();
        }

        return documentType;
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
    public void setType(String type) {
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
    public ReturnMessage delete(EntityManager em) {
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
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
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
