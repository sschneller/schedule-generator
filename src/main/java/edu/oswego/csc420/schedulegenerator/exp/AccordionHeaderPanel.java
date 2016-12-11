package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.Colors;
import edu.oswego.csc420.schedulegenerator.Course;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

/**
 * The Accordion Header Panel. This is what is shown when an Accordion Entry is contracted.
 */
public class AccordionHeaderPanel extends ClickablePanel {
    private final Course course;
    private final JLabel label;

    /**
     * The Constructor.
     *
     * @param course the course the Header Panel updates itself from.
     */
    AccordionHeaderPanel(final Course course) {
        // Set the variables
        this.course = course;
        this.label  = new JLabel("", JLabel.CENTER);
        label.setForeground(Color.white);

        // Set the panel properties
        setBackground(Colors.DARK_PRIMARY);
        setMinimumSize(new Dimension(0, 40));
        setLayout(new MigLayout("insets 0", "[grow, fill]", "[grow, fill]"));
        add(label);
        update();
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public void update() {
        label.setText(course.toString());
        super.update();
    }
}
