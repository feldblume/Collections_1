package main;

import vector.LinkedListVectorFactory;
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
    }
}
