package holeFiller;

import holeFiller.passiveObjects.HoledImage;
import holeFiller.passiveObjects.Pixel;

import java.io.IOException;

/**
 * HoleFiller holds the cmdline arguments, validate them and run the process
 */
public class HoleFiller {
    private final String imagePath;
    private final String imageMaskPath;
    private final int Z;
    private final float epsilon;
    private final int connectivityType;
    static final float maxMaskHoleVal = 0.5f; // less value then maxMaskHoleVal will be considered as hall pixel
    static final float HOLE = -1f;

    public HoleFiller(String imagePath, String imageMaskPath, int Z, float epsilon, int connectivityType){
        this.imagePath = imagePath;
        this.imageMaskPath = imageMaskPath;
        this.Z = Z;
        this.epsilon = IOHandler.validateEpsilon(epsilon); // shouldn't be 0
        this.connectivityType = IOHandler.validateConnectivityType(connectivityType); // should be 4 or 8
    }

    /**
     * runs the whole process: create HoledImage > fill his hole > save it as output.jpg
     */
    public void run() throws IOException {
        HoledImage holedImage = new HoledImage(imagePath, imageMaskPath, connectivityType);
        fillHole(holedImage);
        String outputName = "output.jpg";
        IOHandler.createImgFile(holedImage, outputName);
        System.out.println("Program finished, the image has been filled successfully!\n");
    }

    static boolean isHole(Pixel p) {
        return p.getValue() == HOLE;
    }

    /**
     * update the hole values in the pixelsMap of holedImage to new values according to the algo formula
     *
     * @param  holedImage   the holed image that should be filled
     */
    private void fillHole(HoledImage holedImage) {
        for (Pixel u : holedImage.hole) {
            float newColorValue = AlgoFormula.calcColor(u, holedImage.boundary, Z, epsilon);
            int x = u.getX();
            int y = u.getY();
            HoledImage.pixelsMap[x][y].setValue(newColorValue);
        }
    }

}
