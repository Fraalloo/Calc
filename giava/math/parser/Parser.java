package giava.math.parser;

import giava.datastr.LinkedList;
import giava.math.GMath;
import giava.math.exceptions.MathSyntaxError;

public class Parser{
    private Parser(){}

    public static AbstractSyntaxTree parse(LinkedList<String> tokens) throws MathSyntaxError {
        return new AbstractSyntaxTree(parseExpression(tokens));
    }

    private static Node parseExpression(LinkedList<String> tokens) throws MathSyntaxError {
        Node left = parseTerm(tokens);
        
        while(!tokens.isEmpty() && (tokens.getFirst().equals("+") || tokens.getFirst().equals("-"))){
            String operator = tokens.removeFirst();
            Node right = parseTerm(tokens);
            left = new BinaryOperatorNode(left, right, operator);
        }
        
        return left;
    }

    private static Node parseTerm(LinkedList<String> tokens) throws MathSyntaxError {
        Node left = parseFactor(tokens);
        
        while(!tokens.isEmpty() && (tokens.getFirst().equals("×") || tokens.getFirst().equals("÷") || tokens.getFirst().equals("^") || tokens.getFirst().equals("md"))){
            String operator = tokens.removeFirst();
            Node right = parseFactor(tokens);
            left = new BinaryOperatorNode(left, right, operator);
        }
        
        return left;
    }

    private static Node parseFactor(LinkedList<String> tokens) throws MathSyntaxError {
        String token = tokens.removeFirst();
        
        if(isNumber(token)){
            return new NumberNode(Double.parseDouble(token));
        }else if(isConstant(token)){
            return new NumberNode(token.equals("e") ? Math.E : Math.PI);
        }else if(isFunction(token)){
            Node argument = parseFactor(tokens);
            return new FunctionNode(argument, token);
        }else if(token.equals("(")){
            Node node = parseExpression(tokens);
            if(tokens.removeFirst() == null) throw new MathSyntaxError("Parenthesis missmatch");
            return node;
        }
        
        throw new MathSyntaxError(token + " not supported");
    }

    private static boolean isNumber(String token){
        return GMath.isNumber(token);
    }

    private static boolean isConstant(String token){
        if(token == null || token.isEmpty()) return false;
        return token.equals("e") || token.equals("π");
    }

    private static boolean isFunction(String token){
        if(token == null || token.isEmpty()) return false;
        return token.matches("(sin|cos|tan|ln|log||fact|√)");
    }
}