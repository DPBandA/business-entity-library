/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

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
 *
 * @author Desmond
 */
@Entity
@Table(name = "energyconsumptionandefficiency")
@NamedQueries({
    @NamedQuery(name = "findAllMaxEnergyConsumptions", query = "SELECT e FROM EnergyConsumptionAndEfficiency e")
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
    private Double ConsumptionConstant;
    @Transient
    private Boolean isDirty;

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
        ConsumptionConstant = 0.0;
    }

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
        ConsumptionConstant = 0.0;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getProductRatedFrequency() {
        return productRatedFrequency;
    }

    public void setProductRatedFrequency(String productRatedFrequency) {
        this.productRatedFrequency = productRatedFrequency;
    }

    public Double getMinCEER() {
        return minCEER;
    }

    public void setMinCEER(Double minCEER) {
        this.minCEER = minCEER;
    }

    public Double getMinMEER() {
        return minMEER;
    }

    public void setMinMEER(Double minMEER) {
        this.minMEER = minMEER;
    }

    /**
     * Gets the Star rating index of a refrigerator.
     *
     * @param CEC
     * @param BEC
     * @param ERF
     * @return
     */
    public static double getRefrigeratorSRI(double CEC, double BEC, double ERF) {
        return 1.0 + (Math.log(CEC / BEC) / Math.log(1.0 - ERF));
    }

    public static double getBEC(double Cf, double Cv, double Vadjtot) {
        return Cf + Cv * Math.pow(Vadjtot, 0.67);
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getAVLCoefficient() {
        return AVLCoefficient;
    }

    public void setAVLCoefficient(Double AVLCoefficient) {
        this.AVLCoefficient = AVLCoefficient;
    }

    public Double getAVCuFtCoefficient() {
        return AVCuFtCoefficient;
    }

    public void setAVCuFtCoefficient(Double AVCuFtCoefficient) {
        this.AVCuFtCoefficient = AVCuFtCoefficient;
    }

    public Double getConsumptionConstant() {
        return ConsumptionConstant;
    }

    public void setConsumptionConstant(Double ConsumptionConstant) {
        this.ConsumptionConstant = ConsumptionConstant;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getProductTypeDetail() {
        return productTypeDetail;
    }

    public void setProductTypeDetail(String productTypeDetail) {
        this.productTypeDetail = productTypeDetail;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the Id fields are not set
        if (!(object instanceof EnergyConsumptionAndEfficiency)) {
            return false;
        }
        EnergyConsumptionAndEfficiency other = (EnergyConsumptionAndEfficiency) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<EnergyConsumptionAndEfficiency> findfindAll(EntityManager em) {

        try {
            List<EnergyConsumptionAndEfficiency> consumptions
                    = em.createNamedQuery("findAllMaxEnergyConsumptions", EnergyConsumptionAndEfficiency.class).getResultList();

            return consumptions;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static EnergyConsumptionAndEfficiency findById(EntityManager em, Long Id) {

        try {
            EnergyConsumptionAndEfficiency maxEnergyConsumption = em.find(EnergyConsumptionAndEfficiency.class, Id);

            return maxEnergyConsumption;
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