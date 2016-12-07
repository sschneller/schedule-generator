package edu.oswego.csc420.schedulegenerator;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A wrapper for course and section objects.
 */
public class Schedule {
    private final Set<Pair<Course,Section>> schedule;

    /**
     * Constructor.
     */
    public Schedule() {
        schedule = new HashSet<>();
    }

    /**
     * Cloning Constructor.
     *
     * @param schedule the schedule to clone from.
     */
    private Schedule(final Schedule schedule) {
        this.schedule = new HashSet<>(schedule.getSchedule());
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
    public Set<Pair<Course,Section>> getSchedule() {
        return Collections.unmodifiableSet(schedule);
    }

    /**
     * Creates a clone of the passed schedule.
     *
     * @return a clone of the passed schedule.
     */
    public Schedule duplicate() {
        return new Schedule(this);
    }
}
