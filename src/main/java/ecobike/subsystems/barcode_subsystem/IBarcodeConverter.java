package ecobike.subsystems.barcode_subsystem;

/**
 * Barcode Converter Subsystem Interface
 */
public interface IBarcodeConverter {
    /**
     * Interface of converting barcode to bike code
     * @param barcode : barcode user input
     * @return bikeCode
     */
    int convertBarcodeToBikeCode(String barcode);
}
