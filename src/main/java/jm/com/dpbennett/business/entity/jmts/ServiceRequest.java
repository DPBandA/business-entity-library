/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.jmts;

import jm.com.dpbennett.business.entity.fm.Sector;
import jm.com.dpbennett.business.entity.fm.JobSubCategory;
import jm.com.dpbennett.business.entity.fm.JobCategory;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.fm.Classification;
import jm.com.dpbennett.business.entity.hrm.BusinessOffice;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.User;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "servicerequest")
@NamedQueries({
    @NamedQuery(name = "findAllServiceRequests", query = "SELECT s FROM ServiceRequest s ORDER BY s.serviceRequestNumber"),
    @NamedQuery(name = "findByServiceRequestNumber", query = "SELECT s FROM ServiceRequest s WHERE s.serviceRequestNumber = :serviceRequestNumber")
})
public class ServiceRequest implements BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String serviceRequestNumber;
    private Boolean autoGenerateServiceRequestNumber;
    private Long serviceRequestSequenceNumber;
    @Column(length = 1024)
    private String comment;
    private Integer estimatedTurnAroundTimeInDays;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Classification classification;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Sector sector;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Department department;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Client client;
    @OneToOne(cascade = CascadeType.REFRESH)
    private JobCategory jobCategory;
    @OneToOne(cascade = CascadeType.REFRESH)
    private JobSubCategory jobSubCategory;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee assignedTo;
    @OneToOne(cascade = CascadeType.REFRESH)
    private ServiceContract serviceContract;
    @OneToOne(cascade = CascadeType.REFRESH)
    private BusinessOffice businessOffice;
    @Column(length = 1024)
    private String jobDescription;
    // tk Add list of services instead. Delete field from database table.
//    @OneToOne(cascade = CascadeType.REFRESH)
//    private Service service;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
    // tracking
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSubmitted;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAndTimeEntered;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee enteredBy;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Employee editedBy;
    @Column(length = 1024)
    private String statusNote;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedDateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfCompletion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateStatusEdited;
    @Column(length = 1024)
    private String purpose;
    @Transient
    private Boolean isDirty;

    public ServiceRequest() {
    }

    public ServiceRequest(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
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

    public ServiceRequest(JobSubCategory jobSubCategory) {
        this.jobSubCategory = jobSubCategory;
    }

    public Integer getYearReceived() {
        Calendar c = Calendar.getInstance();
        Integer year;

        // use the date submitted to get the year if it is valid
        // and only if this is not a subcontracted job
        if (dateSubmitted != null) {
            c.setTime(dateSubmitted);
            year = c.get(Calendar.YEAR);
        } else {
            Date now = new Date();
            c.setTime(now);
            year = c.get(Calendar.YEAR);
        }

        return year;
    }

    public String getServiceRequestNumber() {
        String departmentOrCompanyCode;
        String year;
        String sequenceNumber;

        if ((autoGenerateServiceRequestNumber != null) && (autoGenerateServiceRequestNumber != false)) {
            // include the department code based on parent or subcontract
            if (department != null) { // not a subcontract
                // get the department code based on its id
                if (department.getCode() != null) {
                    departmentOrCompanyCode = department.getCode();
                } else {
                    departmentOrCompanyCode = "?";
                }
            } else {
                departmentOrCompanyCode = "?";
            }

            // use the date submitted to get the year if it is valid
            // and only if this is not a subcontracted job
            year = "" + getYearReceived();

            // include the sequence number if it is valid
            if (serviceRequestSequenceNumber != null) {
                //sequenceNumber = job.getJobSequenceNumber().toString();
                sequenceNumber = BusinessEntityUtils.getIntegerString(serviceRequestSequenceNumber, 4);
            } else {
                sequenceNumber = "?";
            }
            // finally set number
            setServiceRequestNumber("SR/" + departmentOrCompanyCode + "/" + year + "/" + sequenceNumber);
        }

        return serviceRequestNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getDateAndTimeEntered() {
        return dateAndTimeEntered;
    }

    public void setDateAndTimeEntered(Date dateAndTimeEntered) {
        this.dateAndTimeEntered = dateAndTimeEntered;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Date getDateStatusEdited() {
        return dateStatusEdited;
    }

    public void setDateStatusEdited(Date dateStatusEdited) {
        this.dateStatusEdited = dateStatusEdited;
    }

    public Date getDateSubmitted() {
        if (dateSubmitted == null) {
            dateSubmitted = new Date();
        }
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    @Override
    public Person getEditedBy() {
        if (editedBy == null) {
            return new Employee();
        }
        return editedBy;
    }

    @Override
    public void setEditedBy(Person editedBy) {
        this.editedBy = (Employee) editedBy;
    }

    @Override
    public Employee getEnteredBy() {
        if (enteredBy == null) {
            return new Employee();
        }

        return enteredBy;
    }

    public void setEnteredBy(Employee enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Date getExpectedDateOfCompletion() {
        return expectedDateOfCompletion;
    }

    public void setExpectedDateOfCompletion(Date expectedDateOfCompletion) {
        this.expectedDateOfCompletion = expectedDateOfCompletion;
    }

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

//    public List<Service> getServices() {
//        if (services != null) {
//            Collections.sort(services);
//        } else {
//            services = new ArrayList<Service>();
//        }
//
//        return services;
//    }
//
//    public void setServices(List<Service> services) {
//        this.services = services;
//    }
//    public String getServiceRequestNumber() {
//        return serviceRequestNumber;
//    }
    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Classification getClassification() {
        if (classification == null) {
            return new Classification();
        }
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Sector getSector() {
        if (sector == null) {
            return new Sector();
        }
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public BusinessOffice getBusinessOffice() {
        if (businessOffice == null) {
            return new BusinessOffice();
        }
        return businessOffice;
    }

    public void setBusinessOffice(BusinessOffice businessOffice) {
        this.businessOffice = businessOffice;
    }

    public ServiceContract getServiceContract() {
        if (serviceContract == null) {
            return new ServiceContract();
        }
        return serviceContract;
    }

    public void setServiceContract(ServiceContract serviceContract) {
        this.serviceContract = serviceContract;
    }

    public Boolean getAutoGenerateServiceRequestNumber() {
        return autoGenerateServiceRequestNumber;
    }

    public void setAutoGenerateServiceRequestNumber(Boolean autoGenerateServiceRequestNumber) {
        this.autoGenerateServiceRequestNumber = autoGenerateServiceRequestNumber;
    }

    public Client getClient() {
        if (client == null) {
            return new Client("");
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Department getDepartment() {
        if (department == null) {
            return new Department("");
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getAssignedTo() {
        if (assignedTo == null) {
            return new Employee();
        }
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public JobCategory getJobCategory() {
        if (jobCategory == null) {
            return new JobCategory();
        }
        return jobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    public JobSubCategory getJobSubCategory() {
        if (jobSubCategory == null) {
            return new JobSubCategory();
        }
        return jobSubCategory;
    }

    public void setJobSubCategory(JobSubCategory jobSubCategory) {
        this.jobSubCategory = jobSubCategory;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getEstimatedTurnAroundTimeInDays() {
        return estimatedTurnAroundTimeInDays;
    }

    public void setEstimatedTurnAroundTimeInDays(Integer estimatedTurnAroundTimeInDays) {
        this.estimatedTurnAroundTimeInDays = estimatedTurnAroundTimeInDays;
    }

    public Long getServiceRequestSequenceNumber() {
        return serviceRequestSequenceNumber;
    }

    public void setServiceRequestSequenceNumber(Long serviceRequestSequenceNumber) {
        this.serviceRequestSequenceNumber = serviceRequestSequenceNumber;
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
        if (!(object instanceof ServiceRequest)) {
            return false;
        }
        ServiceRequest other = (ServiceRequest) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        if ((serviceRequestNumber != null) && (client != null)) {
            return serviceRequestNumber + " (" + client.getName() + ")";
        } else {
            return "";
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static List<ServiceRequest> findServiceRequestsByDateSearchField(
            EntityManager em,
            User user,
            String dateSearchField,
            String searchType,
            String searchText,
            Date startDate,
            Date endDate,
            Boolean includeSampleSearch) {

        List<ServiceRequest> requests;
        searchText = searchText.replaceAll("&amp;", "&").replaceAll("'", "`");
        String searchQuery = null;
        String searchTextAndClause = "";

        if (searchType.equals("General")) {

            if (!searchText.equals("")) {
                searchTextAndClause
                        = " AND ("
                        + " UPPER(department.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(serviceRequest.serviceRequestNumber) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(serviceRequest.comment) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(serviceRequest.jobDescription) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(serviceRequest.statusNote) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(serviceRequest.purpose) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(classification.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(sector.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(client.name) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobCategory.category) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(jobSubCategory.subCategory) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.firstName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " OR UPPER(assignedTo.lastName) LIKE '%" + searchText.toUpperCase() + "%'"
                        + " )";
            }
            searchQuery
                    = "SELECT serviceRequest FROM ServiceRequest serviceRequest"
                    + " JOIN serviceRequest.department department"
                    + " JOIN serviceRequest.classification classification"
                    + " JOIN serviceRequest.sector sector"
                    + " JOIN serviceRequest.client client"
                    + " JOIN serviceRequest.jobCategory jobCategory"
                    + " JOIN serviceRequest.jobSubCategory jobSubCategory"
                    + " JOIN serviceRequest.assignedTo assignedTo"
                    + " WHERE (serviceRequest." + dateSearchField + " >= " + BusinessEntityUtils.getDateString(startDate, "'", "YMD", "-")
                    + " AND serviceRequest." + dateSearchField + " <= " + BusinessEntityUtils.getDateString(endDate, "'", "YMD", "-") + ")"
                    + searchTextAndClause
                    + " ORDER BY serviceRequest.serviceRequestSequenceNumber DESC";
        }

        try {
            requests = em.createQuery(searchQuery, ServiceRequest.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return requests;
    }

    public static ServiceRequest findServiceRequestByServiceRequestNumber(
            EntityManager em, String value) {

        try {
            
            value = value.replaceAll("&amp;", "&").replaceAll("'", "`");
            
            List<ServiceRequest> requests = em.createQuery("SELECT s FROM ServiceRequest s "
                    + "WHERE UPPER(s.serviceRequestNumber) "
                    + "= '" + value.toUpperCase() + "'", ServiceRequest.class).getResultList();
            if (!requests.isEmpty()) {
                return requests.get(0);
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
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(String type) {
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
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
