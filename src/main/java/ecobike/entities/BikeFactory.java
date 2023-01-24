package ecobike.entities;

import ecobike.databaseservices.ParkingLotDA;

import java.util.ArrayList;

public class BikeFactory {
    private BikeFactory() {
    }
    public static final Bike getBike(ArrayList<String> bike) {
        switch (bike.get(1)) {
            case "single":
                SingleBike singleBike = new SingleBike(Integer.parseInt(bike.get(0)), bike.get(1), bike.get(2), bike.get(3), bike.get(4), bike.get(5), Integer.parseInt(bike.get(6)), bike.get(9), Integer.parseInt(bike.get(10)), Integer.parseInt(bike.get(11)), ParkingLotDA.getBoolean(bike.get(12)));
                return singleBike;

            case "double":
                DoubleBike doubleBike = new DoubleBike(Integer.parseInt(bike.get(0)), bike.get(1), bike.get(2), bike.get(3), bike.get(4), bike.get(5), Integer.parseInt(bike.get(6)), bike.get(9), Integer.parseInt(bike.get(10)), Integer.parseInt(bike.get(11)), ParkingLotDA.getBoolean(bike.get(12)));
                return doubleBike;

            case "electric single":
                ElectricSingleBike electricSingleBike = new ElectricSingleBike(Integer.parseInt(bike.get(0)), bike.get(1), bike.get(2), bike.get(3), bike.get(4), bike.get(5), Integer.parseInt(bike.get(6)), bike.get(9), Integer.parseInt(bike.get(10)), Integer.parseInt(bike.get(11)), ParkingLotDA.getBoolean(bike.get(12)), Integer.parseInt(bike.get(7)), Integer.parseInt(bike.get(8)));
                return electricSingleBike;

            case "electric double":
                ElectricDoubleBike electricDoubleBike = new ElectricDoubleBike(Integer.parseInt(bike.get(0)), bike.get(1), bike.get(2), bike.get(3), bike.get(4), bike.get(5), Integer.parseInt(bike.get(6)), bike.get(9), Integer.parseInt(bike.get(10)), Integer.parseInt(bike.get(11)), ParkingLotDA.getBoolean(bike.get(12)), Integer.parseInt(bike.get(7)), Integer.parseInt(bike.get(8)));
                return electricDoubleBike;

            default:
                throw new IllegalArgumentException("This bike type is unsupported");
        }
    }


}
