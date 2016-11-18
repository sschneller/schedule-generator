package edu.oswego.csc420.schedulegenerator;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    AccordionPanel ap = new AccordionPanel();
    JPanel courseOverview = new JPanel();
    CardLayout cl = new CardLayout();

    CardPanel() {
        setLayout(cl);
        add(ap, "ACC");
        add(courseOverview, "EXP");
        cl.show(this, "ACC");
    }
}