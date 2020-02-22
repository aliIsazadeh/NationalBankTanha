package sample.Controller;

import DataStructure.Person;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
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


    private Person person = new Person();

    private void addVariable(){
        person.setNationalNumber(Integer.parseInt(txtNationalCode.getText()));
        person.setFatherName(txtFatherName.getText());
        LocalDate localData = timePickerBornTime.getValue();
        person.setBornTime(localData.getYear()+","+localData.getMonth()+","+localData.getDayOfMonth());
        person.setBornPlace(txtBornPlace.getText());
        person.setJob(txtJob.getText());
        person.setAddress(txtAddress.getText());

        if (findComboIndex(comboMarriage)==0){
            person.setGender("مجرد"); }
        if (findComboIndex(comboMarriage)==1){
            person.setGender("متاهل"); }

        if (findComboIndex(comboAccount)==0){
            person.setGender("جاری"); }
        if (findComboIndex(comboAccount)==1){
            person.setGender("قرض الحسنه"); }
        if (findComboIndex(comboAccount)==2){
            person.setGender("پسنداز"); }

        if (findComboIndex(comboMarriage)==0){
            person.setGender("مرد"); }
        if (findComboIndex(comboMarriage)==1){
            person.setGender("زن"); }

    }

    private int findComboIndex(JFXComboBox Box) {
        return Box.getSelectionModel().getSelectedIndex();
    }




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
