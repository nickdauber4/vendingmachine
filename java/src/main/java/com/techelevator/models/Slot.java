package com.techelevator.models;

//Slot class that is used for putting each item into a slot as it is read into inventory
public class Slot {

    private String slotId;
    private Item item;
    private int quantity;

    public Slot(String slotId, Item item, int quantity){
        this.slotId = slotId;
        this.item = item;
        this.quantity = quantity;
    }

    public String getSlotId(){ return slotId; }
    public Item getItem(){ return item; }
    public int getQuantity(){ return quantity; }

    public void setQuantity(int quantity){ this.quantity = quantity; }


}
