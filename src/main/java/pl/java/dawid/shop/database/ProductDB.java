package pl.java.dawid.shop.database;

import pl.java.dawid.shop.model.Product;
import pl.java.dawid.shop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProductDB {
    private ArrayList< Product> products = new ArrayList<Product>();
    private static final ProductDB instance = new ProductDB();

    private ProductDB() {

        this.products.add( new Product("cukier", 400,
                4.00));
        this.products.add( new Product("sól", 100,
                3.00));
        this.products.add(new Product("chleb", 50, 7.50));
        this.products.add( new Product("woda", 200,
                2));
        this.products.add( new Product("Pepsi", 500,
                8));
        this.products.add( new Product("kiełbasa krakowska", 50,
                25));

    }

    public ArrayList <Product >getProducts() {
        return products;
    }

    public boolean addToStock(String name, int number) {
        for (Product product : this.products) {
            if (product.getName().equals(name)) {
                product.setStockCounter(number + product.getStockCounter(), false);
                return true;
            }
        }
        return false;
    }
    public List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }
    public void sellProduct(String names) {
        List<String> tokens = this.getTokens(names);
        tokens.forEach( name ->
           products.stream()
                    .filter(product -> product.getName().equals(name)&& product.getStockCounter() > 0)
                    .forEach(p -> p.setStockCounter(p.getStockCounter() - 1, true)  )
        );
    }

    public void addProduct(Product product) {
        this.products.add(product) ;
    }

    public static ProductDB getInstance() {
        return instance;
    }

    public Product getProduct(String name) {
        java.util.Optional<Product> ret = products.stream()
                .filter(product -> product.getName().equals(name)).findFirst();
        if(ret.isEmpty())
            return null;
        return ret.get();

    }
}

