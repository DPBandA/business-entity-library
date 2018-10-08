/*
LabelPrint - A general purpose energy label printing application 
Copyright (C) 2018  D P Bennett & Associates Limited

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

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 * Entity class EnergyLabel
 *
 * @author dbennett
 */
@Entity
@Table(name = "energylabel")
@NamedQueries({
    @NamedQuery(name = "EnergyLabel.findByType", query = "SELECT e FROM EnergyLabel e WHERE e.type = :type")
})
public class EnergyLabel implements Serializable, BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String annualConsumption;
    private String brand;
    private String capacity;    
    private String heatingCapacity;
    private String coolingCapacity;
    private String costPerKwh;
    private String country;
    private String defrost;
    private String distributor;
    private String jobNumber;
    private String labelName;
    private String manufacturer;
    private String model;
    private String operatingCost;
    private String standard;
    private String type;
    private String validity;
    @Transient
    private Boolean isDirty;

    public EnergyLabel() {
        annualConsumption = "";
        brand = "";
        capacity = "";
        heatingCapacity = "";
        coolingCapacity = "";
        costPerKwh = "";
        country = "";
        defrost = "";
        distributor = "";
        jobNumber = "";
        labelName = "";
        manufacturer = "";
        model = "";
        operatingCost = "";
        standard = "";
        type = "";
        validity = "";
        isDirty = false;
    }

    public EnergyLabel(Long id) {
        this.id = id;
        annualConsumption = "";
        brand = "";
        capacity = "";
        heatingCapacity = "";
        coolingCapacity = "";
        costPerKwh = "";
        country = "";
        defrost = "";
        distributor = "";
        jobNumber = "";
        labelName = "";
        manufacturer = "";
        model = "";
        operatingCost = "";
        standard = "";
        type = "";
        validity = "";
        isDirty = false;
    }

    /**
     * Gets the heating capacity of an AC unit.
     * @return 
     */
    public String getHeatingCapacity() {
        return heatingCapacity;
    }

    /**
     * Sets the heating capacity of an AC unit.
     * @param heatingCapacity 
     */
    public void setHeatingCapacity(String heatingCapacity) {
        this.heatingCapacity = heatingCapacity;
    }

    /**
     * Gets the cooling capacity of an AC unit.
     * @return 
     */
    public String getCoolingCapacity() {
        return coolingCapacity;
    }

    /**
     * Sets the cooling capacity of an AC unit.
     * @param coolingCapacity 
     */
    public void setCoolingCapacity(String coolingCapacity) {
        this.coolingCapacity = coolingCapacity;
    }
  
    /**
     * Gets the annualConsumption of this EnergyLabel.
     *
     * @return the annualConsumption
     */
    public String getAnnualConsumption() {
        return this.annualConsumption;
    }

    /**
     * Sets the annualConsumption of this EnergyLabel to the specified value.
     *
     * @param annualConsumption the new annualConsumption
     */
    public void setAnnualConsumption(String annualConsumption) {
        this.annualConsumption = annualConsumption;
    }

    /**
     * Gets the brand of this EnergyLabel.
     *
     * @return the brand
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Sets the brand of this EnergyLabel to the specified value.
     *
     * @param brand the new brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the capacity of this EnergyLabel.
     *
     * @return the capacity
     */
    public String getCapacity() {
        return this.capacity;
    }

    /**
     * Sets the capacity of this EnergyLabel to the specified value.
     *
     * @param capacity the new capacity
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the costPerKwh of this EnergyLabel.
     *
     * @return the costPerKwh
     */
    public String getCostPerKwh() {
        return this.costPerKwh;
    }

    /**
     * Sets the costPerKwh of this EnergyLabel to the specified value.
     *
     * @param costPerKwh the new costPerKwh
     */
    public void setCostPerKwh(String costPerKwh) {
        this.costPerKwh = costPerKwh;
    }

    /**
     * Gets the country of this EnergyLabel.
     *
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the country of this EnergyLabel to the specified value.
     *
     * @param country the new country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the defrost of this EnergyLabel.
     *
     * @return the defrost
     */
    public String getDefrost() {
        return this.defrost;
    }

    /**
     * Sets the defrost of this EnergyLabel to the specified value.
     *
     * @param defrost the new defrost
     */
    public void setDefrost(String defrost) {
        this.defrost = defrost;
    }

    /**
     * Gets the distributor of this EnergyLabel.
     *
     * @return the distributor
     */
    public String getDistributor() {
        return this.distributor;
    }

    /**
     * Sets the distributor of this EnergyLabel to the specified value.
     *
     * @param distributor the new distributor
     */
    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    /**
     * Gets the jobNumber of this EnergyLabel.
     *
     * @return the jobNumber
     */
    public String getJobNumber() {
        return this.jobNumber;
    }

    /**
     * Sets the jobNumber of this EnergyLabel to the specified value.
     *
     * @param jobNumber the new jobNumber
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * Gets the labelName of this EnergyLabel.
     *
     * @return the labelName
     */
    public String getLabelName() {
        return this.labelName;
    }

    /**
     * Sets the labelName of this EnergyLabel to the specified value.
     *
     * @param labelName the new labelName
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * Gets the manufacturer of this EnergyLabel.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**
     * Sets the manufacturer of this EnergyLabel to the specified value.
     *
     * @param manufacturer the new manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the model of this EnergyLabel.
     *
     * @return the model
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Sets the model of this EnergyLabel to the specified value.
     *
     * @param model the new model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the operatingCost of this EnergyLabel.
     *
     * @return the operatingCost
     */
    public String getOperatingCost() {
        return this.operatingCost;
    }

    /**
     * Sets the operatingCost of this EnergyLabel to the specified value.
     *
     * @param operatingCost the new operatingCost
     */
    public void setOperatingCost(String operatingCost) {
        this.operatingCost = operatingCost;
    }

    /**
     * Gets the standard of this EnergyLabel.
     *
     * @return the standard
     */
    public String getStandard() {
        return this.standard;
    }

    /**
     * Sets the standard of this EnergyLabel to the specified value.
     *
     * @param standard the new standard
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * Gets the type of this EnergyLabel.
     *
     * @return the type
     */
    public String getType() {
        if (type == null) {
            type = "";
        }
        return this.type;
    }

    /**
     * Sets the type of this EnergyLabel to the specified value.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the validity of this EnergyLabel.
     *
     * @return the validity
     */
    public String getValidity() {
        return this.validity;
    }

    /**
     * Sets the validity of this EnergyLabel to the specified value.
     *
     * @param validity the new validity
     */
    public void setValidity(String validity) {
        this.validity = validity;
    }

    /**
     * Returns a hash code value for the object. This implementation computes a
     * hash code value based on the id fields in this object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this EnergyLabel. The
     * result is <code>true</code> if and only if the argument is not null and
     * is a EnergyLabel object that has the same id field values as this object.
     *
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnergyLabel)) {
            return false;
        }
        EnergyLabel other = (EnergyLabel) object;

        return !(this.id
                != other.id
                && (this.id == null
                || !this.id.equals(other.id)));
    }

    /**
     * Returns a string representation of the object. This implementation
     * constructs that representation based on the id fields.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return labelName;
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Energy Label not saved");
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
    public String getName() {
        return labelName;
    }

    @Override
    public void setName(String name) {
        this.labelName = name;
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        try {
            if (labelName.isEmpty()) {
                return new ReturnMessage(false, "Invalid Energy Label",
                        "This label is invalid", null);
            } else {
                return new ReturnMessage();
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ReturnMessage(false, "Invalid Energy Label",
                    "This label is invalid", null);
        }
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

}
