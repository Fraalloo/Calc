package giava.math.parser;

import giava.math.exceptions.MathSyntaxError;

public sealed interface Node permits 
    NumberNode,
    BinaryOperatorNode,
    FunctionNode,
    AbstractSyntaxTree
{
    double evaluate() throws MathSyntaxError;
}