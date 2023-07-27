package telran.recursion;

public class LinearRecursion {
	public static void function(int a) {
		if(a > 3) {
			function(a - 1);
		}
		
	}
	public static long factorial(int n) {
		long res = 1;
		if (n < 0) {
			throw new IllegalArgumentException("factorial exists only for positive numbers and 0");
		}
		if (n > 0) {
			res = n * factorial(n - 1);
		}
		return res;
		
	}
	public static long pow (int a, int b) {

		//HW #17 definition
		//Write method pow with following limitations
		//No cycles
		//Arithmetic operations allowed +; - only
		//if Additional functions applied then only with the same limitations
		if (b < 0) {
			throw new IllegalArgumentException();
		}
		long res = 1;
		if (b > 0) {
			res = multiply(a, pow(a, b - 1));
		}
		return res;
	}

	private static int multiply(int a, long b) {

		int res = 0;
		if (b != 0) {
			res = b < 0 ? multiply(-a, -b): a + multiply(a, b - 1);
		}
		

		return res;
	}

	public static void displayArray(int[] ar) {
		displayArray(0, ar, false);
	}
private static void displayArray(int index, int[] ar, boolean isReverse) {
		if (index < ar.length) {
			if (isReverse) {
				displayArray(index + 1, ar, isReverse);
				System.out.print(ar[index] + " ");
			} else {
				System.out.print(ar[index] + " ");
				displayArray(index + 1, ar, isReverse);
			}
		}
		
	}
public static void displayReversedArray(int[] ar) {
	displayArray(0, ar, true);
	}

public static long sumArray(int []array) {
	return sumArray(0, array);
}
private static long sumArray(int index, int[] array) {
	long res = 0;
	if (index < array.length) {
		res = array[index] + sumArray(index + 1, array);
	}
	return res;
}
public static void reverseArray(int[] array) {
	reverseArray(0, array, array.length -1);
}
private static void reverseArray(int left, int[] array, int right) {
	if(left < right) {
		int tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
		reverseArray(left + 1, array, right - 1);
	}
	
}
public static int square(int x) {
	//returns x ^ 2
	//With following limitations
	//No cycles
	//No any additional methods 
	//No static fields
	// Only + ; - arithmetic operations
	int res = 0; 
	if(x != 0) {
		res = x < 0 ? square(-x) : x + x - 1 + square(x - 1);
	}

return res; 
}
public static boolean isSubstring(String string, String substr) { 

	//TODO
	// returns true if a given 'substr' is indeed the //substring of a given `string` 

	/*
	  Challenges:
	 1. To apply only following methods of the class String: charAt(int ind);  

	String substring(int ind); 

	 int length(); 

	2. No cycles;
	*/ 
		

	boolean res = false;
	if (string.length() >= substr.length()) {
		res = isEqual(string, substr) ? true : isSubstring(string.substring(1), substr);
	} 

	return res;

}

private static boolean isEqual(String str, String substr) {
	boolean res = false;
	if (substr.length() == 0) {
		res = true;
	} else if (str.charAt(0) == substr.charAt(0)) {
		res = isEqual(str.substring(1), substr.substring(1));
	}
	
	return res;
}
}