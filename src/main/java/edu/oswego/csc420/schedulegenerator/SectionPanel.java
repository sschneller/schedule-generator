package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Color;

public class SectionPanel extends JPanel {

    SectionPanel() {
        setBackground(Color.WHITE);
        setLayout(new MigLayout("","[grow,fill]","[][grow,fill][]"));
        final JLabel l = new JLabel("Sections", SwingConstants.CENTER);
        add(l, "wrap");
        Object rowData[][] = { { "800", "MWF", "08:00AM - 09:00AM" },
                { "801", "MWF", "12:00PM - 01:00PM" },
                { "802", "Add a meeting time.", "Add a meeting time." }};
        Object columnNames[] = { "Section #", "Days", "Times" };
        JTable table = new JTable(rowData, columnNames);

        add(new JScrollPane(table), "wrap");
        add(new JButton("New Section"));
    }
}
