package edu.oswego.csc420.schedulegenerator.frames;

import edu.oswego.csc420.schedulegenerator.Colors;
import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.GUI;
import edu.oswego.csc420.schedulegenerator.JButton;
import edu.oswego.csc420.schedulegenerator.JTextField;
import edu.oswego.csc420.schedulegenerator.Section;
import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static edu.oswego.csc420.schedulegenerator.JButton.ButtonStyle.PRIMARY;

public class NewSectionFrame extends JDialog implements ActionListener {

    JTextField newSectionNum, newCRN, newTeacher;
    JButton addSection, editSection;
    Course course;
    Section createdSection, sec;
    GUI rootFrame;

    public NewSectionFrame(Course cE, JFrame root) {
        super(root, ModalityType.DOCUMENT_MODAL);
        rootFrame = (GUI)root;
        rootFrame.setDialogShown(true);
        setUndecorated(true);
        course = cE;
        getContentPane().setBackground(Colors.LIGHT_PRIMARY.getColor());
        setLayout(new MigLayout("","[grow,fill]",""));
        setMinimumSize(new Dimension(280, 200));
        // setTitle("New Section");
        add(new JLabel("Section #"),"wrap");
        add(newSectionNum = new JTextField(""),"wrap");
        add(new JLabel("CRN"),"wrap");
        add(newCRN = new JTextField(""),"wrap");
        add(new JLabel("Teacher"),"wrap");
        add(newTeacher = new JTextField(""),"wrap");
        add(addSection = new JButton("Add Section", PRIMARY));
        addSection.addActionListener(this);
        this.setResizable(false);
        this.pack();
        setLocation(((root.getWidth() - 1) / 2) - 140, ((root.getHeight() - 1) / 2) - 100);
        rootFrame.repaint();
    }

    public NewSectionFrame(Section section, JFrame root){
        super(root, ModalityType.DOCUMENT_MODAL);
        sec = section;
        rootFrame = (GUI)root;
        rootFrame.setDialogShown(true);
        getContentPane().setBackground(Colors.LIGHT_PRIMARY.getColor());
        setUndecorated(true);
        setLayout(new MigLayout("","[grow,fill]",""));
        setMinimumSize(new Dimension(280, 200));
        // setTitle("New Section");
        add(new JLabel("Section #"),"wrap");
        add(newSectionNum = new JTextField(""),"wrap");
        newSectionNum.setText(sec.getSectionNumber());
        add(new JLabel("CRN"),"wrap");
        add(newCRN = new JTextField(""),"wrap");
        newCRN.setText(sec.getCrn());
        add(new JLabel("Teacher"),"wrap");
        add(newTeacher = new JTextField(""),"wrap");
        newTeacher.setText(sec.getTeacher());
        add(editSection = new JButton("Finished Editing", PRIMARY));
        editSection.addActionListener(this);
        this.setResizable(false);
        this.pack();
        setLocation(((root.getWidth() - 1) / 2) - 140, ((root.getHeight() - 1) / 2) - 100);
        rootFrame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String errorMessage = "";

        if(newSectionNum.getText().equals("")){
            errorMessage += "Need to Enter a Section Number!" + System.lineSeparator();
        }
        if(newCRN.getText().equals("")){
            errorMessage += "Need to Enter a CRN!" + System.lineSeparator();
        }
        if(newTeacher.getText().equals("")){
            errorMessage += "Need to Enter a Teacher!" + System.lineSeparator();
        }

        if(e.getSource() == addSection) {
            if (errorMessage.equals("")) {
                createdSection = new Section(newSectionNum.getText(), newCRN.getText(), newTeacher.getText());
                course.addSection(createdSection);
                new NewMeetingTimeFrame(createdSection, rootFrame).setVisible(true);
                this.setVisible(false);
                rootFrame.setDialogShown(false);
                rootFrame.repaint();
            }
            else{
                JOptionPane.showMessageDialog(null, errorMessage);
            }
        }
        if(e.getSource() == editSection) {
            if (errorMessage.equals("")) {
                sec.setSectionNumber(newSectionNum.getText());
                sec.setCrn(newCRN.getText());
                sec.setTeacher(newTeacher.getText());
                this.setVisible(false);
                rootFrame.setDialogShown(false);
                rootFrame.repaint();
            }
            else {
                JOptionPane.showMessageDialog(null, errorMessage);
            }
        }
    }

    private class JLabel extends javax.swing.JLabel {

        private JLabel(final String text) {
            super(text);
            setForeground(Color.WHITE);
        }
    }
}
