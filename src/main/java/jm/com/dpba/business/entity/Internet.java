/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "internet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Internet.findAll", query = "SELECT i FROM Internet i")
    , @NamedQuery(name = "Internet.findById", query = "SELECT i FROM Internet i WHERE i.id = :id")
    , @NamedQuery(name = "Internet.findByInstantmessaging2", query = "SELECT i FROM Internet i WHERE i.instantmessaging2 = :instantmessaging2")
    , @NamedQuery(name = "Internet.findByWebsite3", query = "SELECT i FROM Internet i WHERE i.website3 = :website3")
    , @NamedQuery(name = "Internet.findByInstantmessaging1", query = "SELECT i FROM Internet i WHERE i.instantmessaging1 = :instantmessaging1")
    , @NamedQuery(name = "Internet.findByWebsite2", query = "SELECT i FROM Internet i WHERE i.website2 = :website2")
    , @NamedQuery(name = "Internet.findByInstantmessaging3", query = "SELECT i FROM Internet i WHERE i.instantmessaging3 = :instantmessaging3")
    , @NamedQuery(name = "Internet.findByEmail1", query = "SELECT i FROM Internet i WHERE i.email1 = :email1")
    , @NamedQuery(name = "Internet.findByWebsite1", query = "SELECT i FROM Internet i WHERE i.website1 = :website1")
    , @NamedQuery(name = "Internet.findByEmail2", query = "SELECT i FROM Internet i WHERE i.email2 = :email2")
    , @NamedQuery(name = "Internet.findByEmail3", query = "SELECT i FROM Internet i WHERE i.email3 = :email3")})
public class Internet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "INSTANTMESSAGING2")
    private String instantmessaging2;
    @Column(name = "WEBSITE3")
    private String website3;
    @Column(name = "INSTANTMESSAGING1")
    private String instantmessaging1;
    @Column(name = "WEBSITE2")
    private String website2;
    @Column(name = "INSTANTMESSAGING3")
    private String instantmessaging3;
    @Column(name = "EMAIL1")
    private String email1;
    @Column(name = "WEBSITE1")
    private String website1;
    @Column(name = "EMAIL2")
    private String email2;
    @Column(name = "EMAIL3")
    private String email3;
    @OneToMany(mappedBy = "internetId")
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "internetId")
    private List<Contact> contactList;
    @OneToMany(mappedBy = "internetId")
    private List<Businessoffice> businessofficeList;
    @OneToMany(mappedBy = "internetId")
    private List<Client> clientList;
    @OneToMany(mappedBy = "internetId")
    private List<Department> departmentList;
    @OneToMany(mappedBy = "internetId")
    private List<Foodfactory> foodfactoryList;

    public Internet() {
    }

    public Internet(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstantmessaging2() {
        return instantmessaging2;
    }

    public void setInstantmessaging2(String instantmessaging2) {
        this.instantmessaging2 = instantmessaging2;
    }

    public String getWebsite3() {
        return website3;
    }

    public void setWebsite3(String website3) {
        this.website3 = website3;
    }

    public String getInstantmessaging1() {
        return instantmessaging1;
    }

    public void setInstantmessaging1(String instantmessaging1) {
        this.instantmessaging1 = instantmessaging1;
    }

    public String getWebsite2() {
        return website2;
    }

    public void setWebsite2(String website2) {
        this.website2 = website2;
    }

    public String getInstantmessaging3() {
        return instantmessaging3;
    }

    public void setInstantmessaging3(String instantmessaging3) {
        this.instantmessaging3 = instantmessaging3;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getWebsite1() {
        return website1;
    }

    public void setWebsite1(String website1) {
        this.website1 = website1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @XmlTransient
    public List<Businessoffice> getBusinessofficeList() {
        return businessofficeList;
    }

    public void setBusinessofficeList(List<Businessoffice> businessofficeList) {
        this.businessofficeList = businessofficeList;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @XmlTransient
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @XmlTransient
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
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
        if (!(object instanceof Internet)) {
            return false;
        }
        Internet other = (Internet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Internet[ id=" + id + " ]";
    }
    
}
