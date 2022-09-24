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
import jm.com.dpbennett.business.entity.fm.Inventory;
import jm.com.dpbennett.business.entity.fm.InventoryDisbursement;
import jm.com.dpbennett.business.entity.hrm.Employee;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett
 */
public class CreateInventoryDisbursement {

    @Test
    public void create() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();

        Inventory inv = em.find(Inventory.class, 1957701L);

        // Create and save inventory disbursement
        System.out.println("Create and save inventory disbursement...");
        InventoryDisbursement inventoryDisbursement;
        inventoryDisbursement = new InventoryDisbursement();
        inventoryDisbursement.setInventory(inv);
        inventoryDisbursement.setName("Test");
        inventoryDisbursement.setType("None");
        inventoryDisbursement.setQuantityOrdered(1.0);
        inventoryDisbursement.setQuantityReceived(1.0);
        inventoryDisbursement.setDateOrdered(new Date());
        inventoryDisbursement.setDateReceived(new Date());
        if (!inv.getAllSortedCostComponents().isEmpty()) {
            inventoryDisbursement.setUnitCost(inv.getAllSortedCostComponents().get(0).getRate());
        } else {
            inventoryDisbursement.setUnitCost(0.0);
        }
        inventoryDisbursement.setCost(inventoryDisbursement.getQuantityOrdered()
                * inventoryDisbursement.getUnitCost());
        inventoryDisbursement.setDateEntered(new Date());
        inventoryDisbursement.setDateEdited(new Date());
        inventoryDisbursement.setEnteredBy(em.find(Employee.class, 46L));
        inventoryDisbursement.setEditedBy(em.find(Employee.class, 46L));

        inventoryDisbursement.save(em);

    }
}
