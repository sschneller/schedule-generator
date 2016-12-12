package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.GUI;
import edu.oswego.csc420.schedulegenerator.Section;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSectionFrame extends JDialog implements ActionListener {

    JTextField newSectionNum, newCRN, newTeacher;
    JButton addSection;
    Course course;
    Section createdSection;
    GUI rootFrame;

    public NewSectionFrame(Course cE, JFrame root) {
        super(root, ModalityType.DOCUMENT_MODAL);
        rootFrame = (GUI)root;
        rootFrame.setDialogShown(true);
        setUndecorated(true);
        course = cE;
        setLayout(new MigLayout("","[grow,fill]",""));
        setMinimumSize(new Dimension(280, 200));
        // setTitle("New Section");
        add(new JLabel("Section #"),"wrap");
        add(newSectionNum = new JTextField(),"wrap");
        add(new JLabel("CRN"),"wrap");
        add(newCRN = new JTextField(),"wrap");
        add(new JLabel("Teacher"),"wrap");
        add(newTeacher = new JTextField(),"wrap");
        add(addSection = new JButton("Add Section"));
        addSection.addActionListener(this);
        this.setResizable(false);
        this.pack();
        setLocation(((root.getWidth() - 1) / 2) - 140, ((root.getHeight() - 1) / 2) - 100);
        rootFrame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(newSectionNum.getText().equals("")) && !(newCRN.getText().equals("")) && !(newTeacher.getText().equals(""))){
            createdSection = new Section(newSectionNum.getText(), newCRN.getText(), newTeacher.getText());
            course.addSection(createdSection);
            new NewMeetingTimeFrame(createdSection, rootFrame).setVisible(true);
            this.setVisible(false);
        }
        else{
            //Add pop up message saying to enter field
        }
    }
}
