package mastersidi.fste.umi.ac.ma.cuirstyle;
public class Product {
    private int imageResource;
    private String productBrandName;
    private String price;
    private String description;
    private int quantity;

    public Product(int imageResource, String productBrandName, String price, String description) {
        this.imageResource = imageResource;
        this.productBrandName = productBrandName;
        this.price = price;
        this.description = description;
        this.quantity = 0;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
