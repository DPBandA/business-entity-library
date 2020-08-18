/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.util;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author desbenn
 */
public class StringUtils {
    public static List<String> splitString(String string, String itemSep) {
        
        return Arrays.asList(string.split(itemSep));
    }
}
