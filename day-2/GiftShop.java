import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class GiftShop {
	public static long findSumOfInvalidIds(String[] input) {
		long sum = 0;
		for(int i = 0; i < input.length; i++) {
			String[] range = input[i].split("-");

			long start = Long.parseLong(range[0]);
		        long end = Long.parseLong(range[1]);
			
			for(long j = start; j <= end; j++) {
				String current = Long.toString(j);
				
				String doubleOfCurrent = current + current;
				
				// Remove the first and last character from the doubled string
				String trimmedCurrent = doubleOfCurrent.substring(1, doubleOfCurrent.length() - 1);

				if(trimmedCurrent.contains(current)) {
					sum = sum + j;
				}	
			}
		}

		return sum;
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
		String[] input = readInputFromFile("gift-shop-input.txt");
		
		/*
		String[] input = {"11-22", "95-115", "998-1012", "1188511880-1188511890", "222220-222224", "1698522-1698528", "446443-446449", 
				  "38593856-38593862", "565653-565659", "824824821-824824827", "2121212118-2121212124"};
		*/

		long sum = findSumOfInvalidIds(input);
		System.out.println("Sum of invalid Ids: " + sum);
	}
}
