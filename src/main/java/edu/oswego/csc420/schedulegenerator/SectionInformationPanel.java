package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SectionInformationPanel extends JPanel {

    SectionInformationPanel() {
        setBackground(Color.WHITE);
        setLayout(new MigLayout("","[grow,fill]","[][][][grow,fill][]"));
        final JLabel l = new JLabel("Section Information", SwingConstants.CENTER);
        add(l, "wrap, span 3");
        add(new JLabel("Section #"));
        add(new JLabel("CRN"));
        add(new JLabel("Teacher"),"wrap");
        add(new JTextField());
        add(new JTextField());
        add(new JTextField(),"wrap");
        Object rowData[][] = { { "MWF", "08:00AM - 09:00AM" },
                { "T", "10:00AM - 11:00AM" }};
        Object columnNames[] = { "Days", "Time" };
        JTable table = new JTable(rowData, columnNames) {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        add(new JScrollPane(table), "wrap, span 3");
        JButton button = new JButton("New Meeting Time");
        button.addActionListener(a -> new NewMeetingTimeFrame().setVisible(true));
        add(button, "span 3");
    }
}
