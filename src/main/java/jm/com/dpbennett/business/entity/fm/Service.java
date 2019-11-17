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
package jm.com.dpbennett.business.entity.fm;

import jm.com.dpbennett.business.entity.fm.AccountingCode;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "service")
@NamedQueries({
    @NamedQuery(name = "findAll", query = "SELECT s FROM Service s ORDER BY s.name")
    ,
    @NamedQuery(name = "findAllActive", query = "SELECT s FROM Service s WHERE s.active = 1 ORDER BY s.name")
})
public class Service implements Serializable, BusinessEntity, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Boolean active;
    private Boolean internal;
    private String category;
    @Column(length = 1024)
    private String description;
    @Transient
    private Boolean isDirty;
    @OneToOne(cascade = CascadeType.REFRESH)
    private AccountingCode accountingCode;

    public Service() {
        name = "";
        active = true;
        internal = false;
        category = "";
        description = "";
        isDirty = false;
    }

    public Service(String name) {
        this.name = name;
        active = true;
        internal = false;
        category = "";
        description = "";
        isDirty = false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public AccountingCode getAccountingCode() {
        return accountingCode;
    }

    public void setAccountingCode(AccountingCode accountingCode) {
        this.accountingCode = accountingCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUsable() {
        if (getActive()) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
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
        if (name == null) {
            name = "";
        }
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return Collator.getInstance().compare(this.name, ((Service) o).name);
    }

    public static List<Service> findAll(EntityManager em) {

        try {
            List<Service> services = em.createNamedQuery("findAll", Service.class).getResultList();

            return services;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Service> findAllActive(EntityManager em) {

        try {
            List<Service> services = em.createNamedQuery("findAllActive", Service.class).getResultList();

            return services;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

    public static Service findById(EntityManager em, Long id) {

        try {
            Service service = em.find(Service.class, id);

            return service;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

    public static Service findByName(EntityManager em, String serviceName) {

        try {
            String newServiceName = serviceName.trim().replaceAll("'", "''");

            List<Service> services = em.createQuery(
                    "SELECT s FROM Service s "
                    + "WHERE UPPER(s.name) "
                    + "LIKE '%" + newServiceName.toUpperCase() + "%'", 
                    Service.class).getResultList();
            if (services.size() > 0) {
                return services.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Service findActiveByName(EntityManager em, String serviceName) {

        try {
            String newServiceName = serviceName.trim().replaceAll("'", "''");

            List<Service> services = em.createQuery(
                    "SELECT s FROM Service s "
                    + "WHERE UPPER(s.name) "
                    + "LIKE '%" + newServiceName.toUpperCase() + "%' AND s.active = 1", 
                    Service.class).getResultList();
            if (services.size() > 0) {
                return services.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Service findActiveByExactName(EntityManager em, String serviceName) {

        try {
            String newServiceName = serviceName.trim().replaceAll("'", "''");

            List<Service> services = em.createQuery(
                    "SELECT s FROM Service s "
                    + "WHERE UPPER(s.name) "
                    + "= '" + newServiceName.toUpperCase() + "' AND s.active = 1", 
                    Service.class).getResultList();
            if (services.size() > 0) {
                return services.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Service findByNameAndAccountingCode(
            EntityManager em,
            String serviceName,
            String code) {

        try {
            String newServiceName = serviceName.trim().replaceAll("'", "''");

            List<Service> services = em.createQuery(
                    "SELECT s FROM Service s"
                    + " JOIN s.accountingCode accountingCode"
                    + " WHERE UPPER(s.name)"
                    + " LIKE '%" + newServiceName.toUpperCase() + "%'"
                    + " AND accountingCode.code LIKE '%" + code + "%'",
                    Service.class).getResultList();

            if (services.size() > 0) {
                return services.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Service findActiveByNameAndAccountingCode(
            EntityManager em,
            String serviceName,
            String code) {

        try {
            String newServiceName = serviceName.trim().replaceAll("'", "''");

            List<Service> services = em.createQuery(
                    "SELECT s FROM Service s"
                    + " JOIN s.accountingCode accountingCode"
                    + " WHERE UPPER(s.name)"
                    + " LIKE '%" + newServiceName.toUpperCase() + "%'"
                    + " AND accountingCode.code LIKE '%" + code + "%' AND s.active = 1",
                    Service.class).getResultList();

            if (services.size() > 0) {
                return services.get(0);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Service> findAllByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Service> services
                    = em.createQuery("SELECT s FROM Service s WHERE UPPER(s.name) LIKE '%"
                            + newName.toUpperCase().trim() + "%' ORDER BY s.name", Service.class).getResultList();
            return services;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Service> findAllActiveByName(EntityManager em, String name) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Service> services
                    = em.createQuery("SELECT s FROM Service s WHERE UPPER(s.name) LIKE '%"
                            + newName.toUpperCase().trim() + "%' AND s.active = 1 ORDER BY s.name", Service.class).getResultList();
            return services;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Service> findAllActiveByNameAndAccountingCode(
            EntityManager em, 
            String name, 
            String code) {

        try {
            String newName = name.replaceAll("'", "''");

            List<Service> services
                    = em.createQuery("SELECT s FROM Service s"
                            + " JOIN s.accountingCode accountingCode"
                            + " WHERE UPPER(s.name) LIKE '%"
                            + newName.toUpperCase().trim() + "%' AND s.active = 1"
                            + " AND accountingCode.code LIKE '%" + code + "%'" 
                            + " ORDER BY s.name", Service.class).getResultList();
            return services;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
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

        return new ReturnMessage(false, "Service not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }
}
