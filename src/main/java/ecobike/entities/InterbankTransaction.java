package ecobike.entities;

import java.time.LocalDateTime;

public class InterbankTransaction {
    private Card card;
    private String command;
    private String transactionContent;
    private double amount;
    private LocalDateTime createdAt;  //format: yyyy-mm-đd hour:minute:second

    public InterbankTransaction(){
    }

    public InterbankTransaction(Card card, String command, String transactionContent, double amount, LocalDateTime createdAt) {
        this.card = card;
        this.command = command;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

