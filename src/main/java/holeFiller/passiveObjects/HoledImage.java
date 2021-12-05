package holeFiller.passiveObjects;

import holeFiller.HoledImageHandler;

import java.util.ArrayList;

/**
 *  HoledImage holds the holed image pixels values:
 *  the original image values normalized to be in range [0,1], and the mask values to -1float
 *  in addition it's holds the hole pixels and the hall's boundary pixels
 */
public class HoledImage {
    public static Pixel[][] pixelsMap;
    public ArrayList<Pixel> hole;
    public ArrayList<Pixel> boundary;
    public int width;
    public int height;

    public HoledImage(String imagePath, String maskPath, int connectivityType) {
        pixelsMap = HoledImageHandler.getHoledImageMap(imagePath, maskPath);
        hole = HoledImageHandler.getHolePixels(pixelsMap);
        boundary = HoledImageHandler.getBoundaryPixels(pixelsMap, hole, connectivityType);
        width = pixelsMap.length;
        height = pixelsMap[0].length;
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
