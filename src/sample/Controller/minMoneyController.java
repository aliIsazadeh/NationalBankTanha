package sample.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class minMoneyController implements Initializable{


    public Label lblAlertMinMoney;
    public JFXButton confirmMinMoney;
    public TextField txtMinMoney;

    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }



    public  void confirmMinMoney(){



        if(txtMinMoney.getText().equals("")){

            alert("لطفا مبلغ برداشتی را وارد کنید" , lblAlertMinMoney , "red");

        }

        else if(Long.parseLong(txtMinMoney.getText()) > 200000){

            alert( "توجه : سقف وجه برداشتی تا 200هزار تومان در یک روز می باشد." , lblAlertMinMoney , "red");

        }






    }





    public void initialize(URL location, ResourceBundle resources) {








    }



}
