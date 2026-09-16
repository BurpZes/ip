package wally;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Wally wally;

    private final Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image wallyImage = new Image(getClass().getResourceAsStream("/images/DaWally.png"));

    /**
     * Initializes the main window after its FXML components are loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the chatbot that processes user input and displays its startup message.
     *
     * @param wally Chatbot instance.
     */
    public void setWally(Wally wally) {
        this.wally = wally;
        dialogContainer.getChildren().add(DialogBox.getWallyDialog(wally.getStartupMessage(), wallyImage));
    }

    /** Applies a supported background colour or returns the validation error. */
    private String changeBackground(String colour) {
        try {
            String background = Parser.parseBackgroundColour(colour);
            String textColour = background.equals("black") ? "white" : "black";
            scrollPane.getScene().getRoot().setStyle("-wally-background: " + background
                    + "; -wally-text: " + textColour + ";");
            return "Background changed to " + (background.equals("lightblue") ? "light blue" : background) + ".";
        } catch (InvalidBackgroundColourException e) {
            return e.getMessage();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] commandParts = input.trim().split("\\s+", 2);
        String response;
        if (commandParts[0].equals("background")) {
            response = changeBackground(commandParts.length == 2 ? commandParts[1] : "");
        } else {
            response = wally.getResponse(input);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWallyDialog(response, wallyImage));
        userInput.clear();
    }
}
