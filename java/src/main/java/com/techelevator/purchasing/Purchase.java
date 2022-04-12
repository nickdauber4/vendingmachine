package com.techelevator.purchasing;

import com.techelevator.models.Inventory;
import com.techelevator.models.Slot;

import java.util.Scanner;

//This class's purpose is to handle all methods involving money such as inserting,
//selecting an item, or giving change
public class Purchase {

    //Constants for the doPurchase method
    private static final String ABORT = "E";
    private static final Double DEFAULT_MONEY = 0.00;
    //Constants for giving change
    private static final int CONVERT_TO_COINS = 100;
    private static final int QUARTER = 25;
    private static final int DIME = 10;
    private static final int NICKEL = 5;
    private static final int PENNY = 1;

    private Double currentMoney = DEFAULT_MONEY;

    public Double getCurrentMoney(){ return currentMoney; }
    public void setCurrentMoney(Double currentMoney){ this.currentMoney = currentMoney; }

    //Insert money into the vending machine (Option 1 of purchase menu)
    public Double insertMoney() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Insert money. Use only two decimals please for change");
        String insertedMoneyString = userInput.nextLine(); //Inserts money and puts it into a string
        Double insertedMoney = Double.parseDouble(insertedMoneyString); //Converts string to double to add to current money
        currentMoney += insertedMoney;
        return currentMoney; //Returns the money you fed in for purchasing
    }

    //Does the purchase and updates balance and quantity
    public void doPurchase(Inventory inventory){
        //Here the user inputs the item they would like via slot
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the slot Id of the item you would like (Enter E to go back): ");
        String selection = scanner.nextLine();
        //Loop to check if the slot Id exists
        for(int i = 0; i >= 0; i++){
            if (inventory.getInventory().containsKey(selection)){ //If the slot Id exists
                Slot slot = inventory.getInventory().get(selection); //Gets the actual item
                int quantity = slot.getQuantity(); //Gets the item quantity
                //Check for quantity
                if(quantity > 0){ //Check the item is in stock
                    if(slot.getItem().getPrice() <= currentMoney) {
                        currentMoney -= slot.getItem().getPrice(); //Update the currentMoney
                        quantity --;
                        slot.setQuantity(quantity); //Update the quantity by reducing and setting
                        //Print out a successful transaction
                        System.out.println("Item dispensed!");
                        System.out.println(slot.getItem().getName() + " " + "Cost: " +
                                slot.getItem().getPrice() + "\r");
                        System.out.format("Money Left: $%.2f\n", currentMoney);
                        System.out.println(slot.getItem().getSound());
                        //Have to put in code here eventually for the Audit Log
                        break;
                    }
                    else{
                        System.out.println("Insufficient funds!\n");
                        System.out.println("Enter the slot Id of the item you would like (Enter E to go back): ");
                        selection = scanner.nextLine();
                    }
                }
                else{ //Tell the user item is sold out
                    System.out.println("Item is sold out!\n");
                    System.out.println("Enter the slot Id of the item you would like (Enter E to go back): ");
                    selection = scanner.nextLine();
                }
            }
            else if(selection.equals(ABORT)){ //If you want to go back
                break;
            }
            else{ //If you enter something that is invalid
                System.out.println("Invalid input!\n");
                System.out.println("Enter the slot Id of the item you would like (Enter E to go back): ");
                selection = scanner.nextLine();
            }
        }
    }

    //Method that gives the change based on the current money of the user using the least
    //amount of coins possible
    public Double giveChange(Double currentMoney){
        //Give back the change in the least possible amount of coins
        int change = (int)(Math.ceil(currentMoney * CONVERT_TO_COINS)); //Converts the money into coin notation
        int quarters = Math.round(change / QUARTER); //Divides the change by the quarters and rounds so the coins are whole
        change = change % QUARTER;
        int dimes = Math.round(change / DIME); //And so on
        change = change % DIME;
        int nickels = Math.round(change / NICKEL);
        change = change % NICKEL;
        int pennies = Math.round(change / PENNY);
        //Print out change
        System.out.println("Your change:");
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);
        //Update the current money to $0
        currentMoney = DEFAULT_MONEY;
        setCurrentMoney(currentMoney);

        return currentMoney;
    }
}
