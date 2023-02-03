package ecobike.entities;

import ecobike.database_services.CardDatabaseService;

public class Card {
    private String cardCode;
    private String owner;
    private String CVV;
    private String expiredDate;

    public Card(String cardCode, String owner, String CVV, String expiredDate) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.CVV = CVV;
        this.expiredDate = expiredDate;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void saveCardInfo(){
        CardDatabaseService.saveCardInfo(cardCode, owner, CVV, expiredDate);
    }

    public void deleteFromDatabase(){
        CardDatabaseService.deleteCardInfo(this.cardCode);
    }
}
