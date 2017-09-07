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
public class ArobsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDCUST")
    private String idcust;
    @Basic(optional = false)
    @Column(name = "IDINVC")
    private String idinvc;
    @Basic(optional = false)
    @Column(name = "CNTPAYM")
    private int cntpaym;

    public ArobsPK() {
    }

    public ArobsPK(String idcust, String idinvc, int cntpaym) {
        this.idcust = idcust;
        this.idinvc = idinvc;
        this.cntpaym = cntpaym;
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

    public int getCntpaym() {
        return cntpaym;
    }

    public void setCntpaym(int cntpaym) {
        this.cntpaym = cntpaym;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcust != null ? idcust.hashCode() : 0);
        hash += (idinvc != null ? idinvc.hashCode() : 0);
        hash += (int) cntpaym;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArobsPK)) {
            return false;
        }
        ArobsPK other = (ArobsPK) object;
        if ((this.idcust == null && other.idcust != null) || (this.idcust != null && !this.idcust.equals(other.idcust))) {
            return false;
        }
        if ((this.idinvc == null && other.idinvc != null) || (this.idinvc != null && !this.idinvc.equals(other.idinvc))) {
            return false;
        }
        if (this.cntpaym != other.cntpaym) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.ArobsPK[idcust=" + idcust + ", idinvc=" + idinvc + ", cntpaym=" + cntpaym + "]";
    }

}
