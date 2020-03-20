package sample.Controller;

import DataStructure.Account;
import Extras.DBHelper;
import Extras.NumToText;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class remainMoneyController implements Initializable {


    public TextField txtRemainMoneyInNumber;
    public TextField txtRemainMoneyInAlphabet;







    public void initialize(URL location, ResourceBundle resources) {


        DBHelper dbHelper = new DBHelper();
        loginPageController loginPage = new loginPageController();
        Account account = dbHelper.readAccount(loginPage.getAccount().getUserName());

        txtRemainMoneyInNumber.setText(account.getInventory());
        NumToText numToText = new NumToText(Long.parseLong(txtRemainMoneyInNumber.getText()));
        txtRemainMoneyInAlphabet.setText(numToText+"");


    }

}
