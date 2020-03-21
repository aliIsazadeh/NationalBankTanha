package sample.Controller;

import DataStructure.Account;
import DataStructure.Person;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class personalInfoController implements Initializable {
    public JFXTextField txtShowName;
    public JFXTextField txtShowFamily;
    public JFXTextField txtShowNationalCode;
    public JFXTextField txtShowFatherName;
    public JFXTextField txtShowAccountType;
    public JFXTextField txtShowGender;
    public JFXTextField txtShowAddress;
    public JFXTextField txtShowPhoneNumber;

    private void setInfo() {

        loginPageController loginPage = new loginPageController();
        Account account = loginPage.getAccount();
        Person person = account.getPerson();
        System.out.println(person.getPhoneNumber());
        txtShowNationalCode.setText(String.valueOf(person.getNationalNumber()));
        txtShowName.setText(person.getName());
        txtShowFamily.setText(person.getLastName());
        txtShowPhoneNumber.setText(person.getPhoneNumber());
        txtShowFatherName.setText(person.getFatherName());
        txtShowAccountType.setText(account.getAccountType());
        txtShowGender.setText(person.getGender());
        txtShowAddress.setText(person.getAddress());
        txtShowPhoneNumber.setText(String.valueOf(person.getPhoneNumber()));

    }


    public void initialize(URL location, ResourceBundle resources) {
        setInfo();
    }




}
