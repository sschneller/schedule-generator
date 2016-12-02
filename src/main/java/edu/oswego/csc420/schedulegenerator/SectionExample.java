package edu.oswego.csc420.schedulegenerator;

public class SectionExample {
    public String name;
    public String[] daysOfWeek;
    public String[][] meetingTimes;

    public SectionExample(String n, String[] dow, String[][] mt) {
        name = n;
        daysOfWeek = dow;
        meetingTimes = mt;
    }
}
