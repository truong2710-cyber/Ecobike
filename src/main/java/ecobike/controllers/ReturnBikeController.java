package ecobike.controllers;

import ecobike.database_services.BikeDatabaseService;
import ecobike.database_services.EventDatabaseService;
import ecobike.database_services.PaymentTransactionDatabaseService;
import ecobike.database_services.RentalDatabaseService;
import ecobike.entities.Bike;
import ecobike.entities.Card;
import ecobike.entities.Event;
import ecobike.entities.InterbankTransaction;
import ecobike.subsystems.interbank_subsystem.IInterbank;
import ecobike.subsystems.interbank_subsystem.InterbankController;
import ecobike.calculator.CostCalculatorBoundary;

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
        costCalculatorBoundary.setCostCalculationStrategy(bike);
        int rentBikeCost = costCalculatorBoundary.calculateCost((int) rentTime/60);
        long refundAmount = bike.getDeposit() -  rentBikeCost;

        System.out.println("Refund amount: " + refundAmount);
        assert refundAmount > 0;
        InterbankTransaction interbankTransaction = new InterbankTransaction(card, "refund", "Refund transaction", refundAmount, returnTime);
        Event event = new Event(rental_id, returnTime, "end");
        IInterbank interbank = new InterbankController();
        String respondCode = interbank.processTransaction(interbankTransaction);
        System.out.println("respond code: " + respondCode);
        if (respondCode.equals("00")) {
            saveReturnTransaction(park_id, bike.getBikeCode(), event, interbankTransaction);
        }
        return respondCode;
    }

    public static void saveReturnTransaction(String park_id, int bike_id, Event event, InterbankTransaction interbankTransaction) {
        EventDatabaseService.saveEvent(event.getRentalId(), event.getType());
        PaymentTransactionDatabaseService.savePaymentTransaction(event.getRentalId(), (long) interbankTransaction.getAmount(), interbankTransaction.getCommand());
        BikeDatabaseService.updateBikeParkAndStatus(bike_id, park_id);
    }
}
