package vector;

/**
 * Created by nataliya on 09.10.14.
 */
public interface Vector {
    double getElement(int ind);
    void setElement(int ind, double val);
    int getSize();
    void fillFromMass(double[] arr);
    void fillFromVector(Vector v);
    void mult(double factor);
    boolean sum(Vector v);
    boolean equal(Vector v);
    Vector clone() throws CloneNotSupportedException;
}
