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
@Table(name = "petrolpumpcalibrationvalue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findAll", query = "SELECT p FROM Petrolpumpcalibrationvalue p"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findById", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.id = :id"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByTotalizerend", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.totalizerend = :totalizerend"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByTotalizerstart", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.totalizerstart = :totalizerstart"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByDaterecorded", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.daterecorded = :daterecorded"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByError4", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.error4 = :error4"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByError5", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.error5 = :error5"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByError6", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.error6 = :error6"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByRate", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.rate = :rate"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByError1", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.error1 = :error1"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByError2", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.error2 = :error2"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByError3", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.error3 = :error3"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByPetrolcost", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.petrolcost = :petrolcost"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByPetrolusage", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.petrolusage = :petrolusage"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByJobnumber", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.jobnumber = :jobnumber"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasure6", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measure6 = :measure6"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasuretolerance3", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measuretolerance3 = :measuretolerance3"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasuretolerance4", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measuretolerance4 = :measuretolerance4"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasure1", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measure1 = :measure1"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasuretolerance5", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measuretolerance5 = :measuretolerance5"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasuretolerance6", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measuretolerance6 = :measuretolerance6"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasure4", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measure4 = :measure4"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasure5", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measure5 = :measure5"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasure2", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measure2 = :measure2"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasuretolerance1", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measuretolerance1 = :measuretolerance1"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasuretolerance2", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measuretolerance2 = :measuretolerance2"),
    @NamedQuery(name = "Petrolpumpcalibrationvalue.findByMeasure3", query = "SELECT p FROM Petrolpumpcalibrationvalue p WHERE p.measure3 = :measure3")})
public class Petrolpumpcalibrationvalue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTALIZEREND")
    private Double totalizerend;
    @Column(name = "TOTALIZERSTART")
    private Double totalizerstart;
    @Column(name = "DATERECORDED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date daterecorded;
    @Column(name = "ERROR4")
    private Double error4;
    @Column(name = "ERROR5")
    private Double error5;
    @Column(name = "ERROR6")
    private Double error6;
    @Column(name = "RATE")
    private Double rate;
    @Column(name = "ERROR1")
    private Double error1;
    @Column(name = "ERROR2")
    private Double error2;
    @Column(name = "ERROR3")
    private Double error3;
    @Column(name = "PETROLCOST")
    private Double petrolcost;
    @Column(name = "PETROLUSAGE")
    private Double petrolusage;
    @Size(max = 255)
    @Column(name = "JOBNUMBER")
    private String jobnumber;
    @Column(name = "MEASURE6")
    private Double measure6;
    @Column(name = "MEASURETOLERANCE3")
    private Double measuretolerance3;
    @Column(name = "MEASURETOLERANCE4")
    private Double measuretolerance4;
    @Column(name = "MEASURE1")
    private Double measure1;
    @Column(name = "MEASURETOLERANCE5")
    private Double measuretolerance5;
    @Column(name = "MEASURETOLERANCE6")
    private Double measuretolerance6;
    @Column(name = "MEASURE4")
    private Double measure4;
    @Column(name = "MEASURE5")
    private Double measure5;
    @Column(name = "MEASURE2")
    private Double measure2;
    @Column(name = "MEASURETOLERANCE1")
    private Double measuretolerance1;
    @Column(name = "MEASURETOLERANCE2")
    private Double measuretolerance2;
    @Column(name = "MEASURE3")
    private Double measure3;
    @ManyToMany(mappedBy = "petrolpumpcalibrationvalueList")
    private List<Petrolpumpcalibration> petrolpumpcalibrationList;
    @JoinColumn(name = "RECORDEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employee recordedbyId;

    public Petrolpumpcalibrationvalue() {
    }

    public Petrolpumpcalibrationvalue(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDaterecorded() {
        return daterecorded;
    }

    public void setDaterecorded(Date daterecorded) {
        this.daterecorded = daterecorded;
    }

    public Double getError4() {
        return error4;
    }

    public void setError4(Double error4) {
        this.error4 = error4;
    }

    public Double getError5() {
        return error5;
    }

    public void setError5(Double error5) {
        this.error5 = error5;
    }

    public Double getError6() {
        return error6;
    }

    public void setError6(Double error6) {
        this.error6 = error6;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getError1() {
        return error1;
    }

    public void setError1(Double error1) {
        this.error1 = error1;
    }

    public Double getError2() {
        return error2;
    }

    public void setError2(Double error2) {
        this.error2 = error2;
    }

    public Double getError3() {
        return error3;
    }

    public void setError3(Double error3) {
        this.error3 = error3;
    }

    public Double getPetrolcost() {
        return petrolcost;
    }

    public void setPetrolcost(Double petrolcost) {
        this.petrolcost = petrolcost;
    }

    public Double getPetrolusage() {
        return petrolusage;
    }

    public void setPetrolusage(Double petrolusage) {
        this.petrolusage = petrolusage;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public Double getMeasure6() {
        return measure6;
    }

    public void setMeasure6(Double measure6) {
        this.measure6 = measure6;
    }

    public Double getMeasuretolerance3() {
        return measuretolerance3;
    }

    public void setMeasuretolerance3(Double measuretolerance3) {
        this.measuretolerance3 = measuretolerance3;
    }

    public Double getMeasuretolerance4() {
        return measuretolerance4;
    }

    public void setMeasuretolerance4(Double measuretolerance4) {
        this.measuretolerance4 = measuretolerance4;
    }

    public Double getMeasure1() {
        return measure1;
    }

    public void setMeasure1(Double measure1) {
        this.measure1 = measure1;
    }

    public Double getMeasuretolerance5() {
        return measuretolerance5;
    }

    public void setMeasuretolerance5(Double measuretolerance5) {
        this.measuretolerance5 = measuretolerance5;
    }

    public Double getMeasuretolerance6() {
        return measuretolerance6;
    }

    public void setMeasuretolerance6(Double measuretolerance6) {
        this.measuretolerance6 = measuretolerance6;
    }

    public Double getMeasure4() {
        return measure4;
    }

    public void setMeasure4(Double measure4) {
        this.measure4 = measure4;
    }

    public Double getMeasure5() {
        return measure5;
    }

    public void setMeasure5(Double measure5) {
        this.measure5 = measure5;
    }

    public Double getMeasure2() {
        return measure2;
    }

    public void setMeasure2(Double measure2) {
        this.measure2 = measure2;
    }

    public Double getMeasuretolerance1() {
        return measuretolerance1;
    }

    public void setMeasuretolerance1(Double measuretolerance1) {
        this.measuretolerance1 = measuretolerance1;
    }

    public Double getMeasuretolerance2() {
        return measuretolerance2;
    }

    public void setMeasuretolerance2(Double measuretolerance2) {
        this.measuretolerance2 = measuretolerance2;
    }

    public Double getMeasure3() {
        return measure3;
    }

    public void setMeasure3(Double measure3) {
        this.measure3 = measure3;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpcalibration> getPetrolpumpcalibrationList() {
        return petrolpumpcalibrationList;
    }

    public void setPetrolpumpcalibrationList(List<Petrolpumpcalibration> petrolpumpcalibrationList) {
        this.petrolpumpcalibrationList = petrolpumpcalibrationList;
    }

    public Employee getRecordedbyId() {
        return recordedbyId;
    }

    public void setRecordedbyId(Employee recordedbyId) {
        this.recordedbyId = recordedbyId;
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
        if (!(object instanceof Petrolpumpcalibrationvalue)) {
            return false;
        }
        Petrolpumpcalibrationvalue other = (Petrolpumpcalibrationvalue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Petrolpumpcalibrationvalue[ id=" + id + " ]";
    }
    
}
