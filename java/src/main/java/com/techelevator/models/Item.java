package com.techelevator.models;

//General item class that each specific item inherits from
public class Item implements Sound {

    private String slot;
    private String name;
    private double price;
    private String type;
    private String sound;


    public Item(String slot, String name, double price, String type){
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
    }


    public String getSlot(){ return slot; }
    public String getName(){ return name; }
    public double getPrice(){ return price; }
    public String getType(){ return type; }
    public String getSound(){ return sound; }
}
