package sample.Controller;

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


    public void close(){
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();

        }




    public void initialize(URL location, ResourceBundle resources) {


    }
}
