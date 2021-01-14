import java.util.*;
public class Test {

	public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	char[] input = scanner.nextLine().toCharArray();
	String Key = scanner.nextLine();
	String KeyRepeat = "";
	for (int i = 0; i < input.length/Key.length(); i++) {
		KeyRepeat += Key;
	}
	KeyRepeat += Key.substring(0, input.length%Key.length());
	System.out.println("KeyRepeat is: " + KeyRepeat);
	System.out.println(EncryptShift('J','P'));
	scanner.close();
	}
	
	public static char EncryptShift (char shift, char preshift) {
		char outshift = 'x';
		//Preshift: P ASCII Value: 80 Value needs to be: 88
		//Shift: J ASCII Value: 74 Value of Shift: 9
		outshift = (char)((int)(preshift)+((int)(shift)-65));
		//PJ
		return outshift;	
	}
	
}
