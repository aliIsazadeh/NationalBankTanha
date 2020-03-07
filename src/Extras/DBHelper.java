package Extras;

import DataStructure.Account;
import DataStructure.Bill;
import DataStructure.Person;
import DataStructure.Transaction;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    private Connection connectionForPerson;
    private Connection connectionForBill;
    private Connection connectionForAccount;
    private Connection connectionForTransaction;

    private Statement statementForPerson;
    private Statement statementForBill;
    private Statement statementForAccount;
    private Statement statementForTransaction;


    private DBHelper() {
        connectionForPerson();
        creatTableForPerson();
        closePerson();

        connectionForAccount();
        createTableForAccount();
        closeAccount();

        connectionForTransaction();
        createTableForTransaction();
        closeTransaction();

        connectionForBill();
        createTableForBill();
        closeBill();

    }

    private void connectionForPerson() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForPerson = DriverManager.getConnection("jdbc:sqlite:DBPerson.db");
            statementForPerson = connectionForPerson.createStatement();
            System.out.println("Connection Created person");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void connectionForBill() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForBill = DriverManager.getConnection("jdbc:sqlite:DBForBill.db");
            statementForBill = connectionForBill.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void connectionForTransaction() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForTransaction = DriverManager.getConnection("jdbc:sqlite:DBForTransaction.db");
            statementForTransaction = connectionForTransaction.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void connectionForAccount() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForAccount = DriverManager.getConnection("jdbc:sqlite:DBForAccount.db");
            statementForAccount = connectionForAccount.createStatement();
            System.out.println("connectionForAccount");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void creatTableForPerson() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,accountNumber TEXT,firstName TEXT , lastName TEXT,nationalCode TEXT,phoneNumber TEXT,address TEXT,fatherName TEXT ,dataOFBorn TEXT,placeOfBorn TEXT , job TEXT,gender TEXT ,marriage TEXT );";
        try {
            statementForPerson.executeUpdate(tableSQL);
            System.out.println("Person table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTableForBill() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS bill (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,billingID TEXT , paymentCode TEXT , condition TEXT, cost TEXT );";
        try {
            statementForBill.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private void createTableForAccount() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS account (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,accountNumber TEXT , typeOfAccount TEXT , passForATM TEXT , secondPass TEXT , inventory TEXT , userName TEXT);";
        try {
            statementForAccount.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    private void createTableForTransaction() {

        String tableSQL = "CREATE TABLE IF NOT EXISTS transactions (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,typeOfTransaction TEXT , fromAccount TEXT , ToAccount TEXT , finished TEXT , serial TEXT , dat TEXT , cost TEXT);";
        try {
            statementForTransaction.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    ////// zaxire objecti az person dar dataBase
    private void insertPerson(Person person, long number) {
        connectionForPerson();

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
            statementForPerson.executeUpdate(insertSQL);
            System.out.println("inserted person");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage() + "person");
//   //         e.printStackTrace();
        }
        closePerson();
    }
////// zaxire objecti az Bill dar dataBase

    public void insertBill(Bill bill) {
        connectionForBill();
        String billingID = bill.getBillingId() + "";
        String condition = bill.getCondition();
        String paymentCode = bill.getPaymentCode() + "";
        String costOfBill = bill.getCostOfBill() + "";

        String insertSQL = "INSERT INTO bill (billingID  , paymentCode  , condition,cost ) VALUES ('" + billingID + "','" + paymentCode + "','" + condition + "','" + costOfBill + "');";
        try {
            statementForBill.executeUpdate(insertSQL);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeBill();
    }

    ////// zaxire objecti az Account dar dataBase
    private void insertAccount(Account account) {

        insertPerson(account.getPerson(), account.getAccountNumber());
        connectionForAccount();
        String accountID = account.getAccountNumber() + "";
        String accountType = account.getAccountType();
        String passForATM = account.getPasswordForATM();
        String secondPass = account.getSecondPassword();
        String inventory = account.getInventory();
        String userName = account.getUserName();
        String insertSQL = "INSERT INTO account (accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory,userName  ) VALUES ('" + accountID + "','" + accountType + "','" + passForATM + "','" + secondPass + "','" + inventory + "','" + userName + "');";
        try {
            statementForAccount.executeUpdate(insertSQL);
            System.out.println("insert account");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Account");
        }
        closeAccount();

    }
////// zaxire objecti az Transaction dar dataBase

    public void insertTransaction(Transaction transaction) {
        connectionForTransaction();

        String type = transaction.getTypeOfTransaction();
        String from = transaction.getFrom().getAccountNumber() + "";
        String To = transaction.getTo().getAccountNumber() + "";
        String date = transaction.getDateOfTransaction();
        String cost = transaction.getCostOfTransaction();
        String serial = transaction.getSerialOfTransaction();
        String finished = transaction.isFinished() + "";
        String insertSQL = "INSERT INTO transactions (typeOfTransaction  , fromAccount  , ToAccount  , finished  , serial  , dat  , cost)VALUES ('" + type + "','" + from + "','" + To + "','" + finished + "','" + serial + "','" + date + "','" + cost + "');";
        try {
            statementForTransaction.executeUpdate(insertSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeTransaction();
    }

    ////xandane tamam tarakonesh haye 1 hesab
    private ArrayList<Transaction> readAllTransactionForPerson(long id) {


        String getTransactionSQL = "SELECT typeOfTransaction  , fromAccount  , ToAccount  , finished  , serial  , dat  , cost FROM transactions where fromAccount = '" + id + "' or toAccount = '" + id + "';";

        return getTransactionsFromDB(getTransactionSQL);
    }

    //bonan ishiz olmasin
    private ArrayList<Transaction> getTransactionsFromDB(String read) {
        connectionForTransaction();
        ArrayList<Transaction> list = new ArrayList<>();

        try {
            ResultSet resultSet = statementForTransaction.executeQuery(read);
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
                transaction.setDateOfTransaction(resultSet.getString("dat"));
                transaction.setCostOfTransaction(resultSet.getString("cost"));
                list.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeTransaction();
        return list;
    }

    ///xandan tamam tarakonesh haye anjam shode bank
    public ArrayList<Transaction> readAllTransactionForPeople() {

        String getTransactionSQL = "SELECT typeOfTransaction  , fromAccount  , ToAccount  , finished  , serial  , dat  , cost FROM transactions ;";

        return getTransactionsFromDB(getTransactionSQL);
    }

    //xandane gabz
    public Bill readBill(long billingID, long paymentCode) {
        connectionForBill();
        Bill bill = new Bill();
        String readSQL = "SELECT billingID  , paymentCode  , condition,cost from bill Where billingID = '" + billingID + "' and paymentCode = '" + paymentCode + "' ;";
        try {
            ResultSet resultSet = statementForBill.executeQuery(readSQL);
            bill.setBillingId(resultSet.getLong("billingID"));
            bill.setPaymentCode(resultSet.getLong("paymentCode"));
            bill.setCondition(resultSet.getString("condition"));
            bill.setCostOfBill(resultSet.getLong("cost"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeBill();
        return bill;
    }

    ///xandane hesab ba estefade az shomare hesab
    public Account readAccount(long accountNumber) {
        connectionForAccount();
        Account account = new Account();
        account.setPerson(readPerson(accountNumber));
        account.setTransactions(readAllTransactionForPerson(accountNumber));
        String readSQL = "SELECT accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory ,userName FROM account where accountNumber = '" + accountNumber + "';";
        try {
            ResultSet resultSet = statementForAccount.executeQuery(readSQL);
            account.setAccountNumber(resultSet.getLong("accountNumber"));
            account.setPasswordForATM(resultSet.getString("passForATM"));
            account.setAccountType(resultSet.getString("typeOfAccount"));
            account.setSecondPassword(resultSet.getString("secondPass"));
            account.setInventory(resultSet.getString("inventory"));
            account.setUserName(resultSet.getString("userName"));
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"4578/7");
        }
        closeAccount();
        return account;
    }

    //xandan tamam hesab haye bnk
    public ArrayList<Account> readAllAccountForPeople() {
        connectionForAccount();
        ArrayList<Account> list = new ArrayList<>();
        String getSQl = "SELECT accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory FROM account ;";
        try {
            ResultSet resultSet = statementForAccount.executeQuery(getSQl);
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
        closeAccount();
        return list;
    }

    //bonan da ishiz olmasin
    private Person readPerson(long accountNumber) {
        connectionForPerson();
        Person person = new Person();
        Account account = new Account();

        String readSQL = "SELECT accountNumber  ,firstName  , lastName,nationalCode ,phoneNumber ,address ,fatherName  ,dataOFBorn ,placeOfBorn  , job ,gender  ,marriage from person where accountNumber = '" + accountNumber + "';";

        try {
            ResultSet resultSet = statementForPerson.executeQuery(readSQL);
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
            System.out.println(e.getMessage()+"xxx");
        }
        closePerson();
        return person;
    }


    /// beroz kardan hesab masalan afzayesh mojodi v taghyir ramz v ..
    public void updateAccount(Account account) {
        connectionForAccount();
        updatePerson(account.getPerson());
        String update = " UPDATE account set accountNumber ='" + account.getAccountNumber() +"' where accountNumber ='"+account.getAccountNumber()+"';";
        String update1 = "UPDATE account set typeOfAccount = '"+account.getAccountType()+"' where accountNumber ='"+account.getAccountNumber()+"';";
        String update2 = "UPDATE account set passForATM = '"+account.getPasswordForATM()+"' where accountNumber ='"+account.getAccountNumber()+"';";
        String update3 = "UPDATE account set secondPass = '"+account.getSecondPassword()+"' where accountNumber ='"+account.getAccountNumber()+"';";
        String update4 = "UPDATE account set inventory = '"+account.getInventory()+"' where accountNumber ='"+account.getAccountNumber()+"';";
        String update5= "UPDATE account set userName = '"+account.getUserName()+"' where accountNumber ='"+account.getAccountNumber()+"';";


        //accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory ,userName
        try {
            statementForAccount.executeUpdate(update);
            statementForAccount.executeUpdate(update1);
            statementForAccount.executeUpdate(update2);
            statementForAccount.executeUpdate(update3);
            statementForAccount.executeUpdate(update4);
            statementForAccount.executeUpdate(update5);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "A");
        }
        closeAccount();
    }//accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory

    // /// beroz kardan etelaat fard  masalan tagiir telephone v ..
    private void updatePerson(Person person) {
        connectionForPerson();


        String update = "UPDATE person  set accountNumber = '" + person.getAccount().getAccountNumber() + "' WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "' ;";
        String update1 = "UPDATE person  set firstName = '"+person.getName()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update2= "UPDATE person  set lastName ='"+person.getLastName()+"';";
        String update3 = "UPDATE person  set nationalCode ='"+person.getNationalNumber()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update4= "UPDATE person  set phoneNumber ='"+person.getPhoneNumber()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update5= "UPDATE person  set address ='"+ person.getAddress()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update6= "UPDATE person  set fatherName ='"+person.getFatherName()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update7 = "UPDATE person  set dataOFBorn ='"+person.getBornTime()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update8 = "UPDATE person  set placeOfBorn ='"+person.getBornPlace()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update9 = "UPDATE person  set job ='"+person.getJob()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";
        String update10= "UPDATE person  set gender ='"+person.getGender()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "'; ";
        String update11 = "UPDATE person  set marriage = '"+person.isMarriage()+"'WHERE accountNumber = '" + person.getAccount().getAccountNumber() + "';";



        //accountNumber  ,firstName  , lastName,nationalCode ,phoneNumber ,address ,fatherName  ,dataOFBorn ,placeOfBorn  , job ,gender  ,marriage
        try {
            statementForPerson.executeUpdate(update);
            statementForPerson.executeUpdate(update1);
            statementForPerson.executeUpdate(update2);
            statementForPerson.executeUpdate(update3);
            statementForPerson.executeUpdate(update4);
            statementForPerson.executeUpdate(update5);
            statementForPerson.executeUpdate(update6);
            statementForPerson.executeUpdate(update7);
            statementForPerson.executeUpdate(update8);
            statementForPerson.executeUpdate(update9);
            statementForPerson.executeUpdate(update10);
            statementForPerson.executeUpdate(update11);

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "p");
        }

        closePerson();
    }
// /// beroz kardan gabz   masalan tagiir condition v ..

    public void updateBill(Bill bill) {
        connectionForBill();


        String updateSQL = "UPDATE bill set condition = '" + bill.getCondition() + "'  where  billingID = '" + bill.getBillingId() + "' and  paymentCode = '" + bill.getPaymentCode() + "';";

        try {
            statementForBill.executeUpdate(updateSQL);
            System.out.println("update password");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "B");
        }
        closeBill();
    }


    private void closePerson() {
        try {
            statementForPerson.close();

            connectionForPerson.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeBill() {
        try {
            statementForBill.close();

            connectionForBill.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeAccount() {
        try {
            statementForAccount.close();

            connectionForAccount.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeTransaction() {
        try {
            statementForTransaction.close();

            connectionForTransaction.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}

