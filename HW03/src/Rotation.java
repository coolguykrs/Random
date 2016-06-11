/**
 * Created by Kristjan on 26/03/2016.
 */
public final class Rotation {
    /**
     * 2d int array of the block shape, 0 is where the block isnt and 1 is where it is.
     */
    private int[][] rotation;
    /**
     * the number of the rotation for the block.
     */
    private int rotationNumber;
    /**
     * gets rotationnumber.
     * @return rotation number
     */
    public int getRotationNumber() {
        return rotationNumber;
    }
    /**
     * constructor.
     * @param rotation 2d array of the block.
     * @param rotationNumber the rotation's number
     */
    private Rotation(int[][] rotation, int rotationNumber) {
        this.rotation = rotation;
        this.rotationNumber = rotationNumber;
    }
    /**
     * gets rotation.
     * @return 2d array of the block
     */
    public int[][] getRotation() {
        return rotation;
    }

    /**
     * number 3, needed to lose magic numbers.
     */
    private static final int NUMBER_3 = 3;

    /**
     * number 4, needed to lose magic numbers.
     */
    private static final int NUMBER_4 = 4;

    /**
     * gets "I" block with 0 rotation.
     * @return "I" block (2d array)
     */
    public static Rotation getIrot0() {
        int[][] rotation = new int[1][NUMBER_4];
        rotation[0][0] = 1;
        rotation[0][1] = 1;
        rotation[0][2] = 1;
        rotation[0][NUMBER_3] = 1;
        return new Rotation(rotation, 0);
    }

    /**
     * gets "I" block with 1 rotation.
     * @return "I" block (2d array)
     */
    public static Rotation getIrot1() {
        int[][] rotation = new int[NUMBER_4][1];
        rotation[0][0] = 1;
        rotation[1][0] = 1;
        rotation[2][0] = 1;
        rotation[NUMBER_3][0] = 1;
        return new Rotation(rotation, 1);
    }

    /**
     * gets "O" block.
     * @return "O" block.
     */
    public static Rotation getOrot0() {
        int[][] rotation = new int[2][2];
        rotation[0][0] = 1;
        rotation[0][1] = 1;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        return new Rotation(rotation, 0);
    }

    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getJrot0() {
        int[][] rotation = new int[NUMBER_3][2];
        rotation[0][0] = 0;
        rotation[0][1] = 1;
        rotation[1][0] = 0;
        rotation[1][1] = 1;
        rotation[2][0] = 1;
        rotation[2][1] = 1;
        return new Rotation(rotation, 0);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getJrot1() {
        int[][] rotation = new int[2][NUMBER_3];
        rotation[0][0] = 1;
        rotation[0][1] = 0;
        rotation[0][2] = 0;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        rotation[1][2] = 1;
        return new Rotation(rotation, 1);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getJrot2() {
        int[][] rotation = new int[NUMBER_3][2];
        rotation[0][0] = 1;
        rotation[0][1] = 1;
        rotation[1][0] = 1;
        rotation[1][1] = 0;
        rotation[2][0] = 1;
        rotation[2][1] = 0;
        return new Rotation(rotation, 2);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getJrot3() {
        int[][] rotation = new int[2][NUMBER_3];
        rotation[0][0] = 1;
        rotation[0][1] = 1;
        rotation[0][2] = 1;
        rotation[1][0] = 0;
        rotation[1][1] = 0;
        rotation[1][2] = 1;
        return new Rotation(rotation, NUMBER_3);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getLrot0() {
        int[][] rotation = new int[NUMBER_3][2];
        rotation[0][0] = 1;
        rotation[0][1] = 0;
        rotation[1][0] = 1;
        rotation[1][1] = 0;
        rotation[2][0] = 1;
        rotation[2][1] = 1;
        return new Rotation(rotation, 0);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getLrot1() {
        int[][] rotation = new int[2][NUMBER_3];
        rotation[0][0] = 1;
        rotation[0][1] = 1;
        rotation[0][2] = 1;
        rotation[1][0] = 1;
        rotation[1][1] = 0;
        rotation[1][2] = 0;
        return new Rotation(rotation, 0);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getLrot2() {
        int[][] rotation = new int[NUMBER_3][2];
        rotation[0][0] = 1;
        rotation[0][1] = 1;
        rotation[1][0] = 0;
        rotation[1][1] = 1;
        rotation[2][0] = 0;
        rotation[2][1] = 1;
        return new Rotation(rotation, 2);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getLrot3() {
        int[][] rotation = new int[2][NUMBER_3];
        rotation[0][0] = 0;
        rotation[0][1] = 0;
        rotation[0][2] = 1;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        rotation[1][2] = 1;
        return new Rotation(rotation, NUMBER_3);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getTrot0() {
        int[][] rotation = new int[2][NUMBER_3];
        rotation[0][0] = 0;
        rotation[0][1] = 1;
        rotation[0][2] = 0;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        rotation[1][2] = 1;
        return new Rotation(rotation, 0);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getTrot1() {
        int[][] rotation = new int[NUMBER_3][2];
        rotation[0][0] = 1;
        rotation[0][1] = 0;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        rotation[2][0] = 1;
        rotation[2][1] = 0;
        return new Rotation(rotation, 1);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getTrot2() {
        int[][] rotation = new int[2][NUMBER_3];
        rotation[0][0] = 1;
        rotation[0][1] = 1;
        rotation[0][2] = 1;
        rotation[1][0] = 0;
        rotation[1][1] = 1;
        rotation[1][2] = 0;
        return new Rotation(rotation, 2);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getTrot3() {
        int[][] rotation = new int[NUMBER_3][2];
        rotation[0][0] = 0;
        rotation[0][1] = 1;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        rotation[2][0] = 0;
        rotation[2][1] = 1;
        return new Rotation(rotation, NUMBER_3);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getZrot0() {
        int[][] rotation = new int[2][NUMBER_3];
        rotation[0][0] = 1;
        rotation[0][1] = 1;
        rotation[0][2] = 0;
        rotation[1][0] = 0;
        rotation[1][1] = 1;
        rotation[1][2] = 1;
        return new Rotation(rotation, 0);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getZrot1() {
        int[][] rotation = new int[NUMBER_3][2];
        rotation[0][0] = 0;
        rotation[0][1] = 1;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        rotation[2][0] = 1;
        rotation[2][1] = 0;
        return new Rotation(rotation, 1);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getSrot0() {
        int[][] rotation = new int[2][NUMBER_3];
        rotation[0][0] = 0;
        rotation[0][1] = 1;
        rotation[0][2] = 1;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        rotation[1][2] = 0;
        return new Rotation(rotation, 0);
    }
    /**
     * Gets rotation as 2d int array.
     * @return int array.
     */
    public static Rotation getSrot1() {
        int[][] rotation = new int[NUMBER_3][2];
        rotation[0][0] = 1;
        rotation[0][1] = 0;
        rotation[1][0] = 1;
        rotation[1][1] = 1;
        rotation[2][0] = 0;
        rotation[2][1] = 1;
        return new Rotation(rotation, 1);
    }
}
