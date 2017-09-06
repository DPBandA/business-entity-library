/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "manufacturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manufacturer.findAll", query = "SELECT m FROM Manufacturer m")
    , @NamedQuery(name = "Manufacturer.findById", query = "SELECT m FROM Manufacturer m WHERE m.id = :id")
    , @NamedQuery(name = "Manufacturer.findByPhone", query = "SELECT m FROM Manufacturer m WHERE m.phone = :phone")
    , @NamedQuery(name = "Manufacturer.findByFax", query = "SELECT m FROM Manufacturer m WHERE m.fax = :fax")
    , @NamedQuery(name = "Manufacturer.findByEmail", query = "SELECT m FROM Manufacturer m WHERE m.email = :email")
    , @NamedQuery(name = "Manufacturer.findByName", query = "SELECT m FROM Manufacturer m WHERE m.name = :name")
    , @NamedQuery(name = "Manufacturer.findByStreet", query = "SELECT m FROM Manufacturer m WHERE m.street = :street")
    , @NamedQuery(name = "Manufacturer.findByPo", query = "SELECT m FROM Manufacturer m WHERE m.po = :po")
    , @NamedQuery(name = "Manufacturer.findByCity", query = "SELECT m FROM Manufacturer m WHERE m.city = :city")
    , @NamedQuery(name = "Manufacturer.findByCountry", query = "SELECT m FROM Manufacturer m WHERE m.country = :country")})
public class Manufacturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Fax")
    private String fax;
    @Column(name = "Email")
    private String email;
    @Column(name = "Name")
    private String name;
    @Column(name = "Street")
    private String street;
    @Column(name = "PO")
    private String po;
    @Column(name = "City")
    private String city;
    @Column(name = "Country")
    private String country;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Foodsample> foodsampleList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Testmeasure> testmeasureList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Seal> sealList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Scale> scaleList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Sticker> stickerList;
    @OneToMany(mappedBy = "manufacturerId")
    private List<Foodproduct> foodproductList;

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
    public List<Foodsample> getFoodsampleList() {
        return foodsampleList;
    }

    public void setFoodsampleList(List<Foodsample> foodsampleList) {
        this.foodsampleList = foodsampleList;
    }

    @XmlTransient
    public List<Testmeasure> getTestmeasureList() {
        return testmeasureList;
    }

    public void setTestmeasureList(List<Testmeasure> testmeasureList) {
        this.testmeasureList = testmeasureList;
    }

    @XmlTransient
    public List<Seal> getSealList() {
        return sealList;
    }

    public void setSealList(List<Seal> sealList) {
        this.sealList = sealList;
    }

    @XmlTransient
    public List<Scale> getScaleList() {
        return scaleList;
    }

    public void setScaleList(List<Scale> scaleList) {
        this.scaleList = scaleList;
    }

    @XmlTransient
    public List<Sticker> getStickerList() {
        return stickerList;
    }

    public void setStickerList(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }

    @XmlTransient
    public List<Foodproduct> getFoodproductList() {
        return foodproductList;
    }

    public void setFoodproductList(List<Foodproduct> foodproductList) {
        this.foodproductList = foodproductList;
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
        return "jm.com.dpba.business.entity.Manufacturer[ id=" + id + " ]";
    }
    
}
