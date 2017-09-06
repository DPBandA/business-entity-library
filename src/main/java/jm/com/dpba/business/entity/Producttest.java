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
@Table(name = "producttest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producttest.findAll", query = "SELECT p FROM Producttest p"),
    @NamedQuery(name = "Producttest.findById", query = "SELECT p FROM Producttest p WHERE p.id = :id"),
    @NamedQuery(name = "Producttest.findByTestdate", query = "SELECT p FROM Producttest p WHERE p.testdate = :testdate"),
    @NamedQuery(name = "Producttest.findByRetestdate", query = "SELECT p FROM Producttest p WHERE p.retestdate = :retestdate"),
    @NamedQuery(name = "Producttest.findByHourlyrate", query = "SELECT p FROM Producttest p WHERE p.hourlyrate = :hourlyrate"),
    @NamedQuery(name = "Producttest.findByCategory", query = "SELECT p FROM Producttest p WHERE p.category = :category"),
    @NamedQuery(name = "Producttest.findByName", query = "SELECT p FROM Producttest p WHERE p.name = :name"),
    @NamedQuery(name = "Producttest.findByType", query = "SELECT p FROM Producttest p WHERE p.type = :type")})
public class Producttest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "TESTDATE")
    @Temporal(TemporalType.DATE)
    private Date testdate;
    @Column(name = "RETESTDATE")
    @Temporal(TemporalType.DATE)
    private Date retestdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HOURLYRATE")
    private Double hourlyrate;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @ManyToMany(mappedBy = "producttestList")
    private List<Jobsample> jobsampleList;
    @JoinColumn(name = "TESTDONEBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee testdonebyId;

    public Producttest() {
    }

    public Producttest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTestdate() {
        return testdate;
    }

    public void setTestdate(Date testdate) {
        this.testdate = testdate;
    }

    public Date getRetestdate() {
        return retestdate;
    }

    public void setRetestdate(Date retestdate) {
        this.retestdate = retestdate;
    }

    public Double getHourlyrate() {
        return hourlyrate;
    }

    public void setHourlyrate(Double hourlyrate) {
        this.hourlyrate = hourlyrate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public List<Jobsample> getJobsampleList() {
        return jobsampleList;
    }

    public void setJobsampleList(List<Jobsample> jobsampleList) {
        this.jobsampleList = jobsampleList;
    }

    public Employee getTestdonebyId() {
        return testdonebyId;
    }

    public void setTestdonebyId(Employee testdonebyId) {
        this.testdonebyId = testdonebyId;
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
        if (!(object instanceof Producttest)) {
            return false;
        }
        Producttest other = (Producttest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Producttest[ id=" + id + " ]";
    }
    
}
