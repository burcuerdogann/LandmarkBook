package com.burcuerdogan.landmarkbook;

public class Landmark {

    String name;
    String country;
    String content;
    int image;

    //Constructor (Bir sınıftan obje oluştururken ilk çağrılan method)


    public Landmark(String name, String country, String content, int image) {
        this.name = name;
        this.country = country;
        this.content = content;
        this.image = image;
    }
}

