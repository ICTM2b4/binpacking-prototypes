import java.awt.*;
import java.awt.color.*;
public class Product {
    private int size;
    private Color color;
    private String color_s;

    public Product(int size, Color color, String color_s){
        this.color = color;
        this.size = size;
        this.color_s = color_s;
    }

    public int getSize() {
        return size;
    }

    public String getColor_s() {
        return color_s;
    }

    @Override
    public String toString() {
        return color_s + " " + size;
    }
}
