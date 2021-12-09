package holeFiller.passiveObjects;

import holeFiller.HoledImageHandler;

import java.util.ArrayList;

/**
 *  HoledImage holds the holed image pixels values:
 *  the original image values normalized to be in range [0,1], and the mask values to -1float
 *  in addition it's holds the hole pixels and the hall's boundary pixels
 */
public class HoledImage {
    private final Pixel[][] pixelsMap;
    private final ArrayList<Pixel> hole;
    private final ArrayList<Pixel> boundary;
    private final int width;
    private final int height;

    public HoledImage(String imagePath, String maskPath, int connectivityType) {
        HoledImageHandler holedImageHandler = new HoledImageHandler();
        pixelsMap = holedImageHandler.getHoledImageMap(imagePath, maskPath);
        hole = holedImageHandler.getHolePixels(pixelsMap);
        boundary = holedImageHandler.getBoundaryPixels(pixelsMap, hole, connectivityType);
        width = pixelsMap.length;
        height = pixelsMap[0].length;
    }

    public Pixel[][] getPixelsMap() {
        return pixelsMap;
    }

    public ArrayList<Pixel> getHole() {
        return hole;
    }

    public ArrayList<Pixel> getBoundary() {
        return boundary;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // print image pixels values as 2D
    void printImage(){
        for(Pixel[] row : pixelsMap){
            for(Pixel pixel : row){
                System.out.printf("%7.3f", pixel.getValue());
            }
            System.out.println();
        }
        System.out.println();
    }

}
