/*
LabelPrint - A general purpose energy label printing application 
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
package jm.com.dpbennett.business.entity.utils;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * This encapsulates various utilities for dealing with numbers.
 * 
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class NumberUtils {
    
    /**
     * This is a utility method used to validate a double value string.
     *
     * @param value
     * @return
     */
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
    
}
