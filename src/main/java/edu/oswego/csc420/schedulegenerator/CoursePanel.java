package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class CoursePanel extends JPanel {
    public CourseHeaderPanel courseHeaderPanel;
    public CourseInfoPanel courseInfoPanel;

    CoursePanel(CourseHeaderPanel chp, CourseInfoPanel cip) {
        setLayout(new MigLayout("","[grow,fill]","[grow,fill][grow,fill]"));
        courseHeaderPanel = chp;
        courseInfoPanel = cip;
        add(courseHeaderPanel, "wrap");
        add(courseInfoPanel);
        courseHeaderPanel.icb.setEnabled(false);
    }

    public void setTitle(String title) {
        courseHeaderPanel.setTitle(title);
    }
}
