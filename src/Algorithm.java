import java.util.ArrayList;

import static java.lang.Math.floor;

public abstract class Algorithm {
    public static int amountOfFailures = 0;
    private static int oldAmountOfFailures = 0;
    public static int eenproductover = 0;
    public static int confrimed = 0;

    public static int totaalrood = 0;

    public static void getboxes(Order order, int boxVolume) {
        order.deleteCurrentBoxes();
        int remainingProductVolume;
        remainingProductVolume = order.getProductvolume();
        while (remainingProductVolume > 0) {
            remainingProductVolume = remainingProductVolume - boxVolume;
            order.addBox(new Box(1, boxVolume));
        }
    }

    public static void getboxes(Order order, int boxVolume, double fillMin) {
        if (fillMin > 0 && fillMin < 1) {
            order.deleteCurrentBoxes();
            int remainingProductVolume;
            double lastBoxFillPercent;
            int fillMinAmount;

            remainingProductVolume = order.getProductvolume();
            lastBoxFillPercent = (double) order.getProductvolume() / boxVolume;
            while (lastBoxFillPercent > 1) {
                lastBoxFillPercent = lastBoxFillPercent - 1;
            }
            if (lastBoxFillPercent >= fillMin) {
                while (remainingProductVolume > 0) {
                    remainingProductVolume = remainingProductVolume - boxVolume;
                    order.addBox(new Box(1, boxVolume));
                }
            } else {
                fillMinAmount = (int) floor(boxVolume * fillMin);
                while (remainingProductVolume >= fillMinAmount) {
                    order.addBox(new Box(1, boxVolume, fillMinAmount));
                    remainingProductVolume = remainingProductVolume - fillMinAmount;
                }
                double amountToAdd = (double) remainingProductVolume / order.getBoxAmount();
                while (amountToAdd > 1) {
                    for (int i = 0; i < order.getBoxAmount(); i++) {
                        order.addExtraSpace(i);
                    }
                    remainingProductVolume = remainingProductVolume - order.getBoxAmount();
                    amountToAdd--;
                }
                for (int i = 0; i < remainingProductVolume; i++) {
                    order.addExtraSpace(i);
                }
            }
        } else {
            System.out.println("fillMin has an invalid value, expected value between 0 and 1");
        }
    }

    public static void orderProductsFromOrder(Order order) {
        Product[] productsInOrder;
        Product[] productsOrganised;
        ArrayList<Product> productsOrganizing;



        productsInOrder = order.getProductsArray();
        productsOrganizing = new ArrayList<>();

        for (int i = 0; i < productsInOrder.length; i++) {
            int nowSize = 0;
            int position = -1;
            for (int j = 0; j < productsInOrder.length; j++) {
                if (productsInOrder[j] != null) {
                    if (productsInOrder[j].getSize() > nowSize) {
                        nowSize = productsInOrder[j].getSize();
                        position = j;
                    }
                }
            }
            productsOrganizing.add(productsInOrder[position]);
            productsInOrder[position] = null;
        }
        productsOrganised = productsOrganizing.toArray(new Product[0]);
        order.setProductsOrganised(productsOrganised);
    }

    public static void binPacking(Order order) {
        Box[] boxesInOrder;
        Product[] productsInOrder;
        Box[] boxesPacked;
        int remainingSize = 0;
        boolean backupAlgorithmRun = false;
        boolean done = true;
        boolean failure = false;
        boolean failed = false;

        boxesInOrder = order.getBoxesArray();
        productsInOrder = order.getProductsOrganisedArray();
        while(!backupAlgorithmRun){
        for (int i = 0; i < boxesInOrder.length; i++) {
            for (int j = 0; j < productsInOrder.length; j++) {
                if (productsInOrder[j] != null) {
                    boxesInOrder[i].addproduct(productsInOrder[j]);
                    if (boxesInOrder[i].validate()) {
                        productsInOrder[j] = null;
                    } else {
                        boxesInOrder[i].removeProduct();
                    }
                } else {
                    if (done) {

                       System.out.println("done");
                        done = false;
                    }
                }
            }
        }
        for (int i = 0; i < productsInOrder.length; i++) {
            if (productsInOrder[i] != null) {
                remainingSize = remainingSize + productsInOrder[i].getSize();
                failed = true;
                failure = true;
            }
        }
        if (failure) {
            order.addBox(new Box(order.getBox().getType(), order.getBox().getVolume(), order.getBox().getExpectedProductVolume()));
            binPacking_2(order);
            failed = false;
            backupAlgorithmRun = true;
        }
        if (failed){
            amountOfFailures++;
        }

        if (remainingSize == 0) {
            System.out.println("Order Fulfilled\n");
        } else {

            System.out.println("Remaining volume : " + remainingSize + "");

        }
        if (!backupAlgorithmRun){
                boxesPacked = boxesInOrder;
                order.setBoxesPacked(boxesPacked);
                backupAlgorithmRun = true;
            }
    }}
    private static void binPacking_2(Order order) {
        Box[] boxesInOrder;
        Product[] productsInOrder;
        Box[] boxesPacked;
        int remainingSize = 0;
        boolean done = true;


        boxesInOrder = order.getBoxesArray();
        productsInOrder = order.getProductsOrganisedArray();

        for (int i = 0; i < boxesInOrder.length; i++) {
            for (int j = 0; j < productsInOrder.length; j++) {
                if (productsInOrder[j] != null) {
                    boxesInOrder[i].addproduct(productsInOrder[j]);
                    if (boxesInOrder[i].validate()) {
                        productsInOrder[j] = null;
                    } else {
                        boxesInOrder[i].removeProduct();
                    }
                } else {
                    if (done) {

//                       System.out.println("done");
                        done = false;
                    }
                }
            }
        }
        for (int i = 0; i < productsInOrder.length; i++) {
            if (productsInOrder[i] != null) {
                remainingSize = remainingSize + productsInOrder[i].getSize();
            }
        }

        if (remainingSize == 0) {
            System.out.println("Order Fulfilled\n");
        } else {
            System.out.println("Remaining volume : " + remainingSize + "");


        }
        boxesPacked = boxesInOrder;
        order.setBoxesPacked(boxesPacked);
        System.out.println("backupran");
    }

}
