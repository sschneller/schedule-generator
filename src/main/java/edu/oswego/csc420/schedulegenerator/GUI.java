package edu.oswego.csc420.schedulegenerator;

import edu.oswego.csc420.schedulegenerator.panels.CardPanel;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI extends JFrame {

    public static void main(String[] args) {
        final GUI gui = new GUI();
        javax.swing.SwingUtilities.invokeLater(() -> gui.setVisible(true));
    }

    private GUI() {
        setResizable(true);
        setSize(890,650);
        setTitle("Course Schedule Generator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(new CardPanel(new Generator()));
    }
}