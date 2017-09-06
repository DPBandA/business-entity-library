/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "documentstandard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentstandard.findAll", query = "SELECT d FROM Documentstandard d"),
    @NamedQuery(name = "Documentstandard.findById", query = "SELECT d FROM Documentstandard d WHERE d.id = :id"),
    @NamedQuery(name = "Documentstandard.findByAutogeneratenumber", query = "SELECT d FROM Documentstandard d WHERE d.autogeneratenumber = :autogeneratenumber"),
    @NamedQuery(name = "Documentstandard.findByComments", query = "SELECT d FROM Documentstandard d WHERE d.comments = :comments"),
    @NamedQuery(name = "Documentstandard.findByDateconfirmed", query = "SELECT d FROM Documentstandard d WHERE d.dateconfirmed = :dateconfirmed"),
    @NamedQuery(name = "Documentstandard.findByDateedited", query = "SELECT d FROM Documentstandard d WHERE d.dateedited = :dateedited"),
    @NamedQuery(name = "Documentstandard.findByDateentered", query = "SELECT d FROM Documentstandard d WHERE d.dateentered = :dateentered"),
    @NamedQuery(name = "Documentstandard.findByDatepublished", query = "SELECT d FROM Documentstandard d WHERE d.datepublished = :datepublished"),
    @NamedQuery(name = "Documentstandard.findByDaterevised", query = "SELECT d FROM Documentstandard d WHERE d.daterevised = :daterevised"),
    @NamedQuery(name = "Documentstandard.findByDaterevisiondue", query = "SELECT d FROM Documentstandard d WHERE d.daterevisiondue = :daterevisiondue"),
    @NamedQuery(name = "Documentstandard.findByDocumentform", query = "SELECT d FROM Documentstandard d WHERE d.documentform = :documentform"),
    @NamedQuery(name = "Documentstandard.findByEnforcement", query = "SELECT d FROM Documentstandard d WHERE d.enforcement = :enforcement"),
    @NamedQuery(name = "Documentstandard.findByNotes", query = "SELECT d FROM Documentstandard d WHERE d.notes = :notes"),
    @NamedQuery(name = "Documentstandard.findByNumber", query = "SELECT d FROM Documentstandard d WHERE d.number = :number"),
    @NamedQuery(name = "Documentstandard.findBySequencenumber", query = "SELECT d FROM Documentstandard d WHERE d.sequencenumber = :sequencenumber"),
    @NamedQuery(name = "Documentstandard.findByStatus", query = "SELECT d FROM Documentstandard d WHERE d.status = :status"),
    @NamedQuery(name = "Documentstandard.findBySynopsis", query = "SELECT d FROM Documentstandard d WHERE d.synopsis = :synopsis"),
    @NamedQuery(name = "Documentstandard.findByTitle", query = "SELECT d FROM Documentstandard d WHERE d.title = :title"),
    @NamedQuery(name = "Documentstandard.findByUrl", query = "SELECT d FROM Documentstandard d WHERE d.url = :url"),
    @NamedQuery(name = "Documentstandard.findByWorkperformedondocument", query = "SELECT d FROM Documentstandard d WHERE d.workperformedondocument = :workperformedondocument"),
    @NamedQuery(name = "Documentstandard.findByName", query = "SELECT d FROM Documentstandard d WHERE d.name = :name"),
    @NamedQuery(name = "Documentstandard.findByActive", query = "SELECT d FROM Documentstandard d WHERE d.active = :active")})
public class Documentstandard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "AUTOGENERATENUMBER")
    private Boolean autogeneratenumber;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "DATECONFIRMED")
    @Temporal(TemporalType.DATE)
    private Date dateconfirmed;
    @Column(name = "DATEEDITED")
    @Temporal(TemporalType.DATE)
    private Date dateedited;
    @Column(name = "DATEENTERED")
    @Temporal(TemporalType.DATE)
    private Date dateentered;
    @Column(name = "DATEPUBLISHED")
    @Temporal(TemporalType.DATE)
    private Date datepublished;
    @Column(name = "DATEREVISED")
    @Temporal(TemporalType.DATE)
    private Date daterevised;
    @Column(name = "DATEREVISIONDUE")
    @Temporal(TemporalType.DATE)
    private Date daterevisiondue;
    @Size(max = 255)
    @Column(name = "DOCUMENTFORM")
    private String documentform;
    @Size(max = 255)
    @Column(name = "ENFORCEMENT")
    private String enforcement;
    @Size(max = 1024)
    @Column(name = "NOTES")
    private String notes;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "SEQUENCENUMBER")
    private BigInteger sequencenumber;
    @Size(max = 1024)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 1024)
    @Column(name = "SYNOPSIS")
    private String synopsis;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 255)
    @Column(name = "URL")
    private String url;
    @Size(max = 255)
    @Column(name = "WORKPERFORMEDONDOCUMENT")
    private String workperformedondocument;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACTIVE")
    private Boolean active;
    @JoinTable(name = "compliancesurvey_documentstandard", joinColumns = {
        @JoinColumn(name = "standardsBreached_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ComplianceSurvey_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Compliancesurvey> compliancesurveyList;
    @JoinColumn(name = "CLASSIFICATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Classification classificationId;
    @JoinColumn(name = "DOCUMENTTYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Documenttype documenttypeId;
    @JoinColumn(name = "EDITEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee editedbyId;

    public Documentstandard() {
    }

    public Documentstandard(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAutogeneratenumber() {
        return autogeneratenumber;
    }

    public void setAutogeneratenumber(Boolean autogeneratenumber) {
        this.autogeneratenumber = autogeneratenumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateconfirmed() {
        return dateconfirmed;
    }

    public void setDateconfirmed(Date dateconfirmed) {
        this.dateconfirmed = dateconfirmed;
    }

    public Date getDateedited() {
        return dateedited;
    }

    public void setDateedited(Date dateedited) {
        this.dateedited = dateedited;
    }

    public Date getDateentered() {
        return dateentered;
    }

    public void setDateentered(Date dateentered) {
        this.dateentered = dateentered;
    }

    public Date getDatepublished() {
        return datepublished;
    }

    public void setDatepublished(Date datepublished) {
        this.datepublished = datepublished;
    }

    public Date getDaterevised() {
        return daterevised;
    }

    public void setDaterevised(Date daterevised) {
        this.daterevised = daterevised;
    }

    public Date getDaterevisiondue() {
        return daterevisiondue;
    }

    public void setDaterevisiondue(Date daterevisiondue) {
        this.daterevisiondue = daterevisiondue;
    }

    public String getDocumentform() {
        return documentform;
    }

    public void setDocumentform(String documentform) {
        this.documentform = documentform;
    }

    public String getEnforcement() {
        return enforcement;
    }

    public void setEnforcement(String enforcement) {
        this.enforcement = enforcement;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigInteger getSequencenumber() {
        return sequencenumber;
    }

    public void setSequencenumber(BigInteger sequencenumber) {
        this.sequencenumber = sequencenumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWorkperformedondocument() {
        return workperformedondocument;
    }

    public void setWorkperformedondocument(String workperformedondocument) {
        this.workperformedondocument = workperformedondocument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    @JsonIgnore
    public List<Compliancesurvey> getCompliancesurveyList() {
        return compliancesurveyList;
    }

    public void setCompliancesurveyList(List<Compliancesurvey> compliancesurveyList) {
        this.compliancesurveyList = compliancesurveyList;
    }

    public Classification getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Classification classificationId) {
        this.classificationId = classificationId;
    }

    public Documenttype getDocumenttypeId() {
        return documenttypeId;
    }

    public void setDocumenttypeId(Documenttype documenttypeId) {
        this.documenttypeId = documenttypeId;
    }

    public Employee getEditedbyId() {
        return editedbyId;
    }

    public void setEditedbyId(Employee editedbyId) {
        this.editedbyId = editedbyId;
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
        if (!(object instanceof Documentstandard)) {
            return false;
        }
        Documentstandard other = (Documentstandard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Documentstandard[ id=" + id + " ]";
    }
    
}
