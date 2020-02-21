package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {


    public AnchorPane mainAnchorPane;
    public Label lblSuccess;
    public JFXTextField txtNationalCode;
    public JFXTextField txtFatherName;
    public DatePicker timePickerBornTime;
    public JFXTextField txtBornPlace;
    public JFXTextField txtJob;
    public JFXTextField txtAddress;
    public JFXTextField txtPhoneNumber;
    public CheckBox checkSendMessage;
    //
    public JFXComboBox comboMarriage;
    public JFXComboBox txtAccount;
    public JFXComboBox txtGender;

    //
    public  JFXButton btnPersonalInfo;
    public  JFXButton btnAddMoney;
    public  JFXButton btnMinMoney;
    public  JFXButton btnCardToCard;
    public  JFXButton btnCash;
    public  JFXButton btnPassWord;
    public  JFXButton btnHistory;
    public  JFXButton btnOther;
    public  ImageView faceImage;
    public AnchorPane infoAnchorPane;

    public JFXButton btnConfirmInfos;

    public  void recordInfos(){

        btnPersonalInfo.setDisable(false);
        btnAddMoney.setDisable(false);
        btnMinMoney.setDisable(false);
        btnCardToCard.setDisable(false);
        btnCash.setDisable(false);
        btnPassWord.setDisable(false);
        btnHistory.setDisable(false);
        btnOther.setDisable(false);
        faceImage.setVisible(true);
        btnConfirmInfos.setVisible(false);
        lblSuccess.setVisible(true);

    }


    public void profileLoad() throws IOException {


        mainAnchorPane.getChildren().removeAll(lblSuccess);


        try {
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/personalInfos.fxml"));
            mainAnchorPane.getChildren().addAll(root);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }

    }


    public void addMoneyLoad(){

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/addMoney.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }


    }


    public  void minMoneyLoad(){

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/minMoney.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }



    }

    public  void remainLoad(){

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/reamainMoney.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }



    }

    public  void cardToCardLoad(){

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/cardToCard.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }



    }

    public  void HistoryLoad(){
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/history.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }




    }

    public  void ChangePassLoad(){

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/changePassWord.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }



    }

    public void anotherOptions(){

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/anotherOptions.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }



    }





    public void initialize(URL location, ResourceBundle resources) {

        try {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/infoCompletion.fxml"));
        mainAnchorPane.getChildren().addAll(anchorPane);
    }
        catch (IOException ex){
        System.out.println("oh crap!!!");
    }




      //  comboMarriage.getItems().addAll("مجرد","متاهل");
//        comboMarriage.getItems().addAll();
//
//        txtAccount.getItems().addAll("جاری");
//        txtAccount.getItems().addAll("قرض الحسنه");
//        txtAccount.getItems().addAll("پسنداز");



    }
}
