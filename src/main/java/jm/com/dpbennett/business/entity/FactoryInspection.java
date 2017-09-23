/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

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
import jm.com.dpbennett.business.entity.utils.MethodResult;

/**
 * 
 * @author dbennett
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
    private String inspectionType;
    @Column(length = 1024)
    private String generalComments;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignedInspector;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact factoryRepresentative;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FactoryInspectionComponent> inspectionComponents;
    private String name;
    private Integer maxDaysForCompliance;
    private String actionsTaken;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice businessOffice;

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

    public BusinessOffice getBusinessOffice() {
        return businessOffice;
    }

    public void setBusinessOffice(BusinessOffice businessOffice) {
        this.businessOffice = businessOffice;
    }

    /**
     * Get all the entities contained in lists in this class
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

    public void setInspectionComponents(List<FactoryInspectionComponent> inspectionComponents) {
        this.inspectionComponents = inspectionComponents;
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
        return "jm.org.bsj.entity.FactoryInspection[id=" + id + "]";
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
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
