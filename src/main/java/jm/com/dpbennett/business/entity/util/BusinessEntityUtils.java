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
package jm.com.dpbennett.business.entity.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Person;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.fm.AccPacCustomer;
import jm.com.dpbennett.business.entity.hrm.Address;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.rm.DatePeriod;

/**
 *
 * @author Desmond Bennett
 */
public class BusinessEntityUtils {

    private static EntityManagerFactory EMF;

    public static final String[] MONTH_NAMES = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public static final String[] ALPHABET = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    private static final DateTimeFormatter MEDIUM_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    private static final DateTimeFormatter MEDIUM_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy, h:mm a");

    public static String sanitize(String value) {

        if (value != null) {
            return value.replaceAll("'", "`").
                    replaceAll("&#x2F;", "/");
        }

        return "";

    }

    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    public static int getLetterIndex(String letter) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i].equals(letter)) {
                return i;
            }
        }

        return -1;
    }

    public static Boolean isBusinessEntityInList(List<? extends BusinessEntity> list, Long id) {
        for (BusinessEntity businessEntity : list) {
            if (Objects.equals(businessEntity.getId(), id)) {
                return true;
            }
        }

        return false;
    }

    public static Boolean isDateWithinPeriod(LocalDateTime dateToCheck, LocalDateTime startDate, LocalDateTime endDate) {
        return dateToCheck != null
                && startDate != null
                && endDate != null
                && !dateToCheck.isBefore(startDate)
                && !dateToCheck.isAfter(endDate);
    }

    public static String getMonthAndYearString(LocalDateTime date) {
        if (date == null) {
            return "";
        }

        return MONTH_NAMES[date.getMonthValue() - 1] + " " + date.getYear();
    }

    public static String getBasicAddress(Address address) {
        String addressStr;

        addressStr = getValidString(address.getAddressLine1()) + "\n"
                + getValidString(address.getAddressLine2()) + "\n"
                + getValidString(address.getStateOrProvince()) + "\n"
                + getValidString(address.getCity()) + "\n";

        return addressStr;
    }

    public static String getValidString(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    public static int characterCount(String str, char chr) {
        int count = 0;

        char chars[] = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == chr) {
                count++;
            }
        }

        return count;

    }

    public static boolean containsChar(String str, char chr) {

        char chars[] = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == chr) {
                return true;
            }
        }

        return false;
    }

    public static boolean validateName(String name) {

        if (name == null) {
            return false;
        } else if (name.isEmpty()) {
            return false;
        } else if (containsChar(name, '"')) {
            return false;
        } else if (name.contains("`")) {
            return false;
        } else if (name.contains("'")) {
            return false;
        } else if (name.contains(";")) {
            return false;
        } else if (name.contains("&")) {
            return false;
        } else if (name.contains(":")) {
            return false;
        } else if (name.contains(",")) {
            return false;
        } else if (name.contains("#")) {
            return false;
        } else if (name.contains("@")) {
            return false;
        } else if (name.contains("$")) {
            return false;
        } else if (name.contains("%")) {
            return false;
        } else if (name.contains("^")) {
            return false;
        } else if (name.contains("*")) {
            return false;
        } else if (name.contains("+")) {
            return false;
        } else if (name.contains("=")) {
            return false;
        } else if (name.contains("!")) {
            return false;
        }

        return true;
    }

    public static boolean validateClientName(String name) {

        return validateIdentifier(name);
    }

    public static boolean validateAccountingCode(String code) {

        return validateIdentifier(code);
    }

    public static boolean validateIdentifier(String identifier) {

        if (identifier == null) {
            return false;
        } else if (identifier.isEmpty()) {
            return false;
        } else if (identifier.contains("'")) {
            return false;
        } else if (containsChar(identifier, '"')) {
            return false;
        }

        return true;
    }

    public static boolean validateText(String text) {

        if (text == null) {
            return false;
        } else if (text.isEmpty()) {
            return false;
        }

        return true;
    }

    public static boolean validateAddressText(String text) {

        if (text == null) {
            return false;
        } else if (text.isEmpty()) {
            return false;
        } else if (containsChar(text, ';')) {
            return false;
        }

        return true;
    }

    public static boolean validateAddressLine(String line) {

        return validateAddressText(line);
    }

    public static boolean validateNameOfBusinessEntity(BusinessEntity entity) {

        return validateName(entity.getName());
    }

    public static Boolean isBasicDataType(String type) {

        switch (type) {
            case "java.lang.Long":
            case "java.lang.Integer":
            case "java.lang.Double":
            case "java.lang.Boolean":
            case "java.lang.String":
            case "java.time.LocalDate":
            case "java.time.LocalDateTime":
            case "java.util.Date":
                return Boolean.TRUE;
            default:
                break;
        }

        return Boolean.FALSE;
    }

    public static Method getMethodOfKnownDataType(String methodPath) {
        int i = 0;
        Method method = null;

        try {

            String[] methodNames = methodPath.split("/");
            Class<?> c = Class.forName(methodNames[0]);
            String[] methodName = methodNames[1].split("\\.");

            do {
                method = c.getMethod(methodName[i], (Class[]) null);
                String dataType = method.getReturnType().getName();
                if (isBasicDataType(dataType)) {
                    break;
                } else {
                    c = method.getReturnType();
                }
                i++;

            } while (i < methodName.length);

            return method;
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static Class<?> getClass(String methodPath) {
        int i = 0;
        Method method;
        Class<?> c;

        try {

            String[] methodNames = methodPath.split("/");
            c = Class.forName(methodNames[0]);
            String[] methodName = methodNames[1].split("\\.");

            do {
                method = c.getMethod(methodName[i], (Class[]) null);
                String dataType = method.getReturnType().getName();
                if (isBasicDataType(dataType)) {
                    return c;
                } else {
                    c = method.getReturnType();
                }
                i++;

            } while (i < methodName.length);

            return c;
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static Object getBusinessEntityValue(Object entity, String methodPath) {

        Object value = null;
        Class<?> c;
        Method m;

        String[] path = methodPath.split("/");
        String[] methodNames = path[1].split("\\.");

        int i = -1;
        try {
            do {
                ++i;
                c = entity.getClass();
                m = c.getMethod(methodNames[i], (Class[]) null);
                entity = m.invoke(c.cast(entity), (Object[]) null);
                value = entity;
            } while (!isBasicDataType(m.getReturnType().getName()));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            System.out.println(ex);
        }

        return value;
    }

    public static Client createClient(AccPacCustomer apcustomer) {
        Client client = new Client();

        return client;
    }

    public static Long saveBusinessEntity(EntityManager em, BusinessEntity businessEntity) {

        try {

            businessEntity.setName(sanitize(businessEntity.getName()));

            if (businessEntity.getId() != null) {
                em.merge(businessEntity);
            } else {
                em.persist(businessEntity);
                em.flush();
            }

            return businessEntity.getId();

        } catch (Exception e) {

            System.out.println(e);

            return null;
        }

    }

    public synchronized static void saveBusinessEntityInTransaction(EntityManager em,
            BusinessEntity entity) {
        try {

            em.getTransaction().begin();
            BusinessEntityUtils.saveBusinessEntity(em, entity);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println(e);
        }
    }

    public static LocalDateTime createDate(int year, int monthIndex, int day) {
        return LocalDateTime.of(year, monthIndex + 1, day, 0, 0, 0, 0);
    }

    public static LocalDateTime createDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }

        return date.toLocalDate().atStartOfDay();
    }

    public static LocalDateTime createDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        return date.atStartOfDay();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }

        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE_ID);
    }

    public static Date toDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return Date.from(dateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * Compatibility helper for legacy callers. Prefer createDate(LocalDateTime).
     */
    public static LocalDateTime createDate(Date date) {
        return createDate(toLocalDateTime(date));
    }

    public static int getYearFromDate(LocalDateTime date) {
        return date != null ? date.getYear() : 0;
    }

    public static LocalDateTime getStartOfCurrentYear() {
        return LocalDate.now().withDayOfYear(1).atStartOfDay();
    }

    public static LocalDateTime getEndOfCurrentYear() {
        LocalDate today = LocalDate.now();
        return LocalDate.of(today.getYear(), 12, 31).atStartOfDay();
    }

    public static LocalDateTime getStartOfCurrentMonth() {
        return YearMonth.now().atDay(1).atStartOfDay();
    }

    public static LocalDateTime getStartOfCurrentMonthPreviousYear() {
        LocalDate today = LocalDate.now();
        return LocalDate.of(today.getYear() - 1, today.getMonth(), 1).atStartOfDay();
    }

    public static LocalDateTime getStartOfMonthInCurrentYear(int monthIndex) {
        return LocalDate.of(LocalDate.now().getYear(), monthIndex + 1, 1).atStartOfDay();
    }

    public static LocalDateTime getEndOfCurrentMonth() {
        return YearMonth.now().atEndOfMonth().atStartOfDay();
    }

    public static LocalDateTime getEndOfCurrentMonthPreviousYear() {
        LocalDate today = LocalDate.now();
        return YearMonth.of(today.getYear() - 1, today.getMonth()).atEndOfMonth().atStartOfDay();
    }

    public static LocalDateTime getThisDatePreviousYear() {
        return LocalDate.now().minusYears(1).atStartOfDay();
    }

    public static LocalDateTime getEndOfMonthInCurrentYear(int monthIndex) {
        return YearMonth.of(LocalDate.now().getYear(), monthIndex + 1).atEndOfMonth().atStartOfDay();
    }

    public static int getDaysInMonth(int monthIndex) {
        return YearMonth.of(LocalDate.now().getYear(), monthIndex + 1).lengthOfMonth();
    }

    public static String getIntegerString(long number, int digits) {

        String string = String.format("%0" + digits + "d", number);

        return string;
    }

    public static String getDateString(LocalDateTime d, String delim, String format, String sep) {
        if (d != null) {
            return getDateString(d.toLocalDate(), delim, format, sep);
        } else {
            return "";
        }
    }

    public static String getDateInMediumDateFormat(LocalDateTime date) {
        if (date != null) {
            return date.format(MEDIUM_DATE_FORMATTER);
        } else {
            return "";
        }
    }

    public static String getUserDefinedDateFormat(LocalDateTime date, String format) {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern(format));
        } else {
            return "";
        }
    }

    public static String getDateInMediumDateAndTimeFormat(LocalDateTime date) {
        if (date != null) {
            return date.format(MEDIUM_DATE_TIME_FORMATTER);
        } else {
            return "";
        }
    }

    public static String getDateString(LocalDate date, String delim, String format, String sep) {
        if (delim == null) {
            delim = "'";
        }
        if (format == null) {
            format = "YMD";
        }
        if (sep == null) {
            sep = "-";
        }

        if (date != null) {
            String str;
            String year = getIntegerString(date.getYear(), 4);
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            switch (format) {
                case "YMD":
                    str = delim + year + sep + month + sep + day + delim;
                    break;
                case "MDY":
                    str = delim + month + sep + day + sep + year + delim;
                    break;
                case "DMY":
                    str = delim + day + sep + month + sep + year + delim;
                    break;
                default:
                    str = delim + year + sep + month + sep + day + delim;
                    break;
            }
            return str;
        }

        return null;
    }

    public static String getDateStringFromCalendar(
            Calendar c,
            String delim,
            String format,
            String sep) {

        return getDateString(toLocalDateTime(c.getTime()), delim, format, sep);
    }

    public static String getDateStringFromDate(
            LocalDateTime d,
            String delim,
            String format,
            String sep) {
        return getDateString(d, delim, format, sep);
    }

    public static LocalDateTime getDateFromInt(int dateInt) {
        String dateString = "" + dateInt;

        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(4, 6));
        int day = Integer.parseInt(dateString.substring(6, 8));

        return LocalDateTime.of(year, month, day, 0, 0);
    }

    public static int getIntFromDate(LocalDateTime date) {
        return Integer.parseInt(date.format(DateTimeFormatter.BASIC_ISO_DATE));
    }

    public static int getPreviousYear() {
        return Year.now().getValue() - 1;
    }

    public static int getCurrentYear() {
        return Year.now().getValue();
    }

    public static int getNextYear() {
        return Year.now().getValue() + 1;
    }

    public static LocalDateTime getStartOfPreviousYear() {
        return LocalDate.of(LocalDate.now().getYear() - 1, 1, 1).atStartOfDay();
    }

    public static LocalDateTime getEndOfPreviousYear() {
        return LocalDate.of(LocalDate.now().getYear() - 1, 12, 31).atStartOfDay();
    }

    public static LocalDateTime getStartOfLastMonth() {
        return getStartOfCurrentMonth().minusMonths(1);
    }

    public static LocalDateTime getEndOfLastMonth() {
        return YearMonth.from(LocalDate.now().minusMonths(1)).atEndOfMonth().atStartOfDay();
    }

    public static EntityManager getEntityManager(String PU) {

        try {
            EMF = Persistence.createEntityManagerFactory(PU);
            if (EMF.isOpen()) {
                EntityManager EM = EMF.createEntityManager();
                if (EM.isOpen()) {
                    return EM;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static EntityManagerFactory getEntityManagerFactory(String PU) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
            if (emf.isOpen()) {
                return emf;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }

    public static LocalDateTime adjustDate(LocalDateTime date, ChronoUnit datePart, int amount) {
        if (date == null) {
            return null;
        }

        return createDate(date).plus(amount, datePart);
    }

    public static LocalDateTime adjustDate(LocalDateTime date, int datePart, int amount) {
        return adjustDate(date, calendarFieldToChronoUnit(datePart), amount);
    }

    private static ChronoUnit calendarFieldToChronoUnit(int datePart) {
        switch (datePart) {
            case Calendar.YEAR:
                return ChronoUnit.YEARS;
            case Calendar.MONTH:
                return ChronoUnit.MONTHS;
            case Calendar.DAY_OF_MONTH:
                return ChronoUnit.DAYS;
            case Calendar.HOUR:
            case Calendar.HOUR_OF_DAY:
                return ChronoUnit.HOURS;
            case Calendar.MINUTE:
                return ChronoUnit.MINUTES;
            case Calendar.SECOND:
                return ChronoUnit.SECONDS;
            default:
                return ChronoUnit.DAYS;
        }
    }

    public static int calculatePeriodInWorkingDays(LocalDateTime startDate, LocalDateTime endDate) {
        int workDays = 0;

        if (startDate == null || endDate == null) {
            return 0;
        }

        LocalDate start = startDate.toLocalDate();
        LocalDate end = endDate.toLocalDate();

        if (start.isAfter(end)) {
            return 0;
        } else if (start.equals(end)) {
            return isWorkingDay(start) ? 1 : 0;
        }

        LocalDate current = start;
        while (!current.isAfter(end)) {
            if (isWorkingDay(current)) {
                workDays++;
            }
            current = current.plusDays(1);
        }

        return workDays;
    }

    private static boolean isWorkingDay(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.SATURDAY
                && date.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    public static DatePeriod[] getMonthlyReportDatePeriods(DatePeriod reportingPeriod) {

        DatePeriod previousReportingPeriod
                = new DatePeriod("Previous reporting period", "month", null, null,
                        BusinessEntityUtils.adjustDate(reportingPeriod.getStartDate(), Calendar.MONTH, -1),
                        BusinessEntityUtils.adjustDate(reportingPeriod.getEndDate(), Calendar.MONTH, -1),
                        false, false, true);

        LocalDateTime now = LocalDateTime.now();
        int year = reportingPeriod.getEndDate().getYear();
        int monthIndex = now.getMonthValue() - 1;
        int day = now.getDayOfMonth();

        DatePeriod reportingPeriodYTD = new DatePeriod("Financial year to date",
                "month",
                null,
                null,
                null,
                BusinessEntityUtils.createDate(year, monthIndex, day),
                false, false, true);

        DatePeriod reportingPeriodLastYear = new DatePeriod("Reporting period last year", "year", null, null,
                BusinessEntityUtils.adjustDate(reportingPeriod.getStartDate(), Calendar.YEAR, -1),
                BusinessEntityUtils.adjustDate(reportingPeriod.getEndDate(), Calendar.YEAR, -1),
                false, false, true);

        DatePeriod lastFinancialYTD = new DatePeriod("Financial year to date",
                "year",
                null,
                null,
                null,
                BusinessEntityUtils.createDate(year - 1, monthIndex, day),
                false, false, true);
        lastFinancialYTD.setName("Last financial year to date");

        DatePeriod datePeriods[] = {
            reportingPeriod,
            previousReportingPeriod,
            reportingPeriodYTD,
            reportingPeriodLastYear,
            lastFinancialYTD
        };

        return datePeriods;

    }

    public static LocalDateTime getModifiedDate(LocalDateTime orgDate, int modPeriod, int modAmount) {
        return adjustDate(orgDate, modPeriod, modAmount);
    }

    public static Boolean areDatesEqual(LocalDateTime date1, LocalDateTime date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        return removeTimeFromDate(date1).equals(removeTimeFromDate(date2));
    }

    public static LocalDateTime removeTimeFromDate(LocalDateTime date) {
        return createDate(date);
    }

    public static Connection getConnection(EntityManager em) {
        if (em == null) {
            return null;
        }

        try {
            return em.unwrap(Connection.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Connection establishConnection(
            String driverClassName,
            String url,
            String user,
            String password) {

        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        return null;

    }

    public static String getPersonFullName(Person person, Boolean reverse) {

        if (person != null) {
            if (person.getFirstName().equals("") && person.getLastName().equals("")) {
                return "";
            }
            if (person.getFirstName().equals("") && !person.getLastName().equals("")) {
                return person.getLastName();
            }
            if (!person.getFirstName().equals("") && person.getLastName().equals("")) {
                return person.getFirstName();
            }
            if (!person.getFirstName().equals("") && !person.getLastName().equals("")) {
                if (reverse) {
                    return person.getLastName() + ", " + person.getFirstName();
                } else {
                    return person.getFirstName() + " " + person.getLastName();
                }
            }

            return "";
        } else {
            return "";
        }
    }

    public static String getContactFullName(Contact contact) {

        return getPersonFullName(contact, false);

    }

    public static String getMainTelFaxEmail(Contact contact) {
        if (contact != null) {
            if (contact.getMainPhoneNumber().getLocalNumber() == null
                    && contact.getMainFaxNumber().getLocalNumber() == null
                    && contact.getInternet().getEmail1() == null) {
                return "";
            }
            if (contact.getMainPhoneNumber().getLocalNumber() == null
                    && contact.getMainFaxNumber().getLocalNumber() == null
                    && contact.getInternet().getEmail1() != null) {
                return contact.getInternet().getEmail1();
            }
            if (contact.getMainPhoneNumber().getLocalNumber() == null
                    && contact.getMainFaxNumber().getLocalNumber() != null
                    && contact.getInternet().getEmail1() != null) {
                return "Fax: " + contact.getMainFaxNumber().getLocalNumber() + "/"
                        + contact.getInternet().getEmail1();
            }
            if (contact.getMainPhoneNumber().getLocalNumber() != null
                    && contact.getMainFaxNumber().getLocalNumber() != null
                    && contact.getInternet().getEmail1() != null) {
                return contact.getMainPhoneNumber().getLocalNumber() + "/"
                        + "Fax: " + contact.getMainFaxNumber().getLocalNumber() + "/"
                        + contact.getInternet().getEmail1();
            }
            if (contact.getMainPhoneNumber().getLocalNumber() != null
                    && contact.getMainFaxNumber().getLocalNumber() != null
                    && contact.getInternet().getEmail1() == null) {
                return contact.getMainPhoneNumber().getLocalNumber() + "/"
                        + "Fax: " + contact.getMainFaxNumber().getLocalNumber();
            }
            if (contact.getMainPhoneNumber().getLocalNumber() != null
                    && contact.getMainFaxNumber().getLocalNumber() == null
                    && contact.getInternet().getEmail1() != null) {
                return contact.getMainPhoneNumber().getLocalNumber() + "/"
                        + contact.getInternet().getEmail1();
            }

            return "";
        } else {
            return "";
        }
    }

    public static String getContactTelAndFax(Contact contact) {
        return ((!contact.getMainPhoneNumber().getLocalNumber().trim().equals(""))
                ? "Tel: " + contact.getMainPhoneNumber().getLocalNumber()
                : "")
                + ((!contact.getMainFaxNumber().getLocalNumber().trim().equals("")
                && !contact.getMainPhoneNumber().getLocalNumber().trim().equals(""))
                ? ", "
                : "")
                + ((!contact.getMainFaxNumber().getLocalNumber().trim().equals(""))
                ? "Fax: " + contact.getMainFaxNumber().getLocalNumber()
                : "");
    }

    public static boolean setupDatabaseConnection(String PU) {

        try {
            EMF = Persistence.createEntityManagerFactory(PU);
            if (EMF.isOpen()) {
                EntityManager EM = EMF.createEntityManager();
                if (EM.isOpen()) {
                    System.out.println("Connected!");
                    EM.close();
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed: " + ex);
            return false;
        }

        return true;
    }

    public static EntityManagerFactory createEntityManagerFactory(String PU) {

        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PU);
            if (entityManagerFactory.isOpen()) {
                return entityManagerFactory;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed: " + ex);
            return null;
        }
    }

    public static EntityManagerFactory getEMF() {
        return EMF;
    }

    public static Boolean validateDate(
            LocalDateTime date,
            int minYear,
            int maxYear) {

        if (date != null) {
            int year = date.getYear();
            if ((year < minYear) || (year > maxYear)) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public static String getAlphaCode(long number) {
        return convertNumberToAlphabet(number);
    }

    public static String convertNumberToAlphabet(long num) {
        long numeric = (num) % 26;
        String letter = "" + (char) (65 + numeric);
        long num2 = (long) ((num) / 26);

        if (num2 > 0) {
            return convertNumberToAlphabet(num2 - 1) + letter;
        } else {
            return letter;
        }
    }

    public static int convertAlphabetToNumber(String alphabet, int len, int num) {
        int letterIndex, previousLetterIndex;

        char chars[] = alphabet.toCharArray();
        letterIndex = getLetterIndex("" + chars[len - 1]);
        if ((len - 1) > 0) {
            previousLetterIndex = getLetterIndex("" + chars[len - 2]);
        } else {
            previousLetterIndex = -1;
        }
        num = num + letterIndex;
        if (previousLetterIndex != -1) {
            num = num + (previousLetterIndex + len - 1) * 26;
        }

        if ((len - 2) > 0) {
            String newAlphabet = alphabet.substring(0, len - 1);
            return convertAlphabetToNumber(newAlphabet, newAlphabet.length(), num);
        } else {
            return num;
        }
    }

    public static String getMonthShortFormat(LocalDateTime date) {
        if (date == null) {
            return "";
        }

        return date.getMonth().name().substring(0, 1)
                + date.getMonth().name().substring(1, 3).toLowerCase();
    }

    public static String getYearShortFormat(LocalDateTime date, int digits) {
        if (date == null) {
            return "";
        }

        String yearString = "" + date.getYear();

        return yearString.substring(yearString.length() - digits, yearString.length());
    }

    public static String getShortenedString(String string, int maxLength) {
        String shortenedString;

        if (string.length() > maxLength) {
            shortenedString = string.substring(0, maxLength);
        } else {
            shortenedString = string;
        }

        return shortenedString;
    }

    public static String getColumnPartOfCellReference(String cellReference) {
        String cellCol = "";

        try {
            char chars[] = cellReference.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (Character.isLetter(c)) {
                    cellCol = cellCol + c;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return cellCol;
        }

    }

    public static String getRowPartOfCellReference(String cellReference) {
        String cellRow = "";

        try {
            char chars[] = cellReference.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (Character.isDigit(c)) {
                    cellRow = cellRow + c;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return cellRow;
        }

    }

    public static Double roundTo2DecimalPlaces(Double value) {

        DecimalFormat df = new DecimalFormat("###.##");

        String strValue = df.format(value);

        return Double.valueOf(strValue);
    }

    public static long getMediumDateStringAsLong(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, MEDIUM_DATE_FORMATTER);
            return date.atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
        } catch (DateTimeParseException ex) {
            return 0L;
        }

    }

    public static Boolean deleteEntity(EntityManager em, Object entity) {

        try {
            if (entity != null) {
                Object managedEntity = em.contains(entity) ? entity : em.merge(entity);
                em.remove(managedEntity);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T extends BusinessEntity> T attachReference(EntityManager em, T entity) {

        if (entity == null) {
            return null;
        }

        if (entity.getId() == null) {
            return entity;
        }

        return em.getReference((Class<T>) entity.getClass(), entity.getId());
    }

    /*
     * Legacy java.util.Date overloads retained to make staged migration easier.
     * Prefer the LocalDateTime overloads above in new BEL/Jakarta code.
     */
    public static Boolean isDateWithinPeriod(Date dateToCheck, Date startDate, Date endDate) {
        return isDateWithinPeriod(toLocalDateTime(dateToCheck), toLocalDateTime(startDate), toLocalDateTime(endDate));
    }

    public static String getMonthAndYearString(Date date) {
        return getMonthAndYearString(toLocalDateTime(date));
    }

    public static String getDateString(Date d, String delim, String format, String sep) {
        return getDateString(toLocalDateTime(d), delim, format, sep);
    }

    public static String getDateInMediumDateFormat(Date date) {
        return getDateInMediumDateFormat(toLocalDateTime(date));
    }

    public static String getUserDefinedDateFormat(Date date, String format) {
        return getUserDefinedDateFormat(toLocalDateTime(date), format);
    }

    public static String getDateInMediumDateAndTimeFormat(Date date) {
        return getDateInMediumDateAndTimeFormat(toLocalDateTime(date));
    }

    public static String getDateStringFromDate(Date d, String delim, String format, String sep) {
        return getDateString(toLocalDateTime(d), delim, format, sep);
    }

    public static int getIntFromDate(Date date) {
        return getIntFromDate(toLocalDateTime(date));
    }

    public static Date adjustDate(Date date, int datePart, int amount) {
        return toDate(adjustDate(toLocalDateTime(date), datePart, amount));
    }

    public static int calculatePeriodInWorkingDays(Date startDate, Date endDate) {
        return calculatePeriodInWorkingDays(toLocalDateTime(startDate), toLocalDateTime(endDate));
    }

    public static Date getModifiedDate(Date orgDate, int modPeriod, int modAmount) {
        return toDate(getModifiedDate(toLocalDateTime(orgDate), modPeriod, modAmount));
    }

    public static Boolean areDatesEqual(Date date1, Date date2) {
        return areDatesEqual(toLocalDateTime(date1), toLocalDateTime(date2));
    }

    public static Date removeTimeFromDate(Date date) {
        return toDate(removeTimeFromDate(toLocalDateTime(date)));
    }

    public static Boolean validateDate(Calendar c, int minYear, int maxYear) {
        return c != null && validateDate(toLocalDateTime(c.getTime()), minYear, maxYear);
    }

    public static String getMonthShortFormat(Date date) {
        return getMonthShortFormat(toLocalDateTime(date));
    }

    public static String getYearShortFormat(Date date, int digits) {
        return getYearShortFormat(toLocalDateTime(date), digits);
    }

}
