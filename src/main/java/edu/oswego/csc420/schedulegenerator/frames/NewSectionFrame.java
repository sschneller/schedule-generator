package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.Course;
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
    JFrame rootPane;

    public NewSectionFrame(Course cE, JFrame root) {
        super(root, ModalityType.DOCUMENT_MODAL);
        setUndecorated(true);
        setLocationRelativeTo(root);
        course = cE;
        rootPane = root;
        setLayout(new MigLayout("","[grow,fill]",""));
        setMinimumSize(new Dimension(280,240));
        setTitle("New Section");
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(newSectionNum.getText().equals("")) && !(newCRN.getText().equals("")) && !(newTeacher.getText().equals(""))){
            createdSection = new Section(newSectionNum.getText(), newCRN.getText(), newTeacher.getText());
            course.addSection(createdSection);
            new NewMeetingTimeFrame(createdSection, rootPane).setVisible(true);
            this.setVisible(false);
        }
        else{
            //Add pop up message saying to enter field
        }
    }
}
