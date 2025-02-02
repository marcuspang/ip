package ui;

import java.util.Objects;

import dukeegg.Dukegg;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaEgg.jpeg")));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Dukegg dukegg;

    /**
     * Initialises the user interface of the application to be displayed.
     * Sends a greeting message also.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Sets the chatbot instance in this MainWindow instance with the given chatbot instance, and also sends a
     * greeting message.
     *
     * @param dukegg The specified chatbot instance.
     */
    public void setDukegg(Dukegg dukegg) {
        this.dukegg = dukegg;
        this.dialogContainer.getChildren().addAll(
                new ChatbotDialogBox(this.dukegg.getGreetingMessage(), this.dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.dukegg.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                new UserDialogBox(input, this.userImage),
                new ChatbotDialogBox(response, this.dukeImage)
        );
        this.userInput.clear();
    }
}

