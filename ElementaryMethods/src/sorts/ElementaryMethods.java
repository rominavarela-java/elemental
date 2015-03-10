package sorts;

public class ElementaryMethods {
	
	/**
	 * Bubble swaps all index combinations.
	 * 
	 * Algorithm:
	 * <ul>
	 * <li>for i = each index of the Array
	 * <li>for ii = each index next to i
	 * <li>if ii is smaller than i, swap
	 * </ul>
	 * 
	 * Properties:
	 * <ul>
	 * <li>Most simple
	 * <li>Worst-case and average complexity both O(n2)
	 * <li>Not efficient in the case of a reverse-ordered collection.
	 * </ul>
	 * 
	 * @param array Array of Integer values to sort
	 */
	public static void bubbleSort (int[] array){
		
		for(int i=0;i<array.length-1;i++)
		{
			for(int ii=i+1; ii<array.length;ii++)
			{
				if(array[ii]<array[i])
					swap(array,i,ii);
			}
		}
		
		return;
	}
	
	/**
	 * Selection sort pushes smallest numbers to the beginning.
	 * 
	 * Algorithm:
	 * <ul>
	 * <li>for pointer = each index of the Array
	 * <li>for index = each index next to pointer
	 * <li>sIndex = smallest value found between pointer,index
	 * <li>swap i,pointer
	 * </ul>
	 * 
	 * Properties:
	 * <ul>
	 * <li>Simple and stable
	 * <li>O(n2) time complexity
	 * <li>Performance advantages where auxiliary memory is limited
	 * </ul>
	 * 
	 * @param array Array of Integer values to sort
	 */
	public static void selectionSort (int[] array)
	{
		for(int pointer=0; pointer<array.length; pointer++)
		{
			int sIndex= pointer;
			
			for(int index=pointer+1; index<array.length; index++)
			{
				if(array[index]<array[sIndex])
					sIndex=index;
			}
			
			if(sIndex!=pointer)
				swap(array,sIndex,pointer);
		}
	}
	
	/**
	 * Insertion sort pushes bigger numbers to the end.
	 * 
	 * Algorithm:
	 * <ul>
	 * <li>for pointer,bIndex = next index
	 * <li>for index = each index before pointer
	 * <li>if current index is bigger than bIndex, swap their values and set bIndex=index
	 * </ul>
	 * 
	 * Properties:
	 * <ul>
	 * <li>Simple and stable
	 * <li>More efficient in practice than selection and bubble
	 * <li>Insertion sort will on average require shifting half of the previous k elements, 
	 * while selection sort always requires scanning all unplaced elements.
	 * </ul>
	 * 
	 * @param array Array of Integer values to sort
	 * @param startPoint Initial index
	 * @param gap Gap size between indexes
	 */
	public static void insertionSort (int[] array, int startPoint, int gap)
	{
		for(int pointer=startPoint+gap ; pointer<array.length ; pointer+=gap )
		{
			int bIndex=pointer;
			
			for(int index=pointer-gap;index>=0;index-=gap)
			{
				if(array[index]>array[bIndex])
				{
					swap(array,index,bIndex);
					bIndex=index;
				}
			}
		}
	}
	
	/**
	 * Shell sort goes from big to small chunks.
	 * 
	 * Algorithm:
	 * <ul>
	 * <li>calculate biggest gap (a number that can sequentially be divided backwards until 1)
	 * <li>while the gap can be reduced
	 * <li>for pointer= each index in the gap
	 * <li>call a sorting method able to sort the gap separated indexes
	 * </ul>
	 * 
	 * Properties:
	 * <ul>
	 * <li>Worst-case complexity depends of gap sequence.
	 * <li>Rarely used in serious applications. It performs more operations and has higher cache miss ratio than quicksort.
	 * <li>For many practical variants, determining their time complexity remains an open problem.
	 * </ul>
	 * 
	 * @param array Array of Integer values to sort
	 */
	public static void shellSort (int[] array)
	{
		int gap=1;
		while(gap<array.length)
			gap=(gap*3)+1;
		
		while((gap=(gap-1)/3)>0)
		{
			for(int pointer=0;pointer<gap;pointer++)
				insertionSort(array,pointer,gap);
		}
	}
	
	/**
	 * DC sort goes from big to small chunks.
	 * 
	 * Algorithm:
	 * <ul>
	 * <li>M = biggest number found
	 * <li>map = array with M size
	 * <li>for each number in array, increase respective counter in map
	 * <li>for each count in map, sum all counts bellow
	 * <li>index=0
	 * <li>for (index,maxIndex) where maxIndex= each count in map, set array[index]= position in map
	 * </ul>
	 * 
	 * @param array Array of Integer values to sort
	 */
	public static void dcSort (int[] array)
	{
		int M=0;
		for(int i=0; i<array.length;i++)
			if(array[i]>M)
				M=array[i];
		
		int[] map= new int[M+1];
		
		//count
		for(int number: array)
			map[number]++;
		
		//sum
		for(int pointer=map.length-1;pointer>0;pointer--)
		{
			for(int i=pointer-1;i>=0;i--)
				map[pointer]+=map[i];
		}
		
		//fill
		int index=0;
		for(int mapPointer=0;mapPointer<map.length;mapPointer++)
		{
			for(int maxIndex=map[mapPointer];index<maxIndex;index++)
				array[index]=mapPointer;
		}
	}
	
	private static int[] merge(int[] array, int[] A, int[] B)
	{		
		int indexA=0;
		int indexB=0;
		
		for(int pointer=0; pointer<array.length; pointer++)
		{
			//end clauses
			if(indexA==A.length)
			{
				for(; indexB<B.length ; indexB++,pointer++ )
					array[pointer]=B[indexB];
				break;
			}
			else if(indexB==B.length)
			{
				for(; indexA<A.length ; indexA++,pointer++ )
					array[pointer]=A[indexA];
				break;
			}
			
			//sort clauses
			if(B[indexB]<A[indexA])
			{
				array[pointer]=B[indexB];
				indexB++;
				continue;
			}
			else
			{
				array[pointer]=A[indexA];
				indexA++;
				continue;
			}
			
		}
		
		return array;
	}
	
	/**
	 * Merge sort separates recursively the problem in 2, until smallest piece and sorts upwards.
	 * 
	 * Algorithm:
	 * <ul>
	 * <li>separate array into A,B
	 * <li>call merge sort for A and B
	 * <li>call {@link merge} for A,B
	 * </ul>
	 * 
	 * Properties:
	 * <ul>
	 * <li>Average and worst-case performance of O(n log n)
	 * <li>Divide and conquer algorithm
	 * <li>Space overhead
	 * </ul>
	 * 
	 * @param array Array of Integer values to sort
	 */
	public static int[] mergeSort(int[] array)
	{
		if(array.length<2)
			return array;
		
		int size1= array.length/2;//floor
		int size2= size1 + (array.length%2);//top
			
		int[] A= new int[size1];
		int[] B= new int[size2];
		
		//llena los arreglos
		for(int i=0; i<size1;i++)
			A[i]=array[i];
		
		for(int i=0, ii=size1; i<size2; i++, ii++)
			B[i]=array[ii];
		
		//
		A=mergeSort(A);
		B=mergeSort(B);
		
		return merge(array, A, B);
	}
	
	private static int quickSortPartition(int[] array, int left, int right) {
		int pivote = array[right];
		int lPointer = left;
		int rPointer = right - 1;
		
		while(true) 
		{
			//until the left pointer contains something bigger than pivote
			while(lPointer <  right && array[lPointer] < pivote)
				lPointer ++;
			
			//until the right pointer contains something bigger than pivote
			while(rPointer >= left  && array[rPointer] > pivote)
				rPointer --;
			
			//if L&R crash, finish
			if(lPointer >= rPointer)
			{
				swap(array, lPointer, right);
				break;
			}
			//else, swap
			else 
			{
				swap(array, lPointer, rPointer);
				lPointer ++;
				rPointer --;
			}
		}
		
		//in this case lPointer is the right most pointer
		return lPointer;
	}
	
	/**
	 * Quick sort separates recursively the problem in 2, until smallest piece and sorts upwards.
	 * 
	 * Algorithm:
	 * <ul>
	 * <li>p = {@link quickSortPartition}
	 * <li>call quick sort from left to p
	 * <li>call quick sort from p to right
	 * </ul>
	 * 
	 * Properties:
	 * <ul>
	 * <li>On average, it takes O(n log n) comparisons and O(n2) in the worst case
	 * <li>Divide and conquer algorithm
	 * <li>It can be about two or three times faster than merge sort and heapsort
	 * </ul>
	 * 
	 * @param array Array of Integer values to sort
	 * @param left Left-most index
	 * @param right Right-most index
	 */
	public static void quickSort(int[] array, int left, int right)
	{
		if(right - left <= 0)
            return;

		int p = quickSortPartition(array, left, right);
		quickSort(array, left, p - 1);
		quickSort(array, p + 1, right);
	}
	
	///////////////////////////////////////////////////////////	
	/**
	 * @param array Array to execute swap in
	 * @param i1 First index
	 * @param i2 Second index
	 */
	private static void swap(int[] array, int i1, int i2){
		int aux=array[i1];
		array[i1]=array[i2];
		array[i2]=aux;
	}
	
}
