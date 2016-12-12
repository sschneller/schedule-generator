package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.MeetingTime;
import edu.oswego.csc420.schedulegenerator.Section;
import edu.oswego.csc420.schedulegenerator.frames.NewMeetingTimeFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class SectionInformationPanel extends AbstractSectionPanel<MeetingTime> {
    private Section section;

    SectionInformationPanel() {
        super("Section Information","New Meeting Time", "Edit", "Delete", new String[]{"Days", "Time", "Location"});
    }

    void setSection(final Section section) {
        this.section = section;
        if(section == null) {
            table.getTableModel().removeAll();
        }
        update();
    }

    @Override
    public void onRowSelected(ListSelectionEvent event, JTable<MeetingTime> jTable) {
        System.out.println("ROW SELECTED: " + jTable.getSelectedRow());
    }

    @Override
    public void onNewButtonClick() {
        new NewMeetingTimeFrame(section, (JFrame)this.getTopLevelAncestor()).setVisible(true);
        update();
    }

    @Override
    public void onEditButtonClick() {

    }

    @Override
    public void onDeleteButtonClick() {

    }

    @Override
    public String[] objectRowMapper(MeetingTime object) {
        return new String[]{object.getDays().stream().sorted(Comparator.comparingInt(f -> ((f.getValue() - 14) % 7)))
                .map(d -> d.getDisplayName(TextStyle.SHORT, Locale.US).substring(0,2) + " ")
                .collect(Collectors.joining()),
                object.getStart().toString() + " - " + object.getEnd().toString(),
                object.getLocation()};
    }

    @Override
    public void update() {
        if(section != null) {
            table.getTableModel().removeAll();
            table.getTableModel().addAll(section.getMeetingTimes());
        }
        super.update();
    }
}
