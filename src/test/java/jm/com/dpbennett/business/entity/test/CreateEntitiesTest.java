/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.sc.MarketProduct;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class CreateEntitiesTest {

    @Test
    public void test() {
        
        Calendar c = Calendar.getInstance();
        String departmentOrCompanyCode;
        String month;
        String day;
        String hour;
        String min;
        String sec;

        c.setTime(new Date());
        // Year
        String year = "" + c.get(Calendar.YEAR);
        year = year.substring(year.length() - 2, year.length());
        // Month        
        int month_int = c.get(Calendar.MONTH) + 1;
        if (month_int < 10) {
            month = "0" + month_int;
        } else {
            month = "" + month_int;
        }
        // Day
        int day_int = c.get(Calendar.DAY_OF_MONTH);
        if (day_int < 10) {
            day = "0" + day_int;
        } else {
            day = "" + day_int;
        }
        // Hour
        int hour_int = c.get(Calendar.HOUR_OF_DAY); 
        if (hour_int < 10) {
            hour = "0" + hour_int;
        } else {
            hour = "" + hour_int;
        }
        // Min
        int min_int = c.get(Calendar.MINUTE); 
        if (min_int < 10) {
            min = "0" + min_int;
        } else {
            min = "" + min_int;
        }
        // Min
        int sec_int = c.get(Calendar.SECOND); 
        if (sec_int < 10) {
            sec = "0" + sec_int;
        } else {
            sec = "" + sec_int;
        }

        System.out.println(year + month + day + " - " + hour + min + sec);

    }
}
