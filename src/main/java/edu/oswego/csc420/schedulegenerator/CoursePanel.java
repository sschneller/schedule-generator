package edu.oswego.csc420.schedulegenerator;

import edu.oswego.csc420.schedulegenerator.components.SGTextField;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CoursePanel extends JPanel implements ActionListener {
    String subject = "", courseNumber = "", name = "";
    boolean editMode = false;
    JButton edit;
    SGTextField subjectField = new SGTextField();
    SGTextField courseNumberField = new SGTextField();
    SGTextField nameField = new SGTextField();
    JCheckBox optionalField = new JCheckBox();

    CoursePanel() {
        setLayout(new MigLayout("","[][]","[grow,fill][grow,fill]"));

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
                JPanel parent = (JPanel)subjectField.getParent().getParent().getParent();
                JLabel centry = (JLabel)((CourseEntry)parent.getComponent(0)).getComponent(1);
                subject = subjectField.getText();
                centry.setText(subject + courseNumber + name);
                parent.repaint();
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
                JPanel parent = (JPanel)courseNumberField.getParent().getParent().getParent();
                JLabel centry = (JLabel)((CourseEntry)parent.getComponent(0)).getComponent(1);
                courseNumber = courseNumberField.getText();
                centry.setText(subject + courseNumber + name);
                parent.repaint();
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
                JPanel parent = (JPanel)nameField.getParent().getParent().getParent();
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

        JPanel test = new JPanel(new MigLayout("","[grow,fill][grow,fill][grow,fill][grow,fill][]","[][]"));
        test.add(new JLabel("Subject"));
        test.add(new JLabel("Course Number"));
        test.add(new JLabel("Name"));
        test.add(new JLabel("Optional"));
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

        test.add(edit, "wrap");
        subjectField.setEnabled(false);
        test.add(subjectField);
        courseNumberField.setEnabled(false);
        test.add(courseNumberField);
        nameField.setEnabled(false);
        test.add(nameField);
        optionalField.setEnabled(false);
        test.add(optionalField);

        add(test, "span, growx, wrap 2");
        add(new SectionPanel());
        add(new SectionInformationPanel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton edited = (JButton)e.getSource();
        Image img;
        try {
            if(editMode) {
                img = ImageIO.read(new File("src\\main\\resources\\ic_mode_edit_black_18dp.png"));
                edit.setIcon(new ImageIcon(img));
                edit.repaint();
                editMode = false;
                subjectField.setEnabled(editMode);
                courseNumberField.setEnabled(editMode);
                nameField.setEnabled(editMode);
                optionalField.setEnabled(editMode);
            }
            else {
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
}
