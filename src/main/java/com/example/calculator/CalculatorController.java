package com.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class CalculatorController {
    @FXML
    private TextField operand1Field;
    @FXML
    private TextField operand2Field;
    @FXML
    private TextField operatorField;
    @FXML
    private TextField resultField;
    @FXML
    private Button addButton;
    @FXML
    private Button subtractButton;
    @FXML
    private Button multiplyButton;
    @FXML
    private Button divideButton;
    @FXML
    private Button exponentiationButton;
    @FXML
    private Button moduloButton;
    @FXML
    private Button floorDivisionButton;
    @FXML
    private Button ggtButton;
    @FXML
    private Button resetButton;

    private final CalculatorModel model = new CalculatorModel();

    public void initialize() {
        // Initialize reset button
        resetButton.setOnAction(e -> reset());

        // Initialize operator buttons
        addButton.setOnAction(e -> calculate("+"));
        subtractButton.setOnAction(e -> calculate("-"));
        multiplyButton.setOnAction(e -> calculate("*"));
        divideButton.setOnAction(e -> calculate("/"));
        exponentiationButton.setOnAction(e -> calculate("^"));
        moduloButton.setOnAction(e -> calculate("%"));
        floorDivisionButton.setOnAction(e -> calculate("//"));
        ggtButton.setOnAction(e -> calculate("ggt"));

        // Set text fields number only
        addChangeListener(operand1Field);
        addChangeListener(operand2Field);

        // Add event handlers for fields
        addEventHandlerOperands(operand1Field);
        addEventHandlerOperands(operand2Field);

        // Add event handlers for buttons
        addEventHandlerOperator(operand1Field);
        addEventHandlerOperator(operand2Field);

    }

    private void addChangeListener(TextField operandField) {
        ArrayList<Character> validCharacters = new ArrayList<>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-'));
        operandField.textProperty().addListener((observable, oldValue, newValue) -> {
            // If change contains illegal character reset the field to old value
            for (int i = 0; i < newValue.length(); i++) {
                if (!validCharacters.contains(newValue.charAt(i))) {
                    operandField.setText(oldValue);
                    break;
                }
            }

            // check for multiple decimal points
            int count = 0;
            for (int i = 0; i < newValue.length(); i++) {
                // Count the numbers of decimal points
                if (newValue.charAt(i) == '.') count++;

                // If there is more than one decimal point delete it
                if (count > 1) {
                    operandField.setText(oldValue);
                    break;
                }
            }

            // Check for minus sign
            for (int i = 0; i < newValue.length(); i++) {
                // If there is more than one minus sign delete it
                if (newValue.charAt(i) == '-' && i != 0) {
                    operandField.setText(oldValue);
                    break;
                }
            }
        });
    }

    private void addEventHandlerOperands(TextField operandField) {
        operandField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (!operatorField.getText().equals("")) {
                calculate(operatorField.getText());
            }
        });
    }

    private void addEventHandlerOperator(TextField operandField) {
        operandField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            // Create a string of the pressed key
            String text = event.getText();

            // Check if shift + key pressed
            if (event.isShiftDown()) {
                // Check if key pressed is a valid operator
                switch (event.getText()) {
                    case "+" -> text = "*";
                    case "7" -> text = "/";
                    case "5" -> text = "%";
                }
            }

            // Check if key pressed is a valid operator
            if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/") || text.equals("%")) {
                // Set operator field to pressed key
                operatorField.setText(text);
                // Set focus to second operand field
                operand2Field.requestFocus();
                // Calculate
                calculate(text);
            }
        });
    }

    private void calculate(String operator) {
        if (areCharactersLegal(operand1Field) && areCharactersLegal(operand2Field)) {
            // Set operator field
            operatorField.setText(operator);

            // Set operands in model
            model.setOperand1(Double.parseDouble(operand1Field.getText()));
            model.setOperand2(Double.parseDouble(operand2Field.getText()));

            // Calculate in model
            switch (operator) {
                case "+" -> model.add();
                case "-" -> model.subtract();
                case "*" -> model.multiply();
                case "/" -> model.divide();
                case "^" -> model.exponent();
                case "%" -> model.modulo();
                case "//" -> model.floorDivision();
                case "ggt" -> model.ggt();
            }

            // Set result field to result of model
            resultField.setText(Double.toString(model.getResult()));
        }
    }

    private boolean areCharactersLegal(TextField operandField) {
        // Check if operand fields contain illegal characters for operation ("", ".", "-")
        return !operandField.getText().equals("") && !operandField.getText().equals(".") && !operandField.getText().equals("-") && !operandField.getText().equals("-.");
    }

    private void reset() {
        operand1Field.clear();
        operand2Field.clear();
        resultField.clear();
        operatorField.clear();
    }

}
