package edu.oswego.csc420.schedulegenerator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Schedule Generator Class.
 */
public class Generator {
    private final Gson gson;
    private final Set<Course> courses;

    /**
     * Constructor.
     */
    public Generator() {
        gson    = new Gson();
        courses = new HashSet<>();
    }

    /**
     * Constructor.
     *
     * @param importFile the file to load the generator from.
     * @throws IOException when the file cannot be accessed.
     * @see #export(File)
     */
    public Generator(final File importFile) throws IOException {
        this();
        try(final Reader reader = new FileReader(importFile)) {
            courses.addAll(gson.fromJson(reader, new TypeToken<Set<Course>>(){}.getType()));
        }
    }

    /**
     * Generates all possible schedules with the course information provided.
     *
     * @return a set of schedule objects.
     */
    public Set<Schedule> generate() {
        //TODO implement.
        return null;
    }

    /**
     * Adds the course to the generator.
     *
     * @param course the course to add.
     * @throws IllegalArgumentException when the course does not have any sections.
     */
    public void addCourse(final Course course) throws IllegalArgumentException {
        if(course.getSections().size() == 0) {
            throw new IllegalArgumentException("Course must have at least one section!");
        }
        courses.add(course);
    }

    /**
     * Removes the course if it exists.
     *
     * @param course the course to be removed.
     */
    public void removeCourse(final Course course) {
        courses.remove(course);
    }

    /**
     * Returns a unmodifiable view of the courses set.
     *
     * @return a unmodifiable view of the courses set.
     */
    @Nonnull
    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    /**
     * Exports the generator to a file for importing later.
     *
     * @param exportFile the file to export to.
     * @throws IOException when the file cannot be written or accessed.
     * @see #Generator(File)
     */
    public void export(final File exportFile) throws IOException {
        try(final Writer writer = new FileWriter(exportFile)) {
            gson.toJson(courses, writer);
        }
    }
}
