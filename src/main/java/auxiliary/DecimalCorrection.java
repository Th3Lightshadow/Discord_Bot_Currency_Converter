package auxiliary;

import java.math.BigDecimal;

public class DecimalCorrection {

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static String decimalCorrect(float input) {
        String temp = String.valueOf(input);
        char charString = temp.charAt(temp.length()-2);
        if (charString == '.') {
            temp = String.format("%s0", temp);
        }
        return temp;
    }
}
