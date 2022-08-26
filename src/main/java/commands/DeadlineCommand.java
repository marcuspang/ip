package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import exceptions.EmptyDateTimeException;
import exceptions.EmptyDescriptionException;
import exceptions.InvalidDateTimeException;
import task.Deadline;
import task.Task;
import task.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Creates a new deadline
 */
public class DeadlineCommand extends Command {
    private final String[] inputStrings;

    /**
     * Constructs a deadline command, using the input strings to generate the deadline
     *
     * @param inputStrings The specified input strings.
     */
    public DeadlineCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Creates a new deadline
     * <p>
     * {@inheritDoc}
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.inputStrings.length == 1) {
            throw new EmptyDescriptionException(TaskType.D);
        }

        String[] deadlineStrings = inputStrings[1].split(" /by ", 2);
        if (deadlineStrings.length == 1 || deadlineStrings[1].equals("")) {
            throw new EmptyDateTimeException(TaskType.D);
        }
        try {
            Deadline deadline = new Deadline(deadlineStrings[0], false,
                    LocalDateTime.parse(deadlineStrings[1], Task.dateTimeParser));
            tasks.addTask(deadline);

            ui.showAddTask(deadline, tasks.size());
        } catch (DateTimeParseException exception) {
            throw new InvalidDateTimeException(Task.DATE_TIME_INPUT_FORMAT);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}