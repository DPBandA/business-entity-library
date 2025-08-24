/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.fm;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "costcomponent")
@NamedQueries({
    @NamedQuery(name = "findByOwnerId", query = "SELECT c FROM CostComponent c WHERE c.ownerId = :ownerId")
})
public class CostComponent implements BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private String name;
    private String code;
    private String type;
    private String category;
    private Double hours;
    private Double hoursOrQuantity;
    private Double rate;
    private Double convertedRate;
    private Double cost;
    private Double convertedCost;
    private String comments;
    private Boolean isHeading;
    private Boolean isFixedCost;
    private Boolean isEditable;
    @Column(length = 1024)
    private String description;
    private String unit;
    @Transient
    private Boolean isDirty;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Currency currency;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date costDate;
    private Double currencyExchangeRate;

    public CostComponent() {
        name = "";
        code = "";
        type = "Fixed";
        category = "";
        rate = 1.0;
        convertedRate = 1.0;
        cost = 0.0;
        convertedCost = 0.0;
        comments = "";
        isHeading = false;
        isFixedCost = true;
        isEditable = true;
        description = "";
        unit = "";
    }

    public CostComponent(CostComponent src) {
        doCopy(src);
    }

    public CostComponent(String name) {
        this.name = name;
        code = "";
        type = "Fixed";
        category = "";
        rate = 1.0;
        convertedRate = 1.0;
        cost = 0.0;
        convertedCost = 0.0;
        comments = "";
        isHeading = false;
        isFixedCost = true;
        isEditable = true;
        description = "";
        unit = "";
    }

    public CostComponent(Long id, String name) {
        this.id = id;
        this.name = name;
        code = "";
        type = "Fixed";
        category = "";
        rate = 1.0;
        convertedRate = 1.0;
        cost = 0.0;
        convertedCost = 0.0;
        comments = "";
        isHeading = false;
        isFixedCost = true;
        isEditable = true;
        description = "";
        unit = "";
    }

    public CostComponent(String name, Boolean isHeading) {
        this.isHeading = isHeading;
        this.name = name;
        rate = 1.0;
        convertedRate = 1.0;
        cost = 0.0;
        convertedCost = 0.0;
        type = "Fixed";
        isFixedCost = false;
        isEditable = true;
        description = "";
        unit = "";
    }

    public CostComponent(String name, Double cost, Boolean isFixedCost, Boolean isEditable) {
        this.name = name;
        this.cost = cost;
        this.isFixedCost = isFixedCost;
        this.isEditable = isEditable;
        type = "Fixed";
        hours = 0.0;
        hoursOrQuantity = 0.0;
        rate = 1.0;
        convertedRate = 0.0;
        convertedCost = cost;
        description = "";
        unit = "";
    }

    public Double getConvertedRate() {

        convertedRate = getRate() * getCurrencyExchangeRate();

        return convertedRate;
    }

    public Double getConvertedCost() {

        convertedCost = getCost() * getCurrencyExchangeRate();

        return convertedCost;
    }

    public Double getCurrencyExchangeRate() {

        if (currencyExchangeRate == null) {
            currencyExchangeRate = 1.0;
        }

        return currencyExchangeRate;
    }

    public void setCurrencyExchangeRate(Double currencyExchangeRate) {
        this.currencyExchangeRate = currencyExchangeRate;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCostDate() {
        return costDate;
    }

    public void setCostDate(Date costDate) {
        this.costDate = costDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void update() {

        switch (getType()) {
            case "Fixed":
                setIsFixedCost(true);
                setIsHeading(false);
                setHours(0.0);
                setHoursOrQuantity(0.0);
                setRate(0.0);
                break;
            case "Heading":
                setIsFixedCost(false);
                setIsHeading(true);
                setHours(0.0);
                setHoursOrQuantity(0.0);
                setRate(0.0);
                setCost(0.0);
                setUnit("");
                break;
            case "Variable":
                setIsFixedCost(false);
                setIsHeading(false);
                break;
            default:
                setIsFixedCost(false);
                setIsHeading(false);
                break;
        }

        // Recalculate cost
        getCost();
    }

    @Override
    public String getType() {
        if (type == null) {
            type = "--";
        }
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Boolean getRenderFixedCostingComponentFormFields() {
        return !(!getIsHeading() && !getIsFixedCost());
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
        this.type = src.type;
        this.category = src.category;
        this.hours = src.hours;
        this.hoursOrQuantity = src.hoursOrQuantity;
        this.rate = src.rate;
        this.convertedRate = src.convertedRate;
        this.cost = src.cost;
        this.convertedCost = src.convertedCost;
        this.comments = src.comments;
        this.isHeading = src.isHeading;
        this.isFixedCost = src.isFixedCost;
        this.isEditable = src.isEditable;
        this.description = src.description;
        this.unit = src.unit;
    }

    public Boolean getIsSubcontract() {
        return getType().toUpperCase().equals("SUBCONTRACT");
    }

    public Boolean getIsVariable() {
        return getType().toUpperCase().equals("VARIABLE");
    }

    public Boolean getIsFixedCost() {
        if (isFixedCost == null) {
            isFixedCost = false;
        }
        return isFixedCost;
    }

    public Boolean getIsCostReadOnly() {
        return getIsSubcontract() || getIsVariable();
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
            hoursOrQuantity = 1.0;
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
        if (code == null) {
            code = "--";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getCost() {
        if (cost == null) {
            cost = 0.0;
        }

        if (!getIsFixedCost()) {
            cost = getRate() * getHoursOrQuantity();
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

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "[id=" + id + "]";
    }

    @Override
    public int compareTo(Object o) {
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

    public static CostComponent findByOwnerId(EntityManager em, Long ownerId) {
        try {
            Query q = em.createNamedQuery("findByJobId");
            q.setParameter("jobId", ownerId);

            return (CostComponent) q.getSingleResult();

        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            
            isDirty = false;
            
            em.getTransaction().begin();            
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
            
        } catch (Exception e) {
            
            System.out.println("Cost Component save exception: " + e);
            
            return new ReturnMessage(false,
                    "Cost component not saved",
                    "An error occurred while saving a cost component: " + e,
                    Message.SEVERITY_ERROR_NAME);
        }

    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEntered(Date dateEntered) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDateEdited(Date dateEdited) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
