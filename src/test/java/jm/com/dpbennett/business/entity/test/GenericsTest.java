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
package jm.com.dpbennett.business.entity.test;

import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett
 */
public class GenericsTest {

    private static final Logger LOG = Logger.getLogger(GenericsTest.class.getName());

    public static Logger getLOG() {
        return LOG;
    }

    @Test
    public void testEntity() {

        // Create a Gen reference for Integers.
        Gen<Integer> iOb;
        // Create a Gen<Integer> object and assign its
        // reference to iOb. Notice the use of autoboxing
        // to encapsulate the value 88 within an Integer object.
        iOb = new Gen<>(88);
        // Show the type of data used by iOb.
        iOb.showType();
        // Get the value in iOb. Notice that
        // no cast is needed.
        int v = iOb.getob();
        System.out.println("value: " + v);
        System.out.println();

        // Create a Gen object for Strings.
        Gen<String> strOb = new Gen<>("Generics Test");
        // Show the type of data used by strOb.
        strOb.showType();
        // Get the value of strOb. Again, notice
        // that no cast is needed.
        String str = strOb.getob();
        System.out.println("value: " + str);

    }

}

class Gen<T> {

    T ob; // declare an object of type T
    // Pass the constructor a reference to
    // an object of type T.

    Gen(T o) {
        ob = o;
    }

    // Return ob.
    T getob() {
        return ob;
    }

    // Show type of T.
    void showType() {
        System.out.println("Type of T is "
                + ob.getClass().getName());
    }

}
