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
@Table(name = "marketproduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marketproduct.findAll", query = "SELECT m FROM Marketproduct m"),
    @NamedQuery(name = "Marketproduct.findById", query = "SELECT m FROM Marketproduct m WHERE m.id = :id"),
    @NamedQuery(name = "Marketproduct.findByHasinstructions", query = "SELECT m FROM Marketproduct m WHERE m.hasinstructions = :hasinstructions"),
    @NamedQuery(name = "Marketproduct.findByBatchcode", query = "SELECT m FROM Marketproduct m WHERE m.batchcode = :batchcode"),
    @NamedQuery(name = "Marketproduct.findByInstructions", query = "SELECT m FROM Marketproduct m WHERE m.instructions = :instructions"),
    @NamedQuery(name = "Marketproduct.findByTimechecked", query = "SELECT m FROM Marketproduct m WHERE m.timechecked = :timechecked"),
    @NamedQuery(name = "Marketproduct.findByType", query = "SELECT m FROM Marketproduct m WHERE m.type = :type"),
    @NamedQuery(name = "Marketproduct.findBySpecificationsummary", query = "SELECT m FROM Marketproduct m WHERE m.specificationsummary = :specificationsummary"),
    @NamedQuery(name = "Marketproduct.findByIngredients", query = "SELECT m FROM Marketproduct m WHERE m.ingredients = :ingredients"),
    @NamedQuery(name = "Marketproduct.findByDatechecked", query = "SELECT m FROM Marketproduct m WHERE m.datechecked = :datechecked"),
    @NamedQuery(name = "Marketproduct.findByName", query = "SELECT m FROM Marketproduct m WHERE m.name = :name"),
    @NamedQuery(name = "Marketproduct.findByQuantity", query = "SELECT m FROM Marketproduct m WHERE m.quantity = :quantity"),
    @NamedQuery(name = "Marketproduct.findByDatemark", query = "SELECT m FROM Marketproduct m WHERE m.datemark = :datemark"),
    @NamedQuery(name = "Marketproduct.findByCountryoforigin", query = "SELECT m FROM Marketproduct m WHERE m.countryoforigin = :countryoforigin"),
    @NamedQuery(name = "Marketproduct.findByHasquantitydescription", query = "SELECT m FROM Marketproduct m WHERE m.hasquantitydescription = :hasquantitydescription"),
    @NamedQuery(name = "Marketproduct.findByStatus", query = "SELECT m FROM Marketproduct m WHERE m.status = :status"),
    @NamedQuery(name = "Marketproduct.findByQuantitydescription", query = "SELECT m FROM Marketproduct m WHERE m.quantitydescription = :quantitydescription"),
    @NamedQuery(name = "Marketproduct.findByHaspictorialrepresentation", query = "SELECT m FROM Marketproduct m WHERE m.haspictorialrepresentation = :haspictorialrepresentation"),
    @NamedQuery(name = "Marketproduct.findByHascountryoforigin", query = "SELECT m FROM Marketproduct m WHERE m.hascountryoforigin = :hascountryoforigin"),
    @NamedQuery(name = "Marketproduct.findByPrecautionstatement", query = "SELECT m FROM Marketproduct m WHERE m.precautionstatement = :precautionstatement"),
    @NamedQuery(name = "Marketproduct.findByHasbusinesssource", query = "SELECT m FROM Marketproduct m WHERE m.hasbusinesssource = :hasbusinesssource"),
    @NamedQuery(name = "Marketproduct.findByBrand", query = "SELECT m FROM Marketproduct m WHERE m.brand = :brand"),
    @NamedQuery(name = "Marketproduct.findByHasingredients", query = "SELECT m FROM Marketproduct m WHERE m.hasingredients = :hasingredients"),
    @NamedQuery(name = "Marketproduct.findByImageurl", query = "SELECT m FROM Marketproduct m WHERE m.imageurl = :imageurl"),
    @NamedQuery(name = "Marketproduct.findByComments", query = "SELECT m FROM Marketproduct m WHERE m.comments = :comments"),
    @NamedQuery(name = "Marketproduct.findBySamplesize", query = "SELECT m FROM Marketproduct m WHERE m.samplesize = :samplesize"),
    @NamedQuery(name = "Marketproduct.findByModel", query = "SELECT m FROM Marketproduct m WHERE m.model = :model"),
    @NamedQuery(name = "Marketproduct.findBySerialnumber", query = "SELECT m FROM Marketproduct m WHERE m.serialnumber = :serialnumber")})
public class Marketproduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "HASINSTRUCTIONS")
    private Boolean hasinstructions;
    @Size(max = 255)
    @Column(name = "BATCHCODE")
    private String batchcode;
    @Size(max = 1024)
    @Column(name = "INSTRUCTIONS")
    private String instructions;
    @Column(name = "TIMECHECKED")
    @Temporal(TemporalType.TIME)
    private Date timechecked;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 1024)
    @Column(name = "SPECIFICATIONSUMMARY")
    private String specificationsummary;
    @Size(max = 1024)
    @Column(name = "INGREDIENTS")
    private String ingredients;
    @Column(name = "DATECHECKED")
    @Temporal(TemporalType.DATE)
    private Date datechecked;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Size(max = 255)
    @Column(name = "DATEMARK")
    private String datemark;
    @Size(max = 255)
    @Column(name = "COUNTRYOFORIGIN")
    private String countryoforigin;
    @Column(name = "HASQUANTITYDESCRIPTION")
    private Boolean hasquantitydescription;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 255)
    @Column(name = "QUANTITYDESCRIPTION")
    private String quantitydescription;
    @Column(name = "HASPICTORIALREPRESENTATION")
    private Boolean haspictorialrepresentation;
    @Column(name = "HASCOUNTRYOFORIGIN")
    private Boolean hascountryoforigin;
    @Size(max = 1024)
    @Column(name = "PRECAUTIONSTATEMENT")
    private String precautionstatement;
    @Column(name = "HASBUSINESSSOURCE")
    private Boolean hasbusinesssource;
    @Size(max = 255)
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "HASINGREDIENTS")
    private Boolean hasingredients;
    @Size(max = 255)
    @Column(name = "IMAGEURL")
    private String imageurl;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "SAMPLESIZE")
    private BigInteger samplesize;
    @Size(max = 255)
    @Column(name = "MODEL")
    private String model;
    @Size(max = 255)
    @Column(name = "SERIALNUMBER")
    private String serialnumber;
    @ManyToMany(mappedBy = "marketproductList")
    private List<Samplerequest> samplerequestList;
    @JoinTable(name = "marketproductsurvey_marketproduct", joinColumns = {
        @JoinColumn(name = "products_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "MarketProductSurvey_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Marketproductsurvey> marketproductsurveyList;
    @JoinTable(name = "portofentrydetention_marketproduct", joinColumns = {
        @JoinColumn(name = "products_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PortOfEntryDetention_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Portofentrydetention> portofentrydetentionList;
    @JoinColumn(name = "BUSINESSSOURCE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Business businesssourceId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;

    public Marketproduct() {
    }

    public Marketproduct(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHasinstructions() {
        return hasinstructions;
    }

    public void setHasinstructions(Boolean hasinstructions) {
        this.hasinstructions = hasinstructions;
    }

    public String getBatchcode() {
        return batchcode;
    }

    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Date getTimechecked() {
        return timechecked;
    }

    public void setTimechecked(Date timechecked) {
        this.timechecked = timechecked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecificationsummary() {
        return specificationsummary;
    }

    public void setSpecificationsummary(String specificationsummary) {
        this.specificationsummary = specificationsummary;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Date getDatechecked() {
        return datechecked;
    }

    public void setDatechecked(Date datechecked) {
        this.datechecked = datechecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDatemark() {
        return datemark;
    }

    public void setDatemark(String datemark) {
        this.datemark = datemark;
    }

    public String getCountryoforigin() {
        return countryoforigin;
    }

    public void setCountryoforigin(String countryoforigin) {
        this.countryoforigin = countryoforigin;
    }

    public Boolean getHasquantitydescription() {
        return hasquantitydescription;
    }

    public void setHasquantitydescription(Boolean hasquantitydescription) {
        this.hasquantitydescription = hasquantitydescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuantitydescription() {
        return quantitydescription;
    }

    public void setQuantitydescription(String quantitydescription) {
        this.quantitydescription = quantitydescription;
    }

    public Boolean getHaspictorialrepresentation() {
        return haspictorialrepresentation;
    }

    public void setHaspictorialrepresentation(Boolean haspictorialrepresentation) {
        this.haspictorialrepresentation = haspictorialrepresentation;
    }

    public Boolean getHascountryoforigin() {
        return hascountryoforigin;
    }

    public void setHascountryoforigin(Boolean hascountryoforigin) {
        this.hascountryoforigin = hascountryoforigin;
    }

    public String getPrecautionstatement() {
        return precautionstatement;
    }

    public void setPrecautionstatement(String precautionstatement) {
        this.precautionstatement = precautionstatement;
    }

    public Boolean getHasbusinesssource() {
        return hasbusinesssource;
    }

    public void setHasbusinesssource(Boolean hasbusinesssource) {
        this.hasbusinesssource = hasbusinesssource;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getHasingredients() {
        return hasingredients;
    }

    public void setHasingredients(Boolean hasingredients) {
        this.hasingredients = hasingredients;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigInteger getSamplesize() {
        return samplesize;
    }

    public void setSamplesize(BigInteger samplesize) {
        this.samplesize = samplesize;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    @XmlTransient
    @JsonIgnore
    public List<Samplerequest> getSamplerequestList() {
        return samplerequestList;
    }

    public void setSamplerequestList(List<Samplerequest> samplerequestList) {
        this.samplerequestList = samplerequestList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marketproductsurvey> getMarketproductsurveyList() {
        return marketproductsurveyList;
    }

    public void setMarketproductsurveyList(List<Marketproductsurvey> marketproductsurveyList) {
        this.marketproductsurveyList = marketproductsurveyList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Portofentrydetention> getPortofentrydetentionList() {
        return portofentrydetentionList;
    }

    public void setPortofentrydetentionList(List<Portofentrydetention> portofentrydetentionList) {
        this.portofentrydetentionList = portofentrydetentionList;
    }

    public Business getBusinesssourceId() {
        return businesssourceId;
    }

    public void setBusinesssourceId(Business businesssourceId) {
        this.businesssourceId = businesssourceId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marketproduct)) {
            return false;
        }
        Marketproduct other = (Marketproduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Marketproduct[ id=" + id + " ]";
    }
    
}
