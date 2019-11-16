/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity;

import jm.com.dpbennett.business.entity.hrm.BusinessOffice;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.Message;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "jobsample")
@NamedQueries({
    @NamedQuery(name = "findAllJobSamples", query = "SELECT e FROM JobSample e ORDER BY e.dateReceived")
})
@XmlRootElement
public class JobSample implements Product, Sample, Serializable, Comparable, BusinessEntity {

    private static final long serialVersionUId = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // general
    private Long id;
    private Long jobId;
    private String name;
    private String code;
    private String reference;
    private Long referenceIndex;
    private Long sampleQuantity;
    private Long quantity;
    private String unitOfMeasure;
    @Column(length = 1024)
    private String description;
    private String type;
    @Column(length = 1024)
    private String comments;
    private String productType;
    private String productModel;
    private String productSerialNumber;
    private String productCode;
    private String productBrand;
    private String sampleSize;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client client;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<ProductTest> tests;
    // end new
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSampled;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateReturned;
    private Integer methodOfDisposal;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice regulatoryOffice;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee sampledBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee receivedBy;
    private String countryOfOrigin;
    @Transient
    private Boolean isToBeAdded;
    @Transient
    private Boolean isDirty;

    public JobSample() {
        tests = new ArrayList<>();
    }

    public JobSample(JobSample src) {
        copy(src);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }
        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    public Boolean getIsToBeAdded() {
        if (isToBeAdded == null) {
            isToBeAdded = false;
        }
        return isToBeAdded;
    }

    public void setIsToBeAdded(Boolean isToBeAdded) {
        this.isToBeAdded = isToBeAdded;
    }

    public final void copy(JobSample src) {
        this.jobId = src.jobId;
        this.name = src.name;
        this.code = src.code;
        this.reference = src.reference;
        this.referenceIndex = src.referenceIndex;
        this.sampleQuantity = src.sampleQuantity;
        this.quantity = src.quantity;
        this.unitOfMeasure = src.unitOfMeasure;
        this.description = src.description;
        this.type = src.type;
        this.comments = src.comments;
        this.productType = src.productType;
        this.productModel = src.productModel;
        this.productSerialNumber = src.productSerialNumber;
        this.productCode = src.productCode;
        this.productBrand = src.productBrand;
        this.sampleSize = src.sampleSize;
        this.client = src.client;
        this.tests = src.tests;
        this.dateReceived = src.dateReceived;
        this.dateSampled = src.dateSampled;
        this.dateReturned = src.dateReturned;
        this.methodOfDisposal = src.methodOfDisposal;
        this.manufacturer = src.manufacturer;
        this.regulatoryOffice = src.regulatoryOffice;
        this.sampledBy = src.sampledBy;
        this.receivedBy = src.receivedBy;
        this.countryOfOrigin = src.countryOfOrigin;
        this.isToBeAdded = src.isToBeAdded;
        this.isDirty = src.isDirty;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public Client getClient() {
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

    public String getProductCode() {
        if (productCode == null) {
            productCode = "";
        }
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductSerialNumber() {
        return productSerialNumber;
    }

    public void setProductSerialNumber(String productSerialNumber) {
        this.productSerialNumber = productSerialNumber;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(String sampleSize) {
        this.sampleSize = sampleSize;
    }

    public List<ProductTest> getTests() {
        return tests;
    }

    public void setTests(List<ProductTest> tests) {
        this.tests = tests;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public Employee getReceivedBy() {
        if (receivedBy == null) {
            return new Employee();
        }
        return receivedBy;
    }

    @Override
    public void setReceivedBy(Employee receivedBy) {
        this.receivedBy = receivedBy;
    }

    @Override
    public Employee getSampledBy() {
        if (sampledBy == null) {
            return new Employee();
        }
        return sampledBy;
    }

    @Override
    public void setSampledBy(Employee sampledBy) {
        this.sampledBy = sampledBy;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Date getDateReturned() {
        return dateReturned;
    }

    @Override
    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    @Override
    public Date getDateSampled() {
        return dateSampled;
    }

    @Override
    public void setDateSampled(Date dateSampled) {
        this.dateSampled = dateSampled;
    }

    @Override
    public BusinessOffice getRegulatoryOffice() {
        return regulatoryOffice;
    }

    @Override
    public void setRegulatoryOffice(BusinessOffice regulatoryOffice) {
        this.regulatoryOffice = regulatoryOffice;
    }

    @Override
    public Long getSampleQuantity() {
        if (sampleQuantity == null) {
            return 1L;
        }
        return sampleQuantity;
    }

    @Override
    public void setSampleQuantity(Long sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    @Override
    public Integer getMethodOfDisposal() {
        if (methodOfDisposal == null) {
            methodOfDisposal = 0;
        }
        return methodOfDisposal;
    }

    @Override
    public void setMethodOfDisposal(Integer methodOfDisposal) {
        this.methodOfDisposal = methodOfDisposal;
    }

    @Override
    public String getReference() {
        if (reference == null) {
            reference = "";
        }
        return reference;
    }

    @Override
    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public Long getReferenceIndex() {
        if (referenceIndex == null) {
            referenceIndex = 0L;
        }
        return referenceIndex;
    }

    @Override
    public void setReferenceIndex(Long referenceIndex) {
        this.referenceIndex = referenceIndex;
    }

    @Override
    public Date getDateReceived() {
        return dateReceived;
    }

    @Override
    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Override
    public String getDescription() {
        if (description == null) {
            description = "";
        }
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Long getQuantity() {
        if (quantity == null) {
            quantity = 0L;
        }
        return quantity;
    }

    @Override
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @Override
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof JobSample)) {
            return false;
        }
        JobSample other = (JobSample) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "(" + reference + ") " + description;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.reference, ((JobSample) o).reference);
        //return Collator.getInstance().compare(this.getReferenceIndex().toString(), ((JobSample) o).getReferenceIndex().toString()); // tk
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public static boolean locateJobSampleByReference(
            ArrayList<JobSample> samples,
            String reference) {

        for (JobSample jobSample : samples) {
            if (jobSample.getReference().equals(reference)) {
                return true;
            }
        }

        return false;
    }

    public static boolean locateJobSampleById(ArrayList<JobSample> samples, Long id) {

        for (JobSample jobSample : samples) {
            if (Objects.equals(jobSample.getId(), id)) {
                return true;
            }
        }

        return false;
    }

    public static List<JobSample> findJobSamplesByJobId(EntityManager em, Long jobId) {

        try {
            List<JobSample> jobSamples
                    = em.createQuery("SELECT s FROM JobSample s "
                            + "WHERE s.jobId "
                            + "= '" + jobId + "'"
                            + " ORDER BY s.reference", JobSample.class).getResultList();
            return jobSamples;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static JobSample findJobSampleById(EntityManager em, Long Id) {
        return em.find(JobSample.class, Id);
    }

    public String getSampleDetail() {
        String detail = "";

        if (reference != null && !reference.equals("")) {
            detail = detail + "(" + reference + ") ";
        }

        if (description != null && !description.equals("")) {
            detail = detail + description;
        }

        if (productBrand != null && !productBrand.equals("")) {
            detail = detail + ", " + productBrand;
        }

        if (productModel != null && !productModel.equals("")) {
            detail = detail + ", " + productModel;
        }

        if (productSerialNumber != null && !productSerialNumber.equals("")) {
            detail = detail + ", " + productSerialNumber;
        }

        if (productCode != null && !productCode.equals("")) {
            detail = detail + ", " + productCode;
        }

        //if (sampleQuantity != null && !productCode.equals("")) {
        detail = detail + ", Sample Qty: " + getSampleQuantity();
        //}

        detail = detail + ", Product Qty: " + getQuantity();

        return detail;
    }

    public String getSampleBasicDetail() {
        String detail = "";

        if (productBrand != null && !productBrand.equals("")) {
            detail = detail + "Brand: " + productBrand;
        }

        if (productModel != null && !productModel.equals("")) {
            detail = detail + ", Model: " + productModel;
        }

        if (productSerialNumber != null && !productSerialNumber.equals("")) {
            detail = detail + ", Serial #: " + productSerialNumber;
        }

        if (productCode != null && !productCode.equals("")) {
            detail = detail + ", Code #: " + productCode;
        }

        return detail;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);            
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            return new ReturnMessage(false,
                "Job sample save error occurred",
                "An error occurred while saving job sample (Null/OL ID): " + e + ": " + this.getReference(),
                Message.SEVERITY_ERROR_NAME);
        }

    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
