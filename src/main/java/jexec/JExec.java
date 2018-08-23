package jexec;

import jexec.exception.CommandErrorException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Executes commands and saves the output, command, and return value in a Log object
 */
public class JExec {
    private final ProcessBuilder processBuilder;
    private final String command;
    private String output;
    private int returnValue;

    /**
     * Create a JExec with a command to execute
     * @param command The command to be execute
     */
    public JExec(String command) {
        this.command = command;
        this.processBuilder = new ProcessBuilder();
        this.processBuilder.command("sh", "-c", this.command);
        this.processBuilder.redirectErrorStream(true);
    }

    /**
     * Run the command
     * @throws InterruptedException Thrown if thread is interrupted
     * @throws IOException Thrown if an I/O error occurs eg: missing command, incorrect permissions...
     * @throws CommandErrorException Thrown if command return non-zero value
     */
    public void exec() throws InterruptedException, IOException, CommandErrorException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Thread is Interrupted");
        }

        Process process;

        try {
            process = this.processBuilder.start();
        } catch (IOException e) {
            this.output = Utils.toString(e);
            this.returnValue = -1;
            throw e;
        }

        process.waitFor();
        this.returnValue = process.exitValue();
        var outputStream = process.getInputStream();
        var outputScanner = new Scanner(outputStream).useDelimiter("\\A");

        if (outputScanner.hasNext()) {
            this.output = outputScanner.next().trim();
        } else {
            this.output = "";
        }

        if (this.returnValue != 0) {
            throw new CommandErrorException(this.command);
        }

        Logger.getAllLogger().add(this);
    }

    /**
     * Return the executed command
     * @return The executed command
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns the output of the executed command
     * @return The output of the executed command
     */
    public String getOutput() {
        return this.output;
    }

    /**
     * Returns the return value of the executed command
     * @return The return value of the executed command
     */
    public int getReturnValue() {
        return this.returnValue;
    }

    /**
     * Returns the Log object of the executed command
     * @return The Log object of the executed command
     */
    public Log getLog() {
        return new Log(this);
    }

    /**
     * Redirects the output and input of the command to stdout and stdin of the program
     */
    public void redirectConsole() {
        this.processBuilder.inheritIO();
    }

    /**
     * Set the command workdir
     * @param workDir The workdir to setup
     */
    public void workDir(File workDir) {
        this.processBuilder.directory(workDir);
    }

    /**
     * Set the command workdir
     * @param workDir The workdir to setup
     */
    public void workDir(Path workDir) {
        workDir(workDir.toFile());
    }

    /**
     * Set the command workdir
     * @param workDir The workdir to setup
     */
    public void workDir(String workDir) {
        workDir(new File(workDir));
    }
}
