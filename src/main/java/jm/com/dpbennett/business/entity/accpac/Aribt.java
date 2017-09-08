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
@Table(name = "ARIBT")
@NamedQueries({
    @NamedQuery(name = "Aribt.findAll", query = "SELECT a FROM Aribt a"),
    @NamedQuery(name = "Aribt.findByCntbtch", query = "SELECT a FROM Aribt a WHERE a.aribtPK.cntbtch = :cntbtch"),
    @NamedQuery(name = "Aribt.findByCntitem", query = "SELECT a FROM Aribt a WHERE a.aribtPK.cntitem = :cntitem"),
    @NamedQuery(name = "Aribt.findByCntline", query = "SELECT a FROM Aribt a WHERE a.aribtPK.cntline = :cntline"),
    @NamedQuery(name = "Aribt.findByAudtdate", query = "SELECT a FROM Aribt a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Aribt.findByAudttime", query = "SELECT a FROM Aribt a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Aribt.findByAudtuser", query = "SELECT a FROM Aribt a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Aribt.findByAudtorg", query = "SELECT a FROM Aribt a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Aribt.findByTextline", query = "SELECT a FROM Aribt a WHERE a.textline = :textline"),
    @NamedQuery(name = "Aribt.findBySwprtstm", query = "SELECT a FROM Aribt a WHERE a.swprtstm = :swprtstm")})
public class Aribt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AribtPK aribtPK;
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
    @Column(name = "TEXTLINE")
    private String textline;
    @Basic(optional = false)
    @Column(name = "SWPRTSTM")
    private short swprtstm;

    public Aribt() {
    }

    public Aribt(AribtPK aribtPK) {
        this.aribtPK = aribtPK;
    }

    public Aribt(AribtPK aribtPK, int audtdate, int audttime, String audtuser, String audtorg, String textline, short swprtstm) {
        this.aribtPK = aribtPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.textline = textline;
        this.swprtstm = swprtstm;
    }

    public Aribt(int cntbtch, int cntitem, int cntline) {
        this.aribtPK = new AribtPK(cntbtch, cntitem, cntline);
    }

    public AribtPK getAribtPK() {
        return aribtPK;
    }

    public void setAribtPK(AribtPK aribtPK) {
        this.aribtPK = aribtPK;
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

    public String getTextline() {
        return textline;
    }

    public void setTextline(String textline) {
        this.textline = textline;
    }

    public short getSwprtstm() {
        return swprtstm;
    }

    public void setSwprtstm(short swprtstm) {
        this.swprtstm = swprtstm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aribtPK != null ? aribtPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aribt)) {
            return false;
        }
        Aribt other = (Aribt) object;
        if ((this.aribtPK == null && other.aribtPK != null) || (this.aribtPK != null && !this.aribtPK.equals(other.aribtPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Aribt[aribtPK=" + aribtPK + "]";
    }

}
