package holeFiller;

import holeFiller.passiveObjects.HoledImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IOHandler {

    public IOHandler() {
    }

    /**
     * make sure connectivityType is 4 or 8
     *
     * @param  connectivityType   the connectivity type
     * @return  connectivityType   or throwing exception if the connectivity type is not 4 or 8
     */
     int validateConnectivityType(int connectivityType) {
        ArrayList<Integer> legalConnectivityType = new ArrayList<>();
        legalConnectivityType.add(4);
        legalConnectivityType.add(8);
        if (!legalConnectivityType.contains(connectivityType)) {
            throw new IllegalArgumentException("Illegal Connectivity Type Given!");
        }
        return connectivityType;
    }

    // make sure epsilon != 0
     float validateEpsilon(float epsilon) {
        if (epsilon == 0f) {
            throw new IllegalArgumentException("Epsilon can't be equal to ZERO!");
        }
        return epsilon;
    }

     BufferedImage getBufferedImage(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return image;
    }

    /**
     * convert HoledImage to jpg file and save it as outputName in the main folder
     *
     * @param  holedImage   the holed image after it has been fixed
     * @param  outputName   the output name for the fixed image
     */
     void createImgFile(HoledImage holedImage, String outputName) throws IOException {
        BufferedImage filledImage = new BufferedImage(holedImage.getWidth(), holedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < holedImage.getWidth(); i++) {
            for (int j = 0; j < holedImage.getHeight(); j++) {
                float pixelColor = holedImage.getPixelsMap()[i][j].getValue();
                Color c = new Color(pixelColor, pixelColor, pixelColor);
                filledImage.setRGB(i, j, c.getRGB());
            }
        }
        File output = new File(outputName);
        ImageIO.write(filledImage, "jpg", output);
        System.out.println("File has been saved to main folder as: " + outputName);
    }

}
