package Extras;

public class CreateCardNumber {

    public static String  createCardNumber(){

        Double x =Math.random();

      long randomNumber  =  (long) ((x)* Long.parseLong(("10000000000")) );
     //   System.out.println(x);
        String a = randomNumber+"";

        //603799 is card CTE amount

        String CardID = "603799" +  a;



     //   System.out.println(CardID);
        String s = "";
        for (int i = 0; i <16 ; i++) {
            s += CardID.charAt(i)+"";
            if(i==3 || i== 7 || i==11 )
            {
                s += "-";

            }
        }

        System.out.println(s);

        return s;
    }

    public static void main(String[] args) {
        createCardNumber();
    }


}
