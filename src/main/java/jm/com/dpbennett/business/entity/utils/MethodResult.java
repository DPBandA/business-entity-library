/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.utils;

/**
 *
 * @author desbenn
 */
public class MethodResult {
    private final boolean success;
    private final String message;

    public MethodResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public MethodResult() {
        this.success = true;
        this.message = "";
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }    
    
  }
