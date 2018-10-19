import java.util.*;

public class el_mal {
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter: ");
		long p = sc.nextLong();
		long g = sc.nextLong();
		long mod = sc.nextLong();
		
		long c1 = sc.nextLong();
		long c2 = sc.nextLong();
		
		long message = 0;
		long pk = 0;
		
		boolean found = false;
		
		for(long x = 0; !found; x++){
			if(modPow(g, x, p) == mod){
				found = true;
				pk = x;
			}
		}
		
		//System.out.println(pk);
		
		message = modPow(c1, p-1-pk, p);
		message = modMult(message, c2, p);
		
		System.out.println(message);
	}
	
	
	public static long modPow(long number, long power, long modulus){
		if(power == 0){
			return 1;
		} else if(power%2 == 0){
			long halfpower = modPow(number, power/2, modulus);
			return modMult(halfpower, halfpower, modulus);
		} else{
			long halfpower = modPow(number, power/2, modulus);
			long firstbit = modMult(halfpower, halfpower, modulus);
			return modMult(firstbit, number, modulus);
		}
	}
	
	public static long modMult(long first, long second, long modulus){
		if(second == 0){
			return 0;
		} else if(second%2 == 0){
			long half = modMult(first, second/2, modulus);
			return (half + half)%modulus;
		} else{
			long half = modMult(first, second/2, modulus);
			return (half + half + first)%modulus;
		}
	}
}
