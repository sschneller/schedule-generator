package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AccordionPanel extends JPanel {
    ArrayList<String> data;
    ArrayList<CourseEntry> courseList;
    AccordionPanel() {
        setBackground(Color.WHITE);
        setLayout(new MigLayout("insets 0","[grow,fill][]","[][]"));
        data = new ArrayList<>();
        data.add("CSC212");
        data.add("CSC420");
        data.add("CSC480");
        data.add("CSC380");
        data.add("CSC365");
        data.add("CSC344");
        data.add("CSC221");
        data.add("CSC222");
        courseList = new ArrayList<>();
        for(String title : data) {
            CourseEntry ce = new CourseEntry(title, true);
            courseList.add(ce);
        }

        for(JPanel course : courseList) {
            add(course, "span");
        }
        // add(new JLabel("CSC 420", SwingConstants.CENTER), "al c, cell 0 0");
        // add(new JButton("-"), "al r, cell 1 0");
    }

    public void addCourse() {
        CourseEntry ce = new CourseEntry("", false);
        courseList.add(ce);
    }
}
