package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.*;
import edu.oswego.csc420.schedulegenerator.panels.SectionInformationPanel;
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
    SectionInformationPanel secI;
    Generator gen;
    Course courseEdit;
    Section sectionEdit;
    GUI2 rootFrame;

    public NewMeetingTimeFrame(SectionInformationPanel sI, Generator g, Course cE, Section sE, JFrame root) {
        super(root, ModalityType.DOCUMENT_MODAL);
        rootFrame = (GUI2)root;
        rootFrame.setDialogShown(true);
        setUndecorated(true);
        setLocationRelativeTo(root);
        secI = sI;
        gen = g;
        courseEdit = cE;
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
        add(new JLabel("Location"), "wrap");
        add(newLocation = new JTextField(),"span");
        JButton addMeetingTime = new JButton("Add Meeting Time");
        addMeetingTime.addActionListener(this);
        add(addMeetingTime, "span");
        this.pack();
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

            LocalTime start = LocalTime.of((Integer)sh.getValue(), (Integer)sm.getValue());
            LocalTime end = LocalTime.of((Integer)eh.getValue(), (Integer)em.getValue());
            DayOfWeek[] dayWeek = new DayOfWeek[dow.size()];
            dayWeek = dow.toArray(dayWeek);
            MeetingTime newMeetingTime = new MeetingTime(start, end, newLocation.getText(), dayWeek);
            if(sectionEdit.getMeetingTimes().size() == 0){
                // if no section times have been added, add the course to the backend, the section to the course
                // and the meeting time to the section
                sectionEdit.addMeetingTime(newMeetingTime);
                courseEdit.addSection(sectionEdit);
                gen.addCourse(courseEdit);
            }
            else{
                // If a section time has already been added just add the section time
                sectionEdit.addMeetingTime(newMeetingTime);
            }
            this.setVisible(false);
        }
    }
}
