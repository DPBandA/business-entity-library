/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "marketproductsurvey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marketproductsurvey.findAll", query = "SELECT m FROM Marketproductsurvey m"),
    @NamedQuery(name = "Marketproductsurvey.findById", query = "SELECT m FROM Marketproductsurvey m WHERE m.id = :id"),
    @NamedQuery(name = "Marketproductsurvey.findBySurveystarttime", query = "SELECT m FROM Marketproductsurvey m WHERE m.surveystarttime = :surveystarttime"),
    @NamedQuery(name = "Marketproductsurvey.findByPortofentry", query = "SELECT m FROM Marketproductsurvey m WHERE m.portofentry = :portofentry"),
    @NamedQuery(name = "Marketproductsurvey.findBySurveytype", query = "SELECT m FROM Marketproductsurvey m WHERE m.surveytype = :surveytype"),
    @NamedQuery(name = "Marketproductsurvey.findByDateofsurvey", query = "SELECT m FROM Marketproductsurvey m WHERE m.dateofsurvey = :dateofsurvey"),
    @NamedQuery(name = "Marketproductsurvey.findByDatesigned", query = "SELECT m FROM Marketproductsurvey m WHERE m.datesigned = :datesigned"),
    @NamedQuery(name = "Marketproductsurvey.findByContainer", query = "SELECT m FROM Marketproductsurvey m WHERE m.container = :container"),
    @NamedQuery(name = "Marketproductsurvey.findByName", query = "SELECT m FROM Marketproductsurvey m WHERE m.name = :name"),
    @NamedQuery(name = "Marketproductsurvey.findBySurveyendtime", query = "SELECT m FROM Marketproductsurvey m WHERE m.surveyendtime = :surveyendtime"),
    @NamedQuery(name = "Marketproductsurvey.findByComments", query = "SELECT m FROM Marketproductsurvey m WHERE m.comments = :comments")})
public class Marketproductsurvey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "SURVEYSTARTTIME")
    @Temporal(TemporalType.TIME)
    private Date surveystarttime;
    @Size(max = 255)
    @Column(name = "PORTOFENTRY")
    private String portofentry;
    @Size(max = 255)
    @Column(name = "SURVEYTYPE")
    private String surveytype;
    @Column(name = "DATEOFSURVEY")
    @Temporal(TemporalType.DATE)
    private Date dateofsurvey;
    @Column(name = "DATESIGNED")
    @Temporal(TemporalType.DATE)
    private Date datesigned;
    @Size(max = 255)
    @Column(name = "CONTAINER")
    private String container;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURVEYENDTIME")
    @Temporal(TemporalType.TIME)
    private Date surveyendtime;
    @Size(max = 1024)
    @Column(name = "COMMENTS")
    private String comments;
    @ManyToMany(mappedBy = "marketproductsurveyList")
    private List<Marketproduct> marketproductList;
    @ManyToMany(mappedBy = "marketproductsurveyList")
    private List<Productinspection> productinspectionList;
    @JoinColumn(name = "INSPECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee inspectorId;
    @JoinColumn(name = "RETAILOUTLET_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client retailoutletId;
    @JoinColumn(name = "RETAILREPRESENTATIVE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact retailrepresentativeId;

    public Marketproductsurvey() {
    }

    public Marketproductsurvey(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSurveystarttime() {
        return surveystarttime;
    }

    public void setSurveystarttime(Date surveystarttime) {
        this.surveystarttime = surveystarttime;
    }

    public String getPortofentry() {
        return portofentry;
    }

    public void setPortofentry(String portofentry) {
        this.portofentry = portofentry;
    }

    public String getSurveytype() {
        return surveytype;
    }

    public void setSurveytype(String surveytype) {
        this.surveytype = surveytype;
    }

    public Date getDateofsurvey() {
        return dateofsurvey;
    }

    public void setDateofsurvey(Date dateofsurvey) {
        this.dateofsurvey = dateofsurvey;
    }

    public Date getDatesigned() {
        return datesigned;
    }

    public void setDatesigned(Date datesigned) {
        this.datesigned = datesigned;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSurveyendtime() {
        return surveyendtime;
    }

    public void setSurveyendtime(Date surveyendtime) {
        this.surveyendtime = surveyendtime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @XmlTransient
    @JsonIgnore
    public List<Marketproduct> getMarketproductList() {
        return marketproductList;
    }

    public void setMarketproductList(List<Marketproduct> marketproductList) {
        this.marketproductList = marketproductList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Productinspection> getProductinspectionList() {
        return productinspectionList;
    }

    public void setProductinspectionList(List<Productinspection> productinspectionList) {
        this.productinspectionList = productinspectionList;
    }

    public Employee getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Employee inspectorId) {
        this.inspectorId = inspectorId;
    }

    public Client getRetailoutletId() {
        return retailoutletId;
    }

    public void setRetailoutletId(Client retailoutletId) {
        this.retailoutletId = retailoutletId;
    }

    public Contact getRetailrepresentativeId() {
        return retailrepresentativeId;
    }

    public void setRetailrepresentativeId(Contact retailrepresentativeId) {
        this.retailrepresentativeId = retailrepresentativeId;
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
        if (!(object instanceof Marketproductsurvey)) {
            return false;
        }
        Marketproductsurvey other = (Marketproductsurvey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Marketproductsurvey[ id=" + id + " ]";
    }
    
}
