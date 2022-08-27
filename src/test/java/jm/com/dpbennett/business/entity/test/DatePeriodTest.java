/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2019  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.test;

import java.util.Calendar;
import java.util.Date;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class DatePeriodTest {

    @Test
    public void generateDatePeriods() {
        Date now = new Date();
        System.out.println("now: " + now);
        
        
//        DatePeriod dp = new DatePeriod();
//
//        dp.setName("This financial year");
//        dp.initDatePeriod(/*BusinessEntityUtils.createDate(2019, 0, 31)*/new Date());
//
//        System.out.println("Start date: " + dp.getStartDate());
//        System.out.println("End date: " + dp.getEndDate());
//          System.out.println("Adjusted date: " + 
//                  BusinessEntityUtils.adjustDate(new Date(), Calendar.DAY_OF_MONTH, 10));  
    }
}
