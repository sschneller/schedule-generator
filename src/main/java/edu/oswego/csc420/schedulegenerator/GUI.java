package edu.oswego.csc420.schedulegenerator;

import edu.oswego.csc420.schedulegenerator.panels.CardPanel;

import javax.swing.*;

public class GUI extends JFrame {

    private Generator gen = new Generator();

    GUI(String windowTitle) {
        setTitle(windowTitle);
        add(new CardPanel(gen));
    }

    private static void createGui() {
        JFrame frame = new GUI("Course Schedule Generator");
        // JFrame frame = new GeneratedScheduleFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(890,650);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(GUI::createGui);
    }
}