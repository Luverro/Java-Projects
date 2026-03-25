class Product {
    private String productID;
    private String productName;
    private double price;
    private long quantitySold;

    public Product(String productID, String productName, double price, long quantitySold) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantitySold = quantitySold;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public long getQuantitySold() {
        return quantitySold;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantitySold(long quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double calculateTotalSales() {
        return price * quantitySold;
    }

    public void displayProductDetails() {
        System.out.println("Product ID: " + productID +
                ", Product Name: " + productName +
                ", Price: " + price +
                ", Quantity Sold: " + quantitySold +
                ", Total Sales: " + calculateTotalSales());
    }
}

public class ProductDisplay {
    public static void main(String[] args) {
        Product product1 = new Product("P0671", "Creatine", 699, 10);
        Product product2 = new Product("P0467", "Protein powder", 799, 12);

        System.out.println("Product Details: ");
        product1.displayProductDetails();
        product2.displayProductDetails();
    }
}
