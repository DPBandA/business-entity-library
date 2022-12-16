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
package jm.com.dpbennett.business.entity.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.fm.Inventory;
import jm.com.dpbennett.business.entity.fm.MarketProduct;
import jm.com.dpbennett.business.entity.hrm.Department;
import jm.com.dpbennett.business.entity.hrm.Employee;
import jm.com.dpbennett.business.entity.pm.PurchaseRequisition;
import jm.com.dpbennett.business.entity.pm.Supplier;
import jm.com.dpbennett.business.entity.sm.Category;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett
 */
public class EntityTest {

//    @Test
//    public void testEntity() {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
//        EntityManager em = emf.createEntityManager();
//
//        Inventory inventory = em.find(Inventory.class, 1957701L);
//        System.out.println("Inventory found: " + inventory);
//
//        // Create and save inventory
//        System.out.println("Create and save inventory...");
//        Inventory invtry;
//        invtry = new Inventory();
//
//        invtry.setName("Test");
//        invtry.setType("None");
//        invtry.setCategory(em.find(Category.class, 1724203L));
//        invtry.setStockKeepingUnit("001");
//        invtry.setMeasurementUnit("each");
//        invtry.setValuationMethod("FIFO");
//        invtry.setProduct(em.find(MarketProduct.class, 1738815L));
//        invtry.setSupplier(em.find(Supplier.class, 1613313L));
//        invtry.setEnteredBy(em.find(Employee.class, 46L));
//
//        //invtry.save(em);
//
//    }
}
