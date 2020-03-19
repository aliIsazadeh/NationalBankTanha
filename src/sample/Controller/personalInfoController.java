package sample.Controller;

import DataStructure.Account;
import DataStructure.Person;
import Extras.DBHelper;
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
    private loginPageController loginPage = new loginPageController();
    private DBHelper dbHelper = new DBHelper();
    private Account account = dbHelper.readAccount(loginPage.getAccount().getUserName());
    private Person person = account.getPerson();

    public void setInfo() {




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
