package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class NewSectionFrame extends JFrame {

    NewSectionFrame() {
        setLayout(new MigLayout("","[grow,fill]",""));
        setMinimumSize(new Dimension(280,240));
        setTitle("New Section");
        add(new JLabel("Section #"),"wrap");
        add(new JTextField(),"wrap");
        add(new JLabel("CRN"),"wrap");
        add(new JTextField(),"wrap");
        add(new JLabel("Teacher"),"wrap");
        add(new JTextField(),"wrap");
        add(new JButton("Add Section"));
        this.setResizable(false);
        this.pack();
    }
}
