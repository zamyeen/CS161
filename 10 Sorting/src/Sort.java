import java.io.*;
import java.util.*;


public class Sort {
	
	//Compare each pair of numbers and move the larger to the right
	static int [] BubbleSort (int[]array) {
		int temp = 0;
		for(int j = 0; j < array.length-1;j++) {
			for (int i = 0; i < array.length-1-j; i++) {
				if(array[i] > array[i+1]) {
					//Swap whichever is smaller
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					//Use temporary variable so nothing is lost
				}
			}
		}
		return array;
	}
	
	//Find the smallest in the set and move it to the front
	static int [] SelectionSort (int[] array) {
		int[] smallest = new int[2];
		for (int j = 0; j < array.length; j++) {
			smallest[0] = array[j];
			smallest[1] = j;
			for (int i = j; i < array.length; i++) {
				if(array[i]<smallest[0]) {
					smallest[0] = array[i];
					smallest[1] = i;
				}
			}
			int temp = array[smallest[1]];
			array[smallest[1]] = array[j];
			array[j] = temp;
		}
		return array;
	}
	
	//Tally how often you see each number, print out that number of times
	static int [] TableSort (int[]array) {
		int[] tally = new int[1001];
		for (int i = 0; i < array.length; i++) {
			tally[array[i]]++;
		}
		int count = 0;
		//I keeps track of the actual number
		for (int i = 0; i < tally.length; i++) {
			//J keeps track of how many times we've seen that number
			for (int j = 0; j < tally[i]; j++) {
				array[count] = i;
				count++;
			}
		}
		return array;
	}
	
	static void printArray (int[]array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
	static int [] SmallestInt (int[] array) {
		int [] smallest = new int [2];
		smallest[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			if(array[i] < smallest[0]) {
				smallest[0] = array[i];
				smallest[1] = i;
			}
		}
		return smallest;
	}
	
	static int [] LargestInt (int[] array) {
		int[] largest = new int[2];
		largest[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			if(array[i] > largest[0]) {
				largest[0] = array[i];
				largest[1] = i;
			}
		}
		return largest;
	}
	
	
	
	public static void main(String[] args) throws IOException{
		Scanner consoleScanner = new Scanner(System.in);
		Scanner fileScanner = null;
		System.out.println("Enter a number for the file you want to input.");
		System.out.println("1:input1.txt \t 2:input2.txt \t 3:input3.txt \t 4:input4.txt");
		//Choose which text file to sort from
		String input1 = consoleScanner.nextLine();
		//If input was not one of the possible text files
		if(input1.length() != 1 && input1.charAt(0) != 1 && input1.charAt(0) != 2 && input1.charAt(0) != 3 && input1.charAt(0) != 4) {
			System.out.println("Enter 1,2,3,4");
			while(input1.length() != 1 && input1.charAt(0) != 1 && input1.charAt(0) != 2 && input1.charAt(0) != 3 && input1.charAt(0) != 4) {
				input1 = consoleScanner.nextLine();
			}
		}
		//Open file from specified input1
		fileScanner = new Scanner(new File("input" + input1.charAt(0) + ".txt"));
		
		//Create string and scan the file
		String fileinput = fileScanner.nextLine();
		//Splits each string when there is a comma and inputs into a newly initialized inputStringArray
		String [] inputStringArray = fileinput.split(",");
		int[] inputArray = new int [inputStringArray.length];
		for (int i = 0; i < inputStringArray.length; i++) {
			//Converts each string at specific array index into an integer and inputs into the integer inputArray
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
			//Print to check if correct (unnecessary)
			System.out.println(inputArray[i]);
		}
		System.out.println("Enter a number for the sort you want to use");
		System.out.println("1:Bubble Sort \t 2:Selection Sort \t 3:Table Sort");
		//Choose which type of sort to use
		String input = consoleScanner.nextLine();
		//Check if number inserted in console was one of the available options
		if(input.length() != 1 && input.charAt(0) != 1 && input.charAt(0) != 2 && input.charAt(0) != 3 && input.charAt(0) != 4) {
			System.out.println("Enter 1,2,3,4");
			while(input.length() != 1 && input.charAt(0) != 1 && input.charAt(0) != 2 && input.charAt(0) != 3 && input.charAt(0) != 4) {
				input = consoleScanner.nextLine();
			}
		}
		long startTime = System.currentTimeMillis();
		String output = "You have sorted input" + input1 + ".txt with ";
		if(input.charAt(0) == '1') {
			output+="Bubble Sort\n";
			inputArray = BubbleSort(inputArray);
			//printArray(inputArray);
			System.out.println("You have sorted with the Bubble Sort algorithm");
		}
		if(input.charAt(0) == '2') {
			output+="Selection Sort\n";
			inputArray = SelectionSort(inputArray);
			//printArray(inputArray);
			System.out.println("You have sorted with the Selection Sort algorithm");
		}
		if(input.charAt(0) == '3') {
			output+="Table Sort\n";
			inputArray = TableSort(inputArray);
			//printArray(inputArray);
			System.out.println("You have sorted with the Table Sort algorithm");
		}
		if(input.charAt(0) == '4') {
			output+="Quick Sort\n";
			Arrays.sort(inputArray);
			//printArray(inputArray);
			System.out.println("You have sorted with the Quick Sort algorithm");
		}
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println(totalTime + " Milliseconds");
		PrintWriter out = new PrintWriter (new FileWriter (new File("output.txt")));
		for (int i = 0; i < inputArray.length; i++) {
			output = output + inputArray[i] + " ,";
		}
		out.println(output);
		out.println();
		out.println("Sorting runtime: " + totalTime);
		out.close();
		fileScanner.close();
		consoleScanner.close();
	}

}
