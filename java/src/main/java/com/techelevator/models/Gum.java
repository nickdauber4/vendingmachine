package com.techelevator.models;

//Gum item class
public class Gum extends Item {

    public Gum(String slot, String name, double price, String type)
    {
        super(slot, name, price, type);
    }

    @Override
    public String getSound() {
        return "*Gum Noises*";
    }
}
