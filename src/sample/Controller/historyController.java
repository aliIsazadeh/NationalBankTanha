package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class historyController implements Initializable {


    public TextArea historyTextField;



    public void setHistoryTextField() {

        loginPageController loginPageController = new loginPageController();
        Account account = loginPageController.getAccount();
        ArrayList<Transaction> list = new ArrayList<>();
        list.addAll(account.getTransactions());
        for (int i = 0; i < list.size(); i++) {
            Transaction transaction = list.get(i);
            String transactionInfo = "بانک ملی تنها"
                    + "\n" + transaction.getTypeOfTransaction() + "نوع تراکنش : "
                    + "\n" + transaction.getCostOfTransaction() + "مبلغ : "
                    + "\n";
            if (transaction.getTypeOfTransaction().equals("انتقال وجه")) {
                transactionInfo += transaction.getFrom().getAccountNumber() + "انتقال از کارت : "
                        + "\n" + transaction.getTo().getAccountNumber() + "انتقال به کارت : "
                        + "\n";
            } else if (transaction.getTypeOfTransaction().equals("پرداخت قبض")) {
                transactionInfo += transaction.getBillingId() + "شناسه قبض : "
                        + "\n" + transaction.getPaymentCode() + "شناسه پرداخت : "
                        + "\n";
            }

            transactionInfo += transaction.getSerialOfTransaction() + "شماره سزیال :"
                    + "\n" + transaction.getDateOfTransaction() + " تاریخ :"
                    + "\n";
            if (transaction.isFinished() == true) {
                transactionInfo += "عملیات موفق ";
            } else if (transaction.isFinished() == false) {
                transactionInfo += "عملیات نا موفق ";

            }
            historyTextField.setText(historyTextField.getText() + transactionInfo);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        setHistoryTextField();

    }

}
