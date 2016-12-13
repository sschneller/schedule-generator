package edu.oswego.csc420.schedulegenerator.panels;

import edu.oswego.csc420.schedulegenerator.Colors;
import edu.oswego.csc420.schedulegenerator.JButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static edu.oswego.csc420.schedulegenerator.JButton.ButtonStyle.*;

public abstract class AbstractSectionPanel<T> extends UpdatablePanel implements ActionListener {
    protected JTable<T> table;
    private final JButton newButton, editButton, deleteButton;

    AbstractSectionPanel(final String labelText, final String newButtonText, final String editButtonText, final String deleteButtonText, final String[] headers) {
        JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        this.newButton = new JButton(newButtonText, PRIMARY, this);
        this.editButton = new JButton(editButtonText, WARNING, this);
        this.deleteButton = new JButton(deleteButtonText, DANGER, this);
        table = new JTable<>(headers, this::objectRowMapper, this::onRowSelected);

        setBackground(Colors.PRIMARY);
        setLayout(new MigLayout("","[grow,fill]","[][grow,fill][]"));
        add(label,"span 3, wrap");
        final JScrollPane tablePane = new JScrollPane(table);
        tablePane.getViewport().setBackground(Colors.ACCENT.getColor());
        tablePane.setBorder(BorderFactory.createEmptyBorder());
        table.getTableHeader().setBackground(Colors.ACCENT.getColor());
        table.getTableHeader().setForeground(Color.WHITE);

        add(tablePane, "span 3, wrap");
        add(newButton);
        add(editButton);
        add(deleteButton);
    }

    public abstract void onRowSelected(final ListSelectionEvent event, final JTable<T> jTable);
    public abstract void onNewButtonClick();
    public abstract void onEditButtonClick();
    public abstract void onDeleteButtonClick();
    public abstract String[] objectRowMapper(final T object);

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newButton){
            onNewButtonClick();
        } else if(e.getSource() == editButton){
            onEditButtonClick();
        } else if(e.getSource() == deleteButton){
            onDeleteButtonClick();
        }
        update();
    }
}
