package vector;

import java.io.*;

/**
 * Created by nataliya on 04.10.14.
 */
public class Vectors {

    static VectorFactory vectorFactory = new ArrayVectorFactory();

    public static void setVectorFactory(VectorFactory vf) {
        vectorFactory = vf;
    }

    public static Vector createInstance(int size) {
        return vectorFactory.createVector(size);
    }

    public static Vector createInstance() {
        return vectorFactory.createVector();
    }


    public static Vector sortV(Vector initial) {

        for (int i = 0; i < initial.getSize() - 1; i++) {
            for (int j = 0; j < initial.getSize() - i - 1; j++){
                if (initial.getElement(j) > initial.getElement(j + 1)) {
                    double l = initial.getElement(j + 1);
                    initial.setElement(j + 1, initial.getElement(j));
                    initial.setElement(j, l);
                }

            }
        }

        return initial;
    }

    public static void outputVector(Vector v, OutputStream out) throws IOException {
        DataOutputStream dOut = new DataOutputStream(out);
        //System.out.println(v.getSize());
        dOut.writeInt(v.getSize());

        for (int i = 0; i < v.getSize(); i++) {
            dOut.writeDouble(v.getElement(i));
        }
        dOut.flush();
    }

    public static Vector inputVector(InputStream in) throws IOException {
        DataInputStream dIn = new DataInputStream(in);
        System.out.println("Total file size to read (in bytes): "
                + dIn.available());
        int size = dIn.readInt();

        Vector v = createInstance(size);

        for (int i = 0; i < size && dIn.available() != 0; i++) {
            v.setElement(i, dIn.readDouble());
        }

    return v;

    }

    public static void writeVector(Vector v, Writer out) throws IOException {
        BufferedWriter bufWr = new BufferedWriter(out);
        bufWr.write(v.getSize() + " ");
        for (int i = 0; i < v.getSize(); i++) {
//            if (i == v.getSize() - 1)
//                bufWr.write(v.getElement(i) + " \n");
//            else
                bufWr.write(v.getElement(i) + " ");


        }
        bufWr.flush();
    }

    public static Vector readVector(Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        st.nextToken();
        int size = (int) st.nval;
        //System.out.println("\nTokens output: " + size + "\n");

        Vector v = createInstance(size);
        for (int i = 0; i < size && st.nextToken() != StreamTokenizer.TT_EOF; i++) {
            v.setElement(i, st.nval);
        }

        return v;
    }












//    public static Vector readVector(Reader in) throws IOException {
//        StreamTokenizer tokenizer = new StreamTokenizer(in);
//
//        tokenizer.nextToken();
//        int size = (int) tokenizer.nval;
//
//        ArrayVector v = new ArrayVector(size);
//
//        for (int i = 0; i < size && tokenizer.nextToken() != StreamTokenizer.TT_EOF; i++) {
//            v.setElement(i, tokenizer.nval);
//        }
//
//        return v;
//    }
}

