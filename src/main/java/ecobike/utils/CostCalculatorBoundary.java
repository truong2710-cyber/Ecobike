package ecobike.utils;

import ecobike.entities.*;

public class CostCalculatorBoundary {
    private ICostCalculator costCalculationMethod;

    public int calculateCost(int duration) {
        return costCalculationMethod.calculateCost(duration);
    }

    public void setCostCalculationMethod(Bike bike) {
        if (bike instanceof SingleBike)
            this.costCalculationMethod = new SingleBikeICostCalculator();
        else if (bike instanceof DoubleBike)
            this.costCalculationMethod = new DoubleBikeICostCalculator();
        else if (bike instanceof ElectricSingleBike)
            this.costCalculationMethod = new ElectricSingleBikeICostCalculator();
        else if (bike instanceof ElectricDoubleBike)
            this.costCalculationMethod = new ElectricDoubleBikeICostCalculator();
    }
}
