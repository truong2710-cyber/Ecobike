package ecobike.calculator;

import ecobike.entities.*;

public class CostCalculatorBoundary {
    private ICostCalculator costCalculationStrategy;

    public int calculateCost(int duration) {
        return costCalculationStrategy.calculateCost(duration);
    }

    public void setCostCalculationStrategy(Bike bike) {
        if (bike instanceof SingleBike)
            this.costCalculationStrategy = new SingleBikeICostCalculator();
        else if (bike instanceof DoubleBike)
            this.costCalculationStrategy = new DoubleBikeICostCalculator();
        else if (bike instanceof ElectricSingleBike)
            this.costCalculationStrategy = new ElectricSingleBikeICostCalculator();
        else if (bike instanceof ElectricDoubleBike)
            this.costCalculationStrategy = new ElectricDoubleBikeICostCalculator();
    }
}
