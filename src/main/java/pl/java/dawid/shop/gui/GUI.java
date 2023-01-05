package pl.java.dawid.shop.gui;

import pl.java.dawid.shop.core.Authenticator;
import pl.java.dawid.shop.database.ProductDB;
import pl.java.dawid.shop.model.*;
import java.util.Scanner;

public class GUI {
    private final Scanner scanner = new Scanner(System.in);
    final Authenticator authenticator = Authenticator.getInstance();
    final ProductDB productDB = ProductDB.getInstance();
    private static final GUI instance = new GUI();

    private GUI() {
    }

    public String showMenu() {
        System.out.println("1. product list");
        System.out.println("2. stockCounter");
        System.out.println("3. buy products");

        if(this.authenticator.getLoggedUser() != null &&
                this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
            System.out.println("4. Add products");
            System.out.println("5. addToStock");
            System.out.println("6. new status admin");
        }

        System.out.println("9. Exit");
        return scanner.nextLine();
    }

    public void productList() {
        productDB.getProducts().stream()
                .forEach(System.out::println);

    }

    public String readName() {
        System.out.println("Name:");
        return this.scanner.nextLine();
    }
    public int readNumber(){
        System.out.println("stockCounter:");
        int n = this.scanner.nextInt();
        this.scanner.nextLine();
        return n;
    }

    public User readLoginAndPassword() {
        User user = new User();
        System.out.println("Login:");
        user.setLogin(this.scanner.nextLine());
        System.out.println("Password:");
        user.setPassword(this.scanner.nextLine());
        return user;
    }


    public Product readNewProduct() {
        System.out.println("Name:");
        String name = this.scanner.nextLine();
        System.out.println("StockCounter:");
        int stockCounter = Integer.parseInt(this.scanner.nextLine());
        System.out.println("Price:");
        double price = Double.parseDouble(this.scanner.nextLine());

                return new Product( name, stockCounter, price);

    }
    public void showProduct(Product product){
        if(product!= null) {
            System.out.println("Name:");
            System.out.println(product.getName());
            System.out.println("StockCounter:");
            System.out.println(product.getStockCounter());
            System.out.println("Price:");
            System.out.println(product.getPrice());
        }
    }

    public static GUI getInstance() {
        return instance;
    }
}
