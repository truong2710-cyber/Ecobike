package ecobike.database_services;

import ecobike.database_connection.MySQLConnector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentTransactionDatabaseService {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static void savePaymentTransaction(String rental_id, long amount, String type){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN));
        String command = String.format("INSERT INTO paymenttransaction(rental_id, amount, time, type) " +
                "VALUES (%1$s, '%2$s', '%3$s', '%4$s')", rental_id, amount, currentTime, type);
        MySQLConnector.execute(command);
    }
}
