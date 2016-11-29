package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.components.SGTextField;
import edu.oswego.csc420.schedulegenerator.frames.NewMeetingTimeFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SectionInformationPanel extends JPanel {

    JLabel sectionL, crnL, teacherL;
    SGTextField editSection, editCRN, editTeacher;

    SectionInformationPanel() {
        setBackground(Color.WHITE);
        setLayout(new MigLayout("","[grow,fill]","[][][][grow,fill][]"));
        final JLabel l = new JLabel("Section Information", SwingConstants.CENTER);
        add(l, "wrap, span 3");
        Object rowData[][] = { { "MWF", "08:00AM - 09:00AM", "Shineman 444" },
                { "T", "10:00AM - 11:00AM", "Shineman 444" }};
        Object columnNames[] = { "Days", "Time", "Location" };
        JTable table = new JTable(rowData, columnNames) {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        add(new JScrollPane(table), "wrap, span 3");
        add(sectionL = new JLabel("Section #"));
        add(crnL = new JLabel("CRN"));
        add(teacherL = new JLabel("Teacher"),"wrap");
        editSection = new SGTextField();
        editSection.setEnabled(false);
        add(editSection);
        editCRN = new SGTextField();
        editCRN.setEnabled(false);
        add(editCRN);
        editTeacher = new SGTextField();
        editTeacher.setEnabled(false);
        add(editTeacher, "wrap");
        JButton button = new JButton("New Meeting Time");
        button.addActionListener(a -> new NewMeetingTimeFrame().setVisible(true));
        add(button, "span 3");
    }
}
