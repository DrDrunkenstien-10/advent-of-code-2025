import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Cafeteria {
	public static int findFreshIngredientIdsCount(String[] freshIngredientIdRanges, String[] availableIngredientIds) {
	        HashSet<Long> freshIngredientIds = new HashSet<Long>();

		for(int i = 0; i < availableIngredientIds.length; i++) {
		       long currentAvailableIngredientId = Long.parseLong(availableIngredientIds[i]);

	               for(int j = 0; j < freshIngredientIdRanges.length; j++) {
			       String[] range = freshIngredientIdRanges[j].split("-");

			       long rangeStart = Long.parseLong(range[0]);
			       long rangeEnd = Long.parseLong(range[1]);

			       if(currentAvailableIngredientId >= rangeStart && currentAvailableIngredientId <= rangeEnd) {
				       freshIngredientIds.add(currentAvailableIngredientId);
				       break;
			       }
		       }
		}

		int freshIngredientIdsCount = freshIngredientIds.size();
		return freshIngredientIdsCount;

	}
	
	public static String[] readFreshIngredientIdRangesFromFile(String fileName) {
		List<String> inputList = new ArrayList<>();

		File file = new File(fileName);

		try (Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				String input = scanner.nextLine();

				if(input.isEmpty()) {
					break;
				}

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
	
	public static String[] readAvailableIngredientIdsFromFile(String fileName) {
                List<String> inputList = new ArrayList<>();

                File file = new File(fileName);

                try (Scanner scanner = new Scanner(file)) {
                        while(scanner.hasNextLine()) {
                                String input = scanner.nextLine();

                                if(input.contains("-") || input.isEmpty()) {
                                        continue;
                                }
				
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
		String[] freshIngredientIdRanges = {"3-5", "10-14", "16-20", "12-18"};
		String[] availableIngredientIds = {"1", "5","8", "11", "17", "32"};
		*/

		String[] freshIngredientIdRanges = readFreshIngredientIdRangesFromFile("cafeteria-input.txt");
		String[] availableIngredientIds = readAvailableIngredientIdsFromFile("cafeteria-input.txt");

		int freshIngredientIdsCount = findFreshIngredientIdsCount(freshIngredientIdRanges, availableIngredientIds);
		System.out.println("Fresh Ingredient Ids Count: " + freshIngredientIdsCount);
	}
}
