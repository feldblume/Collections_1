package vector;

/**
 * Created by Jack on 11/27/14.
 */
public class ProtectedVector implements Vector {

    Vector innerVector;
    public ProtectedVector(Vector v) {
        innerVector = v;
    }

    @Override
    public double getElement(int ind) {
        return innerVector.getElement(ind);
    }

    @Override
    public void setElement(int ind, double val) {
        System.out.println("Ooops!");
    }

    @Override
    public int getSize() {
        return innerVector.getSize();
    }

    @Override
    public void fillFromMass(double[] arr) {
        System.out.println("Ooops again!");
    }

    @Override
    public void fillFromVector(Vector v) {
        // same
    }

    @Override
    public void mult(double factor) {
        // uh-uh
    }

    @Override
    public boolean sum(Vector v) {
        return false;
    }

    @Override
    public boolean equal(Vector v) {
        return innerVector.equal(v);
    }

    @Override
    public Vector clone() throws CloneNotSupportedException {
        return new ProtectedVector(innerVector);
    }

    @Override
    public String toString() {
        return "A proxy of" + innerVector.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return innerVector.equal((Vector)obj);
    }

    @Override
    public int hashCode() {
        return innerVector.hashCode(); // Doubtedly... needs explanations
    }
}
