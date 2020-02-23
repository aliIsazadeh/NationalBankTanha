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


    public DBHelper() {
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

    public void connectionForPerson() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForPerson = DriverManager.getConnection("jdbc:sqlite:DBPerson.db");
            statementForPerson = connectionForPerson.createStatement();
            System.out.println("Connection Created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void connectionForBill() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForBill = DriverManager.getConnection("jdbc:sqlite:DBForBill.db");
            statementForBill = connectionForBill.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void connectionForTransaction() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForTransaction = DriverManager.getConnection("jdbc:sqlite:DBForTransaction.db");
            statementForTransaction = connectionForTransaction.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void connectionForAccount() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionForAccount = DriverManager.getConnection("jdbc:sqlite:DBForAccount.db");
            statementForAccount = connectionForAccount.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void creatTableForPerson() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,accountNumber TEXT,name TEXT , lastName TEXT,nationalCode TEXT,phoneNumber TEXT,address TEXT,fatherName TEXT ,dataOFBorn TEXT,placeOfBorn TEXT , job TEXT,gender TEXT ,marriage TEXT );";
        try {
            statementForPerson.executeUpdate(tableSQL);
            System.out.println("Person table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTableForBill() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS bill (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,billingID TEXT , paymentCode TEXT , condition TEXT, cost TEXT );";
        try {
            statementForBill.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void createTableForAccount() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS account (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,accountNumber TEXT , typeOfAccount TEXT , passForATM TEXT , secondPass TEXT , inventory TEXT);";
        try {
            statementForAccount.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void createTableForTransaction() {

        String tableSQL = "CREATE TABLE IF NOT EXISTS transactions (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,typeOfTransaction TEXT , fromAccount TEXT , ToAccount TEXT , finished TEXT , serial TEXT , dat TEXT , cost TEXT);";
        try {
            statementForTransaction.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // TODO write conection method
    public void insertPerson(Person person) {
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
        String id = person.getAccount().getAccountNumber() + "";
        String insertSQL = "INSERT INTO person (accountNumber  ,name  , lastName,nationalCode ,phoneNumber ,address ,fatherName  ,dataOFBorn ,placeOfBorn  , job ,gender  ,marriage  ) VALUES ('" + id + "','" + name + "','" + lastName + "','" + nationalCode + "','" + phoneNumber + "','" + address + "','" + fatherName + "','" + dateOfBorn + "','" + placeOfBorn + "','" + job + "','" + gender + "','" + marriage + "');";
        ////////                                                                                                                                  id ,accountType,name ,lastName , nationalCode , phoneNumber,address,passwordFoeATM,secondPassword , inventory
        try {
            statementForPerson.executeUpdate(insertSQL);
            System.out.println("inserted");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        closePerson();
    }

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

    private void insertAccount(Account account) {
        connectionForAccount();
        String accountID = account.getAccountNumber() + "";
        String accountType = account.getAccountType();
        String passForATM = account.getPasswordForATM();
        String secondPass = account.getSecondPassword();
        String inventory = account.getInventory();
        String insertSQL = "INSERT INTO account (accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory  ) VALUES ('" + accountID + "','" + accountType + "','" + passForATM + "','" + secondPass + "','" + inventory + "');";
        try {
            statementForAccount.executeUpdate(insertSQL);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeAccount();

    }

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


    public ArrayList<Transaction> readAllTransactionForPerson(long id) {

//        ArrayList<Transaction> list = new ArrayList<>();
//        try {
//            ResultSet resultSet = statementForTransaction.executeQuery(getTransactionSQL);
//            while (resultSet.next()) {
//                Transaction transaction = new Transaction();
//                Account accountFrom = new Account();
//                accountFrom.setAccountNumber(Long.valueOf(resultSet.getString("fromAccount")));
//                Account To = new Account();
//                To.setAccountNumber(resultSet.getLong("ToAccount"));
//                transaction.setTypeOfTransaction(resultSet.getString("typeOfTransaction"));
//                transaction.setFrom(accountFrom);
//                transaction.setTo(To);
//                transaction.setFinished(resultSet.getBoolean("finished"));
//                transaction.setSerialOfTransaction(resultSet.getString("serial"));
//                transaction.setDateOfTransaction(resultSet.getString("dat"));
//                transaction.setCostOfTransaction(resultSet.getString("cost"));
//                list.add(transaction);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        String getTransactionSQL = "SELECT typeOfTransaction  , fromAccount  , ToAccount  , finished  , serial  , dat  , cost FROM transactions where fromAccount = '" + id + "' or toAccount = '" + id + "';";

        return getTransactionsFromDB(getTransactionSQL);
    }

    public ArrayList<Transaction> getTransactionsFromDB(String read) {
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

    public ArrayList<Transaction> readAllTransactionForPeople() {

        String getTransactionSQL = "SELECT typeOfTransaction  , fromAccount  , ToAccount  , finished  , serial  , dat  , cost FROM transactions ;";

        return getTransactionsFromDB(getTransactionSQL);
    }

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

    public Account readAccount(long accountNumber) {
        connectionForAccount();
        Account account = new Account();
        account.setTransactions(readAllTransactionForPerson(accountNumber));
        String readSQL = "SELECT accountNumber  , typeOfAccount,passForATM  , secondPass  , inventory FROM account where accountNumber = '" + accountNumber + "';";
        try {
            ResultSet resultSet = statementForAccount.executeQuery(readSQL);
            account.setAccountNumber(resultSet.getLong("accountNumber"));
            account.setPasswordForATM(resultSet.getString("passForATM"));
            account.setAccountType(resultSet.getString("typeOfAccount"));
            account.setSecondPassword(resultSet.getString("secondPass"));
            account.setInventory(resultSet.getString("inventory"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeAccount();
        return account;
    }

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
//    public void addTransaction(Transaction transaction, long id) {
//
//        ArrayList<Transaction> list = readAllTransaction(id);
//        list.add(transaction);
//        String tarakonesh = "";
//        for (int i = 0; i < list.size(); i++) {
//            tarakonesh = list.get(i).getTypeOfTransaction() + "," + list.get(i).getCostOfTransaction() + "," + list.get(i).getSerialOfTransaction() + "," + list.get(i).getDateOfTransaction() + "@";
//        }
//        String upTransactionSQL = "UPDATE person set tr ='" + tarakonesh + "' where id ='" + id + "'";
//        try {
//            statementForPerson.executeUpdate(upTransactionSQL);
//            System.out.println("transactions is updated");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public Person readPerson(long accountNumber) {
        connectionForPerson();
        Person person = new Person();
        Account account = new Account();
        account = readAccount(accountNumber);
        person.setAccount(account);

        String readSQL = "SELECT accountNumber  ,name  , lastName,nationalCode ,phoneNumber ,address ,fatherName  ,dataOFBorn ,placeOfBorn  , job ,gender  ,marriage from person where accountNumber = '" + accountNumber + "';";
        try {
            ResultSet resultSet = statementForPerson.executeQuery(readSQL);
            person.setName(resultSet.getString("name"));
            person.setLastName(resultSet.getString("lastName"));
            person.setAddress(resultSet.getString("address"));
            person.setNationalNumber(resultSet.getLong("nationalCode"));
            person.setPhoneNumber(resultSet.getLong("phoneNumber"));
            person.setFatherName(resultSet.getString("fatherName"));
            person.setBornTime(resultSet.getString("dataOFBorn"));
            person.setBornPlace(resultSet.getString("placeOfBorn"));
            person.setJob(resultSet.getString("job"));
            person.setGender(resultSet.getString("gender"));
            person.setMarriage(resultSet.getBoolean("marriage"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closePerson();
        return person;
    }

//    public ArrayList<Person> getAllPerson() {
//        //End
//        String getSql = "SELECT id,accountType,name , lastname, nationalcode,phonNumber,address,passwordForATM,secondPassword,tr,inventory FROM person;";
//        ArrayList<Person> list = new ArrayList<Person>();
//        try {
//            ResultSet resultSet = statementForPerson.executeQuery(getSql);
//            while (resultSet.next()) {
//                Person person = new Person();
//                Account account = new Account();
////id  ,accountType ,name , lastName,nationalCode TEXT,phoneNumber TEXT,address TEXT ,passwordForATM TEXT ,secondPassword TEXT, transaction TEXT ,inventory TEXT );";
//                account.setAccountNumber(resultSet.getLong(0));
//                account.setAccountType(resultSet.getString(1));
//                account.setPasswordForATM(resultSet.getString(7));
//                account.setSecondPassword(resultSet.getString(8));
//                account.setInventory(resultSet.getString(10));
//                account.setTransactions(readAllTransaction(account.getAccountNumber()));
//
//
//                person.setAccount(account);
//                person.setName(resultSet.getString(2));
//                person.setLastName(resultSet.getString(3));
//                person.setNationalNumber(resultSet.getLong(4));
//                person.setPhoneNumber((resultSet.getLong(5)));
//                person.setAddress(resultSet.getString(6));
//                ;
//
//                list.add(person);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        //  close();
//        return list;
//
//    }

    public void upDateInventory(long ID, String inventory) {
        //TODO
    }

    public void updatePasswordForATM(long id, String password) {
//TODO

    }

    public void updateSecondPassword(String newPassword, long ID) {
        //TODO
    }

    public void updatePassword(String pass) {

        String updatePassSQL = "UPDATE person set passwordForATM = '" + pass + "'";
        try {
            statementForPerson.executeUpdate(updatePassSQL);
            System.out.println("update password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


//    class ObjToDB {
//        String name;
//        String lastName;
//
//        public String toString() {
//            return "[" + name + "  " + lastName + "]";
//        }
//    }

    public void closePerson() {
        try {
            statementForPerson.close();

            connectionForPerson.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeBill() {
        try {
            statementForBill.close();

            connectionForBill.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeAccount() {
        try {
            statementForAccount.close();

            connectionForAccount.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeTransaction() {
        try {
            statementForTransaction.close();

            connectionForTransaction.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new DBHelper();
    }
}

