package ecobike.subsystems.barcode_subsystem;

import com.google.gson.JsonObject;



public class BarcodeConverterBoundary {
    /**
     * Convert barcode to bike code
     * @param body : request body
     * @return bikeCode
     */
    public static String convertBarcodeToBikeCode(JsonObject body){
        try {
            // return "20221" + body.get("barcode").getAsString().substring(1);
            return body.get("barcode").getAsString();
        } catch (Exception e){
            System.out.println("Can not connect to API!");
        }
        return null;
    }
}
