package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.MeetingTime;
import edu.oswego.csc420.schedulegenerator.Section;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class SectionInformationPanel extends UpdatablePanel {
    private final JLabel label;
    private final JTable<MeetingTime> table;
    private final JButton button;
    private Section section;

    SectionInformationPanel() {
        this.label = new JLabel("Section Information", JLabel.CENTER);
        this.button = new JButton("New Meeting Time");
        this.table = new edu.oswego.csc420.schedulegenerator.exp.JTable<>(new String[]{"Days", "Time", "Location"},
                m -> new String[]{m.getDays().stream().sorted(Comparator.comparingInt(f -> ((f.getValue() - 14) % 7))).map(d -> d.getDisplayName(TextStyle.SHORT, Locale.US).substring(0,2) + " ").collect(Collectors.joining()), m.getStart().toString() + " - " + m.getEnd().toString(), m.getLocation()},
                (e, t) -> System.out.println("SELECTED: " + t.getSelectedRow()));

        setBackground(new Color(96,125,139));
        setLayout(new MigLayout("wrap","[grow,fill]","[][grow,fill][]"));
        add(label);
        add(new JScrollPane(table));
        add(button);
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
}
