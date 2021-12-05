package holeFiller;

import java.util.ArrayList;

public class Algo_Formula {

    // K is our constant of accuracy (Question 2)
    private static final float K = 400;

    // calculate color according to the formula definition
    protected static float calcColor(Pixel u, ArrayList<Pixel> B, int z, float epsilon) {
        float boundaryWeightsColorsSum = 0;
        float boundaryWeightsSum = 0;
        for (Pixel v : B) {
            float weight_uv = getWeight(u, v, z, epsilon);
            boundaryWeightsColorsSum += weight_uv * v.getValue();
            boundaryWeightsSum += weight_uv;
        }
        return boundaryWeightsColorsSum / boundaryWeightsSum;
    }

    // same formula, but ignore all the v∈B in which ‖u-v‖ ≥ k (Question 2)
    protected static float calcColorConstComplex(Pixel u, ArrayList<Pixel> B, int z, float epsilon) {
        float boundaryWeightsColorsSum = 0;
        float boundaryWeightsSum = 0;
        for (int i=0; i<B.size() && getEuclideanDistance(u,B.get(i)) < K; i++) {
            float weight_uv = getWeight(u, B.get(i), z, epsilon);
            boundaryWeightsColorsSum += weight_uv * B.get(i).getValue();
            boundaryWeightsSum += weight_uv;
        }
        return boundaryWeightsColorsSum / boundaryWeightsSum;
    }

    private static float getWeight(Pixel u, Pixel v, int z, float epsilon) {
        return (float) (1 / (Math.pow(getEuclideanDistance(u, v), z) + epsilon));
    }

    private static float getEuclideanDistance(Pixel u, Pixel v) {
        float xVal = Math.abs(u.getX() - v.getX());
        float yVal = Math.abs(u.getY() - v.getY());
        return (float) Math.sqrt(xVal*xVal + yVal*yVal);
    }

}
