/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "factoryinspection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factoryinspection.findAll", query = "SELECT f FROM Factoryinspection f")
    , @NamedQuery(name = "Factoryinspection.findById", query = "SELECT f FROM Factoryinspection f WHERE f.id = :id")
    , @NamedQuery(name = "Factoryinspection.findByInspectiontype", query = "SELECT f FROM Factoryinspection f WHERE f.inspectiontype = :inspectiontype")
    , @NamedQuery(name = "Factoryinspection.findByInspectiondate", query = "SELECT f FROM Factoryinspection f WHERE f.inspectiondate = :inspectiondate")
    , @NamedQuery(name = "Factoryinspection.findByInspectionstarttime", query = "SELECT f FROM Factoryinspection f WHERE f.inspectionstarttime = :inspectionstarttime")
    , @NamedQuery(name = "Factoryinspection.findByWorkinprogress", query = "SELECT f FROM Factoryinspection f WHERE f.workinprogress = :workinprogress")
    , @NamedQuery(name = "Factoryinspection.findByActionstaken", query = "SELECT f FROM Factoryinspection f WHERE f.actionstaken = :actionstaken")
    , @NamedQuery(name = "Factoryinspection.findByGeneralcomments", query = "SELECT f FROM Factoryinspection f WHERE f.generalcomments = :generalcomments")
    , @NamedQuery(name = "Factoryinspection.findByName", query = "SELECT f FROM Factoryinspection f WHERE f.name = :name")
    , @NamedQuery(name = "Factoryinspection.findByInspectionendtime", query = "SELECT f FROM Factoryinspection f WHERE f.inspectionendtime = :inspectionendtime")
    , @NamedQuery(name = "Factoryinspection.findByMaxdaysforcompliance", query = "SELECT f FROM Factoryinspection f WHERE f.maxdaysforcompliance = :maxdaysforcompliance")})
public class Factoryinspection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "INSPECTIONTYPE")
    private String inspectiontype;
    @Column(name = "INSPECTIONDATE")
    @Temporal(TemporalType.DATE)
    private Date inspectiondate;
    @Column(name = "INSPECTIONSTARTTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inspectionstarttime;
    @Column(name = "WORKINPROGRESS")
    private String workinprogress;
    @Column(name = "ACTIONSTAKEN")
    private String actionstaken;
    @Column(name = "GENERALCOMMENTS")
    private String generalcomments;
    @Column(name = "NAME")
    private String name;
    @Column(name = "INSPECTIONENDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inspectionendtime;
    @Column(name = "MAXDAYSFORCOMPLIANCE")
    private Integer maxdaysforcompliance;
    @ManyToMany(mappedBy = "factoryinspectionList")
    private List<Foodfactory> foodfactoryList;
    @JoinTable(name = "factoryinspection_factoryinspectioncomponent", joinColumns = {
        @JoinColumn(name = "FactoryInspection_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "inspectionComponents_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Factoryinspectioncomponent> factoryinspectioncomponentList;
    @JoinColumn(name = "ASSIGNEDINSPECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee assignedinspectorId;
    @JoinColumn(name = "FACTORYREPRESENTATIVE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact factoryrepresentativeId;
    @JoinColumn(name = "BUSINESSOFFICE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Businessoffice businessofficeId;

    public Factoryinspection() {
    }

    public Factoryinspection(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInspectiontype() {
        return inspectiontype;
    }

    public void setInspectiontype(String inspectiontype) {
        this.inspectiontype = inspectiontype;
    }

    public Date getInspectiondate() {
        return inspectiondate;
    }

    public void setInspectiondate(Date inspectiondate) {
        this.inspectiondate = inspectiondate;
    }

    public Date getInspectionstarttime() {
        return inspectionstarttime;
    }

    public void setInspectionstarttime(Date inspectionstarttime) {
        this.inspectionstarttime = inspectionstarttime;
    }

    public String getWorkinprogress() {
        return workinprogress;
    }

    public void setWorkinprogress(String workinprogress) {
        this.workinprogress = workinprogress;
    }

    public String getActionstaken() {
        return actionstaken;
    }

    public void setActionstaken(String actionstaken) {
        this.actionstaken = actionstaken;
    }

    public String getGeneralcomments() {
        return generalcomments;
    }

    public void setGeneralcomments(String generalcomments) {
        this.generalcomments = generalcomments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInspectionendtime() {
        return inspectionendtime;
    }

    public void setInspectionendtime(Date inspectionendtime) {
        this.inspectionendtime = inspectionendtime;
    }

    public Integer getMaxdaysforcompliance() {
        return maxdaysforcompliance;
    }

    public void setMaxdaysforcompliance(Integer maxdaysforcompliance) {
        this.maxdaysforcompliance = maxdaysforcompliance;
    }

    @XmlTransient
    public List<Foodfactory> getFoodfactoryList() {
        return foodfactoryList;
    }

    public void setFoodfactoryList(List<Foodfactory> foodfactoryList) {
        this.foodfactoryList = foodfactoryList;
    }

    @XmlTransient
    public List<Factoryinspectioncomponent> getFactoryinspectioncomponentList() {
        return factoryinspectioncomponentList;
    }

    public void setFactoryinspectioncomponentList(List<Factoryinspectioncomponent> factoryinspectioncomponentList) {
        this.factoryinspectioncomponentList = factoryinspectioncomponentList;
    }

    public Employee getAssignedinspectorId() {
        return assignedinspectorId;
    }

    public void setAssignedinspectorId(Employee assignedinspectorId) {
        this.assignedinspectorId = assignedinspectorId;
    }

    public Contact getFactoryrepresentativeId() {
        return factoryrepresentativeId;
    }

    public void setFactoryrepresentativeId(Contact factoryrepresentativeId) {
        this.factoryrepresentativeId = factoryrepresentativeId;
    }

    public Businessoffice getBusinessofficeId() {
        return businessofficeId;
    }

    public void setBusinessofficeId(Businessoffice businessofficeId) {
        this.businessofficeId = businessofficeId;
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
        if (!(object instanceof Factoryinspection)) {
            return false;
        }
        Factoryinspection other = (Factoryinspection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Factoryinspection[ id=" + id + " ]";
    }
    
}
