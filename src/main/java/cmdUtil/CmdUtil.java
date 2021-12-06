package cmdUtil;

import holeFiller.HoleFiller;

import java.io.IOException;

/**
 * >>> PLEASE READ THE README FILE BEFORE RUNNING THE PROGRAM <<<
 *
 * @author: Tal Hazi
 * @version: 1.0
 * @lastUpdatedDate: Dec 5, 2021
 */
public class CmdUtil {
    private static final int zDefault = 3;
    private static final float epsilonDefault = 0.001f;
    private static final int cTypeDefault = 8;

    public static void main(String[] args) {
        try {
            // args parsing
            float epsilon;
            int Z, connectivityType;
            if (args.length == 2 || args.length == 5) {
                Z = args.length == 2 ? zDefault : Integer.parseInt(args[2]);
                epsilon = args.length == 2 ? epsilonDefault : Float.parseFloat(args[3]);
                connectivityType = args.length == 2 ? cTypeDefault : Integer.parseInt(args[4]);
            } else {
                throw new IllegalArgumentException("Usage: required: <image> <image_mask> optional: <Z> <epsilon> <connectivityType>");
            }
            String imagePath = args[0];
            String imageMaskPath = args[1];

            // main goal function
            HoleFiller holeFiller = new HoleFiller(imagePath, imageMaskPath, Z, epsilon, connectivityType);
            holeFiller.run();  // save the fixed image as "output.jpg" in the main folder

        } catch (IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

}
