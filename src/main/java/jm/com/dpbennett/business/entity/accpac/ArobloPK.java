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
public class ArobloPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDCUST")
    private String idcust;
    @Basic(optional = false)
    @Column(name = "IDINVC")
    private String idinvc;
    @Basic(optional = false)
    @Column(name = "OPTFIELD")
    private String optfield;

    public ArobloPK() {
    }

    public ArobloPK(String idcust, String idinvc, String optfield) {
        this.idcust = idcust;
        this.idinvc = idinvc;
        this.optfield = optfield;
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

    public String getOptfield() {
        return optfield;
    }

    public void setOptfield(String optfield) {
        this.optfield = optfield;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcust != null ? idcust.hashCode() : 0);
        hash += (idinvc != null ? idinvc.hashCode() : 0);
        hash += (optfield != null ? optfield.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArobloPK)) {
            return false;
        }
        ArobloPK other = (ArobloPK) object;
        if ((this.idcust == null && other.idcust != null) || (this.idcust != null && !this.idcust.equals(other.idcust))) {
            return false;
        }
        if ((this.idinvc == null && other.idinvc != null) || (this.idinvc != null && !this.idinvc.equals(other.idinvc))) {
            return false;
        }
        if ((this.optfield == null && other.optfield != null) || (this.optfield != null && !this.optfield.equals(other.optfield))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.ArobloPK[idcust=" + idcust + ", idinvc=" + idinvc + ", optfield=" + optfield + "]";
    }

}
