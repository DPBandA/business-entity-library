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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
    private String ratedVoltage;
    private String ratedFrequency;
    private String annualConsumption;
    private String brand;
    private String capacity;
    private String heatingCapacity;
    private String coolingCapacity;
    private String costPerKwh;
    private String AEER;
    private String ACOP;
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
    @OneToOne(cascade = CascadeType.REFRESH)
    private EnergyConsumptionAndEfficiency energyConsumptionAndEfficiency;
    @Transient
    private Boolean isDirty;

    public EnergyLabel() {
        ratedVoltage = "";
        ratedFrequency = "";
        annualConsumption = "";
        brand = "";
        capacity = "";
        heatingCapacity = "";
        coolingCapacity = "";
        costPerKwh = "";
        AEER = "";
        ACOP = "";
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

    public EnergyLabel(Long id, String labelName) {
        this.id = id;
        this.labelName = labelName;
        ratedVoltage = "";
        ratedFrequency = "";
        annualConsumption = "";
        brand = "";
        capacity = "";
        heatingCapacity = "";
        coolingCapacity = "";
        costPerKwh = "";
        AEER = "";
        ACOP = "";
        country = "";
        defrost = "";
        distributor = "";
        jobNumber = "";
        manufacturer = "";
        model = "";
        operatingCost = "";
        standard = "";
        type = "";
        validity = "";
        isDirty = false;
    }

    public EnergyConsumptionAndEfficiency getEnergyConsumptionAndEfficiency() {
        return energyConsumptionAndEfficiency;
    }

    public void setEnergyConsumptionAndEfficiency(
            EnergyConsumptionAndEfficiency energyConsumptionAndEfficiency) {
        this.energyConsumptionAndEfficiency = energyConsumptionAndEfficiency;
    }

    public String getAEER() {
        if (AEER == null || AEER.isEmpty()) {
            AEER = "0.0";
        }
        return AEER;
    }

    public void setAEER(String AEER) {
        this.AEER = AEER;
    }

    public String getACOP() {
        if (ACOP == null || ACOP.isEmpty()) {
            ACOP = "0.0";
        }
        return ACOP;
    }

    public void setACOP(String ACOP) {
        this.ACOP = ACOP;
    }

    public String getRatedVoltage() {
        return ratedVoltage;
    }

    public void setRatedVoltage(String ratedVoltage) {
        this.ratedVoltage = ratedVoltage;
    }

    public String getRatedFrequency() {
        return ratedFrequency;
    }

    public void setRatedFrequency(String ratedFrequency) {
        this.ratedFrequency = ratedFrequency;
    }

    /**
     * Gets the heating capacity of an AC unit.
     *
     * @return
     */
    public String getHeatingCapacity() {
        if (heatingCapacity == null || heatingCapacity.isEmpty()) {
            heatingCapacity = "0.0";
        }
        return heatingCapacity;
    }

    /**
     * Sets the heating capacity of an AC unit.
     *
     * @param heatingCapacity
     */
    public void setHeatingCapacity(String heatingCapacity) {
        this.heatingCapacity = heatingCapacity;
    }

    /**
     * Gets the cooling capacity of an AC unit.
     *
     * @return
     */
    public String getCoolingCapacity() {
        if (coolingCapacity == null || coolingCapacity.isEmpty()) {
            coolingCapacity = "0.0";
        }
        return coolingCapacity;
    }

    /**
     * Sets the cooling capacity of an AC unit.
     *
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
        if (annualConsumption == null || annualConsumption.isEmpty()) {
            annualConsumption = "0.0";
        }
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
     * Gets the volumetric capacity.
     *
     * @return the capacity
     */
    public String getCapacity() {
        if (capacity == null || capacity.isEmpty()) {
            capacity = "0.0";
        }
        return this.capacity;
    }

    /**
     * Sets the volumetric capacity to the specified value.
     *
     * @param capacity the new capacity
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the costPerKwh.
     *
     * @return the costPerKwh
     */
    public String getCostPerKwh() {
        if (costPerKwh == null || costPerKwh.isEmpty()) {
            costPerKwh = "0.0";
        }
        return this.costPerKwh;
    }

    /**
     * Sets the costPerKwh to the specified value.
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
        if (operatingCost == null || operatingCost.isEmpty()) {
            operatingCost = "0.0";
        }
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
            if (energyConsumptionAndEfficiency != null) {
                if (energyConsumptionAndEfficiency.getId() == null) {
                    energyConsumptionAndEfficiency.save(em);
                }
            }

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
            // Validate double values
            // Validate capacity
            if (!validateDoubleValue(getCapacity()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Capacity",
                        "The volumetric capacity is invalid", null);
            }
            // Validate cooling capacity
            if (!validateDoubleValue(getCoolingCapacity()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Cooling Capacity",
                        "The cooling capacity is invalid", null);
            }
            // Validate heating capacity
            if (!validateDoubleValue(getHeatingCapacity()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Heating Capacity",
                        "The heating capacity is invalid", null);
            }
            // Validate operating cost
            if (!validateDoubleValue(getOperatingCost()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Operating Cost",
                        "The operating cost is invalid", null);
            }
            // Validate annual consumption
            if (!validateDoubleValue(getAnnualConsumption()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Annual Consumption",
                        "The annual consumption is invalid", null);
            }
            // Validate cost per kwh
            if (!validateDoubleValue(getCostPerKwh()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Electricity Rate",
                        "The electricity rate is invalid", null);
            }
            // Validate AEER
            if (!validateDoubleValue(getAEER()).isSuccess()) {
                return new ReturnMessage(false, "Invalid AEER",
                        "The AEER is invalid", null);
            }
            // Validate ACOP
            if (!validateDoubleValue(getACOP()).isSuccess()) {
                return new ReturnMessage(false, "Invalid ACOP",
                        "The ACOP is invalid", null);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage();
    }

    /**
     * This is a utility method used to validate all double values used in
     * calculations.
     *
     * @param value
     * @return
     */
    public static ReturnMessage validateDoubleValue(String value) {
        try {
            Double.parseDouble(value);

            return new ReturnMessage();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Double value is invalid");
    }

    /**
     * Returns the double value of a string if the string represents a valid
     * double value. If the string is invalid 0.0 is returned.
     *
     * @param value
     * @return
     */
    public static double getDoubleValue(String value) {
        try {
            return Double.parseDouble(value);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        return 0.0;
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
