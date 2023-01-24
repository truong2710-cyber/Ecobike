package ecobike.databaseservices;

import ecobike.databaseconnection.MySQLDB;
import ecobike.entities.Card;

import java.util.ArrayList;

public class CardDA {
    public static ArrayList<ArrayList<String>> getAllCardInUse(){
        String command = "SELECT * FROM card";
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        s = MySQLDB.query(command);
        return s;
    }

    public static void saveCardInfo(String cardCode, String owner, String CVV, String expiredDate){
        String command = "INSERT INTO card VALUES" +
                "(" + '\'' + cardCode + '\'' + ", " +
                '\'' + owner + '\'' + ", " +
                '\'' + CVV + '\'' + ", " +
                '\'' + expiredDate + '\'' + ");";
        MySQLDB.execute(command);
    }

    public static void deleteCardInfo(String cardCode){
        String command = "DELETE FROM card WHERE" +
                " cardcode" + "=" + '\'' + cardCode + '\'' + ";";
        MySQLDB.execute(command);
    }

    public static Card getCardRentalTransaction(String rental_id){
        String command = "SELECT card.* " +
                "FROM card JOIN rental " +
                "ON card.cardcode = rental.cardcode " +
                "WHERE rental.id = " + rental_id;
        ArrayList<String> card_info = MySQLDB.query(command).get(0);
        Card card = new Card(card_info.get(0), card_info.get(1), card_info.get(2), card_info.get(3));
        return card;
    }
}
