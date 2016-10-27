package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sschneller on 10/27/2016.
 */
public class CourseEntry extends JPanel {
    CourseEntry(String courseLabel) {
        setLayout(new MigLayout("","[grow,fill][]",""));
        setBackground(Color.LIGHT_GRAY);
        add(new JButton("+"), "dock east");
        add(new JLabel(courseLabel, SwingConstants.CENTER), "dock north");
    }
}
