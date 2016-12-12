package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Colors;

import javax.swing.JPanel;
import java.util.Arrays;

/**
 * A JPanel which has changing information.
 */
public class UpdatablePanel extends JPanel {

    /**
     * Applies the update method to all children UpdatablePanels, revalidates, and repaints.
     */
    public void update() {
        Arrays.stream(getComponents()).filter(c -> c instanceof UpdatablePanel)
                .map(c -> (UpdatablePanel) c)
                .forEach(UpdatablePanel::update);
        revalidate();
        repaint();
    }

    public void setBackground(final Colors bg) {
        super.setBackground(bg.getColor());
    }
}
