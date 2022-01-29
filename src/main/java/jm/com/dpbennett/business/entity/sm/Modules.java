/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.sm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.auth.Privilege;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "modules")
public class Modules implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean active;
    private String name;
    private String type;
    private String category;
    private String description;
    // tk: Create table modules_privilege
    // tk: Create field: Modules_ID (BIGINT, part of primary key/ an index)
    // tk: Create field: privileges_ID ((BIGINT, part of primary key/ an index)
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Privilege> privileges;
    private Boolean legalMetrologyModule;
    private Boolean jobManagementAndTrackingModule;
    private Boolean complianceModule;
    private Boolean foodsModule;
    private Boolean standardsModule;
    private Boolean certificationModule;
    private Boolean serviceRequestModule;
    private Boolean adminModule;
    private Boolean financialAdminModule;
    private Boolean purchaseManagementModule;
    private Boolean legalOfficeModule;
    private Boolean crmModule;
    private Boolean hrmModule;
    private Boolean reportModule;
    @Transient
    private Boolean isDirty;

    public Modules() {
        this.active = true;
        this.name = "";
        this.type = "";
        this.category = "";
        this.description = "";
    }

    public Modules(Boolean active,
            String name,
            String type,
            String category,
            String description) {

        this.active = active;
        this.name = name;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public List<Privilege> getPrivileges() {
        if (privileges == null) {
            privileges = new ArrayList<>();
        }
        
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getLegalMetrologyModule() {
        if (legalMetrologyModule == null) {
            legalMetrologyModule = false;
        }
        return legalMetrologyModule;
    }

    public void setLegalMetrologyModule(Boolean legalMetrologyModule) {
        this.legalMetrologyModule = legalMetrologyModule;
    }

    public Boolean getJobManagementAndTrackingModule() {
        if (jobManagementAndTrackingModule == null) {
            jobManagementAndTrackingModule = true;
        }
        return jobManagementAndTrackingModule;
    }

    public void setJobManagementAndTrackingModule(Boolean jobManagementAndTrackingModule) {
        this.jobManagementAndTrackingModule = jobManagementAndTrackingModule;
    }

    public Boolean getComplianceModule() {
        if (complianceModule == null) {
            complianceModule = false;
        }
        return complianceModule;
    }

    public void setComplianceModule(Boolean complianceModule) {
        this.complianceModule = complianceModule;
    }

    public Boolean getFoodsModule() {
        if (foodsModule == null) {
            foodsModule = false;
        }
        return foodsModule;
    }

    public void setFoodsModule(Boolean foodsModule) {
        this.foodsModule = foodsModule;
    }

    public Boolean getStandardsModule() {
        if (standardsModule == null) {
            standardsModule = false;
        }
        return standardsModule;
    }

    public void setStandardsModule(Boolean standardsModule) {
        this.standardsModule = standardsModule;
    }

    public Boolean getCertificationModule() {
        if (certificationModule == null) {
            certificationModule = false;
        }
        return certificationModule;
    }

    public void setCertificationModule(Boolean certificationModule) {
        this.certificationModule = certificationModule;
    }

    public Boolean getServiceRequestModule() {
        if (serviceRequestModule == null) {
            serviceRequestModule = false;
        }
        return serviceRequestModule;
    }

    public void setServiceRequestModule(Boolean serviceRequestModule) {
        this.serviceRequestModule = serviceRequestModule;
    }

    public Boolean getHrmModule() {
        if (hrmModule == null) {
            hrmModule = getAdminModule();
        }
        return hrmModule;
    }

    public void setHrmModule(Boolean hrmModule) {
        this.hrmModule = hrmModule;
    }

    public Boolean getReportModule() {
        if (reportModule == null) {
            reportModule = false;
        }
        return reportModule;
    }

    public void setReportModule(Boolean reportModule) {
        this.reportModule = reportModule;
    }

    public Boolean getAdminModule() {
        if (adminModule == null) {
            adminModule = false;
        }
        return adminModule;
    }

    public void setAdminModule(Boolean adminModule) {
        this.adminModule = adminModule;
    }

    public Boolean getFinancialAdminModule() {
        if (financialAdminModule == null) {
            financialAdminModule = false;
        }
        return financialAdminModule;
    }

    public void setFinancialAdminModule(Boolean financialAdminModule) {
        this.financialAdminModule = financialAdminModule;
    }

    public Boolean getPurchaseManagementModule() {
        if (purchaseManagementModule == null) {
            purchaseManagementModule = false;
        }
        return purchaseManagementModule;
    }

    public void setPurchaseManagementModule(Boolean purchaseManagementModule) {
        this.purchaseManagementModule = purchaseManagementModule;
    }

    public Boolean getLegalOfficeModule() {
        if (legalOfficeModule == null) {
            legalOfficeModule = false;
        }
        return legalOfficeModule;
    }

    public void setLegalOfficeModule(Boolean legalOfficeModule) {
        this.legalOfficeModule = legalOfficeModule;
    }

    public Boolean getCrmModule() {
        if (crmModule == null) {
            crmModule = false;
        }
        return crmModule;
    }

    public void setCrmModule(Boolean crmModule) {
        this.crmModule = crmModule;
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
        if (!(object instanceof Modules)) {
            return false;
        }
        Modules other = (Modules) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Modules not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

}
