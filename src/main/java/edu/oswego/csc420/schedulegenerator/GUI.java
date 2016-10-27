package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame implements ActionListener, ComponentListener, ChangeListener {
    ArrayList<String> data;
    ArrayList<CourseEntry> courseList;

    public GUI(String windowTitle) {
        setLayout(new MigLayout("","[grow,fill]",""));
        setTitle(windowTitle);
        addComponentListener(this);
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
            CourseEntry ce = new CourseEntry(title);
            ((JButton)ce.getComponent(0)).addActionListener(this);
            courseList.add(ce);
        }

        for(JPanel course : courseList) {
            add(course, "span");
        }
    }

    public void componentResized(ComponentEvent e) {

    }

    public void componentHidden(ComponentEvent e) {} // EXTRANEOUS
    public void componentMoved(ComponentEvent e) {} // ABSTRACT
    public void componentShown(ComponentEvent e) {} // OVERWRITES

    public void stateChanged(ChangeEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        // JLabel parent = (JLabel)clickedButton.getParent().getComponent(0);
        clickedButton.setText(clickedButton.getText().replace("+", "-"));
        for(JPanel ccp : courseList) {
            this.remove(ccp);
        }
        this.validate();
        this.setTitle("Course Schedule Generator");
        this.setBackground(Color.GRAY);
        this.setLayout(new MigLayout("","[grow,fill][][]300[]","[grow,fill][]"));
        final AccordionPanel accordionPanel = new AccordionPanel();
        accordionPanel.add(new CoursePanel(), "cell 0 1 2 1");
        add(accordionPanel, "span, grow");
        add(new JButton("New Course"));
        add(new JButton("Import"));
        add(new JButton("Export"));
        add(new JButton("Generate"));
        this.repaint();
    }

    public static void createGui() {
        JFrame frame = new GUI("Schedule Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(890,650);
        // frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGui();
            }
        });
    }
}