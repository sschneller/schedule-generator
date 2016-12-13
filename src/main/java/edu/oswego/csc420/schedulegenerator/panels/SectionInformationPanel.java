package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.*;
import edu.oswego.csc420.schedulegenerator.JButton;
import edu.oswego.csc420.schedulegenerator.frames.NewMeetingTimeFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class SectionInformationPanel extends AbstractSectionPanel<MeetingTime> {
    private Section section;

    SectionInformationPanel() {
        super("Meeting Time","New Meeting Time", "Edit", "Delete", new String[]{"Days", "Time", "Location"});
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
        if(section != null) {
            new NewMeetingTimeFrame(section, (JFrame) this.getTopLevelAncestor()).setVisible(true);
            update();
        }
        else{
            JOptionPane.showMessageDialog(null, "Need a Section Selected to Add Meeting Time!");
        }
    }
    @Override
    public void onEditButtonClick() {
        if(section != null && table.getSelectedRow() != -1) {
            int selectedRow = table.getSelectedRow();
            new NewMeetingTimeFrame(section, section.getMeetingTimes().get(selectedRow), (JFrame) this.getTopLevelAncestor()).setVisible(true);
            update();
        }
        else{
            if(section == null) {
                JOptionPane.showMessageDialog(null, "Need a Section to be Selected to Edit a Meeting Time!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Need to Select a Meeting Time to Edit!");
            }
        }
    }

    @Override
    public void onDeleteButtonClick() {
        if(section != null && table.getSelectedRow() != -1) {
            int selectedRow = table.getSelectedRow();
            section.removeMeetingTime(section.getMeetingTimes().get(selectedRow));
            update();
        }
        else{
            if(section == null) {
                JOptionPane.showMessageDialog(null, "Need a Section to be Selected to Delete a Meeting Time!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Need to Select a Meeting Time to Delete!");
            }
        }
    }

    @Override
    public String[] objectRowMapper(MeetingTime object) {
        String startTime, endTime, startMinutes, endMinutes;

        if(object.getStart().getMinute() < 10) {
            startMinutes = "0" + object.getStart().getMinute();
        }
        else{
            startMinutes = object.getStart().getMinute() + "";
        }
        if(object.getEnd().getMinute() < 10) {
            endMinutes = "0" + object.getEnd().getMinute();
        }
        else{
            endMinutes = object.getEnd().getMinute() + "";
        }

        if(object.getStart().getHour() == 0){
            startTime = "12:" + startMinutes + " AM";
        }
        else if(object.getStart().getHour() == 12){
            startTime = "12:" + startMinutes + " PM";
        }
        else if(object.getStart().getHour() > 12){
            startTime = object.getStart().getHour() - 12 + ":" + startMinutes + "PM";
        }
        else{
            startTime = object.getStart().getHour() + ":" + startMinutes + " AM";
        }

        if(object.getEnd().getHour() == 0){
            endTime = "12:" + endMinutes + " AM";
        }
        else if(object.getEnd().getHour() == 12){
            endTime = "12:" + endMinutes + " PM";
        }
        else if(object.getEnd().getHour() > 12){
            endTime = object.getEnd().getHour() - 12 + ":" + endMinutes + " PM";
        }
        else{
            endTime = object.getEnd().getHour() + ":" + endMinutes + " AM";
        }

        return new String[]{object.getDays().stream().sorted(Comparator.comparingInt(f -> ((f.getValue() - 14) % 7)))
                .map(d -> d.getDisplayName(TextStyle.SHORT, Locale.US).substring(0,2) + " ")
                .collect(Collectors.joining()),
                startTime + " - " + endTime,
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
