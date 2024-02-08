package by.fksis.parking.controller;

import java.util.Objects;
import java.util.function.Predicate;

import by.fksis.parking.model.ParkedCar;
import by.fksis.parking.model.Parking;
import by.fksis.parking.model.ParkingException;
import by.fksis.parking.view.Printer;

public class App {
	
	private Parking parking;
	private Printer printer;
	
	public App(Parking parking, Printer printer) {
		if(parking == null || printer == null) {
			throw new IllegalArgumentException("Parking or Printer should not be null");
		}
		this.parking = parking;
		this.printer = printer;
	}
	
	public static void main(String[] args) {
		App app = new App(new Parking(10, 1.53), Printer.INSTANCE);
		app.run();
	}
	
	public void run() {
		int choice;
		while((choice = printer.menu()) != 0) {
			try {
				switch(choice) {
					case 1: {
						String[] enteredData = printer.enterParkedCarData("Into parking", 4);
						printer.printParkedCarData(parking.intoParking(enteredData[0], enteredData[1], enteredData[2], enteredData[3]));
					} break;
					case 2: {
						String[] enteredData = printer.enterParkedCarData("Out parking", 1);
						printer.printTotalPrice(parking.outParking(enteredData[0]));
					} break;
					case 3: {
						String[] enteredData = printer.enterParkedCarData("Search cars", 4);
						Predicate<ParkedCar> criterion = Objects::nonNull;
						if(!enteredData[0].isBlank()) {
							criterion = criterion.and(car -> car.getLicensePlate().equalsIgnoreCase(enteredData[0]));
						}
						if(!enteredData[1].isBlank()) {
							criterion = criterion.and(car -> car.getBrand().equalsIgnoreCase(enteredData[1]));
						}
						if(!enteredData[2].isBlank()) {
							criterion = criterion.and(car -> car.getModel().equalsIgnoreCase(enteredData[2]));
						}
						if(!enteredData[3].isBlank()) {
							criterion = criterion.and(car -> car.getColor().equalsIgnoreCase(enteredData[3]));
						}
						printer.printParkedCarData((Object[])parking.search(criterion));
					} break;
					case 4: {
						printer.printAllParkedCars(parking.cars());
					} break;
					default: continue;
				}
			} catch(IllegalArgumentException | ParkingException e) {
				printer.printExceptionMessage(e.getMessage());
			}
			printer.back();
		}
		printer.close();
	}
	
}