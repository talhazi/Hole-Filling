package holeFiller;

import java.util.ArrayList;

public class Connectivity_Handler {

    // add the boundary pixels of Pixel u to boundary, according to 4-Connect
    protected static void handle4Connect(Pixel[][] pixelsMap, ArrayList<Pixel> boundary, Pixel u) {
        int x = u.getX();
        int y = u.getY();
        Pixel[] potentialBoundary4Connect = new Pixel[]{pixelsMap[x-1][y],
                                                        pixelsMap[x+1][y],
                                                        pixelsMap[x][y-1],
                                                        pixelsMap[x][y+1]};
        for (Pixel pixel : potentialBoundary4Connect) {
            if (!Hole_Handler.isHole(pixel) && !boundary.contains(pixel)) {
                boundary.add(pixel);
            }
        }
    }

    // add the boundary pixels of Pixel u to boundary, according to 8-Connect\4-Connect
    protected static void handle8Connect(Pixel[][] pixelsMap, ArrayList<Pixel> boundary, Pixel u) {
        int x = u.getX();
        int y = u.getY();
        Pixel[] potentialBoundary8Connect = new Pixel[]{pixelsMap[x-1][y-1],
                                                        pixelsMap[x+1][y-1],
                                                        pixelsMap[x-1][y+1],
                                                        pixelsMap[x+1][y+1]};
        for (Pixel pixel : potentialBoundary8Connect) {
            if (!Hole_Handler.isHole(pixel) && !boundary.contains(pixel)) {
                boundary.add(pixel);
            }
        }
    }

}
