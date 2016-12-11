package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.Colors;
import edu.oswego.csc420.schedulegenerator.MeetingTime;
import edu.oswego.csc420.schedulegenerator.Section;
import edu.oswego.csc420.schedulegenerator.frames.NewMeetingTimeFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class SectionInformationPanel extends UpdatablePanel implements ActionListener {
    private final JLabel label;
    private final JTable<MeetingTime> table;
    private final JButton newMeetingTime, editMeetingTime, deleteMeetingTime;
    private Section section;

    SectionInformationPanel() {
        this.label = new JLabel("Section Information", JLabel.CENTER);
        label.setForeground(Color.WHITE);
        this.newMeetingTime = new JButton("New Meeting Time");
        this.editMeetingTime = new JButton("Edit");
        this.deleteMeetingTime = new JButton("Delete");
        this.table = new edu.oswego.csc420.schedulegenerator.exp.JTable<>(new String[]{"Days", "Time", "Location"},
                m -> new String[]{m.getDays().stream().sorted(Comparator.comparingInt(f -> ((f.getValue() - 14) % 7))).map(d -> d.getDisplayName(TextStyle.SHORT, Locale.US).substring(0,2) + " ").collect(Collectors.joining()), m.getStart().toString() + " - " + m.getEnd().toString(), m.getLocation()},
                (e, t) -> System.out.println("SELECTED: " + t.getSelectedRow()));

        setBackground(Colors.PRIMARY);
        setLayout(new MigLayout("","[grow,fill]","[][grow,fill][]"));
        add(label, "span 3, wrap");
        add(new JScrollPane(table), "span 3, wrap");
        newMeetingTime.addActionListener(this);
        editMeetingTime.addActionListener(this);
        deleteMeetingTime.addActionListener(this);
        add(newMeetingTime);
        add(editMeetingTime);
        add(deleteMeetingTime);
    }

    public void setSection(final Section section) {
        this.section = section;
        update();
    }

    @Override
    public void update() {
        if(section != null) {
            table.getTableModel().removeAll();
            table.getTableModel().addAll(section.getMeetingTimes());
        }
        super.update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newMeetingTime){
            new NewMeetingTimeFrame(section, (JFrame)newMeetingTime.getTopLevelAncestor()).setVisible(true);
            update();
        }
        if(e.getSource() == editMeetingTime){

        }
        if(e.getSource() == deleteMeetingTime){
            int selectedRow = table.getSelectedRow();
            section.removeMeetingTime(section.getMeetingTimes().get(selectedRow));
            update();
        }
    }
}
