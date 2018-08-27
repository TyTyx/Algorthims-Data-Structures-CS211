package CS211;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class huffman_algo {
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		while(true){
			System.out.println("Enter your sentence: ");
			String sentence = scan.nextLine();
			String binaryString = "";

			for(int i = 0; i < sentence.length(); i++){
				int decimalValue = (int)sentence.charAt(i);
				String binaryValue = Integer.toBinaryString(decimalValue);

				for(int j = 7; j > binaryValue.length(); j--){
					binaryString += "0"; // Loops to add in leading zeros
				}

				binaryString += binaryValue + " "; // Add to the string of binary
			}

			System.out.println(binaryString);  // Print out the binary

			int[] array = new int[256]; // Array to store all the frequencies

			for(int i = 0; i < sentence.length(); i++){
				array[(int)sentence.charAt(i)]++; // Increment appropriate frequencies
			}

			PriorityQueue <Tree> PQ = new PriorityQueue <Tree>();

			for(int i = 0; i < array.length; i++){
				if(array[i] > 0){
					System.out.println("'"+(char)i+"' appeared "+array[i]+((array[i] == 1) ? " time" : " times"));
					Tree myTree = new Tree();
					myTree.frequency = array[i];
					myTree.root = new Node();
					myTree.root.letter = (char)i;
					myTree.alpha = i;
					PQ.add(myTree);
				}
			}

			while(PQ.size() > 1){
				Tree firstTree = PQ.poll();
				Tree secondTree = PQ.poll();
				Tree comboTree = new Tree();

				comboTree.frequency = firstTree.frequency + secondTree.frequency;
				comboTree.root = new Node();
				comboTree.root.leftChild = firstTree.root;
				comboTree.root.rightChild = secondTree.root;

				if(firstTree.frequency == secondTree.frequency){
					if(firstTree.alpha > secondTree.alpha){
						comboTree.root.leftChild = secondTree.root;
						comboTree.root.rightChild = firstTree.root;
					}
				}

				comboTree.alpha = Math.min(firstTree.alpha, secondTree.alpha);
				PQ.add(comboTree);
			}

			Tree HuffmanTree = PQ.poll();
			int totalLength = 0;
			String theCode;
			for(int i = 0; i < sentence.length(); i++){
				theCode = HuffmanTree.getCode(sentence.charAt(i));
				System.out.print(theCode);
				totalLength += theCode.length();
			}

			System.out.println("\nCompressed size is "+totalLength+" bits / "+sentence.length()*7+" bits = "+(int)((totalLength*100)/(sentence.length()*7))+" %\n");

		}
	}
}

class Tree implements Comparable<Tree>{

	public Node root;             // first node of tree
	public int frequency=0;

	//-------------------------------------------------------------
	public Tree()                  // constructor
	   {   root = null; }            // no nodes in tree yet
	//-------------------------------------------------------------

	//the PriorityQueue needs to be able to somehow rank the objects in it
	//thus, the objects in the PriorityQueue must implement an interface called Comparable
	//the interface requires you to write a compareTo() method so here it is:
	public int alpha =0;
	public int compareTo(Tree object){

	    if(frequency-object.frequency>0){ //compare the cumulative frequencies of the tree
	        return 1;
	     }else if(frequency-object.frequency<0){
	        return -1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
	     }else{
	         if(alpha>object.alpha)
	             {
	             return 1;
	         }
	        else
	            if(alpha<object.alpha)
	            {
	            return -1;
	        }
	        else{
	            return 0;
	        }
	        //return 0;


	     }
}

//-------------------------------------------------------------

String path="error";     //this variable will track the path to the letter we're looking for

public String getCode(char letter){  //we want the code for this letter

    inOrder(root, letter, "");    //call an inOrder traversal, starting at the root, looking for this letter
    return path;     //return the path that results

}

//-------------------------------------------------------------
private void inOrder(Node localRoot, char letter, String path){ //the path variable tracks the current search path
   if(localRoot != null){ //if root is null we've gone off the edge of the tree - back up
       if(localRoot.letter==letter){
         this.path=path;     //if we've found the letter, note the path - final path = current search path
       }else{
         inOrder(localRoot.leftChild, letter, path+"0"); //go left and add "0" to the current search path
         inOrder(localRoot.rightChild, letter, path+"1");    //go right and add "1" to the current search path
      }
   }
   return; //quit searching this branch of the tree
}
//-------------------------------------------------------------
}
 class Node{


	public char letter='@';            //stores letter

	public Node leftChild;         // this node's left child
	public Node rightChild;        // this node's right child

}
