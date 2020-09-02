/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2020  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sc;

import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.hrm.BusinessOffice;
import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.hrm.Address;
import jm.com.dpbennett.business.entity.hrm.Manufacturer;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.Message;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "factoryinspection")
public class FactoryInspection implements BusinessEntity, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inspectionDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inspectionStartTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inspectionEndTime;
    @Column(length = 1024)
    private String workInProgress;
    private String workProgress;
    private String inspectionType;
    @Column(length = 1024)
    private String generalComments;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignedInspector;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Address address;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Contact factoryRepresentative;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<FactoryInspectionComponent> inspectionComponents;
    private String name;
    private Integer maxDaysForCompliance;
    private String actionsTaken;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice businessOffice;
    @Transient
    private Boolean isDirty;

    public FactoryInspection() {
        inspectionComponents = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
     public static List<FactoryInspection> findFactoryInspectionsByName(EntityManager em, String value) {

        try {
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<FactoryInspection> factoryInspections
                    = em.createQuery("SELECT f FROM FactoryInspection f WHERE UPPER(f.name) LIKE '%"
                            + value.toUpperCase().trim() + "%' ORDER BY f.name", FactoryInspection.class).getResultList();
            
            return factoryInspections;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
     
    public static FactoryInspection findFactoryInspectionByName(EntityManager em, String value) {

        try {
            
            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<FactoryInspection> factoryInspections = em.createQuery("SELECT f FROM FactoryInspection f "
                    + "WHERE UPPER(f.name) "
                    + "= '" + value.toUpperCase() + "'", FactoryInspection.class).getResultList();
            
            if (factoryInspections.size() > 0) {
                return factoryInspections.get(0);
            }
            
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String getWorkProgress() {
        return workProgress;
    }

    public void setWorkProgress(String workProgress) {
        this.workProgress = workProgress;
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

    public Address getAddress() {
        if (address == null) {
            return new Address();
        }
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Manufacturer getManufacturer() {
        if (manufacturer == null) {
            return new Manufacturer();
        }
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BusinessOffice getBusinessOffice() {
        return businessOffice;
    }

    public void setBusinessOffice(BusinessOffice businessOffice) {
        this.businessOffice = businessOffice;
    }

    /**
     * Get all the entities contained in lists in this class
     *
     * @return
     */
    public List<BusinessEntity> getAllBusinessEntitiesLists() {
        ArrayList<BusinessEntity> entities = new ArrayList<>();

        entities.addAll(inspectionComponents);

        return entities;
    }

    public String getActionsTaken() {
        return actionsTaken;
    }

    public void setActionsTaken(String actionsTaken) {
        this.actionsTaken = actionsTaken;
    }

    public Integer getMaxDaysForCompliance() {
        return maxDaysForCompliance;
    }

    public void setMaxDaysForCompliance(Integer maxDaysForCompliance) {
        this.maxDaysForCompliance = maxDaysForCompliance;
    }

    public Employee getAssignedInspector() {
        return assignedInspector;
    }

    public void setAssignedInspector(Employee assignedInspector) {
        this.assignedInspector = assignedInspector;
    }

    public Contact getFactoryRepresentative() {
        if (factoryRepresentative == null) {
            return new Contact();
        }
        return factoryRepresentative;
    }

    public void setFactoryRepresentative(Contact factoryRepresentative) {
        this.factoryRepresentative = factoryRepresentative;
    }

    public String getGeneralComments() {
        return generalComments;
    }

    public void setGeneralComments(String generalComments) {
        this.generalComments = generalComments;
    }

    public List<FactoryInspectionComponent> getInspectionComponents() {
        return inspectionComponents;
    }

    public void setInspectionComponents(List<FactoryInspectionComponent> inspectionComponents) {
        this.inspectionComponents = inspectionComponents;
    }

    public List<FactoryInspectionComponent> getInspectionComponentsByCategory(String category) {
        ArrayList<FactoryInspectionComponent> components = new ArrayList<>();

        for (FactoryInspectionComponent factoryInspectionComponent : inspectionComponents) {
            if (factoryInspectionComponent.getCategory() != null) {
                if (factoryInspectionComponent.getCategory().equals(category)) {
                    components.add(factoryInspectionComponent);
                }
            }
        }
        return components;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Date getInspectionEndTime() {
        return inspectionEndTime;
    }

    public void setInspectionEndTime(Date inspectionEndTime) {
        this.inspectionEndTime = inspectionEndTime;
    }

    public Date getInspectionStartTime() {
        return inspectionStartTime;
    }

    public void setInspectionStartTime(Date inspectionStartTime) {
        this.inspectionStartTime = inspectionStartTime;
    }

    public String getInspectionType() {
//        if (inspectionType == null) {
//            inspectionType = "Routine";
//        }
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getWorkInProgress() {
        return workInProgress;
    }

    public void setWorkInProgress(String workInProgress) {
        this.workInProgress = workInProgress;
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
        if (!(object instanceof FactoryInspection)) {
            return false;
        }
        FactoryInspection other = (FactoryInspection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
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
    public ReturnMessage save(EntityManager em) {
        try {

            // Save inspection components
            if (!getInspectionComponents().isEmpty()) {
                for (FactoryInspectionComponent inspectionComponent : getInspectionComponents()) {
                    if ((inspectionComponent.getIsDirty() || inspectionComponent.getId() == null)
                            && !inspectionComponent.save(em).isSuccess()) {

                        return new ReturnMessage(false,
                                "Inspection component save error occurred",
                                "An error occurred while saving an Inspection component",
                                Message.SEVERITY_ERROR_NAME);
                    }
                }
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Factory inspection not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
