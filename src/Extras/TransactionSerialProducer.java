package Extras;

import DataStructure.Transaction;

public class TransactionSerialProducer {

    Transaction transaction = new Transaction();
    public  String serialProducer(){
        String serial = String.valueOf(((Math.random()+1)*1398)+((Math.random()+1)*1398));
        while (!(serial.equals(transaction.getSerialOfTransaction()))){
            serial = String.valueOf(((Math.random()+1)*1398)+((Math.random()+1)*1398));
        }

        return serial;
    }


}
