package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import Extras.DBHelper;
import Extras.TransactionSerialProducer;
import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class addMoneyController implements Initializable {


    public TextField txtAddMoney;
    public JFXButton confirmAddMoney;
    public Label lblAlertAddMoney;
    public TextField txtAddMoneySecendPass;

    private notificationAddMoneyController not;


    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void confirmAddingMoney() {
        if (txtAddMoney.getText().equals("") || txtAddMoneySecendPass.getText().equals("")) {
            alert("لطفا فیلد هارا پر کنید", lblAlertAddMoney, "red");

        } else {

            boolean flag = addMoney();

            if (flag) {

                alert("عملیات با موفقیت انجام شد", lblAlertAddMoney, "green");
                not = new notificationAddMoneyController();
                not.ReceivingAmountAddMoney(txtAddMoney.getText());

                Parent root;
                try {
                    Stage stage = (Stage) confirmAddMoney.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/notificationPageAddMoney.fxml"));
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


            } else {

                alert("تراکنش شما ناموفق بود!! ", lblAlertAddMoney, "red");

            }

        }
    }


//    public void sendingUniqueCode() {
//
//        txtAddMoneyUniquePass.setVisible(true);
//
//        alert(" رمز پویا به شماره شما ارسال شد", lblAlertAddMoney, "green");
//
//
//    }

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


    private boolean addMoney() {
        System.out.println("start");
        loginPageController loginPage = new loginPageController();
        DBHelper dbHelper = new DBHelper();
        Account account = loginPage.getAccount();
        Transaction transaction = new Transaction();
        Date today = new Date();


        System.out.println("start2");
        if (account.getSecondPassword().equals(txtAddMoneySecendPass.getText())) {
            System.out.println(true);
            transaction.setCostOfTransaction(txtAddMoney.getText());
            transaction.setFinished(true);
            transaction.setDateOfTransaction(today);
            transaction.setSerialOfTransaction(new TransactionSerialProducer().serialProducer());
            transaction.setTypeOfTransaction("افزودن موجودی");
            transaction.setFrom(account);
            dbHelper.insertTransaction(transaction);
            account.setInventory(String.valueOf(Integer.parseInt(account.getInventory()) + Integer.parseInt(txtAddMoney.getText())));
            System.out.println(account.getPerson().getAccount().getAccountNumber());

            dbHelper.updateAccount(account);
            alert("عملیات با موفقیت انجام شد", lblAlertAddMoney, "green");

        } else {
            alert("رمز دوم اشتباه است", lblAlertAddMoney, "red");
//            transaction.setFinished(false);
//            System.out.println("finished"+false);
//            transaction.setDateOfTransaction(today);
//            System.out.println(today);
//            transaction.setSerialOfTransaction(new TransactionSerialProducer().serialProducer());
//            System.out.println(transaction.getSerialOfTransaction());
//            transaction.setTypeOfTransaction("افزودن موجودی");
//            System.out.println(transaction.getTypeOfTransaction());
//            dbHelper.insertTransaction(transaction);
//            System.out.println("inserted");
        }

        System.out.println("finish");
        System.out.println(transaction.isFinished());
        ArrayList<Transaction >list = new ArrayList<>();
       // list.addAll(account.getTransactions());
        list.add(transaction);
        account.setTransactions(list);
        return transaction.isFinished();
    }

    public void testCost() {
        txtAddMoney.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                confirmAddMoney.setDisable(false);


            }
        });

    }


    public void initialize(URL location, ResourceBundle resources) {

        txtAddMoneySecendPass.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));
//        txtAddMoneyUniquePass.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));
        txtAddMoney.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(20));


    }
}
