package by.fksis.parking.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Predicate;

public class Parking {
	
	private ParkedCar[] cars;
	public final double PRICE;
	
	public Parking(int size, double hourPrice) {
		if(size < 1) {
			throw new IllegalArgumentException("Invalid parking size value");
		}
		if(hourPrice <= 0.0) {
			throw new IllegalArgumentException("Invalid hour price value");
		}
		cars = new ParkedCar[size];
		PRICE = hourPrice;
	}
	
	public ParkedCar intoParking(String licensePlate, String brand, String model, String color) throws ParkingException {
		for(int i = 0; i < cars.length; i++) {
			if(cars[i] == null) {
				cars[i] = new ParkedCar(i + 1, licensePlate, brand, model, color);
				return cars[i];
			}
		}
		throw new ParkingException("No empty parking spot");
	}
	
	public double outParking(String licensePlate) throws ParkingException {
		for(int i = 0; i < cars.length; i++) {
			if(cars[i] != null && cars[i].getLicensePlate().equalsIgnoreCase(licensePlate)) {
				double totalPrice = Math.ceil(Duration.between(cars[i].getStartParkedDateTime(), LocalDateTime.now()).toMinutes() / 60.0) * PRICE;
				cars[i] = null;
				return totalPrice;
			}
		}
		throw new ParkingException("Car not found");
	}
	
	public ParkedCar[] search(Predicate<ParkedCar> criterion) {
		return Arrays.stream(cars).filter(criterion).toArray(ParkedCar[]::new);
	}
	
	public ParkedCar[] cars() {
		return cars.clone();
	}
	
}