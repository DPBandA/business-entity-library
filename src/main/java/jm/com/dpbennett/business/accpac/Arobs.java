/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpbennett.business.accpac;

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
@Table(name = "AROBS")
@NamedQueries({
    @NamedQuery(name = "Arobs.findAll", query = "SELECT a FROM Arobs a"),
    @NamedQuery(name = "Arobs.findByIdcust", query = "SELECT a FROM Arobs a WHERE a.arobsPK.idcust = :idcust"),
    @NamedQuery(name = "Arobs.findByIdinvc", query = "SELECT a FROM Arobs a WHERE a.arobsPK.idinvc = :idinvc"),
    @NamedQuery(name = "Arobs.findByCntpaym", query = "SELECT a FROM Arobs a WHERE a.arobsPK.cntpaym = :cntpaym"),
    @NamedQuery(name = "Arobs.findByAudtdate", query = "SELECT a FROM Arobs a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Arobs.findByAudttime", query = "SELECT a FROM Arobs a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Arobs.findByAudtuser", query = "SELECT a FROM Arobs a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Arobs.findByAudtorg", query = "SELECT a FROM Arobs a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Arobs.findByIdrmit", query = "SELECT a FROM Arobs a WHERE a.idrmit = :idrmit"),
    @NamedQuery(name = "Arobs.findByDatedue", query = "SELECT a FROM Arobs a WHERE a.datedue = :datedue"),
    @NamedQuery(name = "Arobs.findByDatedisc", query = "SELECT a FROM Arobs a WHERE a.datedisc = :datedisc"),
    @NamedQuery(name = "Arobs.findBySwpaid", query = "SELECT a FROM Arobs a WHERE a.swpaid = :swpaid"),
    @NamedQuery(name = "Arobs.findByDlnqstts", query = "SELECT a FROM Arobs a WHERE a.dlnqstts = :dlnqstts"),
    @NamedQuery(name = "Arobs.findByAmtduehc", query = "SELECT a FROM Arobs a WHERE a.amtduehc = :amtduehc"),
    @NamedQuery(name = "Arobs.findByAmtdischc", query = "SELECT a FROM Arobs a WHERE a.amtdischc = :amtdischc"),
    @NamedQuery(name = "Arobs.findByAmtdcsrmhc", query = "SELECT a FROM Arobs a WHERE a.amtdcsrmhc = :amtdcsrmhc"),
    @NamedQuery(name = "Arobs.findByAmtpymrmhc", query = "SELECT a FROM Arobs a WHERE a.amtpymrmhc = :amtpymrmhc"),
    @NamedQuery(name = "Arobs.findByAmtduetc", query = "SELECT a FROM Arobs a WHERE a.amtduetc = :amtduetc"),
    @NamedQuery(name = "Arobs.findByAmtdisctc", query = "SELECT a FROM Arobs a WHERE a.amtdisctc = :amtdisctc"),
    @NamedQuery(name = "Arobs.findByAmtdscrmtc", query = "SELECT a FROM Arobs a WHERE a.amtdscrmtc = :amtdscrmtc"),
    @NamedQuery(name = "Arobs.findByAmtpymrmtc", query = "SELECT a FROM Arobs a WHERE a.amtpymrmtc = :amtpymrmtc"),
    @NamedQuery(name = "Arobs.findByIdordrnbr", query = "SELECT a FROM Arobs a WHERE a.idordrnbr = :idordrnbr"),
    @NamedQuery(name = "Arobs.findByIdcustpo", query = "SELECT a FROM Arobs a WHERE a.idcustpo = :idcustpo"),
    @NamedQuery(name = "Arobs.findByIdnatacct", query = "SELECT a FROM Arobs a WHERE a.idnatacct = :idnatacct"),
    @NamedQuery(name = "Arobs.findByIdgrp", query = "SELECT a FROM Arobs a WHERE a.idgrp = :idgrp"),
    @NamedQuery(name = "Arobs.findByIdprepaid", query = "SELECT a FROM Arobs a WHERE a.idprepaid = :idprepaid"),
    @NamedQuery(name = "Arobs.findByIdtrxtype", query = "SELECT a FROM Arobs a WHERE a.idtrxtype = :idtrxtype"),
    @NamedQuery(name = "Arobs.findByTxttrxtype", query = "SELECT a FROM Arobs a WHERE a.txttrxtype = :txttrxtype"),
    @NamedQuery(name = "Arobs.findByDateinvc", query = "SELECT a FROM Arobs a WHERE a.dateinvc = :dateinvc"),
    @NamedQuery(name = "Arobs.findByDaystopay", query = "SELECT a FROM Arobs a WHERE a.daystopay = :daystopay"),
    @NamedQuery(name = "Arobs.findBySwjob", query = "SELECT a FROM Arobs a WHERE a.swjob = :swjob"),
    @NamedQuery(name = "Arobs.findByIdshipnbr", query = "SELECT a FROM Arobs a WHERE a.idshipnbr = :idshipnbr"),
    @NamedQuery(name = "Arobs.findByRtgapplyto", query = "SELECT a FROM Arobs a WHERE a.rtgapplyto = :rtgapplyto")})
public class Arobs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArobsPK arobsPK;
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
    @Column(name = "IDRMIT")
    private String idrmit;
    @Basic(optional = false)
    @Column(name = "DATEDUE")
    private int datedue;
    @Basic(optional = false)
    @Column(name = "DATEDISC")
    private int datedisc;
    @Basic(optional = false)
    @Column(name = "SWPAID")
    private short swpaid;
    @Basic(optional = false)
    @Column(name = "DLNQSTTS")
    private short dlnqstts;
    @Basic(optional = false)
    @Column(name = "AMTDUEHC")
    private BigDecimal amtduehc;
    @Basic(optional = false)
    @Column(name = "AMTDISCHC")
    private BigDecimal amtdischc;
    @Basic(optional = false)
    @Column(name = "AMTDCSRMHC")
    private BigDecimal amtdcsrmhc;
    @Basic(optional = false)
    @Column(name = "AMTPYMRMHC")
    private BigDecimal amtpymrmhc;
    @Basic(optional = false)
    @Column(name = "AMTDUETC")
    private BigDecimal amtduetc;
    @Basic(optional = false)
    @Column(name = "AMTDISCTC")
    private BigDecimal amtdisctc;
    @Basic(optional = false)
    @Column(name = "AMTDSCRMTC")
    private BigDecimal amtdscrmtc;
    @Basic(optional = false)
    @Column(name = "AMTPYMRMTC")
    private BigDecimal amtpymrmtc;
    @Basic(optional = false)
    @Column(name = "IDORDRNBR")
    private String idordrnbr;
    @Basic(optional = false)
    @Column(name = "IDCUSTPO")
    private String idcustpo;
    @Basic(optional = false)
    @Column(name = "IDNATACCT")
    private String idnatacct;
    @Basic(optional = false)
    @Column(name = "IDGRP")
    private String idgrp;
    @Basic(optional = false)
    @Column(name = "IDPREPAID")
    private String idprepaid;
    @Basic(optional = false)
    @Column(name = "IDTRXTYPE")
    private short idtrxtype;
    @Basic(optional = false)
    @Column(name = "TXTTRXTYPE")
    private short txttrxtype;
    @Basic(optional = false)
    @Column(name = "DATEINVC")
    private int dateinvc;
    @Basic(optional = false)
    @Column(name = "DAYSTOPAY")
    private short daystopay;
    @Basic(optional = false)
    @Column(name = "SWJOB")
    private short swjob;
    @Basic(optional = false)
    @Column(name = "IDSHIPNBR")
    private String idshipnbr;
    @Basic(optional = false)
    @Column(name = "RTGAPPLYTO")
    private String rtgapplyto;

    public Arobs() {
    }

    public Arobs(ArobsPK arobsPK) {
        this.arobsPK = arobsPK;
    }

    public Arobs(ArobsPK arobsPK, int audtdate, int audttime, String audtuser, String audtorg, String idrmit, int datedue, int datedisc, short swpaid, short dlnqstts, BigDecimal amtduehc, BigDecimal amtdischc, BigDecimal amtdcsrmhc, BigDecimal amtpymrmhc, BigDecimal amtduetc, BigDecimal amtdisctc, BigDecimal amtdscrmtc, BigDecimal amtpymrmtc, String idordrnbr, String idcustpo, String idnatacct, String idgrp, String idprepaid, short idtrxtype, short txttrxtype, int dateinvc, short daystopay, short swjob, String idshipnbr, String rtgapplyto) {
        this.arobsPK = arobsPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.idrmit = idrmit;
        this.datedue = datedue;
        this.datedisc = datedisc;
        this.swpaid = swpaid;
        this.dlnqstts = dlnqstts;
        this.amtduehc = amtduehc;
        this.amtdischc = amtdischc;
        this.amtdcsrmhc = amtdcsrmhc;
        this.amtpymrmhc = amtpymrmhc;
        this.amtduetc = amtduetc;
        this.amtdisctc = amtdisctc;
        this.amtdscrmtc = amtdscrmtc;
        this.amtpymrmtc = amtpymrmtc;
        this.idordrnbr = idordrnbr;
        this.idcustpo = idcustpo;
        this.idnatacct = idnatacct;
        this.idgrp = idgrp;
        this.idprepaid = idprepaid;
        this.idtrxtype = idtrxtype;
        this.txttrxtype = txttrxtype;
        this.dateinvc = dateinvc;
        this.daystopay = daystopay;
        this.swjob = swjob;
        this.idshipnbr = idshipnbr;
        this.rtgapplyto = rtgapplyto;
    }

    public Arobs(String idcust, String idinvc, int cntpaym) {
        this.arobsPK = new ArobsPK(idcust, idinvc, cntpaym);
    }

    public ArobsPK getArobsPK() {
        return arobsPK;
    }

    public void setArobsPK(ArobsPK arobsPK) {
        this.arobsPK = arobsPK;
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

    public String getIdrmit() {
        return idrmit;
    }

    public void setIdrmit(String idrmit) {
        this.idrmit = idrmit;
    }

    public int getDatedue() {
        return datedue;
    }

    public void setDatedue(int datedue) {
        this.datedue = datedue;
    }

    public int getDatedisc() {
        return datedisc;
    }

    public void setDatedisc(int datedisc) {
        this.datedisc = datedisc;
    }

    public short getSwpaid() {
        return swpaid;
    }

    public void setSwpaid(short swpaid) {
        this.swpaid = swpaid;
    }

    public short getDlnqstts() {
        return dlnqstts;
    }

    public void setDlnqstts(short dlnqstts) {
        this.dlnqstts = dlnqstts;
    }

    public BigDecimal getAmtduehc() {
        return amtduehc;
    }

    public void setAmtduehc(BigDecimal amtduehc) {
        this.amtduehc = amtduehc;
    }

    public BigDecimal getAmtdischc() {
        return amtdischc;
    }

    public void setAmtdischc(BigDecimal amtdischc) {
        this.amtdischc = amtdischc;
    }

    public BigDecimal getAmtdcsrmhc() {
        return amtdcsrmhc;
    }

    public void setAmtdcsrmhc(BigDecimal amtdcsrmhc) {
        this.amtdcsrmhc = amtdcsrmhc;
    }

    public BigDecimal getAmtpymrmhc() {
        return amtpymrmhc;
    }

    public void setAmtpymrmhc(BigDecimal amtpymrmhc) {
        this.amtpymrmhc = amtpymrmhc;
    }

    public BigDecimal getAmtduetc() {
        return amtduetc;
    }

    public void setAmtduetc(BigDecimal amtduetc) {
        this.amtduetc = amtduetc;
    }

    public BigDecimal getAmtdisctc() {
        return amtdisctc;
    }

    public void setAmtdisctc(BigDecimal amtdisctc) {
        this.amtdisctc = amtdisctc;
    }

    public BigDecimal getAmtdscrmtc() {
        return amtdscrmtc;
    }

    public void setAmtdscrmtc(BigDecimal amtdscrmtc) {
        this.amtdscrmtc = amtdscrmtc;
    }

    public BigDecimal getAmtpymrmtc() {
        return amtpymrmtc;
    }

    public void setAmtpymrmtc(BigDecimal amtpymrmtc) {
        this.amtpymrmtc = amtpymrmtc;
    }

    public String getIdordrnbr() {
        return idordrnbr;
    }

    public void setIdordrnbr(String idordrnbr) {
        this.idordrnbr = idordrnbr;
    }

    public String getIdcustpo() {
        return idcustpo;
    }

    public void setIdcustpo(String idcustpo) {
        this.idcustpo = idcustpo;
    }

    public String getIdnatacct() {
        return idnatacct;
    }

    public void setIdnatacct(String idnatacct) {
        this.idnatacct = idnatacct;
    }

    public String getIdgrp() {
        return idgrp;
    }

    public void setIdgrp(String idgrp) {
        this.idgrp = idgrp;
    }

    public String getIdprepaid() {
        return idprepaid;
    }

    public void setIdprepaid(String idprepaid) {
        this.idprepaid = idprepaid;
    }

    public short getIdtrxtype() {
        return idtrxtype;
    }

    public void setIdtrxtype(short idtrxtype) {
        this.idtrxtype = idtrxtype;
    }

    public short getTxttrxtype() {
        return txttrxtype;
    }

    public void setTxttrxtype(short txttrxtype) {
        this.txttrxtype = txttrxtype;
    }

    public int getDateinvc() {
        return dateinvc;
    }

    public void setDateinvc(int dateinvc) {
        this.dateinvc = dateinvc;
    }

    public short getDaystopay() {
        return daystopay;
    }

    public void setDaystopay(short daystopay) {
        this.daystopay = daystopay;
    }

    public short getSwjob() {
        return swjob;
    }

    public void setSwjob(short swjob) {
        this.swjob = swjob;
    }

    public String getIdshipnbr() {
        return idshipnbr;
    }

    public void setIdshipnbr(String idshipnbr) {
        this.idshipnbr = idshipnbr;
    }

    public String getRtgapplyto() {
        return rtgapplyto;
    }

    public void setRtgapplyto(String rtgapplyto) {
        this.rtgapplyto = rtgapplyto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arobsPK != null ? arobsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arobs)) {
            return false;
        }
        Arobs other = (Arobs) object;
        if ((this.arobsPK == null && other.arobsPK != null) || (this.arobsPK != null && !this.arobsPK.equals(other.arobsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Arobs[arobsPK=" + arobsPK + "]";
    }

}
