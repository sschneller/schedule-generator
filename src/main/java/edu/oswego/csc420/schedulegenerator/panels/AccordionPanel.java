package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Generator;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class AccordionPanel extends JPanel {
    CourseHeaderListPanel chlp = new CourseHeaderListPanel();
    SwitchViewPanel svp;

    AccordionPanel(Generator gen) {
        svp = new SwitchViewPanel(gen);
        setLayout(new MigLayout("","[grow,fill]","[grow,fill][]"));
        add(chlp, "wrap");
        add(svp);
    }
}
