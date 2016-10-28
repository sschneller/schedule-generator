package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by sschneller on 10/27/2016.
 */
public class CourseEntry extends JPanel implements ActionListener {
    CourseEntry(String courseLabel) {
        setLayout(new MigLayout("","[grow,fill][]","[grow,fill]"));
        setBackground(Color.LIGHT_GRAY);
        JButton icb = new JButton();
        try {
            Image img = ImageIO.read(new File("src\\main\\resources\\ic_expand_more_black_18dp.png"));
            icb.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        icb.setBackground(Color.LIGHT_GRAY);
        icb.setBorder(BorderFactory.createEmptyBorder(9, 5, 9, 5));
        add(icb, "dock east");
        JLabel lb = new JLabel(courseLabel, SwingConstants.CENTER);
        lb.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        add(lb, "dock north");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        // JLabel parent = (JLabel)clickedButton.getParent().getComponent(0);
        clickedButton.setText(clickedButton.getText().replace("+", "-"));
        AccordionPanel cs = (AccordionPanel)clickedButton.getParent();
        cs.removeAll();
        this.validate();
        /*this.setLayout(new MigLayout("","[grow,fill][][]300[]","[grow,fill][]"));
        final AccordionPanel accordionPanel = new AccordionPanel();
        accordionPanel.add(new CoursePanel(), "cell 0 1 2 1");
        add(accordionPanel, "span, grow");*/
        this.repaint();
    }
}
