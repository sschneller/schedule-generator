package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.Generator;
import edu.oswego.csc420.schedulegenerator.MeetingTime;
import edu.oswego.csc420.schedulegenerator.Section;
import edu.oswego.csc420.schedulegenerator.components.SGTextField;
import edu.oswego.csc420.schedulegenerator.frames.NewMeetingTimeFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SectionInformationPanel extends JPanel {

    JLabel sectionL, crnL, teacherL;
    SGTextField editSection, editCRN, editTeacher;
    Generator gen;
    Course courseEdit;
    Section sectionChosen;
    JButton button;
    Object columnNames[] = { "Days", "Time", "Location" };
    Object rowData[][] = {};
    JTable table;

    SectionInformationPanel(Generator g, Course cE) {
        gen = g;
        courseEdit = cE;
        setBackground(Color.WHITE);
        setLayout(new MigLayout("","[grow,fill]","[][grow,fill][]"));
        final JLabel l = new JLabel("Section Information", SwingConstants.CENTER);
        add(l, "wrap");
        table = new JTable(new DefaultTableModel(rowData, columnNames)) {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        add(new JScrollPane(table), "wrap");
        button = new JButton("New Meeting Time");
        // SectionPanel sp = ((CourseInfoPanel)button.getParent()).sp;
        // sp.table.getSelectedRow();
        button.addActionListener(a -> new NewMeetingTimeFrame(this, gen, courseEdit, sectionChosen, (JFrame)button.getTopLevelAncestor()).setVisible(true));
        add(button);
    }

    public void createMeetingTimes(Section sec){
        sectionChosen = sec;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if(model.getRowCount() > 0) {
            for(int i = model.getRowCount() - 1; i >=0; i--){
                model.removeRow(i);
            }
            for(int i = 0; i < sectionChosen.getMeetingTimes().size(); i++){
                MeetingTime m = sectionChosen.getMeetingTimes().get(i);
                model.addRow(new Object[]{m.getDays(), m.getStart() + " - " + m.getEnd(), m.getLocation()});
            }
        }
        else{
            for(int i = 0; i < sectionChosen.getMeetingTimes().size(); i++){
                MeetingTime m = sectionChosen.getMeetingTimes().get(i);
                model.addRow(new Object[]{m.getDays(), m.getStart() + " - " + m.getEnd(), m.getLocation()});
            }
        }
    }
}
