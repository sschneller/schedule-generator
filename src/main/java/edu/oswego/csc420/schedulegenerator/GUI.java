package edu.oswego.csc420.schedulegenerator;

import javax.swing.*;

public class GUI extends JFrame {

    GUI(String windowTitle) {
        setTitle(windowTitle);
        add(new CardPanel());
    }

    private static void createGui() {
        JFrame frame = new GUI("Course Schedule Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(890,650);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> createGui());
    }
}