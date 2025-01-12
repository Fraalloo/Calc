package giava.math.parser;

import giava.math.exceptions.MathSyntaxError;

public non-sealed class BinaryOperatorNode implements Node {
    private Node left;
    private Node right;
    private String operator;

    public BinaryOperatorNode(Node left, Node right, String operator){
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double evaluate() throws MathSyntaxError {
        if((operator.equals("md") || operator.equals("÷")) && right.evaluate() == 0) throw new MathSyntaxError("division by zero");

        switch(operator){
            case "+": return left.evaluate() + right.evaluate();
            case "-": return left.evaluate() - right.evaluate();
            case "×": return left.evaluate() * right.evaluate();
            case "÷": return left.evaluate() / right.evaluate();
            case "md": return left.evaluate() % right.evaluate();
            case "^": return Math.pow(left.evaluate(), right.evaluate());
            default: throw new MathSyntaxError(operator + " not supported");
        }
    }
}