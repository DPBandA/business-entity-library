/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2018  D P Bennett & Associates Limited

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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.fm.AccPacCustomer;
import jm.com.dpbennett.business.entity.hrm.Address;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.cm.Client;
import jm.com.dpbennett.business.entity.hrm.Contact;
import jm.com.dpbennett.business.entity.rm.DatePeriod;
import jm.com.dpbennett.business.entity.Person;

/**
 *
 * @author Desmond Bennett
 */
public class BusinessEntityUtils {

    
    private static EntityManagerFactory EMF;
    public static String MONTH_NAMES[] = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    public static String ALPHABET[] = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public static Date getNow() {
        return new Date();
    }

    public static int getLetterIndex(String letter) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i].equals(letter)) {
                return i;
            }
        }

        return -1;
    }
    
    public static Boolean isBusinessEntityList(List<? extends BusinessEntity> list, Long id) { 
        for (BusinessEntity businessEntity : list) {
            if (Objects.equals(businessEntity.getId(), id)) {
                return true;
            }
        }
        
        return false;
    }

    public static Boolean isDateWithinPeriod(Date dateToCheck, Date startDate, Date endDate) {
        return (dateToCheck.compareTo(startDate) >= 0) && (dateToCheck.compareTo(endDate) <= 0);
    }

    public static String getMonthAndYearString(Date date) {
        String dateMonthAndYearString;
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        dateMonthAndYearString = MONTH_NAMES[c.get(Calendar.MONTH)];
        dateMonthAndYearString = dateMonthAndYearString + " " + getYearFromDate(date);

        return dateMonthAndYearString;
    }

    public static String getBasicAddress(Address address) {
        String addressStr = "";

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
        } else if (name.equals("")) {
            return false;
        } else if (containsChar(name, '"')) {
            return false;
        } else if (name.contains("''")) {
            return false;
        } else if (name.contains(";")) {
            return false;
        }

        return true;
    }

    public static boolean validateText(String name) {

        if (name == null) {
            return false;
        } else if (name.equals("")) {
            return false;
        } else if (containsChar(name, '"')) {
            return false;
        } else if (name.contains("''")) {
            return false;
        }

        return true;
    }

    public static boolean validateAddressLine(String name) {

        if (name == null) {
            return false;
        } else if (name.equals("")) {
            return false;
        } else if (containsChar(name, '"')) {
            return false;
        } else if (name.contains("''")) {
            return false;
        } else if (name.contains(";")) {
            return false;
        }

        return true;
    }

    public static boolean validateNameOfBusinessEntity(BusinessEntity entity) {

        if (entity.getName() == null) {
            return false;
        } else if (entity.getName().equals("")) {
            return false;
        } else if (containsChar(entity.getName(), '"')) {
            return false;
        } else if (entity.getName().contains("''")) {
            return false;
        }

        return true;
    }

    public static Boolean isBasicDataType(String type) {

        if (type.equals("java.lang.Long")) {
            return Boolean.TRUE;
        } else if (type.equals("java.lang.Integer")) {
            return Boolean.TRUE;
        } else if (type.equals("java.lang.Double")) {
            return Boolean.TRUE;
        } else if (type.equals("java.lang.Boolean")) {
            return Boolean.TRUE;
        } else if (type.equals("java.lang.String")) {
            return Boolean.TRUE;
        } else if (type.equals("java.util.Date")) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * Returns a method that is of known basic type given a string representing
     * a path to a method that will return a value for use in table of values
     * for example.
     *
     * @param methodPath
     * @return
     */
    public static Method getMethodOfKnownDataType(String methodPath) {
        int i = 0;
        Method method = null;

        try {

            String[] methodNames = methodPath.split("/");
            // first class that could contain the method
            Class c = Class.forName(methodNames[0]);
            // get array of method names
            String[] methodName = methodNames[1].split("\\.");

            do {
                // get method with basic datatype and call it if possible
                method = c.getMethod(methodName[i], (Class[]) null);
                String dataType = method.getReturnType().getName();
                if (isBasicDataType(dataType)) {
                    break;
                } else {
                    // get and use the class returned by this method
                    c = method.getReturnType();
                }
                i++;

            } while (i < methodName.length);

            return method;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    /**
     * Returns the class to which a method belongs given the the methods path
     * for example.
     *
     * @param methodPath
     * @return
     */
    public static Class getClass(String methodPath) {
        int i = 0;
        Method method;
        Class c;

        try {

            String[] methodNames = methodPath.split("/");
            // first class that could contain the method
            c = Class.forName(methodNames[0]);
            // get array of method names
            String[] methodName = methodNames[1].split("\\.");

            do {
                // get method with basic datatype and call it if possible
                method = c.getMethod(methodName[i], (Class[]) null);
                String dataType = method.getReturnType().getName();
                if (isBasicDataType(dataType)) {
                    return c;
                } else {
                    // get and use the class returned by this method
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
        Class c;
        Method m;

        // split the path into class and methods string
        String[] path = methodPath.split("/");
        // get array of method names
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

    /**
     * Creates a new client object given an AccPac client object
     *
     * @param apcustomer
     * @return
     */
    public static Client createClient(AccPacCustomer apcustomer) {
        Client client = new Client();

        return client;
    }

    public static Long saveBusinessEntity(EntityManager em, BusinessEntity businessEntity) {

        try {
            if (businessEntity.getId() != null) {
                em.merge(businessEntity);
            } else {
                em.persist(businessEntity);
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
            System.out.println(e);
        }
    }

    public static Date createDate(int year, int monthIndex, int day) {
        Calendar c;

        c = Calendar.getInstance();        
        c.clear();
        c.set(year, monthIndex, day, 0, 0, 0);

        return c.getTime();
    }

    public static Date createDate(Date date) {
        Calendar c;

        c = Calendar.getInstance();
        c.clear();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static int getYearFromDate(Date date) {
        Calendar c;

        // current time and date
        c = Calendar.getInstance();
        c.setTime(date);

        return c.get(Calendar.YEAR);
    }

    public static Date getStartOfCurrentYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfCurrentYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR), Calendar.DECEMBER, 31, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getStartOfCurrentMonth() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR), current.get(Calendar.MONTH), 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getStartOfCurrentMonthPreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR) - 1, current.get(Calendar.MONTH), 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getStartOfMonthInCurrentYear(int monthIndex) {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR), monthIndex, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfCurrentMonth() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR), current.get(Calendar.MONTH), getDaysInMonth(current.get(Calendar.MONTH)), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfCurrentMonthPreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR) - 1, current.get(Calendar.MONTH), getDaysInMonth(current.get(Calendar.MONTH)), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getThisDatePreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR) - 1, current.get(Calendar.MONTH), getDaysInMonth(current.get(Calendar.MONTH)), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfMonthInCurrentYear(int monthIndex) {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR), monthIndex, getDaysInMonth(monthIndex), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * Get the number of days in a month using the current year
     *
     * @param month
     * @return
     */
    public static int getDaysInMonth(int month) {
        Calendar current = Calendar.getInstance();

        switch (month) {
            case Calendar.JANUARY:
                return 31;
            case Calendar.FEBRUARY:
                int currentYear = current.get(Calendar.YEAR);
                if ((currentYear % 4) == 0) { // leap year?
                    return 29;
                } else {
                    return 28;
                }
            case Calendar.MARCH:
                return 31;
            case Calendar.APRIL:
                return 30;
            case Calendar.MAY:
                return 31;
            case Calendar.JUNE:
                return 30;
            case Calendar.JULY:
                return 31;
            case Calendar.AUGUST:
                return 31;
            case Calendar.SEPTEMBER:
                return 30;
            case Calendar.OCTOBER:
                return 31;
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.DECEMBER:
                return 31;
            default:
                return -1;
        }
    }

    /**
     * getFourDigitNumberString Pad number with leading zeros if the number is
     * less than four digits
     *
     * @param number
     * @return
     */
    public static String getFourDigitString(long number) {
        String fourDigitString = "";

        if ((number >= 0L) && (number <= 9L)) {
            fourDigitString = "000" + number;
        }
        if ((number >= 10L) && (number <= 99L)) {
            fourDigitString = "00" + number;
        }
        if ((number >= 100L) && (number <= 999L)) {
            fourDigitString = "0" + number;
        }
        if (number >= 1000L) {
            fourDigitString = "" + number;
        }
        return fourDigitString;
    }

    public static String getDateString(Date d, String delim, String format, String sep) {
        if (d != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return getDateString(c, delim, format, sep);
        } else {
            return "";
        }
    }
    
    public static String getDateInMediumDateFormat(Date date) {
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
    }

    public static String getUserDefinedDateFormat(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);

        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
    }

    public static String getDateInMediumDateAndTimeFormat(Date date) {
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, h:mm a");

        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
    }

    public static String getDateString(Calendar c, String delim, String format, String sep) {
        // date delimiter
        if (delim == null) {
            delim = "'";
        }
        // date format, YMD, MDY etc
        if (format == null) {
            format = "YMD";
        }
        // separator of date fields
        if (sep == null) {
            sep = "-";
        }

        if (c != null) {
            String str;
            String year = getFourDigitString(c.get(Calendar.YEAR));
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            if (format.equals("YMD")) {
                str = delim + year + sep + month + sep + day + delim;
            } else if (format.equals("MDY")) {
                str = delim + month + sep + day + sep + year + delim;
            } else if (format.equals("DMY")) {
                str = delim + day + sep + month + sep + year + delim;
            } else {
                str = delim + year + sep + month + sep + day + delim;
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

        return getDateString(c, delim, format, sep);
    }

    public static String getDateStringFromDate(
            Date d,
            String delim,
            String format,
            String sep) {
        if (d != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return getDateString(c, delim, format, sep);
        } else {
            return "";
        }
    }

    /**
     * Returns a date given an integer representation of a date in the form
     * YYYYMMDD
     *
     * @param dateInt
     * @return
     */
    public static Date getDateFromInt(int dateInt) {
        Calendar c = Calendar.getInstance();
        String dateString = "" + dateInt;

        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(4, 6));
        int day = Integer.parseInt(dateString.substring(6, 8));

        c.set(year, month - 1, day);
        return c.getTime();
    }

    /**
     * Returns an integer representation of a date in the form YYYYMMDD given a
     * Date object.
     *
     * @param date
     * @return
     */
    public static int getIntFromDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        String yearStr = String.format("%04d", c.get(Calendar.YEAR));
        String monthStr = String.format("%02d", c.get(Calendar.MONTH) + 1);
        String dayStr = String.format("%02d", c.get(Calendar.DAY_OF_MONTH));

        return new Integer(yearStr + monthStr + dayStr);
    }

    public static int getPreviousYear() {
        Calendar c;

        // current time and date
        c = Calendar.getInstance();

        return c.get(Calendar.YEAR) - 1;
    }

    public static int getCurrentYear() {
        
        Calendar c = Calendar.getInstance();

        return c.get(Calendar.YEAR);
    }
    
    public static int getNextYear() {
        
        Calendar c = Calendar.getInstance();

        return c.get(Calendar.YEAR) + 1;
    }

    public static Date getStartOfPreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR) - 1, Calendar.JANUARY, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfPreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 31, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getStartOfLastMonth() {

        Date date = getStartOfCurrentMonth();

        return BusinessEntityUtils.adjustDate(date, Calendar.MONTH, -1);

    }

    public static Date getEndOfLastMonth() {
        
        Calendar c = Calendar.getInstance();

        Date date = getStartOfLastMonth();
        c.setTime(date);

        int month = c.get(Calendar.MONTH);

        return BusinessEntityUtils.adjustDate(date, Calendar.DAY_OF_MONTH, getDaysInMonth(month) - 1);
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

    public static Date adjustDate(Date date, int datePart, int amount) {
        Date adjustedDate = createDate(date);

        Calendar c = Calendar.getInstance();
        c.setTime(adjustedDate);
        c.add(datePart, amount);

        return c.getTime();
    }

    public static int calculatePeriodInWorkingDays(Date startDate, Date endDate) {
        int workDays = 0;

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(createDate(startDate));

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(createDate(endDate));

        // return 0 if end date is earlier that start date
        // inc days if working days is a weekend
        if (startCal.after(endCal)) {
            return 0;
        } else if (startCal.equals(endCal)) {
            return 1;
        } else if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            workDays++;
        }

        // count days excluding sat. and sun.
        while (startCal.before(endCal)) {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                workDays++;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return workDays;
    }

    // tk put this method in DatePerio class as a static or done away with the method
    // and use the periods in selectedReport??
    public static DatePeriod[] getMonthlyReportDatePeriods(DatePeriod reportingPeriod) {

        // previous reporting period
        DatePeriod previousReportingPeriod
                = new DatePeriod("Previous reporting period", "month", null, null,
                        BusinessEntityUtils.adjustDate(reportingPeriod.getStartDate(), Calendar.MONTH, -1),
                        BusinessEntityUtils.adjustDate(reportingPeriod.getEndDate(), Calendar.MONTH, -1),
                        false, false, true);

        // reporting period year to date
        Calendar now = Calendar.getInstance();
        Calendar reportingPeriodEndDateCalendar = Calendar.getInstance();
        reportingPeriodEndDateCalendar.setTime(reportingPeriod.getEndDate());

        int year = reportingPeriodEndDateCalendar.get(Calendar.YEAR);
        int monthIndex = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);

        DatePeriod reportingPeriodYTD = new DatePeriod("Financial year to date",
                "month",
                null,
                null,
                null,
                BusinessEntityUtils.createDate(year, monthIndex, day),
                false, false, true);

        // reporting period last year
        DatePeriod reportingPeriodLastYear = new DatePeriod("Reporting period last year", "year", null, null,
                BusinessEntityUtils.adjustDate(reportingPeriod.getStartDate(), Calendar.YEAR, -1),
                BusinessEntityUtils.adjustDate(reportingPeriod.getEndDate(), Calendar.YEAR, -1),
                false, false, true);

        // Last financial year to date
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

    public static List<String> removeDuplicatesFromStringList(List<String> list) {
//        HashSet<String> hashSet = new HashSet<String>(list);

        //Assign the HashSet to a new ArrayList
//        return new ArrayList<String>(hashSet);
        return list;
    }

    public static Date getModifiedDate(Date orgDate, int modPeriod, int modAmount) {
        Calendar calendar;

        calendar = Calendar.getInstance();
        calendar.setTime(orgDate);
        calendar.add(modPeriod, modAmount);

        return calendar.getTime();
    }

    public static Boolean areDatesEqual(Date date1, Date date2) {
        if (removeTimeFromDate(date1).equals(removeTimeFromDate(date2))) {
            return true;
        } else {
            return false;
        }
    }

    public static Date removeTimeFromDate(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     *
     * @param driverClassName
     * @param url 
     * @param user
     * @param password
     * @return
     */
    public static Connection establishConnection(
            String driverClassName,
            String url,
            String user,
            String password) {

        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
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
            Calendar c,
            int minYear,
            int maxYear) {

        if (c != null) {
            int year = c.get(Calendar.YEAR);
            // just year validation for now
            if ((year < minYear) || (year > maxYear)) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * getAlphaCode Generates code for 0 to 701 (A - ZZ)
     *
     * @param number int
     * @return String
     */
    public static String getAlphaCode(long number) {
        return convertNumberToAlphabet(number);
    }

    /**
     * Convert number to spreadsheet style column names e.g. 1 -> B
     *
     * @param num
     * @return
     */
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

    /**
     * Determines the corresponding index for a maximum sequence of characters.
     * The sequence of characters are those that are obtained from a spreadsheet
     * column heading (eg AR).Currently it works for alphabets up to "ZZ" but
     * falls apart beyond that. Need to fix this!
     *
     * @param alphabet
     * @param len
     * @param num
     * @return
     */
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

   

    public static String getMonthShortFormat(Date date) {
        String month = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        switch (c.get(Calendar.MONTH)) {
            case 0:
                month = "Jan";
                break;
            case 1:
                month = "Feb";
                break;
            case 2:
                month = "Mar";
                break;
            case 3:
                month = "Apr";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "Jun";
                break;
            case 6:
                month = "Jul";
                break;
            case 7:
                month = "Aug";
                break;
            case 8:
                month = "Sep";
                break;
            case 9:
                month = "Oct";
                break;
            case 10:
                month = "Nov";
                break;
            case 11:
                month = "Dec";
                break;
            default:
                month = "";
                break;
        }
        return month;
    }

    public static String getYearShortFormat(Date date, int digits) {
        String yearString = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        yearString = yearString + year;

        // get last x digits of year
        yearString = yearString.substring(yearString.length() - digits, yearString.length());

        return yearString;
    }

    public static String getShortenedString(String string, int maxLength) {
        String shortenedString = "";

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

        return Double.parseDouble(strValue);
    }

    public static long getMediumDateStringAsLong(String dateStr) {
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        try {
            Date date = formatter.parse(dateStr);

            return date.getTime();
        } catch (ParseException ex) {
            System.out.println(ex);
            return 0L;
        }

    }

    /**
     * Delete a generic entity
     *
     * @param em
     * @param entity
     * @return
     */
    public static Boolean deleteEntity(EntityManager em, Object entity) {

        try {
            if (entity != null) {
                em.remove(entity);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }
  
}
