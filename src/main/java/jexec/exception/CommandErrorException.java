package jexec.exception;

/**
 * CommandErrorException exception that is thrown when something command return non-zero value
 */
public class CommandErrorException extends Exception {
    /**
     * Constructs a new exception
     * @param command The command that returned a non-zero value
     */
    public CommandErrorException(String command) {
        super("Command \"" + command + "\"");
    }
}
