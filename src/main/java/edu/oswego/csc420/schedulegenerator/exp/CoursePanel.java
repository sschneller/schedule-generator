package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.Colors;
import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.JTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;

public class CoursePanel extends UpdatablePanel {
    private final Course course;
    private final Runnable listener;
    private final JCheckBox optional;
    private final JTextField subject, number, name;

    public CoursePanel(final Course course, final Runnable listener) {
        this.course    = course;
        this.name      = new JTextField(course.getName(), t -> update());
        this.number    = new JTextField(course.getCourseNumber(), t -> update());
        this.subject   = new JTextField(course.getSubject(), t -> update());
        this.listener  = listener;
        this.optional  = new JCheckBox();
        optional.setBackground(new Color(0,0,0,0));

        setLayout(new MigLayout("","[grow,fill][grow,fill][grow,fill][grow,fill]","[][][grow,fill]"));
        add(new JLabel("Course Subject:"));
        add(new JLabel("Course Number:"));
        add(new JLabel("Course Name:"));
        add(new JLabel("Optional?"), "wrap");
        add(subject);
        add(number);
        add(name);
        add(optional, "wrap");
        final SectionInformationPanel sectionInformationPanel = new SectionInformationPanel();
        add(new SectionPanel(course, sectionInformationPanel), "span 2");
        add(sectionInformationPanel, "span 2");

        setMinimumSize(new Dimension(400,400));
        setBackground(Colors.PRIMARY);
    }

    @Override
    public void update() {
        course.setName(name.getText());
        course.setSubject(subject.getText());
        course.setCourseNumber(number.getText());
        course.setOptional(optional.isSelected());
        listener.run();
        super.update();
    }

    private class JLabel extends javax.swing.JLabel {

        JLabel(final String text) {
            super(text);
            setForeground(Color.WHITE);
        }
    }
}
