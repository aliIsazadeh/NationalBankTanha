package DataStructure;

import java.util.ArrayList;

public class Person {
    private String name;
    private String lastName;
    private long nationalNumber;
    private Account account;
    private long phoneNumber;
    private String address;//آدرس

    private String fatherName ;
    private String bornTime ;
    private String bornPlace ;
    private String job ;
    private boolean marriage ;
    private String gender ;


    public String getBornTime() {
        return bornTime;
    }

    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }

    public String getBornPlace() {
        return bornPlace;
    }

    public void setBornPlace(String bornPlace) {
        this.bornPlace = bornPlace;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isMarriage() {
        return marriage;
    }

    public void setMarriage(boolean marriage) {
        this.marriage = marriage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }




    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }





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
