import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Created by Kristjan on 18/04/2016.
 */
public class MoveBlocks {
    /**
     * Direction enum.
     */
    public enum Direction {
        /**
         * Up.
         */
        UP,
        /**
         * LEFT.
         */
        LEFT,
        /**
         * RIGHT.
         */
        RIGHT
    }
    /**
     * Game field.
     */
    private GameField gameField;
    /**
     * Pane where blocks are moved.
     */
    private Pane pane = new Pane();
    /**
     * Block shapes.
     */
    private List<Rotation> rotation;
    /**
     * Getter for pane.
     * @return Pane
     */
    public Pane getPane() {
        return pane;
    }
    /**
     * Rotation number.
     */
    private int rotationNumber;
    /**
     * Number of rotations for the given block.
     */
    private int numberOfRotations;
    /**
     * Block X-axis location.
     */
    private int x;
    /**
     * Block Y-axis location.
     */
    private int y;
    /**
     * Boolean that shows whether a block has landed.
     */
    boolean state = false;
    /**
     * Getter for state boolean.
     * @return Boolean state
     */
    public boolean getState() {
        return state;
    }

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
     * @param rotation Block shapes (all rotations).
     * @param gameField Game field.
     * @param isAdd shows if its the first time calling this class instance out.
     */
    public MoveBlocks(List<Rotation> rotation, GameField gameField, boolean isAdd) {
//        state = false;

        this.gameField = gameField;
        pane.relocate(0, 0);
        pane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        numberOfRotations = rotation.size();

        this.rotation = rotation;

        if (isAdd) pane.getChildren().add(gameField.getPane());
    }
    /**
     * Small block default position.
     */
    private static final int SMALL_BLOCK_POSITION_X = 4;
    /**
     * Big block default position.
     */
    private static final int BIG_BLOCK_POSITION_X = 3;
    /**
     * Makes a block start moving and places it on the board in the correct place.
     * @return True if can be placed on the board, false otherwise.
     */
    public boolean startMoving() {
        rotationNumber = 0;
        if (rotation.get(rotationNumber).getRotation()[0].length == 1
                || rotation.get(rotationNumber).getRotation()[0].length == 2) {
            if (gameField.checkIfPieceFits(0, SMALL_BLOCK_POSITION_X, rotation.get(rotationNumber))) {
                gameField.updateBoard(0, SMALL_BLOCK_POSITION_X, rotation.get(rotationNumber).getRotation(), Color.RED);
                y = 0;
                x = SMALL_BLOCK_POSITION_X;
            } else {
                return false;
            }
        } else if (rotation.get(rotationNumber).getRotation()[0].length == SMALL_BLOCK_POSITION_X
                || rotation.get(rotationNumber).getRotation()[0].length == BIG_BLOCK_POSITION_X) {
            if (gameField.checkIfPieceFits(0, SMALL_BLOCK_POSITION_X, rotation.get(rotationNumber))) {
                gameField.updateBoard(0, BIG_BLOCK_POSITION_X, rotation.get(rotationNumber).getRotation(), Color.RED);
                y = 0;
                x = BIG_BLOCK_POSITION_X;
            } else {
                return false;
            }
        }
        moveBlockDown(false);
        return true;
    }
    /**
     * Moves a block down by one unit.
     * @param button Shows if its moved by pressing down or not.
     * @return True if block landed.
     */
    public boolean moveBlockDown(boolean button) {
        if (gameField.checkIfPieceFits(y + 1, x, rotation.get(rotationNumber))) {
            gameField.updateBoard(y, x, rotation.get(rotationNumber).getRotation(), Color.WHITE);
            y++;
            gameField.updateBoard(y, x, rotation.get(rotationNumber).getRotation(), Color.RED);
        } else if (gameField.checkIfPieceLanded(y, x, rotation.get(rotationNumber)) && !button) {
            gameField.updateBoard(y, x, rotation.get(rotationNumber).getRotation(), Color.DARKSLATEGREY);
            gameField.deleteFullLines();
            state = true;
        }
        return state;
    }
    /**
     * Moves block in the given direction, or rotates it.
     * @param direction Direction that is pressed on the keyboard.
     */
    public void moveBlock(Direction direction) {
        if (direction == Direction.RIGHT) {
            if (gameField.checkIfPieceFits(y, x + 1, rotation.get(rotationNumber))) {
                gameField.updateBoard(y, x, rotation.get(rotationNumber).getRotation(), Color.WHITE);
                gameField.updateBoard(y, x + 1, rotation.get(rotationNumber).getRotation(), Color.RED);
                x++;
            }
        } else if (direction == Direction.LEFT) {
            if (gameField.checkIfPieceFits(y, x - 1, rotation.get(rotationNumber))) {
                gameField.updateBoard(y, x, rotation.get(rotationNumber).getRotation(), Color.WHITE);
                gameField.updateBoard(y, x - 1, rotation.get(rotationNumber).getRotation(), Color.RED);
                x--;
            }
        } else if (direction == Direction.UP) {
            if (gameField.checkIfCanRotate(y, x, rotation.get(rotationNumber), rotation.get(nextRotationNumber()))) {
                gameField.updateBoard(y, x, rotation.get(rotationNumber).getRotation(), Color.WHITE);
                rotationNumber = nextRotationNumber();
                gameField.updateBoard(y, x, rotation.get(rotationNumber).getRotation(), Color.RED);
            }
        }
    }
    /**
     * Rotation number 3.
     */
    private static final int ROTATION_NUMBER_3 = 3;
    /**
     * Rotation number 4.
     */
    private static final int ROTATION_NUMBER_4 = 4;
    /**
     * Gets next rotation number.
     * @return next rotation number int.
     */
    private int nextRotationNumber() {
        if (numberOfRotations == 1) return 1;
        if (numberOfRotations == 2 && rotationNumber == 0) return 1;
        if (numberOfRotations == 2 && rotationNumber == 1) return 0;
        if (numberOfRotations == ROTATION_NUMBER_4 && rotationNumber == ROTATION_NUMBER_3) {
            return 0;
        } else {
            return rotationNumber + 1;
        }
    }

}
