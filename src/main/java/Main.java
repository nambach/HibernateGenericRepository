import model.Product;
import repository.impl.GenericRepositoryImpl;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        testHibernate();
    }

    private static void testSort() {
        int loop = 100;
        SortingAssignment.count = new HashMap<String, Integer>();
        SortingAssignment.count.put("quickSort", 0);
        SortingAssignment.count.put("mergeSort", 0);
        SortingAssignment.count.put("heapSort", 0);

        for (int i = 0; i < loop; i++) {
            SortingAssignment.runTest();
        }
        System.out.println("hea:" + SortingAssignment.heapSort / loop);
        System.out.println("qui:" + SortingAssignment.quickSort / loop);
        System.out.println("meg:" + SortingAssignment.mergeSort / loop);
        System.out.println();
        SortingAssignment.count.forEach((s, integer) -> {
            System.out.println(s + " wins " + integer + " times");
        });
    }

    private static void testHibernate() {
        ProductRepositoryImpl repository = new ProductRepositoryImpl(GenericRepositoryImpl.getFactory());

        Product product = new Product("1", null, null);

        for (Product product1 : repository.searchAll()) {
            System.out.println(product1);
        }
    }
}
