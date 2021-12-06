package holeFiller.passiveObjects;

/**
 * Pixel holds pixel's coordinate and color-value in range [0,1]
 */
public class Pixel {
    private final int x;
    private final int y;
    private float value;

    public Pixel(int x, int y, float value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
