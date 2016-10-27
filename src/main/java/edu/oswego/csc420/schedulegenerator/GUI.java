package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame implements ActionListener, ComponentListener, ChangeListener {
    ArrayList<String> data;
    ArrayList<JButton> courseList;

    public GUI(String windowTitle) {
        setLayout(new MigLayout());
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
            JButton here = new JButton(title + "                                                             +  ");
            here.setFont(new Font("Arial", Font.PLAIN, 40));
            here.addActionListener(this);
            courseList.add(here);
        }

        for(JButton course : courseList) {
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
        clickedButton.setText(clickedButton.getText().replace("+", "-"));
        for(JButton ccp : courseList) {
            this.remove(ccp);
        }
        this.validate();
        this.add(clickedButton, "cell 0 0");
        this.repaint();
    }

    public static void createGui() {
        JFrame frame = new GUI("Canvas Painter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.pack();
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