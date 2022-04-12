package com.techelevator.models;

//Beverage item class
public class Beverage extends Item {

    public Beverage(String slot, String name, double price, String type)
    {
        super(slot, name, price, type);
    }

    @Override
    public String getSound() {
        return "*Drink Noises*";
    }
}
