import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static int[] boxVolumes = {30};
    static int oldErrorAmount = 0;
    static ArrayList<Product> products = new ArrayList<>();
    public static void main(String[] args) {
        Product blauw = new Product(4, Color.BLUE, "Blue");
        Product geel = new Product(3, Color.YELLOW, "Yellow");
        Product groen = new Product(2, Color.GREEN, "Green");
        Product rood = new Product(1, Color.RED, "Red");

        products.add(blauw);
        products.add(geel);
        products.add(groen);
        products.add(rood);
        for (int x = 0; x < 1000; x++) {
            Order order1 = new Order();
            int min = 0;
            int max = 3;
            for (int i = 0; i < 9; i++) {
                order1.addProduct(products.get(ThreadLocalRandom.current().nextInt(min, max + 1)));
            }
            if (oldErrorAmount != Algorithm.amountOfFailures){
                System.out.println((x + 1));
                oldErrorAmount = Algorithm.amountOfFailures;
            }
            System.out.println("___________________________________________________________________________________________________________________________________________________\n");

            System.out.println(order1.getProductvolume() + "\n");

            Algorithm.getboxes(order1, 10);

            System.out.println(order1.getBoxes());

            System.out.println(order1.getProducts());

            System.out.println(order1.getProductsOrganised());

            Algorithm.binPacking(order1);

            System.out.println(order1.getBoxesPacked());

            Algorithm.getboxes(order1, 10, 0.7);

            System.out.println(order1.getBoxes());

            Algorithm.orderProductsFromOrder(order1);

            Algorithm.binPacking(order1);

            System.out.println(order1.getBoxesPacked());

            System.out.println("___________________________________________________________________________________________________________________________________________________\n");
        }
        System.out.println("\n" + Algorithm.amountOfFailures);

    }
}