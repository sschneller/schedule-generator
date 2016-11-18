package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class CourseInfoPanel extends JPanel {

    CourseInfoPanel() {
        setLayout(new MigLayout("","[][]","[grow,fill][grow,fill]"));
        add(new CourseInfoEntryPanel(), "span, growx, wrap 2");
        add(new SectionPanel());
        add(new SectionInformationPanel());
    }
}
