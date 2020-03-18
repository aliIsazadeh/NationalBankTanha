package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import Extras.DBHelper;
import Extras.SecondPassProducer;
import Extras.TransactionSerialProducer;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class minMoneyController implements Initializable {


    public Label lblAlertMinMoney;
    public JFXButton confirmMinMoney;
    public TextField txtMinMoney;
    public Button btnSendUniquePass;
    public TextField txtUniquePassMinMoney;
    public TextField txtSecendPassWordMinMoney;
    private DBHelper dbHelper = new DBHelper();
    private Transaction transaction = new Transaction();
    private loginPageController loginPage = new loginPageController();
    private Account account =dbHelper.readAccount(loginPage.txtUserLogin.getText());
    private SecondPassProducer secondPassProducer = new SecondPassProducer();
    private TransactionSerialProducer transactionSerialProducer = new TransactionSerialProducer();
     Date date = new Date();

    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void confirmMinMoney() {


        if (txtMinMoney.getText().equals("")) {

            alert("لطفا مبلغ برداشتی را وارد کنید", lblAlertMinMoney, "red");

        } else if (Long.parseLong(txtMinMoney.getText()) > 200000) {

            alert("توجه : سقف وجه برداشتی تا 200هزار تومان در یک روز می باشد.", lblAlertMinMoney, "red");

        }


    }

    boolean minMoney(){
        if ((Long.parseLong(txtMinMoney.getText()) < Long.parseLong(account.getInventory()))&&txtSecendPassWordMinMoney.getText().equals(secondPassProducer.secondPass())) {
            account.setInventory(String.valueOf(Long.parseLong(account.getInventory())-Long.parseLong(txtMinMoney.getText())));
            dbHelper.updateAccount(account);
            transaction.setFinished(true);
            transaction.setTypeOfTransaction("برداشت وجه");
            transaction.setSerialOfTransaction(transactionSerialProducer.serialProducer());
            transaction.setDateOfTransaction(date);
            transaction.setCostOfTransaction(txtMinMoney.getText());
            transaction.setIDnumber(account.getAccountNumber());
        }else if (!(txtSecendPassWordMinMoney.getText().equals(secondPassProducer.secondPass()))){
            transaction.setFinished(false);
            transaction.setTypeOfTransaction("برداشت وجه");
            transaction.setSerialOfTransaction(transactionSerialProducer.serialProducer());
            transaction.setDateOfTransaction(date);
            transaction.setCostOfTransaction(txtMinMoney.getText());
            transaction.setIDnumber(account.getAccountNumber());
        }else if ((txtSecendPassWordMinMoney.getText().equals(secondPassProducer.secondPass()))&&!(Long.parseLong(txtMinMoney.getText()) < Long.parseLong(account.getInventory()))) {
            transaction.setFinished(false);
            transaction.setTypeOfTransaction("برداشت وجه");
            transaction.setSerialOfTransaction(transactionSerialProducer.serialProducer());
            transaction.setDateOfTransaction(date);
            transaction.setCostOfTransaction(txtMinMoney.getText());
            transaction.setIDnumber(account.getAccountNumber());
        }

        dbHelper.insertTransaction(transaction);
        return transaction.isFinished();
    }

    public void initialize(URL location, ResourceBundle resources) {


    }


}
