package vector;

/**
 * Created by Jack on 11/27/14.
 */
public class JVectorAdapter implements Vector {
    java.util.Vector<Double> jVector;

    public JVectorAdapter(java.util.Vector<Double> jv) {
        this.jVector = jv;
    }

    @Override
    public double getElement(int ind) {
        return jVector.get(ind);
    }

    @Override
    public void setElement(int ind, double val) {
        jVector.set(ind,val);

    }

    @Override
    public int getSize() {
        return jVector.size();
    }

    @Override
    public void fillFromMass(double[] arr) {
        jVector.clear();
        for(double v : arr) {
            jVector.add(v);
        }
    }

    @Override
    public void fillFromVector(Vector v) {
        jVector.clear();
        for(int i = 0; i < v.getSize(); i++) {
            jVector.add(v.getElement(i));
        }
    }

    @Override
    public void mult(double factor) {
        for(int i = 0; i<jVector.size(); i++) {
            jVector.set(i,jVector.get(i) * factor);
        }

    }

    @Override
    public boolean sum(Vector v) {
        if(v.getSize() != jVector.size()) {
            try {
                throw new IncompatibleVectorSizesException();
            } catch (IncompatibleVectorSizesException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i<jVector.size(); i++) {
            jVector.set(i,jVector.get(i) + v.getElement(i));
        }
        return true;
    }

    @Override
    public boolean equal(Vector v) {
        if(v.getSize() != jVector.size()) {
            try {
                throw new IncompatibleVectorSizesException();
            } catch (IncompatibleVectorSizesException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i<jVector.size(); i++) {
            if(jVector.get(i) != v.getElement(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Vector clone() throws CloneNotSupportedException {
        return new JVectorAdapter(this.jVector);
    }
}
