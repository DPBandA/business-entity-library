/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.sm;

import java.util.ArrayList;
import java.util.Date;
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
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.auth.Privilege;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "modules")
public class Module implements BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean active;
    private String name;
    private String type;
    private String category;
    private String description;
    private String dashboardTitle;
    private String mainViewTitle;
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

    public Module() {
        this.active = false;
        this.name = "";
        this.type = "";
        this.category = "";
        this.description = "";
        this.dashboardTitle = "";
        this.mainViewTitle = "";
    }

    public Module(String name) {
        this.active = false;
        this.name = name;
        this.type = "";
        this.category = "";
        this.description = "";
        this.dashboardTitle = "";
        this.mainViewTitle = "";
    }

    public String getDashboardTitle() {
        return dashboardTitle;
    }

    public void setDashboardTitle(String dashboardTitle) {
        this.dashboardTitle = dashboardTitle;
    }

    public String getMainViewTitle() {
        return mainViewTitle;
    }

    public void setMainViewTitle(String mainViewTitle) {
        this.mainViewTitle = mainViewTitle;
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

    public List<String> getPrivilegeNames() {

        ArrayList<String> privilegeNames = new ArrayList<>();

        for (Privilege privilege : getPrivileges()) {
            privilegeNames.add(privilege.getName());
        }

        return privilegeNames;
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
    public Boolean getActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
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
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;

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

            for (Privilege privilege : privileges) {
                privilege.save(em);
            }

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Module not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    public static Module findActiveModuleByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Module> modules = em.createQuery("SELECT m FROM Module m "
                    + "WHERE m.active = 1 AND UPPER(m.name) "
                    + "= '" + value.toUpperCase() + "'", Module.class).getResultList();
            if (!modules.isEmpty()) {
                return modules.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Module> findActiveModules(
            EntityManager em,
            String value,
            int maxResults) {
        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Module> modules = em.createQuery("SELECT m FROM Module m"
                    + " WHERE (m.active = 1) AND (UPPER(m.name) like '%"
                    + value + "%'" + " OR UPPER(m.category) like '%"
                    + value + "%'" + " OR UPPER(m.description) like '%"
                    + value + "%') ORDER BY m.name", Module.class).
                    setMaxResults(maxResults).getResultList();

            return modules;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Module> findModules(
            EntityManager em,
            String value,
            int maxResults) {

        try {

            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");

            List<Module> modules = em.createQuery("SELECT m FROM Module m"
                    + " WHERE (UPPER(m.name) like '%"
                    + value + "%'" + " OR UPPER(m.category) like '%"
                    + value + "%'" + " OR UPPER(m.description) like '%"
                    + value + "%') ORDER BY m.name", Module.class).
                    setMaxResults(maxResults).getResultList();

            return modules;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Module> findAllActiveModules(
            EntityManager em,
            int maxResults) {
        try {

            List<Module> modules = em.createQuery("SELECT m FROM Module m"
                    + " WHERE m.active = 1 ORDER BY m.name", Module.class).
                    setMaxResults(maxResults).getResultList();

            return modules;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
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

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
