import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Kristjan on 21/04/2016.
 */
public class GameField {
    /**
     * Pane with the gamefield on it.
     */
    private Pane pane = new Pane();
    /**
     * Game field grid width.
     */
    private static final int GRID_WIDTH = 10;
    /**
     * Game field grid height.
     */
    private static final int GRID_HEIGHT = 20;
    /**
     * Rectangles that the game field pane is made of.
     */
    private Rectangle[][] rectangles = new Rectangle[GRID_HEIGHT][GRID_WIDTH];
    /**
     * One block height in pixels.
     */
    private static final int ONE_BLOCK_HEIGHT = 30;
    /**
     * One block width in pixels.
     */
    private static final int ONE_BLOCK_WIDTH = 40;
    /**
     * Gamefield width.
     */
    private static final int GAMEFIELD_WIDTH = 400;
    /**
     * Gamefield height.
     */
    private static final int GAMEFIELD_HEIGHT = 600;
    /**
     * Constructor for gamefield.
     */
    public GameField() {

        pane.relocate(0, 0);
        pane.setPrefSize(GAMEFIELD_WIDTH, GAMEFIELD_HEIGHT);
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                rectangles[i][j]
                        = new Rectangle(ONE_BLOCK_WIDTH * j, ONE_BLOCK_HEIGHT * i, ONE_BLOCK_WIDTH, ONE_BLOCK_HEIGHT);
                rectangles[i][j].setFill(Color.WHITE);
                pane.getChildren().add(rectangles[i][j]);
            }
        }

    }
    /**
     * Getter for pane.
     * @return Pane.
     */
    public Pane getPane() {
        return pane;
    }
    /**
     * Determines whether a block fits in a given position or not.
     * @param row Block position row on the grid.
     * @param column Block position column on the grid.
     * @param rotation Block shape as a matrix.
     * @return True if block fits, false otherwise.
     */
    public boolean checkIfPieceFits(int row, int column, Rotation rotation) {
        boolean state = true;

        for (int i = 0; i < rotation.getRotation().length; i++) {
            for (int j = 0; j < rotation.getRotation()[0].length; j++) {
                try {
                    if (row + i > (GRID_HEIGHT - 1) || column + j > (GRID_WIDTH - 1)) {
                        state = false;
                    }
                    if (rectangles[row + i][column + j].getFill().equals(Color.DARKSLATEGREY)
                            && rotation.getRotation()[i][j] == 1) {
                        state = false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    state = false;
                }
            }
            if (!state) break;
        }

        return state;
    }

    /**
     * Checks whether a block can rotate in the given position.
     * @param row Block position row.
     * @param column Block position column.
     * @param oldRotation The position the block is in before rotation.
     * @param newRotation The position the block is turned.
     * @return True if block can be rotated, false otherwise.
     */
    public boolean checkIfCanRotate(int row, int column, Rotation oldRotation, Rotation newRotation) {
        boolean state = true;

        for (int i = 0; i < newRotation.getRotation().length; i++) {
            for (int j = 0; j < newRotation.getRotation()[0].length; j++) {
                try {
                    if (row + i > (GRID_HEIGHT - 1) || column + j > (GRID_WIDTH - 1)) {
                        state = false;
                    }
                    if (rectangles[row + i][column + j].getFill().equals(Color.DARKSLATEGREY)
                            && newRotation.getRotation()[i][j] == 1) {
                        if (oldRotation.getRotation()[i][j] != 1) state = false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    state = false;
                }
            }
            if (!state) break;
        }

        return state;
    }
    /**
     * Checks if the block has laned.
     * @param row Block row.
     * @param column block column.
     * @param rotation block shape.
     * @return True if block landed, false otherwise
     */
    public boolean checkIfPieceLanded(int row, int column, Rotation rotation) {
        boolean state = false;
        for (int i = 0; i < rotation.getRotation().length; i++) {
            for (int j = 0; j < rotation.getRotation()[0].length; j++) {
                try {
                    if (row + i == (GRID_HEIGHT - 1)) {
                        state = true;
                    }
                    if (rectangles[row + i + 1][column + j].getFill().equals(Color.DARKSLATEGREY)
                            && rotation.getRotation()[i][j] == 1) {
                        state = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            if (state) break;
        }

        return state;
    }

    /**
     * Updates the board.
     * @param row block row.
     * @param column block column.
     * @param blockShape block shape as a 2d array.
     * @param color Color that the given place needs to be painted as.
     */
    public void updateBoard(int row, int column, int[][] blockShape, Color color) {

        for (int i = 0; i < blockShape.length; i++) {
            for (int j = 0; j < blockShape[0].length; j++) {
                if (blockShape[i][j] == 1) {
                    rectangles[row + i][column + j].setFill(color);
                    if (color.equals(Color.WHITE)) rectangles[row + i][column + j].setStroke(Color.WHITE);
                    else rectangles[row + i][column + j].setStroke(Color.BLACK);
                }

            }
        }

    }
    /**
     * Score.
     */
    private int score = 0;
    /**
     * score when 1 line is moved.
     */
    private static final int SCORE_1_LINE = 100;
    /**
     * score when 2 lines are moved.
     */
    private static final int SCORE_2_LINE = 200;
    /**
     * score when 3 lines are moved.
     */
    private static final int SCORE_3_LINE = 900;
    /**
     * score when 4 lines are moved.
     */
    private static final int SCORE_4_LINE = 1600;
    /**
     * This is needed for calculating the score when 3 lines are removed.
     */
    private static final int NUMBER_3 = 3;
    /**
     * This is needed for calculating the score when 4 lines are removed.
     */
    private static final int NUMBER_4 = 4;
    /**
     * Deletes full lines.
     */
    public void deleteFullLines() {
        int lines = 0;
//        DELETES FULL LINES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        boolean rowFull;
        for (int i = 0; i < GRID_HEIGHT; i++) {
            rowFull = true;
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (rectangles[i][j].getFill().equals(Color.WHITE)) rowFull = false;
            }
            if (rowFull) {
                lines++;
                for (int j = i; j > 0; j--) {
                    for (int k = 0; k < GRID_WIDTH; k++) {
                        rectangles[j][k].setFill(rectangles[j - 1][k].getFill());
                        if (rectangles[j - 1][k].getFill().equals(Color.WHITE)) {
                            rectangles[j][k].setStroke(Color.WHITE);
                        }

                    }
                }
            }
        }
        //          CALCULATES SCORE
        switch (lines) {
            case 1:
                score += SCORE_1_LINE;
                break;
            case 2:
                score += SCORE_2_LINE;
                break;
            case NUMBER_3:
                score += SCORE_3_LINE;
                break;
            case NUMBER_4:
                score += SCORE_4_LINE;
                break;
            default: break;
        }
    }
    /**
     * Getter for score.
     * @return score int.
     */
    public int getScore() {
        return score;
    }

}
