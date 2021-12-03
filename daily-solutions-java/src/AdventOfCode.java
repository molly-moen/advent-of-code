import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCode {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(Day1.puzzle2());
	}

	
	public static ArrayList<String> readInFileLines(String filename) throws FileNotFoundException {
		ArrayList<String> result = new ArrayList<String>();
		System.out.println(new File(".").getAbsolutePath());
		Scanner input = new Scanner(new File(filename));
		while(input.hasNextLine()) {
			result.add(input.nextLine());
		}
		return result;
	}
	
}
