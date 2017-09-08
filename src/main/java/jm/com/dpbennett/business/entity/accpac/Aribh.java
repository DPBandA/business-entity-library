/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jm.com.dpbennett.business.entity.accpac;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "ARIBH")
@NamedQueries({
    @NamedQuery(name = "Aribh.findAll", query = "SELECT a FROM Aribh a"),
    @NamedQuery(name = "Aribh.findByCntbtch", query = "SELECT a FROM Aribh a WHERE a.aribhPK.cntbtch = :cntbtch"),
    @NamedQuery(name = "Aribh.findByCntitem", query = "SELECT a FROM Aribh a WHERE a.aribhPK.cntitem = :cntitem"),
    @NamedQuery(name = "Aribh.findByAudtdate", query = "SELECT a FROM Aribh a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Aribh.findByAudttime", query = "SELECT a FROM Aribh a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Aribh.findByAudtuser", query = "SELECT a FROM Aribh a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Aribh.findByAudtorg", query = "SELECT a FROM Aribh a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Aribh.findByIdcust", query = "SELECT a FROM Aribh a WHERE a.idcust = :idcust"),
    @NamedQuery(name = "Aribh.findByIdinvc", query = "SELECT a FROM Aribh a WHERE a.idinvc = :idinvc"),
    @NamedQuery(name = "Aribh.findByIdshpt", query = "SELECT a FROM Aribh a WHERE a.idshpt = :idshpt"),
    @NamedQuery(name = "Aribh.findByShipvia", query = "SELECT a FROM Aribh a WHERE a.shipvia = :shipvia"),
    @NamedQuery(name = "Aribh.findBySpecinst", query = "SELECT a FROM Aribh a WHERE a.specinst = :specinst"),
    @NamedQuery(name = "Aribh.findByTexttrx", query = "SELECT a FROM Aribh a WHERE a.texttrx = :texttrx"),
    @NamedQuery(name = "Aribh.findByIdtrx", query = "SELECT a FROM Aribh a WHERE a.idtrx = :idtrx"),
    @NamedQuery(name = "Aribh.findByInvcstts", query = "SELECT a FROM Aribh a WHERE a.invcstts = :invcstts"),
    @NamedQuery(name = "Aribh.findByOrdrnbr", query = "SELECT a FROM Aribh a WHERE a.ordrnbr = :ordrnbr"),
    @NamedQuery(name = "Aribh.findByCustpo", query = "SELECT a FROM Aribh a WHERE a.custpo = :custpo"),
    @NamedQuery(name = "Aribh.findByJobnbr", query = "SELECT a FROM Aribh a WHERE a.jobnbr = :jobnbr"),
    @NamedQuery(name = "Aribh.findByInvcdesc", query = "SELECT a FROM Aribh a WHERE a.invcdesc = :invcdesc"),
    @NamedQuery(name = "Aribh.findBySwprtinvc", query = "SELECT a FROM Aribh a WHERE a.swprtinvc = :swprtinvc"),
    @NamedQuery(name = "Aribh.findByInvcapplto", query = "SELECT a FROM Aribh a WHERE a.invcapplto = :invcapplto"),
    @NamedQuery(name = "Aribh.findByIdacctset", query = "SELECT a FROM Aribh a WHERE a.idacctset = :idacctset"),
    @NamedQuery(name = "Aribh.findByDateinvc", query = "SELECT a FROM Aribh a WHERE a.dateinvc = :dateinvc"),
    @NamedQuery(name = "Aribh.findByDateasof", query = "SELECT a FROM Aribh a WHERE a.dateasof = :dateasof"),
    @NamedQuery(name = "Aribh.findByFiscyr", query = "SELECT a FROM Aribh a WHERE a.fiscyr = :fiscyr"),
    @NamedQuery(name = "Aribh.findByFiscper", query = "SELECT a FROM Aribh a WHERE a.fiscper = :fiscper"),
    @NamedQuery(name = "Aribh.findByCodecurn", query = "SELECT a FROM Aribh a WHERE a.codecurn = :codecurn"),
    @NamedQuery(name = "Aribh.findByRatetype", query = "SELECT a FROM Aribh a WHERE a.ratetype = :ratetype"),
    @NamedQuery(name = "Aribh.findBySwmanrte", query = "SELECT a FROM Aribh a WHERE a.swmanrte = :swmanrte"),
    @NamedQuery(name = "Aribh.findByExchratehc", query = "SELECT a FROM Aribh a WHERE a.exchratehc = :exchratehc"),
    @NamedQuery(name = "Aribh.findByOrigratehc", query = "SELECT a FROM Aribh a WHERE a.origratehc = :origratehc"),
    @NamedQuery(name = "Aribh.findByTermcode", query = "SELECT a FROM Aribh a WHERE a.termcode = :termcode"),
    @NamedQuery(name = "Aribh.findBySwtermovrd", query = "SELECT a FROM Aribh a WHERE a.swtermovrd = :swtermovrd"),
    @NamedQuery(name = "Aribh.findByDatedue", query = "SELECT a FROM Aribh a WHERE a.datedue = :datedue"),
    @NamedQuery(name = "Aribh.findByDatedisc", query = "SELECT a FROM Aribh a WHERE a.datedisc = :datedisc"),
    @NamedQuery(name = "Aribh.findByPctdisc", query = "SELECT a FROM Aribh a WHERE a.pctdisc = :pctdisc"),
    @NamedQuery(name = "Aribh.findByAmtdiscavl", query = "SELECT a FROM Aribh a WHERE a.amtdiscavl = :amtdiscavl"),
    @NamedQuery(name = "Aribh.findByLastline", query = "SELECT a FROM Aribh a WHERE a.lastline = :lastline"),
    @NamedQuery(name = "Aribh.findByCodeslsp1", query = "SELECT a FROM Aribh a WHERE a.codeslsp1 = :codeslsp1"),
    @NamedQuery(name = "Aribh.findByCodeslsp2", query = "SELECT a FROM Aribh a WHERE a.codeslsp2 = :codeslsp2"),
    @NamedQuery(name = "Aribh.findByCodeslsp3", query = "SELECT a FROM Aribh a WHERE a.codeslsp3 = :codeslsp3"),
    @NamedQuery(name = "Aribh.findByCodeslsp4", query = "SELECT a FROM Aribh a WHERE a.codeslsp4 = :codeslsp4"),
    @NamedQuery(name = "Aribh.findByCodeslsp5", query = "SELECT a FROM Aribh a WHERE a.codeslsp5 = :codeslsp5"),
    @NamedQuery(name = "Aribh.findByPctsasplt1", query = "SELECT a FROM Aribh a WHERE a.pctsasplt1 = :pctsasplt1"),
    @NamedQuery(name = "Aribh.findByPctsasplt2", query = "SELECT a FROM Aribh a WHERE a.pctsasplt2 = :pctsasplt2"),
    @NamedQuery(name = "Aribh.findByPctsasplt3", query = "SELECT a FROM Aribh a WHERE a.pctsasplt3 = :pctsasplt3"),
    @NamedQuery(name = "Aribh.findByPctsasplt4", query = "SELECT a FROM Aribh a WHERE a.pctsasplt4 = :pctsasplt4"),
    @NamedQuery(name = "Aribh.findByPctsasplt5", query = "SELECT a FROM Aribh a WHERE a.pctsasplt5 = :pctsasplt5"),
    @NamedQuery(name = "Aribh.findBySwtaxbl", query = "SELECT a FROM Aribh a WHERE a.swtaxbl = :swtaxbl"),
    @NamedQuery(name = "Aribh.findBySwmantx", query = "SELECT a FROM Aribh a WHERE a.swmantx = :swmantx"),
    @NamedQuery(name = "Aribh.findByCodetaxgrp", query = "SELECT a FROM Aribh a WHERE a.codetaxgrp = :codetaxgrp"),
    @NamedQuery(name = "Aribh.findByCodetax1", query = "SELECT a FROM Aribh a WHERE a.codetax1 = :codetax1"),
    @NamedQuery(name = "Aribh.findByCodetax2", query = "SELECT a FROM Aribh a WHERE a.codetax2 = :codetax2"),
    @NamedQuery(name = "Aribh.findByCodetax3", query = "SELECT a FROM Aribh a WHERE a.codetax3 = :codetax3"),
    @NamedQuery(name = "Aribh.findByCodetax4", query = "SELECT a FROM Aribh a WHERE a.codetax4 = :codetax4"),
    @NamedQuery(name = "Aribh.findByCodetax5", query = "SELECT a FROM Aribh a WHERE a.codetax5 = :codetax5"),
    @NamedQuery(name = "Aribh.findByTaxstts1", query = "SELECT a FROM Aribh a WHERE a.taxstts1 = :taxstts1"),
    @NamedQuery(name = "Aribh.findByTaxstts2", query = "SELECT a FROM Aribh a WHERE a.taxstts2 = :taxstts2"),
    @NamedQuery(name = "Aribh.findByTaxstts3", query = "SELECT a FROM Aribh a WHERE a.taxstts3 = :taxstts3"),
    @NamedQuery(name = "Aribh.findByTaxstts4", query = "SELECT a FROM Aribh a WHERE a.taxstts4 = :taxstts4"),
    @NamedQuery(name = "Aribh.findByTaxstts5", query = "SELECT a FROM Aribh a WHERE a.taxstts5 = :taxstts5"),
    @NamedQuery(name = "Aribh.findByBasetax1", query = "SELECT a FROM Aribh a WHERE a.basetax1 = :basetax1"),
    @NamedQuery(name = "Aribh.findByBasetax2", query = "SELECT a FROM Aribh a WHERE a.basetax2 = :basetax2"),
    @NamedQuery(name = "Aribh.findByBasetax3", query = "SELECT a FROM Aribh a WHERE a.basetax3 = :basetax3"),
    @NamedQuery(name = "Aribh.findByBasetax4", query = "SELECT a FROM Aribh a WHERE a.basetax4 = :basetax4"),
    @NamedQuery(name = "Aribh.findByBasetax5", query = "SELECT a FROM Aribh a WHERE a.basetax5 = :basetax5"),
    @NamedQuery(name = "Aribh.findByAmttax1", query = "SELECT a FROM Aribh a WHERE a.amttax1 = :amttax1"),
    @NamedQuery(name = "Aribh.findByAmttax2", query = "SELECT a FROM Aribh a WHERE a.amttax2 = :amttax2"),
    @NamedQuery(name = "Aribh.findByAmttax3", query = "SELECT a FROM Aribh a WHERE a.amttax3 = :amttax3"),
    @NamedQuery(name = "Aribh.findByAmttax4", query = "SELECT a FROM Aribh a WHERE a.amttax4 = :amttax4"),
    @NamedQuery(name = "Aribh.findByAmttax5", query = "SELECT a FROM Aribh a WHERE a.amttax5 = :amttax5"),
    @NamedQuery(name = "Aribh.findByAmttxbl", query = "SELECT a FROM Aribh a WHERE a.amttxbl = :amttxbl"),
    @NamedQuery(name = "Aribh.findByAmtnottxbl", query = "SELECT a FROM Aribh a WHERE a.amtnottxbl = :amtnottxbl"),
    @NamedQuery(name = "Aribh.findByAmttaxtot", query = "SELECT a FROM Aribh a WHERE a.amttaxtot = :amttaxtot"),
    @NamedQuery(name = "Aribh.findByAmtinvctot", query = "SELECT a FROM Aribh a WHERE a.amtinvctot = :amtinvctot"),
    @NamedQuery(name = "Aribh.findByAmtppd", query = "SELECT a FROM Aribh a WHERE a.amtppd = :amtppd"),
    @NamedQuery(name = "Aribh.findByAmtpaymtot", query = "SELECT a FROM Aribh a WHERE a.amtpaymtot = :amtpaymtot"),
    @NamedQuery(name = "Aribh.findByAmtpymschd", query = "SELECT a FROM Aribh a WHERE a.amtpymschd = :amtpymschd"),
    @NamedQuery(name = "Aribh.findByAmtnettot", query = "SELECT a FROM Aribh a WHERE a.amtnettot = :amtnettot"),
    @NamedQuery(name = "Aribh.findByIdstdinvc", query = "SELECT a FROM Aribh a WHERE a.idstdinvc = :idstdinvc"),
    @NamedQuery(name = "Aribh.findByDateprcs", query = "SELECT a FROM Aribh a WHERE a.dateprcs = :dateprcs"),
    @NamedQuery(name = "Aribh.findByIdppd", query = "SELECT a FROM Aribh a WHERE a.idppd = :idppd"),
    @NamedQuery(name = "Aribh.findByIdbill", query = "SELECT a FROM Aribh a WHERE a.idbill = :idbill"),
    @NamedQuery(name = "Aribh.findByShptoloc", query = "SELECT a FROM Aribh a WHERE a.shptoloc = :shptoloc"),
    @NamedQuery(name = "Aribh.findByShptoste1", query = "SELECT a FROM Aribh a WHERE a.shptoste1 = :shptoste1"),
    @NamedQuery(name = "Aribh.findByShptoste2", query = "SELECT a FROM Aribh a WHERE a.shptoste2 = :shptoste2"),
    @NamedQuery(name = "Aribh.findByShptoste3", query = "SELECT a FROM Aribh a WHERE a.shptoste3 = :shptoste3"),
    @NamedQuery(name = "Aribh.findByShptoste4", query = "SELECT a FROM Aribh a WHERE a.shptoste4 = :shptoste4"),
    @NamedQuery(name = "Aribh.findByShptocity", query = "SELECT a FROM Aribh a WHERE a.shptocity = :shptocity"),
    @NamedQuery(name = "Aribh.findByShptostte", query = "SELECT a FROM Aribh a WHERE a.shptostte = :shptostte"),
    @NamedQuery(name = "Aribh.findByShptopost", query = "SELECT a FROM Aribh a WHERE a.shptopost = :shptopost"),
    @NamedQuery(name = "Aribh.findByShptoctry", query = "SELECT a FROM Aribh a WHERE a.shptoctry = :shptoctry"),
    @NamedQuery(name = "Aribh.findByShptoctac", query = "SELECT a FROM Aribh a WHERE a.shptoctac = :shptoctac"),
    @NamedQuery(name = "Aribh.findByShptophon", query = "SELECT a FROM Aribh a WHERE a.shptophon = :shptophon"),
    @NamedQuery(name = "Aribh.findByShptofax", query = "SELECT a FROM Aribh a WHERE a.shptofax = :shptofax"),
    @NamedQuery(name = "Aribh.findByDaterate", query = "SELECT a FROM Aribh a WHERE a.daterate = :daterate"),
    @NamedQuery(name = "Aribh.findBySwprocppd", query = "SELECT a FROM Aribh a WHERE a.swprocppd = :swprocppd"),
    @NamedQuery(name = "Aribh.findByCuroper", query = "SELECT a FROM Aribh a WHERE a.curoper = :curoper"),
    @NamedQuery(name = "Aribh.findByDrillapp", query = "SELECT a FROM Aribh a WHERE a.drillapp = :drillapp"),
    @NamedQuery(name = "Aribh.findByDrilltype", query = "SELECT a FROM Aribh a WHERE a.drilltype = :drilltype"),
    @NamedQuery(name = "Aribh.findByDrilldwnlk", query = "SELECT a FROM Aribh a WHERE a.drilldwnlk = :drilldwnlk"),
    @NamedQuery(name = "Aribh.findByShpviacode", query = "SELECT a FROM Aribh a WHERE a.shpviacode = :shpviacode"),
    @NamedQuery(name = "Aribh.findByShpviadesc", query = "SELECT a FROM Aribh a WHERE a.shpviadesc = :shpviadesc"),
    @NamedQuery(name = "Aribh.findBySwjob", query = "SELECT a FROM Aribh a WHERE a.swjob = :swjob"),
    @NamedQuery(name = "Aribh.findByErrbatch", query = "SELECT a FROM Aribh a WHERE a.errbatch = :errbatch"),
    @NamedQuery(name = "Aribh.findByErrentry", query = "SELECT a FROM Aribh a WHERE a.errentry = :errentry"),
    @NamedQuery(name = "Aribh.findByEmail", query = "SELECT a FROM Aribh a WHERE a.email = :email"),
    @NamedQuery(name = "Aribh.findByCtacphone", query = "SELECT a FROM Aribh a WHERE a.ctacphone = :ctacphone"),
    @NamedQuery(name = "Aribh.findByCtacfax", query = "SELECT a FROM Aribh a WHERE a.ctacfax = :ctacfax"),
    @NamedQuery(name = "Aribh.findByCtacemail", query = "SELECT a FROM Aribh a WHERE a.ctacemail = :ctacemail"),
    @NamedQuery(name = "Aribh.findByAmtdsbwtax", query = "SELECT a FROM Aribh a WHERE a.amtdsbwtax = :amtdsbwtax"),
    @NamedQuery(name = "Aribh.findByAmtdsbntax", query = "SELECT a FROM Aribh a WHERE a.amtdsbntax = :amtdsbntax"),
    @NamedQuery(name = "Aribh.findByAmtdscbase", query = "SELECT a FROM Aribh a WHERE a.amtdscbase = :amtdscbase"),
    @NamedQuery(name = "Aribh.findByInvctype", query = "SELECT a FROM Aribh a WHERE a.invctype = :invctype"),
    @NamedQuery(name = "Aribh.findBySwrtginvc", query = "SELECT a FROM Aribh a WHERE a.swrtginvc = :swrtginvc"),
    @NamedQuery(name = "Aribh.findByRtgapplyto", query = "SELECT a FROM Aribh a WHERE a.rtgapplyto = :rtgapplyto"),
    @NamedQuery(name = "Aribh.findBySwrtg", query = "SELECT a FROM Aribh a WHERE a.swrtg = :swrtg"),
    @NamedQuery(name = "Aribh.findByRtgamt", query = "SELECT a FROM Aribh a WHERE a.rtgamt = :rtgamt"),
    @NamedQuery(name = "Aribh.findByRtgpercent", query = "SELECT a FROM Aribh a WHERE a.rtgpercent = :rtgpercent"),
    @NamedQuery(name = "Aribh.findByRtgdays", query = "SELECT a FROM Aribh a WHERE a.rtgdays = :rtgdays"),
    @NamedQuery(name = "Aribh.findByRtgdatedue", query = "SELECT a FROM Aribh a WHERE a.rtgdatedue = :rtgdatedue"),
    @NamedQuery(name = "Aribh.findByRtgterms", query = "SELECT a FROM Aribh a WHERE a.rtgterms = :rtgterms"),
    @NamedQuery(name = "Aribh.findBySwrtgddtov", query = "SELECT a FROM Aribh a WHERE a.swrtgddtov = :swrtgddtov"),
    @NamedQuery(name = "Aribh.findBySwrtgamtov", query = "SELECT a FROM Aribh a WHERE a.swrtgamtov = :swrtgamtov"),
    @NamedQuery(name = "Aribh.findBySwrtgrate", query = "SELECT a FROM Aribh a WHERE a.swrtgrate = :swrtgrate"),
    @NamedQuery(name = "Aribh.findByValues", query = "SELECT a FROM Aribh a WHERE a.values = :values"),
    @NamedQuery(name = "Aribh.findBySrceappl", query = "SELECT a FROM Aribh a WHERE a.srceappl = :srceappl"),
    @NamedQuery(name = "Aribh.findByArversion", query = "SELECT a FROM Aribh a WHERE a.arversion = :arversion"),
    @NamedQuery(name = "Aribh.findByTaxversion", query = "SELECT a FROM Aribh a WHERE a.taxversion = :taxversion"),
    @NamedQuery(name = "Aribh.findBySwtxrtgrpt", query = "SELECT a FROM Aribh a WHERE a.swtxrtgrpt = :swtxrtgrpt"),
    @NamedQuery(name = "Aribh.findByCodecurnrc", query = "SELECT a FROM Aribh a WHERE a.codecurnrc = :codecurnrc"),
    @NamedQuery(name = "Aribh.findBySwtxctlrc", query = "SELECT a FROM Aribh a WHERE a.swtxctlrc = :swtxctlrc"),
    @NamedQuery(name = "Aribh.findByRaterc", query = "SELECT a FROM Aribh a WHERE a.raterc = :raterc"),
    @NamedQuery(name = "Aribh.findByRatetyperc", query = "SELECT a FROM Aribh a WHERE a.ratetyperc = :ratetyperc"),
    @NamedQuery(name = "Aribh.findByRatedaterc", query = "SELECT a FROM Aribh a WHERE a.ratedaterc = :ratedaterc"),
    @NamedQuery(name = "Aribh.findByRateoprc", query = "SELECT a FROM Aribh a WHERE a.rateoprc = :rateoprc"),
    @NamedQuery(name = "Aribh.findBySwraterc", query = "SELECT a FROM Aribh a WHERE a.swraterc = :swraterc"),
    @NamedQuery(name = "Aribh.findByTxamt1rc", query = "SELECT a FROM Aribh a WHERE a.txamt1rc = :txamt1rc"),
    @NamedQuery(name = "Aribh.findByTxamt2rc", query = "SELECT a FROM Aribh a WHERE a.txamt2rc = :txamt2rc"),
    @NamedQuery(name = "Aribh.findByTxamt3rc", query = "SELECT a FROM Aribh a WHERE a.txamt3rc = :txamt3rc"),
    @NamedQuery(name = "Aribh.findByTxamt4rc", query = "SELECT a FROM Aribh a WHERE a.txamt4rc = :txamt4rc"),
    @NamedQuery(name = "Aribh.findByTxamt5rc", query = "SELECT a FROM Aribh a WHERE a.txamt5rc = :txamt5rc"),
    @NamedQuery(name = "Aribh.findByTxtotrc", query = "SELECT a FROM Aribh a WHERE a.txtotrc = :txtotrc"),
    @NamedQuery(name = "Aribh.findByTxbsert1tc", query = "SELECT a FROM Aribh a WHERE a.txbsert1tc = :txbsert1tc"),
    @NamedQuery(name = "Aribh.findByTxbsert2tc", query = "SELECT a FROM Aribh a WHERE a.txbsert2tc = :txbsert2tc"),
    @NamedQuery(name = "Aribh.findByTxbsert3tc", query = "SELECT a FROM Aribh a WHERE a.txbsert3tc = :txbsert3tc"),
    @NamedQuery(name = "Aribh.findByTxbsert4tc", query = "SELECT a FROM Aribh a WHERE a.txbsert4tc = :txbsert4tc"),
    @NamedQuery(name = "Aribh.findByTxbsert5tc", query = "SELECT a FROM Aribh a WHERE a.txbsert5tc = :txbsert5tc"),
    @NamedQuery(name = "Aribh.findByTxamtrt1tc", query = "SELECT a FROM Aribh a WHERE a.txamtrt1tc = :txamtrt1tc"),
    @NamedQuery(name = "Aribh.findByTxamtrt2tc", query = "SELECT a FROM Aribh a WHERE a.txamtrt2tc = :txamtrt2tc"),
    @NamedQuery(name = "Aribh.findByTxamtrt3tc", query = "SELECT a FROM Aribh a WHERE a.txamtrt3tc = :txamtrt3tc"),
    @NamedQuery(name = "Aribh.findByTxamtrt4tc", query = "SELECT a FROM Aribh a WHERE a.txamtrt4tc = :txamtrt4tc"),
    @NamedQuery(name = "Aribh.findByTxamtrt5tc", query = "SELECT a FROM Aribh a WHERE a.txamtrt5tc = :txamtrt5tc"),
    @NamedQuery(name = "Aribh.findByTxbse1hc", query = "SELECT a FROM Aribh a WHERE a.txbse1hc = :txbse1hc"),
    @NamedQuery(name = "Aribh.findByTxbse2hc", query = "SELECT a FROM Aribh a WHERE a.txbse2hc = :txbse2hc"),
    @NamedQuery(name = "Aribh.findByTxbse3hc", query = "SELECT a FROM Aribh a WHERE a.txbse3hc = :txbse3hc"),
    @NamedQuery(name = "Aribh.findByTxbse4hc", query = "SELECT a FROM Aribh a WHERE a.txbse4hc = :txbse4hc"),
    @NamedQuery(name = "Aribh.findByTxbse5hc", query = "SELECT a FROM Aribh a WHERE a.txbse5hc = :txbse5hc"),
    @NamedQuery(name = "Aribh.findByTxamt1hc", query = "SELECT a FROM Aribh a WHERE a.txamt1hc = :txamt1hc"),
    @NamedQuery(name = "Aribh.findByTxamt2hc", query = "SELECT a FROM Aribh a WHERE a.txamt2hc = :txamt2hc"),
    @NamedQuery(name = "Aribh.findByTxamt3hc", query = "SELECT a FROM Aribh a WHERE a.txamt3hc = :txamt3hc"),
    @NamedQuery(name = "Aribh.findByTxamt4hc", query = "SELECT a FROM Aribh a WHERE a.txamt4hc = :txamt4hc"),
    @NamedQuery(name = "Aribh.findByTxamt5hc", query = "SELECT a FROM Aribh a WHERE a.txamt5hc = :txamt5hc"),
    @NamedQuery(name = "Aribh.findByAmtgroshc", query = "SELECT a FROM Aribh a WHERE a.amtgroshc = :amtgroshc"),
    @NamedQuery(name = "Aribh.findByRtgamthc", query = "SELECT a FROM Aribh a WHERE a.rtgamthc = :rtgamthc"),
    @NamedQuery(name = "Aribh.findByAmtdischc", query = "SELECT a FROM Aribh a WHERE a.amtdischc = :amtdischc"),
    @NamedQuery(name = "Aribh.findByDistnethc", query = "SELECT a FROM Aribh a WHERE a.distnethc = :distnethc"),
    @NamedQuery(name = "Aribh.findByAmtppdhc", query = "SELECT a FROM Aribh a WHERE a.amtppdhc = :amtppdhc"),
    @NamedQuery(name = "Aribh.findByAmtduehc", query = "SELECT a FROM Aribh a WHERE a.amtduehc = :amtduehc"),
    @NamedQuery(name = "Aribh.findBySwprtlbl", query = "SELECT a FROM Aribh a WHERE a.swprtlbl = :swprtlbl"),
    @NamedQuery(name = "Aribh.findByIdshipnbr", query = "SELECT a FROM Aribh a WHERE a.idshipnbr = :idshipnbr"),
    @NamedQuery(name = "Aribh.findBySwoecost", query = "SELECT a FROM Aribh a WHERE a.swoecost = :swoecost"),
    @NamedQuery(name = "Aribh.findByEnteredby", query = "SELECT a FROM Aribh a WHERE a.enteredby = :enteredby"),
    @NamedQuery(name = "Aribh.findByDatebus", query = "SELECT a FROM Aribh a WHERE a.datebus = :datebus")})
public class Aribh implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AribhPK aribhPK;
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
    @Column(name = "IDCUST")
    private String idcust;
    @Basic(optional = false)
    @Column(name = "IDINVC")
    private String idinvc;
    @Basic(optional = false)
    @Column(name = "IDSHPT")
    private String idshpt;
    @Basic(optional = false)
    @Column(name = "SHIPVIA")
    private String shipvia;
    @Basic(optional = false)
    @Column(name = "SPECINST")
    private String specinst;
    @Basic(optional = false)
    @Column(name = "TEXTTRX")
    private short texttrx;
    @Basic(optional = false)
    @Column(name = "IDTRX")
    private short idtrx;
    @Basic(optional = false)
    @Column(name = "INVCSTTS")
    private short invcstts;
    @Basic(optional = false)
    @Column(name = "ORDRNBR")
    private String ordrnbr;
    @Basic(optional = false)
    @Column(name = "CUSTPO")
    private String custpo;
    @Basic(optional = false)
    @Column(name = "JOBNBR")
    private String jobnbr;
    @Basic(optional = false)
    @Column(name = "INVCDESC")
    private String invcdesc;
    @Basic(optional = false)
    @Column(name = "SWPRTINVC")
    private short swprtinvc;
    @Basic(optional = false)
    @Column(name = "INVCAPPLTO")
    private String invcapplto;
    @Basic(optional = false)
    @Column(name = "IDACCTSET")
    private String idacctset;
    @Basic(optional = false)
    @Column(name = "DATEINVC")
    private int dateinvc;
    @Basic(optional = false)
    @Column(name = "DATEASOF")
    private int dateasof;
    @Basic(optional = false)
    @Column(name = "FISCYR")
    private String fiscyr;
    @Basic(optional = false)
    @Column(name = "FISCPER")
    private String fiscper;
    @Basic(optional = false)
    @Column(name = "CODECURN")
    private String codecurn;
    @Basic(optional = false)
    @Column(name = "RATETYPE")
    private String ratetype;
    @Basic(optional = false)
    @Column(name = "SWMANRTE")
    private short swmanrte;
    @Basic(optional = false)
    @Column(name = "EXCHRATEHC")
    private BigDecimal exchratehc;
    @Basic(optional = false)
    @Column(name = "ORIGRATEHC")
    private BigDecimal origratehc;
    @Basic(optional = false)
    @Column(name = "TERMCODE")
    private String termcode;
    @Basic(optional = false)
    @Column(name = "SWTERMOVRD")
    private short swtermovrd;
    @Basic(optional = false)
    @Column(name = "DATEDUE")
    private int datedue;
    @Basic(optional = false)
    @Column(name = "DATEDISC")
    private int datedisc;
    @Basic(optional = false)
    @Column(name = "PCTDISC")
    private BigDecimal pctdisc;
    @Basic(optional = false)
    @Column(name = "AMTDISCAVL")
    private BigDecimal amtdiscavl;
    @Basic(optional = false)
    @Column(name = "LASTLINE")
    private int lastline;
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
    @Column(name = "SWTAXBL")
    private short swtaxbl;
    @Basic(optional = false)
    @Column(name = "SWMANTX")
    private short swmantx;
    @Basic(optional = false)
    @Column(name = "CODETAXGRP")
    private String codetaxgrp;
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
    @Column(name = "AMTTXBL")
    private BigDecimal amttxbl;
    @Basic(optional = false)
    @Column(name = "AMTNOTTXBL")
    private BigDecimal amtnottxbl;
    @Basic(optional = false)
    @Column(name = "AMTTAXTOT")
    private BigDecimal amttaxtot;
    @Basic(optional = false)
    @Column(name = "AMTINVCTOT")
    private BigDecimal amtinvctot;
    @Basic(optional = false)
    @Column(name = "AMTPPD")
    private BigDecimal amtppd;
    @Basic(optional = false)
    @Column(name = "AMTPAYMTOT")
    private int amtpaymtot;
    @Basic(optional = false)
    @Column(name = "AMTPYMSCHD")
    private BigDecimal amtpymschd;
    @Basic(optional = false)
    @Column(name = "AMTNETTOT")
    private BigDecimal amtnettot;
    @Basic(optional = false)
    @Column(name = "IDSTDINVC")
    private String idstdinvc;
    @Basic(optional = false)
    @Column(name = "DATEPRCS")
    private int dateprcs;
    @Basic(optional = false)
    @Column(name = "IDPPD")
    private String idppd;
    @Basic(optional = false)
    @Column(name = "IDBILL")
    private String idbill;
    @Basic(optional = false)
    @Column(name = "SHPTOLOC")
    private String shptoloc;
    @Basic(optional = false)
    @Column(name = "SHPTOSTE1")
    private String shptoste1;
    @Basic(optional = false)
    @Column(name = "SHPTOSTE2")
    private String shptoste2;
    @Basic(optional = false)
    @Column(name = "SHPTOSTE3")
    private String shptoste3;
    @Basic(optional = false)
    @Column(name = "SHPTOSTE4")
    private String shptoste4;
    @Basic(optional = false)
    @Column(name = "SHPTOCITY")
    private String shptocity;
    @Basic(optional = false)
    @Column(name = "SHPTOSTTE")
    private String shptostte;
    @Basic(optional = false)
    @Column(name = "SHPTOPOST")
    private String shptopost;
    @Basic(optional = false)
    @Column(name = "SHPTOCTRY")
    private String shptoctry;
    @Basic(optional = false)
    @Column(name = "SHPTOCTAC")
    private String shptoctac;
    @Basic(optional = false)
    @Column(name = "SHPTOPHON")
    private String shptophon;
    @Basic(optional = false)
    @Column(name = "SHPTOFAX")
    private String shptofax;
    @Basic(optional = false)
    @Column(name = "DATERATE")
    private int daterate;
    @Basic(optional = false)
    @Column(name = "SWPROCPPD")
    private short swprocppd;
    @Basic(optional = false)
    @Column(name = "CUROPER")
    private short curoper;
    @Basic(optional = false)
    @Column(name = "DRILLAPP")
    private String drillapp;
    @Basic(optional = false)
    @Column(name = "DRILLTYPE")
    private short drilltype;
    @Basic(optional = false)
    @Column(name = "DRILLDWNLK")
    private BigInteger drilldwnlk;
    @Basic(optional = false)
    @Column(name = "SHPVIACODE")
    private String shpviacode;
    @Basic(optional = false)
    @Column(name = "SHPVIADESC")
    private String shpviadesc;
    @Basic(optional = false)
    @Column(name = "SWJOB")
    private short swjob;
    @Basic(optional = false)
    @Column(name = "ERRBATCH")
    private int errbatch;
    @Basic(optional = false)
    @Column(name = "ERRENTRY")
    private int errentry;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "CTACPHONE")
    private String ctacphone;
    @Basic(optional = false)
    @Column(name = "CTACFAX")
    private String ctacfax;
    @Basic(optional = false)
    @Column(name = "CTACEMAIL")
    private String ctacemail;
    @Basic(optional = false)
    @Column(name = "AMTDSBWTAX")
    private BigDecimal amtdsbwtax;
    @Basic(optional = false)
    @Column(name = "AMTDSBNTAX")
    private BigDecimal amtdsbntax;
    @Basic(optional = false)
    @Column(name = "AMTDSCBASE")
    private BigDecimal amtdscbase;
    @Basic(optional = false)
    @Column(name = "INVCTYPE")
    private short invctype;
    @Basic(optional = false)
    @Column(name = "SWRTGINVC")
    private short swrtginvc;
    @Basic(optional = false)
    @Column(name = "RTGAPPLYTO")
    private String rtgapplyto;
    @Basic(optional = false)
    @Column(name = "SWRTG")
    private short swrtg;
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
    @Column(name = "RTGTERMS")
    private String rtgterms;
    @Basic(optional = false)
    @Column(name = "SWRTGDDTOV")
    private short swrtgddtov;
    @Basic(optional = false)
    @Column(name = "SWRTGAMTOV")
    private short swrtgamtov;
    @Basic(optional = false)
    @Column(name = "SWRTGRATE")
    private short swrtgrate;
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
    @Column(name = "TAXVERSION")
    private int taxversion;
    @Basic(optional = false)
    @Column(name = "SWTXRTGRPT")
    private short swtxrtgrpt;
    @Basic(optional = false)
    @Column(name = "CODECURNRC")
    private String codecurnrc;
    @Basic(optional = false)
    @Column(name = "SWTXCTLRC")
    private short swtxctlrc;
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
    @Column(name = "AMTGROSHC")
    private BigDecimal amtgroshc;
    @Basic(optional = false)
    @Column(name = "RTGAMTHC")
    private BigDecimal rtgamthc;
    @Basic(optional = false)
    @Column(name = "AMTDISCHC")
    private BigDecimal amtdischc;
    @Basic(optional = false)
    @Column(name = "DISTNETHC")
    private BigDecimal distnethc;
    @Basic(optional = false)
    @Column(name = "AMTPPDHC")
    private BigDecimal amtppdhc;
    @Basic(optional = false)
    @Column(name = "AMTDUEHC")
    private BigDecimal amtduehc;
    @Basic(optional = false)
    @Column(name = "SWPRTLBL")
    private short swprtlbl;
    @Basic(optional = false)
    @Column(name = "IDSHIPNBR")
    private String idshipnbr;
    @Basic(optional = false)
    @Column(name = "SWOECOST")
    private short swoecost;
    @Basic(optional = false)
    @Column(name = "ENTEREDBY")
    private String enteredby;
    @Basic(optional = false)
    @Column(name = "DATEBUS")
    private int datebus;

    public Aribh() {
    }

    public Aribh(AribhPK aribhPK) {
        this.aribhPK = aribhPK;
    }

    public Aribh(AribhPK aribhPK, int audtdate, int audttime, String audtuser, String audtorg, String idcust, String idinvc, String idshpt, String shipvia, String specinst, short texttrx, short idtrx, short invcstts, String ordrnbr, String custpo, String jobnbr, String invcdesc, short swprtinvc, String invcapplto, String idacctset, int dateinvc, int dateasof, String fiscyr, String fiscper, String codecurn, String ratetype, short swmanrte, BigDecimal exchratehc, BigDecimal origratehc, String termcode, short swtermovrd, int datedue, int datedisc, BigDecimal pctdisc, BigDecimal amtdiscavl, int lastline, String codeslsp1, String codeslsp2, String codeslsp3, String codeslsp4, String codeslsp5, BigDecimal pctsasplt1, BigDecimal pctsasplt2, BigDecimal pctsasplt3, BigDecimal pctsasplt4, BigDecimal pctsasplt5, short swtaxbl, short swmantx, String codetaxgrp, String codetax1, String codetax2, String codetax3, String codetax4, String codetax5, short taxstts1, short taxstts2, short taxstts3, short taxstts4, short taxstts5, BigDecimal basetax1, BigDecimal basetax2, BigDecimal basetax3, BigDecimal basetax4, BigDecimal basetax5, BigDecimal amttax1, BigDecimal amttax2, BigDecimal amttax3, BigDecimal amttax4, BigDecimal amttax5, BigDecimal amttxbl, BigDecimal amtnottxbl, BigDecimal amttaxtot, BigDecimal amtinvctot, BigDecimal amtppd, int amtpaymtot, BigDecimal amtpymschd, BigDecimal amtnettot, String idstdinvc, int dateprcs, String idppd, String idbill, String shptoloc, String shptoste1, String shptoste2, String shptoste3, String shptoste4, String shptocity, String shptostte, String shptopost, String shptoctry, String shptoctac, String shptophon, String shptofax, int daterate, short swprocppd, short curoper, String drillapp, short drilltype, BigInteger drilldwnlk, String shpviacode, String shpviadesc, short swjob, int errbatch, int errentry, String email, String ctacphone, String ctacfax, String ctacemail, BigDecimal amtdsbwtax, BigDecimal amtdsbntax, BigDecimal amtdscbase, short invctype, short swrtginvc, String rtgapplyto, short swrtg, BigDecimal rtgamt, BigDecimal rtgpercent, short rtgdays, int rtgdatedue, String rtgterms, short swrtgddtov, short swrtgamtov, short swrtgrate, int values, String srceappl, String arversion, int taxversion, short swtxrtgrpt, String codecurnrc, short swtxctlrc, BigDecimal raterc, String ratetyperc, int ratedaterc, short rateoprc, short swraterc, BigDecimal txamt1rc, BigDecimal txamt2rc, BigDecimal txamt3rc, BigDecimal txamt4rc, BigDecimal txamt5rc, BigDecimal txtotrc, BigDecimal txbsert1tc, BigDecimal txbsert2tc, BigDecimal txbsert3tc, BigDecimal txbsert4tc, BigDecimal txbsert5tc, BigDecimal txamtrt1tc, BigDecimal txamtrt2tc, BigDecimal txamtrt3tc, BigDecimal txamtrt4tc, BigDecimal txamtrt5tc, BigDecimal txbse1hc, BigDecimal txbse2hc, BigDecimal txbse3hc, BigDecimal txbse4hc, BigDecimal txbse5hc, BigDecimal txamt1hc, BigDecimal txamt2hc, BigDecimal txamt3hc, BigDecimal txamt4hc, BigDecimal txamt5hc, BigDecimal amtgroshc, BigDecimal rtgamthc, BigDecimal amtdischc, BigDecimal distnethc, BigDecimal amtppdhc, BigDecimal amtduehc, short swprtlbl, String idshipnbr, short swoecost, String enteredby, int datebus) {
        this.aribhPK = aribhPK;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.idcust = idcust;
        this.idinvc = idinvc;
        this.idshpt = idshpt;
        this.shipvia = shipvia;
        this.specinst = specinst;
        this.texttrx = texttrx;
        this.idtrx = idtrx;
        this.invcstts = invcstts;
        this.ordrnbr = ordrnbr;
        this.custpo = custpo;
        this.jobnbr = jobnbr;
        this.invcdesc = invcdesc;
        this.swprtinvc = swprtinvc;
        this.invcapplto = invcapplto;
        this.idacctset = idacctset;
        this.dateinvc = dateinvc;
        this.dateasof = dateasof;
        this.fiscyr = fiscyr;
        this.fiscper = fiscper;
        this.codecurn = codecurn;
        this.ratetype = ratetype;
        this.swmanrte = swmanrte;
        this.exchratehc = exchratehc;
        this.origratehc = origratehc;
        this.termcode = termcode;
        this.swtermovrd = swtermovrd;
        this.datedue = datedue;
        this.datedisc = datedisc;
        this.pctdisc = pctdisc;
        this.amtdiscavl = amtdiscavl;
        this.lastline = lastline;
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
        this.swtaxbl = swtaxbl;
        this.swmantx = swmantx;
        this.codetaxgrp = codetaxgrp;
        this.codetax1 = codetax1;
        this.codetax2 = codetax2;
        this.codetax3 = codetax3;
        this.codetax4 = codetax4;
        this.codetax5 = codetax5;
        this.taxstts1 = taxstts1;
        this.taxstts2 = taxstts2;
        this.taxstts3 = taxstts3;
        this.taxstts4 = taxstts4;
        this.taxstts5 = taxstts5;
        this.basetax1 = basetax1;
        this.basetax2 = basetax2;
        this.basetax3 = basetax3;
        this.basetax4 = basetax4;
        this.basetax5 = basetax5;
        this.amttax1 = amttax1;
        this.amttax2 = amttax2;
        this.amttax3 = amttax3;
        this.amttax4 = amttax4;
        this.amttax5 = amttax5;
        this.amttxbl = amttxbl;
        this.amtnottxbl = amtnottxbl;
        this.amttaxtot = amttaxtot;
        this.amtinvctot = amtinvctot;
        this.amtppd = amtppd;
        this.amtpaymtot = amtpaymtot;
        this.amtpymschd = amtpymschd;
        this.amtnettot = amtnettot;
        this.idstdinvc = idstdinvc;
        this.dateprcs = dateprcs;
        this.idppd = idppd;
        this.idbill = idbill;
        this.shptoloc = shptoloc;
        this.shptoste1 = shptoste1;
        this.shptoste2 = shptoste2;
        this.shptoste3 = shptoste3;
        this.shptoste4 = shptoste4;
        this.shptocity = shptocity;
        this.shptostte = shptostte;
        this.shptopost = shptopost;
        this.shptoctry = shptoctry;
        this.shptoctac = shptoctac;
        this.shptophon = shptophon;
        this.shptofax = shptofax;
        this.daterate = daterate;
        this.swprocppd = swprocppd;
        this.curoper = curoper;
        this.drillapp = drillapp;
        this.drilltype = drilltype;
        this.drilldwnlk = drilldwnlk;
        this.shpviacode = shpviacode;
        this.shpviadesc = shpviadesc;
        this.swjob = swjob;
        this.errbatch = errbatch;
        this.errentry = errentry;
        this.email = email;
        this.ctacphone = ctacphone;
        this.ctacfax = ctacfax;
        this.ctacemail = ctacemail;
        this.amtdsbwtax = amtdsbwtax;
        this.amtdsbntax = amtdsbntax;
        this.amtdscbase = amtdscbase;
        this.invctype = invctype;
        this.swrtginvc = swrtginvc;
        this.rtgapplyto = rtgapplyto;
        this.swrtg = swrtg;
        this.rtgamt = rtgamt;
        this.rtgpercent = rtgpercent;
        this.rtgdays = rtgdays;
        this.rtgdatedue = rtgdatedue;
        this.rtgterms = rtgterms;
        this.swrtgddtov = swrtgddtov;
        this.swrtgamtov = swrtgamtov;
        this.swrtgrate = swrtgrate;
        this.values = values;
        this.srceappl = srceappl;
        this.arversion = arversion;
        this.taxversion = taxversion;
        this.swtxrtgrpt = swtxrtgrpt;
        this.codecurnrc = codecurnrc;
        this.swtxctlrc = swtxctlrc;
        this.raterc = raterc;
        this.ratetyperc = ratetyperc;
        this.ratedaterc = ratedaterc;
        this.rateoprc = rateoprc;
        this.swraterc = swraterc;
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
        this.amtgroshc = amtgroshc;
        this.rtgamthc = rtgamthc;
        this.amtdischc = amtdischc;
        this.distnethc = distnethc;
        this.amtppdhc = amtppdhc;
        this.amtduehc = amtduehc;
        this.swprtlbl = swprtlbl;
        this.idshipnbr = idshipnbr;
        this.swoecost = swoecost;
        this.enteredby = enteredby;
        this.datebus = datebus;
    }

    public Aribh(int cntbtch, int cntitem) {
        this.aribhPK = new AribhPK(cntbtch, cntitem);
    }

    public AribhPK getAribhPK() {
        return aribhPK;
    }

    public void setAribhPK(AribhPK aribhPK) {
        this.aribhPK = aribhPK;
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

    public String getIdshpt() {
        return idshpt;
    }

    public void setIdshpt(String idshpt) {
        this.idshpt = idshpt;
    }

    public String getShipvia() {
        return shipvia;
    }

    public void setShipvia(String shipvia) {
        this.shipvia = shipvia;
    }

    public String getSpecinst() {
        return specinst;
    }

    public void setSpecinst(String specinst) {
        this.specinst = specinst;
    }

    public short getTexttrx() {
        return texttrx;
    }

    public void setTexttrx(short texttrx) {
        this.texttrx = texttrx;
    }

    public short getIdtrx() {
        return idtrx;
    }

    public void setIdtrx(short idtrx) {
        this.idtrx = idtrx;
    }

    public short getInvcstts() {
        return invcstts;
    }

    public void setInvcstts(short invcstts) {
        this.invcstts = invcstts;
    }

    public String getOrdrnbr() {
        return ordrnbr;
    }

    public void setOrdrnbr(String ordrnbr) {
        this.ordrnbr = ordrnbr;
    }

    public String getCustpo() {
        return custpo;
    }

    public void setCustpo(String custpo) {
        this.custpo = custpo;
    }

    public String getJobnbr() {
        return jobnbr;
    }

    public void setJobnbr(String jobnbr) {
        this.jobnbr = jobnbr;
    }

    public String getInvcdesc() {
        return invcdesc;
    }

    public void setInvcdesc(String invcdesc) {
        this.invcdesc = invcdesc;
    }

    public short getSwprtinvc() {
        return swprtinvc;
    }

    public void setSwprtinvc(short swprtinvc) {
        this.swprtinvc = swprtinvc;
    }

    public String getInvcapplto() {
        return invcapplto;
    }

    public void setInvcapplto(String invcapplto) {
        this.invcapplto = invcapplto;
    }

    public String getIdacctset() {
        return idacctset;
    }

    public void setIdacctset(String idacctset) {
        this.idacctset = idacctset;
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

    public String getCodecurn() {
        return codecurn;
    }

    public void setCodecurn(String codecurn) {
        this.codecurn = codecurn;
    }

    public String getRatetype() {
        return ratetype;
    }

    public void setRatetype(String ratetype) {
        this.ratetype = ratetype;
    }

    public short getSwmanrte() {
        return swmanrte;
    }

    public void setSwmanrte(short swmanrte) {
        this.swmanrte = swmanrte;
    }

    public BigDecimal getExchratehc() {
        return exchratehc;
    }

    public void setExchratehc(BigDecimal exchratehc) {
        this.exchratehc = exchratehc;
    }

    public BigDecimal getOrigratehc() {
        return origratehc;
    }

    public void setOrigratehc(BigDecimal origratehc) {
        this.origratehc = origratehc;
    }

    public String getTermcode() {
        return termcode;
    }

    public void setTermcode(String termcode) {
        this.termcode = termcode;
    }

    public short getSwtermovrd() {
        return swtermovrd;
    }

    public void setSwtermovrd(short swtermovrd) {
        this.swtermovrd = swtermovrd;
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

    public BigDecimal getPctdisc() {
        return pctdisc;
    }

    public void setPctdisc(BigDecimal pctdisc) {
        this.pctdisc = pctdisc;
    }

    public BigDecimal getAmtdiscavl() {
        return amtdiscavl;
    }

    public void setAmtdiscavl(BigDecimal amtdiscavl) {
        this.amtdiscavl = amtdiscavl;
    }

    public int getLastline() {
        return lastline;
    }

    public void setLastline(int lastline) {
        this.lastline = lastline;
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

    public short getSwtaxbl() {
        return swtaxbl;
    }

    public void setSwtaxbl(short swtaxbl) {
        this.swtaxbl = swtaxbl;
    }

    public short getSwmantx() {
        return swmantx;
    }

    public void setSwmantx(short swmantx) {
        this.swmantx = swmantx;
    }

    public String getCodetaxgrp() {
        return codetaxgrp;
    }

    public void setCodetaxgrp(String codetaxgrp) {
        this.codetaxgrp = codetaxgrp;
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

    public BigDecimal getAmttxbl() {
        return amttxbl;
    }

    public void setAmttxbl(BigDecimal amttxbl) {
        this.amttxbl = amttxbl;
    }

    public BigDecimal getAmtnottxbl() {
        return amtnottxbl;
    }

    public void setAmtnottxbl(BigDecimal amtnottxbl) {
        this.amtnottxbl = amtnottxbl;
    }

    public BigDecimal getAmttaxtot() {
        return amttaxtot;
    }

    public void setAmttaxtot(BigDecimal amttaxtot) {
        this.amttaxtot = amttaxtot;
    }

    public BigDecimal getAmtinvctot() {
        return amtinvctot;
    }

    public void setAmtinvctot(BigDecimal amtinvctot) {
        this.amtinvctot = amtinvctot;
    }

    public BigDecimal getAmtppd() {
        return amtppd;
    }

    public void setAmtppd(BigDecimal amtppd) {
        this.amtppd = amtppd;
    }

    public int getAmtpaymtot() {
        return amtpaymtot;
    }

    public void setAmtpaymtot(int amtpaymtot) {
        this.amtpaymtot = amtpaymtot;
    }

    public BigDecimal getAmtpymschd() {
        return amtpymschd;
    }

    public void setAmtpymschd(BigDecimal amtpymschd) {
        this.amtpymschd = amtpymschd;
    }

    public BigDecimal getAmtnettot() {
        return amtnettot;
    }

    public void setAmtnettot(BigDecimal amtnettot) {
        this.amtnettot = amtnettot;
    }

    public String getIdstdinvc() {
        return idstdinvc;
    }

    public void setIdstdinvc(String idstdinvc) {
        this.idstdinvc = idstdinvc;
    }

    public int getDateprcs() {
        return dateprcs;
    }

    public void setDateprcs(int dateprcs) {
        this.dateprcs = dateprcs;
    }

    public String getIdppd() {
        return idppd;
    }

    public void setIdppd(String idppd) {
        this.idppd = idppd;
    }

    public String getIdbill() {
        return idbill;
    }

    public void setIdbill(String idbill) {
        this.idbill = idbill;
    }

    public String getShptoloc() {
        return shptoloc;
    }

    public void setShptoloc(String shptoloc) {
        this.shptoloc = shptoloc;
    }

    public String getShptoste1() {
        return shptoste1;
    }

    public void setShptoste1(String shptoste1) {
        this.shptoste1 = shptoste1;
    }

    public String getShptoste2() {
        return shptoste2;
    }

    public void setShptoste2(String shptoste2) {
        this.shptoste2 = shptoste2;
    }

    public String getShptoste3() {
        return shptoste3;
    }

    public void setShptoste3(String shptoste3) {
        this.shptoste3 = shptoste3;
    }

    public String getShptoste4() {
        return shptoste4;
    }

    public void setShptoste4(String shptoste4) {
        this.shptoste4 = shptoste4;
    }

    public String getShptocity() {
        return shptocity;
    }

    public void setShptocity(String shptocity) {
        this.shptocity = shptocity;
    }

    public String getShptostte() {
        return shptostte;
    }

    public void setShptostte(String shptostte) {
        this.shptostte = shptostte;
    }

    public String getShptopost() {
        return shptopost;
    }

    public void setShptopost(String shptopost) {
        this.shptopost = shptopost;
    }

    public String getShptoctry() {
        return shptoctry;
    }

    public void setShptoctry(String shptoctry) {
        this.shptoctry = shptoctry;
    }

    public String getShptoctac() {
        return shptoctac;
    }

    public void setShptoctac(String shptoctac) {
        this.shptoctac = shptoctac;
    }

    public String getShptophon() {
        return shptophon;
    }

    public void setShptophon(String shptophon) {
        this.shptophon = shptophon;
    }

    public String getShptofax() {
        return shptofax;
    }

    public void setShptofax(String shptofax) {
        this.shptofax = shptofax;
    }

    public int getDaterate() {
        return daterate;
    }

    public void setDaterate(int daterate) {
        this.daterate = daterate;
    }

    public short getSwprocppd() {
        return swprocppd;
    }

    public void setSwprocppd(short swprocppd) {
        this.swprocppd = swprocppd;
    }

    public short getCuroper() {
        return curoper;
    }

    public void setCuroper(short curoper) {
        this.curoper = curoper;
    }

    public String getDrillapp() {
        return drillapp;
    }

    public void setDrillapp(String drillapp) {
        this.drillapp = drillapp;
    }

    public short getDrilltype() {
        return drilltype;
    }

    public void setDrilltype(short drilltype) {
        this.drilltype = drilltype;
    }

    public BigInteger getDrilldwnlk() {
        return drilldwnlk;
    }

    public void setDrilldwnlk(BigInteger drilldwnlk) {
        this.drilldwnlk = drilldwnlk;
    }

    public String getShpviacode() {
        return shpviacode;
    }

    public void setShpviacode(String shpviacode) {
        this.shpviacode = shpviacode;
    }

    public String getShpviadesc() {
        return shpviadesc;
    }

    public void setShpviadesc(String shpviadesc) {
        this.shpviadesc = shpviadesc;
    }

    public short getSwjob() {
        return swjob;
    }

    public void setSwjob(short swjob) {
        this.swjob = swjob;
    }

    public int getErrbatch() {
        return errbatch;
    }

    public void setErrbatch(int errbatch) {
        this.errbatch = errbatch;
    }

    public int getErrentry() {
        return errentry;
    }

    public void setErrentry(int errentry) {
        this.errentry = errentry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCtacphone() {
        return ctacphone;
    }

    public void setCtacphone(String ctacphone) {
        this.ctacphone = ctacphone;
    }

    public String getCtacfax() {
        return ctacfax;
    }

    public void setCtacfax(String ctacfax) {
        this.ctacfax = ctacfax;
    }

    public String getCtacemail() {
        return ctacemail;
    }

    public void setCtacemail(String ctacemail) {
        this.ctacemail = ctacemail;
    }

    public BigDecimal getAmtdsbwtax() {
        return amtdsbwtax;
    }

    public void setAmtdsbwtax(BigDecimal amtdsbwtax) {
        this.amtdsbwtax = amtdsbwtax;
    }

    public BigDecimal getAmtdsbntax() {
        return amtdsbntax;
    }

    public void setAmtdsbntax(BigDecimal amtdsbntax) {
        this.amtdsbntax = amtdsbntax;
    }

    public BigDecimal getAmtdscbase() {
        return amtdscbase;
    }

    public void setAmtdscbase(BigDecimal amtdscbase) {
        this.amtdscbase = amtdscbase;
    }

    public short getInvctype() {
        return invctype;
    }

    public void setInvctype(short invctype) {
        this.invctype = invctype;
    }

    public short getSwrtginvc() {
        return swrtginvc;
    }

    public void setSwrtginvc(short swrtginvc) {
        this.swrtginvc = swrtginvc;
    }

    public String getRtgapplyto() {
        return rtgapplyto;
    }

    public void setRtgapplyto(String rtgapplyto) {
        this.rtgapplyto = rtgapplyto;
    }

    public short getSwrtg() {
        return swrtg;
    }

    public void setSwrtg(short swrtg) {
        this.swrtg = swrtg;
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

    public String getRtgterms() {
        return rtgterms;
    }

    public void setRtgterms(String rtgterms) {
        this.rtgterms = rtgterms;
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

    public short getSwrtgrate() {
        return swrtgrate;
    }

    public void setSwrtgrate(short swrtgrate) {
        this.swrtgrate = swrtgrate;
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

    public int getTaxversion() {
        return taxversion;
    }

    public void setTaxversion(int taxversion) {
        this.taxversion = taxversion;
    }

    public short getSwtxrtgrpt() {
        return swtxrtgrpt;
    }

    public void setSwtxrtgrpt(short swtxrtgrpt) {
        this.swtxrtgrpt = swtxrtgrpt;
    }

    public String getCodecurnrc() {
        return codecurnrc;
    }

    public void setCodecurnrc(String codecurnrc) {
        this.codecurnrc = codecurnrc;
    }

    public short getSwtxctlrc() {
        return swtxctlrc;
    }

    public void setSwtxctlrc(short swtxctlrc) {
        this.swtxctlrc = swtxctlrc;
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

    public BigDecimal getAmtgroshc() {
        return amtgroshc;
    }

    public void setAmtgroshc(BigDecimal amtgroshc) {
        this.amtgroshc = amtgroshc;
    }

    public BigDecimal getRtgamthc() {
        return rtgamthc;
    }

    public void setRtgamthc(BigDecimal rtgamthc) {
        this.rtgamthc = rtgamthc;
    }

    public BigDecimal getAmtdischc() {
        return amtdischc;
    }

    public void setAmtdischc(BigDecimal amtdischc) {
        this.amtdischc = amtdischc;
    }

    public BigDecimal getDistnethc() {
        return distnethc;
    }

    public void setDistnethc(BigDecimal distnethc) {
        this.distnethc = distnethc;
    }

    public BigDecimal getAmtppdhc() {
        return amtppdhc;
    }

    public void setAmtppdhc(BigDecimal amtppdhc) {
        this.amtppdhc = amtppdhc;
    }

    public BigDecimal getAmtduehc() {
        return amtduehc;
    }

    public void setAmtduehc(BigDecimal amtduehc) {
        this.amtduehc = amtduehc;
    }

    public short getSwprtlbl() {
        return swprtlbl;
    }

    public void setSwprtlbl(short swprtlbl) {
        this.swprtlbl = swprtlbl;
    }

    public String getIdshipnbr() {
        return idshipnbr;
    }

    public void setIdshipnbr(String idshipnbr) {
        this.idshipnbr = idshipnbr;
    }

    public short getSwoecost() {
        return swoecost;
    }

    public void setSwoecost(short swoecost) {
        this.swoecost = swoecost;
    }

    public String getEnteredby() {
        return enteredby;
    }

    public void setEnteredby(String enteredby) {
        this.enteredby = enteredby;
    }

    public int getDatebus() {
        return datebus;
    }

    public void setDatebus(int datebus) {
        this.datebus = datebus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aribhPK != null ? aribhPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aribh)) {
            return false;
        }
        Aribh other = (Aribh) object;
        if ((this.aribhPK == null && other.aribhPK != null) || (this.aribhPK != null && !this.aribhPK.equals(other.aribhPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Aribh[aribhPK=" + aribhPK + "]";
    }

}
