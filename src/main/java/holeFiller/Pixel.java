package holeFiller;

// Pixel holds pixel coordinate and value in range [0,1]
public class Pixel {
    private final int x;
    private final int y;
    private float value;

    protected Pixel(int x, int y, float value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    float getValue() {
        return value;
    }

    void setValue(float value) {
        this.value = value;
    }
}
