package com.techelevator.models;

//Chip item class
public class Chip extends Item {

    public Chip(String slot, String name, double price, String type)
    {
        super(slot, name, price, type);
    }

    @Override
    public String getSound() {
        return "*Chip Noises*";
    }
}
