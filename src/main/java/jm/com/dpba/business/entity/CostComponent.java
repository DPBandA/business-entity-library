/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Desmond
 */
@Entity
@Table(name = "costcomponent")
public class CostComponent implements BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String category;
    private Double hours;
    private Double hoursOrQuantity;
    private Double rate;
    private Double cost;
    private String comments;
    private Boolean isHeading;
    private Boolean isFixedCost;
    private Boolean isEditable;

    public CostComponent() {
        name = "";
        code = "";
        category = "";
        comments = "";
        isHeading = false;
        isFixedCost = false;
        isEditable = true;
    }
 
    public CostComponent(CostComponent src) {
        doCopy(src);
    }

    public CostComponent(String name) {
        this.name = name;
        this.cost = 0.0;
        isFixedCost = false;
        isEditable = true;
    }

    public CostComponent(Long id, String name) {
        this.id = id;
        this.name = name;
        this.cost = 0.0;
        isFixedCost = false;
        isEditable = true;
    }

    public CostComponent(String name, Boolean isHeading) {
        this.isHeading = isHeading;
        this.name = name;
        this.cost = 0.0;
        isFixedCost = false;
        isEditable = true;
    }

    public CostComponent(String name, Double cost, Boolean isFixedCost, Boolean isEditable) {
        this.name = name;
        this.cost = cost;
        this.isFixedCost = isFixedCost;
        this.isEditable = isEditable;
        this.hours = 0.0;
        this.hoursOrQuantity = 0.0;
        this.rate = 0.0;        
    }
    
    public Boolean getRenderFixedCostingComponentFormFields() {
        if (!getIsHeading() && !getIsFixedCost()) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public final void doCopy(CostComponent src) {
        this.name = src.name;
        this.code = src.code;
        this.category = src.category;
        this.hours = src.hours;
        this.hoursOrQuantity = src.hoursOrQuantity;
        this.rate = src.rate;
        this.cost = src.cost;
        this.comments = src.comments;
        this.isHeading = src.isHeading;
        this.isFixedCost = src.isFixedCost;
        this.isEditable = src.isEditable;
    }

    public Boolean getIsFixedCost() {
        if (isFixedCost == null) {
            isFixedCost = false;
        }
        return isFixedCost;
    }

    public void setIsFixedCost(Boolean isFixedCost) {
        this.isFixedCost = isFixedCost;
    }

    public Boolean getIsEditable() {
        if (isEditable == null) {
            isEditable = true;
        }
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public Double getHoursOrQuantity() {
        if (hoursOrQuantity == null) {
            hoursOrQuantity = 0.0;
        }
        return hoursOrQuantity;
    }

    public void setHoursOrQuantity(Double hoursOrQuantity) {
        this.hoursOrQuantity = hoursOrQuantity;
    }

    public Boolean getIsHeading() {
        if (isHeading == null) {
            isHeading = false;
        }
        return isHeading;
    }

    public void setIsHeading(Boolean isHeading) {
        this.isHeading = isHeading;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getCost() {
        if (!getIsFixedCost()) {
            cost = getRate().doubleValue() * getHoursOrQuantity().doubleValue();
        }
        
        if (cost == null) {
            cost = 0.0;
        }

        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getHours() {
        if (hours == null) {
            hours = 0.0;
        }
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
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

    public Double getRate() {
        if (rate == null) {
            rate = 0.0;
        }
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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
        if (!(object instanceof CostComponent)) {
            return false;
        }
        CostComponent other = (CostComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.CostComponent[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
        //return Collator.getInstance().compare(this.name, ((CostComponent) o).name);
        return Collator.getInstance().compare(this.toString(), ((CostComponent) o).toString());
    }
    
    public static List<CostComponent> findCostComponentsByName(String name, List<CostComponent> list) {
        ArrayList<CostComponent> foundComponents = new ArrayList<>();
        
        for (CostComponent costComponent : list) {
            if (costComponent.getName().equals(name)) {
                foundComponents.add(costComponent);
            }
        }
        
        return foundComponents;
    }
}
