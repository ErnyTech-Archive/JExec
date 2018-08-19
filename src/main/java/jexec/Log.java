package jexec;

public class Log {
    private final String output;
    private final String command;
    private final int returnValue;

    /**
     * Represents the log of a single command
     * @param jExec The JExec object from which to get the log
     */
    public Log(JExec jExec) {
        this.output = jExec.getOutput();
        this.command = jExec.getCommand();
        this.returnValue = jExec.getReturnValue();
    }

    /**
     * Returns the output of the executed command
     * @return The output of the executed command
     */
    public String getOutput() {
        return this.output;
    }

    /**
     * Return the executed command
     * @return The executed command
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns the return value of the executed command
     * @return The return value of the executed command
     */
    public int getReturnValue() {
        return this.returnValue;
    }

    @Override
    public String toString() {
        return "{command: " +
                this.command +
                ", output: " +
                this.output +
                ", returnValue: " +
                this.returnValue +
                "}";
    }
}
