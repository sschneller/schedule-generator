package edu.oswego.csc420.schedulegenerator;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Defines a Section for a Course.
 */
public class Section {
    private static final Logger logger = LoggerFactory.getLogger(Section.class);
    private final String location;
    private final int sectionNumber;
    private final Set<SectionTime> sectionTimes;

    /**
     * Constructor.
     *
     * @param sectionNumber the number of the section.
     * @param sectionTime the time slot this section occupies.
     */
    public Section(final int sectionNumber, final SectionTime sectionTime) {
        this(sectionNumber, sectionTime, null);
    }

    /**
     * Constructor.
     *
     * @param sectionNumber the number of the section.
     * @param sectionTime the time slot this section occupies.
     * @param location the location of this section. e.g. Building/Room #
     */
    public Section(final int sectionNumber, final SectionTime sectionTime, final String location) {
        this.location = location;
        this.sectionNumber = sectionNumber;
        this.sectionTimes = new HashSet<>();
        Validate.notNull(sectionTime, "Section time cannot be null!");
        sectionTimes.add(sectionTime);
    }

    /**
     * Returns the section number.
     *
     * @return the section number.
     */
    public int getSectionNumber() {
        return sectionNumber;
    }

    /**
     * Returns an optional of the location.
     *
     * @return an optional of the location.
     */
    public Optional<String> getLocation() {
        return Optional.ofNullable(location);
    }

    /**
     * Returns an unmodifiable list of section times.
     *
     * @return an unmodifiable list of section times.
     */
    public Set<SectionTime> getSectionTimes() {
        return Collections.unmodifiableSet(sectionTimes);
    }

    /**
     *
     *
     * @param sectionTime
     * @return
     */
    public void addSectionTime(final SectionTime sectionTime) {
        Optional<SectionTime> overlapping = sectionTimes.stream().filter(s -> s.overlaps(sectionTime)).findFirst();
        if(overlapping.isPresent()) {
            IllegalArgumentException e = new IllegalArgumentException("Section time conflicts with an existing section time.");
            logger.error("Section '" + sectionTime + "' Conflicts with section '" + overlapping.get() + "'", e);
            throw e;
        }

        sectionTimes.add(sectionTime);
    }

    public void removeSectionTime(final SectionTime sectionTime) {
        if(sectionTimes.size() == 1 && sectionTimes.contains(sectionTime)) {
            UnsupportedOperationException e = new UnsupportedOperationException("Cannot remove the last section time in a section.");
            logger.error("Attempted to remove the last section time in a section.", e);
            throw e;
        }

        sectionTimes.remove(sectionTime);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(21, 49)
                .append(sectionNumber)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof Section)) {
            return false;
        }

        Section section = (Section) obj;

        return new EqualsBuilder()
                .append(sectionNumber, section.getSectionNumber())
                .isEquals();
    }
}
