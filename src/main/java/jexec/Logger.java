package jexec;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Logger of multiple commands
 */
public class Logger {
    private static final Logger allLogger = new Logger();
    private final List<Log> logs = new ArrayList<>();

    /**
     * Adds a Log object to the Logger
     * @param log The Log object to add
     */
    public void add(Log log) {
        this.logs.add(log);
    }

    /**
     * Generate a Log object from JExec and add it to the Logger
     * @param jExec The JExec object to generate log
     */
    public void add(JExec jExec) {
        add(jExec.getLog());
    }

    /**
     * Returns a static logger that contains the log of ALL executed commands
     * @return The static logger
     */
    public static Logger getAllLogger() {
        return allLogger;
    }

    /**
     * Return a list containing all the logs collected in the Logger
     * @return The list of all logs
     */
    public List<Log> getLogs() {
        return this.logs;
    }

    @Override
    public String toString() {
        var logsStrings = new ArrayList<String>();

        for (Log log : this.logs) {
            logsStrings.add(log.toString());
        }

        return logsStrings.toString();
    }
}
