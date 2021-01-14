/**
 * Input: A string
 * Output: String in reverse order
 */

import java.util.*;

public class MidTerm_Reverse {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String normal = scanner.nextLine();
		char[] normalchar = normal.toCharArray();
		for (int i = normalchar.length-1; i >= 0; i--) {
			System.out.print(normalchar[i]);
		}
		scanner.close();
	}

}
