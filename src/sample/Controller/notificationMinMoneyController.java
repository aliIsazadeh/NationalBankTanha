package sample.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class notificationMinMoneyController implements Initializable {


    public Button btnClose;

    public void close(){

        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();



    }




    public void initialize(URL location, ResourceBundle resources) {


    }
}
