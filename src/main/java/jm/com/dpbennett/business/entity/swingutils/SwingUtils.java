/*
LabelPrint - A general purpose energy label printing application
Copyright (C) 2018  D P Bennett & Associates Limited

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
package jm.com.dpbennett.business.entity.swingutils;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.EnergyLabel;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class SwingUtils {

    public static void main(String[] a) {
      
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Model
        ArrayList<BusinessEntity> data = new ArrayList<>();
        data.add(new EnergyLabel(1L, "Iya!!"));
        data.add(new EnergyLabel(2L, "Seen!! 4o3i4e3o4io3i4o 3io4 4o3 o4io3i4 oi34o3io 4o3i4o3io4 io3i4o3io4 o3i4o3i 4o3o4i3oi4o3i o4i3o"));
        EnergyLabel yes = new EnergyLabel(3L, "Yes!!");
        data.add(yes);

        JComboBox cbox = new JComboBox(new BusinessEntityComboBoxModel(data));
        cbox.setRenderer(new BusinessEntityComboBoxRenderer(4, 4));

        cbox.setMaximumRowCount(3);
        frame.add(cbox);

        frame.setSize(600, 50);
        frame.setVisible(true);
    }

}




