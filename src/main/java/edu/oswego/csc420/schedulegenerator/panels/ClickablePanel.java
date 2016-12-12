package edu.oswego.csc420.schedulegenerator.panels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * JPanel with built in mouse click listener.
 */
public abstract class ClickablePanel extends UpdatablePanel implements MouseListener {
    private final List<BiConsumer<MouseEvent, ClickablePanel>> listeners;

    /**
     * Constructor.
     */
    ClickablePanel() {
        listeners = new ArrayList<>();
        addMouseListener(this);
    }

    /**
     * Adds a listener which runs whenever a mouse clicks this panel.
     *
     * @param listener the listener to run when a mouse clicks this panel.
     */
    public void addClickListener(final BiConsumer<MouseEvent, ClickablePanel> listener) {
        listeners.add(listener);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        listeners.parallelStream().forEach(l -> l.accept(e, this));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
