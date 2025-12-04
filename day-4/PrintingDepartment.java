import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class PrintingDepartment {
	public static int findAccessiblePaperRolls(String[] input) {
		int count = 0;

		int rows = input.length;
		int columns = input[0].length();
		
		// All the 8 directions
		int[][] directions = {
			{-1, -1}, {-1, 0}, {-1, 1},
			{0, -1},           {0, 1},
			{1, -1},  {1, 0},  {1, 1}
		};

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(input[i].charAt(j) == '@') {
					int adjacent = 0;

					for(int[] d : directions) {
						int nextRow = i + d[0];
						int nextColumn = j + d[1];

						if(nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns) {
							if(input[nextRow].charAt(nextColumn) == '@') {
								adjacent++;
							}
						}

					}

					if(adjacent < 4) {
						count++;
					}
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
		/*
		String[] input = {
			"..@@.@@@@.",
            		"@@@.@.@.@@",
            		"@@@@@.@.@@",
            		"@.@@@@..@.",
            		"@@.@@@@.@@",
            		".@@@@@@@.@",
            		".@.@.@.@@@",
            		"@.@@@.@@@@",
            		".@@@@@@@@.",
            		"@.@.@@@.@."
		};
		*/
		
		String[] input = readInputFromFile("printing-department-input.txt");

		int count = findAccessiblePaperRolls(input);
		System.out.println("Accessible Paper Rolls: " + count);
	}
}
