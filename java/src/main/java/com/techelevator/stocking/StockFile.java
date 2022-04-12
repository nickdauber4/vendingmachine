package com.techelevator.stocking;


import com.techelevator.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//This class reads in the vendingmachine.csv file, passes each item into an array,
//adds each array to a map (<String slotId, Slot slot>), then passes it
//to the inventory class to be displayed. This happens before the main menu is loaded.
public class StockFile {

    //Default quantity every item is set to when the application runs
    private static final int DEFAULT_QUANTITY = 5;
    private static final int ARRAY_SLOT_ID = 0;
    private static final int ARRAY_NAME = 1;
    private static final int ARRAY_PRICE = 2;
    private static final int ARRAY_TYPE = 3;

    public Map<String, Slot> inventoryMap = new HashMap<>();
    File inventoryFile = new File("src/vendingmachine.csv");

    public StockFile(Map<String, Slot> inventoryMap) {
        this.inventoryMap = inventoryMap;
    }
    public StockFile(){}

    public Map<String, Slot> getInventoryMap(){ return inventoryMap; }


    //Method that reads in the file and instantiates the items
    public Map<String, Slot> readFile() {

        try(Scanner dataInput = new Scanner(inventoryFile)) {
            while (dataInput.hasNextLine()){
                //Parsing the file, splitting it by the | symbol and assigning
                //accordingly
                String[] dataArray = dataInput.nextLine().split("\\|");
                String slotId = dataArray[ARRAY_SLOT_ID];
                String name = dataArray[ARRAY_NAME];
                Double price = Double.parseDouble(dataArray[ARRAY_PRICE]);
                String type = dataArray[ARRAY_TYPE];
                //Now we take each property and put it into an object based on item type
                Item item; //Instantiate the item class, so we can make our item objects
                //If-else for judging what type of item it is then inserting
                if(type.equalsIgnoreCase("Drink")) {
                    item = new Beverage(slotId, name, price, type);
                } else if(type.equalsIgnoreCase("Candy")) {
                    item = new Candy(slotId, name, price, type);
                } else if(type.equalsIgnoreCase("Chip")) {
                    item = new Chip(slotId, name, price, type);
                } else {
                    item = new Gum(slotId, name, price, type);
                }
                //Now that the item is made, put it into a slot with default quantity
                Slot slot = new Slot(slotId, item, DEFAULT_QUANTITY);
                //Finally, add it to the map of the inventory along with the slotId for lookup
                inventoryMap.put(slotId, slot);
            }
        } catch(FileNotFoundException ex) {
            System.out.println("Inventory File not Found!");
        }

        return inventoryMap;
    }

}
