import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LaboratoriesPartOne {
	public static int findSplitCount(char[][] input) {
		int splitCount = 0;
		
		int rows = input.length;
		int columns = input[0].length;
		
		for(int c = 0; c < columns; c++) {
		       if(input[0][c] == 'S') {
			       input[1][c] = '|';

			       input[2][c - 1] = '|';
			       input[2][c + 1] = '|';
			       
			       break;
		       }
		}
		
		for(int r = 2; r < (rows - 1); r++) {
			for(int c = 0; c < columns; c++) {
				if(input[r][c] == '|' && input[r + 1][c] == '.') {
					input[r + 1][c] = '|';
				}

				else if(input[r][c] == '|' && input[r + 1][c] == '^') {
				       input[r + 1][c - 1] = '|';
			       	       input[r + 1][c + 1] = '|';
				}
			}
		}		
		
		for(int r = 2; r < rows; r++) {
                        for(int c = 0; c < columns; c++) {
                                if(input[r][c] == '^' && input[r - 1][c] == '|') {
					splitCount++;
				}
                        }
                }
		
		/*	
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				System.out.print(input[r][c] + " ");
			}

			System.out.println();
		}
		*/

		return splitCount;
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
		
		int splitCount = findSplitCount(input);
		System.out.println("Split Count: " + splitCount);	
	}
}
