/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpbennett.business.entity.accpac;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author DBennett
 */
@Embeddable
public class ArobljpPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDCUST")
    private String idcust;
    @Basic(optional = false)
    @Column(name = "IDINVC")
    private String idinvc;
    @Basic(optional = false)
    @Column(name = "CNTLINE")
    private int cntline;
    @Basic(optional = false)
    @Column(name = "CNTSEQENCE")
    private int cntseqence;

    public ArobljpPK() {
    }

    public ArobljpPK(String idcust, String idinvc, int cntline, int cntseqence) {
        this.idcust = idcust;
        this.idinvc = idinvc;
        this.cntline = cntline;
        this.cntseqence = cntseqence;
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

    public int getCntline() {
        return cntline;
    }

    public void setCntline(int cntline) {
        this.cntline = cntline;
    }

    public int getCntseqence() {
        return cntseqence;
    }

    public void setCntseqence(int cntseqence) {
        this.cntseqence = cntseqence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcust != null ? idcust.hashCode() : 0);
        hash += (idinvc != null ? idinvc.hashCode() : 0);
        hash += (int) cntline;
        hash += (int) cntseqence;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArobljpPK)) {
            return false;
        }
        ArobljpPK other = (ArobljpPK) object;
        if ((this.idcust == null && other.idcust != null) || (this.idcust != null && !this.idcust.equals(other.idcust))) {
            return false;
        }
        if ((this.idinvc == null && other.idinvc != null) || (this.idinvc != null && !this.idinvc.equals(other.idinvc))) {
            return false;
        }
        if (this.cntline != other.cntline) {
            return false;
        }
        if (this.cntseqence != other.cntseqence) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.ArobljpPK[idcust=" + idcust + ", idinvc=" + idinvc + ", cntline=" + cntline + ", cntseqence=" + cntseqence + "]";
    }

}
