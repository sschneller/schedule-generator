package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.Section;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.util.List;
import java.util.stream.IntStream;

public class SectionPanel extends UpdatablePanel {
    private final JLabel label;
    private final JTable<Section> table;
    private final JButton newSection, editSection, deleteSection;
    private final Course course;

    SectionPanel(final Course course, final SectionInformationPanel sectionInformationPanel) {
        this.course = course;
        this.label = new JLabel("Sections", JLabel.CENTER);
        this.newSection = new JButton("New Section");
        this.editSection = new JButton("Edit");
        this.deleteSection = new JButton("Delete");
        this.table = new JTable<>(new String[]{"Section #", "CRN", "Teacher"},
                s -> new String[]{s.getSectionNumber(), s.getCrn(), s.getTeacher()},
                (e, t) -> sectionInformationPanel.setSection(course.getSections().get(Math.max(0, t.getSelectedRow()))));

        setBackground(new Color(96,125,139));
        setLayout(new MigLayout("wrap","[grow,fill]","[][grow,fill][]"));
        add(label);
        add(new JScrollPane(table));
        add(newSection);
        add(editSection);
        add(deleteSection);
    }

    @Override
    public void update() {
        table.getTableModel().removeAll();
        table.getTableModel().addAll(course.getSections());
        super.update();
    }
}
