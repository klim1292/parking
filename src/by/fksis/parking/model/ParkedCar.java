package by.fksis.parking.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ParkedCar {
	
	private int spot;
	private String licensePlate;
	private String brand;
	private String model;
	private String color;
	private LocalDateTime startParkedDateTime;
	
	public ParkedCar(int spot, String licensePlate, String brand, String model, String color) {
		if(spot < 1) {
			throw new IllegalArgumentException("Invalid parking spot value");
		}
		if(licensePlate == null || !licensePlate.matches("\\d{4}\\p{Alpha}{2}-[0-7]")) {
			throw new IllegalArgumentException("Invalid car license plate value");
		}
		if(brand == null || brand.isBlank()) {
			throw new IllegalArgumentException("Invalid car brand value");
		}
		if(model == null || model.isBlank()) {
			throw new IllegalArgumentException("Invalid car model value");
		}
		if(color == null || color.isBlank()) {
			throw new IllegalArgumentException("Invalid car color value");
		}
		this.spot = spot;
		this.licensePlate = licensePlate;
		this.brand = brand;
		this.model = model;
		this.color = color;
		startParkedDateTime = LocalDateTime.now();
	}
	
	public int getSpot() {
		return spot;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getColor() {
		return color;
	}
	
	public LocalDateTime getStartParkedDateTime() {
		return startParkedDateTime;
	}
	
	@Override
	public String toString() {
		return String.format("%6d%15s%16s%14s%10s%18s", spot, licensePlate, brand, model, color, startParkedDateTime.format(DateTimeFormatter.ofPattern("d.MM.Y H:mm")));
	}
	
}