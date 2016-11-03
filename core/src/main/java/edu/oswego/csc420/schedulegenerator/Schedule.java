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
     * Adds a Course and its Section to the schedule.
     *
     * @param course a Course.
     * @param section a Section from the course.
     */
    public void addCourse(final Course course, final Section section) {
        if(!course.getSections().stream().anyMatch(section::equals)) {
            throw new IllegalArgumentException("Specified section is not from the specified course!");
        }
        schedule.add(Pair.of(course,section));
    }

    /**
     * Returns a set of Course-Section pairs that represents a schedule.
     *
     * @return a set of Course-Section pairs that represents a schedule.
     */
    public Set<Pair<Course,Section>> getSchedule() {
        return Collections.unmodifiableSet(schedule);
    }
}
