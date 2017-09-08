/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpbennett.business.entity.accpac;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "ARIBS")
@NamedQueries({
    @NamedQuery(name = "Aribs.findAll", query = "SELECT a FROM Aribs a"),
    @NamedQuery(name = "Aribs.findByCntbtch", query = "SELECT a FROM Aribs a WHERE a.aribsPK.cntbtch = :cntbtch"),
    @NamedQuery(name = "Aribs.findByCntitem", query = "SELECT a FROM Aribs a WHERE a.aribsPK.cntitem = :cntitem"),
    @NamedQuery(name = "Aribs.findByCntpaym", query = "SELECT a FROM Aribs a WHERE a.aribsPK.cntpaym = :cntpaym"),
    @NamedQuery(name = "Aribs.findByAudtdate", query = "SELECT a FROM Aribs a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Aribs.findByAudttime", query = "SELECT a FROM Aribs a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Aribs.findByAudtuser", query = "SELECT a FROM Aribs a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Aribs.findByAudtorg", query = "SELECT a FROM Aribs a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Aribs.findByDatedue", query = "SELECT a FROM Aribs a WHERE a.datedue = :datedue"),
    @NamedQuery(name = "Aribs.findByAmtdue", query = "SELECT a FROM Aribs a WHERE a.amtdue = :amtdue"),
    @NamedQuery(name = "Aribs.findByDatedisc", query = "SELECT a FROM Aribs a WHERE a.datedisc = :datedisc"),
    @NamedQuery(name = "Aribs.findByAmtdisc", query = "SELECT a FROM Aribs a WHERE a.amtdisc = :amtdisc"),
    @NamedQuery(name = "Aribs.findByAmtduehc", query = "SELECT a FROM Aribs a WHERE a.amtduehc = :amtduehc"),
    @NamedQuery(name = "Aribs.findByAmtdischc", query = "SELECT a FROM Aribs a WHERE a.amtdischc = :amtdischc")})
public class Aribs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AribsPK aribsPK;
    @Basic(optional = false)
    @Column(name = "AUDTDATE")
    private int audtdate;
    @Basic(optional = false)
    @Column(name = "AUDTTIME")
    private int audttime;
    @Basic(optional = false)
    @Column(name = "AUDTUSER")
    private String audtuser;
    @Basic(optional = false)
    @Column(name = "AUDTORG")
    private String audtorg;
    @Basic(optional = false)
    @Column(name = "DATEDUE")
    private int datedue;
    @Basic(optional = false)
    @Column(name = "AMTDUE")
    private BigDecimal amtdue;
    @Basic(optional = false)
    @Column(name = "DATEDISC")
    private int datedisc;
    @Basic(optional = false)
    @Column(name = "AMTDISC")
    private BigDecimal amtdisc;
    @Basic(optional = false)
    @Column(name = "AMTDUEHC")
    private BigDecimal amtduehc;
    @Basic(optional = false)
    @Column(name = "AMTDISCHC")
    private BigDecimal amtdischc;

    public Aribs() {
    }

    public Aribs(AribsPK aribsPK) {
        this.aribsPK = aribsPK;
    }

    public Aribs(AribsPK aribsPK, int audtdate, int audttime, String audtuser, String audtorg, int datedue, BigDecimal amtdue, int datedisc, BigDecimal amtdisc, BigDecimal amtduehc, BigDecimal amtdischc) {
        this.aribsPK = aribsPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.datedue = datedue;
        this.amtdue = amtdue;
        this.datedisc = datedisc;
        this.amtdisc = amtdisc;
        this.amtduehc = amtduehc;
        this.amtdischc = amtdischc;
    }

    public Aribs(int cntbtch, int cntitem, int cntpaym) {
        this.aribsPK = new AribsPK(cntbtch, cntitem, cntpaym);
    }

    public AribsPK getAribsPK() {
        return aribsPK;
    }

    public void setAribsPK(AribsPK aribsPK) {
        this.aribsPK = aribsPK;
    }

    public int getAudtdate() {
        return audtdate;
    }

    public void setAudtdate(int audtdate) {
        this.audtdate = audtdate;
    }

    public int getAudttime() {
        return audttime;
    }

    public void setAudttime(int audttime) {
        this.audttime = audttime;
    }

    public String getAudtuser() {
        return audtuser;
    }

    public void setAudtuser(String audtuser) {
        this.audtuser = audtuser;
    }

    public String getAudtorg() {
        return audtorg;
    }

    public void setAudtorg(String audtorg) {
        this.audtorg = audtorg;
    }

    public int getDatedue() {
        return datedue;
    }

    public void setDatedue(int datedue) {
        this.datedue = datedue;
    }

    public BigDecimal getAmtdue() {
        return amtdue;
    }

    public void setAmtdue(BigDecimal amtdue) {
        this.amtdue = amtdue;
    }

    public int getDatedisc() {
        return datedisc;
    }

    public void setDatedisc(int datedisc) {
        this.datedisc = datedisc;
    }

    public BigDecimal getAmtdisc() {
        return amtdisc;
    }

    public void setAmtdisc(BigDecimal amtdisc) {
        this.amtdisc = amtdisc;
    }

    public BigDecimal getAmtduehc() {
        return amtduehc;
    }

    public void setAmtduehc(BigDecimal amtduehc) {
        this.amtduehc = amtduehc;
    }

    public BigDecimal getAmtdischc() {
        return amtdischc;
    }

    public void setAmtdischc(BigDecimal amtdischc) {
        this.amtdischc = amtdischc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aribsPK != null ? aribsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aribs)) {
            return false;
        }
        Aribs other = (Aribs) object;
        if ((this.aribsPK == null && other.aribsPK != null) || (this.aribsPK != null && !this.aribsPK.equals(other.aribsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Aribs[aribsPK=" + aribsPK + "]";
    }

}
