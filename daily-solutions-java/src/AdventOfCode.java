import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCode {

	public static void main(String[] args) throws FileNotFoundException {
		//System.out.println(Day13.puzzle1(readInFileLines("day13-input.txt")));
		Day13.puzzle2(readInFileLines("day13-input.txt"));
	}

	
	public static ArrayList<String> readInFileLines(String filename) throws FileNotFoundException {
		ArrayList<String> result = new ArrayList<String>();
		Scanner input = new Scanner(new File("src/inputs/" + filename));
		while(input.hasNextLine()) {
			result.add(input.nextLine());
		}
		return result;
	}
	
}
