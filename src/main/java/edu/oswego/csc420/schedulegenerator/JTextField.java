package edu.oswego.csc420.schedulegenerator;

import javax.swing.BorderFactory;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class JTextField extends javax.swing.JTextField {
    private final List<Consumer<JTextField>> listeners;

    public JTextField(final String text) {
        setText(text);
        setColumns(1);
        listeners = new ArrayList<>();
        setForeground(Color.WHITE);
        setBackground(new Color(120,144,156));
        setBorder(null);
        setBorder(BorderFactory.createCompoundBorder(
                getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
//        setBorder(BorderFactory.createLineBorder(Colors.ACCENT.getColor(), 2));
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
