package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import Extras.DBHelper;
import Extras.TransactionSerialProducer;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class addMoneyController implements Initializable {


    public TextField txtAddMoney;
    public JFXButton confirmAddMoney;
    public Label lblAlertAddMoney;
    public TextField txtAddMoneySecendPass;
    public TextField txtAddMoneyUniquePass;
    public Button sendUniquePass;



    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void confirmAddingMoney(){

        if(txtAddMoney.getText().equals("")){
            alert("لطفا مبلغ را وارد کنید" , lblAlertAddMoney , "red") ;

        }



    }


    public void sendingUniqueCode(){

        txtAddMoneyUniquePass.setVisible(true);


    }

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
    loginPageController loginPage = new loginPageController();
    TransactionSerialProducer transactionSerialProducer = new TransactionSerialProducer();
    Account account = new Account();
    DBHelper dbHelper = new DBHelper();
    Transaction transaction = new Transaction();
    Date today = new Date();
    boolean addMoney(){

       transaction.setCostOfTransaction(txtAddMoney.getText());
        if (account.getSecondPassword().equals(txtAddMoneySecendPass)){
            transaction.setFinished(true);
            transaction.setDateOfTransaction((java.sql.Date) today);
            transaction.setSerialOfTransaction(transactionSerialProducer.serialProducer());
            dbHelper.insertTransaction(transaction);
            dbHelper.readAccount(loginPage.txtUserLogin.getText());
            account.setInventory(String.valueOf(Integer.valueOf(account.getInventory())+Integer.valueOf(txtAddMoney.getText())));

        }else {
            transaction.setFinished(false);
        }






      return transaction.isFinished();
    }


    public void initialize(URL location, ResourceBundle resources) {

    txtAddMoneySecendPass.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(4));
    txtAddMoneyUniquePass.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(4));
    txtAddMoney.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(20));





    }
}
