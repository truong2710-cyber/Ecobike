package ecobike.controllers;

import ecobike.database_services.BikeDatabaseService;
import ecobike.database_services.EventDatabaseService;
import ecobike.database_services.PaymentTransactionDatabaseService;
import ecobike.database_services.RentalDatabaseService;
import ecobike.entities.Bike;
import ecobike.entities.Card;
import ecobike.subsystems.interbank_subsystem.IInterbank;
import ecobike.subsystems.interbank_subsystem.InterbankController;
import ecobike.utils.CostCalculatorBoundary;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ReturnBikeController {
    public static String processReturnBike(String rental_id, Card card, String park_id){
        // return the result code
        // get time
        LocalDateTime returnTime = LocalDateTime.now();
        LocalDateTime startTime = EventDatabaseService.getRentalStartTime(rental_id);
        long rentTime = returnTime.toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC);
        // get bike
        Bike bike = RentalDatabaseService.getRentalBike(rental_id);
        CostCalculatorBoundary costCalculatorBoundary = new CostCalculatorBoundary();
        costCalculatorBoundary.setCostCalculationMethod(bike);
        int rentBikeCost = costCalculatorBoundary.calculateCost((int) rentTime/60);
        long refundAmount = bike.getDeposit() -  rentBikeCost;

        System.out.println("Refund amount: " + refundAmount);
        assert refundAmount > 0;
        IInterbank interbank = new InterbankController();
        String respondCode = interbank.processTransaction(card, refundAmount, "refund", "Refund transaction");
        System.out.println("respond code: " + respondCode);
        if (respondCode.equals("00")) {
            saveReturnTransaction(park_id, rental_id, refundAmount, bike.getBikeCode());
        }
        return respondCode;
    }

    public static void saveReturnTransaction(String park_id, String rental_id, long amount, int bike_id) {
        EventDatabaseService.saveEvent(rental_id, "end");
        PaymentTransactionDatabaseService.savePaymentTransaction(rental_id, amount, "refund");
        BikeDatabaseService.updateBikeParkAndStatus(bike_id, park_id);
    }
}
