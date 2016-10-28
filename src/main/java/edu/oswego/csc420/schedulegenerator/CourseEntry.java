package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CourseEntry extends JPanel implements ActionListener {
    boolean collapsed;
    CourseEntry(String courseLabel, boolean collapsed) {
        this.collapsed = collapsed;
        setLayout(new MigLayout("","[grow,fill][]","[grow,fill]"));
        setBackground(Color.LIGHT_GRAY);
        JButton icb = new JButton();
        try {
            Image img;
            if(collapsed) {
                img = ImageIO.read(new File("src\\main\\resources\\ic_expand_more_black_18dp.png"));
            }
            else {
                img = ImageIO.read(new File("src\\main\\resources\\ic_expand_less_black_18dp.png"));
            }
            icb.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        icb.setBackground(Color.LIGHT_GRAY);
        icb.setBorder(BorderFactory.createEmptyBorder(9, 5, 9, 5));
        icb.addActionListener(this);
        add(icb, "dock east");
        JLabel lb = new JLabel(courseLabel, SwingConstants.CENTER);
        lb.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        add(lb, "dock north");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GUI jf = (GUI)SwingUtilities.getRoot((Component)e.getSource());
        CardLayout cl = (CardLayout)jf.cards.getLayout();
        if(collapsed) {
            cl.show(jf.cards, "EXP");
        }
        else {
            cl.show(jf.cards, "ACC");
        }
    }
}
