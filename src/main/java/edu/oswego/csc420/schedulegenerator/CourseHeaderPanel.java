package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CourseHeaderPanel extends JPanel implements ActionListener {
    boolean collapsed;
    JButton icb;
    JLabel lb;

    CourseHeaderPanel(boolean collapsed) {
        setLayout(new MigLayout("","[grow,fill][]","[grow,fill]"));
        setBackground(Color.LIGHT_GRAY);
        icb = new JButton();
        icb.setBackground(Color.LIGHT_GRAY);
        icb.setBorder(BorderFactory.createEmptyBorder(9, 5, 9, 5));
        icb.addActionListener(this);
        setCollapsed(collapsed);
        add(icb, "dock east");
        lb = new JLabel("", SwingConstants.CENTER);
        lb.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        add(lb, "dock north");
    }

    CourseHeaderPanel(String courseLabel, boolean collapsed) {
        this.collapsed = collapsed;
        setLayout(new MigLayout("","[grow,fill][]","[grow,fill]"));
        setBackground(Color.LIGHT_GRAY);
        icb = new JButton();
        icb.setBackground(Color.LIGHT_GRAY);
        icb.setBorder(BorderFactory.createEmptyBorder(9, 5, 9, 5));
        icb.addActionListener(this);
        add(icb, "dock east");
        lb = new JLabel(courseLabel, SwingConstants.CENTER);
        lb.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        add(lb, "dock north");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(collapsed) {
            setCollapsed(false);
            CardPanel cardPanel = (CardPanel)this.getParent().getParent().getParent();
            cardPanel.remove(cardPanel.courseOverview);
            cardPanel.courseOverview = cardPanel.ap.chlp.getCoursePanel(this);
            cardPanel.add(cardPanel.courseOverview, "EXP");
            cardPanel.cl.show(cardPanel, "EXP");
        }
        else {
            setCollapsed(true);
            CoursePanel cp = (CoursePanel)this.getParent();
            CardPanel cardPanel = (CardPanel)this.getParent().getParent();
            cardPanel.ap.chlp.add(this, "span");
            cardPanel.ap.chlp.putCoursePanel(this, cp);
            cardPanel.ap.chlp.repaint();
            cardPanel.cl.show(cardPanel, "ACC");
        }
    }

    public void setTitle(String title) {
        lb.setText(title);
    }

    public void setCollapsed(boolean collapsed) {
        try {
            Image img;
            if(collapsed) {
                img = ImageIO.read(new File("src\\main\\resources\\ic_expand_more_black_18dp.png"));
            }
            else {
                img = ImageIO.read(new File("src\\main\\resources\\ic_expand_less_black_18dp.png"));
            }
            icb.setIcon(new ImageIcon(img));
            icb.repaint();
            this.collapsed = collapsed;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
