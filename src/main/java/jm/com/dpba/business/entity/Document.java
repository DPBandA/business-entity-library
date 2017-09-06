/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpba.business.entity;

/**
 *
 * @author dbennett
 */
public interface Document {

    public String getDescription();

    public void setDescription(String description);

    public String getNotes();

    public void setNotes(String notes);

    public String getNumber();

    public void setNumber(String number);

    public DocumentType getType();

    public void setType(DocumentType type);

    public Classification getClassification();
    
    public void setClassification(Classification classification);

    public String getUrl();

    public void setUrl(String url);
}
