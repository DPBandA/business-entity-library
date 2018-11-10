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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "purchaserequisition")
@NamedQueries({
    @NamedQuery(name = "findAllPurchaseRequisitions", 
            query = "SELECT p FROM PurchaseRequisition p ORDER BY p.number")
})
@XmlRootElement
public class PurchaseRequisition implements Document, Serializable, Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private DocumentType type;
    private String number;
    private Boolean autoGenerateNumber;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department originatingDepartment;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department purchasingDepartment;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee procurementOfficer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee originator;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> approvers;
    @Column(length = 1024)
    private String description;
    @Column(length = 1024)
    private String notes;
    @Column(length = 1024)
    private String terms;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date requisitionDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedDateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRequired;
    private String url;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Classification classification;
    @Column(length = 1024)
    private String comments;
    private Long sequenceNumber;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Supplier supplier;
    @Column(length = 1024)
    private String status;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    private String priorityCode;
    private Boolean onHandNow;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<CostComponent> costComponents;
    private String purchaseOrderNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date purchaseOrderDate;
    private String quotationNumber;
    @Transient
    private Boolean isDirty;
    @Transient
    private Boolean visited;
    @Transient
    private List<BusinessEntity.Action> actions;

    /**
     * Default constructor.
     */
    public PurchaseRequisition() {
        approvers = new ArrayList<>();
        costComponents = new ArrayList<>();
        actions = new ArrayList<>();

        actions.add(BusinessEntity.Action.CREATE);
    }

    public String getQuotationNumber() {
        return quotationNumber;
    }

    public void setQuotationNumber(String quotationNumber) {
        this.quotationNumber = quotationNumber;
    }

    public List<BusinessEntity.Action> getActions() {
        return actions;
    }

    public void setActions(List<BusinessEntity.Action> actions) {
        this.actions = actions;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Date getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(Date purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public List<CostComponent> getCostComponents() {
        if (costComponents == null) {
            costComponents = new ArrayList<>();
        }
        return costComponents;
    }

    public void setCostComponents(List<CostComponent> costComponents) {
        this.costComponents = costComponents;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Date getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(Date dateRequired) {
        this.dateRequired = dateRequired;
    }

    public Boolean getOnHandNow() {
        return onHandNow;
    }

    public void setOnHandNow(Boolean onHandNow) {
        this.onHandNow = onHandNow;
    }

    public String getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    public List<Employee> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<Employee> approvers) {
        this.approvers = approvers;
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

    public Boolean getVisited() {
        if (visited == null) {
            visited = false;
        }
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    private Boolean getCompleted() {
        return dateOfCompletion != null;
    }

    public String getRowStyle() {

        if (getVisited()) {
            visited = false;
            return "lightgreybg";
        } else if (getCompleted()) {
            return "lightgreenbg";
        } else if (getExpectedDateOfCompletion() != null) {
            if (BusinessEntityUtils.getNow().compareTo(getExpectedDateOfCompletion()) >= 0) {
                // Due or overdue
                return "orangeredbg";
            } else if (BusinessEntityUtils.getNow().compareTo(BusinessEntityUtils.adjustDate(getExpectedDateOfCompletion(), Calendar.DATE, -3)) >= 0) {
                // Soon due 
                return "yellowbg";
            } else {
                // It's all good!
                return "";
            }

        } else { // EDOC possibly not set so warn
            return "lightyellowbg";
        }
    }

    public Employee getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }
        return editedBy;
    }

    public void setEditedBy(Employee employee) {
        editedBy = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Department getOriginatingDepartment() {
        if (originatingDepartment == null) {
            originatingDepartment = new Department();
        }

        return originatingDepartment;
    }

    public void setOriginatingDepartment(Department originatingDepartment) {
        this.originatingDepartment = originatingDepartment;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Department getPurchasingDepartment() {
        if (purchasingDepartment == null) {
            return new Department();
        }
        return purchasingDepartment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPurchasingDepartment(Department purchasingDepartment) {
        this.purchasingDepartment = purchasingDepartment;
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

    public Date getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionDate(Date requisitionDate) {
        this.requisitionDate = requisitionDate;
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

    public Employee getProcurementOfficer() {
        if (procurementOfficer == null) {
            return new Employee();
        }
        return procurementOfficer;
    }

    public void setProcurementOfficer(Employee procurementOfficer) {
        this.procurementOfficer = procurementOfficer;
    }

    public Employee getOriginator() {
        if (originator == null) {
            return new Employee();
        }
        return originator;
    }

    public void setOriginator(Employee originator) {
        this.originator = originator;
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
        if (!(object instanceof PurchaseRequisition)) {
            return false;
        }
        PurchaseRequisition other = (PurchaseRequisition) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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

    public Integer getCurrentDocumentActualTurnaroundTime() {
        if (requisitionDate != null && dateOfCompletion != null) {
            return BusinessEntityUtils.calculatePeriodInWorkingDays(requisitionDate, dateOfCompletion);
        } else {
            return 0;
        }
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static List<PurchaseRequisition> findGroupedLegalDocumentsByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            Date startDate,
            Date endDate) {

        List<PurchaseRequisition> foundDocuments;
        String searchQuery = null;

        switch (searchType) {
            case "General":
                searchQuery
                        = "SELECT new jm.com.dpbennett.entity.LegalDocument(doc.type, COUNT(doc.type)) FROM LegalDocument doc"
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
            foundDocuments = em.createQuery(searchQuery, PurchaseRequisition.class).getResultList();
            if (foundDocuments == null) {
                foundDocuments = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundDocuments;
    }

    public static List<PurchaseRequisition> findLegalDocumentsByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate) {

        List<PurchaseRequisition> foundDocuments;
        String searchQuery = null;
        String searchTextAndClause = "";
        String searchText = originalSearchText.replaceAll("'", "''");

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
                        = "SELECT doc FROM LegalDocument doc"
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
                        = "SELECT doc FROM LegalDocument doc"
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
            foundDocuments = em.createQuery(searchQuery, PurchaseRequisition.class).getResultList();
            if (foundDocuments == null) {
                foundDocuments = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundDocuments;
    }

    public static PurchaseRequisition findLegalDocumentById(EntityManager em, Long Id) {

        return em.find(PurchaseRequisition.class, Id);
    }

    public static List<PurchaseRequisition> findAllLegalDocuments(EntityManager em) {

        try {
            return em.createNamedQuery("findAllLegalDocuments", PurchaseRequisition.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String getLegalDocumentNumber(PurchaseRequisition legalDocument, String prefix) {
        String number = prefix;

        // append department code
        if (legalDocument.getPurchasingDepartment().getSubGroupCode() != null) {
            number = number + legalDocument.getPurchasingDepartment().getSubGroupCode();
        } else {
            number = number + "?";
        }
        // append doc type
        if (legalDocument.getType() != null) {
            number = number + "_" + legalDocument.getType().getCode();
        }
        // append doc form
//        if (legalDocument.getDocumentForm() != null) {
//            number = number + "/" + legalDocument.getDocumentForm();
//        }
        // append doc seq
        if (legalDocument.getSequenceNumber() != null) {
            NumberFormat formatter = DecimalFormat.getIntegerInstance();
            formatter.setMinimumIntegerDigits(2);
            number = number + "_" + formatter.format(legalDocument.getSequenceNumber());
        } else {
            number = number + "_?";
        }
        // append month in the form (MMM) and year in the form (YY).
        if (legalDocument.getRequisitionDate() != null) {
            number = number + "/" + BusinessEntityUtils.getMonthShortFormat(legalDocument.getRequisitionDate())
                    + BusinessEntityUtils.getYearShortFormat(legalDocument.getRequisitionDate(), 2);
        }

        return number;
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

        return new ReturnMessage(false, "Document not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
