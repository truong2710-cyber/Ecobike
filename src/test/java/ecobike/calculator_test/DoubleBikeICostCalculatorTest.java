package ecobike.calculator_test;

import ecobike.calculator.DoubleBikeICostCalculator;
import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DoubleBikeICostCalculatorTest {
    private DoubleBikeICostCalculator doubleBikeICostCalculator = new DoubleBikeICostCalculator();

    @Test
    @FileParameters("src/test/resources/doubleBikeCost.csv")
    public void calculateCost(int duration, int expected) {
        int cost = doubleBikeICostCalculator.calculateCost(duration);
        Assertions.assertEquals(cost, expected);
    }
}
