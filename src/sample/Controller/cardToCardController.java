package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class cardToCardController implements Initializable {


    public JFXTextField DestinationCardNumber;
    public TextArea txtCardToCardSubject;
    public JFXButton btnConfirmCardToCard;
    public JFXButton btnSearchDestinationCard;
    public TextArea txtDescribeDestinationCard;
    public Label lblAlertCardToCard;


    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public  void searchDestinationCard(){

    if(DestinationCardNumber.getText().equals("")){
        alert("لطفا کارت مورد نظر را وارد کنید" , lblAlertCardToCard , "red");
    }



}





    public void initialize(URL location, ResourceBundle resources) {


    }

}
