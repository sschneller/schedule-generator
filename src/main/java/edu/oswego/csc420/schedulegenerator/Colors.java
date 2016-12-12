package edu.oswego.csc420.schedulegenerator;

import java.awt.Color;

public enum  Colors {
    DARK_PRIMARY(38, 50, 56),
    LIGHT_PRIMARY(96,125,139),
    PRIMARY(55,71,79),
    ACCENT(0,121,107);

    final Color color;

    Colors(final int r, final int g, final int b) {
        color = new Color(r,g,b);
    }

    public Color getColor() {
        return color;
    }
}
