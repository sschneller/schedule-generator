package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AccordionPanel extends JPanel {
    ArrayList<CourseEntry> courseList = new ArrayList<>();

    AccordionPanel() {
        setBackground(Color.WHITE);
        setLayout(new MigLayout("insets 0, debug","[grow,fill]","[]"));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Added Classes"), BorderFactory.createEmptyBorder(5,5,5,5)));
    }

    public int addCourse(CourseEntry c) {
        courseList.add(c);
        // add(c, "span");
        return courseList.indexOf(c);
    }

    public void updateCourse(int i, CourseEntry c) {
        courseList.set(i, c);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Component cp : this.getComponents()) {
            if(cp instanceof CourseEntry) {
                this.remove(cp);
            }
        }
        for (CourseEntry c : courseList) {
            add(c);
        }
    }
}
