package ecobike.subsystems.barcodesubsystem;

import com.google.gson.JsonObject;



public class BarcodeConverterBoundary {
    /**
     * chuyen barcode thanh bike code
     * @param body : noi dung request gui len api
     * @return bikeCode
     */
    public static String convertBarcodeToBikeCode(JsonObject body){
        try {
            // return "202100"+body.get("barcode").getAsString().substring(1);
            return body.get("barcode").getAsString();
        } catch (Exception e){
            System.out.println("Không kết nối được API !");
        }
        return null;
    }
}
