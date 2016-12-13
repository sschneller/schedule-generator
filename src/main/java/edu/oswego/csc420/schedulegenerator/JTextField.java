package edu.oswego.csc420.schedulegenerator;

import javax.swing.BorderFactory;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class JTextField extends javax.swing.JTextField {
    private static Color TEXT_FIELD = new Color(70,69,69);
    private static Color TEXT_FIELD_TEXT = Color.WHITE;
    private final List<Consumer<JTextField>> listeners;

    public JTextField(final String text) {
        setText(text);
        setColumns(1);
        listeners = new ArrayList<>();
        setForeground(TEXT_FIELD_TEXT);
        setBackground(TEXT_FIELD);
        setCaretColor(TEXT_FIELD_TEXT);
        setBorder(null);
        setBorder(BorderFactory.createCompoundBorder(
                getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange();
            }

            private void onChange() {
                listeners.forEach(l -> l.accept(JTextField.this));
            }
        });
    }

    public JTextField(final String text, final Consumer<JTextField> listener) {
        this(text);
        addChangeListener(listener);
    }

    public void addChangeListener(final Consumer<JTextField> listener) {
        listeners.add(listener);
    }
}
