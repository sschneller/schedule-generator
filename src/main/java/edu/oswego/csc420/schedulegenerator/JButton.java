package edu.oswego.csc420.schedulegenerator;

import javax.swing.ImageIcon;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JButton extends javax.swing.JButton {
    private boolean enabled;

    public JButton(final ImageIcon icon) {
        super(icon);
    }

    public JButton(final String text, final ButtonStyle style) {
        super(text);
        enabled = true;
        setForeground(style.getText());
        setBackground(style.getPrimary());
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
                    JButton.this.setBackground(style.getHover());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(enabled) {
                    JButton.this.setBackground(style.getPrimary());
                }
            }
        });
    }

    public JButton(final String text, final ButtonStyle style, final ActionListener actionListener) {
        this(text, style);
        addActionListener(actionListener);
    }

    @Override
    public void setEnabled(boolean b) {
        enabled = b;
        super.setEnabled(b);
    }

    @Override
    public void paint(Graphics g) {
        if(!enabled) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.65));
        }
        super.paint(g);
    }

    public enum ButtonStyle {
        DEFAULT(new Color(70,69, 69), new Color(44, 44, 44)),
        PRIMARY(new Color(55,90,127), new Color(40, 65, 91)),
        SUCCESS(new Color(0, 188, 140), new Color(0, 137, 102)),
        INFO(new Color(52, 152, 219), new Color(33, 125, 187)),
        WARNING(new Color(243, 156, 18), new Color(200, 127, 10)),
        DANGER(new Color(231, 76, 60), new Color(214, 44, 26));

        final Color text, hover, primary;

        ButtonStyle(final Color primary, final Color hover) {
            this.text    = Color.WHITE;
            this.hover   = hover;
            this.primary = primary;
        }

        public Color getText() {
            return text;
        }

        public Color getPrimary() {
            return primary;
        }

        public Color getHover() {
            return hover;
        }
    }
}
