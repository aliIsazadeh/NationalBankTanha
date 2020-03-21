package sample.Controller;

import DataStructure.Account;
import Extras.DBHelper;
import Extras.NumToText;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class notificationAddMoneyController implements Initializable {


    public Button btnClose;
    public TextField txtAddMoneyNum;
    public TextField txtAddMoneyInventoryNum;
    public TextField txtAlphabetAddMoney;
    public TextField txtAlphabetInventory;

    private String transferMoney;

    public void close(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();

    }

    public  void   ReceivingAmountAddMoney(String st){

        transferMoney = st;

    }



    public void initialize(URL location, ResourceBundle resources) {

        DBHelper dbHelper = new DBHelper();
        loginPageController loginPage = new loginPageController();
        Account account = dbHelper.readAccount(loginPage.getAccount().getUserName());

        NumToText numToText = new NumToText(Long.parseLong(account.getInventory()));
        NumToText numToText1 = new NumToText(Long.parseLong(transferMoney));


        txtAddMoneyInventoryNum.setText(account.getInventory());
        txtAlphabetInventory.setText(numToText.getText() + " " + "تومان");

        txtAddMoneyNum.setText(transferMoney);
        txtAlphabetAddMoney.setText(numToText1.getText() + " " +"تومان" );






    }

}
