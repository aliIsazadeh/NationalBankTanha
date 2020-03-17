package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import Extras.DBHelper;
import Extras.TransactionSerialProducer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Date;
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
    private DBHelper dbHelper;

    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void searchDestinationCard() {

        long accountNumber = Long.parseLong(DestinationCardNumber.getText());
        dbHelper = new DBHelper();
        Account account = dbHelper.readAccount(accountNumber);
        if (DestinationCardNumber.getText().equals("")) {
            alert("لطفا کارت مورد نظر را وارد کنید", lblAlertCardToCard, "red");
        } else if (account == null) {
            alert("حسابی با این مشحصات وجود ندارد", lblAlertCardToCard, "red");
        } else {
            alert(account.getPerson().getName(), lblAlertCardToCard, "WHITE");
        }
    }

    public void doTransaction(MouseEvent mouseEvent) {
        Account from = new loginPageController().account;
        long accountNumber = Long.parseLong(DestinationCardNumber.getText());
        dbHelper = new DBHelper();
        Account to = dbHelper.readAccount(accountNumber);
        doTransaction(to, from);

    }

    public void doTransaction(Account to, Account from) {
        Transaction transaction = new Transaction();
        long money = Long.parseLong(txtMoneyCardToCard.getText());
        long toMoney = Long.parseLong(to.getInventory());
        long fromMoney = Long.parseLong(from.getInventory());
        if (fromMoney >= money) {
            creatTransaction(to, from, transaction, money, true);
            toMoney += money;
            fromMoney -= money;
            to.setInventory(String.valueOf(toMoney));
            from.setInventory(String.valueOf(fromMoney));
            dbHelper = new DBHelper();
            dbHelper.updateAccount(to);
            dbHelper.updateAccount(from);
        } else {
            creatTransaction(to, from, transaction, money, false);
        }
    }

    private void creatTransaction(Account to, Account from, Transaction transaction, long money, boolean b) {
        transaction.setTypeOfTransaction("انتقال وجه");
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setFinished(b);
        transaction.setSerialOfTransaction(new TransactionSerialProducer().serialProducer());
        transaction.setCostOfTransaction(String.valueOf(money));
        transaction.setDateOfTransaction(new Date());
        dbHelper = new DBHelper();
        dbHelper.insertTransaction(transaction);
    }


    public void testSearchDestination() {
        DestinationCardNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!DestinationCardNumber.getText().equals("")) {

                    btnSearchDestinationCard.setDisable(false);

                }


            }
        });

    }


    public void initialize(URL location, ResourceBundle resources) {


    }


}
