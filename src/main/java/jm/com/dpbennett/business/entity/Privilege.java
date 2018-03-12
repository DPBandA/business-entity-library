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
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author dbennett
 */
@Entity
@Table(name = "privilege")
@NamedQueries({
    @NamedQuery(name = "findAllPrivileges", query = "SELECT p FROM Privilege p ORDER BY p.name")
    ,
    @NamedQuery(name = "findByPrivilegesName", query = "SELECT p FROM Privilege p WHERE p.name = :name")
})
@XmlRootElement
public class Privilege implements Serializable, BusinessEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String category;
    private String roles;
    private String description;
    // Privileges
    private Boolean canEnterJob;
    private Boolean canEnterOwnJob;
    private Boolean canEnterDepartmentJob;
    private Boolean canDeleteJob;
    private Boolean canEditJob;
    private Boolean canEditOwnJob;
    private Boolean canEditDepartmentJob;
    private Boolean canAddClient;
    private Boolean canDeleteClient;
    private Boolean canAddEmployee;
    private Boolean canDeleteEmployee;
    private Boolean canAddDepartment;
    private Boolean canDeleteDepartment;
    private Boolean canBeSuperUser;
    private Boolean canApproveJobCosting;
    private Boolean canEnterParentJob;
    private Boolean canEditInvoicingAndPayment;
    private Boolean canAuthDetentionRequest; //CANAUTHDETENTIONREQUEST, Authorize detention request
    private Boolean canAuthDetentionNotice; //CANAUTHDETENTIONNOTICE, Authorize detention notice/release
    private Boolean canApprvReleaseRequest; //CANAPPRVRELEASEREQUEST, Approve release request
    private Boolean canApplyTaxesToJobCosting;
    // Unit/Module access     
    private Boolean canBeJMTSAdministrator;
    private Boolean canBeFinancialAdministrator;
    private Boolean canAccessLegalMetrologyUnit;
    private Boolean canAccessJobManagementUnit;
    private Boolean canAccessComplianceUnit;
    private Boolean canAccessFoodsUnit;
    private Boolean canAccessStandardsUnit;
    private Boolean canAccessCertificationUnit;
    private Boolean canAccessServiceRequestUnit;
    private Boolean canAccessLegalOfficeUnit;
    private Boolean canAccessCRMUnit;
    // End privilges

    public Privilege() {
        init("");
    }

    public Privilege(String name) {
        init(name);
    }

    public Boolean getCanAccessCRMUnit() {
        if (canAccessCRMUnit == null) {
            canAccessCRMUnit = false;
        }
        return canAccessCRMUnit;
    }

    public void setCanAccessCRMUnit(Boolean canAccessCRMUnit) {
        this.canAccessCRMUnit = canAccessCRMUnit;
    }

    public Boolean getCanAccessLegalMetrologyUnit() {
        if (canAccessLegalMetrologyUnit == null) {
            canAccessLegalMetrologyUnit = false;
        }
        return canAccessLegalMetrologyUnit;
    }

    public void setCanAccessLegalMetrologyUnit(Boolean canAccessLegalMetrologyUnit) {
        this.canAccessLegalMetrologyUnit = canAccessLegalMetrologyUnit;
    }

    public Boolean getCanAccessJobManagementUnit() {
        if (canAccessJobManagementUnit == null) {
            canAccessJobManagementUnit = false;
        }
        return canAccessJobManagementUnit;
    }

    public void setCanAccessJobManagementUnit(Boolean canAccessJobManagementUnit) {
        this.canAccessJobManagementUnit = canAccessJobManagementUnit;
    }

    public Boolean getCanAccessComplianceUnit() {
        if (canAccessComplianceUnit == null) {
            canAccessComplianceUnit = false;
        }
        return canAccessComplianceUnit;
    }

    public void setCanAccessComplianceUnit(Boolean canAccessComplianceUnit) {
        this.canAccessComplianceUnit = canAccessComplianceUnit;
    }

    public Boolean getCanAccessFoodsUnit() {
        if (canAccessFoodsUnit == null) {
            canAccessFoodsUnit = false;
        }
        return canAccessFoodsUnit;
    }

    public void setCanAccessFoodsUnit(Boolean canAccessFoodsUnit) {
        this.canAccessFoodsUnit = canAccessFoodsUnit;
    }

    public Boolean getCanAccessStandardsUnit() {
        if (canAccessStandardsUnit == null) {
            canAccessStandardsUnit = false;
        }
        return canAccessStandardsUnit;
    }

    public void setCanAccessStandardsUnit(Boolean canAccessStandardsUnit) {
        this.canAccessStandardsUnit = canAccessStandardsUnit;
    }

    public Boolean getCanAccessCertificationUnit() {
        if (canAccessCertificationUnit == null) {
            canAccessCertificationUnit = false;
        }
        return canAccessCertificationUnit;
    }

    public void setCanAccessCertificationUnit(Boolean canAccessCertificationUnit) {
        this.canAccessCertificationUnit = canAccessCertificationUnit;
    }

    public Boolean getCanAccessServiceRequestUnit() {
        if (canAccessServiceRequestUnit == null) {
            canAccessServiceRequestUnit = false;
        }
        return canAccessServiceRequestUnit;
    }

    public void setCanAccessServiceRequestUnit(Boolean canAccessServiceRequestUnit) {
        this.canAccessServiceRequestUnit = canAccessServiceRequestUnit;
    }

    public Boolean getCanAccessLegalOfficeUnit() {
        if (canAccessLegalOfficeUnit == null) {
            canAccessLegalOfficeUnit = false;
        }
        return canAccessLegalOfficeUnit;
    }

    public void setCanAccessLegalOfficeUnit(Boolean canAccessLegalOfficeUnit) {
        this.canAccessLegalOfficeUnit = canAccessLegalOfficeUnit;
    }

    /**
     * NB: Temp code included to be removed in the future
     */
    private void init(String name) {
        // default privileges
        this.name = name;
        canEnterJob = false;
        canEnterOwnJob = true;
        canEnterDepartmentJob = true;
        canDeleteJob = false;
        canEditJob = false;
        canEditOwnJob = true;
        canEditDepartmentJob = true;
        canAddClient = false;
        canDeleteClient = false;
        canAddEmployee = false;
        canDeleteEmployee = false;
        canAddDepartment = false;
        canDeleteDepartment = false;
        canBeJMTSAdministrator = false;
        canBeSuperUser = false;
        canApproveJobCosting = false;
        canEnterParentJob = true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCanBeFinancialAdministrator() {
        if (canBeFinancialAdministrator == null) {
            canBeFinancialAdministrator = false;
        }
        return canBeFinancialAdministrator;
    }

    public void setCanBeFinancialAdministrator(Boolean canBeFinancialAdministrator) {
        this.canBeFinancialAdministrator = canBeFinancialAdministrator;
    }

    public Boolean getCanApplyTaxesToJobCosting() {
        if (canApplyTaxesToJobCosting == null) {
            canApplyTaxesToJobCosting = true;
        }
        return canApplyTaxesToJobCosting;
    }

    public void setCanApplyTaxesToJobCosting(Boolean canApplyTaxesToJobCosting) {
        this.canApplyTaxesToJobCosting = canApplyTaxesToJobCosting;
    }

    public Boolean getCanApprvReleaseRequest() {
        if (canApprvReleaseRequest == null) {
            canApprvReleaseRequest = false;
        }
        return canApprvReleaseRequest;
    }

    public void setCanApprvReleaseRequest(Boolean canApprvReleaseRequest) {
        this.canApprvReleaseRequest = canApprvReleaseRequest;
    }

    public Boolean getCanAuthDetentionNotice() {
        if (canAuthDetentionNotice == null) {
            canAuthDetentionNotice = false;
        }
        return canAuthDetentionNotice;
    }

    public void setCanAuthDetentionNotice(Boolean canAuthDetentionNotice) {
        this.canAuthDetentionNotice = canAuthDetentionNotice;
    }

    public Boolean getCanAuthDetentionRequest() {
        if (canAuthDetentionRequest == null) {
            canAuthDetentionRequest = false;
        }
        return canAuthDetentionRequest;
    }

    public void setCanAuthDetentionRequest(Boolean canAuthDetentionRequest) {
        this.canAuthDetentionRequest = canAuthDetentionRequest;
    }

    public Boolean getCanEditInvoicingAndPayment() {
        if (canEditInvoicingAndPayment == null) {
            canEditInvoicingAndPayment = false;
        }
        return canEditInvoicingAndPayment;
    }

    public void setCanEditInvoicingAndPayment(Boolean canEditInvoicingAndPayment) {
        this.canEditInvoicingAndPayment = canEditInvoicingAndPayment;
    }

    public Boolean getCanApproveJobCosting() {
        if (canApproveJobCosting == null) {
            canApproveJobCosting = false;
        }
        return canApproveJobCosting;
    }

    public void setCanApproveJobCosting(Boolean canApproveJobCosting) {
        this.canApproveJobCosting = canApproveJobCosting;
    }

    public Boolean getCanEnterParentJob() {
        if (canEnterParentJob == null) {
            canEnterParentJob = false;
        }
        return canEnterParentJob;
    }

    public void setCanEnterParentJob(Boolean canEnterParentJob) {
        this.canEnterParentJob = canEnterParentJob;
    }

    public Boolean getCanEnterOwnJob() {
        if (canEnterOwnJob == null) {
            canEnterOwnJob = false;
        }
        return canEnterOwnJob;
    }

    public void setCanEnterOwnJob(Boolean canEnterOwnJob) {
        this.canEnterOwnJob = canEnterOwnJob;
    }

    public Boolean getCanEnterDepartmentJob() {
        if (canEnterDepartmentJob == null) {
            canEnterDepartmentJob = true;
        }
        return canEnterDepartmentJob;
    }

    public void setCanEnterDepartmentJob(Boolean canEnterDepartmentJob) {
        this.canEnterDepartmentJob = canEnterDepartmentJob;
    }

    public Boolean getCanBeJMTSAdministrator() {
        if (canBeJMTSAdministrator == null) {
            canBeJMTSAdministrator = false;
        }

        return canBeJMTSAdministrator;
    }

    public void setCanBeJMTSAdministrator(Boolean canBeJMTSAdministrator) {
        this.canBeJMTSAdministrator = canBeJMTSAdministrator;
    }

    public Boolean getCanBeSuperUser() {
        if (canBeSuperUser == null) {
            canBeSuperUser = false;
        }
        return canBeSuperUser;
    }

    public void setCanBeSuperUser(Boolean canBeSuperUser) {
        this.canBeSuperUser = canBeSuperUser;
    }

    public Boolean getCanAddClient() {
        if (canAddClient == null) {
            canAddClient = false;
        }
        return canAddClient;
    }

    public void setCanAddClient(Boolean canAddClient) {
        this.canAddClient = canAddClient;
    }

    public Boolean getCanAddDepartment() {
        if (canAddDepartment == null) {
            canAddDepartment = false;
        }
        return canAddDepartment;
    }

    public void setCanAddDepartment(Boolean canAddDepartment) {
        this.canAddDepartment = canAddDepartment;
    }

    public Boolean getCanAddEmployee() {
        if (canAddEmployee == null) {
            canAddEmployee = false;
        }
        return canAddEmployee;
    }

    public void setCanAddEmployee(Boolean canAddEmployee) {
        this.canAddEmployee = canAddEmployee;
    }

    public Boolean getCanDeleteClient() {
        if (canDeleteClient == null) {
            canDeleteClient = false;
        }
        return canDeleteClient;
    }

    public void setCanDeleteClient(Boolean canDeleteClient) {
        this.canDeleteClient = canDeleteClient;
    }

    public Boolean getCanDeleteDepartment() {
        if (canDeleteDepartment == null) {
            canDeleteDepartment = false;
        }
        return canDeleteDepartment;
    }

    public void setCanDeleteDepartment(Boolean canDeleteDepartment) {
        this.canDeleteDepartment = canDeleteDepartment;
    }

    public Boolean getCanDeleteEmployee() {
        if (canDeleteEmployee == null) {
            canDeleteEmployee = false;
        }
        return canDeleteEmployee;
    }

    public void setCanDeleteEmployee(Boolean canDeleteEmployee) {
        this.canDeleteEmployee = canDeleteEmployee;
    }

    public Boolean getCanEditDepartmentJob() {
        if (canEditDepartmentJob == null) {
            canEditDepartmentJob = true;
        }
        return canEditDepartmentJob;
    }

    public void setCanEditDepartmentJob(Boolean canEditDepartmentJob) {
        this.canEditDepartmentJob = canEditDepartmentJob;
    }

    public Boolean getCanDeleteJob() {
        if (canDeleteJob == null) {
            canDeleteJob = false;
        }
        return canDeleteJob;
    }

    public void setCanDeleteJob(Boolean canDeleteJob) {
        this.canDeleteJob = canDeleteJob;
    }

    public Boolean getCanEditJob() {
        if (canEditJob == null) {
            canEditJob = false;
        }
        return canEditJob;
    }

    public void setCanEditJob(Boolean canEditJob) {
        this.canEditJob = canEditJob;
    }

    public Boolean getCanEditOwnJob() {
        if (canEditOwnJob == null) {
            canEditOwnJob = false;
        }
        return canEditOwnJob;
    }

    public void setCanEditOwnJob(Boolean canEditOwnJob) {
        this.canEditOwnJob = canEditOwnJob;
    }

    public Boolean getCanEnterJob() {
        if (canEnterJob == null) {
            canEnterJob = false;
        }
        return canEnterJob;
    }

    public void setCanEnterJob(Boolean canEnterJob) {
        this.canEnterJob = canEnterJob;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static Privilege findPrivilegeByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''").trim();

            List<Privilege> privileges = em.createQuery("SELECT p FROM Privilege p "
                    + "WHERE UPPER(p.name) "
                    + "LIKE '" + newName.toUpperCase() + "%'", Privilege.class).getResultList();

            if (privileges.size() > 0) {
                // Make sure this is the current option stored in the database
                Privilege privilege = privileges.get(0);
                em.refresh(privilege);

                return privilege;
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ReturnMessage save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
