/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpbennett.business.accpac;

import java.io.Serializable;
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
@Table(name = "ARIBHO")
@NamedQueries({
    @NamedQuery(name = "Aribho.findAll", query = "SELECT a FROM Aribho a"),
    @NamedQuery(name = "Aribho.findByCntbtch", query = "SELECT a FROM Aribho a WHERE a.aribhoPK.cntbtch = :cntbtch"),
    @NamedQuery(name = "Aribho.findByCntitem", query = "SELECT a FROM Aribho a WHERE a.aribhoPK.cntitem = :cntitem"),
    @NamedQuery(name = "Aribho.findByOptfield", query = "SELECT a FROM Aribho a WHERE a.aribhoPK.optfield = :optfield"),
    @NamedQuery(name = "Aribho.findByAudtdate", query = "SELECT a FROM Aribho a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Aribho.findByAudttime", query = "SELECT a FROM Aribho a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Aribho.findByAudtuser", query = "SELECT a FROM Aribho a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Aribho.findByAudtorg", query = "SELECT a FROM Aribho a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Aribho.findByValue", query = "SELECT a FROM Aribho a WHERE a.value = :value"),
    @NamedQuery(name = "Aribho.findByType", query = "SELECT a FROM Aribho a WHERE a.type = :type"),
    @NamedQuery(name = "Aribho.findByLength", query = "SELECT a FROM Aribho a WHERE a.length = :length"),
    @NamedQuery(name = "Aribho.findByDecimals", query = "SELECT a FROM Aribho a WHERE a.decimals = :decimals"),
    @NamedQuery(name = "Aribho.findByAllownull", query = "SELECT a FROM Aribho a WHERE a.allownull = :allownull"),
    @NamedQuery(name = "Aribho.findByValidate", query = "SELECT a FROM Aribho a WHERE a.validate = :validate"),
    @NamedQuery(name = "Aribho.findBySwset", query = "SELECT a FROM Aribho a WHERE a.swset = :swset")})
public class Aribho implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AribhoPK aribhoPK;
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
    @Column(name = "VALUE")
    private String value;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private short type;
    @Basic(optional = false)
    @Column(name = "LENGTH")
    private short length;
    @Basic(optional = false)
    @Column(name = "DECIMALS")
    private short decimals;
    @Basic(optional = false)
    @Column(name = "ALLOWNULL")
    private short allownull;
    @Basic(optional = false)
    @Column(name = "VALIDATE")
    private short validate;
    @Basic(optional = false)
    @Column(name = "SWSET")
    private short swset;

    public Aribho() {
    }

    public Aribho(AribhoPK aribhoPK) {
        this.aribhoPK = aribhoPK;
    }

    public Aribho(AribhoPK aribhoPK, int audtdate, int audttime, String audtuser, String audtorg, String value, short type, short length, short decimals, short allownull, short validate, short swset) {
        this.aribhoPK = aribhoPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.value = value;
        this.type = type;
        this.length = length;
        this.decimals = decimals;
        this.allownull = allownull;
        this.validate = validate;
        this.swset = swset;
    }

    public Aribho(int cntbtch, int cntitem, String optfield) {
        this.aribhoPK = new AribhoPK(cntbtch, cntitem, optfield);
    }

    public AribhoPK getAribhoPK() {
        return aribhoPK;
    }

    public void setAribhoPK(AribhoPK aribhoPK) {
        this.aribhoPK = aribhoPK;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public short getDecimals() {
        return decimals;
    }

    public void setDecimals(short decimals) {
        this.decimals = decimals;
    }

    public short getAllownull() {
        return allownull;
    }

    public void setAllownull(short allownull) {
        this.allownull = allownull;
    }

    public short getValidate() {
        return validate;
    }

    public void setValidate(short validate) {
        this.validate = validate;
    }

    public short getSwset() {
        return swset;
    }

    public void setSwset(short swset) {
        this.swset = swset;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aribhoPK != null ? aribhoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aribho)) {
            return false;
        }
        Aribho other = (Aribho) object;
        if ((this.aribhoPK == null && other.aribhoPK != null) || (this.aribhoPK != null && !this.aribhoPK.equals(other.aribhoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Aribho[aribhoPK=" + aribhoPK + "]";
    }

}
