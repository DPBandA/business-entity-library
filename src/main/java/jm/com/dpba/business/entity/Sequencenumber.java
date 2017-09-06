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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "sequencenumber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sequencenumber.findAll", query = "SELECT s FROM Sequencenumber s"),
    @NamedQuery(name = "Sequencenumber.findById", query = "SELECT s FROM Sequencenumber s WHERE s.id = :id"),
    @NamedQuery(name = "Sequencenumber.findByName", query = "SELECT s FROM Sequencenumber s WHERE s.name = :name"),
    @NamedQuery(name = "Sequencenumber.findBySequentialnumber", query = "SELECT s FROM Sequencenumber s WHERE s.sequentialnumber = :sequentialnumber"),
    @NamedQuery(name = "Sequencenumber.findByYearreceived", query = "SELECT s FROM Sequencenumber s WHERE s.yearreceived = :yearreceived")})
public class Sequencenumber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "SEQUENTIALNUMBER")
    private BigInteger sequentialnumber;
    @Column(name = "YEARRECEIVED")
    private Integer yearreceived;

    public Sequencenumber() {
    }

    public Sequencenumber(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getSequentialnumber() {
        return sequentialnumber;
    }

    public void setSequentialnumber(BigInteger sequentialnumber) {
        this.sequentialnumber = sequentialnumber;
    }

    public Integer getYearreceived() {
        return yearreceived;
    }

    public void setYearreceived(Integer yearreceived) {
        this.yearreceived = yearreceived;
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
        if (!(object instanceof Sequencenumber)) {
            return false;
        }
        Sequencenumber other = (Sequencenumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.utils.Sequencenumber[ id=" + id + " ]";
    }
    
}
