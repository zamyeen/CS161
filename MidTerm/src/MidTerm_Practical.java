/**
 * Input:  Enter a sentence.
 * Output:  Print out how many of each letter are used in the sentence. In other words, the number of times you’ve seen ‘a’, the number of times you’ve seen ‘b’, etc.
 */

import java.util.*;

public class MidTerm_Practical {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[] char_sentence = scanner.nextLine().toLowerCase().toCharArray();
		
		HashMap<Character,Integer> hm = new HashMap<>();
		//HashMap<Integer, Character> mh = new HashMap<>();
		for (int i = 0; i < char_sentence.length; i++) {
			if(char_sentence[i] == '.') {
			
			}
			else if(char_sentence[i] == ' ') {
				
			}
			else if(hm.containsKey(char_sentence[i])) {
				hm.put(char_sentence[i],hm.get(char_sentence[i])+1);
			}
			else if(!hm.containsKey(char_sentence[i])){
				hm.put(char_sentence[i], 1);
			}
		}
		for(int i = 0; i < 26; i++) {
			if(hm.containsKey((char)(i+97))){
				
				System.out.print((char)(i+97) + ":" + hm.get((char)(i+97)) + ", ");
			}
		}
		scanner.close();
	}

}
