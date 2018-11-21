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
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
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
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.utils.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "foodfactory")
public class FoodFactory implements Customer, BusinessEntity, Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contact> contacts;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastAccessed;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFirstReceived;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRegistered;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastVisited;
    private String number;
    private String code;
    private String type;
    private String notes;
    @OneToOne(cascade = CascadeType.ALL)
    private Internet internet;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignedInspector;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FoodProduct> products;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FactoryInspection> inspections;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Registration> registrations;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Certification> certifications;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice assignedBusinessOffice;
    @Column(length = 1024)
    private String purpose;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    private String taxRegistrationNumber;
    @Transient
    private Boolean isDirty;

    public FoodFactory() {
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        products = new ArrayList<>();
        registrations = new ArrayList<>();
        certifications = new ArrayList<>();
        inspections = new ArrayList<>();
    }

    public FoodFactory(String name) {
        this.name = name;
        contacts = new ArrayList<>();
        addresses = new ArrayList<>();
        products = new ArrayList<>();
        registrations = new ArrayList<>();
        certifications = new ArrayList<>();
        inspections = new ArrayList<>();
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

    /**
     * Get all the entities contained in lists in this class
     *
     * @return
     */
    public List<BusinessEntity> getAllBusinessEntitiesLists() {
        ArrayList<BusinessEntity> entities = new ArrayList<>();

        entities.addAll(contacts);
        entities.addAll(addresses);
        entities.addAll(products);
        entities.addAll(registrations);
        entities.addAll(certifications);
        entities.addAll(inspections);

        for (FactoryInspection inspection : getInspections()) {
            entities.addAll(inspection.getAllBusinessEntitiesLists());
        }

        return entities;
    }

    public List<FactoryInspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<FactoryInspection> inspections) {
        this.inspections = inspections;
    }

    public Employee getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Employee editedBy) {
        this.editedBy = editedBy;
    }

    public List<Certification> getCertifications() {
        Collections.sort(certifications);

        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public Date getDateLastVisited() {
        return dateLastVisited;
    }

    public void setDateLastVisited(Date dateLastVisited) {
        this.dateLastVisited = dateLastVisited;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BusinessOffice getAssignedBusinessOffice() {
        if (assignedBusinessOffice == null) {
            return new BusinessOffice("");
        }
        return assignedBusinessOffice;
    }

    public void setAssignedBusinessOffice(BusinessOffice assignedBusinessOffice) {
        this.assignedBusinessOffice = assignedBusinessOffice;
    }

    public Employee getAssignedInspector() {
        if (assignedInspector == null) {
            return new Employee();
        }
        return assignedInspector;
    }

    public void setAssignedInspector(Employee assignedInspector) {
        this.assignedInspector = assignedInspector;
    }

    public Internet getInternet() {
        if (internet == null) {
            internet = new Internet();
        }
        return internet;
    }

    public void setInternet(Internet internet) {
        this.internet = internet;
    }

    public List<Registration> getRegistrations() {
        Collections.sort(registrations);

        return registrations;
    }

    public Date getLastRegistationDate() {
        if (!registrations.isEmpty()) {
            return getRegistrations().get(0).getDateRegistered();
        } else {
            return null;
        }
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public List<FoodProduct> getProducts() {
        Collections.sort(products);

        return products;
    }

    public void setProducts(List<FoodProduct> products) {
        this.products = products;
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
        if (!(object instanceof FoodFactory)) {
            return false;
        }
        FoodFactory other = (FoodFactory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.FoodFactory[id=" + id + "]";
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
    public List<Address> getAddresses() {
        Collections.sort(addresses);

        return addresses;
    }

    @Override
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public List<Contact> getContacts() {
        Collections.sort(contacts);

        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Address getDefaultAddress() {
        if (!addresses.isEmpty()) {
            return addresses.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Date getDateLastAccessed() {
        return dateLastAccessed;
    }

    @Override
    public void setDateLastAccessed(Date dateLastAccessed) {
        this.dateLastAccessed = dateLastAccessed;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
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
    public Date getDateFirstReceived() {
        return dateFirstReceived;
    }

    @Override
    public void setDateFirstReceived(Date dateFirstReceived) {
        this.dateFirstReceived = dateFirstReceived;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int compareTo(Object o) {
//        return Collator.getInstance().compare(this.toString(), o.toString());
        if ((((FoodFactory) o).getLastRegistationDate() != null) && (this.getLastRegistationDate() != null)) {
            return Collator.getInstance().compare(
                    new Long(((FoodFactory) o).getLastRegistationDate().getTime()).toString(),
                    new Long(this.getLastRegistationDate().getTime()).toString());
        } else {
            return 0;
        }
    }

    @Override
    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    @Override
    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public static List<FoodFactory> findFoodFactoriesByDateSearchField(
            EntityManager em,
            JobManagerUser user,
            String dateSearchField,
            String searchType,
            String originalSearchText,
            Date startDate,
            Date endDate) {

        List<FoodFactory> foundFactories;
        String searchQuery = null;
        String searchTextAndClause = "";
        String searchText;

        if (originalSearchText != null) {
            searchText = originalSearchText.replaceAll("'", "''");
        } else {
            searchText = "";
        }

        if (searchType.equals("General")) {
            if (!searchText.equals("")) {
                searchTextAndClause =
                        " AND ("
                        + " UPPER(factory.number) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedBusinessOffice.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedInspector.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedInspector.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(factory.code) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(factory.type) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(factory.notes) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(factory.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
            }
            if ((startDate == null) || (endDate == null)) {
                searchQuery =
                        "SELECT factory FROM FoodFactory factory"
                        + " JOIN factory.assignedBusinessOffice assignedBusinessOffice"
                        + " JOIN factory.assignedInspector assignedInspector"
                        + " WHERE (0 = 0)" // used as place holder
                        + searchTextAndClause
                        + " ORDER BY factory.id DESC";
            } else {
                searchQuery =
                        "SELECT factory FROM FoodFactory factory"
                        + " JOIN factory.assignedBusinessOffice assignedBusinessOffice"
                        + " JOIN factory.assignedInspector assignedInspector"
                        + " WHERE (factory." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND factory." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY factory.id DESC";
            }
        } else if (searchType.equals("?")) {
        }

        try {
            foundFactories = em.createQuery(searchQuery, FoodFactory.class).getResultList();
            if (foundFactories == null) {
                foundFactories = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundFactories;
    }

    public static FoodFactory findFoodFactoryById(EntityManager em, Long Id) {
        return em.find(FoodFactory.class, Id);
    }

    public static FoodFactory findFoodFactoryByName(EntityManager em, String name) {

        try {
            String newName = name.trim().replaceAll("'", "''");

            List<FoodFactory> factories = em.createQuery("SELECT f FROM FoodFactory f "
                    + "WHERE UPPER(f.name) "
                    + "= '" + newName.toUpperCase() + "'", FoodFactory.class).getResultList();

            if (!factories.isEmpty()) {
                return factories.get(0);
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

    @Override
    public Contact getDefaultContact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
