/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpbennett.business.accpac;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author DBennett
 */
@Embeddable
public class ArobpPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDCUST")
    private String idcust;
    @Basic(optional = false)
    @Column(name = "IDINVC")
    private String idinvc;
    @Basic(optional = false)
    @Column(name = "CNTPAYMNBR")
    private int cntpaymnbr;
    @Basic(optional = false)
    @Column(name = "IDRMIT")
    private String idrmit;
    @Basic(optional = false)
    @Column(name = "DATEBUS")
    private int datebus;
    @Basic(optional = false)
    @Column(name = "TRANSTYPE")
    private short transtype;
    @Basic(optional = false)
    @Column(name = "CNTSEQNCE")
    private int cntseqnce;

    public ArobpPK() {
    }

    public ArobpPK(String idcust, String idinvc, int cntpaymnbr, String idrmit, int datebus, short transtype, int cntseqnce) {
        this.idcust = idcust;
        this.idinvc = idinvc;
        this.cntpaymnbr = cntpaymnbr;
        this.idrmit = idrmit;
        this.datebus = datebus;
        this.transtype = transtype;
        this.cntseqnce = cntseqnce;
    }

    public String getIdcust() {
        return idcust;
    }

    public void setIdcust(String idcust) {
        this.idcust = idcust;
    }

    public String getIdinvc() {
        return idinvc;
    }

    public void setIdinvc(String idinvc) {
        this.idinvc = idinvc;
    }

    public int getCntpaymnbr() {
        return cntpaymnbr;
    }

    public void setCntpaymnbr(int cntpaymnbr) {
        this.cntpaymnbr = cntpaymnbr;
    }

    public String getIdrmit() {
        return idrmit;
    }

    public void setIdrmit(String idrmit) {
        this.idrmit = idrmit;
    }

    public int getDatebus() {
        return datebus;
    }

    public void setDatebus(int datebus) {
        this.datebus = datebus;
    }

    public short getTranstype() {
        return transtype;
    }

    public void setTranstype(short transtype) {
        this.transtype = transtype;
    }

    public int getCntseqnce() {
        return cntseqnce;
    }

    public void setCntseqnce(int cntseqnce) {
        this.cntseqnce = cntseqnce;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcust != null ? idcust.hashCode() : 0);
        hash += (idinvc != null ? idinvc.hashCode() : 0);
        hash += (int) cntpaymnbr;
        hash += (idrmit != null ? idrmit.hashCode() : 0);
        hash += (int) datebus;
        hash += (int) transtype;
        hash += (int) cntseqnce;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArobpPK)) {
            return false;
        }
        ArobpPK other = (ArobpPK) object;
        if ((this.idcust == null && other.idcust != null) || (this.idcust != null && !this.idcust.equals(other.idcust))) {
            return false;
        }
        if ((this.idinvc == null && other.idinvc != null) || (this.idinvc != null && !this.idinvc.equals(other.idinvc))) {
            return false;
        }
        if (this.cntpaymnbr != other.cntpaymnbr) {
            return false;
        }
        if ((this.idrmit == null && other.idrmit != null) || (this.idrmit != null && !this.idrmit.equals(other.idrmit))) {
            return false;
        }
        if (this.datebus != other.datebus) {
            return false;
        }
        if (this.transtype != other.transtype) {
            return false;
        }
        if (this.cntseqnce != other.cntseqnce) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.ArobpPK[idcust=" + idcust + ", idinvc=" + idinvc + ", cntpaymnbr=" + cntpaymnbr + ", idrmit=" + idrmit + ", datebus=" + datebus + ", transtype=" + transtype + ", cntseqnce=" + cntseqnce + "]";
    }

}
