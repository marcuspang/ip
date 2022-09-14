package commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dukeegg.Storage;
import dukeegg.TaskList;
import ui.Ui;
import exceptions.DukeException;

public class UnmarkCommandTest {
    @Test
    public void execute_emptyTaskList_exceptionThrown() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./");

        String[] inputStrings = new String[]{"unmark", "1"};
        Command command = new UnmarkCommand(inputStrings);

        assertThrows(DukeException.class, () -> command.execute(taskList, ui, storage));
    }
}
