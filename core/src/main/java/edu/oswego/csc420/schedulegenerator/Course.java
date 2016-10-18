package edu.oswego.csc420.schedulegenerator;

import java.util.*;

public class Course {
    private final boolean isOptional;
    private final Integer crn, courseNumber;
    private final Set<Section> sections;
    private final String name, subject, teacher;

    public Course(final String name, final boolean isOptional) {
        this(null, null, name, null, null, isOptional);
    }

    public Course(final Integer crn, final Integer courseNumber, final String name, final String subject, final String teacher, final boolean isOptional) {
        this.crn          = crn;
        this.courseNumber = courseNumber;
        this.name         = name;
        this.subject      = subject;
        this.teacher      = teacher;
        this.isOptional   = isOptional;
        this.sections     = new HashSet<>();
    }

    public boolean addSection(final Section section) {
        if(sections.contains(section)) {
            sections.remove(section);
        }
        return sections.add(section);
    }

    public boolean removeSection(final Section section) {
        return sections.remove(section);
    }

    public boolean isOptional() {
        return isOptional;
    }

    public Optional<Integer> getCrn() {
        return Optional.ofNullable(crn);
    }

    public Optional<Integer> getCourseNumber() {
        return Optional.ofNullable(courseNumber);
    }

    public Set<Section> getSections() {
        return Collections.unmodifiableSet(sections);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getSubject() {
        return Optional.ofNullable(subject);
    }

    public Optional<String> getTeacher() {
        return Optional.ofNullable(teacher);
    }
}
