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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

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
    @Transient
    private Boolean isDirty;

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

    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }
        return isDirty;
    }

    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
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

        return new ReturnMessage(false, "Shipping Container not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

}
