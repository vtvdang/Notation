/**
 * This class can manipulate and return infix and postfix expressions
 * 
 * @author Vivian Dang
 *
 */
public class Notation {

	/**
	 * @param the infix string to be converted 
	 * @return the postfix expression
	 * @throws InvalidNotationFormatException
	 * 
	 *  infixToPostfix to convert infix notation to
	 *  postfix notation that will take in a string and return a string,
	 *  
	 *  In the infixToPostfix method, you MUST use a queue for the internal
	 *  structure that holds the postfix solution. Then use the toString method of the
	 *  Queue to return the solution as a string.
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

		MyStack<Character> stack = new MyStack<Character>(infix.length());
		MyQueue<Character> solution = new MyQueue<Character>(infix.length());
		String finalSolution = "";

		try {
		    for (int i = 0; i < infix.length(); i++) {
		        char current = infix.charAt(i);
		        if (current != ' ') {
		            if (current >= '0' && current <= '9') {
		                solution.enqueue(current);
		            } else if (current == '(') {
		                stack.push(current);
		            } else if (current == '+' || current == '-' || current == '*' || current == '/') {
		                while (!stack.isEmpty() && stack.top() != '(' && 
		                       ((current == '+' || current == '-') ||
		                        (stack.top() == '*' || stack.top() == '/'))) {
		                    solution.enqueue(stack.pop());
		                }
		                stack.push(current);
		            } else if (current == ')') {
		                while (stack.top() != '(') {
		                    solution.enqueue(stack.pop());
		                }
		                stack.pop(); 
		            }
		        }
		    }
		    while (!stack.isEmpty())
		        solution.enqueue(stack.pop());

		    while (!solution.isEmpty())
		        finalSolution += solution.dequeue();

		    return finalSolution;
		

			
		} catch (StackUnderflowException e) {
			System.err.println("invalid format");
			throw new InvalidNotationFormatException();
			
		} catch (Exception e) {
			System.err.println("error");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param the postfix string to be converted 
	 * @return the infix expression
	 * @throws InvalidNotationFormatException
	 * 
	 * postfixToInfix to convert postfix notation to infix notation that will take in a
	 * string and return a string
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {

		MyStack<String> stack = new MyStack<String>(postfix.length());

		try {
			for (int i = 0; i < postfix.length();) {
			//If the current character in the infix is a space, ignore it.
			char current = postfix.charAt(i);
			if (current != ' ') {
				//If the current character is an operand, push it on the stack
				if (postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9')
					stack.push(postfix.charAt(i) + "");
				//If the current character is an operator,
				if (postfix.charAt(i) == '+' || postfix.charAt(i) == '-' || postfix.charAt(i) == '*'
						|| postfix.charAt(i) == '/') {
					//1. Pop the top 2 values from the stack
					String second = stack.pop();
					String first = stack.pop();
					//2. Create a string with 1st value and then the operator and then the 2nd value
					String str = first + postfix.charAt(i) + second;
					//3. Encapsulate the resulting string within parenthesis
					String finalStr = "{" + str + ")";
					//4. Push the resulting string back to the stack
					stack.push(finalStr);
				}
			}
			throw new InvalidNotationFormatException();
			}
		} catch (StackUnderflowException e) {
			System.err.println("invaid format");
			throw new InvalidNotationFormatException();
		} catch (Exception e) {
			System.err.println("error");
			e.printStackTrace();
		}

		return "";
	}
	
	/**
	 * @param the postfix string to be evaluated 
	 * @return the value of the expression
	 * @throws InvalidNotationFormatException
	 * 
	 * evaluatePostfix to evaluate the postfix
	 * expression. It will take in a string and return a double.
	 */
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException {
		MyStack<Double> stack = new MyStack<Double>(postfix.length());

		try {
		    for (int i = 0; i < postfix.length(); i++) {
		        char current = postfix.charAt(i);
		        if (current == ' ') {
		            continue; 
		        }
		        if (Character.isDigit(current)) {
		            stack.push(Double.parseDouble(String.valueOf(current)));
		        } 
		        else if (current == '+' || current == '-' || current == '*' || current == '/') {
		            if (stack.size() < 2) {
		                throw new InvalidNotationFormatException();
		            }

		            double second = stack.pop();
		            double first = stack.pop();
		            double result = 0;

		            switch (current) {
		                case '+':
		                    result = first + second;
		                    break;
		                case '-':
		                    result = first - second;
		                    break;
		                case '*':
		                    result = first * second;
		                    break;
		                case '/':
		                    result = first / second;
		                    break;
		            }

		            stack.push(result);
		        }
		    }
		    if (stack.size() != 1) {
		        throw new InvalidNotationFormatException();
		    }

		    return stack.pop(); 
		
		} catch (StackUnderflowException e) {
			System.err.println("invalid format");
			throw new InvalidNotationFormatException();
		} catch (Exception e) {
			System.err.println("error");
			e.printStackTrace();
		}
		return 0;
	}
}