package edu.oswego.csc420.schedulegenerator.components;

import javax.swing.*;
import java.awt.*;

public class SGTextField extends JTextField {
    private boolean enabled;

    public SGTextField() {
        enabled = true;
        repaint();
    }

    @Override
    public void setEnabled(boolean en) {
        enabled = en;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(enabled) {
            this.setEditable(true);
            this.setBackground(Color.WHITE);
        }
        else {
            this.setEditable(false);
            this.setBackground(Color.LIGHT_GRAY);
        }
    }
}
