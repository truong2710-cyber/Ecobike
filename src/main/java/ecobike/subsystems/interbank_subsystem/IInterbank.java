package ecobike.subsystems.interbank_subsystem;

import ecobike.entities.Card;
import ecobike.entities.InterbankTransaction;

/**
 * Interbank Subsystem Interface
 */
public interface IInterbank {
    String processTransaction(InterbankTransaction interbankTransaction);

    void reset();
}
