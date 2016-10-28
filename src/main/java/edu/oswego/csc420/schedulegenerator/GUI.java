package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener, ComponentListener, ChangeListener {
    ArrayList<String> data;
    ArrayList<CourseEntry> courseList;
    JPanel currPanel;
    JPanel cards;

    public GUI(String windowTitle) {
        cards = new JPanel(new CardLayout());
        currPanel = new JPanel(new MigLayout("","[grow,fill]","[grow,fill][]"));
        // setLayout();
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

        AccordionPanel ap = new AccordionPanel();
        ap.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Added Classes"), BorderFactory.createEmptyBorder(5,5,5,5)));
        currPanel.add(ap, "wrap");

        JPanel bottom = new JPanel();
        bottom.setLayout(new MigLayout("","[grow,fill][][]300[]","[grow,fill][]"));
        bottom.add(new JButton("New Course"));
        bottom.add(new JButton("Import"));
        bottom.add(new JButton("Export"));
        bottom.add(new JButton("Generate"));
        currPanel.add(bottom);
        cards.add(currPanel, "ACC");
        JPanel cp = new JPanel(new MigLayout("","[grow,fill]","[grow,fill][grow,fill]"));
        cp.add(new CourseEntry("CSC420", false), "wrap");
        cp.add(new CoursePanel());
        cards.add(cp, "EXP");
        add(cards);
        CardLayout cl = (CardLayout)cards.getLayout();
        cl.show(cards, "ACC");
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