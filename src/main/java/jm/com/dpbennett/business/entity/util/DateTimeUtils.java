/*
Business Entity Library (BEL) - A foundational library.
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * java.time utilities used during and after BEL's migration away from
 * java.util.Date and Calendar.
 */
public final class DateTimeUtils {

    private static final DateTimeFormatter MEDIUM_DATE = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    private static final DateTimeFormatter MEDIUM_DATE_TIME = DateTimeFormatter.ofPattern("MMM dd, yyyy, h:mm a");

    private DateTimeUtils() {
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * Creates a date using BEL's historical zero-based month index contract.
     */
    public static LocalDate createDate(int year, int monthIndex, int day) {
        return LocalDate.of(year, monthIndex + 1, day);
    }

    public static boolean isDateWithinPeriod(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
        return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
    }

    public static LocalDate startOfCurrentYear() {
        return LocalDate.now().withDayOfYear(1);
    }

    public static LocalDate endOfCurrentYear() {
        return LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
    }

    public static LocalDate startOfCurrentMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }

    public static LocalDate endOfCurrentMonth() {
        return YearMonth.from(LocalDate.now()).atEndOfMonth();
    }

    public static LocalDate adjustDate(LocalDate date, long amount, ChronoUnit unit) {
        return date.plus(amount, unit);
    }

    public static int calculatePeriodInWorkingDays(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return 0;
        }

        int workDays = 0;
        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            switch (current.getDayOfWeek()) {
                case SATURDAY, SUNDAY -> {
                }
                default -> workDays++;
            }
            current = current.plusDays(1);
        }
        return workDays;
    }

    public static String getDateInMediumDateFormat(LocalDate date) {
        return date == null ? "" : MEDIUM_DATE.format(date);
    }

    public static String getDateInMediumDateAndTimeFormat(LocalDateTime dateTime) {
        return dateTime == null ? "" : MEDIUM_DATE_TIME.format(dateTime);
    }

    public static String getUserDefinedDateFormat(LocalDate date, String format) {
        return date == null ? "" : DateTimeFormatter.ofPattern(format).format(date);
    }

    public static int getIntFromDate(LocalDate date) {
        return date.getYear() * 10000 + date.getMonthValue() * 100 + date.getDayOfMonth();
    }

    public static LocalDate getDateFromInt(int dateInt) {
        String value = String.format("%08d", dateInt);
        return LocalDate.of(
                Integer.parseInt(value.substring(0, 4)),
                Integer.parseInt(value.substring(4, 6)),
                Integer.parseInt(value.substring(6, 8)));
    }
}
