package ecobike.validators;


import java.time.LocalDate;

public class CardInfoValidator {
    public static boolean validateCardCode(String cardCode){
        return true;
    }
    public static boolean validateOwner(String owner){
        return true;
    }
    public static boolean validateCvv(String cvv){
        return true;
    }
    public static boolean validateExpireDate(LocalDate expireDate){
        return true;
    }

}
