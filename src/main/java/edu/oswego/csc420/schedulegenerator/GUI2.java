package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;

public class GUI2 extends JFrame {

    public static void main(String[] args) {
        final GUI2 gui2 = new GUI2();
        SwingUtilities.invokeLater(() -> gui2.setVisible(true));
    }

    private GUI2() {
        init();
        final AccordionPanel accordionPanel = new AccordionPanel();
        accordionPanel.add(new CoursePanel());
        add(accordionPanel, "span, grow");
        add(new JButton("New Course"));
        add(new JButton("Import"));
        add(new JButton("Export"));
        add(new JButton("Generate"));
    }

    private void init() {
        setTitle("Course Schedule Generator");
        setBackground(Color.GRAY);
        setLayout(new MigLayout("","[grow,fill][][]300[]","[grow,fill][]"));
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(890,650));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
