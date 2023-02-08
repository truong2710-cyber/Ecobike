package ecobike.validators;


import java.time.LocalDate;
import java.util.regex.Pattern;

public class CardInfoValidator {
    public static boolean validateCardCode(String cardCode){
        Pattern pattern = Pattern.compile("[0-9]{12}");

        return pattern.matcher(cardCode).matches();
    }
    public static boolean validateOwner(String owner){
        return (owner.length() > 0);
    }
    public static boolean validateCvv(String cvv){
        return (cvv.length() > 0);
    }
    public static boolean validateExpireDate(LocalDate expireDate){
        LocalDate now = LocalDate.now();

        return expireDate.isAfter(now);
    }

}
