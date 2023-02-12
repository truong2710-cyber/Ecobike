package ecobike.validators;

public class BarcodeValidator {
    public static boolean validateBarcode(String barcode){
        try {
            Integer.parseInt(barcode);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
