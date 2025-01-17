package giava.math;

import giava.math.exceptions.MathException;
import giava.math.exceptions.MathBaseException;
import giava.math.exceptions.MathNumberBaseException;

public class GMath{
    public final static int MIN_BASE = 2;
    public final static int MAX_BASE = 36;

    private GMath(){}

    public static double factorial(double n){
        if(n < 0) throw new IllegalArgumentException("Negative value");
        if(n == 0) return 1;
        return n * factorial(n - 1);
    }

    public static boolean isNumber(String token){
        return token == null || token.isEmpty() ?  false : token.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isConstant(String token){
        return token == null || token.isEmpty() ? false : token.matches("e|π");
    }

    public static boolean isValidBase(int base){
        return base >= MIN_BASE && base <= MAX_BASE;
    }

    public static boolean isValidNumber(String num, int base) throws MathNumberBaseException {
        if(!isValidBase(base)) throw new MathNumberBaseException();
        String regex = base >= 2 && base <= 10 ? "^[0-" + (char)('0' + base - 1) + "]+$" : "^[0-9A-" + (char)('A' + base - 11) + "]+$";
        return num.toUpperCase().matches(regex);
    }

    private static int toDecimal(String num, int base) throws MathException {
        if(!isValidBase(base)) throw new MathBaseException();

        int decimalValue = 0;
        int len = num.length();

        for(int i = 0; i < len; i++){
            char c = Character.toUpperCase(num.charAt(i));
            int digitValue;

            if(c >= '0' && c <= '9') digitValue = c - '0';
            else digitValue = c - 'A' + 10;

            decimalValue += Math.pow(base, len - i - 1) * digitValue;
        }

        return decimalValue;
    }

    private static String fromDecimal(int decimalValue, int base) throws MathException {
        if(!isValidBase(base)) throw new MathBaseException();
        if((!isValidNumber(Integer.toString(decimalValue), 10))) throw new MathNumberBaseException();
        if(decimalValue == 0) return "";
        
        int remainder = decimalValue % base;
        char digit = remainder < 10 ? (char)('0' + remainder) : (char)('A' + remainder - 10);

        return fromDecimal(decimalValue / base, base) + digit;
    }

    public static String convert(String num, int inBase, int outBase) throws MathException {
        if(!isValidBase(inBase) || !isValidBase(outBase)) throw new MathBaseException();
        if(!isValidNumber(num, inBase)) throw new MathNumberBaseException();

        return fromDecimal(toDecimal(num, inBase), outBase);
    }
}