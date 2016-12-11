package edu.oswego.csc420.schedulegenerator.exp;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.IntStream;

public class JTable<T> extends javax.swing.JTable {

    JTable(final String[] headers, final Function<T, String[]> mapper, final BiConsumer<ListSelectionEvent, JTable> onRowSelect) {
        super(new TableModel<>(headers, mapper));
        getTableHeader().setReorderingAllowed(false);
        getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getSelectionModel().addListSelectionListener(e -> onRowSelect.accept(e, this));
    }

    @SuppressWarnings("unchecked")
    public TableModel<T> getTableModel() {
        return (TableModel<T>) getModel();
    }

    public static class TableModel<T> extends DefaultTableModel {
        private final Function<T, String[]> mapper;

        TableModel(final String[] headers, final Function<T, String[]> mapper) {
            super(new Object[][]{}, headers);
            this.mapper = mapper;
        }

        void addAll(final List<T> sections) {
            sections.forEach(s -> addRow(mapper.apply(s)));
        }

        void removeAll() {
            IntStream.range(0, getRowCount()).forEach(this::removeRow);
        }
    }
}
