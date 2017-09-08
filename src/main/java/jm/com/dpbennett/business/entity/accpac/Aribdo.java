/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpbennett.business.entity.accpac;

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
@Table(name = "ARIBDO")
@NamedQueries({
    @NamedQuery(name = "Aribdo.findAll", query = "SELECT a FROM Aribdo a"),
    @NamedQuery(name = "Aribdo.findByCntbtch", query = "SELECT a FROM Aribdo a WHERE a.aribdoPK.cntbtch = :cntbtch"),
    @NamedQuery(name = "Aribdo.findByCntitem", query = "SELECT a FROM Aribdo a WHERE a.aribdoPK.cntitem = :cntitem"),
    @NamedQuery(name = "Aribdo.findByCntline", query = "SELECT a FROM Aribdo a WHERE a.aribdoPK.cntline = :cntline"),
    @NamedQuery(name = "Aribdo.findByOptfield", query = "SELECT a FROM Aribdo a WHERE a.aribdoPK.optfield = :optfield"),
    @NamedQuery(name = "Aribdo.findByAudtdate", query = "SELECT a FROM Aribdo a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Aribdo.findByAudttime", query = "SELECT a FROM Aribdo a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Aribdo.findByAudtuser", query = "SELECT a FROM Aribdo a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Aribdo.findByAudtorg", query = "SELECT a FROM Aribdo a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Aribdo.findByValue", query = "SELECT a FROM Aribdo a WHERE a.value = :value"),
    @NamedQuery(name = "Aribdo.findByType", query = "SELECT a FROM Aribdo a WHERE a.type = :type"),
    @NamedQuery(name = "Aribdo.findByLength", query = "SELECT a FROM Aribdo a WHERE a.length = :length"),
    @NamedQuery(name = "Aribdo.findByDecimals", query = "SELECT a FROM Aribdo a WHERE a.decimals = :decimals"),
    @NamedQuery(name = "Aribdo.findByAllownull", query = "SELECT a FROM Aribdo a WHERE a.allownull = :allownull"),
    @NamedQuery(name = "Aribdo.findByValidate", query = "SELECT a FROM Aribdo a WHERE a.validate = :validate"),
    @NamedQuery(name = "Aribdo.findBySwset", query = "SELECT a FROM Aribdo a WHERE a.swset = :swset")})
public class Aribdo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AribdoPK aribdoPK;
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

    public Aribdo() {
    }

    public Aribdo(AribdoPK aribdoPK) {
        this.aribdoPK = aribdoPK;
    }

    public Aribdo(AribdoPK aribdoPK, int audtdate, int audttime, String audtuser, String audtorg, String value, short type, short length, short decimals, short allownull, short validate, short swset) {
        this.aribdoPK = aribdoPK;
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

    public Aribdo(int cntbtch, int cntitem, int cntline, String optfield) {
        this.aribdoPK = new AribdoPK(cntbtch, cntitem, cntline, optfield);
    }

    public AribdoPK getAribdoPK() {
        return aribdoPK;
    }

    public void setAribdoPK(AribdoPK aribdoPK) {
        this.aribdoPK = aribdoPK;
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
        hash += (aribdoPK != null ? aribdoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aribdo)) {
            return false;
        }
        Aribdo other = (Aribdo) object;
        if ((this.aribdoPK == null && other.aribdoPK != null) || (this.aribdoPK != null && !this.aribdoPK.equals(other.aribdoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Aribdo[aribdoPK=" + aribdoPK + "]";
    }

}
