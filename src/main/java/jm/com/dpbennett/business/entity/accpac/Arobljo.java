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
@Table(name = "AROBLJO")
@NamedQueries({
    @NamedQuery(name = "Arobljo.findAll", query = "SELECT a FROM Arobljo a"),
    @NamedQuery(name = "Arobljo.findByIdcust", query = "SELECT a FROM Arobljo a WHERE a.arobljoPK.idcust = :idcust"),
    @NamedQuery(name = "Arobljo.findByIdinvc", query = "SELECT a FROM Arobljo a WHERE a.arobljoPK.idinvc = :idinvc"),
    @NamedQuery(name = "Arobljo.findByCntline", query = "SELECT a FROM Arobljo a WHERE a.arobljoPK.cntline = :cntline"),
    @NamedQuery(name = "Arobljo.findByOptfield", query = "SELECT a FROM Arobljo a WHERE a.arobljoPK.optfield = :optfield"),
    @NamedQuery(name = "Arobljo.findByAudtdate", query = "SELECT a FROM Arobljo a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Arobljo.findByAudttime", query = "SELECT a FROM Arobljo a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Arobljo.findByAudtuser", query = "SELECT a FROM Arobljo a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Arobljo.findByAudtorg", query = "SELECT a FROM Arobljo a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Arobljo.findByValue", query = "SELECT a FROM Arobljo a WHERE a.value = :value"),
    @NamedQuery(name = "Arobljo.findByType", query = "SELECT a FROM Arobljo a WHERE a.type = :type"),
    @NamedQuery(name = "Arobljo.findByLength", query = "SELECT a FROM Arobljo a WHERE a.length = :length"),
    @NamedQuery(name = "Arobljo.findByDecimals", query = "SELECT a FROM Arobljo a WHERE a.decimals = :decimals"),
    @NamedQuery(name = "Arobljo.findByAllownull", query = "SELECT a FROM Arobljo a WHERE a.allownull = :allownull"),
    @NamedQuery(name = "Arobljo.findByValidate", query = "SELECT a FROM Arobljo a WHERE a.validate = :validate")})
public class Arobljo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArobljoPK arobljoPK;
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

    public Arobljo() {
    }

    public Arobljo(ArobljoPK arobljoPK) {
        this.arobljoPK = arobljoPK;
    }

    public Arobljo(ArobljoPK arobljoPK, int audtdate, int audttime, String audtuser, String audtorg, String value, short type, short length, short decimals, short allownull, short validate) {
        this.arobljoPK = arobljoPK;
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
    }

    public Arobljo(String idcust, String idinvc, int cntline, String optfield) {
        this.arobljoPK = new ArobljoPK(idcust, idinvc, cntline, optfield);
    }

    public ArobljoPK getArobljoPK() {
        return arobljoPK;
    }

    public void setArobljoPK(ArobljoPK arobljoPK) {
        this.arobljoPK = arobljoPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arobljoPK != null ? arobljoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arobljo)) {
            return false;
        }
        Arobljo other = (Arobljo) object;
        if ((this.arobljoPK == null && other.arobljoPK != null) || (this.arobljoPK != null && !this.arobljoPK.equals(other.arobljoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Arobljo[arobljoPK=" + arobljoPK + "]";
    }

}
