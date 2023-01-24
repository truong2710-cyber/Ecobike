package ecobike.utils;

public interface CostCalculationStrategy {
    /**
     *
     * @param duration: (s) - the amount of time user rent the bike
     * @return chi phí
     */
    public int calculateCost(int duration);
}
