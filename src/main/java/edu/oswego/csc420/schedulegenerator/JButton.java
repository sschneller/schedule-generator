package edu.oswego.csc420.schedulegenerator;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JButton extends javax.swing.JButton {
    private boolean enabled;

    public JButton(final ImageIcon icon) {
        super(icon);
    }

    public JButton(final String text, final ActionListener actionListener) {
        super(text);
        enabled = true;
        addActionListener(actionListener);
        setForeground(Colors.BUTTON_TEXT.getColor());
        setBackground(Colors.BUTTON.getColor());
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
                if(enabled) {
                    JButton.this.setBackground(Colors.BUTTON_HOVER.getColor());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(enabled) {
                    JButton.this.setBackground(Colors.BUTTON.getColor());
                }
            }
        });
    }

    @Override
    public void setEnabled(boolean b) {
        enabled = b;
        super.setEnabled(b);
    }

    private enum ButtonStyle {
    }
}
