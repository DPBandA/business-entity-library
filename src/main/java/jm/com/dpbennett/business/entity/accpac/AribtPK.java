/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
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
public class AribtPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CNTBTCH")
    private int cntbtch;
    @Basic(optional = false)
    @Column(name = "CNTITEM")
    private int cntitem;
    @Basic(optional = false)
    @Column(name = "CNTLINE")
    private int cntline;

    public AribtPK() {
    }

    public AribtPK(int cntbtch, int cntitem, int cntline) {
        this.cntbtch = cntbtch;
        this.cntitem = cntitem;
        this.cntline = cntline;
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

    public int getCntline() {
        return cntline;
    }

    public void setCntline(int cntline) {
        this.cntline = cntline;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cntbtch;
        hash += (int) cntitem;
        hash += (int) cntline;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AribtPK)) {
            return false;
        }
        AribtPK other = (AribtPK) object;
        if (this.cntbtch != other.cntbtch) {
            return false;
        }
        if (this.cntitem != other.cntitem) {
            return false;
        }
        if (this.cntline != other.cntline) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.AribtPK[cntbtch=" + cntbtch + ", cntitem=" + cntitem + ", cntline=" + cntline + "]";
    }

}
