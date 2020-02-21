package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class infoCompletionController implements Initializable {

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
    public JFXComboBox comboAccount;
    public JFXComboBox comboGender;
    //
    public JFXButton confirmButton;
    public AnchorPane infoAnchorPane;








    public  void recordInfos(){

        //mainPageController.btnPersonalInfo.setDisable(true);
      //  mainPageController.btnAddMoney.setDisable(true);
//        mainPageController.btnMinMoney.setDisable(false);
//        mainPageController.btnCardToCard.setDisable(false);
//        mainPageController.btnCash.setDisable(false);
//        mainPageController.btnPassWord.setDisable(false);
//        mainPageController.btnHistory.setDisable(false);
//        mainPageController.btnOther.setDisable(false);
//        mainPageController.faceImage.setDisable(false);


    }





    public void initialize(URL location, ResourceBundle resources) {

        String[] items1 = {"مجرد","متاهل"};
        comboMarriage.getItems().addAll(items1);

        String[] items2 = {"جاری","قرض احسنه","پسنداز"};
        comboAccount.getItems().addAll(items2);

        String[] items3 = {"مرد","زن"};
        comboGender.getItems().addAll(items3);




    }

}
