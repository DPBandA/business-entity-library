/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import jm.com.dpba.business.entity.utils.BusinessEntityUtils;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "documenttracking")
@NamedQueries({
    @NamedQuery(name = "findAllDocumentTrackings", query = "SELECT d FROM DocumentTracking d ORDER BY d.number")
})
public class DocumentTracking implements Document, Serializable, Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private DocumentType type;
    private String number;
    private Boolean autoGenerateNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateReceived;
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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedDateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfCompletion;
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
    @OneToOne(cascade = CascadeType.ALL)
    private Client externalClient;
    @Column(length = 1024)
    private String goal;
    @Column(length = 1024)
    private String status;
    private String priorityLevel;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;

    public DocumentTracking() {
    }

    public Employee getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }
        return editedBy;
    }
    
    public void setEditedBy(Employee employee) {
       editedBy =  employee;
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
        Calendar c = Calendar.getInstance();

        if (dateReceived != null) {
            c.setTime(dateReceived);
            yearReceived = c.get(Calendar.YEAR);
        }
        return yearReceived;
    }

    public void setYearReceived(Integer yearReceived) {
        this.yearReceived = yearReceived;
    }

    public Client getExternalClient() {
        if (externalClient == null) {
            return new Client("");
        }
        return externalClient;
    }

    public void setExternalClient(Client externalClient) {
        this.externalClient = externalClient;
    }

    public DocumentTracking(DocumentType type, Long numberOfDocuments) {
        this.type = type;
        this.numberOfDocuments = numberOfDocuments;
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
            requestingDepartment = new Department();
        }

        return requestingDepartment;
    }

    public void setRequestingDepartment(Department requestingDepartment) {
        this.requestingDepartment = requestingDepartment;
    }

    public void saveDocument() {
    }

    public Integer getMonthReceived() {
        Calendar c = Calendar.getInstance();

        if (dateReceived != null) {
            c.setTime(dateReceived);
            monthReceived = c.get(Calendar.MONTH);
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

    public String getComments() {
        return comments;
    }

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

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpectedDateOfCompletion() {
        return expectedDateOfCompletion;
    }

    public void setExpectedDateOfCompletion(Date expectedDateOfCompletion) {
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
    public DocumentType getType() {
        if (type == null) {
            return new DocumentType();
        }
        return type;
    }

    @Override
    public void setType(DocumentType type) {
        this.type = type;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentTracking)) {
            return false;
        }
        DocumentTracking other = (DocumentTracking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.Document[id=" + id + "]";
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
        //Integer workingDays = null;


        // if exp. date not set just return null
        if (getDateReceived() == null) {
            return null;
        }
        // if date of completion not set then use current date
        if (getDateOfCompletion() == null) {
            return null;
        }

        return BusinessEntityUtils.calculatePeriodInWorkingDays(dateReceived, dateOfCompletion);
    }
   
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static List<DocumentTracking> findGroupedDocumentTrackingsByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            Date startDate,
            Date endDate) {

        List<DocumentTracking> foundDocuments = null;
        String searchQuery = null;

        if (searchType.equals("General")) {

            searchQuery =
                    "SELECT new jm.com.dpbennett.entity.DocumentTracking(doc.type, COUNT(doc.type)) FROM DocumentTracking doc"
                    + " WHERE (doc." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                    + " AND doc." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                    + " GROUP BY doc.type";
        } else if (searchType.equals("My documents")) {
        } else if (searchType.equals("My department's documents")) {
        }

        try {
            foundDocuments = em.createQuery(searchQuery, DocumentTracking.class).getResultList();
            if (foundDocuments == null) {
                foundDocuments = new ArrayList<DocumentTracking>();
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

        List<DocumentTracking> foundDocuments = null;
        String searchQuery = null;
        String searchTextAndClause = "";
        String searchText = originalSearchText.replaceAll("'", "''");

        if (searchType.equals("General")) {
            if (!searchText.equals("")) {
                searchTextAndClause =
                        " AND ("
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
            searchQuery =
                    "SELECT doc FROM DocumentTracking doc"
                    + " JOIN doc.responsibleDepartment responsibleDepartment"
                    + " JOIN doc.responsibleOfficer responsibleOfficer"
                    + " JOIN doc.submittedBy submittedBy"
                    + " JOIN doc.classification classification"
                    + " WHERE (doc." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                    + " AND doc." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                    + searchTextAndClause
                    + " ORDER BY doc.dateReceived DESC";
        } else if (searchType.equals("By type")) {
            searchTextAndClause =
                    " AND ("
                    + " UPPER(t.name) = '" + searchText.toUpperCase() + "'"
                    + " )";
            searchQuery =
                    "SELECT doc FROM DocumentTracking doc"
                    + " JOIN doc.type t"
                    + " WHERE (doc." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                    + " AND doc." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                    + searchTextAndClause
                    + " ORDER BY doc.dateReceived DESC";
        } else if (searchType.equals("My department's documents")) {
        }

        try {
            foundDocuments = em.createQuery(searchQuery, DocumentTracking.class).getResultList();
            if (foundDocuments == null) {
                foundDocuments = new ArrayList<DocumentTracking>();
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

        // append department code
        if (documentTracking.getResponsibleDepartment().getSubGroupCode() != null) {
            number = number + documentTracking.getResponsibleDepartment().getSubGroupCode();
        } else {
            number = number + "?";
        }
        // append doc type
        if (documentTracking.getType() != null) {
            number = number + "_" + documentTracking.getType().getCode();
        }
        // append doc form
        if (documentTracking.getDocumentForm() != null) {
            number = number + "/" + documentTracking.getDocumentForm();
        }
        // append doc seq
        if (documentTracking.getSequenceNumber() != null) {
            NumberFormat formatter = DecimalFormat.getIntegerInstance();
            formatter.setMinimumIntegerDigits(2);
            number = number + "_" + formatter.format(documentTracking.getSequenceNumber());
        } else {
            number = number + "_?";
        }
        // append month in the form (MMM) and year in the form (YY).
        if (documentTracking.getDateReceived() != null) {
            number = number + "/" + BusinessEntityUtils.getMonthShortFormat(documentTracking.getDateReceived())
                    + BusinessEntityUtils.getYearShortFormat(documentTracking.getDateReceived(), 2);
        }

        return number;
    }
}
