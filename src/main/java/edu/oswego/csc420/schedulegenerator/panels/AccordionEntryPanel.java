package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.GUI;
import net.miginfocom.swing.MigLayout;

import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * The Accordion Entry Panel. This panel represents a singular entry in the Accordion.
 * It contains the Course Header Panel which is shown when the Accordion Entry is not clicked,
 * and the Course Panel which shows up when the Accordion Entry has been clicked.
 */
public class AccordionEntryPanel extends ClickablePanel {
    private final Course course;
    private final CoursePanel coursePanel;
    private final AccordionHeaderPanel accordionHeaderPanel;
    private boolean isMinimized;

    /**
     * The constructor.
     *
     * @param course the course that will be used to populate this panel.
     * @param clickListener the click listener to be ran when the header is clicked.
     */
    AccordionEntryPanel(final Course course, final BiConsumer<MouseEvent, ClickablePanel> clickListener) {
        // Set the variables
        this.course               = course;
        this.accordionHeaderPanel = new AccordionHeaderPanel(course);
        this.coursePanel          = new CoursePanel(course, accordionHeaderPanel::update);
        this.isMinimized          = true;
        // Set the panel properties
        setLayout(new MigLayout("insets 0, wrap, gap rel 0", "[grow, fill]", "[][grow, fill]"));
        add(accordionHeaderPanel);
        accordionHeaderPanel.addClickListener((e, p) -> clickListener.accept(e, this));
        update();
    }

    /**
     * Shows the course panel.
     */
    public void expand() {
        if(Arrays.stream(getComponents()).noneMatch(c -> c == coursePanel)) {
            add(coursePanel);
        }
        isMinimized = false;
        update();
    }

    /**
     * Hides the course panel.
     */
    public void contract() {
        remove(coursePanel);
        isMinimized = true;
        update();
    }

    /**
     * Returns the course that this Entry Panel is based on.
     *
     * @return the course that this Entry Panel is based on.
     */
    public Course getCourse() {
        return course;
    }

    public boolean isMinimized() {
        return isMinimized;
    }
}
