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
public class AribhPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CNTBTCH")
    private int cntbtch;
    @Basic(optional = false)
    @Column(name = "CNTITEM")
    private int cntitem;

    public AribhPK() {
    }

    public AribhPK(int cntbtch, int cntitem) {
        this.cntbtch = cntbtch;
        this.cntitem = cntitem;
    }

    public int getCntbtch() {
        return cntbtch;
    }

    public void setCntbtch(int cntbtch) {
        this.cntbtch = cntbtch;
    }

    public int getCntitem() {
        return cntitem;
    }

    public void setCntitem(int cntitem) {
        this.cntitem = cntitem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cntbtch;
        hash += (int) cntitem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AribhPK)) {
            return false;
        }
        AribhPK other = (AribhPK) object;
        if (this.cntbtch != other.cntbtch) {
            return false;
        }
        if (this.cntitem != other.cntitem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.AribhPK[cntbtch=" + cntbtch + ", cntitem=" + cntitem + "]";
    }

}
