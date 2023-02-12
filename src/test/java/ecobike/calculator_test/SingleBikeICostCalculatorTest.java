package ecobike.calculator_test;

import ecobike.calculator.SingleBikeICostCalculator;
import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class SingleBikeICostCalculatorTest {
    private SingleBikeICostCalculator singleBikeICostCalculator = new SingleBikeICostCalculator();

    @Test
    @FileParameters("src/test/resources/singleBikeCost.csv")
    public void calculateCost(int duration, int expected) {
        int cost = singleBikeICostCalculator.calculateCost(duration);
        Assertions.assertEquals(cost, expected);
    }
}
