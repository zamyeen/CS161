/**
 * 
 */

import java.util.*;

public class VigenereCipher {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to Encrypt or Decrypt?");
		String DoE = scanner.nextLine();
		
		//Encrypt
		if(DoE.equals("Encrypt") || DoE.equals("ENCRYPT")) {
			System.out.println("What is your key?");
			String Key = scanner.nextLine().toUpperCase();
			System.out.println("Enter what you want to Encrypt (without spaces): ");
			char[] input = scanner.nextLine().toUpperCase().toCharArray();
			System.out.println(Encrypt(input, Key));
		}
		
		
		//Decrypt
		else if (DoE.equals("Decrypt") || DoE.equals("DECRYPT")) {
			
		}
		scanner.close();
	}

	public static String Encrypt (char[] input, String Key) {
		String output = "";
		//Creating the Repeating Key
		String KeyRepeat = "";
		for (int i = 0; i < input.length/Key.length(); i++) {
			KeyRepeat += Key;
		}
		KeyRepeat += Key.substring(0, input.length%Key.length());
		char[] Keys = KeyRepeat.toCharArray();
		for (int i = 0; i < Keys.length; i++) {
			output += EncryptShift(Keys[i],input[i]);
		}
		return output;
	}
	
	public static char EncryptShift (char shift, char preshift) {
		char outshift = 'x';
		//Preshift: P ASCII Value: 80 Value needs to be: 88
		//Shift: J ASCII Value: 74 Value of Shift: 9
		if((int)(preshift)+(int)(shift)-65 < 90) {
			outshift = (char)((int)(preshift)+((int)(shift)-65));
		}
		else{
			outshift = (char)((int)(preshift)+((int)(shift)-65 - 26));
		}
		//PJ
		return outshift;	
	}
	
	public static String Decrypt (char[] input, String Key) {
		String output = "";
		
		return output;
	}
}
