package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.Section;
import edu.oswego.csc420.schedulegenerator.frames.NewSectionFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class SectionPanel extends AbstractSectionPanel<Section> {
    private final Course course;
    private final SectionInformationPanel sectionInformationPanel;

    SectionPanel(final Course course, final SectionInformationPanel sectionInformationPanel) {
        super("Sections", "New Section", "Edit", "Delete", new String[]{"Section #", "CRN", "Teacher"});
        this.course = course;
        this.sectionInformationPanel = sectionInformationPanel;
    }

    @Override
    public void onRowSelected(ListSelectionEvent event, JTable<Section> jTable) {
        if(!course.getSections().isEmpty()) {
            sectionInformationPanel.setSection(course.getSections().get(Math.max(0, jTable.getSelectedRow())));
        }
    }

    @Override
    public void onNewButtonClick() {
        new NewSectionFrame(course, (JFrame)this.getTopLevelAncestor()).setVisible(true);
        update();
    }

    @Override
    public void onEditButtonClick() {

    }

    @Override
    public void onDeleteButtonClick() {
        int selectedRow = table.getSelectedRow();
        course.removeSection(course.getSections().get(selectedRow));
        sectionInformationPanel.setSection(null);
        update();
    }

    @Override
    public String[] objectRowMapper(Section object) {
        return new String[]{object.getSectionNumber(), object.getCrn(), object.getTeacher()};
    }

    @Override
    public void update() {
        table.getTableModel().removeAll();
        table.getTableModel().addAll(course.getSections());
        super.update();
    }
}
