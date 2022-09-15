package ui;

import dukeegg.TaskList;
import task.Task;

/**
 * Deals with the interaction of the user and messages to be displayed.
 */
public class Ui {
    private static final String INDENTATION = "  ";

    /**
     * Prints the message after adding a task, based on the task's fields.
     *
     * @param task              The specified task.
     * @param numberOfTasksLeft The number of tasks left.
     * @return The formatted message.
     */
    public String showAddTask(Task task, int numberOfTasksLeft) {
        return "Got it. I've added this task:\n"
                + INDENTATION + task + "\n"
                + "Now you have " + numberOfTasksLeft + " task(s) in the list.";
    }

    /**
     * Prints the message after removing a task, based on the task's fields.
     *
     * @param task              The specified task.
     * @param numberOfTasksLeft The number of tasks left.
     * @return The formatted message.
     */
    public String showRemoveTask(Task task, int numberOfTasksLeft) {
        return "Noted. I've removed this task:\n"
                + INDENTATION + task + "\n"
                + "Now you have " + numberOfTasksLeft + " task(s) in the list.";
    }

    /**
     * Prints the message after marking a task as done, based on the task's fields.
     *
     * @param task The specified task.
     * @return The formatted message.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n"
                + INDENTATION + task;
    }

    /**
     * Prints the message after marking a task as not done, based on the task's fields.
     *
     * @param task The specified task.
     * @return The formatted message.
     */
    public String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + INDENTATION + task;
    }

    /**
     * Prints the message after tagging a task with some tag.
     *
     * @param task The specified task.
     * @return The formatted message.
     */
    public String showTagTask(Task task) {
        return "OK, I've tagged this task:\n"
                + INDENTATION + task;
    }

    /**
     * Prints the message after removing a tag from the task.
     *
     * @param task The specified task.
     * @return The formatted message.
     */
    public String showUntagTask(Task task) {
        return "OK, I've untagged this task:\n"
                + INDENTATION + task;
    }

    /**
     * Prints the error message involved with retrieving tasks from a storage file.
     */
    public void showLoadingError() {
        System.out.println("There was some error initialising the chatbot, no tasks are loaded.");
    }

    /**
     * Prints the welcome message whenever the chatbot starts running.
     *
     * @return The formatted message.
     */
    public String showWelcome() {
        return "🥚 Hello! I'm Dukegg 🥚\n"
                + "Type help to find out more about the commands available.";
    }

    /**
     * Prints the specified tasks in a 1-indexed list format.
     *
     * @param tasks The specified tasks.
     * @return The formatted message.
     */
    public String showTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the current tasks of the chatbot. Used by the list command.
     *
     * @param tasks The specified tasks.
     * @return The formatted message.
     */
    public String showCurrentTasks(TaskList tasks) {
        return "Here are the tasks in your list:\n"
                + this.showTasks(tasks);
    }

    /**
     * Prints the matching tasks based on user's input. Used by the find command.
     *
     * @param tasks The specified tasks.
     * @return The formatted message.
     */
    public String showMatchingTasks(TaskList tasks) {
        return "Here are the matching tasks in your list:\n"
                + this.showTasks(tasks);
    }

    /**
     * Prints the goodbye message when the chatbot shuts down.
     *
     * @return The formatted message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon! (Application closing in 2 seconds)";
    }

    /**
     * Prints the error message to the user.
     *
     * @param errorMessage The specified error message.
     * @return The formatted message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints the specified commands delimited by a new line.
     *
     * @param commands The specified commands.
     * @return The formatted message.
     */
    public String showAvailableCommands(String[] commands) {
        return "Here are a list of available commands:\n" + String.join("\n", commands);
    }
}
