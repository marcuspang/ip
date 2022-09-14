package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import ui.Ui;
import exceptions.DukeException;

/**
 * Represents the commands that are available in the chatbot.
 */
public abstract class Command {
    /**
     * Executes the command based on the current tasks and local storage, and calls UI methods as required.
     *
     * @param tasks   The specified tasks.
     * @param ui      The Ui class used to print messages to the console.
     * @param storage The storage that is used to manage tasks saved locally.
     * @return The resulting output of executing the command.
     * @throws DukeException if the command did not complete successfully.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether the chatbot should terminate after the command is executed.
     *
     * @return true if the chatbot should terminate, false otherwise.
     */
    public abstract boolean isExit();
}
