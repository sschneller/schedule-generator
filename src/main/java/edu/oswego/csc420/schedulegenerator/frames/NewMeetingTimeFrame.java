package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.GUI;
import edu.oswego.csc420.schedulegenerator.MeetingTime;
import edu.oswego.csc420.schedulegenerator.Section;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewMeetingTimeFrame extends JDialog implements ActionListener {

    JCheckBox su, m, t, w, r, f, s;
    JSpinner sh, sm, sap, eh, em, eap;
    JTextField newLocation;
    Section sectionEdit;
    MeetingTime meetingTime;
    GUI rootFrame;
    boolean editMode;

    public NewMeetingTimeFrame(Section sE, JFrame root) {
        super(root, ModalityType.DOCUMENT_MODAL);
        editMode = false;
        rootFrame = (GUI)root;
        rootFrame.setDialogShown(true);
        setUndecorated(true);
        sectionEdit = sE;
        setLayout(new MigLayout("","[grow,fill]",""));
        setMinimumSize(new Dimension(280,200));
        setTitle("New Meeting Time");
        setResizable(false);
        add(new JLabel(""));
        add(new JLabel("Hour"));
        add(new JLabel("Minute"));
        add(new JLabel(""),"wrap");
        add(new JLabel("Start:"));

        add(sh = new JSpinner(new SpinnerNumberModel(8, 1, 12, 1)));
        add(sm = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1)));
        add(sap = new JSpinner(new SpinnerListModel(new String[]{"AM","PM"})),"wrap");
        add(new JLabel("End:"));
        add(eh = new JSpinner(new SpinnerNumberModel(9, 1, 12, 1)));
        add(em = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1)));
        add(eap = new JSpinner(new SpinnerListModel(new String[]{"AM","PM"})),"wrap");

        JSpinner.NumberEditor editor1 = new JSpinner.NumberEditor(sh, "00");
        sh.setEditor(editor1);
        JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(sm, "00");
        sm.setEditor(editor2);
        JSpinner.NumberEditor editor3 = new JSpinner.NumberEditor(eh, "00");
        eh.setEditor(editor3);
        JSpinner.NumberEditor editor4 = new JSpinner.NumberEditor(em, "00");
        em.setEditor(editor4);

        JPanel panel = new JPanel(new MigLayout("insets 0","[grow,fill]","[grow,fill]"));
        panel.add(new JLabel(" Su"));
        panel.add(new JLabel(" Mo"));
        panel.add(new JLabel(" Tu"));
        panel.add(new JLabel(" We"));
        panel.add(new JLabel(" Th"));
        panel.add(new JLabel(" Fr"));
        panel.add(new JLabel(" Sa"),"wrap");
        panel.add(su = new JCheckBox());
        panel.add(m = new JCheckBox());
        panel.add(t = new JCheckBox());
        panel.add(w = new JCheckBox());
        panel.add(r = new JCheckBox());
        panel.add(f = new JCheckBox());
        panel.add(s =  new JCheckBox());
        add(panel,"span 4,wrap");
        add(new JLabel("Location"), "wrap");
        add(newLocation = new JTextField(),"span");
        JButton addMeetingTime = new JButton("Add Meeting Time");
        addMeetingTime.addActionListener(this);
        add(addMeetingTime, "span");
        this.pack();
        setLocation(((root.getWidth() - 1) / 2) - 140, ((root.getHeight() - 1) / 2) - 100);
        rootFrame.repaint();
    }

    public NewMeetingTimeFrame(Section sE, MeetingTime mE, JFrame root) {
        super(root, ModalityType.DOCUMENT_MODAL);
        editMode = true;
        rootFrame = (GUI)root;
        rootFrame.setDialogShown(true);
        setUndecorated(true);
        sectionEdit = sE;
        meetingTime = mE;
        setLayout(new MigLayout("","[grow,fill]",""));
        setMinimumSize(new Dimension(280,200));
        setTitle("New Meeting Time");
        setResizable(false);
        add(new JLabel(""));
        add(new JLabel("Hour"));
        add(new JLabel("Minute"));
        add(new JLabel(""),"wrap");
        add(new JLabel("Start:"));

        int startingHour = meetingTime.getStart().getHour();
        String[] startingAMPM;
        if(startingHour > 12 && startingHour < 24){
            startingHour = startingHour - 12;
            startingAMPM = new String[]{"PM", "AM"};
        }
        else if(startingHour == 12){
            startingAMPM = new String[]{"PM", "AM"};
        }
        else if(startingHour == 0 || startingHour == 24){
            startingHour = 12;
            startingAMPM = new String[]{"AM", "PM"};
        }
        else{
            startingAMPM = new String[]{"AM", "PM"};
        }

        add(sh = new JSpinner(new SpinnerNumberModel(startingHour, 1, 12, 1)));
        add(sm = new JSpinner(new SpinnerNumberModel(meetingTime.getStart().getMinute(), 0, 60, 1)));
        add(sap = new JSpinner(new SpinnerListModel(startingAMPM)),"wrap");

        add(new JLabel("End:"));

        int endingHour = meetingTime.getEnd().getHour();
        String[] endingAMPM;
        if(endingHour > 12 && endingHour < 24){
            endingHour = endingHour - 12;
            endingAMPM = new String[]{"PM", "AM"};
        }
        else if(endingHour == 12){
            endingAMPM = new String[]{"PM", "AM"};
        }
        else if(endingHour == 0 || endingHour == 24){
            endingHour = 12;
            endingAMPM = new String[]{"AM", "PM"};
        }
        else{
            endingAMPM = new String[]{"AM", "PM"};
        }

        add(eh = new JSpinner(new SpinnerNumberModel(endingHour, 1, 12, 1)));
        add(em = new JSpinner(new SpinnerNumberModel(meetingTime.getEnd().getMinute(), 0, 60, 1)));
        add(eap = new JSpinner(new SpinnerListModel(endingAMPM)),"wrap");

        JSpinner.NumberEditor editor1 = new JSpinner.NumberEditor(sh, "00");
        sh.setEditor(editor1);
        JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(sm, "00");
        sm.setEditor(editor2);
        JSpinner.NumberEditor editor3 = new JSpinner.NumberEditor(eh, "00");
        eh.setEditor(editor3);
        JSpinner.NumberEditor editor4 = new JSpinner.NumberEditor(em, "00");
        em.setEditor(editor4);

        JPanel panel = new JPanel(new MigLayout("insets 0","[grow,fill]","[grow,fill]"));
        panel.add(new JLabel(" Su"));
        panel.add(new JLabel(" Mo"));
        panel.add(new JLabel(" Tu"));
        panel.add(new JLabel(" We"));
        panel.add(new JLabel(" Th"));
        panel.add(new JLabel(" Fr"));
        panel.add(new JLabel(" Sa"),"wrap");

        if(meetingTime.getDays().contains(DayOfWeek.SUNDAY)){
            panel.add(su = new JCheckBox("", true));
        }
        else{
            panel.add(su = new JCheckBox());
        }
        if(meetingTime.getDays().contains(DayOfWeek.MONDAY)){
            panel.add(m = new JCheckBox("", true));
        }
        else{
            panel.add(m = new JCheckBox());
        }
        if(meetingTime.getDays().contains(DayOfWeek.TUESDAY)){
            panel.add(t = new JCheckBox("", true));
        }
        else{
            panel.add(t = new JCheckBox());
        }
        if(meetingTime.getDays().contains(DayOfWeek.WEDNESDAY)){
            panel.add(w = new JCheckBox("", true));
        }
        else{
            panel.add(w = new JCheckBox());
        }
        if(meetingTime.getDays().contains(DayOfWeek.THURSDAY)){
            panel.add(r = new JCheckBox("", true));
        }
        else{
            panel.add(r = new JCheckBox());
        }
        if(meetingTime.getDays().contains(DayOfWeek.FRIDAY)){
            panel.add(f = new JCheckBox("", true));
        }
        else{
            panel.add(f = new JCheckBox());
        }
        if(meetingTime.getDays().contains(DayOfWeek.SATURDAY)){
            panel.add(s = new JCheckBox("", true));
        }
        else{
            panel.add(s = new JCheckBox());
        }

        add(panel,"span 4,wrap");
        add(new JLabel("Location"), "wrap");
        add(newLocation = new JTextField(),"span");
        newLocation.setText(meetingTime.getLocation());
        JButton editMeetingTime = new JButton("Finished Editing");
        editMeetingTime.addActionListener(this);
        add(editMeetingTime, "span");
        this.pack();
        setLocation(((root.getWidth() - 1) / 2) - 140, ((root.getHeight() - 1) / 2) - 100);
        rootFrame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if((su.isSelected() || m.isSelected() || t.isSelected() || w.isSelected()
                || r.isSelected() || f.isSelected() || s.isSelected()) && !(newLocation.getText().equals(""))) {
            ArrayList<DayOfWeek> dow = new ArrayList<>();
            if(su.isSelected()){
                dow.add(DayOfWeek.SUNDAY);
            }if(m.isSelected()){
                dow.add(DayOfWeek.MONDAY);
            }if(t.isSelected()){
                dow.add(DayOfWeek.TUESDAY);
            }if(w.isSelected()){
                dow.add(DayOfWeek.WEDNESDAY);
            }if(r.isSelected()){
                dow.add(DayOfWeek.THURSDAY);
            }if(f.isSelected()){
                dow.add(DayOfWeek.FRIDAY);
            }if(s.isSelected()){
                dow.add(DayOfWeek.SATURDAY);
            }

            LocalTime start;
            if(sap.getValue().equals("AM")) {
                if((Integer) sh.getValue() == 12) {
                    start = LocalTime.of(0, (Integer) sm.getValue());
                }
                else{
                    start = LocalTime.of((Integer) sh.getValue(), (Integer) sm.getValue());
                }
            }
            else{
                if((Integer) sh.getValue() == 12) {
                    start = LocalTime.of((Integer) sh.getValue(), (Integer) sm.getValue());
                }
                else{
                    start = LocalTime.of((Integer) sh.getValue() + 12, (Integer) sm.getValue());
                }
            }
            LocalTime end;
            if(eap.getValue().equals("AM")) {
                if((Integer) eh.getValue() == 12) {
                    end = LocalTime.of(0, (Integer) em.getValue());
                }
                else{
                    end = LocalTime.of((Integer) eh.getValue(), (Integer) em.getValue());
                }
            }
            else{
                if((Integer) eh.getValue() == 12) {
                    end = LocalTime.of((Integer) eh.getValue(), (Integer) em.getValue());
                }
                else{
                    end = LocalTime.of((Integer) eh.getValue() + 12, (Integer) em.getValue());
                }
            }
            DayOfWeek[] dayWeek = new DayOfWeek[dow.size()];
            dayWeek = dow.toArray(dayWeek);
            MeetingTime newMeetingTime = new MeetingTime(start, end, newLocation.getText(), dayWeek);
            if(editMode){
                sectionEdit.removeMeetingTime(meetingTime);
                sectionEdit.addMeetingTime(newMeetingTime);
            }
            else {
                sectionEdit.addMeetingTime(newMeetingTime);
            }
            this.setVisible(false);
            rootFrame.setDialogShown(false);
            rootFrame.repaint();
        }
    }
}
