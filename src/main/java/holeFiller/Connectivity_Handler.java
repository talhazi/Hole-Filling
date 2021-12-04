package holeFiller;

import java.util.ArrayList;

public class Connectivity_Handler {

    // add the boundary pixels of Pixel u to boundary, according to 4-Connect
    public static void handleC4(Pixel[][] pixelsMap, ArrayList<Pixel> boundary, Pixel u) {
        int x = u.getX();
        int y = u.getY();
        Pixel[] potentialBoundaryC4 = new Pixel[]{pixelsMap[x-1][y],
                                                pixelsMap[x+1][y],
                                                pixelsMap[x][y-1],
                                                pixelsMap[x][y+1]};
        for (Pixel pixel : potentialBoundaryC4) {
            if (!Hole_Handler.isHole(pixel) && !boundary.contains(pixel)) {
                boundary.add(pixel);
            }
        }
    }

    // add the boundary pixels of Pixel u to boundary, according to 8-Connect\4-Connect
    public static void handleC8(Pixel[][] pixelsMap, ArrayList<Pixel> boundary, Pixel u) {
        int x = u.getX();
        int y = u.getY();
        Pixel[] potentialBoundaryC8 = new Pixel[]{pixelsMap[x-1][y-1],
                                                pixelsMap[x+1][y-1],
                                                pixelsMap[x-1][y+1],
                                                pixelsMap[x+1][y+1]};
        for (Pixel pixel : potentialBoundaryC8) {
            if (!Hole_Handler.isHole(pixel) && !boundary.contains(pixel)) {
                boundary.add(pixel);
            }
        }
    }

}
