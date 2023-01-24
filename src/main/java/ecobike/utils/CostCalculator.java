package ecobike.utils;

import ecobike.entities.Bike;
import ecobike.entities.DoubleBike;
import ecobike.entities.SingleBike;

public  class CostCalculator {
    private CostCalculationStrategy costCalculationMethod;

    public  int calculateCost(int duration){
        return  costCalculationMethod.calculateCost(duration);
    }

    public void setCostCalculationMethod(Bike bike){
        if(bike instanceof SingleBike)
            this.costCalculationMethod = new SingleBikeCostCalculation();
        else if(bike instanceof DoubleBike)
            this.costCalculationMethod = new DoubleBikeCostCalculation();
        else
            this.costCalculationMethod = new ElectricBikeCostCalculation();
    }
}
