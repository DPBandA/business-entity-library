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
package jm.com.dpbennett.business.entity.dm;

import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.fm.Classification;
import java.text.Collator;
import java.util.ArrayList;
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
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "documentstandard")
@NamedQueries({
    @NamedQuery(name = "findAllDocumentStandards", query = "SELECT d FROM DocumentStandard d ORDER BY d.number")
})
public class DocumentStandard implements Document, Comparable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @OneToOne(cascade = CascadeType.REFRESH)
    private DocumentType documentType;
    private String number;
    private String enforcement;
    private Boolean autoGenerateNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRevised;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateConfirmed;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePublished;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRevisionDue;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdited;
    private String url;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Classification classification;
    @Column(length = 1024)
    private String notes;
    @Column(length = 1024)
    private String comments;
    @Column(length = 1024)
    private String synopsis;
    @Column(length = 1024)
    private String status;
    private String workPerformedOnDocument;
    private String documentForm;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    private Long sequenceNumber;
    private String name;
    private Boolean active;
    @Transient
    private Boolean isDirty;

    public DocumentStandard() {
        this.name = "";
        this.active = true;
    }

    public DocumentStandard(String name) {
        this.name = name;
        this.active = true;
    }

    public DocumentStandard(DocumentType documentType) {
        this.documentType = documentType;
        this.name = "";
        this.active = true;
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
    public Boolean getActive() {
        if (active == null) {
            active = false;
        }
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public Date getDateEntered() {
        return dateEntered;
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
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
        return editedBy;
    }

    public void setEditedBy(Employee editedBy) {
        this.editedBy = editedBy;
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

    @Override
    public String getComments() {
        if (comments == null) {
            comments = "";
        }
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof DocumentStandard)) {
            return false;
        }
        DocumentStandard other = (DocumentStandard) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "DocumentStandard[name=" + getName() + "]";
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

    @Override
    public String getDescription() {
        return synopsis;
    }

    @Override
    public DocumentType getDocumentType() {
        if (documentType == null) {
            return new DocumentType();
        }

        return documentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnforcement() {
        return enforcement;
    }

    public void setEnforcement(String enforcement) {
        this.enforcement = enforcement;
    }

    public Date getDateRevised() {
        return dateRevised;
    }

    public void setDateRevised(Date dateRevised) {
        this.dateRevised = dateRevised;
    }

    public Date getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(Date dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public Date getDateRevisionDue() {
        return dateRevisionDue;
    }

    public void setDateRevisionDue(Date dateRevisionDue) {
        this.dateRevisionDue = dateRevisionDue;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static DocumentStandard findActiveByName(EntityManager em, String value,
            Boolean ignoreCase) {

        List<DocumentStandard> documentStandards;

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            if (ignoreCase) {
                documentStandards = em.createQuery("SELECT d FROM DocumentStandard d "
                        + "WHERE UPPER(d.name) "
                        + "= '" + value.toUpperCase() + "'"
                        + " AND d.active = 1", DocumentStandard.class).getResultList();
            } else {
                documentStandards = em.createQuery("SELECT d FROM DocumentStandard d "
                        + "WHERE d.name "
                        + "= '" + value + "'"
                        + " AND d.active = 1", DocumentStandard.class).getResultList();
            }

            if (!documentStandards.isEmpty()) {
                return documentStandards.get(0);
            }

            return null;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static DocumentStandard findByName(EntityManager em, String value,
            Boolean ignoreCase) {

        List<DocumentStandard> documentStandards;

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            if (ignoreCase) {
                documentStandards = em.createQuery("SELECT d FROM DocumentStandard d "
                        + "WHERE UPPER(d.name) "
                        + "= '" + value.toUpperCase() + "'",
                        DocumentStandard.class).getResultList();
            } else {
                documentStandards = em.createQuery("SELECT d FROM DocumentStandard d "
                        + "WHERE d.name "
                        + "= '" + value + "'", 
                        DocumentStandard.class).getResultList();
            }

            if (!documentStandards.isEmpty()) {
                return documentStandards.get(0);
            }

            return null;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<DocumentStandard> findByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate) {

        List<DocumentStandard> foundDocuments;
        String searchQuery = null;
        String searchTextAndClause = "";
        String searchText = originalSearchText;
        switch (searchType) {
            case "General":
                if (!searchText.equals("")) {
                    searchTextAndClause
                            = " AND ("
                            + " UPPER(doc.number) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.notes) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.url) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(doc.documentForm) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " )";
                }
                searchQuery
                        = "SELECT doc FROM DocumentStandard doc"
                        + " JOIN doc.classification classification"
                        + " WHERE (doc." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND doc." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY doc." + dateSearchField + " DESC";
                break;
            case "By type":
                searchTextAndClause
                        = " AND ("
                        + " UPPER(t.name) = '" + searchText.toUpperCase() + "'"
                        + " )";
                searchQuery
                        = "SELECT doc FROM DocumentStandard doc"
                        + " JOIN doc.documentType t"
                        + " WHERE (doc." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND doc." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY doc." + dateSearchField + " DESC";
                break;
        }

        try {
            foundDocuments = em.createQuery(searchQuery, DocumentStandard.class).getResultList();
            if (foundDocuments == null) {
                foundDocuments = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundDocuments;
    }

    public static List<DocumentStandard> findActive(
            EntityManager em,
            String value,
            int maxResults) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<DocumentStandard> documentStandards
                    = em.createQuery("SELECT d FROM DocumentStandard d WHERE (d.name like '%"
                            + value + "%'"
                            + " OR d.number like '%"
                            + value + "%')"
                            + " AND d.active = 1"
                            + " ORDER BY d.id", DocumentStandard.class).setMaxResults(maxResults).getResultList();
            return documentStandards;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<DocumentStandard> find(
            EntityManager em, String value, int maxResults) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<DocumentStandard> documentStandards
                    = em.createQuery("SELECT d FROM DocumentStandard d WHERE d.name like '%"
                            + value + "%'"
                            + " OR d.number like '%"
                            + value + "%'"
                            + " ORDER BY d.id", DocumentStandard.class).
                            setMaxResults(maxResults).
                            getResultList();

            return documentStandards;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static DocumentStandard findById(EntityManager em, Long Id) {

        return em.find(DocumentStandard.class, Id);
    }

    public static List<DocumentStandard> findAll(EntityManager em) {

        try {
            return em.createNamedQuery("findAllDocumentStandards", DocumentStandard.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DocumentStandard> findAllActive(EntityManager em) {

        try {

            return em.createQuery("SELECT d FROM DocumentStandard d "
                    + "WHERE d.active = 1", DocumentStandard.class).getResultList();

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<DocumentStandard> findAllActive(
            EntityManager em, int maxResults) {

        try {

            return em.createQuery("SELECT d FROM DocumentStandard d "
                    + "WHERE d.active = 1",
                    DocumentStandard.class).setMaxResults(maxResults).getResultList();

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setUsable(String usable) {
        if (usable.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {

            getDocumentType().save(em);
            getClassification().save(em);
            getEditedBy().save(em);

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
        return new ReturnMessage();
    }

    @Override
    public void setDescription(String description) {
        this.synopsis = description;
    }

    public String getIsActive() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public void setIsActive(String active) {
        if (active.equals("Yes")) {
            setActive(true);
        } else {
            setActive(false);
        }
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
    public ReturnMessage delete(EntityManager em) {
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

    @Override
    public void setDocumentType(DocumentType documentType) {
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
    public ReturnMessage saveUnique(EntityManager em) {
        try {

            if (this.id == null) {
                DocumentStandard existing = DocumentStandard.findByName(em, this.name, false);
                if (existing != null) {
                    return new ReturnMessage(false, "Document Standard exists");
                } else {
                    return save(em);
                }
            } else {
                return save(em);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Document Standard not saved");
    }
}
