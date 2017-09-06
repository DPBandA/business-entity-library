/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "certification")
public class Certification implements BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private String number = "";
    private String type = "";
    private String notes = "";
    private Boolean active = false;
    private String certificateNumber = "";
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee certificateSignedBy = null;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Business grantedTo = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateIssued = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiryDate = null;
    @OneToOne(cascade = CascadeType.ALL)
    private Client applicant = null;

    public Certification() {
    }
    
    public Certification(Certification org) {
        this.number = org.number;
        this.type = org.type;
        this.notes = org.notes;
        this.active = org.active;
        this.certificateNumber = org.certificateNumber;
        this.certificateSignedBy = org.certificateSignedBy;
        this.grantedTo = org.grantedTo;
        this.dateIssued = org.dateIssued;
        this.expiryDate = org.expiryDate;
        this.applicant = org.applicant;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Client getApplicant() {
        if (applicant == null) {
            return new Client("");
        }
        return applicant;
    }

    public void setApplicant(Client applicant) {
        this.applicant = applicant;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Employee getCertificateSignedBy() {
        if (certificateSignedBy == null) {
            return new Employee();
        }
        return certificateSignedBy;
    }

    public void setCertificateSignedBy(Employee certificateSignedBy) {
        this.certificateSignedBy = certificateSignedBy;
    }

    public Business getGrantedTo() {
        if (grantedTo == null) {
            return new Business();
        }
        return grantedTo;
    }

    public void setGrantedTo(Business grantedTo) {
        this.grantedTo = grantedTo;
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

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public Date getExpiryDate() {
        // tk get the option that dictates how the expiry date is to calculated.
//        if ((expiryDate == null) && (dateIssued != null)) {
//            Calendar calendar;
//
//            calendar = Calendar.getInstance();
//            calendar.setTime(dateIssued);
//            calendar.add(Calendar.MONTH, 6);  // tk period to be made option
//
//            return calendar.getTime();
//        }
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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
        return "jm.org.bsj.entity.Certification[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
        // this sorts in descending based on issue date.
        if ((((Certification) o).dateIssued != null) && (this.dateIssued != null)) {
            return Collator.getInstance().compare(
                    new Long(((Certification) o).dateIssued.getTime()).toString(),
                    new Long(this.dateIssued.getTime()).toString());
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
}
