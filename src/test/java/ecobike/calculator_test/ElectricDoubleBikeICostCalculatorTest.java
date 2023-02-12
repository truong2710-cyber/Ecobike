package ecobike.calculator_test;

import ecobike.calculator.ElectricDoubleBikeICostCalculator;
import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ElectricDoubleBikeICostCalculatorTest {
    private ElectricDoubleBikeICostCalculator electricDoubleBikeICostCalculator = new ElectricDoubleBikeICostCalculator();

    @Test
    @FileParameters("src/test/resources/electricDoubleBikeCost.csv")
    public void calculateCost(int duration, int expected) {
        int cost = electricDoubleBikeICostCalculator.calculateCost(duration);
        Assertions.assertEquals(cost, expected);
    }
}
