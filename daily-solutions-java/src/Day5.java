import java.util.ArrayList;

public class Day5 {
	public static int puzzle1(ArrayList<String> input) {
		int[][] field = new int[1000][1000];
		for(int i = 0; i < input.size(); i++) {
			String inputLine = input.get(i);
			String[] coordinates = inputLine.split(" -> ");
			String[] firstCoord = coordinates[0].split(",");
			String[] secondCoord = coordinates[1].split(",");
			int x1 = Integer.parseInt(firstCoord[0]);
			int y1 = Integer.parseInt(firstCoord[1]);
			int x2 = Integer.parseInt(secondCoord[0]);
			int y2 = Integer.parseInt(secondCoord[1]);
			if (x1 == x2) {
				//System.out.println("pair is (" + x1 + "," + y1 + "), (" + x2 + "," + y2 + ")");
				int minY = Math.min(y1, y2);
				int maxY = Math.max(y1, y2);
				for(int j = minY; j <= maxY; j++) {
					//System.out.println("incrementing (" + x1 + ", " + j + ")");
					incrementLocation(field, x1, j);
				}
			} else if (y1 == y2) {
				int minX = Math.min(x1, x2);
				int maxX = Math.max(x1, x2);
				for(int j = minX; j <= maxX; j++) {
					incrementLocation(field, j, y1);
				}
			}
		}
		return calculateResult(field);
	}
	
	public static int puzzle2(ArrayList<String> input) {

		int[][] field = new int[1000][1000];
		for(int i = 0; i < input.size(); i++) {
			String inputLine = input.get(i);
			String[] coordinates = inputLine.split(" -> ");
			String[] firstCoord = coordinates[0].split(",");
			String[] secondCoord = coordinates[1].split(",");
			int x1 = Integer.parseInt(firstCoord[0]);
			int y1 = Integer.parseInt(firstCoord[1]);
			int x2 = Integer.parseInt(secondCoord[0]);
			int y2 = Integer.parseInt(secondCoord[1]);
			if (x1 == x2) {
				int minY = Math.min(y1, y2);
				int maxY = Math.max(y1, y2);
				for(int j = minY; j <= maxY; j++) {
					incrementLocation(field, x1, j);
				}
			} else if (y1 == y2) {
				int minX = Math.min(x1, x2);
				int maxX = Math.max(x1, x2);
				for(int j = minX; j <= maxX; j++) {
					incrementLocation(field, j, y1);
				}
			} else {
				int xDistance = Math.abs(x1 - x2) + 1;
				int yDistance = Math.abs(y1 - y2) + 1;
				int xIncrementor = x1 > x2 ? -1 : 1;
				int yIncrementor = y1 > y2 ? -1 : 1;
				int currentX = x1;
				int currentY = y1;
				int numLocations = xDistance > yDistance ? xDistance : yDistance;
				for(int j = 0; j < numLocations; j++) {
					incrementLocation(field, currentX, currentY);
					currentX = currentX + xIncrementor;
					currentY = currentY + yIncrementor;
				}
			}
		}
		return calculateResult(field);
	}
	
	private static void incrementLocation(int[][] field, int x, int y) {
		field[y][x] = field[y][x] + 1;
	}
	
	private static int calculateResult(int[][] field) {
		int result = 0;
		System.out.println(field.length);
		System.out.println(field[0].length);
		for(int i = 0; i < field.length; i++) {
			for(int j = 0; j < field[0].length; j++) {
				if (field[i][j] > 1) {
					result++;
				}
			}
		}
		return result;
	}
}
