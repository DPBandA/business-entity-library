/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "costcomponent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Costcomponent.findAll", query = "SELECT c FROM Costcomponent c"),
    @NamedQuery(name = "Costcomponent.findById", query = "SELECT c FROM Costcomponent c WHERE c.id = :id"),
    @NamedQuery(name = "Costcomponent.findByCategory", query = "SELECT c FROM Costcomponent c WHERE c.category = :category"),
    @NamedQuery(name = "Costcomponent.findByRate", query = "SELECT c FROM Costcomponent c WHERE c.rate = :rate"),
    @NamedQuery(name = "Costcomponent.findByHours", query = "SELECT c FROM Costcomponent c WHERE c.hours = :hours"),
    @NamedQuery(name = "Costcomponent.findByName", query = "SELECT c FROM Costcomponent c WHERE c.name = :name"),
    @NamedQuery(name = "Costcomponent.findByIsheading", query = "SELECT c FROM Costcomponent c WHERE c.isheading = :isheading"),
    @NamedQuery(name = "Costcomponent.findByCode", query = "SELECT c FROM Costcomponent c WHERE c.code = :code"),
    @NamedQuery(name = "Costcomponent.findByHoursorquantity", query = "SELECT c FROM Costcomponent c WHERE c.hoursorquantity = :hoursorquantity"),
    @NamedQuery(name = "Costcomponent.findByCost", query = "SELECT c FROM Costcomponent c WHERE c.cost = :cost"),
    @NamedQuery(name = "Costcomponent.findByComments", query = "SELECT c FROM Costcomponent c WHERE c.comments = :comments"),
    @NamedQuery(name = "Costcomponent.findByIsfixedcost", query = "SELECT c FROM Costcomponent c WHERE c.isfixedcost = :isfixedcost"),
    @NamedQuery(name = "Costcomponent.findByIseditable", query = "SELECT c FROM Costcomponent c WHERE c.iseditable = :iseditable")})
public class Costcomponent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RATE")
    private Double rate;
    @Column(name = "HOURS")
    private Double hours;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "ISHEADING")
    private Boolean isheading;
    @Size(max = 255)
    @Column(name = "CODE")
    private String code;
    @Column(name = "HOURSORQUANTITY")
    private Double hoursorquantity;
    @Column(name = "COST")
    private Double cost;
    @Size(max = 255)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "ISFIXEDCOST")
    private Boolean isfixedcost;
    @Column(name = "ISEDITABLE")
    private Boolean iseditable;
    @ManyToMany(mappedBy = "costcomponentList")
    private List<Jobcostingandpayment> jobcostingandpaymentList;
    @ManyToMany(mappedBy = "costcomponentList")
    private List<Jobcosting> jobcostingList;

    public Costcomponent() {
    }

    public Costcomponent(Long id) {
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsheading() {
        return isheading;
    }

    public void setIsheading(Boolean isheading) {
        this.isheading = isheading;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getHoursorquantity() {
        return hoursorquantity;
    }

    public void setHoursorquantity(Double hoursorquantity) {
        this.hoursorquantity = hoursorquantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsfixedcost() {
        return isfixedcost;
    }

    public void setIsfixedcost(Boolean isfixedcost) {
        this.isfixedcost = isfixedcost;
    }

    public Boolean getIseditable() {
        return iseditable;
    }

    public void setIseditable(Boolean iseditable) {
        this.iseditable = iseditable;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobcostingandpayment> getJobcostingandpaymentList() {
        return jobcostingandpaymentList;
    }

    public void setJobcostingandpaymentList(List<Jobcostingandpayment> jobcostingandpaymentList) {
        this.jobcostingandpaymentList = jobcostingandpaymentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobcosting> getJobcostingList() {
        return jobcostingList;
    }

    public void setJobcostingList(List<Jobcosting> jobcostingList) {
        this.jobcostingList = jobcostingList;
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
        if (!(object instanceof Costcomponent)) {
            return false;
        }
        Costcomponent other = (Costcomponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Costcomponent[ id=" + id + " ]";
    }
    
}
