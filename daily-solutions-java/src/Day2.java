import java.util.ArrayList;

public class Day2 {
	public static int puzzle1(ArrayList<String> input) {
		int horizontal = 0;
		int depth = 0;
		for(String line : input) {
			String[] parts = line.split(" ");
			if (parts[0].equals("forward")) {
				horizontal += Integer.parseInt(parts[1]);
			} else if (parts[0].equals("down")) {
				depth += Integer.parseInt(parts[1]);
			} else if (parts[0].equals("up")) {
				depth -= Integer.parseInt(parts[1]);
			}
		}
		return horizontal * depth;
	}
	
	public static int puzzle2(ArrayList<String> input) {
		int horizontal = 0;
		int depth = 0;
		int aim = 0;
		for(String line : input) {
			String[] parts = line.split(" ");
			int value = Integer.parseInt(parts[1]);
			if (parts[0].equals("forward")) {
				horizontal += value;
				depth += (aim * value);
			} else if (parts[0].equals("down")) {
				aim += value;
			} else if (parts[0].equals("up")) {
				aim -= value;
			}
		}
		return horizontal * depth;
	}

}
