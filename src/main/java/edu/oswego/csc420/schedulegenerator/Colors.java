package edu.oswego.csc420.schedulegenerator;

import java.awt.Color;

public enum  Colors {
    DARK_PRIMARY(55, 90, 127),
    LIGHT_PRIMARY(34,34,34),
    PRIMARY(48,48,48),
    ACCENT(70,69,69),
    BUTTON(55,90,127),
    BUTTON_HOVER(40,65,91),
    BUTTON_TEXT(Color.WHITE),
    BUTTON_DISABLED(47,70,94),
    BUTTON_DISABLED_TEXT(162,165,167),
    TEXT_FIELD(70,69,69),
    TEXT_FIELD_TEXT(Color.WHITE);

    final Color color;

    Colors(final Color color) {
        this.color = color;
    }

    Colors(final int r, final int g, final int b) {
        color = new Color(r,g,b);
    }

    public Color getColor() {
        return color;
    }
}
