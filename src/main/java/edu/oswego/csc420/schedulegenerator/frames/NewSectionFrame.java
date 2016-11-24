package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.Section;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSectionFrame extends JFrame implements ActionListener {

    JTextField newSection, newCRN, newTeacher;
    JButton addSection;

    public NewSectionFrame() {
        setLayout(new MigLayout("","[grow,fill]",""));
        setMinimumSize(new Dimension(280,240));
        setTitle("New Section");
        add(new JLabel("Section #"),"wrap");
        add(newSection = new JTextField(),"wrap");
        add(new JLabel("CRN"),"wrap");
        add(newCRN = new JTextField(),"wrap");
        add(new JLabel("Teacher"),"wrap");
        add(newTeacher = new JTextField(),"wrap");
        add(addSection = new JButton("Add Section"));
        addSection.addActionListener(this);
        this.setResizable(false);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(newTeacher.getText().equals(""))){
            Section createdSection = new Section(newSection.getText(),newCRN.getText(),newTeacher.getText());
            //(course back end).addSection(createdSection);
            this.setVisible(false);
        }
        else{
            //Add pop up message saying to enter field
        }
    }
}
