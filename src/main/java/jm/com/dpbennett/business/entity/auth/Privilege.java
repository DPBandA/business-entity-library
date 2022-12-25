/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2022  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.auth;

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
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "privilege")
@NamedQueries({
    @NamedQuery(name = "findAllPrivileges", query = "SELECT p FROM Privilege p ORDER BY p.name"),
    @NamedQuery(name = "findByPrivilegesName", query = "SELECT p FROM Privilege p WHERE p.name = :name")
})
public class Privilege implements Serializable, PrivilegeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean active;
    private String name;
    private String type;
    private String category;
    private String roles;
    private String description;
    // Job privileges
    private Boolean canEnterJob;
    private Boolean canEditJob;
    private Boolean canEditDisabledJobField;
    // Client privileges 
    private Boolean canAddClient;
    private Boolean canDeleteClient;
    // Financial privileges
    private Boolean canEditInvoicingAndPayment;
    private Boolean canApplyTaxesToJobCosting;
    private Boolean canApplyDiscountsToJobCosting;

    @Transient
    private Boolean isDirty;

    public Privilege() {
        init("");
    }

    public Privilege(String name) {
        init(name);
    }

    public static List<Privilege> findActivePrivileges(EntityManager em, String query) {
        try {
            String newQuery = query.toUpperCase().trim().replaceAll("'", "''");

            List<Privilege> privileges = em.createQuery("SELECT p FROM Privilege p"
                    + " WHERE (p.active = 1) AND (UPPER(p.name) like '%"
                    + newQuery + "%'" + " OR UPPER(p.category) like '%"
                    + newQuery + "%'" + " OR UPPER(p.description) like '%"
                    + newQuery + "%') ORDER BY p.name", Privilege.class).getResultList();

            return privileges;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Boolean getActive() {
        if (active == null) {
            active = true;
        }
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    private void init(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public Boolean getCanApplyDiscountsToJobCosting() {
        if (canApplyDiscountsToJobCosting == null) {
            canApplyDiscountsToJobCosting = false;
        }

        return canApplyDiscountsToJobCosting;
    }

    public void setCanApplyDiscountsToJobCosting(Boolean canApplyDiscountsToJobCosting) {
        this.canApplyDiscountsToJobCosting = canApplyDiscountsToJobCosting;
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

    public Boolean getCanEditDisabledJobField() {
        if (canEditDisabledJobField == null) {
            canEditDisabledJobField = false;
        }
        return canEditDisabledJobField;
    }

    public void setCanEditDisabledJobField(Boolean canEditDisabledJobField) {
        this.canEditDisabledJobField = canEditDisabledJobField;
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

    public Boolean getCanDeleteClient() {
        if (canDeleteClient == null) {
            canDeleteClient = false;
        }
        return canDeleteClient;
    }

    public void setCanDeleteClient(Boolean canDeleteClient) {
        this.canDeleteClient = canDeleteClient;
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

    public Boolean getCanEnterJob() {
        if (canEnterJob == null) {
            canEnterJob = false;
        }
        return canEnterJob;
    }

    public void setCanEnterJob(Boolean canEnterJob) {
        this.canEnterJob = canEnterJob;
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
    public String getRoles() {
        return roles;
    }

    @Override
    public void setRoles(String roles) {
        this.roles = roles;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
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

    public static Privilege findActivePrivilegeByName(EntityManager em, String value) {

        try {

            value = value.replaceAll("'", "''").replaceAll("&amp;", "&");

            List<Privilege> privileges = em.createQuery("SELECT p FROM Privilege p "
                    + "WHERE p.active = 1 AND UPPER(p.name) "
                    + "= '" + value.toUpperCase() + "'", Privilege.class).getResultList();
            if (!privileges.isEmpty()) {
                return privileges.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Privilege findPrivilegeByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''").trim();

            List<Privilege> privileges = em.createQuery("SELECT p FROM Privilege p "
                    + "WHERE UPPER(p.name) "
                    + "LIKE '" + newName.toUpperCase() + "%'", Privilege.class).getResultList();

            if (!privileges.isEmpty()) {
                
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
        try {
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Privilege not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
