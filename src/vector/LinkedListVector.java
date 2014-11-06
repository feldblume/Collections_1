package vector;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by nataliya on 16.10.14.
 */

public class LinkedListVector implements Vector, Cloneable, Iterable<VectorElement>, Serializable {
    VectorElement head;
    int size;

    public LinkedListVector() {
        head = new VectorElement(0);
        head.setPrevious(head);
        head.setNext(head);
        size = 0;
    }

    @Override
    public Iterator<VectorElement> iterator() {
        return new LinkedListVectorIterator();
    }

    class LinkedListVectorIterator implements Iterator<VectorElement> {
        VectorElement current;
        LinkedListVectorIterator() {
            current = head;
        }
        @Override
        public boolean hasNext() {
            return current.getNext() != head;
        }

        @Override
        public void remove() {
            //isn't supposed to work))
        }

        @Override
        public VectorElement next() {
            current = current.getNext();
            return current;

        }
    }

    private boolean addElement(VectorElement element) {
        VectorElement prevElement = head.getPrevious();

        prevElement.setNext(element);
        element.setPrevious(prevElement);

        element.setNext(head);
        head.setPrevious(element);

        size++;
        return true;
    }

    public void addEl(double d) {

        this.addElement(new VectorElement(d));

    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        if(this.size == 0)
            return "Empty vector!";
        for(VectorElement ve : this)
            s.append(ve.value.toString()).append("  ");
        s.setCharAt(s.length() - 2,']');
        return "Vector: [" + s;
    }

    public VectorElement getByInd(int ind) {

        try {
            if (ind >= size || ind < 0)
                throw new VectorIndexOutOfBoundsException("Index is incorrect!");
            for (VectorElement ve : this)
                if (ind-- == 0)
                    return ve;
        }
        catch (VectorIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println(e);
            return null;
        }
        return null;
    }

    public boolean removeElement(int ind) {
        try {
            if (ind >= size || ind < 0)
                throw new VectorIndexOutOfBoundsException("Index is incorrect!");

            VectorElement current = this.getByInd(ind);
            System.out.println(current);

            VectorElement prevElement = current.getPrevious();
            VectorElement nextElement = current.getNext();

            prevElement.setNext(nextElement);
            nextElement.setPrevious(prevElement);

            size--;
            return true;
        }
        catch (VectorIndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public double getElement(int ind) {
        try {
            if (ind >= size || ind < 0)
                throw new VectorIndexOutOfBoundsException("Index is incorrect!");

            return this.getByInd(ind).value;
        }
        catch (VectorIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public void setElement(int ind, double val) {
        try {
            if (ind >= size || ind < 0)
                throw new VectorIndexOutOfBoundsException("Index is incorrect!");
            this.getByInd(ind).value = val;
        }

        catch (VectorIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println(e);
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void fillFromMass(double[] arr) {
        LinkedListVector llv = new LinkedListVector();
        for(int i = 0; i < arr.length; i++) {
            VectorElement ve = new VectorElement(arr[i]);
            llv.addElement(ve);
        }
        this.head = llv.head;
        this.size = llv.size;
    }

    @Override
    public void fillFromVector(Vector v) {
        LinkedListVector llv = new LinkedListVector();
        for(int i = 0; i < v.getSize(); i++) {
            VectorElement ve = new VectorElement(v.getElement(i));
            llv.addElement(ve);
        }
        this.head = llv.head;
        this.size = llv.size;
    }

    @Override
    public void mult(double factor) {
        for(VectorElement ve : this)
            ve.value *= factor;
    }

    @Override
    public boolean sum(Vector v) {
        if(this.size != v.getSize()) {
            System.err.println("Incompatible sizes: " + this.size + " and " + v.getSize());
            return false;
        }
        int i = 0;
        for(VectorElement ve : this)
            ve.value = ve.value + v.getElement(i++);
        return true;
    }

    @Override
    public boolean equal(Vector v) {
        if(this.size != v.getSize()) {
            System.out.println("Incompatible sizes: " + this.size + " and " + v.getSize());
            return false;
        }
        int i = 0;
        for(VectorElement ve : this)
            if(ve.value != v.getElement(i++))
                return false;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Vector llv = (Vector)obj;
        if(this.size != llv.getSize()) {
            System.out.println("different lengths: " + this.size + "!= " + llv.getSize());
            return false;
        }
        int i = 0;
        for(VectorElement ve : this)
            if(ve.value != llv.getElement(i++))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        double sum = 0;
        for(VectorElement ve : this)
            sum += ve.value;
        return (int) Math.round(sum / size);
    }

    @Override
    public LinkedListVector clone() throws CloneNotSupportedException {
        LinkedListVector cloned = (LinkedListVector) super.clone();

        VectorElement headElement = new VectorElement(0);
        cloned.head = headElement;
        cloned.head.setPrevious(headElement);
        cloned.head.setNext(headElement);
        cloned.size = 0;

        for(VectorElement ve : this)
            cloned.addElement(new VectorElement(ve.value));
        return cloned;
    }
}