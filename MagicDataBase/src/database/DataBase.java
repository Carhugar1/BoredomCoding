package database;

import java.util.Arrays;
import java.util.Comparator;

public class DataBase {

	private final int StartingSize = 25;
	private final int GrowingSize = 25;
	
	public Card arr[];
	
	private int currentSize, totalSize;
	
	public DataBase() {
		this.arr = new Card[StartingSize];
		this.currentSize = 0;
		this.totalSize = StartingSize;
	}
	
	public void add(Card obj) {
		if (currentSize + 1 > totalSize) { // Make the array big enough
			this.increaseArr();
		}
		
		this.mergeSort(arr, new Alphabetical()); // sort for the searching
		int index = this.binarySearch(arr, obj, new Alphabetical()); // find what we are looking for
		
		if (index != -1) { // Card was found
			// increase the Quantity of that card
			arr[index].Quantity += obj.Quantity;
		}
		
		else { // not found
			arr[currentSize] = obj;
			currentSize += 1;
		}
	}
	
	public Card remove(Card obj, int Quantity) { // might as well as have a remove method since i have an add method
		this.mergeSort(arr, new Alphabetical()); // sort for the searching
		int index = this.binarySearch(arr, obj, new Alphabetical()); // find what we are looking for
		
		if (index != -1) { // card found
			Card result = arr[index];
			result.Quantity = Quantity;
			arr[index].Quantity -= Quantity;
			
			
			if (arr[index].Quantity < 1) {
				arr[index] = null;
			}
			
			return result;
		}
		
		else { // card not found, ?
			return null;
		}
	}
	
	public void edit() { // edit might be useful
		//TODO
	}
	
	
	
	private Card[] mergeSort(Card arr[], Comparator<Card> comparator) {
		// uses a comparator to make the sorting changeable when needed.
		if (arr.length <= 1) { // base case of recursion
			return arr;
		}
		
		else {
			Card arrL[] = Arrays.copyOfRange(arr, 0, arr.length / 2); // left array
			Card arrR[] = Arrays.copyOfRange(arr, arr.length / 2 + 1, arr.length); // right array
			
			arrL = this.mergeSort(arrL, comparator); // mergesort leftside
			arrR = this.mergeSort(arrR, comparator); // mergesort rightside
			
			int L = 0, R = 0, i = 0; // array places
			
			// begin the sorting
			while (L < arrL.length && R < arrR.length) { 
				if (arrL[L] != null && comparator.compare(arrL[L], arrR[R]) <= 0) { // left is smaller or the same and not null
					arr[i] = arrL[L];
					L++;
				}
				else {
					arr[i] = arrR[R]; // right is smaller
					R++;
				}
				i++;
			} // end while loop
		
			// need to pick up the extra's
			while (L < arrL.length) { // left side pick up
				arr[i] = arrL[L];
				L++;
				i++;
			}
			
			while (R < arrR.length) { // right side pick up
				arr[i] = arrR[R];
				R++;
				i++;
			}
			
			// all good return :)
			return arr;
			
		}
	} // end recursive mergesort()
	
	private int binarySearch(Card arr[], Card key, Comparator<Card> com) {
		return this.binarySerch(arr, key, com, 0, arr.length);
	}
	
	private int binarySerch(Card card[], Card key, Comparator<Card> com, int front, int back) {
		// recursion
		int mid = (back - front) / 2;
		
		if (mid < front || mid > back) { // whoops not here
			return -1;
		}
		
		else if (com.compare(key, arr[mid]) < 0) { // smaller
			return this.binarySerch(card, key, com, front, mid - 1);
		}
		
		else if (com.compare(key, arr[mid]) == 0) { // yeah! found it.
			return mid;
		}
		
		else { // bigger
			return this.binarySerch(card, key, com, mid + 1, back);
		}
	}

	private void increaseArr() {
		totalSize += GrowingSize;
		arr = Arrays.copyOf(arr, totalSize);
	}
}
