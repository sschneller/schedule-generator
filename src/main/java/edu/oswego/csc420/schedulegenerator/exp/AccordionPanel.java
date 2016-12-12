package edu.oswego.csc420.schedulegenerator.exp;

import edu.oswego.csc420.schedulegenerator.Colors;
import edu.oswego.csc420.schedulegenerator.Generator;
import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates an accordion layout of data.
 */
public class AccordionPanel extends UpdatablePanel {
    private final Generator generator;
    private AccordionEntryPanel currentEntry;
    private final List<AccordionEntryPanel> entries, viewableEntries;

    /**
     * The constructor.
     *
     * @param generator the generator from the core backend.
     */
    public AccordionPanel(final Generator generator) {
        // Set the variables
        this.entries         = new ArrayList<>();
        this.generator       = generator;
        this.viewableEntries = new ArrayList<>();

        // Set panel properties
        setBackground(Colors.LIGHT_PRIMARY);
        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(new MigLayout("wrap, insets 0","[grow,fill]",""));
    }

    /**
     * Rebuilds the layout of the accordion based on the list of courses.
     */
    @Override
    public void update() {
        // Remove entries which no longer exist
        entries.removeAll(entries.parallelStream()
                .filter(e -> generator.getCourses().parallelStream().noneMatch(c -> c.equals(e.getCourse())))
                .collect(Collectors.toList()));

        // Add the entries that are not there
        entries.addAll(generator.getCourses().parallelStream()
                .filter(c -> entries.parallelStream().noneMatch(e -> e.getCourse().equals(c)))
                .map(c -> new AccordionEntryPanel(c, (e, p) -> {
                    if(currentEntry == p) {
                        currentEntry = null;
                    } else {
                        currentEntry = (AccordionEntryPanel) p;
                    }
                    update();
                }))
                .collect(Collectors.toList()));

        // Show the entries that should be shown
        removeAll();
        viewableEntries.clear();
        entries.forEach(AccordionEntryPanel::contract);
        if(currentEntry == null) {
            viewableEntries.addAll(entries);
            ((MigLayout) getLayout()).setRowConstraints("");
        } else {
            final int entryIndex = entries.indexOf(currentEntry);
            currentEntry.expand();
            viewableEntries.addAll(entries.subList(Math.max(0, entryIndex - 1), Math.min(entries.size(), entryIndex + 2)));
            if(entryIndex == 0) {
                ((MigLayout) getLayout()).setRowConstraints("[grow,fill][]");
            } else if(entryIndex == entries.size() - 1) {
                ((MigLayout) getLayout()).setRowConstraints("[][grow,fill]");
            } else {
                ((MigLayout) getLayout()).setRowConstraints("[][grow,fill][]");
            }
        }

        // Add all of the accordion entries to the accordion
        viewableEntries.forEach(this::add);
        super.update();
    }
}
