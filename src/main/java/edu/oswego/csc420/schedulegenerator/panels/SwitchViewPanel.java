package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.frames.GeneratedScheduleFrame;
import edu.oswego.csc420.schedulegenerator.frames.NewSectionFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchViewPanel extends JPanel implements ActionListener {
    JButton newCourseButton = new JButton("New Course");
    JButton importButton = new JButton("Import");
    JButton exportButton = new JButton("Export");
    JButton generateButton = new JButton("Generate");

    SwitchViewPanel() {
        setLayout(new MigLayout("","[grow,fill][][]300[]","[grow,fill][]"));
        newCourseButton.addActionListener(this);
        importButton.addActionListener(this);
        exportButton.addActionListener(this);
        generateButton.addActionListener(this);
        add(newCourseButton);
        add(importButton);
        add(exportButton);
        add(generateButton);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        CardPanel rootPanel = (CardPanel)this.getParent().getParent();
        switch(clickedButton.getText()) {
            case("New Course"): {
                rootPanel.remove(rootPanel.courseOverview);
                rootPanel.courseOverview = new CoursePanel(new CourseHeaderPanel(false), new CourseInfoPanel());
                rootPanel.add(rootPanel.courseOverview, "EXP");
                rootPanel.cl.show(rootPanel, "EXP");
                break;
            }
            case("Import"): {
                System.out.println("Import Clicked!");
                break;
            }
            case("Export"): {
                System.out.println("Export Clicked!");
                break;
            }
            case("Generate"): {
                System.out.println("Generate Clicked!");
                GeneratedScheduleFrame gsf = new GeneratedScheduleFrame();
                gsf.setSize(890,650);
                gsf.setVisible(true);
                break;
            }
            default: {
                System.out.println("Something Bad Was Clicked!!\nSource Was: " + e.getSource());
            }
        }
    }
}
