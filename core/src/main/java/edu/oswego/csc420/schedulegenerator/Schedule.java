package edu.oswego.csc420.schedulegenerator;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * A wrapper for course and section objects.
 */
public class Schedule implements Comparable<Schedule> {
    private final List<Pair<Course,Section>> schedule;

    /**
     * Constructor.
     */
    public Schedule() {
        schedule = new ArrayList<>();
    }

    /**
     * Cloning Constructor.
     *
     * @param schedule the schedule to clone from.
     */
    private Schedule(final Schedule schedule) {
        this.schedule = new ArrayList<>(schedule.getSchedule());
    }

    /**
     * Adds a Course and its Section to the schedule.
     *
     * @param course a Course.
     * @param section a Section from the course.
     */
    public void addCourse(final Course course, final Section section) {
        if(course.getSections().stream().noneMatch(section::equals)) {
            throw new IllegalArgumentException("Specified section is not from the specified course!");
        }
        schedule.add(Pair.of(course,section));
    }

    /**
     * Checks if the specified section can fit inside this schedule.
     *
     * @param section a section.
     * @return true if the section can fit in this schedule.
     */
    public boolean fits(final Section section) {
        return schedule.parallelStream()
                .flatMap(s -> s.getValue()
                        .getMeetingTimes()
                        .parallelStream())
                .noneMatch(m -> section.getMeetingTimes()
                        .parallelStream()
                        .anyMatch(meetingTime -> meetingTime
                                .overlaps(m)));
    }

    /**
     * Returns a set of Course-Section pairs that represents a schedule.
     *
     * @return a set of Course-Section pairs that represents a schedule.
     */
    public List<Pair<Course,Section>> getSchedule() {
        return Collections.unmodifiableList(schedule);
    }

    /**
     * Creates a clone of the passed schedule.
     *
     * @return a clone of the passed schedule.
     */
    public Schedule duplicate() {
        return new Schedule(this);
    }

    public LocalTime getEarliest() {
        return schedule.parallelStream().map(Pair::getRight)
                .flatMap(s -> s.getMeetingTimes().stream())
                .map(MeetingTime::getStart)
                .min(Comparator.comparing(Function.identity()))
                .orElse(null);
    }

    public LocalTime getLatest() {
        return schedule.parallelStream().map(Pair::getRight)
                .flatMap(s -> s.getMeetingTimes().stream())
                .map(MeetingTime::getEnd)
                .max(Comparator.comparing(Function.identity()))
                .orElse(null);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 15)
                .append(schedule)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof Schedule)) {
            return false;
        }

        Schedule schedule = (Schedule) obj;

        return new EqualsBuilder()
                .append(this.schedule, schedule.getSchedule())
                .isEquals();
    }

    @Override
    public int compareTo(Schedule o) {
        return new CompareToBuilder()
                .append(this.schedule, o.getSchedule())
                .toComparison();
    }
}
