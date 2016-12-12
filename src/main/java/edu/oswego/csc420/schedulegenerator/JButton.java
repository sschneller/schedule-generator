package edu.oswego.csc420.schedulegenerator;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JButton extends javax.swing.JButton {

    public JButton(final String text, final ActionListener actionListener) {
        super(text);
        addActionListener(actionListener);
        setBackground(Colors.ACCENT.getColor());
        setRolloverEnabled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
