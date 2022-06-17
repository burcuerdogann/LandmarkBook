package com.burcuerdogan.landmarkbook;

public class Singleton {

    private Landmark sentLandmark;
    private static Singleton singleton;

    private Singleton() {
    }

    public Landmark getSentLandmark(){
        return sentLandmark;
    }
    public void setSentLandmark(Landmark sentLandmark){
        this.sentLandmark = sentLandmark;
    }

    //Get instance methodu: Nereden açğırırsak çağıralım hep aynı objeyi döndürür.

    public static Singleton getInstance(){
        if (singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }






}

