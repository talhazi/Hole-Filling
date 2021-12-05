package holeFiller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IO_Handler {

    // make sure connectivityType is 4 or 8
    protected static int validateConnectivityType(int connectivityType) {
        ArrayList<Integer> legalConnectivityType = new ArrayList<>();
        legalConnectivityType.add(4);
        legalConnectivityType.add(8);
        if (!legalConnectivityType.contains(connectivityType)) {
            throw new IllegalArgumentException("Illegal Connectivity Type Given!");
        }
        return connectivityType;
    }

    // make sure epsilon != 0
    protected static float validateEpsilon(float epsilon) {
        if (epsilon == 0f) {
            throw new IllegalArgumentException("Epsilon can't be equal to ZERO!");
        }
        return epsilon;
    }

    protected static BufferedImage getBufferedImage(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return image;
    }

    // convert HoledImage to jpg file and save it as outputName in the main folder
    protected static void createImgFile(HoledImage holedImage, String outputName) throws IOException {
        BufferedImage filledImage = new BufferedImage(holedImage.width, holedImage.height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < holedImage.width; i++) {
            for (int j = 0; j < holedImage.height; j++) {
                float pixelColor = HoledImage.pixelsMap[i][j].getValue();
                Color c = new Color(pixelColor, pixelColor, pixelColor);
                filledImage.setRGB(i, j, c.getRGB());
            }
        }
        File output = new File(outputName);
        ImageIO.write(filledImage, "jpg", output);
        System.out.println("File has been saved to main folder as: " + outputName);
    }


}
