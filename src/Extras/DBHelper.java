package Extras;

import DataStructure.Account;
import DataStructure.Bill;
import DataStructure.Person;
import DataStructure.Transaction;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper {
    private Connection connectionForBank;


    private Statement statementForBank;


    public DBHelper() {
        connectionForBank();
        creatTableForPerson();

        createTableForAccount();

        createTableForTransaction();

        createTableForBill();
        closeBank();
    }

    private void connectionForBank() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForBank = DriverManager.getConnection("jdbc:sqlite:bank.db");
            statementForBank = connectionForBank.createStatement();
            System.out.println("Connection Created person");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

//    private void connectionForBill() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            connectionForBill = DriverManager.getConnection("jdbc:sqlite:DBForBill.db");
//            statementForBill = connectionForBill.createStatement();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void connectionForTransaction() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            connectionForTransaction = DriverManager.getConnection("jdbc:sqlite:DBForTransaction.db");
//            statementForTransaction = connectionForTransaction.createStatement();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void connectionForAccount() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            connectionForAccount = DriverManager.getConnection("jdbc:sqlite:DBForAccount.db");
//            statementForAccount = connectionForAccount.createStatement();
//            System.out.println("connectionForAccount");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    private void creatTableForPerson() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,accountNumber TEXT,firstName TEXT , lastName TEXT,nationalCode TEXT,phoneNumber TEXT,address TEXT,fatherName TEXT ,dataOFBorn TEXT,placeOfBorn TEXT , job TEXT,gender TEXT ,marriage TEXT );";
        try {
            statementForBank.executeUpdate(tableSQL);
            System.out.println("Person table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTableForBill() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS bill (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,billingID TEXT , paymentCode TEXT , condition TEXT, cost TEXT );";
        try {
            statementForBank.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private void createTableForAccount() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS account (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,accountNumber TEXT , typeOfAccount TEXT , passForATM TEXT , secondPass TEXT , inventory TEXT , userName TEXT);";
        try {
            statementForBank.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    private void createTableForTransaction() {

        String tableSQL = "CREATE TABLE IF NOT EXISTS transactions (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,typeOfTransaction TEXT , fromAccount TEXT , ToAccount TEXT , finished TEXT , serial TEXT , dat TEXT , cost TEXT , billingID TEXT , paymentCode TEXT);";
        try {
            statementForBank.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    ////// zaxire objecti az person dar dataBase
    private void insertPerson(Person person, long number) {
        connectionForBank();

        String name = person.getName();
        String lastName = person.getLastName();
        String address = person.getAddress();
        String phoneNumber = person.getPhoneNumber() + "";
        String nationalCode = person.getNationalNumber() + "";
        String fatherName = person.getFatherName();
        String dateOfBorn = person.getBornTime();
        String placeOfBorn = person.getBornPlace();
        String job = person.getJob();
        String gender = person.getGender();
        String marriage = person.isMarriage() + "";
        String id = number + "";
        String insertSQL = "INSERT INTO person (accountNumber  ,firstName  , lastName,nationalCode ,phoneNumber ,address ,fatherName  ,dataOFBorn ,placeOfBorn  , job ,gender  ,marriage  ) VALUES ('" + id + "','" + name + "','" + lastName + "','" + nationalCode + "','" + phoneNumber + "','" + address + "','" + fatherName + "','" + dateOfBorn + "','" + placeOfBorn + "','" + job + "','" + gender + "','" + marriage + "');";
        ////////                                                                                                                                  id ,accountType,name ,lastName , nationalCode , phoneNumber,address,passwordFoeATM,secondPassword , inventory
        try {
            statementForBank.executeUpdate(insertSQL);
            System.out.println("inserted person");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage() + "person");
//   //         e.printStackTrace();
        }
        closeBank();
    }
////// zaxire objecti az Bill dar dataBase

    public void insertBill(Bill bill) {
        connectionForBank();
        String billingID = bill.getBillingId() + "";
        String condition = bill.getCondition();
        String paymentCode = bill.getPaymentCode() + "";
        String costOfBill = bill.getCostOfBill() + "";

        String insertSQL = "INSERT INTO bill (billingID  , paymentCode  , condition,cost ) VALUES ('" + billingID + "','" + paymentCode + "','" + condition + "','" + costOfBill + "');";
        try {
            statementForBank.executeUpdate(insertSQL);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeBank();
    }

    ////// zaxire objecti az Account dar dataBase

    public void insertAccount(Account account) {

        insertPerson(account.getPerson(), account.getAccountNumber());
        connectionForBank();
        String accountID = account.getAccountNumber() + "";
        String accountType = account.getAccountType();
        String passForATM = account.getPasswordForATM();
        String secondPass = account.getSecondPassword();
        String inventory = account.getInventory();
        String userName = account.getUserName();
        String insertSQL = "INSERT INTO account (accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory,userName  ) VALUES ('" + accountID + "','" + accountType + "','" + passForATM + "','" + secondPass + "','" + inventory + "','" + userName + "');";
        try {
            statementForBank.executeUpdate(insertSQL);
            System.out.println("insert account");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Account");
        }
        closeBank();

    }
////// zaxire objecti az Transaction dar dataBase

    public void insertTransaction(Transaction transaction) {
        connectionForBank();

        String type = transaction.getTypeOfTransaction();
        String from = transaction.getFrom().getAccountNumber() + "";
        String To = transaction.getTo().getAccountNumber() + "";
        Date date = transaction.getDateOfTransaction();
        String cost = transaction.getCostOfTransaction();
        String serial = transaction.getSerialOfTransaction();
        String finished = transaction.isFinished() + "";
        String billingId = transaction.getBillingId() + "";
        String paymentCode = transaction.getPaymentCode() + "";


        String insertSQL = "INSERT INTO transactions (typeOfTransaction  , fromAccount  , ToAccount  , finished  , serial  , dat  , cost ,billingID,paymentCode)VALUES ('" + type + "','" + from + "','" + To + "','" + finished + "','" + serial + "','" + date + "','" + cost + "','" + billingId + "','" + paymentCode + "');";
        // billingID TEXT , billingID TEXT
        try {
            statementForBank.executeUpdate(insertSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeBank();
    }

    ////xandane tamam tarakonesh haye 1 hesab
    private ArrayList<Transaction> readAllTransactionForPerson(long id) {


        String getTransactionSQL = "SELECT typeOfTransaction  , fromAccount  , ToAccount  , finished  , serial  , dat  , cost,billingID,paymentCode FROM transactions where fromAccount = '" + id + "' or toAccount = '" + id + "';";
        ArrayList<Transaction> list = new ArrayList<>();

        try {
            ResultSet resultSet = statementForBank.executeQuery(getTransactionSQL);
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                Account accountFrom = new Account();
                accountFrom.setAccountNumber((resultSet.getLong("fromAccount")));
                Account To = new Account();
                To.setAccountNumber(resultSet.getLong("ToAccount"));
                transaction.setTypeOfTransaction(resultSet.getString("typeOfTransaction"));
                transaction.setFrom(accountFrom);
                transaction.setTo(To);
                transaction.setFinished(resultSet.getBoolean("finished"));
                transaction.setSerialOfTransaction(resultSet.getString("serial"));
                transaction.setDateOfTransaction(resultSet.getDate("dat"));
                transaction.setCostOfTransaction(resultSet.getString("cost"));
                transaction.setBillingId(resultSet.getLong("billingID"));
                transaction.setPaymentCode(resultSet.getLong("paymentCode"));
                list.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeBank();
        return list;

        //return getTransactionsFromDB(getTransactionSQL);
    }

    //bonan ishiz olmasin
//    private ArrayList<Transaction> getTransactionsFromDB(String read) {
//        connectionForBank();
//        ArrayList<Transaction> list = new ArrayList<>();
//
//        try {
//            ResultSet resultSet = statementForBank.executeQuery(read);
//            while (resultSet.next()) {
//                Transaction transaction = new Transaction();
//                Account accountFrom = new Account();
//                accountFrom.setAccountNumber((resultSet.getLong("fromAccount")));
//                Account To = new Account();
//                To.setAccountNumber(resultSet.getLong("ToAccount"));
//                transaction.setTypeOfTransaction(resultSet.getString("typeOfTransaction"));
//                transaction.setFrom(accountFrom);
//                transaction.setTo(To);
//                transaction.setFinished(resultSet.getBoolean("finished"));
//                transaction.setSerialOfTransaction(resultSet.getString("serial"));
//                transaction.setDateOfTransaction(resultSet.getDate("dat"));
//                transaction.setCostOfTransaction(resultSet.getString("cost"));
//                list.add(transaction);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        closeBank();
//        return list;
//    }

    ///xandan tamam tarakonesh haye anjam shode bank
//    public ArrayList<Transaction> readAllTransactionForPeople() {
//
//        String getTransactionSQL = "SELECT typeOfTransaction  , fromAccount  , ToAccount  , finished  , serial  , dat  , cost FROM transactions ;";
//
//        return getTransactionsFromDB(getTransactionSQL);
//    }

    //xandane gabz
    public Bill readBill(long billingID, long paymentCode) {
        connectionForBank();
        Bill bill = new Bill();
        String readSQL = "SELECT billingID  , paymentCode  , condition,cost from bill Where billingID = '" + billingID + "' and paymentCode = '" + paymentCode + "' ;";
        try {
            ResultSet resultSet = statementForBank.executeQuery(readSQL);
            bill.setBillingId(resultSet.getLong("billingID"));
            bill.setPaymentCode(resultSet.getLong("paymentCode"));
            bill.setCondition(resultSet.getString("condition"));
            bill.setCostOfBill(resultSet.getLong("cost"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeBank();
        return bill;
    }

    ///xandane hesab ba estefade az shomare hesab
    public Account readAccount(String userName) {


        String readSQL = "SELECT accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory ,userName FROM account where userName = '" + userName + "';";

        return getAccount(readSQL);
    }

    public Account readAccount(long accountNumber) {

        String readSQL = "SELECT accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory ,userName FROM account where accountNumber = '" + accountNumber + "';";
        return getAccount(readSQL);
    }

    private Account getAccount(String sql) {
        connectionForBank();
        Account account = new Account();
        try {
            ResultSet resultSet = statementForBank.executeQuery(sql);
            account.setAccountNumber(resultSet.getLong("accountNumber"));
            account.setPasswordForATM(resultSet.getString("passForATM"));
            account.setAccountType(resultSet.getString("typeOfAccount"));
            account.setSecondPassword(resultSet.getString("secondPass"));
            account.setInventory(resultSet.getString("inventory"));
            account.setUserName(resultSet.getString("userName"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeBank();
        account.setPerson(readPerson(account.getAccountNumber()));
        account.setTransactions(readAllTransactionForPerson(account.getAccountNumber()));
        return account;
    }

    //xandan tamam hesab haye bnk
    public ArrayList<Account> readAllAccountForPeople() {
        connectionForBank();
        ArrayList<Account> list = new ArrayList<>();
        String getSQl = "SELECT accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory FROM account ;";
        try {
            ResultSet resultSet = statementForBank.executeQuery(getSQl);
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountNumber(resultSet.getLong("accountNumber"));
                account.setPasswordForATM(resultSet.getString("passForATM"));
                account.setAccountType(resultSet.getString("typeOfAccount"));
                account.setSecondPassword(resultSet.getString("secondPass"));
                account.setInventory(resultSet.getString("inventory"));
                account.setTransactions(readAllTransactionForPerson(account.getAccountNumber()));
                list.add(account);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeBank();
        return list;
    }

    //bonan da ishiz olmasin
    private Person readPerson(long accountNumber) {
        connectionForBank();
        Person person = new Person();
        Account account = new Account();

        String readSQL = "SELECT accountNumber  ,firstName  , lastName,nationalCode ,phoneNumber ,address ,fatherName  ,dataOFBorn ,placeOfBorn  , job ,gender  ,marriage from person where accountNumber = '" + accountNumber + "';";

        try {
            ResultSet resultSet = statementForBank.executeQuery(readSQL);
            account.setAccountNumber(resultSet.getLong("accountNumber"));
            person.setAccount(account);

            person.setName(resultSet.getString("firstName"));
            person.setLastName(resultSet.getString("lastName"));
            person.setAddress(resultSet.getString("address"));
            person.setNationalNumber(resultSet.getLong("nationalCode"));
            person.setPhoneNumber(("phoneNumber"));
            person.setFatherName(resultSet.getString("fatherName"));
            person.setBornTime(resultSet.getString("dataOFBorn"));
            person.setBornPlace(resultSet.getString("placeOfBorn"));
            person.setJob(resultSet.getString("job"));
            person.setGender(resultSet.getString("gender"));
            person.setMarriage(resultSet.getBoolean("marriage"));
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "xxx");
        }
        closeBank();
        return person;
    }


    /// beroz kardan hesab masalan afzayesh mojodi v taghyir ramz v ..
    public void updateAccount(Account account) {
        connectionForBank();
        updatePerson(account.getPerson());
        String update = " UPDATE account set accountNumber ='" + account.getAccountNumber() + "' where accountNumber ='" + account.getAccountNumber() + "';";
        String update1 = "UPDATE account set typeOfAccount = '" + account.getAccountType() + "' where accountNumber ='" + account.getAccountNumber() + "';";
        String update2 = "UPDATE account set passForATM = '" + account.getPasswordForATM() + "' where accountNumber ='" + account.getAccountNumber() + "';";
        String update3 = "UPDATE account set secondPass = '" + account.getSecondPassword() + "' where accountNumber ='" + account.getAccountNumber() + "';";
        String update4 = "UPDATE account set inventory = '" + account.getInventory() + "' where accountNumber ='" + account.getAccountNumber() + "';";
        String update5 = "UPDATE account set userName = '" + account.getUserName() + "' where accountNumber ='" + account.getAccountNumber() + "';";


        //accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory ,userName
        try {
            statementForBank.executeUpdate(update);
            statementForBank.executeUpdate(update1);
            statementForBank.executeUpdate(update2);
            statementForBank.executeUpdate(update3);
            statementForBank.executeUpdate(update4);
            statementForBank.executeUpdate(update5);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "A");
        }
        closeBank();
    }//accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory

    // /// beroz kardan etelaat fard  masalan tagiir telephone v ..
    private void updatePerson(Person person) {
        connectionForBank();


        String update = "UPDATE person  set accountNumber = '" + person.getAccount().getAccountNumber() + "' WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "' ;";
        String update1 = "UPDATE person  set firstName = '" + person.getName() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update2 = "UPDATE person  set lastName ='" + person.getLastName() + "';";
        String update3 = "UPDATE person  set nationalCode ='" + person.getNationalNumber() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update4 = "UPDATE person  set phoneNumber ='" + person.getPhoneNumber() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update5 = "UPDATE person  set address ='" + person.getAddress() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update6 = "UPDATE person  set fatherName ='" + person.getFatherName() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update7 = "UPDATE person  set dataOFBorn ='" + person.getBornTime() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update8 = "UPDATE person  set placeOfBorn ='" + person.getBornPlace() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update9 = "UPDATE person  set job ='" + person.getJob() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update10 = "UPDATE person  set gender ='" + person.getGender() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "'; ";
        String update11 = "UPDATE person  set marriage = '" + person.isMarriage() + "'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";


        //accountNumber  ,firstName  , lastName,nationalCode ,phoneNumber ,address ,fatherName  ,dataOFBorn ,placeOfBorn  , job ,gender  ,marriage
        try {
            statementForBank.executeUpdate(update);
            statementForBank.executeUpdate(update1);
            statementForBank.executeUpdate(update2);
            statementForBank.executeUpdate(update3);
            statementForBank.executeUpdate(update4);
            statementForBank.executeUpdate(update5);
            statementForBank.executeUpdate(update6);
            statementForBank.executeUpdate(update7);
            statementForBank.executeUpdate(update8);
            statementForBank.executeUpdate(update9);
            statementForBank.executeUpdate(update10);
            statementForBank.executeUpdate(update11);

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "p");
        }

        closeBank();
    }
// /// beroz kardan gabz   masalan tagiir condition v ..

    public void updateBill(Bill bill) {
        connectionForBank();


        String updateSQL = "UPDATE bill set condition = '" + bill.getCondition() + "'  where  billingID = '" + bill.getBillingId() + "' and  paymentCode = '" + bill.getPaymentCode() + "';";

        try {
            statementForBank.executeUpdate(updateSQL);
            System.out.println("update password");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "B");
        }
        closeBank();
    }


    private void closeBank() {
        try {
            statementForBank.close();

            connectionForBank.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new DBHelper();
    }


}

