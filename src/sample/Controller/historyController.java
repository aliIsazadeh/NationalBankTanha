package sample.Controller;

import DataStructure.Account;
import DataStructure.Transaction;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class historyController implements Initializable {


    public TextArea historyTextField;



    private void setHistoryTextField() {

        loginPageController loginPageController = new loginPageController();
        Account account = loginPageController.getAccount();
        ArrayList<Transaction> list = new ArrayList<>();
        if (account.getTransactions()!=(null)) {
            list.addAll(account.getTransactions());
            for (int i = 0; i < list.size(); i++) {
                Transaction transaction = list.get(i);
                String transactionInfo = "بانک ملی تنها"
                        + "\n"+ "نوع تراکنش : " + transaction.getTypeOfTransaction()
                        + "\n" + "مبلغ : "+ transaction.getCostOfTransaction()
                        + "\n";
                if (transaction.getTypeOfTransaction().equals("انتقال وجه")) {
                    transactionInfo +=  "انتقال از کارت : "+transaction.getFrom().getAccountNumber()
                            + "\n" + "انتقال به کارت : "+ transaction.getTo().getAccountNumber()
                            + "\n";
                } else if (transaction.getTypeOfTransaction().equals("پرداخت قبض")) {
                    transactionInfo += "شناسه قبض : "+transaction.getBillingId()
                            + "\n" + "شناسه پرداخت : "+ transaction.getPaymentCode()
                            + "\n";
                }else if (transaction.getTypeOfTransaction().equals("افزودن موجودی")){
                  transactionInfo += "واریز پول" +"\n" ;

                }

                transactionInfo += "موجودی : "+account.getInventory()+"\n";

                transactionInfo += "شماره سریال :"+transaction.getSerialOfTransaction()
                        + "\n" + " تاریخ :"+ transaction.getDateOfTransaction()
                        + "\n";


                if (transaction.isFinished()) {
                    transactionInfo += "عملیات موفق "+"\n\n";
                } else if (!transaction.isFinished()) {
                    transactionInfo += "عملیات نا موفق "+"\n\n";

                }
                historyTextField.setText(historyTextField.getText() + transactionInfo);
            }
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        setHistoryTextField();

    }

}
