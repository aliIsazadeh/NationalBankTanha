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

public class notificationCardToCard  implements Initializable {


    public Button btnClose;
    public TextField txtTransferMoneyCardToCardNum;
    public TextField txtInventoryCardToCardNum;
    public TextField txtAlphabetMoneyCardToCard;
    public TextField txtAlphabetInventoryCardToCard;

    private String transferMoney;


    public void close(){
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();

        }

    public  void   ReceivingAmountCardToCard(String st){

         transferMoney = st;

    }


    public void initialize(URL location, ResourceBundle resources) {

        DBHelper dbHelper = new DBHelper();
        loginPageController loginPage = new loginPageController();
        Account account = dbHelper.readAccount(loginPage.getAccount().getUserName());

        NumToText numToText = new NumToText(Long.parseLong(account.getInventory()));

        NumToText numToText1 = new NumToText(Long.parseLong(transferMoney));


        txtInventoryCardToCardNum.setText(account.getInventory());
        txtAlphabetInventoryCardToCard.setText(numToText.getText() + " " + "تومان");

        txtTransferMoneyCardToCardNum.setText(transferMoney);
        txtAlphabetMoneyCardToCard.setText(numToText1.getText() + " " + "تومان");





    }
}
