package ecobike.calculator;

public class ElectricDoubleBikeICostCalculator implements ICostCalculator {
    @Override
    public int calculateCost(int duration) {
        // free if time is smaller than 10 minutes
        if (duration <= 10) {
            return 0;
        }

        // 20,000VND for the first 30 minutes
        int cost = 20000;
        duration -= 30;
        if (duration <= 0) return cost;
        int period = (int) Math.ceil(duration / 15.0);
        cost += period * 6000;
        return cost;
    }
}
