package com.example.demo;

import java.util.HashMap;

public class IntCalculator {
    //One arg interface
    private interface IntOp {
        int op(int a);
    }
    //Two arg interface
    private interface IntOp2 {
        int op(int a, int b);
    }
    //Lambda interface definitions
    private IntOp  sqrt = a -> {System.out.print("\"warning integer square\" "); return (int) Math.sqrt(a); };
    private IntOp2 power = (a, b)  -> (int) Math.pow(a, b);
    private IntOp2 addition = Integer::sum;
    private IntOp2 subtraction = (a, b) -> a - b;
    private IntOp2 multiplication = (a, b) -> a * b;
    private IntOp2 division = (a, b) -> {System.out.print("\"warning integer divide\" "); return a / b; };
    //Map of symbols to interfaces
    private HashMap<String, Object> opMethod  = new HashMap<>() {{
        put("^", power);
        put("+", addition);
        put("-", subtraction);
        put("*", multiplication);
        put("/", division);
        put("sqrt", sqrt);
    }};
    //Signature for one arg calculator
    private void calculate(int a, String op) {
        IntOp method = (IntOp) opMethod.get(op);
        System.out.println(op + "(" +  a + ")" + " = " + method.op(a));
    }
    //Signature for two arg calculator
    private void calculate(int a, String op, int b ) {
        IntOp2 method = (IntOp2) opMethod.get(op);
        System.out.println(a + " " +  op + " " +  b + " = " + method.op(a, b));
    }
    //Tester
    public static void main(String[] args) {
        IntCalculator icalc = new IntCalculator();

        icalc.calculate(10, "+", 5);
        icalc.calculate(10, "-", 5);
        icalc.calculate(10, "*", 5);
        icalc.calculate(10, "/", 5);
        icalc.calculate(10, "^", 5);
        icalc.calculate(10, "sqrt");
    }
}
