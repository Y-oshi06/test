import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class main {
	public static void main(String[] args) {
		String a =getnum(); //式の入力
		String b =Gp(a);
    
     
		Stack<Double> num12 = new Stack<>();
		
		
		String[] tokens = b.split(""); //tokenを区切る
		
		for(String token :tokens) {
			if(isNumeric(token)) {//数字の場合
				num12.push(Double.parseDouble(token));
			}else if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){//演算子の場合
				double operand2 = num12.pop();
				double operand1 = num12.pop();
				double result =performeOperation(token,operand1,operand2);
				num12.push(result);
				System.out.println(result);
			}
		}
		System.out.println("結果は" + num12.pop());
}
//式の計算
		private static boolean isNumeric(String str) {
			try {
				double d = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				return false;
			}
			return true;
		}
		
		private static double performeOperation(String operator,double operand1,double operand2) {

			switch (operator){
			case "+":
				return operand1 + operand2;
			case "-":
				return operand1 - operand2;
			case "*":
				return operand1 * operand2;
			case "/" :
				return operand1 / operand2;
			case "÷" :
				return operand1 / operand2;
			default://それ以外の場合
			    throw new IllegalArgumentException("無効な演算子です: " + operator);
			
			
		}
		
		
	}
//式の入力
		private  static String getnum() {
			System.out.println("式を入れてください");
			Scanner siki = new Scanner(System.in);
			String siki2 = siki.nextLine();
			return siki2;
		}

	
//逆ポーランド式に変換
		private static String Gp(String a) {
			char[] sikiList = a.toCharArray();
			
			StringBuilder result = new StringBuilder(sikiList.length);
			Deque<Character> stack = new ArrayDeque();
			
			for(char token : sikiList) {
				switch(token) {
				case '+':
				case '-':
					while (!stack.isEmpty()) {
						char c = stack.getFirst();
						if(c == '*' || c== '/') {
							result.append(stack.removeFirst());
						}else {
							break;
						}
				    stack.addFirst(token);
				    break;
					}
					
				case '*':
				case '/':
				case '(':
					stack.addFirst(token);
					break;
					
				case ')':	
					Deque<Character> workstack = new ArrayDeque<>();
					
					char c = stack.removeFirst();
					if(c != '(') {
						workstack.addFirst(c);
					}
					
					while(!workstack.isEmpty()) {
						result.append(workstack.removeFirst());
					}
					stack.removeFirst();//(を消す					
					break;
					
				default:
					result.append(token);
					break;
					
				}
			}
			while (!stack.isEmpty()) { //からになるまでstackにいれる
				result.append(stack.removeFirst());
			}
			return result.toString();
		}
}
//5+7*3

