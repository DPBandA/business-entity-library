/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2023  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.rm;

import java.io.Serializable;
import java.text.Collator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "dateperiod")
public class DatePeriod implements BusinessEntity, Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String dateField;
    private String label;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    private Boolean startDateDisabled;
    private Boolean endDateDisabled;
    @Transient
    private Boolean init;
    @Transient
    private Boolean isDirty;
    @Transient
    private Boolean show;

    public DatePeriod() {
    }

    public DatePeriod(
            String name,
            String type,
            String dateField,
            String label,
            Date startDate,
            Date endDate,
            Boolean startDateDisabled,
            Boolean endDateDisabled,
            Boolean init) {

        this.name = name;
        this.type = type;
        this.dateField = dateField;
        this.label = label;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateDisabled = startDateDisabled;
        this.endDateDisabled = endDateDisabled;
        this.init = init;

        if (init) {
            init();
        }
    }

    public Boolean getShow() {
        if (show == null) {
            show = true;
        }
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public static DatePeriod findById(EntityManager em, Long id) {
        return em.find(DatePeriod.class, id);
    }

    public String getLabel() {
        if (label == null) {
            label = "";
        }
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDateField() {
        return dateField;
    }

    public void setDateField(String dateField) {
        this.dateField = dateField;
    }

    @Override
    public Boolean getIsDirty() {
        if (isDirty == null) {
            isDirty = false;
        }

        return isDirty;
    }

    @Override
    public void setIsDirty(Boolean isDirty) {
        this.isDirty = isDirty;
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
        names.add("This month last year");
        names.add("This financial month");
        names.add("This financial year");
        //names.add("This financial year to date");
        names.add("This year to date");
        names.add("This year");
        names.add("Last month");
        names.add("Last financial month");
        names.add("Last financial year");
        //names.add("Last financial year to date");
        names.add("Last year");
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

    public String getFormattedStartDate() {
        return BusinessEntityUtils.getDateInMediumDateFormat(getStartDate());
    }

    public String getFormattedEndDate() {
        return BusinessEntityUtils.getDateInMediumDateFormat(getEndDate());
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

    /**
     * Initialize the date period to the financial year based on the reference
     * date.
     *
     * @param refDate
     */
    public void initFinancialYearPeriod(Date refDate) {
        Calendar referenceCalendar = Calendar.getInstance();

        referenceCalendar.setTime(refDate);
     
        // This creates the reference date with 0 msecs.
        Date referenceDate = BusinessEntityUtils.createDate(
                referenceCalendar.get(Calendar.YEAR),
                referenceCalendar.get(Calendar.MONTH),
                referenceCalendar.get(Calendar.DAY_OF_MONTH));

        // Setup reference years and dates
        int referenceYear = referenceCalendar.get(Calendar.YEAR);
        Date referenceStartOfFinancialYear = BusinessEntityUtils.createDate(referenceYear, 3, 1);
        Date referenceEndOfFinancialYear = BusinessEntityUtils.createDate(referenceYear, 2, 31);
        Date referenceStartOfYear = BusinessEntityUtils.createDate(referenceYear, 0, 1);
        Date referenceEndOfYear = BusinessEntityUtils.createDate(referenceYear, 11, 31);

        if (referenceDate.equals(referenceStartOfYear)) {
            setStartDate(BusinessEntityUtils.createDate(referenceYear - 1, 3, 1));
            setEndDate(referenceEndOfFinancialYear);
        } else if (referenceDate.after(referenceStartOfYear)
                && referenceDate.before(referenceEndOfFinancialYear)) {
            setStartDate(BusinessEntityUtils.createDate(referenceYear - 1, 3, 1));
            setEndDate(referenceEndOfFinancialYear);
        } else if (referenceDate.equals(referenceEndOfFinancialYear)) {
            setStartDate(BusinessEntityUtils.createDate(referenceYear - 1, 3, 1));
            setEndDate(referenceEndOfFinancialYear);
        } else if (referenceDate.after(referenceEndOfFinancialYear)
                && referenceDate.before(referenceEndOfYear)) {
            setStartDate(referenceStartOfFinancialYear);
            setEndDate(BusinessEntityUtils.createDate(referenceYear + 1, 2, 31));
        } else {
            setStartDate(referenceStartOfFinancialYear);
            setEndDate(BusinessEntityUtils.createDate(referenceYear + 1, 2, 31));
        }

    }

    public DatePeriod getInitDatePeriod() {
        return initDatePeriod();
    }

    public DatePeriod initDatePeriod() {
        return initDatePeriod(new Date());
    }

    public DatePeriod initDatePeriod(Date referencDate) {

        switch (getName()) {
            case "This month":
                setStartDate(BusinessEntityUtils.getStartOfCurrentMonth());
                setEndDate(BusinessEntityUtils.getEndOfCurrentMonth());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This month last year":
                setStartDate(BusinessEntityUtils.getStartOfCurrentMonthPreviousYear());
                setEndDate(BusinessEntityUtils.getEndOfCurrentMonthPreviousYear());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This financial month":
                initFinancialMonthPeriod(BusinessEntityUtils.createDate(referencDate));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This financial year":
                initFinancialYearPeriod(referencDate);
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
//            case "This financial year to date":
//                initFinancialYearPeriod(BusinessEntityUtils.createDate(referencDate));
//                setStartDateDisabled(true);
//                setEndDateDisabled(true);
//                break;
            case "This year to date":
                setStartDate(BusinessEntityUtils.getStartOfCurrentYear());
                setEndDate(BusinessEntityUtils.createDate(referencDate));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This year":
                setStartDate(BusinessEntityUtils.getStartOfCurrentYear());
                setEndDate(BusinessEntityUtils.getEndOfCurrentYear());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last month":
                setStartDate(BusinessEntityUtils.getStartOfLastMonth());
                setEndDate(BusinessEntityUtils.getEndOfLastMonth());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last financial month":
                initFinancialMonthPeriod(BusinessEntityUtils.getEndOfLastMonth());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last financial year":
//                setStartDate(BusinessEntityUtils.createDate(BusinessEntityUtils.getPreviousYear(), 3, 1));
//                setEndDate(BusinessEntityUtils.createDate(BusinessEntityUtils.getCurrentYear(), 2, 31));
                initFinancialYearPeriod(BusinessEntityUtils.createDate(referencDate));
                startDate = BusinessEntityUtils.adjustDate(startDate, Calendar.YEAR, -1);
                endDate = BusinessEntityUtils.adjustDate(endDate, Calendar.YEAR, -1);
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
//            case "Last financial year to date":
//                // get this financial year to date and adjust years by 1
//                initFinancialYearPeriod(BusinessEntityUtils.createDate(referencDate));
//                startDate = BusinessEntityUtils.adjustDate(startDate, Calendar.YEAR, -1);
//                endDate = BusinessEntityUtils.adjustDate(endDate, Calendar.YEAR, -1);
//                setStartDateDisabled(true);
//                setEndDateDisabled(true);
//                break;
            case "Last year":
                setStartDate(BusinessEntityUtils.getStartOfPreviousYear());
                setEndDate(BusinessEntityUtils.getEndOfPreviousYear());
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Custom":
                setStartDateDisabled(false);
                setEndDateDisabled(false);
                break;
            default:
                setStartDateDisabled(false);
                setEndDateDisabled(false);
                break;
        }

        return this;

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
        if (name == null) {
            name = "";
        }
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

    @Override
    public ReturnMessage save(EntityManager em) {
        try {
            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, this);
            em.getTransaction().commit();

            return new ReturnMessage();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Date period not saved");
    }

    @Override
    public ReturnMessage validate(EntityManager em) {
        return new ReturnMessage();
    }

    @Override
    public int compareTo(Object o) {
//        return Collator.getInstance().compare(this.getLabel(), ((DatePeriod) o).getLabel());
        if (((DatePeriod) o).getId() != null && this.getId() != null) {
            return Collator.getInstance().compare(this.getId().toString(), ((DatePeriod) o).getId().toString());
        } else {
            return 1;
        }
    }
}
