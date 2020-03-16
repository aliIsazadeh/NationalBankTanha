package sample.Controller;

import Extras.DBHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class cardToCardController implements Initializable {


    public JFXTextField DestinationCardNumber;
    public TextArea txtCardToCardSubject;
    public JFXButton btnConfirmCardToCard;
    public JFXButton btnSearchDestinationCard;
    public TextArea txtDescribeDestinationCard;
    public Label lblAlertCardToCard;
    public AnchorPane cardTocardAncorPane;
    public JFXTextField txtCardToCardUniquePass;
    public Button btnSendUniqueCodeCardToCard;
    public JFXTextField txtMoneyCardToCard;


    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public  void searchDestinationCard(){
        DBHelper dbHelper = new DBHelper();
        String  destinationCardNumber=DestinationCardNumber.getText();

    if(destinationCardNumber.equals("")){
        alert("لطفا کارت مورد نظر را وارد کنید" , lblAlertCardToCard , "red");
    }else if (dbHelper.readAccount(Long.parseLong(destinationCardNumber))== null){
        alert("کارتی با این مشخصات وجود ندارد" , lblAlertCardToCard , "red");
    }



}


    public void testSearchDestination(){
        DestinationCardNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!DestinationCardNumber.getText().equals("") ){

                    btnSearchDestinationCard.setDisable(false);

                }


            }
        });

    }


    public void initialize(URL location, ResourceBundle resources) {


    }

}
