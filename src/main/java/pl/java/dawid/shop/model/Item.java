package pl.java.dawid.shop.model;

sealed public class Item permits Product {
    public String name;

    public Item(String name) {
        this.name = name;
    }
}

