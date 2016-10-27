package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.Color;

public class AccordionPanel extends JPanel {

    AccordionPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new MigLayout("insets 0","[grow][]","[][grow,fill]"));
        add(new JLabel("CSC 420", SwingConstants.CENTER), "al c, cell 0 0");
        add(new JButton("-"), "al r, cell 1 0");

    }
}
