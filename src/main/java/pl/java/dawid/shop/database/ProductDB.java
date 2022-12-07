package pl.java.dawid.shop.database;

import pl.java.dawid.shop.model.Product;

public class ProductDB {
    private Product[] products = new Product[6];
    private static final ProductDB instance = new ProductDB();

    private ProductDB() {

        this.products[0] = new Product("cukier", 400,
                4.00);
        this.products[1] = new Product("sól", 100,
                3.00);
        this.products[2] = new Product("chleb", 50, 7.50);
        this.products[3] = new Product("woda", 200,
                2);
        this.products[4] = new Product("Pepsi", 500,
                8);
        this.products[5] = new Product("kiełbasa krakowska", 50,
                25);

    }

    public Product[] getProducts() {
        return products;
    }

    public boolean addToStock(String name, int number) {
        for (Product product : this.products) {
            if (product.getName().equals(name)) {
                product.setStockCounter(number + product.getStockCounter());
                return true;
            }
        }
        return false;
    }

    public boolean sellProduct(String name) {
        for (Product product : this.products) {
            if (product.getName().equals(name) && product.getStockCounter() > 0) {
                product.setStockCounter(product.getStockCounter() - 1);
                return true;
            }
        }
        return false;
    }

    public void addProduct(Product product) {
        Product[] newProducts = new Product[this.products.length + 1];
        for (int i = 0; i < this.products.length; i++) {
            newProducts[i] = this.products[i];
        }
        newProducts[newProducts.length - 1] = product;
        this.products = newProducts;
    }

    public static ProductDB getInstance() {
        return instance;
    }

    public Product getProduct(String name) {
        for (int i = 0; i < this.products.length; i++) {
            if (name.equals(this.products[i].getName()) ) {
                return this.products[i];
            }
        }
        return null;
    }
}

