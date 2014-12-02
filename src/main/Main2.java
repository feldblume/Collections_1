package main;

import vector.LinkedListVectorFactory;
import vector.SynchronizedVector;
import vector.Vector;
import vector.Vectors;

/**
 * Created by Jack on 11/27/14.
 */
public class Main2 {
    public static void main(String[] args) {
        Vector v = Vectors.createInstance();
        System.out.println(v.getSize());
        System.out.println(v.getClass());

        Vectors.setVectorFactory(new LinkedListVectorFactory());
        v = Vectors.createInstance();
        System.out.println(v.getClass());

        // Adapter
        java.util.Vector v2 = new java.util.Vector();
        v2.add(new Double(3));
        v2.add(new Double(4));
        System.out.println(v2);
        Vector adaptedVector = Vectors.getAdaptedJVector(v2);
        System.out.println(adaptedVector);
        for(int i = 0; i<adaptedVector.getSize(); i++) {
            System.out.print(adaptedVector.getElement(i) + ", ");
        }
        System.out.println();
        adaptedVector.setElement(0,8);
        System.out.println(v2);

        // Synchronized
        SynchronizedVector sv = new SynchronizedVector(adaptedVector);
        System.out.println(sv.getSize());
    }
}
