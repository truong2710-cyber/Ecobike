package ecobike.database_services;

import ecobike.database_connection.MySQLConnector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventDatabaseService {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static LocalDateTime getRentalStartTime(String rental_id){
        String command = "SELECT time FROM event " +
                "WHERE rental_id = " + rental_id +
                " AND type = 'start'";
        String result = MySQLConnector.query(command).get(0).get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        LocalDateTime startTime = LocalDateTime.parse(result, formatter);

        return startTime;
    }
    public static void saveEvent(String rental_id, String type){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN));
        String command = String.format("INSERT INTO event(rental_id, time, type) " +
                "VALUES (%1$s, '%2$s', '%3$s')", rental_id, currentTime, type);
        MySQLConnector.execute(command);
    }

    public static ArrayList<ArrayList<String>> getAllEvents(){
        String command = "SELECT * FROM event";
        return MySQLConnector.query(command);
    }
//    public static void main(String[] args){
//        String txt = "2023-01-24T15:27:10";
//        LocalDateTime returnTime = getRentalStartTime("2");
//        long t = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
//        System.out.println(t);
//    }
}
