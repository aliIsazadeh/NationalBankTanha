package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import Extras.DBHelper;
import Extras.SecondPassProducer;
import Extras.TransactionSerialProducer;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
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


    public void confirmAddingMoney() {

        if (txtAddMoney.getText().equals("") || txtAddMoneySecendPass.getText().equals("")) {
            alert("لطفا فیلد هارا پر کنید", lblAlertAddMoney, "red");

        }

       else {

            boolean flag = addMoney();

            if (flag) {

                alert("عملیات با موفقیت انجام شد", lblAlertAddMoney, "green");


//                Parent root;
//                try {
//                    Stage stage = (Stage) confirmAddMoney.getScene().getWindow();
//
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/notificationPageAddMoney.fxml"));
//                    root = loader.load();
//                    stage = new Stage();
//                    Stage finalStage = stage;
//                    finalStage.setResizable(false);
//                    finalStage.initStyle(StageStyle.TRANSPARENT);
//                    stage.setScene(new Scene(root, 361, 329));
//                    stage.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


            } else {

                alert("تراکنش شما ناموفق بود!! ", lblAlertAddMoney, "red");

            }

        }
    }


    public void sendingUniqueCode() {

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
        loginPageController loginPage = new loginPageController();
        TransactionSerialProducer transactionSerialProducer = new TransactionSerialProducer();
        DBHelper dbHelper = new DBHelper();
        Account account = loginPage.getAccount();
        Transaction transaction = new Transaction();
        Date today = new Date();
        SecondPassProducer secondPassProducer = new SecondPassProducer();
        String pass = secondPassProducer.secondPass();

        transaction.setCostOfTransaction(txtAddMoney.getText());
        if (pass.equals(txtAddMoneySecendPass)) {
            transaction.setFinished(true);
            transaction.setDateOfTransaction( today);
            transaction.setSerialOfTransaction(transactionSerialProducer.serialProducer());
            transaction.setTypeOfTransaction("افزودن موجودی");
            dbHelper.insertTransaction(transaction);
            account.setInventory(String.valueOf(Integer.parseInt(account.getInventory()) + Integer.parseInt(txtAddMoney.getText())));
            dbHelper.updateAccount(account);
        } else {
            transaction.setFinished(false);
            transaction.setDateOfTransaction(today);
            transaction.setSerialOfTransaction(transactionSerialProducer.serialProducer());
            transaction.setTypeOfTransaction("افزودن موجودی");
            dbHelper.insertTransaction(transaction);



        }


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
        txtAddMoneyUniquePass.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));
        txtAddMoney.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(20));


    }
}
