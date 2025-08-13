/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2025  D P Bennett & Associates Limited

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
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at https://dpbennett.com.jm>
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
