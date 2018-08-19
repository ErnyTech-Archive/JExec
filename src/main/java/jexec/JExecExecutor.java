package jexec;

import jexec.exception.CommandErrorException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Is the implementation of Executor()
 * command(), onSuccess(Logger logger), onError(Logger logger, Exception exception), onExecuted(Log log, Exception exception) remain not implemented to allow an external implementation
 */
public abstract class JExecExecutor implements Executor {
    private Thread execThread;
    private final Logger logger = new Logger();
    private boolean redirectConsole = false;
    private File workDir = null;

    @Override
    public void run() {
        this.execThread = new Thread(() -> {
            try {
                command();
                onSuccess(this.logger);
            } catch (InterruptedException | IOException | CommandErrorException e) {
                onError(this.logger, e);
            }
        });

        this.execThread.start();
    }

    @Override
    public void term() {
        this.execThread.interrupt();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void kill() {
        this.execThread.stop();
    }

    @Override
    public void redirectConsole() {
        this.redirectConsole = true;
    }

    @Override
    public void workDir(File workDir) {
        this.workDir = workDir;
    }

    @Override
    public void workDir(Path workDir) {
        workDir(workDir.toFile());
    }

    @Override
    public void workDir(String workDir) {
        workDir(new File(workDir));
    }

    @Override
    public JExec exec(JExec jExec) throws InterruptedException, IOException, CommandErrorException {
        if (this.redirectConsole) {
            jExec.redirectConsole();
        }

        if (this.workDir != null) {
            jExec.workDir(this.workDir);
        }

        try {
            jExec.exec();
        } catch (InterruptedException | IOException | CommandErrorException e) {
            this.logger.add(jExec.getLog());
            onExecuted(jExec.getLog(), e);
            throw e;
        }

        this.logger.add(jExec.getLog());
        onExecuted(jExec.getLog(), null);
        return jExec;
    }

    @Override
    public JExec exec(String command) throws InterruptedException, IOException, CommandErrorException {
        var jExec = new JExec(command);
        return exec(jExec);
    }
}
