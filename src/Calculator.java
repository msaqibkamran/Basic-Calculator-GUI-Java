import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Calculator class for implementing all functionalities for calculator
public class Calculator implements ActionListener{
    private JButton mod;
    private JButton reciprocal;
    private JButton square;
    private JButton underroot;
    private JPanel calculator_panel;
    private JButton CE;
    private JButton clear;
    private JButton backspace;
    private JButton divide;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton multiply;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton minus;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton addition;
    private JButton invert;
    private JButton zero;
    private JButton decimal;
    private JButton calculate;
    private JTextField showInputScreen;
    private JTextField takeInputDisplayOutput;
    private String operand1;
    private String operand2;
    private String operator;
    private Double result;
    private int operatorFlag;

    // default constructor + binding all buttons and their listeners with it
    public Calculator() {
        operand1 = "";
        operand2 = "";
        operator = "";
        result = 0.0;
        operatorFlag = 0;

        addition.addActionListener(this::actionPerformed);
        minus.addActionListener(this::actionPerformed);
        multiply.addActionListener(this::actionPerformed);
        divide.addActionListener(this::actionPerformed);
        mod.addActionListener(this::actionPerformed);
        underroot.addActionListener(this::actionPerformed);
        square.addActionListener(this::actionPerformed);
        reciprocal.addActionListener(this::actionPerformed);


        decimal.addActionListener(this::actionPerformed);
        zero.addActionListener(this::actionPerformed);
        one.addActionListener(this::actionPerformed);
        two.addActionListener(this::actionPerformed);
        three.addActionListener(this::actionPerformed);
        four.addActionListener(this::actionPerformed);
        five.addActionListener(this::actionPerformed);
        six.addActionListener(this::actionPerformed);
        seven.addActionListener(this::actionPerformed);
        eight.addActionListener(this::actionPerformed);
        nine.addActionListener(this::actionPerformed);

        backspace.addActionListener(this::actionPerformed);
        invert.addActionListener(this::actionPerformed);


        CE.addActionListener(this::actionPerformed);
        clear.addActionListener(this::actionPerformed);
        showInputScreen.addActionListener(this::actionPerformed);
        takeInputDisplayOutput.addActionListener(this::actionPerformed);

        calculate.addActionListener(this::actionPerformed);

    }

    // fucntion to perfrom basice arithemetic operations like addition, subtraction, division, multiplication
    // and return the result after calculations
    public Double performOperation()
    {
        result = 0.0;
        operatorFlag = 0;
        try
        {
            if(this.operator.equals("+"))
            {
                result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
            }
            else if(this.operator.equals("-"))
            {
                result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
            }
            else if(this.operator.equals("*"))
            {
                result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
            }
            else if(this.operator.equals("/"))
            {
                try {
                    result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                }
                catch (ArithmeticException e) {
                    takeInputDisplayOutput.setText("Divided by zero not possible!");
                    System.out.println("Divided by zero not possible!");
                }
            }
        }
        catch(Exception e)
        {
            showInputScreen.setText("invalid input!");

        }

        return result;

    }


    // method to handle actions of each button
    // on pressing that particular button
    // and behaves accordingly
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton buttonPressed = (JButton)e.getSource();
        // to clear the screen buttons
        if(buttonPressed == CE || buttonPressed == clear)
        {
            operand1 = "";
            operand2 = "";
            operator = "";
            result = 0.0;
            operatorFlag = 0;
            takeInputDisplayOutput.setText("");
            showInputScreen.setText("");
        }

        // = button to calcuate the result
        else if(buttonPressed == calculate)
        {
            showInputScreen.setText(operand1 + operator + operand2);
            result = performOperation();
            takeInputDisplayOutput.setText(result.toString());
            operator = "";
            operand1 = result.toString();
            operand2 = "";
        }

        // check for other operator/ operand1/ operand2
        else
        {
            // to handle 1/x button functionality
            if(buttonPressed == reciprocal)
            {
                try
                {
                    operator = buttonPressed.getText();
                    showInputScreen.setText(operator + " of " + operand1);
                    Double num = Double.parseDouble(operand1);
                    num = 1/num;
                    operand1 = num.toString();
                    takeInputDisplayOutput.setText(operand1);
                }
                catch(Exception e1)
                {
                    showInputScreen.setText("Please first enter number then press this button");
                }
            }

            // to 0-9 buttons functionality and .
            else if ((buttonPressed.getText().charAt(0) >= '0' && buttonPressed.getText().charAt(0) <= '9') || buttonPressed.getText().charAt(0) == '.')
            {
                String currentText = buttonPressed.getText();
                if(operator == "")
                {
                    operand1 += currentText;
                }
                else {
                    operand2 += currentText;
                }

                takeInputDisplayOutput.setText(operand1 + operator + operand2);

            }

            // to handle <- button functionality
            else if(buttonPressed == backspace)
            {
                try
                {
                    if(operator == "")
                    {
                        operand1 = operand1.substring(0, operand1.length() - 1);
                        takeInputDisplayOutput.setText(operand1 + operator + operand2);
                    }
                    else {

                        operand2 = operand2.substring(0, operand2.length() - 1);
                        takeInputDisplayOutput.setText(operand1 + operator + operand2);
                    }
                }

                catch(Exception e1)
                {
                    showInputScreen.setText("invalid operation!");
                }
            }

            // to handle invert sign button functionality
            else if(buttonPressed == invert)
            {
                try
                {
                    Double num = 0.0;
                    if(operator == "")
                    {
                        showInputScreen.setText(operand1 + operator + operand2);
                        num = Double.parseDouble(operand1);
                        num = -num;
                        operand1 = num.toString();
                        takeInputDisplayOutput.setText(operand1);
                    }
                    else {
                        showInputScreen.setText(operand1 + operator + operand2);
                        num = Double.parseDouble(operand2);
                        num = -num;
                        operand2 = num.toString();
                        takeInputDisplayOutput.setText(operand2);
                    }
                }
                catch(Exception e1)
                {
                    showInputScreen.setText("Please first enter number then press this button");
                }
            }

            // to handle percentage button functionality
            else if(buttonPressed == mod)
            {
                try
                {
                    operator = buttonPressed.getText();
                    showInputScreen.setText(operand1 + operator + operand2);
                    Double num = Double.parseDouble(operand1);
                    num /= 100;
                    operand1 = num.toString();
                    takeInputDisplayOutput.setText(operand1);
                }

                catch(Exception e1)
                {
                    showInputScreen.setText("Please first enter number then press this button");
                }
            }

            // to handle square button functionality
            else if(buttonPressed == square)
            {
                try
                {
                    operator = buttonPressed.getText();
                    showInputScreen.setText(operator + " of " + operand1);
                    Double num = Double.parseDouble(operand1);
                    num = num * num;
                    operand1 = num.toString();
                    takeInputDisplayOutput.setText(operand1);
                }

                catch(Exception e1)
                {
                    showInputScreen.setText("Please first enter number then press this button");
                }
            }

            // to handle underoot button functionality
            else if(buttonPressed == underroot)
            {
                try
                {
                    operator = buttonPressed.getText();
                    showInputScreen.setText(operator + " of " + operand1);
                    Double num = Double.parseDouble(operand1);
                    num = Math.pow(num, 0.5);
                    operand1 = num.toString();
                    takeInputDisplayOutput.setText(operand1);
                }

                catch(Exception e1)
                {
                    showInputScreen.setText("Please first enter number then press this button");
                }
            }

            else
            {
                // to check for operator and set the operator variavle accordingly
                if(operator == "" || operand2 == "")
                {
                    operatorFlag = 1;
                    if((buttonPressed == addition))
                    {
                        operator = "+";
                    }
                    else if(buttonPressed == minus)
                    {
                        operator = "-";
                    }
                    else if(buttonPressed == divide)
                    {
                        operator = "/";
                    }
                    else if(buttonPressed == multiply)
                    {
                        operator = "*";
                    }
                }

                // make calculations and store in first operand
                else
                {
                    result = performOperation();
                    operand1 = result.toString();
                    operator = buttonPressed.getText();
                    operand2 = "";
                }
                takeInputDisplayOutput.setText(operand1 + operator + operand2);
            }
        }
    }

    // Main function to make a frame and display the calculator in it
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Calculator");
        jFrame.setContentPane(new Calculator().calculator_panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);


        // Make instance of calculator
        Calculator calculator = new Calculator();
    }
}
