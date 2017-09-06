/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "jobsequencenumber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobsequencenumber.findAll", query = "SELECT j FROM Jobsequencenumber j"),
    @NamedQuery(name = "Jobsequencenumber.findById", query = "SELECT j FROM Jobsequencenumber j WHERE j.id = :id"),
    @NamedQuery(name = "Jobsequencenumber.findByYearreceived", query = "SELECT j FROM Jobsequencenumber j WHERE j.yearreceived = :yearreceived"),
    @NamedQuery(name = "Jobsequencenumber.findBySequentialnumber", query = "SELECT j FROM Jobsequencenumber j WHERE j.sequentialnumber = :sequentialnumber")})
public class Jobsequencenumber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "YEARRECEIVED")
    private Integer yearreceived;
    @Column(name = "SEQUENTIALNUMBER")
    private BigInteger sequentialnumber;

    public Jobsequencenumber() {
    }

    public Jobsequencenumber(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYearreceived() {
        return yearreceived;
    }

    public void setYearreceived(Integer yearreceived) {
        this.yearreceived = yearreceived;
    }

    public BigInteger getSequentialnumber() {
        return sequentialnumber;
    }

    public void setSequentialnumber(BigInteger sequentialnumber) {
        this.sequentialnumber = sequentialnumber;
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
        if (!(object instanceof Jobsequencenumber)) {
            return false;
        }
        Jobsequencenumber other = (Jobsequencenumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Jobsequencenumber[ id=" + id + " ]";
    }
    
}
