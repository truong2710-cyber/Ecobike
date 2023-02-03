package ecobike.database_services;

import ecobike.database_connection.MySQLConnector;
import ecobike.entities.Card;

import java.util.ArrayList;

public class CardDatabaseService {
    public static void saveCardInfo(String cardCode, String owner, String CVV, String expiredDate){
        ArrayList<ArrayList<String>> card = getCard(cardCode);
        if (!card.isEmpty()) return;
        String command = "INSERT INTO card VALUES" +
                "(" + '\'' + cardCode + '\'' + ", " +
                '\'' + owner + '\'' + ", " +
                '\'' + CVV + '\'' + ", " +
                '\'' + expiredDate + '\'' + ");";
        MySQLConnector.execute(command);
    }

    public static void deleteCardInfo(String cardCode){
        String command = "DELETE FROM card WHERE" +
                " cardcode" + "=" + '\'' + cardCode + '\'' + ";";
        MySQLConnector.execute(command);
    }

    public static ArrayList<ArrayList<String>> getCard(String cardCode){
        String command = "SELECT * FROM card WHERE cardcode = " + cardCode;
        ArrayList<ArrayList<String>> result = MySQLConnector.query(command);
        return result;
    }

    public static Card getCardRentalTransaction(String rental_id){
        String command = "SELECT card.* " +
                "FROM card JOIN rental " +
                "ON card.cardcode = rental.cardcode " +
                "WHERE rental.id = " + rental_id;
        ArrayList<String> card_info = MySQLConnector.query(command).get(0);
        Card card = new Card(card_info.get(0), card_info.get(1), card_info.get(2), card_info.get(3));
        return card;
    }

//    public static void main(String[] args){
//        saveCardInfo("123412341234", "Nguyen Phuc Tan", "111111", "2029-01-12");
//    }
}
