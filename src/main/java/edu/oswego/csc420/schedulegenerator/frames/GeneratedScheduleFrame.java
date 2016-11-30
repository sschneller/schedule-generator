package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.Schedule;
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
    int earliestHour = 11, latestHour = 17; // Example Input - Should be start time of earliest class, and end time of the latest class for entire week
                                            // Needs to be military time, and round down to hour for first, and round up for last

    JPanel calendar = new JPanel(new MigLayout("insets 5 5", "[grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,fill]", "[][grow,fill]")) {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            height = getHeight() - 1;
            width = getWidth() - 1;
            xDivider = width / 7;
            try {
                yDivider = (height - 30) / ((latestHour + 1) - (earliestHour - 1));
            }
            catch(ArithmeticException ae) {
                yDivider = 0;
            }
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
            // DRAW THE BACKGROUND BORDER AND STUFF
            g.setColor(Color.WHITE);
            g.fillRoundRect(0, 0, width, height, 35, 35);
            g.setColor(Color.BLACK);
            g.drawRoundRect(0, 0, width, height, 35, 35);
            g.drawLine(0, 30, width, 30);
            // DRAW THE LINES TO DIVIDE THE WEEK DAYS
            g.drawLine(xDivider, 0, xDivider, height);
            g.drawLine(xDivider * 2, 0, xDivider * 2, height);
            g.drawLine(xDivider * 3, 0, xDivider * 3, height);
            g.drawLine(xDivider * 4, 0, xDivider * 4, height);
            g.drawLine(xDivider * 5, 0, xDivider * 5, height);
            g.drawLine(xDivider * 6, 0, xDivider * 6, height);
            // DRAW THE WEEKDAY NAMES
            g.drawString("Sunday", (xDivider/2) - (g.getFontMetrics().stringWidth("Sunday")/2), 30 - (g.getFontMetrics().getHeight()/2));
            g.drawString("Monday", (3 * xDivider/2) - (g.getFontMetrics().stringWidth("Monday")/2), 30 - (g.getFontMetrics().getHeight()/2));
            g.drawString("Tuesday", (5 * xDivider/2) - (g.getFontMetrics().stringWidth("Tuesday")/2), 30 - (g.getFontMetrics().getHeight()/2));
            g.drawString("Wednesday", (7 * xDivider/2) - (g.getFontMetrics().stringWidth("Wednesday")/2), 30 - (g.getFontMetrics().getHeight()/2));
            g.drawString("Thursday", (9 * xDivider/2) - (g.getFontMetrics().stringWidth("Thursday")/2), 30 - (g.getFontMetrics().getHeight()/2));
            g.drawString("Friday", (11 * xDivider/2) - (g.getFontMetrics().stringWidth("Friday")/2), 30 - (g.getFontMetrics().getHeight()/2));
            g.drawString("Saturday", (13 * xDivider/2) - (g.getFontMetrics().stringWidth("Saturday")/2), 30 - (g.getFontMetrics().getHeight()/2));

            for(int i = 1; i <= ((latestHour + 1) - (earliestHour - 1)); i++) {
                g.drawLine(0, 30 + yDivider * i, width, 30 + yDivider * i);
            }
        }
    };

    public GeneratedScheduleFrame() {
        setLayout(new MigLayout("", "[grow,fill]", "[][grow,fill]"));
        JButton back;
        JButton forward;
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
            ArrayList<String> options = new ArrayList<>();
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

    private static void createGui() {
        JFrame frame = new GeneratedScheduleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(890,650);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> createGui());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
