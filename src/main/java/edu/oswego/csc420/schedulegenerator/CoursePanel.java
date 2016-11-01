package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class CoursePanel extends JPanel {

    CoursePanel() {
        setLayout(new MigLayout("","[grow,fill]","[][][grow,fill]"));
        add(new JLabel("Name"));
        add(new JLabel("Subject"));
        add(new JLabel("Course Number"));
        add(new JLabel("Optional"), "wrap");
        add(new JTextField());
        add(new JTextField());
        add(new JTextField());
        add(new JCheckBox(), "wrap");
        add(new SectionPanel(), "span 2");
        add(new SectionInformationPanel(), "span 2");
    }
}
