/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desbenn
 */
@Entity
@Table(name = "documentsequencenumber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentsequencenumber.findAll", query = "SELECT d FROM Documentsequencenumber d")
    , @NamedQuery(name = "Documentsequencenumber.findById", query = "SELECT d FROM Documentsequencenumber d WHERE d.id = :id")
    , @NamedQuery(name = "Documentsequencenumber.findByMonthreceived", query = "SELECT d FROM Documentsequencenumber d WHERE d.monthreceived = :monthreceived")
    , @NamedQuery(name = "Documentsequencenumber.findByDocumenttypeid", query = "SELECT d FROM Documentsequencenumber d WHERE d.documenttypeid = :documenttypeid")
    , @NamedQuery(name = "Documentsequencenumber.findByYearreceived", query = "SELECT d FROM Documentsequencenumber d WHERE d.yearreceived = :yearreceived")
    , @NamedQuery(name = "Documentsequencenumber.findBySequentialnumber", query = "SELECT d FROM Documentsequencenumber d WHERE d.sequentialnumber = :sequentialnumber")})
public class Documentsequencenumber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "MONTHRECEIVED")
    private Integer monthreceived;
    @Column(name = "DOCUMENTTYPEID")
    private BigInteger documenttypeid;
    @Column(name = "YEARRECEIVED")
    private Integer yearreceived;
    @Column(name = "SEQUENTIALNUMBER")
    private BigInteger sequentialnumber;

    public Documentsequencenumber() {
    }

    public Documentsequencenumber(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonthreceived() {
        return monthreceived;
    }

    public void setMonthreceived(Integer monthreceived) {
        this.monthreceived = monthreceived;
    }

    public BigInteger getDocumenttypeid() {
        return documenttypeid;
    }

    public void setDocumenttypeid(BigInteger documenttypeid) {
        this.documenttypeid = documenttypeid;
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
        if (!(object instanceof Documentsequencenumber)) {
            return false;
        }
        Documentsequencenumber other = (Documentsequencenumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.com.dpba.business.entity.Documentsequencenumber[ id=" + id + " ]";
    }
    
}
