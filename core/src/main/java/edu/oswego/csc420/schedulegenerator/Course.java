package edu.oswego.csc420.schedulegenerator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static edu.oswego.csc420.schedulegenerator.Util.nullToEmpty;

/**
 * Defines a course.
 */
public class Course {
    private boolean isOptional;
    private Integer courseNumber;
    private String name, subject;
    private final Set<Section> sections;

    /**
     * Constructor.
     *
     * @param name the name of the course. e.g. Graphical User Interfaces
     * @param subject the subject of the course. e.g. CSC
     * @param courseNumber the course number. e.g. 420
     * @param isOptional set to true if the course does not have to be in every generated schedule.
     */
    public Course(final String name, final String subject, final Integer courseNumber, final boolean isOptional) {
        setName(name);
        setSubject(subject);
        this.sections     = new HashSet<>();
        this.isOptional   = isOptional;
        this.courseNumber = courseNumber;
    }

    /**
     * Returns the sections of this course.
     *
     * @return the sections of this course.
     */
    @Nonnull
    public Set<Section> getSections() {
        return Collections.unmodifiableSet(sections);
    }

    /**
     * Adds a section to this course.
     *
     * @param section a section.
     * @throws IllegalArgumentException when the section being added already exists in the course.
     */
    public void addSection(final Section section) throws IllegalArgumentException {
        if(section.getMeetingTimes().size() == 0) {
            throw new IllegalArgumentException("Section must have at least one meeting time!");
        } else if(sections.contains(section)) {
            throw new IllegalArgumentException("Section " + section.getSectionNumber() + " already exists in this course!");
        }
        sections.add(section);
    }

    /**
     * Removes a section from this course if it exists.
     *
     * @param section a section.
     */
    public void removeSection(final Section section) {
        sections.remove(section);
    }

    /**
     * Returns true if the course does not have to be in every generated schedule.
     *
     * @return true if the course does not have to be in every generated schedule.
     */
    public boolean isOptional() {
        return isOptional;
    }

    /**
     * Set to true if the course does not have to be in every generated schedule.
     *
     * @param optional true if the course does not have to be in every generated schedule.
     */
    public void setOptional(final boolean optional) {
        isOptional = optional;
    }

    /**
     * Returns an optional course number.
     *
     * @return an optional course number.
     */
    @Nonnull
    public Optional<Integer> getCourseNumber() {
        return Optional.ofNullable(courseNumber);
    }

    /**
     * Sets the course number.
     *
     * @param courseNumber a course number.
     */
    @Nonnull
    public void setCourseNumber(final Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    /**
     * Returns the name of this course. e.g. Graphical User Interfaces
     *
     * @return the name of this course.
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this course.
     *
     * @param name a course name.
     */
    public void setName(final String name) {
        this.name = nullToEmpty(name);
    }

    /**
     * Returns the subject of this course. e.g. CSC
     *
     * @return the subject of this course.
     */
    @Nonnull
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of this course.
     *
     * @param subject the subject of this course.
     */
    public void setSubject(final String subject) {
        this.subject = nullToEmpty(subject);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(43, 73)
                .append(name)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof Course)) {
            return false;
        }

        Course course = (Course) obj;

        return new EqualsBuilder()
                .append(name, course.getName())
                .isEquals();
    }
}
