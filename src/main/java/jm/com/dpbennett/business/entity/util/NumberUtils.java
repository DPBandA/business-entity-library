/*
Business Entity Library (BEL) 
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

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * This encapsulates various utilities for dealing with numbers.
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at https//dpbennett.com.jm>
 */
public class NumberUtils {

    public static String formatAsCurrency(Double number, String symbol) {
        DecimalFormat formatter = new DecimalFormat("#,###.##;(#,###.##)");

        try {

            return symbol + formatter.format(number);
        } catch (NumberFormatException e) {
            System.out.println(e);

            return "";
        }
    }

    public static ReturnMessage validateDoubleValue(String value) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");

        try {

            formatter.parse(value);

            return new ReturnMessage();
        } catch (NumberFormatException | ParseException e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Double value is invalid");
    }

    public static double getDoubleValue(String value) {
        try {
            return Double.parseDouble(value);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        return 0.0;
    }

    public static ReturnMessage validateIntegerValue(String value) {

        try {

            Integer.valueOf(value);

            return new ReturnMessage();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        return new ReturnMessage(false, "Integer value is invalid");
    }

}
