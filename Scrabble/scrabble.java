import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class scrabble {
	public static void main(String args[] ) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter: ");
		String letters = sc.nextLine();
		char[] letter = letters.toCharArray();
		Arrays.sort(letter);
        String longest = new String();
		String [] array = new String[216555];

        for (int i =0; i <216555; i++){
        	array[i]=sc.nextLine();
        }

       int a = 0;
            int x =0;
            for(int i = 0;i < array.length;i++)
       			{
           			if(compareLetters(letter,array[i].trim()) == true){
                    	if (array[i].length()> x){
                        	x=array[i].length();
                       		longest= array[i];
                    	}
                    	
                    	a++;
           			}
       			}
            
            System.out.println(longest.length());
    	}

   public static boolean compareLetters(char[] letters,String word){
       char [] words = word.toCharArray();
       Arrays.sort(words);
       int a=0,b=0;
       
       while(a < words.length && b <letters.length){
           if(letters[b] == words[a]){
               a++;
               b++;
           }
           else{
               b++;
           }
       }

       return (a == words.length);
    }
}
