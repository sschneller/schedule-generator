package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.frames.NewSectionFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;

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
        JTable table = new JTable(rowData, columnNames) {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        add(new JScrollPane(table), "wrap");
        JButton button = new JButton("New Section");
        button.addActionListener(a -> new NewSectionFrame().setVisible(true));
        add(button);
    }
}
