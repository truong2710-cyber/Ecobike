package ecobike.subsystems_test.barcode_subsystem_test;

import ecobike.subsystems.barcode_subsystem.BarcodeConverterController;
import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class BarcodeConverterControllerTest {
    @Test
    @FileParameters("src/test/resources/barcodeToBikeCode.csv")
    public void convertBarcodeToBikeCode(String barcode, int expected) {
        int bikeCode = new BarcodeConverterController().convertBarcodeToBikeCode(barcode);
        Assertions.assertEquals(bikeCode, expected);
    }
}
