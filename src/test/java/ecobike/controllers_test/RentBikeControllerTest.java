package ecobike.controllers_test;

import ecobike.controllers.RentBikeController;
import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RentBikeControllerTest {
    @Test
    @FileParameters("src/test/resources/cardOnRental.csv")
    public void checkCardOnRental(String cardCode, String expected) {
        boolean isCardOnRental = new RentBikeController().checkCardOnRental(cardCode);
        Assertions.assertEquals(String.valueOf(isCardOnRental), expected);
    }
}
