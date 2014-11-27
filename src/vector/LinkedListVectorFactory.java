package vector;

/**
 * Created by Jack on 11/27/14.
 */
public class LinkedListVectorFactory implements VectorFactory {
    @Override
    public Vector createVector() {
        return new LinkedListVector();
    }

    @Override
    public Vector createVector(int i) {
        LinkedListVector llv = new LinkedListVector();
        while(llv.getSize()<i) {
            llv.addEl(0);
        }
        return llv;
    }
}
