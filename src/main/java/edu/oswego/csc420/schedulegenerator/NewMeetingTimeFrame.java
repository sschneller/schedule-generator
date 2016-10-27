package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class NewMeetingTimeFrame extends JFrame {

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
        add(new JSpinner(new SpinnerNumberModel(8, 1, 12, 1)));
        add(new JSpinner(new SpinnerNumberModel(0, 0, 60, 1)));
        add(new JSpinner(new SpinnerListModel(new String[]{"AM","PM"})),"wrap");
        add(new JLabel("End:"));
        add(new JSpinner(new SpinnerNumberModel(9, 1, 12, 1)));
        add(new JSpinner(new SpinnerNumberModel(0, 0, 60, 1)));
        add(new JSpinner(new SpinnerListModel(new String[]{"AM","PM"})),"wrap");
        JPanel panel = new JPanel(new MigLayout("insets 0","[grow,fill]","[grow,fill]"));
        panel.add(new JLabel("  S"));
        panel.add(new JLabel("  M"));
        panel.add(new JLabel("  T"));
        panel.add(new JLabel("  W"));
        panel.add(new JLabel("  R"));
        panel.add(new JLabel("  F"));
        panel.add(new JLabel("  S"),"wrap");
        panel.add(new JCheckBox());
        panel.add(new JCheckBox());
        panel.add(new JCheckBox());
        panel.add(new JCheckBox());
        panel.add(new JCheckBox());
        panel.add(new JCheckBox());
        panel.add(new JCheckBox());
        add(panel,"span 4,wrap");
        add(new JButton("Add Meeting Time"), "span");
    }
}
