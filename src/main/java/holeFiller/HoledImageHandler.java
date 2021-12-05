package holeFiller;

import holeFiller.passiveObjects.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HoledImageHandler {

    /**
     * initializes 2D map, which represents the image with the hole;
     * the original image values normalized to be in range [0,1] and the mask values to -1float.
     *
     * @param  imagePath   the path for the source image
     * @param  imageMaskPath   the path for the mask image
     * @return  pixelsMap   which represents the image with the hole
     */
    public static Pixel[][] getHoledImageMap(String imagePath, String imageMaskPath) {
        BufferedImage image = IOHandler.getBufferedImage(imagePath);
        BufferedImage imageMask = IOHandler.getBufferedImage(imageMaskPath);
        int width = image.getWidth();
        int height = image.getHeight();
        // assuming image and imageMask has the same width and height
        Pixel[][] pixelsMap = new Pixel[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // first check if it's hole, if not update the value to normalized image value
                Color pixelColor = new Color(imageMask.getRGB(x, y));
                float value = getNormalGrayscale(pixelColor);
                if (value < 0.5)
                    value = HoleHandler.HOLE;
                else
                    value = getNormalGrayscale(new Color(image.getRGB(x, y)));
                pixelsMap[x][y] = new Pixel(x, y, value);
            }
        }
        return pixelsMap;
    }

    /**
     * return the hole pixels of HoledImage
     *
     * @param  pixelsMap   which represents the image with the hole
     * @return  hole   list of the hole pixels
     */
    public static ArrayList<Pixel> getHolePixels(Pixel[][] pixelsMap) {
        ArrayList<Pixel> hole = new ArrayList<>();
        for(Pixel[] row : pixelsMap){
            for(Pixel pixel : row){
                if (HoleHandler.isHole(pixel)) {
                    hole.add(pixel);
                }
            }
        }
        return hole;
    }

    /**
     * return the boundary pixels of HoledImage, according to his hole and connectivityType
     * 4-Connect is contained in 8-Connect, so anyway handle 4-Connect and then handle 8-Connect if needed
     *
     * @param  pixelsMap   which represents the image with the hole
     * @param  hole   list of the hole pixels
     * @param  connectivityType   4-Connect or 8-Connect, for the boundary
     * @return  boundary   list of the boundary pixels
     */
    public static ArrayList<Pixel> getBoundaryPixels(Pixel[][] pixelsMap, ArrayList<Pixel> hole, int connectivityType) {
        ArrayList<Pixel> boundary = new ArrayList<>();
        for (Pixel u : hole) {
            ConnectivityHandler.handle4Connect(pixelsMap, boundary, u);
            if (connectivityType == 8){
                ConnectivityHandler.handle8Connect(pixelsMap, boundary, u);
            }
        }
        return boundary;
    }

    /**
     * convert rgb to grayscale according to the average method, and return the normalized grayscale value (in range [0,1])
     *
     * @param  c   which represents rgb color
     * @return  normalizedGrayscaleValue in range [0,1]
     */
    private static float getNormalGrayscale(Color c) {
        float average = (float) (c.getRed() + c.getGreen() + c.getBlue()) / 3;
        return average / 255;
    }

}