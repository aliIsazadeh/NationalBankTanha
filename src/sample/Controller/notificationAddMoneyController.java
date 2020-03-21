package sample.Controller;

import DataStructure.Account;
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

    static String transferMoney;

    public void close(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();

    }

    public  void   ReceivingAmountAddMoney(String st){

       this.transferMoney = st;

    }



    public void initialize(URL location, ResourceBundle resources) {
        loginPageController loginPage = new loginPageController();
        Account account = loginPage.getAccount();

        NumToText numToText = new NumToText(Long.parseLong(account.getInventory()));


        txtAddMoneyInventoryNum.setText(account.getInventory());
        txtAlphabetInventory.setText(numToText.getText() + " " + "تومان");

        numToText = new NumToText(Long.parseLong(transferMoney));
        txtAddMoneyNum.setText(transferMoney);
        txtAlphabetAddMoney.setText(numToText.getText() + " " +"تومان" );
        System.out.println("initialize");





    }

}
