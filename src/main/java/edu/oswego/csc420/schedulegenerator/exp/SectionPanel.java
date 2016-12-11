package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.Section;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;

public class SectionPanel extends UpdatablePanel {
    private final JLabel label;
    private final JTable<Section> table;
    private final JButton button;
    private final Course course;

    SectionPanel(final Course course, final SectionInformationPanel sectionInformationPanel) {
        this.course = course;
        this.label = new JLabel("Sections", JLabel.CENTER);
        this.button = new JButton("New Section");
        this.table = new JTable<>(new String[]{"Section #", "CRN", "Teacher"},
                s -> new String[]{s.getSectionNumber(), s.getCrn(), s.getTeacher()},
                (e, t) -> sectionInformationPanel.setSection(course.getSections().get(Math.max(0, t.getSelectedRow()))));

        setBackground(new Color(96,125,139));
        setLayout(new MigLayout("","[grow,fill]","[][grow,fill][]"));
        add(label,"span 3, wrap");
        add(new JScrollPane(table), "span 3, wrap");
        add(button);
    }

    @Override
    public void update() {
        table.getTableModel().removeAll();
        table.getTableModel().addAll(course.getSections());
        super.update();
    }
}
