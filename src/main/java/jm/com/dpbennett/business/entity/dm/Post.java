/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2023  D P Bennett & Associates Limited

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
@Table(name = "post")
@NamedQueries({
    @NamedQuery(name = "findAllPosts", query = "SELECT p FROM Post p ORDER BY p.number")
})
public class Post implements Document, Comparable, BusinessEntity {

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

    public Post() {
        this.name = "";
        this.active = true;
    }

    public Post(String name) {
        this.name = name;
        this.active = true;
    }

    public Post(DocumentType documentType) {
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
       
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Post[name=" + getName() + "]";
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

    public static Post findActiveByName(EntityManager em, String value, Boolean ignoreCase) {

        List<Post> posts;

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            if (ignoreCase) {
                posts = em.createQuery("SELECT p FROM Post p "
                        + "WHERE UPPER(p.name) "
                        + "= '" + value.toUpperCase() + "'"
                        + " AND p.active = 1", Post.class).getResultList();
            } else {
                posts = em.createQuery("SELECT p FROM Post p "
                        + "WHERE p.name "
                        + "= '" + value + "'"
                        + " AND p.active = 1", Post.class).getResultList();
            }

            if (!posts.isEmpty()) {
                return posts.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Post> findByDateSearchField(
            EntityManager em,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate) {

        List<Post> foundPosts;
        String searchQuery = null;
        String searchTextAndClause = "";
        String searchText = originalSearchText.replaceAll("'", "''");
        switch (searchType) {
            case "General":
                if (!searchText.equals("")) {
                    searchTextAndClause
                            = " AND ("
                            + " UPPER(post.number) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(post.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(post.notes) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(post.url) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " OR UPPER(post.documentForm) LIKE '%" + searchText.toUpperCase() + "%'"
                            + " )";
                }
                searchQuery
                        = "SELECT post FROM Post post"
                        + " JOIN post.classification classification"
                        + " WHERE (post." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND post." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY post." + dateSearchField + " DESC";
                break;
            case "By type":
                searchTextAndClause
                        = " AND ("
                        + " UPPER(t.name) = '" + searchText.toUpperCase() + "'"
                        + " )";
                searchQuery
                        = "SELECT post FROM Post post"
                        + " JOIN post.documentType t"
                        + " WHERE (post." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND post." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY post." + dateSearchField + " DESC";
                break;
        }

        try {
            foundPosts = em.createQuery(searchQuery, Post.class).getResultList();
            if (foundPosts == null) {
                foundPosts = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundPosts;
    }

    public static List<Post> findActive(
            EntityManager em, 
            String value,
            int maxResults) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Post> posts
                    = em.createQuery("SELECT p FROM Post p WHERE (p.name like '%"
                            + value + "%'"
                            + " OR p.number like '%"
                            + value + "%')"
                            + " AND p.active = 1"
                            + " ORDER BY p.id", Post.class).setMaxResults(maxResults).getResultList();
            return posts;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
  
    public static List<Post> find(
            EntityManager em, String value, int maxResults) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Post> posts
                    = em.createQuery("SELECT p FROM Post p WHERE p.name like '%"
                            + value + "%'"
                            + " OR p.number like '%"
                            + value + "%'"
                            + " ORDER BY p.id", Post.class).
                            setMaxResults(maxResults).
                            getResultList();
            
            return posts;
            
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Post findById(EntityManager em, Long Id) {

        return em.find(Post.class, Id);
    }

    public static List<Post> findAll(EntityManager em) {

        try {
            return em.createNamedQuery("findAllPosts", Post.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Post> findAllActive(EntityManager em) {

        try {

            return em.createQuery("SELECT p FROM Post p "
                    + "WHERE p.active = 1", Post.class).getResultList();

        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static List<Post> findAllActive(
            EntityManager em, int maxResults) {

        try {

            return em.createQuery("SELECT p FROM Post p "
                    + "WHERE p.active = 1", 
                    Post.class).setMaxResults(maxResults).getResultList();

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

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Post not saved");
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
}
