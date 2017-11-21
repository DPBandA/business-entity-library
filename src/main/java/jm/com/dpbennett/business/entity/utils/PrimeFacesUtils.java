/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.utils;

import java.util.HashMap;
import java.util.Map;
import org.primefaces.context.RequestContext;

/**
 *
 * @author D P Bennett
 */
public class PrimeFacesUtils {

    public static void openDialog(Object entity,
            String outcome,
            Boolean modal,
            Boolean draggable,
            Boolean resizable,
            Integer contentHeight,
            Integer contentWidth) {

        Map<String, Object> options = new HashMap<>();
        options.put("modal", modal);
        options.put("draggable", draggable);
        options.put("resizable", resizable);
        options.put("contentHeight", contentHeight);
        options.put("contentWidth", contentWidth);

        RequestContext.getCurrentInstance().openDialog(outcome, options, null);
    }
}