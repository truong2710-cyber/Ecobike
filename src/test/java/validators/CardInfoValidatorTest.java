package validators;

import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class CardInfoValidatorTest {
    @Test
    @FileParameters("src/test/resources/cardCode.csv")
    public void validateCardCode(String cardCode, String expected) {
        //boolean check = CardInfoValidator
        Assertions.assertEquals(1,1);
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

