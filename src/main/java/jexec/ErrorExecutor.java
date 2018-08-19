package jexec;

/**
 * A subclass of JExecExecutor to implement only onError(Logger logger, Exception exception)
 * onSuccess(Logger logger) and onExecuted(Log log, Exception exception) are implemented in this class but they do nothing
 */
public abstract class ErrorExecutor extends JExecExecutor {

    /**
     * DOES NOTHING
     * @param logger The logger of the executed commands
     */
    @Override
    public void onSuccess(Logger logger) {
        // nope
    }

    /**
     * DOES NOTHING
     * @param log The logg of the executed command
     * @param exception The exception thrown by command
     */
    @Override
    public void onExecuted(Log log, Exception exception) {
        // nope
    }
}
