/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024 D P Bennett & Associates Limited

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
import jm.com.dpbennett.business.entity.cm.Client;
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
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.rm.Form;
import jm.com.dpbennett.business.entity.sm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "samplerequest")
public class SampleRequest implements BusinessEntity, Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @OneToOne(cascade = CascadeType.ALL)
    private Client receivedFrom;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact representative;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee inspector;
    @Column(length = 1024)
    private String comments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductInspection> products;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfRequest;
    @Transient
    private Boolean isDirty;

    public SampleRequest() {
        products = new ArrayList<>();
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

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public List<ProductInspection> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInspection> products) {
        this.products = products;
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
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }

    public Employee getInspector() {
        if (inspector == null) {
            return new Employee();
        }
        return inspector;
    }

    public void setInspector(Employee inspector) {
        this.inspector = inspector;
    }

    public Client getReceivedFrom() {
        if (receivedFrom == null) {
            receivedFrom = new Client("");
        }
        return receivedFrom;
    }

    public void setReceivedFrom(Client receivedFrom) {
        this.receivedFrom = receivedFrom;
    }

    public Contact getRepresentative() {
        if (representative == null) {
            representative = new Contact();
        }
        return representative;
    }

    public void setRepresentative(Contact representative) {
        this.representative = representative;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SampleRequest)) {
            return false;
        }
        SampleRequest other = (SampleRequest) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.SampleRequest[id=" + id + "]";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public static List<SampleRequest> findSampleRequestsByDateSearchField(
            EntityManager em,
            User user,
            String dateSearchField,
            String searchType,
            String searchText,
            Date startDate,
            Date endDate) {

        List<SampleRequest> foundSampleRequests;
        String searchQuery = null;
        String searchTextAndClause = "";
       
        if (searchType.equals("General")) {
            if (!searchText.equals("")) {
                searchTextAndClause =
                        " AND ("
                        + " UPPER(sampleRequest.type) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(inspector.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(inspector.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(receivedFrom.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(sampleRequest.comments) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
            }
            if ((startDate == null) || (endDate == null)) {
                searchQuery =
                        "SELECT sampleRequest FROM SampleRequest sampleRequest"
                        + " JOIN sampleRequest.inspector inspector"
                        + " JOIN sampleRequest.receivedFrom receivedFrom"
                        + " WHERE (0 = 0)" // used as place holder
                        + searchTextAndClause
                        + " ORDER BY sampleRequest.id DESC";
            } else {
                searchQuery =
                        "SELECT sampleRequest FROM SampleRequest sampleRequest"
                        + " JOIN sampleRequest.inspector inspector"
                        + " JOIN sampleRequest.receivedFrom receivedFrom"
                        + " WHERE (sampleRequest." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                        + " AND sampleRequest." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                        + searchTextAndClause
                        + " ORDER BY sampleRequest.id DESC";
            }
        } else if (searchType.equals("?")) {
        }

        try {
            foundSampleRequests = em.createQuery(searchQuery, SampleRequest.class).getResultList();
            if (foundSampleRequests == null) {
                foundSampleRequests = new ArrayList<SampleRequest>();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return foundSampleRequests;
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
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDescription(String description) {
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
}
