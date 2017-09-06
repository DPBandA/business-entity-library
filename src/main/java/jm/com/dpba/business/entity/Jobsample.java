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
@Table(name = "jobsample")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobsample.findAll", query = "SELECT j FROM Jobsample j"),
    @NamedQuery(name = "Jobsample.findById", query = "SELECT j FROM Jobsample j WHERE j.id = :id"),
    @NamedQuery(name = "Jobsample.findByJobid", query = "SELECT j FROM Jobsample j WHERE j.jobid = :jobid"),
    @NamedQuery(name = "Jobsample.findByDatereceived", query = "SELECT j FROM Jobsample j WHERE j.datereceived = :datereceived"),
    @NamedQuery(name = "Jobsample.findByType", query = "SELECT j FROM Jobsample j WHERE j.type = :type"),
    @NamedQuery(name = "Jobsample.findByProducttype", query = "SELECT j FROM Jobsample j WHERE j.producttype = :producttype"),
    @NamedQuery(name = "Jobsample.findByMethodofdisposal", query = "SELECT j FROM Jobsample j WHERE j.methodofdisposal = :methodofdisposal"),
    @NamedQuery(name = "Jobsample.findByProductbrand", query = "SELECT j FROM Jobsample j WHERE j.productbrand = :productbrand"),
    @NamedQuery(name = "Jobsample.findBySamplequantity", query = "SELECT j FROM Jobsample j WHERE j.samplequantity = :samplequantity"),
    @NamedQuery(name = "Jobsample.findByDescription", query = "SELECT j FROM Jobsample j WHERE j.description = :description"),
    @NamedQuery(name = "Jobsample.findByName", query = "SELECT j FROM Jobsample j WHERE j.name = :name"),
    @NamedQuery(name = "Jobsample.findByQuantity", query = "SELECT j FROM Jobsample j WHERE j.quantity = :quantity"),
    @NamedQuery(name = "Jobsample.findByUnitofmeasure", query = "SELECT j FROM Jobsample j WHERE j.unitofmeasure = :unitofmeasure"),
    @NamedQuery(name = "Jobsample.findByProductserialnumber", query = "SELECT j FROM Jobsample j WHERE j.productserialnumber = :productserialnumber"),
    @NamedQuery(name = "Jobsample.findByDatereturned", query = "SELECT j FROM Jobsample j WHERE j.datereturned = :datereturned"),
    @NamedQuery(name = "Jobsample.findByProductcode", query = "SELECT j FROM Jobsample j WHERE j.productcode = :productcode"),
    @NamedQuery(name = "Jobsample.findBySamplesize", query = "SELECT j FROM Jobsample j WHERE j.samplesize = :samplesize"),
    @NamedQuery(name = "Jobsample.findByProductmodel", query = "SELECT j FROM Jobsample j WHERE j.productmodel = :productmodel"),
    @NamedQuery(name = "Jobsample.findByCode", query = "SELECT j FROM Jobsample j WHERE j.code = :code"),
    @NamedQuery(name = "Jobsample.findByReferenceindex", query = "SELECT j FROM Jobsample j WHERE j.referenceindex = :referenceindex"),
    @NamedQuery(name = "Jobsample.findByReference", query = "SELECT j FROM Jobsample j WHERE j.reference = :reference"),
    @NamedQuery(name = "Jobsample.findByDatesampled", query = "SELECT j FROM Jobsample j WHERE j.datesampled = :datesampled"),
    @NamedQuery(name = "Jobsample.findByComments", query = "SELECT j FROM Jobsample j WHERE j.comments = :comments"),
    @NamedQuery(name = "Jobsample.findByCountryoforigin", query = "SELECT j FROM Jobsample j WHERE j.countryoforigin = :countryoforigin")})
public class Jobsample implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "JOBID")
    private BigInteger jobid;
    @Column(name = "DATERECEIVED")
    @Temporal(TemporalType.DATE)
    private Date datereceived;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "PRODUCTTYPE")
    private String producttype;
    @Column(name = "METHODOFDISPOSAL")
    private Integer methodofdisposal;
    @Size(max = 255)
    @Column(name = "PRODUCTBRAND")
    private String productbrand;
    @Column(name = "SAMPLEQUANTITY")
    private BigInteger samplequantity;
    @Size(max = 1024)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "QUANTITY")
    private BigInteger quantity;
    @Size(max = 255)
    @Column(name = "UNITOFMEASURE")
    private String unitofmeasure;
    @Size(max = 255)
    @Column(name = "PRODUCTSERIALNUMBER")
    private String productserialnumber;
    @Column(name = "DATERETURNED")
    @Temporal(TemporalType.DATE)
    private Date datereturned;
    @Size(max = 255)
    @Column(name = "PRODUCTCODE")
    private String productcode;
    @Size(max = 255)
    @Column(name = "SAMPLESIZE")
    private String samplesize;
    @Size(max = 255)
    @Column(name = "PRODUCTMODEL")
    private String productmodel;
    @Size(max = 255)
    @Column(name = "CODE")
    private String code;
    @Column(name = "REFERENCEINDEX")
    private BigInteger referenceindex;
    @Size(max = 255)
    @Column(name = "REFERENCE")
    private String reference;
    @Column(name = "DATESAMPLED")
    @Temporal(TemporalType.DATE)
    private Date datesampled;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @Size(max = 255)
    @Column(name = "COUNTRYOFORIGIN")
    private String countryoforigin;
    @ManyToMany(mappedBy = "jobsampleList")
    private List<Job> jobList;
    @JoinTable(name = "jobsample_producttest", joinColumns = {
        @JoinColumn(name = "JobSample_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "tests_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Producttest> producttestList;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;
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

    public Jobsample() {
    }

    public Jobsample(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getJobid() {
        return jobid;
    }

    public void setJobid(BigInteger jobid) {
        this.jobid = jobid;
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

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public Integer getMethodofdisposal() {
        return methodofdisposal;
    }

    public void setMethodofdisposal(Integer methodofdisposal) {
        this.methodofdisposal = methodofdisposal;
    }

    public String getProductbrand() {
        return productbrand;
    }

    public void setProductbrand(String productbrand) {
        this.productbrand = productbrand;
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

    public String getUnitofmeasure() {
        return unitofmeasure;
    }

    public void setUnitofmeasure(String unitofmeasure) {
        this.unitofmeasure = unitofmeasure;
    }

    public String getProductserialnumber() {
        return productserialnumber;
    }

    public void setProductserialnumber(String productserialnumber) {
        this.productserialnumber = productserialnumber;
    }

    public Date getDatereturned() {
        return datereturned;
    }

    public void setDatereturned(Date datereturned) {
        this.datereturned = datereturned;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getSamplesize() {
        return samplesize;
    }

    public void setSamplesize(String samplesize) {
        this.samplesize = samplesize;
    }

    public String getProductmodel() {
        return productmodel;
    }

    public void setProductmodel(String productmodel) {
        this.productmodel = productmodel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getDatesampled() {
        return datesampled;
    }

    public void setDatesampled(Date datesampled) {
        this.datesampled = datesampled;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCountryoforigin() {
        return countryoforigin;
    }

    public void setCountryoforigin(String countryoforigin) {
        this.countryoforigin = countryoforigin;
    }

    @XmlTransient
    @JsonIgnore
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Producttest> getProducttestList() {
        return producttestList;
    }

    public void setProducttestList(List<Producttest> producttestList) {
        this.producttestList = producttestList;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        if (!(object instanceof Jobsample)) {
            return false;
        }
        Jobsample other = (Jobsample) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Jobsample[ id=" + id + " ]";
    }
    
}
