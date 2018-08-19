package jexec;

/**
 * A subclass of JExecExecutor to implement only onExecuted(Log log, Exception exception)
 * onSuccess(Logger logger) and onError(Logger logger, Exception exception) are implemented in this class but they do nothing
 */
public abstract class ExecutedExecutor extends JExecExecutor {

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
     * @param logger The logger of the executed commands
     * @param exception The exception thrown by command()
     */
    @Override
    public void onError(Logger logger, Exception exception) {
        // nope
    }
}
