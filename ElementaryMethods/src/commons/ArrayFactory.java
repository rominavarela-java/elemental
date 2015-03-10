package commons;

import enums.arrays;

public class ArrayFactory {
	
	/**
	 * Create an array filed with Integer values
	 * @param size Array size
	 * @param min Minimum value
	 * @param max Maximum value
	 * @param type {@link arrays}
	 * @return
	 */
	public static int[] createIntArray(int size, int min, int max, arrays type){
		int[] x = new int[size];
		
		switch(type)
		{			
			case ordered:
				for(int i=0;i<size;i++)
					x[i]= i;
				break;
			
			case inverse:
				for(int i=0, ii=size;i<size;i++,ii--)
					x[i]= ii;
				break;
				
			case random:
				for(int i=0;i<size;i++)
					x[i]= min + (int) (Math.random() * (max - min +1));
				break;
		}
		
			
		return x;
	}
	
	/**
	 * Print the contents of an Array
	 * @param array
	 */
	public static void printArray(int[] array){
		for(int i=0;i<array.length;i++)
			System.out.print(array[i]+ (i%100==0 ? "\n" : " "));
		System.out.print('\n');
	}
	
	/**
	 * @param array Array of Integer values
	 * @return If the array is sorted
	 */
	public static boolean isSorted(int[] array){
		
		for(int i=1;i< array.length;i++)
		{
			if(array[i-1]> array[i])
				return false;
		}
			
		return true;
	}
}
