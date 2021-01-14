/**
 * Choose if you want to Encrypt or Decrypt
 * Encrypt w/Key
 * Decrypt w or w/o Key
 * Input: PLAINTEXTMESSAGE Key: 3
 * Output: SODLQWHAWPHVVDJH
 * 
 * GORYVNDROCOFOXNSXQWKMRSXOCSXMYWWYX
 * 
 * SUXYGMYVVOQOCKXNMKBOOBC
 * 
 * MOEPKIVRBXLOPKCWFZRADIJHAVYEPEP
 */

import java.util.*;
import java.io.*;

public class CaesarCipher {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to Encrypt or Decrypt?");
		String DoE = scanner.nextLine();
		
		//Encrypt
		if(DoE.equals("Encrypt") || DoE.equals("ENCRYPT")) {
			System.out.println("What is your key?");
			int Key = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter what you want to Encrypt (without spaces): ");
			char[] input = scanner.nextLine().toUpperCase().toCharArray();
			System.out.println("Array is " + Arrays.toString(input) + "\nKey is " + Key);
			System.out.println(Encrypt(input,Key));
		}
		
		
		//Decrypt
		else if (DoE.equals("Decrypt") || DoE.equals("DECRYPT")) {
			System.out.println("Do you have a key?");
			String KYoN = scanner.nextLine();
			
			
			//Decrypting with Key
			if (KYoN.equals("Yes") || KYoN.equals("Y") || KYoN.equals("YES")) {
				System.out.println("What is your key?");
				int Key = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter what you want to Decrypt (without spaces): ");
				char[] input = scanner.nextLine().toUpperCase().toCharArray();
				System.out.println(Decrypt(input,Key));
			}
			
			
			//Decrypting without Key
			else {
				System.out.println("Enter what you want to Decrypt (without spaces): ");
				char[] input = scanner.nextLine().toUpperCase().toCharArray();
				for (int i = 1; i <= 26; i++) {
					System.out.println(Decrypt(input,i));
				}
			}
		}
		scanner.close();
	}
	
	public static String Decrypt (char[] input,int Key) {
		String output = "";
		for (int i = 0; i < input.length; i++) {
			if((int)(input[i])-Key > 64) {
				output += ((char)((int)(input[i])-Key));
			}
			else {
				output += (char)(91 - (65-((int)(input[i])-Key)));
			}
		}
		return output;
	}
	
	public static String Encrypt (char[] input, int Key) {
		String output = "";
		for (int i = 0; i < input.length; i++) {
			if (((int)(input[i])+Key) < 90) {
				output += (char)((int)(input[i])+Key);
			}
			else {
				output += (char)((int)(input[i])+Key-26);
			}
		}
		return output;
	}
}
