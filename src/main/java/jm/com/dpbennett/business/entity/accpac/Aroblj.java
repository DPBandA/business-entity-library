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
@Table(name = "AROBLJ")
@NamedQueries({
    @NamedQuery(name = "Aroblj.findAll", query = "SELECT a FROM Aroblj a"),
    @NamedQuery(name = "Aroblj.findByIdcust", query = "SELECT a FROM Aroblj a WHERE a.arobljPK.idcust = :idcust"),
    @NamedQuery(name = "Aroblj.findByIdinvc", query = "SELECT a FROM Aroblj a WHERE a.arobljPK.idinvc = :idinvc"),
    @NamedQuery(name = "Aroblj.findByCntline", query = "SELECT a FROM Aroblj a WHERE a.arobljPK.cntline = :cntline"),
    @NamedQuery(name = "Aroblj.findByAudtdate", query = "SELECT a FROM Aroblj a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Aroblj.findByAudttime", query = "SELECT a FROM Aroblj a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Aroblj.findByAudtuser", query = "SELECT a FROM Aroblj a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Aroblj.findByAudtorg", query = "SELECT a FROM Aroblj a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Aroblj.findByContract", query = "SELECT a FROM Aroblj a WHERE a.contract = :contract"),
    @NamedQuery(name = "Aroblj.findByProject", query = "SELECT a FROM Aroblj a WHERE a.project = :project"),
    @NamedQuery(name = "Aroblj.findByCategory", query = "SELECT a FROM Aroblj a WHERE a.category = :category"),
    @NamedQuery(name = "Aroblj.findByResource", query = "SELECT a FROM Aroblj a WHERE a.resource = :resource"),
    @NamedQuery(name = "Aroblj.findByTransnbr", query = "SELECT a FROM Aroblj a WHERE a.transnbr = :transnbr"),
    @NamedQuery(name = "Aroblj.findByCostclass", query = "SELECT a FROM Aroblj a WHERE a.costclass = :costclass"),
    @NamedQuery(name = "Aroblj.findByIddist", query = "SELECT a FROM Aroblj a WHERE a.iddist = :iddist"),
    @NamedQuery(name = "Aroblj.findByIdglacct", query = "SELECT a FROM Aroblj a WHERE a.idglacct = :idglacct"),
    @NamedQuery(name = "Aroblj.findByAmtinvctc", query = "SELECT a FROM Aroblj a WHERE a.amtinvctc = :amtinvctc"),
    @NamedQuery(name = "Aroblj.findByAmtduetc", query = "SELECT a FROM Aroblj a WHERE a.amtduetc = :amtduetc"),
    @NamedQuery(name = "Aroblj.findByAmtinvchc", query = "SELECT a FROM Aroblj a WHERE a.amtinvchc = :amtinvchc"),
    @NamedQuery(name = "Aroblj.findByAmtduehc", query = "SELECT a FROM Aroblj a WHERE a.amtduehc = :amtduehc"),
    @NamedQuery(name = "Aroblj.findByIditem", query = "SELECT a FROM Aroblj a WHERE a.iditem = :iditem"),
    @NamedQuery(name = "Aroblj.findByUnitmeas", query = "SELECT a FROM Aroblj a WHERE a.unitmeas = :unitmeas"),
    @NamedQuery(name = "Aroblj.findByQtyinvc", query = "SELECT a FROM Aroblj a WHERE a.qtyinvc = :qtyinvc"),
    @NamedQuery(name = "Aroblj.findByAmtcost", query = "SELECT a FROM Aroblj a WHERE a.amtcost = :amtcost"),
    @NamedQuery(name = "Aroblj.findByBilldate", query = "SELECT a FROM Aroblj a WHERE a.billdate = :billdate"),
    @NamedQuery(name = "Aroblj.findBySwdiscabl", query = "SELECT a FROM Aroblj a WHERE a.swdiscabl = :swdiscabl"),
    @NamedQuery(name = "Aroblj.findByRtgdatedue", query = "SELECT a FROM Aroblj a WHERE a.rtgdatedue = :rtgdatedue"),
    @NamedQuery(name = "Aroblj.findByRtgoamthc", query = "SELECT a FROM Aroblj a WHERE a.rtgoamthc = :rtgoamthc"),
    @NamedQuery(name = "Aroblj.findByRtgamthc", query = "SELECT a FROM Aroblj a WHERE a.rtgamthc = :rtgamthc"),
    @NamedQuery(name = "Aroblj.findByRtgoamttc", query = "SELECT a FROM Aroblj a WHERE a.rtgoamttc = :rtgoamttc"),
    @NamedQuery(name = "Aroblj.findByRtgamttc", query = "SELECT a FROM Aroblj a WHERE a.rtgamttc = :rtgamttc"),
    @NamedQuery(name = "Aroblj.findByValues", query = "SELECT a FROM Aroblj a WHERE a.values = :values"),
    @NamedQuery(name = "Aroblj.findByRtgdisttc", query = "SELECT a FROM Aroblj a WHERE a.rtgdisttc = :rtgdisttc"),
    @NamedQuery(name = "Aroblj.findByRtgcogstc", query = "SELECT a FROM Aroblj a WHERE a.rtgcogstc = :rtgcogstc"),
    @NamedQuery(name = "Aroblj.findByRtgaltbtc", query = "SELECT a FROM Aroblj a WHERE a.rtgaltbtc = :rtgaltbtc"),
    @NamedQuery(name = "Aroblj.findByTaxclass1", query = "SELECT a FROM Aroblj a WHERE a.taxclass1 = :taxclass1"),
    @NamedQuery(name = "Aroblj.findByTaxclass2", query = "SELECT a FROM Aroblj a WHERE a.taxclass2 = :taxclass2"),
    @NamedQuery(name = "Aroblj.findByTaxclass3", query = "SELECT a FROM Aroblj a WHERE a.taxclass3 = :taxclass3"),
    @NamedQuery(name = "Aroblj.findByTaxclass4", query = "SELECT a FROM Aroblj a WHERE a.taxclass4 = :taxclass4"),
    @NamedQuery(name = "Aroblj.findByTaxclass5", query = "SELECT a FROM Aroblj a WHERE a.taxclass5 = :taxclass5"),
    @NamedQuery(name = "Aroblj.findBySwtaxincl1", query = "SELECT a FROM Aroblj a WHERE a.swtaxincl1 = :swtaxincl1"),
    @NamedQuery(name = "Aroblj.findBySwtaxincl2", query = "SELECT a FROM Aroblj a WHERE a.swtaxincl2 = :swtaxincl2"),
    @NamedQuery(name = "Aroblj.findBySwtaxincl3", query = "SELECT a FROM Aroblj a WHERE a.swtaxincl3 = :swtaxincl3"),
    @NamedQuery(name = "Aroblj.findBySwtaxincl4", query = "SELECT a FROM Aroblj a WHERE a.swtaxincl4 = :swtaxincl4"),
    @NamedQuery(name = "Aroblj.findBySwtaxincl5", query = "SELECT a FROM Aroblj a WHERE a.swtaxincl5 = :swtaxincl5"),
    @NamedQuery(name = "Aroblj.findByTxrate1", query = "SELECT a FROM Aroblj a WHERE a.txrate1 = :txrate1"),
    @NamedQuery(name = "Aroblj.findByTxrate2", query = "SELECT a FROM Aroblj a WHERE a.txrate2 = :txrate2"),
    @NamedQuery(name = "Aroblj.findByTxrate3", query = "SELECT a FROM Aroblj a WHERE a.txrate3 = :txrate3"),
    @NamedQuery(name = "Aroblj.findByTxrate4", query = "SELECT a FROM Aroblj a WHERE a.txrate4 = :txrate4"),
    @NamedQuery(name = "Aroblj.findByTxrate5", query = "SELECT a FROM Aroblj a WHERE a.txrate5 = :txrate5"),
    @NamedQuery(name = "Aroblj.findByTxbsert1tc", query = "SELECT a FROM Aroblj a WHERE a.txbsert1tc = :txbsert1tc"),
    @NamedQuery(name = "Aroblj.findByTxbsert2tc", query = "SELECT a FROM Aroblj a WHERE a.txbsert2tc = :txbsert2tc"),
    @NamedQuery(name = "Aroblj.findByTxbsert3tc", query = "SELECT a FROM Aroblj a WHERE a.txbsert3tc = :txbsert3tc"),
    @NamedQuery(name = "Aroblj.findByTxbsert4tc", query = "SELECT a FROM Aroblj a WHERE a.txbsert4tc = :txbsert4tc"),
    @NamedQuery(name = "Aroblj.findByTxbsert5tc", query = "SELECT a FROM Aroblj a WHERE a.txbsert5tc = :txbsert5tc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt1tc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt1tc = :txamtrt1tc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt2tc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt2tc = :txamtrt2tc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt3tc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt3tc = :txamtrt3tc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt4tc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt4tc = :txamtrt4tc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt5tc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt5tc = :txamtrt5tc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt1hc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt1hc = :txamtrt1hc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt2hc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt2hc = :txamtrt2hc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt3hc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt3hc = :txamtrt3hc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt4hc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt4hc = :txamtrt4hc"),
    @NamedQuery(name = "Aroblj.findByTxamtrt5hc", query = "SELECT a FROM Aroblj a WHERE a.txamtrt5hc = :txamtrt5hc"),
    @NamedQuery(name = "Aroblj.findByCntlastseq", query = "SELECT a FROM Aroblj a WHERE a.cntlastseq = :cntlastseq")})
public class Aroblj implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArobljPK arobljPK;
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
    @Column(name = "CONTRACT")
    private String contract;
    @Basic(optional = false)
    @Column(name = "PROJECT")
    private String project;
    @Basic(optional = false)
    @Column(name = "CATEGORY")
    private String category;
    @Basic(optional = false)
    @Column(name = "RESOURCE")
    private String resource;
    @Basic(optional = false)
    @Column(name = "TRANSNBR")
    private int transnbr;
    @Basic(optional = false)
    @Column(name = "COSTCLASS")
    private short costclass;
    @Basic(optional = false)
    @Column(name = "IDDIST")
    private String iddist;
    @Basic(optional = false)
    @Column(name = "IDGLACCT")
    private String idglacct;
    @Basic(optional = false)
    @Column(name = "AMTINVCTC")
    private BigDecimal amtinvctc;
    @Basic(optional = false)
    @Column(name = "AMTDUETC")
    private BigDecimal amtduetc;
    @Basic(optional = false)
    @Column(name = "AMTINVCHC")
    private BigDecimal amtinvchc;
    @Basic(optional = false)
    @Column(name = "AMTDUEHC")
    private BigDecimal amtduehc;
    @Basic(optional = false)
    @Column(name = "IDITEM")
    private String iditem;
    @Basic(optional = false)
    @Column(name = "UNITMEAS")
    private String unitmeas;
    @Basic(optional = false)
    @Column(name = "QTYINVC")
    private BigDecimal qtyinvc;
    @Basic(optional = false)
    @Column(name = "AMTCOST")
    private BigDecimal amtcost;
    @Basic(optional = false)
    @Column(name = "BILLDATE")
    private int billdate;
    @Basic(optional = false)
    @Column(name = "SWDISCABL")
    private short swdiscabl;
    @Basic(optional = false)
    @Column(name = "RTGDATEDUE")
    private int rtgdatedue;
    @Basic(optional = false)
    @Column(name = "RTGOAMTHC")
    private BigDecimal rtgoamthc;
    @Basic(optional = false)
    @Column(name = "RTGAMTHC")
    private BigDecimal rtgamthc;
    @Basic(optional = false)
    @Column(name = "RTGOAMTTC")
    private BigDecimal rtgoamttc;
    @Basic(optional = false)
    @Column(name = "RTGAMTTC")
    private BigDecimal rtgamttc;
    @Basic(optional = false)
    @Column(name = "VALUES")
    private int values;
    @Basic(optional = false)
    @Column(name = "RTGDISTTC")
    private BigDecimal rtgdisttc;
    @Basic(optional = false)
    @Column(name = "RTGCOGSTC")
    private BigDecimal rtgcogstc;
    @Basic(optional = false)
    @Column(name = "RTGALTBTC")
    private BigDecimal rtgaltbtc;
    @Basic(optional = false)
    @Column(name = "TAXCLASS1")
    private short taxclass1;
    @Basic(optional = false)
    @Column(name = "TAXCLASS2")
    private short taxclass2;
    @Basic(optional = false)
    @Column(name = "TAXCLASS3")
    private short taxclass3;
    @Basic(optional = false)
    @Column(name = "TAXCLASS4")
    private short taxclass4;
    @Basic(optional = false)
    @Column(name = "TAXCLASS5")
    private short taxclass5;
    @Basic(optional = false)
    @Column(name = "SWTAXINCL1")
    private short swtaxincl1;
    @Basic(optional = false)
    @Column(name = "SWTAXINCL2")
    private short swtaxincl2;
    @Basic(optional = false)
    @Column(name = "SWTAXINCL3")
    private short swtaxincl3;
    @Basic(optional = false)
    @Column(name = "SWTAXINCL4")
    private short swtaxincl4;
    @Basic(optional = false)
    @Column(name = "SWTAXINCL5")
    private short swtaxincl5;
    @Basic(optional = false)
    @Column(name = "TXRATE1")
    private BigDecimal txrate1;
    @Basic(optional = false)
    @Column(name = "TXRATE2")
    private BigDecimal txrate2;
    @Basic(optional = false)
    @Column(name = "TXRATE3")
    private BigDecimal txrate3;
    @Basic(optional = false)
    @Column(name = "TXRATE4")
    private BigDecimal txrate4;
    @Basic(optional = false)
    @Column(name = "TXRATE5")
    private BigDecimal txrate5;
    @Basic(optional = false)
    @Column(name = "TXBSERT1TC")
    private BigDecimal txbsert1tc;
    @Basic(optional = false)
    @Column(name = "TXBSERT2TC")
    private BigDecimal txbsert2tc;
    @Basic(optional = false)
    @Column(name = "TXBSERT3TC")
    private BigDecimal txbsert3tc;
    @Basic(optional = false)
    @Column(name = "TXBSERT4TC")
    private BigDecimal txbsert4tc;
    @Basic(optional = false)
    @Column(name = "TXBSERT5TC")
    private BigDecimal txbsert5tc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT1TC")
    private BigDecimal txamtrt1tc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT2TC")
    private BigDecimal txamtrt2tc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT3TC")
    private BigDecimal txamtrt3tc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT4TC")
    private BigDecimal txamtrt4tc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT5TC")
    private BigDecimal txamtrt5tc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT1HC")
    private BigDecimal txamtrt1hc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT2HC")
    private BigDecimal txamtrt2hc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT3HC")
    private BigDecimal txamtrt3hc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT4HC")
    private BigDecimal txamtrt4hc;
    @Basic(optional = false)
    @Column(name = "TXAMTRT5HC")
    private BigDecimal txamtrt5hc;
    @Basic(optional = false)
    @Column(name = "CNTLASTSEQ")
    private int cntlastseq;

    public Aroblj() {
    }

    public Aroblj(ArobljPK arobljPK) {
        this.arobljPK = arobljPK;
    }

    public Aroblj(ArobljPK arobljPK, int audtdate, int audttime, String audtuser, String audtorg, String contract, String project, String category, String resource, int transnbr, short costclass, String iddist, String idglacct, BigDecimal amtinvctc, BigDecimal amtduetc, BigDecimal amtinvchc, BigDecimal amtduehc, String iditem, String unitmeas, BigDecimal qtyinvc, BigDecimal amtcost, int billdate, short swdiscabl, int rtgdatedue, BigDecimal rtgoamthc, BigDecimal rtgamthc, BigDecimal rtgoamttc, BigDecimal rtgamttc, int values, BigDecimal rtgdisttc, BigDecimal rtgcogstc, BigDecimal rtgaltbtc, short taxclass1, short taxclass2, short taxclass3, short taxclass4, short taxclass5, short swtaxincl1, short swtaxincl2, short swtaxincl3, short swtaxincl4, short swtaxincl5, BigDecimal txrate1, BigDecimal txrate2, BigDecimal txrate3, BigDecimal txrate4, BigDecimal txrate5, BigDecimal txbsert1tc, BigDecimal txbsert2tc, BigDecimal txbsert3tc, BigDecimal txbsert4tc, BigDecimal txbsert5tc, BigDecimal txamtrt1tc, BigDecimal txamtrt2tc, BigDecimal txamtrt3tc, BigDecimal txamtrt4tc, BigDecimal txamtrt5tc, BigDecimal txamtrt1hc, BigDecimal txamtrt2hc, BigDecimal txamtrt3hc, BigDecimal txamtrt4hc, BigDecimal txamtrt5hc, int cntlastseq) {
        this.arobljPK = arobljPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.contract = contract;
        this.project = project;
        this.category = category;
        this.resource = resource;
        this.transnbr = transnbr;
        this.costclass = costclass;
        this.iddist = iddist;
        this.idglacct = idglacct;
        this.amtinvctc = amtinvctc;
        this.amtduetc = amtduetc;
        this.amtinvchc = amtinvchc;
        this.amtduehc = amtduehc;
        this.iditem = iditem;
        this.unitmeas = unitmeas;
        this.qtyinvc = qtyinvc;
        this.amtcost = amtcost;
        this.billdate = billdate;
        this.swdiscabl = swdiscabl;
        this.rtgdatedue = rtgdatedue;
        this.rtgoamthc = rtgoamthc;
        this.rtgamthc = rtgamthc;
        this.rtgoamttc = rtgoamttc;
        this.rtgamttc = rtgamttc;
        this.values = values;
        this.rtgdisttc = rtgdisttc;
        this.rtgcogstc = rtgcogstc;
        this.rtgaltbtc = rtgaltbtc;
        this.taxclass1 = taxclass1;
        this.taxclass2 = taxclass2;
        this.taxclass3 = taxclass3;
        this.taxclass4 = taxclass4;
        this.taxclass5 = taxclass5;
        this.swtaxincl1 = swtaxincl1;
        this.swtaxincl2 = swtaxincl2;
        this.swtaxincl3 = swtaxincl3;
        this.swtaxincl4 = swtaxincl4;
        this.swtaxincl5 = swtaxincl5;
        this.txrate1 = txrate1;
        this.txrate2 = txrate2;
        this.txrate3 = txrate3;
        this.txrate4 = txrate4;
        this.txrate5 = txrate5;
        this.txbsert1tc = txbsert1tc;
        this.txbsert2tc = txbsert2tc;
        this.txbsert3tc = txbsert3tc;
        this.txbsert4tc = txbsert4tc;
        this.txbsert5tc = txbsert5tc;
        this.txamtrt1tc = txamtrt1tc;
        this.txamtrt2tc = txamtrt2tc;
        this.txamtrt3tc = txamtrt3tc;
        this.txamtrt4tc = txamtrt4tc;
        this.txamtrt5tc = txamtrt5tc;
        this.txamtrt1hc = txamtrt1hc;
        this.txamtrt2hc = txamtrt2hc;
        this.txamtrt3hc = txamtrt3hc;
        this.txamtrt4hc = txamtrt4hc;
        this.txamtrt5hc = txamtrt5hc;
        this.cntlastseq = cntlastseq;
    }

    public Aroblj(String idcust, String idinvc, int cntline) {
        this.arobljPK = new ArobljPK(idcust, idinvc, cntline);
    }

    public ArobljPK getArobljPK() {
        return arobljPK;
    }

    public void setArobljPK(ArobljPK arobljPK) {
        this.arobljPK = arobljPK;
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

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getTransnbr() {
        return transnbr;
    }

    public void setTransnbr(int transnbr) {
        this.transnbr = transnbr;
    }

    public short getCostclass() {
        return costclass;
    }

    public void setCostclass(short costclass) {
        this.costclass = costclass;
    }

    public String getIddist() {
        return iddist;
    }

    public void setIddist(String iddist) {
        this.iddist = iddist;
    }

    public String getIdglacct() {
        return idglacct;
    }

    public void setIdglacct(String idglacct) {
        this.idglacct = idglacct;
    }

    public BigDecimal getAmtinvctc() {
        return amtinvctc;
    }

    public void setAmtinvctc(BigDecimal amtinvctc) {
        this.amtinvctc = amtinvctc;
    }

    public BigDecimal getAmtduetc() {
        return amtduetc;
    }

    public void setAmtduetc(BigDecimal amtduetc) {
        this.amtduetc = amtduetc;
    }

    public BigDecimal getAmtinvchc() {
        return amtinvchc;
    }

    public void setAmtinvchc(BigDecimal amtinvchc) {
        this.amtinvchc = amtinvchc;
    }

    public BigDecimal getAmtduehc() {
        return amtduehc;
    }

    public void setAmtduehc(BigDecimal amtduehc) {
        this.amtduehc = amtduehc;
    }

    public String getIditem() {
        return iditem;
    }

    public void setIditem(String iditem) {
        this.iditem = iditem;
    }

    public String getUnitmeas() {
        return unitmeas;
    }

    public void setUnitmeas(String unitmeas) {
        this.unitmeas = unitmeas;
    }

    public BigDecimal getQtyinvc() {
        return qtyinvc;
    }

    public void setQtyinvc(BigDecimal qtyinvc) {
        this.qtyinvc = qtyinvc;
    }

    public BigDecimal getAmtcost() {
        return amtcost;
    }

    public void setAmtcost(BigDecimal amtcost) {
        this.amtcost = amtcost;
    }

    public int getBilldate() {
        return billdate;
    }

    public void setBilldate(int billdate) {
        this.billdate = billdate;
    }

    public short getSwdiscabl() {
        return swdiscabl;
    }

    public void setSwdiscabl(short swdiscabl) {
        this.swdiscabl = swdiscabl;
    }

    public int getRtgdatedue() {
        return rtgdatedue;
    }

    public void setRtgdatedue(int rtgdatedue) {
        this.rtgdatedue = rtgdatedue;
    }

    public BigDecimal getRtgoamthc() {
        return rtgoamthc;
    }

    public void setRtgoamthc(BigDecimal rtgoamthc) {
        this.rtgoamthc = rtgoamthc;
    }

    public BigDecimal getRtgamthc() {
        return rtgamthc;
    }

    public void setRtgamthc(BigDecimal rtgamthc) {
        this.rtgamthc = rtgamthc;
    }

    public BigDecimal getRtgoamttc() {
        return rtgoamttc;
    }

    public void setRtgoamttc(BigDecimal rtgoamttc) {
        this.rtgoamttc = rtgoamttc;
    }

    public BigDecimal getRtgamttc() {
        return rtgamttc;
    }

    public void setRtgamttc(BigDecimal rtgamttc) {
        this.rtgamttc = rtgamttc;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    public BigDecimal getRtgdisttc() {
        return rtgdisttc;
    }

    public void setRtgdisttc(BigDecimal rtgdisttc) {
        this.rtgdisttc = rtgdisttc;
    }

    public BigDecimal getRtgcogstc() {
        return rtgcogstc;
    }

    public void setRtgcogstc(BigDecimal rtgcogstc) {
        this.rtgcogstc = rtgcogstc;
    }

    public BigDecimal getRtgaltbtc() {
        return rtgaltbtc;
    }

    public void setRtgaltbtc(BigDecimal rtgaltbtc) {
        this.rtgaltbtc = rtgaltbtc;
    }

    public short getTaxclass1() {
        return taxclass1;
    }

    public void setTaxclass1(short taxclass1) {
        this.taxclass1 = taxclass1;
    }

    public short getTaxclass2() {
        return taxclass2;
    }

    public void setTaxclass2(short taxclass2) {
        this.taxclass2 = taxclass2;
    }

    public short getTaxclass3() {
        return taxclass3;
    }

    public void setTaxclass3(short taxclass3) {
        this.taxclass3 = taxclass3;
    }

    public short getTaxclass4() {
        return taxclass4;
    }

    public void setTaxclass4(short taxclass4) {
        this.taxclass4 = taxclass4;
    }

    public short getTaxclass5() {
        return taxclass5;
    }

    public void setTaxclass5(short taxclass5) {
        this.taxclass5 = taxclass5;
    }

    public short getSwtaxincl1() {
        return swtaxincl1;
    }

    public void setSwtaxincl1(short swtaxincl1) {
        this.swtaxincl1 = swtaxincl1;
    }

    public short getSwtaxincl2() {
        return swtaxincl2;
    }

    public void setSwtaxincl2(short swtaxincl2) {
        this.swtaxincl2 = swtaxincl2;
    }

    public short getSwtaxincl3() {
        return swtaxincl3;
    }

    public void setSwtaxincl3(short swtaxincl3) {
        this.swtaxincl3 = swtaxincl3;
    }

    public short getSwtaxincl4() {
        return swtaxincl4;
    }

    public void setSwtaxincl4(short swtaxincl4) {
        this.swtaxincl4 = swtaxincl4;
    }

    public short getSwtaxincl5() {
        return swtaxincl5;
    }

    public void setSwtaxincl5(short swtaxincl5) {
        this.swtaxincl5 = swtaxincl5;
    }

    public BigDecimal getTxrate1() {
        return txrate1;
    }

    public void setTxrate1(BigDecimal txrate1) {
        this.txrate1 = txrate1;
    }

    public BigDecimal getTxrate2() {
        return txrate2;
    }

    public void setTxrate2(BigDecimal txrate2) {
        this.txrate2 = txrate2;
    }

    public BigDecimal getTxrate3() {
        return txrate3;
    }

    public void setTxrate3(BigDecimal txrate3) {
        this.txrate3 = txrate3;
    }

    public BigDecimal getTxrate4() {
        return txrate4;
    }

    public void setTxrate4(BigDecimal txrate4) {
        this.txrate4 = txrate4;
    }

    public BigDecimal getTxrate5() {
        return txrate5;
    }

    public void setTxrate5(BigDecimal txrate5) {
        this.txrate5 = txrate5;
    }

    public BigDecimal getTxbsert1tc() {
        return txbsert1tc;
    }

    public void setTxbsert1tc(BigDecimal txbsert1tc) {
        this.txbsert1tc = txbsert1tc;
    }

    public BigDecimal getTxbsert2tc() {
        return txbsert2tc;
    }

    public void setTxbsert2tc(BigDecimal txbsert2tc) {
        this.txbsert2tc = txbsert2tc;
    }

    public BigDecimal getTxbsert3tc() {
        return txbsert3tc;
    }

    public void setTxbsert3tc(BigDecimal txbsert3tc) {
        this.txbsert3tc = txbsert3tc;
    }

    public BigDecimal getTxbsert4tc() {
        return txbsert4tc;
    }

    public void setTxbsert4tc(BigDecimal txbsert4tc) {
        this.txbsert4tc = txbsert4tc;
    }

    public BigDecimal getTxbsert5tc() {
        return txbsert5tc;
    }

    public void setTxbsert5tc(BigDecimal txbsert5tc) {
        this.txbsert5tc = txbsert5tc;
    }

    public BigDecimal getTxamtrt1tc() {
        return txamtrt1tc;
    }

    public void setTxamtrt1tc(BigDecimal txamtrt1tc) {
        this.txamtrt1tc = txamtrt1tc;
    }

    public BigDecimal getTxamtrt2tc() {
        return txamtrt2tc;
    }

    public void setTxamtrt2tc(BigDecimal txamtrt2tc) {
        this.txamtrt2tc = txamtrt2tc;
    }

    public BigDecimal getTxamtrt3tc() {
        return txamtrt3tc;
    }

    public void setTxamtrt3tc(BigDecimal txamtrt3tc) {
        this.txamtrt3tc = txamtrt3tc;
    }

    public BigDecimal getTxamtrt4tc() {
        return txamtrt4tc;
    }

    public void setTxamtrt4tc(BigDecimal txamtrt4tc) {
        this.txamtrt4tc = txamtrt4tc;
    }

    public BigDecimal getTxamtrt5tc() {
        return txamtrt5tc;
    }

    public void setTxamtrt5tc(BigDecimal txamtrt5tc) {
        this.txamtrt5tc = txamtrt5tc;
    }

    public BigDecimal getTxamtrt1hc() {
        return txamtrt1hc;
    }

    public void setTxamtrt1hc(BigDecimal txamtrt1hc) {
        this.txamtrt1hc = txamtrt1hc;
    }

    public BigDecimal getTxamtrt2hc() {
        return txamtrt2hc;
    }

    public void setTxamtrt2hc(BigDecimal txamtrt2hc) {
        this.txamtrt2hc = txamtrt2hc;
    }

    public BigDecimal getTxamtrt3hc() {
        return txamtrt3hc;
    }

    public void setTxamtrt3hc(BigDecimal txamtrt3hc) {
        this.txamtrt3hc = txamtrt3hc;
    }

    public BigDecimal getTxamtrt4hc() {
        return txamtrt4hc;
    }

    public void setTxamtrt4hc(BigDecimal txamtrt4hc) {
        this.txamtrt4hc = txamtrt4hc;
    }

    public BigDecimal getTxamtrt5hc() {
        return txamtrt5hc;
    }

    public void setTxamtrt5hc(BigDecimal txamtrt5hc) {
        this.txamtrt5hc = txamtrt5hc;
    }

    public int getCntlastseq() {
        return cntlastseq;
    }

    public void setCntlastseq(int cntlastseq) {
        this.cntlastseq = cntlastseq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arobljPK != null ? arobljPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aroblj)) {
            return false;
        }
        Aroblj other = (Aroblj) object;
        if ((this.arobljPK == null && other.arobljPK != null) || (this.arobljPK != null && !this.arobljPK.equals(other.arobljPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Aroblj[arobljPK=" + arobljPK + "]";
    }

}
