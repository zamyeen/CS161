import java.util.Scanner;

/**
 * Input a string. 
 * Output: Print "yes" if that string is a "palindrome" (reads exactly the same backwards and forward). 
 */
public class MidTerm_Palindrome_Check {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[] Num = scanner.nextLine().toCharArray();
		int sum = 0;
		for (int i = 0; i < Num.length; i++) {
			if(Num[i] != Num[Num.length-1-i]) {
				sum = sum + 1;
			}
		}
		if(sum == 0) {
			System.out.println("This number is a palindrome");
		}
		else {
			System.out.println("The number is not a palindrome");
		}
		scanner.close();
	}
}
