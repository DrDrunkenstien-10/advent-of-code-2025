import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LaboratoriesPartTwo {
	public static long findTotalTimelines(char[][] input) {	
		int rows = input.length;
		int columns = input[0].length;
		
		long[][] timelines = new long[rows][columns];

		for(int c = 0; c < columns; c++) {
		       if(input[0][c] == 'S') {
			       timelines[0][c] = 1;
			       break;
		       }
		}
		
		for(int r = 0; r < (rows - 1); r++) {
			for(int c = 0; c < columns; c++) {
				long count = timelines[r][c];

				if(count == 0) {
					continue;
				}

				if(input[r + 1][c] == '.') {
					timelines[r + 1][c] += count;
				}

				else if(input[r + 1][c] == '^') {
				       if(c - 1 >= 0) {
					       timelines[r + 1][c - 1] += count;
				       }

				       if(c + 1 < columns) {
					       timelines[r + 1][c + 1] += count;
				       }
				}
			}
		}		
		
		long totalTimelines = 0;
		
		for(int c = 0; c < columns; c++) {
			totalTimelines += timelines[rows - 1][c];
		}	
	       	
		return totalTimelines;
	}

	public static char[][] readInputFromFile(String fileName) {
		List<char[]> rows = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				rows.add(line.toCharArray());
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading file", e);
		}

		char[][] input = new char[rows.size()][];
		for (int i = 0; i < rows.size(); i++) {
			input[i] = rows.get(i);
		}

		return input;
	}

	public static void main(String[] args) {
		char[][] input = readInputFromFile("laboratories-input.txt");
		
		long totalTimelines = findTotalTimelines(input);
		System.out.println("Total Timeline: " + totalTimelines);	
	}
}
