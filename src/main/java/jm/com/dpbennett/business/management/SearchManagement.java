/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.management;

import jm.com.dpbennett.business.utils.SearchParameters;


/**
 *
 * @author dbennett
 */
public interface SearchManagement {

    public SearchParameters getCurrentSearchParameters();

    public String getCurrentSearchParameterKey();

    public void setCurrentSearchParameterKey(String currentSearchParameterKey);
}
