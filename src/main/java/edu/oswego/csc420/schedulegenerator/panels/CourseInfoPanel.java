package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.Generator;
import edu.oswego.csc420.schedulegenerator.Section;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class CourseInfoPanel extends JPanel {

    Course newCourse = new Course("", "", "", false);
    SectionPanel sp;
    SectionInformationPanel sI;

    CourseInfoPanel(Generator gen) {
        setLayout(new MigLayout("","[][]","[grow,fill][grow,fill]"));
        add(new CourseInfoEntryPanel(newCourse), "span, growx, wrap 2");
        sI = new SectionInformationPanel(gen, newCourse);
        add(sp = new SectionPanel(sI, gen, newCourse));
        add(sI);
    }
}
