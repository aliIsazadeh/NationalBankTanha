package sample.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class addMoneyController implements Initializable {


    public TextField txtAddMoney;
    public JFXButton confirmAddMoney;
    public Label lblAlertAddMoney;


    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void confirmAddingMoney(){

        if(txtAddMoney.getText().equals("")){
            alert("لطفا مبلغ را وارد کنید" , lblAlertAddMoney , "red") ;

        }



    }




    public void initialize(URL location, ResourceBundle resources) {








    }
}
