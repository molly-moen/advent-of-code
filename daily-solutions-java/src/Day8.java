import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.*;

public class Day8 {
	public static int puzzle1(ArrayList<String> input) {
		int result = 0;
		for(int i = 0; i < input.size(); i++) {
			String line = input.get(i);
			String[] segments = line.split(" \\| ");
			String[] nums = segments[1].split(" ");
			for(int j = 0; j < nums.length; j++) {
				int length = nums[j].length();
				if(length <= 4 || length == 7) {
					result++;
				}
			}
		}
		return result;
	}
	
	public static int puzzle2(ArrayList<String> input) {
		int result = 0;
		Map<String, Integer> numMapping = getNumMapping();
		for(int i = 0; i < input.size(); i++) {
			String line = input.get(i);
			String[] segments = line.split(" \\| ");
			String[] inputNums = segments[0].split(" ");
			String[] resultNums = segments[1].split(" ");
			Map<Integer, List<List<Character>>> formattedInput = getFormattedInput(inputNums);
			Map<Character, Character> charMapping = getCharMapping(formattedInput);
			result += getResult(charMapping, resultNums, numMapping);
			
		}
		return result;
	}
	
	private static Map<Integer, List<List<Character>>> getFormattedInput(String[] inputNums) {
		HashMap<Integer, List<List<Character>>> input = new HashMap<>();
		for(int i = 0; i < inputNums.length; i++) {
			List<Character> sortedInput = getSortedChars(inputNums[i]);
			if (!input.containsKey(sortedInput.size())) {
				input.put(sortedInput.size(), new ArrayList<List<Character>>());
			}
			List<List<Character>> inputPerLength = input.get(sortedInput.size());
			inputPerLength.add(sortedInput);
		}
		return input;
	}
	
	private static List<Character> getSortedChars(String s) {
		char[] charArr = s.toCharArray();
		List<Character> chars = new ArrayList<Character>();
		for(int i = 0; i < charArr.length; i++) {
			chars.add(charArr[i]);
		}
		//Collections.sort(chars);
		return chars;
	}
	
	private static Map<Character, Character> getCharMapping(Map<Integer, List<List<Character>>> input) {
		Map<Character, Character> result = new HashMap<>();
		// get 'a'
		List<Character> lengthTwo = input.get(2).get(0);
		List<Character> lengthThree = input.get(3).get(0);
		Character a = lengthThree.stream()
	            .filter(element -> !lengthTwo.contains(element))
	            .collect(Collectors.toList()).get(0);
		result.put(a, 'a');
		// get 'd'
		List<Character> firstLengthFive = input.get(5).get(0);
		List<Character> secondLengthFive = input.get(5).get(1);
		List<Character> thirdLengthFive = input.get(5).get(2);
		List<Character> lengthFour = input.get(4).get(0);
		Character d = lengthFour.stream()
				.filter(element -> firstLengthFive.contains(element) && secondLengthFive.contains(element) && thirdLengthFive.contains(element))
	            .collect(Collectors.toList()).get(0);
		result.put(d, 'd');
		// get 'g'
		Character g = firstLengthFive.stream()
				.filter(element -> secondLengthFive.contains(element) && thirdLengthFive.contains(element) && element != a && element != d)
	            .collect(Collectors.toList()).get(0);
		result.put(g, 'g');
		// get 'b'
		Character b = lengthFour.stream()
				.filter(element -> element != d && !lengthTwo.contains(element))
	            .collect(Collectors.toList()).get(0);
		result.put(b, 'b');
		// get 'f'
		List<Character> firstLengthSix = input.get(6).get(0);
		List<Character> secondLengthSix = input.get(6).get(1);
		List<Character> thirdLengthSix = input.get(6).get(2);
		Character f = lengthTwo.stream()
				.filter(element -> firstLengthSix.contains(element) && secondLengthSix.contains(element) && thirdLengthSix.contains(element))
	            .collect(Collectors.toList()).get(0);
		result.put(f, 'f');
		// get 'c'
		Character c = lengthTwo.stream()
				.filter(element -> element != f)
	            .collect(Collectors.toList()).get(0);
		result.put(c, 'c');
		// get 'e'
		String allChars = "abcdefg";
		for(int i = 0; i < allChars.length(); i++) {
			char nextChar = allChars.charAt(i);
			if (!result.containsKey(nextChar)) {
				result.put(nextChar, 'e');
			}
		}
		return result;
	}
	
	private static int getResult(Map<Character, Character> mapping, String[] numsToConvert, Map<String, Integer> numMapping) {
		String result = "";
		for(int i = 0; i < numsToConvert.length; i++) {
			List<Character> converted = new ArrayList<Character>();
			String toConvert = numsToConvert[i];
			for(int j = 0; j < toConvert.length(); j++) {
				converted.add(mapping.get(toConvert.charAt(j)));
			}
			Collections.sort(converted);
			String convertedStr = "";
			for(int j = 0; j < converted.size(); j++) {
				convertedStr += converted.get(j);
			}
			result += numMapping.get(convertedStr);
		}
		return Integer.parseInt(result);
	}
	
	private static HashMap<String, Integer> getNumMapping() {
		HashMap<String, Integer> mapping = new HashMap<>();
		mapping.put("abcefg", 0);
		mapping.put("cf", 1);
		mapping.put("acdeg", 2);
		mapping.put("acdfg", 3);
		mapping.put("bcdf", 4);
		mapping.put("abdfg", 5);
		mapping.put("abdefg", 6);
		mapping.put("acf", 7);
		mapping.put("abcdefg", 8);
		mapping.put("abcdfg", 9);
		return mapping;
	}
}
