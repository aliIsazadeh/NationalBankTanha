package sample.Controller;

import DataStructure.Account;
import DataStructure.Bill;
import DataStructure.Transaction;
import Extras.DBHelper;
import Extras.SecondPassProducer;
import Extras.SendSMS;
import Extras.TransactionSerialProducer;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class billController implements Initializable {


    public Button considerBill;
    public JFXTextField txtBillNumber;
    public JFXTextField txtPayNumber;

    public Label billAlertLabel;
    public Button sendUniquePass;
    public Button PayBill;
    public JFXTextField txtBillCost;
    public JFXTextField txtSecendPassForBill;
    public JFXTextField txtUniquePassForBill;

    public Label lblBillCost;
    private String password;

    private DBHelper dbHelper;
    private NotificationPageBill not;


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


    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void sendingUniqueCode() {
        SecondPassProducer secondPassProducer = new SecondPassProducer();
        password = secondPassProducer.secondPass();
        SendSMS sendSMS = new SendSMS(new loginPageController().getAccount().getPerson().getPhoneNumber());
        sendSMS.setMessage(password);
        sendSMS.send();
        alert(" رمز پویا به شماره شما ارسال شد", billAlertLabel, "green");


    }


    public void considerBill() {
        dbHelper = new DBHelper();
        Bill bill = dbHelper.readBill(Long.parseLong(txtBillNumber.getText()), Long.parseLong(txtPayNumber.getText()));

        if (txtBillNumber.getText().equals("") || txtPayNumber.getText().equals("")) {
            alert("لطفا فیلد هارا پرکنید", billAlertLabel, "red");
        } else  if (bill.getPaymentCode() == 0 || bill.getBillingId() == 0) {
            alert("قبضی با این مشخصات وجود ندارد", billAlertLabel, "red");

        } else if (bill.getPaymentCode() != 0 && bill.getBillingId() != 0){
            System.out.println(bill.getPaymentCode()+"nnnn");
            // Account account = new loginPageController().getAccount();
//            if (String.valueOf(bill.getPaymentCode()) != null || String.valueOf(bill.getBillingId()) != null) {
                if (bill.getCondition().equals("پردخت نشده")) {
                    txtBillCost.setText(bill.getCostOfBill() + "");

                    sendUniquePass.setVisible(true);
                    PayBill.setVisible(true);
                    txtBillCost.setVisible(true);
                    txtSecendPassForBill.setVisible(true);
                    txtUniquePassForBill.setVisible(true);
                    lblBillCost.setVisible(true);
                } else {
                    alert("قبض پرداخت شده است ", billAlertLabel, "red");
                }

            }



    }

    private void doTransaction(Bill bill, Account account) {
        dbHelper = new DBHelper();
        if (txtSecendPassForBill.getText().equals(account.getSecondPassword()) && txtUniquePassForBill.getText().equals(password)) {
            if (bill.getCostOfBill() > Long.parseLong(account.getInventory())) {
                alert("موجودی کافی نیست!", billAlertLabel, "red");
                Transaction transaction = creatTransaction(bill, false);
                ArrayList <Transaction> list = new ArrayList<>();
                list.addAll(account.getTransactions());
                list.add(transaction);
                account.setTransactions(list);
            } else {
                bill.setCondition("پردخت شده");
                long money = Long.parseLong(account.getInventory());
                money -= bill.getCostOfBill();
                account.setInventory(String.valueOf(money));
                dbHelper.updateAccount(account);
                Transaction transaction = creatTransaction(bill, true);
                ArrayList <Transaction> list = new ArrayList<>();
                list.addAll(account.getTransactions());
                list.add(transaction);
                account.setTransactions(list);
                dbHelper.updateBill(bill);

            }
        } else
            alert("رمز یکبارمصرف یا رمز دوم اشتباه است", billAlertLabel, "red");
    }

    private Transaction creatTransaction(Bill bill, boolean b) {
        // private TransactionSerialProducer transactionSerialProducer = new TransactionSerialProducer();
        Transaction transaction = new Transaction();
        transaction.setDateOfTransaction(new Date());
        transaction.setCostOfTransaction(String.valueOf(bill.getCostOfBill()));
        transaction.setSerialOfTransaction(new TransactionSerialProducer().serialProducer());
        transaction.setFinished(b);
        transaction.setTypeOfTransaction("پرداخت قبض");
        transaction.setBillingId(bill.getBillingId());
        transaction.setPaymentCode(bill.getPaymentCode());
        dbHelper = new DBHelper();
        dbHelper.insertTransaction(transaction);
        return transaction;
    }

    public void test() {
        txtBillNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!txtPayNumber.equals(""))
                    considerBill.setDisable(false);


            }
        });

    }

    public void test2() {
        txtPayNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!txtBillNumber.equals(""))
                    considerBill.setDisable(false);


            }
        });

    }


    public void initialize(URL location, ResourceBundle resources) {


        txtBillNumber.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(15));
        txtPayNumber.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(15));
        txtBillCost.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(15));
        txtSecendPassForBill.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));
        txtUniquePassForBill.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(6));

    }

    public void doTransaction(MouseEvent mouseEvent) {

        if (txtBillNumber.getText().equals("") || txtPayNumber.getText().equals("") || txtSecendPassForBill.getText().equals("") || txtUniquePassForBill.getText().equals("")) {
            alert("لطفا فیلد هارا پرکنید", billAlertLabel, "red");
        }


        Account account = new loginPageController().getAccount();
        Bill bill = dbHelper.readBill(Long.parseLong(txtBillNumber.getText()), Long.parseLong(txtPayNumber.getText()));

        if (txtSecendPassForBill.getText().equals("")) {
            alert("رمز دوم با پویای خود را وارد کنید", billAlertLabel, "red");

        } else if (!txtSecendPassForBill.getText().equals(account.getSecondPassword())) {
            alert("رمز دوم اشتباه است", billAlertLabel, "red");

        } else {
            doTransaction(bill, account);

            not = new NotificationPageBill();
            not.ReceivingAmountBill(txtBillCost.getText());

            Parent root;
            try {
                Stage stage = (Stage) PayBill.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/notificationPageBill.fxml"));
                root = loader.load();
                stage = new Stage();
                Stage finalStage = stage;
                finalStage.setResizable(false);
                finalStage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root, 451, 360));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            alert("قبض شما با موفقیت پرداخت شد", billAlertLabel, "green");

            txtPayNumber.setText("");
            txtBillNumber.setText("");
            txtUniquePassForBill.setText("");
            txtSecendPassForBill.setText("");
            txtBillCost.setText("");
        }

    }
}
