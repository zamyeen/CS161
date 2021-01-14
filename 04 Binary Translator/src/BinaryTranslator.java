/*
 * This program will first ask the user if it wants to take in input from file or console
 * It will then read the file or the console for the number
 * It will ask if the number that it reads needs to be changed from binary or from decimal
 * It will print out the converted number in the console
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BinaryTranslator {
	
	public BinaryTranslator(){
		
		System.out.println("Please enter \"file\" to enter a file or \"console\" to use the console.");
		Scanner scanner = new Scanner(System.in);
		String fileoconsole = scanner.nextLine();
		String numberInput = "";
		if (fileoconsole.equals("file")){// input from a file
			try {
				System.out.println("Enter your file name.");
				fileoconsole = scanner.nextLine();
				Scanner fileScanner = new Scanner(new File(fileoconsole));
				numberInput = fileScanner.nextLine();
			} catch (IOException ex){
				System.out.println("File not found");
				scanner.close();
				System.exit(1);
			}
			System.out.println("If you are translating from decimal to binary, type \"dtb\".");
			System.out.println("If you are translating from binary to decimal, type \"btd\".");
			String dob = scanner.nextLine();
			if (dob.equals("dtb")){//Decimal to Binary converter
				int number = Integer.parseInt(numberInput);
				String answer = "";
				while(number > 0){
					if(number % 2 ==1){
						answer = "1" + answer;
					}
					else{
						answer = "0" + answer;
					}
					number = number/2;
				}
				System.out.println(answer);
			}
			else{//Binary to Decimal converter
				int answer = 0;
				for (int i=0; i < numberInput.length(); i++ ){
					
					if(numberInput.charAt(i) == '1'){
						answer = answer + (int)(Math.pow(2,numberInput.length() - i-1));
					}
				}
				System.out.println(answer);
			}
		}
		
		else{// input from console
			numberInput = scanner.nextLine();
			System.out.println("If you are translating from decimal to binary, type \"dtb\".");
			System.out.println("If you are translating from binary to decimal, type \"btd\".");
			String dob = scanner.nextLine();
			if (dob.equals("dtb")){//Decimal to Binary converter
				int number = Integer.parseInt(numberInput);
				String answer = "";
				while(number > 0){
					if(number % 2 ==1){
						answer = "1" + answer;
					}
					else{
						answer = "0" + answer;
					}
					number = number/2;
				}
				System.out.println(answer);
			}
			else{//Binary to Decimal converter
				int answer = 0;
				for (int i=0; i < numberInput.length(); i++ ){
					
					if(numberInput.charAt(i) == '1'){
						answer = answer + (int)(Math.pow(2,numberInput.length() - i-1));
					}
				}
				System.out.println(answer);
			}
		}
	}
	public static void main(String[] args) {
		new BinaryTranslator();

	}

}