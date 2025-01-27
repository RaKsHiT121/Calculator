Calculator Application

Overview

This is a Java-based GUI calculator application built using Swing. The calculator supports basic arithmetic operations along with advanced features such as power, parentheses for grouping, and real-time input manipulation (e.g., delete and clear). It is designed with an intuitive layout and responsive display.

Features

Basic Arithmetic Operations: Addition (+), Subtraction (-), Multiplication (*), Division (/).

Advanced Operation: Exponentiation (^).

Parentheses Support: Allows grouping of expressions using ( and ).

Decimal Support: Handles floating-point numbers.

Input Manipulation:

DEL: Deletes the last entered character.

CLR: Clears the entire input.

Evaluation Button: = to calculate the result of the expression.

Error Handling: Displays "Error" for invalid expressions.

Button Layout

The calculator's buttons are organized as follows:

9   8   7   +
6   5   4   -
3   2   1   *
0   DEL CLR  /
(   )   .   ^
=   

**How to Use**

Input Numbers and Operators:

Use the number buttons (0-9) and operation buttons (+, -, *, /, ^) to create expressions.

Use ( and ) for grouping expressions.

Modify Input:

Use DEL to delete the last character.

Use CLR to clear the entire input.

Evaluate the Expression:

Press = to compute the result of the entered expression.

Decimal Numbers:

Use the . button to input floating-point numbers.

How to Run

Prerequisites:

Ensure you have Java Development Kit (JDK) installed on your system.

Set up javac and java commands in your environment's PATH.

Save the Code:

Copy the provided Java code and save it as Calculator.java.

Compile the Code:

Open a terminal or command prompt.

Navigate to the directory where Calculator.java is saved.

Run the following command to compile the program:

javac Calculator.java

Run the Application:

After successful compilation, run the program using:

java Calculator

Interacting with the Calculator:

A window will open displaying the calculator.

Enter expressions using the buttons and evaluate the results.

Development

Tools and Libraries

Java Swing: Used for building the graphical user interface (GUI).

JDK Version: Tested on JDK 11 and above.

Folder Structure

No additional files or dependencies are required. The project is contained within a single file Calculator.java.

Future Enhancements

Add support for trigonometric functions (e.g., sin, cos, tan).

Enable history tracking of previous calculations.

Support keyboard input for faster interaction.

Add themes for better user experience.

Contributing

Contributions are welcome! Feel free to fork the repository, make improvements, and submit a pull request.

License

This project is released under the MIT License.

Author

Rakshit Bhatt
