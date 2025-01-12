package giava.math.parser;

import giava.math.GMath;
import giava.math.exceptions.MathSyntaxError;

public non-sealed class FunctionNode implements Node {
    private Node argument;
    private String function;

    public FunctionNode(Node argument, String function){
        this.argument = argument;
        this.function = function;
    }

    @Override
    public double evaluate() throws MathSyntaxError {
        double arg;
        switch(function){
            case "sin": return Math.sin(argument.evaluate());
            case "cos": return Math.cos(argument.evaluate());
            case "tan": return Math.tan(argument.evaluate());
            case "√":
                arg = argument.evaluate();
                if(arg >= 0) return Math.sqrt(arg);
                else throw new MathSyntaxError("Negative value (" + arg + ") passed to √");
            case "ln":
                arg = argument.evaluate();
                if(arg > 0) return Math.log(arg);
                else throw new MathSyntaxError("Non-positive value (" + arg + ") passed to ln");
            case "log":
                arg = argument.evaluate();
                if(arg > 0) return Math.log10(arg);
                else throw new MathSyntaxError("Non-positive value (" + arg + ") passed to log");
            case "fact":
                arg = argument.evaluate();
                if(arg >= 0) return GMath.factorial(arg);
                else throw new MathSyntaxError("Negative value (" + arg + ") passed to !");
            default: throw new MathSyntaxError(function + " not supported");
        }
    }
}