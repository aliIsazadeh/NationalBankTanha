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
    //dbHelper.readAccount(loginPage.txtUserLogin.getText());
    private Account account = loginPage.getAccount();
    private SecondPassProducer secondPassProducer = new SecondPassProducer();
    private TransactionSerialProducer transactionSerialProducer = new TransactionSerialProducer();
    Date date = new Date();

    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void sendingUniqueCode() {



        alert(" رمز پویا به شماره شما ارسال شد",lblAlertMinMoney,"green");


    }


    public void confirmMinMoney() {


        if (txtMinMoney.getText().equals("") ) {

            alert("لطفا مبلغ برداشتی را وارد کنید", lblAlertMinMoney, "red");

        } else if (Long.parseLong(txtMinMoney.getText()) > 200000) {

            alert("توجه : سقف وجه برداشتی تا 200هزار تومان در یک روز می باشد.", lblAlertMinMoney, "red");

        }
        else if(txtSecendPassWordMinMoney.equals("")){

            alert("لطفا رمز دوم را وارد کنید", lblAlertMinMoney, "red");
        }

        else {
            boolean flag = minMoney();
            if (flag) {
//                Parent root;
//                try {
//                    Stage stage = (Stage) confirmMinMoney.getScene().getWindow();
//
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/notificationMinMoney.fxml"));
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

                alert(" تراکنش با موفقیت انجام شد", lblAlertMinMoney, "green");
            }



            if (!flag) {

                alert(" تراکنش ناموفق بود", lblAlertMinMoney, "red");

            }


        }





    }

    boolean minMoney() {
        if ((Long.parseLong(txtMinMoney.getText()) < Long.parseLong(account.getInventory())) && txtSecendPassWordMinMoney.getText().equals(secondPassProducer.secondPass())) {
            account.setInventory(String.valueOf(Long.parseLong(account.getInventory()) - Long.parseLong(txtMinMoney.getText())));
            dbHelper.updateAccount(account);
            transaction.setFinished(true);
            transaction.setTypeOfTransaction("برداشت وجه");
            transaction.setSerialOfTransaction(transactionSerialProducer.serialProducer());
            transaction.setDateOfTransaction(date);
            transaction.setCostOfTransaction(txtMinMoney.getText());
            transaction.setIDnumber(account.getAccountNumber());
        } else if (!(txtSecendPassWordMinMoney.getText().equals(secondPassProducer.secondPass()))) {
            transaction.setFinished(false);
            transaction.setTypeOfTransaction("برداشت وجه");
            transaction.setSerialOfTransaction(transactionSerialProducer.serialProducer());
            transaction.setDateOfTransaction(date);
            transaction.setCostOfTransaction(txtMinMoney.getText());
            transaction.setIDnumber(account.getAccountNumber());
        } else if ((txtSecendPassWordMinMoney.getText().equals(secondPassProducer.secondPass())) && !(Long.parseLong(txtMinMoney.getText()) < Long.parseLong(account.getInventory()))) {
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

    public void testCost() {
        txtMinMoney.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                confirmMinMoney.setDisable(false);


            }
        });

    }




    public void initialize(URL location, ResourceBundle resources) {

        txtMinMoney.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(6));
        txtSecendPassWordMinMoney.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));
        txtUniquePassMinMoney.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(6));

    }


}
