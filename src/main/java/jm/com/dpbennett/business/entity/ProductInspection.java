/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "productinspection")
@XmlRootElement
public class ProductInspection implements Serializable, Comparable, BusinessEntity, Product {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name = "";
    private String type = "";
    private String model = "";
    private String serialNumber = "";
    private String containerSize = "";
    private String containerNumber = "";
    private String productCategory = "";
    private String productGroupPackageType = "";
    private Integer numGroupPackages;
    private Integer numProductsPerGroupPackage;
    private String productPackageType;
    private Integer quantity;
    private String quantityUnit;
    private Boolean hasQuantityDescription = false;
    private String quantityDescription;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Distributor distributor;
    private String brand;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateChecked;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date timeChecked;
    @OneToOne(cascade = CascadeType.ALL)
    private Client client;
    @Column(length = 1024)
    private String specificationSummary;
    private Boolean hasCountryOfOrigin = false;
    private String countryOfOrigin;
    private Boolean hasBusinessSource = false;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Business businessSource;
    private Boolean hasIngredients = false;
    @Column(length = 1024)
    private String ingredients;
    private Boolean hasPictorialRepresentation = false;
    private String imageURL;
    private Boolean hasInstructions = false;
    @Column(length = 1024)
    private String instructions;
    private String batchCode;
    private String dateMark;
    @Column(length = 1024)
    private String precautionStatement;
    @Column(length = 1024)
    private String comments;
    private String status;
    private Integer sampleSize;
    private Integer numProductsSampled;
    private String sampleSizeUnits;
    private Boolean sampledForLabelAssessment;
    private Boolean sampledForTesting;
    private Boolean sampledForInvestigation;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee inspector;
    private String tariffCode;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffCode() {
        if (tariffCode == null) {
            tariffCode = "";
        }
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public Employee getInspector() {
        if (inspector == null) {
            return new Employee();
        }
        return inspector;
    }

    public void setInspector(Employee inspector) {
        this.inspector = inspector;
    }

    public String getProductCategory() {
        if (productCategory == null) {
            productCategory = "";
        }
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Distributor getDistributor() {
        if (distributor == null) {
            distributor = new Distributor();
        }
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public Boolean getSampledForInvestigation() {
        return sampledForInvestigation;
    }

    public void setSampledForInvestigation(Boolean sampledForInvestigation) {
        this.sampledForInvestigation = sampledForInvestigation;
    }

    public Integer getNumProductsSampled() {
        return numProductsSampled;
    }

    public void setNumProductsSampled(Integer numProductsSampled) {
        this.numProductsSampled = numProductsSampled;
    }

    public String getProductGroupPackageType() {
        return productGroupPackageType;
    }

    public void setProductGroupPackageType(String productGroupPackageType) {
        this.productGroupPackageType = productGroupPackageType;
    }

    public Integer getNumGroupPackages() {
        return numGroupPackages;
    }

    public void setNumGroupPackages(Integer numGroupPackages) {
        this.numGroupPackages = numGroupPackages;
    }

    public Integer getNumProductsPerGroupPackage() {
        return numProductsPerGroupPackage;
    }

    public void setNumProductsPerGroupPackage(Integer numProductsPerGroupPackage) {
        this.numProductsPerGroupPackage = numProductsPerGroupPackage;
    }

    public String getProductPackageType() {
        return productPackageType;
    }

    public void setProductPackageType(String productPackageType) {
        this.productPackageType = productPackageType;
    }

    public String getContainerNumber() {
        if (containerNumber == null) {
            containerNumber = "";
        }
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public Boolean getSampledForLabelAssessment() {
        if (sampledForLabelAssessment == null) {
            sampledForLabelAssessment = false;
        }
        return sampledForLabelAssessment;
    }

    public void setSampledForLabelAssessment(Boolean sampledForLabelAssessment) {
        this.sampledForLabelAssessment = sampledForLabelAssessment;
    }

    public Boolean getSampledForTesting() {
        if (sampledForTesting == null) {
            sampledForTesting = false;
        }
        return sampledForTesting;
    }

    public void setSampledForTesting(Boolean sampledForTesting) {
        this.sampledForTesting = sampledForTesting;
    }

    public String getSampleSizeUnits() {
        return sampleSizeUnits;
    }

    public void setSampleSizeUnits(String sampleSizeUnits) {
        this.sampleSizeUnits = sampleSizeUnits;
    }

    public String getContainerSize() {
        if (containerSize == null) {
            containerSize = "";
        }
        return containerSize;
    }

    public void setContainerSize(String containerSize) {
        this.containerSize = containerSize;
    }

    public String getQuantityUnit() {
        if (quantityUnit == null) {
            quantityUnit = "";
        }
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getQuantityText() {

        try {
            if (quantity != null) {
                return quantity.toString();
            } else {
                return "0";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "0";
        }
    }

    public void setQuantityText(String text) {
        try {
            setQuantity(Integer.parseInt(text));
        } catch (Exception e) {
            System.out.println(e);
            setQuantity(0);
        }
    }

    public String getNumProductsSampledText() {

        try {
            if (numProductsSampled != null) {
                return numProductsSampled.toString();
            } else {
                return "0";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "0";
        }
    }

    public void setNumProductsSampledText(String text) {
        try {
            setNumProductsSampled(Integer.parseInt(text));
        } catch (Exception e) {
            System.out.println(e);
            setQuantity(0);
        }
    }

    public String getSampleSizeText() {

        try {
            if (sampleSize != null) {
                return sampleSize.toString();
            } else {
                return "0";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "0";
        }
    }

    public void setSampleSizeText(String text) {
        try {
            setSampleSize(Integer.parseInt(text));
        } catch (Exception e) {
            System.out.println(e);
            setSampleSize(0);
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public Integer getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(Integer sampleSize) {
        this.sampleSize = sampleSize;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getHasBusinessSource() {
        return hasBusinessSource;
    }

    public void setHasBusinessSource(Boolean hasBusinessSource) {
        this.hasBusinessSource = hasBusinessSource;
    }

    public Boolean getHasCountryOfOrigin() {
        return hasCountryOfOrigin;
    }

    public void setHasCountryOfOrigin(Boolean hasCountryOfOrigin) {
        this.hasCountryOfOrigin = hasCountryOfOrigin;
    }

    public Boolean getHasIngredients() {
        return hasIngredients;
    }

    public void setHasIngredients(Boolean hasIngredients) {
        this.hasIngredients = hasIngredients;
    }

    public Boolean getHasInstructions() {
        return hasInstructions;
    }

    public void setHasInstructions(Boolean hasInstructions) {
        this.hasInstructions = hasInstructions;
    }

    public Boolean getHasPictorialRepresentation() {
        return hasPictorialRepresentation;
    }

    public void setHasPictorialRepresentation(Boolean hasPictorialRepresentation) {
        this.hasPictorialRepresentation = hasPictorialRepresentation;
    }

    public Boolean getHasQuantityDescription() {
        return hasQuantityDescription;
    }

    public void setHasQuantityDescription(Boolean hasQuantityDescription) {
        this.hasQuantityDescription = hasQuantityDescription;
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
        if (!(object instanceof ProductInspection)) {
            return false;
        }
        ProductInspection other = (ProductInspection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
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
    public String getType() {
        if (type == null) {
            type = "";
        }
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Manufacturer getManufacturer() {
        if (manufacturer == null) {
            manufacturer = new Manufacturer();
        }

        return manufacturer;
    }

    @Override
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Business getBusinessSource() {
        if (businessSource == null) {
            businessSource = new Business();
        }
        return businessSource;
    }

    public void setBusinessSource(Business businessSource) {
        this.businessSource = businessSource;
    }

   
    public Client getClient() {
        if (client == null) {
            return new Client("");
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Date getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(Date dateChecked) {
        this.dateChecked = dateChecked;
    }

    public String getDateMark() {
        return dateMark;
    }

    public void setDateMark(String dateMark) {
        this.dateMark = dateMark;
    }

    public String getImageURL() {
        if (imageURL == null) {
            imageURL = "";
        }
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public String getPrecautionStatement() {
        return precautionStatement;
    }

    public void setPrecautionStatement(String precautionStatement) {
        this.precautionStatement = precautionStatement;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getQuantityDescription() {
        return quantityDescription;
    }

    public void setQuantityDescription(String quantityDescription) {
        this.quantityDescription = quantityDescription;
    }

    public String getSpecificationSummary() {
        return specificationSummary;
    }

    public void setSpecificationSummary(String specificationSummary) {
        this.specificationSummary = specificationSummary;
    }

    public Date getTimeChecked() {
        return timeChecked;
    }

    public void setTimeChecked(Date timeChecked) {
        this.timeChecked = timeChecked;
    }

    @Override
    public int compareTo(Object o) {
        String thisIdStr = (this.getId() == null ? "" + Integer.MAX_VALUE : this.getId().toString()); 
        String oIdStr = (((ProductInspection) o).getId() == null ? "" + Integer.MAX_VALUE : ((ProductInspection) o).getId().toString()); 
        
        //return Collator.getInstance().compare(this.getId().toString(), ((ProductInspection) o).getId().toString());
        return Collator.getInstance().compare(thisIdStr, oIdStr);
    }

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
