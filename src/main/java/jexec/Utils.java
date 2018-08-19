package jexec;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Contains various useful methods
 */
public class Utils {
    /**
     * Convert an exception to a string
     * @param e The exception to be converted
     * @return The string containing the exception text
     */
    public static String toString(Exception e) {
        var stringWriter = new StringWriter();
        var printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
