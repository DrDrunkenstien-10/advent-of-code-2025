import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrashCompactor {
	// Part 1
	public static long findGrandTotal(long[][] numbers, char[] operations) {
		long grandTotal = 0;

		int rows = numbers.length;
		int columns = numbers[0].length;

		for (int c = 0; c < columns; c++) {
			long currentColumnTotal = numbers[0][c];

			for (int r = 1; r < rows; r++) {
				if (operations[c] == '+') {
					currentColumnTotal = currentColumnTotal + numbers[r][c];
				} else {
					currentColumnTotal = currentColumnTotal * numbers[r][c];
				}
			}

			grandTotal = grandTotal + currentColumnTotal;
		}

		return grandTotal;
	}

	// Part 2
	public static long findGrandTotalForPartTwo(char[][] numbers, char[] operations) {
		long grandTotal = 0;

		int rows = numbers.length;
		int cols = numbers[0].length;

		int opIndex = operations.length - 1;
		long blockResult = 0;
		boolean inBlock = false;

		for (int c = cols - 1; c >= 0; c--) {

			boolean isBlank = true;
			for (int r = 0; r < rows; r++) {
				if (numbers[r][c] != ' ') {
					isBlank = false;
					break;
				}
			}

			if (isBlank) {
				if (inBlock) {
					grandTotal += blockResult;
					inBlock = false;
					opIndex--;
				}
				continue;
			}

			if (!inBlock) {
				char op = operations[opIndex];
				blockResult = (op == '+') ? 0 : 1;
				inBlock = true;
			}

			long n = 0;
			for (int r = 0; r < rows; r++) {
				char ch = numbers[r][c];
				if (ch >= '0' && ch <= '9') {
					n = n * 10 + (ch - '0');
				}
			}

			char op = operations[opIndex];
			if (op == '+') {
				blockResult += n;
			} else {
				blockResult *= n;
			}
		}

		if (inBlock) {
			grandTotal += blockResult;
		}

		return grandTotal;
	}

	// Part 1
	public static long[][] readNumbersAs2DArrayFromFile(String filePath) throws IOException {
		List<long[]> rows = new ArrayList<>();

		Pattern numberPattern = Pattern.compile("\\d+");

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;

			while ((line = br.readLine()) != null) {
				Matcher matcher = numberPattern.matcher(line);
				List<Long> numsInRow = new ArrayList<>();

				while (matcher.find()) {
					numsInRow.add(Long.parseLong(matcher.group()));
				}

				if (!numsInRow.isEmpty()) {
					long[] row = new long[numsInRow.size()];
					for (int i = 0; i < numsInRow.size(); i++) {
						row[i] = numsInRow.get(i);
					}

					rows.add(row);
				}
			}
		}

		long[][] result = new long[rows.size()][];
		for (int i = 0; i < rows.size(); i++) {
			result[i] = rows.get(i);
		}

		return result;
	}

	// Part 2
	public static char[][] readNumbersAs2DCharArrayFromFile(String filePath) throws IOException {
		List<char[]> rows = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {

				// Skip the operator row
				if (line.contains("+") || line.contains("*")) {
					continue;
				}

				rows.add(line.toCharArray());
			}
		}

		char[][] grid = new char[rows.size()][];
		for (int i = 0; i < rows.size(); i++) {
			grid[i] = rows.get(i);
		}

		return grid;
	}

	public static char[] readOperationsFromFile(String filePath) throws IOException {
		List<Character> ops = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;

			while ((line = br.readLine()) != null) {
				if (line.contains("+") || line.contains("*")) {

					for (char c : line.toCharArray()) {
						if (c == '+' || c == '*') {
							ops.add(c);
						}
					}

					break;
				}
			}
		}

		char[] result = new char[ops.size()];
		for (int i = 0; i < ops.size(); i++) {
			result[i] = ops.get(i);
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		/*
		 * long[][] numbers = {
		 * { 123, 328, 51, 64 },
		 * { 45, 64, 387, 23 },
		 * { 6, 98, 215, 314 }
		 * };
		 * 
		 * char[] operations = { '*', '+', '*', '+' };
		 * 
		 * long[][] numbers = readNumbersAs2DArrayFromFile("trash-compactor-input.txt");
		 * char[] operations = readOperationsFromFile("trash-compactor-input.txt");
		 * 
		 * long grandTotal = findGrandTotal(numbers, operations);
		 * System.out.println("Grand Total: " + grandTotal);
		 * 
		 * char[][] numbers = readNumbersAs2DCharArrayFromFile("test-input.txt");
		 * char[] operations = readOperationsFromFile("test-input.txt");
		 */

		char[][] numbers = readNumbersAs2DCharArrayFromFile("trash-compactor-input.txt");
		char[] operations = readOperationsFromFile("trash-compactor-input.txt");

		long grandTotal = findGrandTotalForPartTwo(numbers, operations);
		System.out.println(grandTotal);
	}
}
