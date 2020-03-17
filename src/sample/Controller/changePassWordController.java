package sample.Controller;

import DataStructure.Account;
import Extras.DBHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

public class changePassWordController {
    public JFXTextField txtRecentPass;
    public JFXTextField txtNewPass;
    public JFXButton btnSubmit;
    loginPageController loginPage = new loginPageController();
    DBHelper dbHelper = new DBHelper();
    Account account = dbHelper.readAccount(loginPage.txtUserLogin.getText());

    public boolean change(){
        boolean changed = false ;

        if (txtRecentPass.getText().equals(account.getAccountPassword())){
            account.setAccountPassword(txtNewPass.getText());
            changed=true;
        }


       return changed;
    }

}
