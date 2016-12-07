package edu.oswego.csc420.schedulegenerator;

import com.google.common.collect.Range;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static edu.oswego.csc420.schedulegenerator.Util.nullToEmpty;

/**
 * Defines a time slot and the days that time slot is active.
 */
public class MeetingTime {
    private final String location;
    private final Set<DayOfWeek> days;
    private final LocalTime start, end;
    private final Range<Integer> interval;

    /**
     * Constructor.
     *
     * @param start the start time.
     * @param end the end time.
     * @param location the location of the meeting.
     * @param days the days the start and end times apply.
     *
     * @throws DateTimeException when start time is equal to end time or when end time is before start time.
     */
    public MeetingTime(final LocalTime start, final LocalTime end, final String location, final DayOfWeek... days)
            throws DateTimeException, NullPointerException, IllegalArgumentException {
        Validate.notNull(end, "End time cannot be null!");
        Validate.notNull(start, "Start time cannot be null!");
        Validate.notEmpty(days, "Must select at least one day of the week!");

        if(end.isBefore(start)) {
            throw new DateTimeException("End time cannot be before start time! Start Time: " + start + " End Time: " + end);
        } else if(end.compareTo(start) == 0) {
            throw new DateTimeException("Start and end time cannot be the same! Start Time: " + start + " End Time: " + end);
        }

        this.end      = end;
        this.days     = new HashSet<>(Arrays.asList(days));
        this.start    = start;
        this.interval = Range.closed(toInt(start), toInt(end));
        this.location = nullToEmpty(location);
    }

    /**
     * Converts local time to the following format integer: HHMMSS
     *
     * @param time the local time.
     * @return the integer representation of the time.
     */
    private int toInt(final LocalTime time) {
        final String format = "%02d";
        return Integer.parseInt(String.format(format, time.getHour()) + String.format(format, time.getMinute()) + String.format(format, time.getSecond()));
    }

    /**
     * Returns true if the passed MeetingTime overlaps with the
     * current MeetingTime.
     *
     * @param meetingTime a meetingTime.
     * @return true if the passed MeetingTime overlaps with the current MeetingTime.
     */
    public boolean overlaps(final MeetingTime meetingTime) {
        return !Collections.disjoint(days, meetingTime.getDays()) && interval.isConnected(meetingTime.getInterval());
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
     * Returns an unmodifiable view of the days the times are active.
     *
     * @return the days the times are active.
     */
    @Nonnull
    public Set<DayOfWeek> getDays() {
        return Collections.unmodifiableSet(days);
    }

    /**
     * Returns the time interval.
     *
     * @return the time interval.
     */
    @Nonnull
    private Range<Integer> getInterval() {
        return interval;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(end)
                .append(days)
                .append(start)
                .append(location)
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
                .append(location, meetingTime.getLocation())
                .isEquals();
    }

    @Override
    public String toString() {
        return start.toString() + " - " + end.toString() + " " + days.stream().map(d -> d.getDisplayName(TextStyle.NARROW, Locale.US)).collect(Collectors.joining());
    }
}
