package Extras;

import DataStructure.Account;
import DataStructure.Person;
import DataStructure.Transaction;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    private Connection connection;
    private Statement statement;

    public DBHelper() {
        connect();

        creatTable();
//        ObjToDB account = new ObjToDB();
//        account.lastName = "hossein";
//        account.name = "Shakeri";
//        insertPerson(account);
//        ArrayList<ObjToDB> list = getAllPerson();
//        for (ObjToDB o : list
//        ) {
//            System.out.println(o.name.toString());
//        }
        close();
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:Data.db");
            statement = connection.createStatement();
            System.out.println("Connection Created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void creatTable() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,accountType TEXT,name TEXT , lastName TEXT,nationalCode TEXT,phoneNumber TEXT,address TEXT ,passwordForATM TEXT ,secondPassword TEXT,Tr TEXT ,inventory TEXT );";
        try {
            statement.executeUpdate(tableSQL);
            System.out.println("Person table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPerson(Person person) {
        String accountType = person.getAccount().getAccountType();
        String name = person.getName();
        String lastName = person.getLastName();
        String address = person.getAddress();
        long phoneNumber = person.getPhoneNumber();
        long nationalCode = person.getNationalNumber();
        String pssForATM = person.getAccount().getPasswordForATM();
        String secondPass = person.getAccount().getSecondPassword();
        String inventory = person.getAccount().getInventory();
        long id = person.getAccount().getAccountNumber();
        String insertSQL = "INSERT INTO person (id ,accountType,name ,lastName , nationalCode , phoneNumber,address,passwordFoeATM,secondPassword , inventory) VALUES ('" + id + "','" + accountType + "','" + name + "','" + lastName + "','" + nationalCode + "','" + phoneNumber + "','" + address + "','" + pssForATM + "','" + secondPass + "','" + inventory + "');";
        ////////                                                                                                                                  id ,accountType,name ,lastName , nationalCode , phoneNumber,address,passwordFoeATM,secondPassword , inventory
        try {
            statement.executeUpdate(insertSQL);
            System.out.println("inserted");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // close();
    }

    public void updatePassword(String pass) {

        String updatePassSQL = "UPDATE person set passwordForATM = '" + pass + "'";
        try {
            statement.executeUpdate(updatePassSQL);
            System.out.println("update password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Transaction> readAllTransaction(long id) {
        //End
        ArrayList<Transaction> list = new ArrayList<>();
        String getTransactionSQL = "SELECT tr FROM person where id = '" + id + "'";
        try {
            ResultSet resultSet = statement.executeQuery(getTransactionSQL);
            while (resultSet.next()) {
                String[] transactions = resultSet.getString(9).split("@");
                for (int i = 0; i < transactions.length; i++) {
                    Transaction transaction = new Transaction();
                    String[] result = transactions[i].split(",");
                    transaction.setTypeOfTransaction(result[0]);
                    transaction.setInventoryOfTransaction(result[1]);
                    transaction.setSerialOfTransaction(result[2]);
                    transaction.setDateOfTransaction(result[3]);
                    list.add(transaction);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void addTransaction(Transaction transaction, long id) {

        ArrayList<Transaction> list = readAllTransaction(id);
        list.add(transaction);
        String tarakonesh = "";
        for (int i = 0; i < list.size(); i++) {
            tarakonesh = list.get(i).getTypeOfTransaction() + "," + list.get(i).getInventoryOfTransaction() + "," + list.get(i).getSerialOfTransaction() + "," + list.get(i).getDateOfTransaction() + "@";
        }
        String upTransactionSQL = "UPDATE person set tr ='" + tarakonesh + "' where id ='" + id + "'";
        try {
            statement.executeUpdate(upTransactionSQL);
            System.out.println("transactions is updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Person readPerson(long id) {

        Person person = new Person();
        Account account = new Account();
        account.setTransactions(readAllTransaction(id));
        ArrayList<Transaction> list = new ArrayList<>();
        String readSQL = "SELECT from person where id = '" + id + "';";
        try {
            ResultSet resultSet = statement.executeQuery(readSQL);
            person.setName(resultSet.getString("name"));
            person.setLastName(resultSet.getString("lastName"));
            person.setAddress(resultSet.getString("address"));
            person.setNationalNumber(resultSet.getLong("nationalCode"));
            person.setPhoneNumber(resultSet.getLong("phoneNumber"));
            account.setSecondPassword(resultSet.getString("secondPassword"));
            account.setPasswordForATM(resultSet.getString("passwordForATM"));
            account.setAccountType(resultSet.getString("accountType"));
            account.setAccountNumber(resultSet.getLong("id"));
            person.setAccount(account);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return person;
    }

    public ArrayList<Person> getAllPerson() {
        //End
        String getSql = "SELECT id,accountType,name , lastname, nationalcode,phonNumber,address,passwordForATM,secondPassword,tr,inventory FROM person;";
        ArrayList<Person> list = new ArrayList<Person>();
        try {
            ResultSet resultSet = statement.executeQuery(getSql);
            while (resultSet.next()) {
                Person person = new Person();
                Account account = new Account();
//id  ,accountType ,name , lastName,nationalCode TEXT,phoneNumber TEXT,address TEXT ,passwordForATM TEXT ,secondPassword TEXT, transaction TEXT ,inventory TEXT );";
                account.setAccountNumber(resultSet.getLong(0));
                account.setAccountType(resultSet.getString(1));
                account.setPasswordForATM(resultSet.getString(7));
                account.setSecondPassword(resultSet.getString(8));
                account.setInventory(resultSet.getString(10));
                account.setTransactions(readAllTransaction(account.getAccountNumber()));


                person.setAccount(account);
                person.setName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
                person.setNationalNumber(resultSet.getLong(4));
                person.setPhoneNumber((resultSet.getLong(5)));
                person.setAddress(resultSet.getString(6));
                ;

                list.add(person);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //  close();
        return list;

    }
    public void upDateInventory(long ID,String inventory){
        //TODO
    }
    public void updatePasswordForATM(long id, String password) {
//TODO

    }
    public void updateSecondPassword(String newPassword , long ID){
        //TODO
    }

//    class ObjToDB {
//        String name;
//        String lastName;
//
//        public String toString() {
//            return "[" + name + "  " + lastName + "]";
//        }
//    }

    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new DBHelper();
    }
}

