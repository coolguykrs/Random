import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by Kristjan on 18/04/2016.
 */
public class RightSide {
    /**
     * Right side's pane.
     */
    Pane pane = new Pane();
    /**
     * Start button.
     */
    Button startButton = new Button("Start");
    /**
     * Label with score on it.
     */
    Label scoreInfo = new Label();
    /**
     * Settings button.
     */
    Button settingsButton = new Button("Settings");
    /**
     * Getter for settings button.
     * @return Settings Button
     */
    public Button getSettingsButton() {
        return settingsButton;
    }

    /**
     * Getter for start button.
     * @param gameOn Shows if a game is currently on.
     * @return Start button.
     */
    public Button getStartButton(boolean gameOn) {
        if (!gameOn) {
            startButton.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        } else {
            startButton.setStyle("-fx-font: 22 arial; -fx-base: #666666;");
        }

        return startButton;
    }

    /**
     * Buttons x-axis location.
     */
    private static final int BUTTON_LOCATION_X = 100;
    /**
     * Buttons y-axis location.
     */
    private static final int START_BUTTON_LOCATION_Y = 50;
    /**
     * Buttons y-axis location.
     */
    private static final int SCORE_LABEL_LOCATION_Y = 150;
    /**
     * Default height.
     */
    private static final int DEFAULT_HEIGHT = 25;
    /**
     * Settings button x-axis location.
     */
    private static final int SETTINGS_BUTTON_LOCATION_X = 175;
    /**
     * Settings button y-axis location.
     */
    private static final int SETTINGS_BUTTON_LOCATION_Y = 550;
    /**
     * Pane's x-axis location.
     */
    private static final int PANE_LOCATION_X = 400;
    /**
     * Pane width.
     */
    private static final int PANE_WIDTH = 400;
    /**
     * Pane height.
     */
    private static final int PANE_HEIGHT = 600;
    /**
     * Constructor.
     */
    public RightSide() {
        startButton.relocate(BUTTON_LOCATION_X, START_BUTTON_LOCATION_Y);
        startButton.setPrefSize(BUTTON_LOCATION_X * 2, DEFAULT_HEIGHT);
//        startButton.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        pane.getChildren().add(startButton);

        scoreInfo.relocate(BUTTON_LOCATION_X, SCORE_LABEL_LOCATION_Y);
        scoreInfo.setPrefSize(BUTTON_LOCATION_X * 2, DEFAULT_HEIGHT);
        scoreInfo.setStyle("-fx-font: 22 arial;");
        scoreInfo.setText("Score: " + 0);
        pane.getChildren().add(scoreInfo);

        settingsButton.relocate(SETTINGS_BUTTON_LOCATION_X, SETTINGS_BUTTON_LOCATION_Y);
        settingsButton.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        settingsButton.setPrefSize(BUTTON_LOCATION_X * 2, DEFAULT_HEIGHT);
        pane.getChildren().add(settingsButton);

        pane.relocate(PANE_LOCATION_X, 0);
        pane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        pane.setStyle("-fx-background-color: #91a043;");
    }

    /**
     * Updates score.
     * @param score score.
     */
    public void setScoreInfo(int score) {
        scoreInfo.setText("Score: " + score);
    }

    /**
     * Getter for pane.
     * @return Pane.
     */
    public Pane getPane() {
        return pane;
    }
}
