package DataStructure;


import java.util.Date;

public class Transaction {
    private String typeOfTransaction;
    private long IDnumber;//شماره حساب
    private Account from;//میدا
    private Account to;//مقصد

    private boolean finished;//نتیجه
    private String serialOfTransaction;//شماره
    private Date DateOfTransaction;//تاریخ
    private String costOfTransaction;//میلغ تراکنش
    private long billingId;//شناسه قبض
    private long paymentCode;// شناسه پرداخت

    public long getBillingId() {
        return billingId;
    }

    public void setBillingId(long billingId) {
        this.billingId = billingId;
    }

    public long getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(long paymentCode) {
        this.paymentCode = paymentCode;
    }

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

    public Date getDateOfTransaction() {
        return DateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        DateOfTransaction = dateOfTransaction;
    }

    public String getCostOfTransaction() {
        return costOfTransaction;
    }

    public void setCostOfTransaction(String inventoryOfTransaction) {
        this.costOfTransaction = inventoryOfTransaction;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }
}
