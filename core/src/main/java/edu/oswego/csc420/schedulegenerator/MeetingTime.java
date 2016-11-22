package edu.oswego.csc420.schedulegenerator;


import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

import static edu.oswego.csc420.schedulegenerator.Util.nullToEmpty;

/**
 * Defines a time slot and the days that time slot is active.
 */
public class MeetingTime {
    private final LocalTime start, end;
    private String location;
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
    public MeetingTime(final LocalTime start, final LocalTime end, final String location, final DayOfWeek... days)
            throws DateTimeException, NullPointerException, IllegalArgumentException {
        Validate.notNull(start, "Start time cannot be null!");
        Validate.notNull(end, "End time cannot be null!");
        Validate.notEmpty(days, "Must select at least one day of the week!");
        this.end   = end;
        this.days  = new HashSet<>(Arrays.asList(days));
        this.start = start;
        setLocation(location);

        if(end.isBefore(start)) {
            throw new DateTimeException("End time cannot be before start time! Start Time: " + start + " End Time: " + end);
        } else if(end.compareTo(start) == 0) {
            throw new DateTimeException("Start and end time cannot be the same! Start Time: " + start + " End Time: " + end);
        }
    }

    /**
     * Returns true if the passed MeetingTime overlaps with the
     * current MeetingTime.
     *
     * @param meetingTime a meetingTime.
     * @return true if the passed MeetingTime overlaps with the current MeetingTime.
     */
    public boolean overlaps(final MeetingTime meetingTime) {
        return !Collections.disjoint(days, meetingTime.getDays())
                && start.isBefore(meetingTime.getEnd())
                && meetingTime.getStart().isBefore(end);
    }

    /**
     * Returns the start time.
     *
     * @return the start time.
     */
    @Nonnull
    public LocalTime getStart() {
        return start;
    }

    /**
     * Returns the end time.
     *
     * @return the end time.
     */
    @Nonnull
    public LocalTime getEnd() {
        return end;
    }

    /**
     * Returns the location.
     *
     * @return the location.
     */
    @Nonnull
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location the location.
     */
    public void setLocation(final String location) {
        this.location = nullToEmpty(location);
    }

    /**
     * Returns an unmodifiable view of the days the times are active.
     *
     * @return the days the times are active.
     */
    @Nonnull
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
        } else if(!(obj instanceof MeetingTime)) {
            return false;
        }

        MeetingTime meetingTime = (MeetingTime) obj;

        return new EqualsBuilder()
                .append(end, meetingTime.getEnd())
                .append(days, meetingTime.getDays())
                .append(start, meetingTime.getStart())
                .isEquals();
    }

    @Override
    public String toString() {
        return start.toString() + " - " + end.toString() + " " + days.stream().map(d -> d.getDisplayName(TextStyle.NARROW, Locale.US)).collect(Collectors.joining());
    }
}
