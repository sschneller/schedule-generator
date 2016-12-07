package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.Schedule;
import edu.oswego.csc420.schedulegenerator.SectionExample;
import net.miginfocom.swing.MigLayout;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GeneratedScheduleFrame extends JFrame implements ActionListener {
    int height, width, xDivider, yDivider;
    int earliestHour = 11, latestHour = 14; // Example Input - Should be start time of earliest class, and end time of the latest class for entire week
                                            // Needs to be military time, and round down to hour for first, and round up for last
    JButton back;
    JButton forward;

    // String[][] courses = {{"08.00", "09.55"}};
    ArrayList<String> options = new ArrayList<>();
    ArrayList<SectionExample> sectionExample = new ArrayList<>();

    JPanel calendar = new JPanel(new MigLayout("insets 5 5", "[grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,fill]", "[][grow,fill]")) {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            height = getHeight() - 1;
            width = getWidth() - 1;
            xDivider = width / 7;
            try {
                yDivider = (height - 30) / ((latestHour + 1) - (earliestHour - 1) + 2);
            } catch (ArithmeticException ae) {
                yDivider = 0;
            }

            int SUNDAY_XLOC = xDivider;
            int SUNDAY_XLOC_CENTER = SUNDAY_XLOC / 2;
            int MONDAY_XLOC = 3 * xDivider;
            int MONDAY_XLOC_CENTER = MONDAY_XLOC / 2;
            int TUESDAY_XLOC = 5 * xDivider;
            int TUESDAY_XLOC_CENTER = TUESDAY_XLOC / 2;
            int WEDNESDAY_XLOC = 7 * xDivider;
            int WEDNESDAY_XLOC_CENTER = WEDNESDAY_XLOC / 2;
            int THURSDAY_XLOC = 9 * xDivider;
            int THURSDAY_XLOC_CENTER = THURSDAY_XLOC / 2;
            int FRIDAY_XLOC = 11 * xDivider;
            int FRIDAY_XLOC_CENTER = FRIDAY_XLOC / 2;
            int SATURDAY_XLOC = 13 * xDivider;
            int SATURDAY_XLOC_CENTER = SATURDAY_XLOC / 2;

            // DRAW THE BACKGROUND BORDER AND STUFF
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
            g.setColor(Color.WHITE);
            g.fillRoundRect(0, 0, width, height, 35, 35);
            g.setColor(Color.BLACK);
            g.drawRoundRect(0, 0, width, height, 35, 35);
            g.drawLine(0, 30, width, 30);

            // DRAW THE LINES TO DIVIDE THE WEEK DAYS
            g.drawLine(xDivider, 0, xDivider, height);
            g.drawLine(2 * xDivider, 0, 2 * xDivider, height);
            g.drawLine(3 * xDivider, 0, 3 * xDivider, height);
            g.drawLine(4 * xDivider, 0, 4 * xDivider, height);
            g.drawLine(5 * xDivider, 0, 5 * xDivider, height);
            g.drawLine(6 * xDivider, 0, 6 * xDivider, height);

            // DRAW THE WEEKDAY NAMES
            g.drawString("Sunday", SUNDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth("Sunday") / 2), 30 - (g.getFontMetrics().getHeight() / 2));
            g.drawString("Monday", MONDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth("Monday") / 2), 30 - (g.getFontMetrics().getHeight() / 2));
            g.drawString("Tuesday", TUESDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth("Tuesday") / 2), 30 - (g.getFontMetrics().getHeight() / 2));
            g.drawString("Wednesday", WEDNESDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth("Wednesday") / 2), 30 - (g.getFontMetrics().getHeight() / 2));
            g.drawString("Thursday", THURSDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth("Thursday") / 2), 30 - (g.getFontMetrics().getHeight() / 2));
            g.drawString("Friday", FRIDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth("Friday") / 2), 30 - (g.getFontMetrics().getHeight() / 2));
            g.drawString("Saturday", SATURDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth("Saturday") / 2), 30 - (g.getFontMetrics().getHeight() / 2));

            for (int i = 1; i <= ((latestHour + 1) - (earliestHour - 1) + 1); i++) {
                g.drawLine(0, 30 + yDivider * i, width, 30 + yDivider * i);
            }

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
            for(SectionExample se : sectionExample) {
                int mult = Integer.parseInt(se.meetingTimes[0][0].substring(0, 2)) - (earliestHour - 1);
                int partial = (int) ((Double.parseDouble(se.meetingTimes[0][0].substring(3)) / 60.0) * yDivider);
                int mult2 = Integer.parseInt(se.meetingTimes[0][1].substring(0, 2)) - (earliestHour - 1);
                int partial2 = (int) ((Double.parseDouble(se.meetingTimes[0][1].substring(3)) / 60.0) * yDivider);
                for(String a : se.daysOfWeek) {
                    switch (a) {
                        case "Su": {
                            g.setColor(Color.PINK);
                            g.fillRect(1, (31) + (mult * yDivider) + (partial), xDivider - 1, ((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial)));
                            g.setColor(Color.BLACK);
                            g.drawString(se.name, SUNDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth(se.name) / 2), (31) + (mult * yDivider) + (partial) + (((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial))) / 2);
                            break;
                        }
                        case "M": {
                            g.setColor(Color.PINK);
                            g.fillRect(1 + xDivider, (31) + (mult * yDivider) + partial, xDivider - 1, ((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial)));
                            g.setColor(Color.BLACK);
                            g.drawString(se.name, MONDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth(se.name) / 2), (31) + (mult * yDivider) + (partial) + (((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial))) / 2);
                            break;
                        }
                        case "T": {
                            g.setColor(Color.PINK);
                            g.fillRect(1 + 2 * xDivider, (31) + (mult * yDivider) + partial, xDivider - 1, ((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial)));
                            g.setColor(Color.BLACK);
                            g.drawString(se.name, TUESDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth(se.name) / 2), (31) + (mult * yDivider) + (partial) + (((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial))) / 2);
                            break;
                        }
                        case "W": {
                            g.setColor(Color.PINK);
                            g.fillRect(1 + 3 * xDivider, (31) + (mult * yDivider) + partial, xDivider - 1, ((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial)));
                            g.setColor(Color.BLACK);
                            g.drawString(se.name, WEDNESDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth(se.name) / 2), (31) + (mult * yDivider) + (partial) + (((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial))) / 2);
                            break;
                        }
                        case "R": {
                            g.setColor(Color.PINK);
                            g.fillRect(1 + 4 * xDivider, (31) + (mult * yDivider) + partial, xDivider - 1, ((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial)));
                            g.setColor(Color.BLACK);
                            g.drawString(se.name, THURSDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth(se.name) / 2), (31) + (mult * yDivider) + (partial) + (((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial))) / 2);
                            break;
                        }
                        case "F": {
                            g.setColor(Color.PINK);
                            g.fillRect(1 + 5 * xDivider, (31) + (mult * yDivider) + partial, xDivider - 1, ((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial)));
                            g.setColor(Color.BLACK);
                            g.drawString(se.name, FRIDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth(se.name) / 2), (31) + (mult * yDivider) + (partial) + (((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial))) / 2);
                            break;
                        }
                        case "Sa": {
                            g.setColor(Color.PINK);
                            g.fillRect(1 + 6 * xDivider, (31) + (mult * yDivider) + partial, xDivider - 1, ((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial)));
                            g.setColor(Color.BLACK);
                            g.drawString(se.name, SATURDAY_XLOC_CENTER - (g.getFontMetrics().stringWidth(se.name) / 2), (31) + (mult * yDivider) + (partial) + (((31) + (mult2 * yDivider) + (partial2)) - ((31) + (mult * yDivider) + (partial))) / 2);
                            break;
                        }
                    }
                }
            }
        }
    };

    public GeneratedScheduleFrame() {
        setLayout(new MigLayout("", "[grow,fill]", "[][grow,fill]"));
        sectionExample.add(new SectionExample("PSY305", new String[]{"M", "W", "F"}, new String[][]{{"11.30", "12.25"}}));
        sectionExample.add(new SectionExample("CSC385", new String[]{"M", "W", "F"}, new String[][]{{"13.50", "14.45"}}));
        sectionExample.add(new SectionExample("MAT318", new String[]{"T", "R"}, new String[][]{{"11.10", "12.30"}}));
        sectionExample.add(new SectionExample("CSC420", new String[]{"T", "R"}, new String[][]{{"14.20", "15.40"}}));

        JPanel makenewfilehere = new JPanel(new MigLayout("", "[grow,fill][][][][][grow,fill]", "[]"));
        try {
            back = new JButton(new ImageIcon(ImageIO.read(new File("src\\main\\resources\\ic_back_black_24dp.png"))));
            forward = new JButton(new ImageIcon(ImageIO.read(new File("src\\main\\resources\\ic_forward_black_24dp.png"))));

            back.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createEmptyBorder()));
            back.setContentAreaFilled(false);
            back.setFocusPainted(false);
            back.setOpaque(false);

            forward.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createEmptyBorder()));
            forward.setContentAreaFilled(false);
            forward.setFocusPainted(false);
            forward.setOpaque(false);

            back.addActionListener(this);

            makenewfilehere.add(back, "cell 1 0");
            makenewfilehere.add(new JLabel("Schedule"), "cell 2 0");

            options.add("1");
            options.add("2");
            options.add("3");

            JComboBox<Object> comboBox = new JComboBox<>(options.toArray());
            ((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            makenewfilehere.add(comboBox, "cell 3 0");
            makenewfilehere.add(forward, "cell 4 0");
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(makenewfilehere, "growx, wrap");
        add(calendar, "growx, growy");
    }

    public void fillSchedulePanel(Schedule sch) {

    }

    public void addClassToCalendar() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}