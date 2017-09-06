/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "petrolpumpnozzlecalibrationpoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petrolpumpnozzlecalibrationpoint.findAll", query = "SELECT p FROM Petrolpumpnozzlecalibrationpoint p"),
    @NamedQuery(name = "Petrolpumpnozzlecalibrationpoint.findById", query = "SELECT p FROM Petrolpumpnozzlecalibrationpoint p WHERE p.id = :id"),
    @NamedQuery(name = "Petrolpumpnozzlecalibrationpoint.findByError", query = "SELECT p FROM Petrolpumpnozzlecalibrationpoint p WHERE p.error = :error"),
    @NamedQuery(name = "Petrolpumpnozzlecalibrationpoint.findByNumber", query = "SELECT p FROM Petrolpumpnozzlecalibrationpoint p WHERE p.number = :number"),
    @NamedQuery(name = "Petrolpumpnozzlecalibrationpoint.findByTolerance", query = "SELECT p FROM Petrolpumpnozzlecalibrationpoint p WHERE p.tolerance = :tolerance"),
    @NamedQuery(name = "Petrolpumpnozzlecalibrationpoint.findByUseit", query = "SELECT p FROM Petrolpumpnozzlecalibrationpoint p WHERE p.useit = :useit")})
public class Petrolpumpnozzlecalibrationpoint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ERROR")
    private Double error;
    @Column(name = "NUMBER")
    private BigInteger number;
    @Column(name = "TOLERANCE")
    private Double tolerance;
    @Column(name = "USEIT")
    private Boolean useit;
    @ManyToMany(mappedBy = "petrolpumpnozzlecalibrationpointList")
    private List<Petrolpumpnozzlecalibration> petrolpumpnozzlecalibrationList;
    @JoinColumn(name = "TESTMEASURE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Testmeasure testmeasureId;

    public Petrolpumpnozzlecalibrationpoint() {
    }

    public Petrolpumpnozzlecalibrationpoint(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getError() {
        return error;
    }

    public void setError(Double error) {
        this.error = error;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public Boolean getUseit() {
        return useit;
    }

    public void setUseit(Boolean useit) {
        this.useit = useit;
    }

    @XmlTransient
    @JsonIgnore
    public List<Petrolpumpnozzlecalibration> getPetrolpumpnozzlecalibrationList() {
        return petrolpumpnozzlecalibrationList;
    }

    public void setPetrolpumpnozzlecalibrationList(List<Petrolpumpnozzlecalibration> petrolpumpnozzlecalibrationList) {
        this.petrolpumpnozzlecalibrationList = petrolpumpnozzlecalibrationList;
    }

    public Testmeasure getTestmeasureId() {
        return testmeasureId;
    }

    public void setTestmeasureId(Testmeasure testmeasureId) {
        this.testmeasureId = testmeasureId;
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
        if (!(object instanceof Petrolpumpnozzlecalibrationpoint)) {
            return false;
        }
        Petrolpumpnozzlecalibrationpoint other = (Petrolpumpnozzlecalibrationpoint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Petrolpumpnozzlecalibrationpoint[ id=" + id + " ]";
    }
    
}
