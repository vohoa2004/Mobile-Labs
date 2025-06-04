package com.hoavtm.lab4.models;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private String description;
    private int image;
    private int price;

    public Food(String name, String description, int image, int price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }
}