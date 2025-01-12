package giava.math.parser;

public non-sealed class NumberNode implements Node {
    private double value;

    public NumberNode(double value){
        this.value = value;
    }

    @Override
    public double evaluate(){
        return value;
    }
}