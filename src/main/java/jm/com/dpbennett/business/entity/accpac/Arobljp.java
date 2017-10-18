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
import java.math.BigDecimal;
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
@Table(name = "AROBLJP")
@NamedQueries({
    @NamedQuery(name = "Arobljp.findAll", query = "SELECT a FROM Arobljp a"),
    @NamedQuery(name = "Arobljp.findByIdcust", query = "SELECT a FROM Arobljp a WHERE a.arobljpPK.idcust = :idcust"),
    @NamedQuery(name = "Arobljp.findByIdinvc", query = "SELECT a FROM Arobljp a WHERE a.arobljpPK.idinvc = :idinvc"),
    @NamedQuery(name = "Arobljp.findByCntline", query = "SELECT a FROM Arobljp a WHERE a.arobljpPK.cntline = :cntline"),
    @NamedQuery(name = "Arobljp.findByCntseqence", query = "SELECT a FROM Arobljp a WHERE a.arobljpPK.cntseqence = :cntseqence"),
    @NamedQuery(name = "Arobljp.findByAudtdate", query = "SELECT a FROM Arobljp a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Arobljp.findByAudttime", query = "SELECT a FROM Arobljp a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Arobljp.findByAudtuser", query = "SELECT a FROM Arobljp a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Arobljp.findByAudtorg", query = "SELECT a FROM Arobljp a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Arobljp.findByTransnbr", query = "SELECT a FROM Arobljp a WHERE a.transnbr = :transnbr"),
    @NamedQuery(name = "Arobljp.findByDatebus", query = "SELECT a FROM Arobljp a WHERE a.datebus = :datebus"),
    @NamedQuery(name = "Arobljp.findByTranstype", query = "SELECT a FROM Arobljp a WHERE a.transtype = :transtype"),
    @NamedQuery(name = "Arobljp.findByTrxtype", query = "SELECT a FROM Arobljp a WHERE a.trxtype = :trxtype"),
    @NamedQuery(name = "Arobljp.findByTypebtch", query = "SELECT a FROM Arobljp a WHERE a.typebtch = :typebtch"),
    @NamedQuery(name = "Arobljp.findByCntbtch", query = "SELECT a FROM Arobljp a WHERE a.cntbtch = :cntbtch"),
    @NamedQuery(name = "Arobljp.findByCntitem", query = "SELECT a FROM Arobljp a WHERE a.cntitem = :cntitem"),
    @NamedQuery(name = "Arobljp.findByDatebtch", query = "SELECT a FROM Arobljp a WHERE a.datebtch = :datebtch"),
    @NamedQuery(name = "Arobljp.findByAmtpaymhc", query = "SELECT a FROM Arobljp a WHERE a.amtpaymhc = :amtpaymhc"),
    @NamedQuery(name = "Arobljp.findByAmtpaymtc", query = "SELECT a FROM Arobljp a WHERE a.amtpaymtc = :amtpaymtc"),
    @NamedQuery(name = "Arobljp.findByTxtotrthc", query = "SELECT a FROM Arobljp a WHERE a.txtotrthc = :txtotrthc"),
    @NamedQuery(name = "Arobljp.findByTxtotrttc", query = "SELECT a FROM Arobljp a WHERE a.txtotrttc = :txtotrttc"),
    @NamedQuery(name = "Arobljp.findByCodecurn", query = "SELECT a FROM Arobljp a WHERE a.codecurn = :codecurn"),
    @NamedQuery(name = "Arobljp.findByIdratetype", query = "SELECT a FROM Arobljp a WHERE a.idratetype = :idratetype"),
    @NamedQuery(name = "Arobljp.findByRateexchhc", query = "SELECT a FROM Arobljp a WHERE a.rateexchhc = :rateexchhc"),
    @NamedQuery(name = "Arobljp.findByRatedate", query = "SELECT a FROM Arobljp a WHERE a.ratedate = :ratedate"),
    @NamedQuery(name = "Arobljp.findByRateop", query = "SELECT a FROM Arobljp a WHERE a.rateop = :rateop"),
    @NamedQuery(name = "Arobljp.findByIdbank", query = "SELECT a FROM Arobljp a WHERE a.idbank = :idbank"),
    @NamedQuery(name = "Arobljp.findByIdcustrmit", query = "SELECT a FROM Arobljp a WHERE a.idcustrmit = :idcustrmit"),
    @NamedQuery(name = "Arobljp.findByIdrmit", query = "SELECT a FROM Arobljp a WHERE a.idrmit = :idrmit"),
    @NamedQuery(name = "Arobljp.findByDepseq", query = "SELECT a FROM Arobljp a WHERE a.depseq = :depseq"),
    @NamedQuery(name = "Arobljp.findByDepline", query = "SELECT a FROM Arobljp a WHERE a.depline = :depline"),
    @NamedQuery(name = "Arobljp.findByDatermit", query = "SELECT a FROM Arobljp a WHERE a.datermit = :datermit"),
    @NamedQuery(name = "Arobljp.findByPymcuid", query = "SELECT a FROM Arobljp a WHERE a.pymcuid = :pymcuid"),
    @NamedQuery(name = "Arobljp.findByIdmemoxref", query = "SELECT a FROM Arobljp a WHERE a.idmemoxref = :idmemoxref"),
    @NamedQuery(name = "Arobljp.findByFiscyr", query = "SELECT a FROM Arobljp a WHERE a.fiscyr = :fiscyr"),
    @NamedQuery(name = "Arobljp.findByFiscper", query = "SELECT a FROM Arobljp a WHERE a.fiscper = :fiscper")})
public class Arobljp implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArobljpPK arobljpPK;
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
    @Column(name = "TRANSNBR")
    private int transnbr;
    @Basic(optional = false)
    @Column(name = "DATEBUS")
    private int datebus;
    @Basic(optional = false)
    @Column(name = "TRANSTYPE")
    private short transtype;
    @Basic(optional = false)
    @Column(name = "TRXTYPE")
    private short trxtype;
    @Basic(optional = false)
    @Column(name = "TYPEBTCH")
    private String typebtch;
    @Basic(optional = false)
    @Column(name = "CNTBTCH")
    private int cntbtch;
    @Basic(optional = false)
    @Column(name = "CNTITEM")
    private int cntitem;
    @Basic(optional = false)
    @Column(name = "DATEBTCH")
    private int datebtch;
    @Basic(optional = false)
    @Column(name = "AMTPAYMHC")
    private BigDecimal amtpaymhc;
    @Basic(optional = false)
    @Column(name = "AMTPAYMTC")
    private BigDecimal amtpaymtc;
    @Basic(optional = false)
    @Column(name = "TXTOTRTHC")
    private BigDecimal txtotrthc;
    @Basic(optional = false)
    @Column(name = "TXTOTRTTC")
    private BigDecimal txtotrttc;
    @Basic(optional = false)
    @Column(name = "CODECURN")
    private String codecurn;
    @Basic(optional = false)
    @Column(name = "IDRATETYPE")
    private String idratetype;
    @Basic(optional = false)
    @Column(name = "RATEEXCHHC")
    private BigDecimal rateexchhc;
    @Basic(optional = false)
    @Column(name = "RATEDATE")
    private int ratedate;
    @Basic(optional = false)
    @Column(name = "RATEOP")
    private short rateop;
    @Basic(optional = false)
    @Column(name = "IDBANK")
    private String idbank;
    @Basic(optional = false)
    @Column(name = "IDCUSTRMIT")
    private String idcustrmit;
    @Basic(optional = false)
    @Column(name = "IDRMIT")
    private String idrmit;
    @Basic(optional = false)
    @Column(name = "DEPSEQ")
    private int depseq;
    @Basic(optional = false)
    @Column(name = "DEPLINE")
    private int depline;
    @Basic(optional = false)
    @Column(name = "DATERMIT")
    private int datermit;
    @Basic(optional = false)
    @Column(name = "PYMCUID")
    private int pymcuid;
    @Basic(optional = false)
    @Column(name = "IDMEMOXREF")
    private String idmemoxref;
    @Basic(optional = false)
    @Column(name = "FISCYR")
    private String fiscyr;
    @Basic(optional = false)
    @Column(name = "FISCPER")
    private String fiscper;

    public Arobljp() {
    }

    public Arobljp(ArobljpPK arobljpPK) {
        this.arobljpPK = arobljpPK;
    }

    public Arobljp(ArobljpPK arobljpPK, int audtdate, int audttime, String audtuser, String audtorg, int transnbr, int datebus, short transtype, short trxtype, String typebtch, int cntbtch, int cntitem, int datebtch, BigDecimal amtpaymhc, BigDecimal amtpaymtc, BigDecimal txtotrthc, BigDecimal txtotrttc, String codecurn, String idratetype, BigDecimal rateexchhc, int ratedate, short rateop, String idbank, String idcustrmit, String idrmit, int depseq, int depline, int datermit, int pymcuid, String idmemoxref, String fiscyr, String fiscper) {
        this.arobljpPK = arobljpPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.transnbr = transnbr;
        this.datebus = datebus;
        this.transtype = transtype;
        this.trxtype = trxtype;
        this.typebtch = typebtch;
        this.cntbtch = cntbtch;
        this.cntitem = cntitem;
        this.datebtch = datebtch;
        this.amtpaymhc = amtpaymhc;
        this.amtpaymtc = amtpaymtc;
        this.txtotrthc = txtotrthc;
        this.txtotrttc = txtotrttc;
        this.codecurn = codecurn;
        this.idratetype = idratetype;
        this.rateexchhc = rateexchhc;
        this.ratedate = ratedate;
        this.rateop = rateop;
        this.idbank = idbank;
        this.idcustrmit = idcustrmit;
        this.idrmit = idrmit;
        this.depseq = depseq;
        this.depline = depline;
        this.datermit = datermit;
        this.pymcuid = pymcuid;
        this.idmemoxref = idmemoxref;
        this.fiscyr = fiscyr;
        this.fiscper = fiscper;
    }

    public Arobljp(String idcust, String idinvc, int cntline, int cntseqence) {
        this.arobljpPK = new ArobljpPK(idcust, idinvc, cntline, cntseqence);
    }

    public ArobljpPK getArobljpPK() {
        return arobljpPK;
    }

    public void setArobljpPK(ArobljpPK arobljpPK) {
        this.arobljpPK = arobljpPK;
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

    public int getTransnbr() {
        return transnbr;
    }

    public void setTransnbr(int transnbr) {
        this.transnbr = transnbr;
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

    public short getTrxtype() {
        return trxtype;
    }

    public void setTrxtype(short trxtype) {
        this.trxtype = trxtype;
    }

    public String getTypebtch() {
        return typebtch;
    }

    public void setTypebtch(String typebtch) {
        this.typebtch = typebtch;
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

    public int getDatebtch() {
        return datebtch;
    }

    public void setDatebtch(int datebtch) {
        this.datebtch = datebtch;
    }

    public BigDecimal getAmtpaymhc() {
        return amtpaymhc;
    }

    public void setAmtpaymhc(BigDecimal amtpaymhc) {
        this.amtpaymhc = amtpaymhc;
    }

    public BigDecimal getAmtpaymtc() {
        return amtpaymtc;
    }

    public void setAmtpaymtc(BigDecimal amtpaymtc) {
        this.amtpaymtc = amtpaymtc;
    }

    public BigDecimal getTxtotrthc() {
        return txtotrthc;
    }

    public void setTxtotrthc(BigDecimal txtotrthc) {
        this.txtotrthc = txtotrthc;
    }

    public BigDecimal getTxtotrttc() {
        return txtotrttc;
    }

    public void setTxtotrttc(BigDecimal txtotrttc) {
        this.txtotrttc = txtotrttc;
    }

    public String getCodecurn() {
        return codecurn;
    }

    public void setCodecurn(String codecurn) {
        this.codecurn = codecurn;
    }

    public String getIdratetype() {
        return idratetype;
    }

    public void setIdratetype(String idratetype) {
        this.idratetype = idratetype;
    }

    public BigDecimal getRateexchhc() {
        return rateexchhc;
    }

    public void setRateexchhc(BigDecimal rateexchhc) {
        this.rateexchhc = rateexchhc;
    }

    public int getRatedate() {
        return ratedate;
    }

    public void setRatedate(int ratedate) {
        this.ratedate = ratedate;
    }

    public short getRateop() {
        return rateop;
    }

    public void setRateop(short rateop) {
        this.rateop = rateop;
    }

    public String getIdbank() {
        return idbank;
    }

    public void setIdbank(String idbank) {
        this.idbank = idbank;
    }

    public String getIdcustrmit() {
        return idcustrmit;
    }

    public void setIdcustrmit(String idcustrmit) {
        this.idcustrmit = idcustrmit;
    }

    public String getIdrmit() {
        return idrmit;
    }

    public void setIdrmit(String idrmit) {
        this.idrmit = idrmit;
    }

    public int getDepseq() {
        return depseq;
    }

    public void setDepseq(int depseq) {
        this.depseq = depseq;
    }

    public int getDepline() {
        return depline;
    }

    public void setDepline(int depline) {
        this.depline = depline;
    }

    public int getDatermit() {
        return datermit;
    }

    public void setDatermit(int datermit) {
        this.datermit = datermit;
    }

    public int getPymcuid() {
        return pymcuid;
    }

    public void setPymcuid(int pymcuid) {
        this.pymcuid = pymcuid;
    }

    public String getIdmemoxref() {
        return idmemoxref;
    }

    public void setIdmemoxref(String idmemoxref) {
        this.idmemoxref = idmemoxref;
    }

    public String getFiscyr() {
        return fiscyr;
    }

    public void setFiscyr(String fiscyr) {
        this.fiscyr = fiscyr;
    }

    public String getFiscper() {
        return fiscper;
    }

    public void setFiscper(String fiscper) {
        this.fiscper = fiscper;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arobljpPK != null ? arobljpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arobljp)) {
            return false;
        }
        Arobljp other = (Arobljp) object;
        if ((this.arobljpPK == null && other.arobljpPK != null) || (this.arobljpPK != null && !this.arobljpPK.equals(other.arobljpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Arobljp[arobljpPK=" + arobljpPK + "]";
    }

}
