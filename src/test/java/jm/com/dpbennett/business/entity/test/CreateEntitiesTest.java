/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.test;

import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.dm.Attachment;
import jm.com.dpbennett.business.entity.Currency;
import jm.com.dpbennett.business.entity.PurchaseRequisition;
import org.junit.Test;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class CreateEntitiesTest {
    @Test
    public void test() {
        HashMap prop = new HashMap();

        prop.put("javax.persistence.jdbc.user",
                "root");
        prop.put("javax.persistence.jdbc.password",
                "bsj0001"); // NB: REMOVE PWD WHEN DONE
        prop.put("javax.persistence.jdbc.url",
                "jdbc:mysql://172.16.0.10:3306/jmts");
        prop.put("javax.persistence.jdbc.driver",
                "com.mysql.jdbc.Driver");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU", prop);
        EntityManager em = emf.createEntityManager();

       // These instantiations cause the respective database tables to be created
       // if they don't already exist.
       // NB: Set <skipTests>true</skipTests> when done.
       PurchaseRequisition pr = new PurchaseRequisition();

    }
}
