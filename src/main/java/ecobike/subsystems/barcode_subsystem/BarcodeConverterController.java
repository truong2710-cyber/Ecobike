package ecobike.subsystems.barcode_subsystem;

import com.google.gson.JsonObject;



public class BarcodeConverterController implements IBarcodeConverter{
    /**
     * Convert barcode to bike code
     * @param barcode : barcode user input
     * @return bikeCode
     */
    public int convertBarcodeToBikeCode(String barcode){
        JsonObject body = new JsonObject();
        body.addProperty("barcode", String.valueOf(barcode));

        String bikeCode = BarcodeConverterBoundary.convertBarcodeToBikeCode(body);


        return Integer.parseInt(bikeCode);
    }
}
