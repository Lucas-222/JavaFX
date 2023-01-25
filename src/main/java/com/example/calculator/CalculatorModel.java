package com.example.calculator;

public class CalculatorModel {
    private double operand1;
    private double operand2;
    private double result;

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void add() {
        result = operand1 + operand2;
    }

    public void subtract() {
        result = operand1 - operand2;
    }

    public void multiply() {
        result = operand1 * operand2;
    }

    public void divide() {
        if (operand2 != 0) {
            result = operand1 / operand2;
        }
    }

    public void exponent() {
        result = Math.pow(operand1, operand2);
    }

    public void modulo() {
        result = operand1 % operand2;
    }

    public void floorDivision() {
        result = (int) operand1 / (int) operand2;
    }

    public void ggt() {
        int operand1 = (int) this.operand1;
        int operand2 = (int) this.operand2;
        while (operand2 != 0) {
            int temp = operand2;
            operand2 = operand1 % operand2;
            operand1 = temp;
        }
        this.result = operand1;
    }

    public double getResult() {
        System.out.println("Result: " + result);
        return result;
    }
}
