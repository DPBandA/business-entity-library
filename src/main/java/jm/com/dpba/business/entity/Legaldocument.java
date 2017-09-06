/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "legaldocument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Legaldocument.findAll", query = "SELECT l FROM Legaldocument l"),
    @NamedQuery(name = "Legaldocument.findById", query = "SELECT l FROM Legaldocument l WHERE l.id = :id"),
    @NamedQuery(name = "Legaldocument.findByMonthreceived", query = "SELECT l FROM Legaldocument l WHERE l.monthreceived = :monthreceived"),
    @NamedQuery(name = "Legaldocument.findByNumber", query = "SELECT l FROM Legaldocument l WHERE l.number = :number"),
    @NamedQuery(name = "Legaldocument.findByExpecteddateofcompletion", query = "SELECT l FROM Legaldocument l WHERE l.expecteddateofcompletion = :expecteddateofcompletion"),
    @NamedQuery(name = "Legaldocument.findByYearreceived", query = "SELECT l FROM Legaldocument l WHERE l.yearreceived = :yearreceived"),
    @NamedQuery(name = "Legaldocument.findByDatereceived", query = "SELECT l FROM Legaldocument l WHERE l.datereceived = :datereceived"),
    @NamedQuery(name = "Legaldocument.findByUrl", query = "SELECT l FROM Legaldocument l WHERE l.url = :url"),
    @NamedQuery(name = "Legaldocument.findBySequencenumber", query = "SELECT l FROM Legaldocument l WHERE l.sequencenumber = :sequencenumber"),
    @NamedQuery(name = "Legaldocument.findByNumberofdocuments", query = "SELECT l FROM Legaldocument l WHERE l.numberofdocuments = :numberofdocuments"),
    @NamedQuery(name = "Legaldocument.findByDocumentform", query = "SELECT l FROM Legaldocument l WHERE l.documentform = :documentform"),
    @NamedQuery(name = "Legaldocument.findByWorkperformedondocument", query = "SELECT l FROM Legaldocument l WHERE l.workperformedondocument = :workperformedondocument"),
    @NamedQuery(name = "Legaldocument.findByDescription", query = "SELECT l FROM Legaldocument l WHERE l.description = :description"),
    @NamedQuery(name = "Legaldocument.findByTurnaroundtime", query = "SELECT l FROM Legaldocument l WHERE l.turnaroundtime = :turnaroundtime"),
    @NamedQuery(name = "Legaldocument.findByNotes", query = "SELECT l FROM Legaldocument l WHERE l.notes = :notes"),
    @NamedQuery(name = "Legaldocument.findByComments", query = "SELECT l FROM Legaldocument l WHERE l.comments = :comments"),
    @NamedQuery(name = "Legaldocument.findByAutogeneratenumber", query = "SELECT l FROM Legaldocument l WHERE l.autogeneratenumber = :autogeneratenumber"),
    @NamedQuery(name = "Legaldocument.findByDateofcompletion", query = "SELECT l FROM Legaldocument l WHERE l.dateofcompletion = :dateofcompletion"),
    @NamedQuery(name = "Legaldocument.findByGoal", query = "SELECT l FROM Legaldocument l WHERE l.goal = :goal"),
    @NamedQuery(name = "Legaldocument.findByStatus", query = "SELECT l FROM Legaldocument l WHERE l.status = :status"),
    @NamedQuery(name = "Legaldocument.findByPrioritylevel", query = "SELECT l FROM Legaldocument l WHERE l.prioritylevel = :prioritylevel")})
public class Legaldocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "MONTHRECEIVED")
    private Integer monthreceived;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "EXPECTEDDATEOFCOMPLETION")
    @Temporal(TemporalType.DATE)
    private Date expecteddateofcompletion;
    @Column(name = "YEARRECEIVED")
    private Integer yearreceived;
    @Column(name = "DATERECEIVED")
    @Temporal(TemporalType.DATE)
    private Date datereceived;
    @Size(max = 255)
    @Column(name = "URL")
    private String url;
    @Column(name = "SEQUENCENUMBER")
    private BigInteger sequencenumber;
    @Column(name = "NUMBEROFDOCUMENTS")
    private BigInteger numberofdocuments;
    @Size(max = 255)
    @Column(name = "DOCUMENTFORM")
    private String documentform;
    @Size(max = 255)
    @Column(name = "WORKPERFORMEDONDOCUMENT")
    private String workperformedondocument;
    @Size(max = 1024)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TURNAROUNDTIME")
    private Integer turnaroundtime;
    @Size(max = 1024)
    @Column(name = "NOTES")
    private String notes;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "AUTOGENERATENUMBER")
    private Boolean autogeneratenumber;
    @Column(name = "DATEOFCOMPLETION")
    @Temporal(TemporalType.DATE)
    private Date dateofcompletion;
    @Size(max = 1024)
    @Column(name = "GOAL")
    private String goal;
    @Size(max = 1024)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 255)
    @Column(name = "PRIORITYLEVEL")
    private String prioritylevel;
    @JoinColumn(name = "CLASSIFICATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Classification classificationId;
    @JoinColumn(name = "EXTERNALCLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client externalclientId;
    @JoinColumn(name = "REQUESTINGDEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department requestingdepartmentId;
    @JoinColumn(name = "RESPONSIBLEDEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department responsibledepartmentId;
    @JoinColumn(name = "RESPONSIBLEOFFICER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee responsibleofficerId;
    @JoinColumn(name = "SUBMITTEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee submittedbyId;
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Documenttype typeId;
    @JoinColumn(name = "EDITEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee editedbyId;

    public Legaldocument() {
    }

    public Legaldocument(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonthreceived() {
        return monthreceived;
    }

    public void setMonthreceived(Integer monthreceived) {
        this.monthreceived = monthreceived;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpecteddateofcompletion() {
        return expecteddateofcompletion;
    }

    public void setExpecteddateofcompletion(Date expecteddateofcompletion) {
        this.expecteddateofcompletion = expecteddateofcompletion;
    }

    public Integer getYearreceived() {
        return yearreceived;
    }

    public void setYearreceived(Integer yearreceived) {
        this.yearreceived = yearreceived;
    }

    public Date getDatereceived() {
        return datereceived;
    }

    public void setDatereceived(Date datereceived) {
        this.datereceived = datereceived;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigInteger getSequencenumber() {
        return sequencenumber;
    }

    public void setSequencenumber(BigInteger sequencenumber) {
        this.sequencenumber = sequencenumber;
    }

    public BigInteger getNumberofdocuments() {
        return numberofdocuments;
    }

    public void setNumberofdocuments(BigInteger numberofdocuments) {
        this.numberofdocuments = numberofdocuments;
    }

    public String getDocumentform() {
        return documentform;
    }

    public void setDocumentform(String documentform) {
        this.documentform = documentform;
    }

    public String getWorkperformedondocument() {
        return workperformedondocument;
    }

    public void setWorkperformedondocument(String workperformedondocument) {
        this.workperformedondocument = workperformedondocument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTurnaroundtime() {
        return turnaroundtime;
    }

    public void setTurnaroundtime(Integer turnaroundtime) {
        this.turnaroundtime = turnaroundtime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getAutogeneratenumber() {
        return autogeneratenumber;
    }

    public void setAutogeneratenumber(Boolean autogeneratenumber) {
        this.autogeneratenumber = autogeneratenumber;
    }

    public Date getDateofcompletion() {
        return dateofcompletion;
    }

    public void setDateofcompletion(Date dateofcompletion) {
        this.dateofcompletion = dateofcompletion;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrioritylevel() {
        return prioritylevel;
    }

    public void setPrioritylevel(String prioritylevel) {
        this.prioritylevel = prioritylevel;
    }

    public Classification getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Classification classificationId) {
        this.classificationId = classificationId;
    }

    public Client getExternalclientId() {
        return externalclientId;
    }

    public void setExternalclientId(Client externalclientId) {
        this.externalclientId = externalclientId;
    }

    public Department getRequestingdepartmentId() {
        return requestingdepartmentId;
    }

    public void setRequestingdepartmentId(Department requestingdepartmentId) {
        this.requestingdepartmentId = requestingdepartmentId;
    }

    public Department getResponsibledepartmentId() {
        return responsibledepartmentId;
    }

    public void setResponsibledepartmentId(Department responsibledepartmentId) {
        this.responsibledepartmentId = responsibledepartmentId;
    }

    public Employee getResponsibleofficerId() {
        return responsibleofficerId;
    }

    public void setResponsibleofficerId(Employee responsibleofficerId) {
        this.responsibleofficerId = responsibleofficerId;
    }

    public Employee getSubmittedbyId() {
        return submittedbyId;
    }

    public void setSubmittedbyId(Employee submittedbyId) {
        this.submittedbyId = submittedbyId;
    }

    public Documenttype getTypeId() {
        return typeId;
    }

    public void setTypeId(Documenttype typeId) {
        this.typeId = typeId;
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
        if (!(object instanceof Legaldocument)) {
            return false;
        }
        Legaldocument other = (Legaldocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Legaldocument[ id=" + id + " ]";
    }
    
}
