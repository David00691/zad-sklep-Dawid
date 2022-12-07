package pl.java.dawid.shop.core;

import pl.java.dawid.shop.database.ProductDB;
import pl.java.dawid.shop.gui.GUI;
import pl.java.dawid.shop.model.User;

public class Core {
    final ProductDB productDB= ProductDB.getInstance();
    final Authenticator authenticator = Authenticator.getInstance();
    final GUI gui = GUI.getInstance();
    private static final Core instance = new Core();

    private Core() {

    }
    public void start() {
        boolean isRunning = false;
        int counter = 0;

        while(!isRunning && counter < 3) {
            this.authenticator.authenticate(this.gui.readLoginAndPassword());
            isRunning = this.authenticator.getLoggedUser() != null;
            if(!isRunning) {
                System.out.println("Not authorized !!");
            }
            counter++;
        }

        while(isRunning) {
            String choosed = this.gui.showMenu();
            System.out.println("Wybrales: "+choosed);
            switch(choosed) {
                case "1":
                    this.gui.productList();
                    break;
                case "2":
                    this.gui.showProduct(this.productDB.getProduct(this.gui.readName()));
                    break;
                case "3":
                    this.buyProduct();
                    break;

                case "4":
                    if(this.authenticator.getLoggedUser() != null &&
                            this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
                        this.productDB.addProduct(this.gui.readNewProduct());
                    }
                    break;
                case "5":
                    if(this.authenticator.getLoggedUser() != null &&
                            this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
                        this.productDB.addToStock(this.gui.readName(),this.gui.readNumber());
                    }
                    break;
                case "6":
                    if(this.authenticator.getLoggedUser() != null &&
                            this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
                      User user= this.authenticator.userDB.findByLogin(gui.readName());
                      user.setRole(User.Role.ADMIN);
                      user.showUser();
                    }
                    break;

                case "9":
                     isRunning = false;
                     break;
                default:
                    System.out.println("Wrong choose !!");
                    break;
            }
        }
    }
    public void buyProduct() {
        String name = this.gui.readName();
            if (this.productDB.sellProduct(name) == true) {
                System.out.println("Operation successfully");
            }
            else
                System.out.println(("operation failed"));
        System.out.println("StockCounter is:");
               this.gui.showProduct(productDB.getProduct(name));
    }



    public static Core getInstance() {
        return instance;
    }
}
