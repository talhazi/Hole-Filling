package holeFiller;

import java.util.ArrayList;

// HoledImage holds the holed image pixels values:
// the original image values normalized to be in range [0,1], and the mask values to -1float
// in addition its hold the hole pixels and the hall boundary pixels
public class HoledImage {
    protected static Pixel[][] pixelsMap;
    ArrayList<Pixel> hole;
    ArrayList<Pixel> boundary;
    int width;
    int height;

    protected HoledImage(String imagePath, String maskPath, int connectivityType) {
        pixelsMap = Image_Handler.getHoledImageMap(imagePath, maskPath);
        hole = Image_Handler.getHolePixels(pixelsMap);
        boundary = Image_Handler.getBoundaryPixels(pixelsMap, hole, connectivityType);
        width = pixelsMap.length;
        height = pixelsMap[0].length;
    }

    // print image pixels values as 2D
    protected void printImage(){
        for(Pixel[] row : pixelsMap){
            for(Pixel pixel : row){
                System.out.printf("%7.3f", pixel.getValue());
            }
            System.out.println();
        }
        System.out.println();
    }

}
