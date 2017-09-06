/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "foodsample")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodsample.findAll", query = "SELECT f FROM Foodsample f")
    , @NamedQuery(name = "Foodsample.findById", query = "SELECT f FROM Foodsample f WHERE f.id = :id")
    , @NamedQuery(name = "Foodsample.findByDatereturned", query = "SELECT f FROM Foodsample f WHERE f.datereturned = :datereturned")
    , @NamedQuery(name = "Foodsample.findByJobid", query = "SELECT f FROM Foodsample f WHERE f.jobid = :jobid")
    , @NamedQuery(name = "Foodsample.findByCode", query = "SELECT f FROM Foodsample f WHERE f.code = :code")
    , @NamedQuery(name = "Foodsample.findByDatereceived", query = "SELECT f FROM Foodsample f WHERE f.datereceived = :datereceived")
    , @NamedQuery(name = "Foodsample.findByType", query = "SELECT f FROM Foodsample f WHERE f.type = :type")
    , @NamedQuery(name = "Foodsample.findByReferenceindex", query = "SELECT f FROM Foodsample f WHERE f.referenceindex = :referenceindex")
    , @NamedQuery(name = "Foodsample.findByReference", query = "SELECT f FROM Foodsample f WHERE f.reference = :reference")
    , @NamedQuery(name = "Foodsample.findByMethodofdisposal", query = "SELECT f FROM Foodsample f WHERE f.methodofdisposal = :methodofdisposal")
    , @NamedQuery(name = "Foodsample.findBySamplequantity", query = "SELECT f FROM Foodsample f WHERE f.samplequantity = :samplequantity")
    , @NamedQuery(name = "Foodsample.findByDescription", query = "SELECT f FROM Foodsample f WHERE f.description = :description")
    , @NamedQuery(name = "Foodsample.findByName", query = "SELECT f FROM Foodsample f WHERE f.name = :name")
    , @NamedQuery(name = "Foodsample.findByQuantity", query = "SELECT f FROM Foodsample f WHERE f.quantity = :quantity")
    , @NamedQuery(name = "Foodsample.findByDatesampled", query = "SELECT f FROM Foodsample f WHERE f.datesampled = :datesampled")
    , @NamedQuery(name = "Foodsample.findByUnitofmeasure", query = "SELECT f FROM Foodsample f WHERE f.unitofmeasure = :unitofmeasure")})
public class Foodsample implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATERETURNED")
    @Temporal(TemporalType.DATE)
    private Date datereturned;
    @Column(name = "JOBID")
    private BigInteger jobid;
    @Column(name = "CODE")
    private String code;
    @Column(name = "DATERECEIVED")
    @Temporal(TemporalType.DATE)
    private Date datereceived;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "REFERENCEINDEX")
    private BigInteger referenceindex;
    @Column(name = "REFERENCE")
    private String reference;
    @Column(name = "METHODOFDISPOSAL")
    private Integer methodofdisposal;
    @Column(name = "SAMPLEQUANTITY")
    private BigInteger samplequantity;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "NAME")
    private String name;
    @Column(name = "QUANTITY")
    private BigInteger quantity;
    @Column(name = "DATESAMPLED")
    @Temporal(TemporalType.DATE)
    private Date datesampled;
    @Column(name = "UNITOFMEASURE")
    private String unitofmeasure;
    @JoinTable(name = "foodsample_foodtest", joinColumns = {
        @JoinColumn(name = "FoodSample_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "tests_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Foodtest> foodtestList;
    @JoinColumn(name = "ASSIGNEDLAB_ID", referencedColumnName = "ID")
    @ManyToOne
    private Laboratory assignedlabId;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;
    @JoinColumn(name = "RECEIVEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee receivedbyId;
    @JoinColumn(name = "REGULATORYOFFICE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Businessoffice regulatoryofficeId;
    @JoinColumn(name = "SAMPLEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee sampledbyId;

    public Foodsample() {
    }

    public Foodsample(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatereturned() {
        return datereturned;
    }

    public void setDatereturned(Date datereturned) {
        this.datereturned = datereturned;
    }

    public BigInteger getJobid() {
        return jobid;
    }

    public void setJobid(BigInteger jobid) {
        this.jobid = jobid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDatereceived() {
        return datereceived;
    }

    public void setDatereceived(Date datereceived) {
        this.datereceived = datereceived;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigInteger getReferenceindex() {
        return referenceindex;
    }

    public void setReferenceindex(BigInteger referenceindex) {
        this.referenceindex = referenceindex;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getMethodofdisposal() {
        return methodofdisposal;
    }

    public void setMethodofdisposal(Integer methodofdisposal) {
        this.methodofdisposal = methodofdisposal;
    }

    public BigInteger getSamplequantity() {
        return samplequantity;
    }

    public void setSamplequantity(BigInteger samplequantity) {
        this.samplequantity = samplequantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public Date getDatesampled() {
        return datesampled;
    }

    public void setDatesampled(Date datesampled) {
        this.datesampled = datesampled;
    }

    public String getUnitofmeasure() {
        return unitofmeasure;
    }

    public void setUnitofmeasure(String unitofmeasure) {
        this.unitofmeasure = unitofmeasure;
    }

    @XmlTransient
    public List<Foodtest> getFoodtestList() {
        return foodtestList;
    }

    public void setFoodtestList(List<Foodtest> foodtestList) {
        this.foodtestList = foodtestList;
    }

    public Laboratory getAssignedlabId() {
        return assignedlabId;
    }

    public void setAssignedlabId(Laboratory assignedlabId) {
        this.assignedlabId = assignedlabId;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Employee getReceivedbyId() {
        return receivedbyId;
    }

    public void setReceivedbyId(Employee receivedbyId) {
        this.receivedbyId = receivedbyId;
    }

    public Businessoffice getRegulatoryofficeId() {
        return regulatoryofficeId;
    }

    public void setRegulatoryofficeId(Businessoffice regulatoryofficeId) {
        this.regulatoryofficeId = regulatoryofficeId;
    }

    public Employee getSampledbyId() {
        return sampledbyId;
    }

    public void setSampledbyId(Employee sampledbyId) {
        this.sampledbyId = sampledbyId;
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
        if (!(object instanceof Foodsample)) {
            return false;
        }
        Foodsample other = (Foodsample) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Foodsample[ id=" + id + " ]";
    }
    
}
