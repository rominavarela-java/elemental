package sorts;

import commons.ArrayFactory;
import enums.arrays;
import enums.sorts;

public class TestBench {
	
	public static void main(String[] args){
		test(sorts.quick,10000,arrays.random);
	}

	public static void test(sorts method, int l, arrays type)
	{
		System.out.println(method.name());
		int[] x= ArrayFactory.createIntArray(l,0,l,type);
		ArrayFactory.printArray(x);
		long time_start=0, time_end=0;
		
		switch(method)
		{
		case bubble:
			time_start = System.currentTimeMillis();
			ElementaryMethods.bubbleSort(x);
			time_end = System.currentTimeMillis();
			break;
			
		case selection:
			time_start = System.currentTimeMillis();
			ElementaryMethods.selectionSort(x);
			time_end = System.currentTimeMillis();
			break;
			
		case insertion:
			time_start = System.currentTimeMillis();
			ElementaryMethods.insertionSort(x,0,1);
			time_end = System.currentTimeMillis();
			break;
			
		case shell:
			time_start = System.currentTimeMillis();
			ElementaryMethods.shellSort(x);
			time_end = System.currentTimeMillis();
			break;
			
		case dc:
			time_start = System.currentTimeMillis();
			ElementaryMethods.dcSort(x);
			time_end = System.currentTimeMillis();
			break;
			
		case merge:
			time_start = System.currentTimeMillis();
			ElementaryMethods.mergeSort(x);
			time_end = System.currentTimeMillis();
			break;
			
		case quick:
			time_start = System.currentTimeMillis();
			ElementaryMethods.quickSort(x,0,x.length-1);
			time_end = System.currentTimeMillis();
			break;
		}
		
		System.out.println("Test time: "+ ( time_end - time_start ) +" milliseconds");
		ArrayFactory.printArray(x);
		System.out.println("\n\n");
	}
	
}
