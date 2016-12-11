package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.GUI2;
import edu.oswego.csc420.schedulegenerator.Generator;
import edu.oswego.csc420.schedulegenerator.Section;
import edu.oswego.csc420.schedulegenerator.panels.SectionInformationPanel;
import edu.oswego.csc420.schedulegenerator.panels.SectionPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSectionFrame extends JDialog implements ActionListener {

    JTextField newSectionNum, newCRN, newTeacher;
    JButton addSection;
    Generator gen;
    Course courseEdit;
    Section createdSection;
    SectionPanel sectionP;
    SectionInformationPanel secI;
    GUI2 rootFrame;

    public NewSectionFrame(SectionPanel sP, SectionInformationPanel sI, Generator g, Course cE, JFrame root) {
        super(root, ModalityType.DOCUMENT_MODAL);
        rootFrame = (GUI2)root;
        rootFrame.setDialogShown(true);
        setUndecorated(true);
        setLocationRelativeTo(root);
        sectionP = sP;
        secI = sI;
        gen = g;
        courseEdit = cE;
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
            sectionP.addSectionToTable(createdSection);
            new NewMeetingTimeFrame(secI, gen, courseEdit, createdSection, rootFrame).setVisible(true);
            this.setVisible(false);
        }
        else{
            //Add pop up message saying to enter field
        }
    }
}
