package com.hoavtm.lab3.models;

public class Fruit {
    private String name;
    private String description;
    private int image;
    private String imageUri;

    public Fruit(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.imageUri = null;
    }

    public Fruit(String name, String description, String imageUri) {
        this.name = name;
        this.description = description;
        this.imageUri = imageUri;
        this.image = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
