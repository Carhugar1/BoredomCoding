package timeClock;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.print("Loading ");
		
		/**
		 * Checks if the file exists, and if it doesn't creates a new file with the specified name
		 */
		File in = new File("Time.txt");
		if (!in.exists()) {
			try {
				in.createNewFile();
			} catch (Exception e) {
				// Shouldn't ever happen
				System.out.println(e.getMessage());
			}
		}
		
		System.out.print("... ");
		Scanner input = null;
		
		try {
			input = new Scanner(in);
		} catch (Exception e) {
			// Shouldn't ever happen
			System.out.println(e.getMessage());
		}
		
		HashMap<String, Integer> values = new HashMap<String, Integer>();
		ArrayList<String> projects = new ArrayList<String>();
		String key;
		int value;
		
		System.out.print("... ");
		
		/**
		 * Get all the info from the file to the HashMap
		 */
		while (input.hasNextLine()) {
			key = input.next();
			value = input.nextInt();
			
			projects.add(key);
			values.put(key, value);
		}
		
		System.out.println("Done");
		// end loading.
		
		printTable(projects, values);
		// ^ shows time spent on projects so far.
		
		System.out.println("Waiting for Command. ( ? for list of commands )");
		
		input.close();
		input = new Scanner(System.in);
		String next;
		
		boolean clockIn = false;
		TimeClock clock = new TimeClock();
		
		while (true) {
			
			next = input.next();
			
			if (next.equalsIgnoreCase("quit") || next.equalsIgnoreCase("q")) {
				break;
			}
			
			else if (next.equalsIgnoreCase("?") || next.equalsIgnoreCase("list") || next.equalsIgnoreCase("help") || next.equalsIgnoreCase("commands")) {
				System.out.println("edit [project name] [time]");
				System.out.println("add [project name]");
				System.out.println("remove [project name]");
				System.out.println("clock [project name]");
				System.out.println("reprint");
				System.out.println("quit");
			}
			
			else if (next.equalsIgnoreCase("add")) {
				next = input.next();
				if(!next.equalsIgnoreCase("add") && !projects.contains(next)) {
					projects.add(next);
					values.put(next, 0);
					printTable(projects, values);
				}
				else {
					System.out.println("Syntax Error: add [project name]");
				}
			}
			
			else if (next.equalsIgnoreCase("remove")) {
				next = input.next();
				if(projects.contains(next)) {
					projects.remove(next);
					values.remove(next);
					printTable(projects, values);
				}
				else {
					System.out.println("Error: " + next + " is not a project");
				}
			}
			
			else if (next.equalsIgnoreCase("edit")) {
				next = input.next();
				int number = input.nextInt();
				if(projects.contains(next)) {
					values.put(next, number);
					printTable(projects, values);
				}
				else {
					System.out.println("Error: " + next + " is not a project");
				}
			}
			
			else if (next.equalsIgnoreCase("clock")) {
				next = input.next();
				if(projects.contains(next) || !clockIn) {
					if (!clockIn) {
						clock.clockIn();
						clockIn = true;
						System.out.println("Clocked In!");
					}
					else {
						clock.clockOut();
						clockIn = false;
						values.put(next, values.get(next) + clock.timeUsed("minutes"));
						System.out.println("Added " + clock.timeUsed("minutes") + " minutes to project " + next + ".");
						printTable(projects, values);
					}
				}
				else {
					System.out.println("Error: " + next + " is not a project");
				}
			}
			
			else if (next.equalsIgnoreCase("reprint")) {
				printTable(projects, values);
			}
			
			else {
				System.out.println(next + " Isn't a vaild command");
			}
			
			
			
		}
		
		PrintWriter out;
		
		try {
			out = new PrintWriter(in);
			
			for (int i = 0; i < projects.size(); i++) {
				if (i > 0) out.println();
				out.print(projects.get(i) + " ");
				out.print(values.get(projects.get(i)));
			}
			
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		input.close();
		return;
	}
	
	

	
	private static void printTable(ArrayList<String> projects, HashMap<String, Integer> values) {
        
		projects = helperSort(projects.toArray()); // I like everything sorted
		
		System.out.println("     Project          Time Spent (Mins)");
		System.out.println("-----------------------------------------");
		String line;

		for (int i = 0; i < projects.size(); i++) {
			
			line = "| " + projects.get(i);
			
			while (line.length() < 25) {
				line = line + " ";
			}
			
			line = line + "| " + values.get(projects.get(i));
			
			while (line.length() < 40) {
				line = line + " ";
			}
			
			line = line + "|";
			
			System.out.println(line);
		}
		
		System.out.println("-----------------------------------------");
		
	}

	/**
	 * Just your general merge sort method.
	 * 
	 * @param arr
	 * @return
	 */
	private static ArrayList<String> helperSort(Object arr[]) {
		ArrayList<String> A = new ArrayList<String>();
		
		if (arr.length == 1) {
			A.add((String) arr[0]);
			return A;
		}
		
		else if (arr.length == 0) {
			return A;
		}
		
		else {
			
			ArrayList<String> left = helperSort(Arrays.copyOfRange(arr, 0, arr.length / 2));
			ArrayList<String> right = helperSort(Arrays.copyOfRange(arr, arr.length / 2, arr.length));
			int l = 0, r = 0;
			
			while (l < left.size() && r < right.size()) { // comparing.
				
				if (left.get(l).compareTo(right.get(r)) <= 0) {
					A.add(left.get(l));
					l++;
				}
				
				else {
					A.add(right.get(r));
					r++;
				}
			}
			
			while (l < left.size()) { // pick up the rest of the left.
				A.add(left.get(l));
				l++;
			}
			
			while (r < right.size()) { // pick up the rest of the right.
				A.add(right.get(r));
				r++;
			}
			
			return A;
			
		}
	}

}
