package com.techelevator.models;

//Candy item class
public class Candy extends Item {

    public Candy(String slot, String name, double price, String type)
    {
        super(slot, name, price, type);
    }

    @Override
    public String getSound() {
        return "*Candy Noises*";
    }
}
