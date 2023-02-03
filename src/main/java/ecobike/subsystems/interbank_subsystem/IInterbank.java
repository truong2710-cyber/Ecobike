package ecobike.subsystems.interbank_subsystem;

import ecobike.entities.Card;

/**
 * Interbank Subsystem Interface
 */
public interface IInterbank {
    String processTransaction(Card card, long cost, String command, String content);

    void reset();
}
