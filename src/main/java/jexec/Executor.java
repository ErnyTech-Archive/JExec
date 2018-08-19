package jexec;

import jexec.exception.CommandErrorException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * A interface to be implemented in the executors
 */
public interface Executor {
    /**
     * exec(JExec jExec) runs the JExec object that is passed in the arguments, it is typically used in the implementation of command()
     * @param jExec The object to be executed
     * @return The executed object
     * @throws InterruptedException Thrown if term () is thrown, this exception will be thrown
     * @throws IOException Thrown if an I/O error occurs eg: missing command, incorrect permissions...
     * @throws CommandErrorException Thrown if command return non-zero value
     */
    JExec exec(JExec jExec) throws InterruptedException, IOException, CommandErrorException;

    /**
     * exec(String command) creates new JExec object and call exec(JExec jExec) with that object
     * @param command A command to create JExec object
     * @return The executed object
     * @throws InterruptedException Thrown if term () is thrown, this exception will be thrown
     * @throws IOException Thrown if an I/O error occurs eg: missing command, incorrect permissions...
     * @throws CommandErrorException Thrown if command return non-zero value
     */
    JExec exec(String command) throws InterruptedException, IOException, CommandErrorException;

    /**
     * command() is designed to be called by the thread that creates run(), it is generally not implemented by executors and is used to call exec (JExec jExec) and exec (String command)
     * @throws InterruptedException When something methods inside command() thrown this exception
     * @throws IOException When something methods inside command() thrown this exception
     * @throws CommandErrorException When something methods inside command() thrown this exception
     */
    void command() throws InterruptedException, IOException, CommandErrorException;

    /**
     * Create a new thread and execute command(), this call onSuccess(Logger logger) when command() throw any exception or onError(Logger logger, Exception exception) when something thrown
     */
    void run();

    /**
     * Send interrupt to the thread
     */
    void term();

    /**
     * It forcibly kills the thread
     */
    void kill();

    /**
     * Is called by run() when command() has not thrown any exceptions
     * @param logger The logger of the executed commands
     */
    void onSuccess(Logger logger);

    /**
     * Is called by run() when command() has thrown some exceptions
     * @param logger The logger of the executed commands
     * @param exception The exception thrown by command()
     */
    void onError(Logger logger, Exception exception);

    /**
     * Is called by exec(JExec jExec) after the command is executed
     * @param log The log of the executed command
     * @param exception The exception thrown by execution of command, or null if no exception has been thrown
     */
    void onExecuted(Log log, Exception exception);

    /**
     * Redirects the output and input of the commands to stdout and stdin of the program
     */
    void redirectConsole();

    /**
     * Set the commands workdir
     * @param workDir The workdir to setup
     */
    void workDir(File workDir);

    /**
     * Set the commands workdir
     * @param workDir The workdir to setup
     */
    void workDir(Path workDir);

    /**
     * Set the commands workdir
     * @param workDir The workdir to setup
     */
    void workDir(String workDir);
}
