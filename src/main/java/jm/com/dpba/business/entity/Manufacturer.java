/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "manufacturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manufacturer.findAll", query = "SELECT m FROM Manufacturer m"),
    @NamedQuery(name = "Manufacturer.findById", query = "SELECT m FROM Manufacturer m WHERE m.id = :id"),
    @NamedQuery(name = "Manufacturer.findByPhone", query = "SELECT m FROM Manufacturer m WHERE m.phone = :phone"),
    @NamedQuery(name = "Manufacturer.findByFax", query = "SELECT m FROM Manufacturer m WHERE m.fax = :fax"),
    @NamedQuery(name = "Manufacturer.findByEmail", query = "SELECT m FROM Manufacturer m WHERE m.email = :email"),
    @NamedQuery(name = "Manufacturer.findByName", query = "SELECT m FROM Manufacturer m WHERE m.name = :name"),
    @NamedQuery(name = "Manufacturer.findByStreet", query = "SELECT m FROM Manufacturer m WHERE m.street = :street"),
    @NamedQuery(name = "Manufacturer.findByPo", query = "SELECT m FROM Manufacturer m WHERE m.po = :po"),
    @NamedQuery(name = "Manufacturer.findByCity", query = "SELECT m FROM Manufacturer m WHERE m.city = :city"),
    @NamedQuery(name = "Manufacturer.findByCountry", query = "SELECT m FROM Manufacturer m WHERE m.country = :country")})
public class Manufacturer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "Fax")
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "Email")
    private String email;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    @Size(max = 255)
    @Column(name = "Street")
    private String street;
    @Size(max = 255)
    @Column(name = "PO")
    private String po;
    @Size(max = 255)
    @Column(name = "City")
    private String city;
    @Size(max = 255)
    @Column(name = "Country")
    private String country;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Marketproduct> marketproductList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Productinspection> productinspectionList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Foodsample> foodsampleList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Testmeasure> testmeasureList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Jobsample> jobsampleList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Seal> sealList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Scale> scaleList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Sticker> stickerList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Foodproduct> foodproductList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Petrolpump> petrolpumpList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Petrolpumpnozzle> petrolpumpnozzleList;

    public Manufacturer() {
    }

    public Manufacturer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marketproduct> getMarketproductList() {
        return marketproductList;
    }

    public void setMarketproductList(List<Marketproduct> marketproductList) {
        this.marketproductList = marketproductList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Productinspection> getProductinspectionList() {
        return productinspectionList;
    }

    public void setProductinspectionList(List<Productinspection> productinspectionList) {
        this.productinspectionList = productinspectionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodsample> getFoodsampleList() {
        return foodsampleList;
    }

    public void setFoodsampleList(List<Foodsample> foodsampleList) {
        this.foodsampleList = foodsampleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Testmeasure> getTestmeasureList() {
        return testmeasureList;
    }

    public void setTestmeasureList(List<Testmeasure> testmeasureList) {
        this.testmeasureList = testmeasureList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobsample> getJobsampleList() {
        return jobsampleList;
    }

    public void setJobsampleList(List<Jobsample> jobsampleList) {
        this.jobsampleList = jobsampleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Seal> getSealList() {
        return sealList;
    }

    public void setSealList(List<Seal> sealList) {
        this.sealList = sealList;
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
    public List<Sticker> getStickerList() {
        return stickerList;
    }

    public void setStickerList(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodproduct> getFoodproductList() {
        return foodproductList;
    }

    public void setFoodproductList(List<Foodproduct> foodproductList) {
        this.foodproductList = foodproductList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpump> getPetrolpumpList() {
        return petrolpumpList;
    }

    public void setPetrolpumpList(List<Petrolpump> petrolpumpList) {
        this.petrolpumpList = petrolpumpList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzle> getPetrolpumpnozzleList() {
        return petrolpumpnozzleList;
    }

    public void setPetrolpumpnozzleList(List<Petrolpumpnozzle> petrolpumpnozzleList) {
        this.petrolpumpnozzleList = petrolpumpnozzleList;
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
        if (!(object instanceof Manufacturer)) {
            return false;
        }
        Manufacturer other = (Manufacturer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Manufacturer[ id=" + id + " ]";
    }
    
}
