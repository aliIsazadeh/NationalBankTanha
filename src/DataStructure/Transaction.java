package DataStructure;

public class Transaction {
    private String typeOfTransaction;
    private String serialOfTransaction;
    private String DateOfTransaction;
    private String inventoryOfTransaction;

    public String getSerialOfTransaction() {
        return serialOfTransaction;
    }

    public void setSerialOfTransaction(String serialOfTransaction) {
        this.serialOfTransaction = serialOfTransaction;
    }

    public String getDateOfTransaction() {
        return DateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        DateOfTransaction = dateOfTransaction;
    }

    public String getInventoryOfTransaction() {
        return inventoryOfTransaction;
    }

    public void setInventoryOfTransaction(String inventoryOfTransaction) {
        this.inventoryOfTransaction = inventoryOfTransaction;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }
}
