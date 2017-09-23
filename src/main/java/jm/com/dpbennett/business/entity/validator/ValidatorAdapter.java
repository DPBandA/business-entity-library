/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desbenn
 */
public class ValidatorAdapter implements Validator {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ValidatorAdapter() {
        emf = Persistence.createEntityManagerFactory("JMTSPU"); // tk get from bundle       
        em = emf.createEntityManager();
    }
    
    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
    }
    
}
