import java.util.ArrayList;
public class Order {
    private ArrayList<Product> productsInOrder;
    private ArrayList<Box> boxesInOrder;
    private int productvolume;
    private Product[] productsOrganised;
    private Box[] boxesPacked;
    public Order(){
        productsInOrder = new ArrayList<>();
        boxesInOrder = new ArrayList<>();
        productvolume = 0;
    }

    public void addProduct(Product product){
            productsInOrder.add(product);
            productvolume = productvolume + product.getSize();
            Algorithm.orderProductsFromOrder(this);
    }
    public void addBox(Box box){
        boxesInOrder.add(box);
    }
    public void addExtraSpace(int boxNumber){
        boxesInOrder.get(boxNumber).addOneToExpectedVolume();
    }
    public int getProductvolume() {
        return productvolume;
    }
    public Product[] getProductsArray(){
        return  productsInOrder.toArray(new Product[0]);
    }

    public Product[] getProductsOrganisedArray(){
        return productsOrganised;
    }
    public Box[] getBoxesArray(){
        return boxesInOrder.toArray(new Box[0]);
    }
    public int getBoxAmount(){
        return boxesInOrder.size();
    }
    public Box getBox(){
        return this.boxesInOrder.get(0);
    }
    public String getBoxes(){
        String returnString = "";
        for (Box box:boxesInOrder
        ) {String boxString = box.toString();
            returnString = returnString + boxString + "\n";
        }
        return returnString;
    }

    public String getBoxesPacked(){
        String returnString = "";
        for (Box box:boxesPacked
             ) {String boxString = box.toString();
           returnString = returnString + boxString + "\n";
        }
        return returnString;
    }
    public String getProducts(){
        String returnString = "";
        int i = 1;
        for (Product product:productsInOrder
             ) {String productString = product.toString();
            returnString = returnString + i + ": " + productString + "\n";
            i++;
        }
        return returnString;
    }
    public String getProductsOrganised(){
        String returnString = "";
        int i = 1;
        for (Product product:productsOrganised
        ) {String productString = product.toString();
            returnString = returnString + i + ": " + productString + "\n";
            i++;
        }
        return returnString;
    }

    public void setProductsOrganised(Product[] productsOrganised) {
        this.productsOrganised = productsOrganised;
    }

    public void setBoxesPacked(Box[] boxesPacked){
        this.boxesPacked = boxesPacked;
    }

    public void deleteCurrentBoxes(){
        this.boxesInOrder = new ArrayList<>();
    }
}
