package DataStructure;

import java.util.ArrayList;

public class Person {
    private String name;
    private String lastName;
    private long nationalNumber;
    private Account account;
    private long phoneNumber;
    private String address;//آدرس

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(long nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
//    public void addTTransaction(Transaction transaction){
//        this.transaction.add(transaction);
//    }
//
//    public ArrayList<Transaction> getTransaction() {
//        return transaction;
//    }


}
