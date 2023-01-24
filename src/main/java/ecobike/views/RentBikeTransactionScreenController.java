package ecobike.views;

//import entities.RentBikeTransaction;
import ecobike.databaseservices.RentalDA;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RentBikeTransactionScreenController {
    @FXML
    private TextField text1, text2, text3, text4, text5, text8, text9, text10;

    @FXML
    private Button OK;

    public void initData(String rental_id){
        // TODO: get info to fill in the text boxes
        System.out.println("Display rent bike transaction information");
        text1.setText(String.valueOf(RentalDA.getRefund(rental_id)));
        text2.setText(String.valueOf(RentalDA.getRentalBike(rental_id).getBikeCode()));
        text3.setText(String.valueOf(RentalDA.getRentalBike(rental_id).getType()));
        text5.setText(RentalDA.getRentee(rental_id));
        text8.setText(RentalDA.getRentTime(rental_id));
        text9.setText(RentalDA.getReturnTime(rental_id));
        text10.setText(String.valueOf(RentalDA.getDeposit(rental_id)));
        text4.setText(String.valueOf(RentalDA.getDeposit(rental_id)-RentalDA.getRefund(rental_id)));
    }

    @FXML
    public void processOKClick(){
        Stage stage = (Stage)OK.getScene().getWindow();
        stage.close();
    }
}
