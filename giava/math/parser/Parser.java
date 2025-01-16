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
        if(tokens.isEmpty() || tokens.getFirst().equals("")) throw new MathSyntaxError("Empty expression");
        Node left = parseFactor(tokens);
        
        while(!tokens.isEmpty() && tokens.getFirst().matches("(\\+|-)")){
            String operator = tokens.removeFirst();
            Node right = parseFactor(tokens);
            left = new BinaryOperatorNode(left, right, operator);
        }
        
        return left;
    }

    private static Node parseFactor(LinkedList<String> tokens) throws MathSyntaxError {
        Node left = parseAddend(tokens);
        
        while(!tokens.isEmpty() && tokens.getFirst().matches("×|÷|^|md")){
            String operator = tokens.removeFirst();
            Node right = parseAddend(tokens);
            left = new BinaryOperatorNode(left, right, operator);
        }
        
        return left;
    }

    private static Node parseAddend(LinkedList<String> tokens) throws MathSyntaxError {
        String token = tokens.removeFirst();
        
        if(isNumber(token)){
            return new NumberNode(Double.parseDouble(token));
        }else if(isConstant(token)){
            return new NumberNode(token.equals("e") ? Math.E : Math.PI);
        }else if(isFunction(token)){
            Node argument = parseAddend(tokens);
            return new FunctionNode(argument, token);
        }else if(token.equals("(")){
            Node node = parseExpression(tokens);
            String closing = tokens.removeFirst();
            if(closing == null || !closing.equals(")")) throw new MathSyntaxError("Parenthesis missmatch");
            return node;
        }
        
        throw new MathSyntaxError(token + " not recognized");
    }

    private static boolean isNumber(String token){
        return GMath.isNumber(token);
    }

    private static boolean isConstant(String token){
        return GMath.isConstant(token);
    }

    private static boolean isFunction(String token){
        if(token == null || token.isEmpty()) return false;
        return token.matches("(sin|cos|tan|ln|log|fact|√)");
    }
}