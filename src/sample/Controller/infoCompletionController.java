package sample.Controller;

import DataStructure.Person;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    public JFXTextField getTxtNationalCode() {
        return txtNationalCode;
    }

    public void setTxtNationalCode(JFXTextField txtNationalCode) {
        this.txtNationalCode = txtNationalCode;
    }

    public JFXTextField getTxtFatherName() {
        return txtFatherName;
    }

    public void setTxtFatherName(JFXTextField txtFatherName) {
        this.txtFatherName = txtFatherName;
    }

    public DatePicker getTimePickerBornTime() {
        return timePickerBornTime;
    }

    public void setTimePickerBornTime(DatePicker timePickerBornTime) {
        this.timePickerBornTime = timePickerBornTime;
    }

    public JFXTextField getTxtBornPlace() {
        return txtBornPlace;
    }

    public void setTxtBornPlace(JFXTextField txtBornPlace) {
        this.txtBornPlace = txtBornPlace;
    }

    public JFXTextField getTxtJob() {
        return txtJob;
    }

    public void setTxtJob(JFXTextField txtJob) {
        this.txtJob = txtJob;
    }

    public JFXTextField getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(JFXTextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    public JFXTextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public void setTxtPhoneNumber(JFXTextField txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    public CheckBox getCheckSendMessage() {
        return checkSendMessage;
    }

    public void setCheckSendMessage(CheckBox checkSendMessage) {
        this.checkSendMessage = checkSendMessage;
    }

    public JFXComboBox getComboMarriage() {
        return comboMarriage;
    }

    public void setComboMarriage(JFXComboBox comboMarriage) {
        this.comboMarriage = comboMarriage;
    }

    public JFXComboBox getComboAccount() {
        return comboAccount;
    }

    public void setComboAccount(JFXComboBox comboAccount) {
        this.comboAccount = comboAccount;
    }

    public JFXComboBox getComboGender() {
        return comboGender;
    }

    public void setComboGender(JFXComboBox comboGender) {
        this.comboGender = comboGender;
    }

    public JFXButton getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(JFXButton confirmButton) {
        this.confirmButton = confirmButton;
    }

    public AnchorPane getInfoAnchorPane() {
        return infoAnchorPane;
    }

    public void setInfoAnchorPane(AnchorPane infoAnchorPane) {
        this.infoAnchorPane = infoAnchorPane;
    }

//    public Person getPerson() {
//        return person;
//    }
//
//    public void setPerson(Person person) {
//        this.person = person;
//    }







//    public  void recordInfos(){
//
//        private boolean StringFinder(JFXTextField txtField) {
//            boolean exit = true;
//
//            for (int i = 0; i < txtField.getText().length(); i++) {
//                int c = txtField.getText().charAt(i);
//                if (!(c >= 48 && c <= 57)) {
//                    exit = false;
//                }
//            }
//            return exit;
//        }
//
//
//
//
//
//
//
//    }











    public void initialize(URL location, ResourceBundle resources) {





    }

}
