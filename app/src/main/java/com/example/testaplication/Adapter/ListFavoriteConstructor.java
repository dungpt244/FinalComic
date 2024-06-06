package com.example.testaplication.Adapter;

public class ListFavoriteConstructor {
    private String image;
    private String name;
    private String description;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public ListFavoriteConstructor(String image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ListFavorite{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
