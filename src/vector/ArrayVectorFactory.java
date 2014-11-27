package vector;

/**
 * Created by Jack on 11/27/14.
 */
public class ArrayVectorFactory implements VectorFactory {
    @Override
    public Vector createVector() {
        return new ArrayVector(0);
    }

    @Override
    public Vector createVector(int i) {
        return new ArrayVector(i);
    }
}
