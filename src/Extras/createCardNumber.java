package Extras;

public class createCardNumber {

    public static String  createCardNumber(){

      int randomNumber  =  (int) (Math.random()*999999);
        String a = randomNumber+"";

        //603799 is out CTE amount

        String CardID = "603799" +  a;

        return CardID;
    }

    public static void main(String[] args) {
        createCardNumber();
    }


}
