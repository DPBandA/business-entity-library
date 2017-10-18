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
@Table(name = "ARIBD")
@NamedQueries({
    @NamedQuery(name = "Aribd.findAll", query = "SELECT a FROM Aribd a"),
    @NamedQuery(name = "Aribd.findByCntbtch", query = "SELECT a FROM Aribd a WHERE a.aribdPK.cntbtch = :cntbtch"),
    @NamedQuery(name = "Aribd.findByCntitem", query = "SELECT a FROM Aribd a WHERE a.aribdPK.cntitem = :cntitem"),
    @NamedQuery(name = "Aribd.findByCntline", query = "SELECT a FROM Aribd a WHERE a.aribdPK.cntline = :cntline"),
    @NamedQuery(name = "Aribd.findByAudtdate", query = "SELECT a FROM Aribd a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Aribd.findByAudttime", query = "SELECT a FROM Aribd a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Aribd.findByAudtuser", query = "SELECT a FROM Aribd a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Aribd.findByAudtorg", query = "SELECT a FROM Aribd a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Aribd.findByIdinvc", query = "SELECT a FROM Aribd a WHERE a.idinvc = :idinvc"),
    @NamedQuery(name = "Aribd.findByIditem", query = "SELECT a FROM Aribd a WHERE a.iditem = :iditem"),
    @NamedQuery(name = "Aribd.findByIddist", query = "SELECT a FROM Aribd a WHERE a.iddist = :iddist"),
    @NamedQuery(name = "Aribd.findByTextdesc", query = "SELECT a FROM Aribd a WHERE a.textdesc = :textdesc"),
    @NamedQuery(name = "Aribd.findBySwmanlitem", query = "SELECT a FROM Aribd a WHERE a.swmanlitem = :swmanlitem"),
    @NamedQuery(name = "Aribd.findByUnitmeas", query = "SELECT a FROM Aribd a WHERE a.unitmeas = :unitmeas"),
    @NamedQuery(name = "Aribd.findByQtyinvc", query = "SELECT a FROM Aribd a WHERE a.qtyinvc = :qtyinvc"),
    @NamedQuery(name = "Aribd.findByAmtcost", query = "SELECT a FROM Aribd a WHERE a.amtcost = :amtcost"),
    @NamedQuery(name = "Aribd.findByAmtpric", query = "SELECT a FROM Aribd a WHERE a.amtpric = :amtpric"),
    @NamedQuery(name = "Aribd.findByAmtextn", query = "SELECT a FROM Aribd a WHERE a.amtextn = :amtextn"),
    @NamedQuery(name = "Aribd.findByAmtcogs", query = "SELECT a FROM Aribd a WHERE a.amtcogs = :amtcogs"),
    @NamedQuery(name = "Aribd.findByAmttxbl", query = "SELECT a FROM Aribd a WHERE a.amttxbl = :amttxbl"),
    @NamedQuery(name = "Aribd.findByTottax", query = "SELECT a FROM Aribd a WHERE a.tottax = :tottax"),
    @NamedQuery(name = "Aribd.findBySwmanltx", query = "SELECT a FROM Aribd a WHERE a.swmanltx = :swmanltx"),
    @NamedQuery(name = "Aribd.findByBasetax1", query = "SELECT a FROM Aribd a WHERE a.basetax1 = :basetax1"),
    @NamedQuery(name = "Aribd.findByBasetax2", query = "SELECT a FROM Aribd a WHERE a.basetax2 = :basetax2"),
    @NamedQuery(name = "Aribd.findByBasetax3", query = "SELECT a FROM Aribd a WHERE a.basetax3 = :basetax3"),
    @NamedQuery(name = "Aribd.findByBasetax4", query = "SELECT a FROM Aribd a WHERE a.basetax4 = :basetax4"),
    @NamedQuery(name = "Aribd.findByBasetax5", query = "SELECT a FROM Aribd a WHERE a.basetax5 = :basetax5"),
    @NamedQuery(name = "Aribd.findByTaxstts1", query = "SELECT a FROM Aribd a WHERE a.taxstts1 = :taxstts1"),
    @NamedQuery(name = "Aribd.findByTaxstts2", query = "SELECT a FROM Aribd a WHERE a.taxstts2 = :taxstts2"),
    @NamedQuery(name = "Aribd.findByTaxstts3", query = "SELECT a FROM Aribd a WHERE a.taxstts3 = :taxstts3"),
    @NamedQuery(name = "Aribd.findByTaxstts4", query = "SELECT a FROM Aribd a WHERE a.taxstts4 = :taxstts4"),
    @NamedQuery(name = "Aribd.findByTaxstts5", query = "SELECT a FROM Aribd a WHERE a.taxstts5 = :taxstts5"),
    @NamedQuery(name = "Aribd.findBySwtaxincl1", query = "SELECT a FROM Aribd a WHERE a.swtaxincl1 = :swtaxincl1"),
    @NamedQuery(name = "Aribd.findBySwtaxincl2", query = "SELECT a FROM Aribd a WHERE a.swtaxincl2 = :swtaxincl2"),
    @NamedQuery(name = "Aribd.findBySwtaxincl3", query = "SELECT a FROM Aribd a WHERE a.swtaxincl3 = :swtaxincl3"),
    @NamedQuery(name = "Aribd.findBySwtaxincl4", query = "SELECT a FROM Aribd a WHERE a.swtaxincl4 = :swtaxincl4"),
    @NamedQuery(name = "Aribd.findBySwtaxincl5", query = "SELECT a FROM Aribd a WHERE a.swtaxincl5 = :swtaxincl5"),
    @NamedQuery(name = "Aribd.findByRatetax1", query = "SELECT a FROM Aribd a WHERE a.ratetax1 = :ratetax1"),
    @NamedQuery(name = "Aribd.findByRatetax2", query = "SELECT a FROM Aribd a WHERE a.ratetax2 = :ratetax2"),
    @NamedQuery(name = "Aribd.findByRatetax3", query = "SELECT a FROM Aribd a WHERE a.ratetax3 = :ratetax3"),
    @NamedQuery(name = "Aribd.findByRatetax4", query = "SELECT a FROM Aribd a WHERE a.ratetax4 = :ratetax4"),
    @NamedQuery(name = "Aribd.findByRatetax5", query = "SELECT a FROM Aribd a WHERE a.ratetax5 = :ratetax5"),
    @NamedQuery(name = "Aribd.findByAmttax1", query = "SELECT a FROM Aribd a WHERE a.amttax1 = :amttax1"),
    @NamedQuery(name = "Aribd.findByAmttax2", query = "SELECT a FROM Aribd a WHERE a.amttax2 = :amttax2"),
    @NamedQuery(name = "Aribd.findByAmttax3", query = "SELECT a FROM Aribd a WHERE a.amttax3 = :amttax3"),
    @NamedQuery(name = "Aribd.findByAmttax4", query = "SELECT a FROM Aribd a WHERE a.amttax4 = :amttax4"),
    @NamedQuery(name = "Aribd.findByAmttax5", query = "SELECT a FROM Aribd a WHERE a.amttax5 = :amttax5"),
    @NamedQuery(name = "Aribd.findByIdacctrev", query = "SELECT a FROM Aribd a WHERE a.idacctrev = :idacctrev"),
    @NamedQuery(name = "Aribd.findByIdacctinv", query = "SELECT a FROM Aribd a WHERE a.idacctinv = :idacctinv"),
    @NamedQuery(name = "Aribd.findByIdacctcogs", query = "SELECT a FROM Aribd a WHERE a.idacctcogs = :idacctcogs"),
    @NamedQuery(name = "Aribd.findByIdjobproj", query = "SELECT a FROM Aribd a WHERE a.idjobproj = :idjobproj"),
    @NamedQuery(name = "Aribd.findByContract", query = "SELECT a FROM Aribd a WHERE a.contract = :contract"),
    @NamedQuery(name = "Aribd.findByProject", query = "SELECT a FROM Aribd a WHERE a.project = :project"),
    @NamedQuery(name = "Aribd.findByCategory", query = "SELECT a FROM Aribd a WHERE a.category = :category"),
    @NamedQuery(name = "Aribd.findByResource", query = "SELECT a FROM Aribd a WHERE a.resource = :resource"),
    @NamedQuery(name = "Aribd.findByTransnbr", query = "SELECT a FROM Aribd a WHERE a.transnbr = :transnbr"),
    @NamedQuery(name = "Aribd.findByCostclass", query = "SELECT a FROM Aribd a WHERE a.costclass = :costclass"),
    @NamedQuery(name = "Aribd.findByBilldate", query = "SELECT a FROM Aribd a WHERE a.billdate = :billdate"),
    @NamedQuery(name = "Aribd.findBySwibt", query = "SELECT a FROM Aribd a WHERE a.swibt = :swibt"),
    @NamedQuery(name = "Aribd.findBySwdiscabl", query = "SELECT a FROM Aribd a WHERE a.swdiscabl = :swdiscabl"),
    @NamedQuery(name = "Aribd.findByOcntline", query = "SELECT a FROM Aribd a WHERE a.ocntline = :ocntline"),
    @NamedQuery(name = "Aribd.findByRtgamt", query = "SELECT a FROM Aribd a WHERE a.rtgamt = :rtgamt"),
    @NamedQuery(name = "Aribd.findByRtgpercent", query = "SELECT a FROM Aribd a WHERE a.rtgpercent = :rtgpercent"),
    @NamedQuery(name = "Aribd.findByRtgdays", query = "SELECT a FROM Aribd a WHERE a.rtgdays = :rtgdays"),
    @NamedQuery(name = "Aribd.findByRtgdatedue", query = "SELECT a FROM Aribd a WHERE a.rtgdatedue = :rtgdatedue"),
    @NamedQuery(name = "Aribd.findBySwrtgddtov", query = "SELECT a FROM Aribd a WHERE a.swrtgddtov = :swrtgddtov"),
    @NamedQuery(name = "Aribd.findBySwrtgamtov", query = "SELECT a FROM Aribd a WHERE a.swrtgamtov = :swrtgamtov"),
    @NamedQuery(name = "Aribd.findByValues", query = "SELECT a FROM Aribd a WHERE a.values = :values"),
    @NamedQuery(name = "Aribd.findByRtgdisttc", query = "SELECT a FROM Aribd a WHERE a.rtgdisttc = :rtgdisttc"),
    @NamedQuery(name = "Aribd.findByRtgcogstc", query = "SELECT a FROM Aribd a WHERE a.rtgcogstc = :rtgcogstc"),
    @NamedQuery(name = "Aribd.findByRtgaltbtc", query = "SELECT a FROM Aribd a WHERE a.rtgaltbtc = :rtgaltbtc"),
    @NamedQuery(name = "Aribd.findByRtginvdist", query = "SELECT a FROM Aribd a WHERE a.rtginvdist = :rtginvdist"),
    @NamedQuery(name = "Aribd.findByRtginvcogs", query = "SELECT a FROM Aribd a WHERE a.rtginvcogs = :rtginvcogs"),
    @NamedQuery(name = "Aribd.findByRtginvaltb", query = "SELECT a FROM Aribd a WHERE a.rtginvaltb = :rtginvaltb"),
    @NamedQuery(name = "Aribd.findByTxamt1rc", query = "SELECT a FROM Aribd a WHERE a.txamt1rc = :txamt1rc"),
    @NamedQuery(name = "Aribd.findByTxamt2rc", query = "SELECT a FROM Aribd a WHERE a.txamt2rc = :txamt2rc"),
    @NamedQuery(name = "Aribd.findByTxamt3rc", query = "SELECT a FROM Aribd a WHERE a.txamt3rc = :txamt3rc"),
    @NamedQuery(name = "Aribd.findByTxamt4rc", query = "SELECT a FROM Aribd a WHERE a.txamt4rc = :txamt4rc"),
    @NamedQuery(name = "Aribd.findByTxamt5rc", query = "SELECT a FROM Aribd a WHERE a.txamt5rc = :txamt5rc"),
    @NamedQuery(name = "Aribd.findByTxtotrc", query = "SELECT a FROM Aribd a WHERE a.txtotrc = :txtotrc"),
    @NamedQuery(name = "Aribd.findByTxbsert1tc", query = "SELECT a FROM Aribd a WHERE a.txbsert1tc = :txbsert1tc"),
    @NamedQuery(name = "Aribd.findByTxbsert2tc", query = "SELECT a FROM Aribd a WHERE a.txbsert2tc = :txbsert2tc"),
    @NamedQuery(name = "Aribd.findByTxbsert3tc", query = "SELECT a FROM Aribd a WHERE a.txbsert3tc = :txbsert3tc"),
    @NamedQuery(name = "Aribd.findByTxbsert4tc", query = "SELECT a FROM Aribd a WHERE a.txbsert4tc = :txbsert4tc"),
    @NamedQuery(name = "Aribd.findByTxbsert5tc", query = "SELECT a FROM Aribd a WHERE a.txbsert5tc = :txbsert5tc"),
    @NamedQuery(name = "Aribd.findByTxamtrt1tc", query = "SELECT a FROM Aribd a WHERE a.txamtrt1tc = :txamtrt1tc"),
    @NamedQuery(name = "Aribd.findByTxamtrt2tc", query = "SELECT a FROM Aribd a WHERE a.txamtrt2tc = :txamtrt2tc"),
    @NamedQuery(name = "Aribd.findByTxamtrt3tc", query = "SELECT a FROM Aribd a WHERE a.txamtrt3tc = :txamtrt3tc"),
    @NamedQuery(name = "Aribd.findByTxamtrt4tc", query = "SELECT a FROM Aribd a WHERE a.txamtrt4tc = :txamtrt4tc"),
    @NamedQuery(name = "Aribd.findByTxamtrt5tc", query = "SELECT a FROM Aribd a WHERE a.txamtrt5tc = :txamtrt5tc"),
    @NamedQuery(name = "Aribd.findByTxbse1hc", query = "SELECT a FROM Aribd a WHERE a.txbse1hc = :txbse1hc"),
    @NamedQuery(name = "Aribd.findByTxbse2hc", query = "SELECT a FROM Aribd a WHERE a.txbse2hc = :txbse2hc"),
    @NamedQuery(name = "Aribd.findByTxbse3hc", query = "SELECT a FROM Aribd a WHERE a.txbse3hc = :txbse3hc"),
    @NamedQuery(name = "Aribd.findByTxbse4hc", query = "SELECT a FROM Aribd a WHERE a.txbse4hc = :txbse4hc"),
    @NamedQuery(name = "Aribd.findByTxbse5hc", query = "SELECT a FROM Aribd a WHERE a.txbse5hc = :txbse5hc"),
    @NamedQuery(name = "Aribd.findByTxamt1hc", query = "SELECT a FROM Aribd a WHERE a.txamt1hc = :txamt1hc"),
    @NamedQuery(name = "Aribd.findByTxamt2hc", query = "SELECT a FROM Aribd a WHERE a.txamt2hc = :txamt2hc"),
    @NamedQuery(name = "Aribd.findByTxamt3hc", query = "SELECT a FROM Aribd a WHERE a.txamt3hc = :txamt3hc"),
    @NamedQuery(name = "Aribd.findByTxamt4hc", query = "SELECT a FROM Aribd a WHERE a.txamt4hc = :txamt4hc"),
    @NamedQuery(name = "Aribd.findByTxamt5hc", query = "SELECT a FROM Aribd a WHERE a.txamt5hc = :txamt5hc"),
    @NamedQuery(name = "Aribd.findByTxamtrt1hc", query = "SELECT a FROM Aribd a WHERE a.txamtrt1hc = :txamtrt1hc"),
    @NamedQuery(name = "Aribd.findByTxamtrt2hc", query = "SELECT a FROM Aribd a WHERE a.txamtrt2hc = :txamtrt2hc"),
    @NamedQuery(name = "Aribd.findByTxamtrt3hc", query = "SELECT a FROM Aribd a WHERE a.txamtrt3hc = :txamtrt3hc"),
    @NamedQuery(name = "Aribd.findByTxamtrt4hc", query = "SELECT a FROM Aribd a WHERE a.txamtrt4hc = :txamtrt4hc"),
    @NamedQuery(name = "Aribd.findByTxamtrt5hc", query = "SELECT a FROM Aribd a WHERE a.txamtrt5hc = :txamtrt5hc"),
    @NamedQuery(name = "Aribd.findByDistnethc", query = "SELECT a FROM Aribd a WHERE a.distnethc = :distnethc"),
    @NamedQuery(name = "Aribd.findByRtgamthc", query = "SELECT a FROM Aribd a WHERE a.rtgamthc = :rtgamthc"),
    @NamedQuery(name = "Aribd.findByAmtcogshc", query = "SELECT a FROM Aribd a WHERE a.amtcogshc = :amtcogshc"),
    @NamedQuery(name = "Aribd.findByAmtcosthc", query = "SELECT a FROM Aribd a WHERE a.amtcosthc = :amtcosthc"),
    @NamedQuery(name = "Aribd.findByAmtprichc", query = "SELECT a FROM Aribd a WHERE a.amtprichc = :amtprichc"),
    @NamedQuery(name = "Aribd.findByAmtextnhc", query = "SELECT a FROM Aribd a WHERE a.amtextnhc = :amtextnhc")})
public class Aribd implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AribdPK aribdPK;
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
    @Column(name = "IDINVC")
    private String idinvc;
    @Basic(optional = false)
    @Column(name = "IDITEM")
    private String iditem;
    @Basic(optional = false)
    @Column(name = "IDDIST")
    private String iddist;
    @Basic(optional = false)
    @Column(name = "TEXTDESC")
    private String textdesc;
    @Basic(optional = false)
    @Column(name = "SWMANLITEM")
    private short swmanlitem;
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
    @Column(name = "AMTPRIC")
    private BigDecimal amtpric;
    @Basic(optional = false)
    @Column(name = "AMTEXTN")
    private BigDecimal amtextn;
    @Basic(optional = false)
    @Column(name = "AMTCOGS")
    private BigDecimal amtcogs;
    @Basic(optional = false)
    @Column(name = "AMTTXBL")
    private BigDecimal amttxbl;
    @Basic(optional = false)
    @Column(name = "TOTTAX")
    private BigDecimal tottax;
    @Basic(optional = false)
    @Column(name = "SWMANLTX")
    private short swmanltx;
    @Basic(optional = false)
    @Column(name = "BASETAX1")
    private BigDecimal basetax1;
    @Basic(optional = false)
    @Column(name = "BASETAX2")
    private BigDecimal basetax2;
    @Basic(optional = false)
    @Column(name = "BASETAX3")
    private BigDecimal basetax3;
    @Basic(optional = false)
    @Column(name = "BASETAX4")
    private BigDecimal basetax4;
    @Basic(optional = false)
    @Column(name = "BASETAX5")
    private BigDecimal basetax5;
    @Basic(optional = false)
    @Column(name = "TAXSTTS1")
    private short taxstts1;
    @Basic(optional = false)
    @Column(name = "TAXSTTS2")
    private short taxstts2;
    @Basic(optional = false)
    @Column(name = "TAXSTTS3")
    private short taxstts3;
    @Basic(optional = false)
    @Column(name = "TAXSTTS4")
    private short taxstts4;
    @Basic(optional = false)
    @Column(name = "TAXSTTS5")
    private short taxstts5;
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
    @Column(name = "RATETAX1")
    private BigDecimal ratetax1;
    @Basic(optional = false)
    @Column(name = "RATETAX2")
    private BigDecimal ratetax2;
    @Basic(optional = false)
    @Column(name = "RATETAX3")
    private BigDecimal ratetax3;
    @Basic(optional = false)
    @Column(name = "RATETAX4")
    private BigDecimal ratetax4;
    @Basic(optional = false)
    @Column(name = "RATETAX5")
    private BigDecimal ratetax5;
    @Basic(optional = false)
    @Column(name = "AMTTAX1")
    private BigDecimal amttax1;
    @Basic(optional = false)
    @Column(name = "AMTTAX2")
    private BigDecimal amttax2;
    @Basic(optional = false)
    @Column(name = "AMTTAX3")
    private BigDecimal amttax3;
    @Basic(optional = false)
    @Column(name = "AMTTAX4")
    private BigDecimal amttax4;
    @Basic(optional = false)
    @Column(name = "AMTTAX5")
    private BigDecimal amttax5;
    @Basic(optional = false)
    @Column(name = "IDACCTREV")
    private String idacctrev;
    @Basic(optional = false)
    @Column(name = "IDACCTINV")
    private String idacctinv;
    @Basic(optional = false)
    @Column(name = "IDACCTCOGS")
    private String idacctcogs;
    @Basic(optional = false)
    @Column(name = "IDJOBPROJ")
    private String idjobproj;
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
    @Column(name = "BILLDATE")
    private int billdate;
    @Basic(optional = false)
    @Column(name = "SWIBT")
    private short swibt;
    @Basic(optional = false)
    @Column(name = "SWDISCABL")
    private short swdiscabl;
    @Basic(optional = false)
    @Column(name = "OCNTLINE")
    private int ocntline;
    @Basic(optional = false)
    @Column(name = "RTGAMT")
    private BigDecimal rtgamt;
    @Basic(optional = false)
    @Column(name = "RTGPERCENT")
    private BigDecimal rtgpercent;
    @Basic(optional = false)
    @Column(name = "RTGDAYS")
    private short rtgdays;
    @Basic(optional = false)
    @Column(name = "RTGDATEDUE")
    private int rtgdatedue;
    @Basic(optional = false)
    @Column(name = "SWRTGDDTOV")
    private short swrtgddtov;
    @Basic(optional = false)
    @Column(name = "SWRTGAMTOV")
    private short swrtgamtov;
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
    @Column(name = "RTGINVDIST")
    private BigDecimal rtginvdist;
    @Basic(optional = false)
    @Column(name = "RTGINVCOGS")
    private BigDecimal rtginvcogs;
    @Basic(optional = false)
    @Column(name = "RTGINVALTB")
    private BigDecimal rtginvaltb;
    @Basic(optional = false)
    @Column(name = "TXAMT1RC")
    private BigDecimal txamt1rc;
    @Basic(optional = false)
    @Column(name = "TXAMT2RC")
    private BigDecimal txamt2rc;
    @Basic(optional = false)
    @Column(name = "TXAMT3RC")
    private BigDecimal txamt3rc;
    @Basic(optional = false)
    @Column(name = "TXAMT4RC")
    private BigDecimal txamt4rc;
    @Basic(optional = false)
    @Column(name = "TXAMT5RC")
    private BigDecimal txamt5rc;
    @Basic(optional = false)
    @Column(name = "TXTOTRC")
    private BigDecimal txtotrc;
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
    @Column(name = "TXBSE1HC")
    private BigDecimal txbse1hc;
    @Basic(optional = false)
    @Column(name = "TXBSE2HC")
    private BigDecimal txbse2hc;
    @Basic(optional = false)
    @Column(name = "TXBSE3HC")
    private BigDecimal txbse3hc;
    @Basic(optional = false)
    @Column(name = "TXBSE4HC")
    private BigDecimal txbse4hc;
    @Basic(optional = false)
    @Column(name = "TXBSE5HC")
    private BigDecimal txbse5hc;
    @Basic(optional = false)
    @Column(name = "TXAMT1HC")
    private BigDecimal txamt1hc;
    @Basic(optional = false)
    @Column(name = "TXAMT2HC")
    private BigDecimal txamt2hc;
    @Basic(optional = false)
    @Column(name = "TXAMT3HC")
    private BigDecimal txamt3hc;
    @Basic(optional = false)
    @Column(name = "TXAMT4HC")
    private BigDecimal txamt4hc;
    @Basic(optional = false)
    @Column(name = "TXAMT5HC")
    private BigDecimal txamt5hc;
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
    @Column(name = "DISTNETHC")
    private BigDecimal distnethc;
    @Basic(optional = false)
    @Column(name = "RTGAMTHC")
    private BigDecimal rtgamthc;
    @Basic(optional = false)
    @Column(name = "AMTCOGSHC")
    private BigDecimal amtcogshc;
    @Basic(optional = false)
    @Column(name = "AMTCOSTHC")
    private BigDecimal amtcosthc;
    @Basic(optional = false)
    @Column(name = "AMTPRICHC")
    private BigDecimal amtprichc;
    @Basic(optional = false)
    @Column(name = "AMTEXTNHC")
    private BigDecimal amtextnhc;

    public Aribd() {
    }

    public Aribd(AribdPK aribdPK) {
        this.aribdPK = aribdPK;
    }

    public Aribd(AribdPK aribdPK, int audtdate, int audttime, String audtuser, String audtorg, String idinvc, String iditem, String iddist, String textdesc, short swmanlitem, String unitmeas, BigDecimal qtyinvc, BigDecimal amtcost, BigDecimal amtpric, BigDecimal amtextn, BigDecimal amtcogs, BigDecimal amttxbl, BigDecimal tottax, short swmanltx, BigDecimal basetax1, BigDecimal basetax2, BigDecimal basetax3, BigDecimal basetax4, BigDecimal basetax5, short taxstts1, short taxstts2, short taxstts3, short taxstts4, short taxstts5, short swtaxincl1, short swtaxincl2, short swtaxincl3, short swtaxincl4, short swtaxincl5, BigDecimal ratetax1, BigDecimal ratetax2, BigDecimal ratetax3, BigDecimal ratetax4, BigDecimal ratetax5, BigDecimal amttax1, BigDecimal amttax2, BigDecimal amttax3, BigDecimal amttax4, BigDecimal amttax5, String idacctrev, String idacctinv, String idacctcogs, String idjobproj, String contract, String project, String category, String resource, int transnbr, short costclass, int billdate, short swibt, short swdiscabl, int ocntline, BigDecimal rtgamt, BigDecimal rtgpercent, short rtgdays, int rtgdatedue, short swrtgddtov, short swrtgamtov, int values, BigDecimal rtgdisttc, BigDecimal rtgcogstc, BigDecimal rtgaltbtc, BigDecimal rtginvdist, BigDecimal rtginvcogs, BigDecimal rtginvaltb, BigDecimal txamt1rc, BigDecimal txamt2rc, BigDecimal txamt3rc, BigDecimal txamt4rc, BigDecimal txamt5rc, BigDecimal txtotrc, BigDecimal txbsert1tc, BigDecimal txbsert2tc, BigDecimal txbsert3tc, BigDecimal txbsert4tc, BigDecimal txbsert5tc, BigDecimal txamtrt1tc, BigDecimal txamtrt2tc, BigDecimal txamtrt3tc, BigDecimal txamtrt4tc, BigDecimal txamtrt5tc, BigDecimal txbse1hc, BigDecimal txbse2hc, BigDecimal txbse3hc, BigDecimal txbse4hc, BigDecimal txbse5hc, BigDecimal txamt1hc, BigDecimal txamt2hc, BigDecimal txamt3hc, BigDecimal txamt4hc, BigDecimal txamt5hc, BigDecimal txamtrt1hc, BigDecimal txamtrt2hc, BigDecimal txamtrt3hc, BigDecimal txamtrt4hc, BigDecimal txamtrt5hc, BigDecimal distnethc, BigDecimal rtgamthc, BigDecimal amtcogshc, BigDecimal amtcosthc, BigDecimal amtprichc, BigDecimal amtextnhc) {
        this.aribdPK = aribdPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.idinvc = idinvc;
        this.iditem = iditem;
        this.iddist = iddist;
        this.textdesc = textdesc;
        this.swmanlitem = swmanlitem;
        this.unitmeas = unitmeas;
        this.qtyinvc = qtyinvc;
        this.amtcost = amtcost;
        this.amtpric = amtpric;
        this.amtextn = amtextn;
        this.amtcogs = amtcogs;
        this.amttxbl = amttxbl;
        this.tottax = tottax;
        this.swmanltx = swmanltx;
        this.basetax1 = basetax1;
        this.basetax2 = basetax2;
        this.basetax3 = basetax3;
        this.basetax4 = basetax4;
        this.basetax5 = basetax5;
        this.taxstts1 = taxstts1;
        this.taxstts2 = taxstts2;
        this.taxstts3 = taxstts3;
        this.taxstts4 = taxstts4;
        this.taxstts5 = taxstts5;
        this.swtaxincl1 = swtaxincl1;
        this.swtaxincl2 = swtaxincl2;
        this.swtaxincl3 = swtaxincl3;
        this.swtaxincl4 = swtaxincl4;
        this.swtaxincl5 = swtaxincl5;
        this.ratetax1 = ratetax1;
        this.ratetax2 = ratetax2;
        this.ratetax3 = ratetax3;
        this.ratetax4 = ratetax4;
        this.ratetax5 = ratetax5;
        this.amttax1 = amttax1;
        this.amttax2 = amttax2;
        this.amttax3 = amttax3;
        this.amttax4 = amttax4;
        this.amttax5 = amttax5;
        this.idacctrev = idacctrev;
        this.idacctinv = idacctinv;
        this.idacctcogs = idacctcogs;
        this.idjobproj = idjobproj;
        this.contract = contract;
        this.project = project;
        this.category = category;
        this.resource = resource;
        this.transnbr = transnbr;
        this.costclass = costclass;
        this.billdate = billdate;
        this.swibt = swibt;
        this.swdiscabl = swdiscabl;
        this.ocntline = ocntline;
        this.rtgamt = rtgamt;
        this.rtgpercent = rtgpercent;
        this.rtgdays = rtgdays;
        this.rtgdatedue = rtgdatedue;
        this.swrtgddtov = swrtgddtov;
        this.swrtgamtov = swrtgamtov;
        this.values = values;
        this.rtgdisttc = rtgdisttc;
        this.rtgcogstc = rtgcogstc;
        this.rtgaltbtc = rtgaltbtc;
        this.rtginvdist = rtginvdist;
        this.rtginvcogs = rtginvcogs;
        this.rtginvaltb = rtginvaltb;
        this.txamt1rc = txamt1rc;
        this.txamt2rc = txamt2rc;
        this.txamt3rc = txamt3rc;
        this.txamt4rc = txamt4rc;
        this.txamt5rc = txamt5rc;
        this.txtotrc = txtotrc;
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
        this.txbse1hc = txbse1hc;
        this.txbse2hc = txbse2hc;
        this.txbse3hc = txbse3hc;
        this.txbse4hc = txbse4hc;
        this.txbse5hc = txbse5hc;
        this.txamt1hc = txamt1hc;
        this.txamt2hc = txamt2hc;
        this.txamt3hc = txamt3hc;
        this.txamt4hc = txamt4hc;
        this.txamt5hc = txamt5hc;
        this.txamtrt1hc = txamtrt1hc;
        this.txamtrt2hc = txamtrt2hc;
        this.txamtrt3hc = txamtrt3hc;
        this.txamtrt4hc = txamtrt4hc;
        this.txamtrt5hc = txamtrt5hc;
        this.distnethc = distnethc;
        this.rtgamthc = rtgamthc;
        this.amtcogshc = amtcogshc;
        this.amtcosthc = amtcosthc;
        this.amtprichc = amtprichc;
        this.amtextnhc = amtextnhc;
    }

    public Aribd(int cntbtch, int cntitem, int cntline) {
        this.aribdPK = new AribdPK(cntbtch, cntitem, cntline);
    }

    public AribdPK getAribdPK() {
        return aribdPK;
    }

    public void setAribdPK(AribdPK aribdPK) {
        this.aribdPK = aribdPK;
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

    public String getIdinvc() {
        return idinvc;
    }

    public void setIdinvc(String idinvc) {
        this.idinvc = idinvc;
    }

    public String getIditem() {
        return iditem;
    }

    public void setIditem(String iditem) {
        this.iditem = iditem;
    }

    public String getIddist() {
        return iddist;
    }

    public void setIddist(String iddist) {
        this.iddist = iddist;
    }

    public String getTextdesc() {
        return textdesc;
    }

    public void setTextdesc(String textdesc) {
        this.textdesc = textdesc;
    }

    public short getSwmanlitem() {
        return swmanlitem;
    }

    public void setSwmanlitem(short swmanlitem) {
        this.swmanlitem = swmanlitem;
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

    public BigDecimal getAmtpric() {
        return amtpric;
    }

    public void setAmtpric(BigDecimal amtpric) {
        this.amtpric = amtpric;
    }

    public BigDecimal getAmtextn() {
        return amtextn;
    }

    public void setAmtextn(BigDecimal amtextn) {
        this.amtextn = amtextn;
    }

    public BigDecimal getAmtcogs() {
        return amtcogs;
    }

    public void setAmtcogs(BigDecimal amtcogs) {
        this.amtcogs = amtcogs;
    }

    public BigDecimal getAmttxbl() {
        return amttxbl;
    }

    public void setAmttxbl(BigDecimal amttxbl) {
        this.amttxbl = amttxbl;
    }

    public BigDecimal getTottax() {
        return tottax;
    }

    public void setTottax(BigDecimal tottax) {
        this.tottax = tottax;
    }

    public short getSwmanltx() {
        return swmanltx;
    }

    public void setSwmanltx(short swmanltx) {
        this.swmanltx = swmanltx;
    }

    public BigDecimal getBasetax1() {
        return basetax1;
    }

    public void setBasetax1(BigDecimal basetax1) {
        this.basetax1 = basetax1;
    }

    public BigDecimal getBasetax2() {
        return basetax2;
    }

    public void setBasetax2(BigDecimal basetax2) {
        this.basetax2 = basetax2;
    }

    public BigDecimal getBasetax3() {
        return basetax3;
    }

    public void setBasetax3(BigDecimal basetax3) {
        this.basetax3 = basetax3;
    }

    public BigDecimal getBasetax4() {
        return basetax4;
    }

    public void setBasetax4(BigDecimal basetax4) {
        this.basetax4 = basetax4;
    }

    public BigDecimal getBasetax5() {
        return basetax5;
    }

    public void setBasetax5(BigDecimal basetax5) {
        this.basetax5 = basetax5;
    }

    public short getTaxstts1() {
        return taxstts1;
    }

    public void setTaxstts1(short taxstts1) {
        this.taxstts1 = taxstts1;
    }

    public short getTaxstts2() {
        return taxstts2;
    }

    public void setTaxstts2(short taxstts2) {
        this.taxstts2 = taxstts2;
    }

    public short getTaxstts3() {
        return taxstts3;
    }

    public void setTaxstts3(short taxstts3) {
        this.taxstts3 = taxstts3;
    }

    public short getTaxstts4() {
        return taxstts4;
    }

    public void setTaxstts4(short taxstts4) {
        this.taxstts4 = taxstts4;
    }

    public short getTaxstts5() {
        return taxstts5;
    }

    public void setTaxstts5(short taxstts5) {
        this.taxstts5 = taxstts5;
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

    public BigDecimal getRatetax1() {
        return ratetax1;
    }

    public void setRatetax1(BigDecimal ratetax1) {
        this.ratetax1 = ratetax1;
    }

    public BigDecimal getRatetax2() {
        return ratetax2;
    }

    public void setRatetax2(BigDecimal ratetax2) {
        this.ratetax2 = ratetax2;
    }

    public BigDecimal getRatetax3() {
        return ratetax3;
    }

    public void setRatetax3(BigDecimal ratetax3) {
        this.ratetax3 = ratetax3;
    }

    public BigDecimal getRatetax4() {
        return ratetax4;
    }

    public void setRatetax4(BigDecimal ratetax4) {
        this.ratetax4 = ratetax4;
    }

    public BigDecimal getRatetax5() {
        return ratetax5;
    }

    public void setRatetax5(BigDecimal ratetax5) {
        this.ratetax5 = ratetax5;
    }

    public BigDecimal getAmttax1() {
        return amttax1;
    }

    public void setAmttax1(BigDecimal amttax1) {
        this.amttax1 = amttax1;
    }

    public BigDecimal getAmttax2() {
        return amttax2;
    }

    public void setAmttax2(BigDecimal amttax2) {
        this.amttax2 = amttax2;
    }

    public BigDecimal getAmttax3() {
        return amttax3;
    }

    public void setAmttax3(BigDecimal amttax3) {
        this.amttax3 = amttax3;
    }

    public BigDecimal getAmttax4() {
        return amttax4;
    }

    public void setAmttax4(BigDecimal amttax4) {
        this.amttax4 = amttax4;
    }

    public BigDecimal getAmttax5() {
        return amttax5;
    }

    public void setAmttax5(BigDecimal amttax5) {
        this.amttax5 = amttax5;
    }

    public String getIdacctrev() {
        return idacctrev;
    }

    public void setIdacctrev(String idacctrev) {
        this.idacctrev = idacctrev;
    }

    public String getIdacctinv() {
        return idacctinv;
    }

    public void setIdacctinv(String idacctinv) {
        this.idacctinv = idacctinv;
    }

    public String getIdacctcogs() {
        return idacctcogs;
    }

    public void setIdacctcogs(String idacctcogs) {
        this.idacctcogs = idacctcogs;
    }

    public String getIdjobproj() {
        return idjobproj;
    }

    public void setIdjobproj(String idjobproj) {
        this.idjobproj = idjobproj;
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

    public int getBilldate() {
        return billdate;
    }

    public void setBilldate(int billdate) {
        this.billdate = billdate;
    }

    public short getSwibt() {
        return swibt;
    }

    public void setSwibt(short swibt) {
        this.swibt = swibt;
    }

    public short getSwdiscabl() {
        return swdiscabl;
    }

    public void setSwdiscabl(short swdiscabl) {
        this.swdiscabl = swdiscabl;
    }

    public int getOcntline() {
        return ocntline;
    }

    public void setOcntline(int ocntline) {
        this.ocntline = ocntline;
    }

    public BigDecimal getRtgamt() {
        return rtgamt;
    }

    public void setRtgamt(BigDecimal rtgamt) {
        this.rtgamt = rtgamt;
    }

    public BigDecimal getRtgpercent() {
        return rtgpercent;
    }

    public void setRtgpercent(BigDecimal rtgpercent) {
        this.rtgpercent = rtgpercent;
    }

    public short getRtgdays() {
        return rtgdays;
    }

    public void setRtgdays(short rtgdays) {
        this.rtgdays = rtgdays;
    }

    public int getRtgdatedue() {
        return rtgdatedue;
    }

    public void setRtgdatedue(int rtgdatedue) {
        this.rtgdatedue = rtgdatedue;
    }

    public short getSwrtgddtov() {
        return swrtgddtov;
    }

    public void setSwrtgddtov(short swrtgddtov) {
        this.swrtgddtov = swrtgddtov;
    }

    public short getSwrtgamtov() {
        return swrtgamtov;
    }

    public void setSwrtgamtov(short swrtgamtov) {
        this.swrtgamtov = swrtgamtov;
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

    public BigDecimal getRtginvdist() {
        return rtginvdist;
    }

    public void setRtginvdist(BigDecimal rtginvdist) {
        this.rtginvdist = rtginvdist;
    }

    public BigDecimal getRtginvcogs() {
        return rtginvcogs;
    }

    public void setRtginvcogs(BigDecimal rtginvcogs) {
        this.rtginvcogs = rtginvcogs;
    }

    public BigDecimal getRtginvaltb() {
        return rtginvaltb;
    }

    public void setRtginvaltb(BigDecimal rtginvaltb) {
        this.rtginvaltb = rtginvaltb;
    }

    public BigDecimal getTxamt1rc() {
        return txamt1rc;
    }

    public void setTxamt1rc(BigDecimal txamt1rc) {
        this.txamt1rc = txamt1rc;
    }

    public BigDecimal getTxamt2rc() {
        return txamt2rc;
    }

    public void setTxamt2rc(BigDecimal txamt2rc) {
        this.txamt2rc = txamt2rc;
    }

    public BigDecimal getTxamt3rc() {
        return txamt3rc;
    }

    public void setTxamt3rc(BigDecimal txamt3rc) {
        this.txamt3rc = txamt3rc;
    }

    public BigDecimal getTxamt4rc() {
        return txamt4rc;
    }

    public void setTxamt4rc(BigDecimal txamt4rc) {
        this.txamt4rc = txamt4rc;
    }

    public BigDecimal getTxamt5rc() {
        return txamt5rc;
    }

    public void setTxamt5rc(BigDecimal txamt5rc) {
        this.txamt5rc = txamt5rc;
    }

    public BigDecimal getTxtotrc() {
        return txtotrc;
    }

    public void setTxtotrc(BigDecimal txtotrc) {
        this.txtotrc = txtotrc;
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

    public BigDecimal getTxbse1hc() {
        return txbse1hc;
    }

    public void setTxbse1hc(BigDecimal txbse1hc) {
        this.txbse1hc = txbse1hc;
    }

    public BigDecimal getTxbse2hc() {
        return txbse2hc;
    }

    public void setTxbse2hc(BigDecimal txbse2hc) {
        this.txbse2hc = txbse2hc;
    }

    public BigDecimal getTxbse3hc() {
        return txbse3hc;
    }

    public void setTxbse3hc(BigDecimal txbse3hc) {
        this.txbse3hc = txbse3hc;
    }

    public BigDecimal getTxbse4hc() {
        return txbse4hc;
    }

    public void setTxbse4hc(BigDecimal txbse4hc) {
        this.txbse4hc = txbse4hc;
    }

    public BigDecimal getTxbse5hc() {
        return txbse5hc;
    }

    public void setTxbse5hc(BigDecimal txbse5hc) {
        this.txbse5hc = txbse5hc;
    }

    public BigDecimal getTxamt1hc() {
        return txamt1hc;
    }

    public void setTxamt1hc(BigDecimal txamt1hc) {
        this.txamt1hc = txamt1hc;
    }

    public BigDecimal getTxamt2hc() {
        return txamt2hc;
    }

    public void setTxamt2hc(BigDecimal txamt2hc) {
        this.txamt2hc = txamt2hc;
    }

    public BigDecimal getTxamt3hc() {
        return txamt3hc;
    }

    public void setTxamt3hc(BigDecimal txamt3hc) {
        this.txamt3hc = txamt3hc;
    }

    public BigDecimal getTxamt4hc() {
        return txamt4hc;
    }

    public void setTxamt4hc(BigDecimal txamt4hc) {
        this.txamt4hc = txamt4hc;
    }

    public BigDecimal getTxamt5hc() {
        return txamt5hc;
    }

    public void setTxamt5hc(BigDecimal txamt5hc) {
        this.txamt5hc = txamt5hc;
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

    public BigDecimal getDistnethc() {
        return distnethc;
    }

    public void setDistnethc(BigDecimal distnethc) {
        this.distnethc = distnethc;
    }

    public BigDecimal getRtgamthc() {
        return rtgamthc;
    }

    public void setRtgamthc(BigDecimal rtgamthc) {
        this.rtgamthc = rtgamthc;
    }

    public BigDecimal getAmtcogshc() {
        return amtcogshc;
    }

    public void setAmtcogshc(BigDecimal amtcogshc) {
        this.amtcogshc = amtcogshc;
    }

    public BigDecimal getAmtcosthc() {
        return amtcosthc;
    }

    public void setAmtcosthc(BigDecimal amtcosthc) {
        this.amtcosthc = amtcosthc;
    }

    public BigDecimal getAmtprichc() {
        return amtprichc;
    }

    public void setAmtprichc(BigDecimal amtprichc) {
        this.amtprichc = amtprichc;
    }

    public BigDecimal getAmtextnhc() {
        return amtextnhc;
    }

    public void setAmtextnhc(BigDecimal amtextnhc) {
        this.amtextnhc = amtextnhc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aribdPK != null ? aribdPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aribd)) {
            return false;
        }
        Aribd other = (Aribd) object;
        if ((this.aribdPK == null && other.aribdPK != null) || (this.aribdPK != null && !this.aribdPK.equals(other.aribdPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Aribd[aribdPK=" + aribdPK + "]";
    }

}
