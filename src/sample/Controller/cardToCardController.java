package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import Extras.DBHelper;
import Extras.TransactionSerialProducer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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

    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }

    public void sendingUniqueCode() {



        alert(" رمز پویا به شماره شما ارسال شد",lblAlertCardToCard,"green");


    }



    public void searchDestinationCard() {

        long accountNumber = Long.parseLong(DestinationCardNumber.getText());

        dbHelper = new DBHelper();
        Account account = dbHelper.readAccount(accountNumber);
        if (DestinationCardNumber.getText().equals("")) {
            alert("لطفا کارت مورد نظر را وارد کنید", lblAlertCardToCard, "red");
        } else if (account.getAccountNumber() ==null) {
            alert("حسابی با این مشحصات وجود ندارد", lblAlertCardToCard, "red");
        } else {
            txtDescribeDestinationCard.setText("صاحب حساب : "+account.getPerson().getName()+" "+account.getPerson().getLastName());
        }
    }

    public void doTransaction(MouseEvent mouseEvent) {
        Account from = new loginPageController().getAccount();
        long accountNumber = Long.parseLong(DestinationCardNumber.getText());
        dbHelper = new DBHelper();
        Account to = dbHelper.readAccount(accountNumber);
        //TODO بررسی رمز یکبار مصرف
        if (from.getSecondPassword().equals(txtSecondPass.getText())) {
            doTransaction(to, from);

//            Parent root;
//            try {
//                Stage stage = (Stage) btnConfirmCardToCard.getScene().getWindow();
//
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/notificationCardToCard.fxml"));
//                root = loader.load();
//                stage = new Stage();
//                Stage finalStage = stage;
//                finalStage.setResizable(false);
//                finalStage.initStyle(StageStyle.TRANSPARENT);
//                stage.setScene(new Scene(root, 361, 329));
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        } else if (txtSecondPass.getText().equals("")) {
            alert("رمز دوم خود را وارد کنید", lblAlertCardToCard, "red");
        } else if (!txtSecondPass.getText().equals(from.getSecondPassword())) {
            alert("رمز دوم اشتباه است", lblAlertCardToCard, "red");
        }

    }

    private void doTransaction(Account to, Account from) {
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
            alert("موجودی کافی نمیباشد", lblAlertCardToCard, "WHITE");

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
