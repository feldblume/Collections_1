package vector.vectorContainer;

import vector.ArrayVector;
import vector.LinkedListVector;
import vector.Vector;

/**
 * Created by Jack on 12.11.2014.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Main!");

        VectorMap<String, Vector> vm = new VectorMap<String, Vector>();
        double[] arr = {1,2,3,4,5};
        LinkedListVector llv = new LinkedListVector();
        llv.fillFromMass(arr);
        double[] arr2 = {1,2,3,4,7};
        LinkedListVector llv2 = new LinkedListVector();
        llv2.fillFromMass(arr2);


        vm.put("one", llv);
        vm.put("two", llv2);
        vm.put("three", llv);
        vm.put("four", llv);
        vm.put("five", llv);
        Vector v = vm.get("one");
        System.out.println(v);

        System.out.println(vm);
    }
}
