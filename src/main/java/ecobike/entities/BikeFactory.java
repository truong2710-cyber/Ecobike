package ecobike.entities;

import ecobike.database_services.ParkingLotDatabaseService;

import java.util.ArrayList;

public class BikeFactory {
    private BikeFactory() {
    }
    public static Bike getBike(ArrayList<String> bike) {
        switch (bike.get(1)) {
            case "single" -> {
                return new SingleBike(Integer.parseInt(bike.get(0)), bike.get(1), bike.get(2), bike.get(3), bike.get(4), bike.get(5), Integer.parseInt(bike.get(6)), bike.get(9), Integer.parseInt(bike.get(10)), Integer.parseInt(bike.get(11)), ParkingLotDatabaseService.getBoolean(bike.get(12)));
            }
            case "double" -> {
                return new DoubleBike(Integer.parseInt(bike.get(0)), bike.get(1), bike.get(2), bike.get(3), bike.get(4), bike.get(5), Integer.parseInt(bike.get(6)), bike.get(9), Integer.parseInt(bike.get(10)), Integer.parseInt(bike.get(11)), ParkingLotDatabaseService.getBoolean(bike.get(12)));
            }
            case "electric single" -> {
                return new ElectricSingleBike(Integer.parseInt(bike.get(0)), bike.get(1), bike.get(2), bike.get(3), bike.get(4), bike.get(5), Integer.parseInt(bike.get(6)), bike.get(9), Integer.parseInt(bike.get(10)), Integer.parseInt(bike.get(11)), ParkingLotDatabaseService.getBoolean(bike.get(12)), Integer.parseInt(bike.get(7)), Integer.parseInt(bike.get(8)));
            }
            case "electric double" -> {
                return new ElectricDoubleBike(Integer.parseInt(bike.get(0)), bike.get(1), bike.get(2), bike.get(3), bike.get(4), bike.get(5), Integer.parseInt(bike.get(6)), bike.get(9), Integer.parseInt(bike.get(10)), Integer.parseInt(bike.get(11)), ParkingLotDatabaseService.getBoolean(bike.get(12)), Integer.parseInt(bike.get(7)), Integer.parseInt(bike.get(8)));
            }
            default -> throw new IllegalArgumentException("This bike type is unsupported");
        }
    }


}
