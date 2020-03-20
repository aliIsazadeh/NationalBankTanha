package Extras;

import DataStructure.Transaction;

public class TransactionSerialProducer {


    public String serialProducer() {
        String serial = String.valueOf(Math.round(((Math.random() + 1) * 1398) + ((Math.random() + 1) * 1398)));


        return serial;
    }


}
