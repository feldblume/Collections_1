package vector;

import java.io.Serializable;

/**
 * Created by nataliya on 16.10.14.
 */
public class VectorElement implements Serializable {
    public VectorElement(double value) {
        this.value = value;
    }
    public String toString() {
        String s = "Element:  value: " + this.value;
        return s;
    }


    Double value;
    private VectorElement previous;
    private VectorElement next;

    public VectorElement getPrevious() {
        return previous;
    }
    public void setPrevious(VectorElement previous) {
        this.previous = previous;
    }
    public VectorElement getNext() {
        return next;
    }
    public void setNext(VectorElement next) {
        this.next = next;
    }


}