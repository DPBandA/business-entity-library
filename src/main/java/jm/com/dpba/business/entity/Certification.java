/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "certification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certification.findAll", query = "SELECT c FROM Certification c"),
    @NamedQuery(name = "Certification.findById", query = "SELECT c FROM Certification c WHERE c.id = :id"),
    @NamedQuery(name = "Certification.findByCertificatenumber", query = "SELECT c FROM Certification c WHERE c.certificatenumber = :certificatenumber"),
    @NamedQuery(name = "Certification.findByExpirydate", query = "SELECT c FROM Certification c WHERE c.expirydate = :expirydate"),
    @NamedQuery(name = "Certification.findByActive", query = "SELECT c FROM Certification c WHERE c.active = :active"),
    @NamedQuery(name = "Certification.findByDateissued", query = "SELECT c FROM Certification c WHERE c.dateissued = :dateissued"),
    @NamedQuery(name = "Certification.findByNumber", query = "SELECT c FROM Certification c WHERE c.number = :number"),
    @NamedQuery(name = "Certification.findByType", query = "SELECT c FROM Certification c WHERE c.type = :type"),
    @NamedQuery(name = "Certification.findByNotes", query = "SELECT c FROM Certification c WHERE c.notes = :notes")})
public class Certification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "CERTIFICATENUMBER")
    private String certificatenumber;
    @Column(name = "EXPIRYDATE")
    @Temporal(TemporalType.DATE)
    private Date expirydate;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "DATEISSUED")
    @Temporal(TemporalType.DATE)
    private Date dateissued;
    @Size(max = 255)
    @Column(name = "NUMBER")
    private String number;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "NOTES")
    private String notes;
    @ManyToMany(mappedBy = "certificationList")
    private List<Petrolpumpnozzle> petrolpumpnozzleList;
    @ManyToMany(mappedBy = "certificationList")
    private List<Foodfactory> foodfactoryList;
    @JoinColumn(name = "APPLICANT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client applicantId;
    @JoinColumn(name = "CERTIFICATESIGNEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee certificatesignedbyId;
    @JoinColumn(name = "GRANTEDTO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Business grantedtoId;
    @OneToMany(mappedBy = "certificationId")
    private List<Petrolstation> petrolstationList;
    @OneToMany(mappedBy = "certificationId")
    private List<Scale> scaleList;
    @OneToMany(mappedBy = "certificationId")
    private List<Petrolpump> petrolpumpList;

    public Certification() {
    }

    public Certification(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificatenumber() {
        return certificatenumber;
    }

    public void setCertificatenumber(String certificatenumber) {
        this.certificatenumber = certificatenumber;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateissued() {
        return dateissued;
    }

    public void setDateissued(Date dateissued) {
        this.dateissued = dateissued;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzle> getPetrolpumpnozzleList() {
        return petrolpumpnozzleList;
    }

    public void setPetrolpumpnozzleList(List<Petrolpumpnozzle> petrolpumpnozzleList) {
        this.petrolpumpnozzleList = petrolpumpnozzleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
    }

    public Client getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Client applicantId) {
        this.applicantId = applicantId;
    }

    public Employee getCertificatesignedbyId() {
        return certificatesignedbyId;
    }

    public void setCertificatesignedbyId(Employee certificatesignedbyId) {
        this.certificatesignedbyId = certificatesignedbyId;
    }

    public Business getGrantedtoId() {
        return grantedtoId;
    }

    public void setGrantedtoId(Business grantedtoId) {
        this.grantedtoId = grantedtoId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolstation> getPetrolstationList() {
        return petrolstationList;
    }

    public void setPetrolstationList(List<Petrolstation> petrolstationList) {
        this.petrolstationList = petrolstationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Scale> getScaleList() {
        return scaleList;
    }

    public void setScaleList(List<Scale> scaleList) {
        this.scaleList = scaleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpump> getPetrolpumpList() {
        return petrolpumpList;
    }

    public void setPetrolpumpList(List<Petrolpump> petrolpumpList) {
        this.petrolpumpList = petrolpumpList;
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
        if (!(object instanceof Certification)) {
            return false;
        }
        Certification other = (Certification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Certification[ id=" + id + " ]";
    }
    
}
