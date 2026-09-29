package org.lrserver;

public class product {
    int id;
    String name;
    String description;
    float price;
    int stock;
    String category;
    void product(int id, String name, String description, float price, int stock, String category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
