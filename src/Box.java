import java.util.ArrayList;
public class Box {
    private int type;
    private int volume;
    private int expectedProductVolume;
    private ArrayList<Product> productsInBox;

    public Box(int type, int volume){
        this.type = type;
        this.volume = volume;
        this.expectedProductVolume = volume;
        productsInBox = new ArrayList<>();
    }
    public Box(int type, int volume, int expectedProductVolume){
       this(type, volume);
       this.expectedProductVolume = expectedProductVolume;
    }

    public void addproduct(Product product){
        productsInBox.add(product);
    }
    public void addOneToExpectedVolume(){
        this.expectedProductVolume++;
    }
    public void removeProduct(){
        int maxpos = productsInBox.size() - 1;
        productsInBox.remove(maxpos);
    }
    public void emptyBox(){
        productsInBox = new ArrayList<>();
    }

    public int getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public int getExpectedProductVolume() {
        return expectedProductVolume;
    }

    private int getFilledVolume() {
        int filledVolume = 0;
        for (Product product:productsInBox
             ) {filledVolume = filledVolume + product.getSize();
        }
        return filledVolume;
    }

    public boolean validate(){
        int size_f = 0;
        for (Product product:productsInBox
             ) {int size_p = product.getSize();
            size_f = size_f + size_p;
        }
        if (size_f <= volume && size_f <= expectedProductVolume){
            return true;
        } else
            return false;
    }
    @Override
    public String toString() {
        String returnString = "Box of type " + type + ", (volume: "+ volume + " expected: " + expectedProductVolume +")/" + getFilledVolume() + " Inventory:\n";
                int i = 0;
        for (Product product:productsInBox
             ) {String productString = product.toString();
            returnString = returnString + productString + "\n";
        }
        return returnString;
    }

}
