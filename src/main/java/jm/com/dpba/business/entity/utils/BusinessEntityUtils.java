/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity.utils;

import javax.persistence.EntityManager;
import jm.com.dpba.business.entity.BusinessEntity;

/**
 *
 * @author desbenn
 */
public class BusinessEntityUtils {
     public static Long saveBusinessEntity(EntityManager em, BusinessEntity businessEntity) {

        if (businessEntity.getId() != null) {
            em.merge(businessEntity);
        } else {
            em.persist(businessEntity);
        }

        return businessEntity.getId();
    }    
}
