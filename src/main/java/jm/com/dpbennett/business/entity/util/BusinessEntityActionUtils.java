/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.util;

import java.util.List;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.BusinessEntity.Action;

/**
 *
 * @author dbennett
 */
public class BusinessEntityActionUtils {

    public static void addAction(Action action, List<Action> actions) {

        if (findAction(action, actions) == null) {
            actions.add(action);
        }

    }

    public static Action findAction(BusinessEntity.Action action, List<Action> actions) {
        for (Action existingAction : actions) {
            if (existingAction == action) {
                return action;
            }
        }

        return null;
    }

    public static void removeAction(BusinessEntity.Action action, List<Action> actions) {
        actions.remove(action);
    }
}
