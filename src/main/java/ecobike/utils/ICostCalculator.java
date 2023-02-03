package ecobike.utils;

public interface ICostCalculator {
    /**
     * @param duration: (minutes) - the amount of time user rent the bike
     * @return cost
     */
    int calculateCost(int duration);
}
