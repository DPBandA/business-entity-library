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
@Table(name = "petrolpumpcalibration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petrolpumpcalibration.findAll", query = "SELECT p FROM Petrolpumpcalibration p"),
    @NamedQuery(name = "Petrolpumpcalibration.findById", query = "SELECT p FROM Petrolpumpcalibration p WHERE p.id = :id"),
    @NamedQuery(name = "Petrolpumpcalibration.findByRecalibrationdate", query = "SELECT p FROM Petrolpumpcalibration p WHERE p.recalibrationdate = :recalibrationdate"),
    @NamedQuery(name = "Petrolpumpcalibration.findByHourlyrate", query = "SELECT p FROM Petrolpumpcalibration p WHERE p.hourlyrate = :hourlyrate"),
    @NamedQuery(name = "Petrolpumpcalibration.findByName", query = "SELECT p FROM Petrolpumpcalibration p WHERE p.name = :name"),
    @NamedQuery(name = "Petrolpumpcalibration.findByType", query = "SELECT p FROM Petrolpumpcalibration p WHERE p.type = :type"),
    @NamedQuery(name = "Petrolpumpcalibration.findByCalibrationdate", query = "SELECT p FROM Petrolpumpcalibration p WHERE p.calibrationdate = :calibrationdate")})
public class Petrolpumpcalibration implements Serializable {
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
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "CALIBRATIONDATE")
    @Temporal(TemporalType.DATE)
    private Date calibrationdate;
    @JoinTable(name = "petrolpumpcalibration_petrolpumpcalibrationvalue", joinColumns = {
        @JoinColumn(name = "PetrolPumpCalibration_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "calibrationValues_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Petrolpumpcalibrationvalue> petrolpumpcalibrationvalueList;
    @JoinColumn(name = "CALIBRATIONDONEBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee calibrationdonebyId;

    public Petrolpumpcalibration() {
    }

    public Petrolpumpcalibration(Long id) {
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

    public Date getCalibrationdate() {
        return calibrationdate;
    }

    public void setCalibrationdate(Date calibrationdate) {
        this.calibrationdate = calibrationdate;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpcalibrationvalue> getPetrolpumpcalibrationvalueList() {
        return petrolpumpcalibrationvalueList;
    }

    public void setPetrolpumpcalibrationvalueList(List<Petrolpumpcalibrationvalue> petrolpumpcalibrationvalueList) {
        this.petrolpumpcalibrationvalueList = petrolpumpcalibrationvalueList;
    }

    public Employee getCalibrationdonebyId() {
        return calibrationdonebyId;
    }

    public void setCalibrationdonebyId(Employee calibrationdonebyId) {
        this.calibrationdonebyId = calibrationdonebyId;
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
        if (!(object instanceof Petrolpumpcalibration)) {
            return false;
        }
        Petrolpumpcalibration other = (Petrolpumpcalibration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Petrolpumpcalibration[ id=" + id + " ]";
    }
    
}
