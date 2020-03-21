package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import Extras.DBHelper;
import Extras.SecondPassProducer;
import Extras.SendSMS;
import Extras.TransactionSerialProducer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class cardToCardController implements Initializable {


    public JFXTextField DestinationCardNumber;
    public TextArea txtCardToCardSubject;
    //
    public JFXButton btnConfirmCardToCard;
    public JFXButton btnSearchDestinationCard;
    //
    public TextArea txtDescribeDestinationCard;
    public Label lblAlertCardToCard;

    public AnchorPane cardTocardAncorPane;

    public JFXTextField txtCardToCardUniquePass;
    ///
    public Button btnSendUniqueCodeCardToCard;
    ///
    public JFXTextField txtMoneyCardToCard;
    public JFXTextField txtSecondPass;
    private DBHelper dbHelper;
    private notificationCardToCard not;

    private String password;
    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }

    public void sendingUniqueCode() {


        SecondPassProducer secondPassProducer = new SecondPassProducer();
        password=secondPassProducer.secondPass();
        SendSMS sendSMS =new SendSMS(new loginPageController().getAccount().getPerson().getPhoneNumber());
        sendSMS.setMessage(password);
        sendSMS.send();
        System.out.println(password);
        alert(" رمز پویا به شماره شما ارسال شد",lblAlertCardToCard,"green");


    }



    public void searchDestinationCard() {

        long accountNumber = Long.parseLong(DestinationCardNumber.getText());
        dbHelper = new DBHelper();
        Account account = dbHelper.readAccount(accountNumber);
        if (DestinationCardNumber.getText().equals("")) {
            alert("لطفا کارت مورد نظر را وارد کنید", lblAlertCardToCard, "red");
        } else if (account.getAccountNumber() == null) {
            alert("حسابی با این مشحصات وجود ندارد", lblAlertCardToCard, "red");
        } else {
            txtDescribeDestinationCard.setText("دارنده حساب : "+account.getPerson().getName() +account.getPerson().getLastName());
            cardTocardAncorPane.setVisible(true);
        }
    }

    public void doTransaction(MouseEvent mouseEvent) {
        Account from = new loginPageController().getAccount();
        long accountNumber = Long.parseLong(DestinationCardNumber.getText());
        dbHelper = new DBHelper();
        Account to = dbHelper.readAccount(accountNumber);
        if (from.getSecondPassword().equals(txtSecondPass.getText())&&txtCardToCardUniquePass.getText().equals(password)) {
            doTransaction(to, from);

            not = new notificationCardToCard();
            not.ReceivingAmountCardToCard(txtMoneyCardToCard.getText());




            Parent root;
            try {
                Stage stage = (Stage) btnConfirmCardToCard.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/notificationCardToCard.fxml"));
                root = loader.load();
                stage = new Stage();
                Stage finalStage = stage;
                finalStage.setResizable(false);
                finalStage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root, 361, 329));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (txtSecondPass.getText().equals("")) {
            alert("رمز دوم خود را وارد کنید", lblAlertCardToCard, "red");
        }else if (txtCardToCardUniquePass.getText().equals(""))
            alert("رمز پویای خود را وارد کنید", lblAlertCardToCard, "red");

        else if (!txtSecondPass.getText().equals(from.getSecondPassword())&&txtCardToCardUniquePass.getText().equals(password)) {
            alert("رمز دوم یا رمز پویا اشتباه است", lblAlertCardToCard, "red");
        }

    }

    private void doTransaction(Account to, Account from) {
        Transaction transaction = new Transaction();
        long money = Long.parseLong(txtMoneyCardToCard.getText());
        long toMoney = Long.parseLong(to.getInventory());
        long fromMoney = Long.parseLong(from.getInventory());
        if (fromMoney >= money) {
           Transaction transaction1= creatTransaction(to, from, transaction, money, true);
            ArrayList<Transaction> list = new ArrayList<>();
            list.addAll(from.getTransactions());
            list.add(transaction1);
            from.setTransactions(list);
            toMoney += money;
            fromMoney -= money;
            to.setInventory(String.valueOf(toMoney));
            from.setInventory(String.valueOf(fromMoney));
            dbHelper = new DBHelper();
            dbHelper.updateAccount(to);
            dbHelper.updateAccount(from);
        } else {
           Transaction transaction1= creatTransaction(to, from, transaction, money, false);
            ArrayList<Transaction> list = new ArrayList<>();
            list.addAll(from.getTransactions());
            list.add(transaction1);
            from.setTransactions(list);
            alert("موجودی کافی نمیباشد", lblAlertCardToCard, "WHITE");

        }
    }

    private Transaction creatTransaction(Account to, Account from, Transaction transaction, long money, boolean b) {
        transaction.setTypeOfTransaction("انتقال وجه");
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setFinished(b);
        transaction.setSerialOfTransaction(new TransactionSerialProducer().serialProducer());
        transaction.setCostOfTransaction(String.valueOf(money));
        transaction.setDateOfTransaction(new Date());

        dbHelper = new DBHelper();
        dbHelper.insertTransaction(transaction);
        return transaction;
    }


    public void testSearchDestination() {
        DestinationCardNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    btnSearchDestinationCard.setDisable(false);

            }
        });

    }

    public void testCost() {
        txtMoneyCardToCard.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    btnSendUniqueCodeCardToCard.setDisable(false);
                    btnConfirmCardToCard.setDisable(false);

            }
        });

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

    public void initialize(URL location, ResourceBundle resources) {

        DestinationCardNumber.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(16));
        txtMoneyCardToCard.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(20));
        txtSecondPass.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));
        txtCardToCardUniquePass.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(6));

    }


}
