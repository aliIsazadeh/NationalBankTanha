package Extras;

public class SecondPassProducer {


    public String secondPass() {
        String pass = null;
        pass = String.valueOf(Math.round((Math.random() + 1) * 1398));

        return pass;
    }


}
