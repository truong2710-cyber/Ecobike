package ecobike.subsystems.barcodesubsystem;

/**
 * interface cho Barcode Converter Subsystem
 */
public interface IBarcodeConverter {
    /**
     * Interface chuyển barcode thành bikeCode
     *
     * @param barcode : barcode người dùng nhập vào
     * @return bikeCode
     */
    int convertBarcodeToBikeCode(String barcode);
}
