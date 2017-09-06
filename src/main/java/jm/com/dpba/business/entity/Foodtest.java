/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name = "foodtest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodtest.findAll", query = "SELECT f FROM Foodtest f")
    , @NamedQuery(name = "Foodtest.findById", query = "SELECT f FROM Foodtest f WHERE f.id = :id")
    , @NamedQuery(name = "Foodtest.findByTestdate", query = "SELECT f FROM Foodtest f WHERE f.testdate = :testdate")
    , @NamedQuery(name = "Foodtest.findByRetestdate", query = "SELECT f FROM Foodtest f WHERE f.retestdate = :retestdate")
    , @NamedQuery(name = "Foodtest.findByHourlyrate", query = "SELECT f FROM Foodtest f WHERE f.hourlyrate = :hourlyrate")
    , @NamedQuery(name = "Foodtest.findByCategory", query = "SELECT f FROM Foodtest f WHERE f.category = :category")
    , @NamedQuery(name = "Foodtest.findByName", query = "SELECT f FROM Foodtest f WHERE f.name = :name")
    , @NamedQuery(name = "Foodtest.findByType", query = "SELECT f FROM Foodtest f WHERE f.type = :type")})
public class Foodtest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TESTDATE")
    @Temporal(TemporalType.DATE)
    private Date testdate;
    @Column(name = "RETESTDATE")
    @Temporal(TemporalType.DATE)
    private Date retestdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HOURLYRATE")
    private Double hourlyrate;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;
    @ManyToMany(mappedBy = "foodtestList")
    private List<Foodsample> foodsampleList;
    @JoinColumn(name = "TESTDONEBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee testdonebyId;

    public Foodtest() {
    }

    public Foodtest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTestdate() {
        return testdate;
    }

    public void setTestdate(Date testdate) {
        this.testdate = testdate;
    }

    public Date getRetestdate() {
        return retestdate;
    }

    public void setRetestdate(Date retestdate) {
        this.retestdate = retestdate;
    }

    public Double getHourlyrate() {
        return hourlyrate;
    }

    public void setHourlyrate(Double hourlyrate) {
        this.hourlyrate = hourlyrate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<Foodsample> getFoodsampleList() {
        return foodsampleList;
    }

    public void setFoodsampleList(List<Foodsample> foodsampleList) {
        this.foodsampleList = foodsampleList;
    }

    public Employee getTestdonebyId() {
        return testdonebyId;
    }

    public void setTestdonebyId(Employee testdonebyId) {
        this.testdonebyId = testdonebyId;
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
        if (!(object instanceof Foodtest)) {
            return false;
        }
        Foodtest other = (Foodtest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Foodtest[ id=" + id + " ]";
    }
    
}
