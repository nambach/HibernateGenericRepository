import model.Product;
import model.User;
import repository.UserRepository;
import repository.generic.impl.GenericRepositoryImpl;
import repository.impl.UserRepositoryImpl;

import java.util.Arrays;
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

    /**
     * Postgre
     */
    private static void testHibernate() {
        ProductRepositoryImpl repository = new ProductRepositoryImpl(GenericRepositoryImpl.getFactory());

        Product product = new Product("1", null, null);

        for (Product product1 : repository.getByNameAndId(Arrays.asList("Product 012", "Product 001"), Arrays.asList("1", "12"))) {
            System.out.println(product1);
        }
    }

    /**
     * Sql Server
     */
    private static void testHibernate2() {
        UserRepository repository = new UserRepositoryImpl(GenericRepositoryImpl.getFactory());

        for (User user : repository.getByNameAndId(Arrays.asList("Nam Bach", "20"), Arrays.asList("nambm"))) {
            System.out.println(user);
        }
    }
}
