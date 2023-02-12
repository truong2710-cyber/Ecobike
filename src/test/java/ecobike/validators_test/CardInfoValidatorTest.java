package ecobike.validators_test;//package test.java.ecobike.validators;

import ecobike.validators.CardInfoValidator;
import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CardInfoValidatorTest {
    @Test
    @FileParameters("src/test/resources/cardCode.csv")
//    @Parameters({
//            "1|false",
//            "2|false",
//    })
    public void validateCardCode(String cardCode, String expected) {
        boolean check = CardInfoValidator.validateCardCode(cardCode);
        Assertions.assertEquals(String.valueOf(check),expected);
    }

    @Test
    public void validateOwner() {
        Assertions.assertEquals(1,1);
    }

    @Test
    public void validateCvv() {
        Assertions.assertEquals(1,1);
    }

    @Test
    public void validateExpireDate() {
        Assertions.assertEquals(1,1);
    }
}

