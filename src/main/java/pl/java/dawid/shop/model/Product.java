package pl.java.dawid.shop.model;

public class Product {
    private String name;
    private int stockCounter ;
    private double price;


    public Product() {
    }

    public Product(String name,  int stockCounter,
                   double price) {

        this.name = name;
        this.stockCounter = stockCounter;
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockCounter(){
        return this.stockCounter;
    }

    public void setStockCounter(int stockCounter) {
        this.stockCounter = stockCounter;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("  ")
                .append(this.getName())
                .append(" ilosc sztuk: ")
                .append(this.getStockCounter())
                .append(" cena: ")
                .append(this.getPrice())
                .toString();
    }
}
