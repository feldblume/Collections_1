package vector;

/**
 * Created by Jack on 12/2/14.
 */
public class SynchronizedVector implements Vector {
    private Vector sync;

    public SynchronizedVector(Vector vector) {
        sync = vector;
    }

    @Override
    public synchronized double getElement(int ind) {
        return sync.getElement(ind);
    }

    @Override
    public synchronized void setElement(int ind, double val) {
        sync.setElement(ind,val);
    }

    @Override
    public synchronized int getSize() {
        return sync.getSize();
    }

    @Override
    public synchronized void fillFromMass(double[] arr) {
        sync.fillFromMass(arr);
    }

    @Override
    public synchronized void fillFromVector(Vector v) {
        sync.fillFromVector(v);
    }

    @Override
    public synchronized void mult(double factor) {
        sync.mult(factor);
    }

    @Override
    public synchronized boolean sum(Vector v) {
        return sync.sum(v);
    }

    @Override
    public synchronized boolean equal(Vector v) {
        return sync.equal(v);
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("Synchronized vector: ");
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
        if(!(obj instanceof Vector))
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
    public SynchronizedVector clone() throws CloneNotSupportedException {
        SynchronizedVector sv = (SynchronizedVector)super.clone();
        sv.sync = this.sync.clone();
        return sv;
    }
}
