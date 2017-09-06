/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dbennett
 */
@Entity
@XmlRootElement
public class ShippingContainer implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String number;
    private String sizeDescription;
    private Integer L;
    private Integer W;
    private Integer H;
    private String dimensionUnit;
    private Double percentageDetained;

    public ShippingContainer() {
    }

    public ShippingContainer(String number) {
        this.number = number;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentageDetained() {
        if (percentageDetained == null) {
            percentageDetained = 0.0;
        }
        return percentageDetained;
    }

    public void setPercentageDetained(Double percentageDetained) {
        this.percentageDetained = percentageDetained;
    }

    public String getSizeDescription() {
        return sizeDescription;
    }

    public void setSizeDescription(String sizeDescription) {
        this.sizeDescription = sizeDescription;
    }

    public String getNumber() {
        if (number == null) {
            number = "";
        }
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getL() {
        return L;
    }

    public void setL(Integer L) {
        this.L = L;
    }

    public Integer getW() {
        return W;
    }

    public void setW(Integer W) {
        this.W = W;
    }

    public Integer getH() {
        return H;
    }

    public void setH(Integer H) {
        this.H = H;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
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
        if (!(object instanceof ShippingContainer)) {
            return false;
        }
        ShippingContainer other = (ShippingContainer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNumber();
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

    public ShippingContainer getShippingContainerByComplianceSurveyID(EntityManager em,
            String containerNumber,
            Long complianceSurveyID) {

        try {
            String newContainerNumber = containerNumber.trim().replaceAll("'", "''");

            ComplianceSurvey complianceSurvey = ComplianceSurvey.findComplianceSurveyById(em, complianceSurveyID);
            if (complianceSurvey != null) {
                List<ShippingContainer> shippingContainers = complianceSurvey.getEntryDocumentInspection().getShippingContainers();
                if (shippingContainers.isEmpty()) {
                    return null;
                } else {
                    //List<String> containerNums = complianceSurvey.getContainerNumberList();
                    for (ShippingContainer shippingContainer : shippingContainers) {
                        if (shippingContainer.getNumber().trim().equals(newContainerNumber)) {
                            return shippingContainer;
                        }
                    }
                }

            } else {
                return null;
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
    
     public static ShippingContainer findShippingContainerByNumber(EntityManager em, String shippingContainerByNumber) {

        try {
            String newShippingContainerByNumber = shippingContainerByNumber.trim().replaceAll("'", "''");

            List<ShippingContainer> containers = em.createQuery("SELECT s FROM ShippingContainer s "
                    + "WHERE UPPER(s.number) "
                    + "= '" + newShippingContainerByNumber.toUpperCase() + "'", ShippingContainer.class).getResultList();
            if (containers.size() > 0) {
                return containers.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
     
}
