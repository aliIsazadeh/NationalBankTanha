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


    public void test() {

        Person person = new Person();
        Account account = new Account();

        txtShowNationalCode.setText(String.valueOf(person.getNationalNumber()));
        //  txtShowName.setText(person.getName());
        //  txtShowName.setText(person.getLastName());
        txtShowFatherName.setText(person.getFatherName());
        txtShowAccountType.setText(account.getAccountType());
        txtShowGender.setText(person.getGender());
        txtShowAddress.setText(person.getAddress());
        txtShowPhoneNumber.setText(String.valueOf(person.getPhoneNumber()));

    }


    public void initialize(URL location, ResourceBundle resources) {

        test();
    }


    // txtShowName.setText("ffff");
    //txtShowFamily.setText(person.getLastName());
//        txtShowNationalCode.setText(person.getNationalNumber());
//        txtShowFatherName.setText(person.getFatherName());
//        txtShowAccountType.setText(account.getAccountType());
//        txtShowGender.setText(person.getGender());
//        txtShowAddress.setText(person.getAddress());
//        txtShowPhoneNumber.setText(person.getPhoneNumber());


}
