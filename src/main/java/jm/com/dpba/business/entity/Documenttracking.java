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
@Table(name = "documenttracking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documenttracking.findAll", query = "SELECT d FROM Documenttracking d"),
    @NamedQuery(name = "Documenttracking.findById", query = "SELECT d FROM Documenttracking d WHERE d.id = :id"),
    @NamedQuery(name = "Documenttracking.findByAutogeneratenumber", query = "SELECT d FROM Documenttracking d WHERE d.autogeneratenumber = :autogeneratenumber"),
    @NamedQuery(name = "Documenttracking.findByComments", query = "SELECT d FROM Documenttracking d WHERE d.comments = :comments"),
    @NamedQuery(name = "Documenttracking.findByDateofcompletion", query = "SELECT d FROM Documenttracking d WHERE d.dateofcompletion = :dateofcompletion"),
    @NamedQuery(name = "Documenttracking.findByDatereceived", query = "SELECT d FROM Documenttracking d WHERE d.datereceived = :datereceived"),
    @NamedQuery(name = "Documenttracking.findByDescription", query = "SELECT d FROM Documenttracking d WHERE d.description = :description"),
    @NamedQuery(name = "Documenttracking.findByDocumentform", query = "SELECT d FROM Documenttracking d WHERE d.documentform = :documentform"),
    @NamedQuery(name = "Documenttracking.findByExpecteddateofcompletion", query = "SELECT d FROM Documenttracking d WHERE d.expecteddateofcompletion = :expecteddateofcompletion"),
    @NamedQuery(name = "Documenttracking.findByGoal", query = "SELECT d FROM Documenttracking d WHERE d.goal = :goal"),
    @NamedQuery(name = "Documenttracking.findByMonthreceived", query = "SELECT d FROM Documenttracking d WHERE d.monthreceived = :monthreceived"),
    @NamedQuery(name = "Documenttracking.findByNotes", query = "SELECT d FROM Documenttracking d WHERE d.notes = :notes"),
    @NamedQuery(name = "Documenttracking.findByNumber", query = "SELECT d FROM Documenttracking d WHERE d.number = :number"),
    @NamedQuery(name = "Documenttracking.findByNumberofdocuments", query = "SELECT d FROM Documenttracking d WHERE d.numberofdocuments = :numberofdocuments"),
    @NamedQuery(name = "Documenttracking.findByPrioritylevel", query = "SELECT d FROM Documenttracking d WHERE d.prioritylevel = :prioritylevel"),
    @NamedQuery(name = "Documenttracking.findBySequencenumber", query = "SELECT d FROM Documenttracking d WHERE d.sequencenumber = :sequencenumber"),
    @NamedQuery(name = "Documenttracking.findByStatus", query = "SELECT d FROM Documenttracking d WHERE d.status = :status"),
    @NamedQuery(name = "Documenttracking.findByTurnaroundtime", query = "SELECT d FROM Documenttracking d WHERE d.turnaroundtime = :turnaroundtime"),
    @NamedQuery(name = "Documenttracking.findByUrl", query = "SELECT d FROM Documenttracking d WHERE d.url = :url"),
    @NamedQuery(name = "Documenttracking.findByWorkperformedondocument", query = "SELECT d FROM Documenttracking d WHERE d.workperformedondocument = :workperformedondocument"),
    @NamedQuery(name = "Documenttracking.findByYearreceived", query = "SELECT d FROM Documenttracking d WHERE d.yearreceived = :yearreceived")})
public class Documenttracking implements Serializable {
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
    @Column(name = "DATEOFCOMPLETION")
    @Temporal(TemporalType.DATE)
    private Date dateofcompletion;
    @Column(name = "DATERECEIVED")
    @Temporal(TemporalType.DATE)
    private Date datereceived;
    @Size(max = 1024)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 255)
    @Column(name = "DOCUMENTFORM")
    private String documentform;
    @Column(name = "EXPECTEDDATEOFCOMPLETION")
    @Temporal(TemporalType.DATE)
    private Date expecteddateofcompletion;
    @Size(max = 1024)
    @Column(name = "GOAL")
    private String goal;
    @Column(name = "MONTHRECEIVED")
    private Integer monthreceived;
    @Size(max = 1024)
    @Column(name = "NOTES")
    private String notes;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "NUMBEROFDOCUMENTS")
    private BigInteger numberofdocuments;
    @Size(max = 255)
    @Column(name = "PRIORITYLEVEL")
    private String prioritylevel;
    @Column(name = "SEQUENCENUMBER")
    private BigInteger sequencenumber;
    @Size(max = 1024)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TURNAROUNDTIME")
    private Integer turnaroundtime;
    @Size(max = 255)
    @Column(name = "URL")
    private String url;
    @Size(max = 255)
    @Column(name = "WORKPERFORMEDONDOCUMENT")
    private String workperformedondocument;
    @Column(name = "YEARRECEIVED")
    private Integer yearreceived;
    @JoinColumn(name = "CLASSIFICATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Classification classificationId;
    @JoinColumn(name = "EDITEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee editedbyId;
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

    public Documenttracking() {
    }

    public Documenttracking(Long id) {
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

    public Date getDateofcompletion() {
        return dateofcompletion;
    }

    public void setDateofcompletion(Date dateofcompletion) {
        this.dateofcompletion = dateofcompletion;
    }

    public Date getDatereceived() {
        return datereceived;
    }

    public void setDatereceived(Date datereceived) {
        this.datereceived = datereceived;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentform() {
        return documentform;
    }

    public void setDocumentform(String documentform) {
        this.documentform = documentform;
    }

    public Date getExpecteddateofcompletion() {
        return expecteddateofcompletion;
    }

    public void setExpecteddateofcompletion(Date expecteddateofcompletion) {
        this.expecteddateofcompletion = expecteddateofcompletion;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Integer getMonthreceived() {
        return monthreceived;
    }

    public void setMonthreceived(Integer monthreceived) {
        this.monthreceived = monthreceived;
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

    public BigInteger getNumberofdocuments() {
        return numberofdocuments;
    }

    public void setNumberofdocuments(BigInteger numberofdocuments) {
        this.numberofdocuments = numberofdocuments;
    }

    public String getPrioritylevel() {
        return prioritylevel;
    }

    public void setPrioritylevel(String prioritylevel) {
        this.prioritylevel = prioritylevel;
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

    public Integer getTurnaroundtime() {
        return turnaroundtime;
    }

    public void setTurnaroundtime(Integer turnaroundtime) {
        this.turnaroundtime = turnaroundtime;
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

    public Integer getYearreceived() {
        return yearreceived;
    }

    public void setYearreceived(Integer yearreceived) {
        this.yearreceived = yearreceived;
    }

    public Classification getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Classification classificationId) {
        this.classificationId = classificationId;
    }

    public Employee getEditedbyId() {
        return editedbyId;
    }

    public void setEditedbyId(Employee editedbyId) {
        this.editedbyId = editedbyId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documenttracking)) {
            return false;
        }
        Documenttracking other = (Documenttracking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Documenttracking[ id=" + id + " ]";
    }
    
}
