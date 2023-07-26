/*
LabelPrint (LP)
Copyright (C) 2023  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.mt;

import java.util.Date;
import java.util.Objects;
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
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.NumberUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 * This class encapsulates the properties and methods of the energy label of an
 * energy product.
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "energylabel")
@NamedQueries({
    @NamedQuery(name = "EnergyLabel.findByType", query = "SELECT e FROM EnergyLabel e WHERE e.type = :type")
})
public class EnergyLabel implements BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String starRating;
    private Boolean calcStarRating;
    private String ratedVoltage;
    private String ratedCurrent;
    private String ratedFrequency;
    private String annualConsumption;
    private String brand;
    private String capacity;
    private String freshFoodCompartmentVol;
    private String freezerCompartmentVol;
    private String coolingCapacity;
    private Boolean showCoolingCapacity;
    private String heatingCapacity;
    private Boolean showHeatingCapacity;
    private String costPerKwh;
    private String costPerKwh2;
    private String CEC;
    private String BEC;
    private Boolean calcBEC;
    private String ERF;
    private String totalAdjustedVol;
    private Boolean calcTotalAdjustedVol;
    private String Cf;
    private String Cv;
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
    @Transient
    private Boolean showSampleWatermark;
    private String yearOfEvaluation;
    private String feature1;
    private String feature2;
    private String letterRating;
    private String batchCode;
    private String efficiencyRatio;
    @Transient
    private String editStatus;
    private String serialNumber;

    /**
     * The default constructor of an EnergyLabel.
     */
    public EnergyLabel() {
        starRating = "";
        calcStarRating = false;
        ratedVoltage = "";
        ratedFrequency = "";
        annualConsumption = "";
        brand = "";
        capacity = "";
        freshFoodCompartmentVol = "";
        freezerCompartmentVol = "";
        coolingCapacity = "";
        showCoolingCapacity = true;
        heatingCapacity = "";
        showHeatingCapacity = false;
        costPerKwh = "";
        costPerKwh2 = "";
        BEC = "";
        calcBEC = true;
        CEC = "";
        ERF = "";
        totalAdjustedVol = "";
        calcTotalAdjustedVol = false;
        Cf = "";
        Cv = "";
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
        showSampleWatermark = false;
        yearOfEvaluation = "";
        ratedCurrent = "";
        feature1 = "";
        feature2 = "";
        letterRating = "A";
        batchCode = "";
        efficiencyRatio = "0.0";
        serialNumber = "";
    }

    public String getSerialNumber() {

        if (serialNumber == null) {
            serialNumber = "";
        }

        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public String getEfficiencyRatio() {
        if (efficiencyRatio == null) {
            efficiencyRatio = "0.0";
        }
        return efficiencyRatio;
    }

    public void setEfficiencyRatio(String efficiencyRatio) {
        this.efficiencyRatio = efficiencyRatio;
    }

    public String getBatchCode() {
        if (batchCode == null) {
            batchCode = "";
        }
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getLetterRating() {
        if (letterRating == null) {
            letterRating = "A";
        }
        return letterRating;
    }

    public void setLetterRating(String letterRating) {
        this.letterRating = letterRating;
    }

    public String getFeature1() {
        if (feature1 == null) {
            feature1 = "";
        }
        return feature1;
    }

    public void setFeature1(String feature1) {
        this.feature1 = feature1;
    }

    public String getFeature2() {
        if (feature2 == null) {
            feature2 = "";
        }

        return feature2;
    }

    public void setFeature2(String feature2) {
        this.feature2 = feature2;
    }

    public String getRatedCurrent() {
        if (ratedCurrent == null) {
            ratedCurrent = "";
        }

        return ratedCurrent;
    }

    public void setRatedCurrent(String ratedCurrent) {
        this.ratedCurrent = ratedCurrent;
    }

    public String getYearOfEvaluation() {
        if (yearOfEvaluation == null) {
            yearOfEvaluation = "";
        }
        return yearOfEvaluation;
    }

    public void setYearOfEvaluation(String yearOfEvaluation) {
        this.yearOfEvaluation = yearOfEvaluation;
    }

    /**
     * Indicates if the cooling capacity is to be shown on the label.
     *
     * @return
     */
    public Boolean getShowCoolingCapacity() {
        if (showCoolingCapacity == null) {
            showCoolingCapacity = true;
        }
        return showCoolingCapacity;
    }

    /**
     * Indicates if the cooling capacity is to be shown on the label.
     *
     * @param showCoolingCapacity
     */
    public void setShowCoolingCapacity(Boolean showCoolingCapacity) {
        this.showCoolingCapacity = showCoolingCapacity;
    }

    /**
     * Indicates if the heating capacity is to be shown on the label.
     *
     * @return
     */
    public Boolean getShowHeatingCapacity() {
        if (showHeatingCapacity == null) {
            showHeatingCapacity = false;
        }
        return showHeatingCapacity;
    }

    /**
     * Indicates if the heating capacity is to be shown on the label.
     *
     * @param showHeatingCapacity
     */
    public void setShowHeatingCapacity(Boolean showHeatingCapacity) {
        this.showHeatingCapacity = showHeatingCapacity;
    }

    /**
     * Gets the inputted or calculated star rating of the product.
     *
     * @return
     */
    public String getStarRating() {
        if (isCalcStarRating()) {
            starRating = getCalcStarRating().toString();

            return starRating;
        }

        return (starRating == null || starRating.isEmpty() ? "0.0" : starRating);
    }

    /**
     * Sets the inputted or calculated star rating of the product.
     *
     * @param starRating
     */
    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    /**
     * Gets if star rating is to be calculated.
     *
     * @return
     */
    public Boolean isCalcStarRating() {
        if (calcStarRating == null) {
            calcStarRating = false;
        }
        return calcStarRating;
    }

    /**
     * Sets if star rating is to be calculated.
     *
     * @param calcStarRating
     */
    public void setCalcStarRating(Boolean calcStarRating) {
        this.calcStarRating = calcStarRating;
    }

    /**
     * Gets if BEC is to be calculated.
     *
     * @return
     */
    public Boolean isCalcBEC() {
        if (calcBEC == null) {
            calcBEC = false;
        }
        return calcBEC;
    }

    /**
     * Gets if BEC is to be calculated.
     *
     * @param calcBEC
     */
    public void setCalcBEC(Boolean calcBEC) {
        this.calcBEC = calcBEC;
    }

    /**
     * Gets if Total Adjusted Volume is to be calculated.
     *
     * @return
     */
    public Boolean isCalcTotalAdjustedVol() {
        if (calcTotalAdjustedVol == null) {
            calcTotalAdjustedVol = false;
        }
        return calcTotalAdjustedVol;
    }

    /**
     * Sets if Total Adjusted Volume is to be calculated.
     *
     * @param calcTotalAdjustedVol
     */
    public void setCalcTotalAdjustedVol(Boolean calcTotalAdjustedVol) {
        this.calcTotalAdjustedVol = calcTotalAdjustedVol;
    }

    /**
     * Gets the Energy Consumption Reduction Factor (ERF) for a product.
     *
     * @return
     */
    public String getERF() {
        return (ERF == null || ERF.isEmpty() ? "0.0" : ERF);
    }

    /**
     * Sets the Energy Consumption Reduction Factor (ERF) for a product.
     *
     * @param ERF
     */
    public void setERF(String ERF) {
        this.ERF = ERF;
    }

    /**
     * Gets the Base Energy Consumption (BEC) for the product.
     *
     * @return
     */
    public String getBEC() {
        if (isCalcBEC()) {
            BEC = getCalcBEC().toString();

            return BEC;
        } else {

            return (BEC == null || BEC.isEmpty() ? "0.0" : BEC);
        }
    }

    /**
     * Sets the Base Energy Consumption (BEC) for the product.
     *
     * @param BEC
     */
    public void setBEC(String BEC) {
        this.BEC = BEC;
    }

    /**
     * Gets the energyConsumptionAndEfficiency property of this EnergyLabel.
     *
     * @see EnergyConsumptionAndEfficiency
     * @return
     */
    public EnergyConsumptionAndEfficiency getEnergyConsumptionAndEfficiency() {
        return energyConsumptionAndEfficiency;
    }

    /**
     * Sets the energyConsumptionAndEfficiency property of this EnergyLabel.
     *
     * @see EnergyConsumptionAndEfficiency
     * @param energyConsumptionAndEfficiency
     */
    public void setEnergyConsumptionAndEfficiency(
            EnergyConsumptionAndEfficiency energyConsumptionAndEfficiency) {
        this.energyConsumptionAndEfficiency = energyConsumptionAndEfficiency;
    }

    /**
     * Gets the fresh food compartment volume of a refrigerator.
     *
     * @return
     */
    public String getFreshFoodCompartmentVol() {

        return (freshFoodCompartmentVol == null
                || freshFoodCompartmentVol.isEmpty() ? "0.0" : freshFoodCompartmentVol);
    }

    /**
     * Sets the fresh food compartment volume of a refrigerator.
     *
     * @param freshFoodCompartmentVol
     */
    public void setFreshFoodCompartmentVol(String freshFoodCompartmentVol) {
        this.freshFoodCompartmentVol = freshFoodCompartmentVol;
    }

    /**
     * Gets the freezer compartment volume of a refrigerator or freezer.
     *
     * @return
     */
    public String getFreezerCompartmentVol() {

        return (freezerCompartmentVol == null
                || freezerCompartmentVol.isEmpty() ? "0.0" : freezerCompartmentVol);
    }

    /**
     * Sets the freezer compartment volume of a refrigerator or freezer.
     *
     * @param freezerCompartmentVol
     */
    public void setFreezerCompartmentVol(String freezerCompartmentVol) {
        this.freezerCompartmentVol = freezerCompartmentVol;
    }

    /**
     * Gets the Comparative Energy Consumption (CEC) for the product.
     *
     * @return
     */
    public String getCEC() {
        return (CEC == null || CEC.isEmpty() ? "0.0" : CEC);
    }

    /**
     * Sets the Comparative Energy Consumption (CEC) for the product.
     *
     * @param CEC
     */
    public void setCEC(String CEC) {
        this.CEC = CEC;
    }

    /**
     * Gets the Total Adjusted Volume of the product.
     *
     * @return
     */
    public String getTotalAdjustedVol() {
        if (isCalcTotalAdjustedVol()) {
            totalAdjustedVol = getCalcTotalAdjustedVol().toString();

            return totalAdjustedVol;
        } else {

            return (totalAdjustedVol == null || totalAdjustedVol.isEmpty() ? "0.0" : totalAdjustedVol);
        }
    }

    /**
     * Sets the Total Adjusted Volume of the product.
     *
     * @param totalAdjustedVol
     */
    public void setTotalAdjustedVol(String totalAdjustedVol) {
        this.totalAdjustedVol = totalAdjustedVol;
    }

    /**
     * Gets the Fixed Allowance Factor (Cf) for the product.
     *
     * @return
     */
    public String getCf() {
        return (Cf == null || Cf.isEmpty() ? "0.0" : Cf);
    }

    /**
     * Sets the Fixed Allowance Factor (Cf) for the product.
     *
     * @param Cf
     */
    public void setCf(String Cf) {
        this.Cf = Cf;
    }

    /**
     * Gets the Variable Allowance Factor (Cv) for the product.
     *
     * @return
     */
    public String getCv() {
        return (Cv == null || Cv.isEmpty() ? "0.0" : Cv);
    }

    /**
     * Sets the Variable Allowance Factor (Cv) for the product.
     *
     * @param Cv
     */
    public void setCv(String Cv) {
        this.Cv = Cv;
    }

    /**
     * Gets the Annual Energy Efficiency Ratio (AEER) property.
     *
     * @return
     */
    public String getAEER() {

        return (AEER == null || AEER.isEmpty() ? "0.0" : AEER);
    }

    /**
     * Sets the Annual Energy Efficiency Ratio (AEER) property.
     *
     * @param AEER
     */
    public void setAEER(String AEER) {
        this.AEER = AEER;
    }

    /**
     * Gets the Annual Coefficient of Performance (ACOP) property.
     *
     * @return
     */
    public String getACOP() {

        return (ACOP == null || ACOP.isEmpty() ? "0.0" : ACOP);
    }

    /**
     * Sets the Annual Coefficient of Performance (ACOP) property.
     *
     * @param ACOP
     */
    public void setACOP(String ACOP) {
        this.ACOP = ACOP;
    }

    /**
     * Gets the rated voltage or voltage range of the product.
     *
     * @return
     */
    public String getRatedVoltage() {
        return ratedVoltage;
    }

    /**
     * Sets the rated voltage or voltage range of the product.
     *
     * @param ratedVoltage
     */
    public void setRatedVoltage(String ratedVoltage) {
        this.ratedVoltage = ratedVoltage;
    }

    /**
     * Gets the rated frequency or frequency range of the product.
     *
     * @return
     */
    public String getRatedFrequency() {
        return ratedFrequency;
    }

    /**
     * Sets the rated frequency or frequency range of the product.
     *
     * @param ratedFrequency
     */
    public void setRatedFrequency(String ratedFrequency) {
        this.ratedFrequency = ratedFrequency;
    }

    /**
     * Gets the heating capacity of an air-conditioning unit.
     *
     * @return
     */
    public String getHeatingCapacity() {

        return (heatingCapacity == null || heatingCapacity.isEmpty() ? "0.0" : heatingCapacity);
    }

    /**
     * Sets the heating capacity of an air-conditioning unit.
     *
     * @param heatingCapacity
     */
    public void setHeatingCapacity(String heatingCapacity) {
        this.heatingCapacity = heatingCapacity;
    }

    /**
     * Gets the cooling capacity of an air-conditioning unit.
     *
     * @return
     */
    public String getCoolingCapacity() {

        return (coolingCapacity == null || coolingCapacity.isEmpty() ? "0.0" : coolingCapacity);
    }

    /**
     * Sets the cooling capacity of an air-conditioning unit.
     *
     * @param coolingCapacity
     */
    public void setCoolingCapacity(String coolingCapacity) {
        this.coolingCapacity = coolingCapacity;
    }

    /**
     * Gets the annual energy consumption of the product. If the string is null
     * or empty "0.0" is returned.
     *
     * @return annualConsumption
     */
    public String getAnnualConsumption() {

        return (annualConsumption == null || annualConsumption.isEmpty() ? "0.0" : annualConsumption);
    }

    /**
     * Sets the annual energy consumption of the product.
     *
     * @param annualConsumption
     */
    public void setAnnualConsumption(String annualConsumption) {
        this.annualConsumption = annualConsumption;
    }

    /**
     * Gets the brand of the product.
     *
     * @return
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Sets the brand of the product.
     *
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the volumetric capacity of the product. For refrigerators and wine
     * chillers the is taken to be the Total Refrigerated Volume.
     *
     * @return
     */
    public String getCapacity() {

        return (capacity == null || capacity.isEmpty() ? "0.0" : capacity);
    }

    /**
     * Sets the volumetric capacity of the product.
     *
     * @param capacity
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the first cost per Kwh.
     *
     * @return
     */
    public String getCostPerKwh() {

        return (costPerKwh == null || costPerKwh.isEmpty() ? "0.0" : costPerKwh);
    }

    /**
     * Sets the first cost per kWh.
     *
     * @param costPerKwh
     */
    public void setCostPerKwh(String costPerKwh) {
        this.costPerKwh = costPerKwh;
    }

    /**
     * Gets the second cost per kWh.
     *
     * @return
     */
    public String getCostPerKwh2() {

        return (costPerKwh2 == null || costPerKwh2.isEmpty() ? "0.0" : costPerKwh2);
    }

    /**
     * Sets the second cost per kWh.
     *
     * @param costPerKwh2
     */
    public void setCostPerKwh2(String costPerKwh2) {
        this.costPerKwh2 = costPerKwh2;
    }

    /**
     * Gets the country of origin of the product.
     *
     * @return
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the country of origin of the product.
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the defrost type of the product (e.g manual, automatic).
     *
     * @return
     */
    public String getDefrost() {
        return this.defrost;
    }

    /**
     * Sets the defrost type of this product.
     *
     * @param defrost
     */
    public void setDefrost(String defrost) {
        this.defrost = defrost;
    }

    /**
     * Gets the distributor of the product.
     *
     * @return
     */
    public String getDistributor() {
        return this.distributor;
    }

    /**
     * Sets the distributor of the product.
     *
     * @param distributor
     */
    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    /**
     * Gets the job number of the product.
     *
     * @return
     */
    public String getJobNumber() {
        if (jobNumber == null) {
            jobNumber = "";
        }
        return this.jobNumber;
    }

    /**
     * Sets the job number of the product.
     *
     * @param jobNumber
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
     * @param labelName
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * Gets the manufacturer of the product.
     *
     * @return
     */
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**
     * Sets the manufacturer of the product.
     *
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the model of the product.
     *
     * @return the model
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Sets the model of the product.
     *
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the operating cost of the product.
     *
     * @return the operatingCost
     */
    public String getOperatingCost() {

        return (operatingCost == null || operatingCost.isEmpty() ? "0.0" : operatingCost);
    }

    /**
     * Sets the operating cost of the product.
     *
     * @param operatingCost
     */
    public void setOperatingCost(String operatingCost) {
        this.operatingCost = operatingCost;
    }

    /**
     * Gets the standard to which the product was tested.
     *
     * @return the standard
     */
    public String getStandard() {
        return this.standard;
    }

    /**
     * Sets the standard to which the product was tested.
     *
     * @param standard
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * Gets the type of the product.
     *
     * @return
     */
    @Override
    public String getType() {
        if (type == null) {
            type = "";
        }
        return this.type;
    }

    /**
     * Sets the type of the product (e.g Refrigerator, Room Air-conditioner
     * etc.)
     *
     * @param type the new type
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the year of validity of this EnergyLabel.
     *
     * @return
     */
    public String getValidity() {
        return this.validity;
    }

    /**
     * Sets the year of validity of this EnergyLabel.
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

        return !(!Objects.equals(this.id, other.id)
                && (this.id == null
                || !this.id.equals(other.id)));
    }

    /**
     * Returns the label's name as a string representation of the label.
     *
     * @return
     */
    @Override
    public String toString() {
        return labelName;
    }

    /**
     * Saves the label data to a database.
     *
     * @param em
     * @return
     */
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

    /**
     * Gets the id of this EnergyLabel.
     *
     * @return
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Sets this id of this EnergyLabel.
     *
     * @param id
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of this EnergyLabel.
     *
     * @return
     */
    @Override
    public String getName() {
        return labelName;
    }

    /**
     * Sets the name of this EnergyLabel.
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.labelName = name;
    }

    /**
     * Validates this EnergyLabel. Currently, only double values are validated.
     *
     * @param em
     * @return
     */
    @Override
    public ReturnMessage validate(EntityManager em) {
        try {

            // Star rating
            if (!NumberUtils.validateDoubleValue(getStarRating()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Star Rating",
                        "The star rating is invalid", null);
            }
            // Job number
            if (getJobNumber().isEmpty()) {
                return new ReturnMessage(false, "No Job Number",
                        "You have not entered a job number", null);
            }
            // Fresh food compartment volume
            if (!NumberUtils.validateDoubleValue(getFreshFoodCompartmentVol()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Fresh Food Compartment Volume",
                        "The fresh food compartment volume is invalid", null);
            }
            // Freezer compartment volume
            if (!NumberUtils.validateDoubleValue(getFreezerCompartmentVol()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Freezer Compartment Volume",
                        "The freezer compartment volume is invalid", null);
            }
            // Volumetric capacity
            if (!NumberUtils.validateDoubleValue(getCapacity()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Capacity",
                        "The volumetric capacity is invalid", null);
            }
            // Total Adjusted Volume
            if (!NumberUtils.validateDoubleValue(getTotalAdjustedVol()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Total Adjusted Volume",
                        "The total adjusted volume is invalid", null);
            }
            // Cooling capacity
            if (!NumberUtils.validateDoubleValue(getCoolingCapacity()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Cooling Capacity",
                        "The cooling capacity is invalid", null);
            }
            // Heating capacity
            if (!NumberUtils.validateDoubleValue(getHeatingCapacity()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Heating Capacity",
                        "The heating capacity is invalid", null);
            }
            // BEC
            if (!NumberUtils.validateDoubleValue(getBEC()).isSuccess()) {
                return new ReturnMessage(false, "Invalid BEC",
                        "The BEC is invalid", null);
            }
            // CEC
            if (!NumberUtils.validateDoubleValue(getCEC()).isSuccess()) {
                return new ReturnMessage(false, "Invalid CEC",
                        "The CEC is invalid", null);
            }
            // Cf
            if (!NumberUtils.validateDoubleValue(getCf()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Cf",
                        "The Cf is invalid", null);
            }
            // Cv
            if (!NumberUtils.validateDoubleValue(getCv()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Cv",
                        "The Cv is invalid", null);
            }
            // ERF
            if (!NumberUtils.validateDoubleValue(getERF()).isSuccess()) {
                return new ReturnMessage(false, "Invalid ERF",
                        "The ERF is invalid", null);
            }
            // AEER
            if (!NumberUtils.validateDoubleValue(getAEER()).isSuccess()) {
                return new ReturnMessage(false, "Invalid AEER",
                        "The AEER is invalid", null);
            }
            // ACOP
            if (!NumberUtils.validateDoubleValue(getACOP()).isSuccess()) {
                return new ReturnMessage(false, "Invalid ACOP",
                        "The ACOP is invalid", null);
            }
            // Distributor
            if (getDistributor().isEmpty()) {
                return new ReturnMessage(false, "No Distributor",
                        "You have not entered a distributor's name", null);
            }
            // Manufacturer
            if (getManufacturer().isEmpty()) {
                return new ReturnMessage(false, "No Manufacturer",
                        "You have not entered a manufacturer's name", null);
            }
            // Brand
            if (getBrand().isEmpty()) {
                return new ReturnMessage(false, "No Brand",
                        "You have not entered a brand name", null);
            }
            // Model
            if (getModel().isEmpty()) {
                return new ReturnMessage(false, "No Model",
                        "You have not entered a model", null);
            }
            // Country of origin
            if (getCountry().isEmpty()) {
                return new ReturnMessage(false, "No Country of Origin",
                        "You have not entered a country of origin", null);
            }
            // Operating cost
            if (!NumberUtils.validateDoubleValue(getOperatingCost()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Operating Cost",
                        "The operating cost is invalid", null);
            }
            // Annual consumption
            if (!NumberUtils.validateDoubleValue(getAnnualConsumption()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Annual Consumption",
                        "The annual consumption is invalid", null);
            }
            // First cost per kwh (Electricity Rate 1)
            if (!NumberUtils.validateDoubleValue(getCostPerKwh()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Electricity Rate",
                        "The electricity rate is invalid", null);
            }
            // Second cost per kwh (Electricity Rate 2)
            if (!NumberUtils.validateDoubleValue(getCostPerKwh2()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Electricity Rate 2",
                        "The electricity rate 2 is invalid", null);
            }
            // Validity (year)
            if (!NumberUtils.validateIntegerValue(getValidity()).isSuccess()) {
                return new ReturnMessage(false, "Invalid Year",
                        "The year of validity is invalid", null);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage();
    }

    public Boolean getShowSampleWatermark() {
        if (showSampleWatermark == null) {
            showSampleWatermark = false;
        }
        return showSampleWatermark;
    }

    public void setShowSampleWatermark(Boolean showSampleWatermark) {
        this.showSampleWatermark = showSampleWatermark;
    }

    /**
     * Gets the isDirty flag that determines if this EnergyLabel was edited.
     *
     * @return
     */
    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }
        return isDirty;
    }

    /**
     * Sets the isDirty flag that determines if this EnergyLabel was edited.
     *
     * @param isDirty
     */
    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    /**
     * Calculates and returns the Star Rating Index (SRI) for cooling.
     *
     * @return
     */
    public Double getCoolingSRI() {
        Double aeer = NumberUtils.getDoubleValue(getAEER());

        return (aeer * 8.0 - 18.0) / 4.0;
    }

    /**
     * Calculates and returns the Star Rating Index (SRI) for heating.
     *
     * @return
     */
    public Double getHeatingSRI() {
        Double acop = NumberUtils.getDoubleValue(getACOP());

        return (acop * 8.0 - 18.0) / 4.0;
    }

    /**
     * Gets the Star Rating Index (SRI) of a refrigerator.
     *
     * @return
     */
    public Double getRefrigeratorSRI() {
        return 1.0 + (Math.log(NumberUtils.getDoubleValue(getCEC())
                / NumberUtils.getDoubleValue(getBEC()))
                / Math.log(1.0 - NumberUtils.getDoubleValue(getERF())));
    }

    /**
     * Calculates and returns the calculated Base Energy Consumption (BEC) of a
     * refrigerator.
     *
     * @return
     */
    public Double getCalcBEC() {
        return (NumberUtils.getDoubleValue(getCf())
                + NumberUtils.getDoubleValue(getCv())
                * Math.pow(NumberUtils.getDoubleValue(getTotalAdjustedVol()), 0.67));
    }

    /**
     * Calculates the star rating for a product based on the product type.
     *
     * @return
     */
    public Double getCalcStarRating() {
        if (getType().equals("Room Air-conditioner")) {
            return getCalcStarRatingForRoomAC();
        } else {
            return getCalcStarRatingForRefrigerator();
        }

    }

    /**
     * Calculates the star rating for a refrigerator.
     *
     * @return
     */
    private Double getCalcStarRatingForRefrigerator() {
        Double sri = getRefrigeratorSRI();

        if (sri < 1.5) {
            return 1.0;
        }
        if (sri >= 1.5 && sri < 2.0) {
            return 1.5;
        }
        if (sri >= 2.5 && sri < 3.0) {
            return 2.5;
        }
        if (sri >= 3.0 && sri < 3.5) {
            return 3.0;
        }
        if (sri >= 3.5 && sri < 4.0) {
            return 3.5;
        }
        if (sri >= 4.0 && sri < 4.5) {
            return 4.0;
        }
        if (sri >= 4.5 && sri < 5.0) {
            return 4.5;
        }
        if (sri >= 5.0 && sri < 5.5) {
            return 5.0;
        }
        if (sri >= 5.5 && sri < 6.0) {
            return 5.5;
        }
        if (sri >= 6.0 && sri < 7.0) {
            return 6.0;
        }
        if (sri >= 7.0 && sri < 8.0) {
            return 7.0;
        }
        if (sri >= 8.0 && sri < 9.0) {
            return 8.0;
        }
        if (sri >= 9.0 && sri < 10.0) {
            return 9.0;
        }
        if (sri >= 10.0) {
            return 10.0;
        }

        return 0.0;
    }

    /**
     * Calculates the star rating for Room Air-conditioner.
     *
     * @return
     */
    private Double getCalcStarRatingForRoomAC() {
        Double sri;

        if (getShowCoolingCapacity()) {
            sri = getCoolingSRI();
        } else {
            sri = getHeatingSRI();
        }

        if (sri < 1.5) {
            return 1.0;
        }
        if (sri >= 1.5 && sri < 2.0) {
            return 1.5;
        }
        if (sri >= 2.0 && sri < 2.5) {
            return 2.0;
        }
        if (sri >= 2.5 && sri < 3.0) {
            return 2.5;
        }
        if (sri >= 3.0 && sri < 3.5) {
            return 3.0;
        }
        if (sri >= 3.5 && sri < 4.0) {
            return 3.5;
        }
        if (sri >= 4.0 && sri < 4.5) {
            return 4.0;
        }
        if (sri >= 4.5 && sri < 5.0) {
            return 4.5;
        }
        if (sri >= 5.0 && sri < 5.5) {
            return 5.0;
        }
        if (sri >= 5.5 && sri < 6.0) {
            return 5.5;
        }
        if (sri >= 6.0 && sri < 7.0) {
            return 6.0;
        }
        if (sri >= 7.0 && sri < 8.0) {
            return 7.0;
        }
        if (sri >= 8.0 && sri < 9.0) {
            return 8.0;
        }
        if (sri >= 9.0 && sri < 10.0) {
            return 9.0;
        }
        if (sri >= 10.0) {
            return 10.0;
        }

        return 0.0;
    }

    /**
     * Calculates the total adjusted volume for a refrigerator. It is assumed
     * that the stored value is in cubic meters so it is multiplied by 1000.0 to
     * get litres.
     *
     * @return
     */
    private Double getCalcTotalAdjustedVol() {
        return NumberUtils.getDoubleValue(totalAdjustedVol) * 1000.0;
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
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
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
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
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
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
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

}
