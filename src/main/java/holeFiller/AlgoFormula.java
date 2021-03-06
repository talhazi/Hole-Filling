package holeFiller;

import holeFiller.passiveObjects.Pixel;
import java.util.ArrayList;

public class AlgoFormula {

    public AlgoFormula() {
    }

    /**
     * calculate hole pixel color according to the formula definition
     *
     * @param  u   which represents the hole pixel which we want to calculate his new color
     * @param  B   list of the boundary pixels
     * @param  z   for the formula
     * @param  epsilon   to avoid division by zero
     * @return  newColor   the new hole color
     */
     float calcColor(Pixel u, ArrayList<Pixel> B, int z, float epsilon) {
        float boundaryWeightsColorsSum = 0;
        float boundaryWeightsSum = 0;
        for (Pixel v : B) {
            float weight_uv = getWeight(u, v, z, epsilon);
            boundaryWeightsColorsSum += weight_uv * v.getValue();
            boundaryWeightsSum += weight_uv;
        }
        return boundaryWeightsColorsSum / boundaryWeightsSum;
    }

    // same formula, but ignore all the v∈B in which ‖u-v‖ ≥ k (Question 2) - Const Complexity
     float calcColorConstComplex(Pixel u, ArrayList<Pixel> B, int z, float epsilon) {
        float boundaryWeightsColorsSum = 0;
        float boundaryWeightsSum = 0;
         float k = 400;  // K is our constant of accuracy
         for (int i = 0; i<B.size() && getEuclideanDistance(u,B.get(i)) < k; i++) {
            float weight_uv = getWeight(u, B.get(i), z, epsilon);
            boundaryWeightsColorsSum += weight_uv * B.get(i).getValue();
            boundaryWeightsSum += weight_uv;
        }
        return boundaryWeightsColorsSum / boundaryWeightsSum;
    }

    // this weight function can be changed if necessary
    private float getWeight(Pixel u, Pixel v, int z, float epsilon) {
        return (float) (1 / (Math.pow(getEuclideanDistance(u, v), z) + epsilon));
    }

    private float getEuclideanDistance(Pixel u, Pixel v) {
        float xVal = Math.abs(u.getX() - v.getX());
        float yVal = Math.abs(u.getY() - v.getY());
        return (float) Math.sqrt(xVal*xVal + yVal*yVal);
    }

}
