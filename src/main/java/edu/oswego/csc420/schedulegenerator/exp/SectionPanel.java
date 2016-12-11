package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.Colors;
import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.Section;
import edu.oswego.csc420.schedulegenerator.frames.NewSectionFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SectionPanel extends UpdatablePanel implements ActionListener {
    private final JLabel label;
    private final JTable<Section> table;
    private final JButton newSection, editSection, deleteSection;
    private final Course course;

    SectionPanel(final Course course, final SectionInformationPanel sectionInformationPanel) {
        this.course = course;
        this.label = new JLabel("Sections", JLabel.CENTER);
        label.setForeground(Color.WHITE);
        this.newSection = new JButton("New Section");
        this.editSection = new JButton("Edit");
        this.deleteSection = new JButton("Delete");
        this.table = new JTable<>(new String[]{"Section #", "CRN", "Teacher"},
                s -> new String[]{s.getSectionNumber(), s.getCrn(), s.getTeacher()},
                (e, t) -> { if(!course.getSections().isEmpty()) {
                    sectionInformationPanel.setSection(course.getSections().get(Math.max(0, t.getSelectedRow())));
                }});

        setBackground(Colors.PRIMARY);
        setLayout(new MigLayout("","[grow,fill]","[][grow,fill][]"));
        add(label,"span 3, wrap");
        add(new JScrollPane(table), "span 3, wrap");
        newSection.addActionListener(this);
        editSection.addActionListener(this);
        deleteSection.addActionListener(this);
        add(newSection);
        add(editSection);
        add(deleteSection);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newSection){
            new NewSectionFrame(course, (JFrame)newSection.getTopLevelAncestor()).setVisible(true);
            update();
        }
        if(e.getSource() == editSection){

        }
        if(e.getSource() == deleteSection){
            int selectedRow = table.getSelectedRow();
            course.removeSection(course.getSections().get(selectedRow));
            update();
        }
    }

    @Override
    public void update() {
        table.getTableModel().removeAll();
        table.getTableModel().addAll(course.getSections());
        super.update();
    }
}
