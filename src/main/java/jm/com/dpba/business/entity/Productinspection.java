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
@Table(name = "productinspection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productinspection.findAll", query = "SELECT p FROM Productinspection p"),
    @NamedQuery(name = "Productinspection.findById", query = "SELECT p FROM Productinspection p WHERE p.id = :id"),
    @NamedQuery(name = "Productinspection.findByBatchcode", query = "SELECT p FROM Productinspection p WHERE p.batchcode = :batchcode"),
    @NamedQuery(name = "Productinspection.findByBrand", query = "SELECT p FROM Productinspection p WHERE p.brand = :brand"),
    @NamedQuery(name = "Productinspection.findByComments", query = "SELECT p FROM Productinspection p WHERE p.comments = :comments"),
    @NamedQuery(name = "Productinspection.findByContainernumber", query = "SELECT p FROM Productinspection p WHERE p.containernumber = :containernumber"),
    @NamedQuery(name = "Productinspection.findByContainersize", query = "SELECT p FROM Productinspection p WHERE p.containersize = :containersize"),
    @NamedQuery(name = "Productinspection.findByCountryoforigin", query = "SELECT p FROM Productinspection p WHERE p.countryoforigin = :countryoforigin"),
    @NamedQuery(name = "Productinspection.findByDatechecked", query = "SELECT p FROM Productinspection p WHERE p.datechecked = :datechecked"),
    @NamedQuery(name = "Productinspection.findByDatemark", query = "SELECT p FROM Productinspection p WHERE p.datemark = :datemark"),
    @NamedQuery(name = "Productinspection.findByHasbusinesssource", query = "SELECT p FROM Productinspection p WHERE p.hasbusinesssource = :hasbusinesssource"),
    @NamedQuery(name = "Productinspection.findByHascountryoforigin", query = "SELECT p FROM Productinspection p WHERE p.hascountryoforigin = :hascountryoforigin"),
    @NamedQuery(name = "Productinspection.findByHasingredients", query = "SELECT p FROM Productinspection p WHERE p.hasingredients = :hasingredients"),
    @NamedQuery(name = "Productinspection.findByHasinstructions", query = "SELECT p FROM Productinspection p WHERE p.hasinstructions = :hasinstructions"),
    @NamedQuery(name = "Productinspection.findByHaspictorialrepresentation", query = "SELECT p FROM Productinspection p WHERE p.haspictorialrepresentation = :haspictorialrepresentation"),
    @NamedQuery(name = "Productinspection.findByHasquantitydescription", query = "SELECT p FROM Productinspection p WHERE p.hasquantitydescription = :hasquantitydescription"),
    @NamedQuery(name = "Productinspection.findByImageurl", query = "SELECT p FROM Productinspection p WHERE p.imageurl = :imageurl"),
    @NamedQuery(name = "Productinspection.findByIngredients", query = "SELECT p FROM Productinspection p WHERE p.ingredients = :ingredients"),
    @NamedQuery(name = "Productinspection.findByInstructions", query = "SELECT p FROM Productinspection p WHERE p.instructions = :instructions"),
    @NamedQuery(name = "Productinspection.findByModel", query = "SELECT p FROM Productinspection p WHERE p.model = :model"),
    @NamedQuery(name = "Productinspection.findByName", query = "SELECT p FROM Productinspection p WHERE p.name = :name"),
    @NamedQuery(name = "Productinspection.findByNumgrouppackages", query = "SELECT p FROM Productinspection p WHERE p.numgrouppackages = :numgrouppackages"),
    @NamedQuery(name = "Productinspection.findByNumproductspergrouppackage", query = "SELECT p FROM Productinspection p WHERE p.numproductspergrouppackage = :numproductspergrouppackage"),
    @NamedQuery(name = "Productinspection.findByNumproductssampled", query = "SELECT p FROM Productinspection p WHERE p.numproductssampled = :numproductssampled"),
    @NamedQuery(name = "Productinspection.findByPrecautionstatement", query = "SELECT p FROM Productinspection p WHERE p.precautionstatement = :precautionstatement"),
    @NamedQuery(name = "Productinspection.findByProductcategory", query = "SELECT p FROM Productinspection p WHERE p.productcategory = :productcategory"),
    @NamedQuery(name = "Productinspection.findByProductgrouppackagetype", query = "SELECT p FROM Productinspection p WHERE p.productgrouppackagetype = :productgrouppackagetype"),
    @NamedQuery(name = "Productinspection.findByProductpackagetype", query = "SELECT p FROM Productinspection p WHERE p.productpackagetype = :productpackagetype"),
    @NamedQuery(name = "Productinspection.findByQuantity", query = "SELECT p FROM Productinspection p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Productinspection.findByQuantitydescription", query = "SELECT p FROM Productinspection p WHERE p.quantitydescription = :quantitydescription"),
    @NamedQuery(name = "Productinspection.findByQuantityunit", query = "SELECT p FROM Productinspection p WHERE p.quantityunit = :quantityunit"),
    @NamedQuery(name = "Productinspection.findBySamplesize", query = "SELECT p FROM Productinspection p WHERE p.samplesize = :samplesize"),
    @NamedQuery(name = "Productinspection.findBySamplesizeunits", query = "SELECT p FROM Productinspection p WHERE p.samplesizeunits = :samplesizeunits"),
    @NamedQuery(name = "Productinspection.findBySampledforinvestigation", query = "SELECT p FROM Productinspection p WHERE p.sampledforinvestigation = :sampledforinvestigation"),
    @NamedQuery(name = "Productinspection.findBySampledforlabelassessment", query = "SELECT p FROM Productinspection p WHERE p.sampledforlabelassessment = :sampledforlabelassessment"),
    @NamedQuery(name = "Productinspection.findBySampledfortesting", query = "SELECT p FROM Productinspection p WHERE p.sampledfortesting = :sampledfortesting"),
    @NamedQuery(name = "Productinspection.findBySerialnumber", query = "SELECT p FROM Productinspection p WHERE p.serialnumber = :serialnumber"),
    @NamedQuery(name = "Productinspection.findBySpecificationsummary", query = "SELECT p FROM Productinspection p WHERE p.specificationsummary = :specificationsummary"),
    @NamedQuery(name = "Productinspection.findByStatus", query = "SELECT p FROM Productinspection p WHERE p.status = :status"),
    @NamedQuery(name = "Productinspection.findByTimechecked", query = "SELECT p FROM Productinspection p WHERE p.timechecked = :timechecked"),
    @NamedQuery(name = "Productinspection.findByType", query = "SELECT p FROM Productinspection p WHERE p.type = :type")})
public class Productinspection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "BATCHCODE")
    private String batchcode;
    @Size(max = 255)
    @Column(name = "BRAND")
    private String brand;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @Size(max = 255)
    @Column(name = "CONTAINERNUMBER")
    private String containernumber;
    @Size(max = 255)
    @Column(name = "CONTAINERSIZE")
    private String containersize;
    @Size(max = 255)
    @Column(name = "COUNTRYOFORIGIN")
    private String countryoforigin;
    @Column(name = "DATECHECKED")
    @Temporal(TemporalType.DATE)
    private Date datechecked;
    @Size(max = 255)
    @Column(name = "DATEMARK")
    private String datemark;
    @Column(name = "HASBUSINESSSOURCE")
    private Boolean hasbusinesssource;
    @Column(name = "HASCOUNTRYOFORIGIN")
    private Boolean hascountryoforigin;
    @Column(name = "HASINGREDIENTS")
    private Boolean hasingredients;
    @Column(name = "HASINSTRUCTIONS")
    private Boolean hasinstructions;
    @Column(name = "HASPICTORIALREPRESENTATION")
    private Boolean haspictorialrepresentation;
    @Column(name = "HASQUANTITYDESCRIPTION")
    private Boolean hasquantitydescription;
    @Size(max = 255)
    @Column(name = "IMAGEURL")
    private String imageurl;
    @Size(max = 1024)
    @Column(name = "INGREDIENTS")
    private String ingredients;
    @Size(max = 1024)
    @Column(name = "INSTRUCTIONS")
    private String instructions;
    @Size(max = 255)
    @Column(name = "MODEL")
    private String model;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "NUMGROUPPACKAGES")
    private Integer numgrouppackages;
    @Column(name = "NUMPRODUCTSPERGROUPPACKAGE")
    private Integer numproductspergrouppackage;
    @Column(name = "NUMPRODUCTSSAMPLED")
    private Integer numproductssampled;
    @Size(max = 1024)
    @Column(name = "PRECAUTIONSTATEMENT")
    private String precautionstatement;
    @Size(max = 255)
    @Column(name = "PRODUCTCATEGORY")
    private String productcategory;
    @Size(max = 255)
    @Column(name = "PRODUCTGROUPPACKAGETYPE")
    private String productgrouppackagetype;
    @Size(max = 255)
    @Column(name = "PRODUCTPACKAGETYPE")
    private String productpackagetype;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Size(max = 255)
    @Column(name = "QUANTITYDESCRIPTION")
    private String quantitydescription;
    @Size(max = 255)
    @Column(name = "QUANTITYUNIT")
    private String quantityunit;
    @Column(name = "SAMPLESIZE")
    private Integer samplesize;
    @Size(max = 255)
    @Column(name = "SAMPLESIZEUNITS")
    private String samplesizeunits;
    @Column(name = "SAMPLEDFORINVESTIGATION")
    private Boolean sampledforinvestigation;
    @Column(name = "SAMPLEDFORLABELASSESSMENT")
    private Boolean sampledforlabelassessment;
    @Column(name = "SAMPLEDFORTESTING")
    private Boolean sampledfortesting;
    @Size(max = 255)
    @Column(name = "SERIALNUMBER")
    private String serialnumber;
    @Size(max = 1024)
    @Column(name = "SPECIFICATIONSUMMARY")
    private String specificationsummary;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TIMECHECKED")
    @Temporal(TemporalType.TIME)
    private Date timechecked;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @JoinTable(name = "samplerequest_productinspection", joinColumns = {
        @JoinColumn(name = "products_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "SampleRequest_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Samplerequest> samplerequestList;
    @ManyToMany(mappedBy = "productinspectionList")
    private List<Compliancesurvey> compliancesurveyList;
    @JoinTable(name = "portofentrydetention_productinspection", joinColumns = {
        @JoinColumn(name = "products_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PortOfEntryDetention_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Portofentrydetention> portofentrydetentionList;
    @JoinTable(name = "marketproductsurvey_productinspection", joinColumns = {
        @JoinColumn(name = "products_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "MarketProductSurvey_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Marketproductsurvey> marketproductsurveyList;
    @JoinColumn(name = "BUSINESSSOURCE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Business businesssourceId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "DISTRIBUTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Distributor distributorId;
    @JoinColumn(name = "INSPECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee inspectorId;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;

    public Productinspection() {
    }

    public Productinspection(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchcode() {
        return batchcode;
    }

    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContainernumber() {
        return containernumber;
    }

    public void setContainernumber(String containernumber) {
        this.containernumber = containernumber;
    }

    public String getContainersize() {
        return containersize;
    }

    public void setContainersize(String containersize) {
        this.containersize = containersize;
    }

    public String getCountryoforigin() {
        return countryoforigin;
    }

    public void setCountryoforigin(String countryoforigin) {
        this.countryoforigin = countryoforigin;
    }

    public Date getDatechecked() {
        return datechecked;
    }

    public void setDatechecked(Date datechecked) {
        this.datechecked = datechecked;
    }

    public String getDatemark() {
        return datemark;
    }

    public void setDatemark(String datemark) {
        this.datemark = datemark;
    }

    public Boolean getHasbusinesssource() {
        return hasbusinesssource;
    }

    public void setHasbusinesssource(Boolean hasbusinesssource) {
        this.hasbusinesssource = hasbusinesssource;
    }

    public Boolean getHascountryoforigin() {
        return hascountryoforigin;
    }

    public void setHascountryoforigin(Boolean hascountryoforigin) {
        this.hascountryoforigin = hascountryoforigin;
    }

    public Boolean getHasingredients() {
        return hasingredients;
    }

    public void setHasingredients(Boolean hasingredients) {
        this.hasingredients = hasingredients;
    }

    public Boolean getHasinstructions() {
        return hasinstructions;
    }

    public void setHasinstructions(Boolean hasinstructions) {
        this.hasinstructions = hasinstructions;
    }

    public Boolean getHaspictorialrepresentation() {
        return haspictorialrepresentation;
    }

    public void setHaspictorialrepresentation(Boolean haspictorialrepresentation) {
        this.haspictorialrepresentation = haspictorialrepresentation;
    }

    public Boolean getHasquantitydescription() {
        return hasquantitydescription;
    }

    public void setHasquantitydescription(Boolean hasquantitydescription) {
        this.hasquantitydescription = hasquantitydescription;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumgrouppackages() {
        return numgrouppackages;
    }

    public void setNumgrouppackages(Integer numgrouppackages) {
        this.numgrouppackages = numgrouppackages;
    }

    public Integer getNumproductspergrouppackage() {
        return numproductspergrouppackage;
    }

    public void setNumproductspergrouppackage(Integer numproductspergrouppackage) {
        this.numproductspergrouppackage = numproductspergrouppackage;
    }

    public Integer getNumproductssampled() {
        return numproductssampled;
    }

    public void setNumproductssampled(Integer numproductssampled) {
        this.numproductssampled = numproductssampled;
    }

    public String getPrecautionstatement() {
        return precautionstatement;
    }

    public void setPrecautionstatement(String precautionstatement) {
        this.precautionstatement = precautionstatement;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public String getProductgrouppackagetype() {
        return productgrouppackagetype;
    }

    public void setProductgrouppackagetype(String productgrouppackagetype) {
        this.productgrouppackagetype = productgrouppackagetype;
    }

    public String getProductpackagetype() {
        return productpackagetype;
    }

    public void setProductpackagetype(String productpackagetype) {
        this.productpackagetype = productpackagetype;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getQuantitydescription() {
        return quantitydescription;
    }

    public void setQuantitydescription(String quantitydescription) {
        this.quantitydescription = quantitydescription;
    }

    public String getQuantityunit() {
        return quantityunit;
    }

    public void setQuantityunit(String quantityunit) {
        this.quantityunit = quantityunit;
    }

    public Integer getSamplesize() {
        return samplesize;
    }

    public void setSamplesize(Integer samplesize) {
        this.samplesize = samplesize;
    }

    public String getSamplesizeunits() {
        return samplesizeunits;
    }

    public void setSamplesizeunits(String samplesizeunits) {
        this.samplesizeunits = samplesizeunits;
    }

    public Boolean getSampledforinvestigation() {
        return sampledforinvestigation;
    }

    public void setSampledforinvestigation(Boolean sampledforinvestigation) {
        this.sampledforinvestigation = sampledforinvestigation;
    }

    public Boolean getSampledforlabelassessment() {
        return sampledforlabelassessment;
    }

    public void setSampledforlabelassessment(Boolean sampledforlabelassessment) {
        this.sampledforlabelassessment = sampledforlabelassessment;
    }

    public Boolean getSampledfortesting() {
        return sampledfortesting;
    }

    public void setSampledfortesting(Boolean sampledfortesting) {
        this.sampledfortesting = sampledfortesting;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getSpecificationsummary() {
        return specificationsummary;
    }

    public void setSpecificationsummary(String specificationsummary) {
        this.specificationsummary = specificationsummary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public List<Compliancesurvey> getCompliancesurveyList() {
        return compliancesurveyList;
    }

    public void setCompliancesurveyList(List<Compliancesurvey> compliancesurveyList) {
        this.compliancesurveyList = compliancesurveyList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Portofentrydetention> getPortofentrydetentionList() {
        return portofentrydetentionList;
    }

    public void setPortofentrydetentionList(List<Portofentrydetention> portofentrydetentionList) {
        this.portofentrydetentionList = portofentrydetentionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marketproductsurvey> getMarketproductsurveyList() {
        return marketproductsurveyList;
    }

    public void setMarketproductsurveyList(List<Marketproductsurvey> marketproductsurveyList) {
        this.marketproductsurveyList = marketproductsurveyList;
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

    public Distributor getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Distributor distributorId) {
        this.distributorId = distributorId;
    }

    public Employee getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Employee inspectorId) {
        this.inspectorId = inspectorId;
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
        if (!(object instanceof Productinspection)) {
            return false;
        }
        Productinspection other = (Productinspection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Productinspection[ id=" + id + " ]";
    }
    
}
