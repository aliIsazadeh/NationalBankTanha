package sample.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class billController implements Initializable {


    public JFXTextField txtBillNumber;
    public JFXTextField txtPayNumber;
    public JFXTextField txtBillCost;
    public JFXTextField txtSecendPassForBill;
    public JFXTextField txtUniquePassForBill;
    public Label billAlertLabel;




    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if(e.getCharacter().matches("[0-9.]")){
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume();
                    }
                }else{
                    e.consume();
                }
            }
        };
    }


    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void payBill(){
        if(txtBillNumber.getText().equals("")||txtPayNumber.getText().equals("")||txtBillCost.getText().equals("")||txtSecendPassForBill.getText().equals("")||txtUniquePassForBill.getText().equals(""))
        {
            alert("لطفا فیلد هارا پرکنید", billAlertLabel, "red");
        }




    }





    public void initialize(URL location, ResourceBundle resources) {


        txtBillNumber.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(15));
        txtPayNumber.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(15));
        txtBillCost.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(15));
        txtSecendPassForBill.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(4));
        txtUniquePassForBill.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(4));

    }

}
