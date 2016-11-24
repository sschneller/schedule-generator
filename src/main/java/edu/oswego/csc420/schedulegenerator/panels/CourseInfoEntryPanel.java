package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.components.SGTextField;
import net.miginfocom.swing.MigLayout;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CourseInfoEntryPanel extends JPanel implements ActionListener {
    String subject = "", courseNumber = "", name = "";
    boolean editMode = false;
    JButton edit;
    SGTextField subjectField = new SGTextField();
    SGTextField courseNumberField = new SGTextField();
    SGTextField nameField = new SGTextField();
    JCheckBox optionalField = new JCheckBox();
    Course newCourse;

    CourseInfoEntryPanel() {
        setLayout(new MigLayout("","[grow,fill][grow,fill][grow,fill][grow,fill][]","[][]"));
        newCourse = new Course(name, subject, courseNumber, optionalField.isSelected());
        subjectField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                subject = subjectField.getText();
                newCourse.setSubject(subject);
                ((CoursePanel)subjectField.getParent().getParent().getParent()).setTitle(subject + courseNumber + name);

                if(!subjectField.getText().equals("") && !courseNumberField.getText().equals("") && !nameField.getText().equals("")) {
                    ((CoursePanel)subjectField.getParent().getParent().getParent()).courseHeaderPanel.icb.setEnabled(true);
                }
                else {
                    ((CoursePanel)subjectField.getParent().getParent().getParent()).courseHeaderPanel.icb.setEnabled(false);
                }
            }
        });

        courseNumberField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                courseNumber = courseNumberField.getText();
                newCourse.setCourseNumber(courseNumber);
                ((CoursePanel)courseNumberField.getParent().getParent().getParent()).setTitle(subject + courseNumber + name);

                if(!subjectField.getText().equals("") && !courseNumberField.getText().equals("") && !nameField.getText().equals("")) {
                    ((CoursePanel)courseNumberField.getParent().getParent().getParent()).courseHeaderPanel.icb.setEnabled(true);
                }
                else {
                    ((CoursePanel)courseNumberField.getParent().getParent().getParent()).courseHeaderPanel.icb.setEnabled(false);
                }
            }
        });

        nameField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if(nameField.getText().equals("")) {
                    name = "";
                }
                else {
                    newCourse.setName(nameField.getText());
                    name = " - " + nameField.getText();
                }
                ((CoursePanel)nameField.getParent().getParent().getParent()).setTitle(subject + courseNumber + name);

                if(!subjectField.getText().equals("") && !courseNumberField.getText().equals("") && !nameField.getText().equals("")) {
                    ((CoursePanel)nameField.getParent().getParent().getParent()).courseHeaderPanel.icb.setEnabled(true);
                }
                else {
                    ((CoursePanel)nameField.getParent().getParent().getParent()).courseHeaderPanel.icb.setEnabled(false);
                }
            }
        });

        add(new JLabel("Subject"));
        add(new JLabel("Course Number"));
        add(new JLabel("Name"));
        add(new JLabel("Optional"));

        edit = new JButton();
        edit.addActionListener(this);
        edit.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        Image img;
        try {
            img = ImageIO.read(new File("src\\main\\resources\\ic_mode_edit_black_18dp.png"));
            edit.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(edit, "wrap");
        subjectField.setEnabled(false);
        add(subjectField);
        courseNumberField.setEnabled(false);
        add(courseNumberField);
        nameField.setEnabled(false);
        add(nameField);
        optionalField.addActionListener(this);
        optionalField.setEnabled(false);
        add(optionalField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            Image img;
            try {
                if (editMode) {
                    img = ImageIO.read(new File("src\\main\\resources\\ic_mode_edit_black_18dp.png"));
                    edit.setIcon(new ImageIcon(img));
                    edit.repaint();
                    editMode = false;
                    subjectField.setEnabled(editMode);
                    courseNumberField.setEnabled(editMode);
                    nameField.setEnabled(editMode);
                    optionalField.setEnabled(editMode);
                } else {
                    img = ImageIO.read(new File("src\\main\\resources\\ic_check_black_18dp.png"));
                    edit.setIcon(new ImageIcon(img));
                    edit.repaint();
                    editMode = true;
                    subjectField.setEnabled(editMode);
                    courseNumberField.setEnabled(editMode);
                    nameField.setEnabled(editMode);
                    optionalField.setEnabled(editMode);
                }
            } catch (IOException ed) {
                ed.printStackTrace();
            }
        }
        else if(e.getSource() instanceof JCheckBox){
            if(optionalField.isSelected()) {
                newCourse.setOptional(true);
            }
            else{
                newCourse.setOptional(false);
            }
        }
    }
}
