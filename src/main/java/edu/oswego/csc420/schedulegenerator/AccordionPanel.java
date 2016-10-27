package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class AccordionPanel extends JPanel {

    AccordionPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new MigLayout("insets 0","[grow,fill]","[][grow,fill]"));
        add(new JLabel("CSC 420", SwingConstants.CENTER), "align right, wrap");
    }
}
