/*
Business Entity Library (BEL) 
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
package jm.com.dpbennett.business.entity.utils;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.im.Inventory;
import jm.com.dpbennett.business.entity.im.InventoryDisbursement;
import jm.com.dpbennett.business.entity.im.InventoryRequisition;
import jm.com.dpbennett.business.entity.hrm.Employee;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett
 */
public class CreateInventoryRequisition {

//    @Test
//    public void create() {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
//        EntityManager em = emf.createEntityManager();
//
//        System.out.println("Create and save inventory requisition...");
//        InventoryRequisition inventoryRequisition = new InventoryRequisition();
//        inventoryRequisition.setName("InventoryRequisition Test Name");
//        inventoryRequisition.setCode("000123");
//        inventoryRequisition.setType("Test Type");
//        inventoryRequisition.getInventoryDisbursements().
//                add(em.find(InventoryDisbursement.class, 1958051L));
//        inventoryRequisition.setDateEntered(new Date());
//        inventoryRequisition.setDateEdited(new Date());
//        inventoryRequisition.setEnteredBy(em.find(Employee.class, 46L));
//        inventoryRequisition.setEditedBy(em.find(Employee.class, 46L));
//        
//        inventoryRequisition.save(em);
//
//    }
}
