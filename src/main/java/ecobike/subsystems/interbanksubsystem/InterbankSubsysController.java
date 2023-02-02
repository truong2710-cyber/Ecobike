package ecobike.subsystems.interbanksubsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ecobike.entities.Card;
import ecobike.entities.InterbankTransaction;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InterbankSubsysController implements IInterbank {

    /**
     * @param card : Card instance
     * @param cost : transaction amount
     * @param command : transaction request {"pay", "refund"}
     * @param content : transaction content
     * @return : error code returned by API
     */

    @Override
    public String processTransaction(Card card, long cost, String command, String content){
        reset();
        return "00";
//        try {
//            // convert to payment transaction
//            Calendar calendar = Calendar.getInstance();
//            Date date = calendar.getTime();
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String createdAt = df.format(date);
//
//            InterbankTransaction transaction= new InterbankTransaction();
//            transaction.setCardCode(card.getCardCode());
//            transaction.setOwner(card.getOwner());
//            transaction.setCvvCode(card.getCVV());
//            transaction.setDateExpired(card.getExpiredDate());
//            transaction.setCommand(command);
//            transaction.setTransactionContent(content);
//            transaction.setAmount(cost);
//            transaction.setCreatedAt(createdAt);
//
//            String transactString = new ObjectMapper().writeValueAsString(transaction);
//            JsonObject transactionBody = new JsonParser().parse(transactString).getAsJsonObject();
//
//            // convert to request transaction
//            JsonObject transToHash = new JsonObject();
//            // transToHash là chuỗi cần băm
//            transToHash.addProperty("secretKey", "Bk5+TDRsBPY=");
//            transToHash.add("transaction", transactionBody);
//            MessageDigest md = null;
//            try {
//                md = MessageDigest.getInstance("MD5");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            md.update(transToHash.toString().getBytes());
//            byte[] digest = md.digest();
//            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
//            JsonObject sentJson = new JsonObject();
//            sentJson.addProperty("version","1.0.1");
//            sentJson.add("transaction",transactionBody);
//            sentJson.addProperty("appCode", "ApBD97uYEU8=");
//            sentJson.addProperty("hashCode", myHash);
//            System.out.println("Sent string: " + sentJson.toString());
//
//            InterbankBoundary interbank = new InterbankBoundary();
//            //String errorCode = "00";
//            String errorCode = interbank.processTransaction(sentJson);
//            return errorCode;
//
//        }catch (Exception e) {
//            System.out.println("Error process transaction!");
//            return "08";
//        }
    }

    /**
     * Reset account balance
     */
    @Override
    public void reset(){
        JsonObject body = new JsonObject();
        InterbankBoundary interbank = new InterbankBoundary();
        String errorCode = interbank.reset();
        switch (errorCode) {
            case "00": System.out.println("Successfully reset balance!"); break;
            case "01": System.out.println("Invalid card!"); break;
            case "02": System.out.println("Not enough balance!"); break;
            case "03": System.out.println("Internal server error!"); break;
            case "04": System.out.println("Suspicious transaction!"); break;
            case "05": System.out.println("Not enough transaction information!"); break;
            case "06": System.out.println("Lack of version information!"); break;
            case "07": System.out.println("Invalid amount!"); break;
            default: System.out.println("404 Not Found!");
        }
    }
}
