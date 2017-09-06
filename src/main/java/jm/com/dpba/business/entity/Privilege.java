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
@Table(name = "privilege")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p")
    , @NamedQuery(name = "Privilege.findById", query = "SELECT p FROM Privilege p WHERE p.id = :id")
    , @NamedQuery(name = "Privilege.findByCategory", query = "SELECT p FROM Privilege p WHERE p.category = :category")
    , @NamedQuery(name = "Privilege.findByDescription", query = "SELECT p FROM Privilege p WHERE p.description = :description")
    , @NamedQuery(name = "Privilege.findByName", query = "SELECT p FROM Privilege p WHERE p.name = :name")
    , @NamedQuery(name = "Privilege.findByRoles", query = "SELECT p FROM Privilege p WHERE p.roles = :roles")
    , @NamedQuery(name = "Privilege.findByType", query = "SELECT p FROM Privilege p WHERE p.type = :type")
    , @NamedQuery(name = "Privilege.findByCanaddclient", query = "SELECT p FROM Privilege p WHERE p.canaddclient = :canaddclient")
    , @NamedQuery(name = "Privilege.findByCanadddepartment", query = "SELECT p FROM Privilege p WHERE p.canadddepartment = :canadddepartment")
    , @NamedQuery(name = "Privilege.findByCanaddemployee", query = "SELECT p FROM Privilege p WHERE p.canaddemployee = :canaddemployee")
    , @NamedQuery(name = "Privilege.findByCanbejmtsadministrator", query = "SELECT p FROM Privilege p WHERE p.canbejmtsadministrator = :canbejmtsadministrator")
    , @NamedQuery(name = "Privilege.findByCandeleteclient", query = "SELECT p FROM Privilege p WHERE p.candeleteclient = :candeleteclient")
    , @NamedQuery(name = "Privilege.findByCandeletedepartment", query = "SELECT p FROM Privilege p WHERE p.candeletedepartment = :candeletedepartment")
    , @NamedQuery(name = "Privilege.findByCandeleteemployee", query = "SELECT p FROM Privilege p WHERE p.candeleteemployee = :candeleteemployee")
    , @NamedQuery(name = "Privilege.findByCandeletejob", query = "SELECT p FROM Privilege p WHERE p.candeletejob = :candeletejob")
    , @NamedQuery(name = "Privilege.findByCaneditdepartmentjob", query = "SELECT p FROM Privilege p WHERE p.caneditdepartmentjob = :caneditdepartmentjob")
    , @NamedQuery(name = "Privilege.findByCaneditownjob", query = "SELECT p FROM Privilege p WHERE p.caneditownjob = :caneditownjob")
    , @NamedQuery(name = "Privilege.findByCanenterownjob", query = "SELECT p FROM Privilege p WHERE p.canenterownjob = :canenterownjob")
    , @NamedQuery(name = "Privilege.findByCanenterdepartmentjob", query = "SELECT p FROM Privilege p WHERE p.canenterdepartmentjob = :canenterdepartmentjob")
    , @NamedQuery(name = "Privilege.findByCaneditjob", query = "SELECT p FROM Privilege p WHERE p.caneditjob = :caneditjob")
    , @NamedQuery(name = "Privilege.findByCanenterjob", query = "SELECT p FROM Privilege p WHERE p.canenterjob = :canenterjob")
    , @NamedQuery(name = "Privilege.findByCanbesuperuser", query = "SELECT p FROM Privilege p WHERE p.canbesuperuser = :canbesuperuser")
    , @NamedQuery(name = "Privilege.findByCanapprovejobcosting", query = "SELECT p FROM Privilege p WHERE p.canapprovejobcosting = :canapprovejobcosting")
    , @NamedQuery(name = "Privilege.findByCanenterparentjob", query = "SELECT p FROM Privilege p WHERE p.canenterparentjob = :canenterparentjob")
    , @NamedQuery(name = "Privilege.findByCaneditinvoicingandpayment", query = "SELECT p FROM Privilege p WHERE p.caneditinvoicingandpayment = :caneditinvoicingandpayment")
    , @NamedQuery(name = "Privilege.findByCanauthdetentionrequest", query = "SELECT p FROM Privilege p WHERE p.canauthdetentionrequest = :canauthdetentionrequest")
    , @NamedQuery(name = "Privilege.findByCanauthdetentionnotice", query = "SELECT p FROM Privilege p WHERE p.canauthdetentionnotice = :canauthdetentionnotice")
    , @NamedQuery(name = "Privilege.findByCanapprvreleaserequest", query = "SELECT p FROM Privilege p WHERE p.canapprvreleaserequest = :canapprvreleaserequest")
    , @NamedQuery(name = "Privilege.findByCanapplytaxestojobcosting", query = "SELECT p FROM Privilege p WHERE p.canapplytaxestojobcosting = :canapplytaxestojobcosting")
    , @NamedQuery(name = "Privilege.findByCanbefinancialadministrator", query = "SELECT p FROM Privilege p WHERE p.canbefinancialadministrator = :canbefinancialadministrator")})
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ROLES")
    private String roles;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "CANADDCLIENT")
    private Boolean canaddclient;
    @Column(name = "CANADDDEPARTMENT")
    private Boolean canadddepartment;
    @Column(name = "CANADDEMPLOYEE")
    private Boolean canaddemployee;
    @Column(name = "CANBEJMTSADMINISTRATOR")
    private Boolean canbejmtsadministrator;
    @Column(name = "CANDELETECLIENT")
    private Boolean candeleteclient;
    @Column(name = "CANDELETEDEPARTMENT")
    private Boolean candeletedepartment;
    @Column(name = "CANDELETEEMPLOYEE")
    private Boolean candeleteemployee;
    @Column(name = "CANDELETEJOB")
    private Boolean candeletejob;
    @Column(name = "CANEDITDEPARTMENTJOB")
    private Boolean caneditdepartmentjob;
    @Column(name = "CANEDITOWNJOB")
    private Boolean caneditownjob;
    @Column(name = "CANENTEROWNJOB")
    private Boolean canenterownjob;
    @Column(name = "CANENTERDEPARTMENTJOB")
    private Boolean canenterdepartmentjob;
    @Column(name = "CANEDITJOB")
    private Boolean caneditjob;
    @Column(name = "CANENTERJOB")
    private Boolean canenterjob;
    @Column(name = "CANBESUPERUSER")
    private Boolean canbesuperuser;
    @Column(name = "CANAPPROVEJOBCOSTING")
    private Boolean canapprovejobcosting;
    @Column(name = "CANENTERPARENTJOB")
    private Boolean canenterparentjob;
    @Column(name = "CANEDITINVOICINGANDPAYMENT")
    private Boolean caneditinvoicingandpayment;
    @Column(name = "CANAUTHDETENTIONREQUEST")
    private Boolean canauthdetentionrequest;
    @Column(name = "CANAUTHDETENTIONNOTICE")
    private Boolean canauthdetentionnotice;
    @Column(name = "CANAPPRVRELEASEREQUEST")
    private Boolean canapprvreleaserequest;
    @Column(name = "CANAPPLYTAXESTOJOBCOSTING")
    private Boolean canapplytaxestojobcosting;
    @Column(name = "CANBEFINANCIALADMINISTRATOR")
    private Boolean canbefinancialadministrator;
    @OneToMany(mappedBy = "privilegeId")
    private List<Department> departmentList;

    public Privilege() {
    }

    public Privilege(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getCanaddclient() {
        return canaddclient;
    }

    public void setCanaddclient(Boolean canaddclient) {
        this.canaddclient = canaddclient;
    }

    public Boolean getCanadddepartment() {
        return canadddepartment;
    }

    public void setCanadddepartment(Boolean canadddepartment) {
        this.canadddepartment = canadddepartment;
    }

    public Boolean getCanaddemployee() {
        return canaddemployee;
    }

    public void setCanaddemployee(Boolean canaddemployee) {
        this.canaddemployee = canaddemployee;
    }

    public Boolean getCanbejmtsadministrator() {
        return canbejmtsadministrator;
    }

    public void setCanbejmtsadministrator(Boolean canbejmtsadministrator) {
        this.canbejmtsadministrator = canbejmtsadministrator;
    }

    public Boolean getCandeleteclient() {
        return candeleteclient;
    }

    public void setCandeleteclient(Boolean candeleteclient) {
        this.candeleteclient = candeleteclient;
    }

    public Boolean getCandeletedepartment() {
        return candeletedepartment;
    }

    public void setCandeletedepartment(Boolean candeletedepartment) {
        this.candeletedepartment = candeletedepartment;
    }

    public Boolean getCandeleteemployee() {
        return candeleteemployee;
    }

    public void setCandeleteemployee(Boolean candeleteemployee) {
        this.candeleteemployee = candeleteemployee;
    }

    public Boolean getCandeletejob() {
        return candeletejob;
    }

    public void setCandeletejob(Boolean candeletejob) {
        this.candeletejob = candeletejob;
    }

    public Boolean getCaneditdepartmentjob() {
        return caneditdepartmentjob;
    }

    public void setCaneditdepartmentjob(Boolean caneditdepartmentjob) {
        this.caneditdepartmentjob = caneditdepartmentjob;
    }

    public Boolean getCaneditownjob() {
        return caneditownjob;
    }

    public void setCaneditownjob(Boolean caneditownjob) {
        this.caneditownjob = caneditownjob;
    }

    public Boolean getCanenterownjob() {
        return canenterownjob;
    }

    public void setCanenterownjob(Boolean canenterownjob) {
        this.canenterownjob = canenterownjob;
    }

    public Boolean getCanenterdepartmentjob() {
        return canenterdepartmentjob;
    }

    public void setCanenterdepartmentjob(Boolean canenterdepartmentjob) {
        this.canenterdepartmentjob = canenterdepartmentjob;
    }

    public Boolean getCaneditjob() {
        return caneditjob;
    }

    public void setCaneditjob(Boolean caneditjob) {
        this.caneditjob = caneditjob;
    }

    public Boolean getCanenterjob() {
        return canenterjob;
    }

    public void setCanenterjob(Boolean canenterjob) {
        this.canenterjob = canenterjob;
    }

    public Boolean getCanbesuperuser() {
        return canbesuperuser;
    }

    public void setCanbesuperuser(Boolean canbesuperuser) {
        this.canbesuperuser = canbesuperuser;
    }

    public Boolean getCanapprovejobcosting() {
        return canapprovejobcosting;
    }

    public void setCanapprovejobcosting(Boolean canapprovejobcosting) {
        this.canapprovejobcosting = canapprovejobcosting;
    }

    public Boolean getCanenterparentjob() {
        return canenterparentjob;
    }

    public void setCanenterparentjob(Boolean canenterparentjob) {
        this.canenterparentjob = canenterparentjob;
    }

    public Boolean getCaneditinvoicingandpayment() {
        return caneditinvoicingandpayment;
    }

    public void setCaneditinvoicingandpayment(Boolean caneditinvoicingandpayment) {
        this.caneditinvoicingandpayment = caneditinvoicingandpayment;
    }

    public Boolean getCanauthdetentionrequest() {
        return canauthdetentionrequest;
    }

    public void setCanauthdetentionrequest(Boolean canauthdetentionrequest) {
        this.canauthdetentionrequest = canauthdetentionrequest;
    }

    public Boolean getCanauthdetentionnotice() {
        return canauthdetentionnotice;
    }

    public void setCanauthdetentionnotice(Boolean canauthdetentionnotice) {
        this.canauthdetentionnotice = canauthdetentionnotice;
    }

    public Boolean getCanapprvreleaserequest() {
        return canapprvreleaserequest;
    }

    public void setCanapprvreleaserequest(Boolean canapprvreleaserequest) {
        this.canapprvreleaserequest = canapprvreleaserequest;
    }

    public Boolean getCanapplytaxestojobcosting() {
        return canapplytaxestojobcosting;
    }

    public void setCanapplytaxestojobcosting(Boolean canapplytaxestojobcosting) {
        this.canapplytaxestojobcosting = canapplytaxestojobcosting;
    }

    public Boolean getCanbefinancialadministrator() {
        return canbefinancialadministrator;
    }

    public void setCanbefinancialadministrator(Boolean canbefinancialadministrator) {
        this.canbefinancialadministrator = canbefinancialadministrator;
    }

    @XmlTransient
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
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
        return "jm.com.dpba.business.entity.Privilege[ id=" + id + " ]";
    }
    
}
