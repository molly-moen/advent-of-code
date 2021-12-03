import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
	public static int puzzle1() throws FileNotFoundException {
		ArrayList<Integer> nums = readInFileLines("day1-input.txt");
		int resultNum = 0;
		int lastNum = nums.get(0);
		for(int i = 0; i < nums.size(); i++) {
			int nextNum = nums.get(i);
			if (nextNum > lastNum) {
				resultNum++;
			}
			lastNum = nextNum;
		}
		return resultNum;
	}
	
	public static int puzzle2() throws FileNotFoundException {
		ArrayList<Integer> nums = readInFileLines("day1-input.txt");
		int resultNum = 0;
		for(int i = 3; i < nums.size(); i++) {
			int firstSum = nums.get(i - 3) + nums.get(i - 2) + nums.get(i - 1);
			int secondSum = nums.get(i) + nums.get(i - 1) + nums.get(i - 2);
			if (secondSum > firstSum) {
				resultNum++;
			}
		}
		
		return resultNum;
	}
	
	public static ArrayList<Integer> readInFileLines(String filename) throws FileNotFoundException {
		ArrayList<Integer> result = new ArrayList<>();
		Scanner input = new Scanner(new File("src/inputs/" + filename));
		while(input.hasNextInt()) {
			result.add(input.nextInt());
		}
		input.close();
		return result;
	}
	
}
