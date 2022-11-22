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
package jm.com.dpbennett.business.entity.test;

import javax.naming.directory.DirContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.fm.Inventory;
import jm.com.dpbennett.business.entity.sm.LdapContext;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett
 */
public class LDAPTest {

    @Test
    public void testLDAP() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        
        //LdapContext.addUser(em, "mbennett", "Milton", "Bennett", "milton@dpbennett.com.jm");
        
//        DirContext dc = LdapContext.getConnection(em, "LDAP");
//
//        if (dc != null) {
//            System.out.println("Connection made!");
//        }
//        else {
//            System.out.println("Connection failed!");
//        }
    }
}
