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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.EnergyLabel;

/**
 *
 * @author Desmond Bennett <info@dpbennett.com.jm at http//dpbennett.com.jm>
 */
public class SwingUtils {

    public static BusinessEntityComboBoxModel getBusinessEntityComboBoxModel(
            JComboBox comboBox, ArrayList<BusinessEntity> data, int rows,
            int columns) {

        BusinessEntityComboBoxModel model = new BusinessEntityComboBoxModel(data);
        comboBox.setRenderer(new BusinessEntityComboBoxRenderer(rows, columns));

        return model;
    }

    public static void main(String[] a) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIDefaults uiDefaults = UIManager.getDefaults();
            Enumeration enum1 = uiDefaults.keys();
            while (enum1.hasMoreElements()) {
                Object key
                        = enum1.nextElement();
                Object val = uiDefaults.get(key);
                System.out.println("[" + key.toString() + "]:["
                        + (null != val ? val.toString() : "(null)")
                        + "]");
            }

        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Model
        ArrayList<BusinessEntity> data = new ArrayList<>();
        data.add(new EnergyLabel(1L, "Iya!!"));
        data.add(new EnergyLabel(2L, "Seen!!"));
        EnergyLabel yes = new EnergyLabel(3L, "Yes!!");
        data.add(yes);

        JComboBox cbox = new JComboBox();
        cbox.setModel(SwingUtils.getBusinessEntityComboBoxModel(cbox, data, 4, 1));

        cbox.setMaximumRowCount(3);
        frame.add(cbox);

        frame.setSize(600, 50);
        frame.setVisible(true);
    }

}
