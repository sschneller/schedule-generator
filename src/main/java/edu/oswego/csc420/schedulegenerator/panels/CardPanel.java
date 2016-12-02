package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Generator;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    AccordionPanel ap;
    JPanel courseOverview = new JPanel();
    CardLayout cl = new CardLayout();

    public CardPanel(Generator gen) {
        ap = new AccordionPanel(gen);
        setLayout(cl);
        add(ap, "ACC");
        add(courseOverview, "EXP");
        cl.show(this, "ACC");
    }
}