package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.Generator;
import edu.oswego.csc420.schedulegenerator.Section;
import edu.oswego.csc420.schedulegenerator.frames.NewSectionFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SectionPanel extends JPanel implements ListSelectionListener {

    Object rowData[][] = {};
    Object columnNames[] = {"Section #", "CRN", "Teacher"};
    JTable table;
    SectionInformationPanel secI;
    Course secFromCourse;
    DefaultTableModel model;

    SectionPanel(SectionInformationPanel sI, Generator g, Course cE) {
        secI = sI;
        secFromCourse = cE;
        setBackground(Color.WHITE);
        setLayout(new MigLayout("","[grow,fill]","[][grow,fill][]"));
        final JLabel l = new JLabel("Sections", SwingConstants.CENTER);
        add(l, "wrap");

        table = new JTable(new DefaultTableModel(rowData, columnNames)) {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.getSelectionModel().addListSelectionListener(this);
        // table.setRowSelectionInterval(0, 0);
        add(new JScrollPane(table), "wrap");
        JButton button = new JButton("New Section");
        //button.addActionListener(a -> new NewSectionFrame(this, secI, cE, (JFrame)button.getTopLevelAncestor()).setVisible(true));
        // button.addActionListener(a -> table.setRowSelectionInterval(0, 0));
        add(button);
    }

    public void addSectionToTable(Section s){
        model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{s.getSectionNumber(), s.getCrn(), s.getTeacher()});
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Section s = secFromCourse.getSections().get(table.getSelectedRow());
        secI.createMeetingTimes(s);
    }
}
