import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Kristjan on 24/04/2016.
 */
public class Settings {
    /**
     * Settings stage.
     */
    private Stage settingsStage = new Stage();
    /**
     * The gap between things in the vbox.
     */
    private static final int VBOX_GAP = 50;
    /**
     * VBox with all the stuff.
     */
    private VBox pane = new VBox(VBOX_GAP);
    /**
     * Label with text.
     */
    private Label difficultyLevel = new Label("Game difficulty (1-7): ");
    /**
     * Difficulty input field.
     */
    private TextArea difficultyLevelInput = new TextArea();
    /**
     * Difficulty level HBox.
     */
    private HBox difficultyLevelHBox = new HBox();
    /**
     * Label with text.
     */
    private Label speedLevel = new Label("Game speed (1-10): ");
    /**
     * Game speed input field.
     */
    private TextArea speedLevelInput = new TextArea();
    /**
     * Speed level HBox.
     */
    private HBox speedLevelHBox = new HBox();
    /**
     * Button that sets the settings.
     */
    private Button okButton = new Button("OK");
    /**
     * Game difficulty and speed labels' width.
     */
    private static final int LABEL_WIDTH = 60;
    /**
     * Game difficulty and speed labels' height.
     */
    private static final int LABEL_HEIGHT = 20;
    /**
     * Pane width.
     */
    private static final int PANE_WIDTH = 300;
    /**
     * Pane height.
     */
    private static final int PANE_HEIGHT = 400;
    /**
     * Constructor.
     * @param gameDifficulty game difficulty level (1-7) (Shows how many different blocks are in the game.
     * @param gameSpeed Game speed level(1-10).
     */
    public Settings(int gameDifficulty, int gameSpeed) {

        difficultyLevel.setStyle("-fx-font: 22 arial;");
        difficultyLevelInput.setPrefSize(LABEL_WIDTH, LABEL_HEIGHT);
        difficultyLevelInput.setText(gameDifficulty + "");
        difficultyLevelHBox.getChildren().add(difficultyLevel);
        difficultyLevelHBox.getChildren().add(difficultyLevelInput);
        pane.getChildren().add(difficultyLevelHBox);

        speedLevel.setStyle("-fx-font: 22 arial;");
        speedLevelInput.setPrefSize(LABEL_WIDTH, LABEL_HEIGHT);
        speedLevelInput.setText(gameSpeed + "");
        speedLevelHBox.getChildren().add(speedLevel);
        speedLevelHBox.getChildren().add(speedLevelInput);
        pane.getChildren().add(speedLevelHBox);

        okButton.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        pane.getChildren().add(okButton);

        settingsStage.setTitle("Tetris");
        settingsStage.setScene(new Scene(pane, PANE_WIDTH, PANE_HEIGHT));
        settingsStage.setResizable(false);
        settingsStage.sizeToScene();
    }

    /**
     * Getter for ok button.
     * @return Button.
     */
    public Button getOkButton() {
        return okButton;
    }

    /**
     * Getter for game difficulty.
     * @return int game difficulty
     */
    public int getGameDifficulty() {
        return Integer.parseInt(difficultyLevelInput.getText());
    }

    /**
     * Getter for game speed.
     * @return int game speed
     */
    public int getGameSpeed() {
        return Integer.parseInt(speedLevelInput.getText());
    }

    /**
     * Shows settings menu when called.
     */
    public void showSettings() {
        settingsStage.show();
    }
}
