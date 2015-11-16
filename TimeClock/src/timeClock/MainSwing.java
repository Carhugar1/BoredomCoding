package timeClock;

import java.awt.Component;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;



public class MainSwing {
	
	static String project = "";
	
	static TimeClock clock = new TimeClock();
	
	static HashMap<String, Integer> values;
	static ArrayList<String> projects;
	
	public static void main(String[] args) {
		
		final File in = new File("Time.txt");
		
		/**
		 * Checks if the file exists, and if it doesn't creates a new file with the specified name
		 */
		if (!in.exists()) {
			try {
				in.createNewFile();
			} catch (Exception e) {
				// Shouldn't ever happen
				System.out.println(e.getMessage());
			}
		}
		
		Scanner input = null;
		
		try {
			input = new Scanner(in);
		} catch (Exception e) {
			// Shouldn't ever happen
			System.out.println(e.getMessage());
		}
		
		values = new HashMap<String, Integer>();
		projects = new ArrayList<String>();
		String key;
		int value;
		
		/**
		 * Get all the info from the file to the HashMap
		 */
		while (input.hasNextLine()) {
			key = input.next();
			value = input.nextInt();
			
			projects.add(key);
			values.put(key, value);
		}
		
		input.close();
		
		/**
		 * Here is the beginning of the new JSwing elements
		 */
		JFrame frame = new JFrame("TimeClock");
		frame.add(TimePanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){ // close then leave
            	save(in);
                System.exit(0);
            }
        });
		
	}
		

	private static JPanel TimePanel() {
		
		final JPanel main = new JPanel();
		// main.setPreferredSize(new Dimension(400, 200));
		main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
		// main.add(Box.createRigidArea(new Dimension(0, 0)));
		
		// projects drop down list
		String[] List = new String[projects.size() + 1];
		for (int i = 0; i < projects.size(); i++) {
			List[i] = projects.get(i);
		}
		List[projects.size()] = "New...";
		
		final JComboBox projectComboBox = new JComboBox(List);
		projectComboBox.setSelectedIndex(0);
		projectComboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				int index = projectComboBox.getSelectedIndex();
				if (index == projects.size()) {
					String newProject = (String) JOptionPane.showInputDialog(
							main,
							"Enter the new project's name",
							"New Project", 
							JOptionPane.PLAIN_MESSAGE, 
							null, 
							null, 
							"New project"
							);
					if (newProject != null) {
						projects.add(newProject);
						values.put(newProject, 0);
						projectComboBox.removeItemAt(index);
						projectComboBox.addItem(newProject);
						projectComboBox.addItem("New...");
						projectComboBox.setSelectedIndex(index);
					}
				}	
				
				project = projects.get(index);
			}
		});
		
		// need to set the project String :)
		project = projects.get(projectComboBox.getSelectedIndex());
		
		// clock in/clock out button
		final JButton clockButton = new JButton("Clock In");
		clockButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (clockButton.getText().compareTo("Clock In") == 0) {
					clock.clockIn();
					clockButton.setText("Clock Out");
				}
				else {
					clock.clockOut();
					values.put(project, values.get(project) + clock.timeUsed("minutes"));
					clockButton.setText("Clock In");
				}
			}
		});
		
		// edit button Note:
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Object r = JOptionPane.showInputDialog(
						main,
						"Change " + project + "'s time to?",
						"Edit", 
						JOptionPane.PLAIN_MESSAGE, 
						null, 
						null, 
						values.get(project)
						);
				
				if (r != null) {
					values.put(project, Integer.decode((String) r));
				}
			}
		});
		
		// Remove button for mistyping with a JoptionPane for confirmation
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(
						main, 
						"Are you sure you want to remove the " + project + " project?", 
						"Remove", 
						JOptionPane.YES_NO_OPTION
						) == JOptionPane.YES_OPTION) {
					
					projects.remove(project);
					projectComboBox.removeItem(project);
					values.remove(project);
				}
			}
		});
		
		main.add(projectComboBox);
		main.add(clockButton);
		main.add(editButton);
		main.add(removeButton);
		return main;
	}

	/**
	 * My Save Method.
	 */
	private static void save(File in) {
		PrintWriter out;
		
		try {
			out = new PrintWriter(in);
			projects = helperSort(projects.toArray());
			
			for (int i = 0; i < projects.size(); i++) {
				if (i > 0) out.println();
				out.print(projects.get(i) + " ");
				out.print(values.get(projects.get(i)));
			}
			
			out.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
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
