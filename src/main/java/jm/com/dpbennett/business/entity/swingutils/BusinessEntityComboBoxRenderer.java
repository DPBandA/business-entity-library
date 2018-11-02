/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.swingutils;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class BusinessEntityComboBoxRenderer extends JLabel
        implements ListCellRenderer {
    
    private int rows;
    private int columns;

    public BusinessEntityComboBoxRenderer(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }    

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    
        setBorder(new EmptyBorder(0, 5, 0, 0));
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
              
        if (value != null) {
            setText(value.toString());
        }

        BusinessEntityCellRenderer cellRenderer = new BusinessEntityCellRenderer(rows, columns);
        list.setCellRenderer(cellRenderer);
        
        
        return this;
    }
    
}
