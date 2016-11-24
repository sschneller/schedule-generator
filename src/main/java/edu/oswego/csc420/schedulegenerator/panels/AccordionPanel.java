package edu.oswego.csc420.schedulegenerator.panels;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class AccordionPanel extends JPanel {
    CourseHeaderListPanel chlp = new CourseHeaderListPanel();
    SwitchViewPanel svp = new SwitchViewPanel();

    AccordionPanel() {
        setLayout(new MigLayout("","[grow,fill]","[grow,fill][]"));
        add(chlp, "wrap");
        add(svp);
    }
}
