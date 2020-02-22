package DataStructure;

import java.util.Date;

public class Transaction {
    private String typeOfTransaction;
    private long IDnumber;//شماره حساب
    private Account from;//میدا
    private Account to;//مقصد
    private boolean finished;//نتیجه
    private String serialOfTransaction;//شماره
    private String DateOfTransaction;//تاریخ
    private String costOfTransaction;//میلغ تراکنش




    public long getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(long IDnumber) {
        this.IDnumber = IDnumber;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }




    public String getSerialOfTransaction() {
        return serialOfTransaction;
    }

    public void setSerialOfTransaction(String serialOfTransaction) {
        this.serialOfTransaction = serialOfTransaction;
    }

    public String getDateOfTransaction() {
        return DateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        DateOfTransaction = dateOfTransaction;
    }

    public String getInventoryOfTransaction() {
        return costOfTransaction;
    }

    public void setInventoryOfTransaction(String inventoryOfTransaction) {
        this.costOfTransaction = inventoryOfTransaction;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }
}
