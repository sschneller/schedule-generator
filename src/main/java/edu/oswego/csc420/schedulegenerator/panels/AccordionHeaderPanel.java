package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Colors;
import edu.oswego.csc420.schedulegenerator.Course;
import net.miginfocom.swing.MigLayout;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(((AccordionEntryPanel)getParent()).isMinimized()) {
            try {
                Image i = new ImageIcon(ImageIO.read(new File("src\\main\\resources\\ic_expand_more_black_18dp.png"))).getImage();
                g.drawImage(i, getWidth() - i.getWidth(null) - 10, getHeight()/2 - i.getHeight(null)/2, null);
            }
            catch(IOException ioe) {}
        }
        else {
            try {
                Image i = new ImageIcon(ImageIO.read(new File("src\\main\\resources\\ic_expand_less_black_18dp.png"))).getImage();
                g.drawImage(i, getWidth() - i.getWidth(null) - 10, getHeight()/2 - i.getHeight(null)/2, null);
            }
            catch(IOException ioe) {}
        }
    }
}
