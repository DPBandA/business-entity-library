/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "AROBP")
@NamedQueries({
    @NamedQuery(name = "Arobp.findAll", query = "SELECT a FROM Arobp a"),
    @NamedQuery(name = "Arobp.findByIdcust", query = "SELECT a FROM Arobp a WHERE a.arobpPK.idcust = :idcust"),
    @NamedQuery(name = "Arobp.findByIdinvc", query = "SELECT a FROM Arobp a WHERE a.arobpPK.idinvc = :idinvc"),
    @NamedQuery(name = "Arobp.findByCntpaymnbr", query = "SELECT a FROM Arobp a WHERE a.arobpPK.cntpaymnbr = :cntpaymnbr"),
    @NamedQuery(name = "Arobp.findByIdrmit", query = "SELECT a FROM Arobp a WHERE a.arobpPK.idrmit = :idrmit"),
    @NamedQuery(name = "Arobp.findByDatebus", query = "SELECT a FROM Arobp a WHERE a.arobpPK.datebus = :datebus"),
    @NamedQuery(name = "Arobp.findByTranstype", query = "SELECT a FROM Arobp a WHERE a.arobpPK.transtype = :transtype"),
    @NamedQuery(name = "Arobp.findByCntseqnce", query = "SELECT a FROM Arobp a WHERE a.arobpPK.cntseqnce = :cntseqnce"),
    @NamedQuery(name = "Arobp.findByAudtdate", query = "SELECT a FROM Arobp a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Arobp.findByAudttime", query = "SELECT a FROM Arobp a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Arobp.findByAudtuser", query = "SELECT a FROM Arobp a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Arobp.findByAudtorg", query = "SELECT a FROM Arobp a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Arobp.findByDepstnbr", query = "SELECT a FROM Arobp a WHERE a.depstnbr = :depstnbr"),
    @NamedQuery(name = "Arobp.findByCntbtch", query = "SELECT a FROM Arobp a WHERE a.cntbtch = :cntbtch"),
    @NamedQuery(name = "Arobp.findByDatebtch", query = "SELECT a FROM Arobp a WHERE a.datebtch = :datebtch"),
    @NamedQuery(name = "Arobp.findByAmtpaymhc", query = "SELECT a FROM Arobp a WHERE a.amtpaymhc = :amtpaymhc"),
    @NamedQuery(name = "Arobp.findByAmtpaymtc", query = "SELECT a FROM Arobp a WHERE a.amtpaymtc = :amtpaymtc"),
    @NamedQuery(name = "Arobp.findByCodecurn", query = "SELECT a FROM Arobp a WHERE a.codecurn = :codecurn"),
    @NamedQuery(name = "Arobp.findByIdratetype", query = "SELECT a FROM Arobp a WHERE a.idratetype = :idratetype"),
    @NamedQuery(name = "Arobp.findByRateexchhc", query = "SELECT a FROM Arobp a WHERE a.rateexchhc = :rateexchhc"),
    @NamedQuery(name = "Arobp.findBySwovrdrate", query = "SELECT a FROM Arobp a WHERE a.swovrdrate = :swovrdrate"),
    @NamedQuery(name = "Arobp.findByIdbank", query = "SELECT a FROM Arobp a WHERE a.idbank = :idbank"),
    @NamedQuery(name = "Arobp.findByTrxtype", query = "SELECT a FROM Arobp a WHERE a.trxtype = :trxtype"),
    @NamedQuery(name = "Arobp.findByIdmemoxref", query = "SELECT a FROM Arobp a WHERE a.idmemoxref = :idmemoxref"),
    @NamedQuery(name = "Arobp.findBySwinvcdel", query = "SELECT a FROM Arobp a WHERE a.swinvcdel = :swinvcdel"),
    @NamedQuery(name = "Arobp.findByDatelststm", query = "SELECT a FROM Arobp a WHERE a.datelststm = :datelststm"),
    @NamedQuery(name = "Arobp.findByIdprepaid", query = "SELECT a FROM Arobp a WHERE a.idprepaid = :idprepaid"),
    @NamedQuery(name = "Arobp.findByIdcustrmit", query = "SELECT a FROM Arobp a WHERE a.idcustrmit = :idcustrmit"),
    @NamedQuery(name = "Arobp.findByDatermit", query = "SELECT a FROM Arobp a WHERE a.datermit = :datermit"),
    @NamedQuery(name = "Arobp.findByCntitem", query = "SELECT a FROM Arobp a WHERE a.cntitem = :cntitem"),
    @NamedQuery(name = "Arobp.findByFiscyr", query = "SELECT a FROM Arobp a WHERE a.fiscyr = :fiscyr"),
    @NamedQuery(name = "Arobp.findByFiscper", query = "SELECT a FROM Arobp a WHERE a.fiscper = :fiscper"),
    @NamedQuery(name = "Arobp.findByRatedate", query = "SELECT a FROM Arobp a WHERE a.ratedate = :ratedate"),
    @NamedQuery(name = "Arobp.findByRateop", query = "SELECT a FROM Arobp a WHERE a.rateop = :rateop"),
    @NamedQuery(name = "Arobp.findByStmtseq", query = "SELECT a FROM Arobp a WHERE a.stmtseq = :stmtseq"),
    @NamedQuery(name = "Arobp.findByPymcuid", query = "SELECT a FROM Arobp a WHERE a.pymcuid = :pymcuid"),
    @NamedQuery(name = "Arobp.findByDepseq", query = "SELECT a FROM Arobp a WHERE a.depseq = :depseq"),
    @NamedQuery(name = "Arobp.findByDepline", query = "SELECT a FROM Arobp a WHERE a.depline = :depline")})
public class Arobp implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArobpPK arobpPK;
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
    @Column(name = "DEPSTNBR")
    private int depstnbr;
    @Basic(optional = false)
    @Column(name = "CNTBTCH")
    private int cntbtch;
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
    @Column(name = "CODECURN")
    private String codecurn;
    @Basic(optional = false)
    @Column(name = "IDRATETYPE")
    private String idratetype;
    @Basic(optional = false)
    @Column(name = "RATEEXCHHC")
    private BigDecimal rateexchhc;
    @Basic(optional = false)
    @Column(name = "SWOVRDRATE")
    private short swovrdrate;
    @Basic(optional = false)
    @Column(name = "IDBANK")
    private String idbank;
    @Basic(optional = false)
    @Column(name = "TRXTYPE")
    private short trxtype;
    @Basic(optional = false)
    @Column(name = "IDMEMOXREF")
    private String idmemoxref;
    @Basic(optional = false)
    @Column(name = "SWINVCDEL")
    private short swinvcdel;
    @Basic(optional = false)
    @Column(name = "DATELSTSTM")
    private int datelststm;
    @Basic(optional = false)
    @Column(name = "IDPREPAID")
    private String idprepaid;
    @Basic(optional = false)
    @Column(name = "IDCUSTRMIT")
    private String idcustrmit;
    @Basic(optional = false)
    @Column(name = "DATERMIT")
    private int datermit;
    @Basic(optional = false)
    @Column(name = "CNTITEM")
    private int cntitem;
    @Basic(optional = false)
    @Column(name = "FISCYR")
    private String fiscyr;
    @Basic(optional = false)
    @Column(name = "FISCPER")
    private String fiscper;
    @Basic(optional = false)
    @Column(name = "RATEDATE")
    private int ratedate;
    @Basic(optional = false)
    @Column(name = "RATEOP")
    private short rateop;
    @Basic(optional = false)
    @Column(name = "STMTSEQ")
    private int stmtseq;
    @Basic(optional = false)
    @Column(name = "PYMCUID")
    private int pymcuid;
    @Basic(optional = false)
    @Column(name = "DEPSEQ")
    private int depseq;
    @Basic(optional = false)
    @Column(name = "DEPLINE")
    private int depline;

    public Arobp() {
    }

    public Arobp(ArobpPK arobpPK) {
        this.arobpPK = arobpPK;
    }

    public Arobp(ArobpPK arobpPK, int audtdate, int audttime, String audtuser, String audtorg, int depstnbr, int cntbtch, int datebtch, BigDecimal amtpaymhc, BigDecimal amtpaymtc, String codecurn, String idratetype, BigDecimal rateexchhc, short swovrdrate, String idbank, short trxtype, String idmemoxref, short swinvcdel, int datelststm, String idprepaid, String idcustrmit, int datermit, int cntitem, String fiscyr, String fiscper, int ratedate, short rateop, int stmtseq, int pymcuid, int depseq, int depline) {
        this.arobpPK = arobpPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.depstnbr = depstnbr;
        this.cntbtch = cntbtch;
        this.datebtch = datebtch;
        this.amtpaymhc = amtpaymhc;
        this.amtpaymtc = amtpaymtc;
        this.codecurn = codecurn;
        this.idratetype = idratetype;
        this.rateexchhc = rateexchhc;
        this.swovrdrate = swovrdrate;
        this.idbank = idbank;
        this.trxtype = trxtype;
        this.idmemoxref = idmemoxref;
        this.swinvcdel = swinvcdel;
        this.datelststm = datelststm;
        this.idprepaid = idprepaid;
        this.idcustrmit = idcustrmit;
        this.datermit = datermit;
        this.cntitem = cntitem;
        this.fiscyr = fiscyr;
        this.fiscper = fiscper;
        this.ratedate = ratedate;
        this.rateop = rateop;
        this.stmtseq = stmtseq;
        this.pymcuid = pymcuid;
        this.depseq = depseq;
        this.depline = depline;
    }

    public Arobp(String idcust, String idinvc, int cntpaymnbr, String idrmit, int datebus, short transtype, int cntseqnce) {
        this.arobpPK = new ArobpPK(idcust, idinvc, cntpaymnbr, idrmit, datebus, transtype, cntseqnce);
    }

    public ArobpPK getArobpPK() {
        return arobpPK;
    }

    public void setArobpPK(ArobpPK arobpPK) {
        this.arobpPK = arobpPK;
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

    public int getDepstnbr() {
        return depstnbr;
    }

    public void setDepstnbr(int depstnbr) {
        this.depstnbr = depstnbr;
    }

    public int getCntbtch() {
        return cntbtch;
    }

    public void setCntbtch(int cntbtch) {
        this.cntbtch = cntbtch;
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

    public short getSwovrdrate() {
        return swovrdrate;
    }

    public void setSwovrdrate(short swovrdrate) {
        this.swovrdrate = swovrdrate;
    }

    public String getIdbank() {
        return idbank;
    }

    public void setIdbank(String idbank) {
        this.idbank = idbank;
    }

    public short getTrxtype() {
        return trxtype;
    }

    public void setTrxtype(short trxtype) {
        this.trxtype = trxtype;
    }

    public String getIdmemoxref() {
        return idmemoxref;
    }

    public void setIdmemoxref(String idmemoxref) {
        this.idmemoxref = idmemoxref;
    }

    public short getSwinvcdel() {
        return swinvcdel;
    }

    public void setSwinvcdel(short swinvcdel) {
        this.swinvcdel = swinvcdel;
    }

    public int getDatelststm() {
        return datelststm;
    }

    public void setDatelststm(int datelststm) {
        this.datelststm = datelststm;
    }

    public String getIdprepaid() {
        return idprepaid;
    }

    public void setIdprepaid(String idprepaid) {
        this.idprepaid = idprepaid;
    }

    public String getIdcustrmit() {
        return idcustrmit;
    }

    public void setIdcustrmit(String idcustrmit) {
        this.idcustrmit = idcustrmit;
    }

    public int getDatermit() {
        return datermit;
    }

    public void setDatermit(int datermit) {
        this.datermit = datermit;
    }

    public int getCntitem() {
        return cntitem;
    }

    public void setCntitem(int cntitem) {
        this.cntitem = cntitem;
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

    public int getStmtseq() {
        return stmtseq;
    }

    public void setStmtseq(int stmtseq) {
        this.stmtseq = stmtseq;
    }

    public int getPymcuid() {
        return pymcuid;
    }

    public void setPymcuid(int pymcuid) {
        this.pymcuid = pymcuid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arobpPK != null ? arobpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arobp)) {
            return false;
        }
        Arobp other = (Arobp) object;
        if ((this.arobpPK == null && other.arobpPK != null) || (this.arobpPK != null && !this.arobpPK.equals(other.arobpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Arobp[arobpPK=" + arobpPK + "]";
    }

}
