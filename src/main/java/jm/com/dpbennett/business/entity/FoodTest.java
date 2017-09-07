/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "foodtest")
public class FoodTest implements Test, Serializable, Comparable, BusinessEntity  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double hourlyRate;
    private String type;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date testDate;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee testDoneBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reTestDate;
    private String category;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof FoodTest)) {
            return false;
        }
        FoodTest other = (FoodTest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.FoodTest[id=" + id + "]";
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
    public Double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public Date getTestDate() {
        return testDate;
    }

    @Override
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    @Override
    public Employee getTestDoneBy() {
        return testDoneBy;
    }

    @Override
    public void setTestDoneBy(Employee testDoneBy) {
        this.testDoneBy = testDoneBy;
    }

    @Override
    public Date getReTestDate() {
        return reTestDate;
    }

    @Override
    public void setReCalibrationDate(Date reTestDate) {
        this.reTestDate = reTestDate;
    }

    @Override
    public int compareTo(Object o) {
       return Collator.getInstance().compare(this.toString(), o.toString());
    }

    @Override
    public String getCateogy() {
       return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

}
