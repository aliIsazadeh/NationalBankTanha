package Extras;

import java.util.ArrayList;

public class NumToText {
    long number;

    public NumToText(long number) {
        this.number = number;
    }

    private String textOfNumber(int number) {
        String result = "";
        String[] text100_900 = {"", "صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشت صد", "نهصد"};
        String[] text20_90 = {"", "", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"};
        String[] text10_19 = {"ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هیجده", "نوزده"};
        String[] text1_9 = {"", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه"};
        int[] digits = {number % 10, number / 10 % 10, number / 100 % 10};

        result += text100_900[digits[2]];
        if (((digits[0] != 0 || digits[1] != 0)) && (digits[2] != 0))
            result += " و ";
        result += text20_90[digits[1]];
        if (digits[1] != 1) {
            if ((digits[0] != 0) && (digits[1] != 0))
                result += " و ";
            result += text1_9[digits[0]];
        } else {
            result += text10_19[digits[0]];
        }
        return result;
    }

    public String getText() {
        int a = 0;
        String[] word = {"", " هزار", " میلیون", " میلیارد", " هزار میلیارد", " میلیون میلیارد", " میلیارد میلیارد"};
        ArrayList <String> result = new ArrayList <>();
        while (number > 0) {
            String string = textOfNumber((int) (number % 1000));
            String add = !(string.equals("")) ? word[a] : "";
            result.add(string + add);
            System.out.println(string + add);
            number /= 1000;
            a++;
        }
        System.out.println(result);
        String output = "";
        for (int i = 0; i < result.size() - 1; i++) {
            output = result.get(i) + output;

            if ((!(result.get(i + 1).length() == 1)))
                output = " و " + output;
        }
        output = result.get(result.size() - 1) + output;
        if ((output.charAt(output.length() - 2) == 'و') && (output.charAt(output.length() - 1) == ' ') && (output.charAt(output.length() - 3) == ' '))
            output = output.substring(0, output.length() - 1);
        return output;
    }
}

