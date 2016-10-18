package edu.oswego.csc420.schedulegenerator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Defines a time slot and the days that time slot is active.
 */
public class SectionTime {
    private final LocalTime start, end;
    private final Set<DayOfWeek> days;

    /**
     * Constructor.
     *
     * @param start the start time.
     * @param end the end time.
     * @param days the days the start and end times apply.
     *
     * @throws DateTimeException when start time is equal to end time or when end time is before start time.
     */
    public SectionTime(final LocalTime start, final LocalTime end, final DayOfWeek... days) throws DateTimeException {
        this.end            = end;
        this.days           = new HashSet<>(Arrays.asList(days));
        this.start          = start;

        if(end.isBefore(start)) {
            throw new DateTimeException("End time cannot be before start time! Start Time: " + start + " End Time: " + end);
        } else if(end.compareTo(start) == 0) {
            throw new DateTimeException("Start and end time cannot be the same! Start Time: " + start + " End Time: " + end);
        }
    }

    /**
     * Returns true if the passed SectionTime overlaps with the
     * current SectionTime.
     *
     * @param sectionTime a sectionTime.
     * @return true if the passed SectionTime overlaps with the current SectionTime.
     */
    public boolean overlaps(final SectionTime sectionTime) {
        return !Collections.disjoint(days, sectionTime.getDays())
                && start.isBefore(sectionTime.getEnd())
                && sectionTime.getStart().isBefore(end);
    }

    /**
     * Returns the start time.
     *
     * @return the start time.
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * Returns the end time.
     *
     * @return the end time.
     */
    public LocalTime getEnd() {
        return end;
    }

    /**
     * Returns the days the times are active.
     *
     * @return the days the times are active.
     */
    public Set<DayOfWeek> getDays() {
        return Collections.unmodifiableSet(days);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(end)
                .append(days)
                .append(start)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof SectionTime)) {
            return false;
        }

        SectionTime sectionTime = (SectionTime) obj;

        return new EqualsBuilder()
                .append(end, sectionTime.getEnd())
                .append(days, sectionTime.getDays())
                .append(start, sectionTime.getStart())
                .isEquals();
    }

    @Override
    public String toString() {
        return start.toString() + " - " + end.toString() + " " + days.stream().map(d -> d.getDisplayName(TextStyle.NARROW, Locale.US)).collect(Collectors.joining());
    }
}
