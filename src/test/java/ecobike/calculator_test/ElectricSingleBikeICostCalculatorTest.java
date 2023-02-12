package ecobike.calculator_test;

import ecobike.calculator.ElectricSingleBikeICostCalculator;
import junitparams.FileParameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ElectricSingleBikeICostCalculatorTest {
    private ElectricSingleBikeICostCalculator electricSingleBikeICostCalculator = new ElectricSingleBikeICostCalculator();

    @Test
    @FileParameters("src/test/resources/electricSingleBikeCost.csv")
    public void calculateCost(int duration, int expected) {
        int cost = electricSingleBikeICostCalculator.calculateCost(duration);
        Assertions.assertEquals(cost, expected);
    }
}
