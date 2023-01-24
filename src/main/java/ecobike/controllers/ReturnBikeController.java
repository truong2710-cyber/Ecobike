package ecobike.controllers;

import ecobike.databaseservices.BikeDA;
import ecobike.databaseservices.EventDA;
import ecobike.databaseservices.PaymentTransactionDA;
import ecobike.databaseservices.RentalDA;
import ecobike.entities.Bike;
import ecobike.entities.Card;
import ecobike.entities.ParkingLot;
import ecobike.subsystems.interbanksubsystem.IInterbank;
import ecobike.subsystems.interbanksubsystem.InterbankSubsysController;
import ecobike.utils.CostCalculator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ReturnBikeController {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String processReturnBike(String rental_id, Card card, String park_id){
        // return the result code
        // get time
        LocalDateTime returnTime = LocalDateTime.now();
        LocalDateTime startTime = EventDA.getRentalStartTime(rental_id);
        long rentTime = returnTime.toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC);
        // get bike
        Bike bike = RentalDA.getRentalBike(rental_id);
        CostCalculator costCalculator = new CostCalculator();
        costCalculator.setCostCalculationMethod(bike);
        int rentBikeCost = costCalculator.calculateCost((int) rentTime/60);
        long refundAmount = bike.getDeposit() -  rentBikeCost;

        System.out.println("Refund amount: " + refundAmount);
        assert refundAmount > 0;
        IInterbank interbank = new InterbankSubsysController();
        String respondCode = interbank.processTransaction(card, refundAmount, "refund", "Refund transaction");
        System.out.println("respond code: " + respondCode);
        if (respondCode.equals("00")) {
            saveReturnTransaction(park_id, rental_id, refundAmount, bike.getBikeCode());
        }
        return respondCode;
    }

    public static void saveReturnTransaction(String park_id, String rental_id, long amount, int bike_id) {
        EventDA.saveEvent(rental_id, "end");
        PaymentTransactionDA.savePaymentTransaction(rental_id, amount, "refund");
        BikeDA.updateBikeParkAndStatus(bike_id, park_id);
    }
}
