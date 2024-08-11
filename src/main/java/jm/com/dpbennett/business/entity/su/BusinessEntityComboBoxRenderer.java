/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2024  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.su;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at https://dpbennett.com.jm>
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
