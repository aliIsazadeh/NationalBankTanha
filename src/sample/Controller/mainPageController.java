package sample.Controller;

import DataStructure.Account;
import DataStructure.Person;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {

    public JFXTextField txtAccountType;
    private Person person = new Person();



    public AnchorPane mainAnchorPane;
    public JFXTextField txtNationalCode;
    public JFXTextField txtFatherName;
    public DatePicker timePickerBornTime;
    public JFXTextField txtBornPlace;
    public JFXTextField txtJob;
    public JFXTextField txtAddress;
    public JFXTextField txtPhoneNumber;
    public CheckBox checkSendMessage;
    public AnchorPane infoAnchorPane;
    //
    public JFXComboBox comboMarriage;
    public JFXComboBox comboGender;
    public JFXComboBox comboAccount;

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

    public Label lblSuccess;
    public JFXButton btnConfirmInfos;


    public AnchorPane getMainAnchorPane() {
        return mainAnchorPane;
    }

    public void setMainAnchorPane(AnchorPane mainAnchorPane) {
        this.mainAnchorPane = mainAnchorPane;
    }

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

    public AnchorPane getInfoAnchorPane() {
        return infoAnchorPane;
    }

    public void setInfoAnchorPane(AnchorPane infoAnchorPane) {
        this.infoAnchorPane = infoAnchorPane;
    }

    public JFXComboBox getComboMarriage() {
        return comboMarriage;
    }

    public void setComboMarriage(JFXComboBox comboMarriage) {
        this.comboMarriage = comboMarriage;
    }

    public JFXComboBox getComboGender() {
        return comboGender;
    }

    public void setComboGender(JFXComboBox comboGender) {
        this.comboGender = comboGender;
    }

    public JFXComboBox getComboAccount() {
        return comboAccount;
    }

    public void setComboAccount(JFXComboBox comboAccount) {
        this.comboAccount = comboAccount;
    }

    public JFXButton getBtnPersonalInfo() {
        return btnPersonalInfo;
    }

    public void setBtnPersonalInfo(JFXButton btnPersonalInfo) {
        this.btnPersonalInfo = btnPersonalInfo;
    }

    public JFXButton getBtnAddMoney() {
        return btnAddMoney;
    }

    public void setBtnAddMoney(JFXButton btnAddMoney) {
        this.btnAddMoney = btnAddMoney;
    }

    public JFXButton getBtnMinMoney() {
        return btnMinMoney;
    }

    public void setBtnMinMoney(JFXButton btnMinMoney) {
        this.btnMinMoney = btnMinMoney;
    }

    public JFXButton getBtnCardToCard() {
        return btnCardToCard;
    }

    public void setBtnCardToCard(JFXButton btnCardToCard) {
        this.btnCardToCard = btnCardToCard;
    }

    public JFXButton getBtnCash() {
        return btnCash;
    }

    public void setBtnCash(JFXButton btnCash) {
        this.btnCash = btnCash;
    }

    public JFXButton getBtnPassWord() {
        return btnPassWord;
    }

    public void setBtnPassWord(JFXButton btnPassWord) {
        this.btnPassWord = btnPassWord;
    }

    public JFXButton getBtnHistory() {
        return btnHistory;
    }

    public void setBtnHistory(JFXButton btnHistory) {
        this.btnHistory = btnHistory;
    }

    public JFXButton getBtnOther() {
        return btnOther;
    }

    public void setBtnOther(JFXButton btnOther) {
        this.btnOther = btnOther;
    }

    public ImageView getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(ImageView faceImage) {
        this.faceImage = faceImage;
    }

    public Label getLblSuccess() {
        return lblSuccess;
    }

    public void setLblSuccess(Label lblSuccess) {
        this.lblSuccess = lblSuccess;
    }

    public JFXButton getBtnConfirmInfos() {
        return btnConfirmInfos;
    }

    public void setBtnConfirmInfos(JFXButton btnConfirmInfos) {
        this.btnConfirmInfos = btnConfirmInfos;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }










    private int findComboIndex(JFXComboBox Box) {
        return Box.getSelectionModel().getSelectedIndex();
    }




    private void addVariable(){
        person.setNationalNumber(Integer.parseInt(txtNationalCode.getText()));
        person.setFatherName(txtFatherName.getText());
        LocalDate localData = timePickerBornTime.getValue();
        person.setBornTime(localData.getYear()+","+localData.getMonth()+","+localData.getDayOfMonth());
        person.setBornPlace(txtBornPlace.getText());
        person.setJob(txtJob.getText());
        person.setAddress(txtAddress.getText());

        if (findComboIndex(comboMarriage)==0){
            person.setMarriage(false); }
        if (findComboIndex(comboMarriage)==1){
            person.setMarriage(true); }

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


    public void activePhoneText(){

        boolean flag = checkSendMessage.isSelected();

        if(flag)
        txtPhoneNumber.setVisible(true);

        if(!flag)
        txtPhoneNumber.setVisible(false);


    }


    public  void recordInfos(){

        addVariable();

        Account account =new Account();
        btnPersonalInfo.setDisable(false);
        btnAddMoney.setDisable(false);
        btnMinMoney.setDisable(false);
        btnCardToCard.setDisable(false);
        btnCash.setDisable(false);
        btnPassWord.setDisable(false);
        btnHistory.setDisable(false);
        btnOther.setDisable(false);

        btnConfirmInfos.setVisible(false);
        lblSuccess.setVisible(true);

        if(findComboIndex(comboGender)==0) {
            Image imageMale = new Image("./sample/bankPics/1.png");
            faceImage.setImage(imageMale);
        }

        else if(findComboIndex(comboGender)==1){
            Image imageFemale = new Image("./sample/bankPics/MasterFM.png");
            faceImage.setImage(imageFemale);

        }



        if(findComboIndex(comboAccount)==0){
            txtAccountType.setText("جاری");
            account.setAccountType("جاری");
        }


       else if(findComboIndex(comboAccount)==1){
            txtAccountType.setText("قرضا لحسنه");
            account.setAccountType("قرضا لحسنه");
        }


       else if(findComboIndex(comboAccount)==2){
            txtAccountType.setText("پسنداز");
            account.setAccountType("پسنداز");
        }

        infoAnchorPane.setVisible(false);


    }


    public void profileLoad() throws IOException {


       lblSuccess.setVisible(false);


        try {
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/personalInfos.fxml"));
            mainAnchorPane.getChildren().addAll(root);
        }
        catch (IOException ex){
            System.out.println("oh crap!!!");
        }

    }


    public void addMoneyLoad(){

        lblSuccess.setVisible(false);
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


    public void exit() {

        Alert alert = new Alert(Alert.AlertType.WARNING, "آیا میخواهید خارج شوید؟ ", ButtonType.YES, ButtonType.NO);


        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.YES)
                System.exit(0);
    }




    public void initialize(URL location, ResourceBundle resources) {


        String[] items1 = {"مجرد","متاهل"};
        comboMarriage.getItems().addAll(items1);

        String[] items2 = {"جاری","قرض احسنه","پسنداز"};
        comboAccount.getItems().addAll(items2);

        String[] items3 = {"مرد","زن"};
        comboGender.getItems().addAll(items3);


//        try {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/infoCompletion.fxml"));
//        mainAnchorPane.getChildren().addAll(anchorPane);
//    }
//        catch (IOException ex){
//        System.out.println("oh crap!!!");
//    }




      //  comboMarriage.getItems().addAll("مجرد","متاهل");
//        comboMarriage.getItems().addAll();
//
//        txtAccount.getItems().addAll("جاری");
//        txtAccount.getItems().addAll("قرض الحسنه");
//        txtAccount.getItems().addAll("پسنداز");



    }
}
