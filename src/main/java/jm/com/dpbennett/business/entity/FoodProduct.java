/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import jm.com.dpbennett.business.utils.MethodResult;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "foodproduct")
public class FoodProduct implements Product, BusinessEntity, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String brand;
    private String type;
    private String code;
    private Boolean active;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastSampled;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastManufactured;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastTested;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> categories;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;

    public FoodProduct() {
        categories = new ArrayList<>();
    }

    public FoodProduct(String name) {
        this.name = name;
        categories = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateLastManufactured() {
        return dateLastManufactured;
    }

    public void setDateLastManufactured(Date dateLastManufactured) {
        this.dateLastManufactured = dateLastManufactured;
    }

    public Date getDateLastSampled() {
        return dateLastSampled;
    }

    public void setDateLastSampled(Date dateLastSampled) {
        this.dateLastSampled = dateLastSampled;
    }

    public Date getDateLastTested() {
        return dateLastTested;
    }

    public void setDateLastTested(Date dateLastTested) {
        this.dateLastTested = dateLastTested;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
        if (!(object instanceof FoodProduct)) {
            return false;
        }
        FoodProduct other = (FoodProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.FoodProduct[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
        if ((((FoodProduct) o).id != null) && (this.id != null)) {
            return Collator.getInstance().compare(
                    new Long(((FoodProduct) o).id).toString(),
                    new Long(this.id).toString());
        } else {
            return 0;
        }
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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Manufacturer getManufacturer() {
        if (manufacturer == null) {
            return new Manufacturer("");
        }
        return manufacturer;
    }

    @Override
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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
