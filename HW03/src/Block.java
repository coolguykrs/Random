import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristjan on 26/03/2016.
 */
public class Block {
    /**
     * block shape.
     */
    private String block;

    /**
     * constructor.
     * @param block block shape.
     */
    public Block(String block) {
        this.block = block;
    }

    /**
     * gets block.
     * @return block string
     */
    public String getBlock() {
        return block;
    }

    /**
     * gets the block's all rotations.
     * @return list of block's rotations.
     */
    public List<Rotation> getRotations() {
        List<Rotation> rotations = new ArrayList<>();
        switch (block) {
            case "I":
                rotations.add(Rotation.getIrot0());
                rotations.add(Rotation.getIrot1());
                break;
            case "O":
                rotations.add(Rotation.getOrot0());
                rotations.add(Rotation.getOrot0());
                break;
            case "J":
                rotations.add(Rotation.getJrot0());
                rotations.add(Rotation.getJrot1());
                rotations.add(Rotation.getJrot2());
                rotations.add(Rotation.getJrot3());
                break;
            case "L":
                rotations.add(Rotation.getLrot0());
                rotations.add(Rotation.getLrot1());
                rotations.add(Rotation.getLrot2());
                rotations.add(Rotation.getLrot3());
                break;
            case "T":
                rotations.add(Rotation.getTrot0());
                rotations.add(Rotation.getTrot1());
                rotations.add(Rotation.getTrot2());
                rotations.add(Rotation.getTrot3());
                break;
            case "Z":
                rotations.add(Rotation.getZrot0());
                rotations.add(Rotation.getZrot1());
                break;
            case "S":
                rotations.add(Rotation.getSrot0());
                rotations.add(Rotation.getSrot1());
                break;
            default: break;
        }
        return rotations;
    }


}
