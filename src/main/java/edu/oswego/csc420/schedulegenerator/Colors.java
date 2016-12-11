package edu.oswego.csc420.schedulegenerator;

import java.awt.Color;

public enum  Colors {
    DARK_PRIMARY(4, 38, 51),
    LIGHT_PRIMARY(46, 75, 85),
    PRIMARY(26, 57,69);

    final Color color;

    Colors(final int r, final int g, final int b) {
        color = new Color(r,g,b);
    }

    public Color getColor() {
        return color;
    }
}
