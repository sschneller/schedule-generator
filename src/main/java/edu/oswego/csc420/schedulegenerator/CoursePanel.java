package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class CoursePanel extends JPanel {
    public CourseEntry courseEntry;
    public CourseInfoPanel courseInfoPanel;
    public int courseEntryIndex;

    CoursePanel(CourseEntry ce, CourseInfoPanel cip, int cei) {
        setLayout(new MigLayout("","[grow,fill]","[grow,fill][grow,fill]"));
        courseEntry = ce;
        courseInfoPanel = cip;
        courseEntryIndex = cei;
        add(courseEntry, "wrap");
        add(courseInfoPanel);
    }

    public void setTitle(String title) {
        courseEntry.setTitle(title);
    }
}
