package holeFiller;

import java.io.IOException;

// Hole_Handler holds the arguments, validate them and run the process
public class Hole_Handler {
    private final String imagePath;
    private final String imageMaskPath;
    private final int z;
    private final float epsilon;
    private final int connectivityType;
    protected static final float HOLE = -1f;

    public Hole_Handler(String imagePath, String imageMaskPath, int z, float epsilon, int connectivityType){
        this.imagePath = imagePath;
        this.imageMaskPath = imageMaskPath;
        this.z = z;
        this.epsilon = epsilon;
        this.connectivityType = IO_Handler.validateConnectivityType(connectivityType);
    }

    // update the hole values of holedImage to new one according to the algo formula
    private void fillHole(HoledImage holedImage, int z, float e) {
        for (Pixel u : holedImage.hole) {
            float newValue = Algo_Formula.calcColor(u, holedImage.boundary, z, e);
            int x = u.getX();
            int y = u.getY();
            HoledImage.pixelsMap[x][y].setValue(newValue);
        }
    }

    protected static boolean isHole(Pixel p) {
        return p.getValue() == HOLE;
    }

    // run the all process: create HoledImage > fill his hole > save it as output.jpg
    public void run() throws IOException {
        HoledImage holedImage = new HoledImage(imagePath, imageMaskPath, connectivityType);
        fillHole(holedImage, z, epsilon);
        String outputName = "output.jpg";
        IO_Handler.createImgFile(holedImage, outputName);
        System.out.println("Program finished, the image has been filled successfully!\n");
    }
}
