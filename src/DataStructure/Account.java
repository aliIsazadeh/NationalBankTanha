package DataStructure;

import java.util.ArrayList;

public class Account {
    private long accountNumber;//شماره حساب
    private String accountType;//نوع حساب
    private String accountBank;//بانکی که در ان حساب را باز کردیم
    private String passwordForATM;
    private long uniquePassword;//پسورد یکبار مصرف
    private String secondPassword;//رمز دوم
    private String inventory;//مجودی
    private ArrayList<Transaction> transactions;
    private String accountPassword ;
    private String  userName;
    private Person person;

    public void setUserName(String  userName) {
        this.userName = userName;
    }

    public String  getUserName() {
        return userName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }



    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getPasswordForATM() {
        return passwordForATM;
    }

    public void setPasswordForATM(String passwordForATM) {
        this.passwordForATM = passwordForATM;
    }

    public long getUniquePassword() {
        return uniquePassword;
    }

    public void setUniquePassword(long uniquePassword) {
        this.uniquePassword = uniquePassword;
    }
}
