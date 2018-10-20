/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 * This class encapsulates energy consumption and efficiency properties and
 * methods of an energy product.
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "energyconsumptionandefficiency")
@NamedQueries({
    @NamedQuery(name = "findAllEnergyConsumptionAndEfficiency",
            query = "SELECT e FROM EnergyConsumptionAndEfficiency e")
})
@XmlRootElement
public class EnergyConsumptionAndEfficiency implements Serializable, BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String itemNo;
    private String productType;
    private String productTypeDetail;
    private String productClass;
    private String productRatedFrequency;
    private Double minCEER;
    private Double minMEER;
    private Double AVLCoefficient;
    private Double AVCuFtCoefficient;
    private Double consumptionConstant;
    private Double adjustmentFactor;
    @Transient
    private Boolean isDirty;

    /**
     * The default constructor.
     */
    public EnergyConsumptionAndEfficiency() {
        name = "";
        itemNo = "";
        productType = "";
        productTypeDetail = "";
        productClass = "";
        productRatedFrequency = "";
        minCEER = 0.0;
        minMEER = 0.0;
        AVLCoefficient = 0.0;
        AVCuFtCoefficient = 0.0;
        consumptionConstant = 0.0;
        adjustmentFactor = 0.0;
    }

    /**
     * A constructor that takes an id and name as parameters.
     *
     * @param id
     * @param name
     */
    public EnergyConsumptionAndEfficiency(Long id, String name) {
        this.id = id;
        this.name = name;
        itemNo = "";
        productType = "";
        productTypeDetail = "";
        productClass = "";
        productRatedFrequency = "";
        minCEER = 0.0;
        minMEER = 0.0;
        AVLCoefficient = 0.0;
        AVCuFtCoefficient = 0.0;
        consumptionConstant = 0.0;
        adjustmentFactor = 0.0;
    }

    /**
     * Gets the id.
     *
     * @return
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets an Adjustment Factor (AF) used in the calculation of Adjusted Volume (AV).
     * 
     * @return 
     */
    public Double getAdjustmentFactor() {
        return adjustmentFactor;
    }

    /**
     * Sets an Adjustment Factor (AF) used in the calculation of Adjusted Volume (AV).
     * 
     * @param adjustmentFactor 
     */
    public void setAdjustmentFactor(Double adjustmentFactor) {
        this.adjustmentFactor = adjustmentFactor;
    }    

    /**
     * Gets the class of the product. This usually applies to air-conditioning
     * products.
     *
     * @return
     */
    public String getProductClass() {
        if (productClass == null) {
            productClass = "";
        }
        return productClass;
    }

    /**
     * Sets the product class. This usually applies to air-conditioning
     * products.
     *
     * @param productClass
     */
    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    /**
     * Gets the rated frequency or frequency range of the product.
     *
     * @return
     */
    public String getProductRatedFrequency() {
        return productRatedFrequency;
    }

    /**
     * Sets the rated frequency or frequency range of the product.
     *
     * @param productRatedFrequency
     */
    public void setProductRatedFrequency(String productRatedFrequency) {
        this.productRatedFrequency = productRatedFrequency;
    }

    /**
     * Gets the minimum Combined Energy Efficiency Ratio (CEER) of the product
     * class.
     *
     * @return
     */
    public Double getMinCEER() {
        return minCEER;
    }

    /**
     * Sets the minimum Combined Energy Efficiency Ratio (CEER) of the product
     * class.
     *
     * @param minCEER
     */
    public void setMinCEER(Double minCEER) {
        this.minCEER = minCEER;
    }

    /**
     * Gets the Minimum Energy Efficiency Requirements (MEER) of the product
     * class. This is the same as minimum CEER (??)
     *
     * @return
     */
    public Double getMinMEER() {
        return minMEER;
    }

    /**
     * Sets the Minimum Energy Efficiency Requirements (MEER) of the product
     * class. This is the same as minimum CEER (??)
     *
     * @param minMEER
     */
    public void setMinMEER(Double minMEER) {
        this.minMEER = minMEER;
    }

    /**
     * Gets the Star Rating Index (SRI) of a refrigerator.
     *
     * @param CEC Comparative Energy Consumption
     * @param BEC Base Energy Consumption
     * @param ERF Energy Consumption Reduction Factor
     * @return
     */
    public static double getRefrigeratorSRI(double CEC, double BEC, double ERF) {
        return 1.0 + (Math.log(CEC / BEC) / Math.log(1.0 - ERF));
    }

    /**
     * Calculates and returns the Base Energy Consumption (BEC) of a
     * refrigerator.
     *
     * @param Cf Fixed allowance factor for the product's group in kWh/y
     * @param Cv Variable allowance factor for its group in kWh/y
     * @param Vadjtot Total adjusted volume for the model in litres
     * @return
     */
    public static double getBEC(double Cf, double Cv, double Vadjtot) {
        return Cf + Cv * Math.pow(Vadjtot, 0.67);
    }

    /**
     * Gets the product's type (e.g. Refrigerator, Room Air-conditioner).
     *
     * @return
     */
    public String getProductType() {
        if (productType == null) {
            productType = "";
        }
        return productType;
    }

    /**
     * Sets the product's type (e.g. Refrigerator, Room Air-conditioner).
     *
     * @param productType
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Gets the coefficient of the product's Adjusted Volume (AV) in litres.
     * This is part of the formula used to calculate the maximum allowed energy
     * consumption of a product.
     *
     * @return
     */
    public Double getAVLCoefficient() {
        return AVLCoefficient;
    }

    /**
     * Sets the coefficient of the product's Adjusted Volume (AV) in litres.
     * This is part of the formula used to calculate the maximum allowed energy
     * consumption of a product.
     *
     * @param AVLCoefficient
     */
    public void setAVLCoefficient(Double AVLCoefficient) {
        this.AVLCoefficient = AVLCoefficient;
    }

    /**
     * Gets the coefficient of the product's Adjusted Volume (AV) in cubic feet.
     * This is part of the formula used to calculate the maximum allowed energy
     * consumption of a product.
     *
     * @return
     */
    public Double getAVCuFtCoefficient() {
        return AVCuFtCoefficient;
    }

    /**
     * Sets the coefficient of the product's Adjusted Volume (AV) in cubic feet.
     * This is part of the formula used to calculate the maximum allowed energy
     * consumption of a product.
     *
     * @param AVCuFtCoefficient
     */
    public void setAVCuFtCoefficient(Double AVCuFtCoefficient) {
        this.AVCuFtCoefficient = AVCuFtCoefficient;
    }

    /**
     * Gets the constant for calculating maximum energy consumption. This is
     * part of the formula used to calculate the maximum allowed energy
     * consumption of a product.
     *
     * @return
     */
    public Double getConsumptionConstant() {
        return consumptionConstant;
    }

    /**
     * Sets the constant for calculating maximum energy consumption. This is
     * part of the formula used to calculate the maximum allowed energy
     * consumption of a product.
     *
     * @param consumptionConstant
     */
    public void setConsumptionConstant(Double consumptionConstant) {
        this.consumptionConstant = consumptionConstant;
    }

    /**
     * Gets the item number for the product's type.
     *
     * @return
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the item number for the product's type.
     *
     * @param itemNo
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * Gets the detail of the product's type.
     *
     * @return
     */
    public String getProductTypeDetail() {
        if (productTypeDetail == null) {
            productTypeDetail = "";
        }
        return productTypeDetail;
    }

    /**
     * Sets the detail of the product's type.
     *
     * @param productTypeDetail
     */
    public void setProductTypeDetail(String productTypeDetail) {
        this.productTypeDetail = productTypeDetail;
    }

    /**
     * Gets the isDirty flag that determines if this object is dirty (edited).
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
     * Sets the isDirty flag that determines if this object is dirty (edited).
     *
     * @param isDirty
     */
    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
    }

    /**
     * Gets the name of this object.
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this object.
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets a hash code for this object.
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Tests if this object is equal to another object of the same type. This
     * method won't work in the case the Id fields are not set.
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EnergyConsumptionAndEfficiency)) {
            return false;
        }
        EnergyConsumptionAndEfficiency other = (EnergyConsumptionAndEfficiency) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    /**
     * Gets the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        if (getProductType().equals("Room Air-conditioner")) {
            return getProductClass();
        } else {
            return getProductTypeDetail();
        }
    }

    /**
     * Find and return all EnergyConsumptionAndEfficiency objects.
     *
     * @param em
     * @return
     */
    public static List<EnergyConsumptionAndEfficiency> findAll(EntityManager em) {

        try {
            List<EnergyConsumptionAndEfficiency> consumptions
                    = em.createNamedQuery("findAllEnergyConsumptionAndEfficiency", EnergyConsumptionAndEfficiency.class).getResultList();

            return consumptions;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Find and return all EnergyConsumptionAndEfficiency objects based on
     * product type. The objects are returned as BusinessEntity interface
     * objects.
     *
     * @param em
     * @param value
     * @return
     */
    public static List<BusinessEntity> findAllByProductType(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<BusinessEntity> list
                    = em.createQuery("SELECT e FROM EnergyConsumptionAndEfficiency e where UPPER(e.productType) like '%"
                            + value.toUpperCase().trim() + "%' ORDER BY e.productType", BusinessEntity.class).getResultList();

            return list;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static EnergyConsumptionAndEfficiency findById(EntityManager em, Long Id) {

        try {
            EnergyConsumptionAndEfficiency energyConsumptionAndEfficiency
                    = em.find(EnergyConsumptionAndEfficiency.class, Id);

            return energyConsumptionAndEfficiency;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
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

        return new ReturnMessage(false, "EnergyConsumptionAndEfficiency not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
