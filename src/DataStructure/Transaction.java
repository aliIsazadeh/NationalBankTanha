package DataStructure;

import java.util.Date;

public class Transaction {
    private String typeOfTransaction;
    private long IDnumber;
    private Date date;
    private long cost;
    private Account from;
    private Account to;
    private boolean finished;



    public long getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(long IDnumber) {
        this.IDnumber = IDnumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
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




    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }
}
