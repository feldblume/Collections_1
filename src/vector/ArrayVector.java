package vector;
import java.io.Serializable;

/**
 * Created by nataliya on 04.10.14.
 */
public class ArrayVector implements Vector, Cloneable, Serializable {
    private double[] elements;

    public ArrayVector(int length) {

        elements = new double[length];
    }

    @Override
    public double getElement(int ind) {
        try {
            if (ind < 0 || ind > this.getSize() - 1)
                throw new VectorIndexOutOfBoundsException("Index is incorrect");
            return elements[ind];
        }
        catch(VectorIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public void setElement(int ind, double val) {
        try {
            if (ind < 0 || ind > this.getSize()-1)
                throw new VectorIndexOutOfBoundsException("Index is incorrect");
            elements[ind] = val;
        }
        catch(VectorIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println(e);
        }
    }

    @Override
    public void fillFromMass(double[] arr) {
        for(int i = 0; i < this.getSize(); i++)
            this.setElement(i, arr[i]);
    }

    @Override
    public void fillFromVector(Vector v) {
        for(int i = 0; i < this.getSize(); i++)
            this.setElement(i, v.getElement(i));
    }

    @Override
    public boolean equal(Vector v) {
        double abs1 = 0, abs2 = 0;
        if (this.getSize() != v.getSize()) {
            System.out.println("Vectors have different lengths");
            return false;
        }
        else {
            for(int i = 0; i < this.getSize(); i++) {
                abs1 += this.getElement(i) * this.getElement(i);
                abs2 += v.getElement(i) * v.getElement(i);
            }
            abs1 = Math.sqrt(abs1);
            abs2 = Math.sqrt(abs2);
            if (abs1 != abs2) {
                System.out.println("Vectors have different absolute values");
                return false;
            }
            else {
                System.out.println("Vectors are equal!");
                return true;
            }

        }
    }

    @Override
    public int getSize() {

        return elements.length;
    }

    @Override
    public void mult(double factor) {
        for(int i = 0; i < this.getSize(); i++) {
            elements[i] = elements[i] * factor;
        }
    }

    @Override
    public boolean sum(Vector v) {

        try {
            if (this.getSize() != v.getSize())
                throw new IncompatibleVectorSizesException("Dimensions must agree");
            for (int i = 0; i < this.getSize(); i++) {
                this.setElement(i, this.getElement(i) + v.getElement(i));
            }
            return true;
        }
        catch(IncompatibleVectorSizesException e) {
            System.out.println(e);
            return false;
        }
    }

    public double maxEl() {
        double max;
        max = this.getElement(0);
        for(int i = 1; i < this.getSize(); i++) {
            if (this.getElement(i) > max)
                max = this.getElement(i);
        }

        return max;
    }

    public double minEl() {
        double min;
        min = this.getElement(0);
        for(int i = 1; i < this.getSize(); i++) {
            if (this.getElement(i) < min)
                min = this.getElement(i);
        }

        return min;
    }

    @Override
    public String toString() {

        StringBuffer s = new StringBuffer();

        for(int i = 0; i < this.getSize(); i++) {
            s.append(this.getElement(i));
            s.append(" ");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        Vector that = (Vector) obj;
        if (this.getSize() != that.getSize())
            return false;
        for(int i = 0; i < this.getSize(); i++) {
            if (this.getElement(i) != that.getElement(i))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        double sum = 0;
        for (int i = 0; i < this.getSize(); i++)
            sum += this.getElement(i);
        return (int) Math.round(sum / getSize());
    }

    @Override
    public ArrayVector clone() throws CloneNotSupportedException {
        ArrayVector cloned = (ArrayVector) super.clone();
        for(int i = 0; i < this.getSize(); i++)
            cloned.setElement(i, getElement(i));
        return cloned;

    }

}
