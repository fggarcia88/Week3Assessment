package view;

import java.util.List;
import java.util.Scanner;

import controller.ArcadeInventoryHelper;
import model.ArcadeInventory;

/**
 * @author Fernando Garcia - fggarcia
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */
public class Driver {
	static Scanner input = new Scanner(System.in);
	static ArcadeInventoryHelper aih = new ArcadeInventoryHelper();

	public static void main(String[] args) {
		runMenu();
	}

	private static void runMenu() {
		boolean repeat = true;
		System.out.println("Pizza Planet Arcade Database");
		while (repeat) {
			System.out.println("Select a menu option:");
			System.out.println("1. View arcade inventory");
			System.out.println("2. Add arcade cabinet");
			System.out.println("3. Edit arcade cabinet");
			System.out.println("4. Delete arcade cabinet");
			System.out.println("Any other number to exit.");
			System.out.print("-> ");
			int option = input.nextInt();
			if (option == 1) {
				viewInventory();
			}
			else if (option == 2) {
				addInventory();
			}
			else if (option == 3) {
				editInventory();
			}
			else if (option == 4) {
				deleteInventory();
			}
			else {
				aih.cleanUp();
				System.out.println("Shutting down...");
				System.out.println("Thank you for using Planet Arcade Database!");
				repeat = false;
			}
		}
	}
	
	private static void viewInventory() {
		List<ArcadeInventory> allArcades = aih.showAllArcades();
		for (ArcadeInventory arcade : allArcades) {
			System.out.println(arcade.returnArcadeDetails());
		}
		
	}

	private static void addInventory() {
		System.out.println("Enter arcade game title: ");
		System.out.print("-> ");
		input.nextLine();
		String title = input.nextLine();
		System.out.println("Enter cost per play: ");
		System.out.print("-> ");
		double costPerPlay = input.nextDouble();
		System.out.println("Enter the total purchase cost of arcade cabinet: ");
		System.out.print("-> ");
		double purchasePrice = input.nextDouble();
		ArcadeInventory toAdd = new ArcadeInventory(title, costPerPlay, purchasePrice);
		aih.insertArcade(toAdd);		
	}
	
	private static void editInventory() {
		System.out.println("Enter the arcade cabinet ID to edit:");
		System.out.print("-> ");
		int searchOption = input.nextInt();
		ArcadeInventory foundArcadeId = aih.lookupArcadeById(searchOption);
		System.out.println("Selected item: " + foundArcadeId.returnArcadeDetails());
		System.out.println("1. Update Title");
		System.out.println("2. Update Cost Per Play");
		System.out.println("3. Update Purchase Price");
		int updateOption = input.nextInt();
		input.nextLine();
		
		if (updateOption == 1) {
			System.out.println("Enter new title:");
			System.out.print("-> ");
			String newTitle = input.nextLine();
			foundArcadeId.setTitle(newTitle);
		}
		else if (updateOption == 2) {
			System.out.println("Enter new Cost Per Play:");
			System.out.print("-> ");
			double newCostPerPlay = input.nextDouble();
			input.nextLine();
			foundArcadeId.setCostPerPlay(newCostPerPlay);
		}
		else if (updateOption == 3) {
			System.out.println("Enter new Purchase Price:");
			System.out.print("-> ");
			double newPurchasePrice = input.nextDouble();
			input.nextLine();
			foundArcadeId.setPurchasePrice(newPurchasePrice);
		}		
		else {
			System.out.println("Invalid option");
			return;
		}
		
		aih.updateItem(foundArcadeId);
	}
	
	private static void deleteInventory() {
		System.out.println("Enter the arcade cabinet ID to delete");
		System.out.print("-> ");
		int idOption = input.nextInt();
		ArcadeInventory foundArcade = aih.lookupArcadeById(idOption);
		aih.deleteArcade(foundArcade);		
		System.out.println("Deleted entry: "+ foundArcade.returnArcadeDetails());
	}

}
