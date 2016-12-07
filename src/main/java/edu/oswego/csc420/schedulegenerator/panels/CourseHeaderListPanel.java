package edu.oswego.csc420.schedulegenerator.panels;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CourseHeaderListPanel extends JPanel {
    HashMap<CourseHeaderPanel, CoursePanel> courses = new HashMap<>();

    CourseHeaderListPanel() {
        setBackground(Color.WHITE);
        setLayout(new MigLayout("insets 0","[grow,fill]","[]"));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Added Classes"), BorderFactory.createEmptyBorder(5,5,5,5)));
    }

    public void putCoursePanel(CourseHeaderPanel chp, CoursePanel cp) {
        courses.put(chp, cp);
    }

    public CoursePanel getCoursePanel(CourseHeaderPanel chp) {
        CoursePanel cp = courses.get(chp);
        cp.removeAll();
        cp.courseHeaderPanel.setCollapsed(false);
        cp.add(cp.courseHeaderPanel, "span");
        cp.add(cp.courseInfoPanel);
        cp.repaint();
        return cp;
    }
}