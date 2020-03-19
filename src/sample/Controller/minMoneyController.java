package sample.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class minMoneyController implements Initializable {


    public Label lblAlertMinMoney;
    public JFXButton confirmMinMoney;
    public TextField txtMinMoney;

    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void confirmMinMoney() {


        if (txtMinMoney.getText().equals("")) {

            alert("لطفا مبلغ برداشتی را وارد کنید", lblAlertMinMoney, "red");

        } else if (Long.parseLong(txtMinMoney.getText()) > 200000) {

            alert("توجه : سقف وجه برداشتی تا 200هزار تومان در یک روز می باشد.", lblAlertMinMoney, "red");

        }

        //TODO setting information in notification page that I wrote it as  comment

//        Parent root;
//        try {
//            Stage stage = (Stage) confirmMinMoney.getScene().getWindow();
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/notificationMinMoney.fxml"));
//            root = loader.load();
//            stage = new Stage();
//            Stage finalStage = stage;
//            finalStage.setResizable(false);
//            finalStage.initStyle(StageStyle.TRANSPARENT);
//            stage.setScene(new Scene(root, 361, 329));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }


    public void initialize(URL location, ResourceBundle resources) {


    }


}
