package com.example.demo;

import java.util.HashMap;

public class IntCalculator {

    public enum OPERATOR {PLUS, MINUS, MULTIPLY, DIVIDE}

    private interface MathOp {
        int op(int a, int b);
    }
    private MathOp addition = (a, b) -> a + b;
    private MathOp subtraction = (a, b) -> a - b;
    private MathOp multiplication = (a, b) -> a * b;
    private MathOp division = (a, b) -> a / b;

    private HashMap<OPERATOR, Object> opSymbol  = new HashMap<>() {{
        put(OPERATOR.PLUS, "+");
        put(OPERATOR.MINUS, "-");
        put(OPERATOR.MULTIPLY, "*");
        put(OPERATOR.DIVIDE, "/");
    }};

    private HashMap<OPERATOR, Object> opMethod  = new HashMap<>() {{
        put(OPERATOR.PLUS, addition);
        put(OPERATOR.MINUS, subtraction);
        put(OPERATOR.MULTIPLY, multiplication);
        put(OPERATOR.DIVIDE, division);
    }};

    private void intCalculator(int a, OPERATOR op, int b ) {
        MathOp mathop = (MathOp) opMethod.get(op);
        System.out.println(a + " " +  opSymbol.get(op) + " " +  b + " = " + mathop.op(a, b));
    }

    public static void main(String args[]) {
        IntCalculator tester = new IntCalculator();

        tester.intCalculator(10, OPERATOR.PLUS, 5);
        tester.intCalculator(10, OPERATOR.MINUS, 5);
        tester.intCalculator(10, OPERATOR.MULTIPLY, 5);
        tester.intCalculator(10, OPERATOR.DIVIDE, 5);
    }
}
