package edu.oswego.csc420.schedulegenerator;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schedule {
    private final List<Pair<Course,Section>> schedule;

    public Schedule() {
        schedule = new ArrayList<>();
    }

    public List<Pair<Course,Section>> getSchedule() {
        return Collections.unmodifiableList(schedule);
    }
}
