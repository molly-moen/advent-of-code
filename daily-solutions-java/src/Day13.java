import java.util.*;

public class Day13 {
	public static int puzzle1(ArrayList<String> input) {
		int maxX = 0;
		int maxY = 0;
		List<int[]> coordinates = new ArrayList<>();
		int foldIndex = 0;
		for(int i = 0; i < input.size(); i++) {
			String line = input.get(i);
			if (line.length() == 0) {
				foldIndex = i + 1;
				break;
			}
			String[] pair = line.split(",");
			int x = Integer.parseInt(pair[0]);
			int y = Integer.parseInt(pair[1]);
			coordinates.add(new int[] {x, y});
			if (x > maxX) {
				maxX = x;
			}
			if (y > maxY) {
				maxY = y;
			}
		}
		boolean[][] grid = new boolean[maxY + 1][maxX + 1];
		for(int i = 0; i < coordinates.size(); i++) {
			int[] coordinate = coordinates.get(i);
			grid[coordinate[1]][coordinate[0]] = true;
		}
		String firstFold = input.get(foldIndex);
		String[] foldInstructions = firstFold.split("=");
		boolean isX = foldInstructions[0].endsWith("x");
		int index = Integer.parseInt(foldInstructions[1]);
		if (isX) {
			int previousLastIndex = grid[0].length - 1;
			boolean[][] newGrid = new boolean[grid.length][index];
			for(int row = 0; row < newGrid.length; row++) {
				for(int col = 0; col < newGrid[0].length; col++) {
					if(grid[row][col] || grid[row][previousLastIndex - col]) {
						newGrid[row][col] = true;
					}
				}
			}
			return countDots(newGrid);
		} else {
			int previousLastIndex = grid.length - 1;
			boolean[][] newGrid = new boolean[index][grid.length];
			for(int row = 0; row < newGrid.length; row++) {
				for(int col = 0; col < newGrid[0].length; col++) {
					if(grid[row][col] || grid[previousLastIndex - row][col]) {
						newGrid[row][col] = true;
					}
				}
			}
			return countDots(newGrid);
		}
	}
	
	public static void puzzle2(ArrayList<String> input) {
		int maxX = 0;
		int maxY = 0;
		List<int[]> coordinates = new ArrayList<>();
		int foldIndex = 0;
		for(int i = 0; i < input.size(); i++) {
			String line = input.get(i);
			if (line.length() == 0) {
				foldIndex = i + 1;
				break;
			}
			String[] pair = line.split(",");
			int x = Integer.parseInt(pair[0]);
			int y = Integer.parseInt(pair[1]);
			coordinates.add(new int[] {x, y});
			if (x > maxX) {
				maxX = x;
			}
			if (y > maxY) {
				maxY = y;
			}
		}
		boolean[][] grid =  new boolean[maxY + 1][maxX + 1];
		for(int i = 0; i < coordinates.size(); i++) {
			int[] coordinate = coordinates.get(i);
			grid[coordinate[1]][coordinate[0]] = true;
		}
		boolean[][] newGrid;
		for(int i = foldIndex; i < input.size(); i++) {
			String[] foldInstructions = input.get(i).split("=");
			boolean isX = foldInstructions[0].endsWith("x");
			int index = Integer.parseInt(foldInstructions[1]);
			if (isX) {
				int previousLastIndex = grid[0].length - 1;
				newGrid = new boolean[grid.length][index];
				for(int row = 0; row < newGrid.length; row++) {
					for(int col = 0; col < newGrid[0].length; col++) {
						if(grid[row][col] || grid[row][previousLastIndex - col]) {
							newGrid[row][col] = true;
						}
					}
				}
			} else {
				int previousLastIndex = grid.length - 1;
				//System.out.println("new grid dimensions: (" + index + ", " + grid.length + ")");
				newGrid = new boolean[index][grid[0].length];
				for(int row = 0; row < newGrid.length; row++) {
					for(int col = 0; col < newGrid[0].length; col++) {
						// System.out.println("checking grid at (" + row + "," + col + ") and (" + (previousLastIndex - row) + "," + col + ")");
						if(grid[row][col] || grid[previousLastIndex - row][col]) {
							newGrid[row][col] = true;
						}
					}
				}
			}
			grid = newGrid;
		}
		printGrid(grid);
	}
	
	private static void printGrid(boolean[][] grid) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if (grid[i][j]) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
	
	private static int countDots(boolean[][] grid) {
		int count = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if (grid[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
}
