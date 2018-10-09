/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.swingutils;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class BusinessEntityCellRenderer extends DefaultListCellRenderer {

    private int rows;
    private int columns;

    public BusinessEntityCellRenderer(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

            JTextArea jTextArea = new JTextArea(rows, columns);
            jTextArea.setLineWrap(true);
                       
            
            if (value != null) {
                jTextArea.setText(value.toString());
            }
            
            return jTextArea;
      
    }
}
