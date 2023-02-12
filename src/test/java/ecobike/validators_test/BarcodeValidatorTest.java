package ecobike.validators_test;

import ecobike.validators.BarcodeValidator;
import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class BarcodeValidatorTest {
    @Test
    @FileParameters("src/test/resources/barcode.csv")
    public void validateBarcode(String barcode, String expected) {
        boolean check = BarcodeValidator.validateBarcode(barcode);
        Assertions.assertEquals(String.valueOf(check), expected);
    }
}
