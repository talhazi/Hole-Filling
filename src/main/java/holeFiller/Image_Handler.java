package holeFiller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Image_Handler {

    // initializes 2D map, which represents the image with the hole;
    // the original image values normalized to be in range [0,1] and the mask values to -1float.
    protected static Pixel[][] getHoledImageMap(String imagePath, String imageMaskPath) {
        Pixel[][] pixelsMap = null;
        float value;
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImage imageMask = ImageIO.read(new File(imageMaskPath));
            int width = image.getWidth();
            int height = image.getHeight();
            pixelsMap = new Pixel[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color pixelColor = new Color(imageMask.getRGB(x, y));
                    value = getNormalGrayscale(pixelColor);
                    if (value < 0.5)
                        value = Hole_Handler.HOLE;
                    else
                        value = getNormalGrayscale(new Color(image.getRGB(x, y)));
                    pixelsMap[x][y] = new Pixel(x, y, value);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return pixelsMap;
    }

    // convert rgb to grayscale according to the average method, and return the normalized grayscale value (in range [0,1])
    private static float getNormalGrayscale(Color c) {
        float average = (float) (c.getRed() + c.getGreen() + c.getBlue()) / 3;
        return average / 255;
    }

    protected static ArrayList<Pixel> getHolePixels(Pixel[][] pixelsMap) {
        ArrayList<Pixel> hole = new ArrayList<>();
        for(Pixel[] row : pixelsMap){
            for(Pixel pixel : row){
                if (Hole_Handler.isHole(pixel)) {
                    hole.add(pixel);
                }
            }
        }
        return hole;
    }

    // return boundary pixels of HoledImage, according to his holes and connectivityType
    // 4-Connect is contained in 8-Connect, so anyway handle 4-Connect and then handle 8-Connect if needed
    protected static ArrayList<Pixel> getBoundaryPixels(Pixel[][] pixelsMap, ArrayList<Pixel> holes, int connectivityType) {
        ArrayList<Pixel> boundary = new ArrayList<>();
        for (Pixel u : holes) {
            Connectivity_Handler.handleC4(pixelsMap, boundary, u);
            if (connectivityType == 8){
                Connectivity_Handler.handleC8(pixelsMap, boundary, u);
            }
        }
        return boundary;
    }

}