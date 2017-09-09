/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import jm.com.dpbennett.business.utils.BusinessEntityUtils;
import jm.com.dpbennett.business.utils.MethodResult;


/**
 *
 * @author DBennett
 */
@Entity
@Table(name = "dateperiod")
@XmlRootElement
public class DatePeriod implements BusinessEntity, Serializable, Converter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    private Boolean startDateDisabled;
    private Boolean endDateDisabled;
    private Boolean init;

    public DatePeriod() {
    }

    public DatePeriod(
            String name,
            String type,
            Date startDate,
            Date endDate,
            Boolean startDateDisabled,
            Boolean endDateDisabled,
            Boolean init) {
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateDisabled = startDateDisabled;
        this.endDateDisabled = endDateDisabled;
        this.init = init;

        if (init) {
            init();
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getInit() {
        return init;
    }

    public void setInit(Boolean init) {
        this.init = init;
    }

    private void init() {
        initDatePeriod();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static List<String> getDatePeriodNames() {
        ArrayList<String> names = new ArrayList<>();

        names.add("This month");
        names.add("Last month");
        names.add("This year");
        names.add("Last year");
        names.add("This year to date");
        names.add("This financial month");
        names.add("Last financial month");
        names.add("Last financial year");
        names.add("This financial year to date");
        names.add("Last financial year to date");
        names.add("Financial year to date");
        names.add("This month (last year)");
        names.add("Custom");

        return names;
    }

    public Boolean getEndDateDisabled() {
        return endDateDisabled;
    }

    public void setEndDateDisabled(Boolean endDateDisabled) {
        this.endDateDisabled = endDateDisabled;
    }

    public Boolean getStartDateDisabled() {
        return startDateDisabled;
    }

    public void setStartDateDisabled(Boolean startDateDisabled) {
        this.startDateDisabled = startDateDisabled;
    }

    public Date getEndDate() {
        if (endDate == null) {
            initDatePeriod();
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        if (startDate == null) {
            initDatePeriod();
        }
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void initFinancialMonthPeriod(Date baseDate) {
        Calendar c = Calendar.getInstance();

        // get start date that is the 25 of the current month
        c.setTime(baseDate);
        Date edate = BusinessEntityUtils.createDate(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                25);

        // get end date is the 26 of the previous month
        Calendar edateCal = Calendar.getInstance();
        edateCal.setTime(edate);
        edateCal.add(Calendar.MONTH, -1);
        edateCal.add(Calendar.DAY_OF_MONTH, 1);
        Date sdate = edateCal.getTime();

        setStartDate(sdate);
        setEndDate(edate);
    }

    public void initFinancialYearPeriod(Date baseDate) {
        Calendar baseCalendar = Calendar.getInstance();
        int baseYear;
        // get start date that is the 25 of the current month

        baseCalendar.setTime(baseDate);
        baseYear = baseCalendar.get(Calendar.YEAR);
        Date edate = BusinessEntityUtils.createDate(
                baseYear,
                baseCalendar.get(Calendar.MONTH),
                baseCalendar.get(Calendar.DAY_OF_MONTH));

        // get start of financial year
        // create of financial year using this base date
        Date endOfBaseYearToDate = BusinessEntityUtils.createDate(baseYear, 3, 1); // tk April 1
        Calendar endOfBaseYearCalendar = Calendar.getInstance();
        endOfBaseYearCalendar.setTime(endOfBaseYearToDate);
        if (baseCalendar.before(endOfBaseYearCalendar)) {
            baseYear = baseYear - 1;
        }
        Date sdate = BusinessEntityUtils.createDate(
                baseYear,
                3, // April 1
                1);

        setStartDate(sdate);
        setEndDate(edate);
    }

    public void initDatePeriod() {
        switch (getName()) {
            case "This year":
                setStartDate(BusinessEntityUtils.getStartOfCurrentYear());
                setEndDate(BusinessEntityUtils.getEndOfCurrentYear());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last year":
                setStartDate(BusinessEntityUtils.getStartOfPreviousYear());
                setEndDate(BusinessEntityUtils.getEndOfPreviousYear());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This month":
                setStartDate(BusinessEntityUtils.getStartOfCurrentMonth());
                setEndDate(BusinessEntityUtils.getEndOfCurrentMonth());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last financial month":
                initFinancialMonthPeriod(BusinessEntityUtils.getEndOfLastMonth());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This financial month":
                initFinancialMonthPeriod(BusinessEntityUtils.createDate(new Date()));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last month":
                setStartDate(BusinessEntityUtils.getStartOfLastMonth());
                setEndDate(BusinessEntityUtils.getEndOfLastMonth());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This year to date":
                setStartDate(BusinessEntityUtils.getStartOfCurrentYear());
                setEndDate(BusinessEntityUtils.createDate(new Date()));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This financial year to date":
                initFinancialYearPeriod(BusinessEntityUtils.createDate(new Date()));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last financial year to date":
                // get this financial year to date and adjust years by 1
                initFinancialYearPeriod(BusinessEntityUtils.createDate(new Date()));
                startDate = BusinessEntityUtils.adjustDate(startDate, Calendar.YEAR, -1);
                endDate = BusinessEntityUtils.adjustDate(endDate, Calendar.YEAR, -1);
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last financial year":
                setStartDate(BusinessEntityUtils.createDate(BusinessEntityUtils.getPreviousYear(), 3, 1));
                setEndDate(BusinessEntityUtils.createDate(BusinessEntityUtils.getCurrentYear(), 2, 31));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Financial year to date":
                if (endDate == null) {
                    endDate = BusinessEntityUtils.createDate(new Date());
                }
                initFinancialYearPeriod(endDate);
                setStartDateDisabled(true);
                setEndDateDisabled(false);
                break;
            case "This month (last year)":
                setStartDate(BusinessEntityUtils.getStartOfCurrentMonthPreviousYear());
                setEndDate(BusinessEntityUtils.getEndOfCurrentMonthPreviousYear());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Custom":
                if (startDate == null) {
                    setStartDate(BusinessEntityUtils.createDate(new Date()));
                }
                if (endDate == null) {
                    setEndDate(BusinessEntityUtils.createDate(new Date()));
                }
                setStartDateDisabled(false);
                setEndDateDisabled(false);
                break;
        }

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatePeriod)) {
            return false;
        }
        DatePeriod other = (DatePeriod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        return getName() + " (" + formatter.format(startDate) + " to " + formatter.format(endDate) + ")";
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPeriodString() {
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        return formatter.format(startDate) + " to " + formatter.format(endDate);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        DatePeriod dp = new DatePeriod("Last financial year to date", "year", null, null, false, false, true);

        System.out.println(dp.getStartDate());
        System.out.println(dp.getEndDate());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DatePeriod period = new DatePeriod();

        if (value != null) {
            period.setName(value);
        }

        return period;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((DatePeriod) value).getName();
    }

    @Override
    public MethodResult save(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodResult validate(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
