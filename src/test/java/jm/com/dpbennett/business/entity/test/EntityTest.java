/*
Report Manager (RM) - Reporting module. 
Copyright (C) 2022  D P Bennett & Associates Limited

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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.hrm.Division;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.sm.Notification;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett
 */
public class EntityTest {

    @Test
    public void testEntity() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();

        Department department = em.find(Department.class, 26L);
        System.out.println("Department: " + department.getName());
        
        Employee head = Division.findHeadOfActiveDivistionByDepartment(em, department);
        if ( head != null) {
             System.out.println("The head of a division that has the department " + department.getName() + " is " +
                            head.toString());
        }
        else {
            System.out.println("A division head was not found!");
        }

    }
}
