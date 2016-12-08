package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.components.SGEditButton;
import edu.oswego.csc420.schedulegenerator.components.SGTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseInfoEntryPanel extends JPanel implements ActionListener {
    String subject = "", courseNumber = "", name = "";
    boolean editMode = true;
    SGEditButton edit;
    SGTextField subjectField = new SGTextField();
    SGTextField courseNumberField = new SGTextField();
    SGTextField nameField = new SGTextField();
    JCheckBox optionalField = new JCheckBox();
    Course newCourse;

    CourseInfoEntryPanel(Course nC) {
        setLayout(new MigLayout("","[grow,fill][grow,fill][grow,fill][grow,fill][]","[][]"));
        newCourse = nC;

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
            }
        });

        add(new JLabel("Subject"));
        add(new JLabel("Course Number"));
        add(new JLabel("Name"));
        add(new JLabel("Optional"));

        edit = new SGEditButton();
        edit.addActionListener(this);
        add(edit, "wrap");
        subjectField.setEnabled(editMode);
        add(subjectField);
        courseNumberField.setEnabled(editMode);
        add(courseNumberField);
        nameField.setEnabled(editMode);
        add(nameField);
        optionalField.addActionListener(this);
        optionalField.setEnabled(editMode);
        add(optionalField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof SGEditButton) {
            edit.switchState();
            editMode = !editMode;
            subjectField.setEnabled(editMode);
            courseNumberField.setEnabled(editMode);
            nameField.setEnabled(editMode);
            optionalField.setEnabled(editMode);

            if(!subjectField.getText().equals("") && !courseNumberField.getText().equals("") && !nameField.getText().equals("") && !editMode) {
                ((CoursePanel)nameField.getParent().getParent().getParent()).courseHeaderPanel.icb.setEnabled(true);
            }
            else {
                ((CoursePanel)nameField.getParent().getParent().getParent()).courseHeaderPanel.icb.setEnabled(false);
            }
        }
        else if(e.getSource() instanceof JCheckBox){
            newCourse.setOptional(optionalField.isSelected());
        }
    }
}
