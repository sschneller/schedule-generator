package edu.oswego.csc420.schedulegenerator;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoursePanel extends JPanel {
    String subject = "", courseNumber = "", name = "";
    CoursePanel() {
        setLayout(new MigLayout("","[grow,fill]","[][][grow,fill]"));

        JTextField subjectField = new JTextField();
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
                JPanel parent = (JPanel)subjectField.getParent().getParent();
                JLabel centry = (JLabel)((CourseEntry)parent.getComponent(0)).getComponent(1);
                subject = subjectField.getText();
                centry.setText(subject + courseNumber + name);
                parent.repaint();
            }
        });

        JTextField courseNumberField = new JTextField();
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
                JPanel parent = (JPanel)courseNumberField.getParent().getParent();
                JLabel centry = (JLabel)((CourseEntry)parent.getComponent(0)).getComponent(1);
                courseNumber = courseNumberField.getText();
                centry.setText(subject + courseNumber + name);
                parent.repaint();
            }
        });

        JTextField nameField = new JTextField();
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
                JPanel parent = (JPanel)nameField.getParent().getParent();
                JLabel centry = (JLabel)((CourseEntry)parent.getComponent(0)).getComponent(1);
                if(nameField.getText().equals("")) {
                    name = "";
                }
                else {
                    name = " - " + nameField.getText();
                }
                centry.setText(subject + courseNumber + name);
                parent.repaint();
            }
        });

        add(new JLabel("Subject"));
        add(new JLabel("Course Number"));
        add(new JLabel("Name"));
        add(new JLabel("Optional"), "wrap");
        add(subjectField);
        add(courseNumberField);
        add(nameField);
        add(new JCheckBox(), "wrap");
        add(new SectionPanel(), "span 2");
        add(new SectionInformationPanel(), "span 2");
    }
}
