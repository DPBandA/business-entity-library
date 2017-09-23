/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.accpac;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import jm.com.dpbennett.business.entity.utils.BusinessEntityUtils;

/**
 * AccPac Invoice Batches table
 * @author DBennett
 */
@Entity
@Table(name = "ARIBC")
@NamedQueries({
    @NamedQuery(name = "Aribc.findAll", query = "SELECT a FROM Aribc a"),
    @NamedQuery(name = "Aribc.findByCntbtch", query = "SELECT a FROM Aribc a WHERE a.cntbtch = :cntbtch"),
    @NamedQuery(name = "Aribc.findByAudtdate", query = "SELECT a FROM Aribc a WHERE a.audtdate = :audtdate"),
    @NamedQuery(name = "Aribc.findByAudttime", query = "SELECT a FROM Aribc a WHERE a.audttime = :audttime"),
    @NamedQuery(name = "Aribc.findByAudtuser", query = "SELECT a FROM Aribc a WHERE a.audtuser = :audtuser"),
    @NamedQuery(name = "Aribc.findByAudtorg", query = "SELECT a FROM Aribc a WHERE a.audtorg = :audtorg"),
    @NamedQuery(name = "Aribc.findByDatebtch", query = "SELECT a FROM Aribc a WHERE a.datebtch = :datebtch"),
    @NamedQuery(name = "Aribc.findByBtchdesc", query = "SELECT a FROM Aribc a WHERE a.btchdesc = :btchdesc"),
    @NamedQuery(name = "Aribc.findByCntinvcent", query = "SELECT a FROM Aribc a WHERE a.cntinvcent = :cntinvcent"),
    @NamedQuery(name = "Aribc.findByAmtentr", query = "SELECT a FROM Aribc a WHERE a.amtentr = :amtentr"),
    @NamedQuery(name = "Aribc.findByBtchtype", query = "SELECT a FROM Aribc a WHERE a.btchtype = :btchtype"),
    @NamedQuery(name = "Aribc.findByBtchstts", query = "SELECT a FROM Aribc a WHERE a.btchstts = :btchstts"),
    @NamedQuery(name = "Aribc.findByInvctype", query = "SELECT a FROM Aribc a WHERE a.invctype = :invctype"),
    @NamedQuery(name = "Aribc.findByCntlstitem", query = "SELECT a FROM Aribc a WHERE a.cntlstitem = :cntlstitem"),
    @NamedQuery(name = "Aribc.findByPostseqnbr", query = "SELECT a FROM Aribc a WHERE a.postseqnbr = :postseqnbr"),
    @NamedQuery(name = "Aribc.findByNbrerrors", query = "SELECT a FROM Aribc a WHERE a.nbrerrors = :nbrerrors"),
    @NamedQuery(name = "Aribc.findByDtelstedit", query = "SELECT a FROM Aribc a WHERE a.dtelstedit = :dtelstedit"),
    @NamedQuery(name = "Aribc.findBySwprinted", query = "SELECT a FROM Aribc a WHERE a.swprinted = :swprinted"),
    @NamedQuery(name = "Aribc.findBySrceappl", query = "SELECT a FROM Aribc a WHERE a.srceappl = :srceappl")})
public class Aribc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CNTBTCH")
    private Integer cntbtch;
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
    @Column(name = "DATEBTCH")
    private int datebtch;
    @Basic(optional = false)
    @Column(name = "BTCHDESC")
    private String btchdesc;
    @Basic(optional = false)
    @Column(name = "CNTINVCENT")
    private int cntinvcent;
    @Basic(optional = false)
    @Column(name = "AMTENTR")
    private BigDecimal amtentr;
    @Basic(optional = false)
    @Column(name = "BTCHTYPE")
    private short btchtype;
    @Basic(optional = false)
    @Column(name = "BTCHSTTS")
    private short btchstts;
    @Basic(optional = false)
    @Column(name = "INVCTYPE")
    private short invctype;
    @Basic(optional = false)
    @Column(name = "CNTLSTITEM")
    private int cntlstitem;
    @Basic(optional = false)
    @Column(name = "POSTSEQNBR")
    private int postseqnbr;
    @Basic(optional = false)
    @Column(name = "NBRERRORS")
    private int nbrerrors;
    @Basic(optional = false)
    @Column(name = "DTELSTEDIT")
    private int dtelstedit;
    @Basic(optional = false)
    @Column(name = "SWPRINTED")
    private short swprinted;
    @Basic(optional = false)
    @Column(name = "SRCEAPPL")
    private String srceappl;

    public Aribc() {
    }

    public Aribc(Integer cntbtch) {
        this.cntbtch = cntbtch;
    }

    public Date getBatchDate() {
        return BusinessEntityUtils.getDateFromInt(datebtch);
    }

    public void setBatchDate(Date batchDate) {
        datebtch =  BusinessEntityUtils.getIntFromDate(batchDate);
    }

    public Aribc(Integer cntbtch, int audtdate, int audttime, String audtuser, String audtorg, int datebtch, String btchdesc, int cntinvcent, BigDecimal amtentr, short btchtype, short btchstts, short invctype, int cntlstitem, int postseqnbr, int nbrerrors, int dtelstedit, short swprinted, String srceappl) {
        this.cntbtch = cntbtch;
        this.audtdate = audtdate;
        this.audttime = audttime;
        this.audtuser = audtuser;
        this.audtorg = audtorg;
        this.datebtch = datebtch;
        this.btchdesc = btchdesc;
        this.cntinvcent = cntinvcent;
        this.amtentr = amtentr;
        this.btchtype = btchtype;
        this.btchstts = btchstts;
        this.invctype = invctype;
        this.cntlstitem = cntlstitem;
        this.postseqnbr = postseqnbr;
        this.nbrerrors = nbrerrors;
        this.dtelstedit = dtelstedit;
        this.swprinted = swprinted;
        this.srceappl = srceappl;
    }

    public Integer getCntbtch() {
        return cntbtch;
    }

    public void setCntbtch(Integer cntbtch) {
        this.cntbtch = cntbtch;
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

    public int getDatebtch() {
        return datebtch;
    }

    public void setDatebtch(int datebtch) {
        this.datebtch = datebtch;
    }

    public String getBtchdesc() {
        return btchdesc;
    }

    public void setBtchdesc(String btchdesc) {
        this.btchdesc = btchdesc;
    }

    public int getCntinvcent() {
        return cntinvcent;
    }

    public void setCntinvcent(int cntinvcent) {
        this.cntinvcent = cntinvcent;
    }

    public BigDecimal getAmtentr() {
        return amtentr;
    }

    public void setAmtentr(BigDecimal amtentr) {
        this.amtentr = amtentr;
    }

    public short getBtchtype() {
        return btchtype;
    }

    public void setBtchtype(short btchtype) {
        this.btchtype = btchtype;
    }

    public short getBtchstts() {
        return btchstts;
    }

    public void setBtchstts(short btchstts) {
        this.btchstts = btchstts;
    }

    public String getBatchStatus() {
        switch (btchstts) {
            case 1:
                return "Open";
            case 3:
                return "Posted";
            case 4:
                return "Deleted";
            case 5:
                return "Post in Progress";
            case 7:
                return "Ready to Post";
        }

        return "";
    }

    public short getInvctype() {
        return invctype;
    }

    public void setInvctype(short invctype) {
        this.invctype = invctype;
    }

    public int getCntlstitem() {
        return cntlstitem;
    }

    public void setCntlstitem(int cntlstitem) {
        this.cntlstitem = cntlstitem;
    }

    public int getPostseqnbr() {
        return postseqnbr;
    }

    public void setPostseqnbr(int postseqnbr) {
        this.postseqnbr = postseqnbr;
    }

    public int getNbrerrors() {
        return nbrerrors;
    }

    public void setNbrerrors(int nbrerrors) {
        this.nbrerrors = nbrerrors;
    }

    public int getDtelstedit() {
        return dtelstedit;
    }

    public void setDtelstedit(int dtelstedit) {
        this.dtelstedit = dtelstedit;
    }

    public short getSwprinted() {
        return swprinted;
    }

    public void setSwprinted(short swprinted) {
        this.swprinted = swprinted;
    }

    public String getPrinted() {
        switch (swprinted) {
            case 0:
                return "No";
            case 1:
                return "Yes";
        }

        return "No";
    }

    public String getSrceappl() {
        return srceappl;
    }

    public void setSrceappl(String srceappl) {
        this.srceappl = srceappl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cntbtch != null ? cntbtch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aribc)) {
            return false;
        }
        Aribc other = (Aribc) object;
        if ((this.cntbtch == null && other.cntbtch != null) || (this.cntbtch != null && !this.cntbtch.equals(other.cntbtch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jm.org.bsj.entity.accpac.Aribc[cntbtch=" + cntbtch + "]";
    }
}
