package Extras;

public class createCardNumber {

    public  String  createCardNumber(){

      long randomNumber  =  (long) ((Math.random())* Long.parseLong(("9999999999")) );

        String a = randomNumber+"";

        //603799 is card CTE amount

        String CardID = "603799" +  a;




        String s = "";
        for (int i = 0; i <16 ; i++) {
            s += CardID.charAt(i)+"";
            if(i==3 || i== 7 || i==11 ||i==15 )
            {
                s += " ";

            }
        }

        System.out.println(s);

        return s;
    }



}
