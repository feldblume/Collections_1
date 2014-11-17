package vector.vectorContainer;

import vector.ArrayVector;
import vector.LinkedListVector;
import vector.Vector;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Jack on 12.11.2014.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Main!");

        VectorMap<String, Vector> vm = new VectorMap<String, Vector>();
        LinkedListVector llv,llv2,llv3,llv4;
        llv = new LinkedListVector();
        llv.fillFromMass(new double[]{2,3,4,5});
        llv2 = new LinkedListVector();
        llv2.fillFromMass(new double[]{1,3,4,5});
        llv3 = new LinkedListVector();
        llv3.fillFromMass(new double[]{3,3,3});


        vm.put("one", llv3);
        vm.put("two", llv2);
        vm.put("three", llv);
        vm.put("four", llv2);
        vm.put("five", llv);
        System.out.println(vm);

        System.out.println(vm.get("one"));

        VectorMap<String, Vector> vm2 = new VectorMap<String, Vector>(vm);
        System.out.println(vm2);

        System.out.println(vm.size());

        vm2.clear();
        System.out.println(vm2.isEmpty());

        System.out.println(vm.containsKey("three"));

        System.out.println(vm.containsValue(llv));

        llv4 = new LinkedListVector();
        llv4.fillFromMass(new double[]{5,5,5,5});
        vm.put("six",llv4);
        System.out.println(vm);

        vm.remove("six");
        System.out.println(vm);

        VectorMap<String, Vector> vm3 = new VectorMap<String, Vector>();
        vm3.put("Seven",llv4);
        vm3.put("Eight",llv4);

        vm.putAll(vm3);
        System.out.println(vm);

        Set<String> s = vm.keySet();
        System.out.println(s);

        Collection c = vm.values();
        System.out.println(c);

        Set es = vm.entrySet();
        System.out.println(es);



    }
}
