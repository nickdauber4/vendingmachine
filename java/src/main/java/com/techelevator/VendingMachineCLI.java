package com.techelevator;

import com.techelevator.purchasing.Purchase;
import com.techelevator.models.Inventory;
import com.techelevator.models.Slot;
import com.techelevator.stocking.StockFile;
import com.techelevator.view.Menu;

import java.util.Map;

public class VendingMachineCLI {
	//Main menu items
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	//Purchase menu items
	private static final String PURCHASE_OPTION_FEED_MONEY = "Insert Money";
	private static final String PURCHASE_OPTION_SELECT_PRODUCT = "Select a Product";
	private static final String PURCHASE_OPTION_FINISH_TRANSACTION = "Finish Transaction and Give Change";
	private static final String[] PURCHASE_OPTIONS = {PURCHASE_OPTION_FEED_MONEY, PURCHASE_OPTION_SELECT_PRODUCT, PURCHASE_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	private Inventory inventory;
	private Purchase purchase = new Purchase();
	private StockFile fileReading = new StockFile();

	public VendingMachineCLI(Menu menu, Inventory inventory) {
		this.menu = menu;
		this.inventory = inventory;
	}
	//The main menu
	public void run(){
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inventory.printItems(); //Display the Inventory
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseMenu(); //Bring up the purchase menu
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you for using the vending machine!");
				return; //Simply exits the program
			}
		}
	}
	//The purchase menu
	public void purchaseMenu(){
		while (true) {
			System.out.println("Current balance:\r");
			System.out.format("$%.2f", purchase.getCurrentMoney()); //Gets current balance
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_OPTIONS);
			if (choice.equals(PURCHASE_OPTION_FEED_MONEY)) {
				purchase.insertMoney(); //Insert money into the vending machine
			} else if (choice.equals(PURCHASE_OPTION_SELECT_PRODUCT)) {
				//Display items and then select an item to purchase
				inventory.printItems();
				purchase.doPurchase(inventory);
			} else if (choice.equals(PURCHASE_OPTION_FINISH_TRANSACTION)) {
				//Give the change
				purchase.giveChange(purchase.getCurrentMoney());
				break; //Returns to the main menu
			}
		}
	}

	public static void main(String[] args){
		Menu menu = new Menu(System.in, System.out); //Create menu
		StockFile reader = new StockFile(); //Create file reader
		Map<String, Slot> inventoryMap = reader.readFile(); //Create inventory map
		Inventory inventory = new Inventory(inventoryMap); //Create inventory
		VendingMachineCLI cli = new VendingMachineCLI(menu, inventory);
		cli.run();
	}
}
