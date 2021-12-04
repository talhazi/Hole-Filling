package cmdUtil;

import holeFiller.Hole_Handler;
import java.io.IOException;

/*
 * >>> PLEASE READ THE README FILE BEFORE RUNNING THE PROGRAM <<<
 *
 * @author: Tal Hazi
 * @version: 1.0
 *
 * Last Updated Date: Dec 5, 2021
 */

public class CmdUtil {
    private static final int zDefault = 3;
    private static final float epsilonDefault = 0.001f;
    private static final int cTypeDefault = 8;

    static public void main(String[] args) {
        try {
            // args parsing
            float epsilon;
            int z, connectivityType;
            if (args.length == 2 || args.length == 5) {
                z = args.length == 2 ? zDefault : Integer.parseInt(args[2]);
                epsilon = args.length == 2 ? epsilonDefault : Float.parseFloat(args[3]);
                connectivityType = args.length == 2 ? cTypeDefault : Integer.parseInt(args[4]);
            } else {
                throw new IllegalArgumentException("Usage: required: <image> <image_mask> optional: <z> <E> <connectivityType>");
            }
            String imagePath = args[0];
            String imageMaskPath = args[1];

            // main goal function
            Hole_Handler holeHandler = new Hole_Handler(imagePath, imageMaskPath, z, epsilon, connectivityType);
            holeHandler.run();  // save the fixed image as "output.jpg" in the main folder

        } catch (IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

}
