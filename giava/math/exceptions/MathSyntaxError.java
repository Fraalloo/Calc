package giava.math.exceptions;

public class MathSyntaxError extends Exception {
    public MathSyntaxError(){
        super("Math Syntax Error");
    }

    public MathSyntaxError(String msg){
        super("Math Syntax Error: " + msg);
    }
}
