package makesureaboutarraycopy;

import java.util.Collection;

/**
 * Created by Jack on 11/9/14.
 */
public class ArrayAreCopiedShallowly {
    public static void main(String[] args) {
        C[] array = {new C(1), new C(2), new C(3), new C(4), new C(5)};
        showArray(array);

        // arraycopy CHECK!
        C[] array2 = new C[5];
        System.arraycopy(array,0,array2,0,array.length);
/*        showArray(array2);

        array2[0].f = 10;
        showArray(array2);
        showArray(array);*/

        // Arrays.copyOf
        C[] array3 = {new C(1), new C(2), new C(3), new C(4), new C(5)};
        Collection<C> col3 = java.util.Arrays.asList(array3);
        System.out.println(col3);

        C[] array3p = (C[])col3.toArray();
        showArray(array3p);
        array3p[3].f = 40;
        showArray(array3p);
        System.out.println(col3);


    }

    static void showArray(C[] input) {
        for(C obj:input)
            System.out.print(obj + " ");
        System.out.println();
    }
}

class C{
    C(int i){
        f = i;
    }
    public int f;

    @Override
    public String toString() {
        return Integer.toString(f);
    }
}
