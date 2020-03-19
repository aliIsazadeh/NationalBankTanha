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
    private loginPageController loginPage ;
    private Account account ;
    private Person person ;

    public void setInfo() {

        loginPage = new loginPageController();
        account = loginPage.getAccount();
        person = account.getPerson();

        txtShowNationalCode.setText(String.valueOf(person.getNationalNumber()));
        txtShowName.setText(person.getName());
        txtShowName.setText(person.getLastName());
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
