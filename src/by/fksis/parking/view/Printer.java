package by.fksis.parking.view;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Printer {
	
	private Scanner scnr;
	public static final Printer INSTANCE = new Printer();
	
	private Printer() {
		scnr = new Scanner(System.in);
	}
	
	public int menu() {
		System.out.println("\n\n\nParking menu");
		System.out.println("1 - Into parking");
		System.out.println("2 - Out parking");
		System.out.println("3 - Search car");
		System.out.println("4 - List");
		System.out.println("0 - Exit");
		System.out.print("->");
		String choice;
		while(!(choice = scnr.nextLine()).matches("\\d+")) {
			printExceptionMessage("Entered not number. Try again");
			System.out.print("->");
		}
		return Integer.parseInt(choice);
	}
	
	public String[] enterParkedCarData(String formLabel, int fieldCount) {
		String[] fieldLabels = {"License plate", "Brand", "Model", "Color"};
		if(fieldCount < 1 || fieldCount > fieldLabels.length) {
			throw new IllegalArgumentException();
		}
		String[] fields = new String[fieldCount];
		System.out.println(formLabel);
		for(int i = 0; i < fields.length; i++) {
			System.out.print(fieldLabels[i] + ": ");
			fields[i] = scnr.nextLine();
		}
		return fields;
	}
	
	@SuppressWarnings("unchecked")
	public <T> void printParkedCarData(T... cars) {
		if(cars.length > 0) {
			int spot = 1;
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("| Spot| License plate|          Brand|        Model|    Color|     Start parked|");
			System.out.println("--------------------------------------------------------------------------------");
			for(Object car : cars) {
				System.out.printf("%s%n", car != null ? car : String.format("%6d\t\t\t\t\tF  R  E  E", spot));
				System.out.println("--------------------------------------------------------------------------------");
				spot++;
			}			
		} else {
			System.out.println("Cars not found");
		}
	}
	
	public <T> void printAllParkedCars(T[] cars) {
		printParkedCarData(cars);
		System.out.printf("%d free of %d%n", Arrays.stream(cars).filter(Objects::isNull).count(), cars.length);
	}
	
	public void printTotalPrice(double totalPrice) {
		System.out.println("Total price: " + totalPrice);
	}
	
	public void printExceptionMessage(String message) {
		System.err.println(message);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void back() {
		System.out.print("\nPress enter to back to menu...");
		scnr.nextLine();
	}
	
	public void close() {
		scnr.close();
	}
	
}