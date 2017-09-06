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
@Table(name = "foodproduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodproduct.findAll", query = "SELECT f FROM Foodproduct f"),
    @NamedQuery(name = "Foodproduct.findById", query = "SELECT f FROM Foodproduct f WHERE f.id = :id"),
    @NamedQuery(name = "Foodproduct.findByDatelasttested", query = "SELECT f FROM Foodproduct f WHERE f.datelasttested = :datelasttested"),
    @NamedQuery(name = "Foodproduct.findByName", query = "SELECT f FROM Foodproduct f WHERE f.name = :name"),
    @NamedQuery(name = "Foodproduct.findByDatelastmanufactured", query = "SELECT f FROM Foodproduct f WHERE f.datelastmanufactured = :datelastmanufactured"),
    @NamedQuery(name = "Foodproduct.findByBrand", query = "SELECT f FROM Foodproduct f WHERE f.brand = :brand"),
    @NamedQuery(name = "Foodproduct.findByActive", query = "SELECT f FROM Foodproduct f WHERE f.active = :active"),
    @NamedQuery(name = "Foodproduct.findByDatelastsampled", query = "SELECT f FROM Foodproduct f WHERE f.datelastsampled = :datelastsampled"),
    @NamedQuery(name = "Foodproduct.findByCode", query = "SELECT f FROM Foodproduct f WHERE f.code = :code"),
    @NamedQuery(name = "Foodproduct.findByType", query = "SELECT f FROM Foodproduct f WHERE f.type = :type")})
public class Foodproduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATELASTTESTED")
    @Temporal(TemporalType.DATE)
    private Date datelasttested;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "DATELASTMANUFACTURED")
    @Temporal(TemporalType.DATE)
    private Date datelastmanufactured;
    @Size(max = 255)
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "DATELASTSAMPLED")
    @Temporal(TemporalType.DATE)
    private Date datelastsampled;
    @Size(max = 255)
    @Column(name = "CODE")
    private String code;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @JoinTable(name = "foodproduct_category", joinColumns = {
        @JoinColumn(name = "FoodProduct_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "categories_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Category> categoryList;
    @ManyToMany(mappedBy = "foodproductList")
    private List<Foodfactory> foodfactoryList;
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Manufacturer manufacturerId;

    public Foodproduct() {
    }

    public Foodproduct(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatelasttested() {
        return datelasttested;
    }

    public void setDatelasttested(Date datelasttested) {
        this.datelasttested = datelasttested;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatelastmanufactured() {
        return datelastmanufactured;
    }

    public void setDatelastmanufactured(Date datelastmanufactured) {
        this.datelastmanufactured = datelastmanufactured;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDatelastsampled() {
        return datelastsampled;
    }

    public void setDatelastsampled(Date datelastsampled) {
        this.datelastsampled = datelastsampled;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
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
        if (!(object instanceof Foodproduct)) {
            return false;
        }
        Foodproduct other = (Foodproduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Foodproduct[ id=" + id + " ]";
    }
    
}
