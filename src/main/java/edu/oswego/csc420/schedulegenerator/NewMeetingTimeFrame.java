package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewMeetingTimeFrame extends JFrame implements ActionListener {

    JCheckBox su, m, t, w, r, f, s;
    JSpinner sh, sm, sap, eh, em, eap;

    NewMeetingTimeFrame() {
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
        JPanel panel = new JPanel(new MigLayout("insets 0","[grow,fill]","[grow,fill]"));
        panel.add(new JLabel("  S"));
        panel.add(new JLabel("  M"));
        panel.add(new JLabel("  T"));
        panel.add(new JLabel("  W"));
        panel.add(new JLabel("  R"));
        panel.add(new JLabel("  F"));
        panel.add(new JLabel("  S"),"wrap");
        panel.add(su = new JCheckBox());
        panel.add(m = new JCheckBox());
        panel.add(t = new JCheckBox());
        panel.add(w = new JCheckBox());
        panel.add(r = new JCheckBox());
        panel.add(f = new JCheckBox());
        panel.add(s =  new JCheckBox());
        add(panel,"span 4,wrap");
        JButton addMeetingTime = new JButton("Add Meeting Time");
        addMeetingTime.addActionListener(this);
        add(addMeetingTime, "span");
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(su.isSelected() || m.isSelected() || t.isSelected() || w.isSelected()
                || r.isSelected() || f.isSelected() || s.isSelected()) {
            ArrayList<DayOfWeek> dow = new ArrayList();
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

            LocalTime start = LocalTime.of((Integer)sh.getValue(), (Integer)sm.getValue());
            LocalTime end = LocalTime.of((Integer)eh.getValue(), (Integer)em.getValue());
            DayOfWeek[] dayWeek = new DayOfWeek[dow.size()];
            dayWeek = dow.toArray(dayWeek);
            MeetingTime newMeetingTime = new MeetingTime(start, end, dayWeek);
            //(section back end).addMeetingTime(newMeetingTime);
            this.setVisible(false);
        }
        else{
            //Pop up message saying what needs to be entered
        }
    }
}
