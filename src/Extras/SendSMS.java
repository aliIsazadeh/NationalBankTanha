package Extras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class SendSMS {
    private final String USER_AGENT = "Mozilla/5.0";

    String phoneNumber = "";
    String message = "";


    SendSMS(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String send() throws Exception {
        try {
            String url = "https://raygansms.com/SendMessageWithCode.ashx?Username=a0forghani&Password=1346794613&Mobile=" + phoneNumber + "&Message=" + message;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            if (Integer.parseInt(response.toString()) > 2000)
                return "پیام با موفقیت ارسال شد.";
            else
                return response.toString();
        } catch (UnknownHostException e) {
            return "خطای اتصال به اینترنت";
        }


    }
}
