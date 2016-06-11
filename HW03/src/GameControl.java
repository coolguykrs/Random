import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 * Created by Kristjan on 18/04/2016.
 */
public class GameControl {
    /**
     * Pane with all the stuff.
     */
    private Pane pane = new Pane();
    /**
     * Getter for pane.
     * @return Pane
     */
    public Pane getPane() {
        return pane;
    }
    /**
     * Class with things that are on the right side of the screen.
     */
    private RightSide rightSide = new RightSide();
    /**
     * Scene width.
     */
    private static final int SCENE_WIDTH = 800;
    /**
     * Scene height.
     */
    private static final int SCENE_HEIGHT = 600;
    /**
     * Scene with all the stuff.
     */
    private Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
    /**
     * String with all the block shapes.
     */
    private String[] blockShapeLetter = {"I", "O", "J", "L", "T", "Z", "S"};
    /**
     * Class that moves the blocks on the screen.
     */
    private MoveBlocks moveBlocks;
    /**
     * Random number needed to determine the next block coming.
     */
    private int random;
    /**
     * Start button.
     */
    private Button startButton;
    /**
     * Boolean that shows if a game is currently on.
     */
    private boolean gameCurrentlyOn = false;
    /**
     * Score.
     */
    private int score;
    /**
     * Settings button, opens the settings menu.
     */
    private Button settingsButton;
    /**
     * getter for settings button.
     * @return Button
     */
    public Button getSettingsButton() {
        return rightSide.getSettingsButton();
    }

    /**
     * Shows game difficulty.
     */
    private int gameDifficulty;
    /**
     * shows game speed.
     */
    private int gameSpeed;

    /**
     * Constructor.
     * @param gameDifficulty game difficulty
     * @param gameSpeed game speed
     */
    public GameControl(int gameDifficulty, int gameSpeed) {

        setSettings(gameDifficulty, gameSpeed);

        scene.setOnKeyPressed(event -> {
            if (gameCurrentlyOn) {
                if (event.getCode() == KeyCode.RIGHT) {
                    moveBlocks.moveBlock(MoveBlocks.Direction.RIGHT);
                } else if (event.getCode() == KeyCode.LEFT) {
                    moveBlocks.moveBlock(MoveBlocks.Direction.LEFT);
                } else if (event.getCode() == KeyCode.UP) {
                    moveBlocks.moveBlock(MoveBlocks.Direction.UP);
                } else if (event.getCode() == KeyCode.DOWN) {
                    moveBlocks.moveBlockDown(true);
                }
            }
        });

        pane.getChildren().add(rightSide.getPane());

        startButton = rightSide.getStartButton(gameCurrentlyOn);

        startButton.setOnMouseClicked(event -> {
            if (!gameCurrentlyOn) {
                startGame();
                gameCurrentlyOn = true;
                startButton = rightSide.getStartButton(gameCurrentlyOn);
            }
        });
    }

    /**
     * Slowest game speed in milliseconds.
     */
    private static final int SLOWEST_GAME_SPEED = 800;
    /**
     * Default game speed, for when something wrong is entered.
     */
    private static final int DEFAULT_GAME_SPEED = 5;
    /**
     * Max game speed level.
     */
    private static final int MAX_GAME_DIFFICULTY = 7;
    /**
     * Max game speed level.
     */
    private static final int MAX_GAME_SPEED = 10;
    /**
     * Sets the game settings (speed and difficulty).
     * @param gameDifficulty game difficulty (1-7), shows how many different blocks are used.
     * @param gameSpeed game speed(1-10).
     */
    public void setSettings(int gameDifficulty, int gameSpeed) {
        if (gameDifficulty > MAX_GAME_DIFFICULTY || gameDifficulty < 1) this.gameDifficulty = MAX_GAME_DIFFICULTY;
        else this.gameDifficulty = gameDifficulty;
        if (gameSpeed < 1 || gameSpeed > MAX_GAME_SPEED) this.gameSpeed = SLOWEST_GAME_SPEED / DEFAULT_GAME_SPEED;
        else this.gameSpeed = SLOWEST_GAME_SPEED / gameSpeed;
    }
    /**
     * Starts the game.
     */
    private void startGame() {
        GameField gameField = new GameField();

        moveBlocks = new MoveBlocks(makeNewBlock(gameField).getRotations(), gameField, true);
        moveBlocks.startMoving();
        pane.getChildren().add(moveBlocks.getPane());

        startTimer(gameField);
    }
    /**
     * Makes a new random block to be dropped.
     * @param gameField GameField class where the game is saved and shown.
     * @return New random Block.
     */
    private Block makeNewBlock(GameField gameField) {
//        random = (int) (Math.random() * 50 + 1) % gameDifficulty;
        random = (int) (Math.random() * gameDifficulty);
        return new Block(blockShapeLetter[random]);
    }
    /**
     * Starts the timer that makes blocks fall.
     * @param gameField Game field with blocks on it.
     */
    private void startTimer(GameField gameField) {
        DelayedAnimationTimer timer = new DelayedAnimationTimer();
        timer.setDelayInMs(gameSpeed);
        timer.setListener(() -> {
            moveBlockDown(gameField, timer);
        });
        timer.start();
    }

    /**
     * Moves the currently falling block down.
     * @param gameField Game field.
     * @param timer timer that is cancelled when the block reaches the ground.
     */
    private void moveBlockDown(GameField gameField, DelayedAnimationTimer timer) {
        if (moveBlocks.moveBlockDown(false)) {
            moveBlocks = new MoveBlocks(makeNewBlock(gameField).getRotations(), gameField, false);
            if (!moveBlocks.startMoving()) {
                gameCurrentlyOn = false;
                startButton = rightSide.getStartButton(gameCurrentlyOn);
                System.out.println("game over!");
                timer.stop();
            }

        }
        rightSide.setScoreInfo(gameField.getScore());
    }
    /**
     * Getter for scene.
     * @return Scene
     */
    public Scene getScene() {
        return scene;
    }

}
