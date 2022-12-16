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
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.hrm.User;
import jm.com.dpbennett.business.entity.sm.LdapContext;
import jm.com.dpbennett.business.entity.util.Security;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett
 */
public class LDAPTest {

//    @Test
//    public void testLDAP() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
//        EntityManager em = emf.createEntityManager();
//        LdapContext ldap = LdapContext.findActiveLdapContextByName(em, "LDAP");

//        if (LdapContext.addUser(em, "LDAP", "mbennett", 
//                Security.encrypt("123456789"), 
//                "Milton",
//                "Bennett")) {
//            System.out.println("User Added!");
//        }
//        if (LdapContext.authenticateUser(em, ldap, "mbennett", "")) {
//            System.out.println("User authenticated!");
//        }
//        else {
//            System.out.println("User NOT authenticated");
//        }
//        if (LdapContext.updateUserPassword(em, ldap, "mbennett", "")) {
//            System.out.println("Password updated!");
//        } else {
//            System.out.println("Password NOT updated!");
//        }
//        User user = new User();
//        Employee emp = new Employee("Milton", "Bennett");
//        user.setUsername("mbennett");
//        user.setEmployee(emp);
//        if (LdapContext.updateUser(em, ldap, user)) {
//            System.out.println("User updated!");
//        } else {
//            System.out.println("User NOT updated!");
//        }

//    }
}
