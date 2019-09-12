/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        return "Modules";
    }

    @Override
    public void setName(String name) {
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
