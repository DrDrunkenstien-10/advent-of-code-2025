import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
	public static int findTotalOutputJoltage(String[] input) {
		int totalJoltage = 0;

		for(int i = 0; i < input.length; i++) {
			String currentInput = input[i];
			
			int maxFromLeft = 0;
			int maxFromLeftIndex = -1;

			for(int j = 0; j < (currentInput.length() - 1); j++) {
			       int currentDigit = Integer.parseInt(String.valueOf(currentInput.charAt(j)));
			       
			       if(currentDigit > maxFromLeft) {
				       maxFromLeft = currentDigit;
				       maxFromLeftIndex = j;
			    }
			}
			
			int maxFromRight = 0;

			for(int k = (currentInput.length() - 1); k > maxFromLeftIndex; k--) {
			       int currentDigit = Integer.parseInt(String.valueOf(currentInput.charAt(k)));
		       	       
			       if(currentDigit > maxFromRight) {
			       	       maxFromRight = currentDigit;
			       }
			}

			int maxJoltage = (maxFromLeft * 10) + maxFromRight;
			totalJoltage = totalJoltage + maxJoltage;			
		}

		return totalJoltage;
	}
	
	public static String[] readInputFromFile(String fileName) {
		List<String> inputList = new ArrayList<>();

		File file = new File(fileName);

		try (Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				String input = scanner.nextLine();
				inputList.add(input);
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
      			e.printStackTrace();
		}

		String[] inputArray = inputList.toArray(new String[0]);

		return inputArray;
	}

	public static void main(String[] args) {
		// String[] input = {"987654321111111", "811111111111119", "234234234234278", "818181911112111"};
		
		String[] input = readInputFromFile("lobby-input.txt");
		
		int totalJoltage = findTotalOutputJoltage(input);
		System.out.println("Total Joltage: " + totalJoltage);
	}
}
