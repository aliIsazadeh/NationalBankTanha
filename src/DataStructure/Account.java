package DataStructure;

public class Account {
    private long accountNumber;//شماره حساب
    private String accountType;//نوع حساب
    private String accountBank;//بانکی که در ان حساب را باز کردیم
    private String passwordForATM;
    private long uniquePassword;

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
