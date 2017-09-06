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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "petrolpumpnozzlecalibration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findAll", query = "SELECT p FROM Petrolpumpnozzlecalibration p"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findById", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.id = :id"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByRecalibrationdate", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.recalibrationdate = :recalibrationdate"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByHourlyrate", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.hourlyrate = :hourlyrate"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByPetrolpricerate", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.petrolpricerate = :petrolpricerate"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByTotalizerend", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.totalizerend = :totalizerend"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByTotalizerstart", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.totalizerstart = :totalizerstart"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByCalibrationdate", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.calibrationdate = :calibrationdate"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByType", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.type = :type"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByActualpetrolusage", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.actualpetrolusage = :actualpetrolusage"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByDateandtimerecorded", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.dateandtimerecorded = :dateandtimerecorded"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByResults", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.results = :results"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByName", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.name = :name"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByProductdispensed", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.productdispensed = :productdispensed"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findBySetpetrolusage", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.setpetrolusage = :setpetrolusage"),
    @NamedQuery(name = "Petrolpumpnozzlecalibration.findByPetrolcost", query = "SELECT p FROM Petrolpumpnozzlecalibration p WHERE p.petrolcost = :petrolcost")})
public class Petrolpumpnozzlecalibration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "RECALIBRATIONDATE")
    @Temporal(TemporalType.DATE)
    private Date recalibrationdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HOURLYRATE")
    private Double hourlyrate;
    @Column(name = "PETROLPRICERATE")
    private Double petrolpricerate;
    @Column(name = "TOTALIZEREND")
    private Double totalizerend;
    @Column(name = "TOTALIZERSTART")
    private Double totalizerstart;
    @Column(name = "CALIBRATIONDATE")
    @Temporal(TemporalType.DATE)
    private Date calibrationdate;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "ACTUALPETROLUSAGE")
    private Double actualpetrolusage;
    @Column(name = "DATEANDTIMERECORDED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateandtimerecorded;
    @Size(max = 255)
    @Column(name = "RESULTS")
    private String results;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "PRODUCTDISPENSED")
    private String productdispensed;
    @Column(name = "SETPETROLUSAGE")
    private Double setpetrolusage;
    @Column(name = "PETROLCOST")
    private Double petrolcost;
    @JoinTable(name = "petrolpumpnozzle_petrolpumpnozzlecalibration", joinColumns = {
        @JoinColumn(name = "calibrations_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PetrolPumpNozzle_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Petrolpumpnozzle> petrolpumpnozzleList;
    @JoinTable(name = "petrolpumpnozzlecalibration_petrolpumpnozzlecalibrationpoint", joinColumns = {
        @JoinColumn(name = "PetrolPumpNozzleCalibration_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "calibrationPoints_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Petrolpumpnozzlecalibrationpoint> petrolpumpnozzlecalibrationpointList;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "ID")
    @ManyToOne
    private Job jobId;
    @JoinColumn(name = "CALIBRATIONDONEBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee calibrationdonebyId;
    @OneToMany(mappedBy = "lastcalibrationId")
    private List<Petrolpumpnozzle> petrolpumpnozzleList1;

    public Petrolpumpnozzlecalibration() {
    }

    public Petrolpumpnozzlecalibration(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecalibrationdate() {
        return recalibrationdate;
    }

    public void setRecalibrationdate(Date recalibrationdate) {
        this.recalibrationdate = recalibrationdate;
    }

    public Double getHourlyrate() {
        return hourlyrate;
    }

    public void setHourlyrate(Double hourlyrate) {
        this.hourlyrate = hourlyrate;
    }

    public Double getPetrolpricerate() {
        return petrolpricerate;
    }

    public void setPetrolpricerate(Double petrolpricerate) {
        this.petrolpricerate = petrolpricerate;
    }

    public Double getTotalizerend() {
        return totalizerend;
    }

    public void setTotalizerend(Double totalizerend) {
        this.totalizerend = totalizerend;
    }

    public Double getTotalizerstart() {
        return totalizerstart;
    }

    public void setTotalizerstart(Double totalizerstart) {
        this.totalizerstart = totalizerstart;
    }

    public Date getCalibrationdate() {
        return calibrationdate;
    }

    public void setCalibrationdate(Date calibrationdate) {
        this.calibrationdate = calibrationdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getActualpetrolusage() {
        return actualpetrolusage;
    }

    public void setActualpetrolusage(Double actualpetrolusage) {
        this.actualpetrolusage = actualpetrolusage;
    }

    public Date getDateandtimerecorded() {
        return dateandtimerecorded;
    }

    public void setDateandtimerecorded(Date dateandtimerecorded) {
        this.dateandtimerecorded = dateandtimerecorded;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductdispensed() {
        return productdispensed;
    }

    public void setProductdispensed(String productdispensed) {
        this.productdispensed = productdispensed;
    }

    public Double getSetpetrolusage() {
        return setpetrolusage;
    }

    public void setSetpetrolusage(Double setpetrolusage) {
        this.setpetrolusage = setpetrolusage;
    }

    public Double getPetrolcost() {
        return petrolcost;
    }

    public void setPetrolcost(Double petrolcost) {
        this.petrolcost = petrolcost;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzle> getPetrolpumpnozzleList() {
        return petrolpumpnozzleList;
    }

    public void setPetrolpumpnozzleList(List<Petrolpumpnozzle> petrolpumpnozzleList) {
        this.petrolpumpnozzleList = petrolpumpnozzleList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzlecalibrationpoint> getPetrolpumpnozzlecalibrationpointList() {
        return petrolpumpnozzlecalibrationpointList;
    }

    public void setPetrolpumpnozzlecalibrationpointList(List<Petrolpumpnozzlecalibrationpoint> petrolpumpnozzlecalibrationpointList) {
        this.petrolpumpnozzlecalibrationpointList = petrolpumpnozzlecalibrationpointList;
    }

    public Job getJobId() {
        return jobId;
    }

    public void setJobId(Job jobId) {
        this.jobId = jobId;
    }

    public Employee getCalibrationdonebyId() {
        return calibrationdonebyId;
    }

    public void setCalibrationdonebyId(Employee calibrationdonebyId) {
        this.calibrationdonebyId = calibrationdonebyId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzle> getPetrolpumpnozzleList1() {
        return petrolpumpnozzleList1;
    }

    public void setPetrolpumpnozzleList1(List<Petrolpumpnozzle> petrolpumpnozzleList1) {
        this.petrolpumpnozzleList1 = petrolpumpnozzleList1;
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
        if (!(object instanceof Petrolpumpnozzlecalibration)) {
            return false;
        }
        Petrolpumpnozzlecalibration other = (Petrolpumpnozzlecalibration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Petrolpumpnozzlecalibration[ id=" + id + " ]";
    }
    
}
