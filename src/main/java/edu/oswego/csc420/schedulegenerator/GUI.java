package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    ArrayList<CourseEntry> courseList;
    JPanel currPanel;
    JPanel cards;
    JButton newCourseButton = new JButton("New Course");
    JButton importButton = new JButton("Import");
    JButton exportButton = new JButton("Export");
    JButton generateButton = new JButton("Generate");
    JPanel cp = new JPanel(new MigLayout("","[grow,fill]","[grow,fill][grow,fill]"));

    public GUI(String windowTitle) {
        cards = new JPanel(new CardLayout());
        currPanel = new JPanel(new MigLayout("","[grow,fill]","[grow,fill][]"));
        // setLayout();
        setTitle(windowTitle);

        newCourseButton.addActionListener(this);
        importButton.addActionListener(this);
        exportButton.addActionListener(this);
        generateButton.addActionListener(this);

        AccordionPanel ap = new AccordionPanel();
        ap.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Added Classes"), BorderFactory.createEmptyBorder(5,5,5,5)));
        currPanel.add(ap, "wrap");

        JPanel bottom = new JPanel();
        bottom.setLayout(new MigLayout("","[grow,fill][][]300[]","[grow,fill][]"));
        bottom.add(newCourseButton);
        bottom.add(importButton);
        bottom.add(exportButton);
        bottom.add(generateButton);
        currPanel.add(bottom);
        cards.add(currPanel, "ACC");
        // JPanel cp = new JPanel(new MigLayout("","[grow,fill]","[grow,fill][grow,fill]"));
        // cp.add(new CourseEntry("CSC420", false), "wrap");
        // cp.add(new CoursePanel());
        // cards.add(cp, "EXP");
        add(cards);
        CardLayout cl = (CardLayout)cards.getLayout();
        cl.show(cards, "ACC");
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        switch(clickedButton.getText()) {
            case("New Course"): {
                cp.removeAll();
                cp.add(new CourseEntry("", false), "wrap");
                cp.add(new CoursePanel());
                cards.add(cp, "EXP");
                cards.repaint();
                CardLayout cl = (CardLayout)cards.getLayout();
                cl.show(cards, "EXP");
                break;
            }
            case("Import"): {
                break;
            }
            case("Export"): {
                break;
            }
            case("Generate"): {
                break;
            }
            default: {
                // clickedButton.setText(clickedButton.getText().replace("+", "-"));
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
                this.repaint();
                break;
            }
        }
    }

    public static void createGui() {
        JFrame frame = new GUI("Course Schedule Generator");
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