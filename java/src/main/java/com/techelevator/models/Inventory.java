package com.techelevator.models;

import com.techelevator.models.Item;
import com.techelevator.models.Slot;

import java.util.Map;

//Inventory class used to get and print the inventory for option 1 on the main menu
public class Inventory {

    private Map<String, Slot> inventory;

    public Inventory(Map<String, Slot> inventory){
        this.inventory = inventory;
    }

    public Map<String, Slot> getInventory() { return inventory; }

    //Method to display the items. Used for option 1 to display, and option 2 when purchasing
    public void printItems() {
        System.out.println("----------------CURRENT INVENTORY:----------------");
        for (Map.Entry<String, Slot> entry : inventory.entrySet()) { //For-each loop to go through the map that is made at the beginning of the application
            Slot slot = entry.getValue(); //Make the slot so it can find the item
            Item item = slot.getItem(); //Make the item itself for printing
            String itemList; //The string that gets printed out
            if(slot.getQuantity() > 0) { //If the item is in stock
                itemList = item.getSlot() + " | " + item.getName() + " | $" + item.getPrice() +
                        " | " + item.getType() + " | QUANTITY: " + slot.getQuantity();
            }
            else { //If the item isn't in stock anymore
                itemList = item.getSlot() + " | " + item.getName() + " | SOLD OUT";
            }
            System.out.println(itemList);
            System.out.println("--------------------------------------------------");
        }
    }

}
