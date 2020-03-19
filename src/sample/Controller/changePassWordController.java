package sample.Controller;

import DataStructure.Account;
import Extras.DBHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class changePassWordController implements Initializable {
    public JFXTextField txtRecentPass;
    public JFXTextField txtNewPass;
    public JFXButton btnChangePass;
    public Label lblAlert;
    private loginPageController loginPage ;
    private DBHelper dbHelper;
    private Account account ;



    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }

    public void change(){
        dbHelper = new DBHelper();
        loginPage = new loginPageController();
        account = dbHelper.readAccount(loginPage.getAccount().getUserName());

        if(txtRecentPass.getText().equals("")||txtNewPass.getText().equals("")){

            alert("لطفا فیلد هارا پر کنید",lblAlert,"red");
        }


        if (txtRecentPass.getText().equals(account.getAccountPassword())){
            account.setAccountPassword(txtNewPass.getText());

            alert("رمز عبور حساب بانکی تغییر داده شد",lblAlert,"green");

        }

        else {

            alert("رمز عبور وارد شده(رمز فعلی) اشتباه است",lblAlert,"red");


        }



    }


    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[0-9.]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        };
    }
    public void testPassRecent() {
        txtRecentPass.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                if (!txtNewPass.getText().equals("")) {

                    btnChangePass.setDisable(false);


                }


            }
        });

    }


    public void testPassNew() {
        txtNewPass.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                if (!txtRecentPass.getText().equals("")) {

                    btnChangePass.setDisable(false);


                }


            }
        });

    }

    public void initialize(URL location, ResourceBundle resources) {

        txtRecentPass.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(12));
        txtNewPass.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(12));





    }


}


