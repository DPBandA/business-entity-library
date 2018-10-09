/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jm.com.dpbennett.business.entity.swingutils;

import java.awt.Component;
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

        // tk - not working as expected. Fix!
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        if (value != null) {
            jTextArea.setText(value.toString());
        }

        return jTextArea;
    }
}
