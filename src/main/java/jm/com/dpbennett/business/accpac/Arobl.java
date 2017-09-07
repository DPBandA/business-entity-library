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
@Table(name = "AROBL")
@NamedQueries({
    @NamedQuery(name = "Arobl.findAll", query = "SELECT a FROM Arobl a"),
    @NamedQuery(name = "Arobl.findByIdcust", query = "SELECT a FROM Arobl a WHERE a.aroblPK.idcust = :idcust"),
    @NamedQuery(name = "Arobl.findByIdinvc", query = "SELECT a FROM Arobl a WHERE a.aroblPK.idinvc = :idinvc"),
    @NamedQuery(name = "Arobl.findByAudtdate", query = "SELECT a FROM Arobl a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Arobl.findByAudttime", query = "SELECT a FROM Arobl a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Arobl.findByAudtuser", query = "SELECT a FROM Arobl a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Arobl.findByAudtorg", query = "SELECT a FROM Arobl a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Arobl.findByIdrmit", query = "SELECT a FROM Arobl a WHERE a.idrmit = :idrmit"),
    @NamedQuery(name = "Arobl.findByIdordernbr", query = "SELECT a FROM Arobl a WHERE a.idordernbr = :idordernbr"),
    @NamedQuery(name = "Arobl.findByIdcustpo", query = "SELECT a FROM Arobl a WHERE a.idcustpo = :idcustpo"),
    @NamedQuery(name = "Arobl.findByDatedue", query = "SELECT a FROM Arobl a WHERE a.datedue = :datedue"),
    @NamedQuery(name = "Arobl.findByIdnatacct", query = "SELECT a FROM Arobl a WHERE a.idnatacct = :idnatacct"),
    @NamedQuery(name = "Arobl.findByIdcustshpt", query = "SELECT a FROM Arobl a WHERE a.idcustshpt = :idcustshpt"),
    @NamedQuery(name = "Arobl.findByTrxtypeid", query = "SELECT a FROM Arobl a WHERE a.trxtypeid = :trxtypeid"),
    @NamedQuery(name = "Arobl.findByTrxtypetxt", query = "SELECT a FROM Arobl a WHERE a.trxtypetxt = :trxtypetxt"),
    @NamedQuery(name = "Arobl.findByDatebtch", query = "SELECT a FROM Arobl a WHERE a.datebtch = :datebtch"),
    @NamedQuery(name = "Arobl.findByCntbtch", query = "SELECT a FROM Arobl a WHERE a.cntbtch = :cntbtch"),
    @NamedQuery(name = "Arobl.findByCntitem", query = "SELECT a FROM Arobl a WHERE a.cntitem = :cntitem"),
    @NamedQuery(name = "Arobl.findByIdgrp", query = "SELECT a FROM Arobl a WHERE a.idgrp = :idgrp"),
    @NamedQuery(name = "Arobl.findByDescinvc", query = "SELECT a FROM Arobl a WHERE a.descinvc = :descinvc"),
    @NamedQuery(name = "Arobl.findByDateinvc", query = "SELECT a FROM Arobl a WHERE a.dateinvc = :dateinvc"),
    @NamedQuery(name = "Arobl.findByDateasof", query = "SELECT a FROM Arobl a WHERE a.dateasof = :dateasof"),
    @NamedQuery(name = "Arobl.findByCodeterm", query = "SELECT a FROM Arobl a WHERE a.codeterm = :codeterm"),
    @NamedQuery(name = "Arobl.findByDatedisc", query = "SELECT a FROM Arobl a WHERE a.datedisc = :datedisc"),
    @NamedQuery(name = "Arobl.findByCodecurn", query = "SELECT a FROM Arobl a WHERE a.codecurn = :codecurn"),
    @NamedQuery(name = "Arobl.findByIdratetype", query = "SELECT a FROM Arobl a WHERE a.idratetype = :idratetype"),
    @NamedQuery(name = "Arobl.findBySwrateovrd", query = "SELECT a FROM Arobl a WHERE a.swrateovrd = :swrateovrd"),
    @NamedQuery(name = "Arobl.findByExchratehc", query = "SELECT a FROM Arobl a WHERE a.exchratehc = :exchratehc"),
    @NamedQuery(name = "Arobl.findByAmtinvchc", query = "SELECT a FROM Arobl a WHERE a.amtinvchc = :amtinvchc"),
    @NamedQuery(name = "Arobl.findByAmtduehc", query = "SELECT a FROM Arobl a WHERE a.amtduehc = :amtduehc"),
    @NamedQuery(name = "Arobl.findByAmttxblhc", query = "SELECT a FROM Arobl a WHERE a.amttxblhc = :amttxblhc"),
    @NamedQuery(name = "Arobl.findByAmtnontxhc", query = "SELECT a FROM Arobl a WHERE a.amtnontxhc = :amtnontxhc"),
    @NamedQuery(name = "Arobl.findByAmttaxhc", query = "SELECT a FROM Arobl a WHERE a.amttaxhc = :amttaxhc"),
    @NamedQuery(name = "Arobl.findByAmtdischc", query = "SELECT a FROM Arobl a WHERE a.amtdischc = :amtdischc"),
    @NamedQuery(name = "Arobl.findByAmtinvctc", query = "SELECT a FROM Arobl a WHERE a.amtinvctc = :amtinvctc"),
    @NamedQuery(name = "Arobl.findByAmtduetc", query = "SELECT a FROM Arobl a WHERE a.amtduetc = :amtduetc"),
    @NamedQuery(name = "Arobl.findByAmttxbltc", query = "SELECT a FROM Arobl a WHERE a.amttxbltc = :amttxbltc"),
    @NamedQuery(name = "Arobl.findByAmtnontxtc", query = "SELECT a FROM Arobl a WHERE a.amtnontxtc = :amtnontxtc"),
    @NamedQuery(name = "Arobl.findByAmttaxtc", query = "SELECT a FROM Arobl a WHERE a.amttaxtc = :amttaxtc"),
    @NamedQuery(name = "Arobl.findByAmtdisctc", query = "SELECT a FROM Arobl a WHERE a.amtdisctc = :amtdisctc"),
    @NamedQuery(name = "Arobl.findBySwpaid", query = "SELECT a FROM Arobl a WHERE a.swpaid = :swpaid"),
    @NamedQuery(name = "Arobl.findByDatelstact", query = "SELECT a FROM Arobl a WHERE a.datelstact = :datelstact"),
    @NamedQuery(name = "Arobl.findByDatelststm", query = "SELECT a FROM Arobl a WHERE a.datelststm = :datelststm"),
    @NamedQuery(name = "Arobl.findByDatelstdlq", query = "SELECT a FROM Arobl a WHERE a.datelstdlq = :datelstdlq"),
    @NamedQuery(name = "Arobl.findByCodedlqsts", query = "SELECT a FROM Arobl a WHERE a.codedlqsts = :codedlqsts"),
    @NamedQuery(name = "Arobl.findByCnttotpaym", query = "SELECT a FROM Arobl a WHERE a.cnttotpaym = :cnttotpaym"),
    @NamedQuery(name = "Arobl.findByCntlstpaid", query = "SELECT a FROM Arobl a WHERE a.cntlstpaid = :cntlstpaid"),
    @NamedQuery(name = "Arobl.findByCntlstpyst", query = "SELECT a FROM Arobl a WHERE a.cntlstpyst = :cntlstpyst"),
    @NamedQuery(name = "Arobl.findByAmtremit", query = "SELECT a FROM Arobl a WHERE a.amtremit = :amtremit"),
    @NamedQuery(name = "Arobl.findByCntlastseq", query = "SELECT a FROM Arobl a WHERE a.cntlastseq = :cntlastseq"),
    @NamedQuery(name = "Arobl.findBySwtaxinput", query = "SELECT a FROM Arobl a WHERE a.swtaxinput = :swtaxinput"),
    @NamedQuery(name = "Arobl.findByCodetax1", query = "SELECT a FROM Arobl a WHERE a.codetax1 = :codetax1"),
    @NamedQuery(name = "Arobl.findByCodetax2", query = "SELECT a FROM Arobl a WHERE a.codetax2 = :codetax2"),
    @NamedQuery(name = "Arobl.findByCodetax3", query = "SELECT a FROM Arobl a WHERE a.codetax3 = :codetax3"),
    @NamedQuery(name = "Arobl.findByCodetax4", query = "SELECT a FROM Arobl a WHERE a.codetax4 = :codetax4"),
    @NamedQuery(name = "Arobl.findByCodetax5", query = "SELECT a FROM Arobl a WHERE a.codetax5 = :codetax5"),
    @NamedQuery(name = "Arobl.findByAmtbase1hc", query = "SELECT a FROM Arobl a WHERE a.amtbase1hc = :amtbase1hc"),
    @NamedQuery(name = "Arobl.findByAmtbase2hc", query = "SELECT a FROM Arobl a WHERE a.amtbase2hc = :amtbase2hc"),
    @NamedQuery(name = "Arobl.findByAmtbase3hc", query = "SELECT a FROM Arobl a WHERE a.amtbase3hc = :amtbase3hc"),
    @NamedQuery(name = "Arobl.findByAmtbase4hc", query = "SELECT a FROM Arobl a WHERE a.amtbase4hc = :amtbase4hc"),
    @NamedQuery(name = "Arobl.findByAmtbase5hc", query = "SELECT a FROM Arobl a WHERE a.amtbase5hc = :amtbase5hc"),
    @NamedQuery(name = "Arobl.findByAmttax1hc", query = "SELECT a FROM Arobl a WHERE a.amttax1hc = :amttax1hc"),
    @NamedQuery(name = "Arobl.findByAmttax2hc", query = "SELECT a FROM Arobl a WHERE a.amttax2hc = :amttax2hc"),
    @NamedQuery(name = "Arobl.findByAmttax3hc", query = "SELECT a FROM Arobl a WHERE a.amttax3hc = :amttax3hc"),
    @NamedQuery(name = "Arobl.findByAmttax4hc", query = "SELECT a FROM Arobl a WHERE a.amttax4hc = :amttax4hc"),
    @NamedQuery(name = "Arobl.findByAmttax5hc", query = "SELECT a FROM Arobl a WHERE a.amttax5hc = :amttax5hc"),
    @NamedQuery(name = "Arobl.findByAmtbase1tc", query = "SELECT a FROM Arobl a WHERE a.amtbase1tc = :amtbase1tc"),
    @NamedQuery(name = "Arobl.findByAmtbase2tc", query = "SELECT a FROM Arobl a WHERE a.amtbase2tc = :amtbase2tc"),
    @NamedQuery(name = "Arobl.findByAmtbase3tc", query = "SELECT a FROM Arobl a WHERE a.amtbase3tc = :amtbase3tc"),
    @NamedQuery(name = "Arobl.findByAmtbase4tc", query = "SELECT a FROM Arobl a WHERE a.amtbase4tc = :amtbase4tc"),
    @NamedQuery(name = "Arobl.findByAmtbase5tc", query = "SELECT a FROM Arobl a WHERE a.amtbase5tc = :amtbase5tc"),
    @NamedQuery(name = "Arobl.findByAmttax1tc", query = "SELECT a FROM Arobl a WHERE a.amttax1tc = :amttax1tc"),
    @NamedQuery(name = "Arobl.findByAmttax2tc", query = "SELECT a FROM Arobl a WHERE a.amttax2tc = :amttax2tc"),
    @NamedQuery(name = "Arobl.findByAmttax3tc", query = "SELECT a FROM Arobl a WHERE a.amttax3tc = :amttax3tc"),
    @NamedQuery(name = "Arobl.findByAmttax4tc", query = "SELECT a FROM Arobl a WHERE a.amttax4tc = :amttax4tc"),
    @NamedQuery(name = "Arobl.findByAmttax5tc", query = "SELECT a FROM Arobl a WHERE a.amttax5tc = :amttax5tc"),
    @NamedQuery(name = "Arobl.findByCodeslsp1", query = "SELECT a FROM Arobl a WHERE a.codeslsp1 = :codeslsp1"),
    @NamedQuery(name = "Arobl.findByCodeslsp2", query = "SELECT a FROM Arobl a WHERE a.codeslsp2 = :codeslsp2"),
    @NamedQuery(name = "Arobl.findByCodeslsp3", query = "SELECT a FROM Arobl a WHERE a.codeslsp3 = :codeslsp3"),
    @NamedQuery(name = "Arobl.findByCodeslsp4", query = "SELECT a FROM Arobl a WHERE a.codeslsp4 = :codeslsp4"),
    @NamedQuery(name = "Arobl.findByCodeslsp5", query = "SELECT a FROM Arobl a WHERE a.codeslsp5 = :codeslsp5"),
    @NamedQuery(name = "Arobl.findByPctsasplt1", query = "SELECT a FROM Arobl a WHERE a.pctsasplt1 = :pctsasplt1"),
    @NamedQuery(name = "Arobl.findByPctsasplt2", query = "SELECT a FROM Arobl a WHERE a.pctsasplt2 = :pctsasplt2"),
    @NamedQuery(name = "Arobl.findByPctsasplt3", query = "SELECT a FROM Arobl a WHERE a.pctsasplt3 = :pctsasplt3"),
    @NamedQuery(name = "Arobl.findByPctsasplt4", query = "SELECT a FROM Arobl a WHERE a.pctsasplt4 = :pctsasplt4"),
    @NamedQuery(name = "Arobl.findByPctsasplt5", query = "SELECT a FROM Arobl a WHERE a.pctsasplt5 = :pctsasplt5"),
    @NamedQuery(name = "Arobl.findByFiscyr", query = "SELECT a FROM Arobl a WHERE a.fiscyr = :fiscyr"),
    @NamedQuery(name = "Arobl.findByFiscper", query = "SELECT a FROM Arobl a WHERE a.fiscper = :fiscper"),
    @NamedQuery(name = "Arobl.findByIdprepaid", query = "SELECT a FROM Arobl a WHERE a.idprepaid = :idprepaid"),
    @NamedQuery(name = "Arobl.findByDatebus", query = "SELECT a FROM Arobl a WHERE a.datebus = :datebus"),
    @NamedQuery(name = "Arobl.findByRatedate", query = "SELECT a FROM Arobl a WHERE a.ratedate = :ratedate"),
    @NamedQuery(name = "Arobl.findByRateop", query = "SELECT a FROM Arobl a WHERE a.rateop = :rateop"),
    @NamedQuery(name = "Arobl.findByYplastact", query = "SELECT a FROM Arobl a WHERE a.yplastact = :yplastact"),
    @NamedQuery(name = "Arobl.findByIdbank", query = "SELECT a FROM Arobl a WHERE a.idbank = :idbank"),
    @NamedQuery(name = "Arobl.findByDepstnbr", query = "SELECT a FROM Arobl a WHERE a.depstnbr = :depstnbr"),
    @NamedQuery(name = "Arobl.findByPostseqnce", query = "SELECT a FROM Arobl a WHERE a.postseqnce = :postseqnce"),
    @NamedQuery(name = "Arobl.findBySwjob", query = "SELECT a FROM Arobl a WHERE a.swjob = :swjob"),
    @NamedQuery(name = "Arobl.findBySwrtg", query = "SELECT a FROM Arobl a WHERE a.swrtg = :swrtg"),
    @NamedQuery(name = "Arobl.findBySwrtgout", query = "SELECT a FROM Arobl a WHERE a.swrtgout = :swrtgout"),
    @NamedQuery(name = "Arobl.findByRtgdatedue", query = "SELECT a FROM Arobl a WHERE a.rtgdatedue = :rtgdatedue"),
    @NamedQuery(name = "Arobl.findByRtgoamthc", query = "SELECT a FROM Arobl a WHERE a.rtgoamthc = :rtgoamthc"),
    @NamedQuery(name = "Arobl.findByRtgamthc", query = "SELECT a FROM Arobl a WHERE a.rtgamthc = :rtgamthc"),
    @NamedQuery(name = "Arobl.findByRtgoamttc", query = "SELECT a FROM Arobl a WHERE a.rtgoamttc = :rtgoamttc"),
    @NamedQuery(name = "Arobl.findByRtgamttc", query = "SELECT a FROM Arobl a WHERE a.rtgamttc = :rtgamttc"),
    @NamedQuery(name = "Arobl.findByRtgterms", query = "SELECT a FROM Arobl a WHERE a.rtgterms = :rtgterms"),
    @NamedQuery(name = "Arobl.findBySwrtgrate", query = "SELECT a FROM Arobl a WHERE a.swrtgrate = :swrtgrate"),
    @NamedQuery(name = "Arobl.findByRtgapplyto", query = "SELECT a FROM Arobl a WHERE a.rtgapplyto = :rtgapplyto"),
    @NamedQuery(name = "Arobl.findByValues", query = "SELECT a FROM Arobl a WHERE a.values = :values"),
    @NamedQuery(name = "Arobl.findBySrceappl", query = "SELECT a FROM Arobl a WHERE a.srceappl = :srceappl"),
    @NamedQuery(name = "Arobl.findByArversion", query = "SELECT a FROM Arobl a WHERE a.arversion = :arversion"),
    @NamedQuery(name = "Arobl.findByInvctype", query = "SELECT a FROM Arobl a WHERE a.invctype = :invctype"),
    @NamedQuery(name = "Arobl.findByDepseq", query = "SELECT a FROM Arobl a WHERE a.depseq = :depseq"),
    @NamedQuery(name = "Arobl.findByDepline", query = "SELECT a FROM Arobl a WHERE a.depline = :depline"),
    @NamedQuery(name = "Arobl.findByTypebtch", query = "SELECT a FROM Arobl a WHERE a.typebtch = :typebtch"),
    @NamedQuery(name = "Arobl.findByCntoblj", query = "SELECT a FROM Arobl a WHERE a.cntoblj = :cntoblj"),
    @NamedQuery(name = "Arobl.findByCodecurnrc", query = "SELECT a FROM Arobl a WHERE a.codecurnrc = :codecurnrc"),
    @NamedQuery(name = "Arobl.findByRaterc", query = "SELECT a FROM Arobl a WHERE a.raterc = :raterc"),
    @NamedQuery(name = "Arobl.findByRatetyperc", query = "SELECT a FROM Arobl a WHERE a.ratetyperc = :ratetyperc"),
    @NamedQuery(name = "Arobl.findByRatedaterc", query = "SELECT a FROM Arobl a WHERE a.ratedaterc = :ratedaterc"),
    @NamedQuery(name = "Arobl.findByRateoprc", query = "SELECT a FROM Arobl a WHERE a.rateoprc = :rateoprc"),
    @NamedQuery(name = "Arobl.findBySwraterc", query = "SELECT a FROM Arobl a WHERE a.swraterc = :swraterc"),
    @NamedQuery(name = "Arobl.findBySwtxrtgrpt", query = "SELECT a FROM Arobl a WHERE a.swtxrtgrpt = :swtxrtgrpt"),
    @NamedQuery(name = "Arobl.findByCodetaxgrp", query = "SELECT a FROM Arobl a WHERE a.codetaxgrp = :codetaxgrp"),
    @NamedQuery(name = "Arobl.findByTaxversion", query = "SELECT a FROM Arobl a WHERE a.taxversion = :taxversion"),
    @NamedQuery(name = "Arobl.findBySwtxctlrc", query = "SELECT a FROM Arobl a WHERE a.swtxctlrc = :swtxctlrc"),
    @NamedQuery(name = "Arobl.findByTaxclass1", query = "SELECT a FROM Arobl a WHERE a.taxclass1 = :taxclass1"),
    @NamedQuery(name = "Arobl.findByTaxclass2", query = "SELECT a FROM Arobl a WHERE a.taxclass2 = :taxclass2"),
    @NamedQuery(name = "Arobl.findByTaxclass3", query = "SELECT a FROM Arobl a WHERE a.taxclass3 = :taxclass3"),
    @NamedQuery(name = "Arobl.findByTaxclass4", query = "SELECT a FROM Arobl a WHERE a.taxclass4 = :taxclass4"),
    @NamedQuery(name = "Arobl.findByTaxclass5", query = "SELECT a FROM Arobl a WHERE a.taxclass5 = :taxclass5"),
    @NamedQuery(name = "Arobl.findByTxbsert1tc", query = "SELECT a FROM Arobl a WHERE a.txbsert1tc = :txbsert1tc"),
    @NamedQuery(name = "Arobl.findByTxbsert2tc", query = "SELECT a FROM Arobl a WHERE a.txbsert2tc = :txbsert2tc"),
    @NamedQuery(name = "Arobl.findByTxbsert3tc", query = "SELECT a FROM Arobl a WHERE a.txbsert3tc = :txbsert3tc"),
    @NamedQuery(name = "Arobl.findByTxbsert4tc", query = "SELECT a FROM Arobl a WHERE a.txbsert4tc = :txbsert4tc"),
    @NamedQuery(name = "Arobl.findByTxbsert5tc", query = "SELECT a FROM Arobl a WHERE a.txbsert5tc = :txbsert5tc"),
    @NamedQuery(name = "Arobl.findByTxamtrt1tc", query = "SELECT a FROM Arobl a WHERE a.txamtrt1tc = :txamtrt1tc"),
    @NamedQuery(name = "Arobl.findByTxamtrt2tc", query = "SELECT a FROM Arobl a WHERE a.txamtrt2tc = :txamtrt2tc"),
    @NamedQuery(name = "Arobl.findByTxamtrt3tc", query = "SELECT a FROM Arobl a WHERE a.txamtrt3tc = :txamtrt3tc"),
    @NamedQuery(name = "Arobl.findByTxamtrt4tc", query = "SELECT a FROM Arobl a WHERE a.txamtrt4tc = :txamtrt4tc"),
    @NamedQuery(name = "Arobl.findByTxamtrt5tc", query = "SELECT a FROM Arobl a WHERE a.txamtrt5tc = :txamtrt5tc"),
    @NamedQuery(name = "Arobl.findByIdshipnbr", query = "SELECT a FROM Arobl a WHERE a.idshipnbr = :idshipnbr"),
    @NamedQuery(name = "Arobl.findByDatefrstbk", query = "SELECT a FROM Arobl a WHERE a.datefrstbk = :datefrstbk"),
    @NamedQuery(name = "Arobl.findByDatelstrvl", query = "SELECT a FROM Arobl a WHERE a.datelstrvl = :datelstrvl"),
    @NamedQuery(name = "Arobl.findByOrate", query = "SELECT a FROM Arobl a WHERE a.orate = :orate"),
    @NamedQuery(name = "Arobl.findByOratetype", query = "SELECT a FROM Arobl a WHERE a.oratetype = :oratetype"),
    @NamedQuery(name = "Arobl.findByOratedate", query = "SELECT a FROM Arobl a WHERE a.oratedate = :oratedate"),
    @NamedQuery(name = "Arobl.findByOrateop", query = "SELECT a FROM Arobl a WHERE a.orateop = :orateop"),
    @NamedQuery(name = "Arobl.findByOswrate", query = "SELECT a FROM Arobl a WHERE a.oswrate = :oswrate"),
    @NamedQuery(name = "Arobl.findByIdacctset", query = "SELECT a FROM Arobl a WHERE a.idacctset = :idacctset"),
    @NamedQuery(name = "Arobl.findByDatepaid", query = "SELECT a FROM Arobl a WHERE a.datepaid = :datepaid"),
    @NamedQuery(name = "Arobl.findBySwnonrcvbl", query = "SELECT a FROM Arobl a WHERE a.swnonrcvbl = :swnonrcvbl"),
    @NamedQuery(name = "Arobl.findByCodeterr", query = "SELECT a FROM Arobl a WHERE a.codeterr = :codeterr")})
public class Arobl implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AroblPK aroblPK;
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
    @Column(name = "IDORDERNBR")
    private String idordernbr;
    @Basic(optional = false)
    @Column(name = "IDCUSTPO")
    private String idcustpo;
    @Basic(optional = false)
    @Column(name = "DATEDUE")
    private int datedue;
    @Basic(optional = false)
    @Column(name = "IDNATACCT")
    private String idnatacct;
    @Basic(optional = false)
    @Column(name = "IDCUSTSHPT")
    private String idcustshpt;
    @Basic(optional = false)
    @Column(name = "TRXTYPEID")
    private short trxtypeid;
    @Basic(optional = false)
    @Column(name = "TRXTYPETXT")
    private short trxtypetxt;
    @Basic(optional = false)
    @Column(name = "DATEBTCH")
    private int datebtch;
    @Basic(optional = false)
    @Column(name = "CNTBTCH")
    private int cntbtch;
    @Basic(optional = false)
    @Column(name = "CNTITEM")
    private int cntitem;
    @Basic(optional = false)
    @Column(name = "IDGRP")
    private String idgrp;
    @Basic(optional = false)
    @Column(name = "DESCINVC")
    private String descinvc;
    @Basic(optional = false)
    @Column(name = "DATEINVC")
    private int dateinvc;
    @Basic(optional = false)
    @Column(name = "DATEASOF")
    private int dateasof;
    @Basic(optional = false)
    @Column(name = "CODETERM")
    private String codeterm;
    @Basic(optional = false)
    @Column(name = "DATEDISC")
    private int datedisc;
    @Basic(optional = false)
    @Column(name = "CODECURN")
    private String codecurn;
    @Basic(optional = false)
    @Column(name = "IDRATETYPE")
    private String idratetype;
    @Basic(optional = false)
    @Column(name = "SWRATEOVRD")
    private short swrateovrd;
    @Basic(optional = false)
    @Column(name = "EXCHRATEHC")
    private BigDecimal exchratehc;
    @Basic(optional = false)
    @Column(name = "AMTINVCHC")
    private BigDecimal amtinvchc;
    @Basic(optional = false)
    @Column(name = "AMTDUEHC")
    private BigDecimal amtduehc;
    @Basic(optional = false)
    @Column(name = "AMTTXBLHC")
    private BigDecimal amttxblhc;
    @Basic(optional = false)
    @Column(name = "AMTNONTXHC")
    private BigDecimal amtnontxhc;
    @Basic(optional = false)
    @Column(name = "AMTTAXHC")
    private BigDecimal amttaxhc;
    @Basic(optional = false)
    @Column(name = "AMTDISCHC")
    private BigDecimal amtdischc;
    @Basic(optional = false)
    @Column(name = "AMTINVCTC")
    private BigDecimal amtinvctc;
    @Basic(optional = false)
    @Column(name = "AMTDUETC")
    private BigDecimal amtduetc;
    @Basic(optional = false)
    @Column(name = "AMTTXBLTC")
    private BigDecimal amttxbltc;
    @Basic(optional = false)
    @Column(name = "AMTNONTXTC")
    private BigDecimal amtnontxtc;
    @Basic(optional = false)
    @Column(name = "AMTTAXTC")
    private BigDecimal amttaxtc;
    @Basic(optional = false)
    @Column(name = "AMTDISCTC")
    private BigDecimal amtdisctc;
    @Basic(optional = false)
    @Column(name = "SWPAID")
    private short swpaid;
    @Basic(optional = false)
    @Column(name = "DATELSTACT")
    private int datelstact;
    @Basic(optional = false)
    @Column(name = "DATELSTSTM")
    private int datelststm;
    @Basic(optional = false)
    @Column(name = "DATELSTDLQ")
    private int datelstdlq;
    @Basic(optional = false)
    @Column(name = "CODEDLQSTS")
    private short codedlqsts;
    @Basic(optional = false)
    @Column(name = "CNTTOTPAYM")
    private int cnttotpaym;
    @Basic(optional = false)
    @Column(name = "CNTLSTPAID")
    private int cntlstpaid;
    @Basic(optional = false)
    @Column(name = "CNTLSTPYST")
    private int cntlstpyst;
    @Basic(optional = false)
    @Column(name = "AMTREMIT")
    private BigDecimal amtremit;
    @Basic(optional = false)
    @Column(name = "CNTLASTSEQ")
    private int cntlastseq;
    @Basic(optional = false)
    @Column(name = "SWTAXINPUT")
    private short swtaxinput;
    @Basic(optional = false)
    @Column(name = "CODETAX1")
    private String codetax1;
    @Basic(optional = false)
    @Column(name = "CODETAX2")
    private String codetax2;
    @Basic(optional = false)
    @Column(name = "CODETAX3")
    private String codetax3;
    @Basic(optional = false)
    @Column(name = "CODETAX4")
    private String codetax4;
    @Basic(optional = false)
    @Column(name = "CODETAX5")
    private String codetax5;
    @Basic(optional = false)
    @Column(name = "AMTBASE1HC")
    private BigDecimal amtbase1hc;
    @Basic(optional = false)
    @Column(name = "AMTBASE2HC")
    private BigDecimal amtbase2hc;
    @Basic(optional = false)
    @Column(name = "AMTBASE3HC")
    private BigDecimal amtbase3hc;
    @Basic(optional = false)
    @Column(name = "AMTBASE4HC")
    private BigDecimal amtbase4hc;
    @Basic(optional = false)
    @Column(name = "AMTBASE5HC")
    private BigDecimal amtbase5hc;
    @Basic(optional = false)
    @Column(name = "AMTTAX1HC")
    private BigDecimal amttax1hc;
    @Basic(optional = false)
    @Column(name = "AMTTAX2HC")
    private BigDecimal amttax2hc;
    @Basic(optional = false)
    @Column(name = "AMTTAX3HC")
    private BigDecimal amttax3hc;
    @Basic(optional = false)
    @Column(name = "AMTTAX4HC")
    private BigDecimal amttax4hc;
    @Basic(optional = false)
    @Column(name = "AMTTAX5HC")
    private BigDecimal amttax5hc;
    @Basic(optional = false)
    @Column(name = "AMTBASE1TC")
    private BigDecimal amtbase1tc;
    @Basic(optional = false)
    @Column(name = "AMTBASE2TC")
    private BigDecimal amtbase2tc;
    @Basic(optional = false)
    @Column(name = "AMTBASE3TC")
    private BigDecimal amtbase3tc;
    @Basic(optional = false)
    @Column(name = "AMTBASE4TC")
    private BigDecimal amtbase4tc;
    @Basic(optional = false)
    @Column(name = "AMTBASE5TC")
    private BigDecimal amtbase5tc;
    @Basic(optional = false)
    @Column(name = "AMTTAX1TC")
    private BigDecimal amttax1tc;
    @Basic(optional = false)
    @Column(name = "AMTTAX2TC")
    private BigDecimal amttax2tc;
    @Basic(optional = false)
    @Column(name = "AMTTAX3TC")
    private BigDecimal amttax3tc;
    @Basic(optional = false)
    @Column(name = "AMTTAX4TC")
    private BigDecimal amttax4tc;
    @Basic(optional = false)
    @Column(name = "AMTTAX5TC")
    private BigDecimal amttax5tc;
    @Basic(optional = false)
    @Column(name = "CODESLSP1")
    private String codeslsp1;
    @Basic(optional = false)
    @Column(name = "CODESLSP2")
    private String codeslsp2;
    @Basic(optional = false)
    @Column(name = "CODESLSP3")
    private String codeslsp3;
    @Basic(optional = false)
    @Column(name = "CODESLSP4")
    private String codeslsp4;
    @Basic(optional = false)
    @Column(name = "CODESLSP5")
    private String codeslsp5;
    @Basic(optional = false)
    @Column(name = "PCTSASPLT1")
    private BigDecimal pctsasplt1;
    @Basic(optional = false)
    @Column(name = "PCTSASPLT2")
    private BigDecimal pctsasplt2;
    @Basic(optional = false)
    @Column(name = "PCTSASPLT3")
    private BigDecimal pctsasplt3;
    @Basic(optional = false)
    @Column(name = "PCTSASPLT4")
    private BigDecimal pctsasplt4;
    @Basic(optional = false)
    @Column(name = "PCTSASPLT5")
    private BigDecimal pctsasplt5;
    @Basic(optional = false)
    @Column(name = "FISCYR")
    private String fiscyr;
    @Basic(optional = false)
    @Column(name = "FISCPER")
    private String fiscper;
    @Basic(optional = false)
    @Column(name = "IDPREPAID")
    private String idprepaid;
    @Basic(optional = false)
    @Column(name = "DATEBUS")
    private int datebus;
    @Basic(optional = false)
    @Column(name = "RATEDATE")
    private int ratedate;
    @Basic(optional = false)
    @Column(name = "RATEOP")
    private short rateop;
    @Basic(optional = false)
    @Column(name = "YPLASTACT")
    private String yplastact;
    @Basic(optional = false)
    @Column(name = "IDBANK")
    private String idbank;
    @Basic(optional = false)
    @Column(name = "DEPSTNBR")
    private int depstnbr;
    @Basic(optional = false)
    @Column(name = "POSTSEQNCE")
    private int postseqnce;
    @Basic(optional = false)
    @Column(name = "SWJOB")
    private short swjob;
    @Basic(optional = false)
    @Column(name = "SWRTG")
    private short swrtg;
    @Basic(optional = false)
    @Column(name = "SWRTGOUT")
    private short swrtgout;
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
    @Column(name = "RTGTERMS")
    private String rtgterms;
    @Basic(optional = false)
    @Column(name = "SWRTGRATE")
    private short swrtgrate;
    @Basic(optional = false)
    @Column(name = "RTGAPPLYTO")
    private String rtgapplyto;
    @Basic(optional = false)
    @Column(name = "VALUES")
    private int values;
    @Basic(optional = false)
    @Column(name = "SRCEAPPL")
    private String srceappl;
    @Basic(optional = false)
    @Column(name = "ARVERSION")
    private String arversion;
    @Basic(optional = false)
    @Column(name = "INVCTYPE")
    private short invctype;
    @Basic(optional = false)
    @Column(name = "DEPSEQ")
    private int depseq;
    @Basic(optional = false)
    @Column(name = "DEPLINE")
    private int depline;
    @Basic(optional = false)
    @Column(name = "TYPEBTCH")
    private String typebtch;
    @Basic(optional = false)
    @Column(name = "CNTOBLJ")
    private int cntoblj;
    @Basic(optional = false)
    @Column(name = "CODECURNRC")
    private String codecurnrc;
    @Basic(optional = false)
    @Column(name = "RATERC")
    private BigDecimal raterc;
    @Basic(optional = false)
    @Column(name = "RATETYPERC")
    private String ratetyperc;
    @Basic(optional = false)
    @Column(name = "RATEDATERC")
    private int ratedaterc;
    @Basic(optional = false)
    @Column(name = "RATEOPRC")
    private short rateoprc;
    @Basic(optional = false)
    @Column(name = "SWRATERC")
    private short swraterc;
    @Basic(optional = false)
    @Column(name = "SWTXRTGRPT")
    private short swtxrtgrpt;
    @Basic(optional = false)
    @Column(name = "CODETAXGRP")
    private String codetaxgrp;
    @Basic(optional = false)
    @Column(name = "TAXVERSION")
    private int taxversion;
    @Basic(optional = false)
    @Column(name = "SWTXCTLRC")
    private short swtxctlrc;
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
    @Column(name = "IDSHIPNBR")
    private String idshipnbr;
    @Basic(optional = false)
    @Column(name = "DATEFRSTBK")
    private int datefrstbk;
    @Basic(optional = false)
    @Column(name = "DATELSTRVL")
    private int datelstrvl;
    @Basic(optional = false)
    @Column(name = "ORATE")
    private BigDecimal orate;
    @Basic(optional = false)
    @Column(name = "ORATETYPE")
    private String oratetype;
    @Basic(optional = false)
    @Column(name = "ORATEDATE")
    private int oratedate;
    @Basic(optional = false)
    @Column(name = "ORATEOP")
    private short orateop;
    @Basic(optional = false)
    @Column(name = "OSWRATE")
    private short oswrate;
    @Basic(optional = false)
    @Column(name = "IDACCTSET")
    private String idacctset;
    @Basic(optional = false)
    @Column(name = "DATEPAID")
    private int datepaid;
    @Basic(optional = false)
    @Column(name = "SWNONRCVBL")
    private short swnonrcvbl;
    @Basic(optional = false)
    @Column(name = "CODETERR")
    private String codeterr;

    public Arobl() {
    }

    public Arobl(AroblPK aroblPK) {
        this.aroblPK = aroblPK;
    }

    public Arobl(AroblPK aroblPK, int audtdate, int audttime, String audtuser, String audtorg, String idrmit, String idordernbr, String idcustpo, int datedue, String idnatacct, String idcustshpt, short trxtypeid, short trxtypetxt, int datebtch, int cntbtch, int cntitem, String idgrp, String descinvc, int dateinvc, int dateasof, String codeterm, int datedisc, String codecurn, String idratetype, short swrateovrd, BigDecimal exchratehc, BigDecimal amtinvchc, BigDecimal amtduehc, BigDecimal amttxblhc, BigDecimal amtnontxhc, BigDecimal amttaxhc, BigDecimal amtdischc, BigDecimal amtinvctc, BigDecimal amtduetc, BigDecimal amttxbltc, BigDecimal amtnontxtc, BigDecimal amttaxtc, BigDecimal amtdisctc, short swpaid, int datelstact, int datelststm, int datelstdlq, short codedlqsts, int cnttotpaym, int cntlstpaid, int cntlstpyst, BigDecimal amtremit, int cntlastseq, short swtaxinput, String codetax1, String codetax2, String codetax3, String codetax4, String codetax5, BigDecimal amtbase1hc, BigDecimal amtbase2hc, BigDecimal amtbase3hc, BigDecimal amtbase4hc, BigDecimal amtbase5hc, BigDecimal amttax1hc, BigDecimal amttax2hc, BigDecimal amttax3hc, BigDecimal amttax4hc, BigDecimal amttax5hc, BigDecimal amtbase1tc, BigDecimal amtbase2tc, BigDecimal amtbase3tc, BigDecimal amtbase4tc, BigDecimal amtbase5tc, BigDecimal amttax1tc, BigDecimal amttax2tc, BigDecimal amttax3tc, BigDecimal amttax4tc, BigDecimal amttax5tc, String codeslsp1, String codeslsp2, String codeslsp3, String codeslsp4, String codeslsp5, BigDecimal pctsasplt1, BigDecimal pctsasplt2, BigDecimal pctsasplt3, BigDecimal pctsasplt4, BigDecimal pctsasplt5, String fiscyr, String fiscper, String idprepaid, int datebus, int ratedate, short rateop, String yplastact, String idbank, int depstnbr, int postseqnce, short swjob, short swrtg, short swrtgout, int rtgdatedue, BigDecimal rtgoamthc, BigDecimal rtgamthc, BigDecimal rtgoamttc, BigDecimal rtgamttc, String rtgterms, short swrtgrate, String rtgapplyto, int values, String srceappl, String arversion, short invctype, int depseq, int depline, String typebtch, int cntoblj, String codecurnrc, BigDecimal raterc, String ratetyperc, int ratedaterc, short rateoprc, short swraterc, short swtxrtgrpt, String codetaxgrp, int taxversion, short swtxctlrc, short taxclass1, short taxclass2, short taxclass3, short taxclass4, short taxclass5, BigDecimal txbsert1tc, BigDecimal txbsert2tc, BigDecimal txbsert3tc, BigDecimal txbsert4tc, BigDecimal txbsert5tc, BigDecimal txamtrt1tc, BigDecimal txamtrt2tc, BigDecimal txamtrt3tc, BigDecimal txamtrt4tc, BigDecimal txamtrt5tc, String idshipnbr, int datefrstbk, int datelstrvl, BigDecimal orate, String oratetype, int oratedate, short orateop, short oswrate, String idacctset, int datepaid, short swnonrcvbl, String codeterr) {
        this.aroblPK = aroblPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.idrmit = idrmit;
        this.idordernbr = idordernbr;
        this.idcustpo = idcustpo;
        this.datedue = datedue;
        this.idnatacct = idnatacct;
        this.idcustshpt = idcustshpt;
        this.trxtypeid = trxtypeid;
        this.trxtypetxt = trxtypetxt;
        this.datebtch = datebtch;
        this.cntbtch = cntbtch;
        this.cntitem = cntitem;
        this.idgrp = idgrp;
        this.descinvc = descinvc;
        this.dateinvc = dateinvc;
        this.dateasof = dateasof;
        this.codeterm = codeterm;
        this.datedisc = datedisc;
        this.codecurn = codecurn;
        this.idratetype = idratetype;
        this.swrateovrd = swrateovrd;
        this.exchratehc = exchratehc;
        this.amtinvchc = amtinvchc;
        this.amtduehc = amtduehc;
        this.amttxblhc = amttxblhc;
        this.amtnontxhc = amtnontxhc;
        this.amttaxhc = amttaxhc;
        this.amtdischc = amtdischc;
        this.amtinvctc = amtinvctc;
        this.amtduetc = amtduetc;
        this.amttxbltc = amttxbltc;
        this.amtnontxtc = amtnontxtc;
        this.amttaxtc = amttaxtc;
        this.amtdisctc = amtdisctc;
        this.swpaid = swpaid;
        this.datelstact = datelstact;
        this.datelststm = datelststm;
        this.datelstdlq = datelstdlq;
        this.codedlqsts = codedlqsts;
        this.cnttotpaym = cnttotpaym;
        this.cntlstpaid = cntlstpaid;
        this.cntlstpyst = cntlstpyst;
        this.amtremit = amtremit;
        this.cntlastseq = cntlastseq;
        this.swtaxinput = swtaxinput;
        this.codetax1 = codetax1;
        this.codetax2 = codetax2;
        this.codetax3 = codetax3;
        this.codetax4 = codetax4;
        this.codetax5 = codetax5;
        this.amtbase1hc = amtbase1hc;
        this.amtbase2hc = amtbase2hc;
        this.amtbase3hc = amtbase3hc;
        this.amtbase4hc = amtbase4hc;
        this.amtbase5hc = amtbase5hc;
        this.amttax1hc = amttax1hc;
        this.amttax2hc = amttax2hc;
        this.amttax3hc = amttax3hc;
        this.amttax4hc = amttax4hc;
        this.amttax5hc = amttax5hc;
        this.amtbase1tc = amtbase1tc;
        this.amtbase2tc = amtbase2tc;
        this.amtbase3tc = amtbase3tc;
        this.amtbase4tc = amtbase4tc;
        this.amtbase5tc = amtbase5tc;
        this.amttax1tc = amttax1tc;
        this.amttax2tc = amttax2tc;
        this.amttax3tc = amttax3tc;
        this.amttax4tc = amttax4tc;
        this.amttax5tc = amttax5tc;
        this.codeslsp1 = codeslsp1;
        this.codeslsp2 = codeslsp2;
        this.codeslsp3 = codeslsp3;
        this.codeslsp4 = codeslsp4;
        this.codeslsp5 = codeslsp5;
        this.pctsasplt1 = pctsasplt1;
        this.pctsasplt2 = pctsasplt2;
        this.pctsasplt3 = pctsasplt3;
        this.pctsasplt4 = pctsasplt4;
        this.pctsasplt5 = pctsasplt5;
        this.fiscyr = fiscyr;
        this.fiscper = fiscper;
        this.idprepaid = idprepaid;
        this.datebus = datebus;
        this.ratedate = ratedate;
        this.rateop = rateop;
        this.yplastact = yplastact;
        this.idbank = idbank;
        this.depstnbr = depstnbr;
        this.postseqnce = postseqnce;
        this.swjob = swjob;
        this.swrtg = swrtg;
        this.swrtgout = swrtgout;
        this.rtgdatedue = rtgdatedue;
        this.rtgoamthc = rtgoamthc;
        this.rtgamthc = rtgamthc;
        this.rtgoamttc = rtgoamttc;
        this.rtgamttc = rtgamttc;
        this.rtgterms = rtgterms;
        this.swrtgrate = swrtgrate;
        this.rtgapplyto = rtgapplyto;
        this.values = values;
        this.srceappl = srceappl;
        this.arversion = arversion;
        this.invctype = invctype;
        this.depseq = depseq;
        this.depline = depline;
        this.typebtch = typebtch;
        this.cntoblj = cntoblj;
        this.codecurnrc = codecurnrc;
        this.raterc = raterc;
        this.ratetyperc = ratetyperc;
        this.ratedaterc = ratedaterc;
        this.rateoprc = rateoprc;
        this.swraterc = swraterc;
        this.swtxrtgrpt = swtxrtgrpt;
        this.codetaxgrp = codetaxgrp;
        this.taxversion = taxversion;
        this.swtxctlrc = swtxctlrc;
        this.taxclass1 = taxclass1;
        this.taxclass2 = taxclass2;
        this.taxclass3 = taxclass3;
        this.taxclass4 = taxclass4;
        this.taxclass5 = taxclass5;
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
        this.idshipnbr = idshipnbr;
        this.datefrstbk = datefrstbk;
        this.datelstrvl = datelstrvl;
        this.orate = orate;
        this.oratetype = oratetype;
        this.oratedate = oratedate;
        this.orateop = orateop;
        this.oswrate = oswrate;
        this.idacctset = idacctset;
        this.datepaid = datepaid;
        this.swnonrcvbl = swnonrcvbl;
        this.codeterr = codeterr;
    }

    public Arobl(String idcust, String idinvc) {
        this.aroblPK = new AroblPK(idcust, idinvc);
    }

    public AroblPK getAroblPK() {
        return aroblPK;
    }

    public void setAroblPK(AroblPK aroblPK) {
        this.aroblPK = aroblPK;
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

    public String getIdordernbr() {
        return idordernbr;
    }

    public void setIdordernbr(String idordernbr) {
        this.idordernbr = idordernbr;
    }

    public String getIdcustpo() {
        return idcustpo;
    }

    public void setIdcustpo(String idcustpo) {
        this.idcustpo = idcustpo;
    }

    public int getDatedue() {
        return datedue;
    }

    public void setDatedue(int datedue) {
        this.datedue = datedue;
    }

    public String getIdnatacct() {
        return idnatacct;
    }

    public void setIdnatacct(String idnatacct) {
        this.idnatacct = idnatacct;
    }

    public String getIdcustshpt() {
        return idcustshpt;
    }

    public void setIdcustshpt(String idcustshpt) {
        this.idcustshpt = idcustshpt;
    }

    public short getTrxtypeid() {
        return trxtypeid;
    }

    public void setTrxtypeid(short trxtypeid) {
        this.trxtypeid = trxtypeid;
    }

    public short getTrxtypetxt() {
        return trxtypetxt;
    }

    public void setTrxtypetxt(short trxtypetxt) {
        this.trxtypetxt = trxtypetxt;
    }

    public int getDatebtch() {
        return datebtch;
    }

    public void setDatebtch(int datebtch) {
        this.datebtch = datebtch;
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

    public String getIdgrp() {
        return idgrp;
    }

    public void setIdgrp(String idgrp) {
        this.idgrp = idgrp;
    }

    public String getDescinvc() {
        return descinvc;
    }

    public void setDescinvc(String descinvc) {
        this.descinvc = descinvc;
    }

    public int getDateinvc() {
        return dateinvc;
    }

    public void setDateinvc(int dateinvc) {
        this.dateinvc = dateinvc;
    }

    public int getDateasof() {
        return dateasof;
    }

    public void setDateasof(int dateasof) {
        this.dateasof = dateasof;
    }

    public String getCodeterm() {
        return codeterm;
    }

    public void setCodeterm(String codeterm) {
        this.codeterm = codeterm;
    }

    public int getDatedisc() {
        return datedisc;
    }

    public void setDatedisc(int datedisc) {
        this.datedisc = datedisc;
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

    public short getSwrateovrd() {
        return swrateovrd;
    }

    public void setSwrateovrd(short swrateovrd) {
        this.swrateovrd = swrateovrd;
    }

    public BigDecimal getExchratehc() {
        return exchratehc;
    }

    public void setExchratehc(BigDecimal exchratehc) {
        this.exchratehc = exchratehc;
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

    public BigDecimal getAmttxblhc() {
        return amttxblhc;
    }

    public void setAmttxblhc(BigDecimal amttxblhc) {
        this.amttxblhc = amttxblhc;
    }

    public BigDecimal getAmtnontxhc() {
        return amtnontxhc;
    }

    public void setAmtnontxhc(BigDecimal amtnontxhc) {
        this.amtnontxhc = amtnontxhc;
    }

    public BigDecimal getAmttaxhc() {
        return amttaxhc;
    }

    public void setAmttaxhc(BigDecimal amttaxhc) {
        this.amttaxhc = amttaxhc;
    }

    public BigDecimal getAmtdischc() {
        return amtdischc;
    }

    public void setAmtdischc(BigDecimal amtdischc) {
        this.amtdischc = amtdischc;
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

    public BigDecimal getAmttxbltc() {
        return amttxbltc;
    }

    public void setAmttxbltc(BigDecimal amttxbltc) {
        this.amttxbltc = amttxbltc;
    }

    public BigDecimal getAmtnontxtc() {
        return amtnontxtc;
    }

    public void setAmtnontxtc(BigDecimal amtnontxtc) {
        this.amtnontxtc = amtnontxtc;
    }

    public BigDecimal getAmttaxtc() {
        return amttaxtc;
    }

    public void setAmttaxtc(BigDecimal amttaxtc) {
        this.amttaxtc = amttaxtc;
    }

    public BigDecimal getAmtdisctc() {
        return amtdisctc;
    }

    public void setAmtdisctc(BigDecimal amtdisctc) {
        this.amtdisctc = amtdisctc;
    }

    public short getSwpaid() {
        return swpaid;
    }

    public void setSwpaid(short swpaid) {
        this.swpaid = swpaid;
    }

    public int getDatelstact() {
        return datelstact;
    }

    public void setDatelstact(int datelstact) {
        this.datelstact = datelstact;
    }

    public int getDatelststm() {
        return datelststm;
    }

    public void setDatelststm(int datelststm) {
        this.datelststm = datelststm;
    }

    public int getDatelstdlq() {
        return datelstdlq;
    }

    public void setDatelstdlq(int datelstdlq) {
        this.datelstdlq = datelstdlq;
    }

    public short getCodedlqsts() {
        return codedlqsts;
    }

    public void setCodedlqsts(short codedlqsts) {
        this.codedlqsts = codedlqsts;
    }

    public int getCnttotpaym() {
        return cnttotpaym;
    }

    public void setCnttotpaym(int cnttotpaym) {
        this.cnttotpaym = cnttotpaym;
    }

    public int getCntlstpaid() {
        return cntlstpaid;
    }

    public void setCntlstpaid(int cntlstpaid) {
        this.cntlstpaid = cntlstpaid;
    }

    public int getCntlstpyst() {
        return cntlstpyst;
    }

    public void setCntlstpyst(int cntlstpyst) {
        this.cntlstpyst = cntlstpyst;
    }

    public BigDecimal getAmtremit() {
        return amtremit;
    }

    public void setAmtremit(BigDecimal amtremit) {
        this.amtremit = amtremit;
    }

    public int getCntlastseq() {
        return cntlastseq;
    }

    public void setCntlastseq(int cntlastseq) {
        this.cntlastseq = cntlastseq;
    }

    public short getSwtaxinput() {
        return swtaxinput;
    }

    public void setSwtaxinput(short swtaxinput) {
        this.swtaxinput = swtaxinput;
    }

    public String getCodetax1() {
        return codetax1;
    }

    public void setCodetax1(String codetax1) {
        this.codetax1 = codetax1;
    }

    public String getCodetax2() {
        return codetax2;
    }

    public void setCodetax2(String codetax2) {
        this.codetax2 = codetax2;
    }

    public String getCodetax3() {
        return codetax3;
    }

    public void setCodetax3(String codetax3) {
        this.codetax3 = codetax3;
    }

    public String getCodetax4() {
        return codetax4;
    }

    public void setCodetax4(String codetax4) {
        this.codetax4 = codetax4;
    }

    public String getCodetax5() {
        return codetax5;
    }

    public void setCodetax5(String codetax5) {
        this.codetax5 = codetax5;
    }

    public BigDecimal getAmtbase1hc() {
        return amtbase1hc;
    }

    public void setAmtbase1hc(BigDecimal amtbase1hc) {
        this.amtbase1hc = amtbase1hc;
    }

    public BigDecimal getAmtbase2hc() {
        return amtbase2hc;
    }

    public void setAmtbase2hc(BigDecimal amtbase2hc) {
        this.amtbase2hc = amtbase2hc;
    }

    public BigDecimal getAmtbase3hc() {
        return amtbase3hc;
    }

    public void setAmtbase3hc(BigDecimal amtbase3hc) {
        this.amtbase3hc = amtbase3hc;
    }

    public BigDecimal getAmtbase4hc() {
        return amtbase4hc;
    }

    public void setAmtbase4hc(BigDecimal amtbase4hc) {
        this.amtbase4hc = amtbase4hc;
    }

    public BigDecimal getAmtbase5hc() {
        return amtbase5hc;
    }

    public void setAmtbase5hc(BigDecimal amtbase5hc) {
        this.amtbase5hc = amtbase5hc;
    }

    public BigDecimal getAmttax1hc() {
        return amttax1hc;
    }

    public void setAmttax1hc(BigDecimal amttax1hc) {
        this.amttax1hc = amttax1hc;
    }

    public BigDecimal getAmttax2hc() {
        return amttax2hc;
    }

    public void setAmttax2hc(BigDecimal amttax2hc) {
        this.amttax2hc = amttax2hc;
    }

    public BigDecimal getAmttax3hc() {
        return amttax3hc;
    }

    public void setAmttax3hc(BigDecimal amttax3hc) {
        this.amttax3hc = amttax3hc;
    }

    public BigDecimal getAmttax4hc() {
        return amttax4hc;
    }

    public void setAmttax4hc(BigDecimal amttax4hc) {
        this.amttax4hc = amttax4hc;
    }

    public BigDecimal getAmttax5hc() {
        return amttax5hc;
    }

    public void setAmttax5hc(BigDecimal amttax5hc) {
        this.amttax5hc = amttax5hc;
    }

    public BigDecimal getAmtbase1tc() {
        return amtbase1tc;
    }

    public void setAmtbase1tc(BigDecimal amtbase1tc) {
        this.amtbase1tc = amtbase1tc;
    }

    public BigDecimal getAmtbase2tc() {
        return amtbase2tc;
    }

    public void setAmtbase2tc(BigDecimal amtbase2tc) {
        this.amtbase2tc = amtbase2tc;
    }

    public BigDecimal getAmtbase3tc() {
        return amtbase3tc;
    }

    public void setAmtbase3tc(BigDecimal amtbase3tc) {
        this.amtbase3tc = amtbase3tc;
    }

    public BigDecimal getAmtbase4tc() {
        return amtbase4tc;
    }

    public void setAmtbase4tc(BigDecimal amtbase4tc) {
        this.amtbase4tc = amtbase4tc;
    }

    public BigDecimal getAmtbase5tc() {
        return amtbase5tc;
    }

    public void setAmtbase5tc(BigDecimal amtbase5tc) {
        this.amtbase5tc = amtbase5tc;
    }

    public BigDecimal getAmttax1tc() {
        return amttax1tc;
    }

    public void setAmttax1tc(BigDecimal amttax1tc) {
        this.amttax1tc = amttax1tc;
    }

    public BigDecimal getAmttax2tc() {
        return amttax2tc;
    }

    public void setAmttax2tc(BigDecimal amttax2tc) {
        this.amttax2tc = amttax2tc;
    }

    public BigDecimal getAmttax3tc() {
        return amttax3tc;
    }

    public void setAmttax3tc(BigDecimal amttax3tc) {
        this.amttax3tc = amttax3tc;
    }

    public BigDecimal getAmttax4tc() {
        return amttax4tc;
    }

    public void setAmttax4tc(BigDecimal amttax4tc) {
        this.amttax4tc = amttax4tc;
    }

    public BigDecimal getAmttax5tc() {
        return amttax5tc;
    }

    public void setAmttax5tc(BigDecimal amttax5tc) {
        this.amttax5tc = amttax5tc;
    }

    public String getCodeslsp1() {
        return codeslsp1;
    }

    public void setCodeslsp1(String codeslsp1) {
        this.codeslsp1 = codeslsp1;
    }

    public String getCodeslsp2() {
        return codeslsp2;
    }

    public void setCodeslsp2(String codeslsp2) {
        this.codeslsp2 = codeslsp2;
    }

    public String getCodeslsp3() {
        return codeslsp3;
    }

    public void setCodeslsp3(String codeslsp3) {
        this.codeslsp3 = codeslsp3;
    }

    public String getCodeslsp4() {
        return codeslsp4;
    }

    public void setCodeslsp4(String codeslsp4) {
        this.codeslsp4 = codeslsp4;
    }

    public String getCodeslsp5() {
        return codeslsp5;
    }

    public void setCodeslsp5(String codeslsp5) {
        this.codeslsp5 = codeslsp5;
    }

    public BigDecimal getPctsasplt1() {
        return pctsasplt1;
    }

    public void setPctsasplt1(BigDecimal pctsasplt1) {
        this.pctsasplt1 = pctsasplt1;
    }

    public BigDecimal getPctsasplt2() {
        return pctsasplt2;
    }

    public void setPctsasplt2(BigDecimal pctsasplt2) {
        this.pctsasplt2 = pctsasplt2;
    }

    public BigDecimal getPctsasplt3() {
        return pctsasplt3;
    }

    public void setPctsasplt3(BigDecimal pctsasplt3) {
        this.pctsasplt3 = pctsasplt3;
    }

    public BigDecimal getPctsasplt4() {
        return pctsasplt4;
    }

    public void setPctsasplt4(BigDecimal pctsasplt4) {
        this.pctsasplt4 = pctsasplt4;
    }

    public BigDecimal getPctsasplt5() {
        return pctsasplt5;
    }

    public void setPctsasplt5(BigDecimal pctsasplt5) {
        this.pctsasplt5 = pctsasplt5;
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

    public String getIdprepaid() {
        return idprepaid;
    }

    public void setIdprepaid(String idprepaid) {
        this.idprepaid = idprepaid;
    }

    public int getDatebus() {
        return datebus;
    }

    public void setDatebus(int datebus) {
        this.datebus = datebus;
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

    public String getYplastact() {
        return yplastact;
    }

    public void setYplastact(String yplastact) {
        this.yplastact = yplastact;
    }

    public String getIdbank() {
        return idbank;
    }

    public void setIdbank(String idbank) {
        this.idbank = idbank;
    }

    public int getDepstnbr() {
        return depstnbr;
    }

    public void setDepstnbr(int depstnbr) {
        this.depstnbr = depstnbr;
    }

    public int getPostseqnce() {
        return postseqnce;
    }

    public void setPostseqnce(int postseqnce) {
        this.postseqnce = postseqnce;
    }

    public short getSwjob() {
        return swjob;
    }

    public void setSwjob(short swjob) {
        this.swjob = swjob;
    }

    public short getSwrtg() {
        return swrtg;
    }

    public void setSwrtg(short swrtg) {
        this.swrtg = swrtg;
    }

    public short getSwrtgout() {
        return swrtgout;
    }

    public void setSwrtgout(short swrtgout) {
        this.swrtgout = swrtgout;
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

    public String getRtgterms() {
        return rtgterms;
    }

    public void setRtgterms(String rtgterms) {
        this.rtgterms = rtgterms;
    }

    public short getSwrtgrate() {
        return swrtgrate;
    }

    public void setSwrtgrate(short swrtgrate) {
        this.swrtgrate = swrtgrate;
    }

    public String getRtgapplyto() {
        return rtgapplyto;
    }

    public void setRtgapplyto(String rtgapplyto) {
        this.rtgapplyto = rtgapplyto;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    public String getSrceappl() {
        return srceappl;
    }

    public void setSrceappl(String srceappl) {
        this.srceappl = srceappl;
    }

    public String getArversion() {
        return arversion;
    }

    public void setArversion(String arversion) {
        this.arversion = arversion;
    }

    public short getInvctype() {
        return invctype;
    }

    public void setInvctype(short invctype) {
        this.invctype = invctype;
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

    public String getTypebtch() {
        return typebtch;
    }

    public void setTypebtch(String typebtch) {
        this.typebtch = typebtch;
    }

    public int getCntoblj() {
        return cntoblj;
    }

    public void setCntoblj(int cntoblj) {
        this.cntoblj = cntoblj;
    }

    public String getCodecurnrc() {
        return codecurnrc;
    }

    public void setCodecurnrc(String codecurnrc) {
        this.codecurnrc = codecurnrc;
    }

    public BigDecimal getRaterc() {
        return raterc;
    }

    public void setRaterc(BigDecimal raterc) {
        this.raterc = raterc;
    }

    public String getRatetyperc() {
        return ratetyperc;
    }

    public void setRatetyperc(String ratetyperc) {
        this.ratetyperc = ratetyperc;
    }

    public int getRatedaterc() {
        return ratedaterc;
    }

    public void setRatedaterc(int ratedaterc) {
        this.ratedaterc = ratedaterc;
    }

    public short getRateoprc() {
        return rateoprc;
    }

    public void setRateoprc(short rateoprc) {
        this.rateoprc = rateoprc;
    }

    public short getSwraterc() {
        return swraterc;
    }

    public void setSwraterc(short swraterc) {
        this.swraterc = swraterc;
    }

    public short getSwtxrtgrpt() {
        return swtxrtgrpt;
    }

    public void setSwtxrtgrpt(short swtxrtgrpt) {
        this.swtxrtgrpt = swtxrtgrpt;
    }

    public String getCodetaxgrp() {
        return codetaxgrp;
    }

    public void setCodetaxgrp(String codetaxgrp) {
        this.codetaxgrp = codetaxgrp;
    }

    public int getTaxversion() {
        return taxversion;
    }

    public void setTaxversion(int taxversion) {
        this.taxversion = taxversion;
    }

    public short getSwtxctlrc() {
        return swtxctlrc;
    }

    public void setSwtxctlrc(short swtxctlrc) {
        this.swtxctlrc = swtxctlrc;
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

    public String getIdshipnbr() {
        return idshipnbr;
    }

    public void setIdshipnbr(String idshipnbr) {
        this.idshipnbr = idshipnbr;
    }

    public int getDatefrstbk() {
        return datefrstbk;
    }

    public void setDatefrstbk(int datefrstbk) {
        this.datefrstbk = datefrstbk;
    }

    public int getDatelstrvl() {
        return datelstrvl;
    }

    public void setDatelstrvl(int datelstrvl) {
        this.datelstrvl = datelstrvl;
    }

    public BigDecimal getOrate() {
        return orate;
    }

    public void setOrate(BigDecimal orate) {
        this.orate = orate;
    }

    public String getOratetype() {
        return oratetype;
    }

    public void setOratetype(String oratetype) {
        this.oratetype = oratetype;
    }

    public int getOratedate() {
        return oratedate;
    }

    public void setOratedate(int oratedate) {
        this.oratedate = oratedate;
    }

    public short getOrateop() {
        return orateop;
    }

    public void setOrateop(short orateop) {
        this.orateop = orateop;
    }

    public short getOswrate() {
        return oswrate;
    }

    public void setOswrate(short oswrate) {
        this.oswrate = oswrate;
    }

    public String getIdacctset() {
        return idacctset;
    }

    public void setIdacctset(String idacctset) {
        this.idacctset = idacctset;
    }

    public int getDatepaid() {
        return datepaid;
    }

    public void setDatepaid(int datepaid) {
        this.datepaid = datepaid;
    }

    public short getSwnonrcvbl() {
        return swnonrcvbl;
    }

    public void setSwnonrcvbl(short swnonrcvbl) {
        this.swnonrcvbl = swnonrcvbl;
    }

    public String getCodeterr() {
        return codeterr;
    }

    public void setCodeterr(String codeterr) {
        this.codeterr = codeterr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aroblPK != null ? aroblPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arobl)) {
            return false;
        }
        Arobl other = (Arobl) object;
        if ((this.aroblPK == null && other.aroblPK != null) || (this.aroblPK != null && !this.aroblPK.equals(other.aroblPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Arobl[aroblPK=" + aroblPK + "]";
    }

}
