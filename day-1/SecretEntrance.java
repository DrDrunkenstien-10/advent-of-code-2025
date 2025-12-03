import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class SecretEntrance {
	public static int findPassword(String[] input) {
		int count = 0;
		int dialPosition = 50;

		for(int i = 0; i < input.length; i++) {
			String currentRotation = input[i];
			
			int first = 0; // First number in the string (e.g., 6 in L68)
		        int second = 0; // Second number in the string (e.g., 8 in L68)
			int third = 0; // Third number in the string (e.g., 1 in L951)

			int addition = 0;
			
			if (currentRotation.length() == 4) {
                                // In case of string like R251
                                first = Integer.parseInt(String.valueOf(currentRotation.charAt(1))) * 100;
                                second = Integer.parseInt(String.valueOf(currentRotation.charAt(2))) * 10;
                                third = Integer.parseInt(String.valueOf(currentRotation.charAt(3)));

                                addition = first + second + third;
                        }

			else if(currentRotation.length() == 3) {
				// In case of string like R25
				first = Integer.parseInt(String.valueOf(currentRotation.charAt(1))) * 10;
				second = Integer.parseInt(String.valueOf(currentRotation.charAt(2)));

				addition = first + second;
			}
			
			else {
				// In case of string like R2
				first = Integer.parseInt(String.valueOf(currentRotation.charAt(1)));
				addition = first;
			}
			
			char direction = currentRotation.charAt(0);

			for(int j = 0; j < addition; j++) {
				if(direction == 'L') {
					dialPosition = (dialPosition - 1 + 100) % 100;
				}

				else {
					dialPosition = (dialPosition + 1) % 100;
				}

				if(dialPosition == 0) {
					count++;
				}
			}
		}
		
		return count;
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
		String[] input = readInputFromFile("secret-entrance-input.txt");

		int password = findPassword(input);
		System.out.println("Password: " + password);
	}
}
