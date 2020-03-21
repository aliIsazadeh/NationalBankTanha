package sample.Controller;

import DataStructure.Account;
import Extras.NumToText;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationPageBill  implements Initializable {

    public TextField txtBillCost;
    public TextField txtInventory;
    public TextField txtBillCostAlphabet;
    public TextField txtInventoryAlphabet;
    public Button btnClose;
    static String transferMoney;


    public void close(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();

    }

    public  void   ReceivingAmountBill(String st){

        this.transferMoney = st;

    }




    public void initialize(URL location, ResourceBundle resources) {

        loginPageController loginPage = new loginPageController();
        Account account = loginPage.getAccount();

        NumToText numToText = new NumToText(Long.parseLong(account.getInventory()));

        txtInventory.setText(account.getInventory());
        txtInventoryAlphabet.setText(numToText.getText()+ " " + "تومان");

        numToText = new NumToText(Long.parseLong(transferMoney));
        txtBillCost.setText(transferMoney);
        txtBillCostAlphabet.setText(numToText.getText()+ " " + "تومان");


    }

    }
