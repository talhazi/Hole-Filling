package holeFiller;

import holeFiller.passiveObjects.Pixel;
import java.util.ArrayList;
import static holeFiller.HoledImageHandler.isHole;

public class ConnectivityHandler {

    public ConnectivityHandler() {
    }

    /**
     * add the boundary pixels of Pixel u to boundary, according to 4-Connect
     *
     * @param  pixelsMap   which represents the image with the hole
     * @param  boundary   list of the boundary pixels
     * @param  u   which represents the hole pixel which we want to find "his" boundary
     */
    void handleHorizontalVerticalConnect(Pixel[][] pixelsMap, ArrayList<Pixel> boundary, Pixel u) {
        int x = u.getX();
        int y = u.getY();
        handleConnect(pixelsMap, boundary,x-1, y,x+1, y, x, y-1 ,x,y+1);
    }

    /**
     * add the boundary pixels of Pixel u to boundary, according to 8-Connect\4-Connect
     *
     * @param  pixelsMap   which represents the image with the hole
     * @param  boundary   list of the boundary pixels
     * @param  u   which represents the hole pixel which we want to find "his" boundary
     */
    void handleDiagonalConnect(Pixel[][] pixelsMap, ArrayList<Pixel> boundary, Pixel u) {
        int x = u.getX();
        int y = u.getY();
        handleConnect(pixelsMap, boundary,x-1,y-1,x+1,y-1,x-1,y+1,x+1,y+1);
    }

    /**
     * get the potential boundary pixels by their coordinate, and add them to the boundary list if needed (updating boundary)
     *
     * @param  pixelsMap   which represents the image with the hole
     * @param  boundary   list of the boundary pixels
     * @params 4 pixels coordinate (x,y)
     */
    private void handleConnect(Pixel[][] pixelsMap, ArrayList<Pixel> boundary, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        Pixel[] potentialBoundary = new Pixel[]{pixelsMap[x1][y1],
                                                pixelsMap[x2][y2],
                                                pixelsMap[x3][y3],
                                                pixelsMap[x4][y4]};
        for (Pixel pixel : potentialBoundary) {
            if (!isHole(pixel) && !boundary.contains(pixel)) {
                boundary.add(pixel);
            }
        }
    }

}
