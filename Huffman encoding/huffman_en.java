package CS211;

import java.util.*;

public class huffman_en {
	public static void main(String args[]){
		System.out.println("Enter your sentence: ");

		Scanner scan = new Scanner(System.in);
		String sentence = scan.nextLine();
		String[] ASCIIarray = new String[256];
		//int length = sentence.length();

		System.out.println();

		char[] array = new char[sentence.length()]; //holds the character(letter) values

		for(int i=0; i<sentence.length(); i++){

			array[i] = sentence.charAt(i);

			if(sentence.charAt(i) == ' '){
				int num = (int)sentence.charAt(i);	//convert the letter to it's ASCII value
				String space = "0" + Integer.toBinaryString(num); //place 0 infront of leading 1 (spaces)
				ASCIIarray[i] = space;
				System.out.print(ASCIIarray[i] + " ");
			}
			else{
				int num = (int)sentence.charAt(i);	//convert the letter to it's ASCII value
				ASCIIarray[i] = Integer.toBinaryString(num);	//Change the ASCII value to binary
				System.out.print(ASCIIarray[i] + " ");	//Print the values
			}

		}


		System.out.println(); // to seperate the ASCII vaule from the frequency
		int length = sentence.length();

		//for loop to determine the frequency that the letter appeared
		for(int i=0; i<array.length; i++){

			int times = 0;

			for(int j=0; j<array.length; j++){

				if(j < i && array[i] == array[j]){
					break; //letter has already appeared
				}

				if(array[i] == array[j]){
					times++; //increment the amount the letter appeared
				}

				if(j == length-1){
					System.out.println("'" + array[i] + "' appeared " + times + " times");
				}

			}

		}


		scan.close();
	}
}
