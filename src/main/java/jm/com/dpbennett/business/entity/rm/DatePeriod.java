/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2026  D P Bennett & Associates Limited

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

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.text.Collator;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.sm.SystemOption;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.business.entity.util.ReturnMessage;

/**
 *
 * @author Desmond Bennett
 */
@Entity
@Table(name = "dateperiod")
public class DatePeriod implements BusinessEntity, Comparable<DatePeriod> {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter MEDIUM_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    private static final System.Logger LOG = System.getLogger(DatePeriod.class.getName());
    private static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    private static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    private static LocalDateTime startOfDay(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        
        return dateTime.toLocalDate().atStartOfDay();
    }
    private static LocalDateTime createDate(int year, int month, int day) {
        return LocalDateTime.of(year, month, day, 0, 0, 0, 0);
    }
    private static LocalDateTime getStartOfCurrentMonth(LocalDateTime referenceDate) {
        return referenceDate.withDayOfMonth(1).toLocalDate().atStartOfDay();
    }
    private static LocalDateTime getEndOfCurrentMonth(LocalDateTime referenceDate) {
        return referenceDate.withDayOfMonth(referenceDate.toLocalDate().lengthOfMonth()).toLocalDate().atStartOfDay();
    }
    private static LocalDateTime getStartOfCurrentYear(LocalDateTime referenceDate) {
        return createDate(referenceDate.getYear(), 1, 1);
    }
    private static LocalDateTime getEndOfCurrentYear(LocalDateTime referenceDate) {
        return createDate(referenceDate.getYear(), 12, 31);
    }
    private static LocalDateTime getStartOfPreviousYear(LocalDateTime referenceDate) {
        return createDate(referenceDate.getYear() - 1, 1, 1);
    }
    private static LocalDateTime getEndOfPreviousYear(LocalDateTime referenceDate) {
        return createDate(referenceDate.getYear() - 1, 12, 31);
    }
    private static String formatMediumDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        
        return MEDIUM_DATE_FORMATTER.format(dateTime);
    }
    public static DatePeriod findById(EntityManager em, Long id) {
        return em.find(DatePeriod.class, id);
    }
    public static List<String> getDatePeriodNames() {
        ArrayList<String> names = new ArrayList<>();
        
        names.add("This month");
        names.add("This month last year");
        names.add("This financial month");
        names.add("This financial year");
        names.add("This year to date");
        names.add("This year");
        names.add("Last month");
        names.add("Last financial month");
        names.add("Last financial year");
        names.add("Last year");
        names.add("Custom");
        
        return names;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String dateField;
    private String label;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
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
            LocalDateTime startDate,
            LocalDateTime endDate,
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

        if (Boolean.TRUE.equals(init)) {
            init();
        }
    }

    /**
     * Compatibility constructor for older callers that still pass java.util.Date.
     * Prefer the LocalDateTime constructor in new code.
     */
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

        this(
                name,
                type,
                dateField,
                label,
                toLocalDateTime(startDate),
                toLocalDateTime(endDate),
                startDateDisabled,
                endDateDisabled,
                init);
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

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
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

    public LocalDateTime getEndDate() {
        if (endDate == null) {
            initDatePeriod();
        }

        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Compatibility setter for older JSF/utility code that still passes java.util.Date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = toLocalDateTime(endDate);
    }

    public LocalDateTime getStartDate() {
        if (startDate == null) {
            initDatePeriod();
        }
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Compatibility setter for older JSF/utility code that still passes java.util.Date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = toLocalDateTime(startDate);
    }

    public Date getStartDateAsDate() {
        return toDate(getStartDate());
    }

    public Date getEndDateAsDate() {
        return toDate(getEndDate());
    }

    public String getFormattedStartDate() {
        return formatMediumDate(getStartDate());
    }

    public String getFormattedEndDate() {
        return formatMediumDate(getEndDate());
    }

    public void initFinancialMonthPeriod(LocalDateTime baseDate) {
        LocalDateTime referenceDate = startOfDay(baseDate != null ? baseDate : LocalDateTime.now());
        LocalDateTime edate = createDate(referenceDate.getYear(), referenceDate.getMonthValue(), 25);
        LocalDateTime sdate = edate.minusMonths(1).plusDays(1);

        setStartDate(sdate);
        setEndDate(edate);
    }

    /**
     * Compatibility overload for older callers that still pass java.util.Date.
     */
    public void initFinancialMonthPeriod(Date baseDate) {
        initFinancialMonthPeriod(toLocalDateTime(baseDate));
    }

    public void initFinancialYearPeriod(LocalDateTime refDate) {
        LocalDateTime referenceDate = startOfDay(refDate != null ? refDate : LocalDateTime.now());
        int referenceYear = referenceDate.getYear();

        LocalDateTime referenceStartOfFinancialYear = createDate(referenceYear, 4, 1);
        LocalDateTime referenceEndOfFinancialYear = createDate(referenceYear, 3, 31);
        LocalDateTime referenceStartOfYear = createDate(referenceYear, 1, 1);
        LocalDateTime referenceEndOfYear = createDate(referenceYear, 12, 31);

        if (referenceDate.equals(referenceStartOfYear)
                || (referenceDate.isAfter(referenceStartOfYear) && referenceDate.isBefore(referenceEndOfFinancialYear))
                || referenceDate.equals(referenceEndOfFinancialYear)) {
            setStartDate(createDate(referenceYear - 1, 4, 1));
            setEndDate(referenceEndOfFinancialYear);
        } else if (referenceDate.isAfter(referenceEndOfFinancialYear)
                && referenceDate.isBefore(referenceEndOfYear)) {
            setStartDate(referenceStartOfFinancialYear);
            setEndDate(createDate(referenceYear + 1, 3, 31));
        } else {
            setStartDate(referenceStartOfFinancialYear);
            setEndDate(createDate(referenceYear + 1, 3, 31));
        }
    }

    /**
     * Compatibility overload for older callers that still pass java.util.Date.
     */
    public void initFinancialYearPeriod(Date refDate) {
        initFinancialYearPeriod(toLocalDateTime(refDate));
    }

    public DatePeriod getInitDatePeriod() {
        return initDatePeriod();
    }

    public DatePeriod initDatePeriod() {
        return initDatePeriod(LocalDateTime.now());
    }

    /**
     * Compatibility overload for older callers that still pass java.util.Date.
     */
    public DatePeriod initDatePeriod(Date referenceDate) {
        return initDatePeriod(toLocalDateTime(referenceDate));
    }

    public DatePeriod initDatePeriod(LocalDateTime referenceDate) {
        LocalDateTime refDate = startOfDay(referenceDate != null ? referenceDate : LocalDateTime.now());

        switch (getName()) {
            case "This month":
                setStartDate(getStartOfCurrentMonth(refDate));
                setEndDate(getEndOfCurrentMonth(refDate));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This month last year":
                setStartDate(getStartOfCurrentMonth(refDate).minusYears(1));
                setEndDate(getEndOfCurrentMonth(refDate).minusYears(1));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This financial month":
                initFinancialMonthPeriod(refDate);
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This financial year":
                initFinancialYearPeriod(refDate);
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This year to date":
                setStartDate(getStartOfCurrentYear(refDate));
                setEndDate(refDate);
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "This year":
                setStartDate(getStartOfCurrentYear(refDate));
                setEndDate(getEndOfCurrentYear(refDate));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last month":
                setStartDate(getStartOfCurrentMonth(refDate).minusMonths(1));
                setEndDate(getStartOfCurrentMonth(refDate).minusDays(1));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last financial month":
                initFinancialMonthPeriod(getStartOfCurrentMonth(refDate).minusDays(1));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last financial year":
                initFinancialYearPeriod(refDate);
                setStartDate(startDate.minusYears(1));
                setEndDate(endDate.minusYears(1));
                setStartDateDisabled(true);
                setEndDateDisabled(true);
                break;
            case "Last year":
                setStartDate(getStartOfPreviousYear(refDate));
                setEndDate(getEndOfPreviousYear(refDate));
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
        if (!(object instanceof DatePeriod)) {
            return false;
        }
        DatePeriod other = (DatePeriod) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return getName() + " (" + getPeriodString() + ")";
    }

    @Override
    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    public String getPeriodString() {
        return formatMediumDate(getStartDate()) + " to " + formatMediumDate(getEndDate());
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
    public int compareTo(DatePeriod o) {
        if (o != null && o.getId() != null && this.getId() != null) {
            return Collator.getInstance().compare(this.getId().toString(), o.getId().toString());
        } else {
            return 1;
        }
    }

    @Override
    public Boolean getActive() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LocalDateTime getDateEntered() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDateEntered(LocalDateTime dateEntered) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LocalDateTime getDateEdited() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDateEdited(LocalDateTime dateEdited) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ReturnMessage delete(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getComments() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setComments(String comments) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person getEditedBy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEditedBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person getEnteredBy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEnteredBy(Person person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ReturnMessage saveUnique(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SystemOption> getSettings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSettings(List<SystemOption> settings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SystemOption getSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSetting(String setting, String settingValue, String type, String category) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
