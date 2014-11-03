package vector;

/**
 * Created by nataliya on 27.10.14.
 */
public class VectorContainer <V extends Vector> {

    private static final int N = 1;

    private V[] vectors;
    private int size;

    public VectorContainer() {
        vectors = (V[]) new Vector[N];
        size = 0;
    }

    public VectorContainer(V[] v) {

        size = v.length;

        vectors = (V[]) new Vector[v.length];
        for (int i = 0; i < v.length; i++) {
            vectors[i] = v[i];
        }
    }

    public void add(int i, V vector) {
        if (i < 0 || i > size)
            throw new VectorIndexOutOfBoundsException();

        V[] tmpVectors;

        if (vectors.length == size) {
            tmpVectors = (V[]) new Vector[size+1];

        } else {
            tmpVectors = (V[]) new Vector[vectors.length];
        }

        System.arraycopy(vectors, 0, tmpVectors, 0, i);
        tmpVectors[i] = vector;
        System.arraycopy(vectors, i, tmpVectors, i + 1, size - i);
        vectors = tmpVectors;

        size++;;
    }

    public void add(VectorContainer<V> vecCont) {
        V[] tmpVectors;
        tmpVectors = (V[]) new Vector[size+vecCont.size];
        int j = 0;
        System.arraycopy(vectors, 0, tmpVectors, 0, size);
        for (int i = size; i < tmpVectors.length; i++) {
            tmpVectors[i] = vecCont.getVector(j);
            j++;
        }
        vectors = tmpVectors;
        size = size + vecCont.getSize();
    }

    public V getVector(int i) {
        if (i < 0 || size <= i)
            throw new VectorIndexOutOfBoundsException();
        return vectors[i];
    }

    public void setVector(int i, V vector) {
        if (i < 0 || size <= i)
            throw new VectorIndexOutOfBoundsException();
        vectors[i] = vector;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int find(V vect) {
        for (int i = 0; i < getSize(); i++) {
            if (vectors[i].equal(vect))
                    return i;
        }
        return -1;
    }

    public void remove(int i) {
        if (i < 0 || size <= i)
            throw new VectorIndexOutOfBoundsException();
        V[] tmpVecs;
        tmpVecs = (V[]) new Vector[size-1];
        System.arraycopy(vectors, 0, tmpVecs, 0, i);
        System.arraycopy(vectors, i+1, tmpVecs, i, size - i - 1);
        vectors = tmpVecs;
        size--;
    }

    public void clear() {
        vectors = (V[]) new Vector[N];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for(int i = 0; i < this.size; i++) {
            s.append(vectors[i]);
            s.append(" ");
        }
        return s.toString();
    }
}
