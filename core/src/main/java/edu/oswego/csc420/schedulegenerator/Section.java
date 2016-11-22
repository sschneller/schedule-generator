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
 * Defines a Section for a Course.
 */
public class Section {
    private String sectionNumber, crn, teacher, location;
    private final Set<MeetingTime> meetingTimes;

    /**
     * Constructor.
     *
     * @param sectionNumber the number of the section. e.g. 800
     * @param crn the CRN number of the section. e.g. 10279
     * @param teacher the name of the sections teacher. e.g. Mr. Filip
     * @param location the location of the section. e.g. Shineman Center Room 400
     */
    public Section(final String sectionNumber, final String crn, final String teacher, final String location) {
        setSectionNumber(sectionNumber);
        setCrn(crn);
        setTeacher(teacher);
        setLocation(location);
        this.meetingTimes = new HashSet<>();
    }

    /**
     * Adds the meeting time to the section.
     *
     * @param meetingTime the meeting time to add.
     * @throws IllegalArgumentException when there is a conflict with two or more meeting times in this section.
     */
    public void addMeetingTime(final MeetingTime meetingTime) throws IllegalArgumentException {
        Optional<MeetingTime> overlapping = meetingTimes.stream().filter(s -> s.overlaps(meetingTime)).findFirst();
        if(overlapping.isPresent()) {
            throw new IllegalArgumentException("Meeting time '" + meetingTime
                    + "' conflicts with the existing meeting time '" + overlapping.get() + "'");
        }

        meetingTimes.add(meetingTime);
    }

    /**
     * Removes the meeting time if it already exists.
     *
     * @param meetingTime the meeting time to remove.
     * @throws UnsupportedOperationException when the meeting time exists and it is the last meeting time in the section.
     */
    public void removeMeetingTime(final MeetingTime meetingTime) throws UnsupportedOperationException {
        if(meetingTimes.size() == 1 && meetingTimes.contains(meetingTime)) {
            throw new UnsupportedOperationException("Cannot remove the last meeting time in the section.");
        }

        meetingTimes.remove(meetingTime);
    }

    /**
     * Returns the teacher name.
     *
     * @return the teacher name.
     */
    @Nonnull
    public String getTeacher() {
        return teacher;
    }

    /**
     * Sets the teacher name.
     *
     * @param teacher the teacher name.
     */
    public void setTeacher(final String teacher) {
        this.teacher = nullToEmpty(teacher);
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
     * Returns an optional CRN.
     *
     * @return an optional CRN.
     */
    @Nonnull
    public Optional<String> getCrn() {
        return Optional.ofNullable(crn);
    }

    /**
     * Sets the CRN.
     *
     * @param crn the CRN.
     */
    public void setCrn(String crn) {
        this.crn = crn;
    }

    /**
     * Returns an optional section number.
     *
     * @return an optional section number.
     */
    @Nonnull
    public Optional<String> getSectionNumber() {
        return Optional.ofNullable(sectionNumber);
    }

    /**
     * Sets the section number.
     *
     * @param sectionNumber the section number.
     */
    public void setSectionNumber(final String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    /**
     * Returns an unmodifiable list of section times.
     *
     * @return an unmodifiable list of section times.
     */
    @Nonnull
    public Set<MeetingTime> getMeetingTimes() {
        return Collections.unmodifiableSet(meetingTimes);
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
