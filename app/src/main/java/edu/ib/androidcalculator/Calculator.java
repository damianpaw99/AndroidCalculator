package edu.ib.androidcalculator;

import org.mariuszgromada.math.mxparser.Function;

/**
 * Class calculating expressions
 */
public class Calculator {
    private StringBuilder expression = new StringBuilder();
    private String lastValue="";
    /**
     * Method calculating value of expression
     * @return Value of expression
     */
    public String calculate() {
        String c=expression.toString();
        if(c.contains("ans") && !lastValue.isEmpty()){
            c=c.replace("ans",lastValue);
        }
        Function f = new Function("f(x)=" + c);

        String outputValue = "";

        if (f.checkSyntax()) {
            outputValue = String.valueOf(f.calculate());
            lastValue=outputValue;
        } else {
            outputValue = "Syntax error";
        }
        return outputValue;
    }
    /**
     * Method calculating value of expression
     * @param ansValue Last answer value
     * @return Value of expression
     */
    public String calculate(double ansValue) {
        String outputValue = "";

        //checking ans

        String text = expression.toString().replace("ans",String.valueOf(ansValue));
        Function f = new Function("f(x)=" + text);

        if (f.checkSyntax()) {
            outputValue = String.valueOf(f.calculate());
        } else {
            outputValue = "Syntax error";
        }
        return outputValue;
    }

    /**
     * Method removing last part of the expression
     */
    public void removeExpresion() {
        String s5 = expression.substring(Math.max(0, expression.length() - 5));
        String s4 = expression.substring(Math.max(0, expression.length() - 4));
        String s3 = expression.substring(Math.max(0, expression.length() - 3));
        String s2 = expression.substring(Math.max(0, expression.length() - 2));
        if (s5.contains("sqrt(")) {
            expression.setLength(Math.max(0, expression.length() - 5));
        } else if (s4.contains("ctg(") || s4.contains("cos(") || s4.contains("sin(") || s4.contains("log(") || s4.contains("min(") || s4.contains("max(")) {
            expression.setLength(Math.max(0, expression.length() - 4));
        } else if (s3.contains("tg(") || s3.contains("ln(") || s3.contains("ans")) {
            expression.setLength(Math.max(0, expression.length() - 3));
        } else if (s2.contains("pi")) {
            expression.setLength(Math.max(0, expression.length() - 2));
        } else {
            expression.setLength(Math.max(0, expression.length() - 1));
        }
    }

    /**
     * Method adding String to expression
     * @param expresion Expression
     */
    public void addExpresion(String expresion) {
        this.expression.append(expresion);
    }

    /**
     * Method getting expression
     * @return Expression
     */
    public StringBuilder getExpression() {
        return expression;
    }

    /**
     * Method setting expression
     * @param expression Expression
     */
    public void setExpression(StringBuilder expression) {
        this.expression = expression;
    }

    /**
     * Overridden method to String
     * @return Expression as String
     */
    @Override
    public String toString() {
        String output=expression.toString();
        output=output.replace("sqrt","√");
        output=output.replace("pi","π");
        return output;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }
}
