package ecobike.subsystems.barcodesubsystem;

import com.google.gson.JsonObject;



public class BarcodeConverterController implements IBarcodeConverter{
    /**
     * chuyen barcode thanh bikeCode
     * @param barcode : barcode người dùng nhập vào
     * @return bikeCode kieu int
     */
    public int convertBarcodeToBikeCode(String barcode){
        JsonObject body = new JsonObject();
        body.addProperty("barcode", String.valueOf(barcode));

        String bikeCode = BarcodeConverterBoundary.convertBarcodeToBikeCode(body);


        return Integer.parseInt(bikeCode);
    }
}
