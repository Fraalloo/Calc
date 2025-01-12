package giava.math.parser;

import giava.math.exceptions.MathSyntaxError;

public non-sealed class AbstractSyntaxTree implements Node {
    private Node root;
    public final static double MIN_VALUE = 1e-10;

    public AbstractSyntaxTree(Node root){
        this.root = root;
    }

    @Override
    public double evaluate() throws MathSyntaxError {
        double ret = root.evaluate();
        return Math.abs(ret) < MIN_VALUE ? 0 : ret;
    }
}